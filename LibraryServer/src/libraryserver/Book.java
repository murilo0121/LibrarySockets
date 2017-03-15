/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libraryserver;

/**
 *
 * @author murilo.erhardt
 */
public class Book {
    private int code;
    private String title;
    private String year;
    private String usrCode;

    public Book(int code, String title, String year) {
        this.code = code;
        this.title = title;
        this.year = year;
    }

    
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getUsrCode() {
        return usrCode;
    }

    public void setUsrCode(String usrCode) {
        this.usrCode = usrCode;
    }
}
