var DriverViolationRPC = jsonrpc.DriverViolationRPC;
var DriverCompanyBean = new Bean("com.cicro.project.dz_driver.DriverCompanyBean", true);
var selectBean = null;//当前选中项对象
var val = new Validator();
var serarch_con = "";//查询条件
var tp = new TurnPage();
var beanList = null;
var table = new Table();
table.table_name = "driverCompany_table";
var current_role_bean;
var is_button_click = true;//是否是点击的按钮触发事件
var m = new Map();

function reloadDriverCompanyList() {
    showList();
}

function initTable() {

    var colsMap = new Map();
    var colsList = new List();
    colsList.add(setTitleClos("simpleName", "简称", "170px", "", "", ""));
    colsList.add(setTitleClos("allName", "全称", "250px", "", "", ""));
    colsList.add(setTitleClos("tel", "联系电话", "100px", "", "", ""));
    colsList.add(setTitleClos("address", "联系地址", "200px", "", "", ""));

    table.setColsList(colsList);
    table.setAllColsList(colsList);
    table.enableSort = false;//禁用表头排序
    table.onSortChange = showList;
    table.show("table");//里面参数为外层div的id
}

function showList() {

    m.put("status", "0");
    m.put("sort_name", "id");
    m.put("sort_type", "desc");

    beanList = DriverViolationRPC.getAllDriverCompanyList(m);//第一个参数为站点ID，暂时默认为空
    beanList = List.toJSList(beanList);//把list转成JS的List对象

    curr_bean = null;
    table.setBeanList(beanList, "td_list");//设置列表内容的样式
    table.show("table");

    table.getCol("simpleName").each(function (i) {
        if (i > 0) {
            $(this).html('<a href="javascript:openUpdateDriverCompanyPage(' + beanList.get(i - 1).id + ')">' + beanList.get(i - 1).simpleName + '</a>');
        }
    });

    Init_InfoTable(table.table_name);
}

//打开修改窗口
function openUpdateDriverCompanyPage(id) {
    var c_id;
    if (id != null) {
        c_id = id;
    } else {
        c_id = table.getSelecteCheckboxValue("id");
    }

    top.OpenModalWindow("企业信息", "/manager/project/dz_driver/viewDriverCompany.jsp?id=" + c_id +"&topnum="+top.curTabIndex, 385, 250);

}


//删除信息
function deleteDriverCompany() {
    var selectIDS = table.getSelecteCheckboxValue("id");
    var m = new Map();
    m.put("id", selectIDS);
    if (DriverViolationRPC.deleteDriverCompany(m)) {
        top.msgAlert("企业信息" + WCMLang.Delete_success);
        reloadDriverCompanyList();
    }
    else {
        top.msgWargin("企业信息" + WCMLang.Delete_fail);
    }
}

function updateDriverCompanyData() {
    if(!standard_checkInputInfo("driverCompany_table"))
    {
        return;
    }
    var bean = BeanUtil.getCopy(DriverCompanyBean);
    var bool = false;
    if (id != null && id != "") {
        bean.id = id;
        $("#driverCompany_table").autoBind(bean);
        bool = DriverViolationRPC.updateDriverCompany(bean);
    }
    else {
        $("#driverCompany_table").autoBind(bean);
        bean.id = 0;
        bean.status = "0";
        bool = DriverViolationRPC.insertDriverCompany(bean);
    }
    if (bool) {
        top.msgAlert("企业信息保存成功");
        top.getCurrentFrameObj(topnum).reloadDriverCompanyList();
        top.CloseModalWindow();

    } else {
        top.msgWargin("企业信息保存失败，请重新操作");
    }

}

function addInfo() {
    top.OpenModalWindow("企业信息", "/manager/project/dz_driver/viewDriverCompany.jsp?topnum="+top.curTabIndex, 385, 250);
}