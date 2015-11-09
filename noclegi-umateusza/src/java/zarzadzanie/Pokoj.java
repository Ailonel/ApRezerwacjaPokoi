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
public class Pokoj {

    private int id_pokoju;
    private int cena_za_dzien;
    private int numer_pokoju;
    private int liczba_osob;
    private int miejsce_parking;
    private int miejsce_jadalnia;
    private int id_obiektu;

    public int getIdPokoju() {
        return id_pokoju;
    }

    public void setIdPokoju(int id_pokoju) {
        this.id_pokoju = id_pokoju;
    }

    public int getCenaZaDzien() {
        return cena_za_dzien;
    }

    public void setCenaZaDzien(int cena_za_dzien) {
        this.cena_za_dzien = cena_za_dzien;
    }

    public int getNumerPokoju() {
        return numer_pokoju;
    }

    public void setNumerPokoju(int numer_pokoju) {
        this.numer_pokoju = numer_pokoju;
    }

    public int getLiczbaOsob() {
        return liczba_osob;
    }

    public void setLiczbaOsob(int liczba_osob) {
        this.liczba_osob = liczba_osob;
    }

    public int getMiejsceParking() {
        return miejsce_parking;
    }

    public void setMiejsceParking(int miejsce_parking) {
        this.miejsce_parking = miejsce_parking;
    }

    public int getMiejsceJadalnia() {
        return miejsce_jadalnia;
    }

    public void setMiejsceJadalnia(int miejsce_jadalnia) {
        this.miejsce_jadalnia = miejsce_jadalnia;
    }

    public int getIdObiektu() {
        return id_obiektu;
    }

    public void setIdObiektu(int id_obiektu) {
        this.id_obiektu = id_obiektu;
    }

    @Override
    public String toString() {
        return "Pokoj [id_pokoju=" + id_pokoju + ",cena_za_dzien=" + cena_za_dzien + ", numer_pokoju="
                + numer_pokoju + ", liczba_osob=" + liczba_osob + ", miejsce_parking=" + miejsce_parking + ", miejsce_jadalnia=" + miejsce_jadalnia + ", id_obiektu=" + id_obiektu + "]";
    }
}
