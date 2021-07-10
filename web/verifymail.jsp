<%-- 
    Document   : verifymail
    Created on : Jul 4, 2021, 7:12:12 PM
    Author     : dangminhtien
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="common.Config"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Verify Your account</h1>
        <form action="${Config.CONFIRM_EMAIL_CONTROLLER}">
            <input type="text" name="txtInputCode" value="" />
            <a href="${Config.SEND_VERIFIED_CONTROLLER}">send verify code</a> <br/>
            <c:if test="${not empty UERROR}">
                <font color="red">${UERROR}</font> </br>
            </c:if>
            <input type="submit" name="btAction" value="Confirm email">
            <a href="${Config.SIGNOUT_CONTROLLER}">SignOut</a>
        </form>
    </body>
</html>
