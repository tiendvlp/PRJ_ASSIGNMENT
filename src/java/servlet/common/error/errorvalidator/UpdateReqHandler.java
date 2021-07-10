package servlet.common.error.errorvalidator;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import servlet.common.error.UpdateUserError;
import servlet.common.sessionmodel.SelectedUserSessionModel;

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
    private boolean hasError;
    private UpdateUserError error = new UpdateUserError();

    @Override
    public boolean init(HttpServletRequest req) {
        
        SelectedUserSessionModel selectedUser = (SelectedUserSessionModel) req.getSession().getAttribute(SelectedUserSessionModel.SESSION_KEY);
        
        if (selectedUser == null) {
            req.getServletContext().log("Can not find selected user in session");
            hasError = true;
            return false;
        }
        SelectedUserSessionModel.EditedValue editedValue = selectedUser.getEditedValue();
        
        if (editedValue.getEmail().trim().isEmpty()) {
            hasError = true;
            error.setEmailEmpty();
        }
        
        if (editedValue.getSignInMethod().equals("BUILTIN")) {
            if (editedValue.getPassword().trim().length() < 6 || editedValue.getPassword().trim().length() > 20) {
                hasError = true;
                error.setPasswordEmpty();
            }
        }

        if (editedValue.getFullName().trim().isEmpty()) {
            hasError = true;
            error.setFullNameEmpty();
        }
        if (editedValue.getAddress().trim().isEmpty()) {
            hasError = true;
            error.setAddressEmpty();
        }
        if (!ValidatorUtils.validatePhoneNumber(editedValue.getPhoneNumber())) {
            hasError = true;
            error.setPhoneEmpty();
        }
        return hasError;
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
