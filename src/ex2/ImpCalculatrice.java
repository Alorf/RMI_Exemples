package ex2;

import ex1.Calculatrice;

import java.rmi.RemoteException;

public class ImpCalculatrice implements Calculatrice {
    @Override
    public void showMsg(String value) throws RemoteException {
        System.out.println(value);
    }

    @Override
    public int addition(int a, int b) throws RemoteException {
        showMsg("Addition : " + a + " + " + b + " = " + (a + b));
        return a+b;
    }

    @Override
    public int soustraction(int a, int b) throws RemoteException {
        showMsg("Soustraction : " + a + " - " + b + " = " + (a - b));
        return a-b;
    }

    @Override
    public int multiplication(int a, int b) throws RemoteException {
        showMsg("Multiplication : " + a + " * " + b + " = " + (a * b));
        return a*b;
    }
}
