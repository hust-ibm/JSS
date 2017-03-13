<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"></c:set>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<meta name="renderer" content="webkit">
<title></title>
<link rel="stylesheet" href="${ctx}/css/pintuer.css">
<link rel="stylesheet" href="${ctx}/css/admin.css">
<!-- font-awesome图标 -->
<link rel="stylesheet"
	href="${ctx}/font-awesome/css/font-awesome.min.css">


<script src="${ctx}/js/jquery.js"></script>
<script src="${ctx}/js/pintuer.js"></script>
</head>
<body>
	<form method="post" action="">
		<div class="panel admin-panel">
			<div class="panel-head">
				<strong class="icon-reorder">成绩查看</strong>
			</div>
			<div class="padding border-bottom">
				<ul class="search">
					<li><select name="job" class="input">
							<option value="">第一次作业</option>
							<option value="">第二次作业</option>
							<option value="">第三次作业</option>
					</select></li>
						<li><button type="submit" class="button border-green">查询</button></li>

				</ul>
			</div>
			<table class="table table-hover text-center">
				<tr>
					<th>序号</th>
					<th>学生学号</th>
					<th>学生名</th>
					<th>成绩</th>
					<th>操作</th>

				</tr>
				<tr>
					<td>1</td>
					<td>M201672976</td>
					<td>谈凯</td>
					<td>95</td>
					<td>
						<div class="button-group">
							<a class="button border-main" href="tum/updateResult"><span
								class="icon-edit"></span>修改</a> 
						</div>
					</td>
				</tr>
				<tr>
					<td>2</td>
					<td>M201672977</td>
					<td>陈杰</td>
					<td>95</td>
					<td>
						<div class="button-group">
							<a class="button border-main" href="addjob.html"><span
								class="icon-edit"></span> 修改</a> 
						</div>
					</td>
				</tr>

				<tr>
					<td colspan="8"><div class="pagelist">
							<a href="">上一页</a> <span class="current">1</span><a href="">2</a><a
								href="">3</a><a href="">下一页</a><a href="">尾页</a>
						</div></td>
				</tr>
			</table>
		</div>
	</form>
</body>
</html>