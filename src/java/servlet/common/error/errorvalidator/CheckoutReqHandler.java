/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet.common.error.errorvalidator;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import servlet.common.error.CheckoutError;

/**
 *
 * @author dangminhtien
 */
public class CheckoutReqHandler implements RequestValidator {

    private String txtEmail;
    private String txtFullName;
    private String txtAddress;
    private String txtPhoneNumber;
    private boolean hasError;
    private CheckoutError error = new CheckoutError();

    @Override
    public boolean init(HttpServletRequest req) {
        txtEmail = req.getParameter("txtUserEmail");
        txtFullName = req.getParameter("txtUserFullName");
        txtAddress = req.getParameter("txtUserAddress");
        txtPhoneNumber = req.getParameter("txtUserPhoneNumber");

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
        } else if (!ValidatorUtils.validatePhoneNumber(txtPhoneNumber)) {
            hasError = true;
            error.setPhoneInvalid();
        }
        return hasError;
    }

    public String getTxtEmail() {
        return txtEmail;
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

    public boolean isHasError() {
        return hasError;
    }

    @Override
    public CheckoutError getError() {
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
