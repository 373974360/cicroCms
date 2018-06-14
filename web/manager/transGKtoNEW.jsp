<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>时光门户管理系统</title>
<meta name="generator" content="cicro-Builder" />
<meta name="author" content="cicro" />
<meta http-equiv="X-UA-Compatible" content="IE=8" />
<link type="text/css" rel="stylesheet" href="styles/main.css" />
<link type="text/css" rel="stylesheet" href="styles/index.css" />
<link rel="stylesheet" type="text/css" href="styles/themes/default/tree.css">
<link rel="stylesheet" type="text/css" href="styles/themes/icon.css">
<link rel="stylesheet" type="text/css" href="js/jquery-ui/themes/base/jquery.ui.all.css"  />
	<script type="text/javascript" src="js/jquery.js"></script>
	<script type="text/javascript" src="js/jTimers.js"></script>
	<script type="text/javascript" src="js/jquery-plugin/jquery.cookie.js"></script>
	<script type="text/javascript" src="js/jquery-ui/jquery.effects.core.js"></script>
	<script type="text/javascript" src="js/jquery-ui/jquery.effects.blind.js"></script>
	<!--<script type="text/javascript" src="js/jquery-easyui/jquery.tree.js"></script>-->
	<script type="text/javascript" src="js/jquery-easyui/jquery.easyui.min.js"></script>

	<script type="text/javascript" src="js/jquery-ui/jquery.ui.core.js"></script>
	<script type="text/javascript" src="js/jquery-ui/jquery.ui.widget.js"></script>
	<script type="text/javascript" src="js/jquery-ui/jquery.ui.mouse.js"></script>
	<script type="text/javascript" src="js/jquery-ui/jquery.ui.button.js"></script>
	<script type="text/javascript" src="js/jquery-ui/jquery.ui.draggable.js"></script>
	<script type="text/javascript" src="js/jquery-ui/jquery.ui.position.js"></script>
	<script type="text/javascript" src="js/jquery-ui/jquery.ui.dialog.js"></script>
	<script type="text/javascript" src="js/tools.js"></script>
	<script type="text/javascript" src="js/sysUI.js"></script>
	<script type="text/javascript" src="js/java.js"></script>
	<script type="text/javascript" src="js/extend.js"></script>
	<script type="text/javascript" src="js/jsonrpc.js"></script>
	<script type="text/javascript" src="js/jquery.c.js"></script>
	<script type="text/javascript" src="js/common.js"></script>
	<script type="text/javascript" src="js/validator.js"></script>
	<script type="text/javascript" src="js/indexjs/indexList.js"></script>
	<script type="text/javascript" src="js/indexjs/tools.js"></script>	
 
<script type="text/javascript">

//idsTmp  0克隆，1引用，2链接	4725 4726
//--select info_id from cs_info where model_id = 14 and from_id = 0 and cat_id = 4989

//--insert into TEMP_INFO select info_id from cs_info where model_id = 14 and from_id = 0 and cat_id = 4989

//--select count(*) from CS_INFO_ARTICLE where info_id in(select id from TEMP_INFO)

//--insert  into cs_info_article select info_id,info_id,gk_content,1,0,0 from CS_GK_F_TYGS  right join  (select * from TEMP_INFO) news on CS_GK_F_TYGS.INFO_ID = news.ID 

//--update cs_info set model_id = 11 where INFO_ID in (select id from TEMP_INFO)

//--delete from TEMP_INFO



//捐赠救助
//	4735	工作动态	  489
//	4736	政策法规	  113 
//	4737	爱心救助      345 

$(document).ready(function(){

	var m = new Map();	
	m.put("page_size", 5000);
	m.put("start_num", 0);
	m.put("sort_name", "ci.id desc,ci.released_dtime");
	m.put("sort_type", "desc");	
	m.put("info_status", "8");
	m.put("final_status", "0");


	m.put("cat_id", "12223");   //公开目录id  12223   4661
	m.put("app_id", "zwgk");
	m.put("site_id", "GKweinan");	//公开站点id
	var info_list = jsonrpc.InfoBaseRPC.getInfoList(m);
	info_list = List.toJSList(info_list);
	//jsonrpc.InfoBaseRPC.infoGet(info_list,site_id,app_id,cat_id_for_get,idsTmp,isPublish,top.LoginUserBean.user_id);
	//HIWCMweinan -- 目标站点id
	//2159  目标栏目id
	//0   为克隆  0克隆，1引用，2链接	
	//true   是否直接发布
	//1 为登录用户id
	if(jsonrpc.InfoBaseRPC.infoGet(info_list,"HIWCMweinan","cms","2159",0,true,1))
	{
		alert("获取成功");
	}
			
});


</script>
</head>

<body>

	
</body>
</html>
