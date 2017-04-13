var WZJEnterpriseRPC = jsonrpc.WZJEnterpriseRPC;

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
	
	colsList.add(setTitleClos("cname","公司名称","150px","","",""));//英文名，显示名，宽，高，样式名，点击事件　
	colsList.add(setTitleClos("yyzzid","营业执照号","100px","","",""));
	colsList.add(setTitleClos("frname","法人","60px","","",""));
	colsList.add(setTitleClos("sshy","所属行业","120px","","",""));
	colsList.add(setTitleClos("lxr","联系人","100px","","",""));
	colsList.add(setTitleClos("phone","联系电话","60px","","",""));
	colsList.add(setTitleClos("email","邮箱地址","100px","","",""));
	colsList.add(setTitleClos("siteurl","公司网址","100px","","",""));
	colsList.add(setTitleClos("is_audit","审核状态","","","",""));

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

	beanList = WZJEnterpriseRPC.getEnterpriseBeanList(m);//第一个参数为站点ID，暂时默认为空	
	beanList = List.toJSList(beanList);//把list转成JS的List对象	
	
	curr_bean = null;		
	table.setBeanList(beanList,"td_list");//设置列表内容的样式
	table.show("table");

	table.getCol("cname").each(function(i){	
		if(i>0)
		{			
			$(this).html('<a href="javascript:openViewPage('+beanList.get(i-1).id+')" >'+beanList.get(i-1).cname+'</a>');
		}
	});

	table.getCol("is_audit").each(function(i){	
		if(i>0)
		{	
			var status = beanList.get(i-1).is_audit;
			if(status == 0)
				$(this).text("待审核");
			if(status == 1)
				$(this).text("审核已通过");
			if(status == -1)
				$(this).text("审核未通过");
		}
	});
	
	Init_InfoTable(table.table_name);
}

function showTurnPage(){	
	
	tp.total = WZJEnterpriseRPC.getEnterpriseBeanCount(m);
			
	tp.show("turn","");	
	tp.onPageChange = showList;
}

//打开查看窗口
function openViewPage(r_id)
{
	top.addTab(true,"/manager/project/dz_wzjhy/enterprise/enterprise_view.jsp?id="+r_id+"&topnum="+top.curTabIndex,"浏览企业会员");
}

function auditEnterprise(flag)
{		
	var selectIDS = table.getSelecteCheckboxValue("id");
	var delMap = new Map();
	delMap.put("id",selectIDS);
	delMap.put("is_audit",flag+"");
	
	if(WZJEnterpriseRPC.auditEnterprise(delMap))
	{
		top.msgAlert("审核状态"+WCMLang.Set_success);
		reloadList();
	}else
	{
		top.msgWargin("审核状态"+WCMLang.Set_fail);
	}	
}


//删除企业会员
function deleteEnterpriseBean()
{		
	var selectIDS = table.getSelecteCheckboxValue("id");
	var delMap = new Map();
	delMap.put("id",selectIDS);
	
	if(WZJEnterpriseRPC.deleteEnterpriseBean(delMap))
	{
		top.msgAlert("企业会员信息"+WCMLang.Delete_success);
		reloadList();
	}else
	{
		top.msgWargin("企业会员信息"+WCMLang.Delete_fail);
	}	
}
