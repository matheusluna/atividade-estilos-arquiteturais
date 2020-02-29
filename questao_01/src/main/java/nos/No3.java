package nos;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class No3 {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(23456);
        Socket client = server.accept();
        System.out.println(client.getInetAddress().getHostAddress()+" conectado!!!");
        Scanner entrada = new Scanner(client.getInputStream());
        while (entrada.hasNextLine()){
            String[] lista = entrada.nextLine().split(",");
            Double a = Double.parseDouble(lista[0]);
            Double b = Double.parseDouble(lista[1]);
            Double resultado = Math.pow(a,a) + Math.pow(b,b);
            client = server.accept();
            ObjectOutputStream saida = new ObjectOutputStream(client.getOutputStream());
            saida.flush();
            saida.write(resultado.intValue());
            saida.close();
        }
        entrada.close();
        server.close();
    }
}
