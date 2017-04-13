<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>培训报名信息</title>
<meta name="generator" content="deya-Builder" />
<meta name="author" content="deya" />
<jsp:include page="../../include/include_tools.jsp"/>
<script type="text/javascript" src="js/pxxxBmList.js"></script>
<script type="text/javascript" src="/sys/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="/sys/js/kindeditor/kindeditor.js"></script>
<script type="text/javascript">
var id = request.getParameter("id");
$(document).ready(function(){	
	initButtomStyle();
	init_FromTabsStyle();
	if($.browser.msie&&$.browser.version=="6.0"&&$("html")[0].scrollHeight>$("html").height()) $("html").css("overflowY","scroll"); 

	initTable();
	reloadPxxxBmList();	

	var pxxxList = PxxxRPC.getAllPxxxList();//第一个参数为站点ID，暂时默认为空  
    pxxxList = List.toJSList(pxxxList);//把list转成JS的List对象  
    for(var i = 0; i < pxxxList.size(); i++)
    {
      var selObj = $("#pxid");
      var value = pxxxList.get(i).id;
      var text = pxxxList.get(i).pxmc;
      selObj.append("<option value='"+value+"'>"+text+"</option>");
    }

	
});


function fnPublichSearch(val)
{
	if(val != "")
	{
		m.remove("pxid");
		m.put("pxid", val);
	}
	else
	{
		m.remove("pxid");
	}
	reloadPxxxBmList();
}

function exportExcel()
{
	var url = PxxxBmRPC.exportPxxxBm(m);
	window.open(url);
}

function printPhoto()
{
	var pxid = $("#pxid").val();
	top.addTab(true,"/sys/project/dz_pxxx/printPhoto.jsp?pxid=" + pxid + "&topnum="+top.curTabIndex,"打印培训人员照片");
}

</script>
</head>

<body>
<div>
	<table class="table_option" border="0" cellpadding="0" cellspacing="0" >
		<tr>		
			<td class="fromTabs">
				
				选择培训名称：<select id="pxid" name="pxid" onchange="fnPublichSearch(this.value)"><option value="">全部</option></select>
				
				&nbsp;<input type="button" value="导出EXCEL" onclick="exportExcel()" name="btn1" class="btn x2">

				&nbsp;<input type="button" value="打印照片" onclick="printPhoto()" name="btn1" class="btn x2">
				
				<span class="blank3"></span>
			</td>
			<td align="right" valign="middle" id="dept_search" class="search_td fromTabs" >				
				<input id="searchkey" type="text" class="input_text" value=""  />
				<input id="btnSearch" type="button" class="btn x2" value="搜索" onclick="SearchHandl(this)"/>
				<span class="blank3"></span>
			</td>		
		</tr>
	</table>
	<span class="blank3"></span>
	<div id="table"></div><!-- 列表DIV -->
	<div id="turn"></div><!-- 翻页DIV -->
	<table class="table_option" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td align="left" valign="middle">
			<input id="btnAdd" type="button" class="btn x2" value="添加" onclick="addInfo()"/>
			<input id="btn3" name="btn3" type="button" onclick="deleteRecord(table,'id','deletePxxxBm()');" value="删除" />
		</td>
	</tr>
   </table>	
</div>
</body>
</html>