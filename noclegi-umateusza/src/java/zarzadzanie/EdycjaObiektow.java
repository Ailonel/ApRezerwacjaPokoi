/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zarzadzanie;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import java.util.*;

/**
 *
 * @author Mateusz
 */
@WebServlet(name = "EdycjaObiektow", urlPatterns = {"/EdycjaObiektow"})
public class EdycjaObiektow extends HttpServlet {

    private CRUDObiekt crud = new CRUDObiekt();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getParameter("action") != null) {
            List<Obiekt> lista = new ArrayList<Obiekt>();
            String action = (String) request.getParameter("action");
            Gson gson = new Gson();
            response.setContentType("application/json");

            if (action.equals("list")) {
                try {
                    lista = crud.WszystkieObiekty();
                    JsonElement element = gson.toJsonTree(lista, new TypeToken<List<Obiekt>>() {
                    }.getType());
                    JsonArray jsonArray = element.getAsJsonArray();
                    String listData = jsonArray.toString();
                    listData = "{\"Result\":\"OK\",\"Records\":" + listData + "}";

                    response.getWriter().print(listData);

                } catch (Exception ex) {
                    String error = "{\"Result\":\"ERROR\",\"Message\":" + ex.getMessage() + "}";
                    response.getWriter().print(error);
                    ex.printStackTrace();
                }
            }
            else if (action.equals("create") || action.equals("update")) {
                Obiekt obiekt = new Obiekt();
                if (request.getParameter("id_obiektu") != null) {
                    int id_obiektu = Integer.parseInt(request.getParameter("id_obiektu"));
                    obiekt.setIdObiektu(id_obiektu);
                }
                if (request.getParameter("nazwa_obiektu") != null) {
                    String nazwa_obiektu = (String) request.getParameter("nazwa_obiektu");
                    obiekt.setNazwaObiektu(nazwa_obiektu);
                }
                if (request.getParameter("kod_pocztowy") != null) {
                    String kod_pocztowy = (String) request.getParameter("kod_pocztowy");
                    obiekt.setKodPocztowy(kod_pocztowy);
                }
                if (request.getParameter("miejscowosc") != null) {
                    String miejscowosc = (String) request.getParameter("miejscowosc");
                    obiekt.setMiejscowosc(miejscowosc);
                }
                if (request.getParameter("ulica") != null) {
                    String ulica = (String) request.getParameter("ulica");
                    obiekt.setUlica(ulica);
                }
                try {
                    if (action.equals("create")) {
                        crud.DodajObiekt();
                        lista.add(obiekt);
                        String json = gson.toJson(obiekt);
                        String listData = "{\"Result\":\"OK\",\"Record\":" + json + "}";
                        response.getWriter().print(listData);
                    } else if (action.equals("update")){
                        crud.EdytujObiekt(obiekt);
                        String listData = "{\"Result\":\"OK\"}";
                        response.getWriter().print(listData);
                    }
                } catch (Exception ex) {
                    String error = "{\"Result\":\"ERROR\",\"Message\":" + ex.getStackTrace().toString() + "}";
                    response.getWriter().print(error);
                }
            }
            else if (action.equals("delete"))
                if(request.getParameter("id_obiektu")!=null){
                    String id_obiektu = (String)request.getParameter("id_obiektu");
                    crud.UsunObiekt(Integer.parseInt(id_obiektu));
                    String listData = "{\"Result\":\"OK\"}";
                    response.getWriter().print(listData);
                }

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
