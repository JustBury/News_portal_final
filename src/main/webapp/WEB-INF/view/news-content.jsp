<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>

    <title>News Portal</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/news-content.css"/>" type="text/css">
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

            <c:url var="addNews" value="addNewNews"/>
            <a class="bot1" style="width: 100px" href="${addNews}">Add new News</a>
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
<div class="news_content">
    <p class="title">${news.title}</p>
    <p>${news.publishDate}</p>
    <p class="brief">${news.brief}</p>
    <p>${news.content}</p>
</div>
<div>
    <c:url var="deleteNews" value="/deleteNews">
        <c:param name="newsId" value="${news.id}"/>
    </c:url>
    <c:url var="updateNews" value="/updateNews">
        <c:param name="newsId" value="${news.id}"/>
        <c:param name="idNews" value="${news.id}"/>
    </c:url>

    <sec:authorize access="hasRole('ADMIN')">
        <a class="bot1" href="${deleteNews}"
           onclick="if (!(confirm('Are you sure you want to delete this news?'))) return false">Delete</a>
        <a class="bot1" href="${updateNews}">Update</a>
    </sec:authorize>




    <c:url var="addNewComment" value="/saveComment">
        <c:param name="idNews" value="${news.id}"/>
    </c:url>

    <div class="form-area">
        <form:form action="${addNewComment}" modelAttribute="comment" method="post">
            <form:hidden path="id"/>

            <br>
            <label class="label-form">Leave a comment </label>
            <form:errors cssStyle="color: red" path="content"/>
            <br>
            <br>
            <form:textarea cssClass="form-textarea" path="content"/>
            <br>


            <input type="submit" class="submitOK" value="Save comment">
        </form:form>
    </div>

    <table>
        <c:forEach var="tempComment" items="${news.getComments()}">
            <tr>
                <td><br>${tempComment.content}
                    <br>
                        ${tempComment.publishDate}
                </td>
                <td>
                    <c:url var="deleteComment" value="/deleteComment">
                        <c:param name="commentId" value="${tempComment.id}"/>
                        <c:param name="newsId" value="${news.id}"/>
                    </c:url>
                    <sec:authorize access="hasRole('ADMIN')">
                    <a class="bot1" href="${deleteComment}"
                       onclick="if (!(confirm('Are you sure you want to delete this news?'))) return false">Delete</a>
                    </sec:authorize>
                </td>
            </tr>
        </c:forEach>
    </table>

</div>
</body>
</html>