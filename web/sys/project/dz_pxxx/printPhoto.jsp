<%@ page contentType="text/html; charset=utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>打印培训人员照片</title>
<meta name="generator" content="deya-Builder" />
<meta name="author" content="deya" />
<jsp:include page="../../include/include_tools.jsp"/>

<script type="text/javascript" src="js/pxxxBmList.js"></script>
<script type="text/javascript" src="/sys/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="/sys/js/kindeditor/kindeditor.js"></script>

<SCRIPT LANGUAGE="JavaScript">
	
var site_id = request.getParameter("site_id");
var topnum = request.getParameter("topnum");
var id = request.getParameter("id");
var pxid = request.getParameter("pxid");
var img_count = 0;

$(document).ready(function () {				
	
	if($.browser.msie&&$.browser.version=="6.0"&&$("html")[0].scrollHeight>$("html").height()) $("html").css("overflowY","scroll");
	var m = new Map();
	m.put("start_num", 0);	
	m.put("page_size", 1000);
	m.put("orderby", "id desc");
	if(pxid != null && pxid != "")
	{
		m.put("pxid", pxid);
	}
	var beanList = PxxxBmRPC.getPxxxBmList(m);//第一个参数为站点ID，暂时默认为空	
	beanList = List.toJSList(beanList);//把list转成JS的List对象	
	for(var i = 0; i < beanList.size(); i++)
	{
		$("#photos").append("<li style='text-align:center;float:left;display:inline;margin-left:30px;width:150px;'><img src='" + beanList.get(i).photo + "' height='160' width='114' /><p style='line-height:40px;'>" + beanList.get(i).xm + "</p></li>");
	}
	
}); 



	</SCRIPT>	
</head>

<body>

<div style="width:800px;">
	<center><p style="font-weight:bold;font-size:16px;">培训人员照片</p></center>
	<div class="blank18"></div>
	<div class="blank18"></div>
	<ul id="photos" name="photos">
		
	</ul>
</div>
<div class="blank18"></div>
<div class="blank18"></div>
<div style="width:800px;">
	<center><input type="button" value="打印" onclick="window.print();" name="btn1" class="btn x2"></center>
</div>
</body>
</html>
