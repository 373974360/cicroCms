var CategoryRPC = jsonrpc.CategoryRPC;
var SiteRPC = jsonrpc.SiteRPC;
var ZTCategoryBean = new Bean("com.cicro.wcm.bean.cms.category.ZTCategoryBean",true);
var domain_url = "";
var selectBean = null;//当前选中项对象
var val=new Validator();
var serarch_con = "";//查询条件
var tp = new TurnPage();
var beanList = null;
var table = new Table();	
table.table_name = "zt_table";;

function reloadZTList()
{
	showTurnPage();
	showList();	
}

function initTable(){
	domain_url = SiteRPC.getDefaultSiteDomainBySiteID(site_id);
	var colsMap = new Map();	
	var colsList = new List();	
	colsList.add(setTitleClos("cat_id","ID","20px","","",""));
	colsList.add(setTitleClos("cat_cname","专题名称","200px","","",""));//英文名，显示名，宽，高，样式名，点击事件　
	colsList.add(setTitleClos("zt_cat_id","专题分类","100px","","",""));
	colsList.add(setTitleClos("is_archive","归档状态","70px","","",""));
	colsList.add(setTitleClos("cat_handl","目录管理","70px","","",""));
	colsList.add(setTitleClos("actionCol","操作","320px","","",""));
	colsList.add(setTitleClos("spanCol"," ","","","",""));
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
	
	var m = new Map();	
	m.put("site_id", site_id);
	m.put("cat_type", "1");	
	m.put("start_num", tp.getStart());	
	m.put("page_size", tp.pageSize);
	m.put("sort_name", sortCol);
	m.put("sort_type", sortType);
	m.put("parent_id", "0");
	if(serrch_cat_id != "")
		m.put("zt_cat_id", serrch_cat_id);
	if(table.con_value.trim() != "" && table.con_value != null)
	{		
		m.put("con_name", table.con_name);
		m.put("con_value", table.con_value);
	}

	beanList = CategoryRPC.getCategoryListBySiteAndType(m);//第一个参数为站点ID，暂时默认为空	
	beanList = List.toJSList(beanList);//把list转成JS的List对象	
	
	curr_bean = null;		
	table.setBeanList(beanList,"td_list");//设置列表内容的样式
	table.show("table");
	
	table.getCol("cat_cname").each(function(i){
		$(this).css("text-align","left")
		if(i>0)
		{
			$(this).html('<a href="javascript:openUpdateCategoryPage('+beanList.get(i-1).id+')">'+beanList.get(i-1).cat_cname+'</a>');
		}
	});	
	table.getCol("zt_cat_id").each(function(i){
		if(i>0)
		{
			$(this).text(getZTCategoryNameByID(beanList.get(i-1).zt_cat_id));
		}
	});
	table.getCol("cat_handl").each(function(i){
		if(i>0)
		{
			$(this).html('<a href="javascript:top.addTab(true,\'/manager/cms/category/categoryList.jsp?cat_type=1&app_id='+app_id+'&cat_id='+beanList.get(i-1).cat_id+'\',\'目录管理\')">目录管理</a>');	
		}
	});
	table.getCol("is_archive").each(function(i){
		if(i>0)
		{
			if(beanList.get(i-1).is_archive == 1)
				$(this).text("已归档");
			else
				$(this).text("");
		}
	});
	table.getCol("actionCol").each(function(i){
		if(i>0)
		{
			$(this).html('<a href="javascript:designTemplate('+beanList.get(i-1).template_index+','+beanList.get(i-1).cat_id+',\''+beanList.get(i-1).cat_cname+'\',\'index\')">首页设计</a>&#160;&#160;<a href="javascript:designTemplate('+beanList.get(i-1).template_list+','+beanList.get(i-1).cat_id+',\''+beanList.get(i-1).cat_cname+'\',\'list\')">列表页设计</a>&#160;&#160;<a href="javascript:designTemplate('+CategoryRPC.getTemplateID(beanList.get(i-1).cat_id,site_id,10)+','+beanList.get(i-1).cat_id+',\''+beanList.get(i-1).cat_cname+'\',\'content\')">内容页设计</a>&#160;&#160;&#160;&#160;<a target="_blank" href="'+domain_url+'/info/iIndex.jsp?cat_id='+beanList.get(i-1).cat_id+'"><img src="../../images/zt_home.png" title="浏览首页" alt="浏览首页"></a>&#160;&#160;<a target="_blank" href="'+domain_url+'/info/iList.jsp?cat_id='+beanList.get(i-1).cat_id+'"><img src="../../images/zt_list.png" alt="浏览列表页" title="浏览列表页"></a>');	
		}
	});
	Init_InfoTable(table.table_name);
}

