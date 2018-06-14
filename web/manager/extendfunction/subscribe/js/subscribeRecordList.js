var table = new Table();	
table.table_name = "cs_dz_subscribeRecord";

var SubscribeRPC = jsonrpc.SubscribeRPC;
var beanList = null;
var subRecordBeanList = null; 

var tp = new TurnPage();
var current_page_num = 1;

var orderFeilds = null;//排序 
var highSearchString = null;
var m = new Map();
var i_time_s = "";
var i_time_e = "";

var is_save_first_page = false;//操作成功后是否保存在第一页
var chechAlluserList = new List();
var sendAll_Address = "";


//加载初始化Table和翻页信息
function reloadSubRecordList()
{
	if(is_save_first_page == true){
		current_page_num = 1;
	}
	tp.curr_page = current_page_num;
	showTurnPage();    //分页处理
	showList();
	is_save_first_page = false;
}


// 初始化Table
function initTable(){
	var colsMap = new Map();	
	var colsList = new List();	
	
	//英文名，显示名，宽，高，样式名，点击事件　
	colsList.add(setTitleClos("send_user","发送人","","","","")); 
	colsList.add(setTitleClos("send_time","发送时间","","","",""));
	colsList.add(setTitleClos("user_operate","操作","","","",""));

	table.setColsList(colsList);
	table.setAllColsList(colsList);				
	table.enableSort=false;//禁用表头排序
	table.onSortChange = showList;
	table.show("subRecord_table");//里面参数为外层div的id
}

function showList()
{
	var sortCol = table.sortCol;
	var sortType = table.sortType;
	
	if(sortCol == "" || sortCol == null)
	{
		sortCol = "record_id";
	    sortType = "desc";
	}

	m.put("start_num", tp.getStart());	
	m.put("page_size", tp.pageSize);
	m.put("sort_name", sortCol);
	m.put("sort_type", sortType);
	
	if(i_time_s != "" &&  i_time_s != null && i_time_s != "null"){
		m.put("i_time_s",i_time_s+" 00:00:01");
	}
	if(i_time_e != "" &&  i_time_e != null && i_time_e != "null"){
		m.put("i_time_e",i_time_e+" 23:59:59");
	}
	top.jsonrpc.SubscribeRPC.getSubscribeRecordBeanList(showResultList,m);
}

function showResultList(result,e)
{
    if(e != null){return;}
    subRecordBeanList = List.toJSList(result);
	table.setBeanList(subRecordBeanList,"td_list");
	table.show("subRecord_table");
	
	table.getCol("send_user").each(function(i){
		if(i>0)
		{
			$(this).html(subRecordBeanList.get(i-1).send_user);
		}
	});
	
	table.getCol("send_time").each(function(i){
		if(i>0)
		{
			$(this).html(subRecordBeanList.get(i-1).send_time);	
		}
	});
	
	table.getCol("user_operate").each(function(i){
		if(i>0)
		{
			var title = subRecordBeanList.get(i-1).sendTitle;
			var str = '<a href=javascript:SendCheckAllUser('+subRecordBeanList.get(i-1).record_id+',"'+title+'")>全部发送</a>&#160;&#160;';
			str += '<a href=javascript:OpenModalCheckUser('+subRecordBeanList.get(i-1).record_id+',"'+title+'")>选择用户</a>&#160;&#160;';
			str += '<a href=javascript:OpenModalpreviewuser('+subRecordBeanList.get(i-1).record_id+',"'+title+'")>预览</a>';
			$(this).html(str);	
		}
	});                                                           
	
	Init_InfoTable(table.table_name);
}

//选择用户
function OpenModalCheckUser(record_id,sendTitle)
{
	top.OpenModalWindow("选择用户","/manager/extendfunction/subscribe/choicesendUser.jsp?record_id="+record_id+"&sendTitle="+sendTitle,900,500);
}

//全选
function SendCheckAllUser(record_id,sendTitle)
{
	top.jsonrpc.SubscribeRPC.getAllsubscribeBeanList(record_id,sendTitle);
	setTimeout("alert('发送成功')", 1000);
}


//预览
function OpenModalpreviewuser(record_id,sendTitle)
{
	top.OpenModalWindow("预览","/manager/extendfunction/subscribe/subscribePreview.jsp?record_id="+record_id+"&site_id="+site_id+"&app_id="+app_id+"&sendTitle="+sendTitle,800,500);
}

function showTurnPage()
{	
     top.jsonrpc.SubscribeRPC.getSubscribeRecordBeanListCount(showTurnPageResult);	
}

function showTurnPageResult(result,e)
{
    if(e != null){
        return;
	}
	tp.total = result;
	tp.show("subRecord_turn","");	
	tp.onPageChange = showList;		
}

//时间排序
function changeTimeSort(val)
{
	changeTimeSortHandl(val);
	reloadSubRecordList();
}
function changeTimeSortHandl(val)
{
	table.sortCol = "";
	table.sortType = "";
	
	switch(val)
	{
		case "1": table.sortCol="record_id";
				  table.sortType = "desc";
				  break;
		case "2": table.sortCol="record_id";
				  table.sortType = "asc";
				  break;
	}	
}

//搜索
function searchSubscribeInfo(){
	i_time_s = $("#i_time_s").val();
	i_time_e = $("#i_time_e").val();
	
	if(i_time_s == ""){
		top.msgAlert("开始时间不能为空！");
		return;
	}
	if(i_time_e == ""){
		top.msgAlert("结束时间不能为空！");
	}
	
	if(i_time_s != "" && i_time_s != null && i_time_e != "" && i_time_e != null)
	{
		if(judgeDate(i_time_e,i_time_s))
		{
			top.msgWargin("结束时间不能小于开始时间 !");
			return;
		}
	}	
	reloadSubRecordList(); 
}


//获取发送信息
function getSubscribeSendInfo()
{
	var add_type = "";var record_id = "";var sendTitle="";
	top.OpenModalWindow("信息获取","/manager/extendfunction/subscribe/choicesendContent.jsp?site_id="+site_id+"&app_id="+app_id+"&add_type="+add_type+"&recordid="+record_id+"&sendTitle="+sendTitle,800,530);
}
