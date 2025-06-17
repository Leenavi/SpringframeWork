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
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/dist/css/product.css" />
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
  <form id="actionForm" action="/board/modify" method="post" 
      enctype="multipart/form-data" class="product-detail-container">
    <div class="product-images">
      <div class="main-image">
        <img src="<c:url value='${dto.imgPaths[0].imgPath}'/>" alt="대표 이미지" />
      </div>
      <div class="thumb-list">
        <c:forEach var="img" items="${board.imgPaths}" varStatus="loop">
          <div class="thumb">
            <img src="<c:url value='/resources/${img.imgPath}'/>" alt="써모 이미지 ${loop.index + 1}" />
          </div>
        </c:forEach>
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
        <input type="text" name="product.modelname" value="${dto.product.modelname}" class="form-control" />
      </div>

      <div class="mb-3"><h6>가격:</h6>
        <input type="number" name="product.price" value="${dto.product.price}" class="form-control"  step="10000" />
        <div class="reg-date">
          <h6>등록일: <fmt:formatDate value="${dto.board.regDate}" pattern="yyyy-MM-dd"/></h6>
        </div>
      </div>

      <div class="mb-2">
        <h6>상태:</h6>
	
	    <input type="radio" 
	           id="stat1" 
	           name="product.condition" 
	           value="S" 
	           ${dto.product.condition == 'S' ? 'checked' : ''} />
	    <label for="stat1" class="btn-radio">S</label>
	    

	    <input type="radio" 
	           id="stat2" 
	           name="product.condition" 
	           value="A" 
	           ${dto.product.condition == 'A' ? 'checked' : ''} />
	    <label for="stat2" class="btn-radio">A</label>
	    
	    <input type="radio" 
	           id="stat3" 
	           name="product.condition" 
	           value="B" 
	           ${dto.product.condition == 'B' ? 'checked' : ''} />
	    <label for="stat3" class="btn-radio">B</label>
	    
	    <input type="radio" 
	           id="stat4" 
	           name="product.condition" 
	           value="C" 
	           ${dto.product.condition == 'C' ? 'checked' : ''} />
	    <label for="stat4" class="btn-radio">C</label>
	 </div>
      

     <div class="mb-3">
    <h6>판매 상태:</h6>
    <select name="board.status" class="form-select">
        <option value="판매중" ${dto.board.status == '판매중' ? 'selected' : ''}>판매중</option>
        <option value="예약중" ${dto.board.status == '예약중' ? 'selected' : ''}>예약중</option>
        <option value="판매완료" ${dto.board.status == '판매완료' ? 'selected' : ''}>판매완료</option>
    </select>
	</div>

      <div class="mb-3">
        <h6>브랜드:</h6>
        <select name="product.brand" class="form-select">
          <option value="Apple" ${board.product.brand == 'Apple' ? 'selected' : ''}>애플</option>
          <option value="Samsung" ${board.product.brand == 'Samsung' ? 'selected' : ''}>삼성</option>
          <option value="Sony" ${board.product.brand == 'Sony' ? 'selected' : ''}>소니</option>
          <option value="LG" ${board.product.brand == 'LG' ? 'selected' : ''}>LG</option>
          <option value="기타" ${board.product.brand == '기타' ? 'selected' : ''}>기타</option>
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
