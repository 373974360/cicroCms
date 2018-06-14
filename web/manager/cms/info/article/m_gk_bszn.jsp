<%@ page contentType="text/html; charset=utf-8"%>
<%
String cid = request.getParameter("cid");
String siteid = request.getParameter("site_id");
String infoid = request.getParameter("info_id");
String app_id = request.getParameter("app_id");
String model = request.getParameter("model");
if(cid == null || cid.equals("null")){
	cid = "0";
}
if(app_id == null || app_id.equals("null")){
	app_id = "0";
}
if(model == null || !model.matches("[0-9]*")){
	model = "0";
}
String topnum = request.getParameter("topnum");
if(topnum == null || topnum.trim().equals("") || topnum.trim().equals("null") ){
	topnum = "0";
}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>信息维护</title>
<jsp:include page="../include/include_info_tools.jsp"/>
<script type="text/javascript" src="js/m_gk_bszn.js"></script>
<script type="text/javascript">
<!--
var topnum = "<%=topnum%>";
var site_id = "<%=siteid%>";
var cid = "<%=cid%>";
var app_id = "<%=app_id%>";
var infoid = "<%=infoid%>";
var linksInfo = "";
var focusInfo = "";
var mFlag = false;

	$(document).ready(function(){		
		reloadPublicGKInfo();
		
		getDataList("gk_fwlb","gk_fwlb");

		if(infoid != "" && infoid != "null" && infoid != null){		
			defaultBean = ModelUtilRPC.select(infoid,site_id,"gkfbszn");
			if(defaultBean){
				$("#gk_f_bszn_table").autoFill(defaultBean);	
				setV("gk_bllc",defaultBean.gk_bllc);
				//setV("gk_jdjc",defaultBean.gk_jdjc);
				//setV("gk_zrzj",defaultBean.gk_zrzj);
				//setV("gk_wszx",defaultBean.gk_wszx);
				//setV("gk_wsbl",defaultBean.gk_wsbl);
				//setV("gk_ztcx",defaultBean.gk_ztcx);
				//setV("gk_wsts",defaultBean.gk_wsts);

				setV("gk_sxyj",defaultBean.gk_sxyj);
				setV("gk_bltj",defaultBean.gk_bltj);
				setV("gk_blsx",defaultBean.gk_blsx);
				setV("gk_sfyj",defaultBean.gk_sfyj);
				//setV("gk_zxqd",defaultBean.gk_zxqd);
				//setV("gk_bgsj",defaultBean.gk_bgsj);
				setV("gk_sfbz",defaultBean.gk_sfbz);

				//setV("gk_blshixian",defaultBean.gk_blshixian);
				setV("gk_memo",defaultBean.gk_memo);
				setV("gk_cclx",defaultBean.gk_cclx);

				publicReloadUpdateGKInfos();		
			}
			$("#addButton").click(updateInfoData);		
			mFlag = true;		
		}
		else
		{
			$("#addButton").click(addInfoData);
			mFlag = false;
		}
	});

	init_editer("gk_bllc");	
	//init_editer("gk_jdjc");
	//init_editer("gk_zrzj");
	//init_editer("gk_wszx");
	//init_editer("gk_wsbl");
	//init_editer("gk_ztcx");
	//init_editer("gk_wsts");

	init_editer("gk_sxyj");
	init_editer("gk_bltj");
	init_editer("gk_blsx");
	init_editer("gk_sfyj");
	//init_editer("gk_zxqd");
	//init_editer("gk_bgsj");
	init_editer("gk_sfbz");
	//init_editer("gk_blshixian");
	init_editer("gk_memo");
	init_editer("gk_cclx");
//-->
</script>
</head>

<body>
<span class="blank12"></span>
<form action="#" name="form1">
<div id="gk_f_bszn_table">
<jsp:include page="../include/include_public_gk.jsp"/>

<!-- 内容主体不同部分　开始 -->

