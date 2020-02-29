package nos;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Logger;

public class No1 {
    private static final Logger log = Logger.getLogger(No1.class.getName());
    public static void main(String args[]) throws IOException, ClassNotFoundException {
        ServerSocket serverSocket;
        try {
            serverSocket = iniciarServidor(1234);
        } catch (IOException e) {
            serverSocket = iniciarServidor(1233);
            e.printStackTrace();
        }

        log.info("[NODE1] Servidor executando na porta: " + serverSocket.getLocalPort());

        for(;;) {
            Socket cliente = serverSocket.accept();
            ObjectInputStream ois = new ObjectInputStream(cliente.getInputStream());
            String op = (String) ois.readObject();
            String[] dados = op.split(",");
            if(dados[0].equals("op-1")) {
                log.info("[NODE1] Executando operação 1: 2 * " + Integer.parseInt(dados[1]) + " * " + Integer.parseInt(dados[2]) + " = " + operacao(Integer.parseInt(dados[1]), Integer.parseInt(dados[2])));
            } else {
                log.info("[NODE1] Enviando requisição para NODE3");
                Socket node3 = new Socket("127.0.0.1",4444);
                ObjectOutputStream oos = new ObjectOutputStream(node3.getOutputStream());
                oos.writeObject(op);
            }
        }
    }

    private static double operacao(int num1, int num2) {
        return 2 * num1 * num2;
    }

    private static ServerSocket iniciarServidor(int porta) throws IOException {
        return new ServerSocket(porta);
    }
}
