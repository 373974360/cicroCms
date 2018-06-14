<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>图片管理</title>
<meta name="generator" content="featon-Builder" />
<meta name="author" content="featon" />
<jsp:include page="../../include/include_tools.jsp"/>
<script type="text/javascript" src="js/picList.js"></script>
<script type="text/javascript">
var id = request.getParameter("id");

var audit_type = "${param.audit_type}";
var publish_type = "${param.publish_type}";
var is_recommend = "${param.is_recommend}";
$(document).ready(function(){
	if(audit_type == 0)
	{
		$(".audit").show();
	}
	if(audit_type == 1)
	{
		$(".publish").show();
	}
	if(publish_type == 1 || is_recommend == 1)
	{
		$(".recommend").show();
	}

	initButtomStyle();
	init_FromTabsStyle();
	if($.browser.msie&&$.browser.version=="6.0"&&$("html")[0].scrollHeight>$("html").height()) $("html").css("overflowY","scroll"); 

	initTable();
	reloadPicViewList();	

	
});

function fnPublichSearch(val)
{
	publish_flag = val;
	m.remove("keyword");
	reloadPicViewList();
}

function fnTypeSearch(val)
{
	gq_type = val;
	m.remove("keyword");
	reloadPicViewList();
}

function showListForAudit(type)
{
	audit_type = type;
	m.remove("keyword");
	reloadPicViewList();
}


</script>
</head>

<body>
<div>
	<table class="table_option" border="0" cellpadding="0" cellspacing="0" >
		<tr>		
			<td class="fromTabs">
				<input id="btn3" name="btn3" class="audit hidden" type="button" onclick="publicSelectCheckbox(table,'id','passPicView(1)')" value="审核通过" />
				<input id="btn3" name="btn3" class="audit hidden" type="button" onclick="publicSelectCheckbox(table,'id','passPicView(-1)')" value="审核不通过" />
				<input id="btn3" name="btn3" class="publish hidden" type="button" onclick="publicSelectCheckbox(table,'id','publishPicView(1)')" value="发布" />
				<input id="btn3" name="btn3" class="publish hidden" type="button" onclick="publicSelectCheckbox(table,'id','publishPicView(0)')" value="撤消" />
				<input id="btn3" name="btn3" class="recommend hidden" type="button" onclick="publicSelectCheckbox(table,'id','recommendPicView(1)')" value="推荐" />
				<input id="btn3" name="btn3" class="recommend hidden" type="button" onclick="publicSelectCheckbox(table,'id','recommendPicView(0)')" value="撤消推荐" />
				<input id="btn3" name="btn3" type="button" onclick="deleteRecord(table,'id','deletePicView()');" value="删除" />

				<select class="audit hidden"  onchange="showListForAudit(this.value)">
					<option value="0">未审核</option>
					<option value="-1">审核未通过</option>
				</select>				
				<span class="blank3"></span>
			</td>
			<td align="right" valign="middle" id="dept_search" class="search_td fromTabs" >				
				<input id="searchkey" type="text" class="input_text" value=""  /><input id="btnSearch" type="button" class="btn x2" value="搜索" onclick="SearchHandl(this)"/>
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
			<input id="btn3" name="btn3" class="audit hidden" type="button" onclick="publicSelectCheckbox(table,'id','passPicView(1)')" value="审核通过" />
				<input id="btn3" name="btn3" class="audit hidden" type="button" onclick="publicSelectCheckbox(table,'id','passPicView(-1)')" value="审核不通过" />
				<input id="btn3" name="btn3" class="publish hidden" type="button" onclick="publicSelectCheckbox(table,'id','publishPicView(1)')" value="发布" />
				<input id="btn3" name="btn3" class="publish hidden" type="button" onclick="publicSelectCheckbox(table,'id','publishPicView(0)')" value="撤消" />
				<input id="btn3" name="btn3" class="recommend hidden" type="button" onclick="publicSelectCheckbox(table,'id','recommendPicView(1)')" value="推荐" />
				<input id="btn3" name="btn3" class="recommend hidden" type="button" onclick="publicSelectCheckbox(table,'id','recommendPicView(0)')" value="撤消推荐" />
				<input id="btn3" name="btn3" type="button" onclick="deleteRecord(table,'id','deletePicView()');" value="删除" />
		</td>
	</tr>
   </table>	
</div>
</body>
</html>