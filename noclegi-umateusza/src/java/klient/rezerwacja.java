package klient;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import polaczenie.PolaczenieDB;
import javax.servlet.http.Cookie;

/**
 *
 * @author Mateusz
 */
@WebServlet(name = "rezerwacja", urlPatterns = {"/rezerwacja"})
public class rezerwacja extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String dataP = request.getParameter("dataP");
            String dataW = request.getParameter("dataW");
            String action = request.getParameter("action");

            Cookie loginCookie = null;
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("user")) {
                        loginCookie = cookie;
                        break;
                    }
                }
            }
            String nazwa = loginCookie.getValue();
            Connection con = PolaczenieDB.getConnection();

            //Tworzenie Rezerwacji
            if (action.equals("insert")) {
                String nazwaO = request.getParameter("nazwaO");
                String numerP = request.getParameter("numerP");
                String pokoj = "0";

                Statement stm = con.createStatement();
                ResultSet rs = stm.executeQuery("select p.id_pokoju from pokoj p left join obiekt o on p.id_obiektu=o.id_obiektu where o.nazwa_obiektu = '" + nazwaO + "' and p.numer_pokoju = " + numerP);

                while (rs.next()) {
                    pokoj = rs.getString("id_pokoju");
                }

                PreparedStatement st = con.prepareStatement("Insert into Rezerwacja values(?,CURDATE(),?,?,?,(select id_uzytkownika from uzytkownik where email = '" + nazwa + "'),?)");

                st.setNull(1, java.sql.Types.INTEGER);
                st.setString(2, dataP);
                st.setString(3, dataW);
                st.setString(4, "1");
                st.setString(5, pokoj);
                st.executeUpdate();
                System.out.print(st);
            }
            //Usuwanie rezerwacji
            else if (action.equals("delete")) {
                String nazwaO = request.getParameter("nazwaO");
                String numerP = request.getParameter("numerP");
                String pokoj = "0";

                Statement stm = con.createStatement();
                ResultSet rs = stm.executeQuery("select p.id_pokoju from pokoj p left join obiekt o on p.id_obiektu=o.id_obiektu where o.nazwa_obiektu = '" + nazwaO + "' and p.numer_pokoju = " + numerP);

                while (rs.next()) {
                    pokoj = rs.getString("id_pokoju");
                }

                Statement st = con.createStatement();
                st.executeUpdate("Delete from rezerwacja where id_uzytkownika = (select id_uzytkownika from uzytkownik where email = '" + nazwa + "') and id_pokoju =" + pokoj + " and data_przyjazdu = '" + dataP + "' and data_wyjazdu ='" + dataW + "'");
            } 
            //Wyświetlanie wolnych pokoi w danym terminie
            else if (action.equals("select")) {
                Statement st = con.createStatement();
                //String querry = "select distinct numer_pokoju, id_obiektu, liczba_osob, cena_za_dzien from pokoj p left join rezerwacja r on r.id_pokoju = p.id_pokoju where ('" + dataP + "' < r.data_przyjazdu or '" + dataP + "' >= r.data_wyjazdu) and ('" + dataW + "' <= r.data_przyjazdu or '" + dataW + "' >= r.data_wyjazdu) order by id_obiektu, numer_pokoju";
                String querry = "select p.numer_pokoju, o.nazwa_obiektu, p.liczba_osob, p.cena_za_dzien from pokoj p left join obiekt o on p.id_obiektu = o.id_obiektu where p.id_pokoju not in (select id_pokoju from rezerwacja where data_przyjazdu <= '" + dataW + "' and data_wyjazdu >='" + dataP + "')";

                ResultSet rs = st.executeQuery(querry);
                out.println("<table id =\"tabela\" class = \"table\">");
                out.println("<thead>");
                out.println("<tr>");
                out.println("<th>Grafika</th>");
                out.println("<th>Nazwa Obiektu</th>");
                out.println("<th>Numer pokoju</th>");
                out.println("<th>Liczba osób</th>");
                out.println("<th>Cena za dzień (zł)</th>");
                out.println("<th></th>");
                out.println("</tr>");
                out.println("</thead>");
                out.println("<tbody>");
                while (rs.next()) {
                    out.println("<tr class = \'clickable-row\'>");
                    //out.println("<td><img src=\"/Foto/U_Mateusza/1.jpg\">");
                    out.println("<td><img src=\"http://www.bieszczadypolska.pl/panelfiles/umateusza_panel_69595516.jpg\">");
                    out.println("<td class = \'nazwa\'>" + rs.getString("nazwa_obiektu") + "</td>");
                    out.println("<td class = \' numer\'>" + rs.getString("numer_pokoju") + "</td>");
                    out.println("<td>" + rs.getString("liczba_osob") + "</td>");
                    out.println("<td>" + rs.getString("cena_za_dzien") + "</td>");
                    out.println("<td><button id=\"pRezerwuj\"type=\"button\" class=\"btn btn-default\">Rezerwuj</button></td>");
                    //out.println("<td><input type = submit id=\"pRezerwuj\"/><td>");
                }
                out.println("</tbody>");
                out.println("</table>");
            } else if(action.equals("update")){
                String nazwaO = request.getParameter("nazwaO");
                String numerP = request.getParameter("numerP");
                String pokoj = "select p.id_pokoju from pokoj p left join obiekt o on p.id_obiektu=o.id_obiektu where o.nazwa_obiektu = '" + nazwaO + "' and p.numer_pokoju = " + numerP;

                Statement st = con.createStatement();
                st.executeUpdate("update rezerwacja set rezerwacja.potwierdzenie = 0 where id_uzytkownika = (select id_uzytkownika from uzytkownik where email = '" + nazwa + "') and id_pokoju =(" + pokoj + ") and data_przyjazdu = '" + dataP + "' and data_wyjazdu ='" + dataW + "'");
            
            }

