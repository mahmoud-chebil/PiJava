/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testSingleton;

/**
 *
 * @author macbook
 */
public class Testmain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        A a1= new A();
        A a2= new A();
        
        
        B b1 = B.getInstance();
        B b2=B.getInstance();
        
        a1.setX(1);
        System.out.println(a1.getX());//1
        a2.setX(2);
        System.out.println(a1.getX());//1
        
        
        b1.setX(4);
        System.out.println(b1.getX());//4
        b2.setX(6);
        System.out.println(b1.getX());//6
        
        
        System.out.println(b1);
        System.out.println(b2);
    }
    
}
