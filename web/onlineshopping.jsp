<%-- 
    Document   : onlineshopping
    Created on : Jun 20, 2021, 11:44:22 PM
    Author     : dangminhtien
--%>

<%@page import="data.dto.ProductDto"%>
<%@page import="java.util.List"%>
<%@page import="data.dto.UserDto"%>
<%@page import="java.sql.SQLException"%>
<%@page import="data.dao.ProductDao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="common.Config"%>
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
        <c:choose>
            <c:when test="${ not empty sessionScope.User}">
                <font color="red">Hello ${sessionScope.User.fullName} </font> </br>
                <a href="${Config.SIGNOUT_CONTROLLER}">SignOut</a>
                <c:url value="${Config.USER_INFO_PAGE}" var="viewUserInfoUrl">
                    <c:param name="btAction" value="User Info"></c:param>
                </c:url>
                <form action="${Config.SHOPPING_PAGE}">
                    <input type="submit" name="btAction" value="User Info"/>
                </form>
            </c:when>
            <c:otherwise>
                <a href="${Config.LOGIN_PAGE}">Login here</a> 
            </c:otherwise>
        </c:choose>

        <c:set var="selectedCategory" value="${param.cboCategory}"/>
        <c:set var="categories" value="${requestScope.SHOPPING_CATEGORY}"/>
        <c:set var="products" value="${requestScope.SHOPPING_PRODUCT}"/>
        <c:set var="error" value="${UERROR}"/>
        <c:if test="${ empty selectedCategory}">
            <c:set var="selectedCategory" value="${categories[0].title}"/>
        </c:if>

        <form action="${Config.SHOPPING_PAGE}">
            <select name="cboCategory" id="categoryList" onchange="this.form.submit()">
                <option>${selectedCategory}</option>
                <c:forEach items="${categories}" var="categoryItem" varStatus="status">
                    <c:if test="${categoryItem.title  ne selectedCategory}">
                        <option>${categoryItem.title}</option>
                    </c:if>
                </c:forEach>
            </select>
        </form>
        <table border="1" >
            <thead>
                <tr>
                    <th>Name</th>
                    <th>Price</th>
                    <th>Quantity</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${products}" var="productItem" varStatus="status">
                    <c:if test="${productItem.categoryTitle eq selectedCategory}">
                        <tr>
                    <form action="${Config.SHOPPING_PAGE}">
                        <td>
                            ${productItem.name}
                        </td>
                        <td>
                            ${productItem.price}
                        </td>
                        <td>
                            <input type="text" value="1" name="txtProductQuantity" style="width: 30px"/>
                            <br/>
                            <c:if test="${error.productId eq productItem.id}">
                                ${error.overQuantity}, ${error.incorectQuantity}
                            </c:if>
                        </td>
                        <td>
                            <input type="hidden" value="${selectedCategory}" name="cboCategory">
                            <input type="hidden" name="txtProductName" value="${productItem.name}" />
                            <input type="hidden"  name="txtProductPrice" value="${productItem.price}"/>
                            <input type="hidden"  name="txtProductId" value="${productItem.id}" />
                            <input type="submit" value="Add item to cart" name="btAction" />
                        </td>
                    </form>
                </tr>
            </c:if>
        </c:forEach>
    </tbody>
</table>
<form action="${Config.SHOPPING_PAGE}">
    <input type="submit" value="View your cart" name="btAction"/>
</form>
</body>
</html>
