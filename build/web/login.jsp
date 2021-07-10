<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<%@page import="common.Config"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
    <head>
        <title>Login</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <form method="POST" action="${Config.LOGIN_CONTROLLER}">
            Username
            <input type="text" name="txtUserId" value=""/></br>
            Password
            <input type="text" name="txtPassword" value=""/></br>

            <input type="submit" value ="Login" name="btAction"/>           
            <input type="reset" value ="Reset"/> 
            </br>
            <c:if test="${not empty UERROR}">
                <font color="red">${UERROR.incorrectMessage}</font><br/>
            </c:if>
            </br>
            or
            <a href="https://accounts.google.com/o/oauth2/auth?scope=email&redirect_uri=http://localhost:8081/DevlogsPrj/logingoogle&response_type=code
               &client_id=627419276955-6305n3qcpeo1klqjgjk3b7dk1158es9j.apps.googleusercontent.com&approval_prompt=force">Login With Google</a>  </br>
            <font color="grey">or don't have account ? Register <a href="Register">here !!</a></font> <br/>
        </form>
        <a href="ShoppingOnline"> <button> Shopping now !!</button> </a> </br>

    </body>
</html>
