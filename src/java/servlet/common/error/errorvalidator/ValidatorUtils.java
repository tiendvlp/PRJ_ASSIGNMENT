/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet.common.error.errorvalidator;

/**
 *
 * @author dangminhtien
 */
public class ValidatorUtils {
    public static boolean validateEmail (String email) {
        if (!email.contains("@") || !email.contains(".")) {
            return false;
        }
        
        if (email.indexOf("@") > email.indexOf(".")) {
            return false;
        }
        
        String emailName = email.substring(0, email.indexOf("@"));
        if (emailName.trim().isEmpty()) return false;
        String domain = email.substring(email.indexOf("@"), email.indexOf("."));
        if (domain.trim().isEmpty()) return false;
        String domainType = email.substring(email.indexOf("."), email.length());
        if (domainType.trim().isEmpty()) return false;
        
        return true;
    }
    
    public static boolean validatePhoneNumber (String phoneNumber) {
        return phoneNumber.matches("^\\s*(?:\\+?(\\d{1,3}))?[-. (]*(\\d{3})[-. )]*(\\d{3})[-. ]*(\\d{4})(?: *x(\\d+))?\\s*$");
    }
}
