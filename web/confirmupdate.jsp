<%-- 
    Document   : ConfirmUpdateUser
    Created on : Jul 9, 2021, 1:46:08 PM
    Author     : dangminhtien
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="common.Config"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Confirm</h1>
        <a href="${Config.UPDATE_CONTROLLER}?isConfirm=Yes"><button>Confirm</button></a>
        <a href="${Config.USER_INFO_PAGE}"><button>Cancel</button></a>
    </body>
</html>
