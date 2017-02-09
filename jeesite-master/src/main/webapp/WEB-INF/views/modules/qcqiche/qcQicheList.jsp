<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>汽车管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	$(document).ready(function() {

	});
	function page(n, s) {
		$("#pageNo").val(n);
		$("#pageSize").val(s);
		$("#searchForm").submit();
		return false;
	}
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/qcqiche/qcQiche/">汽车列表</a></li>
		<shiro:hasPermission name="qcqiche:qcQiche:edit">
			<li><a href="${ctx}/qcqiche/qcQiche/form">汽车添加</a></li>
		</shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="qcQiche"
		action="${ctx}/qcqiche/qcQiche/" method="post"
		class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageSize}" />
		<ul class="ul-form">
			<li><label>name：</label> <form:input path="name"
					htmlEscape="false" maxlength="255" class="input-medium" /></li>
			<li><label>price：</label> <form:input path="price"
					htmlEscape="false" maxlength="255" class="input-medium" /></li>
			<li><label>qcType</label>
			<%--  <form:select path="qcType"
					class="input-medium">
					<form:option value="" label="" />
					 <form:options items="${fns:getDictList('qc_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/> 
				</form:select> --%>
				
				<select id="qcType" name="qcType" class="input-xlarge required" maxlength="64"onchange="selectIndustry(this.value)">
					<option value=""></option>
					<c:forEach items="${QcTypes}" var="qcType">
								<option value="${qcType.id}" >${qcType.typename}</option>
					</c:forEach>
				</select>
				</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary"
				type="submit" value="查询" /></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}" />
	<table id="contentTable"
		class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>name</th>
				<th>decription</th>
				<th>price</th>
				<th>update_date</th>
				<th>remarks</th>
				<th>qc_type_id</th>
				<shiro:hasPermission name="qcqiche:qcQiche:edit">
					<th>操作</th>
				</shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="qcQiche">
				<tr>
					<td><a href="${ctx}/qcqiche/qcQiche/form?id=${qcQiche.id}">
							${qcQiche.name} </a></td>
					<td>${qcQiche.decription}</td>
					<td>${qcQiche.price}</td>
					<td><fmt:formatDate value="${qcQiche.updateDate}"
							pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<td>${qcQiche.remarks}</td>
					<td>${qcQiche.qcType.typename} <%-- ${fns:getDictLabel(qcQiche.qcTypeId, '', '')} --%>
					</td>
					<shiro:hasPermission name="qcqiche:qcQiche:edit">
						<td><a href="${ctx}/qcqiche/qcQiche/form?id=${qcQiche.id}">修改</a>
							<a href="${ctx}/qcqiche/qcQiche/delete?id=${qcQiche.id}"
							onclick="return confirmx('确认要删除该汽车吗？', this.href)">删除</a></td>
					</shiro:hasPermission>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>