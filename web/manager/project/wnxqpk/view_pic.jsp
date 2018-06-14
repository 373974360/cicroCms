<%@ page contentType="text/html; charset=utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<meta name="generator" content="featon-Builder" />
<meta name="author" content="featon" />
<jsp:include page="../../include/include_tools.jsp"/>
<script type="text/javascript" src="/wcm.files/js/jwplayer/jwplayer.js"></script>
<script type="text/javascript" src="js/picList.js"></script>
<SCRIPT LANGUAGE="JavaScript">
	<!--
var topnum = request.getParameter("topnum");
var id = request.getParameter("id");
var audit_type = "${param.audit_type}";
var publish_type = "${param.publish_type}";

var defaultBean;
$(document).ready(function () {		
	
	if(audit_type == 0)
	{
		$(".audit").show();
	}
	if(audit_type == 1)
	{
		$(".publish").show();
	}

	initButtomStyle();
	init_input();
	
	if($.browser.msie&&$.browser.version=="6.0"&&$("html")[0].scrollHeight>$("html").height()) $("html").css("overflowY","scroll");
				
	if(id != null && id.trim() != "")
	{
		defaultBean = PicViewRPC.getPicViewNoteNote(id);

		if(defaultBean)
		{
			$("#picviewdiv").autoFill(defaultBean);	
			if(defaultBean.pic_type == 0)
			{
				showPicList();
			}
			if(defaultBean.pic_type == 1)
			{
				showVideo();
			}
		}		
	}
	
}); 

function showVideo()
{
	var str = '<div id="info_video">';
		str += '<div class="textCenter" style="width: 480px; height: 380px;margin:0 auto;">';
		str += '<div id="mediaplayer" class="textCenter">mediaplayer</div>';
		str += '</div></div>';
	$("#view_box").append(str);
	if(defaultBean.video_path.indexOf(".flv")>0 || defaultBean.video_path.indexOf(".mp4")>0)
	{
	  jwplayer("mediaplayer").setup({
		flashplayer: "/wcm.files/js/jwplayer/player.swf",
		file: defaultBean.video_path,
		image: "",
		//autostart:true,    //自动播放，默认不自动播放
		width:480,
		height:380
	  });
	}
	else
	{
	  jQuery("#mediaplayer").html('<embed id="live_video_embed" width="480" height="380" src="'+defaultBean.video_path+'" mediatype="video" autostart="false" loop="false" menu="true" sck_id="0" type="application/x-mplayer2" ></embed>');
	}
}

function showPicList()
{
	var l = defaultBean.pic_list;
	l = List.toJSList(l);
	if(l != null && l.size() > 0)
	{
		var str = "";
		for(var i=0;i<l.size();i++)
		{
			str += '<div><img src="'+l.get(i).pic_path+'"></div>';
		}
		$("#view_box").append(str);
	}
}

function savePublishFlag(publish_type)
{
	var selectIDS = table.getSelecteCheckboxValue("id");
	var m = new Map();
	m.put("publish_type",publish_type+"");
	m.put("ids",id);
	if(PicViewRPC.updatePicViewNoteStatus(m))
	{
		top.msgAlert("投稿信息"+WCMLang.Publish_success);
		top.getCurrentFrameObj(topnum).reloadPicViewList();
		top.tab_colseOnclick(top.curTabIndex);
	}else
	{
		top.msgWargin("投稿信息"+WCMLang.Publish_fail);
	}
}
//审核操作
function saveAuditFlag(audit_type)
{
	var m = new Map();
	m.put("audit_type",audit_type+"");
	m.put("ids",id);	
	
	if(PicViewRPC.updatePicViewNoteStatus(m))
	{
		top.msgAlert("投稿信息审核状态设置成功");
		top.getCurrentFrameObj(topnum).reloadPicViewList();
		top.tab_colseOnclick(top.curTabIndex);
	}else
	{
		top.msgWargin("投稿信息审核状态设置失败，请重新操作");
	}
}
	//-->
	</SCRIPT>	
</head>

<body>
<span class="blank12"></span>
<form id="form1" name="form1" action="#" method="post">
<div id="picviewdiv">
<!-- 隐含字段区域　开始 -->
 <input type="hidden" id="auther_type" name="auther_type" value="1"/>
 <!-- 隐含字段区域　结束 -->
<table id="gq_tab" class="table_form" border="0" cellpadding="0" cellspacing="0">
	<tbody>
		<tr>
			<th>标题：</th>
			<td id="title">				
			</td>
		</tr>		
		<tr>
			<th>作者：</th>
			<td id="editor">				
				<input id="gq_user" name="gq_user" type="text" class="width200" value="" />
			</td>
		</tr>		
		<tr>
			<th>手机号：</th>
			<td id="phone_number">
				<input id="gq_youxiao" name="gq_youxiao" type="text" class="width200" value="" />
			</td>
		</tr>
		<tr>
			<th style="vertical-align:top;">详细内容：</th>
			<td id="content">
				<input id="gq_mobile" name="gq_mobile" type="text" class="width200" value="" />
			</td>
		</tr>	
		<tr>
			<th></th>
			<td id="view_box">
				
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
			<input id="userAddReset" name="btn1"class="audit hidden"  type="button" onclick="saveAuditFlag(1)" value="审核通过" />
			<input id="userAddReset" name="btn1" class="audit hidden" type="button" onclick="saveAuditFlag(-1)" value="审核不通过" />
			<input id="addButton" name="btn1"class="publish hidden" type="button" onclick="savePublishFlag(1)" value="发布" />	
			<input id="addButton" name="btn1"class="publish hidden" type="button" onclick="savePublishFlag(0)" value="撤消" />
			<input id="userAddCancel" name="btn1" type="button" onclick="top.tab_colseOnclick(top.curTabIndex);" value="关闭" />	
		</td>
	</tr>
</table>
<span class="blank3"></span>

</form>
</body>
</html>
