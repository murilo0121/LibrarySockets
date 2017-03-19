/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libraryserver;

import java.net.Socket;

/**
 *
 * @author muriloerhardt
 */
public class Loan {
    private Socket socket;
    private String codBook; 
    private String codUser;

    public Loan(Socket socket, String codBook, String codUser) {
        this.socket = socket;
        this.codBook = codBook;
        this.codUser = codUser;
    }

    public String getCodUser() {
        return codUser;
    }

    public void setCodUser(String codUser) {
        this.codUser = codUser;
    }
    
    



    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public String getCodBook() {
        return codBook;
    }

    public void setCodBook(String codBook) {
        this.codBook = codBook;
    }
    
    
}
