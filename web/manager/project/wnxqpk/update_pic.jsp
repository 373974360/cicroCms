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
<link rel="stylesheet" type="text/css" href="/manager/styles/uploadify.css"/>
<script type="text/javascript" src="/manager/js/jquery.uploadify.js"></script>
<script type="text/javascript" src="/manager/js/uploadFile/swfobject.js"></script>
<script type="text/javascript" src="/manager/js/kindeditor/kindeditor.js"></script>
<script type="text/javascript" src="js/picList.js"></script>
<SCRIPT LANGUAGE="JavaScript">
	<!--
var topnum = request.getParameter("topnum");
var id = request.getParameter("id");
var audit_type = "${param.audit_type}";
var publish_type = "${param.publish_type}";
var site_id = top.current_site_id;

var defaultBean;
$(document).ready(function () {		
	publicUploadButtomLoads();

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

			if(defaultBean.pic_path != "")
			{
				$("#pic_path_view").html('<img style="width:180px" src="'+defaultBean.pic_path+'"/>');
			}
			if(defaultBean.pic_type == 0)
			{
				$("#video_tr").hide();
				showPicList();
				publicUploadButtomLoads2();
			}
			if(defaultBean.pic_type == 1)
			{
				$("#pic").hide();
				$("#video_tr").show();
				showVideo(defaultBean.video_path);
				publicUploadButtomLoadMedia()
			}
			KE.html("content",defaultBean.content);
		}	
		
	}
	
}); 

init_editer_turnpage("content");

var MateInfoRPC = jsonrpc.MateInfoRPC;
function publicUploadButtomLoads()
{
  //var imgDomain = MateInfoRPC.getImgDomain(site_id);
  $("#uploadify").uploadify({//初始化函数
      'uploader' :'/manager/js/uploadFile/uploadify.swf',//flash文件位置，注意路径
      'script' :'/servlet/UploadFileIfy',//后台处理的请求
    'cancelImg' :'/manager/js/uploadFile/cancel.png',//取消按钮图片
    'buttonImg': '/manager/js/uploadFile/upst.gif',
      'folder' :'folder',//您想将文件保存到的路径
      'queueID' :'fileQueue',//与下面的上传文件列表id对应
      'queueSizeLimit' :15,//上传文件的数量
      //'scriptData' :{'is_press':0,'press_osition':5,'site_id':site_id},//向后台传的数据
      'fileDesc' :'jpg,jpeg,gif,png,bmp',//上传文件类型说明
    'fileExt' :'*.jpg;*.jpeg;*.gif;*.png;*.bmp;', //控制可上传文件的扩展名,启用本项时需同时声明fileDesc
      'method':'get',//如果向后台传输数据，必须是get
      'sizeLimit':31457280,//文件上传的大小限制，单位是字节
      'auto' :true,//是否自动上传
      'multi' :true,
      'simUploadLimit' :1,//同时上传文件的数量
      'buttonText' :'BROWSE',//浏览按钮图片
    'onSelect':function(event,queueID,fileObj){//选择完后触发的事件
      
      $("#uploadify").uploadifySettings('scriptData',{'is_press':0,'press_osition':0,'site_id':site_id,'sid':MateInfoRPC.getUploadSecretKey()});
      if(fileObj.size > 31457280){
        alert("附件过大，不允许上传！");
        return;
      }
    },
    'onError':function(event,queueID,fileObj,errorObj){
      alert("文件:" +fileObj.name + "上传失败！");  
    },
      'onComplete': function(event,queueID,fileObj,response,data){
	  var att_path = "";  
      var att_ename = "";
      var hd_url ="";
      var thumb_url ="";
      var tt_att_ename = "";
      var tt_thumb_url = "";
      var tmpPicLi  = "";
      var objPath =  jQuery.parseJSON(response);
      var oldname = fileObj.name;
      att_path = objPath.att_path;
        att_ename = objPath.att_ename;
        hd_url = objPath.hd_url;
        thumb_url = objPath.thum_url;
       
      pic_url = att_path + att_ename;
      $("#pic_path").val(pic_url);
      $("#pic_path_view").html('<img style="width:180px" src="'+pic_url+'">');
       },
        'onAllComplete': function(event,data){
         
       }
   });
}

