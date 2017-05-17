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
    <li class="active"><a href="${ctx}/bank/finance/totalDetail">用户列表</a></li>
</ul>
<form:form id="searchForm" modelAttribute="finance" action="${ctx}/bank/finance/totalDetail" method="post" class="breadcrumb form-search">
    <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
    <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
    <label>储户姓名：</label><form:input path="userName" htmlEscape="false" maxlength="50" class="input-small"/>
    <input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>&nbsp;&nbsp;
</form:form>
<sys:message content="${message}"/>
<table id="contentTable" class="table table-striped table-bordered table-condensed">
    <thead><tr><th>姓名</th><th>身份证号</th><th>存款总额</th><th>贷款总额</th></thead>
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
            <td>${fns:abbr(user.loginName,40)}</td>
            <td>${fns:abbr(user.ckMoney,40)}</td>
            <td>${fns:abbr(user.dkMoney,40)}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<div class="pagination">${page}</div>
</body>
</html>