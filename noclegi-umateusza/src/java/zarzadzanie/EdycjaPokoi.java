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
@WebServlet(name = "EdycjaPokoi", urlPatterns = {"/EdycjaPokoi"})
public class EdycjaPokoi extends HttpServlet {

    private CRUDPokoj crud = new CRUDPokoj();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getParameter("action") != null) {
            List<Pokoj> lista = new ArrayList<Pokoj>();
            String action = (String) request.getParameter("action");
            Gson gson = new Gson();
            response.setContentType("application/json");

            if (action.equals("list")) {
                try {
                    lista = crud.WszystkiePokoje();
                    JsonElement element = gson.toJsonTree(lista, new TypeToken<List<Pokoj>>() {}.getType());
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
                Pokoj pokoj = new Pokoj();
                if (request.getParameter("id_pokoju") != null) {
                    int id_pokoju = Integer.parseInt(request.getParameter("id_pokoju"));
                    pokoj.setIdPokoju(id_pokoju);
                }
                if (request.getParameter("cena_za_dzien") != null) {
                    int cena_za_dzien = Integer.parseInt(request.getParameter("cena_za_dzien"));
                    pokoj.setCenaZaDzien(cena_za_dzien);
                }
                if (request.getParameter("numer_pokoju") != null) {
                    int numer_pokoju = Integer.parseInt(request.getParameter("numer_pokoju"));
                    pokoj.setNumerPokoju(numer_pokoju);
                }
                if (request.getParameter("liczba_osob") != null) {
                    int liczba_osob = Integer.parseInt(request.getParameter("liczba_osob"));
                    pokoj.setLiczbaOsob(liczba_osob);
                }
                if (request.getParameter("miejsce_parking") != null) {
                    int miejsce_parking = Integer.parseInt(request.getParameter("miejsce_parking"));
                    pokoj.setMiejsceParking(miejsce_parking);
                }
                if (request.getParameter("miejsce_jadalnia") != null) {
                    int miejsce_jadalnia = Integer.parseInt(request.getParameter("miejsce_jadalnia"));
                    pokoj.setMiejsceJadalnia(miejsce_jadalnia);
                }
                if (request.getParameter("id_obiektu") != null) {
                    int id_obiektu = Integer.parseInt(request.getParameter("id_obiektu"));
                    pokoj.setIdObiektu(id_obiektu);
                }
                
                try {
                    if (action.equals("create")) {
                        crud.DodajPokoj(pokoj);
                        lista.add(pokoj);
                        String json = gson.toJson(pokoj);
                        String listData = "{\"Result\":\"OK\",\"Record\":" + json + "}";
                        response.getWriter().print(listData);
                    } else if (action.equals("update")){
                        crud.EdytujPokoj(pokoj);
                        String listData = "{\"Result\":\"OK\"}";
                        response.getWriter().print(listData);
                    }
                } catch (Exception ex) {
                    String error = "{\"Result\":\"ERROR\",\"Message\":" + ex.getStackTrace().toString() + "}";
                    response.getWriter().print(error);
                }
            }
            else if (action.equals("delete"))
                if(request.getParameter("id_pokoju")!=null){
                    String id_pokoju = (String)request.getParameter("id_pokoju");
                    crud.UsunPokoj(Integer.parseInt(id_pokoju));
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
