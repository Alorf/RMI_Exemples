package ex2;

import ex1.Calculatrice;
import ex1.Hello;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {
        try {
            /*
                Récupérer le registre
                si Le paramètre host est null, cela précise que nous travaillons en locahost
             */
            Registry reg = LocateRegistry.getRegistry(null);

            // On recherche notre stub créé dans le serveur grâce à notre clé
            Calculatrice stub = (Calculatrice) reg.lookup("Calculatrice");


            Scanner reader = new Scanner(System.in);

            do {
                System.out.println("Entrez 2 nombres");
                int a = reader.nextInt();
                int b = reader.nextInt();
                System.out.println("Que voulez vous faire");
                System.out.println("1.Addition\n2.Soustraction\n3.Multiplication");
                int choix = reader.nextInt();
                switch (choix){
                    case 1:
                        // Appel de la méthode distante à l'aide de l'objet obtenu
                        System.out.println(stub.addition(a,b));
                        break;
                    case 2:
                        // Appel de la méthode distante à l'aide de l'objet obtenu
                        System.out.println(stub.soustraction(a,b));
                        break;
                    case 3:
                        // Appel de la méthode distante à l'aide de l'objet obtenu
                        System.out.println(stub.multiplication(a,b));
                        break;
                    default:
                        System.out.println("Erreur, recommencez");
                        break;
                }
            }while (true);

        } catch (Exception e) {
            System.err.println(e.toString());
            e.printStackTrace();
        }
    }
}
