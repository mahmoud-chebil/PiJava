/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;



/**
 *
 * @author Lenovo
 */
public final class singletont {

  
  private Reservation res;
  private Integer iddev;
  private final static singletont INSTANCE = new singletont();
  
  private singletont() {}
  
  public static singletont  getInstance() 
  {
    return INSTANCE;
  }

    public Reservation getRes() {
        return res;
    }

    public void setRes(Reservation res) {
        this.res = res;
    }

    public Integer getIddev() {
        return iddev;
    }

    public void setIddev(Integer iddev) {
        this.iddev = iddev;
    }
  
 
}