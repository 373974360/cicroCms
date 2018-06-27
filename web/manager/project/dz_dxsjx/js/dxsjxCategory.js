var DxsjxCategoryRPC = jsonrpc.DxsjxCategoryRPC;
var DxsjxCategoryBean = new Bean("com.cicro.project.dz_dxsjx.DxsjxCategoryBean", true);

var selectBean = null;//当前选中项对象
var val = new Validator();
var serarch_con = "";//查询条件
var tp = new TurnPage();
var beanList = null;
var table = new Table();
table.table_name = "Category_table";

function reloadCategoryDataList() {
    showTurnPage();
    showList();
}

function initTable() {
    var colsMap = new Map();
    var colsList = new List();
    colsList.add(setTitleClos("id", "ID", "50px", "", "", ""));
    colsList.add(setTitleClos("name", "分类名称", "", "", "", ""));//英文名，显示名，宽，高，样式名，点击事件　
    colsList.add(setTitleClos("total", "总选中人数", "", "", "", ""));//英文名，显示名，宽，高，样式名，点击事件　
    colsList.add(setTitleClos("size", "每次摇号选中人数", "", "", "", ""));//英文名，显示名，宽，高，样式名，点击事件　
    colsList.add(setTitleClos("add_time", "添加时间", "260px", "", "", ""));
    colsList.add(setTitleClos("status", "状态", "", "", "", ""));
    colsList.add(setTitleClos("oprate", "操作", "", "", "", ""));
    table.setColsList(colsList);
    table.setAllColsList(colsList);
    table.enableSort = false;//禁用表头排序
    table.onSortChange = showList;
    table.show("table");//里面参数为外层div的id
}

function showList() {
    var m = new Map();
    // m.put("name", "name");
    m.put("start_num", tp.getStart());
    m.put("page_size", tp.pageSize);
    m.put("orderby", "id desc");
    beanList = DxsjxCategoryRPC.getDxsjxCategoryList(m);//第一个参数为站点ID，暂时默认为空
    beanList = List.toJSList(beanList);//把list转成JS的List对象
    curr_bean = null;
    table.setBeanList(beanList, "td_list");//设置列表内容的样式
    table.show("table");

    table.getCol("name").each(function (i) {
        $(this).css({"text-align": "left"});
        if (i > 0) {
            $(this).html('<a href="javascript:openUpdatePage(' + beanList.get(i - 1).id + ')">' + beanList.get(i - 1).name + '</a>');
        }
    });

    table.getCol("status").each(function (i) {
        if (i > 0) {
            if(beanList.get(i-1).status == 0){
                $(this).html("<span style='color:red'>未开始摇号</span>");
            }
            if(beanList.get(i-1).status == 1){
                $(this).html("<span style='color:green'>正在摇号</span>");
            }
            if(beanList.get(i-1).status == 2){
                $(this).html("<span style='color:green'>已完成摇号</span>");
            }
        }
    });

    table.getCol("oprate").each(function (i) {
        $(this).css({"text-align": "left"});
        if (i > 0) {
            if(beanList.get(i-1).status != 2){
                $(this).html("<a href='/manager/project/dz_dxsjx/randomDxsjxInfo.jsp?id="+ beanList.get(i-1).id +"' target='_blank'>开始摇号</a>");
            }
        }
    });

    Init_InfoTable(table.table_name);
}

function showTurnPage() {
    var m = new Map();
    // m.put("name", "name");
    tp.total = DxsjxCategoryRPC.getDxsjxCategoryListCount(m);
    tp.show("turn", "");
    tp.onPageChange = showList;
}
//打开添加窗口
function openAddCategoryPage() {
    top.OpenModalWindow("维护来源", "/manager/project/dz_dxsjx/dxsjxCategoryAdd.jsp", 380, 215);
}

//打开修改窗口
function openUpdateCategoryDataPage() {
    var selectIDS = table.getSelecteCheckboxValue("id");
    top.OpenModalWindow("维护来源", "/manager/project/dz_dxsjx/dxsjxCategoryAdd.jsp?id=" + selectIDS, 380, 215);
}

function openUpdatePage(sourceid) {
    top.OpenModalWindow("维护来源", "/manager/project/dz_dxsjx/dxsjxCategoryAdd.jsp?id=" + sourceid, 380, 215);
}

//添加Category
function addCategoryData() {
    var bean = BeanUtil.getCopy(DxsjxCategoryBean);
    $("#Category_table").autoBind(bean);

    if (!standard_checkInputInfo("Category_table")) {
        return;
    }

    if (DxsjxCategoryRPC.insertDxsjxCategory(bean)) {
        top.msgAlert("分类" + WCMLang.Add_success);
        top.CloseModalWindow();
        top.getCurrentFrameObj().reloadCategoryDataList();
    }
    else {
        top.msgWargin("分类" + WCMLang.Add_fail);
    }
}

//修改Category
function updateCategoryData() {
    var bean = BeanUtil.getCopy(DxsjxCategoryBean);
    $("#Category_table").autoBind(bean);

    if (!standard_checkInputInfo("Category_table")) {
        return;
    }
    bean.id = defaultBean.id;
    if (DxsjxCategoryRPC.updateDxsjxCategory(bean)) {
        top.msgAlert("分类" + WCMLang.Add_success);
        top.CloseModalWindow();
        top.getCurrentFrameObj().reloadCategoryDataList();
    }
    else {
        top.msgWargin("分类" + WCMLang.Add_fail);
    }
}

//删除Category
function deleteCategoryData() {
    var selectIDS = table.getSelecteCheckboxValue("id");
    var m = new Map();
    m.put("id",selectIDS);
    if (DxsjxCategoryRPC.deleteDxsjxCategory(m)) {
        top.msgAlert("分类" + WCMLang.Delete_success);
        top.CloseModalWindow();
        top.getCurrentFrameObj().reloadCategoryDataList();
    } else {
        top.msgWargin("分类" + WCMLang.Delete_fail);
    }
}

function closePage() {
    top.CloseModalWindow();
}




