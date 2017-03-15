/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libraryserver;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;

/**
 *
 * @author murilo.erhardt
 */
public class LibraryServer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {

        
        ServerSocket servidor = new ServerSocket(12345);

        Socket cliente = servidor.accept();
        System.out.println("Nova conexão com o cliente "
                + cliente.getInetAddress().getHostAddress()
        );

        Scanner s = new Scanner(cliente.getInputStream());
        while (s.hasNextLine()) {
            System.out.println(s.nextLine());
        }

        s.close();
        servidor.close();
        cliente.close();
        

        //Singleton st = Singleton.getInstance();
        //st.getSomeThing();

    }

}
