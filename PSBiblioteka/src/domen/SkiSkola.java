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


public class SkiSkola implements Serializable, OpstiDomenskiObjekat {

    private int skolaID;
    private String nazivSkole;
    private Mesto mesto;

    public SkiSkola() {
    }

    public SkiSkola(int skolaID, String nazivSkole, Mesto mesto) {
        this.skolaID = skolaID;
        this.nazivSkole = nazivSkole;
        this.mesto = mesto;
    }

    public int getSkolaID() {
        return skolaID;
    }

    public void setSkolaID(int skolaID) {
        this.skolaID = skolaID;
    }

    public String getNazivSkole() {
        return nazivSkole;
    }

    public void setNazivSkole(String nazivSkole) {
        this.nazivSkole = nazivSkole;
    }

    public Mesto getMesto() {
        return mesto;
    }

    public void setMesto(Mesto mesto) {
        this.mesto = mesto;
    }


    @Override
    public String toString() {
        return nazivSkole;
    }

    @Override
    public String vratiNazivTabele() {
        return "ski_skola";
    }

    @Override
    public String vratiVrednostiZaInsert() {
        return String.format("NULL,'%s','%d'",nazivSkole, mesto.getId());
    }

    @Override
    public String vratiVrednostiZaUpdate() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<OpstiDomenskiObjekat> vratiListu(ResultSet rs) {
        List<OpstiDomenskiObjekat> skiSkole = new ArrayList<>();
        try {
            while (rs.next()) {
                int id = rs.getInt("id");
                String naziv = rs.getString("naziv");
                int mestoID = rs.getInt("mesto_id");
                int ptt = rs.getInt("ptt");
                String nazivMesta = rs.getString("mesto");
                Mesto m = new Mesto(mestoID,ptt,nazivMesta);
                SkiSkola skola = new SkiSkola(id, naziv, m);
                skiSkole.add(skola);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SkiSkola.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return skiSkole;
    }

    @Override
    public String vratiJoin() {
        return "ski_skola s INNER JOIN mesto m ON s.mesto_id=m.id";
    }

    @Override
    public String vratiSelect() {
        return "s.id, s.naziv, m.id as mesto_id, m.ptt as ptt, m.naziv as mesto";
    }

    @Override
    public String vratiWhere() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
