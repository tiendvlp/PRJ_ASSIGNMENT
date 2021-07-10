/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet.common.error.errorvalidator;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import servlet.common.error.RegisterError;
import static servlet.common.error.errorvalidator.ValidatorUtils.*;

/**
 *
 * @author dangminhtien
 */
public class RegistrationReqHandler implements RequestValidator {

    private String txtEmail;
    private String txtRawEmail;
    private String txtPassword;
    private String txtFullName;
    private String txtAddress;
    private String txtPhoneNumber;
    private String lastSearchValue;
    private String txtSignInMethod;
    private RegisterError error = new RegisterError();
    private boolean hasError = false;

    @Override
    public boolean init(HttpServletRequest req) {
        txtSignInMethod = req.getParameter("txtSignInMethod");
        txtEmail = req.getParameter("txtUserEmail");
        txtPassword = req.getParameter("txtUserPassword");
        txtFullName = req.getParameter("txtUserFullName");
        txtAddress = req.getParameter("txtUserAddress");
        txtPhoneNumber = req.getParameter("txtUserPhoneNumber");

        if (txtEmail == null || !ValidatorUtils.validateEmail(txtEmail)) {
            hasError = true;
            error.setEmailErrorMessage("Your user id have to be an email");
        }
        
        if (txtSignInMethod.equalsIgnoreCase("BUILTIN")) {
            if (txtPassword == null || txtPassword.length() > 20 || txtPassword.length() < 4 || !txtPassword.matches(".*\\d.*")) {
                hasError = true;
                error.setPasswordErrorMessage("Your password have to have  4-20 chars and contains at least one number");
            }
        } 

        if (txtFullName == null || txtFullName.trim().isEmpty()) {
            hasError = true;
            error.setFullNameErrorMessage("FullName can not be empty");
        }

        if (txtAddress == null || txtAddress.trim().isEmpty()) {
            hasError = true;
            error.setAddressErrorMessage("Address can not be empty");
        }

        if (txtPhoneNumber == null || !validatePhoneNumber(txtPhoneNumber)) {
            hasError = true;
            error.setPhoneErrorMessage("Your phone is incorrect");
        }

        return hasError;
    }

    public String getTxtSignInMethod() {
        return txtSignInMethod;
    }

    public void setTxtSignInMethod(String txtSignInMethod) {
        this.txtSignInMethod = txtSignInMethod;
    }

    public String getTxtEmail() {
        return txtEmail;
    }

    public String getTxtRawEmail() {
        return txtRawEmail;
    }

    public String getTxtPassword() {
        return txtPassword;
    }

    public String getTxtFullName() {
        return txtFullName;
    }

    public String getTxtAddress() {
        return txtAddress;
    }

    public String getTxtPhoneNumber() {
        return txtPhoneNumber;
    }

    public String getLastSearchValue() {
        return lastSearchValue;
    }

    public boolean hasError() {
        return hasError;
    }

    @Override
    public RegisterError getError() {
        if (!hasError) {
            return null;
        }
        return error;
    }

}
