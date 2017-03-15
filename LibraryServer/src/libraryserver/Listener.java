//ISSO TEM QUE SER UM THREAED
package libraryserver;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
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

    @Override
    public void run() {
        while (true) {

            Scanner s = null;
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

    private static void decodeMessage(String message) throws IOException {

        OutputStream os = client.getOutputStream();
        OutputStreamWriter osw = new OutputStreamWriter(os);
        BufferedWriter bw = new BufferedWriter(osw);

        String[] parts = message.split("->");
        //COD 40 = tentativa de login
        int resultCode;
        if (parts[0].equals("40")) {
            resultCode = checkUser(parts[1], parts[2]);
            if (resultCode == 41) {
                bw.write(41+"->"+"   ");
                bw.flush();
            }
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
