package nos;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class No2 {
    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(12345);
            System.out.println("Servidor iniciado na porta 12345");
            Socket client = server.accept();
            System.out.println(client.getInetAddress().getHostAddress()+" conectado!!!");
            Scanner entrada = new Scanner(client.getInputStream());
            while(entrada.hasNextLine()){
                String[] lista = entrada.nextLine().split(",");
                if (lista[0].equals(lista[1])){
                    client = server.accept();
                    ObjectOutputStream saida = new ObjectOutputStream(client.getOutputStream());
                    saida.flush();
                    saida.write(0);
                    saida.close();
                }else{
                    Socket cliente = new Socket("localhost", 23456);
                    System.out.println("Conectado");
                    PrintStream saida = new PrintStream(cliente.getOutputStream());
                    saida.println(lista[0]+","+lista[1]);
                    saida.close();
                    cliente.close();
                    cliente = new Socket("localhost", 23456);
                    System.out.println("Reconectando!");
                    ObjectInputStream entrada2 = new ObjectInputStream(cliente.getInputStream());
                    int retorno = entrada2.read();
                    System.out.println("Recebendo mensagem de retorno!");
                    System.out.println("Resultado de Envio: "+retorno);
                    entrada2.close();
                    cliente.close();
                    client = server.accept();
                    ObjectOutputStream saida2 = new ObjectOutputStream(client.getOutputStream());
                    saida2.flush();
                    saida2.write(retorno);
                    saida2.close();
                }
            }
            entrada.close();
            server.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
