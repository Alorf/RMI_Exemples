package ex3;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ChenilInterface extends Remote {
    List<Chien> getChiens() throws RemoteException;

    void addChien(Chien c) throws RemoteException;
}
