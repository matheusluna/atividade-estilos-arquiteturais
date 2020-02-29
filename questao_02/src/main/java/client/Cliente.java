package client;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Logger;

public class Cliente {
    private static final Logger log = Logger.getLogger(Cliente.class.getName());

    public static void main(String[] args) {
        String data = "op-1,10,5";

        try  {
            Socket cli = new Socket("127.0.0.1", 1234);

            ObjectOutputStream outputStream = new ObjectOutputStream(cli.getOutputStream());
            outputStream.writeObject(data);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
