/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Models.User;

/**
 *
 * @author MSI
 */

    public final class LoggedUser {
    private User user;
    private final static LoggedUser INSTANCE=new LoggedUser();
    public static LoggedUser get_instace()
    {
        return INSTANCE;
    }
    public void setUser (User u)
    {
        this.user=u;
    }
    public User getUser() 
    {
    return this.user;
    }
    
  
}
 

