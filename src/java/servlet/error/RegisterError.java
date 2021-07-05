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
public class RegisterError implements PresentableError {
   private String emailErrorMessage;
   private String passwordErrorMessage;
   private String fullNameErrorMessage;
   private String phoneErrorMessage;
   private String addressErrorMessage;
   
   public RegisterError() {
       
   }
   
    public String getEmailErrorMessage() {
        return emailErrorMessage;
    }

    public void setEmailErrorMessage(String emailErrorMessage) {
        this.emailErrorMessage = emailErrorMessage;
    }

    public String getPasswordErrorMessage() {
        return passwordErrorMessage;
    }

    public void setPasswordErrorMessage(String passwordErrorMessage) {
        this.passwordErrorMessage = passwordErrorMessage;
    }

    public String getFullNameErrorMessage() {
        return fullNameErrorMessage;
    }

    public void setFullNameErrorMessage(String fullNameErrorMessage) {
        this.fullNameErrorMessage = fullNameErrorMessage;
    }

    public String getPhoneErrorMessage() {
        return phoneErrorMessage;
    }

    public void setPhoneErrorMessage(String phoneErrorMessage) {
        this.phoneErrorMessage = phoneErrorMessage;
    }

    public String getAddressErrorMessage() {
        return addressErrorMessage;
    }

    public void setAddressErrorMessage(String addressErrorMessage) {
        this.addressErrorMessage = addressErrorMessage;
    }

   
}