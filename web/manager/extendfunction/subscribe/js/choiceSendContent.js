/****** 取信息　开始 ******/
var GKNodeRPC = jsonrpc.GKNodeRPC;
var SubscribeRPC = jsonrpc.SubscribeRPC;
var count = 0;
var total = 0;
var pageSize = 20;
var pageNum = 1;
var serarch_con = "";//查询条件
var info_list;
var site_type = "";//选择的站点类型
var info_map =  new Map();//存储info对象
var node_name_map =  new Map();//存储公开对象
var m = new Map();	
m.put("step_id","100");
m.put("info_status", "8");
m.put("final_status", "0");
//m.put("is_host", "0");//获取的信息只能是主信息，不能是引用和链接型的

var date = new Date();
var str_id = "";
var conAdd_id = "";
var info_list = new List();
var i_list = new List();
var sendTitle = "";

function setScrollHandl()
{
	$('#scroll_div').scroll(function(){
		var o = $('#scroll_div');
		if (o.scrollTop()+o.height() > o.get(0).scrollHeight - 90)
		{
			if (window.loading ) return;
			if (window.show_more_lock) return;
			if (count >= total) return;
	
			pageNum++;
			getInfoList();
		}
	});
}

function getInfoCount()
{	
	if(cat_id != ""){
		m.put("cat_ids", cat_id);
	}
	if(app_id != ""){
		m.put("app_id", app_id);
	}
	if(site_id != ""){
		m.put("site_id", s_site_id);
	}
	
	if(serarch_con != ""){
		m.put("searchString", serarch_con);
	}
	if(isNaN(site_type) == false && site_type != "")
	{
		m.remove("site_id");
		m.remove("cat_ids");
		m.put("shared_cat_ids",cat_id);
		total = jsonrpc.GKInfoRPC.getGKInfoCount(m);
	}
	else
	{
		if(site_type.indexOf("shared_") > -1)
		{
			//取共享目录的数据
			m.remove("site_id");
			m.remove("cat_ids");
			m.put("shared_cat_ids",cat_id);
			total = InfoBaseRPC.getInfoCount(m);
		}else
			total = InfoBaseRPC.getInfoCount(m);
	}
	$("#checked_count").html( count>total ? total:count +"/"+total);
}

function getInfoList()
{	
	m.remove("shared_cat_ids");
	m.put("page_size", pageSize);
	m.put("start_num", pageSize*(pageNum-1)+"");
	m.put("sort_name", "ci.id desc,ci.released_dtime");
	m.put("sort_type", "desc");	
	
	if(cat_id != ""){
		m.put("cat_ids", cat_id);
	}
	if(app_id != ""){
		m.put("app_id", app_id);
	}
	if(site_id != ""){
		m.put("site_id", s_site_id);
	}
	
	if(serarch_con != ""){
		m.put("searchString", serarch_con);
	}	
	
	m.put("start_num", pageSize*(pageNum-1)+"");
	
	if(isNaN(site_type) == false && site_type != "")
	{
		//取共享目录的数据
		m.remove("site_id");
		m.remove("cat_ids");
		m.put("shared_cat_ids",cat_id);
		info_list = jsonrpc.GKInfoRPC.getGKInfoList(m);
	}else
	{
		if(site_type.indexOf("shared_") > -1)
		{
			//取共享目录的数据
			m.remove("site_id");
			m.remove("cat_ids");
			m.put("shared_cat_ids",cat_id);
			info_list = InfoBaseRPC.getInfoList(m);
		}
		else
			info_list = InfoBaseRPC.getInfoList(m);
	}
	info_list = List.toJSList(info_list);
	if(info_list != null && info_list.size() > 0)
	{
		count += info_list.size();
		var str = "";
		
		for(var i=0;i<info_list.size();i++)
		{
			info_map.put(info_list.get(i).info_id,info_list.get(i));
			var node_name = "";
			var n_name = "";
			if(info_list.get(i).site_id.indexOf("GK") > -1)
			{
				n_name = "";
				if(node_name_map.containsKey(info_list.get(i).site_id))
				{
					n_name = node_name_map.get(info_list.get(i).site_id);
				}else
				{
					n_name = GKNodeRPC.getNodeNameByNodeID(info_list.get(i).site_id);
					node_name_map.put(info_list.get(i).site_id,n_name);
				}
				node_name = "<span style='color:red'>["+n_name+"]</span>";
			}
			
			str += '<li><input type="'+input_type+'" name="infoList" value="'+info_list.get(i).info_id+'" />'+node_name+'<label url="'+info_list.get(i).content_url+'">'+info_list.get(i).title+'</label>&#160;&#160;&#160;&#160;<span>'+info_list.get(i).released_dtime.substring(5,16)+'</span></li>';
		}
		$("#data").append(str);
		init_input();	
	}
	$("#checked_count").html( count>total ? total:count +"/"+total);
}
/*******取信息　结束 *****/

