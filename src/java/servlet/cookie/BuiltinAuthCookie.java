/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet.cookie;

import javax.servlet.http.Cookie;

/**
 *
 * @author dangminhtien
 */
public class BuiltinAuthCookie extends CookieParseable {

    private String email;
    private String password;
    
    public BuiltinAuthCookie() {
        
    }
    
    public BuiltinAuthCookie(String email, String password) {
        assert (email != null && !email.trim().isEmpty());
        assert (password != null && !password.trim().isEmpty());
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public boolean parseFromCookie(Cookie cookie) {
        String cookieName = cookie.getName();
        if (cookieName.contains("acong")) {
            email = cookieName.replace("acong", "@");
            password = cookie.getValue();
        } else {
            return false;
        }
        if (password.trim().isEmpty()) {
            return false;
        }
        return true;
    }

    @Override
    public Cookie getCookie() {
        String cookieName;
        if (email.contains("@")) {
            cookieName = email.replace("@", "acong");
        } else {
            throw new RuntimeException("Invalid email");
        }
        return new Cookie(cookieName, password);
    }

}
