var table = new Table();	
table.table_name = "cs_dz_subscribeuser";

var SubscribeRPC = jsonrpc.SubscribeRPC;
var beanList = null;
var subscribeBeanList = null;
var tp = new TurnPage();

var m = new Map();


//加载初始化Table和翻页信息
function reloadSubscribeList()
{
	showTurnPage();    //分页处理
	showList();
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

	m.put("sort_name", sortCol);
	m.put("sort_type", sortType);
	top.jsonrpc.SubscribeRPC.getchoiceuserBeanList(showResultList,m);
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

function openInfoPage(id){
	is_save_first_page = true;
	top.OpenModalWindow("信息获取","/manager/extendfunction/subscribe/choicesendContent.jsp?id="+id+"&site_id="+site_id+"&app_id="+app_id,800,530);
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
	tp.show("subscribe_turn","simple");	
	tp.onPageChange = showList;		
}

//取消
function closecheckuser()
{
	top.CloseModalWindow();
}

//发送信息
function selectSubUserForId()
{
	var m = new Map();
	var selectIDS = table.getSelecteCheckboxValue("id");
	m.put("selectIDS", selectIDS);
	top.jsonrpc.SubscribeRPC.getsubscribeBeanListForID(m,record_id,sendTitle);
	setTimeout("alertResult()", 1000);
	
}
function alertResult()
{
	top.msgAlert("发送成功");
	top.CloseModalWindow();
}



