var PicViewRPC = jsonrpc.XQPKRPC;

var selectBean = null;//当前选中项对象
var val=new Validator();
var serarch_con = "";//查询条件
var tp = new TurnPage();
var beanList = null;
var table = new Table();	
table.table_name = "gongqidiv";
var current_role_bean;
var is_button_click = true;//是否是点击的按钮触发事件
var m = new Map();

function reloadPicViewList()
{
	showList();	
	showTurnPage();	
}

function initTable(){
	
	var colsMap = new Map();	
	var colsList = new List();	
	
	colsList.add(setTitleClos("title","标题","200px","","",""));//英文名，显示名，宽，高，样式名，点击事件　
	colsList.add(setTitleClos("editor","作者","100px","","",""));
	colsList.add(setTitleClos("phone_number","手机","100px","","",""));
	colsList.add(setTitleClos("pic_type","素材类型","60px","","",""));
	colsList.add(setTitleClos("cont_type","来源","60px","","",""));
	colsList.add(setTitleClos("cont_dtime","提交时间","130px","","",""));
	colsList.add(setTitleClos("audit_type","审核状态","60px","","",""));
	colsList.add(setTitleClos("publish_type","发布状态","60px","","",""));
	colsList.add(setTitleClos("is_recommend","推荐","60px","","",""));
	colsList.add(setTitleClos("action_col","操作","60px","","",""));
	colsList.add(setTitleClos("space_col"," ","","","",""));

	table.setColsList(colsList);
	table.setAllColsList(colsList);				
	table.enableSort=false;//禁用表头排序
	table.onSortChange = showList;
	table.show("table");//里面参数为外层div的id
}

function showList(){
	
	m.put("audit_type",audit_type);
	m.put("publish_type",publish_type);
	if(is_recommend == 1)
		m.put("is_recommend",1);

	m.put("start_num", tp.getStart());	
	m.put("page_size", tp.pageSize);
	m.put("orderby", "id desc");
	
	beanList = PicViewRPC.getPicViewNoteList(m);//第一个参数为站点ID，暂时默认为空	
	beanList = List.toJSList(beanList);//把list转成JS的List对象	
	
	curr_bean = null;		
	table.setBeanList(beanList,"td_list");//设置列表内容的样式
	table.show("table");
	
	table.getCol("title").each(function(i){
		if(i>0)
		{
			$(this).html('<a href="javascript:openUpdatePicViewPage('+beanList.get(i-1).id+')">'+beanList.get(i-1).title+'</a>');
		}
	});		

	table.getCol("action_col").each(function(i){
		if(i>0)
		{
			$(this).html('<a href="javascript:openUpdatePicUpdatePage('+beanList.get(i-1).id+')">修改</a>');
		}
	});

	table.getCol("pic_type").each(function(i){
		if(i>0)
		{
			if(beanList.get(i-1).pic_type == 0)
			{
				$(this).html("图片");
			}
			if(beanList.get(i-1).pic_type == 1)
			{
				$(this).html("视频");
			}
			if(beanList.get(i-1).pic_type == 2)
			{
				$(this).html("文本");
			}
		}
	});
	table.getCol("cont_type").each(function(i){
		if(i>0)
		{
			if(beanList.get(i-1).cont_type == 0)
			{
				$(this).html("网站");
			}
			if(beanList.get(i-1).cont_type == 1)
			{
				$(this).html("手机");
			}			
		}
	});
	table.getCol("audit_type").each(function(i){
		if(i>0)
		{
			if(beanList.get(i-1).audit_type == 0)
			{
				$(this).html("未审核");
			}
			if(beanList.get(i-1).audit_type == 1)
			{
				$(this).html("审核通过");
			}
			if(beanList.get(i-1).audit_type == -1)
			{
				$(this).html("审核不通过");
			}
		}
	});
	table.getCol("publish_type").each(function(i){
		if(i>0)
		{
			if(beanList.get(i-1).publish_type == 0)
			{
				$(this).html("未发布");
			}else
			{
				$(this).html("已发布");
			}
		}
	});
	table.getCol("is_recommend").each(function(i){
		if(i>0)
		{
			if(beanList.get(i-1).is_recommend == 1)
			{
				$(this).html("是");
			}else
			{
				$(this).html("&#160;");
			}
		}
	});

	Init_InfoTable(table.table_name);
}

