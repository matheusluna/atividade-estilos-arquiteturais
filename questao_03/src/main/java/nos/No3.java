package nos;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Logger;

public class No3 {
    private static final Logger log = Logger.getLogger(No3.class.getName());

    public static void main(String args[]) throws IOException, ClassNotFoundException {
        ServerSocket serverSocket = new ServerSocket(4321);
        for (; ; ) {
            Socket cliente = serverSocket.accept();

            ObjectInputStream ois = new ObjectInputStream(cliente.getInputStream());
            String [] operacao = ((String) ois.readObject()).split(",");
            Integer resultado = null;

            if (operacao[2].equals("node-1")) {
                log.info("[NODE3] Encaminhando mensagem para NODE2");
                Socket node3 = new Socket("localhost", 3333);
                operacao[2] = "node-3";
                resultado = encaminhar(node3, operacao);

            } else if (operacao[2].equals("node-4")) {
                log.info("[NODE3] Encaminhando mensagem para NODE4");
                Socket node4 = new Socket("localhost", 3232);
                operacao[2] = "node-3";
                resultado = encaminhar(node4, operacao);
            }

            ObjectOutputStream oos = new ObjectOutputStream(cliente.getOutputStream());
            oos.writeInt(resultado);
            oos.flush();
            cliente.close();
        }
    }

    private static int encaminhar(Socket socket, String[] operacao) throws IOException, ClassNotFoundException {
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        oos.writeObject(operacao[0]+","+operacao[1]+","+operacao[2]);

        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        Integer resultado = ois.readInt();
        oos.close();
        ois.close();

        return resultado;
    }
}
