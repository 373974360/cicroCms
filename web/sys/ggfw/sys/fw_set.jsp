<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>公开系统配置</title>


<jsp:include page="../../include/include_tools.jsp"/>

<script type="text/javascript" src="js/gkNodeList.js"></script>
<script type="text/javascript">
var app_id = "ggfw";

var SiteRPC = jsonrpc.SiteRPC;
var SiteAppBean = new Bean("com.cicro.wcm.bean.control.SiteAppBean",true);
var defaultBean;
var site_id = "";
$(document).ready(function(){	
	initButtomStyle();
	init_input();
	
	var defaultBean = SiteRPC.getSiteAppBean(app_id);
	if(defaultBean != null)
	{
		$("#add_table").autoFill(defaultBean);
		site_id = defaultBean.site_id;
		$("#rela_site_id").val(defaultBean.site_id);
		$("#rela_site_name").val(SiteRPC.getSiteBeanBySiteID(defaultBean.site_id).site_name);

		$("#mark1_name").val(getTemplateNameByReleApp(defaultBean.mark1));
		$("#mark2_name").val(getTemplateNameByReleApp(defaultBean.mark2));
	}
});

function saveZWGKConfig()
{
	var bean = BeanUtil.getCopy(SiteAppBean);
	$("#add_table").autoBind(bean);

	bean.app_id = app_id;
	bean.site_id = $("#rela_site_id").val();
	if(jsonrpc.SiteRPC.insertSiteReleApp(bean)){
		if($("#mark2").val() != "" && $("#mark2").val() != null)
		{
			jsonrpc.SerRPC.updateSerTemplateContent($("#mark2").val());
		}
		top.msgAlert(WCMLang.Add_success);
	}else{		
			top.msgAlert(WCMLang.Add_fail);
	}
}


function getRelaSite(ids,names)
{
	$("#rela_site_name").val(names);
	$("#rela_site_id").val(ids);
}
</script>
</head>

<body>
<span class="blank12"></span>
<form id="form1" name="form1" action="#" method="post">
<table id="add_table" class="table_form" border="0" cellpadding="0" cellspacing="0">
	<tbody>		
		<tr>
			<th><span class="f_red">*</span>关联站点：</th>
			<td >
				<input id="rela_site_name" name="rela_site_name" 
					type="text" class="width300" value="" onblur="checkInputValue('rela_site_name',false,80,'关联站点','')" readOnly="readOnly"/>
				<input id="rela_site_id" name="rela_site_id" type="hidden" value=""/>
				<input id="bution4" name="bution4" type="button" onclick="openSelectSingleSite('选择关联站点','getRelaSite')" value="选择" />
			</td>			
		</tr>
		<tr>
			<th>相关信息列表页：</th>
			<td>				
				<input id="mark1_name" name="mark1_name" type="text" class="width300" value="" readOnly="readOnly"/>	
				<input type="hidden" id="mark1" class="width200" value=""/><input type="button" value="选择" onclick="openSelectTemplate('mark1','mark1_name',site_id)"/>
			</td>			
		</tr>
		<tr>
			<th>相关信息内容页：</th>
			<td>				
				<input id="mark2_name" name="mark2_name" type="text" class="width300" value="" readOnly="readOnly"/>	
				<input type="hidden" id="mark2" class="width200" value=""/><input type="button" value="选择" onclick="openSelectTemplate('mark2','mark2_name',site_id)"/>
			</td>			
		</tr>
	</tbody>
</table>
<span class="blank12"></span>
<div class="line2h"></div>
<span class="blank3"></span>
<table class="table_option" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td align="left" valign="middle" style="text-indent:100px;">
			<input id="addButton" name="btn1" type="button" onclick="saveZWGKConfig()" value="保存" />
		</td>
	</tr>
</table>
<span class="blank3"></span>
</form>
</body>
</html>
