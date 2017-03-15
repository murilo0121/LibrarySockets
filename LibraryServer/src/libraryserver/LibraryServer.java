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
        
    while (true) {
        Socket cliente = servidor.accept();
        Thread thread = new Thread(  new Listener(cliente) );
        thread.start();
    }   

        //Singleton st = Singleton.getInstance();
        //st.getSomeThing();

    }

}
