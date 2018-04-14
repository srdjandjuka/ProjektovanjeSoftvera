/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transfer;

import java.io.Serializable;


public class KlijentskiZahtev implements Serializable {

    private int akcija;
    private Object parametar;

    public KlijentskiZahtev() {
    }

    public KlijentskiZahtev(int akcija, Object parametar) {
        this.akcija = akcija;
        this.parametar = parametar;
    }

    public Object getParametar() {
        return parametar;
    }

    public void setParametar(Object parametar) {
        this.parametar = parametar;
    }

    public int getAkcija() {
        return akcija;
    }

    public void setAkcija(int akcija) {
        this.akcija = akcija;
    }

}
