var PxxxRPC = jsonrpc.PxxxRPC;
var PxxxBean = new Bean("com.cicro.project.dz_pxxx.PxxxBean",true);
var selectBean = null;//当前选中项对象
var val=new Validator();
var serarch_con = "";//查询条件
var tp = new TurnPage();
var beanList = null;
var table = new Table();	
table.table_name = "pxxxdiv";
var current_role_bean;
var is_button_click = true;//是否是点击的按钮触发事件
var m = new Map();

function reloadPxxxList()
{
	showList();	
	showTurnPage();	
}

function initTable(){
	
	var colsMap = new Map();	
	var colsList = new List();	
	
	colsList.add(setTitleClos("pxmc","培训名称","300px","","",""));//英文名，显示名，宽，高，样式名，点击事件　
	colsList.add(setTitleClos("pxlx","培训类型","170px","","",""));
	colsList.add(setTitleClos("rsxz","人数限制","150px","","",""));
	colsList.add(setTitleClos("pxsj","培训时间 ","130px","","",""));

	table.setColsList(colsList);
	table.setAllColsList(colsList);				
	table.enableSort=false;//禁用表头排序
	table.onSortChange = showList;
	table.show("table");//里面参数为外层div的id
}

function showList(){

	m.put("start_num", tp.getStart());	
	m.put("page_size", tp.pageSize);
	m.put("orderby", "id desc");
	
	beanList = PxxxRPC.getPxxxList(m);//第一个参数为站点ID，暂时默认为空	
	beanList = List.toJSList(beanList);//把list转成JS的List对象	
	
	curr_bean = null;		
	table.setBeanList(beanList,"td_list");//设置列表内容的样式
	table.show("table");
	
	table.getCol("pxmc").each(function(i){
		if(i>0)
		{
			$(this).html('<a href="javascript:openUpdatePxxxPage('+beanList.get(i-1).id+')">'+beanList.get(i-1).pxmc+'</a>');
		}
	});	
	
	Init_InfoTable(table.table_name);
}

function showTurnPage(){	
	
	tp.total = PxxxRPC.getPxxxCount(m);
			
	tp.show("turn","");	
	tp.onPageChange = showList;
}


//打开修改窗口
function openUpdatePxxxPage(id)
{
	var c_id;
	if(id != null)
	{
		c_id = id;
	}else
	{
		c_id = table.getSelecteCheckboxValue("id");
	}
	
	top.addTab(true,"/sys/project/dz_pxxx/viewPxxx.jsp?id="+c_id+"&topnum="+top.curTabIndex,"培训信息");
	
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
function deletePxxx()
{	
	var selectIDS = table.getSelecteCheckboxValue("id");
	var m = new Map();
	m.put("ids",selectIDS);
	if(PxxxRPC.deletePxxx(m))
	{
		top.msgAlert("培训信息"+WCMLang.Delete_success);		
		reloadPxxxList();
	}
	else
	{
		top.msgWargin("培训信息"+WCMLang.Delete_fail);
	}
}
//发布操作
function publishPxxx(publish_flag)
{
	var selectIDS = table.getSelecteCheckboxValue("id");
	var m = new Map();
	m.put("status",publish_flag+"");
	m.put("ids",selectIDS);
	m.put("pass_man",top.LoginUserBean.user_realname);
	
	if(PxxxRPC.publishPxxx(m))
	{
		top.msgAlert("培训信息"+WCMLang.Publish_success);
		reloadPxxxList();
	}else
	{
		top.msgWargin("培训信息"+WCMLang.Publish_fail);
	}
}


function updatePxxxData()
{	
	var bean = BeanUtil.getCopy(PxxxBean);
	var bool = false;
	if(id != null && id != "")
	{
		bean.id = id;
		$("#pxxx_table").autoBind(bean);
		bean.bz = KE.html("bz");
		bool = PxxxRPC.updatePxxx(bean);
	}
	else
	{
		$("#pxxx_table").autoBind(bean);
		bean.status = "1";
		bean.bz = KE.html("bz");
		bool = PxxxRPC.insertPxxx(bean);
	}
	if(bool)
	{
		top.msgAlert("培训信息保存成功");
		top.getCurrentFrameObj(topnum).reloadPxxxList();
		top.tab_colseOnclick(top.curTabIndex);
	}else
	{
		top.msgWargin("培训信息保存失败，请重新操作");
	}
	
}

function addInfo()
{
	top.addTab(true,"/sys/project/dz_pxxx/viewPxxx.jsp?topnum="+top.curTabIndex,"培训信息");
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
	reloadPxxxList();
}

//高级搜索
function highSearchHandl(con)
{
	table.con_value = con;
	reloadPxxxList();
}