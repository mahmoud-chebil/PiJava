/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author mariem
 */
public class TypeReclamation {
    private int id;
    private String description;

   
    public TypeReclamation(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public TypeReclamation(int id) {
        this.id = id;
    }
    

    public TypeReclamation(String description) {
        this.description = description;
    }

    public TypeReclamation() {}

    
  
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description ;
    }

    public String idtoString() {
return String.valueOf(id);
    
}
    
    
    
    
}
