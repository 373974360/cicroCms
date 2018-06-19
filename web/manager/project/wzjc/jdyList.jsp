﻿<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>监督员管理</title>
<meta name="generator" content="featon-Builder" />
<meta name="author" content="featon" />
<jsp:include page="../../include/include_tools.jsp"/>
<script type="text/javascript" src="js/jdyList.js"></script>
<script type="text/javascript">
var status = "0";
$(document).ready(function(){	
	initButtomStyle();
	init_FromTabsStyle();
	if($.browser.msie&&$.browser.version=="6.0"&&$("html")[0].scrollHeight>$("html").height()) $("html").css("overflowY","scroll"); 
	
	initTable();
	reloadJdyList();
	
});

function fnPublichSearch(val)
{
	status = val;
	m.remove("keyword");
	reloadJdyList();
}


</script>
</head>

<body>
<div>
	<table class="table_option" border="0" cellpadding="0" cellspacing="0" >
		<tr>		
			<td class="fromTabs">
				审核状态：<select onchange="fnPublichSearch(this.value)"><option value="">全部</option><option value="1">已审核</option><option value="0" selected="selected">未审核</option></select>
				<!--
				租赁类型：<select onchange="fnTypeSearch(this.value)"><option value="">全部</option><option value="1">出租</option><option value="2">求租</option></select>
				-->
				<span class="blank3"></span>
			</td>
			<td align="right" valign="middle" id="dept_search" class="search_td fromTabs" >				
				<input id="searchkey" type="text" class="input_text" value=""  /><input id="btnSearch" type="button" class="btn x2" value="搜索" onclick="SearchHandl(this)"/>
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
			<input id="btn3" name="btn3" type="button" onclick="publicSelectCheckbox(table,'id','publishJdy()')" value="审核通过" />
			<input id="btn3" name="btn3" type="button" onclick="deleteRecord(table,'id','deleteJdy()');" value="删除" />
		</td>
	</tr>
   </table>	
</div>
</body>
</html>