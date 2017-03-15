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
public class Singleton {
    
    private static Singleton myObj;
    
    private Singleton(){
        
    }
    
    public static Singleton getInstance(){
        if(myObj == null){
            myObj = new Singleton();
        }
        return myObj;
    }
     
    public void getSomeThing(){
        // do something here
        System.out.println("I am here....");
    }
    
    
}
