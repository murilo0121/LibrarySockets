//ISSO TEM QUE SER UM THREAED
package libraryserver;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Listener implements Runnable {

    private static Socket client;

    public Listener(Socket client) {
        this.client = client;

    }

    private static void metodo() throws IOException {

        while (true) {
            InputStream cliente = client.getInputStream();

            Scanner s;
            s = new Scanner(cliente);

            try {
                s = new Scanner(client.getInputStream());
            } catch (IOException ex) {
                Logger.getLogger(Listener.class.getName()).log(Level.SEVERE, null, ex);
            }
            while (s.hasNextLine()) {
                try {
                    decodeMessage(s.nextLine());
                } catch (IOException ex) {
                    Logger.getLogger(Listener.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }


    }

    @Override
    public void run() {

        try {
            metodo();
        } catch (IOException ex) {
            Logger.getLogger(Listener.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private static void decodeMessage(String message) throws IOException {

        PrintStream ps = new PrintStream(client.getOutputStream());
        OutputStream os = client.getOutputStream();
        OutputStreamWriter osw = new OutputStreamWriter(os);
        BufferedWriter bw = new BufferedWriter(osw);

        String[] parts = message.split("->");
        String result;
        //COD 40 = tentativa de login
        int resultCode;
        if (parts[0].equals("40")) {
            resultCode = checkUser(parts[1], parts[2]);
            if (resultCode == 41) {
                result = "41->"; 
                ps.println(result);
            }
            if (resultCode == 42) {
                result = "42->"; 
                ps.println(result);
            }
            else{
                result = "Usuário nao encontrado->"; 
                ps.println(result);
            }
        }
        //COD 20 = listar livros
        if (parts[0].equals("20")) {
            Singleton st = Singleton.getInstance();
            List<Book> books = st.getListOfBooks();

            result = "20->";
            result = result + "|Codigo|\t|Nome /Ano|->";
            for(Book book : books){
                result= result +"   "+ book.getCode()+ " \t\t"+book.getTitle()+ "("+ book.getYear() + ")->";
            }
            ps.println(result);
        }
    }

    //retorn false=adm e true=usr
    private static int checkUser(String login, String senha) {
        Singleton st = Singleton.getInstance();
        List<User> users = st.getListOfUser();
        for (User user : users) {
            if (user.getNome().equals(login)) {
                if (user.getSenha().equals(senha)) {
                    if (user.getType() == false) {
                        return 41;
                    }
                    if (user.getType() == true) {
                        return 42;
                    } else {
                        return 43;
                    }
                }
            }
        }
        return 43;
    }

}
