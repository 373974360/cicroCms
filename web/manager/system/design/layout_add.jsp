<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>布局管理</title>


<jsp:include page="../../include/include_tools.jsp"/>
<link type="text/layout" rel="stylesheet" href="/manager/styles/uploadify.layout" />
<script language="javascript" src="/manager/js/jquery.uploadify.js"></script>
<script language="javascript" src="/manager/js/uploadFile/swfobject.js"></script>
<script type="text/javascript" src="/manager/js/uploadTools.js"></script>
<script type="text/javascript" src="js/public.js"></script>
<script type="text/javascript" src="js/layout.js"></script>

<script type="text/javascript">
var site_id = "";
var layout_id = "${param.layout_id}";
var app_id = "${param.app_id}";
var top_index = ${param.top_index};

var defaultBean;
var current_obj;

$(document).ready(function(){
	initButtomStyle();
	init_input();
	
	publicUploadDesignThumbButtom("uploadify","savePicUrl");

	if(layout_id != "" && layout_id != "null" && layout_id != null)
	{		
		defaultBean = DesignRPC.getDesignLayoutBean(layout_id);
		if(defaultBean)
		{
			$("#layout_table").autoFill(defaultBean);
			$("#layout_ename").attr("readOnly",true);
		}

		$("#addButton").click(updateLayout);
	}
	else
	{
		$("#addButton").click(addLayout);
	}


});
function savePicUrl(url)
{
	$("#thumb_url").val(url);
}

</script>
</head>

<body>
<span class="blank12"></span>
<form id="form1" name="form1" action="#" method="post">
<table id="layout_table" class="table_form" border="0" cellpadding="0" cellspacing="0">
	<tbody>
		<tr>
			<th><span class="f_red">*</span>布局名称：</th>
			<td>
				<input id="layout_name" name="layout_name" type="text" class="width300" value="" onblur="checkInputValue('layout_name',false,80,'目录名称','')"/>				
			</td>			
		</tr>		
		<tr>
			<th>权重：</th>
			<td>
				<input id="weight" name="weight" type="text" style="width:50px;" value="60" maxlength="2" onblur="checkInputValue('weight',true,2,'权重','checkNumber')"/>默认值：60，取值范围（0-99）
			</td>			
		</tr>
		<tr>
			<th>缩略图：</th>
			<td>
				<div style="float:left;margin:auto;"><input id="thumb_url" name="thumb_url" type="text" class="width300" value="" /></div>
				<div style="float:left"><input type="file" name="uploadify" id="uploadify"/></div>
			</td>
		</tr>
		<tr>
			<th style="vertical-align:top;">代码：</th>
			<td>				
				<textarea id="layout_content" name="layout_content" style="width:620px;height:300px;"></textarea>	
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
			<input id="addButton" name="btn1" type="button" onclick="" value="保存" />	
			<input id="userAddReset" name="btn1" type="button" onclick="formReSet('layout_table',layout_id)" value="重置" />	
			<input id="userAddCancel" name="btn1" type="button" onclick="top.tab_colseOnclick(top.curTabIndex)" value="取消" />	
		</td>
	</tr>
</table>
<span class="blank3"></span>
</form>
</body>
</html>
