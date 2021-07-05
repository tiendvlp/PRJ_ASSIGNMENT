/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet.error;

/**
 *
 * @author dangminhtien
 */
public class UpdateUserError implements PresentableError {
    private String emailEmpty = null;
    private String passwordEmpty = null;
    private String fullNameEmpty = null;
    private String addressEmpty = null;
    private String phoneEmpty = null;
    private String emailDuplicate = null;

    public String getEmailEmpty() {
        return emailEmpty;
    }

    public String getPasswordEmpty() {
        return passwordEmpty;
    }

    public String getFullNameEmpty() {
        return fullNameEmpty;
    }

    public String getAddressEmpty() {
        return addressEmpty;
    }

    public String getPhoneEmpty() {
        return phoneEmpty;
    }

    public String getEmailDuplicate() {
        return emailDuplicate;
    }
    
    public void setEmailEmpty() {
        this.emailEmpty = "Email can not be empty";
    }

    public void setPasswordEmpty() {
        this.passwordEmpty = "Password can not be empty";
    }

    public void setFullNameEmpty() {
        this.fullNameEmpty = "FullName can not be empty";
    }

    public void setAddressEmpty() {
        this.addressEmpty = "Address can not be empty";
    }

    public void setPhoneEmpty() {
        this.phoneEmpty = "Phone can not be empty";
    }

    public void setEmailDuplicate() {
        this.emailDuplicate = "Email already exist";
    }

}
