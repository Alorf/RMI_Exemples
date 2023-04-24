package ex4;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Chat {

    /*

        Le client envois au serveur qui renvoie a tout les clients

     */
    public static void main(String[] args) throws RemoteException {

        do {
            System.out.println("Que voulez vous faire ? ");
            System.out.println("1.Héberger\n2.Rejoindre");
            int choix = Integer.parseInt(regex("[0-9]+", "Quel est votre choix : "));

            switch (choix) {
                case 1:
                    Heberger();
                    break;
                case 2:
                    Rejoindre();
                    break;
            }
        } while (true);

    }

    public static void Heberger() throws RemoteException {

        String sessionName = regex("[a-zA-Z]+", "Entrez le nom de votre session : ");
        String pseudo = regex("[a-zA-Z]+", "Entrez votre pseudo : ");

        try {

            System.setProperty("java.rmi.server.hostname", "127.0.0.1");

            // ici, nous exportons l'objet distant vers le stub
            ChatInterface stub = new ImpChat(pseudo);

            // Liaison de l'objet distant (stub) dans le Registre
            Registry reg = LocateRegistry.getRegistry();


            reg.rebind(sessionName, stub);

            System.out.println("Le Serveur est prêt...");

            while (true) {
                if (stub.getClients() != null) {

                    //L'hébergeur entre un message
                    String message = regex(".*", "");

                    List<ChatInterface> clients = stub.getClients();

                    if (message.equals("/quit")) {
                        stub.sendBroadcast("Le serveur est coupé");
                        break;
                    }

                    //Le message est envoyé à tout les clients
                    for (ChatInterface c : clients) {
                        //Serveur -> vers les client
                        c.sendMessage(message, stub);
                    }
                }
            }

            System.out.println("Suppression de la session");
            reg.unbind(sessionName);

        } catch (Exception e) {
            System.err.println(e.toString());
            e.printStackTrace();
        }
    }

    public static void Rejoindre() throws RemoteException {


        String pseudo = regex("[a-zA-Z]+", "Entrez votre pseudo : ");

        try {
            // Récupérer le registre
            Registry reg = LocateRegistry.getRegistry("94.105.54.48");

            for (int i = 0 ; i < reg.list().length; i++){
                System.out.println("\t"+ (i+1) + ". " + reg.list()[i]);
            }
            String sessionName = regex("[a-zA-Z]+", "Entrez le nom de la session : ");


            // Recherche dans le registre de l'objet distant
            ChatInterface stub = (ChatInterface) reg.lookup(sessionName);

            ChatInterface client = new ImpChat(pseudo);

            // Appel de la méthode distante à l'aide de l'objet obtenu
            stub.addClient(client);


            //Message d'alerte qui va être envoyer à tout les utilisateurs
            stub.sendBroadcast(pseudo + " à rejoint le serveur...");

            while (true) {
                //Si c'est un client qui se connecte

                //Le client entre un message
                String message = regex(".*", "");

                if (message.equals("/quit")) {
                    stub.sendBroadcast(client.getUsername() + " a quitter le serveur...");
                    stub.delClient(client);
                    break;
                }

                //Le client demande au stub (objet distant) de diffuser son message
                stub.sendMessage(message, client);
            }

        } catch (Exception e) {
            System.err.println(e.toString());
            e.printStackTrace();
        }
    }

    public static String regex(String regex, String msg) {
        Scanner reader = new Scanner(System.in);

        String chaine;
        do {
            System.out.printf(msg);
            chaine = reader.nextLine();
            if (!chaine.matches(regex)) {
                System.out.println("Recommencez !");
            }
        } while (!chaine.matches(regex));

        return chaine;
    }
}