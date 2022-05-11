/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author Fares
 */
public class User {
    private int id;
    private String username,telephone,email,password,image,roles,mailcode;
    private boolean is_verified,is_expired;

    public User() {
    }

    public User(int id) {
        this.id = id;
    }

    public User(int id, String username, String telephone, String email, String password) {
        this.id = id;
        this.username = username;
        this.telephone = telephone;
        this.email = email;
        this.password = password;
    }

    public User(String username) {
        this.username = username;
    }

    public User(int id, String username) {
        this.id = id;
        this.username = username;
    }
    
    

    public User(String username , String telephone,String email, String password, String image,boolean is_verified) {
        this.username = username;
        this.telephone = telephone;
        this.email = email;
        this.password = password;
        this.image = image;
        this.is_verified=is_verified;
    }

    public User(int id, String username, String telephone, String email, String password, String image) {
        this.id = id;
        this.username = username;
        this.telephone = telephone;
        this.email = email;
        this.password = password;
        this.image = image;
    }
    

    public User(int id, String username, String telephone, String email, String password, String image, String roles, boolean is_verified, boolean is_expired) {
        this.id = id;
        this.username = username;
        this.telephone = telephone;
        this.email = email;
        this.password = password;
        this.image = image;
        this.roles = roles;
        this.is_verified = is_verified;
        this.is_expired = is_expired;
    }
     public User(int id, String username, String telephone, String email, String password, String image, String roles,String mailcode, boolean is_verified, boolean is_expired) {
        this.id = id;
        this.username = username;
        this.telephone = telephone;
        this.email = email;
        this.password = password;
        this.image = image;
        this.roles = roles;
        this.mailcode=mailcode;
        this.is_verified = is_verified;
        this.is_expired = is_expired;
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }
    
    

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }
 
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isIs_verified() {
        return is_verified;
    }

    public void setIs_verified(boolean is_verified) {
        this.is_verified = is_verified;
    }

    public boolean isIs_expired() {
        return is_expired;
    }

    public void setIs_expired(boolean is_expired) {
        this.is_expired = is_expired;
    }

    public String getMailcode() {
        return mailcode;
    }

    public User(int id, String username, String email, String password, String roles, boolean is_verified, boolean is_expired) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.is_verified = is_verified;
        this.is_expired = is_expired;
    }

    
    
    public void setMailcode(String mailcode) {
        this.mailcode = mailcode;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", username=" + username + ", telephone=" + telephone + ", email=" + email + ", password=" + password + ", image=" + image + ", roles=" + roles + ", mailcode=" + mailcode + ", is_verified=" + is_verified + ", is_expired=" + is_expired + '}';
    }

    public boolean getIsVerified() {
       return is_verified;
    }
    public boolean getIsExpired() {
       return is_expired;
    }

  
   
    
    
}