<table id="" class="table_form" border="0" cellpadding="0" cellspacing="0">
	<tbody>
    	<tr>
			<th>事项类型：</th>
			<td>
				<select id="gk_fwlb" class="input_select" style="width:150px;">
					
				</select>
			</td>
		</tr>
		<tr>
			<th>办理依据：</th>
			<td>				
				<textarea id="gk_sxyj" name="gk_sxyj" style="width:90%;height:160px;visibility:hidden;"></textarea>
			</td>
		</tr>
		<tr>
			<th>行使层级：</th>
			<td>
				<input id="gk_gsfs" name="gk_gsfs" type="text" class="width350" maxlength="80" value="" />
			</td>
		</tr>
		<tr>
			<th>通办范围：</th>
			<td>
				<input id="gk_bldx" name="gk_bldx" type="text" class="width350" maxlength="80" value="" />
			</td>
		</tr>
    	<tr>
			<th>受理部门：</th>
			<td>
				<input id="gk_bsjg" name="gk_bsjg" type="text" class="width350" maxlength="80" value="" />
			</td>
		</tr>
        <tr>
			<th>实施主体性质：</th>
			<td>
				<input id="gk_jgwz" name="gk_jgwz" type="text" class="width350" maxlength="80" value="" />
			</td>
		</tr>
		<tr>
			<th>服务对象：</th>
			<td>
				<input id="gk_zrzj" name="gk_zrzj" type="text" class="width350" maxlength="80" value="" />
			</td>
		</tr>
        <tr>
			<th>受理条件：</th>
			<td>
				<textarea id="gk_bltj" name="gk_bltj" style="width:90%;height:160px;visibility:hidden;"></textarea>
			</td>
		</tr>
		<tr>
			<th>联办机构：</th>
			<td>
				<input id="gk_wsbl" name="gk_wsbl" type="text" class="width350" maxlength="80" value="" />
			</td>
		</tr>
		<tr>
			<th>承诺办结时限：</th>
			<td>
				<input id="gk_blshixian" name="gk_blshixian" type="text" class="width350" maxlength="80" value="" ></input>
			</td>
		</tr>
		<tr>
			<th>法定办结时限：</th>
			<td>
				<input id="gk_wszx" name="gk_wszx" type="text" class="width350" maxlength="80" value="" ></input>
			</td>
		</tr>
		<tr>
			<th>办理地点：</th>
			<td>
				<input id="gk_bgsj" name="gk_bgsj" type="text" class="width350" maxlength="80" value="" ></input>
			</td>
		</tr>
		<tr>
			<th>办理时间：</th>
			<td>
				<input id="gk_wsts" name="gk_wsts" type="text" class="width350" maxlength="80" value="" ></input>
			</td>
		</tr>
		<tr>
			<th>联系电话：</th>
			<td>
				<input id="gk_zxqd" name="gk_zxqd" type="text" class="width350" maxlength="80" value=""></input>
			</td>
		</tr>
		<tr>
			<th>监督电话：</th>
			<td>
				<input id="gk_jdjc" name="gk_jdjc" type="text" class="width350" maxlength="80" value=""></input>
			</td>
		</tr>
		<tr>
			<th>结果名称：</th>
			<td>
				<input id="gk_ztcx" name="gk_ztcx" type="text" class="width350" maxlength="80" value=""></input>
			</td>
		</tr>
		<tr>
			<th>结果样本：</th>
			<td>
				<textarea id="gk_memo" name="gk_memo" style="width:90%;height:160px;visibility:hidden;"></textarea>
			</td>
		</tr>
        <tr>
			<th>办理材料：</th>
			<td>
				<textarea id="gk_blsx" name="gk_blsx" style="width:90%;height:160px;visibility:hidden;"></textarea>
			</td>
		</tr>
		<tr>
			<th>办理流程：</th>
			<td>
				<textarea id="gk_bllc" name="gk_bllc" style="width:90%;height:160px;visibility:hidden;"></textarea>
			</td>
		</tr>
        <tr>
			<th>收费标准：</th>
			<td>				
				<textarea id="gk_sfbz" name="gk_sfbz" style="width:90%;height:160px;visibility:hidden;"></textarea>
			</td>
		</tr>
        <tr>
			<th>收费依据：</th>
			<td>				
				<textarea id="gk_sfyj" name="gk_sfyj" style="width:90%;height:160px;visibility:hidden;"></textarea>
			</td>
		</tr>
        <tr>
			<th>乘车路线：</th>
			<td>
				<textarea id="gk_cclx" name="gk_cclx" style="width:90%;height:160px;visibility:hidden;"></textarea>
			</td>
		</tr>
	</tbody>
</table>

<!-- 内容主体不同部分　结束 -->

<jsp:include page="../include/include_public_high_gk.jsp"/>

</div>

<!--隔线开始-->
<span class="blank12"></span>
<div class="line2h"></div>
<span class="blank3"></span>
<!--隔线结束-->
<table class="table_option" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td align="left" valign="middle" style="text-indent:100px;">
			<input id="addButton" name="btn1" type="button" onclick="" value="保存" />
			<input id="addReset" name="btn1" type="button" onclick="window.location.reload()" value="重置" />
			<input id="addCancel" name="btn1" type="button" onclick="top.tab_colseOnclick(top.curTabIndex)" value="取消" />	
		</td>
	</tr>
</table>
<span class="blank3"></span>
</div>
</form>
</body>
</html>
