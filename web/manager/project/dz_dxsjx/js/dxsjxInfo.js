var DxsjxInfoRPC = top.jsonrpc.DxsjxInfoRPC;
var DxsjxCategoryRPC = top.jsonrpc.DxsjxCategoryRPC;
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

function initCategory(){
    var map = new Map();
    map.put("start_num", 0);
    map.put("page_size", 100);
    map.put("orderby", "id desc");
    var categories = DxsjxCategoryRPC.getDxsjxCategoryList(map);
    categories = List.toJSList(categories);//把list转成JS的List对象
	for(var i = 0; i < categories.size(); i++){
		$("#category").append("<option value='" +categories.get(i).id+ "'>" +categories.get(i).name + "</option>")
	}
}

function changeCategory(){
    var category = $("#category").val();
    m.remove("category_id");
    if(category != ""){
        m.put("category_id",category);
	}
    reloadPicViewList();
}

function changeStatus(){
    var status = $("#status").val();
    m.remove("status");
    if(status != ""){
        m.put("status",status);
    }
    reloadPicViewList();
}

function reloadPicViewList()
{
	showList();	
	showTurnPage();	
}

function initTable(){
	
	var colsMap = new Map();	
	var colsList = new List();	
	
	colsList.add(setTitleClos("xm","姓名","200px","","",""));//英文名，显示名，宽，高，样式名，点击事件　
	colsList.add(setTitleClos("xb","性别","50px","","",""));
	colsList.add(setTitleClos("mz","民族","100px","","",""));
	colsList.add(setTitleClos("jg","籍贯","100px","","",""));
	colsList.add(setTitleClos("zzmm","政治面貌","60px","","",""));
	colsList.add(setTitleClos("sfzhm","身份证号码","130px","","",""));
	colsList.add(setTitleClos("xxmc","所在学校","100px","","",""));
	colsList.add(setTitleClos("sxzy","所学专业","100px","","",""));
	colsList.add(setTitleClos("sznj","所在年级","","","",""));
    colsList.add(setTitleClos("lxdh","联系电话","","","",""));
    colsList.add(setTitleClos("status","状态","","","",""));

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
	
	beanList = DxsjxInfoRPC.getDxsjxList(m);//第一个参数为站点ID，暂时默认为空
	beanList = List.toJSList(beanList);//把list转成JS的List对象	
	
	curr_bean = null;		
	table.setBeanList(beanList,"td_list");//设置列表内容的样式
	table.show("table");
	
	table.getCol("xm").each(function(i){
		if(i>0)
		{
			$(this).html('<a href="javascript:openUpdatePicViewPage('+beanList.get(i-1).id+')">'+beanList.get(i-1).xm + '-' + beanList.get(i-1).xxmc + '-' + beanList.get(i-1).sxzy +'</a>');
		}
	});

    table.getCol("status").each(function(i){
        if(i>0)
        {
        	if(beanList.get(i-1).status == 0){
                $(this).html("<span style='color:black'>待审核</span>");
			}
            if(beanList.get(i-1).status == 1){
                $(this).html("<span style='color:blue'>审核通过</span>");
            }
            if(beanList.get(i-1).status == 2){
                $(this).html("<span style='color:red'>审核未通过</span>");
            }
            if(beanList.get(i-1).status == 3){
                $(this).html("<span style='color:green'>已选中</span>");
            }
        }
    });

    Init_InfoTable(table.table_name);
}

function showTurnPage(){	
	
	tp.total = DxsjxInfoRPC.getDxsjxListCount(m);
			
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
	
	top.addTab(true,"/manager/project/dz_dxsjx/viewDxsjxInfo.jsp?id="+c_id+"&topnum="+top.curTabIndex,"大学生见习报名信息");
	
}

function openUpdatePicUpdatePage(id)
{
	top.addTab(true,"/manager/project/picview/update_pic.jsp?id="+id+"&topnum="+top.curTabIndex+"&audit_type="+audit_type+"&publish_type="+publish_type,"投稿信息");
}

//删除投稿
function deletePicView()
{
	var selectIDS = table.getSelecteCheckboxValue("id");
	var m = new Map();
	m.put("id",selectIDS);
	if(DxsjxInfoRPC.deleteDxsjx(m))
	{
		top.msgAlert("大学生见习报名信息"+WCMLang.Delete_success);
		reloadPicViewList();
	}
	else
	{
		top.msgWargin("大学生见习报名信息"+WCMLang.Delete_success);
	}
}

//删除投稿
function updateDxsjxInfo(status)
{
    var selectIDS = table.getSelecteCheckboxValue("id");
    var m = new Map();
    m.put("id",selectIDS);
    m.put("status",status);
    if(DxsjxInfoRPC.updateDxsjx(m))
    {
        top.msgAlert("大学生见习报名信息"+WCMLang.Add_success);
        reloadPicViewList();
    }
    else
    {
        top.msgWargin("大学生见习报名信息"+WCMLang.Add_fail);
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




