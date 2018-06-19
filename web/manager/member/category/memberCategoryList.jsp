<%@ page contentType="text/html; charset=utf-8"%>
<%
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>会员分类管理</title>


<jsp:include page="../../include/include_tools.jsp" />

<script type="text/javascript" src="../../js/jquery-easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="../../js/indexjs/tools.js"></script>
<script type="text/javascript" src="js/memberCategoryList.js"></script>
<script type="text/javascript">

$(document).ready(function(){	
	initButtomStyle();
	init_FromTabsStyle();
	if($.browser.msie&&$.browser.version=="6.0"&&$("html")[0].scrollHeight>$("html").height()) $("html").css("overflowY","scroll"); 

	initTable();
	reloadCategoryList();
	
});

</script>
</head>

<body>
<div>
<table class="table_option" border="0" cellpadding="0" cellspacing="0" >	
	<tr>
			<td align="left" valign="middle" class="search_td fromTabs">
				<input id="btn1" name="btn1" type="button" onclick="addMemberCategory()" value="新建会员分类" />
				<input id="btn2" name="btn4" type="button" onclick="updateRecord( table, 'mcat_id', 'updateMemberCategory()')" value="修改" />
				<input id="btn3" name="btn3" type="button" onclick="sortRecord(table,'mcat_id','sortMemberCategorySort()');" value="保存排序" />
				<input id="btn4" name="btn4" type="button" onclick="deleteRecord( table, 'mcat_id', 'deleteMemberCategory()')" value="删除" />
				<span class="blank3"></span>
			</td>
		</tr>
</table>
<span class="blank3"></span>

<div id="table"></div>
<div id="turn"></div>
<table class="table_option" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td align="left" valign="middle">
				<input id="btn1" name="btn1" type="button" onclick="addMemberCategory()" value="新建会员分类" />
				<input id="btn2" name="btn4" type="button" onclick="updateRecord( table, 'mcat_id', 'updateMemberCategory()')" value="修改" />
				<input id="btn3" name="btn3" type="button" onclick="sortRecord(table,'mcat_id','sortMemberCategorySort()');" value="保存排序" />
				<input id="btn4" name="btn4" type="button" onclick="deleteRecord( table, 'mcat_id', 'deleteMemberCategory()')" value="删除" />
			</td>
		</tr>
	</table>
</div>
</body>
</html>