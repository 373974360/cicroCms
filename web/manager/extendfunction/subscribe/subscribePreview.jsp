<%@ page contentType="text/html; charset=utf-8"%>
<%
	String recordid = request.getParameter("record_id");
	String siteid = request.getParameter("site_id");
	String app_id = request.getParameter("app_id");
	String send_title = request.getParameter("sendTitle");
	send_title = new String(send_title.getBytes("ISO-8859-1"),"utf-8");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=8" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>选择用户</title>
<jsp:include page="include/include_info_tools.jsp"/>
<script type="text/javascript" src="js/subscribePreview.js"></script>
<script type="text/javascript" src="js/public.js"></script>
<script type="text/javascript">
var record_id = "<%=recordid%>";
var site_id = "<%=siteid%>";
var app_id = "<%=app_id%>";
var sendTitle = "<%=send_title%>";
$(document).ready(function(){
	initButtomStyle();
	init_FromTabsStyle();
	if($.browser.msie&&$.browser.version=="6.0"&&$("html")[0].scrollHeight>$("html").height()) $("html").css("overflowY","scroll"); 
	initTable();
});

</script>
</head>
<body>
	<span class="blank3"></span>
	<div id="preview_table">
	</div>
	<div id="subscribe_turn"></div>
	<span class="blank12"></span>
	<div class="line2h"></div>
	<span class="blank3"></span>
	<div>
		<table class="table_option" border="0" cellpadding="0" cellspacing="0" >
			<tr>	
				<td align="left" valign="middle" id="dept_search" class="search_td" >
					<input type="button" value="追加" class="btn1" onclick="insertaddSendInfo()" />
					<input type="button" value="关闭" class="btn1" onclick="closePreview()" />
				</td>		
			</tr>
		</table>
	</div>
</body>
</html>