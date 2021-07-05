<%-- 
    Document   : onlineshopping
    Created on : Jun 20, 2021, 11:44:22 PM
    Author     : dangminhtien
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="data.dto.ProductDto"%>
<%@page import="java.util.List"%>
<%@page import="data.dto.UserDto"%>
<%@page import="java.sql.SQLException"%>
<%@page import="data.dao.ProductDao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
    <head>
        <title>Online Shopping</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <h1>Book Store</h1>
        <form action="dispatchercontroller">
            Select your item <select name="cboBook"">
                <c:set var="products" value="${requestScope.SHOPPING_PRODUCT}"/>
                    <c:forEach items="${products}" var="productItem" varStatus="status">
                        <option value="${productItem.id}/${productItem.name}">${productItem.name}</option>
                    </c:forEach>
            </select>
                <input type="submit" value="Add item to cart" name="btAction"/>
            <input type="submit" value="View your cart" name="btAction"/>
        </form>
    </body>
</html>
