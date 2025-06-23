<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ include file="../includes/header.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>팔도마켓 | 메인</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Noto Sans 폰트 -->
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="/resources/dist/css/main.css">
    <style>
    .logo-img {
        height: 38px;
        vertical-align: middle;
        margin-right: 10px;
        border-radius: 7px;
        
        box-shadow: 0 2px 8px rgba(0,0,0,0.07);
    }
    #playButton {
        position: absolute;
        bottom: 10px;
        left: 50%;
        transform: translateX(-50%);
        padding: 10px 20px;
        font-size: 16px;
        background-color: #111;
        color: white;
        border: none;
        border-radius: 8px;
        cursor: pointer;
        opacity: 0.85;
    }
    </style>
</head>

<section class="py-5">
  <div class="container">
    <div class="card shadow-lg">
      <div class="card-body p-4">
        <table class="table table-hover align-middle mb-0">
          <thead class="table-info">
            <tr>
              <th>#번호</th>
              <th>제목</th>
              <th>작성자</th>
              <th>상태</th>
              <th>브랜드</th>
            </tr>
          </thead>
          <tbody>
          <c:forEach var="board" items="${list}">
            <tr>
              <td><c:out value="${board.board.boardid}" /></td>
              <td>
                <a class="move text-decoration-none fw-semibold text-dark" href="<c:out value='${board.board.boardid}'/>">
                  <c:out value="${board.board.title}" />
                </a>
              </td>
              <td><c:out value="${board.user.nickname}" /></td>
              <td><c:out value="${board.board.status}" /></td>
              <td><c:out value="${board.product.brand}" /></td>
            </tr>
          </c:forEach>
          </tbody>
        </table>
      </div>
    </div>

    <form id="actionForm" action="/board/list" method="get" style="display:none;">
      <input type="hidden" name="pageNum" value="${pageMaker.cri.pageNum}">
      <input type="hidden" name="amount" value="${pageMaker.cri.amount}">
      <input type="hidden" name="type" value="${pageMaker.cri.type}">
      <input type="hidden" name="keyword" value="${pageMaker.cri.keyword}">
    </form>
  </div>
</section>



<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
<script>
  $(document).ready(function(){
    let actionForm = $("#actionForm");
    $(".move").on("click", function(e){
      e.preventDefault();
      actionForm.append("<input type='hidden' name='boardid' value='"+$(this).attr("href")+"' >");
      actionForm.attr("action" , "/board/get");
      actionForm.submit();
    });
  });
</script>
</body>
</html>
