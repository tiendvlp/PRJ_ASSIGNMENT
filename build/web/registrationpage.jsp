<%-- 
    Document   : RegistrationPage
    Created on : Jun 30, 2021, 10:52:24 PM
    Author     : dangminhtien
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="common.Config"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registration</title>
    </head>
    <body>
        <h1>Register</h1>
        <c:set var="USER" value="${User}"></c:set>
        <form action="${Config.CREATE_ACCOUNT_CONTROLLER}" id="registerForm">
            <c:choose>
                <c:when test="${USER.signInMethod eq 'GOOGLE_SIGNIN'}">
                    ${USER.email}
                    <input name="txtUserEmail" type="hidden" value="${USER.email}"/>
                    <font color="red"> ${UERROR.getEmailErrorMessage()} </font> 
                    <input type="hidden" name="txtSignInMethod" value="GOOGLE_SIGNIN"/>
                </c:when>   
                <c:otherwise>
                    <input type="hidden" name="txtSignInMethod" value="BUILTIN"/>
                    Email: <input name="txtUserEmail" type="text" value="${param.txtUserEmail}"/></br> 
                    <font color="red"> ${UERROR.getEmailErrorMessage()} </font> </br>
                    Password:  <input name="txtUserPassword" type="text" value="${param.txtUserPassword}"/></br> 
                    <font color="red"> ${UERROR.getPasswordErrorMessage()} </font> </br>
                </c:otherwise>
            </c:choose>
            Phone number: <input name="txtUserPhoneNumber" type="text" value="${param.txtUserPhoneNumber}"/></br>
            <font color="red">${UERROR.phoneErrorMessage}</font></br>
            Address:  <input name="txtUserAddress"  type="text" style="width: 400px" value="${param.txtUserAddress}"/>
            </br> <font color="red">${UERROR.addressErrorMessage}</font></br>
            Fullname: <input name="txtUserFullName" type="text" value="${param.txtUserFullName}"/>
            </br> <font color="red">${UERROR.fullNameErrorMessage}</font></br>
            <div class="g-recaptcha" data-sitekey="6Ld7KXcbAAAAAE0V5zWBBHUwr0wEASKGe5Y2NsEk"></div>
            <div id="error"></div>
            <script src="https://www.google.com/recaptcha/api.js" async defer></script>
            <script>
                window.onload = function () {
                    let isValid = false;
                    const form = document.getElementById("registerForm");
                    const error = document.getElementById("error");
                    form.addEventListener("submit", function (event) {
                        event.preventDefault();
                        const capchaReps = grecaptcha.getResponse();
                        if (capchaReps) {
                            form.submit()
                        } else {
                            error.innerHTML = "Please confirm captcha first";
                        }
                    })
                }
            </script>
            <input type="hidden" name="btAction" value="Create account">
            <input type="submit" name="btAction" value="Create account">
        </form>
    </body>
</html>
