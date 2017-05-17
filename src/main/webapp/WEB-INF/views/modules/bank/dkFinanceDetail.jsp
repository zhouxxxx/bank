<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title>贷款管理</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
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
    <li class="active"><a href="${ctx}/bank/finance/dklist">用户列表</a></li>
</ul>
<div class="control-group">
    <label class="control-label">姓名:</label>

        ${finances.userName}

</div>
<div class="control-group">
    <label class="control-label">身份证号:</label>

        ${finances.loginName}

</div>
<div class="control-group">
    <label class="control-label">银行卡号:</label>

        ${finances.bankId}

</div>
<div class="control-group">
    <label class="control-label">年龄:</label>
        ${finances.age}
</div>
<div class="control-group">
    <label class="control-label">性别:</label>

        ${finances.sex}

</div>
<div class="control-group">
    <label class="control-label">手机:</label>

        ${finances.phone}

</div>
<div class="control-group">
    <h4><c:if test="${finances.type eq '3'}">贷款明细</c:if>
        <c:if test="${finances.type eq '4'}">还款明细</c:if></h4>
</div>

<form:form id="searchForm" modelAttribute="finance" action="${ctx}/bank/finance/dkDetail" method="post" class="breadcrumb form-search">
    <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
    <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
    <input name="id" type="hidden" value="${finance.id}"/>
    <input id="beginDate"  name="startTime"  type="text" readonly="readonly" maxlength="20" class="input-medium Wdate" style="width:163px;"
           value="${finance.startTime}"
           onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/>
    　--　
    <input id="endDate" name="endTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate" style="width:163px;"
           value="${finance.endTime}"
           onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/>
    <input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>&nbsp;&nbsp;
</form:form>
<sys:message content="${message}"/>
<table id="contentTable" class="table table-striped table-bordered table-condensed">
    <thead><tr><th><c:if test="${finances.type eq '3'}">贷款</c:if>
        <c:if test="${finances.type eq '4'}">还款</c:if>流水号</th>
        <th><c:if test="${finances.type eq '3'}">贷款</c:if>
        <c:if test="${finances.type eq '4'}">还款</c:if>资金</th>
        <th><c:if test="${finances.type eq '3'}">贷款</c:if>
        <c:if test="${finances.type eq '4'}">还款</c:if>时间</th></tr></thead>
    <tbody>
    <c:if test="${page.list eq null  || page.list eq '[]'}">
        <table >
            <tr>
                <td style="text-align:center;vertical-align:middle;width:1198px;color: #333333; font: 14px/50px 微软雅黑" >
                    暂无数据
                </td>
            </tr>
        </table>
    </c:if>
    <c:forEach items="${page.list}" var="user">
        <tr>
            <td>${fns:abbr(user.serialNo,40)}</td>
            <td>${fns:abbr(user.money,40)}</td>
            <td>${fns:abbr(user.applyTime,40)}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<div class="pagination">${page}</div>
</body>
</html>