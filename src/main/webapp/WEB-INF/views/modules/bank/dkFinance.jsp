<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>账户管理</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
        $(document).ready(function () {
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

            var type = '${finance.type}';
                $("#inputForm").validate({
                    rules: {
                        money: {
                            required: true,
                            remote: ctx + "/bank/finance/checkDkMoney?userId="+$("#userId").val()
                            }
                        },
                        messages: {
                            money: {remote: "贷款没有付清，不能继续贷款！"}
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
<ul class="nav nav-tabs">
    <li class="active"><a href="${ctx}/bank/finance/dklist">用户列表</a></li>
</ul>
<h1 style="margin-left: 250px"><c:if test="${finance.type eq '3'}">贷款</c:if>
    <c:if test="${finance.type eq '4'}">还款</c:if></h1>
<form:form id="inputForm" modelAttribute="financeDetail" action="${ctx}/bank/finance/dkSave" method="post" class="form-horizontal">
    <input  id="userId" name="userId" type="hidden" value="${finance.id}">
    <input  id="type" name="type" type="hidden" value="${finance.type}">
    <sys:message content="${message}"/>
    <div class="control-group">
        <label class="control-label">姓名:</label>
        <div class="controls">
            <input name="bankId" value="${finance.userName}" htmlEscape="false" maxlength="50" class="required bank"
                   disabled="true"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">银行卡号:</label>
        <div class="controls">
            <input name="bankId" value="${finance.bankId}" htmlEscape="false" maxlength="50" class="required bank"
                   disabled="true"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label"><c:if test="${finance.type eq '3'}">贷款金额:</c:if>
            <c:if test="${finance.type eq '4'}">还款金额:</c:if></label>
        <div class="controls">
            <input name="money" id="money"  htmlEscape="false" maxlength="50" class="required"/>
        </div>
        <span class="help-inline"><font color="red" STYLE="margin-left: 180px">已贷款金额:${finance.dkMoney}
             已还款金额:${finance.hkMoney}</font> </span>
    </div>
    <div class="form-actions">
        <input id="btnSubmit" class="btn btn-primary" type="submit" value="<c:if test="${finance.type eq '3'}">贷 款</c:if>
<c:if test="${finance.type eq '4'}">还 款</c:if>"/>&nbsp;
        <input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
    </div>
</form:form>
</body>
</html>