/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import services.ServiceCartt;
import services.ServiceComImpl;

/**
 *
 * @author poste 1
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    
    
    
    
    public static void main(String[] args) {
        ServiceComImpl ps = new ServiceComImpl ();      
        ServiceCartt pst = new ServiceCartt();

        System.out.println(ps.afficher());
        // TODO code application logic here
    }
    
}
