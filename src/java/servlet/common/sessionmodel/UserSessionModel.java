/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet.common.sessionmodel;

import java.io.Serializable;

/**
 *
 * @author dangminhtien
 */
public class UserSessionModel implements Serializable {
    
    public enum SIGNIN_METHOD {
        BUILTIN, GOOGLE_SIGNIN
    }
    
    public static final String SESSION_KEY = "User";
    
    private String email;
    private String fullName;
    private String password;
    private String phoneNumber; 
    private String address;
    private String role;
    private SIGNIN_METHOD signInMethod;

    public UserSessionModel(String email, String fullName, String password, String phoneNumber, String address, String role, SIGNIN_METHOD signInMethod) {
        this.email = email;
        this.fullName = fullName;
        this.password = password;
        this.signInMethod = signInMethod;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.role = role;
    }

    public SIGNIN_METHOD getSignInMethod() {
        return signInMethod;
    }

    public void setSignInMethod(SIGNIN_METHOD signInMethod) {
        this.signInMethod = signInMethod;
    }

    
    
    public UserSessionModel () {
        
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    
    
}
