/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.dto;

import java.sql.Date;

/**
 *
 * @author dangminhtien
 */
public class UserDto {
    private String email;
    private String fullName;
    private String password;
    private String phoneNumber; 
    private String address;
    private String role;
    private boolean isVerified;
    private String signInMethod;

    public UserDto(String email, boolean isVerified,String signInMethod, String fullName, String password, String phoneNumber, String address, String role) {
        this.email = email;
        this.signInMethod = signInMethod;
        this.fullName = fullName;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.role = role;
        this.isVerified = isVerified;
    }

    public String getSignInMethod() {
        return signInMethod;
    }

    public void setSignInMethod(String signInMethod) {
        this.signInMethod = signInMethod;
    }
    
    public boolean isIsVerified() {
        return isVerified;
    }

    public void setIsVerified(boolean isVerified) {
        this.isVerified = isVerified;
    }

    public UserDto () {
        
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
