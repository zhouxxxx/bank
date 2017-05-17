<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>设置利率</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$("#dkRate").on('keydown', function (event) {
			event = window.event || event;
			if((event.keyCode < 48 && event.keyCode != 8 ) || (event.keyCode > 57 && event.keyCode < 96) || (event.keyCode > 105 &&  event.keyCode != 110 && event.keyCode != 190)){
				return false
			}
		});
		$("#dkRate").on('keyup', function (event) {
			var $amountInput = $(this);
			//响应鼠标事件，允许左右方向键移动
			event = window.event || event;
			//先把非数字的都替换掉，除了数字和.
			$amountInput.val($amountInput.val().replace(/[^\d.]/g, "").
			//只允许一个小数点
			replace(/^\./g, "").replace(/\.{2,}/g, ".").
			//只能输入小数点后两位
			replace(".", "$#$").replace(/\./g, "").replace("$#$", ".").replace(/^(\-)*(\d+)\.(\d\d).*$/, '$1$2.$3'));
		});
		$("#dkRate").on('blur', function () {
			var $amountInput = $(this);
			//最后一位是小数点的话，移除
			$amountInput.val(($amountInput.val().replace(/\.$/g, "")));
		});

		$("#ckRate").on('keydown', function (event) {
			event = window.event || event;
			if((event.keyCode < 48 && event.keyCode != 8 ) || (event.keyCode > 57 && event.keyCode < 96) || (event.keyCode > 105 &&  event.keyCode != 110 && event.keyCode != 190)){
				return false
			}
		});
		$("#ckRate").on('keyup', function (event) {
			var $amountInput = $(this);
			//响应鼠标事件，允许左右方向键移动
			event = window.event || event;
			//先把非数字的都替换掉，除了数字和.
			$amountInput.val($amountInput.val().replace(/[^\d.]/g, "").
			//只允许一个小数点
			replace(/^\./g, "").replace(/\.{2,}/g, ".").
			//只能输入小数点后两位
			replace(".", "$#$").replace(/\./g, "").replace("$#$", ".").replace(/^(\-)*(\d+)\.(\d\d).*$/, '$1$2.$3'));
		});
		$("#ckRate").on('blur', function () {
			var $amountInput = $(this);
			//最后一位是小数点的话，移除
			$amountInput.val(($amountInput.val().replace(/\.$/g, "")));
		});
		function scoreST(obj) {
			// 先把非数字的都替换掉，除了数字-和.
			obj.value = obj.value.replace(/[^\d.]/g, "");
			// 必须保证第一个为数字而不是.
			obj.value = obj.value.replace(/^\./g, "");

			/*// 如果第一个为0，之后不能为连续的0
			 obj.value = obj.value.replace(/^(0|[1-9]\d{0,9})$/g, "");*/

			// 保证只有出现一个.而没有多个.
			obj.value = obj.value.replace(/\.{2,}/g, ".");
			// 保证.只出现一次，而不能出现两次以上
			obj.value = obj.value.replace(".", "$#$").replace(/\./g, "").replace("$#$",
					".");
			// 只能输入两位小数点
			obj.value = (obj.value.match(/\d+(\.\d{0,2})?/) || [''])[0];

			var num = parseFloat(obj.value);
			if (num > 100) {
				obj.value = 100;
			}
	</script>
</head>
<body>
<ul class="nav nav-tabs">
	<li class="active"><a href="${ctx}/bank/finance/settingRate">设置利率</a></li>
</ul><br/>
<form:form id="inputForm" modelAttribute="rate" action="${ctx}/bank/finance/saveRate" method="post" class="form-horizontal">
	<input name="id" type="hidden" value="${rate.id}">
	<sys:message content="${message}"/>
	<div class="control-group">
		<label class="control-label">存款利率</label>
		<div class="controls">
			<input name="ckRate" id="ckRate" value="${rate.ckRate}"  onclick="scoreST(this)"  htmlEscape="false" maxlength="50" class="required"/>
			<span class="help-inline"><font color="red">*</font> </span>
		</div>
	</div>
	<div class="control-group">
		<label class="control-label">贷款利率</label>
		<div class="controls">
			<input name="dkRate" id="dkRate"  value="${rate.dkRate}"  onclick="scoreST(this)" htmlEscape="false" maxlength="50" class="required"/>
			<span class="help-inline"><font color="red">*</font> </span>
		</div>
	</div>
	<div class="form-actions">
		<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
		<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
	</div>
</form:form>
</body>
</html>