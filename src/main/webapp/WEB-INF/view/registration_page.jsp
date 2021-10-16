<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Title</title>

    <link rel="stylesheet" href="<c:url value="/resources/css/button_link.css"/>" type="text/css">
    <link rel="stylesheet" href="<c:url value="/resources/css/head_news.css"/>" type="text/css">
    <link rel="stylesheet" href="<c:url value="/resources/css/registration_page.css"/>" type="text/css">
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
<br>

<br>
<div class="form-area">
    <form:form action="saveUser" modelAttribute="user" method="post">
        <form:hidden path="id"/>
        <label class="label-form">User Name</label>
        <br>
        <form:input cssClass="form-input"  path="username"/>
        <br>
        <form:errors cssStyle="color: red" path="username"/>
        <br>
        <label class="label-form">First Name</label>
        <br>
        <form:input cssClass="form-input"  path="firstName"/>
        <br>
        <form:errors cssStyle="color: red" path="firstName"/>
        <br>
        <label class="label-form">Last Name</label>
        <br>
        <form:input cssClass="form-input"  path="lastName"/>
        <br>
        <form:errors cssStyle="color: red" path="lastName"/>
        <br>
        <label class="label-form">Email</label>
        <br>
        <form:input cssClass="form-input"  path="email"/>
        <br>
        <form:errors cssStyle="color: red" path="email"/>
        <br>
        <label class="label-form">Password</label>
        <br>
        <form:password cssClass="form-input"  path="password"/>
        <br>
        <form:errors cssStyle="color: red" path="password"/>
        <br>
        <input type="submit" class="submitOK" value="OK">

    </form:form>
</div>
</body>
</html>
