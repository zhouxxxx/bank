<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/modules/cms/front/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>${category.name}</title>
	<meta name="decorator" content="cms_default_${site.theme}"/>
	<meta name="description" content="${category.description}" />
	<meta name="keywords" content="${category.keywords}" />
	<script type="text/javascript">
		//姓名验证
		jQuery.validator.addMethod("logiName", function(value, element) {
			var tel = /^([\u4e00-\u9fa5]{1,20}|[a-zA-Z\.\s]{1,20})$/;
			return this.optional(element) || (tel.test(value));
		}, "请输入正确的姓名");

		$("#money").on('keydown', function (event) {
			event = window.event || event;
			if((event.keyCode < 48 && event.keyCode != 8 ) || (event.keyCode > 57 && event.keyCode < 96) || (event.keyCode > 105 &&  event.keyCode != 110 && event.keyCode != 190)){
				return false
			}
		});
		$("#money").on('keyup', function (event) {
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
		$("#money").on('blur', function () {
			var $amountInput = $(this);
			//最后一位是小数点的话，移除
			$amountInput.val(($amountInput.val().replace(/\.$/g, "")));
		});

		$(function () {
			$("#inputForm").validate({
				rules: {
					bankId: {
						required: true,
						remote: "/a/bank/finance/checkBankId?bankId="+$("#bankId").val()
					},
					userName: {
						required:true,
						remote: {
							url: "/a/bank/finance/checkUserId.ajax",     //后台处理程序
							type: "post",               //数据发送方式
							data: {                     //要传递的数据
								bankId: function() {
									return $("#bankId").val();
								},
								name: function() {
									return $("#userName").val();
								}
							}
						}
					},
					money: {
						required: true,
						remote: "/a/bank/finance/checkMoney?userId="+$("#userId").val()
					}
				},
				messages: {
					bankId: {remote: "该卡号不存在！"},
					userName: {remote: "持卡人姓名不正确！"},
					money: {remote: "转账金额大于剩余金额！"}
				},
				submitHandler: function (form) {
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function (error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox") || element.is(":radio") || element.parent().is(".input-append")) {
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
	<div class="row">
	   <div class="span2">
	   	 <h4>栏目列表</h4>
		 <ol>
		 	<cms:frontCategoryList categoryList="${categoryList}"/>
		 </ol>
	   </div>
		<span class="span10">
		<div style="padding:0 0 20px;">
			<div class="control-group">
				<h4>转账</h4>
			</div>
			<form:form id="inputForm" modelAttribute="financeDetail" action="${ctx}/saveToperson" method="post" class="form-horizontal">
				<input  id="userId" name="userId" type="hidden" value="${finacne.id}">
				<sys:message content="${message}"/>
				<div class="control-group">
        <label class="control-label">银行卡号:</label>
        <div class="controls">
            <input name="bankId"  id="bankId" htmlEscape="false" maxlength="50" class="required bank"
				  />
        </div>
    </div>
				<div class="control-group">
        <label class="control-label">姓名:</label>
        <div class="controls">
            <input name="userName" id="userName"  htmlEscape="false" maxlength="50" class="required logiName"
				 />
        </div>
       </div>
				<div class="control-group">
        <label class="control-label">转账金额</label>
        <div class="controls">
            <input name="money" id="money"  htmlEscape="false" maxlength="50" class="required"/>
        </div>
        <span class="help-inline"><font color="red" STYLE="margin-left: 180px">卡上余额:${finacne.money}</font> </span>
    </div>
				<div class="form-actions">
        <input id="btnSubmit" class="btn btn-primary" type="submit" value="转 账"/>&nbsp;
        <input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
    </div>
			</form:form>
		</div>
			</span>
	</div>
</body>
</html>