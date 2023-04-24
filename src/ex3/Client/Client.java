package ex3.Client;


import ex3.ChenilInterface;
import ex3.Chien;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            // Récupérer le registre
            Registry reg = LocateRegistry.getRegistry(null);

            // Recherche dans le registre de l'objet distant
            ChenilInterface stub = (ChenilInterface) reg.lookup("Chenil");

            choix:do {
                System.out.println("1. Ajouter un chien\n2. Voir les chiens\n3. Quitter");
                int choix = sc.nextInt();
                sc.skip("\n");
                switch (choix){
                    case 1:
                        System.out.print("Entrez le nom du chien : ");
                        String nom = sc.nextLine();
                        System.out.print("Entrez l'age du chien : ");
                        int age = sc.nextInt();
                        stub.addChien(new Chien(nom, age));
                        break;
                    case 2:
                        List<Chien> chiens = stub.getChiens();
                        for (Chien chien : chiens){
                            System.out.println(chien);
                        }
                        break;
                    case 3:
                        break choix;
                    default:
                        break;
                }

            }while (true);

        } catch (Exception e) {
            System.err.println(e.toString());
            e.printStackTrace();
        }
    }
}
