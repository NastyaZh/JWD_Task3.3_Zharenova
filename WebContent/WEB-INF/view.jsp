<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Library</title>
</head>
<body>
	<table border="1">
        <tr>
            <th>ID</th>
            <th>Author</th>
            <th>Title</th>
            <th>Genre</th>
            <th>Price</th>
            <th>Publish Date</th>
            <th>Description</th>
        </tr>
        <c:forEach items="${requestScope.list}"  var="book">
            <tr>
                <td>${book.id}</td>
                <td>${book.author}</td>
                <td>${book.title}</td>
                <td>${book.genre}</td>
                <td>${book.price}</td>
                <td>${book.publishDate}</td>
                <td>${book.description}</td>
            </tr>
        </c:forEach>
    </table>
    <div style="text-align: center;">
    <%--For displaying Page numbers. 
    The when condition does not display a link for the current page--%>
    <form method="get" action="Controller">
    <table border="1">
        <tr>
            <c:forEach begin="1" end="3" var="i">
                <c:choose>
                    <c:when test="${requestScope.pageNumber eq i}">
                        <td>${i}</td>
                    </c:when>
                    <c:otherwise>
                    <td>
                    <a href ="${pageContext.request.contextPath}/Controller?pageNumber=${i}&parser=${requestScope.parser}">
                    ${i}</a></td>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </tr>
    </table>
    </form>
    </div>
    
</body>
</html>