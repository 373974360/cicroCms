var ExamRPC = jsonrpc.ExamRPC;
var ExamBean = new Bean("com.cicro.project.scoreSearch.ExamBean", true);
var selectBean = null;//当前选中项对象
var val = new Validator();
var beanList = null;
var table = new Table();
var tp = new TurnPage();
table.table_name = "examTable";
var is_button_click = true; //是否是点击的按钮触发事件
var status = 0;
var m = new Map();

function reloadExamList() {
    showList();
    showTurnPage();
}

function initTable() {

    var colsMap = new Map();
    var colsList = new List();

    colsList.add(setTitleClos("name", "名称", "150px", "", "", ""));
    colsList.add(setTitleClos("description", "描述", "370px", "", "", ""));

    table.setColsList(colsList);
    table.setAllColsList(colsList);
    table.enableSort = false;//禁用表头排序
    table.onSortChange = showList;
    table.show("table");//里面参数为外层div的id
}

function showList() {
	m.put("start_num", tp.getStart());
    m.put("page_size", tp.pageSize);
    beanList = ExamRPC.getExamList(m);//第一个参数为站点ID，暂时默认为空
    beanList = List.toJSList(beanList);//把list转成JS的List对象

    curr_bean = null;
    table.setBeanList(beanList, "td_list");//设置列表内容的样式
    table.show("table");

    table.getCol("name").each(function (i) {
        if (i > 0) {
            $(this).html('<a href="javascript:openUpdateExamPage(' + beanList.get(i - 1).id + ')">' + beanList.get(i - 1).name + '</a>');
        }
    });

    Init_InfoTable(table.table_name);
}

function showTurnPage(){
	
	var m2 = new Map();

    tp.total = ExamRPC.getExamCount(m2);

    tp.show("turn","");
    tp.onPageChange = showList;
}

//打开修改窗口
function openUpdateExamPage(id) {
    var c_id;
    if (id != null && id != '') {
        c_id = id;
    } else {
        c_id = table.getSelecteCheckboxValue("id");
    }
    top.OpenModalWindow("考试信息", "/manager/project/scoreSearch/viewExam.jsp?id=" + c_id + "&topnum="+top.curTabIndex, 485, 373);
}


//删除信息
function deleteExam() {
    var selectIDS = table.getSelecteCheckboxValue("id");
    var m = new Map();
    m.put("ids", selectIDS);
    if (ExamRPC.deleteExam(m)) {
        top.msgAlert("考试信息" + WCMLang.Delete_success);
        reloadExamList();
    }
    else {
        top.msgWargin("考试信息" + WCMLang.Delete_fail);
    }
}

function updateExamData() {
    if(!standard_checkInputInfo("exam_table"))
    {
        return;
    }

    var bean = BeanUtil.getCopy(ExamBean);
    var bool = false;
    if (id != null && id != "") {
        bean.id = id;
        bean.status = "1";
        $("#exam_table").autoBind(bean);
        bool = ExamRPC.updateExam(bean);
    }
    else {
        $("#exam_table").autoBind(bean);
        bean.id = 0;
        bean.status = "1";
        bool = ExamRPC.insertExam(bean);
    }
    if (bool) {
        top.msgAlert("考试信息保存成功");
        top.getCurrentFrameObj(topnum).reloadExamList();
        top.CloseModalWindow();
    } else {
        top.msgWargin("考试信息保存失败，请重新操作");
    }
}

function addInfo() {
    top.OpenModalWindow("考试信息", "/manager/project/scoreSearch/viewExam.jsp?topnum="+top.curTabIndex, 485, 373);
}

//搜索
function SearchHandl()
{
	var name = $("#name").val();
	if(name != "")
	{
		if(name != "" && name != null)
		{
			m.put("name",name);
		}
	}
	else{
		top.msgAlert(WCMLang.Search_empty);
		return;
	}
	reloadExamList();
}
