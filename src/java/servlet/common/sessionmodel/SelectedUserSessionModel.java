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
public class SelectedUserSessionModel implements Serializable {

    public static final String SESSION_KEY = "SelectedUser";

    public static class EditedValue extends Value {

        public EditedValue(String email, String fullName, String password, String phoneNumber, String address, String role, String signInMethod) {
            super(email, fullName, password, phoneNumber, address, role, signInMethod);
        }
    }

    public static class OriginalValue extends Value {

        public OriginalValue(String email, String fullName, String password, String phoneNumber, String address, String role, String signInMethod) {
            super(email, fullName, password, phoneNumber, address, role, signInMethod);
        }
    }

    private static class Value implements Serializable {

        private String email;
        private String fullName;
        private String password;
        private String phoneNumber;
        private String address;
        private String role;
        private String signInMethod;

        public Value(String email, String fullName, String password, String phoneNumber, String address, String role, String signInMethod) {
            this.email = email;
            this.fullName = fullName;
            this.password = password;
            this.signInMethod = signInMethod;
            this.phoneNumber = phoneNumber;
            this.address = address;
            this.role = role;
        }

        public String getSignInMethod() {
            return signInMethod;
        }

        public void setSignInMethod(String signInMethod) {
            this.signInMethod = signInMethod;
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

    private EditedValue editedValue = null;
    private OriginalValue originalValue;
    private boolean isEdited = false;
    
    public SelectedUserSessionModel (OriginalValue originalValue) {
        this.originalValue = originalValue;
    }
    
    public SelectedUserSessionModel () {
        
    }

    public void setOriginalValue(OriginalValue originalValue) {
        this.originalValue = originalValue;
    }

    public EditedValue getEditedValue() {
        return editedValue;
    }

    public OriginalValue getOriginalValue() {
        return originalValue;
    }

    public boolean isEdited() {
        return isEdited;
    }
    
    public void setEditedValue (EditedValue editedValue) {
        this.editedValue = editedValue;
        isEdited = true;
    }
    
    
    
}