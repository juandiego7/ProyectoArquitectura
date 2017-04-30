<%-- 
    Document   : user
    Created on : 29-abr-2017, 23:27:55
    Author     : Juan Diego
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${message}</title>
    </head>
    <body>
        <h1>${message}</h1>
        <table>
            <tr>
                <th>ID</th>
                <th>User Name</th>
                <th>Password</th>
                <th>Email</th>
            </tr>
            <c:forEach items="${users}" var="user">
                <tr>
                    <td><c:out value="${user.numberId}"/></td>
                    <td><c:out value="${user.username}"/></td>
                    <td><c:out value="${user.password}"/></td>
                    <td><c:out value="${user.email}"/></td>
                </tr>
            </c:forEach>
        </table>
        <a href="user.htm">Click Here</a> to get all Users Details.
    </body>
</html>