function publicUploadButtomLoads2()
{
  //var imgDomain = MateInfoRPC.getImgDomain(site_id);
  $("#uploadify2").uploadify({//初始化函数
      'uploader' :'/manager/js/uploadFile/uploadify.swf',//flash文件位置，注意路径
      'script' :'/servlet/UploadFileIfy',//后台处理的请求
    'cancelImg' :'/manager/js/uploadFile/cancel.png',//取消按钮图片
    'buttonImg': '/manager/js/uploadFile/upst.gif',
      'folder' :'folder',//您想将文件保存到的路径
      'queueID' :'fileQueue',//与下面的上传文件列表id对应
      'queueSizeLimit' :15,//上传文件的数量
      //'scriptData' :{'is_press':0,'press_osition':5,'site_id':site_id},//向后台传的数据
      'fileDesc' :'jpg,jpeg,gif,png,bmp',//上传文件类型说明
    'fileExt' :'*.jpg;*.jpeg;*.gif;*.png;*.bmp;', //控制可上传文件的扩展名,启用本项时需同时声明fileDesc
      'method':'get',//如果向后台传输数据，必须是get
      'sizeLimit':31457280,//文件上传的大小限制，单位是字节
      'auto' :true,//是否自动上传
      'multi' :true,
      'simUploadLimit' :1,//同时上传文件的数量
      'buttonText' :'BROWSE',//浏览按钮图片
    'onSelect':function(event,queueID,fileObj){//选择完后触发的事件
      
      $("#uploadify2").uploadifySettings('scriptData',{'is_press':0,'press_osition':0,'site_id':site_id,'sid':MateInfoRPC.getUploadSecretKey()});
      if(fileObj.size > 31457280){
        alert("附件过大，不允许上传！");
        return;
      }
    },
    'onError':function(event,queueID,fileObj,errorObj){
      alert("文件:" +fileObj.name + "上传失败！");  
    },
      'onComplete': function(event,queueID,fileObj,response,data){
	  var att_path = "";  
      var att_ename = "";
      var hd_url ="";
      var thumb_url ="";
      var tt_att_ename = "";
      var tt_thumb_url = "";
      var tmpPicLi  = "";
      var objPath =  jQuery.parseJSON(response);
      var oldname = fileObj.name;
      att_path = objPath.att_path;
        att_ename = objPath.att_ename;
        hd_url = objPath.hd_url;
        thumb_url = objPath.thum_url;
       
      pic_url = att_path + att_ename;

		var str = '<div style="margin:5px 0px"><div><img src="'+pic_url+'" style="width:300px"></div><div style="width:300px;text-align:center">&#160;<input type="button" onclick="deleteImg(this)" value="删除"/><input type="hidden" class="pic_path" value="'+pic_url+'"></div></div>';
		
		$("#view_box").append(str);
     
       },
        'onAllComplete': function(event,data){
         
       }
   });
}

function publicUploadButtomLoadMedia()
{
  //var imgDomain = MateInfoRPC.getImgDomain(site_id);
  $("#video_uploadify").uploadify({//初始化函数
      'uploader' :'/manager/js/uploadFile/uploadify.swf',//flash文件位置，注意路径
      'script' :'/servlet/UploadFileIfy',//后台处理的请求
    'cancelImg' :'/manager/js/uploadFile/cancel.png',//取消按钮图片
    'buttonImg': '/manager/js/uploadFile/upst.gif',
      'folder' :'folder',//您想将文件保存到的路径
      'queueID' :'fileQueue',//与下面的上传文件列表id对应
      'queueSizeLimit' :15,//上传文件的数量
      //'scriptData' :{'is_press':0,'press_osition':5,'site_id':site_id},//向后台传的数据
      'fileDesc' :'mp4,flv',//上传文件类型说明
     'fileExt'  :'*.mp4;*.flv',  //控制可上传文件的扩展名，启用本项时需同时声明fileDesc
      'method':'get',//如果向后台传输数据，必须是get
      'sizeLimit':314572800,//文件上传的大小限制，单位是字节
      'auto' :true,//是否自动上传
      'multi' :true,
      'simUploadLimit' :1,//同时上传文件的数量
      'buttonText' :'BROWSE',//浏览按钮图片
    'onSelect':function(event,queueID,fileObj){//选择完后触发的事件      
        $("#video_uploadify").uploadifySettings('scriptData',{'is_press':0,'press_osition':0,'site_id':site_id,'sid':MateInfoRPC.getUploadSecretKey()});
      if(fileObj.size > 314572800){
        alert("附件过大，不允许上传！");
        return;
      }
    },
    'onError':function(event,queueID,fileObj,errorObj){
      alert("文件:" +fileObj.name + "上传失败！");  
    },
      'onComplete': function(event,queueID,fileObj,response,data){//当上传完成后的回调函数，ajax方式哦~~
     
      var att_path = "";  
      var att_ename = "";
      var hd_url ="";
      var thumb_url ="";
      var tt_att_ename = "";
      var tt_thumb_url = "";
      var tmpPicLi  = "";
      var objPath =  jQuery.parseJSON(response);
      var oldname = fileObj.name;
      att_path = objPath.att_path;
        att_ename = objPath.att_ename;
        hd_url = objPath.hd_url;
        thumb_url = objPath.thum_url;
       
		$("#info_video").remove();
		pic_url = att_path + att_ename;
		showVideo(pic_url);
      
       },
        'onAllComplete': function(event,data){  
       }
   });
}

