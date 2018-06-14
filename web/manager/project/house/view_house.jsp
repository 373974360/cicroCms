<%@ page contentType="text/html; charset=utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<meta name="generator" content="featon-Builder" />
<meta name="author" content="featon" />
<link type="text/css" rel="stylesheet" href="/sys/styles/uploadify.css" />
<jsp:include page="../../include/include_tools.jsp"/>
<script type="text/javascript" src="js/houseList.js"></script>
<SCRIPT LANGUAGE="JavaScript">
	<!--
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
	
	if($.browser.msie&&$.browser.version=="6.0"&&$("html")[0].scrollHeight>$("html").height()) $("html").css("overflowY","scroll");
				
	if(id != null && id.trim() != "")
	{
		defaultBean = HouseRPC.getHouseBean(id,false);

		if(defaultBean)
		{
			$("#housediv").autoFill(defaultBean);
			$("#description").html(defaultBean.description);
					
		}		
	}
	//$("#housediv input").attr("disabled",true);  是否可编辑
}); 


function savePublishFlag(publish_flag)
{
	var m = new Map();
	m.put("publish_flag",publish_flag+"");
	m.put("ids",id);
	
	if(HouseRPC.publishHouse(m))
	{
		top.msgAlert("租赁信息发布状态设置成功");
		top.getCurrentFrameObj(topnum).reloadHouseList();
		top.tab_colseOnclick(top.curTabIndex);
	}else
	{
		top.msgWargin("租赁信息发布状态设置失败，请重新操作");
	}
}

	//-->
	</SCRIPT>	
</head>