//Przeglądanie dokonanych rezerwacji
            else if (action.equals("obecne")) {
                Statement st = con.createStatement();
                String querry = "select r.id_rezerwacji, p.numer_pokoju, o.nazwa_obiektu, p.liczba_osob, p.cena_za_dzien, r.data_rezerwacji, r.data_przyjazdu, r.data_wyjazdu, r.potwierdzenie from rezerwacja r left join pokoj p on r.id_pokoju=p.id_pokoju, obiekt o where r.id_uzytkownika = (select id_uzytkownika from uzytkownik where email = '" + nazwa + "') and r.data_wyjazdu >= CURDATE() order by r.data_rezerwacji desc, r.data_przyjazdu desc;";
                ResultSet rs = st.executeQuery(querry);
                
                out.println("<table id =\"tabela\" class = \"table\">");
                out.println("<thead>");
                out.println("<tr>");
                out.println("<th>Grafika</th>");
                out.println("<th>Nazwa Obiektu</th>");
                out.println("<th>Numer pokoju</th>");
                out.println("<th>Liczba osób</th>");
                out.println("<th>Cena za dzień (zł)</th>");
                out.println("<th>Data Rezerwacji</th>");
                out.println("<th>Data Przyjazdu</th>");
                out.println("<th>Data Wyjazdu</th>");
                out.println("<th>Potwierdzenie</th>");
                out.println("<th></th>");
                out.println("</tr>");
                out.println("</thead>");
                out.println("<tbody>");
                while (rs.next()) {
                    out.println("<tr class = \'clickable-row\'>");
                    //out.println("<td><img src=\"/Foto/U_Mateusza/1.jpg\">");
                    out.println("<td><img src=\"http://www.bieszczadypolska.pl/panelfiles/umateusza_panel_69595516.jpg\">");
                    out.println("<td class = \'nazwa\'>" + rs.getString("nazwa_obiektu") + "</td>");
                    out.println("<td class = \' numer\'>" + rs.getString("numer_pokoju") + "</td>");
                    out.println("<td>" + rs.getString("liczba_osob") + "</td>");
                    out.println("<td>" + rs.getString("cena_za_dzien") + "</td>");
                    out.println("<td>" + rs.getString("data_rezerwacji") + "</td>");
                    out.println("<td class = \'dPrzyjazdu\'>" + rs.getString("data_przyjazdu") + "</td>");
                    out.println("<td class = \'dWyjazdu\'>" + rs.getString("data_wyjazdu") + "</td>");
                    if (rs.getInt("potwierdzenie") == 1) {
                        out.println("<td>Niepotwierdzona</td>");
                        out.println("<td> <button type=\"button\" id = \"pPotwierdz\" class=\"btn btn-info\">Potwierdź</button><br>");
                        out.println("<button type=\"button\" id = \"pUsun\" class=\"btn btn-danger\">Usuń</button></td>");
                    } else {
                        out.println("<td>Potwierdzona</td>");
                    }
                    out.println("<td></td>");

                    out.println("</tr>");
                }
                out.println("</tbody>");
                out.println("</table>");
            } else if (action.equals("zakonczone")) {
                Statement st = con.createStatement();
                String querry = "select r.id_rezerwacji, p.numer_pokoju, o.nazwa_obiektu, p.liczba_osob, p.cena_za_dzien, r.data_rezerwacji, r.data_przyjazdu, r.data_wyjazdu, r.potwierdzenie from rezerwacja r left join pokoj p on r.id_pokoju=p.id_pokoju, obiekt o where r.id_uzytkownika = (select id_uzytkownika from uzytkownik where email = '" + nazwa + "') and r.data_wyjazdu < CURDATE()";
                ResultSet rs = st.executeQuery(querry);
                
                out.println("<table id =\"tabela\" class = \"table\">");
                out.println("<thead>");
                out.println("<tr>");
                out.println("<th>Grafika</th>");
                out.println("<th>Nazwa Obiektu</th>");
                out.println("<th>Numer pokoju</th>");
                out.println("<th>Liczba osób</th>");
                out.println("<th>Cena za dzień (zł)</th>");
                out.println("<th>Data Rezerwacji</th>");
                out.println("<th>Data Przyjazdu</th>");
                out.println("<th>Data Wyjazdu</th>");
                out.println("</tr>");
                out.println("</thead>");
                out.println("<tbody>");
                while (rs.next()) {
                    out.println("<tr class = \'clickable-row\'>");
                    //out.println("<td><img src=\"/Foto/U_Mateusza/1.jpg\">");
                    out.println("<td><img src=\"http://www.bieszczadypolska.pl/panelfiles/umateusza_panel_69595516.jpg\">");
                    out.println("<td class = \'nazwa\'>" + rs.getString("nazwa_obiektu") + "</td>");
                    out.println("<td class = \' numer\'>" + rs.getString("numer_pokoju") + "</td>");
                    out.println("<td>" + rs.getString("liczba_osob") + "</td>");
                    out.println("<td>" + rs.getString("cena_za_dzien") + "</td>");
                    out.println("<td>" + rs.getString("data_rezerwacji") + "</td>");
                    out.println("<td class = \'dPrzyjazdu\'>" + rs.getString("data_przyjazdu") + "</td>");
                    out.println("<td class = \'dWyjazdu\'>" + rs.getString("data_wyjazdu") + "</td>");
                    out.println("</tr>");
                                    System.out.println(rs.getString("numer_pokoju"));
                }
                out.println("</tbody>");
                out.println("</table>");

            }
        } catch (Exception se) {
            se.printStackTrace();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
