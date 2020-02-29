package nos;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Logger;

public class No1 {
    private static final Logger log = Logger.getLogger(No1.class.getName());

    public static void main(String args[]) throws IOException {
        Socket client = new Socket("127.0.0.1", 4321);

        String operacao = "2,4,node-1";

        ObjectOutputStream oos = new ObjectOutputStream(client.getOutputStream());
        oos.writeObject(operacao);

        ObjectInputStream ois = new ObjectInputStream(client.getInputStream());
        log.info("[NODE1] Resultado: " + ois.readInt());
    }
}
