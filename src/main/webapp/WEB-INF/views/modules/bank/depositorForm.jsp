<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>储户管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		//姓名验证
		jQuery.validator.addMethod("logiName", function(value, element) {
			var tel = /^([\u4e00-\u9fa5]{1,20}|[a-zA-Z\.\s]{1,20})$/;
			return this.optional(element) || (tel.test(value));
		}, "请输入正确的姓名");
		//姓名验证
		jQuery.validator.addMethod("bank", function(value, element) {
			var tel = /^\d{16}|\d{19}$/;
			return this.optional(element) || (tel.test(value));
		}, "请输入正确的银行卡格式");

		$(document).ready(function() {
			$("#no").focus();
			$("#inputForm").validate({
				rules: {
                    loginName:{
                        remote:ctx+"/bank/user/check?loginName=" + $("#loginName").val()
                    }
				},
				messages: {
					loginName: {remote: "用户登录名已存在"
                    },
					confirmNewPassword: {equalTo: "输入与上面相同的密码"}
				},
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
			$("#age").on('blur', function () {
				var age = $("#age").val();
				var id = $("#loginName").val();
				var year = id.substring(6,10);
				var date=new Date;
				var year1=date.getFullYear();
				if(age != '' && id != ''){
					if((year1 - year) != age){
						$.jBox.tip("年龄不正确！","error");
					}
				}
			});
		});

	</script>
</head>
<body>
<ul class="nav nav-tabs">
	<li><a href="${ctx}/bank/user/list">储户列表</a></li>
	<li class="active"><a href="${ctx}/bank/user/form?id=${user.id}">储户${not empty user.id?'修改':'添加'}</a></li>
</ul><br/>
<form:form id="inputForm" modelAttribute="user" action="${ctx}/bank/user/save" method="post" class="form-horizontal">
	<form:hidden path="id"/>
	<sys:message content="${message}"/>
	<div class="control-group">
		<label class="control-label">姓名:</label>
		<div class="controls">
			<form:input path="name" htmlEscape="false" maxlength="50" class="required logiName"/>
			<span class="help-inline"><font color="red">*</font> </span>
		</div>
	</div>
	<div class="control-group">
		<label class="control-label">身份证号:</label>
		<div class="controls">
			<input id="oldLoginName" name="oldLoginName" type="hidden" value="${user.loginName}">
			<input id="loginName" name="loginName" value="${user.loginName}" htmlEscape="false" maxlength="50" class="required card"
				  <c:if test="${not empty user.id}">disabled="true"</c:if>/>
			<span class="help-inline"><font color="red">*</font> </span>
		</div>
	</div>
	<div class="control-group">
		<label class="control-label">银行卡号:</label>
		<div class="controls">
			<input name="bankId" value="${user.bankId}" htmlEscape="false" maxlength="50" class="required bank"
				   <c:if test="${not empty user.id}">disabled="true"</c:if>/>
			<span class="help-inline"><font color="red">*</font> </span>
		</div>
	</div>
	<div class="control-group">
		<label class="control-label">年龄:</label>
		<div class="controls">
			<form:input path="age" htmlEscape="false" maxlength="50" class="required"/>
			<span class="help-inline"><font color="red">*</font> </span>
		</div>
	</div>
	<div class="control-group">
		<label class="control-label">性别:</label>
		<div class="controls">
			<form:select path="sex" class="input-large required">
				<form:option value="" label="请选择"/>
				<form:options items="${fns:getDictList('sex')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
			</form:select>
			<span class="help-inline"><font color="red">*</font> </span>
		</div>
	</div>
	<div class="control-group">
		<label class="control-label">密码:</label>
		<div class="controls">
			<input id="newPassword" name="newPassword" type="password" value="" maxlength="50" minlength="3" class="${empty user.id?'required':''}"/>
			<c:if test="${empty user.id}"><span class="help-inline"><font color="red">*</font> </span></c:if>
			<c:if test="${not empty user.id}"><span class="help-inline">若不修改密码，请留空。</span></c:if>
		</div>
	</div>
	<div class="control-group">
		<label class="control-label">确认密码:</label>
		<div class="controls">
			<input id="confirmNewPassword" name="confirmNewPassword" type="password" value="" maxlength="50" minlength="3"  equalTo="#newPassword"/>
			<c:if test="${empty user.id}"><span class="help-inline"><font color="red">*</font> </span></c:if>
		</div>
	</div>
	<%--<div class="control-group">
		<label class="control-label">邮箱:</label>
		<div class="controls">
			<form:input path="email" htmlEscape="false" maxlength="100" class="email"/>
		</div>
	</div>--%>
	<div class="control-group">
		<label class="control-label">手机:</label>
		<div class="controls">
			<form:input path="mobile" htmlEscape="false" maxlength="100" cssClass="required phone"/>
		</div>
	</div><%--
	<div class="control-group">
		<label class="control-label">备注:</label>
		<div class="controls">
			<form:textarea path="remarks" htmlEscape="false" rows="3" maxlength="200" class="input-xlarge"/>
		</div>
	</div>--%>
	<div class="form-actions">
		<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
		<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
	</div>
</form:form>
</body>
</html>