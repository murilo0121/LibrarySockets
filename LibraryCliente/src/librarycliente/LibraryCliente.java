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
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        Socket cliente = new Socket("127.0.0.1", 12345);
        
        ClienteInfo clienteInfo = new ClienteInfo();
        Thread thread = new Thread(  new ClientReciever(cliente, clienteInfo) );
        thread.start();
        Thread thread2 = new Thread(  new ClientSender(cliente, clienteInfo) );
        thread2.start();
    }
    
}
