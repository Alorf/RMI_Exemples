package ex3;

import java.io.Serializable;


public class Chien implements Serializable {
    //private static final long serialVersionUID = 20120731125400L;

    String nom;
    int age;

    public Chien(String nom, int age){
        this.nom = nom;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Chien{" +
                "nom='" + nom + '\'' +
                ", age=" + age +
                '}';
    }
}
