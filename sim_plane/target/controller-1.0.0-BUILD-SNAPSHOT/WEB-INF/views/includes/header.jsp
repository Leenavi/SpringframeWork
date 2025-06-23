<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <style>
        body{margin: 0; padding: 0; font-family: "맑은 고딕";}
        ul,ol,li {list-style: none; margin: 0; padding: 0;}

        header{width: 1300px; height: 150px; margin: 0 auto; }
        #logo {float: left; width: 130px; height: 70px; margin: 30px 0 0 10px;}
        #logo > img {width: 140px; height: 90px; margin: 30px 0 0 100px;}
        input{align-items: center; width: 300px; padding: 10px; font-size: 16px; border: 1px solid #ccc; border-radius: 5px;}
        button {padding: 5px 10px; font-size: 16px; cursor: pointer;}

        .seach{display: flex; align-items: center; width: 400px;
            border: 2px solid lightslategray; border-radius: 20px; padding: 10px 15px 0 0;}

        nav{float:right; width: 740px; height: 30px; margin: 50px 70px 0 0;}
        nav ul{font-family: "Arial"; font-weight: bold;}
        nav ul li{float: left; padding: 0 20px;}
        nav ul li a{text-decoration: none; color: #333;}
        nav ul li a:hover{color: green;}

        #slider{width: 1100px; height: 450px; margin: 0 auto;}
        #slider img{width: 1100px; height: 450px; margin: 0 auto;}
        h4{font-size: 20px; text-align:center; margin: 50px 70px 0 0;}

        section{width: 1100px; height: 280px; margin: 0 auto;}
        article{width: 255px; height: 145px; float: left; margin: 30px 10px;}
        article h3{margin: 0; font-size: 16px; height: 40px; width: 100%;}
        article > a > img{width: 200px; height: 150px}
        article h4{margin: 0; font-size: 14px; height: 30px;}
        article p{font-family: "돋움"; font-size: 12px; color: #666;}


        article .icon img{width: 70px; height: 50px; float: left;margin: 30px 0 0 5px;}
        article .icon span{width: 200%; text-align: left; float: left; font-size: 12px; margin: 10px 0 0 0;}
        article .icon h4{margin: 68px; font-size: 14px; height: 35px;}

        footer{width: 1100px; height: 100px; margin: 0 auto;}

        footer span{font-family: "돋움"; font-size: 12px; color: #666; margin: 20px 100px 0 0; float: left;}

        .board{margin : 0 auto}
    </style>
</head>
<body>
<header>

    <div id="logo"><img src="img01/로고.JPG"></div>

    <nav>
        <ul>
            <li><a href="#">카테고리</a></li>
            <li><a href="#">신상품</a></li>
            <li><a href="#">인기상품</a></li>
            <li><a href="#">공개예정</a></li>
            <li><a href="#">마감임박</a></li>
            <li><a href="#">상품요청</a></li>
            <li><a href="/board/list">고객센터</a></li>
        </ul>
    </nav>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
</header>