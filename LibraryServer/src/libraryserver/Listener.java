//ISSO TEM QUE SER UM THREAED
package libraryserver;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Listener implements Runnable {

    private Socket client;

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
                System.out.println(s.nextLine());
            }
            System.out.println(client.getInetAddress());
            System.out.println(client.getLocalAddress() + "\n");
        }
    }

    public Listener(Socket client) {
        this.client = client;
    }

}
