<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head><title>게시글 목록</title></head>
<body>
<h2>게시글 목록</h2>
<table border="1">
    <tr>
        <th>번호</th>
        <th>제목</th>
        <th>작성일</th>
        <th>이미지</th>
    </tr>
    <c:forEach var="board" items="${list}">
        <tr>
            <td>${board.boardid}</td>
            <td><a href="get.do?boardid=${board.boardid}">${board.title}</a></td>
            <td>${board.regDate}</td>
            <td>
                <c:if test="${not empty board.imagePath}">
                    <img src="${board.imagePath}" width="100" />
                </c:if>
            </td>
        </tr>
    </c:forEach>
</table>
<a href="register.jsp">글쓰기</a>
</body>
</html>
