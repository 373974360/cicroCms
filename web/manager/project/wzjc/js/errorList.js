var ErrorRPC = jsonrpc.ErrorRPC;
var InfoBaseRPC = jsonrpc.InfoBaseRPC;
var JdyRPC = jsonrpc.JdyRPC;
var UserLogin = jsonrpc.UserLoginRPC;
var LoginUserBean = UserLogin.getUserBySession();
var UserManRPC = jsonrpc.UserManRPC;
var selectBean = null;//当前选中项对象
var val=new Validator();
var serarch_con = "";//查询条件
var tp = new TurnPage();
var beanList = null;
var table = new Table();	
table.table_name = "errordiv";
var current_role_bean;
var is_button_click = true;//是否是点击的按钮触发事件
var m = new Map();

function reloadErrorList()
{
	showList();	
	showTurnPage();	
}

function initTable(){
	
	var colsMap = new Map();	
	var colsList = new List();	
	
	colsList.add(setTitleClos("error_title","标题","100px","","",""));//英文名，显示名，宽，高，样式名，点击事件　
	colsList.add(setTitleClos("error_url","错误地址","100px","","",""));
	colsList.add(setTitleClos("error_desc","错误描述","100px","","",""));
	colsList.add(setTitleClos("error_type","错误类型","260px","","",""));
	colsList.add(setTitleClos("jdy_id","监督员","80px","","",""));
	colsList.add(setTitleClos("name","提交用户","80px","","",""));
	colsList.add(setTitleClos("status","处理状态","80px","","",""));
	colsList.add(setTitleClos("user_id","处理用户","100px","","",""));
	colsList.add(setTitleClos("space_col"," ","","","",""));

	table.setColsList(colsList);
	table.setAllColsList(colsList);				
	table.enableSort=false;//禁用表头排序
	table.onSortChange = showList;
	table.show("table");//里面参数为外层div的id
}

function showList(){
	if(status != "")
		m.put("status",status);
	else
		m.remove("status");

	m.put("start_num", tp.getStart());	
	m.put("page_size", tp.pageSize);
	m.put("orderby", "id desc");
	
	beanList = ErrorRPC.getAllErrorList(m);//第一个参数为站点ID，暂时默认为空	
	beanList = List.toJSList(beanList);//把list转成JS的List对象	
	
	curr_bean = null;		
	table.setBeanList(beanList,"td_list");//设置列表内容的样式
	table.show("table");
	
	table.getCol("error_title").each(function(i){
		if(i>0)
		{ 
			var info_id = beanList.get(i-1).info_id;
			if(info_id != null && info_id != 0)
			{
				$(this).html('<a href="javascript:openUpdatePage('+info_id+')">'+beanList.get(i-1).error_title+'</a>');
			}
			else
			{
				$(this).html(beanList.get(i-1).error_title);
			}
		}
	});	
	table.getCol("jdy_id").each(function(i){
		if(i>0)
		{ 
			var jdy_id = beanList.get(i-1).jdy_id;
			if(jdy_id != null && jdy_id != 0)
			{
				var jdybean = JdyRPC.getJdyBean(jdy_id);
				$(this).html(jdybean.name);
			}
			else
			{
				$(this).html("");
			}
		}
	});	
	
	table.getCol("status").each(function(i){
		if(i>0)
		{
			if(beanList.get(i-1).status == 0)
			{
				$(this).html("待处理");
			}
			else if(beanList.get(i-1).status == 1)
			{
				$(this).html("已处理");
			}
			else 
			{
				$(this).html("不予处理");
			}
		}
	});
	table.getCol("user_id").each(function(i){
		if(i>0)
		{
			var user_id = beanList.get(i-1).user_id;
			if( user_id != null && user_id != 0)
			{
				$(this).html(UserManRPC.getUserRealName(user_id));
			}
			else
			{
				$(this).html("");
			}
		}
	});

	Init_InfoTable(table.table_name);
}

function showTurnPage(){	
	
	tp.total = ErrorRPC.getErrorCount(m);
			
	tp.show("turn","");	
	tp.onPageChange = showList;
}


//打开修改窗口
function openUpdateJdyPage(id)
{
	var c_id;
	if(id != null)
	{
		c_id = id;
	}else
	{
		c_id = table.getSelecteCheckboxValue("id");
	}
	
	top.addTab(true,"/manager/project/wzjc/view_error.jsp?id="+c_id+"&topnum="+top.curTabIndex,"纠错信息管理");
	
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
function deleteError()
{	
	var selectIDS = table.getSelecteCheckboxValue("id");
	var m = new Map();
	m.put("ids",selectIDS);
	m.put("user_id",LoginUserBean.user_id)
	if(ErrorRPC.deleteError(m))
	{
		top.msgAlert("纠错信息"+WCMLang.Delete_success);		
		reloadErrorList();
	}
	else
	{
		top.msgWargin("纠错信息"+WCMLang.Delete_success);
	}
}

//审核操作
function publishError(status)
{
	var selectIDS = table.getSelecteCheckboxValue("id");
	var m = new Map();
	m.put("ids",selectIDS);
	m.put("status",status);
	m.put("user_id",LoginUserBean.user_id)
	
	if(ErrorRPC.publishError(m))
	{
		top.msgAlert("纠错信息处理状态设置成功");
		reloadErrorList();
	}else
	{
		top.msgWargin("纠错信息处理状态设置失败，请重新操作");
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
	m.remove("status");
	reloadErrorList();
}

//高级搜索
function highSearchHandl(con)
{
	table.con_value = con;
	reloadErrorList();
}


function openUpdatePage(Infoid)
{
	var ifb = InfoBaseRPC.getInfoById(Infoid);
	if(ifb != null )
	{
		if(ifb.is_host == 1)
		{
			//引用信息只修改信息主表内容
			top.addTab(true,"/manager/cms/info/article/update_info.jsp?cid="+ifb.cat_id+"&info_id="+Infoid+"&site_id="+ifb.site_id+"&app_id="+ifb.app_id+"&model="+ifb.model_id+"&topnum="+top.curTabIndex,"维护信息");
		}
		else
		{
			top.addTab(true,"/manager/cms/info/article/"+getAddPagebyModel(ifb.model_id)+"?cid="+ifb.cat_id+"&info_id="+Infoid+"&site_id="+ifb.site_id+"&app_id="+ifb.app_id+"&model="+ifb.model_id+"&topnum="+top.curTabIndex,"维护信息");
		}
	}
}

function getAddPagebyModel(model_id)
{
	var add_page = jsonrpc.ModelRPC.getModelAddPage(model_id);
	if(add_page == "" || add_page == null)
		add_page = "m_article.jsp";
	return add_page;
}
