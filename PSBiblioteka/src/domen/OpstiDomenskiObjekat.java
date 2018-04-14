 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.sql.ResultSet;
import java.util.List;


public interface OpstiDomenskiObjekat {

    public String vratiNazivTabele();

    public String vratiVrednostiZaInsert();
    
    public String vratiVrednostiZaUpdate();

    public List<OpstiDomenskiObjekat> vratiListu(ResultSet rs);

    public String vratiJoin();

    public String vratiSelect();

    public String vratiWhere();

}
