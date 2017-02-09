<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>汽车管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/qcqiche/qcQiche/">汽车列表</a></li>
		<li class="active"><a href="${ctx}/qcqiche/qcQiche/form?id=${qcQiche.id}">汽车<shiro:hasPermission name="qcqiche:qcQiche:edit">${not empty qcQiche.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="qcqiche:qcQiche:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="qcQiche" action="${ctx}/qcqiche/qcQiche/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>	
		<div class="control-group">
			<label class="control-label">归属部门:</label>
			<div class="controls">
                <sys:treeselect id="office" name="office.id" value="${qcQiche.office.id}" labelName="office.name" labelValue="${qcQiche.office.name}"
					title="部门" url="/sys/office/treeData?type=2" cssClass="required" notAllowSelectParent="true"/>
			</div>
		</div>	
		<div class="control-group">
			<label class="control-label">汽车：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">介绍：</label>
			<div class="controls">
				<form:textarea path="decription" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">价格：</label>
			<div class="controls">
				<form:input path="price" htmlEscape="false" maxlength="255" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">remarks：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">汽车品牌：</label>
			<div class="controls">
				<%-- <form:select path="qcTypeId" class="input-xlarge">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select> --%>
				
				<select id="qcType" name="qcType" class="input-xlarge required" maxlength="64"onchange="selectIndustry(this.value)">
					<option value=""></option>
					<c:forEach items="${QcTypes}" var="qcType">
					<%-- <form:option value="${}" label="${qcType.typename}"/> --%>
				<%-- 	<option value="${qcType.id}" >${qcType.typename}</option>  --%>

						  <c:choose>
							<c:when test="${qcType.id==qcQiche.qcType.id }">
								<option value="${qcType.id }" selected="selected">${qcType.typename }</option>
							</c:when>
							<c:otherwise>
								<option value="${qcType.id}" >${qcType.typename}</option>
							</c:otherwise>
						</c:choose> 
					</c:forEach>
				</select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="qcqiche:qcQiche:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>