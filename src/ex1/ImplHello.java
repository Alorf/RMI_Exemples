package ex1;

import java.rmi.RemoteException;

public class ImplHello implements Hello {
    @Override
    public void showMsg() throws RemoteException {
        System.out.println("Hello World!");
    }
}