function showTurnPage(){	
	
	tp.total = PicViewRPC.getPicViewNoteCount(m);
			
	tp.show("turn","");	
	tp.onPageChange = showList;
}


//打开修改窗口
function openUpdatePicViewPage(id)
{
	var c_id;
	if(id != null)
	{
		c_id = id;
	}else
	{
		c_id = table.getSelecteCheckboxValue("id");
	}
	
	top.addTab(true,"/manager/project/wnxqpk/view_pic.jsp?id="+c_id+"&topnum="+top.curTabIndex+"&audit_type="+audit_type+"&publish_type="+publish_type,"投稿信息");
	
}

function openUpdatePicUpdatePage(id)
{
	top.addTab(true,"/manager/project/wnxqpk/update_pic.jsp?id="+id+"&topnum="+top.curTabIndex+"&audit_type="+audit_type+"&publish_type="+publish_type,"投稿信息");
}

//删除投稿
function deletePicView()
{	
	var selectIDS = table.getSelecteCheckboxValue("id");
	var m = new Map();
	m.put("ids",selectIDS);
	m.put("is_delete","-1");
	if(PicViewRPC.deletePicViewNote(m))
	{
		top.msgAlert("投稿信息"+WCMLang.Delete_success);		
		reloadPicViewList();
	}
	else
	{
		top.msgWargin("投稿信息"+WCMLang.Delete_success);
	}
}
//发布操作
function publishPicView(publish_type)
{
	var selectIDS = table.getSelecteCheckboxValue("id");
	var m = new Map();
	m.put("publish_type",publish_type+"");
	m.put("ids",selectIDS);
	if(PicViewRPC.updatePicViewNoteStatus(m))
	{
		top.msgAlert("投稿信息"+WCMLang.Publish_success);
		reloadPicViewList();
	}else
	{
		top.msgWargin("投稿信息"+WCMLang.Publish_fail);
	}
}
//审核操作
function passPicView(audit_type)
{
	var selectIDS = table.getSelecteCheckboxValue("id");
	var m = new Map();
	m.put("audit_type",audit_type+"");
	m.put("ids",selectIDS);	
	
	if(PicViewRPC.updatePicViewNoteStatus(m))
	{
		top.msgAlert("投稿信息审核状态设置成功");
		reloadPicViewList();
	}else
	{
		top.msgWargin("投稿信息审核状态设置失败，请重新操作");
	}
}

function recommendPicView(is_recommend)
{
	var selectIDS = table.getSelecteCheckboxValue("id");
	var m = new Map();
	m.put("is_recommend",is_recommend+"");
	m.put("ids",selectIDS);	
	
	if(PicViewRPC.updatePicViewNoteStatus(m))
	{
		top.msgAlert("投稿信息推荐状态设置成功");
		reloadPicViewList();
	}else
	{
		top.msgWargin("投稿信息推荐状态设置失败，请重新操作");
	}
}

function updatePic()
{
	var m = new Map();
	if($("#hits").val() != "")
		m.put("hits_manual",$("#hits").val());
	m.put("ids",id);
	m.put("pic_path",$("#pic_path").val());
	m.put("content",KE.html("content"));
	
	if(PicViewRPC.updatePicViewNoteStatus(m))
	{
		top.msgAlert("投稿信息保存成功");
		top.tab_colseOnclick(top.curTabIndex);
	}else
	{
		top.msgWargin("投稿信息保存失败，请重新操作");
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
	reloadPicViewList();
}




