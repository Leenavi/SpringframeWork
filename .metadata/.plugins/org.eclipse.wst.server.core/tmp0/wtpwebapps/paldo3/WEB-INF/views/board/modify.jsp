<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>    
<%@ include file="../includes/header.jsp" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/dist/css/product-detail.css">
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@700&display=swap" rel="stylesheet">

<head>
    <meta charset="UTF-8">
    <title>팔도마켓 | 상품 수정</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
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

<div class="container py-4">
  <div class="row mb-4 align-items-left">
    <div class="col-lg-12 d-flex align-items-center justify-content-center">
      <c:choose>
        <c:when test="${board.brand eq '애플'}">
          <img src="/resources/images/apple_logo_icon_168588.webp" alt="Apple" style="height: 76px;">
        </c:when>
        <c:when test="${board.brand eq '삼성'}">
          <img src="/resources/images/Samsung_Logo.svg.webp" alt="Samsung" style="height: 62px;">
        </c:when>
        <c:when test="${board.brand eq '소니'}">
          <img src="/resources/images/sonylogo.png" alt="Sony" style="height: 90px;">
        </c:when>
        <c:when test="${board.brand eq 'LG'}">
          <img src="/resources/images/LG_logo_(2014).svg" alt="LG" style="height: 62px;">
        </c:when>
        <c:otherwise>
          <span class="text-muted">브랜드 없음</span>
        </c:otherwise>
      </c:choose>
    </div>
  </div>

  <!-- 수정 폼 -->
  <form id="actionForm" action="" method="post" class="product-detail-container">
    <!-- 좌측 이미지 -->
    <div class="product-images">
      <div class="main-image">
        <img src="<c:url value='${board.imgPath}'/>" alt="대표 이미지" />
      </div>
      <div class="thumb-list">
        <div class="thumb"><img src="<c:url value='${board.imgPath}'/>" alt="썸네일" /></div>
        <div class="thumb empty"></div>
        <div class="thumb empty"></div>
        <div class="thumb empty"></div>
      </div>
    </div>

    <!-- 우측 수정 가능한 정보 -->
    <div class="product-info">
      <input type="hidden" name="pno" value="${board.pno}" />
      <input type="hidden" name="pageNum" value="${cri.pageNum}" />
      <input type="hidden" name="amount" value="${cri.amount}" />
      <input type="hidden" name="type" value="${cri.type}" />
      <input type="hidden" name="keyword" value="${cri.keyword}" />

      <div class="product-title">
        <input type="text" name="title" value="${board.title}" class="form-control" />
      </div>
      <div class="model">
        <input type="text" name="product" value="${board.product}" class="form-control" />
      </div>

      <div class="mb-3">
        <input type="number" name="price" value="${board.price}" class="form-control" />
        <div class="reg-date">
          등록일: <fmt:formatDate value="${board.regDate}" pattern="yyyy-MM-dd"/>
        </div>
      </div>

      <div>
        <input type="text" name="condition" value="${board.condition}" class="form-control" />
        <input type="text" name="status" value="${board.status}" class="form-control" />
      </div>

      <div class="brand">
        브랜드: <input type="text" name="brand" value="${board.brand}" class="form-control" />
      </div>

      <div class="nickname mt-2">
        판매자: <input type="text" name="nickname" value="${board.nickname}" class="form-control" />
      </div>

      <div class="product-description mt-3">
        <textarea name="description" rows="6" class="form-control">${board.description}</textarea>
      </div>

      <div class="d-flex justify-content-end gap-2 mt-4">
        <button type="submit" formaction="/board/modify" class="btn btn-warning">수정</button>
        <button type="submit" formaction="/board/remove" class="btn btn-danger">삭제</button>
        <button type="button" id="listBtn" class="btn btn-outline-secondary">목록</button>
      </div>
    </div>
  </form>
</div>

<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script>
  $(function() {
    $('#listBtn').on('click', function() {
      const form = $('#actionForm');
      form.attr('action', '/board/list');
      form.attr('method', 'get');
      form.submit();
    });
  });
</script>

<%@ include file="../includes/footer.jsp" %>
