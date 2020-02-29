package nos;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintStream;
import java.net.Socket;

public class No1 {
    public static void main(String[] args) {

        try {
            Socket client = new Socket("localhost", 12345);
            System.out.println("Conectado");
            PrintStream saida = new PrintStream(client.getOutputStream());
            saida.println("1,2");
            System.out.println("Enviando mensagem!");
            saida.close();
            client.close();
            System.out.println("Fechando conexão!");
            client = new Socket("localhost", 12345);
            System.out.println("Reconectando!");
            ObjectInputStream entrada = new ObjectInputStream(client.getInputStream());
            int retorno = entrada.read();
            System.out.println("Recebendo mensagem de retorno!");
            System.out.println("Resultado de Envio: "+retorno);
            entrada.close();
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
