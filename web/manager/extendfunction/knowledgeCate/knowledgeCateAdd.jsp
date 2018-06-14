<%@ page contentType="text/html; charset=utf-8"%>
<%
	String type = request.getParameter("type");
	String id = request.getParameter("id");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>知识库标签分类</title>
<meta name="generator" content="cicro-Builder"/>
<meta name="author" content="cicro"/>
<link rel="stylesheet" type="text/css" href="../../styles/themes/default/tree.css">
<jsp:include page="../../include/include_tools.jsp"/>
<script type="text/javascript" src="../../js/jquery-easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="../../js/indexjs/tools.js"></script>
<script type="text/javascript" src="knowledgeCateList.js"></script>
<script type="text/javascript">
	var type = "<%=type%>";
	var id = "<%=id%>";
	
	var val = new Validator();
	var defaultBean;
	$(document).ready(function(){
		initButtomStyle();
		init_FromTabsStyle();
		init_input();
		if($.browser.msie&&$.browser.version=="6.0"&&$("html")[0].scrollHeight>$("html").height()) $("html").css("overflowY","scroll"); 
		
		initPage();
	});
	
	function initPage()
	{
		if(type == "update")
		{
			defaultBean = KnowledgeRPC.getWareCategoryByID(id);
			$("#Cate_table").autoFill(defaultBean);
			$("#addButton").click(saveUpdateWareCategory);
		}
		else
		{
			$("#addButton").click(saveAddWareCategory);
		}
	}
</script>
</head>
<body>
<span class="blank12"></span>
<form id="form1" name="form1" action="#" method="post">
<table id="Cate_table" class="table_form" border="0" cellpadding="0" cellspacing="0">
	<tbody>
		<tr>
			<th><span class="f_red">*</span>名称:</th>
			<td>
				<input id="kcat_name" name="kcat_name" 
					type="text" class="width300" value="" onblur="checkInputValue('kcat_name',false,80,'名称','')"/>
			</td>			
		</tr>
		<tr>
			<th style="vertical-align:top;">描述:</th>
			<td>
				<textarea id="kcat_memo" name="kcat_memo" 
					style="width:300px;;height:80px;" onblur="checkInputValue('kcat_memo',true,900,'描述','')"></textarea>		
			</td>
		</tr>
	</tbody>
</table>
<span class="blank12"></span>
<div class="line2h"></div>
<span class="blank3"></span>
<table class="table_option" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td align="left" valign="middle" style="text-indent:100px;">
			<input id="addButton" name="btn1" type="button" onclick="" value="保存"/>	
			<input id="addReset" name="btn1" type="button" onclick="formReSet('Cate_table',kcat_name)" value="重置"/>	
			<input id="addCancel" name="btn1" type="button" onclick="top.CloseModalWindow();" value="取消"/>	
		</td>
	</tr>
</table>
<span class="blank3"></span>
</form>
</body>
</html>