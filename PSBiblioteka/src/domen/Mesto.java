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


public class Mesto implements Serializable, OpstiDomenskiObjekat {

    private int id;
    private int postanskiBroj;
    private String naziv;

    public Mesto() {
    }

    public Mesto(int id, String naziv) {
        this.id = id;
        this.naziv = naziv;
    }

    public Mesto(int id, int postanskiBroj, String naziv) {
        this.id = id;
        this.postanskiBroj = postanskiBroj;
        this.naziv = naziv;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPostanskiBroj() {
        return postanskiBroj;
    }

    public void setPostanskiBroj(int postanskiBroj) {
        this.postanskiBroj = postanskiBroj;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    @Override
    public String toString() {
        return naziv;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        Mesto mesto = (Mesto) obj;
        return mesto.getId() == id;
    }

    @Override
    public String vratiNazivTabele() {
        return "mesto";
    }

    @Override
    public String vratiVrednostiZaInsert() {
        return null;
    }

    @Override
    public List<OpstiDomenskiObjekat> vratiListu(ResultSet rs) {
        List<OpstiDomenskiObjekat> listaMesta = new ArrayList<>();
        try {
            while (rs.next()) {
                int id = rs.getInt("id");
                int ptt = rs.getInt("ptt");
                String naziv = rs.getString("naziv");
                Mesto mesto = new Mesto(id, ptt, naziv);
                listaMesta.add(mesto);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Mesto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaMesta;
    }

    @Override
    public String vratiJoin() {
        return vratiNazivTabele();
    }

    @Override
    public String vratiSelect() {
        return "*";
    }

    @Override
    public String vratiVrednostiZaUpdate() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String vratiWhere() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