//根据站点加载栏目树
function initCatTree(s_id){
	if(s_id == "zwgk" || isNaN(s_id) == false || s_id == "cms" || s_id.indexOf("shared_") > -1)
	{
		if(isNaN(s_id) == false || s_id.indexOf("shared_") > -1)
		{//走共享目录
			$("#tree_td_2").hide();
			$("#scroll_div").css("width","575px");
			cat_jdata = eval(CategoryRPC.getCategoryTreeByClassID(s_id.replace("shared_","")));
			setLeftMenuTreeJsonData(cat_jdata);
			initTreeClick();
		}
		else
		{
			$("#tree_td_2").show();
			$("#scroll_div").css("width","397px");
			if(s_id == "cms")
			{
				cat_jdata = eval(CategoryRPC.getAllowSharedSiteJSONStr(site_id));
				setLeftMenuTreeJsonData(cat_jdata);
				initCMSTreeClick();
			}
			else
			{
				cat_jdata = eval(GKNodeRPC.getGKNodeTreebyCateID(0));
				setLeftMenuTreeJsonData(cat_jdata);
				initZWGKTree();
			}
		}
	}
	else
	{
		$("#status_ul li").eq(0).show();
		$("#tree_td_2").hide();
		$("#scroll_div").css("width","575px");
		if(s_id == site_id)
		{
			top.jsonrpc.CategoryRPC.getCategoryTreeBySiteID(showResultCatTree,site_id);
		}else
		{
			if(site_id == "ggfw")
			{
				//公共服务取出对应站点的所有栏目，不按共享分
				top.jsonrpc.CategoryRPC.getCategoryTreeBySiteID(showResultCatTree,s_id);
			}
			else
				top.jsonrpc.CategoryRPC.getSharedCategoryTreeBySiteID(showResultCatTree,s_id,site_id);
		}
		
	}
}
function showResultCatTree(result,e){
	var cat_jdata = "";
	if(e != null){
		return;
	}
	cat_jdata = eval(result)
	setLeftMenuTreeJsonData(cat_jdata);
	initTreeClick();
}

function initCMSTreeClick()
{
	$('#leftMenuTree').tree({
		onClick:function(node){
			s_site_id = node.attributes.real_site_id;
			if(s_site_id == site_id)
			{
				top.jsonrpc.CategoryRPC.getCategoryTreeBySiteID(showResultList,s_site_id);
			}else
				top.jsonrpc.CategoryRPC.getSharedCategoryTreeBySiteID(showResultList,s_site_id,site_id);
		}
	});
}
function showResultList(result,e){
	if(e != null){
		return;
	}
	var cat_jdata;
	cat_jdata = eval(result);
	$("#leftMenuTree2").empty();
	setAppointTreeJsonData("leftMenuTree2",cat_jdata);
	initTreeClick2();
}

function initTreeClick2()
{
	$('#leftMenuTree2').tree({
		onClick:function(node){
			if(node.id > 0)
				clickCategoryNode(node.id); 
		}
	});
}

function initZWGKTree()
{
	$('#leftMenuTree').tree({
		onClick:function(node){
			if(node.iconCls == "icon-gknode")
			{	
				s_site_id = node.attributes.t_node_id;
				var jdata = eval(CategoryRPC.getCategoryTreeBySiteID(node.attributes.t_node_id));
				jdata = getTreeObjFirstNode(jdata);
				$("#leftMenuTree2").empty();
				setAppointTreeJsonData("leftMenuTree2",jdata);
			}
		}
	});
	$('#leftMenuTree2').tree({
		onClick:function(node){
			clickCategoryNode(node.id); 
		}
	});
}

//得到树中的第一个节点的所有子节点,为了不显示根节点
function getTreeObjFirstNode(cat_jdata)
{
	if(cat_jdata != null)
	{
		return cat_jdata[0].children;
	}
}

//初始化树
function initTreeClick()
{
	$('#leftMenuTree').tree({
		onClick:function(node){
			if(node.id > 0)
				clickCategoryNode(node.id); 
		}
	});
}

