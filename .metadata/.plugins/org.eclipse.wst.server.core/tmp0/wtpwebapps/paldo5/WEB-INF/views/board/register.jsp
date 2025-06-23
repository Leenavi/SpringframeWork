<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>    
<%@ include file="../includes/header.jsp" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>팔도마켓 | 대표 이미지 업로드</title>
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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/dist/css/product.css">
    <title>이미지 업로드 테스트</title>
</head>
<body>

<div class="product-register-container">
    <h2 class="form-title">📱 이미지 업로드 테스트</h2>
    
    <!-- 파일 업로드 폼 -->
    <form action="${pageContext.request.contextPath}/post/upload" 
          method="post" 
          enctype="multipart/form-data" 
          class="image-upload-form">
        
        <!-- ✅ 대표 이미지 업로드 -->
        <div class="form-group" style="display: inline-block; width: 100%;">
            <label>대표 이미지 업로드 <span class="required">*</span></label>
            <input type="file" name="mainImage" accept="image/*" required />
            <button type="submit" class="btn-blue" style="float: right;">이미지 업로드</button>
        </div>
    </form>
</div>

</body>
</html>
