<%@ page contentType="text/html; charset=utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>项目信息</title>
<meta name="generator" content="deya-Builder" />
<meta name="author" content="deya" />
<jsp:include page="../../include/include_tools.jsp"/>

<script type="text/javascript" src="js/xmkList.js"></script>
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
	init_editer_turnpage("gsjs");
	init_editer_turnpage("xmms");
	init_editer_turnpage("cpjt");
	init_editer_turnpage("bz");
	//inisb_xmglForm();
	
	if($.browser.msie&&$.browser.version=="6.0"&&$("html")[0].scrollHeight>$("html").height()) $("html").css("overflowY","scroll");
				
	if(id != null && id.trim() != "")
	{
		defaultBean = XmkRPC.getXmkBean(id,false);

		if(defaultBean)
		{
			$("#xmkdiv").autoFill(defaultBean);
			KE.html("gsjs", defaultBean.gsjs);
			KE.html("xmms", defaultBean.xmms);
			KE.html("cpjt", defaultBean.cpjt);
			KE.html("bz", defaultBean.cpjt);
		}		
	}
	//$("#housediv input").attr("disabled",true);  是否可编辑
}); 


function savePublishFlag(publish_flag)
{
	var m = new Map();
	m.put("status",publish_flag+"");
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
<div id="xmkdiv" >
<!-- 隐含字段区域　开始 -->
 <input type="hidden" id="auther_type" name="auther_type" value="1"/>
 <!-- 隐含字段区域　结束 -->
	  <table style="width:750px;"  class="table_form" border="0" cellpadding="0" cellspacing="0" id="xmk_table" name = "xmk_table" >
  <tr>
    <td colspan="6" style="font-size:14px; text-align:center;" ><B>项目信息管理</B></td>
  </tr>
  <tr>
    <td >项目名称</td>
    <td  colspan="4" style="text-align:left;"><input type="text" id="title" name="title"  /></td>
    
  </tr>
  <tr>
    <td>地区</td>
    <td  colspan="4" style="text-align:left;">
    	<select id="dq" name="dq" style="height:24px;line-height:24px;" >
    		<option value="">全部</option>
			<option value="新城区">新城区</option>
			<option value="莲湖区">莲湖区</option>
			<option value="灞桥区">灞桥区</option>
			<option value="阎良区">阎良区</option>
			<option value="雁塔区">雁塔区</option>
			<option value="长安区">长安区</option>
			<option value="周至县">周至县</option>
			<option value="蓝田县">蓝田县</option>
			<option value="高陵县">高陵县</option>
			<option value="碑林区">碑林区</option>
			<option value="未央区">未央区</option>
			<option value="户县">户县</option>
			<option value="临潼区">临潼区</option>
			<option value="高新区">高新区</option>
			<option value="曲江">曲江</option>
    	</select>
    </td>
  </tr>
  <tr>
    <td >行业</td>
    <td  colspan="4"  style="text-align:left;"><select id="hy" name="hy"  style="height:24px;line-height:24px;" >
		<option value="">全部</option>
		<option value="农林牧渔业">农林牧渔业</option>
		<option value="采矿业">采矿业</option>
		<option value="制造业">制造业</option>
		<option value="电力燃气及生产和供应业">电力燃气及生产和供应业</option>
		<option value="建筑业">建筑业</option>
		<option value="交通运输业/仓储/邮政业">交通运输业/仓储/邮政业</option>
		<option value="金融业">金融业</option>
		<option value="信息传输/计算机服务/软件业">信息传输/计算机服务/软件业</option>
		<option value="批发/零售业">批发/零售业</option>
		<option value="住宿/餐饮业">住宿/餐饮业</option>
		<option value="房地产业">房地产业</option>
		<option value="租赁/商务服务业">租赁/商务服务业</option>
		<option value="居民服务/其他服务行业">居民服务/其他服务行业</option>
		<option value="科学研究/技术服务/地质勘探业">科学研究/技术服务/地质勘探业</option>
		<option value="水利/环境/公共设施管理业">水利/环境/公共设施管理业</option>
		<option value="教育业">教育业</option>
		<option value="卫生/社会保障/社会福利业">卫生/社会保障/社会福利业</option>
		<option value="文化/体育/娱乐业">文化/体育/娱乐业</option>
		<option value="公共管理/社会组织">公共管理/社会组织</option>
		<option value="国际组织">国际组织</option>
	</select></td>
    
  </tr>
  <tr>
	 <td >企业名称</td>
    <td  colspan="4" style="text-align:left;"><input type="text" id="qy" name="qy"  /></td>
  </tr>
  <tr>
	<td >项目投资金额</td>
    <td  colspan="4" style="text-align:left;"><input type="text" id="xmtzje" name="xmtzje"  /></td>
  </tr>
  <tr>
	<td  >负责人</td>
    <td  colspan="4" style="text-align:left;"><input type="text" id="fzr" name="fzr"  /></td>
  </tr>
  <tr>
	<td>联系电话</td>
    <td colspan="4" style="text-align:left;"><input type="text" id="lxdh" name="lxdh"  /></td>
  </tr>
  <tr>
	<td  >公司介绍</td>
    <td  colspan="4" style="text-align:left;">
		<textarea id="gsjs" name="gsjs" style="width:630px;;height:250px;visibility:hidden;"></textarea>
	</td>
  </tr>
  <tr>
	<td  >项目描述</td>
    <td  colspan="4" style="text-align:left;">
		<textarea id="xmms" name="xmms" style="width:630px;;height:250px;visibility:hidden;"></textarea>
	</td>
  </tr>
  <tr>
	<td  >产品截图</td>
    <td  colspan="4" style="text-align:left;">
		<textarea id="cpjt" name="cpjt" style="width:630px;;height:250px;visibility:hidden;"></textarea>
	</td>
  </tr>
  <tr>
	<td  >备注</td>
    <td  colspan="4" style="text-align:left;">
		<textarea id="bz" name="bz" style="width:630px;;height:250px;visibility:hidden;"></textarea>
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
			<input  name="btn1" type="button" onclick="updateXmkData()"  value="保存" />	
			<input id="addButton" name="btn1" type="button" onclick="savePublishFlag(1)" value="发布" />	
			<input id="userAddReset" name="btn1" type="button" onclick="savePublishFlag(0)" value="撤消" />	
			<input id="userAddCancel" name="btn1" type="button" onclick="top.tab_colseOnclick(top.curTabIndex);" value="取消" />	
		</td>
	</tr>
</table>
<span class="blank3"></span>
</div>
</body>
</html>
