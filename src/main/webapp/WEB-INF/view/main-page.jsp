<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>

    <title>News Portal</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/main-page.css"/>" type="text/css">
    <link rel="stylesheet" href="<c:url value="/resources/css/button_link.css"/>" type="text/css">
    <link rel="stylesheet" href="<c:url value="/resources/css/head_news.css"/>" type="text/css">
    <style>

    </style>
</head>
<body>
<body>
<div class="header" id="header" style="overflow: hidden;">
    <div style="width: 100%;">
        <div style="float: left; width: 33%; height:  100px;">
            <h1 class="news">News</h1>
        </div>
        <div style="float: left; width: 33%; height: 100%;">  <!-- Add a logout button -->

            <c:url var="addNews" value="addNewNews"/>
            <a class="bot1" style="width: 100px" href="${addNews}">Add new News</a>

        </div>
        <div style="float: left; width: 33%; height:  100%;">
            <sec:authorize access="!isAuthenticated()">
                <form:form action="showMyLoginPage" method="GET">
                    <input type="submit" class="logout" value="Sign in"/>
                </form:form>
                <form:form action="registration" method="POST">
                    <input type="submit" class="logout" value="Registration"/>
                </form:form>
            </sec:authorize>
            <sec:authorize access="isAuthenticated()">
                <form:form action="${pageContext.request.contextPath}/logout"
                           method="POST">
                    <input type="submit" class="logout" value="Logout"/>
                </form:form>
            </sec:authorize>

        </div>

    </div>
</div>
<br>
<table>

    <c:forEach var="news" items="${newses}">

        <%--        <c:url var="newsContent" value="/getNews/${news.id}">--%>
        <%--           --%>
        <%--        </c:url>--%>

        <tr>
            <td>
                <p class="title">${news.title}</p>
                <p class="brief">${news.brief}</p>
                <sec:authorize access="isAuthenticated()">
                    <a class="bot1" href="getNews/${news.id}">View the news</a>
                </sec:authorize>
            </td>
        </tr>
    </c:forEach>

</table>


</body>
</html>