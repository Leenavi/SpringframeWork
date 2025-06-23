<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ include file="../includes/header.jsp" %>

<h2>새 글 작성</h2>
<form action="register.do" method="post" enctype="multipart/form-data" class="board">
    <p>제목: <input type="text" name="title" required /></p>
    <p>내용: <textarea name="content" required></textarea></p>
    <p>이미지 업로드: <input type="file" name="uploadFile" /></p>
    <input type="submit" value="등록" />
</form>
<a href="list.do">목록으로</a>

<%@ include file="../includes/footer.jsp" %>