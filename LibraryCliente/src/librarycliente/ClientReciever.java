/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package librarycliente;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author muriloerhardt
 */
public class ClientReciever implements Runnable{

    

    private static Socket socket;
    public static ClienteInfo clienteInfo;
    
    @Override
    public void run() {
        while (true) {
            
            
            InputStream is = null;
            try {
                is = socket.getInputStream();
            } catch (IOException ex) {
                Logger.getLogger(ClientReciever.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            while(true){
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(isr);
                String message = null;
                try {
                   message = br.readLine();
                 } catch (IOException ex) {
                    Logger.getLogger(ClientReciever.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.out.println("Message received from the server : " +message);
            }
        } 
    }

    public ClientReciever(Socket socket, ClienteInfo clienteInfo) {
        this.socket = socket;
        this.clienteInfo = clienteInfo;
    }
    
    //Recebe a mensagem do servidor e verifica se existe algum código
    //que representa alguma açao no programa
    public static void decodeMessage(String message){
        String[] parts = message.split("->");
        //COD 0 = mensagem informativa
        if(parts[0].equals("0")){
            System.out.println(parts[1]);      
        }
        //COD 41 = mensagem de sucesso login adm
        if(parts[0].equals("41")){
            System.out.println("-----------------Bem vindo a livraria "+ clienteInfo.getNome() + "-----------------" ); 
            System.out.println("-----------------Logado como admin                                -----------------\n" ); 
            clienteInfo.setType(0);
            
        }
    }
    
}
