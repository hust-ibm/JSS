<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"></c:set>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <meta name="renderer" content="webkit">
    <title></title>  
    <link rel="stylesheet" href="${ctx}/css/pintuer.css">
    <link rel="stylesheet" href="${ctx}/css/admin.css">
     <!-- font-awesome图标 -->
<!--    <link rel="stylesheet" href="${ctx}/font-awesome/css/font-awesome.min.css"> -->


    <script src="${ctx}/js/jquery.js"></script>
    <script src="${ctx}/js/pintuer.js"></script>  
</head>
<body>
<form method="post" action="upload" enctype="multipart/form-data">
  <div class="panel admin-panel">
    <div class="panel-head"><strong class="icon-reorder"> 作业列表</strong></div>
   
    <table class="table table-hover text-center">
      <tr>
        <th width="120">ID</th>
        <th>作业名</th>       
        <th>题目内容</th>
        <th>截至时间</th>
        <th>作业状态</th> 
        <th>上交作业</th> 
        <th>操作</th>        
      </tr>  
	  
		
		<tr>
          <td>1</td>
          <td style="display:none"><input type="text"  name="taskid" value="1" /></td>
          <td><input type="text" style="cursor: text;background-color:white;border:none;text-align:center" name="taskName" value="作业二" readonly="readonly" /></td>
          <td><button type="button">下载</button></td>
          <td>2017-03-10</td>
          <td><input type="text" style=" background-color:white;border:none;text-align:center;width:100px;" name="submit" value="已提交" disabled="disabled" /></td>   
          <td align="center">
			<input type="file" style="text-align:center;width:200px;" multiple="multiple" name="uploadfile"> 
		  </td>   
		  <td>
			<input type="submit"  value="上传">
		  </td>       
               
         
        </tr>
       
          
      <tr>
        <td colspan="8"><div class="pagelist"> <a href="">上一页</a> <span class="current">1</span><a href="">2</a><a href="">3</a><a href="">下一页</a><a href="">尾页</a> </div></td>
      </tr>
    </table>
  </div>
</form>

</body></html>