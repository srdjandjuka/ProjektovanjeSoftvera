/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Clan implements Serializable, OpstiDomenskiObjekat {

    private int id;
    private String ime;
    private String prezime;
    private String jmbg;
    private String ulica;
    private String broj;
    private Mesto mesto;
    private Uzrast uzrast;
    private Trener trener;
    private boolean status;

    public Clan() {
    }

    public Clan(int id, String ime, String prezime) {
        this.id = id;
        this.ime = ime;
        this.prezime = prezime;
    }

    public Clan(int id, String ime, String prezime, String jmbg, String ulica, String broj, Mesto mesto, Uzrast uzrast, Trener trener) {
        this.id = id;
        this.ime = ime;
        this.prezime = prezime;
        this.jmbg = jmbg;
        this.ulica = ulica;
        this.broj = broj;
        this.mesto = mesto;
        this.uzrast = uzrast;
        this.trener = trener;
        this.status = true;
    }

    public Clan(int id, String ime, String prezime, String jmbg, String ulica, String broj, Mesto mesto, Uzrast uzrast, Trener trener, boolean status) {
        this.id = id;
        this.ime = ime;
        this.prezime = prezime;
        this.jmbg = jmbg;
        this.ulica = ulica;
        this.broj = broj;
        this.mesto = mesto;
        this.uzrast = uzrast;
        this.trener = trener;
        this.status = status;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getJmbg() {
        return jmbg;
    }

    public void setJmbg(String jmbg) {
        this.jmbg = jmbg;
    }

    public String getUlica() {
        return ulica;
    }

    public void setUlica(String ulica) {
        this.ulica = ulica;
    }

    public String getBroj() {
        return broj;
    }

    public void setBroj(String broj) {
        this.broj = broj;
    }

    public Mesto getMesto() {
        return mesto;
    }

    public void setMesto(Mesto mesto) {
        this.mesto = mesto;
    }

    public Uzrast getUzrast() {
        return uzrast;
    }

    public void setUzrast(Uzrast uzrast) {
        this.uzrast = uzrast;
    }

    public Trener getTrener() {
        return trener;
    }

    public void setTrener(Trener trener) {
        this.trener = trener;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return ime + " " + prezime;
    }

    @Override
    public String vratiJoin() {
        return "clan c INNER JOIN mesto m ON c.mesto_id=m.id\n"
                + "INNER JOIN uzrast u ON c.uzrast_id=u.sifra\n"
                + "INNER JOIN trener t ON c.trener_id=t.id";
    }

    @Override
    public String vratiNazivTabele() {
        return "clan";
    }

    @Override
    public String vratiSelect() {
        return " c.id,c.ime, c.prezime, c.jmbg, c.ulica, c.broj,c.status, m.id, m.ptt, m.naziv, u.sifra, u.naziv,t.id, t.ime, t.prezime";
    }

    @Override
    public String vratiVrednostiZaInsert() {
        return String.format("NULL,'%s','%s','%s','%s','%s',%d,%d,%d,%d",
                ime, prezime, jmbg, ulica, broj, status ? 1 : 0, mesto.getId(), uzrast.getSifra(), trener.getTrenerId());
    }

    @Override
    public List<OpstiDomenskiObjekat> vratiListu(ResultSet rs) {
        List<OpstiDomenskiObjekat> listaClanova = new ArrayList<>();
        try {
            while (rs.next()) {
                int id = rs.getInt("c.id");
                String ime = rs.getString("c.ime");
                String prezime = rs.getString("c.prezime");
                String jmbg = rs.getString("c.jmbg");
                String ulica = rs.getString("c.ulica");
                String broj = rs.getString("c.broj");
                boolean status = rs.getBoolean("c.status");
                int mesto_id = rs.getInt("m.id");
                int mesto_ptt = rs.getInt("m.ptt");
                String nazivMesta = rs.getString("m.naziv");
                int uzrast_id = rs.getInt("u.sifra");
                String nazivUzrasta = rs.getString("u.naziv");
                int trener_id = rs.getInt("t.id");
                String imeTrenera = rs.getString("t.ime");
                String prezimeTrenera = rs.getString("t.prezime");
                //int skolaID = rs.getInt("s.id");
                //String nazivSkole = rs.getString("s.naziv");
                //int mestoSkoleID = rs.getInt("mm.id");
                //String nazivMestaSkole = rs.getString("mm.naziv");
                //int pttSkole = rs.getInt("mm.ptt");
                //Mesto mestoSkole = new Mesto(mestoSkoleID, pttSkole, nazivMestaSkole);
                //SkiSkola skola = new SkiSkola(skolaID, nazivSkole, mestoSkole);
                Mesto mesto = new Mesto(mesto_id, mesto_ptt, nazivMesta);
                Uzrast uzrast = new Uzrast(uzrast_id, nazivUzrasta);
                Trener trener = new Trener(trener_id, imeTrenera, prezimeTrenera);
                Clan clan = new Clan(id, ime, prezime, jmbg, ulica, broj, mesto, uzrast, trener, status);
                //clan.setStatus(status);
                listaClanova.add(clan);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Mesto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaClanova;
    }

    @Override
    public String vratiVrednostiZaUpdate() {
        return "ime=" + "'" + this.getIme() + "'" + "," + " prezime=" + "'" + this.getPrezime() + "'" + ","
                + " jmbg=" + "'" + this.getJmbg() + "'" + "," + " ulica=" + "'" + this.getUlica() + "'" + ","
                + " broj=" + "'" + this.getBroj() + "'" + "," + " status=" + isStatus() + ","
                + " mesto_id=" + this.getMesto().getId() + "," + " trener_id="
                + this.getTrener().getTrenerId() + "," + " uzrast_id=" + getUzrast().getSifra();

    }

    @Override
    public String vratiWhere() {
        return "id=" + this.getId();
    }

}
