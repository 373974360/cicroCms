<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String sub_id = request.getParameter("id");
	String old_mailAddress = request.getParameter("mail_Address");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base>
<title>UpdateSubscribeInfo</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">    
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
<link rel="stylesheet" type="text/css" href="styles.css">
-->
</head>
<jsp:include page="include/include_info_tools.jsp"/>
<script type="text/javascript" src="js/subscribeList.js"></script>
<script type="text/javascript">
var sub_id="<%= sub_id%>";
var update_email = "";
var m = new Map();
var SubscribeRPC = jsonrpc.SubscribeRPC;
function checkUpdateEmail()
{
	update_email = $("#update_email").val();
	
	if(update_email.replace(/\ /g,"") == ""  || update_email == "null"){
		top.msgAlert("邮箱不能为空");
		return;
	}else{
		var registerMail = /^[-_0-9A-Za-z]+@([-_0-9a-zA-Z]+\.)([0-9A-Za-z]{2,4})$/;
		if(!registerMail.test(update_email)){
			alert("邮箱格式有误");
			return;
		}
	}
	saveUpdateInfo(sub_id);
}

function saveUpdateInfo(sub_id)
{
	m.put("id",sub_id);
	m.put("mail_address",update_email);

	top.jsonrpc.SubscribeRPC.checkSameEmailAddress(checkSameEmailAddressResult,m);
}

function checkSameEmailAddressResult(result,e)
{
    if(e != null){return;}
    if(!result)
	{
	   if(SubscribeRPC.updateSubscribeUserInfo(m))
		{
			top.msgAlert("修改成功");
			top.getCurrentFrameObj().reloadSubscribeList();
			top.CloseModalWindow();
		}else{
			top.msgAlert("修改失败");
		}
	}else{
	   top.msgAlert("邮箱已存在");
	}
}
	  	
</script>
  
  <body>
  <span class="blank12"></span>
    <table id="add_table" class="table_form" border="0" cellpadding="0" cellspacing="0">
		<tbody>
			<tr>
				<th><span class="f_red">*</span>订阅邮件:</th>
				<td >
					<input id="update_email" name="update_email" type="text" size="30" value="<%= old_mailAddress%>"/>
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
				<input id="addButton" name="btn1" type="button" onclick="checkUpdateEmail()" value="保存" />	
				<input id="btnCancel" name="btn1" type="button" onclick="top.CloseModalWindow();" value="取消" />	
			</td>
		</tr>
	</table>
	<span class="blank3"></span>
  </body>
</html>
