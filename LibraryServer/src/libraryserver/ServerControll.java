/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libraryserver;

import java.io.IOException;
import java.io.PrintStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author muriloerhardt
 */
public class ServerControll implements Runnable{

    @Override
    public void run() {
        while(true){
            try {
            Thread.sleep(10000);
        } catch (InterruptedException ex) {
            Logger.getLogger(ServerControll.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            reservControll();
        } catch (IOException ex) {
            Logger.getLogger(ServerControll.class.getName()).log(Level.SEVERE, null, ex);
        }
       removeBookList();
        }
        
    }
    
    //verifica se algum elemento da lista de reserva está disponivel
    private static void reservControll() throws IOException{
        Singleton st = Singleton.getInstance();
        List<Loan> listReserv = st.getListReserv();

        List<Book> listBook = st.getListOfBooks();
        for(int i=0; i<listReserv.size(); i++){
            Loan reservItem = listReserv.get(i);
            for(int j=0; j<listBook.size(); j++){
                Book book=listBook.get(j);
                if(book.getCode().equals(reservItem.getCodBook())){
                    if(book.getUsrCode() == null){
                        book.setUsrCode(reservItem.getCodUser());
                        st.getListOfBooks().remove(Integer.parseInt(book.getCode()));
                        st.getListOfBooks().add(Integer.parseInt(book.getCode()), book);
                        st.getListReserv().remove(i);
                        PrintStream ps;
                        ps = new PrintStream(reservItem.getSocket().getOutputStream());
                        ps.print("75-> O livro "+book.getTitle()+" que voce reservou está com voce agora");
                        break;
                    }
                }
            }
        }
        
    }
    
    private static void removeBookList(){
        
    }
}
