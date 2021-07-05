<%-- 
    Document   : userinfo
    Created on : Jul 3, 2021, 7:49:56 PM
    Author     : dangminhtien
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="common.Config"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="dispatchercontroller">
            <c:choose>
                <c:when test="${User.signInMethod eq 'GOOGLE_SIGNIN'}">
                    Email: ${User.email} <br>
                    <input name="txtUserEmail" type="hidden" value="${User.email}"/> 
                </c:when>
                <c:otherwise>
                    Password:  <input name="txtUserPassword" type="text" value="${User.password}"/>  <font color="red">${UERROR.passwordEmpty}</font></br>
                    Email: <input name="txtUserEmail" type="text" value="${User.email}"/> <font color="red"> ${UERROR.emailEmpty}, ${UERROR.emailDuplicate} </font> </br>
                </c:otherwise>
            </c:choose>
            Phone number: <input name="txtUserPhoneNumber" type="text" value="${User.getPhoneNumber()}"/>  <font color="red">${UERROR.phoneEmpty}</font></br>
            Address:  <input name="txtUserAddress"  type="text" style="width: 400px" value="${User.address}"/>  <font color="red">${UERROR.addressEmpty}</font></br>
            Fullname: <input name="txtUserFullName" type="text" value="${User.fullName}"/> <font color="red">${UERROR.fullNameEmpty}</font></br>
            <c:if test="${User.role eq 'ADMIN'}">
                Current role: <select name="ddlist">
                    <option value="ADMIN">Admin</option>
                    <option value="USER">User</option>
                </select>    
                </br>     
            </c:if>
            <input type="submit" name="btAction" value="Update">     
            <input type="hidden" name="txtRawUserEmail" value="${User.email}">
            <input type="submit" name="btAction" value="SignOut">
            <input type="hidden" name="txtSignInMethod" value="${User.signInMethod}"/>
            <a href="${Config.getFullSearchUrl("")}">Back to search</a>
        </form>
    </body>
</html>
