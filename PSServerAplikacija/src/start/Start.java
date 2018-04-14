/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package start;

import java.io.IOException;
import forme.GlavnaServerskaForma;


public class Start {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        GlavnaServerskaForma gsf=new GlavnaServerskaForma();
        gsf.setTitle("Server program");
        gsf.setVisible(true);
    }
}
