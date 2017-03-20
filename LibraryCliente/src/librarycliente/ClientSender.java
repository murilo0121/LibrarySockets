/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package librarycliente;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author muriloerhardt
 */
public class ClientSender implements Runnable {

    private static Socket sockets;
    public ClienteInfo clienteInfo;
    private boolean controllCliente = false;

    public ClientSender(Socket socket, ClienteInfo clienteInfo) {
        this.sockets = socket;
        this.clienteInfo = clienteInfo;
    }

    public static void sender(ClienteInfo clienteInfo) throws IOException, InterruptedException {
        Socket socket = sockets;
        String login = null;
        String password = null;
        ClienteInfo cliInfo = clienteInfo;

        Scanner teclado = new Scanner(System.in);
        PrintStream saida = null;
        saida = new PrintStream(socket.getOutputStream());
        while (clienteInfo.getType() > 2 ) {
            System.out.println("-----------------Bem vindo a livraria-----------------\n\n");//17 ---
            System.out.println("Login: ");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            login = reader.readLine();
            clienteInfo.setNome(login);
            System.out.println("Senha: ");
            password = reader.readLine();
            clienteInfo.setSenha(password);
            saida.println("40->" + login + "->" + password);
            Thread.sleep(1000);

        }
        if (clienteInfo.getType() == 0) {
            showMenuForAdmin( socket, cliInfo);
        }
        if (clienteInfo.getType() == 1) {
            showMenuForUser( socket, cliInfo);
        }

    }

    @Override
    public void run() {
        try {
            sender(clienteInfo);
        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(ClientSender.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void showMenuForUser(Socket socket, ClienteInfo cliInfo) throws IOException, InterruptedException {
        String option;
        int optionInt = 0;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("-------------------------");
        System.out.println("1. Lista Livros");
        System.out.println("2. Emprestar Livro");
        System.out.println("3. Devolver Livros");
        System.out.println("4. Reservar Livro");
        System.out.println("5. Meus Livros");
        option = reader.readLine();
        try {
            optionInt = Integer.parseInt(option);
        } catch (Exception ex) {
            System.out.println("Opcao inválida");
        }
        if (optionInt == 1) {
            listBooks(socket);
        }
        if (optionInt == 2) {
            lendBook(socket, cliInfo);
        }
        if (optionInt == 3) {
            returnBook(socket, cliInfo);
        }
        if (optionInt == 4) {
            reservBook(socket, cliInfo);
        }
        if (optionInt == 5) {
            myBooks(socket,cliInfo);
        }

        System.out.println("-------------------------");
        Thread.sleep(1000);
        showMenuForUser(socket, cliInfo);

    }

    public static void showMenuForAdmin(Socket socket, ClienteInfo cliInfo) throws IOException, InterruptedException {
        String option;
        int optionInt = 0;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("-------------------------");
        System.out.println("1. Lista Livros");
        System.out.println("2. Cadastrar Livro");
        System.out.println("3. Remover Livro");
        

        option = reader.readLine();
        try {
            optionInt = Integer.parseInt(option);
        } catch (Exception ex) {
            System.out.println("Opcao inválida");
        }
        if (optionInt == 1) {
            listBooks(socket);
        }
        if (optionInt == 2) {
            registerBook(socket);
        }
        if (optionInt == 3) {
            deleteBook(socket);
        }
        //System.out.println("Addr cliente:" + socket.getLocalAddress() );
        
        
        System.out.println("-------------------------");
        Thread.sleep(1000);
        showMenuForAdmin(socket, cliInfo);
    }
    
    private static void deleteBook(Socket socket) throws IOException{
        PrintStream saida = null;
        saida = new PrintStream(socket.getOutputStream());
        String codBook;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Codigo do livro para deletar:");
        codBook = reader.readLine();
        saida.println("60->"+codBook);
    }
    
    
    //COD 25
    private static void myBooks(Socket socket, ClienteInfo cliInfo) throws IOException{
        PrintStream saida = null;
        saida = new PrintStream(socket.getOutputStream());
        saida.println("25->"+cliInfo.getNome());
    }
    
    //COD 70 reservar
    private static void reservBook(Socket socket, ClienteInfo cliInfo) throws IOException{
        PrintStream saida = null;
        saida = new PrintStream(socket.getOutputStream());
        String codBook;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Codigo do livro para reservar:");
        codBook = reader.readLine();
        saida.println("70->"+codBook+"->"+cliInfo.getNome());
    }
    
    
    //COD 10, emprestar livro
    private static void lendBook(Socket socket, ClienteInfo cliInfo) throws IOException, InterruptedException{
        PrintStream saida = null;
        saida = new PrintStream(socket.getOutputStream());
        String codBook;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Codigo do livro:");
        codBook = reader.readLine();
        saida.println("10->"+codBook+"->"+cliInfo.getNome());
    }

    //20 é o código para lista livros
    private static void listBooks(Socket socket) throws IOException {
        PrintStream saida = null;
        saida = new PrintStream(socket.getOutputStream());
        saida.println("20->");
    }
    
    //50 - cadastrar livro
    private static void registerBook(Socket socket) throws IOException, InterruptedException {
        PrintStream saida = null;
        saida = new PrintStream(socket.getOutputStream());
        String bookTitle;
        String bookYear;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Titulo do Livro:");
        bookTitle = reader.readLine();
        System.out.println("Ano do Livro:");
        bookYear = reader.readLine();
        saida.println("50->"+bookTitle+"->"+bookYear);
        
    }
    
    //COD 30 - devolver livro
    private static void returnBook(Socket socket, ClienteInfo cliInfo) throws IOException{
        PrintStream saida = null;
        saida = new PrintStream(socket.getOutputStream());
        String codBook;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Codigo do livro:");
        codBook = reader.readLine();
        saida.println("30->"+codBook+"->"+cliInfo.getNome());
    }

    private static void cleanScreen() {

        System.out.println("-------------------------");
    }

}
