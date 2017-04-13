<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>全镇贫困户信息列表</title>
<meta name="generator" content="deya-Builder" />
<meta name="author" content="deya" />
<jsp:include page="../../include/include_tools.jsp"/>
<script type="text/javascript" src="js/pkhList.js"></script>
<script type="text/javascript" src="/manager/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
var id = request.getParameter("id");
var publish_flag = "0";
$(document).ready(function(){	
	initButtomStyle();
	init_FromTabsStyle();
	if($.browser.msie&&$.browser.version=="6.0"&&$("html")[0].scrollHeight>$("html").height()) $("html").css("overflowY","scroll"); 

	initTable();
	reloadPkhList();	

	
});

function fnPublichSearch(val)
{
	publish_flag = val;
	m.remove("keyword");
	reloadPkhList();
}

</script>
</head>

<body>
<div>
	<table class="table_option" border="0" cellpadding="0" cellspacing="0" >
		<tr style="height:32px;">		
			<td align="center" valign="middle" class="search_td" >	
				户主姓名：
				<input id="hzxm" type="hzxm" class="input_text" value=""  />
				身份证号：
				<input id="hzzjhm" type="hzzjhm" class="input_text" value=""  />
				联系电话：
				<input id="lxdh" type="lxdh" class="input_text" value=""  />
			</td>		
		</tr>
		<tr style="height:32px;">		
			<td align="center" valign="middle" class="search_td" >	
				户 属 性：
				<select id ="hsx" name ="hsx" style="width:128px;">
					<option value="" selected>全部</option>
			  		<option value="扶贫户">扶贫户</option>
			  		<option value="低保户">低保户</option>
			  		<option value="低保贫困户">低保贫困户</option>
			  		<option value="五保户">五保户</option>
			  	</select>
				致贫原因：
				<select id ="zyzpyy" name ="zyzpyy" style="width:128px;">
					<option value="" selected>全部</option>
			  		<option value="因病">因病</option>
			  		<option value="因残">因残</option>
			  		<option value="因灾">因灾</option>
			  		<option value="因学">因学</option>
			  		<option value="缺土地">缺土地</option>
			  		<option value="缺水">缺水</option>
			  		<option value="缺资金">缺资金</option>
			  		<option value="缺劳力">缺劳力</option>
			  		<option value="缺技术">缺技术</option>
			  		<option value="交通条件落后">交通条件落后</option>
			  		<option value="自身发展动力不足">自身发展动力不足</option>
			  		<option value="其它">其它</option>
			  	</select>
				脱贫标识：
				<select id ="tpbs" name ="tpbs" style="width:128px;">
					<option value="" selected>全部</option>
			  		<option value="未脱贫">未脱贫</option>
			  		<option value="脱贫">脱贫</option>
			  	</select>
			</td>		
		</tr>
		<tr style="height:38px;">		
			<td align="center" valign="middle" class="search_td fromTabs" >	
				<input id="btnSearch" type="button" class="btn x2" value="搜索" onclick="SearchHandl()"/>
			</td>		
		</tr>
	</table>
	<span class="blank5"></span>
	<div id="table"></div><!-- 列表DIV -->
	<div id="turn"></div><!-- 翻页DIV -->
	<table class="table_option" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td align="left" valign="middle">
			<input id="btn3" name="btn3" type="button" onclick="deleteRecord(table,'id','deletePkh()');" value="删除" />
		</td>
	</tr>
   </table>	
</div>
</body>
</html>