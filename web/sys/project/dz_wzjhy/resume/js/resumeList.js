var WZJResumeRPC = jsonrpc.WZJResumeRPC;

var selectBean = null;//当前选中项对象
var val=new Validator();
var serarch_con = "";//查询条件
var tp = new TurnPage();
var beanList = null;
var table = new Table();	
table.table_name = "table";
var m = new Map();

function reloadList()
{
	
	showList();	
	showTurnPage();
}

function initTable(){
	
	var colsMap = new Map();	
	var colsList = new List();	
	
	colsList.add(setTitleClos("xm","姓名","60px","","",""));//英文名，显示名，宽，高，样式名，点击事件　
	colsList.add(setTitleClos("csrq","出生日期","60px","","",""));
	colsList.add(setTitleClos("xueli","学历","60px","","",""));
	colsList.add(setTitleClos("hunyin","婚姻状况","60px","","",""));
	colsList.add(setTitleClos("sshy","职位行业","100px","","",""));
	colsList.add(setTitleClos("gzage","工作年限","60px","","",""));
	colsList.add(setTitleClos("gz","薪金要求","100px","","",""));
	colsList.add(setTitleClos("jydq","就业地区","60px","","",""));
	colsList.add(setTitleClos("telephone","手机号码","60px","","",""));
	colsList.add(setTitleClos("is_publish","发布状态","60px","","",""));
	colsList.add(setTitleClos("publish_dtime","发布时间","120px","","",""));

	table.setColsList(colsList);
	table.setAllColsList(colsList);				
	table.enableSort=false;//禁用表头排序
	table.onSortChange = showList;
	table.show("table");//里面参数为外层div的id
}

function showList(){	
	
	var sortCol = table.sortCol;
	var sortType = table.sortType;		
	if(sortCol == "" || sortCol == null)
	{
		sortCol = "id";
		sortType = "desc";
	}
	

	m.put("start_num", tp.getStart());	
	m.put("page_size", tp.pageSize);
	m.put("order_by", sortCol+" "+sortType);
	if(table.con_value.trim() != "" && table.con_value != null)
	{
		m.put("con_name", table.con_name);
		m.put("con_value", table.con_value);
	}

	beanList = WZJResumeRPC.getResumeBeanList(m);//第一个参数为站点ID，暂时默认为空	
	beanList = List.toJSList(beanList);//把list转成JS的List对象	
	
	curr_bean = null;		
	table.setBeanList(beanList,"td_list");//设置列表内容的样式
	table.show("table");

	table.getCol("xm").each(function(i){	
		if(i>0)
		{			
			$(this).html('<a href="javascript:openViewPage('+beanList.get(i-1).id+')" >'+beanList.get(i-1).xm+'</a>');
		}
	});

	table.getCol("is_publish").each(function(i){	
		if(i>0)
		{			
			if(beanList.get(i-1).is_publish == 1)
				$(this).text("已发布");
			else
				$(this).html("&#160;");
		}
	});
	
	Init_InfoTable(table.table_name);
}

function showTurnPage(){	
	
	tp.total = WZJResumeRPC.getResumeBeanCount(m);
			
	tp.show("turn","");	
	tp.onPageChange = showList;
}

//打开查看窗口
function openViewPage(r_id)
{
	top.addTab(true,"/manager/project/dz_wzjhy/resume/resume_view.jsp?id="+r_id+"&topnum="+top.curTabIndex,"浏览简历");
}

function publishResumeBean(flag)
{		
	var selectIDS = table.getSelecteCheckboxValue("id");
	var delMap = new Map();
	delMap.put("id",selectIDS);
	delMap.put("is_publish",flag+"");
	
	if(WZJResumeRPC.publishResumeBean(delMap))
	{
		top.msgAlert("简历发布状态"+WCMLang.Set_success);
		reloadList();
	}else
	{
		top.msgWargin("简历发布状态"+WCMLang.Set_fail);
	}	
}


//删除简历
function deleteResumeBean()
{		
	var selectIDS = table.getSelecteCheckboxValue("id");
	var delMap = new Map();
	delMap.put("id",selectIDS);
	
	if(WZJResumeRPC.deleteResumeBean(delMap))
	{
		top.msgAlert("简历信息"+WCMLang.Delete_success);
		reloadList();
	}else
	{
		top.msgWargin("简历信息"+WCMLang.Delete_fail);
	}	
}
