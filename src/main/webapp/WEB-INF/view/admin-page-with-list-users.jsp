<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>

    <link rel="stylesheet" href="<c:url value="/resources/css/button_link.css"/>" type="text/css">
    <link rel="stylesheet" href="<c:url value="/resources/css/head_news.css"/>" type="text/css">
    <link rel="stylesheet" href="<c:url value="/resources/css/admin-page.css"/>" type="text/css">
</head>
<body>
<div class="header" id="header" style="overflow: hidden;">
    <div style="width: 100%;">
        <div style="float: left; width: 33%; height:  100px;">
            <h1 class="news">News</h1>
        </div>
        <div style="float: left; width: 33%; height: 100%;">  <!-- Add a logout button -->

            <c:url var="back" value="/"/>
            <a class="bot1" href="${back}">Back to main page</a>

        </div>
        <div style="float: left; width: 33%; height:  100%;">

        </div>

    </div>
</div>
<div>
    <table>
        <tr>
            <th>
                <h3>ID User</h3>
            </th>
            <th>
                <h3>User name (Login)</h3>
                <%--        ${users.getUsername()}--%>
            </th>
            <th>
                <h3>First name</h3>
                <%--        ${users.getFirstName()}--%>
            </th>
            <th>
                <h3>Last name</h3>
                <%--        ${users.getLastName()}--%>
            </th>
            <th>
                <h3>Email</h3>
                <%--        ${users.getEmail()}--%>
            </th>
            <th>
                <h3>Date registration</h3>
                <%--        ${users.getDateRegistration()}--%>
            </th>
            <th>
                <h3>Role user</h3>
                <%--        ${users.getRole()}--%>
            </th>
            <th>
                <h3>Functional</h3>
            </th>
        </tr>

        <c:forEach var="users" items="${users}">
            <tr>
                <td>
                        ${users.getId()}
                </td>
                <td>
                        ${users.getUsername()}
                </td>
                <td>
                        ${users.getFirstName()}
                </td>
                <td>
                        ${users.getLastName()}
                </td>
                <td>
                        ${users.getEmail()}
                </td>
                <td>
                        ${users.getDateRegistration()}
                </td>
                <td>
                        ${users.getRole()}
                </td>
                <td>
                    <c:url var="deleteUser" value="/deleteUser">
                        <c:param name="userId" value="${users.getId()}"/>
                    </c:url>
                    <c:url var="getUser" value="/getUser">
                        <c:param name="userId" value="${users.getId()}"/>
                    </c:url>
                    <sec:authorize access="hasRole('ADMIN')">
                        <a class="bot1" href="${deleteUser}"
                           onclick="if (!(confirm('Are you sure you want to delete this news?'))) return false">Delete</a>
                    </sec:authorize>
                    <sec:authorize access="hasRole('ADMIN')">
                        <a class="bot1" href="${getUser}">View the user profile</a>
                    </sec:authorize>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
