<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>汽车品牌管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#btnExport").click(function(){
				top.$.jBox.confirm("确认要导出申报登记数据Word吗？","系统提示",function(v,h,f){
					if(v=="ok"){
						$("#searchForm").attr("action","${ctx}/qctype/qcType/exportWord");
						$("#searchForm").submit();
					}
				},{buttonsFocus:1});
				top.$('.jbox-body .jbox-icon').css('top','55px');
			});
		});
	</script>
</head>
<%-- <form:form id="searchForm" modelAttribute="unitInfo" action="${ctx}/waster/unitInfo/" method="post" class="breadcrumb form-search">
		<form:hidden path="id" value="${unitInfo.id}"/>
	<c:if test="${unitInfo.id!= null}">
	<input id="btnExport" class="btn" type="button" value="导出Word" />
	</c:if>
	</form:form> --%>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/qctype/qcType/">汽车品牌列表</a></li>
		<shiro:hasPermission name="qctype:qcType:edit"><li><a href="${ctx}/qctype/qcType/form">汽车品牌添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="qcType" action="${ctx}/qctype/qcType/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>品牌：</label>
				<form:input path="typename" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
			<input id="btnExport" class="btn" type="button" value="导出Word" />
			</li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>品牌</th>
				<th>update_date</th>
				<th>remarks</th>
				<th>状态</th>
				<shiro:hasPermission name="qctype:qcType:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="qcType">
			<tr>
				<td><a href="${ctx}/qctype/qcType/form?id=${qcType.id}">
					${qcType.typename}
				</a></td>
				<td>
					<fmt:formatDate value="${qcType.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${qcType.remarks}
				</td>
				<td>
				<c:if test="${qcType.state == '0'}">
						已提交
					</c:if>
					<c:if test="${qcType.state == '1'}">
						正在审核
					</c:if>
					<c:if test="${qcType.state == '2'}">
						审核通过
					</c:if>
				</td>
				<shiro:hasPermission name="qctype:qcType:edit"><td>
    				<a href="${ctx}/qctype/qcType/form?id=${qcType.id}">修改</a>
					<a href="${ctx}/qctype/qcType/delete?id=${qcType.id}" onclick="return confirmx('确认要删除该汽车品牌吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>