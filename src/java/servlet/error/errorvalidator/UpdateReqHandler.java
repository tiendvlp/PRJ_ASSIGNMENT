package servlet.error.errorvalidator;

import javax.servlet.ServletRequest;
import servlet.error.UpdateUserError;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author dangminhtien
 */
public class UpdateReqHandler implements RequestValidator {

    private String txtEmail;
    private String txtRawEmail;
    private String txtPassword;
    private String txtFullName;
    private String txtAddress;
    private String txtPhoneNumber;
    private String userRole;
    private String txtLastSearchValue;
    private String txtSignInMethod;
    private boolean hasError;
    private UpdateUserError error = new UpdateUserError();

    @Override
    public boolean init(ServletRequest req) {
        txtSignInMethod = req.getParameter("txtSignInMethod");
        txtEmail = req.getParameter("txtUserEmail").trim();
        txtRawEmail = req.getParameter("txtRawUserEmail");
        txtPassword = req.getParameter("txtUserPassword");
        txtFullName = req.getParameter("txtUserFullName");
        txtAddress = req.getParameter("txtUserAddress");
        txtPhoneNumber = req.getParameter("txtUserPhoneNumber");
        userRole = req.getParameter("ddlist");
        txtLastSearchValue = req.getParameter("txtLastSearchValue");

        if (txtEmail.trim().isEmpty()) {
            hasError = true;
            error.setEmailEmpty();
        }
        
        if (txtSignInMethod.equals("BUILTIN")) {
            if (txtPassword.trim().isEmpty()) {
                hasError = true;
                error.setPasswordEmpty();
            }
        }

        if (txtFullName.trim().isEmpty()) {
            hasError = true;
            error.setFullNameEmpty();
        }
        if (txtAddress.trim().isEmpty()) {
            hasError = true;
            error.setAddressEmpty();
        }
        if (txtPhoneNumber.trim().isEmpty()) {
            hasError = true;
            error.setPhoneEmpty();
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

    public String getUserRole() {
        return userRole;
    }

    public String getTxtLastSearchValue() {
        return txtLastSearchValue;
    }

    public boolean isHasError() {
        return hasError;
    }

    @Override
    public UpdateUserError getError() {
        if (hasError) {
            return error;
        }
        return null;
    }

    @Override
    public boolean hasError() {
        return hasError;
    }

}
