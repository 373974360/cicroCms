<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>短信维护</title>
<meta name="generator" content="deya-Builder" />
<meta name="author" content="deya" />
<jsp:include page="../../../include/include_tools.jsp"/>

<script type="text/javascript" src="js/resumeList.js"></script>
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
		var defaultBean = WZJResumeRPC.getResumeBean(nm);
		if(defaultBean)
		{
			$("#sms_table").autoFill(defaultBean);				
		}
	}
});


</script>
</head>

<body>
<span class="blank12"></span>
<form id="form1" name="form1" action="#" method="post">
<table id="sms_table" class="table_form" border="0" cellpadding="0" cellspacing="0">
	<tbody>
		<tr>
			<th>姓名：</th>
			<td id="xm" width="200px"></td>
			<th>性别：</th>
			<td id="xb" ></td>
		</tr>			
		<tr>
			<th>出生日期：</th>
			<td id="csrq"></td>
			<th>婚姻状况：</th>
			<td id="hunyin"></td>
		</tr>
		<tr>
			<th>学历：</th>
			<td id="xueli"></td>
			<th>民族：</th>
			<td id="mz"></td>
		</tr>
		<tr>
			<th>身高：</th>
			<td id="shengao"></td>
			<th>体重：</th>
			<td id="tizhong"></td>
		</tr>
		<tr>
			<th>现居住地：</th>
			<td id="xaddress" colspan="3"></td>
		</tr>
		<tr>
			<td colspan="4"><span class="blank12"></span></td>
		</tr>
		<tr>
			<th>职位行业：</th>
			<td id="sshy"></td>
			<th>职位：</th>
			<td id="zhiwei"></td>
		</tr>
		<tr>
			<th>性别：</th>
			<td id="xb"></td>
			<th>出生日期：</th>
			<td id="csrq"></td>
		</tr>
		<tr>
			<th>工作年限：</th>
			<td id="gzage"></td>
			<th>薪金要求：</th>
			<td id="gz"></td>
		</tr>
		<tr>
			<th>就业地区：</th>
			<td id="jydq"></td>
		</tr>
		<tr>
			<td colspan="4"><span class="blank12"></span></td>
		</tr>
		<tr>
			<th>个人专业：</th>
			<td id="zhuanye"></td>
			<th>毕业院校：</th>
			<td id="byyx"></td>
		</tr>
		<tr>
			<th>外语种类：</th>
			<td id="waiyu"></td>
			<th>外语水平：</th>
			<td id="wysp"></td>
		</tr>
		<tr>
			<th>计算机水平：</th>
			<td id="jsjsp"></td>
			<th>驾照类型：</th>
			<td id="jzlx"></td>
		</tr>
		<tr>
			<th>其他特长：</th>
			<td id="techang" colspan="3"></td>
		</tr>
		<tr>
			<th>工作经历：</th>
			<td id="gzjl" colspan="3"></td>
		</tr>
		<tr>
			<th>相关证书或<br>培训经历：</th>
			<td id="pxjl" colspan="3"></td>
		</tr>
		<tr>
			<td colspan="4"><span class="blank12"></span></td>
		</tr>
		<tr>
			<th>手机号码：</th>
			<td id="telephone"></td>
			<th>固定电话：</th>
			<td id="phone"></td>
		</tr>
		<tr>
			<th>备用联系方式：</th>
			<td id="btelephone"></td>
			<th>E-mai：</th>
			<td id="email"></td>
		</tr>
		<tr>
			<th>通信地址：</th>
			<td id="taddress" colspan="3"></td>
		</tr>
		<tr>
			<th>邮编：</th>
			<td id="postcode" colspan="3"></td>
		</tr>
	</tbody>
</table>
<span class="blank12"></span>
<div class="line2h"></div>
<span class="blank3"></span>
<table class="table_option" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td align="left" valign="middle" style="text-indent:100px;">
			<input id="userAddCancel" name="btn1" type="button" onclick="top.tab_colseOnclick(top.curTabIndex);" value="关闭" />	
		</td>
	</tr>
</table>
<span class="blank3"></span>
</form>
</body>
</html>
