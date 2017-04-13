<%@page import="java.util.UUID"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@page import="com.cicro.wcm.server.LicenseCheck,com.cicro.util.CryptoTools"%>
<%
    
    String key = request.getParameter("key"); 
	String uuid = UUID.randomUUID().toString().replace("-", "");
	request.getSession().setAttribute("randTxt",uuid);
	if(key != null && !"".equals(key.trim()))
	{
		boolean result = LicenseCheck.createLicense(key.trim());
		if(!result){//输入的key不正确
			response.sendRedirect("/sys/setLicense.jsp?type=0");
			return;
		}
	}else{
		if(!com.cicro.wcm.startup.ServerListener.isLicenseRight || !com.cicro.wcm.startup.ServerListener.isLicenseRight){
	    	response.sendRedirect("/sys/setLicense.jsp");
	    	return;
	    }
	}

	String user_name = request.getParameter("username");
	String pass_word = request.getParameter("password");
	String auth_code = request.getParameter("auth_code");
	if(user_name != null && !"".equals(user_name))
	{
		CryptoTools ct = new CryptoTools();
		pass_word = ct.decode(pass_word);
		String res = com.cicro.wcm.services.org.user.UserLoginRPC.checkUserLogin(user_name,pass_word,auth_code,request);
		if("auth_code_error".equals(res))
		{
			out.println("<script>alert(\"验证码不正确\");top.changeCreateImage();</script>");		
			return;
		}
		else
		{
			if(!"0".equals(res))		
			{
				int msg = Integer.parseInt(res);
				switch(msg)
				{
					case 1:out.println("<script>alert(\"该帐号还未开通\");top.changeCreateImage();</script>");break;
					case 2:out.println("<script>alert(\"该用户已被停用\");top.changeCreateImage();</script>");break;
					case 3:out.println("<script>alert(\"用户名密码不正确\");top.changeCreateImage();</script>");break;//用户名不正确
					case 4:out.println("<script>alert(\"用户名密码不正确\");top.changeCreateImage();</script>");break;//用户名密码不正确
					case 5:out.println("<script>alert(\"该用户不存在\");top.changeCreateImage();</script>");break;//
					default:out.println("<script>alert(\"用户名密码不正确\");top.changeCreateImage();</script>");break;
				}
				
				return;
			}else
			{
				out.println("<script>top.location.href = 'index.jsp';</script>");
			}
		}
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>时光门户管理系统-登录</title>
<meta http-equiv="X-UA-Compatible" content="IE=8" />


<link rel="stylesheet" type="text/css" href="styles/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="styles/themes/icon.css">
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/java.js"></script>
<script type="text/javascript" src="js/extend.js"></script>
<script type="text/javascript" src="js/jsonrpc.js"></script>
<script type="text/javascript" src="js/jquery.c.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript" src="js/validator.js"></script>
<script type="text/javascript" src="js/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="js/jquery.easyui.min.js"></script>

<script type="text/javascript">

var ct = jsonrpc.CryptoTools;

$(document).ready(function(){
	//$("#sysname").html('<img alt="" src="images/login_logo.gif" />');
	$("#sysname").html('时光门户网站管理系统');

	if ($.browser.msie && ($.browser.version == "6.0") && !$.support.style)
	{ 
		$('#dd').show();
		$('#dd').dialog();
		//$("#browserSpan").html("检测到浏览器版本为IE6.0，为了更好的管理后台，建议升级到IE7以上版本，<a href='http://windows.microsoft.com/zh-CN/internet-explorer/downloads/ie'>IE下载地址</a>。");
	}else{
		$('#dd').hide();
	}
        $("#username").focus();	
   
	//2分钟更新一下验证码，以免session失效
	setInterval(function(){
		changeCreateImage();
	},1000*120) ;
});
function loginWCM()
{
	if($.trim($("#username").val())=="")
	{
		alert("用户名不能为空！");
		$("#username").focus();
		return;	
	}
	if($.trim($("#password").val())=="")
	{
		alert("密码不能为空！");
		$("#password").focus();
		return;	
	}
	if($.trim($("#auth_code").val())=="")
	{
		alert("验证码不能为空！");
		$("#auth_code").focus();
		return;	
	}

	var pwd = $.trim($("#password").val());
	var index = pwd.indexOf("=#=");
	if(index != 0)
	{
		$("#password").val(ct.encode(pwd));
	}

	$("#form1").submit();
}

function changeCreateImage()
{
	$('#img').attr('src','createImage.jsp?'+Math.random());
}
</script>
<style>
body {font-family: Tahoma, Geneva, sans-serif,"微软雅黑",\5B8B\4F53,Arial Narrow,arial,serif;background:#C0C0C0;font-size:12px;}
body,div,ul,ol,form,input,textarea{padding:0; margin:0; color:#2b2b2b;}
table,td,tr,th{font-size:12px;}
li{list-style-type:none;list-style:none;}
table{ margin:0 auto;border-spacing:0; border-collapse:collapse;}
img{border:0;}
ol,ul {list-style:none;}
.red{ border:1px solid red;}
.blue{ border:1px solid blue;}
a{color:#CDE7FF; text-decoration:none;}

#sysname{ color:#FFF; line-height:27px; font-size:20px; padding-left:1px; letter-spacing:2px; font-family:"黑体"; font-weight:bold;}

.bodyDiv{ width:890px; height:391px; margin:0 auto; background:url(images/login_bg.png) no-repeat;}

.note{ margin:0px;}
.note li{ line-height:22px; color:#CDE7FF; list-style-image:url(images/login_dot.gif);}

.areaLeft{width:448px; height:96px; float:left; background:url(images/login_vline.gif) right repeat-y;}
.areaRight{width:420px; height:96px; float:right; text-align:left;}

.loginForm{ color:#FFFFFF;}
.loginForm tr{line-height:30px;}
.loginForm th{text-align:right; font-size:13px; font-weight:bold;letter-spacing:3px;}
.loginForm td{text-align:left; padding:3px 0px;  vertical-align:middle;line-height:30px;  }

.loginInput{ width:210px; height:21px; border:1px solid #6797BD; font-size:14px; line-height:21px; font-weight:bold; padding-left:4px;letter-spacing:1px;}

.btn{ width:65px; height:30px;}
.copyright{ font-size:12px; color:#DCEDFD; text-align:center;}
.hidden{display:none;}
</style>
</head>
<body>
<div id="dd" style="padding:5px;width:320px;height:180px;color:#fff;display:none" title="温馨提示" >
		<div style="font-size:13px;text-algin:left;line-height:24px;padding:10px;">
		    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;检测到浏览器版本为IE6.0，为了更好的管理后台和操作，建议升级到IE7以上版本,<a href='http://download.microsoft.com/download/1/6/1/16174D37-73C1-4F76-A305-902E9D32BAC9/IE8-WindowsXP-x86-CHS.exe' target="_blank"><font style="color:red;">IE下载安装</font></a>,谢谢!
		</div>
</div>
<form id="form1" name="form1" method="post" action="login.jsp" target="submitFrame">
<input type="hidden" name="randSesion"  value = "<%=request.getSession().getAttribute("randTxt")%>" />
<div style="height:80px;"></div>
<div class="bodyDiv">
<table width="100%" class="" cellpadding="0" cellspacing="0">
	<tr style="height:25px;">
		<td>&nbsp;
			
		</td>
	</tr>
	<tr style="height:63px;">
		<td style="text-indent:55px;" valign="middle">
			<div style="width:460px; float:left;">
				<div id="sysname"><img alt="" src="images/login_logo.gif" /></div>
                <div><img alt="" src="images/login_logo_en.gif" /></div>
			</div>
			<div style="width:160px; float:right;"><a href="javascript:window.external.AddFavorite(document.location.href,'门户管理系统-登录');">加入收藏</a></div>
		</td>
	</tr>
	<tr style="height:34px;">
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td>
			<div class="areaLeft">
<table class="loginForm" width="100%" cellpadding="0" cellspacing="0">
<tr>
	<th>用户名：</th>
	<td width="220"><input id="username" name="username" type="text" class="loginInput" maxlength="20" value="" /> </td>
	<td width="12"></td>
</tr>
<tr> 
	<th>密　码：</th>
	<td><input id="password" name="password" type="password" class="loginInput"  maxlength="20" value="" /></td>
	<td></td>
</tr>
<tr>
	<th>验证码：</th>
	<td>
		<input id="auth_code" name="auth_code" type="text" class="loginInput" style="width:52px;" maxlength="6" value="" />
		<img id="img" width="60" height="24"  style="vertical-align:top;" src="createImage.jsp" />
		<a href="javascript:changeCreateImage()">看不清，换一个</a>
		</td>
	<td></td>
</tr>
</table>
			</div>
			
			<div class="areaRight">
				<ul class="note">
					<li>请不要在公共场所设定计算机记住您的个人信息</li>
					<li>在公共场所使用本产品后请务必退出系统</li>
					<li>以免造成不必要的损失</li>
					<li>尽量避免多人使用同一帐号</li>
				</ul>
			</div>
		
		</td>
	</tr>
	<tr style="height:28px;">
		<td></td>
	</tr>
	<tr>
		<td align="left" style="">
			<table border="0" width="100%">
				<tr>
					<td width="300"></td>
					<td>
			<input id="btnSubmit" name="btnSubmit" type="submit" onclick="loginWCM()" class="btn" value="登 录" />
			<input id="btnReset" name="btnReset" type="reset" class="btn"  value="重 置" />
					</td>
				</tr>
			</table>
		</td>
	</tr>
	<tr style="height:64px;">
		<td></td>
	</tr>
	<tr>
		<td class="copyright">Copyright © 2002-2011 Cicro Software,Inc. All rights reserved. 时光软件 版权所有</td>
	</tr>
</table>
<br />
<div id="browserSpan"></div>
</div>
</form>
<iframe id="submitFrame" name="submitFrame" src="" frameborder="0" width="0" height="0"></iframe>
</body>
</html>
