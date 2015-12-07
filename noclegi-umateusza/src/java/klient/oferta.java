/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klient;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
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
@WebServlet(name = "oferta", urlPatterns = {"/oferta"})
public class oferta extends HttpServlet {

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
            /* TODO output your page here. You may use following sample code. */
            
            String action = request.getParameter("action");
            Connection con = PolaczenieDB.getConnection();
            
            if (action.equals("oferta")) {
                Statement st = con.createStatement();
                String querry = "select p.numer_pokoju, o.nazwa_obiektu, p.liczba_osob, p.cena_za_dzien from pokoj p left join obiekt o on p.id_obiektu = o.id_obiektu";
                
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
                    out.println("<td><button id=\"pGaleria\"type=\"button\" data-toggle=\"modal\" class=\"btn btn-default\" data-target=\"#Galeria\">Galeria</button></td>");
                    //out.println("<td><input type = submit id=\"pRezerwuj\"/><td>");
                }
                out.println("</tbody>");
                out.println("</table>");
            }
            if(action.equals("galeria"))
            {
                String nazwaO = request.getParameter("nazwaO");
                String numerP = request.getParameter("numerP");
                String pokoj = "select p.id_pokoju from pokoj p left join obiekt o on p.id_obiektu=o.id_obiektu where o.nazwa_obiektu = '" + nazwaO + "' and p.numer_pokoju = " + numerP;
                Statement stm = con.createStatement();
                
                
                ResultSet rs = stm.executeQuery("select zdjecie from Pokoj_Zdjecie where miniatura=false and id_pokoju =("+pokoj+")");
                
                while (rs.next()) {
                    out.println("<div class=\"col-md-6\"><img class = \"img-thumbnail\" src=\""+rs.getString("zdjecie")+"\" class=\"img-responsive\" alt=\"Budynek\"></div>");
                }       
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
