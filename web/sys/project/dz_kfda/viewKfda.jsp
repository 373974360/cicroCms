<%@ page contentType="text/html; charset=utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>开放档案</title>
<meta name="generator" content="deya-Builder" />
<meta name="author" content="deya" />
<jsp:include page="../../include/include_tools.jsp"/>

<script type="text/javascript" src="js/kfdaList.js"></script>
<script type="text/javascript" src="/sys/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="/sys/js/kindeditor/kindeditor.js"></script>
<style>
input[type="text"]{ width:60%; height:100%;}
input[type="password"]{ width:60%; height:100%;}
table tr,td{ height:24px; line-height:24px;}
table tr{ text-align:center; vertical-align:middle;}
.table_form td{text-align:center; }
select{height:24px;line-height:24px;width:285px;}
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
	init_editer_turnpage("fz");
	//inisb_xmglForm();
	
	if($.browser.msie&&$.browser.version=="6.0"&&$("html")[0].scrollHeight>$("html").height()) $("html").css("overflowY","scroll");
				
	if(id != null && id.trim() != "")
	{
		defaultBean = KfdaRPC.getKfdaBean(id);

		if(defaultBean)
		{
			$("#kfdadiv").autoFill(defaultBean);
			KE.html("fz", defaultBean.fz);
		}		
	}
	//$("#housediv input").attr("disabled",true);  是否可编辑
}); 


function savePublishFlag(publish_flag)
{
	var m = new Map();
	m.put("status",status+"");
	m.put("ids",id);
	
	if(XmkRPC.publishXmk(m))
	{
		top.msgAlert("项目信息发布状态设置成功");
		top.getCurrentFrameObj(topnum).reloadsb_xmglList();
		top.tab_colseOnclick(top.curTabIndex);
	}else
	{
		top.msgWargin("项目信息发布状态设置失败，请重新操作");
	}
}

/*
function inisb_xmglForm()
{  
  var validator = $("#form1").validate({
    debug:true,
    rules: {
      company: {
        required: true
      },
      name:{
        required: true,
      },
    gender:{
        required: true,
      },
    zhiwu:{
        required: true,
      },
    bianhao:{
        required: true,
      }
    },
    messages: {
      company: {
        required: "　请输入单位名称！"
      },
      
      name:{
        required: "　请输入姓名！",
      },
    gender:{
        required: "　请选择性别！",
      },
    zhiwu:{
        required: "　请输入职务！",
      },
    bianhao:{
        required: "　请输入编号！",
      }
    },
    
    errorPlacement: function(error, element) {
      //error.appendTo( element.parent("td").find("div.cError") );
      error.appendTo(element.parent());
    },

    submitHandler: function(form) {
		document.getElementById('form1').submit();  
    },

    success: function(label) {
      
    }
  });
  
}
*/
	//-->
	</SCRIPT>	
</head>

<body>
<span class="blank12"></span>
<div style="width:755px;">
<div id="kfdadiv" >
<!-- 隐含字段区域　开始 -->
 <input type="hidden" id="auther_type" name="auther_type" value="1"/>
 <!-- 隐含字段区域　结束 -->
	  <table style="width:750px;"  class="table_form" border="0" cellpadding="0" cellspacing="0" id="kfda_table" name = "kfda_table" >
  <tr>
    <td colspan="6" style="font-size:14px; text-align:center;" ><B>开放档案信息管理</B></td>
  </tr>
  <tr>
    <td >目录名称</td>
    <td  colspan="4" style="text-align:left;"><input type="text" id="mlmc" name="mlmc"  /></td>
    
  </tr>
  <tr>
    <td>档号</td>
    <td  colspan="4" style="text-align:left;"><input type="text" id="dh" name="dh"  /></td>
  </tr>
  <tr>
    <td >题名</td>
    <td  colspan="4" style="text-align:left;"><input type="text" id="tm" name="tm"  /></td>
    
  </tr>
  <tr>
	 <td >关键词</td>
    <td  colspan="4" style="text-align:left;"><input type="text" id="gjc" name="gjc"  /></td>
  </tr>
  <tr>
	<td >责任者</td>
    <td  colspan="4" style="text-align:left;"><input type="text" id="zrz" name="zrz"  /></td>
  </tr>
  <tr>
	<td  >时间</td>
    <td  colspan="4" style="text-align:left;"><input type="text" id="sj" name="sj"  /></td>
  </tr>
  <tr>
	<td>档案类型</td>
    <td colspan="4" style="text-align:left;"><input type="text" id="dalx" name="dalx"  /></td>
  </tr>
  <tr>
	<td  >原文路径</td>
	<td colspan="4" style="text-align:left;"><input type="text" id="ywlj" name="ywlj"  /></td>
  </tr>
  <tr>
	<td  >原文目录</td>
	<td colspan="4" style="text-align:left;"><input type="text" id="ywml" name="ywml"  /></td>
  </tr>
  <tr>
	<td  >附注</td>
    <td  colspan="4" style="text-align:left;">
		<textarea id="fz" name="fz" style="width:630px;;height:250px;visibility:hidden;"></textarea>
	</td>
  </tr>
 </table>  
</div>
<span class="blank12"></span>
<div class="line2h"></div>
<span class="blank3"></span>
<table class="table_option" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td align="center" valign="middle" style="text-indent:100px;">
			<input  name="btn1" type="button" onclick="updateKfdaData()"  value="保存" />	
			<input id="userAddCancel" name="btn1" type="button" onclick="top.tab_colseOnclick(top.curTabIndex);" value="取消" />	
		</td>
	</tr>
</table>
<span class="blank3"></span>
</div>
</body>
</html>
