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
        Socket nClient = client;

        while (true) {
            System.out.println("endereco do cliente:" + nClient.getInetAddress());
            InputStream cliente = nClient.getInputStream();
            System.out.println("localaddr:" + nClient.getLocalAddress() + " inet" + nClient.getInetAddress());
            Scanner s;
            s = new Scanner(cliente);

            try {
                s = new Scanner(nClient.getInputStream());
            } catch (IOException ex) {
                Logger.getLogger(Listener.class.getName()).log(Level.SEVERE, null, ex);
            }
            while (s.hasNextLine()) {
                try {
                    decodeMessage(s.nextLine(), nClient);
                    break;
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

    private static String getUserCodeByName(String name) {
        String cod = null;
        Singleton st = Singleton.getInstance();
        List<User> users = st.getListOfUser();
        for (User user : users) {
            if (user.getNome().equals(name)) {
                cod = user.getCode();
            }
        }
        return cod;
    }

    private static void decodeMessage(String message, Socket nCliente) throws IOException {

        PrintStream ps = new PrintStream(nCliente.getOutputStream());
        OutputStream os = nCliente.getOutputStream();
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
            } else {
                result = "Usuário nao encontrado->";
                ps.println(result);
            }
        }

        //COD 10 empresta livro
        if (parts[0].equals("10")) {
            Singleton st = Singleton.getInstance();
            List<Book> books = st.getListOfBooks();
            boolean loanOk = false; //true emprestou, false nao emprestou
            for (Book book : books) {
                if (book.getCode().equals(parts[1])) {
                    String bookUsrCode = book.getUsrCode();
                    if (bookUsrCode == null || bookUsrCode.isEmpty()) {
                        book.setUsrCode(getUserCodeByName(parts[2]));
                        st.getListOfBooks().remove(Integer.parseInt(book.getCode()));
                        st.getListOfBooks().add(Integer.parseInt(book.getCode()), book);
                        ps.println("11->" + book.getTitle() + " emprestado com sucesso");
                        loanOk = true;
                        break;
                    }
                }
            }
            if(loanOk == false){
                ps.println("12->Falhou ao emprestar o livro, verifique se ele está disponível para emprestimo. É possível reservar um livro no menu de reserva!");
            }


        }

        //COD 20 = listar livros
        if (parts[0].equals("20")) {
            Singleton st = Singleton.getInstance();
            List<Book> books = st.getListOfBooks();

            result = "20->";
            result = result + "|Codigo|\t|Status|\t\t|Nome /Ano|->";
            String status;
            for (Book book : books) {
                if (book.getUsrCode() == null) {
                    status = "Disp.";
                } else {
                    status = "Indi.";
                }
                result = result + "   " + book.getCode() + " \t\t" + status + "\t\t\t" + book.getTitle() + "(" + book.getYear() + ")->";
            }
            ps.println(result);
        }

        //COD 25 Lista livros emprestados pelo cliente
        if (parts[0].equals("25")) {
            String userCod = getUserCodeByName(parts[1]);
            Singleton st = Singleton.getInstance();
            List<Book> books = st.getListOfBooks();
            result = "20->|Codigo|\t\t|Titulo|->";
            for (Book book : books) {
                String bookTemp = book.getUsrCode();
                if (bookTemp != null) {
                    if (bookTemp.equals(userCod)) {
                        result = result + "   " + book.getCode() + "\t\t\t" + book.getTitle() + "->";

                    }
                }
            }

            ps.println(result);
        }

        //COD 30 Devolver livro
        if (parts[0].equals("30")) {
            Singleton st = Singleton.getInstance();
            List<Book> books = st.getListOfBooks();
            for (Book book : books) {
                if (book.getCode().equals(parts[1])) {
                    String bookUsrCode = book.getUsrCode();
                    if (bookUsrCode != null) {
                        if (book.getUsrCode().equals(getUserCodeByName(parts[2]))) {
                            book.setUsrCode(null);
                            st.getListOfBooks().remove(Integer.parseInt(book.getCode()));
                            st.getListOfBooks().add(Integer.parseInt(book.getCode()), book);
                            ps.println("31->" + book.getTitle() + " devolvido com sucesso");
                            break;
                        }

                    }
                }
            }
            ps.println("32-> Ocorreu um problema, tente novamente");
        }

        //COD 50 = cadastrar livro
        if (parts[0].equals("50")) {
            Singleton st = Singleton.getInstance();
            StringBuilder sb = new StringBuilder();
            sb.append("");
            sb.append(st.getListOfBooks().size());
            String strI = sb.toString();
            Book book = new Book(strI, parts[1], parts[2]);

            st.addBook(book);

            ps.println("50->Livro " + book.getTitle() + " adicionado com sucesso. Cod: " + book.getCode());
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
