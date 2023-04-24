package ex1;


import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

public class Serveur extends ImplHello {
    public Serveur() {}

    public static void main(String args[]) {
        try {

            // pour éviter tout problèmes de cartes réseaux, il faut préciser le hostname du serveur dans le code
            System.setProperty("java.rmi.server.hostname", "127.0.0.1");

            // Création de l'objet qui sera distant
            ImplHello obj = new ImplHello();

            // Création du stub (objet distant, port
            Hello stub = (Hello) UnicastRemoteObject.exportObject(obj, 0);
            /*
            La méthode exportObject de la classe statique UnicastRemoteObject nous permet de créer notre stub. celui ci a besoin en paramètre de l’objet
             */


            // Création du registre permetant la liaison des JVM
            Registry reg = LocateRegistry.getRegistry();


            //Bind, rebind nous permet d'attribuer une clé à notre objet distant pour facilement le retrouver
            reg.rebind("Hello", stub);

            System.out.println("Le Serveur est prêt...");

        } catch (Exception e) {
            System.err.println(e.toString());
            e.printStackTrace();
        }
    }
}