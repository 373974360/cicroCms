<%@ page contentType="text/html; charset=utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<meta name="generator" content="featon-Builder" />
<meta name="author" content="featon" />
<link type="text/css" rel="stylesheet" href="/sys/styles/uploadify.css" />
<jsp:include page="../../../include/include_tools.jsp"/>
<script type="text/javascript" src="js/gongqiuList.js"></script>
<SCRIPT LANGUAGE="JavaScript">
	<!--
var site_id = request.getParameter("site_id");
var topnum = request.getParameter("topnum");
var gq_id = request.getParameter("gq_id");
var img_count = 0;

var defaultBean;
$(document).ready(function () {				
	initButtomStyle();
	init_FromTabsStyle();
	init_input();
	
	if($.browser.msie&&$.browser.version=="6.0"&&$("html")[0].scrollHeight>$("html").height()) $("html").css("overflowY","scroll");
				
	if(gq_id != null && gq_id.trim() != "")
	{
		defaultBean = GongQiuRPC.getGongQiuBean(gq_id,false);

		if(defaultBean)
		{
			$("#gongqidiv").autoFill(defaultBean);	
					
		}		
	}
	$("#gongqidiv input").attr("disabled",true);
}); 


function savePublishFlag(gq_publish_flag)
{
	var m = new Map();
	m.put("gq_publish_flag",gq_publish_flag+"");
	m.put("gq_ids",gq_id);
	
	if(GongQiuRPC.publishGongQiu(m))
	{
		top.msgAlert("供求信息发布状态设置成功");
		top.getCurrentFrameObj(topnum).reloadGongQiuList();
		top.tab_colseOnclick(top.curTabIndex);
	}else
	{
		top.msgWargin("供求信息发布状态设置失败，请重新操作");
	}
}

	//-->
	</SCRIPT>	
</head>

<body>
<span class="blank12"></span>
<form id="form1" name="form1" action="#" method="post">
<div id="gongqidiv">
<!-- 隐含字段区域　开始 -->
 <input type="hidden" id="auther_type" name="auther_type" value="1"/>
 <!-- 隐含字段区域　结束 -->
<table id="gq_tab" class="table_form" border="0" cellpadding="0" cellspacing="0">
	<tbody>
		<tr>
			<th>供应/求购：</th>
			<td width="220px">
				<ul>
					<li><input type="radio" id="gq_type" name="gq_type" value="0" checked="checked"><label>供应</label></li>
					<li><input type="radio" id="gq_type" name="gq_type" value="1" ><label>求购</label></li>
				</ul>
			</td>
		</tr>		
		<tr>
			<th>联系人：</th>
			<td >				
				<input id="gq_user" name="gq_user" type="text" class="width200" value="" />
			</td>
		</tr>		
		<tr>
			<th>有效期：</th>
			<td >
				<input id="gq_youxiao" name="gq_youxiao" type="text" class="width200" value="" />
			</td>
		</tr>
		<tr>
			<th>手机号：</th>
			<td>
				<input id="gq_mobile" name="gq_mobile" type="text" class="width200" value="" />
			</td>
		</tr>
		<tr>
			<th>电话：</th>
			<td>
				<input id="gq_tel" name="gq_tel" type="text" class="width200" value="" />
			</td>
		</tr>
		<tr>
			<th>传真：</th>
			<td>
				<input id="gq_fax" name="gq_fax" type="text" class="width200" value="" />
			</td>
		</tr>		
		<tr>
			<th>邮编：</th>
			<td>
				<input id="gq_post" name="gq_post" type="text" class="width200" value="" />
			</td>
		</tr>
		<tr>
			<th>单位名称：</th>
			<td>
				<input id="gq_util" name="gq_util" type="text" class="width500" value="" />
			</td>
		</tr>
		<tr>
			<th>供求开始时间：</th>
			<td>
				<input id="gq_start_time" name="gq_start_time" type="text" class="width200" value="" />
			</td>
		</tr>
		<tr>
			<th>通讯地址：</th>
			<td>
				<input id="gq_address" name="gq_address" type="text" class="width500" value="" />
			</td>
		</tr>
		<tr>
			<th>供应/出售产品：</th>
			<td>
				<input id="gq_title" name="gq_title" type="text" class="width500" value="" />
			</td>
		</tr>
		<tr>
			<th style="vertical-align:top;">详细内容：</th>
			<td colspan="3" id="gq_content">
			</td>			
		</tr>		
	</tbody>
</table>
</div>
<span class="blank12"></span>
<div class="line2h"></div>
<span class="blank3"></span>
<table class="table_option" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td align="left" valign="middle" style="text-indent:100px;">
			<input id="addButton" name="btn1" type="button" onclick="savePublishFlag(1)" value="发布" />	
			<input id="userAddReset" name="btn1" type="button" onclick="savePublishFlag(0)" value="撤消" />	
			<input id="userAddCancel" name="btn1" type="button" onclick="top.tab_colseOnclick(top.curTabIndex);" value="取消" />	
		</td>
	</tr>
</table>
<span class="blank3"></span>

</form>
</body>
</html>
