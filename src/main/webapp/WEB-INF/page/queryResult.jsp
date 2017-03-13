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
	<form method="post" action="query">
		<div class="panel admin-panel">
			<div class="panel-head">
				<strong class="icon-reorder">成绩查看</strong>
			</div>
			<div class="padding border-bottom">
				<ul class="search">
					<li><select name="taskId" class="input">
					<option>请选择要查询作业</option>		
					<c:if test="${taskList!=null}">
					<c:forEach items="${taskList}" var="task">
						<option value="${task.taskId}">${task.taskName}</option>
					</c:forEach>			
					</c:if>		
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
					<th>作业是否提交</th>
					<th>操作</th>

				</tr>
				<c:forEach items="${resultList}" var="result" step="1" varStatus="status">
				<tr>
					<td>${(curPage-1)*30 + status.count}</td>
					<td>${result.stuId}</td>
					<td>${stuNameList[status.count]}</td>
					<td align="center"><input
						style="width: 60px; border: 0px; background-color: white; cursor: text; text-align: center;"
						class="input-big" type="text" readonly="readonly" value="${result.score}" />
						<a class="u_edit border-main" href="javascript:;"><span
							class="icon-edit text-big"></span></a>
							<a class="u_save border-green" style="display: none;"	href="javascript:;" >
							<span class="icon-check-square-o text-big"></span>
						</a>
						</td>
						<c:if test="${result.submit == 0}">
							<td>否</td>
							<td>
								<div class="button-group">
									<a class="button border-red" href="javascript:;"><span
										class="icon-download"></span>下载作业</a>
								</div>
							</td>						
						</c:if>
						<c:if test="${result.submit == 1}">
							<td>是</td>
							<td>
								<div class="button-group">
									<a class="button border-yellow" href="javascript:;"><span
										class="icon-download"></span>下载作业</a>
								</div>
							</td>						
						</c:if>
					
				</tr>
				</c:forEach>			

				<tr>
					<td colspan="8"><div class="pagelist">
							<a href="query?curPage=1">尾页</a>
							<a href="query?curPage=${curPage-1}">上一页</a>
								<c:forEach begin="1" step="1" varStatus="status" end="${totalPage}">
									<c:if test="${curPage==status.count}">
										<span class="current">1</span>
									</c:if>
									<c:if test="${curPage!=status.count}">
										<a href="query?curPage=${status.count}">${status.count}</a>
									</c:if>
								</c:forEach>										
							<a href="query?curPage=${curPage+1}">下一页</a>
							<a href="query?curPage=${totalPage}">尾页</a>
						</div></td>
				</tr>
			</table>
		</div>
	</form>

	<script type="text/javascript">
		$(".u_edit").click(function(){
			var edit = $(this);
			var save = edit.next("a");	
			var input = edit.prevAll("input");
			edit.hide();
			save.show();
			input.css('border','');
			input.removeAttr("readonly");
			
		})
		$(".u_save").click(function(){
			var save = $(this);
			var edit = save.prev("a");
			var input = save.prevAll("input");
			$.ajax({
				type:"POST",
				url:"",
				contentType : "application/json;charset=utf-8",
				data : JSON.stringify({
					 "score":input.val()
				}),
				success:function(data){
					alert("修改成功！");
					save.hide();
					edit.show();	
					input.css('border','0px');
					input.attr("readonly","readonly");
				},
				error:function(){
					alert("修改失败！")
				}
			})
		})
	</script>

</body>
</html>