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
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Trener implements Serializable, OpstiDomenskiObjekat {

    private int trenerId;
    private String jmbg;
    private String ime;
    private String prezime;
    private String username;
    private String password;
    private SkiSkola skiSkola;

    public Trener() {
    }

    public Trener(int trenerId, String ime, String prezime) {
        this.trenerId = trenerId;
        this.ime = ime;
        this.prezime = prezime;
    }

    public Trener(int trenerId, String ime, String prezime, String jmbg, SkiSkola skiSkola) {
        this.trenerId = trenerId;
        this.ime = ime;
        this.prezime = prezime;
        this.jmbg = jmbg;
        this.skiSkola = skiSkola;
    }

    public Trener(int trenerId, String jmbg, String ime, String prezime, String username, String password, SkiSkola skiSkola) {
        this.trenerId = trenerId;
        this.jmbg = jmbg;
        this.ime = ime;
        this.prezime = prezime;
        this.username = username;
        this.password = password;
        this.skiSkola = skiSkola;
    }

    public int getTrenerId() {
        return trenerId;
    }

    public void setTrenerId(int trenerId) {
        this.trenerId = trenerId;
    }

    public String getJmbg() {
        return jmbg;
    }

    public void setJmbg(String jmbg) {
        this.jmbg = jmbg;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public SkiSkola getSkiSkola() {
        return skiSkola;
    }

    public void setSkiSkola(SkiSkola skiSkola) {
        this.skiSkola = skiSkola;
    }

    @Override
    public String toString() {
        return ime + " " + prezime;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Trener other = (Trener) obj;
        return Objects.equals(this.username, other.username);
    }

    @Override
    public String vratiNazivTabele() {
        return "trener";
    }

    @Override
    public String vratiVrednostiZaInsert() {
        return String.format("NULL,'%s','%s','%s','%s','%s',%d",
                jmbg, ime, prezime, username, password, skiSkola.getSkolaID());
    }

    @Override
    public List<OpstiDomenskiObjekat> vratiListu(ResultSet rs) {
        List<OpstiDomenskiObjekat> listaTrenera = new ArrayList<>();
        try {
            while (rs.next()) {
                int id = rs.getInt("t.id");
                String jmbgSql = rs.getString("t.jmbg");
                String imeSql = rs.getString("t.ime");
                String prezimeSql = rs.getString("t.prezime");
                String usernameSql = rs.getString("t.username");
                String pass = rs.getString("t.pass");
                int skolaID = rs.getInt("s.id");
                String naziv = rs.getString("s.naziv");
                int mestoID = rs.getInt("m.id");
                int ptt = rs.getInt("m.ptt");
                String nazivMesta = rs.getString("m.naziv");
                Mesto m = new Mesto(mestoID, ptt, nazivMesta);
                SkiSkola skola = new SkiSkola(skolaID, naziv, m);
                Trener trener = new Trener(id, jmbgSql, imeSql, prezimeSql, usernameSql, pass, skola);
                listaTrenera.add(trener);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Mesto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaTrenera;
    }

    @Override
    public String vratiJoin() {
        return " trener t INNER JOIN ski_skola s ON t.skola_id=s.id INNER JOIN mesto m on s.mesto_id=m.id";
               
    }

    @Override
    public String vratiSelect() {
        return "t.id,t.jmbg,t.ime,t.prezime,t.username,t.pass,s.id, s.naziv, m.id, m.ptt, m.naziv";
    }

    @Override
    public String vratiVrednostiZaUpdate() {
        return "ime=" + "'" + this.getIme() + "'" + "," + " prezime=" + "'" + this.getPrezime() + "'" + ","
                + " jmbg=" + "'" + this.getJmbg() + "'" + "," 
                + " skola_id=" + this.getSkiSkola().getSkolaID();
    }

    @Override
    public String vratiWhere() {
        return "id=" + this.getTrenerId();
    }

}
