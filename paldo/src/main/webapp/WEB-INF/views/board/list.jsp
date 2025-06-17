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
  <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@700&display=swap" rel="stylesheet">
  <link rel="stylesheet" href="/resources/dist/css/main.css">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet">
  <style>
 
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

<body>
<div class="container py-4">
  <div class="row mb-2 align-items-left">
    <div class="col-lg-12 d-flex align-items-center justify-content-center">
      <c:choose>
        <c:when test="${param.keyword eq 'Apple'}">
          <img src="/resources/images/apple_logo_icon_168588.webp" alt="Apple" style="height: 76px;">
        </c:when>
        <c:when test="${param.keyword eq 'Samsung'}">
          <img src="/resources/images/Samsung_Logo.svg.webp" alt="Samsung" style="height: 62px;">
        </c:when>
        <c:when test="${param.keyword eq 'Sony'}">
          <img src="/resources/images/sonylogo.png" alt="Sony" style="height: 90px;">
        </c:when>
        <c:when test="${param.keyword eq 'LG'}">
          <img src="/resources/images/LG_logo_(2014).svg" alt="LG" style="height: 62px;">
        </c:when>
        <c:otherwise>
        </c:otherwise>
      </c:choose>
    </div>
  </div>
  </div>
<section class="py-0">
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
                <td><c:out value="${board.boardid}" /></td>
                <td>
                  <a href="#" class="move text-decoration-none fw-semibold text-dark" data-id="${board.boardid}">
                    <c:out value="${board.title}" />
                  </a>
                </td>
                <td><c:out value="${board.nickname}" /></td>
                <td><c:out value="${board.status}" /></td>
                <td><c:out value="${board.brand}" /></td>
              </tr>
            </c:forEach>
          </tbody>
        </table>
      </div>
    </div>

    <!-- 페이징 네비게이션 -->
    
 <div class="d-flex justify-content-between align-items-center flex-wrap my-4">

  <!-- ◀ 페이징 영역 -->
  <div class="pagination-wrapper d-flex flex-wrap align-items-center gap-2">
    <c:if test="${pageMaker.prev}">
      <a href="javascript:submitPage(${pageMaker.startPage - 1})" class="btn btn-secondary">◀ 이전</a>
    </c:if>

    <c:forEach var="pageNum" begin="${pageMaker.startPage}" end="${pageMaker.endPage}">
      <c:choose>
        <c:when test="${pageNum == pageMaker.cri.pageNum}">
          <span class="current-page btn btn-primary">${pageNum}</span>
        </c:when>
        <c:otherwise>
          <a href="javascript:submitPage(${pageNum})" class="btn btn-light">${pageNum}</a>
        </c:otherwise>
      </c:choose>
    </c:forEach>

    <c:if test="${pageMaker.next}">
      <a href="javascript:submitPage(${pageMaker.endPage + 1})" class="btn btn-secondary">다음 ▶</a>
    </c:if>
  </div>

  <!-- 🔍 검색창 -->
  <form class="d-flex align-items-center shadow-sm rounded-pill px-3"
        action="/board/list" method="get"
        style="background-color: white; height: 48px;">
    
    <i class="bi bi-search text-primary me-2"></i>
    
    <input type="text" class="form-control border-0" name="keyword"
           placeholder="상품을 검색해보세요"
           value="${pageMaker.cri.keyword}"
           style="box-shadow: none; min-width: 150px;">
    
    <button type="submit"
            class="btn btn-primary text-white ms-2 rounded-pill px-3 py-2"
            style="white-space: nowrap;">
      검색
    </button>
    </form>
	</div>
    <!-- 검색 및 페이징 유지용 폼 -->
    <form id="actionForm" action="/board/list" method="get" style="display:none;">
	
      <input type="hidden" name="pageNum" value="${pageMaker.cri.pageNum}">
      <input type="hidden" name="amount" value="${pageMaker.cri.amount}">
      <input type="hidden" name="type" value="${pageMaker.cri.type}">
      <input type="hidden" name="keyword" value="${pageMaker.cri.keyword}">
    </form>
    
<!-- The Modal -->
<div class="modal" id="myModal">
  <div class="modal-dialog">
    <div class="modal-content">

      <!-- Modal Header -->
      <div class="modal-header">
        <h4 class="modal-title">알 림</h4>
        <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
      </div>

      <!-- Modal body -->
      <div class="modal-body">
        ${result}
      </div>

      <!-- Modal footer -->
      <div class="modal-footer">
        <button type="button" class="btn btn-light" data-bs-dismiss="modal">취소</button>
         <button type="button" class="btn btn-info" data-bs-dismiss="modal">확인</button>
      </div>

    </div>
  </div>
</div> <!-- end The Modal -->
  </div>
</section>

<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

<script>
    
     function submitPage(pageNum) {
       document.querySelector('[name="pageNum"]').value = pageNum;
       document.getElementById("actionForm").submit();
     }
   
     $(document).ready(function() {
       let actionForm = $("#actionForm");
   
       $(".move").on("click", function(e) {
         e.preventDefault();
   
         // 기존 boardid 제거 (중복 방지)
         actionForm.find("input[name='boardid']").remove();
   
         // 새로운 boardid 추가
         const boardid = $(this).data("id");
         actionForm.append("<input type='hidden' name='boardid' value='" + boardid + "'>");
   
         actionForm.attr("action", "/board/get");
         actionForm.submit();
       });
    
      // jsp에서 서버가 전달한 result 값 저장 
      var result = '<c:out value="${result}"/>';
       
       checkModal(result); 
       
       history.replaceState({}, null, null); // 히스토리 상태 초기화(새로고침 시 메시지 중복 방지)
       
      function checkModal(result) {
          if (result === '' || history.state) return;

          //숫자면 등록 메세지 출력
          if (!isNaN(parseInt(result))) {
            $(".modal-body").html("게시글 " + result + "번이 등록되었습니다.");
          } else if (result === '수정') {
            $(".modal-body").html("게시글이 성공적으로 수정되었습니다.");
          } else if (result === '삭제') {
            $(".modal-body").html("게시글이 성공적으로 삭제되었습니다.");
          }
          
          $("#myModal").modal("show"); //모달창 띄우기  
       } //end checkModal
       
       //글쓰기 버튼 클릭 시 등록 페이지로 이동 
       $("#regBtn").on("click", function(){ 
          self.location = "/board/register";
       });
  });
</script>

</body>
</html>
