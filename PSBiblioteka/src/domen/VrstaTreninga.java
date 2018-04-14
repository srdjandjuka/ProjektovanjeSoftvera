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


public class VrstaTreninga implements Serializable, OpstiDomenskiObjekat {

    private int vrstaTreningaId;
    private String nazivVrsteTreninga;

    public VrstaTreninga() {
    }

    public VrstaTreninga(int vrstaTreningaId, String nazivVrsteTreninga) {
        this.vrstaTreningaId = vrstaTreningaId;
        this.nazivVrsteTreninga = nazivVrsteTreninga;
    }

    public String getNazivVrsteTreninga() {
        return nazivVrsteTreninga;
    }

    public void setNazivVrsteTreninga(String nazivVrsteTreninga) {
        this.nazivVrsteTreninga = nazivVrsteTreninga;
    }

    public int getVrstaTreningaId() {
        return vrstaTreningaId;
    }

    public void setVrstaTreningaId(int vrstaTreningaId) {
        this.vrstaTreningaId = vrstaTreningaId;
    }

    @Override
    public String toString() {
        return getNazivVrsteTreninga();
    }

    @Override
    public String vratiNazivTabele() {
        return "vrsta_treninga";
    }

    @Override
    public String vratiVrednostiZaInsert() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<OpstiDomenskiObjekat> vratiListu(ResultSet rs) {
        List<OpstiDomenskiObjekat> listaVrstiTreninga = new ArrayList<>();
        try {
            while (rs.next()) {
                int id = rs.getInt("id");
                String naziv = rs.getString("naziv");
                VrstaTreninga vrstaTreninga = new VrstaTreninga(id, naziv);
                listaVrstiTreninga.add(vrstaTreninga);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Mesto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaVrstiTreninga;
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
