/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import domen.Trening;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;


public class ModelTabeleTrening extends AbstractTableModel {

    List<Trening> treninzi;
    String[] kolone = {"Trener", "Datum", "Vrsta treninga", "Dužina trajanja", "Cilj", "Sadržaj"};
    SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

    public ModelTabeleTrening() {
        treninzi = new ArrayList<>();
    }

    @Override
    public int getRowCount() {
        return treninzi.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Trening t = treninzi.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return t.getTrener();
            case 1:
                return sdf.format(t.getDatum());
            case 2:
                return t.getVrstaTreninga();
            case 3:
                return t.getDuzinaTrajanja();
            case 4:
                return t.getCilj();
            case 5:
                return t.getSadrzaj();
            default:
                return "n/a";
        }
    }

    @Override
    public String getColumnName(int i) {
        return kolone[i];
    }

    public void dodaj(Trening t) {
        treninzi.add(t);
        fireTableDataChanged();
    }
    
    public void dodajSve(List<Trening> treninzi){
        this.treninzi.addAll(treninzi);
        fireTableDataChanged();
    }

    public void obrisi(int red) {
        treninzi.remove(red);
        fireTableDataChanged();
    }

    public List<Trening> vratiTreninge() {
        return treninzi;
    }

}
