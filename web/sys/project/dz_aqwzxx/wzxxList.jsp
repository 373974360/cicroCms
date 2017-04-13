<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>报名网站信息管理</title>
<meta name="generator" content="deya-Builder" />
<meta name="author" content="deya" />
<jsp:include page="../../include/include_tools.jsp"/>
<script type="text/javascript" src="js/wzxxList.js"></script>
<script type="text/javascript" src="/manager/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="/manager/js/kindeditor/kindeditor.js"></script>
<script type="text/javascript">
var id = request.getParameter("id");
var publish_flag = "0";
$(document).ready(function(){	
	initButtomStyle();
	init_FromTabsStyle();
	if($.browser.msie&&$.browser.version=="6.0"&&$("html")[0].scrollHeight>$("html").height()) $("html").css("overflowY","scroll"); 

	initTable();
	reloadWzxxList();	

	
});

function fnPublichSearch(val)
{
	publish_flag = val;
	m.remove("keyword");
	reloadWzxxList();
}

</script>
</head>

<body>
<div>
	<table class="table_option" border="0" cellpadding="0" cellspacing="0" >
		<tr>		
			<td class="fromTabs">
				<span class="blank5"></span>
				<!--
				租赁类型：<select onchange="fnTypeSearch(this.value)"><option value="">全部</option><option value="1">出租</option><option value="2">求租</option></select>
				-->
				<span class="blank3"></span>
			</td>
			<td align="right" valign="middle" id="dept_search" class="search_td fromTabs" >				
				<input id="searchkey" type="text" class="input_text" value=""  />
				<input id="btnSearch" type="button" class="btn x2" value="搜索" onclick="SearchHandl(this)"/>
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
			<input id="btn3" name="btn3" type="button" onclick="deleteRecord(table,'id','deleteWzxx()');" value="删除" />
		</td>
	</tr>
   </table>	
</div>
</body>
</html>