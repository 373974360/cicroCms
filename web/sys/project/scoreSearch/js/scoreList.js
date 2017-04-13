var ScoreRPC = jsonrpc.ScoreRPC;
var ScoreBean = new Bean("com.cicro.project.scoreSearch.ScoreBean", true);
var selectBean = null;//当前选中项对象
var val = new Validator();
var beanList = null;
var table = new Table();
var tp = new TurnPage();
table.table_name = "scoreTable";
var is_button_click = true; //是否是点击的按钮触发事件
var status = 0;
var m = new Map();

function reloadScoreList() {
    showList();
    showTurnPage();
}

function initTable() {

    var colsMap = new Map();
    var colsList = new List();

    colsList.add(setTitleClos("xm", "姓名", "150px", "", "", ""));
    colsList.add(setTitleClos("sfzh", "身份证号", "180px", "", "", ""));
    colsList.add(setTitleClos("zkzh", "准考证号", "100px", "", "", ""));
    colsList.add(setTitleClos("examName", "考试名称", "270px", "", "", ""));

    table.setColsList(colsList);
    table.setAllColsList(colsList);
    table.enableSort = false;//禁用表头排序
    table.onSortChange = showList;
    table.show("table");//里面参数为外层div的id
}

function showList() {
	m.put("start_num", tp.getStart());
    m.put("page_size", tp.pageSize);
    beanList = ScoreRPC.searchScore(m);//第一个参数为站点ID，暂时默认为空
    beanList = List.toJSList(beanList);//把list转成JS的List对象

    curr_bean = null;
    table.setBeanList(beanList, "td_list");//设置列表内容的样式
    table.show("table");

    table.getCol("xm").each(function (i) {
        if (i > 0) {
            $(this).html('<a href="javascript:openUpdateScorePage(' + beanList.get(i - 1).id + ')">' + beanList.get(i - 1).xm + '</a>');
        }
    });

    Init_InfoTable(table.table_name);
}

function showTurnPage(){
	
	var m2 = new Map();

    tp.total = ScoreRPC.searchScoreCount(m2);

    tp.show("turn","");
    tp.onPageChange = showList;
}

//打开修改窗口
function openUpdateScorePage(id) {
    var c_id;
    if (id != null && id != '') {
        c_id = id;
    } else {
        c_id = table.getSelecteCheckboxValue("id");
    }
    top.OpenModalWindow("考试信息", "/manager/project/scoreSearch/viewScore.jsp?id=" + c_id + "&topnum="+top.curTabIndex, 485, 373);
}


//搜索
function SearchHandl()
{
	var name = $("#name").val();
	var sfzh = $("#sfzh").val();
	if(name != "" || sfzh != "")
	{
		if(name != "" && name != null)
		{
			m.put("name",name);
		}
		if(sfzh != "" && sfzh != null)
		{
			m.put("sfzh",sfzh);
		}
	}
	else{
		top.msgAlert(WCMLang.Search_empty);
		return;
	}
	reloadScoreList();
}
