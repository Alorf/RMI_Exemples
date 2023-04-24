package ex1;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Calculatrice extends Remote {

    void showMsg(String value) throws RemoteException;
    int addition(int a, int b) throws RemoteException;
    int soustraction(int a, int b) throws RemoteException;
    int multiplication(int a, int b) throws RemoteException;
}
