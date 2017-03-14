<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
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

<script src="${ctx}/js/jquery.js"></script>
<script src="${ctx}/js/pintuer.js"></script>
</head>
<body>
	<form method="post" action="">
		<div class="panel admin-panel">
			<div class="panel-head">
				<strong class="icon-reorder"> 导出成绩</strong>
			</div>
			<div class="padding border-bottom">
				<ul class="search">
					<li>
						<button type="button" class="button border-green" id="checkall">
							<span class="icon-check"></span> 全选
						</button>&nbsp;&nbsp;&nbsp;&nbsp;
						<button type="button" class="button border-yellow" id="checkall">
							<span class="icon-reply">导出</span>
						</button>

					</li>

				</ul>
			</div>
			<table class="table table-hover text-center">
				<tr>
					<th width="120">ID</th>
					<th>作业名</th>
				</tr>
				<c:forEach items="${taskList}" var="task" varStatus="status">
					<tr>
						<td><input type="checkbox" name="id[]" value="${task.taskId}" />
							${status.count}</td>
						<td>${task.taskName}</td>
					</tr>
				</c:forEach>


			</table>
		</div>
	</form>

	<script type="text/javascript">
	
		/* $("#checkall").click(function(){ 
		 $("input[name='id[]']").each(function(){
		 if (this.checked) {
		 this.checked = false;
		 }
		 else {
		 this.checked = true;
		 }
		 });
		 })  */
		$("#checkall").click(function() {
			if ($("input[name='id[]']").is(":checked")) {
				
				$("input[name='id[]']").prop('checked',false);
			} else {
				
				$("input[name='id[]']").prop('checked',true);
			}
		})
	</script>

</body>
</html>