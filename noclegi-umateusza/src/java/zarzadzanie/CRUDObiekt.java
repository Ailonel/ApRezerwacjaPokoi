/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zarzadzanie;

import polaczenie.PolaczenieDB;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mateusz
 */
public class CRUDObiekt {

    Connection con = PolaczenieDB.getConnection();
    String encoding = "UTF-8";
    String nazwa_obiektu, kod_pocztowy, miejscowosc, ulica;
    int id_obiektu;

    public void DodajObiekt() {
        try {
            PreparedStatement st = con.prepareStatement("insert into Obiekt values (?,?,?,?,?)");
            st.setNull(1, java.sql.Types.NULL);
            st.setString(2, nazwa_obiektu);
            st.setString(3, kod_pocztowy);
            st.setString(4, miejscowosc);
            st.setString(5, ulica);
            st.executeUpdate();
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }
    
    public List<Obiekt> WszystkieObiekty() {
        List<Obiekt> obiekty = new ArrayList<Obiekt>();
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from Obiekt");
            while (rs.next()) {
                Obiekt obiekt = new Obiekt();
                obiekt.setIdObiektu(rs.getInt("id_obiektu"));
                obiekt.setNazwaObiektu(rs.getString("nazwa_obiektu"));
                obiekt.setKodPocztowy(rs.getString("kod_pocztowy"));
                obiekt.setMiejscowosc(rs.getString("miejscowosc"));
                obiekt.setUlica(rs.getString("ulica"));
                obiekty.add(obiekt);
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }
        return obiekty;
    }

    public void EdytujObiekt(Obiekt obiekt){
        try {
            PreparedStatement st = con.prepareStatement("update obiekt set nazwa_obiektu = ?, kod_pocztowy = ?, miejscowosc = ?, ulica=? where id_obiektu = ?");
            st.setString(1, obiekt.getNazwaObiektu());
            st.setString(2, obiekt.getKodPocztowy());
            st.setString(3, obiekt.getMiejscowosc());
            st.setString(4, obiekt.getUlica());
            st.setInt(5, obiekt.getIdObiektu());
            st.executeUpdate();
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }
    
    public void UsunObiekt(int id_obiektu) {
        try {
            PreparedStatement st = con.prepareStatement("delete from Obiekt where id_obiektu = ?");
            st.setInt(1, id_obiektu);
            st.executeUpdate();
        } catch (SQLException se) {
            se.printStackTrace();
        }
    } 
}
