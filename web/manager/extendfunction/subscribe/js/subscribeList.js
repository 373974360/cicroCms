var table = new Table();	
table.table_name = "cs_dz_subscribeuser";

var SubscribeRPC = jsonrpc.SubscribeRPC;
var beanList = null;
var subscribeBeanList = null;

var tp = new TurnPage();
var current_page_num = 1;

var orderFeilds = null;//排序 
var highSearchString = null;
var m = new Map();
var i_time = 0;
var i_time_s;
var i_time_e;

var is_save_first_page = false;//操作成功后是否保存在第一页
var ids = "";

//加载初始化Table和翻页信息
function reloadSubscribeList()
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
	
	colsList.add(setTitleClos("mail_Address","邮件地址","","","","")); //英文名，显示名，宽，高，样式名，点击事件　
	colsList.add(setTitleClos("sub_time","订阅时间","","","",""));
	
	table.setColsList(colsList);
	table.setAllColsList(colsList);				
	table.enableSort=false;//禁用表头排序
	table.onSortChange = showList;
	table.show("subscribe_table");//里面参数为外层div的id
}

function showList()
{
	var sortCol = table.sortCol;
	var sortType = table.sortType;
	
	if(sortCol == "" || sortCol == null)
	{
		sortCol = "id";
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
	
	top.jsonrpc.SubscribeRPC.getsubscribeBeanList(showResultList,m);
}

function showResultList(result,e)
{
    if(e != null){return;}
    
    subscribeBeanList = List.toJSList(result);
	table.setBeanList(subscribeBeanList,"td_list");
	table.show("subscribe_table");
	
	table.getCol("mail_Address").each(function(i){
		if(i>0)
		{
			$(this).html(subscribeBeanList.get(i-1).mail_address);
		}
	});

	table.getCol("sub_time").each(function(i){
		if(i>0)
		{
			$(this).html(subscribeBeanList.get(i-1).sub_time);	
		}
	});

	Init_InfoTable(table.table_name);
}

function showTurnPage()
{	
     top.jsonrpc.SubscribeRPC.getSubscribeBeanListCount(showTurnPageResult);	
}

function showTurnPageResult(result,e)
{
    if(e != null){
        return;
	}
	tp.total = result;
	tp.show("subscribe_turn","");	
	tp.onPageChange = showList;		
}

//时间排序
function changeTimeSort(val)
{
	changeTimeSortHandl(val);
	reloadSubscribeList();
}
function changeTimeSortHandl(val)
{
	table.sortCol = "";
	table.sortType = "";
	
	switch(val)
	{
		case "1": table.sortCol="id";
				  table.sortType = "desc";
				  break;
		case "2": table.sortCol="id";
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
	reloadSubscribeList(); 
}

//修改
function updateSubscribeUserInfo()
{
	var selectIDS = table.getSelecteCheckboxValue("id");
	ids = selectIDS;
	top.jsonrpc.SubscribeRPC.getOldMailAddress(showOldMaillAddressResult,selectIDS);
	
	
}

function showOldMaillAddressResult(result,e)
{
	var oldMaillAddress = new List();
	oldMaillAddress = List.toJSList(result);
	var oldmail_Address = "";
	if(e != null){return;}
	for(var i=0; i<oldMaillAddress.size();i++){
		oldmail_Address = oldMaillAddress.get(i).mail_address;
	}
	top.OpenModalWindow("修改订阅信息","/manager/extendfunction/subscribe/updateSubUserInfoPage.jsp?id="+ids+"&mail_Address="+oldmail_Address,420,155);
}



//删除
function deleteSubscribeUser(){
	var selectIDS = table.getSelecteCheckboxValue("id");
	if(SubscribeRPC.deleteSubscribeUser(selectIDS))
	{
		top.msgAlert("信息"+WCMLang.Delete_success);
		reloadSubscribeList();
	}else{
		top.msgWargin("信息"+WCMLang.Delete_fail);
	}
}

//取消
function closecheckuser()
{
	top.CloseModalWindow();
}


