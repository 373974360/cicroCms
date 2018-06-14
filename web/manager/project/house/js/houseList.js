var HouseRPC = jsonrpc.HouseRPC;

var selectBean = null;//当前选中项对象
var val=new Validator();
var serarch_con = "";//查询条件
var tp = new TurnPage();
var beanList = null;
var table = new Table();	
table.table_name = "housediv";
var current_role_bean;
var is_button_click = true;//是否是点击的按钮触发事件
var m = new Map();

function reloadHouseList()
{
	showList();	
	showTurnPage();	
}

function initTable(){
	
	var colsMap = new Map();	
	var colsList = new List();	
	
	colsList.add(setTitleClos("bt","标题","200px","","",""));//英文名，显示名，宽，高，样式名，点击事件　
	colsList.add(setTitleClos("lxr","联系人","200px","","",""));
	colsList.add(setTitleClos("lxdh","联系方式","200px","","",""));
	colsList.add(setTitleClos("type","租赁类型","60px","","",""));
	colsList.add(setTitleClos("publish_flag","发布状态","60px","","",""));
	colsList.add(setTitleClos("publish_time","发布时间","120px","","",""));
	colsList.add(setTitleClos("space_col"," ","","","",""));

	table.setColsList(colsList);
	table.setAllColsList(colsList);				
	table.enableSort=false;//禁用表头排序
	table.onSortChange = showList;
	table.show("table");//里面参数为外层div的id
}

function showList(){
	if(publish_flag != "")
		m.put("publish_flag",publish_flag);
	else
		m.remove("publish_flag");

	if(type != "")
		m.put("type",type);
	else
		m.remove("type");

	m.put("start_num", tp.getStart());	
	m.put("page_size", tp.pageSize);
	m.put("orderby", "id desc");
	
	beanList = HouseRPC.getAllHouseList(m);//第一个参数为站点ID，暂时默认为空	
	beanList = List.toJSList(beanList);//把list转成JS的List对象	
	
	curr_bean = null;		
	table.setBeanList(beanList,"td_list");//设置列表内容的样式
	table.show("table");
	
	table.getCol("bt").each(function(i){
		if(i>0)
		{
			$(this).html('<a href="javascript:openUpdateHousePage('+beanList.get(i-1).id+')">'+beanList.get(i-1).bt+'</a>');
		}
	});		

	table.getCol("type").each(function(i){
		if(i>0)
		{
			if(beanList.get(i-1).type == 1)
			{
				$(this).html("出租");
			}
			if(beanList.get(i-1).type == 2)
			{
				$(this).html("求租");
			}
		}
	});
	table.getCol("publish_flag").each(function(i){
		
		if(i>0)
		{
			if(beanList.get(i-1).publish_flag == 0)
			{
				$(this).html("未发布");
			}else
			{
				$(this).html("已发布");
			}
		}
	});

	Init_InfoTable(table.table_name);
}

function showTurnPage(){	
	
	tp.total = HouseRPC.getHouseCount(m);
			
	tp.show("turn","");	
	tp.onPageChange = showList;
}


//打开修改窗口
function openUpdateHousePage(id)
{
	var c_id;
	if(id != null)
	{
		c_id = id;
	}else
	{
		c_id = table.getSelecteCheckboxValue("id");
	}
	
	top.addTab(true,"/manager/project/house/view_house.jsp?id="+c_id+"&topnum="+top.curTabIndex,"房屋租赁信息");
	
}

//得到所属应用的ID
function getAppIDS()
{
	var ids = "";
	$(":checkbox[checked]").each(function(){
		ids += ","+$(this).val();
	});
	if(ids != "")
		ids = ids.substring(1);

	return ids;
}


//删除信息
function deleteHouse()
{	
	var selectIDS = table.getSelecteCheckboxValue("id");
	var m = new Map();
	m.put("ids",selectIDS);
	if(HouseRPC.deleteHouse(m))
	{
		top.msgAlert("房屋租赁信息"+WCMLang.Delete_success);		
		reloadHouseList();
	}
	else
	{
		top.msgWargin("房屋租赁信息"+WCMLang.Delete_success);
	}
}
//发布操作
function publishHouse(publish_flag)
{
	var selectIDS = table.getSelecteCheckboxValue("id");
	var m = new Map();
	m.put("publish_flag",publish_flag+"");
	m.put("ids",selectIDS);
	m.put("pass_man",top.LoginUserBean.user_realname);
	
	if(HouseRPC.publishHouse(m))
	{
		top.msgAlert("房屋租赁信息"+WCMLang.Publish_success);
		reloadHouseList();
	}else
	{
		top.msgWargin("房屋租赁信息"+WCMLang.Publish_fail);
	}
}
//审核操作
function passHouse(publish_flag)
{
	var selectIDS = table.getSelecteCheckboxValue("id");
	var m = new Map();
	m.put("publish_flag",publish_flag+"");
	m.put("ids",selectIDS);
	m.put("pass_man",top.LoginUserBean.user_realname);
	
	if(HouseRPC.publishHouse(m))
	{
		top.msgAlert("房屋租赁信息审核状态设置成功");
		reloadHouseList();
	}else
	{
		top.msgWargin("房屋租赁信息审核状态设置失败，请重新操作");
	}
}


//搜索
function SearchHandl(obj)
{
	var con_value = $(obj).parent().find("#searchkey").val();
	if(con_value.trim() == "" ||  con_value == null)
	{
		top.msgAlert(WCMLang.Search_empty);
		return;
	}
	m.put("keyword", con_value);
	m.remove("publish_flag");
	m.remove("type");
	reloadHouseList();
}

//高级搜索
function highSearchHandl(con)
{
	table.con_value = con;
	reloadHouseList();
}




