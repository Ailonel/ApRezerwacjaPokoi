/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zarzadzanie;

/**
 *
 * @author Mateusz
 */
public class Obiekt {
    private String encoding = "UTF-8";
    private int id_obiektu;
    private String nazwa_obiektu;
    private String kod_pocztowy;
    private String miejscowosc;
    private String ulica;

    public int getIdObiektu() {
        return id_obiektu;
    }

    public void setIdObiektu(int id_obiektu) {
        this.id_obiektu = id_obiektu;
    }
    public String getNazwaObiektu() {
        return nazwa_obiektu;
    }

    public void setNazwaObiektu(String nazwa_obiektu) {
        this.nazwa_obiektu = nazwa_obiektu;
    }

    public String getKodPocztowy() {
        return kod_pocztowy;
    }

    public void setKodPocztowy(String kod_pocztowy) {
        this.kod_pocztowy = kod_pocztowy;
    }

    public String getMiejscowosc() {
        return miejscowosc;
    }

    public void setMiejscowosc(String miejscowosc) {
        this.miejscowosc = miejscowosc;
    }

    public String getUlica() {
        return ulica;
    }

    public void setUlica(String ulica) {
        this.ulica = ulica;
    }
    @Override
    public String toString(){
        return "Obiekt [id_obiektu=" + id_obiektu + ",nazwa_obiektu=" + nazwa_obiektu + ", kod_pocztowy=" + kod_pocztowy +", miejscowosc=" + miejscowosc + ",ulica="+ ulica +"]";
    }
}
