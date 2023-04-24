package ex4;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ChatInterface extends Remote {

    void sendBroadcast(String message) throws RemoteException;
    void sendMessage(String message, ChatInterface sender) throws RemoteException;

    void addClient(ChatInterface c) throws RemoteException;

    void delClient(ChatInterface c) throws RemoteException;

    List<ChatInterface> getClients() throws RemoteException;

    String getUsername() throws RemoteException;

    String getUuid() throws RemoteException;
    }
