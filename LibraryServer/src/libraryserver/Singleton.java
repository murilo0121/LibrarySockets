/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libraryserver;

import java.util.List;

/**
 *
 * @author murilo.erhardt
 */
public class Singleton {
    
    private static Singleton myObj;
    private List<Book> listOfBooks;
    private List<User> listOfUser;
    private List<Book> listToDeleteBook;
    private List<Loan> listReserv;
    
    private Singleton(){
        
    }
    
    public static Singleton getInstance(){
        if(myObj == null){
            myObj = new Singleton();
        }
        return myObj;
    }
    
    public void addBook(Book book){
        listOfBooks.add(book);
    }
    
    public void addReserv(Loan reserv){
        listReserv.add(reserv);
    }
    
    public List<Book> getListOfBooks() {
        return listOfBooks;
    }

    public void setListOfBooks(List<Book> listOfBooks) {
        this.listOfBooks = listOfBooks;
    }

    public List<User> getListOfUser() {
        return listOfUser;
    }

    public void setListOfUser(List<User> listOfUser) {
        this.listOfUser = listOfUser;
    }

    public List<Book> getListToDeleteBook() {
        return listToDeleteBook;
    }

    public void setListToDeleteBook(List<Book> listToDeleteBook) {
        this.listToDeleteBook = listToDeleteBook;
    }

    public List<Loan> getListReserv() {
        return listReserv;
    }

    public void setListReserv(List<Loan> listToLoan) {
        this.listReserv = listToLoan;
    }

}
