var JdyRPC = jsonrpc.JdyRPC;

var selectBean = null;//当前选中项对象
var val=new Validator();
var serarch_con = "";//查询条件
var tp = new TurnPage();
var beanList = null;
var table = new Table();	
table.table_name = "jdydiv";
var current_role_bean;
var is_button_click = true;//是否是点击的按钮触发事件
var m = new Map();

function reloadJdyList()
{
	showList();	
	showTurnPage();	
}

function initTable(){
	
	var colsMap = new Map();	
	var colsList = new List();	
	
	colsList.add(setTitleClos("name","姓名","100px","","",""));//英文名，显示名，宽，高，样式名，点击事件　
	colsList.add(setTitleClos("gender","性别","100px","","",""));
	colsList.add(setTitleClos("phone","电话","100px","","",""));
	colsList.add(setTitleClos("email","电子邮件","260px","","",""));
	colsList.add(setTitleClos("status","审核状态","60px","","",""));
	colsList.add(setTitleClos("space_col"," ","","","",""));

	table.setColsList(colsList);
	table.setAllColsList(colsList);				
	table.enableSort=false;//禁用表头排序
	table.onSortChange = showList;
	table.show("table");//里面参数为外层div的id
}

function showList(){
	if(status != "")
	{	
		m.put("status",status);
	}
	else
	{	
		m.remove("status");
	}
	m.put("start_num", tp.getStart());	
	m.put("page_size", tp.pageSize);
	m.put("orderby", "id desc");
	
	beanList = JdyRPC.getAllJdyList(m);//第一个参数为站点ID，暂时默认为空	
	beanList = List.toJSList(beanList);//把list转成JS的List对象	
	
	curr_bean = null;		
	table.setBeanList(beanList,"td_list");//设置列表内容的样式
	table.show("table");
	
	table.getCol("name").each(function(i){
		if(i>0)
		{
			$(this).html('<a href="javascript:openUpdateJdyPage('+beanList.get(i-1).id+')">'+beanList.get(i-1).name+'</a>');
		}
	});	
	
	table.getCol("status").each(function(i){
		
		if(i>0)
		{
			if(beanList.get(i-1).status == 0)
			{
				$(this).html("待审核");
			}else
			{
				$(this).html("已审核");
			}
		}
	});

	Init_InfoTable(table.table_name);
}

function showTurnPage(){	
	
	tp.total = JdyRPC.getJdyCount(m);
			
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
	
	top.addTab(true,"/manager/project/wzjc/view_jdy.jsp?id="+c_id+"&topnum="+top.curTabIndex,"监督员信息管理");
	
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
function deleteJdy()
{	
	var selectIDS = table.getSelecteCheckboxValue("id");
	var m = new Map();
	m.put("ids",selectIDS);
	if(JdyRPC.deleteJdy(m))
	{
		top.msgAlert("监督员信息"+WCMLang.Delete_success);		
		reloadJdyList();
	}
	else
	{
		top.msgWargin("监督员信息"+WCMLang.Delete_success);
	}
}

//审核操作
function publishJdy()
{
	var selectIDS = table.getSelecteCheckboxValue("id");
	var m = new Map();
	m.put("ids",selectIDS);
	
	if(JdyRPC.publishJdy(m))
	{
		top.msgAlert("监督员信息审核状态设置成功");
		reloadJdyList();
	}else
	{
		top.msgWargin("监督员信息审核状态设置失败，请重新操作");
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
	reloadJdyList();
}

//高级搜索
function highSearchHandl(con)
{
	table.con_value = con;
	reloadJdyList();
}




