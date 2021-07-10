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
        <c:choose>
            <c:when test="${ not empty SelectedUser.getEditedValue()}">
                <c:set var="User" value="${SelectedUser.getEditedValue()}"></c:set>
            </c:when>
            <c:otherwise>
                <c:set var="User" value="${SelectedUser.getOriginalValue()}"></c:set>
            </c:otherwise>
        </c:choose>
        <form action="${Config.USER_INFO_PAGE}">
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
            <c:if test="${sessionScope.User.role eq 'ADMIN'}">
                Current role: <select name="ddlist">
                    <option value="${User.role}">${User.role}</option>
                    <c:choose>
                        <c:when test="${User.role eq 'ADMIN'}">
                            <option value="USER">USER</option>
                        </c:when>
                        <c:otherwise>
                            <option value="ADMIN">ADMIN</option>
                        </c:otherwise>
                    </c:choose>
                </select>    
                </br>     
            </c:if>
            <input type="submit" name="btAction" value="Update">     
            <input type="hidden" name="txtRawUserEmail" value="${User.email}">
            <input type="hidden" name="txtSignInMethod" value="${User.signInMethod}"/>
        </form>
        <c:choose>
            <c:when test="${sessionScope.User.getRole() eq 'ADMIN'}">
                <a href="Search?btAction=Search&txtSearch=${LAST_SEARCH_VALUE}"><button>back</button></a>
            </c:when>
            <c:otherwise>
                <a href="processrequestservlet"><button>back</button></a>
            </c:otherwise>
        </c:choose>
        <a href="${Config.USER_INFO_PAGE}">Reset</a>
    </body>
</html>
