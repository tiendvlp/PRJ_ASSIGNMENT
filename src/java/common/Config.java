/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

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
    public static final String USER_INFO_PAGE = "UserInfo";
    public static final String USER_INFO_UI = "UserInfoPage";
    public static final String CART_VIEW = "ViewYourCartUI";

    public static final String SEARCH_PAGE = "Search";
    public static final String SEARCH_JSP = "SearchPage";
    public static final String LOGIN_PAGE = "LoginPage";
    public static final String REGISTER_PAGE = "Register";
    public static final String REGISTER_JSP = "register.jsp";
    public static final String LOGIN_UI = "login.html";
    public static final String VIEW_CART_PAGE = "ViewYourCart";
    public static final String SHOPPING_PAGE = "ShoppingOnline";
    public static final String SHOPPING_JSP = "ShoppingPage";
    public static final String VERIFY_EMAIL_PAGE = "VerifiedEmail";

    public static final String REMOVE_CART_ITEM_CONTROLLER = "RemoveSelectedItemsController";
    public static final String ADD_ITEM_TO_CART_CONTROLLER = "AddItemToCartController";
    public static final String SHOPPING_CONTROLLER = "onlineshoppinFg";
    public static final String SIGNOUT_CONTROLLER = "SignOutController";
    public static final String GET_SHOPPING_PRODUCT_CONTROLLER = "GetShoppingProductController";
    public static final String CONFIRM_EMAIL_CONTROLLER = "ConfirmEmailController";
    public static final String SEND_VERIFIED_CONTROLLER = "SendVerifyCodeController";
    public static final String UPDATE_CONTROLLER = "UpdateController";
    public static final String DISPATCH_CONTROLLER = "dispatchercontroller";
    public static final String DELETE_USER_CONTROLLER = "DeleteController";
    public static final String CREATE_ACCOUNT_CONTROLLER = "CreateAccountController";
    public static final String REGISTRATION_PAGE = "registrationpage.jsp";
    public static final String SEARCH_LASTNAME_CONTROLLER = "SearchLastNameController";
    public static final String CHECKOUT_CONTROLLER = "CheckoutController";
    public static final String LOGIN_CONTROLLER = "LoginController";

    public static class Action {

        public static final String VERIFY_MAIL_ACTION = "verifiedmail";
        public static final String SEND_VERIFY_CODE_ACTION = "send verify code";
        public static final String CONFIRM_VERIFY_CODE_ACTION = "confirm email";
        public static final String SEARCH_ACTION = "Search";
        public static final String LOGIN_ACTION = "Login";
        public static final String RESET_ACTION = "Reset";
        public static final String REGISTER_ACTION = "Register";
        public static final String CREATE_ACCOUNT_ACTION = "Create account";
        public static final String REMOVE_CART_ITEM_ACTION = "Remove Selected Items";
        public static final String DELETE_ACTION = "Delete";
        public static final String SIGNOUT_ACTION = "SignOut";
        public static final String EDIT_ACTION = "Edit";
        public static final String ADD_ITEM_TO_CART_ACTION = "Add item to cart";
        public static final String VIEW_CART_ACTION = "View your cart";
        public static final String CHECKOUT_ACTION = "Checkout";
        public static final String USER_INFO_ACTION = "User Info";
        public static final String UPDATE_ACTION = "Update";
        public static final String REGISTRATION_ACTION = "Register";
        public static final String SHOPPING_ACTION = "Shopping Online";
        public static final String VIEW_USER_INFO_ACTION = "viewuserinfo";
    }
}
