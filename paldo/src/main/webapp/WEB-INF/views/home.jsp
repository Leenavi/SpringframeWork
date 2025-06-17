<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="includes/header.jsp" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>팔도마켓 | 메인</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Noto Sans 폰트 -->
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@700&display=swap" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet">
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
<body>

    <main class="main-area">
        <!-- 타이핑 타이틀 -->
        <div class="main-title">
            <span id="typing-txt"></span><span class="typing-cursor">|</span>
        </div>

        <div class="phone-container" style="position: relative; text-align: center;">
            <!-- 자동재생 무음 -->
            <video id="myVideo" src="/resources/images/KakaoTalk_20250518_163345208 (1).mp4" autoplay muted loop playsinline class="phone-screen"></video>
            <img src="/resources/images/portrait_black.png" alt="폰 프레임" class="phone-frame-img" />
         
        </div>
         
            <!-- 버튼 -->
            <button id="playButton">🔊안쪼꼼​´･ᴗ･`!</button>
    </main>

	

    <div style="text-align: center; margin-top: 30px;">
        <a href="/board/list" 
           class="btn btn-light border fw-semibold"
           style="padding: 12px 28px; border-radius: 8px; font-size: 26px; color: #000; text-decoration: none;">
            🛍️ 전체 리스트 보기
        </a>
    </div>
	<br>
	<div class="d-flex justify-content-center my-4">
	  <form class="d-flex align-items-center shadow-sm rounded-pill px-3"
	        action="/board/list" method="get"
	        style="width: 500px; background-color: white;">
	    
	    <!-- 기본 아이콘 (bi bi-search) -->
	    <i class="bi bi-search text-primary me-2"></i>
	
	    <!-- 검색 입력창 -->
	    <input type="text" class="form-control border-0" name="keyword"
	           placeholder="상품을 검색해보세요"
	           style="box-shadow: none; min-width: 0;">
	
	    <!-- 검색 버튼 -->
	    <button type="submit"
	            class="btn btn-primary text-white ms-2 rounded-pill px-3 py-2"
	            style="white-space: nowrap;">
	      검색
	    </button>
	  </form>
	</div>
	
    <section class="product-preview" style="margin: 60px auto; max-width: 1160px;">

           <!-- Apple -->
       <div style="display: flex; align-items: center; gap: 10px; margin-bottom: 10px;">
    <img src="/resources/images/apple_logo_icon_168588.webp" alt="애플" style="height: 82px;">
	    <h3 style="margin: 0;">최신 상품</h3>
	</div>
	
	<div class="product-row" style="display: flex; gap: 30px; flex-wrap: wrap; margin-bottom: 30px;">
	    <c:forEach var="item" items="${appleList}" begin="0" end="3">
	        <div class="card" style="width: 17%; height: 300px; border: 1px solid #ccc; border-radius: 12px; padding: 15px; cursor: pointer; display: flex; flex-direction: column; justify-content: space-between;" 
	             onclick="location.href='/board/get?boardid=${item.boardid}'">
	            <!-- 이미지 -->
	            <img src="${item.imgPath}" alt="${item.title}" style="width: 100%; height: 200px; object-fit: cover; border-radius: 10px;">
	            <div style="font-weight: bold; margin-top: 8px;">${item.title}</div>
	            <div style="color: gray; font-size: 14px;">₩ <fmt:formatNumber value="${item.price}" type="number" /></div>
	        </div>
	    </c:forEach>
	    
		  <!-- 🧃 Apple 더보기 카드 -->
		  <div class="card text-center d-flex justify-content-center align-items-center" 
		       style="width: 17%; height: 300px; border: 2px dashed #999; border-radius: 12px; padding: 15px; cursor: pointer;" 
		       onclick="location.href='/board/list?keyword=Apple'">
		    <div style="margin: auto;">
		       <img src="/resources/images/apple_logo_icon_168588.webp" alt="애플" style="height: 82px;">
		      <div style="font-weight: bold; font-size: 18px; margin-top: 10px;">Apple 더보기</div>
		      <div style="color: gray; font-size: 14px;">더 많은 중고 애플 제품 보기</div>
		    </div>
		  </div>
	    
	</div>



        <!-- Samsung -->
         <div style="display: flex; align-items: center; gap: 10px; margin-bottom: 10px;">
    <img src="/resources/images/Samsung_Logo.svg.webp" alt="삼성" style="height: 57px;">
	    <h3 style="margin: 0;">최신 상품</h3>
	</div>
	
	<div class="product-row" style="display: flex; gap: 30px; flex-wrap: wrap; margin-bottom: 30px;">
	    <c:forEach var="item" items="${samsungList}" begin="0" end="3">
	        <div class="card" style="width: 17%; height: 300px; border: 1px solid #ccc; border-radius: 12px; padding: 15px; cursor: pointer; display: flex; flex-direction: column; justify-content: space-between;" 
	             onclick="location.href='/board/get?boardid=${item.boardid}'">
	            <!-- 이미지 -->
	            <img src="${item.imgPath}" alt="${item.title}" style="width: 100%; height: 200px; object-fit: cover; border-radius: 10px;">
	            <div style="font-weight: bold; margin-top: 8px;">${item.title}</div>
	            <div style="color: gray; font-size: 14px;">₩ <fmt:formatNumber value="${item.price}" type="number" /></div>
	        </div>
	    </c:forEach>
	     <!-- 🧃 Samsung 더보기 카드 -->
		  <div class="card text-center d-flex justify-content-center align-items-center" 
		       style="width: 17%; height: 300px; border: 2px dashed #999; border-radius: 12px; padding: 15px; cursor: pointer;" 
		       onclick="location.href='/board/list?keyword=Samsung'">
		    <div style="margin: auto;">
		       <img src="/resources/images/Samsung_Logo.svg.webp" alt="삼성" style="height: 52px;">
		      <div style="font-weight: bold; font-size: 18px; margin-top: 10px;">Samsung 더보기</div>
		      <div style="color: gray; font-size: 14px;">더 많은 중고 삼성 제품 보기</div>
		    </div>
		  </div>
	</div>

        <!-- Sony -->
        <div style="display: flex; align-items: center; gap: 10px; margin-bottom: 10px;">
            <img src="/resources/images/sonylogo.png" alt="Sony" style="height: 72px;">
            <h3 style="margin: 0;">최신 상품</h3>
        </div>
       <div class="product-row" style="display: flex; gap: 30px; flex-wrap: wrap; margin-bottom: 30px;">
	    <c:forEach var="item" items="${sonyList}" begin="0" end="3">
	        <div class="card" style="width: 17%; height: 300px; border: 1px solid #ccc; border-radius: 12px; padding: 15px; cursor: pointer; display: flex; flex-direction: column; justify-content: space-between;" 
	             onclick="location.href='/board/get?boardid=${item.boardid}'">
	            <!-- 이미지 -->
	            <img src="${item.imgPath}" alt="${item.title}" style="width: 100%; height: 200px; object-fit: cover; border-radius: 10px;">
	            <div style="font-weight: bold; margin-top: 8px;">${item.title}</div>
	            <div style="color: gray; font-size: 14px;">₩ <fmt:formatNumber value="${item.price}" type="number" /></div>
	        </div>
	    </c:forEach>
	    <!-- 🧃 Sony 더보기 카드 -->
		  <div class="card text-center d-flex justify-content-center align-items-center" 
		       style="width: 17%; height: 300px; border: 2px dashed #999; border-radius: 12px; padding: 15px; cursor: pointer;" 
		       onclick="location.href='/board/list?keyword=Sony'">
		    <div style="margin: auto;">
		       <img src="/resources/images/sonylogo.png" alt="소니" style="height: 92px;">
		      <div style="font-weight: bold; font-size: 18px; margin-top: 10px;">Sony 더보기</div>
		      <div style="color: gray; font-size: 14px;">더 많은 중고 Sony 제품 보기</div>
		    </div>
		  </div>
        </div>

        <!-- 기타 -->
        <div style="display: flex; align-items: center; gap: 10px; margin-bottom: 10px;">
            <img src="/resources/images/LG_logo_(2014).svg" alt="Other" style="height: 62px;">
            <h3 style="margin: 0;">최신 상품</h3>
        </div>
       <div class="product-row" style="display: flex; gap: 30px; flex-wrap: wrap; margin-bottom: 30px;">
	    <c:forEach var="item" items="${otherList}" begin="0" end="3">
	        <div class="card" style="width: 17%; height: 300px; border: 1px solid #ccc; border-radius: 12px; padding: 15px; cursor: pointer; display: flex; flex-direction: column; justify-content: space-between;" 
	             onclick="location.href='/board/get?boardid=${item.boardid}'">
	            <!-- 이미지 -->
	            <img src="${item.imgPath}" alt="${item.title}" style="width: 100%; height: 200px; object-fit: cover; border-radius: 10px;">
	            <div style="font-weight: bold; margin-top: 8px;">${item.title}</div>
	            <div style="color: gray; font-size: 14px;">₩ <fmt:formatNumber value="${item.price}" type="number" /></div>
	        </div>
	    </c:forEach>
	    <!-- 🧃 LG 더보기 카드 -->
		  <div class="card text-center d-flex justify-content-center align-items-center" 
		       style="width: 17%; height: 300px; border: 2px dashed #999; border-radius: 12px; padding: 15px; cursor: pointer;" 
		       onclick="location.href='/board/list?keyword=LG'">
		    <div style="margin: auto;">
		       <img src="/resources/images/LG_logo_(2014).svg" alt="LG" style="height: 62px;">
		      <div style="font-weight: bold; font-size: 18px; margin-top: 10px;">LG 더보기</div>
		      <div style="color: gray; font-size: 14px;">더 많은 중고 LG 제품 보기</div>
		    </div>
		  </div>
   	</div>
    </section>

    <!-- 타이핑 스크립트 -->
    <script>
      const spanEl = document.getElementById("typing-txt");
      const txtArr = [
    	    '팔도마켓에서 오늘의 인기폰을 만나보세요!',
            '믿고사는 안전 중고폰 거래!',
            '중고폰도 이제 스마트하게, 팔도마켓!',
            'Get your dream phone!',
            '안쪼꼼​´･ᴗ･`!',
      ];
      let index = 0;
      let currentTxt = txtArr[index].split("");

      function writeTxt() {
        spanEl.textContent += currentTxt.shift();
        if (currentTxt.length !== 0) {
          setTimeout(writeTxt, Math.floor(Math.random() * 100));
        } else {
          currentTxt = spanEl.textContent.split("");
          setTimeout(deleteTxt, 2000);
        }
      }

      function deleteTxt() {
        currentTxt.pop();
        spanEl.textContent = currentTxt.join("");
        if (currentTxt.length !== 0) {
          setTimeout(deleteTxt, Math.floor(Math.random() * 100));
        } else {
          index = (index + 1) % txtArr.length;
          currentTxt = txtArr[index].split("");
          writeTxt();
        }
      }

      writeTxt();

    // 버튼 클릭 → 소리 켜고 재시작
    const video = document.getElementById("myVideo");
    const playBtn = document.getElementById("playButton");

    playBtn.addEventListener("click", () => {
    	video.src = "/resources/images/KakaoTalk_20250519_102200754.mp4"
        video.muted = false;           // 소리 켜기
        video.currentTime = 0;         // 처음부터
        video.play();                  // 재생
    });

  

</script>

</body>
</html>
