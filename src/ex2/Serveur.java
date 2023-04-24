package ex2;

import ex1.Calculatrice;
import ex1.Hello;
import ex1.ImplHello;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Serveur {

    public static void main(String args[]) {
        try {

            // pour éviter tout problèmes de cartes réseaux, il faut préciser le hostname du serveur dans le code
            System.setProperty("java.rmi.server.hostname", "127.0.0.1");

            // Création de l'objet qui sera distant
            ImpCalculatrice obj = new ImpCalculatrice();

            // Création du stub (objet distant, port
            Calculatrice stub = (Calculatrice) UnicastRemoteObject.exportObject(obj, 0);

            // Création du registre permetant la liaison des JVM
            Registry reg = LocateRegistry.getRegistry();

            //Bind, rebind nous permet d'attribuer une clé à notre objet distant pour facilement le retrouver
            reg.rebind("Calculatrice", stub);

            System.out.println("Le Serveur de calcul est prêt...");

        } catch (Exception e) {
            System.err.println(e.toString());
            e.printStackTrace();
        }
    }
}