//点击栏目节点事件
function clickCategoryNode(id){
	getcheckInfoid();
	cat_id=id+"";
    
	m.remove("con_name");
	m.put("con_value");
	serarch_con = "";
	$("#searchTimes option").first().attr("selected",true);
	$("#data").empty();
	pageNum = 1;
	count = 0;
	
	getInfoList();
	getInfoCount();	
	
}

//保存
function related_ok(){
	sendTitle = $("#sendTitle").val();
	if(sendTitle == "" || sendTitle == "null"){
		top.msgAlert("发送标题不能为空");
		return;
	}
	getcheckInfoid();
	doGet();
}

//取消
function related_cancel(){
	top.CloseModalWindow();
	str_id = "";
}

function getcheckInfoid()
{
	$("#data :checked").each(function(){		
		i_list.add(info_map.get($(this).val()));
		if(str_id != ""){
			str_id = str_id +",";
		}
		str_id += info_map.get($(this).val()).info_id;
	});
}

//把选中的信息添加到表中
function doGet()
{	if(add_type == "conAdd") //表示在预览界面追加信息
	{	
		if(str_id != ""){
			top.jsonrpc.SubscribeRPC.insertAddSendInfo(showinsertAddSendInfo,record_id,str_id);
		}else{
			top.msgAlert("您没有选择追加信息");
		}
	}else{
		insertSendRecord();
	}
	
}

function insertSendRecord(result, e)
{
	if(e != null){return;}	
	if(str_id != "")
	{
		if(SubscribeRPC.insertSendRecord(send_user,sendTitle) && SubscribeRPC.insertSendContentRecord(str_id))
		{
			str_id = "";   //清空记录的info_id
			top.msgAlert("记录添加成功");
			top.getCurrentFrameObj().reloadSubRecordList();
			top.CloseModalWindow();
		}else{
			top.msgAlert("记录添加失败");
		}
	}else{
		top.msgAlert("您没有选择发送信息");
	}
} 

function showinsertAddSendInfo(result,e)
{
	if(e != null){return;}
	if(result)
	{
		str_id = ""; 
		top.msgAlert("追加信息成功");
		top.CloseModalWindow();
	}else{
		str_id = ""; 
		top.msgAlert("追加信息失败");
	}
}

//选择查询时间
function changeTimes(){
	var cf = $("#searchTimes").val();
	if(cf == "0b"){
		serarch_con = "";
	}else{
		serarch_con = " searchType_"+cf;
	}

	m.remove("con_name");
	m.put("con_value");		
	$("#data").empty();
	pageNum = 1;
	count = 0;

	getInfoList();
	getInfoCount();
}

//切换站点事件
function changeSiteId(val){
	getcheckInfoid();
	$("#leftMenuTree2").empty();
	site_type = val;
	if(val != "")
	{
		s_site_id = val;
		initCatTree(val);
		cat_id = $("#leftMenuTree div").eq(0).attr("node-id");	
		
		$("#status_ul li").eq(1).find(":radio").attr("checked",true);
		$("#status_ul li").eq(0).hide();
		//treeNodeSelected(cat_id);
	}else
	{
		$("#status_ul li").eq(0).show();
		$("#tree_td_2").show();
		$("#scroll_div").css("width","397px");
		s_site_id = site_id;
		var cat_jdata = CategoryRPC.getCategoryTreeBySiteID(site_id);
		var zt_jdata = CategoryRPC.getZTCategoryTreeJsonStr(site_id);
		var new_jdata = "";
		if(zt_jdata != "" && zt_jdata != null && zt_jdata != "[]")
		{
			cat_jdata = cat_jdata.substring(1,cat_jdata.length-1);
			zt_jdata = zt_jdata.substring(1,zt_jdata.length-1);
			new_jdata = eval("["+cat_jdata+","+zt_jdata+"]");
		}else
		{
			new_jdata = eval(cat_jdata);
		}
		setAppointTreeJsonData("leftMenuTree2",new_jdata);		
		initTreeClick2();		
	}	
	m.remove("con_name");
	m.put("con_value");
	serarch_con = "";
	$("#searchTimes option").first().attr("selected",true);
	$("#data").empty();
	pageNum = 1;
	count = 0;
	getInfoList();
	getInfoCount();		
}

//点击搜索事件
function doSearchInfoForGet(){
	var words = $("#searchkey").val();
	if(words != "" && words != null)
	{
		m.put("con_name", "title");
		m.put("con_value", words);
	}
	$("#data").empty();
	pageNum = 1;
	count = 0;
	getInfoList();
	getInfoCount();	
}

