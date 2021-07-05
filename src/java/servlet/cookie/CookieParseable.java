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
public abstract class CookieParseable {
    
    public CookieParseable() {}
    
    public abstract boolean parseFromCookie (Cookie cookie);
    
    public abstract Cookie getCookie ();
}
