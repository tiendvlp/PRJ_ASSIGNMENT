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
        <form action="dispatchercontroller">
            <input type="text" name="txtInputCode" value="" />
            <input type="submit" name="btAction" value="send verify code"/> <br/>
            <input type="submit" name="btAction" value="confirm email"/>
                    <a href="${Config.getSignOutUrl()}">SignOut</a>
        </form>
    </body>
</html>
