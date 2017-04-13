var XmkRPC = jsonrpc.XmkRPC;
var XmkBean = new Bean("com.cicro.project.dz_xmk.XmkBean",true);
var selectBean = null;//当前选中项对象
var val=new Validator();
var serarch_con = "";//查询条件
var tp = new TurnPage();
var beanList = null;
var table = new Table();	
table.table_name = "xmkdiv";
var current_role_bean;
var is_button_click = true;//是否是点击的按钮触发事件
var m = new Map();

function reloadXmkList()
{
	showList();	
	showTurnPage();	
}

function initTable(){
	
	var colsMap = new Map();	
	var colsList = new List();	
	
	colsList.add(setTitleClos("title","项目名称","200px","","",""));//英文名，显示名，宽，高，样式名，点击事件　
	colsList.add(setTitleClos("hy","行业","170px","","",""));
	colsList.add(setTitleClos("dq","地区","150px","","",""));
	colsList.add(setTitleClos("xmtzje","项目投资金额 ","80px","","",""));
	colsList.add(setTitleClos("fzr","负责人","80px","","",""));
	colsList.add(setTitleClos("status","发布状态","100px","","",""));

	table.setColsList(colsList);
	table.setAllColsList(colsList);				
	table.enableSort=false;//禁用表头排序
	table.onSortChange = showList;
	table.show("table");//里面参数为外层div的id
}

function showList(){
	if(publish_flag != "")
		m.put("status",publish_flag);
	else
		m.remove("status");

	m.put("start_num", tp.getStart());	
	m.put("page_size", tp.pageSize);
	m.put("orderby", "id desc");
	
	beanList = XmkRPC.getXmkList(m);//第一个参数为站点ID，暂时默认为空	
	beanList = List.toJSList(beanList);//把list转成JS的List对象	
	
	curr_bean = null;		
	table.setBeanList(beanList,"td_list");//设置列表内容的样式
	table.show("table");
	
	table.getCol("title").each(function(i){
		if(i>0)
		{
			$(this).html('<a href="javascript:openUpdateXmkPage('+beanList.get(i-1).id+')">'+beanList.get(i-1).title+'</a>');
		}
	});	
	table.getCol("status").each(function(i){
		if(i>0)
		{
			if(beanList.get(i-1).publish_flag == "1")
			$(this).html("已发布");
			else
			$(this).html("未发布");
		}
	});	
	
	Init_InfoTable(table.table_name);
}

function showTurnPage(){	
	
	tp.total = XmkRPC.getXmkCount(m);
			
	tp.show("turn","");	
	tp.onPageChange = showList;
}


//打开修改窗口
function openUpdateXmkPage(id)
{
	var c_id;
	if(id != null)
	{
		c_id = id;
	}else
	{
		c_id = table.getSelecteCheckboxValue("id");
	}
	
	top.addTab(true,"/sys/project/dz_xmk/viewXmk.jsp?id="+c_id+"&topnum="+top.curTabIndex,"项目信息");
	
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
function deleteXmk()
{	
	var selectIDS = table.getSelecteCheckboxValue("id");
	var m = new Map();
	m.put("ids",selectIDS);
	if(XmkRPC.deleteXmk(m))
	{
		top.msgAlert("项目信息"+WCMLang.Delete_success);		
		reloadXmkList();
	}
	else
	{
		top.msgWargin("项目信息"+WCMLang.Delete_success);
	}
}
//发布操作
function publishXmk(publish_flag)
{
	var selectIDS = table.getSelecteCheckboxValue("id");
	var m = new Map();
	m.put("status",publish_flag+"");
	m.put("ids",selectIDS);
	m.put("pass_man",top.LoginUserBean.user_realname);
	
	if(XmkRPC.publishXmk(m))
	{
		top.msgAlert("项目信息"+WCMLang.Publish_success);
		reloadXmkList();
	}else
	{
		top.msgWargin("项目信息"+WCMLang.Publish_fail);
	}
}


function updateXmkData()
{	
	var bean = BeanUtil.getCopy(XmkBean);
	var bool = false;
	if(id != null && id != "")
	{
		bean.id = id;
		$("#xmk_table").autoBind(bean);
		bean.gsjs = KE.html("gsjs");
		bean.xmms = KE.html("xmms");
		bean.cpjt = KE.html("cpjt");
		bean.bz = KE.html("bz");
		bean.status = defaultBean.status;
		bool = XmkRPC.updateXmk(bean);
	}
	else
	{
		$("#xmk_table").autoBind(bean);
		bean.status = "0";
		bean.gsjs = KE.html("gsjs");
		bean.xmms = KE.html("xmms");
		bean.cpjt = KE.html("cpjt");
		bean.bz = KE.html("bz");
		bool = XmkRPC.insertXmk(bean);
	}
	if(bool)
	{
		top.msgAlert("项目信息保存成功");
		top.getCurrentFrameObj(topnum).reloadXmkList();
		top.tab_colseOnclick(top.curTabIndex);
	}else
	{
		top.msgWargin("项目信息保存失败，请重新操作");
	}
	
}

function addInfo()
{
	top.addTab(true,"/sys/project/dz_xmk/viewXmk.jsp?topnum="+top.curTabIndex,"项目信息");
}

//搜索
function SearchHandl(obj)
{
	var con_value = $(obj).parent().find("#searchkey").val();
	
	if((con_value.trim() == "" ||  con_value == null))
	{
		top.msgAlert(WCMLang.Search_empty);
		return;
	}
	m.put("keyword", con_value);
	reloadXmkList();
}

//高级搜索
function highSearchHandl(con)
{
	table.con_value = con;
	reloadXmkList();
}