<body>
<span class="blank12"></span>
<form id="form1" name="form1" action="#" method="post">
<div id="housediv">
<!-- 隐含字段区域　开始 -->
 <input type="hidden" id="auther_type" name="auther_type" value="1"/>
 <!-- 隐含字段区域　结束 -->
	<table width="100%" id="house_tab" class="table_form" border="0" cellpadding="0" cellspacing="0">
	  <tbody>
		  <tr>
			  <th width="100px" >小区名称：</th>
			  <td width="600px">
					<input name="xqmc" type="text" id="xqmc" value="" style="width:450px;" />
					
				</td>
			</tr>
			<tr>
			  <th scope="row" valign="top" >小区地址：</th>
			  <td>
					<input name="xqdz" type="text" id="xqdz" value=""  style="width:450px;"/>
					
				</td>
			</tr>
			<tr>
			  <th scope="row">租赁方式：</th>
			  <td>
					<input value="1" name="zlfs" type="radio" id="zlfs"  checked="checked" onclick="showHz(1)" />整租
					<input value="2" name="zlfs" type="radio" id="zlfs"  onclick="showHz(2)" />合租
				</td>
			</tr>
			<tr id="hzfs" class="displayNone">
			  <th scope="row">合租方式：</th>
			  <td>
			  <select id="hzfs_fj" name="hzfs_fj">
						<option value="1">主卧</option>
						<option value="2">次卧</option>
						<option value="3">床位</option>
						<option value="4">隔断间</option>
					</select>
							  
					<select  name="hzfs_xb" id="hzfs_xb">
						<option selected="selected" value="0">性别不限</option>
						<option value="1">限男生</option>
						<option value="2">限女生</option>
						<option value="3">限夫妻</option>
				  </select>
				</td>
			</tr>
			<tr>
			  <th scope="row">出租面积：</th>
				<td>
					<input name="czmj" type="text" id="czmj"/>平米
					
				</td>
			</tr>
			<tr>
			  <th scope="row">租金：</th>
				<td>
					<input name="zj" type="text" id="zj"/>元/月
					
				</td>
			</tr>
			<tr>
			  <th scope="row">支付方式：</th>
				<td>
					<select  name="zffs" id="zffs">
						<option selected="selected" value="押一付三">押一付三</option>
						<option value="1">押一付二</option>
						<option value="2">押一付一</option>
						<option value="3">押二付一</option>
						<option value="4">半年付</option>
						<option value="5">年付</option>
						<option value="6">面议</option>
				  </select>
				</td>
			</tr>
			<tr>
			  <th scope="row">户型：</th>
				<td>
					<input name="hx_s" type="text" id="hx_s" class="width30"/>室
					<input name="hx_t" type="text" id="hx_t" class="width30"/>厅
					<input name="hx_w" type="text" id="hx_w" class="width30"/>卫
					
				</td>
			</tr>
			<tr>
			  <th scope="row">楼层：</th>
				<td>
					<div style="float:left">第<input name="lc_zj" type="text" id="lc_zj" class="width30"/>层
					共<input name="lc_zg" type="text" id="lc_zg" class="width30"/>层
					
				</td>
			</tr>
			<tr>
			  <th scope="row">房屋状况：</th>
				<td>
					<select name="fwzk_zz" id="fwzk_zz">
						<option selected="selected" value="1">住宅</option>
						<option value="2">别墅</option>
						<option value="3">其他</option>
					</select>
					<select name="fwzk_cx" id="fwzk_cx">
						<option value="1">南北</option>
						<option value="2">南</option>
						<option value="3">东南</option>
						<option value="4">东</option>
						<option value="5">西南</option>
						<option value="6">北</option>
						<option value="7">西</option>
						<option value="8">东西</option>
						<option value="9">东北</option>
						<option value="10">西北</option>
					</select>
					<select name="fwzk_zx" id="fwzk_zx">
						<option value="1">豪华装修</option>
						<option value="2">精装修</option>
						<option value="3">中等装修</option>
						<option value="4" selected>简单装修</option>
						<option value="5">毛坯</option>
					</select>
				</td>
			</tr>            
			<tr>
			  <th valign="top" scope="row">配套设施：</th>
			  <td>
					<label><input name="ptss_c" type="checkbox" id="ptss_c" value="1" />床</label>
					<label><input name="ptss_jj" type="checkbox" id="ptss_jj" value="1" />家具</label>
					<label><input name="ptss_yxds" type="checkbox" id="ptss_yxds" value="1" />有线电视</label>
					<label><input name="ptss_xyj" type="checkbox" id="ptss_xyj" value="1" />洗衣机</label>
					<label><input name="ptss_kt" type="checkbox" id="ptss_kt" value="1" />空调</label>
					<label><input name="ptss_bx" type="checkbox" id="ptss_bx" value="1" />冰箱</label>
					<label><input name="ptss_rsq" type="checkbox" id="ptss_rsq" value="1" />热水器</label>
					<label><input name="ptss_kd" type="checkbox" id="ptss_kd" value="1" />宽带</label>
					<label><input name="ptss_dh" type="checkbox" id="ptss_dh" value="1" />电话</label>
					<br>
					<label><input name="ptss_cj" type="checkbox" id="ptss_cj" value="1" />厨具</label>
					<label><input name="ptss_mq" type="checkbox" id="ptss_mq" value="1" />煤气/天然气</label>
					<label><input name="ptss_nq" type="checkbox" id="ptss_nq" value="1" />暖气</label>
					<label><input name="ptss_dt" type="checkbox" id="ptss_dt" value="1" />电梯</label>
					<label><input name="ptss_cw" type="checkbox" id="ptss_cw" value="1" />车位</label>
					<label><input name="ptss_hy" type="checkbox" id="ptss_hy" value="1" />露台/花园</label>
					<label><input name="ptss_mdxs" type="checkbox" id="ptss_mdxs" value="1" />储藏室/地下室</label>
	</td>
			</tr>
		  <tr>
			  <th width="100px">标<span class="pl27"></span>题：</th>
				<td width="600px">
			  <input name="bt" type="text" id="bt"  style="width:450px;" />
			  
			</td>
			</tr>
			<tr>
			  <th valign="top" style="padding-top:5px;" scope="row">
				  <span>房源描述：</span><br />
				  <span>（可上传房源实景照，支持多张上传，最多可上传10张房源实景照。）</span>
				</th>
				<td>
				  <div class="blank9"></div>
			  <div style="float:left"><div name="description" id="description" class="textarea" style="width:450px;height:250px;overflow:auto;border:1px solid #9DBFDD;background:#fff;"></div>
			  
					<div class="blank9"></div>
			</td>
			</tr>
  <tr>
	<th width="100px">联 系 人：</th>
				<td width="600px">
					<input name="lxr" type="text" id="lxr" class="input" />
					
				</td>
			</tr>
			<tr>
	<th>联系电话：</th>
				<td>
					<input name="lxdh" type="text" id="lxdh" class="input wid105" />
					
				</td>
			</tr>
		</tbody>
	</table>  
</div>
<span class="blank12"></span>
<div class="line2h"></div>
<span class="blank3"></span>
<table class="table_option" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td align="left" valign="middle" style="text-indent:100px;">
			<input id="addButton" name="btn1" type="button" onclick="savePublishFlag(1)" value="发布" />	
			<input id="userAddReset" name="btn1" type="button" onclick="savePublishFlag(0)" value="撤消" />	
			<input id="userAddCancel" name="btn1" type="button" onclick="top.tab_colseOnclick(top.curTabIndex);" value="取消" />	
		</td>
	</tr>
</table>
<span class="blank3"></span>

</form>
</body>
</html>
