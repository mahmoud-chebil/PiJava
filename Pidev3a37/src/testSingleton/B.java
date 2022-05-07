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
public class B {
    
    private int X;
    private static B instance;
    
    private B(){}
    
    private B(int X){
        this.X = X;
    }
    
    public static B getInstance(){
        if (instance == null)
            instance = new B();
        return instance;
    }

    public int getX() {
        return X;
    }

    public void setX(int X) {
        this.X = X;
    }
    
}
