<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ include file="../includes/header.jsp" %>

<h2>게시글 수정</h2>
<form action="/board/modify" method="post" enctype="multipart/form-data" class="board">
  <input type="hidden" name="boardid" value="${board.boardid}" />
  <p>제목: <input type="text" name="title" value="${board.title}" required /></p>
  <p>내용: <textarea name="content" required>${board.content}</textarea></p>
  <p>현재 이미지:<br>
    <c:if test="${not empty board.imagePath}">
      <img src="${board.imagePath}" width="200" /><br>
    </c:if>
  </p>
  <p>이미지 변경: <input type="file" name="uploadFile" /></p>
  <input type="submit" value="수정" />
</form>
<a href="/board/list">목록으로</a>

<%@ include file="../includes/footer.jsp" %>