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
import javax.servlet.http.Cookie;
import javax.servlet.RequestDispatcher;
import polaczenie.PolaczenieDB;

/**
 *
 * @author Mateusz
 */
@WebServlet(urlPatterns = {"/logowanie"})
public class logowanie extends HttpServlet {

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
        boolean status;
        try (PrintWriter out = response.getWriter()) {
            String email = request.getParameter("email");
            String haslo = request.getParameter("haslo");
            
            //połączenie z bazą danych
            Connection con = PolaczenieDB.getConnection();
            
            //szukanie użytkownika
            PreparedStatement st = con.prepareStatement("select * from Uzytkownik where email=? and haslo=?");
            st.setString(1, email);
            st.setString(2, haslo);    
            ResultSet rs=st.executeQuery();
            status=rs.next();
            
            //tworzenie ciasteczka lub wiadomość o niepowodzeniu
            if(status){
                Cookie logowanie = new Cookie ("user", email);
                logowanie.setMaxAge(60*10);
                response.addCookie(logowanie);
                out.println("0");
            }
            else{
                out.println("<div class =\"alert alert-warning\">");
                out.println("<strong>Błąd:<strong> Niepoprawny login lub hasło</font>");
                out.println("</div>");
            }
        }catch (Exception se) {
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
