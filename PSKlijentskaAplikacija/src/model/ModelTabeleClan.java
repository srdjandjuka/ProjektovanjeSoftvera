/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import domen.Clan;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import komunikacija.KomunikacijaSaServerom;
import pomoc.Operacija;
import transfer.KlijentskiZahtev;
import transfer.ServerskiOdgovor;


public class ModelTabeleClan extends AbstractTableModel {

    List<Clan> clanovi;
    List<Clan> clanoviFiltrirano;
    String[] kolone = {"Ime", "Prezime", "JMBG", "Adresa", "Mesto", "Uzrast", "Trener", "Status"};

    public ModelTabeleClan(List<Clan> clanovi) {
        this.clanovi = clanovi;
        this.clanoviFiltrirano = clanovi;
    }

    @Override
    public int getRowCount() {
        if (clanoviFiltrirano == null) {
            return 0;
        }
        return clanoviFiltrirano.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int row, int column) {
        Clan clan = clanoviFiltrirano.get(row);
        switch (column) {
            case 0:
                return clan.getIme();
            case 1:
                return clan.getPrezime();
            case 2:
                return clan.getJmbg();
            case 3:
                return clan.getUlica() + " " + clan.getBroj();
            case 4:
                return clan.getMesto();
            case 5:
                return clan.getUzrast();
            case 6:
                return clan.getTrener();
            case 7:
                return clan.isStatus();
            default:
                return "N/A";
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        if (column == 7) {
            return true;
        }
        return false;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Clan clan = clanoviFiltrirano.get(rowIndex);
        switch (columnIndex) {
            case 7: {
                boolean status = (boolean) aValue;
                clan.setStatus(status);
                try {
                    KlijentskiZahtev kz = new KlijentskiZahtev();
                    kz.setAkcija(Operacija.IZMENI_ODO);
                    kz.setParametar(clan);
                    KomunikacijaSaServerom.getInstanca().posaljiZahtev(kz);
                    ServerskiOdgovor so = KomunikacijaSaServerom.getInstanca().primiOdgovor();
                } catch (Exception ex) {
                    clan.setStatus(!status);
                    System.out.println(ex.getMessage());
                }

                break;
            }
        }
    }

    @Override
    public Class<?> getColumnClass(int i) {
        Class clazz = String.class;
        switch (i) {
            case 7:
                clazz = Boolean.class;
        }
        return clazz;
    }

    public void azurirajPodatkeOIgracu(Clan clan) {
        for (Clan cla : clanoviFiltrirano) {
            if (cla.getId() == clan.getId()) {
                cla.setIme(clan.getIme());
                cla.setPrezime(clan.getPrezime());
                cla.setUlica(clan.getUlica());
                cla.setBroj(clan.getBroj());
                cla.setMesto(clan.getMesto());
                cla.setUzrast(clan.getUzrast());
                cla.setStatus(clan.isStatus());
                break;
            }
        }
        fireTableDataChanged();
    }

    public Clan vratiClana(int index) {
        return clanoviFiltrirano.get(index);
    }

    public void resetujFilter() {
        clanoviFiltrirano = clanovi;
        fireTableDataChanged();
    }

    public void filtriraj(String trim) {
        clanoviFiltrirano = new ArrayList<>();

        for (int i = 0; i < clanovi.size(); i++) {
            Clan clan = clanovi.get(i);

            if (zadovoljenUslov1(clan, trim)) {
                clanoviFiltrirano.add(clan);
            }

        }

        fireTableDataChanged();
        
    }

    private boolean zadovoljenUslov1(Clan clan, String upit) {
        if (upit.isEmpty()) {
            return true;
        }
        if (clan.getIme().toLowerCase().startsWith(upit.toLowerCase())
                || clan.getPrezime().toLowerCase().startsWith(upit
                        .toLowerCase()) || clan.getJmbg().startsWith(upit)) {
            return true;
        }

        return false;
    }

    public List<Clan> vratiListu() {
        return clanoviFiltrirano;
    }

}
