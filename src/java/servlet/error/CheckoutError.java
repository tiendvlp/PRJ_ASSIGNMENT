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
public class CheckoutError implements PresentableError {
    private String fullNameEmpty = null;
    private String addressEmpty = null;
    private String phoneEmpty = null;
    private String phoneInvalid = null;

    public String getFullNameEmpty() {
        return fullNameEmpty;
    }

    public String getAddressEmpty() {
        return addressEmpty;
    }

    public String getPhoneEmpty() {
        return phoneEmpty;
    }

    public void setFullNameEmpty() {
        this.fullNameEmpty = "FullName can not be empty";
    }

    public void setAddressEmpty() {
        this.addressEmpty = "Address can not be empty";
    }

    public void setPhoneInvalid() {
        this.phoneInvalid = "Invalid phone number";
    }
    public String getPhoneInvalid() {
        return phoneInvalid;
    }
    
    public void setPhoneEmpty() {
        this.phoneEmpty = "Phone can not be empty";
    }

}
