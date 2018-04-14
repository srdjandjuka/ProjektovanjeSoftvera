/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package komunikacija;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import transfer.KlijentskiZahtev;
import transfer.ServerskiOdgovor;


public class KomunikacijaSaServerom {

    private static KomunikacijaSaServerom instanca;
    private Socket socket;

    private KomunikacijaSaServerom() {
    }

    public static KomunikacijaSaServerom getInstanca() {
        if (instanca == null) {
            instanca = new KomunikacijaSaServerom();
        }
        return instanca;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public void posaljiZahtev(KlijentskiZahtev kz) throws IOException {
        ObjectOutputStream outSocket = new ObjectOutputStream(socket.getOutputStream());
        outSocket.writeObject(kz);
    }

    public ServerskiOdgovor primiOdgovor() throws IOException, ClassNotFoundException {
        ObjectInputStream inSocket = new ObjectInputStream(socket.getInputStream());
        return (ServerskiOdgovor) inSocket.readObject();
    }
}
