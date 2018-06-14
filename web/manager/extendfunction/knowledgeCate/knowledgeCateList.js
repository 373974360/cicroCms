﻿var KnowledgeRPC = jsonrpc.KnowledgeRPC;
var KnowledgeCateBean = new Bean("com.cicro.wcm.services.extendfunction.knowledgeTab.KnowledgeCateBean",true);

var con_m = new Map();
var beanList = null;
var table = new Table();
    table.table_name = "table";
var tp = new TurnPage();

var app_id = "";
var site_id = "";

function loadWareCategoryTable()
{
	showList();
	showTurnPage();
}

function initTable()
{
	var colsMap = new Map();	
	var colsList = new List();	
	colsList.add(setTitleClos("id","ID","80px","","",""));//英文名，显示名，宽，高，样式名，点击事件　
	colsList.add(setTitleClos("kcat_name","名称","160px","","",""));//英文名，显示名，宽，高，样式名，点击事件　	
	colsList.add(setTitleClos("kcat_memo","描述","","","",""));
	colsList.add(setTitleClos("sort_col","排序","120px","","",""));
	
	table.setColsList(colsList);
	table.setAllColsList(colsList);	
	table.enableSort=false;//禁用表头排序
	table.onSortChange = showList;
	table.show("table");//里面参数为外层div的id
}

function showList()
{
	beanList = KnowledgeRPC.getWareCateList(id,con_m);
	beanList =  List.toJSList(beanList);
	tp.total = beanList.size();		
	
	table.setBeanList(beanList,"td_list");//设置列表内容的样式
	table.show("table");	
	
	table.getCol("kcat_name").each(function(i){	
		if(i>0)
		{
			$(this).html('<a href="javascript:top.OpenModalWindow(\'新建知识库标签分类\',\'/manager/extendfunction/knowledgeCate/knowledgeCateAdd.jsp?type=update&id='+beanList.get(i-1).id+'\',450,230)">'+beanList.get(i-1).kcat_name+'</a>');
		}
	}); 
	
	table.getCol("kcat_memo").each(function(i){	
		if(i>0)
		{
			$(this).html(beanList.get(i-1).kcat_memo);
		}
	});
	
	table.getCol("sort_col").each(function(i){	
		if(i>0)
		{
			$(this).html(getSortColStr());
		}
	});
	Init_InfoTable(table.table_name);
}

function showTurnPage()
{
	tp.show("turn","simple");
	tp.onPageChange = showList;
}

// 新建button事件
function openAddPage()
{
	top.OpenModalWindow("新建知识库标签分类","/manager/extendfunction/knowledgeCate/knowledgeCateAdd.jsp?type=add&id="+id,450,230);
}

// 修改button事件
function openUpdatePage()
{
	var selectID = table.getSelecteCheckboxValue("id");
	top.OpenModalWindow("新建知识库标签分类","/manager/extendfunction/knowledgeCate/knowledgeCateAdd.jsp?type=update&id="+selectID,450,230);
}

// 保存排序button事件
function saveSort()
{
	var ids = table.getAllCheckboxValue("id");;
	if(KnowledgeRPC.saveSort(ids))
	{
		top.msgAlert(WCMLang.Sort_success);
		showWareCateTree();
	}
	else
	{
		top.msgWargin(WCMLang.Sort_fail);
	}
}

// 删除信息分类
function deleteWareCategory()
{
	var ids = table.getSelecteCheckboxValue("kcat_id");
	con_m.put("id", ids);
	con_m.put("site_id", site_id);
	if(KnowledgeRPC.deleteWareCategory(con_m))
	{
		top.msgAlert("知识库分类"+WCMLang.Delete_success);
		deleteTreeNode(ids+"");
		loadWareCategoryTable();
	}
	else
	{
		top.msgWargin("知识库分类"+WCMLang.Delete_fail);
		showWareCateTree();
	}
	con_m.remove("id");
}

// 修改左侧树
function updateWareCateTree(id,name)
{
	updateTreeNode(id,name);
}

/******************************添加修改知识库分类操作*************************************/
function saveAddWareCategory()
{
	if(!checkAddPage())
	{
		return;
	}
	var addBean = BeanUtil.getCopy(KnowledgeCateBean);
	$("#Cate_table").autoBind(addBean);
	
	addBean.site_id = top.getCurrentFrameObj().site_id;
	addBean.app_id = top.getCurrentFrameObj().app_id;
	addBean.kparent_id = id;

	if(KnowledgeRPC.insertWareCate(addBean))
	{
		top.msgAlert("知识库分类"+WCMLang.Add_success);
		top.getCurrentFrameObj().showWareCateTree();
		top.getCurrentFrameObj().loadWareCategoryTable();
		top.CloseModalWindow();
	}
	else
	{
		top.msgWargin("知识库分类"+WCMLang.Add_fail);
	}
}

function saveUpdateWareCategory()
{
	if(!checkAddPage())
	{
		return;
	}
	var updateBean = BeanUtil.getCopy(defaultBean);
	$("#Cate_table").autoBind(updateBean);
	if(KnowledgeRPC.updateWareCate(updateBean))
	{
		top.msgAlert("知识库分类"+WCMLang.Set_success);
		top.getCurrentFrameObj().updateWareCateTree(updateBean.id, updateBean.wcat_name);
		top.getCurrentFrameObj().loadWareCategoryTable();
		top.CloseModalWindow();
	}
	else
	{
		top.msgWargin("知识库分类"+WCMLang.Set_fail);
	}
}

function checkAddPage()
{
	val.check_result = true;
	$("#kcat_name").blur();
	$("#kcat_memo").blur();
	
	if(!val.getResult()){		
		return false;
	}
	return true;
}


//推送
function openForKTree(){		
	top.OpenModalWindow("tree","/manager/extendfunction/knowledgeCate/kc_tree.jsp?site_id="+site_id,320,400);
}

/********** 关联用户 开始 *********
var temp_wcat_id = 0;
function selectSiteUser(wcat_id)
{
	temp_wcat_id = wcat_id;
	openSelectSiteUserPage("选择用户","insertWareReleUserByCat",site_id)
}
var current_user_ids = "";
var current_group_ids = "";
//得到已选过的用户ID
function getSelectedUserIDS()
{
	current_user_ids = "";
	current_group_ids = "";
	var l = KnowledgeRPC.getWareReleUserListByCat(temp_wcat_id,site_id);
	l = List.toJSList(l);
	if(l != null && l.size() > 0)
	{
		for(var i=0;i<l.size();i++)
		{
			if(l.get(i).priv_type == 0)
			{
				current_user_ids += ","+l.get(i).prv_id;				
			}else
			{
				current_group_ids += ","+l.get(i).prv_id;		
			}
		}

		if(current_user_ids != "" && current_user_ids != null)
			current_user_ids = current_user_ids.substring(1);

		if(current_group_ids != "" && current_group_ids != null)
			current_group_ids = current_group_ids.substring(1);
	}
	return current_user_ids;
}
//得到已选过的组ID
function getSelectedGroupIDS()
{
	return current_group_ids;
}

function insertWareReleUserByCat(user_ids,group_ids)
{	
	if(KnowledgeRPC.insertWareReleUserByCat(temp_wcat_id,user_ids,group_ids,app_id,site_id))
	{
		top.msgAlert("标签分类用户关联"+WCMLang.Add_success);
	}else
	{
		top.msgWargin("标签分类用户关联"+WCMLang.Add_fail);
	}
}
/********** 关联用户 结束 **********/