<%-- 
    Document   : search.jsp
    Created on : Jun 14, 2021, 12:25:35 AM
    Author     : dangminhtien
--%>

<%@page import="common.Config"%>
<%@page import="common.Config.Action.*"%>
<%@page import="data.dto.UserDto"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search Page</title>
    </head>
    <body>
        <c:set var="currentUser" value="${User}"></c:set>
        <font color="red"> Welcome ${currentUser.getFullName()} </font> 
        <a href="${Config.getSignOutUrl()}">SignOut</a>

        <h1>Search Page JSP</h1> 
        <c:url value="dispatchercontroller" var="viewUserInfoUrl">
            <c:param name="btAction" value="viewuserinfo"></c:param>
        </c:url>
        <a href="${viewUserInfoUrl}">UserInfo</a>
        <c:set var="searchValue" value="${param.txtSearch}"/>

        <form method="GET" action="dispatchercontroller">
            Search 
            <input type="text" name="txtSearch" value="${searchValue}"><br/>
            <input type="submit" value ="Search" name="btAction"/>
        </form> <br/>

        <c:if test="${not empty searchValue}">
            <c:set var="results" value="${requestScope.SEARCH_RESULT}"></c:set>
            <c:if test="${null != results}">
                <table border="1" >
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>Email</th>
                            <th>Password</th>
                            <th>Fullname</th>
                            <th style="width: 300px">Address</th>
                            <th>Phone</th>
                            <th>Role</th>
                            <th>Signin Method</th>
                            <th>Delete</th>
                            <th>Update</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${results}" var="rowUser" varStatus="status">
                        <form action="dispatchercontroller">
                            <tr>
                                <td>
                                    ${status.count}
                                </td>
                                <td>
                                    <input type="text" name="txtUserEmail" value="${rowUser.getEmail()}" />
                                    <c:if test="${UERROR_INDEX eq rowUser.email}">
                                        <br/>  <font color="red"> ${UERROR.emailEmpty}, ${UERROR.emailDuplicate} </font 
                                    </c:if>
                                </td>
                                <td>
                                    <c:choose>
                                        <c:when test="${rowUser.signInMethod eq 'GOOGLE_SIGNIN'}">
                                            Google signin
                                        </c:when>
                                        <c:otherwise>
                                            <input type="text" name="txtUserPassword" value="${rowUser.getPassword()}" />
                                        </c:otherwise>
                                    </c:choose>
                                    <c:if test="${UERROR_INDEX eq rowUser.email}">
                                        <br/> <font color="red"> ${UERROR.passwordEmpty}</font 
                                    </c:if>
                                </td>
                                <td>
                                    <input type="text" name="txtUserFullName" value="${rowUser.getFullName()}" />
                                    <c:if test="${UERROR_INDEX eq rowUser.email}">
                                        <br/> <font color="red"> ${UERROR.fullNameEmpty} </font 
                                    </c:if>
                                </td>
                                <td>
                                    <input  style="width: 290px" type="text" name="txtUserAddress" value="${rowUser.getAddress()}"/>
                                    <c:if test="${UERROR_INDEX eq rowUser.email}">
                                        <br/> <font color="red"> ${UERROR.addressEmpty} </font 
                                    </c:if>
                                </td>
                                <td>
                                    <input type="text" name="txtUserPhoneNumber" value="${rowUser.getPhoneNumber()}" />
                                    <c:if test="${UERROR_INDEX eq rowUser.email}">
                                        <br/><font color="red"> ${UERROR.phoneEmpty} </font 
                                    </c:if>
                                </td>   
                                <td>
                                    <select name="ddlist">
                                        <c:choose>
                                            <c:when test="${rowUser.getRole() eq 'ADMIN'}">
                                                <option value="Admin">Admin</option>
                                                <option value="User">User</option>
                                            </c:when>
                                            <c:otherwise>
                                                <option value="User">User</option>
                                                <option value="Admin">Admin</option>
                                            </c:otherwise>
                                        </c:choose>
                                    </select>
                                </td>
                                <td>
                                    ${rowUser.signInMethod}
                                </td>
                                <%-- khong duoc xoa chinh minh --%>
                                <td>
                                    <c:choose>
                                        <c:when test="${currentUser.email eq rowUser.email}">
                                            Can not delete yourself
                                        </c:when>
                                        <c:otherwise>
                                            <a href="${Config.getFullDeleteUrl(rowUser.getEmail(), searchValue)}">Delete</a></td>
                                        </c:otherwise>
                                    </c:choose>
                                <td>
                                    <input type="submit" value="Update" name="btAction" />
                                    <input type="hidden" value="${rowUser.signInMethod}" name="txtSignInMethod"/>
                                    <input type="hidden" value="${searchValue}" name="txtLastSearchValue"/>
                                    <input type="hidden" value="${rowUser.getEmail()}" name="txtRawUserEmail"/>
                                </td>
                            </tr>
                        </form>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
    </c:if>
</html>
