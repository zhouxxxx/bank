<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title>财务管理</title>
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
    <li class="active"><a href="${ctx}/bank/finance/list">用户列表</a></li>
</ul>
<form:form id="searchForm" modelAttribute="finance" action="${ctx}/bank/finance/list" method="post" class="breadcrumb form-search">
    <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
    <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
    <label>储户姓名：</label><form:input path="userName" htmlEscape="false" maxlength="50" class="input-small"/>&nbsp;
    <label>银行卡号：</label><form:input path="bankId" htmlEscape="false" maxlength="50" class="input-small"/>
    <input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>&nbsp;&nbsp;
</form:form>
<sys:message content="${message}"/>
<table id="contentTable" class="table table-striped table-bordered table-condensed">
    <thead><tr><th>姓名</th><th>银行卡号</th><th>手机号</th><th>性别</th><th>年龄</th><th>卡上余额</th><th>最近操作日期</th><th>操作</th></tr></thead>
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
            <td>${fns:abbr(user.userName,40)}</td>
            <td>${fns:abbr(user.bankId,40)}</td>
            <td>${fns:abbr(user.phone,40)}</td>
            <td>${fns:abbr(user.sex,40)}</td>
            <td>${fns:abbr(user.age,40)}</td>
            <td>${user.money}</td>
            <td>${user.time}</td>
            <td>
                <a href="${ctx}/bank/finance/drad?type=1&&id=${user.id}">存款</a>
                <a href="${ctx}/bank/finance/drad?type=2&&id=${user.id}">取款</a>
                <a href="${ctx}/bank/finance/detail?type=1&&id=${user.id}">存款明细</a>
                <a href="${ctx}/bank/finance/detail?type=2&&id=${user.id}">取款明细</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<div class="pagination">${page}</div>
</body>
</html>