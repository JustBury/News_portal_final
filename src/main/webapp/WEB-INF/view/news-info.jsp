<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>

    <title>News Portal</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/news-info.css"/>" type="text/css">
    <link rel="stylesheet" href="<c:url value="/resources/css/button_link.css"/>" type="text/css">
    <link rel="stylesheet" href="<c:url value="/resources/css/head_news.css"/>" type="text/css">
    <style>

    </style>
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
<div class="form-area">
    <form:form action="saveNews" modelAttribute="news" method="post">
        <form:hidden path="id"/>
        <label class="label-form">Title</label>
        <br>
        <form:input cssClass="form-input" path="title"/>
        <br>
        <form:errors cssStyle="color: red" path="title"/>
        <br>
        <label class="label-form">Brief</label>
        <br>
        <form:input cssClass="form-input" path="brief"/>
        <br>
        <form:errors cssStyle="color: red" path="brief"/>
        <br>
        <label class="label-form">Content</label>
        <br>
        <form:textarea cssClass="form-textarea" path="content"/>
        <br>
        <form:errors cssStyle="color: red" path="content"/>
        <br>
        <input type="submit" class="submitOK" value="OK">
        <c:url var="back" value="/"/>
        <a class="bot1" href="${back}">Back to main page</a>
    </form:form>
</div>
</body>
</html>