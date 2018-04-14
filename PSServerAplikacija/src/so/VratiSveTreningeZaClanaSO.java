/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import db.DBBroker;
import domen.Clan;
import domen.Trening;
import java.util.ArrayList;
import java.util.List;


public class VratiSveTreningeZaClanaSO extends OpstaSistemskaOperacija {

    private List<Trening> listaTreningaZaIgraca;

    public VratiSveTreningeZaClanaSO(DBBroker dBBroker) {
        super(dBBroker);
        listaTreningaZaIgraca = new ArrayList<>();
    }

    @Override
    protected void proveriPreduslove(Object object) throws Exception {
    }

    @Override
    protected void izvrsiOperaciju(Object object) throws Exception {
        listaTreningaZaIgraca = dBBroker.vratiTreningeZaClana((Clan) object);
    }

    public List<Trening> getListaTreningaZaClana() {
        return listaTreningaZaIgraca;
    }

}
