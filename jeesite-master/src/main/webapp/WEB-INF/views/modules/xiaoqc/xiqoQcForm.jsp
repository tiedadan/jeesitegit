<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>汽车之家管理</title>
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
		<li><a href="${ctx}/xiaoqc/xiqoQc/">汽车之家列表</a></li>
		<li class="active"><a href="${ctx}/xiaoqc/xiqoQc/form?id=${xiqoQc.id}">汽车之家<shiro:hasPermission name="xiaoqc:xiqoQc:edit">${not empty xiqoQc.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="xiaoqc:xiqoQc:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="xiqoQc" action="${ctx}/xiaoqc/xiqoQc/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>	
		<div class="control-group">
			<label class="control-label">上级栏目:</label>
			<div class="controls">
                <sys:treeselect id="xiqoQc" name="parent.id" value="${xiqoQc.parent.id}" labelName="parent.name" labelValue="${xiqoQc.parent.name}"
					title="汽车" url="/xiaoqc/xiqoQc/treeData" extId="${xiqoQc.id}" cssClass="required"/>
			</div>
		</div>	
		<div class="control-group">
			<label class="control-label">name：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">description：</label>
			<div class="controls">
				<form:textarea path="description" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="xiaoqc:xiqoQc:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>