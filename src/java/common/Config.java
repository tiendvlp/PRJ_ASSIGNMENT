/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

import static common.Config.Action.*;

public class Config {

    public static final String DB_URL = "jdbc:sqlserver://localhost:1433;"
            + "DatabaseName=test";
    public static final String USER_NAME = "sa";
    public static final String PASSWORD = "VeryStr0ng_password";

    public static String GOOGLE_CLIENT_ID = "627419276955-6305n3qcpeo1klqjgjk3b7dk1158es9j.apps.googleusercontent.com";
    public static String GOOGLE_CLIENT_SECRET = "r4V7Niqs405TAEoL2D69GGjf";
    public static String GOOGLE_REDIRECT_URI = "http://localhost:8081/DevlogsPrj/logingoogle";
    public static String GOOGLE_LINK_GET_TOKEN = "https://accounts.google.com/o/oauth2/token";
    public static String GOOGLE_LINK_GET_USER_INFO = "https://www.googleapis.com/oauth2/v1/userinfo?access_token=";
    public static String GOOGLE_GRANT_TYPE = "authorization_code";

    public static final String VERIFIED_MAIL_PAGE = "verifymail.jsp";
    public static final String SEND_VERIFY_CODE_CONTROLLER = "sendverifycodeservlet";
    public static final String USER_INFO_PAGE = "userinfo.jsp";
    public static final String SEARCH_PAGE = "search.jsp";
    public static final String LOGIN_PAGE = "login.html";
    public static final String VIEW_CART_PAGE = "viewcart.jsp";
    public static final String REMOVE_CART_ITEM_CONTROLLER = "removecartitemservlet";
    public static final String PROCESS_REQUEST_CONTROLLER = "processrequestservlet";
    public static final String SHOPPING_PAGE = "onlineshopping.jsp";
    public static final String SHOPPING_CONTROLLER = "onlineshopping";
    public static final String SIGNOUT_CONTROLLER = "signoutservlet";
    public static final String UPDATE_CONTROLLER = "updateservlet";
    public static final String DISPATCH_CONTROLLER = "dispatchercontroller";
    public static final String DELETE_USER_CONTROLLER = "deleteservlet";
    public static final String LOGIN_CONTROLLER = "loginservlet";
    public static final String CREATE_ACCOUNT_CONTROLLER = "createaccountservlet";
    public static final String REGISTRATION_PAGE = "registrationpage.jsp";
    public static final String SEARCH_LASTNAME_CONTROLLER = "searchlastnameservlet";
    public static final String ADD_ITEM_TO_CART_CONTROLLER = "additemtocartservlet";
    public static final String CHECKOUT_CONTROLLER = "checkoutservlet";
    public static final String CONFIRM_VERIFY_CODE_CONTROLLER = "confirmverifycodeservlet";

    public static class Action {

        public static final String VERIFY_MAIL_ACTION = "verifiedmail";
        public static final String SEND_VERIFY_CODE_ACTION = "send verify code";
        public static final String CONFIRM_VERIFY_CODE_ACTION = "confirm email";
        public static final String SEARCH_ACTION = "Search";
        public static final String LOGIN_ACTION = "Login";
        public static final String REGISTER_ACTION = "Register";
        public static final String CREATE_ACCOUNT_ACTION = "Create account";
        public static final String REMOVE_CART_ITEM_ACTION = "Remove Selected Items";
        public static final String DELETE_ACTION = "Delete";
        public static final String SIGNOUT_ACTION = "SignOut";
        public static final String ADD_ITEM_TO_CART_ACTION = "Add item to cart";
        public static final String VIEW_CART_ACTION = "View your cart";
        public static final String CHECKOUT_ACTION = "Checkout";
        public static final String UPDATE_ACTION = "Update";
        public static final String REGISTRATION_ACTION = "Register";
        public static final String SHOPPING_ACTION = "ShoppingOnline";
        public static final String VIEW_USER_INFO_ACTION = "viewuserinfo";
    }

    public static String getRegisterUrl() {
        return DISPATCH_CONTROLLER + "?btAction=" + REGISTER_ACTION;
    }

    public static String getShoppingOnlineUrl() {
        return DISPATCH_CONTROLLER + "?btAction=" + SHOPPING_ACTION;
    }

    public static String getCheckoutUrl() {
        return DISPATCH_CONTROLLER
                + "?btAction=" + CHECKOUT_ACTION;
    }

    public static String getViewCartUrl() {
        return DISPATCH_CONTROLLER
                + "?btAction=" + VIEW_CART_ACTION;
    }

    public static String getVerifiedMailPageUrl() {
        return DISPATCH_CONTROLLER
                + "?btAction=" + VERIFY_MAIL_ACTION;
    }

    public static String getFullSearchUrl(String searchValue) {
        String url = DISPATCH_CONTROLLER
                + "?btAction=" + SEARCH_ACTION;
        if (!searchValue.trim().isEmpty()) {
            url += "&txtSearch=" + searchValue;
        }
        return url;

    }

    public static String getLoginUrl(String userEmail, String userPassword) {
        return DISPATCH_CONTROLLER
                + "?btAction=" + LOGIN_ACTION
                + "&txtUserId=" + userEmail
                + "&txtPassword=" + userPassword;
    }

    public static String getFullDeleteUrl(String userEmail, String lastSearchValue) {
        return DISPATCH_CONTROLLER
                + "?btAction=" + DELETE_ACTION
                + "&txtLastSearchValue=" + lastSearchValue
                + "&txtUserEmail=" + userEmail;
    }

    public static String getUserInfoUrl() {
        return DISPATCH_CONTROLLER
                + "?btAction=" + VIEW_USER_INFO_ACTION;
    }

    public static String getSignOutUrl() {
        return DISPATCH_CONTROLLER
                + "?btAction=" + SIGNOUT_ACTION;

    }
}
