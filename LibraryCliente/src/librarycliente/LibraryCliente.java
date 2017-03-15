/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package librarycliente;
import java.io.IOException;
import java.io.PrintStream;
import java.net.*;
import java.util.Scanner;
/**
 *
 * @author murilo.erhardt
 */
public class LibraryCliente {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        Socket cliente = new Socket("127.0.0.1", 12345);
        System.out.println("O cliente se conectou ao servidor!");

        Scanner teclado = new Scanner(System.in);
        PrintStream saida = new PrintStream(cliente.getOutputStream());

        while (teclado.hasNextLine()) {
            saida.println(teclado.nextLine());
        }

        saida.close();
        teclado.close();
        cliente.close();
    }
    
}
