<%@page contentType="text/html; charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<title>第三方excle数据导入</title>
<meta name="generator" content="cicro-Builder" />
<meta name="author" content="cicro" />
<link type="text/css" rel="stylesheet" href="/manager/styles/main.css" />
<link type="text/css" rel="stylesheet" href="/manager/styles/sub.css" />
<link type="text/css" rel="stylesheet" href="/manager/styles/uploadify.css" />
<script type="text/javascript" src="/manager/js/jquery.js"></script>
<script type="text/javascript" src="/manager/js/jsonrpc.js"></script>
<script type="text/javascript" src="/manager/js/java.js"></script>
<script type="text/javascript" src="/manager/js/extend.js"></script>
<script type="text/javascript" src="/manager/js/jquery.c.js"></script>
<script type="text/javascript" src="/manager/js/common.js"></script>
<script type="text/javascript" src="/manager/js/validator.js"></script>
<script type="text/javascript" src="/manager/js/lang/zh-cn.js"></script>
<script type="text/javascript" src="/manager/js/sysUI.js"></script>
<script type="text/javascript" src="/manager/js/jquery.uploadify.js"></script>
<script type="text/javascript" src="/manager/js/uploadFile/swfobject.js"></script>
<script type="text/javascript" src="/manager/js/uploadTools.js"></script>
<script type="text/javascript">
var MateInfoRPC = jsonrpc.MateInfoRPC;
var site_id = "<%=request.getParameter("site_id")%>";
$(document).ready(function(){
	initButtomStyle();
	init_input();
	loadExcelUpload();
});
var fileCount = 0;
function loadExcelUpload()
{
	$("#uploadify").uploadify( {//初始化函数
		'uploader' :'/manager/js/uploadFile/uploadify.swf',//flash文件位置，注意路径
	    'script' :'/servlet/PeculiarUploadFile',//后台处理的请求
		'cancelImg' :'/manager/js/uploadFile/cancel.png',//取消按钮图片
		'buttonImg': '/manager/js/uploadFile/upst.gif',
		'folder' :'uploads',//您想将文件保存到的路径
		'queueID' :'fileQueue',//与下面的上传文件列表id对应
		'queueSizeLimit' :1,//上传文件的数量
		//'scriptData':{'app_id':'appeal'},//向后台传的数据
		'fileDesc' :'xls',//上传文件类型说明
		'fileExt' :'*.xls', //控制可上传文件的扩展名，启用本项时需同时声明fileDesc
		'method':'get',//如果向后台传输数据，必须是get
		'sizeLimit':5242880,//文件上传的大小限制，单位是字节
		'auto' :true,//是否自动上传
		'multi' :false,
		'simUploadLimit' :1,//同时上传文件的数量
		'buttonText' :'BROWSE',//浏览按钮图片
		'onSelect':function(event,queueID,fileObj){//选择完后触发的事件
			
		   $("#uploadify").uploadifySettings('scriptData',{'app_id':'excleData','sid':MateInfoRPC.getUploadSecretKey()});
		   if(fileObj.size > 10000000){
				alert("附件过大，不允许上传！");
				return;
		   }		
		},
		'onSelectOnce':function(event,data){
			fileCount = data.fileCount;			
		},
		'onCancel':function(event,queueId,fileObj,data){
			fileCount = data.fileCount;
		},
		'onComplete': function(event,queueID,fileObj,serverData,response,data) {//当上传完成后的回调函数
			$("#file_url").val(serverData);
		}
   });
}
var updateType = false;

function setExcleItem(site_id)
{
	var file_url = $("#file_url").val();
	if(file_url == ""){
		top.msgAlert("请上传Excel文件！");
		return;
	}else{
		if(!updateType){
			 //加载图片
			var picStr = '<span class="blank9"></span><img style="display:" id="img" width="85" height="13"  style="vertical-align:top;" src="/manager/images/loading3.gif" /><span class="blank24"></span>';
			$("#table").html(picStr);
		}
		updateType = false;
		setTimeout(function(){
			jsonrpc.ExcleDataRPC.writeExcelData(setDateResult,file_url,site_id);	
		},12);
	}
}

function setDateResult(result,e)
{
    if(e != null){return;}
    if(result)
	{
    	updateType = true;
		top.msgAlert("操作"+WCMLang.Add_success);
		window.location.reload();
		$("#file_url").val("");
	}else
	{
		top.msgWargin("操作"+WCMLang.Add_fail);
	}
}
</script>
</head>
<body>
<span class="blank12"></span>
<form id="form1" name="form1" action="#" method="post">
<div id="queryExcel_table" style="width:700px;">
<table id="" class="table_form" border="0" cellpadding="0" cellspacing="0">
	<tbody>
		<tr>
			<th valign="top" align="right">Excel文件: </th>
			<td>
				 <div style="float:left;margin:auto;">
					 <input id="file_url" name="file_url" type="text" style="width:250px;" value=""/>
					 <div style="height:5px;"></div>
					 <div id="fileQueue"></div>
					 <input id="uploadify" name="uploadify" type="file" style="width:200px;height24px;"/>
					 <font class="">(只允许上传格式为:xls)</font>
				</div>
			</td>
		</tr>
	</tbody>
</table>
</div>
<!-- 分隔线开始 -->
<span class="blank12"></span>
<div id="table"></div>
<div class="line2h"></div>
<span class="blank3"></span>
<!-- 分隔线结束 -->
<table class="table_option" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td align="left" valign="middle" style="text-indent:100px;">
			<input id="addButton" name="btn1" type="button" onclick="setExcleItem(site_id)" value="保存"/>
			<input id="btnCancel" name="btn1" type="button" onclick="window.history.go(-1)" value="取消" />
		</td>
	</tr>
</table>
<span class="blank3"></span>
</form>
</body>		
</html>
