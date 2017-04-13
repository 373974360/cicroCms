<%@ page contentType="text/html; charset=utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>道德模范信息</title>
<meta name="generator" content="deya-Builder" />
<meta name="author" content="deya" />
<jsp:include page="../../include/include_tools.jsp"/>
<script type="text/javascript" src="js/ddmfList.js"></script>
<style>

tr,td,th{ height:30px; line-height:30px; border:1px solid #bbccde}
table tr{ text-align:center; vertical-align:middle;}
.thTitle{ font-weight:bold; text-align:center; color:#416aa3;}
.thTitle p{color:#416aa3;}
input[type="text"]{border:none; border:0px; width:85%; height:100%; background:none; padding-left:15px;}
input[type="radio"],input[type="checkbox"]{ margin-left:15px;}
</style>
<SCRIPT LANGUAGE="JavaScript">
	
var site_id = request.getParameter("site_id");
var topnum = request.getParameter("topnum");
var id = request.getParameter("id");
var img_count = 0;

//alert(site_id + topnum + id);
var defaultBean;
$(document).ready(function () {				
	initButtomStyle();
	init_FromTabsStyle();
	init_input();
	
	if($.browser.msie&&$.browser.version=="6.0"&&$("html")[0].scrollHeight>$("html").height()) $("html").css("overflowY","scroll");
				
	if(id != null && id.trim() != "")
	{
		defaultBean = DdmfRPC.getDdmfBean(id,false);

		if(defaultBean)
		{
			$("#ddmfdiv").autoFill(defaultBean);
			if(defaultBean.xb == 0)
			{
				$("#xb").val("男");
			}
			else
			{
				$("#xb").val("女");
			}
			if(defaultBean.xl == 1)
			{
				$("#xl").val("小学");
			}
			else if(defaultBean.xl == 2)
			{
				$("#xl").val("初中");
			}
			else if(defaultBean.xl == 3)
			{
				$("#xl").val("高中");
			}
			else if(defaultBean.xl == 4)
			{
				$("#xl").val("专科");
			}
			else if(defaultBean.xl == 5)
			{
				$("#xl").val("本科");
			}
			else if(defaultBean.xl == 6)
			{
				$("#xl").val("研究生");
			}
			else if(defaultBean.xl == 7)
			{
				$("#xl").val("成人高考");
			}
			else
			{
				$("#xl").val("其他");
			}
		}		
	}
}); 


	//-->
	</SCRIPT>	
</head>

<body>
<span class="blank12"></span>
<div style="width:755px;">
<div id="ddmfdiv" >
<!-- 隐含字段区域　开始 -->
 <input type="hidden" id="auther_type" name="auther_type" value="1"/>
 <!-- 隐含字段区域　结束 -->

	<table  style="width:750px;" border="1" cellpadding="0" cellspacing="0" id="ddmf_table" name = "ddmf_table"  style="line-height:30px;">
	  <tbody>
		<tr>
		  <td colspan="7" class="thTitle">陕西省第四届道德模范候选人评议</td>
		</tr>
		<tr>
		  <td class="thTitle">姓名</td>
		  <td colspan="3"><input type="text" id="xm" name="xm"  /></td>
		  <td class="thTitle">年龄</td>
		  <td colspan="2"><input type="text" id="nl" name="nl"  /></td>
		</tr>
		<tr>
		  <td class="thTitle">性别</td>
		  <td colspan="3"><input type="text" id="xb" name="xb"  /></td>
		  <td class="thTitle"><p >职业</p></td>
		  <td colspan="2"><input type="text" id="zy" name="zy"  /></td>
		</tr>
		<tr>
		  <td class="thTitle"><p >学历</p></td>
		  <td colspan="3"><input type="text" id="xl" name="xl"  /></td>
		  <td class="thTitle"><p >籍贯</p></td>
		  <td colspan="2"><input type="text" id="jg" name="jg"  /></td>
		</tr>
		<tr>
		  <td class="thTitle"><p >住址</p></td>
		  <td colspan="6">
		    <input type="text" id="zz" name="zz"  />
		</tr>
		<tr>
		  <td class="thTitle"><p >先进事迹简介</p></td>
		  <td colspan="6">
			<textarea id="xjsj" name="xjsj" style="width:630px;height:250px;"></textarea>
		  </td>
		</tr>
		<tr>
		  <td class="thTitle">联系手机</td>
		  <td colspan="3"><input type="text" id="lxdh" name="lxdh"  /></td>
		  <td class="thTitle"><p >QQ号码</p></td>
		  <td colspan="2"><input type="text" id="qq" name="qq"  /></td>
		</tr>
		<tr>
		  <td class="thTitle"><p >电子邮箱</p></td>
		  <td colspan="6"><input type="text" id="email" name="email"  /></td>
		</tr>
		</tbody>
	</table>
</div>
<span class="blank12"></span>
<span class="blank3"></span>
<table class="table_option" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td align="center" valign="middle" style="text-indent:100px;">
			<input  name="btn1" type="button" onclick="updateDdmfData()"  value="保存" />	
			<input id="userAddCancel" name="btn1" type="button" onclick="top.tab_colseOnclick(top.curTabIndex);" value="取消" />	
		</td>
	</tr>
</table>
<span class="blank3"></span>
</div>
</body>
</html>