//打开模板设计页面
var d_cat_id,d_c_name,d_page_type;
function designTemplate(template_id,c_id,c_name,page_type)
{	
	d_cat_id = c_id;
	d_c_name = c_name;
	d_page_type = page_type;
	
	if(template_id == 0)
	{
		top.OpenModalWindow("专题设计","/manager/system/design/operate/select_case.jsp?site_id="+site_id,600,535);	
	}
	else
		openDesignPage();
}

function openDesignPage(c_type,v_id)
{
	window.open("/manager/system/design/operate/designPage.jsp?cat_id="+d_cat_id+"&site_id="+site_id+"&app_id="+app_id+"&c_name="+encodeURI(d_c_name)+"&p_type="+d_page_type+"&create_type="+c_type+"&v_id="+v_id);
}

function showTurnPage(){	
	var m = new Map();
	m.put("site_id", site_id);
	m.put("cat_type", "1");	
	m.put("parent_id", "0");
	if(serrch_cat_id != "")
		m.put("zt_cat_id", serrch_cat_id);
	if(table.con_value.trim() != "" && table.con_value != null)
	{
		m.put("con_name", table.con_name);
		m.put("con_value", table.con_value);			
	}	
	tp.total = CategoryRPC.getCategoryCountBySiteAndType(m);
			
	tp.show("turn","");	
	tp.onPageChange = showList;
}

function openAddCategoryPage()
{
	top.addTab(true,"/manager/cms/category/category_add.jsp?top_index="+top.curTabIndex+"&app_id="+app_id+"&parentID=0&cat_type=1","维护目录");
}

function openUpdateCategoryPage(cid)
{
	var id;
	if(cid != "" && cid != null)
		id = cid;
	else
		id = table.getSelecteCheckboxValue("id");
	top.addTab(true,"/manager/cms/category/category_add.jsp?top_index="+top.curTabIndex+"&id="+id+"&cat_type=1&app_id="+app_id,"维护目录");
}

var ztCate_list = new List();
function getZTCategoryList()
{
	ztCate_list = CategoryRPC.getZTCategoryList(site_id);
	ztCate_list = List.toJSList(ztCate_list);
	$("#zt_cat_id").addOptions(ztCate_list,"zt_cat_id","zt_cat_name");
}

function getZTCategoryNameByID(zt_cat_id)
{
	if(ztCate_list != null && ztCate_list.size() > 0)
	{
		for(var i=0;i<ztCate_list.size();i++)
		{
			if(ztCate_list.get(i).zt_cat_id == zt_cat_id)
				return ztCate_list.get(i).zt_cat_name;
		}
	}else
		return "";
}

function deleteCategory()
{
	var selectIDS = table.getSelecteCheckboxValue("cat_id");

	if(CategoryRPC.deleteCategory(selectIDS,site_id,app_id))
	{
		top.msgAlert("目录信息"+WCMLang.Delete_success);
		reloadZTList();
	}else
	{
		top.msgWargin("目录信息"+WCMLang.Delete_fail);
	}
}


function searchHandl(obj)
{
	var con_value = $(obj).parent().find("#searchkey").val();
	if(con_value.trim() == "" ||  con_value == null)
	{
		top.msgAlert(WCMLang.Search_empty);
		return;
	}
	table.con_name = $(obj).parent().find("#searchFields").val(); 
	table.con_value = con_value;
	reloadZTList();
}

function searchClass(val)
{
	table.con_value = "";
	serrch_cat_id = val;
	reloadZTList();
}

//修改归档状态
function updateArchiveStatus(flag)
{
	var selectIDS = table.getSelecteCheckboxValue("id");

	if(CategoryRPC.updateCategoryArchiveStatus(selectIDS,flag))
	{
		top.msgAlert(WCMLang.ArchiveStatus_success);
		reloadZTList();
	}else
	{
		top.msgWargin(WCMLang.ArchiveStatus_fail);
	}
}
