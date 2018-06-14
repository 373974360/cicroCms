var GongQiuRPC = jsonrpc.GongQiuRPC;

var selectBean = null;//当前选中项对象
var val=new Validator();
var serarch_con = "";//查询条件
var tp = new TurnPage();
var beanList = null;
var table = new Table();	
table.table_name = "gongqidiv";
var current_role_bean;
var is_button_click = true;//是否是点击的按钮触发事件
var m = new Map();

function reloadGongQiuList()
{
	showList();	
	showTurnPage();	
}

function initTable(){
	
	var colsMap = new Map();	
	var colsList = new List();	
	
	colsList.add(setTitleClos("gq_title","标题","200px","","",""));//英文名，显示名，宽，高，样式名，点击事件　
	colsList.add(setTitleClos("gq_user","发布人","200px","","",""));
	colsList.add(setTitleClos("gq_mobile","手机","200px","","",""));
	colsList.add(setTitleClos("gq_type","供求类型","60px","","",""));
	colsList.add(setTitleClos("gq_publish_flag","发布状态","60px","","",""));
	colsList.add(setTitleClos("gq_dtime","提交时间","120px","","",""));
	colsList.add(setTitleClos("space_col"," ","","","",""));

	table.setColsList(colsList);
	table.setAllColsList(colsList);				
	table.enableSort=false;//禁用表头排序
	table.onSortChange = showList;
	table.show("table");//里面参数为外层div的id
}

function showList(){
	if(publish_flag != "")
		m.put("gq_publish_flag",publish_flag);
	else
		m.remove("gq_publish_flag");

	if(gq_type != "")
		m.put("gq_type",gq_type);
	else
		m.remove("gq_type");

	m.put("start_num", tp.getStart());	
	m.put("page_size", tp.pageSize);
	m.put("orderby", "gq_id desc");
	
	beanList = GongQiuRPC.getAllGongQiuList(m);//第一个参数为站点ID，暂时默认为空	
	beanList = List.toJSList(beanList);//把list转成JS的List对象	
	
	curr_bean = null;		
	table.setBeanList(beanList,"td_list");//设置列表内容的样式
	table.show("table");
	
	table.getCol("gq_title").each(function(i){
		if(i>0)
		{
			$(this).html('<a href="javascript:openUpdateGongQiuPage('+beanList.get(i-1).gq_id+')">'+beanList.get(i-1).gq_title+'</a>');
		}
	});		

	table.getCol("gq_type").each(function(i){
		if(i>0)
		{
			if(beanList.get(i-1).gq_type == 1)
			{
				$(this).html("供应");
			}
			if(beanList.get(i-1).gq_type == 2)
			{
				$(this).html("求购");
			}
		}
	});
	table.getCol("gq_publish_flag").each(function(i){
		if(i>0)
		{
			if(beanList.get(i-1).gq_publish_flag == 0)
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
	
	tp.total = GongQiuRPC.getGongQiuCount(m);
			
	tp.show("turn","");	
	tp.onPageChange = showList;
}


//打开修改窗口
function openUpdateGongQiuPage(id)
{
	var c_id;
	if(id != null)
	{
		c_id = id;
	}else
	{
		c_id = table.getSelecteCheckboxValue("gq_id");
	}
	
	top.addTab(true,"/manager/project/gq/gongqiu/view_gongqiu.jsp?gq_id="+c_id+"&topnum="+top.curTabIndex,"供求信息");
	
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


//删除供求
function deleteGongQiu()
{	
	var selectIDS = table.getSelecteCheckboxValue("gq_id");
	var m = new Map();
	m.put("gq_ids",selectIDS);
	if(GongQiuRPC.deleteGongQiu(m))
	{
		top.msgAlert("供求信息"+WCMLang.Delete_success);		
		reloadGongQiuList();
	}
	else
	{
		top.msgWargin("供求信息"+WCMLang.Delete_success);
	}
}
//发布操作
function publishGongQiu(gq_publish_flag)
{
	var selectIDS = table.getSelecteCheckboxValue("gq_id");
	var m = new Map();
	m.put("gq_publish_flag",gq_publish_flag+"");
	m.put("gq_ids",selectIDS);
	m.put("pass_man",top.LoginUserBean.user_realname);
	
	if(GongQiuRPC.publishGongQiu(m))
	{
		top.msgAlert("供求信息"+WCMLang.Publish_success);
		reloadGongQiuList();
	}else
	{
		top.msgWargin("供求信息"+WCMLang.Publish_fail);
	}
}
//审核操作
function passGongQiu(gq_publish_flag)
{
	var selectIDS = table.getSelecteCheckboxValue("gq_id");
	var m = new Map();
	m.put("gq_publish_flag",gq_publish_flag+"");
	m.put("gq_ids",selectIDS);
	m.put("pass_man",top.LoginUserBean.user_realname);
	
	if(GongQiuRPC.publishGongQiu(m))
	{
		top.msgAlert("供求信息审核状态设置成功");
		reloadGongQiuList();
	}else
	{
		top.msgWargin("供求信息审核状态设置失败，请重新操作");
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
	m.remove("gq_publish_flag");
	m.remove("gq_type");
	reloadGongQiuList();
}

//高级搜索
function highSearchHandl(con)
{
	table.con_value = con;
	reloadGongQiuList();
}




