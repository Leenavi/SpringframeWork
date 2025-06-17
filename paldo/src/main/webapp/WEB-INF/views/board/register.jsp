<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>    
<%@ include file="../includes/header.jsp" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" />
    <title>팔도마켓 | 중고폰 상품등록</title>
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@700&display=swap" rel="stylesheet" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/dist/css/main.css" />
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
    
    </style>
    
</head>
<body>

<div class="product-register-container">
    <h2 class="form-title">😎 중고폰 상품등록</h2>

    <form action="${pageContext.request.contextPath}/board/register" 
          method="post" 
          enctype="multipart/form-data" 
          class="product-form">

        <!-- 대표 이미지 업로드 -->
        <div class="form-group" style="display: inline-block; width: 100%;">
            <label>대표 이미지 업로드(*수정 불가) <span class="required">*</span></label>
            <input type="file" name="mainImage" accept="image/*" required />
        </div>

        <!-- 제목 -->
        <div class="form-group">
            <label>제목 <span class="required">*</span></label>
            <input type="text" name="title" required placeholder="예: 아이폰 13 미드나잇" />
        </div>

        <!-- 모델명 -->
        <div class="form-group">
            <label>모델명 <span class="required">*</span></label>
            <input type="text" name="product.modelname" required placeholder="예: 아이폰 13" />
        </div>

        <!-- 가격 -->
        <div class="form-group">
            <label>가격 <span class="required">*</span></label>
            <input type="number" name="product.price" required placeholder="예: 650000" />
        </div>

        <!-- 브랜드 -->
        <select name="product.brand" required>
            <option value="">선택하세요</option>
            <option value="Apple">Apple</option>
            <option value="Samsung">Samsung</option>
            <option value="Sony">Sony</option>
            <option value="LG">LG</option>
            <option value="기타">기타</option>
        </select>

        <!-- 상품 상태 -->
        <div class="form-group">
            <label>상품 상태 <span class="required">*</span></label>
            <div class="option-group">
                <input type="radio" id="cond1" name="product.condition" value="S" checked />
                <label for="cond1" class="btn-radio">S</label>

                <input type="radio" id="cond2" name="product.condition" value="A" />
                <label for="cond2" class="btn-radio">A</label>

                <input type="radio" id="cond3" name="product.condition" value="B" />
                <label for="cond3" class="btn-radio">B</label>

                <input type="radio" id="cond4" name="product.condition" value="C" />
                <label for="cond4" class="btn-radio">C</label>
            </div>
        </div>

        <!-- 판매 상태 -->
        <div class="form-group">
            <label>판매 상태</label>
            <div class="option-group">
                <input type="radio" id="stat1" name="status" value="판매중" checked />
                <label for="stat1" class="btn-radio">판매중</label>

               
            </div>
        </div>

        <!-- 닉네임 -->
        <div class="form-group">
            <label>닉네임 <span class="required">*</span></label>
            <input type="text" name="user.nickname" required placeholder="판매자 닉네임 입력" />
        </div>

        <!-- 비밀번호 -->
        <div class="form-group">
            <label>비밀번호 (수정/삭제용) <span class="required">*</span></label>
            <input type="password" name="user.pwd" required placeholder="4~12자 비밀번호" />
        </div>

        <!-- 설명 -->
        <div class="form-group">
            <label>설명 <span class="required">*</span></label>
            <textarea name="product.description" rows="6" minlength="20" required placeholder="20자 이상 상세 설명을 작성해 주세요."></textarea>
        </div>

        <!-- 등록 버튼 -->
        <div class="form-actions">
            <button type="submit" class="btn-blue">등록하기</button>
        </div>
    </form>
</div>

</body>
</html>
