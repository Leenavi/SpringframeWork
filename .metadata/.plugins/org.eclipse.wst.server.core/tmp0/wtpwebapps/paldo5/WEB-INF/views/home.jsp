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
<body>

    <main class="main-area">
        <!-- 타이핑 타이틀 -->
        <div class="main-title">
            <span id="typing-txt"></span><span class="typing-cursor">|</span>
        </div>

        <div class="phone-container" style="position: relative; text-align: center;">
            <!-- 자동재생 무음 -->
            <video id="myVideo" src="/resources/images/17434005158661.mp4" autoplay muted loop playsinline class="phone-screen"></video>
            <img src="/resources/images/portrait_black.png" alt="폰 프레임" class="phone-frame-img" />
         
        </div>
         
            <!-- 버튼 -->
            <button id="playButton">🔊qwer​🧡´･ᴗ･`🧡</button>
    </main>
    <div style="text-align: center; margin-top: 30px;">
  <div style="text-align: center; margin-top: 30px;">
  <a href="/board/list" 
     class="btn btn-light border fw-semibold"
     style="padding: 12px 28px; border-radius: 8px; font-size: 26px; color: #000; text-decoration: none;">
    🛍️ 전체 리스트 보기
  </a>
</div>
</div>
<section class="product-preview" style="margin: 60px auto; max-width: 960px;">

    <!-- Apple -->
    <div style="display: flex; align-items: center; gap: 10px; margin-bottom: 10px;">
        <img src="/resources/images/apple_logo_icon_168588.webp" alt="Apple" style="height: 82px;">
        <h3 style="margin: 0;">인기 상품</h3>
    </div>
    <div class="product-row" style="display: flex; gap: 20px; flex-wrap: wrap; margin-bottom: 30px;">
        <c:forEach var="item" items="${appleList}" begin="0" end="3">
            <div class="card" style="width: 22%; border: 1px solid #ccc; border-radius: 12px; padding: 10px; cursor: pointer;"
                 onclick="location.href='/board/get?pno=${item.pno}'">
                <img src="${item.imgPath}" alt="${item.title}" style="width: 100%; border-radius: 10px;">
                <div style="font-weight: bold; margin-top: 8px;">${item.title}</div>
                <div style="color: gray; font-size: 14px;">₩ <fmt:formatNumber value="${item.price}" type="number"/></div>
            </div>
        </c:forEach>
    </div>

    <!-- Samsung -->
    <div style="display: flex; align-items: center; gap: 10px; margin-bottom: 10px;">
        <img src="/resources/images/Samsung_Logo.svg.webp" alt="Samsung" style="height: 52px;">
        <h3 style="margin: 0;">인기 상품</h3>
    </div>
    <div class="product-row" style="display: flex; gap: 20px; flex-wrap: wrap; margin-bottom: 30px;">
        <c:forEach var="item" items="${samsungList}" begin="0" end="3">
            <div class="card" style="width: 22%; border: 1px solid #ccc; border-radius: 12px; padding: 10px; cursor: pointer;"
                 onclick="location.href='/board/get?pno=${item.pno}'">
                <img src="${item.imgPath}" alt="${item.title}" style="width: 100%; border-radius: 10px;">
                <div style="font-weight: bold; margin-top: 8px;">${item.title}</div>
                <div style="color: gray; font-size: 14px;">₩ <fmt:formatNumber value="${item.price}" type="number"/></div>
            </div>
        </c:forEach>
    </div>

    <!-- Sony -->
    <div style="display: flex; align-items: center; gap: 10px; margin-bottom: 10px;">
        <img src="/resources/images/sonylogo.png" alt="Sony" style="height: 72px;">
        <h3 style="margin: 0;">인기 상품</h3>
    </div>
    <div class="product-row" style="display: flex; gap: 20px; flex-wrap: wrap; margin-bottom: 30px;">
        <c:forEach var="item" items="${sonyList}" begin="0" end="3">
            <div class="card" style="width: 22%; border: 1px solid #ccc; border-radius: 12px; padding: 10px; cursor: pointer;"
                 onclick="location.href='/board/get?pno=${item.pno}'">
                <img src="${item.imgPath}" alt="${item.title}" style="width: 100%; border-radius: 10px;">
                <div style="font-weight: bold; margin-top: 8px;">${item.title}</div>
                <div style="color: gray; font-size: 14px;">₩ <fmt:formatNumber value="${item.price}" type="number"/></div>
            </div>
        </c:forEach>
    </div>

    <!-- 기타 -->
    <div style="display: flex; align-items: center; gap: 10px; margin-bottom: 10px;">
        <img src="/resources/images/LG_logo_(2014).svg" alt="Other" style="height: 62px;">
        <h3 style="margin: 0;">인기 상품</h3>
    </div>
    <div class="product-row" style="display: flex; gap: 20px; flex-wrap: wrap; margin-bottom: 30px;">
        <c:forEach var="item" items="${otherList}" begin="0" end="3">
            <div class="card" style="width: 22%; border: 1px solid #ccc; border-radius: 12px; padding: 10px; cursor: pointer;"
                 onclick="location.href='/board/get?pno=${item.pno}'">
                <img src="${item.imgPath}" alt="${item.title}" style="width: 100%; border-radius: 10px;">
                <div style="font-weight: bold; margin-top: 8px;">${item.title}</div>
                <div style="color: gray; font-size: 14px;">₩ <fmt:formatNumber value="${item.price}" type="number"/></div>
            </div>
        </c:forEach>
    </div>

</section>



    <script>
    // 타이핑 효과
    const txtArr = [
        '팔도마켓에서 오늘의 인기폰을 만나보세요!',
        '믿고사는 안전 중고폰 거래!',
        '중고폰도 이제 스마트하게, 팔도마켓!',
        'Get your dream phone!',
        'qwer​🧡´･ᴗ･`🧡!',
    ];
    const typingEl = document.getElementById("typing-txt");
    let txtIdx = 0;
    let charIdx = 0;

    function typeWriter() {
        if (charIdx === 0) typingEl.textContent = "";
        let currentText = txtArr[txtIdx];
        if (charIdx < currentText.length) {
            typingEl.textContent += currentText.charAt(charIdx);
            charIdx++;
            setTimeout(typeWriter, Math.random() * 45 + 32);
        } else {
            setTimeout(() => {
                charIdx = 0;
                txtIdx = (txtIdx + 1) % txtArr.length;
                typeWriter();
            }, 1600);
        }
    }
    typeWriter();

    // 버튼 클릭 → 소리 켜고 재시작
    const video = document.getElementById("myVideo");
    const playBtn = document.getElementById("playButton");

    playBtn.addEventListener("click", () => {
        video.muted = false;           // 소리 켜기
        video.currentTime = 0;         // 처음부터
        video.play();                  // 재생
    });
    </script>
</body>
</html>
