/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet.cookie;

import java.io.IOException;
import javax.servlet.http.Cookie;
import servlet.GooglePojo;
import servlet.GoogleUtils;

/**
 *
 * @author dangminhtien
 */
public class GoogleAuthCookie extends CookieParseable {

    public static final String COOKIE_NAME = "GG_ACCESS_TOKEN";
    
    private GooglePojo googlePojo;
    private String accessToken;

    public GoogleAuthCookie() {
    }

    public GoogleAuthCookie(String accessToken) throws IOException {
        this.accessToken = accessToken;
        googlePojo = GoogleUtils.getUserInfo(accessToken);
    }

    @Override
    public boolean parseFromCookie(Cookie cookie) {
        String cookieName = cookie.getName();
        if (cookieName.equals(COOKIE_NAME)) {
            try {
                this.accessToken = cookie.getValue();
                this.googlePojo = GoogleUtils.getUserInfo(accessToken);
                return true;
            } catch (IOException ex) {
                return false;
            }
        }
        return false;
    }

    public GooglePojo getGooglePojo() {
        return googlePojo;
    }

    public String getAccessToken() {
        return accessToken;
    }
    
    @Override
    public Cookie getCookie() {
        return new Cookie (COOKIE_NAME, this.accessToken);
    }

}
