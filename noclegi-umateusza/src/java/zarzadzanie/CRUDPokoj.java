/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zarzadzanie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import polaczenie.PolaczenieDB;

/**
 *
 * @author Mateusz
 */
public class CRUDPokoj {
    
    Connection con = PolaczenieDB.getConnection();
    String encoding = "UTF-8";

    public void DodajPokoj(Pokoj pokoj) {
        try {
            PreparedStatement st = con.prepareStatement("insert into Pokoj values (?,?,?,?,?,?,?)");
            st.setInt(1, pokoj.getIdPokoju());
            st.setInt(2, pokoj.getCenaZaDzien());
            st.setInt(3, pokoj.getNumerPokoju());
            st.setInt(4, pokoj.getLiczbaOsob());
            st.setInt(5, pokoj.getMiejsceParking());
            st.setInt(6, pokoj.getMiejsceJadalnia());
            st.setInt(7, pokoj.getIdObiektu());
            st.executeUpdate();
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

    public List<Pokoj> WszystkiePokoje() {
        List<Pokoj> pokoje = new ArrayList<Pokoj>();
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from pokoj");
            while (rs.next()) {
                Pokoj pokoj = new Pokoj();
                pokoj.setIdPokoju(rs.getInt("id_pokoju"));
                pokoj.setCenaZaDzien(rs.getInt("cena_za_dzien"));
                pokoj.setNumerPokoju(rs.getInt("numer_pokoju"));
                pokoj.setLiczbaOsob(rs.getInt("liczba_osob"));
                pokoj.setMiejsceParking(rs.getInt("miejsce_parking"));
                pokoj.setMiejsceJadalnia(rs.getInt("miejsce_jadalnia"));
                pokoj.setIdObiektu(rs.getInt("id_obiektu"));
                pokoje.add(pokoj);
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }
        return pokoje;
    }

    public void EdytujPokoj(Pokoj pokoj) {
        try {
            PreparedStatement st = con.prepareStatement("update pokoj set id_pokoju = ?, cena_za_dzien = ?, numer_pokoju = ?, liczba_osob=?, miejsce_parking=?, miejsce_jadalnia=?, id_obiektu=?  where id_obiektu = ?");
            st.setInt(1, pokoj.getIdPokoju());
            st.setInt(2, pokoj.getCenaZaDzien());
            st.setInt(3, pokoj.getNumerPokoju());
            st.setInt(4, pokoj.getLiczbaOsob());
            st.setInt(5, pokoj.getMiejsceParking());
            st.setInt(6, pokoj.getMiejsceJadalnia());
            st.setInt(7, pokoj.getIdObiektu());
            st.executeUpdate();
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

    public void UsunPokoj(int id_pokoju) {
        try {
            PreparedStatement st = con.prepareStatement("delete from Pokoj where id_pokoju = ?");
            st.setInt(1, id_pokoju);
            st.executeUpdate();
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }
}
