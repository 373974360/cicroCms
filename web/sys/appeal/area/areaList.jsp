<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>诉求分类管理</title>


<link rel="stylesheet" type="text/css" href="../../styles/themes/default/tree.css" />
<jsp:include page="../../include/include_tools.jsp"/>

<script type="text/javascript" src="../../js/jquery-easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="../../js/indexjs/tools.js"></script>
<script type="text/javascript" src="js/areaList.js"></script>
<script type="text/javascript">

var area_id = "1";
var parent_id = "0";
var jsonData;
var chold_jData;
var appList;

$(document).ready(function(){	
	initButtomStyle();
	init_FromTabsStyle();
	if($.browser.msie&&$.browser.version=="6.0"&&$("html")[0].scrollHeight>$("html").height()) $("html").css("overflowY","scroll"); 

	setLeftTreeHeight();
	showAreaTree();	
	 
	initTable();
	loadAreaTable();
	treeNodeSelected(area_id);
});

function loadAreaTable()
{	
	showList();
	showTurnPage();  
	Init_InfoTable(table.table_name);
}
//刷新树
function showAreaTree()
{
	json_data = eval(AreaRPC.getAreaTreeJsonStr());
	setLeftMenuTreeJsonData(json_data);
	initMenuTree();
}

function initMenuTree()
{
	$('#leftMenuTree').tree({		
		onClick:function(node){			
				changeAreaListTable(node.id);            
		}
	});
}

//点击树节点,修改菜单列表数据
function changeAreaListTable(o_id){
	area_id = o_id;
	loadAreaTable();
}




</script>
</head>

<body>
<table style="width:100%" border="0" cellpadding="0" cellspacing="0" >
	<tr>
	 <td width="200px" valign="top">
		<div id="leftMenuBox">
			<div id="leftMenu" class="contentBox6 textLeft" style="overflow:auto">
				<ul id="leftMenuTree" class="easyui-tree" animate="true">
				</ul>
			</div>
		</div>
	 </td>
	 <td class="width10"></td>
	 <td valign="top">
	   <div>		
		<div id="table"></div>
		<div id="turn"></div>
		<table class="table_option" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td align="left" valign="middle">
				<input id="btn1" name="btn1" type="button" onclick="openAddAreaPage();" value="新建地区" />
				<input id="btn1" name="btn1" type="button" onclick="updateRecord (table,'area_id','openUpdateAreaPage()');" value="修改" />
				<!-- 
				input id="btn3" name="btn3" type="button" onclick="publicSelectCheckbox(table,'area_id','openSelectSinglAreaPage(\'选择地区\',\'moveArea\')')" value="移动地区" />
				 -->
				<input id="btn3" name="btn3" type="button" onclick="sortRecord(table,'area_id','saveAreaSort()');" value="保存排序" />
				<input id="btn3" name="btn3" type="button" onclick="deleteRecord(table,'area_id','deleteArea()');" value="删除" />
			</td>
		</tr>  
	</table>
	 </td>
	</tr>
</table>
</div>
</body>
</html>
