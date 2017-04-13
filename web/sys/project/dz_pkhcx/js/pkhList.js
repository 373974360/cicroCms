var PkhRPC = jsonrpc.PkhRPC;
var PkhBean = new Bean("com.cicro.project.dz_pkhcx.PkhBean",true);
var selectBean = null;//当前选中项对象
var val=new Validator();
var serarch_con = "";//查询条件
var tp = new TurnPage();
var beanList = null;
var table = new Table();	
table.table_name = "pkh_table";
var current_role_bean;
var is_button_click = true;//是否是点击的按钮触发事件
var m = new Map();

function reloadPkhList()
{
	showList();	
	showTurnPage();	
}

function initTable(){
	
	var colsMap = new Map();	
	var colsList = new List();	
	
	colsList.add(setTitleClos("hzxm","户主姓名","120px","","",""));
	colsList.add(setTitleClos("hzzjhm","户主证件号码","170px","","",""));
	colsList.add(setTitleClos("rs","人数","70px","","",""));
	colsList.add(setTitleClos("nrjcsr","年人均纯收入 ","120px","","",""));
	colsList.add(setTitleClos("lxdh","联系电话","120px","","",""));
	colsList.add(setTitleClos("hsx","户属性","120px","","",""));
	colsList.add(setTitleClos("zyzpyy","主要致贫原因","120px","","",""));
	colsList.add(setTitleClos("tpbs","脱贫标识","120px","","",""));
	

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
	
	beanList = PkhRPC.getPkhList(m);//第一个参数为站点ID，暂时默认为空	
	beanList = List.toJSList(beanList);//把list转成JS的List对象	
	
	curr_bean = null;		
	table.setBeanList(beanList,"td_list");//设置列表内容的样式
	table.show("table");
	
	table.getCol("hzxm").each(function(i){
		if(i>0)
		{
			$(this).html('<a href="javascript:openUpdatePkhPage('+beanList.get(i-1).id+')">'+beanList.get(i-1).hzxm+'</a>');
		}
	});	
	
	Init_InfoTable(table.table_name);
}

function showTurnPage(){	
	
	tp.total = PkhRPC.getPkhCount(m);
			
	tp.show("turn","");	
	tp.onPageChange = showList;
}


//打开修改窗口
function openUpdatePkhPage(id)
{
	var c_id;
	if(id != null)
	{
		c_id = id;
	}else
	{
		c_id = table.getSelecteCheckboxValue("id");
	}
	
	top.addTab(true,"/manager/project/dz_pkhcx/viewPkh.jsp?id="+c_id+"&topnum="+top.curTabIndex,"全镇贫困户信息");
	
}

//删除信息
function deletePkh()
{	
	var selectIDS = table.getSelecteCheckboxValue("id");
	var m = new Map();
	m.put("ids",selectIDS);
	if(PkhRPC.deletePkh(m))
	{
		top.msgAlert("全镇贫困户信息"+WCMLang.Delete_success);		
		reloadPkhList();
	}
	else
	{
		top.msgWargin("全镇贫困户信息"+WCMLang.Delete_success);
	}
}


function updatePkhData()
{	
	var bean = BeanUtil.getCopy(PkhBean);
	var bool = false;
	$("#pkh_table").autoBind(bean);

	if(id != null && id != "")
	{
		bean.id = id;
		bool = PkhRPC.updatePkh(bean);
	}
	else
	{
		bean.status = "1";
		bool = PkhRPC.insertPkh(bean);
	}
	if(bool)
	{
		top.msgAlert("全镇贫困户信息保存成功");
		top.getCurrentFrameObj(topnum).reloadPkhList();
		top.tab_colseOnclick(top.curTabIndex);
	}else
	{
		top.msgWargin("全镇贫困户信息保存失败，请重新操作");
	}
	
}

function addInfo()
{
	top.addTab(true,"/manager/project/dz_pkhcx/viewPkh.jsp?topnum="+top.curTabIndex,"全镇贫困户信息");
}

//搜索
function SearchHandl()
{
	m.put("hzxm", $("#hzxm").val());
	m.put("hzzjhm", $("#hzzjhm").val());	
	m.put("lxdh", $("#lxdh").val());	
	m.put("hsx", $("#hsx").val());	
	m.put("zyzpyy", $("#zyzpyy").val());	
	m.put("tpbs", $("#tpbs").val());	
	reloadPkhList();
}
