<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>汽车之家管理</title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/treetable.jsp" %>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#treeTable").treeTable({expandLevel : 3});
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/xiaoqc/xiqoQc/">汽车之家列表</a></li>
		<shiro:hasPermission name="xiaoqc:xiqoQc:edit"><li><a href="${ctx}/xiaoqc/xiqoQc/form">汽车之家添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="xiqoQc" action="${ctx}/xiaoqc/xiqoQc/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>name：</label>
				<form:input path="name" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<!--  contentTable-->
	<table id="treeTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>name</th>
				<th>description</th>
				<shiro:hasPermission name="xiaoqc:xiqoQc:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${list}" var="xiqoQc">
			<tr>
				<td><a href="${ctx}/xiaoqc/xiqoQc/form?id=${xiqoQc.id}">
					${xiqoQc.name}
				</a></td>
				<td>
					${xiqoQc.description}
				</td>
				<shiro:hasPermission name="xiaoqc:xiqoQc:edit"><td>
    				<a href="${ctx}/xiaoqc/xiqoQc/form?id=${xiqoQc.id}">修改</a>
					<a href="${ctx}/xiaoqc/xiqoQc/delete?id=${xiqoQc.id}" onclick="return confirmx('确认要删除该汽车之家吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>