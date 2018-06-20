<%@ page contentType="text/html; charset=utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<meta name="generator" content="featon-Builder" />
<meta name="author" content="featon" />
<jsp:include page="../../include/include_tools.jsp"/>
<script type="text/javascript" src="/wcm.files/js/jwplayer/jwplayer.js"></script>
<script type="text/javascript" src="js/zjzfList.js"></script>
<SCRIPT LANGUAGE="JavaScript">
	<!--
var topnum = request.getParameter("topnum");
var id = request.getParameter("id");

var defaultBean;
$(document).ready(function () {		
	
	initButtomStyle();
	init_input();
	
	if($.browser.msie&&$.browser.version=="6.0"&&$("html")[0].scrollHeight>$("html").height()) $("html").css("overflowY","scroll");
				
	if(id != null && id.trim() != "")
	{
		defaultBean = DxsjxRPC.getDxsjxBean(id);

		if(defaultBean)
		{
			$("#picviewdiv").autoFill(defaultBean);	
			
		}		
	}
	
}); 

	//-->
	</SCRIPT>	
</head>

<body>
<span class="blank12"></span>
<form id="form1" name="form1" action="#" method="post">
<div id="picviewdiv">

<table id="gq_tab" class="table_form" border="0" cellpadding="0" cellspacing="0">
	<tbody>
		<tr>
			<th>学校名称：</th>
			<td id="xxmc">
			</td>
		</tr>		
		<tr>
			<th>所学专业：</th>
			<td id="sxzy">
				
			</td>
		</tr>		
		<tr>
			<th>所在年级：</th>
			<td id="sznj">
			</td>
		</tr>
		<tr>
			<th >姓名：</th>
			<td id="xm">
				
			</td>
		</tr>	
		

		<tr>
			<th >性别：</th>
			<td id="xb">
				
			</td>
		</tr>
		<tr>
			<th >民族：</th>
			<td id="mz">
				
			</td>
		</tr>
		<tr>
			<th >籍贯：</th>
			<td id="jg">
				
			</td>
		</tr>
		<tr>
			<th >政治面貌：</th>
			<td id="zzmm">
				
			</td>
		</tr>
		<tr>
			<th >健康状况：</th>
			<td id="jkzk">
				
			</td>
		</tr>
		<tr>
			<th >年龄：</th>
			<td id="nl">
				
			</td>
		</tr>
		<tr>
			<th >QQ：</th>
			<td id="qq">
				
			</td>
		</tr>

		<tr>
			<th >本人特长：</th>
			<td id="brtc">
				
			</td>
		</tr>
		<tr>
			<th >邮政编码：</th>
			<td id="yzbm">
				
			</td>
		</tr>
		<tr>
			<th >身份证号码：</th>
			<td id="sfzhm">
				
			</td>
		</tr>
		<tr>
			<th >联系电话：</th>
			<td id="lxdh">
				
			</td>
		</tr>
		<tr>
			<th >学校通讯地址：</th>
			<td id="xxtxdz">

			</td>
		</tr>
		<tr>
			<th >家庭联系人及电话：</th>
			<td id="jtlxrjdh">

			</td>
		</tr>
		<tr>
			<th >家庭详细住址：</th>
			<td id="jtxxzz">

			</td>
		</tr>
		<tr>
			<th >获奖情况：</th>
			<td id="hjqk">

			</td>
		</tr>
		<tr>
			<th >个人自荐申请：</th>
			<td id="grzjsq">

			</td>
		</tr>
		<tr>
			<th >照片：</th>
			<td id="zp">

			</td>
		</tr>
		<tr>
			<th >推荐表（电子版）：</th>
			<td id="tjb">

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
			<input id="userAddCancel" name="btn1" type="button" onclick="top.tab_colseOnclick(top.curTabIndex);" value="关闭" />	
		</td>
	</tr>
</table>
<span class="blank3"></span>

</form>
</body>
</html>
