/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import domen.Clan;
import domen.Mesto;
import domen.OpstiDomenskiObjekat;
import domen.SkiSkola;
import domen.Trener;
import domen.Trening;
import domen.VrstaTreninga;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class DBBroker {

    public List<OpstiDomenskiObjekat> vratiSve(OpstiDomenskiObjekat odo) throws SQLException, IOException {
        List<OpstiDomenskiObjekat> listaOpstihDomenskihObjekata = new ArrayList<>();
        Connection konekcija = Konekcija.vratiObjekat().vratiKonekciju();
        String upit = "SELECT " + odo.vratiSelect() + " FROM " + odo.vratiJoin();
        Statement statement = konekcija.createStatement();
        ResultSet rs = statement.executeQuery(upit);
        listaOpstihDomenskihObjekata = odo.vratiListu(rs);
        statement.close();
        return listaOpstihDomenskihObjekata;
    }

    public void sacuvaj(OpstiDomenskiObjekat odo) throws SQLException, IOException {
        Connection konekcija = Konekcija.vratiObjekat().vratiKonekciju();
        String upit = "INSERT INTO " + odo.vratiNazivTabele() + " VALUES (" + odo.vratiVrednostiZaInsert() + ")";
        System.out.println("\n\n\n ************** \n" + upit);
        Statement statement = konekcija.createStatement();
        statement.executeUpdate(upit);
        statement.close();
    }

    public void izmeni(OpstiDomenskiObjekat odo) throws SQLException, IOException {
        Connection konekcija = Konekcija.vratiObjekat().vratiKonekciju();
        String upit = "UPDATE " + odo.vratiNazivTabele() + " SET " + odo.vratiVrednostiZaUpdate() + " WHERE " + odo.vratiWhere();
        System.out.println("\n\n\n ************** \n" + upit);
        Statement statement = konekcija.createStatement();
        statement.executeUpdate(upit);
        statement.close();
    }

    public Trener proveriPodatke(Trener trener) throws Exception {
        try {
            Connection konekcija = Konekcija.vratiObjekat().vratiKonekciju();
            String upit = "SELECT t.id,t.jmbg,t.ime,t.prezime,t.username,t.pass,s.id,s.naziv,m.id,m.ptt,m.naziv FROM trener t JOIN ski_skola s ON (t.skola_id = s.id) JOIN mesto m ON (s.mesto_id = m.id) WHERE t.username = '" + trener.getUsername() + "'";
                    //+ ",s.id as skola_id, s.naziv as naziv, m.id as mesto_id,m.ptt AS ptt,m.naziv AS mesto"
                    //+ "FROM trener t INNER JOIN ski_skola s ON t.skola_id=s.id INNER JOIN mesto m ON s.mesto_id=m.id"
                    //+ "WHERE t.username='"+ trener.getUsername() +"'  LIMIT 1";
            Statement s = konekcija.createStatement();
            //PreparedStatement ps = konekcija.prepareStatement(upit);
            //ps.setString(1, trener.getUsername());

            ResultSet rs = s.executeQuery(upit);
            boolean hasRow = rs.next();

            if (hasRow && rs.getString("pass").equals(trener.getPassword())) {
                trener.setTrenerId(rs.getInt("t.id"));
                trener.setPassword(null);
                trener.setJmbg(rs.getString("t.jmbg"));
                trener.setIme(rs.getString("t.ime"));
                trener.setPrezime(rs.getString("t.prezime"));
                
                SkiSkola skola = new SkiSkola();
                skola.setSkolaID(rs.getInt("s.id"));
                skola.setNazivSkole(rs.getString("s.naziv"));
                Mesto mesto = new Mesto();
                mesto.setId(rs.getInt("m.id"));
                mesto.setNaziv(rs.getString("m.naziv"));
                mesto.setPostanskiBroj(rs.getInt("m.ptt"));
                skola.setMesto(mesto);
                trener.setSkiSkola(skola);

                return trener;
            }
            return null;
        } catch (SQLException ex) {
            throw ex;
        }
    }

    public List<Trening> vratiTreningeZaClana(Clan clan) throws SQLException, IOException {
        List<Trening> lista = new ArrayList<>();
        Connection konekcija = Konekcija.vratiObjekat().vratiKonekciju();
        String upit = "SELECT * FROM trening t JOIN vrsta_treninga vt ON t.vrstaTreninga_id=vt.id  JOIN trener tr ON t.trener_id=tr.id WHERE clan_id=?";
        PreparedStatement ps = konekcija.prepareStatement(upit);
        ps.setInt(1, clan.getId());
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("t.id");
            Date datum = rs.getDate("t.datum");
            int duzina = rs.getInt("t.duzinaTrajanja");
            String cilj = rs.getString("t.cilj");
            String sadrzaj = rs.getString("t.sadrzaj");

            int vrstaTreningaId = rs.getInt("vt.id");
            String vrstaTreningaNaziv = rs.getString("vt.naziv");

            int idTrener = rs.getInt("tr.id");
            String ime = rs.getString("tr.ime");
            String prezime = rs.getString("tr.prezime");
            lista.add(new Trening(id, datum, duzina, cilj, sadrzaj, new Trener(idTrener, ime, prezime), clan, new VrstaTreninga(vrstaTreningaId, vrstaTreningaNaziv)));
        }
        ps.close();
        return lista;

    }

    public void commit() {
        try {
            Konekcija.vratiObjekat().vratiKonekciju().commit();
        } catch (SQLException | IOException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void rollback() {
        try {
            Konekcija.vratiObjekat().vratiKonekciju().rollback();
        } catch (SQLException | IOException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
