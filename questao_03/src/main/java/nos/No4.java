package nos;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Logger;

public class No4 {
    private static final Logger log = Logger.getLogger(No4.class.getName());

    public static void main(String args[]) throws IOException, ClassNotFoundException {
        ServerSocket serverSocket = new ServerSocket(3232);

        for (; ; ) {
            Socket cliente = serverSocket.accept();
            ObjectInputStream ois = new ObjectInputStream(cliente.getInputStream());
            String [] operacao = ((String) ois.readObject()).split(",");
            Integer resultado = null;

            if(operacao[2].equals("node-2")) resultado = Integer.parseInt(operacao[0]) + Integer.parseInt(operacao[1]);
            if(operacao[2].equals("node-3")) resultado = Integer.parseInt(operacao[0]) - Integer.parseInt(operacao[1]);
            System.out.println("Resultado: "+resultado);
            ObjectOutputStream oos = new ObjectOutputStream(cliente.getOutputStream());
            oos.writeObject(resultado);
            ois.close();
            oos.close();
            cliente.close();
        }
    }
}
