/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontroler;


import domen.Clan;
import domen.Trener;
import java.util.ArrayList;
import java.util.List;
import observer.ObserverClan;


public class Kontroler {

    private static Trener loggedUser;
    private static List<Clan> listaClanova = new ArrayList<>();
    private static List<ObserverClan> observers = new ArrayList<>();

    public static void attach(ObserverClan observer) {
        observers.add(observer);
    }

    public static void notifyAllObservers(Clan clan) {
        observers.forEach(ObserverClan::update);
//        for (ObserverIgrac observer : observers) {
//            observer.update();
//        }
    }

    public static void setLoggedUser(Trener trener) {
        loggedUser = trener;
    }

    public static Trener getLoggedUser() {
        return loggedUser;
    }

}
