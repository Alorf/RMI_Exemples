package ex3.Serveur;

import ex3.ChenilInterface;
import ex3.Chien;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class Chenil implements ChenilInterface {
    String nom;
    List<Chien> chiens = new ArrayList<>();

    public Chenil(String nom){
        this.nom = nom;
    }

    @Override
    public List<Chien> getChiens(){
        return chiens;
    }

    @Override
    public void addChien(Chien c) throws RemoteException {
        chiens.add(c);
    }


}
