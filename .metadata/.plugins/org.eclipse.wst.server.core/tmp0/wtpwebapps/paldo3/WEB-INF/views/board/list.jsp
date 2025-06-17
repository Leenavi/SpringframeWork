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
<header class="masthead text-center text-dark d-flex align-items-center justify-content-center">

  <div class="container py-2">

    <!-- 타이핑 효과 텍스트 -->
   <main class="main-area">
        <!-- 타이핑 타이틀 -->
        <div class="main-title">
            <span id="typing-txt"></span><span class="typing-cursor">|</span>
        </div>

        <div class="phone-container" style="position: relative; text-align: center;">
            <!-- 자동재생 무음 -->
            <video id="myVideo" src="/resources/images/KakaoTalk_20250519_102200754.mp4" autoplay muted loop playsinline class="phone-screen"></video>
            <img src="/resources/images/portrait_black.png" alt="폰 프레임" class="phone-frame-img" />
         
        </div>
         
            <!-- 버튼 -->
            <button id="playButton">🔊강아지​🧡´･ᴗ･`🧡</button>
    </main>

    <!-- 타이핑 스크립트 -->
    <script>
      const spanEl = document.getElementById("typing-txt");
      const txtArr = [
        'Buy your dream phone!',
        'Get the best price!',
        'Find your perfect device here!',
        '최고의 가격에 드림폰을 만나보세요!'
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
    </script>

  </div>
</header>


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
              <td><c:out value="${board.pno}" /></td>
              <td>
                <a class="move text-decoration-none fw-semibold text-dark" href="<c:out value='${board.pno}'/>">
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
      actionForm.append("<input type='hidden' name='pno' value='"+$(this).attr("href")+"' >");
      actionForm.attr("action" , "/board/get");
      actionForm.submit();
    });
  });
</script>
</body>
</html>
