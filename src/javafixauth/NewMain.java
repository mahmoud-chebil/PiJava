/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package javafixauth;

import Models.User;
import Services.ServiceUser;

/**
 *
 * @author Fares
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        User d =new User(31,"+21627300252","fares@e.e","faresmekni","image");
        User f =new User(4,"dude","+21627300252","sdfghj","faresmekni","image");
        ServiceUser serv = new ServiceUser();
          serv.ajouter(d);
        System.out.println(serv.getAll());
        //System.out.println(serv.getById(4));
        //serv.modifier(f);
       // serv.supprimer(f);
    }
    
}
