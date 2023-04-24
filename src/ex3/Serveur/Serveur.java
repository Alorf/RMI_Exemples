package ex3.Serveur;

import ex3.ChenilInterface;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Serveur {

    public static void main(String args[]) {
        try {

            System.setProperty("java.rmi.server.hostname", "127.0.0.1");

            // crée l'objet distant
            ChenilInterface obj = new Chenil("Le zamis");

            // ici, nous exportons l'objet distant vers le stub
            ChenilInterface stub = (ChenilInterface) UnicastRemoteObject.exportObject(obj, 0);

            // Liaison de l'objet distant (stub) dans le Registre
            Registry reg = LocateRegistry.createRegistry(1099);

            reg.rebind("Chenil", stub);

            System.out.println("Le Serveur est prêt...");

        } catch (Exception e) {
            System.err.println(e.toString());
            e.printStackTrace();
        }
    }
}
