<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>测试列表</title>
<base href="${ctx}">
</head>
<body>
<h2>Hello World!</h2>
	<table id="tab">
  		<tr>
  			<th>id</th>
  			<th>用户名</th>
  			<th>密码</th>
  		
  		</tr>
  		 
  		<%----%> <c:forEach items="${adminList}"  var="admin">
     	<tr>
  			<td>${admin.adminid}</td>
  			<td>${admin.adminname}</td>
  			<td>${admin.adminpassword}</td>
  		</tr>
     </c:forEach> 
  	</table>
	
</body>
</html>