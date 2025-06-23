<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ include file="../includes/header.jsp" %>

<h2>게시글 상세</h2>
<p><b>번호:</b> ${board.boardid}</p>
<p><b>제목:</b> ${board.title}</p>
<p><b>내용:</b> ${board.content}</p>
<p><b>작성일:</b> ${board.regDate}</p>
<p><b>이미지:</b><br>
  <c:if test="${not empty board.imagePath}">
    <img src="${board.imagePath}" width="300" />
  </c:if>
</p>

<form action="modify.do" method="get">
  <input type="hidden" name="boardid" value="${board.boardid}" />
  <input type="submit" value="수정하기" />
</form>
<form action="remove.do" method="post">
  <input type="hidden" name="boardid" value="${board.boardid}" />
  <input type="submit" value="삭제하기" />
</form>
<a href="/board/list">목록으로</a>

<%@ include file="../includes/footer.jsp" %>