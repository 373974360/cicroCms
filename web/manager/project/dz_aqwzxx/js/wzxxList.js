var WzxxRPC = jsonrpc.WzxxRPC;
var WzxxBean = new Bean("com.cicro.project.dz_aqwzxx.WzxxBean",true);
var selectBean = null;//当前选中项对象
var val=new Validator();
var serarch_con = "";//查询条件
var tp = new TurnPage();
var beanList = null;
var table = new Table();	
table.table_name = "wzxx_table";
var current_role_bean;
var is_button_click = true;//是否是点击的按钮触发事件
var m = new Map();

function reloadWzxxList()
{
	showList();	
	showTurnPage();	
}

function initTable(){
	
	var colsMap = new Map();	
	var colsList = new List();	
	
	colsList.add(setTitleClos("wzmc","网站名称","120px","","",""));//英文名，显示名，宽，高，样式名，点击事件　
	colsList.add(setTitleClos("wzwz","网站地址","170px","","",""));
	colsList.add(setTitleClos("jzsj","建站时间","150px","","",""));
	colsList.add(setTitleClos("ssxq","所属辖区 ","120px","","",""));
	colsList.add(setTitleClos("cplx","参评类型","120px","","",""));

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
	
	beanList = WzxxRPC.getWzxxList(m);//第一个参数为站点ID，暂时默认为空	
	beanList = List.toJSList(beanList);//把list转成JS的List对象	
	
	curr_bean = null;		
	table.setBeanList(beanList,"td_list");//设置列表内容的样式
	table.show("table");
	
	table.getCol("wzmc").each(function(i){
		if(i>0)
		{
			$(this).html('<a href="javascript:openUpdateWzxxPage('+beanList.get(i-1).id+')">'+beanList.get(i-1).wzmc+'</a>');
		}
	});	
	
	Init_InfoTable(table.table_name);
}

function showTurnPage(){	
	
	tp.total = WzxxRPC.getWzxxCount(m);
			
	tp.show("turn","");	
	tp.onPageChange = showList;
}


//打开修改窗口
function openUpdateWzxxPage(id)
{
	var c_id;
	if(id != null)
	{
		c_id = id;
	}else
	{
		c_id = table.getSelecteCheckboxValue("id");
	}
	
	top.addTab(true,"/manager/project/dz_aqwzxx/viewWzxx.jsp?id="+c_id+"&topnum="+top.curTabIndex,"网站报名信息");
	
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
function deleteWzxx()
{	
	var selectIDS = table.getSelecteCheckboxValue("id");
	var m = new Map();
	m.put("ids",selectIDS);
	if(WzxxRPC.deleteWzxx(m))
	{
		top.msgAlert("网站报名信息"+WCMLang.Delete_success);		
		reloadWzxxList();
	}
	else
	{
		top.msgWargin("网站报名信息"+WCMLang.Delete_success);
	}
}


function updateWzxxData()
{	
	var bean = BeanUtil.getCopy(WzxxBean);
	var bool = false;
	$("#wzxx_table").autoBind(bean);

	bean.wzjyfw = KE.html("wzjyfw");
	bean.wzjj = KE.html("wzjj");
	bean.hjry = KE.html("hjry");
	bean.cplx = $("input[name='cplx']:checked").val();
	bean.zcyh = $("input[name='zcyh']:checked").val();
	bean.iscf = $("input[name='iscf']:checked").val();
	bean.isglzd = $("input[name='isglzd']:checked").val();
	bean.isaqlc = $("input[name='isaqlc']:checked").val();
	bean.aqysl = $("input[name='aqysl']:checked").val();
	bean.fjpd = "";
	bean.baxx = $("input[name='baxx']:checked").val();
	var chk_value =[];//定义一个数组    
	$('input[name="smrz"]:checked').each(function(){	//遍历每一个名字为interest的复选框，其中选中的执行函数    
		bean.smrz += $(this).val() + "$";	//将选中的值添加到数组chk_value中    
	});

	if(bean.bmlx == "1")
	{
		bean.dwmc = $("#dwmc2").val();
		bean.lxdz = $("#lxdz2").val();
		bean.gsfr = $("#gsfr2").val();
		bean.yb = $("#yb2").val();
	}
	else
	{
		bean.dwxz = $("input[name='dwxz']:checked").val();
	}
	
	if(id != null && id != "")
	{
		bean.id = id;
		bool = WzxxRPC.updateWzxx(bean);
	}
	else
	{
		bean.status = "1";
		bool = WzxxRPC.insertWzxx(bean);
	}
	if(bool)
	{
		top.msgAlert("网站报名信息保存成功");
		top.getCurrentFrameObj(topnum).reloadWzxxList();
		top.tab_colseOnclick(top.curTabIndex);
	}else
	{
		top.msgWargin("网站报名信息保存失败，请重新操作");
	}
	
}

function addInfo()
{
	top.addTab(true,"/manager/project/dz_aqwzxx/viewWzxx.jsp?topnum="+top.curTabIndex,"网站报名信息");
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
	reloadWzxxList();
}

//高级搜索
function highSearchHandl(con)
{
	table.con_value = con;
	reloadWzxxList();
}