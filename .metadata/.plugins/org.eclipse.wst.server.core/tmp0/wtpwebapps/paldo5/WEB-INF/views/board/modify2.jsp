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
    h6 { color: #36454F; }
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

  <!-- 수정 폼 -->
  <form id="actionForm" action="/board/update" method="post" 
      enctype="multipart/form-data" class="product-detail-container">
    <div class="product-images">
      <div class="main-image">
        <img src="<c:url value='/resources/${dto.imgPaths[0].imgPath}'/>" alt="대표 이미지" />
      </div>
      <div class="thumb-list">
        <c:forEach var="img" items="${dto.imgPaths}" varStatus="loop">
          <div class="thumb">
            <img src="<c:url value='/resources/${img.imgPath}'/>" alt="써모 이미지 ${loop.index + 1}" />
          </div>
        </c:forEach>
      </div>
      <div class="form-group mt-2">
        <label>이미지 변경</label>
        <input type="file" name="mainImage" accept="image/*" class="form-control" />
      </div>
    </div>

    <div class="product-info">
      <input type="hidden" name="board.boardid" value="${dto.board.boardid}" />
      <input type="hidden" name="product.productid" value="${dto.product.productid}" />

      <input type="hidden" name="pageNum" value="${cri.pageNum}" />
      <input type="hidden" name="amount" value="${cri.amount}" />
      <input type="hidden" name="type" value="${cri.type}" />
      <input type="hidden" name="keyword" value="${cri.keyword}" />

      <div class="product-title"><h6>제목:</h6>
        <input type="text" name="board.title" value="${dto.board.title}" class="form-control" />
      </div>
      <div class="model"><h6>모델:</h6>
        <input type="text" name="product.modelName" value="${dto.product.modelName}" class="form-control" />
      </div>

      <div class="mb-3"><h6>가격:</h6>
        <input type="number" name="product.price" value="${dto.product.price}" class="form-control"  step="10000" />
        <div class="reg-date">
          <h6>등록일: <fmt:formatDate value="${dto.board.regDate}" pattern="yyyy-MM-dd"/></h6>
        </div>
      </div>

      <div class="mb-3">
        <h6>상태:</h6>
        <input type="text" name="product.condition" value="${dto.product.condition}" class="form-control mb-2" />
        <input type="text" name="board.status" value="${dto.board.status}" class="form-control" />
      </div>

      <div class="mb-3">
        <h6>브랜드:</h6>
        <select name="product.brand" class="form-select">
          <option value="Apple" ${dto.product.brand == 'Apple' ? 'selected' : ''}>애플</option>
          <option value="Samsung" ${dto.product.brand == 'Samsung' ? 'selected' : ''}>삼성</option>
          <option value="Sony" ${dto.product.brand == 'Sony' ? 'selected' : ''}>소니</option>
          <option value="LG" ${dto.product.brand == 'LG' ? 'selected' : ''}>LG</option>
          <option value="기타" ${dto.product.brand == '기타' ? 'selected' : ''}>기타</option>
        </select>
      </div>

      <div class="nickname mt-2">
        <h6>판매자:</h6>
        <input type="text" name="user.nickname" value="${dto.user.nickname}" class="form-control" readonly />
      </div>

      <div class="product-description mt-3">
        <textarea name="product.description" rows="6" class="form-control">${dto.product.description}</textarea>
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
