/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libraryserver;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import libraryserver.User;

/**
 *
 * @author murilo.erhardt
 */
public class LibraryServer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        createDB();

        ServerSocket servidor = new ServerSocket(12345);

        while (true) {
            System.out.println("conectou");
            Socket cliente = servidor.accept();
            System.out.println("Novo cliente - >localaddr:" + cliente.getLocalAddress() + " inet" + cliente.getInetAddress());

            Thread thread = new Thread(new Listener(cliente));
            thread.start();
        }

        //Singleton st = Singleton.getInstance();
        //st.getSomeThing();
    }

    private static void createDB() {

        User user = new User("0", "murilo", "123", false);
        User user1 = new User("1", "lara", "123", true);
        User user2 = new User("2", "claudio", "123", true);
        User user3 = new User("3", "natanael", "123", true);

        Singleton st = Singleton.getInstance();
        List<User> listOfUser = new ArrayList<>();

        listOfUser.add(user);
        listOfUser.add(user1);
        listOfUser.add(user2);
        listOfUser.add(user3);

        st.setListOfUser(listOfUser);

        Book book = new Book("0", "game of thrones", "2029");
        Book book2 = new Book("1", "use a cabeca", "1999");
        Book book3 = new Book("2", "python", "1998");
        Book book4 = new Book("3", "como nao ser um idota", "1990");
        Book book5 = new Book("4", "como ser legal", "1995");
        Book book6 = new Book("5", "harry potter", "2003");
        Book book7 = new Book("6", "dino trovao", "14 a.c");

        List<Book> listOfBooks = new ArrayList<>();
        listOfBooks.add(book);
        listOfBooks.add(book2);
        listOfBooks.add(book3);
        listOfBooks.add(book4);
        listOfBooks.add(book5);
        listOfBooks.add(book6);
        listOfBooks.add(book7);

        st.setListOfBooks(listOfBooks);
        
        List<Loan> listReserv = new ArrayList<>();
        st.setListReserv(listReserv);

    }

}
