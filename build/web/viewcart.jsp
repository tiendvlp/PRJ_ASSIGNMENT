<%-- 
    Document   : viewcart
    Created on : Jun 20, 2021, 9:47:40 PM
    Author     : dangminhtien
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="servlet.sessionmodel.CartItem"%>
<%@page import="java.util.Map"%>
<%@page import="servlet.sessionmodel.Cart"%>
<%@page import="common.Config"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Online Shopping</title>
    </head>
    <body>
        <h1>Your cart:</h1>
        <c:set var="cart" value="${CART}"></c:set>
            <c:choose>
                <c:when test="${ not empty cart and not empty cart.getAll() and fn:length(cart.getAll()) > 0}">
                    <c:set var="items" value = "${cart.getAll()}"></c:set>
                        <form action="dispatchercontroller">
                            Email: ${User.email} <br>
                        <input name="txtUserEmail" type="text" value="${User.getEmail()}"/>
                        Phone number: <input name="txtUserPhoneNumber" type="text" value="${User.getPhoneNumber()}"/>  <font color="red">${UERROR.phoneEmpty}, ${UERROR.phoneInvalid}</font></br>
                        Address:  <input name="txtUserAddress"  type="text" style="width: 400px" value="${User.address}"/>  <font color="red">${UERROR.addressEmpty}</font></br>
                        Fullname: <input name="txtUserFullName" type="text" value="${User.fullName}"/> <font color="red">${UERROR.fullNameEmpty}</font></br>
                        <table border="1">
                            <thead>
                                <tr>
                                    <th>No.</th>
                                    <th>Name</th>
                                    <th>Quantity</th>
                                    <th>Action</th>
                                </tr>
                            </thead>
                            <c:forEach var="cartItem" items="${items}" varStatus="count">
                                <tr>
                                    <td>${count.count}</td>
                                    <td>${cartItem.value.getProductName()}</td>
                                    <td>${cartItem.value.getQuantity()}</td>
                                    <td>
                                        <input type="checkbox" name="chkItem" value="${cartItem.key}" />
                                    </td>
                                </tr>
                            </c:forEach>
                            <tr>
                                <td colspan="3">
                                    <a href="${Config.getShoppingOnlineUrl()}">Shopping More</a>
                                </td>
                                <td>
                                    <input type="submit" value="Remove Selected Items" name="btAction" />
                                </td>
                            </tr>
                            </tbody>
                        </table>
                        <input type="submit" value="Checkout" name="btAction" />
                    </form>    
                </c:when>
                <c:otherwise>
                    <h2>No cart</h2> 
                    <a href=${Config.getShoppingOnlineUrl()}>Shopping More</a>
                </c:otherwise>
            </c:choose>
    </body>
</html>
