package ex4;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class ImpChat extends UnicastRemoteObject implements ChatInterface {
    private String username;
    UUID randomId;
    private List<ChatInterface> clients = new ArrayList<>();

    public ImpChat(String username) throws RemoteException {
        super(48001);
        this.username = username;
        randomId = UUID.randomUUID();
    }

    @Override
    public void sendBroadcast(String message) throws RemoteException {
        System.out.println(message);

        for (ChatInterface c : clients) {
            //On recoit le serveur d'un autre client, donc je l'envoie aux autres
            c.sendBroadcast(message);
        }
    }

    @Override
    public void sendMessage(String message, ChatInterface sender) throws RemoteException {

        //Si ce n'est pas l'hébergeur qui envoie le message, alors on l'affiche pour l'hébergeur

        if (!sender.getUuid().equals(getUuid())) {
            System.out.println(sender.getUsername() + " : " + message);
        }

        //Si c'est un client qui envoie, il parcoure la liste des client du serveur et le serveur renvoie le message
        for (ChatInterface c : clients) {
            c.sendMessage(message, sender);
        }
    }

    @Override
    public void addClient(ChatInterface c) throws RemoteException {
        //Ajouter le client pour pouvoir lui envoyer un message
        clients.add(c);
    }

    @Override
    public void delClient(ChatInterface c) throws RemoteException {
        clients.remove(c);
    }

    @Override
    public List<ChatInterface> getClients() throws RemoteException {
        return clients;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getUuid() {
        return randomId.toString();
    }
}