function showVideo(video_path)
{
	var str = '<div id="info_video">';
		str += '<div class="textCenter" style="width: 480px; height: 380px;margin:0 auto;">';
		str += '<div id="mediaplayer" class="textCenter">mediaplayer</div>';
		str += '</div></div>';
		str += '<input type="hidden" id="video_path" value="'+video_path+'">';
	$("#view_box").append(str);
	if(video_path.indexOf(".flv")>0 || video_path.indexOf(".mp4")>0)
	{
	  jwplayer("mediaplayer").setup({
		flashplayer: "/wcm.files/js/jwplayer/player.swf",
		file: video_path,
		image: "",
		//autostart:true,    //自动播放，默认不自动播放
		width:480,
		height:380
	  });
	}
	else
	{
	  jQuery("#mediaplayer").html('<embed id="live_video_embed" width="480" height="380" src="'+video_path+'" mediatype="video" autostart="false" loop="false" menu="true" sck_id="0" type="application/x-mplayer2" ></embed>');
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
			str += '<div style="margin:5px 0px"><div><img src="'+l.get(i).pic_path+'" style="width:300px"></div><div style="width:300px;text-align:center">&#160;<input type="button" onclick="deleteImg(this)" value="删除"/><input type="hidden" class="pic_path" value="'+l.get(i).pic_path+'"></div></div>';
		}
		$("#view_box").append(str);
	}
}

function deleteImg(obj)
{
	$(obj).parent().parent().remove();
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

function updatePic2()
{
	var m = new Map();
	if($("#hits").val() != "")
		m.put("hits_manual",$("#hits").val());
	m.put("ids",id);
	m.put("pic_path",$("#pic_path").val());
	m.put("content",KE.html("content"));
	m.put("pic_type",defaultBean.pic_type+"");

	if(defaultBean.pic_type == 0)
	{
		var pic_list = "";
		$(".pic_path").each(function(){
			pic_list += ","+$(this).val();
		});
		if(pic_list != "")
		{
			pic_list = pic_list.substring(1);
		}
		m.put("pic_list",pic_list);
	}

	if(defaultBean.pic_type == 1)
	{
		m.put("video_path",$("#video_path").val());
	}
	
	if(PicViewRPC.updatePicViewNote(m))
	{
		top.msgAlert("投稿信息保存成功");
		top.tab_colseOnclick(top.curTabIndex);
	}else
	{
		top.msgWargin("投稿信息保存失败，请重新操作");
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
				
			</td>
		</tr>		
		<tr>
			<th>手机号：</th>
			<td id="phone_number">
				
			</td>
		</tr>
		<tr>
			<th>点击数：</th>
			<td>
				<input id="hits" name="hits" type="text" class="width200" value="" onblur="checkInputValue('hits',true,20,'点击数','checkInt')"/>
			</td>
		</tr>
		<tr>
			<th>缩略图：</th>
			<td>				
				<div style="float:left"><input id="pic_path" name="pic_path" type="text" class="width200" value="" /></div>
                <div style="float:left">&#160;<input type="file"  name="uploadify" id="uploadify"/></div>
			</td>
		</tr>
		<tr>
			<th></th>
			<td>
				<div style="clear:both" id="pic_path_view"></div>
			</td>
		</tr>
		<tr>
			<th style="vertical-align:top;">详细内容：</th>
			<td>
				<textarea id="content" name="content" style="width:620px;;height:200px;visibility:hidden;"></textarea>
			</td>
		</tr>	
		<tr>
			<th></th>
			<td id="view_box">
				
			</td>
		</tr>
		<tr id="pic">
			<th>图片上传：</th>
			<td>
				<div>&#160;<input type="file"  name="uploadify2" id="uploadify2"/></div>
			</td>
		</tr>
		<tr id="video_tr" class="hidden">
			<th>视频上传：</th>
			<td>
				<div>&#160;<input type="file"  name="video_uploadify" id="video_uploadify"/></div>
			</td>
		</tr>
		<tr id="fileq_tr">
			  <th></th>
			  <td>
				  <div id="fileQueue"></div>
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
			<input id="addButton" name="btn1" type="button" onclick="updatePic2()" value="保存" />	
			<input id="userAddReset" name="btn1" type="button" onclick="formReSet('gq_tab',id)" value="重置" />
			<input id="userAddCancel" name="btn1" type="button" onclick="top.tab_colseOnclick(top.curTabIndex);" value="取消" />	
		</td>
	</tr>
</table>
<span class="blank3"></span>

</form>
</body>
</html>
