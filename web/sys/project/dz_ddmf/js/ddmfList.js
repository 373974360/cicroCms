var DdmfRPC = jsonrpc.DdmfRPC;
var DdmfBean = new Bean("com.cicro.project.dz_ddmf.DdmfBean",true);
var selectBean = null;//当前选中项对象
var val=new Validator();
var serarch_con = "";//查询条件
var tp = new TurnPage();
var beanList = null;
var table = new Table();	
table.table_name = "ddmf_table";
var current_role_bean;
var is_button_click = true;//是否是点击的按钮触发事件
var m = new Map();

function reloadDdmfList()
{
	showList();	
	showTurnPage();	
}

function initTable(){
	
	var colsMap = new Map();	
	var colsList = new List();	
	
	colsList.add(setTitleClos("xm","姓名","120px","","",""));//英文名，显示名，宽，高，样式名，点击事件　
	colsList.add(setTitleClos("nl","年龄","100px","","",""));
	colsList.add(setTitleClos("xb","性别","100px","","",""));
	colsList.add(setTitleClos("zy","职业 ","170px","","",""));
	colsList.add(setTitleClos("jg","籍贯","190px","","",""));
	colsList.add(setTitleClos("lxdh","联系电话","120px","","",""));

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
	
	beanList = DdmfRPC.getDdmfList(m);//第一个参数为站点ID，暂时默认为空	
	beanList = List.toJSList(beanList);//把list转成JS的List对象	
	
	curr_bean = null;		
	table.setBeanList(beanList,"td_list");//设置列表内容的样式
	table.show("table");
	
	table.getCol("xm").each(function(i){
		if(i>0)
		{
			$(this).html('<a href="javascript:openUpdateDdmfPage('+beanList.get(i-1).id+')">'+beanList.get(i-1).xm+'</a>');
		}
	});	
	
	table.getCol("xb").each(function(i){
		if(i>0)
		{
			if(beanList.get(i-1).xb == 0)
			{
				$(this).html("男");
			}
			else
			{
				$(this).html("女");
			}
		}
	});
	
	Init_InfoTable(table.table_name);
}

function showTurnPage(){	
	
	tp.total = DdmfRPC.getDdmfCount(m);
			
	tp.show("turn","");	
	tp.onPageChange = showList;
}


//打开修改窗口
function openUpdateDdmfPage(id)
{
	var c_id;
	if(id != null)
	{
		c_id = id;
	}else
	{
		c_id = table.getSelecteCheckboxValue("id");
	}
	
	top.addTab(true,"/sys/project/dz_ddmf/viewDdmf.jsp?id="+c_id+"&topnum="+top.curTabIndex,"道德模范信息");
	
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
function deleteDdmf()
{	
	var selectIDS = table.getSelecteCheckboxValue("id");
	var m = new Map();
	m.put("ids",selectIDS);
	if(DdmfRPC.deleteDdmf(m))
	{
		top.msgAlert("道德模范信息"+WCMLang.Delete_success);		
		reloadDdmfList();
	}
	else
	{
		top.msgWargin("道德模范信息"+WCMLang.Delete_success);
	}
}


function updateDdmfData()
{	
	var bean = BeanUtil.getCopy(DdmfBean);
	var bool = false;
	$("#ddmf_table").autoBind(bean);
	if(id != null && id != "")
	{
		bean.id = id;
		bool = DdmfRPC.updateDdmf(bean);
	}
	if(bool)
	{
		top.msgAlert("道德模范信息保存成功");
		top.getCurrentFrameObj(topnum).reloadDdmfList();
		top.tab_colseOnclick(top.curTabIndex);
	}else
	{
		top.msgWargin("道德模范信息保存失败，请重新操作");
	}
	
}

function addInfo()
{
	top.addTab(true,"/sys/project/dz_ddmf/viewDdmf.jsp?topnum="+top.curTabIndex,"道德模范信息");
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
	reloadDdmfList();
}

//高级搜索
function highSearchHandl(con)
{
	table.con_value = con;
	reloadDdmfList();
}