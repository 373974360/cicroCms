<%@ page contentType="text/html; charset=utf-8"%>
<%
	String sq_id = request.getParameter("sq_id");
	String top_index = request.getParameter("top_index");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=8" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>修改回复时间</title>


<link rel="stylesheet" type="text/css" href="../../styles/themes/default/tree.css">
<link type="text/css" rel="stylesheet" href="../../styles/sq.css" />
<link href="../../js/My97DatePicker/skin/WdatePicker.css" rel="stylesheet" type="text/css">
<jsp:include page="../../include/include_tools.jsp"/>
<script src="../../js/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
<script type="text/javascript">

var sq_id = "<%=sq_id%>";
var top_index = "<%=top_index%>";

$(document).ready(function(){
	initButtomStyle();
	init_input();
	defaultBean = top.getCurrentFrameObj(top_index).defaultBean;
	if(defaultBean)
	{
		$("#oldOver_dtime").val(defaultBean.over_dtime);
	}	
});

function changeOverDtime()
{
	if($("#newOver_dtime").val() == "")
	{
		top.msgWargin("回复时间不能为空！");
		return;
	}
	var sq_map = new Map();
	sq_map.put("oldOver_dtime",$("#oldOver_dtime").val());
	sq_map.put("newOver_dtime",$("#newOver_dtime").val());
	sq_map.put("sq_id",sq_id);
	sq_map.put("model_id",defaultBean.model_id);
	//sq_map.put("sq_status",sq_id);
	//sq_map.put("supervise_flag",sq_id);
	//sq_map.put("publish_status",sq_id);
	
	var SQRPC = jsonrpc.SQRPC;
	try{
		SQRPC.updateStatus(sq_map);
		top.msgAlert("回复时间修改成功！");
		top.CloseModalWindow();
	}
	catch(e){
		top.msgAlert("回复时间修改成功！");
		top.CloseModalWindow();
	}
}

</script>
</head>

<body>
<span class="blank12"></span>
<form id="form1" name="form1" action="#" method="post">
<table id="changeDtime_table" class="table_form" border="0" cellpadding="0" cellspacing="0">
	<tbody>
		<tr>
			<th><span class="f_red">*</span>回复时间：</th>
			<td >
				<input type="text" readonly="" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true,readOnly:true})" name="newOver_dtime" id="newOver_dtime" class="input_text input_text_focus">
				<input type="hidden" id="oldOver_dtime" name="oldOver_dtime" value=""/>
			</td>
		</tr>
	</tbody>
</table>
<span class="blank12"></span>
<div class="line2h"></div>
<span class="blank3"></span>
<span class="blank12"></span>
<table class="table_option" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td align="left" valign="middle" style="text-indent:100px;">
			<input id="addButton" name="btn1" type="button" onclick="changeOverDtime()" value="保存" />	
			<input id="userAddCancel" name="btn1" type="button" onclick="top.CloseModalWindow();" value="取消" />
		</td>
	</tr>
</table>
<span class="blank3"></span>
</form>
</body>
</html>
