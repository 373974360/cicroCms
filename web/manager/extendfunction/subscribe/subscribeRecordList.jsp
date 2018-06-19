<%@ page contentType="text/html; charset=utf-8" language="java" %>
<%
String siteid = request.getParameter("site_id");
String app_id = request.getParameter("app_id");
if(siteid == null || siteid.equals("null")){
	siteid = "GK";
}
if(app_id == null || app_id.trim().equals("")){
	app_id = "cms";
}
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//CN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>订阅用户管理</title>
<meta name="generator" content="cicro-Builder" />
<meta name="author" content="cicro" />
<jsp:include page="include/include_info_tools.jsp"/>
<script type="text/javascript" src="../../js/jquery-easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="../../js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="js/subscribeRecordList.js"></script>
<script type="text/javascript">

var site_id = "<%=siteid%>";
var app_id = "<%=app_id%>";

$(document).ready(function(){

	initButtomStyle();
	init_FromTabsStyle();
	if($.browser.msie&&$.browser.version=="6.0"&&$("html")[0].scrollHeight>$("html").height()) $("html").css("overflowY","scroll"); 
	
	initTable();
	reloadSubRecordList();
});

</script>
</head>
<body>
	<div>
		<table class="table_option" border="0" cellpadding="0" cellspacing="0" > 
			<tr>
				<td align="left" valign="middle"><span class="blank3"></span></td>
				<td align="right" valign="middle" id="user_search" class="search_td">
					<label for="self">时间:</label>
					<input type="text" id="i_time_s" name="i_time_s" size="11" class="input_text" value="" onFocus="WdatePicker({isShowClear:true,readOnly:true,dateFmt:'yyyy-MM-dd'})" readonly="true" />至&nbsp;
					<input type="text" id="i_time_e" name="i_time_e" size="11" class="input_text" value="" onFocus="WdatePicker({isShowClear:true,readOnly:true,dateFmt:'yyyy-MM-dd'})" readonly="true" />
					<input id="btn" type="button" value="搜索" onclick="searchSubscribeInfo() "/>
					<select id="orderByFields" class="input_select"  onchange="changeTimeSort(this.value)"> 
						<option selected="selected" value="1">时间倒序</option> 
						<option value="2">时间正序</option> 
					</select> 
				</td>
			</tr>
		</table> 
	</div>
	<div id="subRecord_table"></div>
	<div id="subRecord_turn"></div>
	<div>
		<table class="table_option" border="0" cellpadding="0" cellspacing="0" >
			<tr>	
				<td align="left" valign="middle" id="dept_search" class="search_td" >
					<input id="btnSubscribeSendInfo" 
						name="btnSubscribeSendInfo" type="button" onclick="getSubscribeSendInfo()" value="获取" />
				</td>		
			</tr>
		</table>
	</div>
</body>
</html>