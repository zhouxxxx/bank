<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/modules/cms/front/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>${category.name}</title>
	<meta name="decorator" content="cms_default_${site.theme}"/>
	<meta name="description" content="${category.description}" />
	<meta name="keywords" content="${category.keywords}" />
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
				<h4>贷还款明细</h4>
			</div>
			<form:form id="searchForm" modelAttribute="finance" action="${ctx}/dkFinance" method="post" class="breadcrumb form-search">
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
				<thead><tr><th>贷还款流水号</th><th>贷还款类型</th><th>金额</th><th>时间</th></tr></thead>
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
						<td>${fns:abbr(user.type,40)}</td>
						<td>${fns:abbr(user.money,40)}</td>
						<td>${fns:abbr(user.applyTime,40)}</td>
					</tr>
				</c:forEach>
				</tbody>
			</table>
			<div class="pagination">${page}</div>
		</div>
			</span>
</body>
</html>