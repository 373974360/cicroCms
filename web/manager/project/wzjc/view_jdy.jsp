<%@ page contentType="text/html; charset=utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<meta name="generator" content="featon-Builder" />
<meta name="author" content="featon" />
<link type="text/css" rel="stylesheet" href="/sys/styles/uploadify.css" />
<jsp:include page="../../include/include_tools.jsp"/>
<script type="text/javascript" src="/manager/js/validator.js"></script>
<script type="text/javascript" src="js/jdyList.js"></script>
<style>
input[type="text"]{ width:100%; height:100%;}
input[type="password"]{ width:100%; height:100%;}
table tr,td{ height:24px; line-height:24px;}
table tr{ text-align:center; vertical-align:middle;}
.table_form td{text-align:center; }
</style>
<SCRIPT LANGUAGE="JavaScript">
	<!--
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
		defaultBean = JdyRPC.getJdyBean(id);

		if(defaultBean)
		{
			$("#jdyDiv").autoFill(defaultBean);	
			var passwd = JdyRPC.decode(defaultBean.password);
			$("#password").val(passwd);	
			$("#password2").val(passwd);	
		}		
	}
	//iniJdyForm();
	//$("#housediv input").attr("disabled",true);  是否可编辑
}); 

function iniJdyForm()
{  
  var validator = $("#form1").validate({
    debug:true,
    rules: {
      name: {
        required: true
      },
      gender:{
        required: true
      },
      phone:{
        required: true,
        number: true,
        minlength:11,
        maxlength:11
      },
      email:{
        required: true,
        email: true  
      },
      password:{
        required: true,
        rangelength: [6,18]
      },
      password2:{
        required: true,
        equalTo: "#password",
        rangelength: [6,18]
      }
    },
  messages: {
      name: {
        required: "请输入姓名！"
      },
      gender: {
        required: "请输入性别！"
      },
      phone: {
        required: "请输入手机号码！",
        number: "请输入正确的手机号码！",
        minlength:"请输入正确的手机号码！",
        maxlength:"请输入正确的手机号码！"
      },
      email:{
        required: "请输入电子邮件！",
        email: "请正确输入正确的Email地址"
      },
      password:{
        required: "请输入密码！",
        rangelength: "密码为6-18位字母组合"
      },
      password2:{
        required: "请输入确认密码！",
        rangelength: "密码为6-18位字母组合",
        equalTo: "两次密码输入不对！"
      }
    },
    
    errorPlacement: function(error, element) {
      //error.appendTo( element.parent("td").find("div.cError") );
      error.appendTo(element.parent()); 
    },

    submitHandler: function(form) {
  
    },

    success: function(label) {
      
    }  




  });
}


function update()
{
	defaultBean = JdyRPC.getJdyBean(id);
	$("#jdyDiv").autoBind(defaultBean);
	
	if(JdyRPC.updateJdy(defaultBean))
	{
		top.msgAlert("监督员信息修改成功");
		top.getCurrentFrameObj(topnum).reloadJdyList();
		top.tab_colseOnclick(top.curTabIndex);
	}else
	{
		top.msgWargin("监督员信息修改失败，请重新操作");
	}
}

	//-->
	</SCRIPT>	
</head>

<body>
<span class="blank12"></span>
<form id="form1" name="form1" action="#" method="post">
<div id="jdyDiv">
<!-- 隐含字段区域　开始 -->
 <input type="hidden" id="auther_type" name="auther_type" value="1"/>
 <!-- 隐含字段区域　结束 -->
	  <table style="width:680px;margin-left:2px;"  class="table_form" border="0" cellpadding="0" cellspacing="0" >
  <tr>
    <td colspan="6" style="font-size:14px; text-align:center;" ><B>监督员信息</B></td>
  </tr>
  <tr>
    <td  width="100">姓名</td>
    <td  width="180"><input type="text" id="name" name="name" /></td>
  </td>
  </tr>
  <tr>
	<td>性别</td>
    <td style="text-align:left;"><select style="width:130px;" id="gender" name="gender"  ><option value="男">男</option><option value="女">女</option></select></td>
  </tr>
  <tr>
	<td >联系电话</td>
    <td ><input type="text" id="phone" name="phone"  /></td>
  </tr>
  <tr>
	<td >电子邮箱</td>
    <td ><input type="text" id="email" name="email"  /></td>
  </tr>
  <tr>
     <td >用户名</td>
    <td ><input type="text" id="username" name="username"  /></td>
    
  </tr>
  <tr>
    <td >密码</td>
    <td ><input type="password" id="password" name="password"  /></td> 
  </tr>
  <tr>
    <td >确认密码</td>
    <td ><input type="password" id="password2" name="password2"  /></td>
  </tr>
</table>
</div>
<span class="blank12"></span>
<div class="line2h"></div>
<span class="blank3"></span>
<table class="table_option" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td align="left" valign="middle" style="text-indent:350px;">
			<input id="addButton" name="btn1" type="button" onclick="update()" value="保存" />	
			<input id="userAddCancel" name="btn1" type="button" onclick="top.tab_colseOnclick(top.curTabIndex);" value="取消" />	
		</td>
	</tr>
</table>
<span class="blank3"></span>

</form>
</body>
</html>
