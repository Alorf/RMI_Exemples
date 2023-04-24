package ex1;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {

    private Client() {}

    public static void main(String[] args) {
        try {
            /*
                Récupérer le registre
                si Le paramètre host est null, cela précise que nous travaillons en locahost
             */
            Registry reg = LocateRegistry.getRegistry("localhost");

            // On recherche notre stub créé dans le serveur grâce à notre clé
            Hello stub = (Hello) reg.lookup("Hello");

            // Appel de la méthode distante à l'aide de l'objet obtenu
            stub.showMsg();

        } catch (Exception e) {
            System.err.println(e.toString());
            e.printStackTrace();
        }
    }
}