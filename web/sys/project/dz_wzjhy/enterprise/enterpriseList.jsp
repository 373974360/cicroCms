<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>企业管理</title>
<meta name="generator" content="deya-Builder" />
<meta name="author" content="deya" />
<jsp:include page="../../../include/include_tools.jsp"/>
<script type="text/javascript" src="js/enterpriseList.js"></script>
<script type="text/javascript">

$(document).ready(function(){	
	initButtomStyle();
	init_FromTabsStyle();
	if($.browser.msie&&$.browser.version=="6.0"&&$("html")[0].scrollHeight>$("html").height()) $("html").css("overflowY","scroll"); 

	initTable();
	reloadList();	
	
});


function changePublishStatus(val)
{
	if(val == "")
		m.remove("is_audit");	
	else
		m.put("is_audit",val);	
	reloadList();
}
</script>
</head>

<body>
<div>
	 <table class="table_option" border="0" cellpadding="0" cellspacing="0" >
		<tr>		
			<td class="fromTabs">
				<select onchange="changePublishStatus(this.value)">
					<option value="">全部</option>
					<option value="1">审核已通过</option>
					<option value="0">待审核</option>
					<option value="-1">审核未通过</option>
				</select>
				<span class="blank3"></span>
			</td>
			<td align="right" valign="middle" id="dept_search" class="search_td fromTabs" >				
				<span class="blank3"></span>
			</td>		
		</tr>
	</table> 
	<span class="blank3"></span>
	<div id="table"></div><!-- 列表DIV -->
	<div id="turn"></div><!-- 翻页DIV -->
	<table class="table_option" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td align="left" valign="middle">			
			<input id="btn3" name="btn3" type="button" onclick="publicSelectCheckbox(table,'id','auditEnterprise(1)');" value="审核通过" />
			<input id="btn3" name="btn3" type="button" onclick="publicSelectCheckbox(table,'id','auditEnterprise(-1)');" value="审核不通过" />
			<input id="btn3" name="btn3" type="button" onclick="deleteRecord(table,'id','deleteEnterpriseBean()');" value="删除" />		
		</td>
	</tr>
   </table>	
</div>
</body>
</html>