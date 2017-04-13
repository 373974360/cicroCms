<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>短信维护</title>
<meta name="generator" content="deya-Builder" />
<meta name="author" content="deya" />
<jsp:include page="../../../include/include_tools.jsp"/>

<script type="text/javascript" src="js/jobsList.js"></script>
<script type="text/javascript">
var topnum = "${param.topnum}";
var id = "${param.id}";

$(document).ready(function(){
	

	initButtomStyle();
	init_input();
	
	if(id != "" && id != null)
	{
		var nm = new Map();
		nm.put("id",id);
		var defaultBean = WZJJobsRPC.getJobsBean(nm);
		if(defaultBean)
		{
			$("#sms_table").autoFill(defaultBean);				
		}
	}
});

function publishJobsBean2(flag)
{		
	var delMap = new Map();
	delMap.put("id",id);
	delMap.put("is_publish",flag+"");
	
	if(WZJJobsRPC.publishJobsBean(delMap))
	{
		top.msgAlert("发布状态"+WCMLang.Set_success);
		top.getCurrentFrameObj(topnum).reloadList();
		top.tab_colseOnclick(top.curTabIndex);
	}else
	{
		top.msgWargin("发布状态"+WCMLang.Set_fail);
	}	
}

</script>
</head>

<body>
<span class="blank12"></span>
<form id="form1" name="form1" action="#" method="post">
<table id="sms_table" class="table_form" border="0" cellpadding="0" cellspacing="0">
	<tbody>
		<tr>
			<th>学历要求：</th>
			<td id="xueli"></td>
		</tr>			
		<tr>
			<th>年龄：</th>
			<td id="nn"></td>
		</tr>
		<tr>
			<th>人数：</th>
			<td id="renshu"></td>
		</tr>
		<tr>
			<th>其它要求：</th>
			<td id="qitayaoqiu"></td>
		</tr>
		<tr>
			<th>有效期限：</th>
			<td id="youxiaoqi"></td>
		</tr>		
		<tr>
			<th>岗位职责：</th>
			<td id="gangweizize"></td>
		</tr>
		<tr>
			<th>岗位要求：</th>
			<td id="gangweiyaoqiu"></td>
		</tr>
		
	</tbody>
</table>
<span class="blank12"></span>
<div class="line2h"></div>
<span class="blank3"></span>
<table class="table_option" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td align="left" valign="middle" style="text-indent:100px;">
			<input id="userAddCancel" name="btn1" type="button" onclick="publishJobsBean2(1);" value="发布" />
			<input id="userAddCancel" name="btn1" type="button" onclick="publishJobsBean2(0);" value="撤消发布" />
			<input id="userAddCancel" name="btn1" type="button" onclick="top.tab_colseOnclick(top.curTabIndex);" value="关闭" />	
		</td>
	</tr>
</table>
<span class="blank3"></span>
</form>
</body>
</html>
