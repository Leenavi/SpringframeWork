<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Custom Login Page</h1>
	<h2><c:out value="${error}"/></h2>
	<h2><c:out value="${logout}"></c:out></h2>
	
	<form action="/login" method="post"> <!-- 컨트롤러에 이 매핑 안 만들어도 스프링이 알아서 해줌. -->
		<div>
			<input type='text' name='username' value='admin90'>
		</div>
		<div>
			<input type='password' name='password' value='pw90'>
		</div>
		
		<div>
			<input type="checkbox" name="remember-me">Remember Me
		</div>
		
		<div>
			<input type='submit'>
		</div>
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
	</form>
</body>
</html>