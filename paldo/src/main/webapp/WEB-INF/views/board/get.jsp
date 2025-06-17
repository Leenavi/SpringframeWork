<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>    
<%@ include file="../includes/header.jsp" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/dist/css/product-detail.css">
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@700&display=swap" rel="stylesheet">
<!-- 브랜드 로고 -->
<head>
    <meta charset="UTF-8">
    <title>팔도마켓 | 메인</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="/resources/dist/css/main.css">
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

<div class="container py-4">
  <div class="row mb-4 align-items-left">
    <div class="col-lg-12 d-flex align-items-center justify-content-center">
      <c:choose>
        <c:when test="${dto.product.brand eq 'Apple'}">
          <img src="/resources/images/apple_logo_icon_168588.webp" alt="Apple" style="height: 76px;">
        </c:when>
        <c:when test="${dto.product.brand eq 'Samsung'}">
          <img src="/resources/images/Samsung_Logo.svg.webp" alt="Samsung" style="height: 62px;">
        </c:when>
        <c:when test="${dto.product.brand eq 'Sony'}">
          <img src="/resources/images/sonylogo.png" alt="Sony" style="height: 90px;">
        </c:when>
        <c:when test="${dto.product.brand eq 'LG'}">
          <img src="/resources/images/LG_logo_(2014).svg" alt="LG" style="height: 62px;">
        </c:when>
        <c:otherwise>
          <span class="text-muted">브랜드 없음</span>
        </c:otherwise>
      </c:choose>
    </div>
  </div>

  <!-- 상세 페이지 본문 -->
  <div class="product-detail-container">
    <div class="product-images">
      <div class="main-image">
       <img src="<c:url value='${dto.imgPaths[0].imgPath}'/>" alt="대표 이미지" />
      </div>
      <div class="thumb-list">
        <c:forEach var="img" items="${board.imgPaths}">
          <div class="thumb"><img src="<c:url value='/resources/${img.imgPath}'/>" alt="썸네일" /></div>
        </c:forEach>
      </div>
    </div>

    <div class="product-info">
      <nav class="breadcrumb">
        <a href="<c:url value='/'/>">HOME</a> &gt;
        <a href="<c:url value='/board/list'/>">전체 리스트</a> &gt;
        <span><c:out value="${dto.board.title}"/></span>
      </nav>

      <div class="product-title">
        <c:out value="${dto.board.title}"/>
      </div>
      <div class="model">
        <c:out value="${dto.product.modelname}"/>
      </div>
     

      <div class="brand">
        브랜드: ${dto.product.brand}
      </div>
      
      <div>
        판매자: ${dto.user.nickname}
      </div>
        
        <span class="reg-date">
          등록일: <fmt:formatDate value="${dto.board.regDate}" pattern="yyyy-MM-dd"/>
        </span>
		
		
      <div class="mb-3">
        <span class="sale-price">
         ₩ <fmt:formatNumber value="${dto.product.price}" pattern="#,/###"/>원
        </span>
      </div>

      <div>
        <span class="option active">상태 : ${dto.product.condition}급</span>

      </div>
 		<div class="status-message">
        판매상태: ${dto.board.status}
      </div>


      <form class="purchase-form" onsubmit="return false;">
        <div class="purchase-row">
          <label for="quantity">구매 수량</label>
          <input type="text" id="quantity" class="qty" value="1" readonly>
          <span class="sum-price">
            총 금액: <fmt:formatNumber value="${dto.product.price}" pattern="#,/###"/>원
          </span>
        </div>
        <button type="button" class="btn-blue btn-purchase" disabled>즉시 구매하기</button>
      </form>

      <div class="product-description">
        <p><c:out value="${dto.product.description}"/></p>
      </div>

      <div class="d-flex justify-content-end gap-2 mt-4">
        <button data-oper='modify' class="btn btn-warning">수정</button>
        <button data-oper='list' class="btn btn-outline-secondary">목록</button>
      </div>

      <form id="operForm" action="/board/modify" method="get">
        <input type="hidden" name="boardid" value='<c:out value="${dto.board.boardid}"/>'>
        <input type="hidden" name="pageNum" value='<c:out value="${cri.pageNum}"/>'>
        <input type="hidden" name="amount" value='<c:out value="${cri.amount}"/>'>
        <input type="hidden" name="type" value='<c:out value="${cri.type}"/>'>
        <input type="hidden" name="keyword" value='<c:out value="${cri.keyword}"/>'>
      </form>
    </div>
  </div>
</div>

<!-- 댓글 영역 -->
<div class="container mt-5">
  <div class="card">
    <div class="card-header bg-success text-white d-flex justify-content-between align-items-center">
      <span><i class="fa fa-comments"></i> 댓글</span>
      <button id="addReplyBtn" class="btn btn-light btn-sm">댓글 등록</button>
    </div>
    <div class="card-body">
      <ul class="list-group chat"></ul>
    </div>
    <div class="card-footer"></div>
  </div>
</div>

<!-- 댓글 모달 -->
<div class="modal fade" id="myModal" tabindex="-1" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header bg-primary text-white">
        <h5 class="modal-title">댓글 등록/수정</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <div class="mb-3">
          <label>댓글 내용</label>
          <input class="form-control" name="reply" />
        </div>
        <div class="mb-3">
          <label>작성자</label>
          <input class="form-control" name="replyer" />
        </div>
        <div class="mb-3">
          <label>작성일</label>
          <input class="form-control" name="replyDate" readonly />
        </div>
      </div>
      <div class="modal-footer">
        <button id="modalRegisterBtn" type="button" class="btn btn-success">등록</button>
        <button id="modalModBtn" type="button" class="btn btn-warning">수정</button>
        <button id="modalRemoveBtn" type="button" class="btn btn-danger">삭제</button>
        <button id="modalCloseBtn" type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
      </div>
    </div>
  </div>
</div>

<!-- JS -->
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
<script type="text/javascript" src="/resources/js/reply.js"></script>
<script>
  $(document).ready(function() {
    let operForm = $("#operForm");

    $("button[data-oper='modify']").on("click", function() {
      operForm.attr("action", "/board/modify").submit();
    });

    $("button[data-oper='list']").on("click", function() {
      operForm.find("input[name='pno']").remove();
      operForm.attr("action", "/board/list").submit();
    });
  });
</script>

<%@ include file="../includes/footer.jsp" %>