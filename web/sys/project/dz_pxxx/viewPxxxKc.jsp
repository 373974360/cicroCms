<%@ page contentType="text/html; charset=utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>培训课程信息</title>
<meta name="generator" content="deya-Builder" />
<meta name="author" content="deya" />
<jsp:include page="../../include/include_tools.jsp"/>

<script type="text/javascript" src="js/pxxxKcList.js"></script>
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
	//inisb_xmglForm();
	
	if($.browser.msie&&$.browser.version=="6.0"&&$("html")[0].scrollHeight>$("html").height()) $("html").css("overflowY","scroll");
				
	if(id != null && id.trim() != "")
	{
		defaultBean = PxxxKcRPC.getPxxxKcBean(id);

		if(defaultBean)
		{
			$("#pxxxKcdiv").autoFill(defaultBean);
		}		
	}
	var pxxxList = PxxxRPC.getAllPxxxList();//第一个参数为站点ID，暂时默认为空	
	pxxxList = List.toJSList(pxxxList);//把list转成JS的List对象	
	for(var i = 0; i < pxxxList.size(); i++)
	{
		var selObj = $("#pxmc");
		var value = pxxxList.get(i).id;
		var text = pxxxList.get(i).pxmc;
		selObj.append("<option value='"+value+"'>"+text+"</option>");
	}
	//$("#housediv input").attr("disabled",true);  是否可编辑
}); 


function savePublishFlag(publish_flag)
{
	var m = new Map();
	m.put("status",status+"");
	m.put("ids",id);
	
	if(PxxxKcRPC.publishPxxxKc(m))
	{
		top.msgAlert("培训课程信息发布状态设置成功");
		top.getCurrentFrameObj(topnum).reloadPxxxKcList();
		top.tab_colseOnclick(top.curTabIndex);
	}else
	{
		top.msgWargin("培训课程信息发布状态设置失败，请重新操作");
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
<div id="pxxxKcdiv" >
<!-- 隐含字段区域　开始 -->
 <input type="hidden" id="auther_type" name="auther_type" value="1"/>
 <!-- 隐含字段区域　结束 -->
	  <table style="width:750px;"  class="table_form" border="0" cellpadding="0" cellspacing="0" id="pxxxKc_table" name = "pxxxKc_table" >
  <tr>
    <td colspan="6" style="font-size:14px; text-align:center;" ><B>培训课程信息管理</B></td>
  </tr>
  <tr>
    <td >培训名称</td>
    <td  colspan="4" style="text-align:left;">
		<select id="pxmc" name="pxmc">
			
		</select>
	</td>
    
  </tr>
  <tr>
    <td>培训时间</td>
    <td  colspan="4" style="text-align:left;">
		<input type="text" readonly="" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true,readOnly:true})" size="11" id="pxsj" name="pxsj" class="Wdate">
	</td>
  </tr>
  <tr>
    <td >培训地点</td>
    <td  colspan="4" style="text-align:left;"><input type="text" id="pxdd" name="pxdd"  /></td>
    
  </tr>
  <tr>
	 <td >主讲人</td>
    <td  colspan="4" style="text-align:left;"><input type="text" id="zjr" name="zjr"  /></td>
  </tr>
 </table>  
</div>
<span class="blank12"></span>
<div class="line2h"></div>
<span class="blank3"></span>
<table class="table_option" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td align="center" valign="middle" style="text-indent:100px;">
			<input  name="btn1" type="button" onclick="updatePxxxKcData()"  value="保存" />	
			<input id="userAddCancel" name="btn1" type="button" onclick="top.tab_colseOnclick(top.curTabIndex);" value="取消" />	
		</td>
	</tr>
</table>
<span class="blank3"></span>
</div>
</body>
</html>
