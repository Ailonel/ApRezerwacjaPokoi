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

            Connection con = PolaczenieDB.getConnection();

//Tworzenie rekordu
            if (action.equals("insert")) {
                String nazwaO = request.getParameter("nazwaO");
                String numerP = request.getParameter("numerP");
                String pokoj = "0";
                
                Statement stm = con.createStatement();

                //ResultSet rs = stm.executeQuery("select p.id_pokoju from pokoj p left join obiekt o on p.id_obiektu=o.id_obiektu where p.numer_pokoju= "+numerP+ "and o.nazwa_obiektu='"+nazwaO+"'");
                ResultSet rs = stm.executeQuery("select p.id_pokoju from pokoj p left join obiekt o on p.id_obiektu=o.id_obiektu where o.nazwa_obiektu = '"+nazwaO+"' and p.numer_pokoju = "+numerP);

                while (rs.next())
                    pokoj = rs.getString("id_pokoju");
                

                PreparedStatement st = con.prepareStatement("Insert into Rezerwacja values(?,CURDATE(),?,?,?,?,?)");

                //st.setString(1, "NULL");
                st.setNull(1, java.sql.Types.INTEGER);
                st.setString(2, dataP);
                st.setString(3, dataW);
                st.setString(4, "1");
                st.setString(5, "1");
                st.setString(6, pokoj);
                st.executeUpdate();
                System.out.print(st);
            } else if (action.equals("select")) {
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
                    out.println("</tr>");
                }
                out.println("</tbody>");
                out.println("</table>");
            }
            //int i = st.executeUpdate();

            //if (i > 0) {
            //out.println("Rezerwacja zakończona pomyślnie!");
            //response.getWriter().write("Rezerwacja zakończona pomyślnie!");
            //}
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
