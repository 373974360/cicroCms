<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>短信维护</title>
<meta name="generator" content="deya-Builder" />
<meta name="author" content="deya" />
<jsp:include page="../../../include/include_tools.jsp"/>

<script type="text/javascript" src="js/enterpriseList.js"></script>
<script type="text/javascript">
var topnum = "${param.topnum}";
var id = "${param.id}";

$(document).ready(function(){
	

	initButtomStyle();
	init_input();
	
	if(id != "" && id != null)
	{
		var nm = new Map();
		nm.put("id",id);
		var defaultBean = WZJEnterpriseRPC.getEnterpriseBean(nm);
		if(defaultBean)
		{
			$("#sms_table").autoFill(defaultBean);				
		}
	}
});

function auditEnterprise2(flag)
{		
	var delMap = new Map();
	delMap.put("id",id);
	delMap.put("is_audit",flag+"");
	
	if(WZJEnterpriseRPC.auditEnterprise(delMap))
	{
		top.msgAlert("审核状态"+WCMLang.Set_success);
		top.getCurrentFrameObj(topnum).reloadList();
		top.tab_colseOnclick(top.curTabIndex);
	}else
	{
		top.msgWargin("审核状态"+WCMLang.Set_fail);
	}	
}
</script>
</head>

<body>
<span class="blank12"></span>
<form id="form1" name="form1" action="#" method="post">
<table id="sms_table" class="table_form" border="0" cellpadding="0" cellspacing="0">
	<tbody>
		<tr>
			<th>公司名称：</th>
			<td id="cname"></td>
		</tr>			
		<tr>
			<th>营业执照号：</th>
			<td id="yyzzid"></td>
		</tr>
		<tr>
			<th>法人：</th>
			<td id="frname"></td>
		</tr>
		<tr>
			<th>单位性质：</th>
			<td id="dwxz"></td>
		</tr>
		<tr>
			<th>所属行业：</th>
			<td id="sshy"></td>
		</tr>		
		<tr>
			<th>注册资金：</th>
			<td id="zczj"></td>
		</tr>
		<tr>
			<th>经营范围：</th>
			<td id="jyfw"></td>
		</tr>
		<tr>
			<th>公司规模：</th>
			<td id="gsgm"></td>
		</tr>
		<tr>
			<th>联系人：</th>
			<td id="lxr"></td>
		</tr>
		<tr>
			<th>联系电话：</th>
			<td id="phone"></td>
		</tr>
		<tr>
			<th>传真：</th>
			<td id="fax"></td>
		</tr>
		<tr>
			<th>邮箱地址：</th>
			<td id="email"></td>
		</tr>
		<tr>
			<th>公司地址：</th>
			<td id="address"></td>
		</tr>
		<tr>
			<th>公司邮编：</th>
			<td id="postcode"></td>
		</tr>
		<tr>
			<th>公交路线：</th>
			<td id="bus"></td>
		</tr>		
		<tr>
			<th>公司网址：</th>
			<td id="siteurl"></td>
		</tr>
		<tr>
			<th>公司简介：</th>
			<td id="aboutc"></td>
		</tr>
	</tbody>
</table>
<span class="blank12"></span>
<div class="line2h"></div>
<span class="blank3"></span>
<table class="table_option" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td align="left" valign="middle" style="text-indent:100px;">
			<input id="userAddCancel" name="btn1" type="button" onclick="auditEnterprise2(1);" value="审核通过" />
			<input id="userAddCancel" name="btn1" type="button" onclick="auditEnterprise2(-1);" value="审核不通过" />
			<input id="userAddCancel" name="btn1" type="button" onclick="top.tab_colseOnclick(top.curTabIndex);" value="关闭" />	
		</td>
	</tr>
</table>
<span class="blank3"></span>
</form>
</body>
</html>
