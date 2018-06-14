var DriverViolationRPC = jsonrpc.DriverViolationRPC;
var DriverViolationBean = new Bean("com.cicro.project.dz_driver.DriverViolationBean", true);
var selectBean = null;//当前选中项对象
var serarch_con = "";//查询条件
var tp = new TurnPage();
var val = new Validator();
var beanList = null;
var table = new Table();
table.table_name = "driverViolation_table";
var current_role_bean;
var is_button_click = true;//是否是点击的按钮触发事件
var current_page_num = 1;
var status = 0;

function reloadDriverViolationList() {
    showList();
    showTurnPage();
}

function initTable() {

    var colsMap = new Map();
    var colsList = new List();

    colsList.add(setTitleClos("name", "姓名", "170px", "", "", ""));
    colsList.add(setTitleClos("carNo", "车牌号码", "150px", "", "", ""));
    colsList.add(setTitleClos("reason", "违规行为 ", "130px", "", "", ""));
    colsList.add(setTitleClos("advice", "处理结果 ", "130px", "", "", ""));
    colsList.add(setTitleClos("simpleName", "企业名称 ", "130px", "", "", ""));

    table.setColsList(colsList);
    table.setAllColsList(colsList);
    table.enableSort = false;//禁用表头排序
    table.onSortChange = showList;
    table.show("table");//里面参数为外层div的id
}

function showList() {

    var m = new Map();
    m.put("start_num", tp.getStart());
    m.put("page_size", tp.pageSize);
    m.put("status", status);
    m.put("sort_name", "id");
    m.put("sort_type", "desc");

    beanList = DriverViolationRPC.getDriverViolationList(m);//第一个参数为站点ID，暂时默认为空
    beanList = List.toJSList(beanList);//把list转成JS的List对象

    curr_bean = null;
    table.setBeanList(beanList, "td_list");//设置列表内容的样式
    table.show("table");

    table.getCol("name").each(function (i) {
        if (i > 0) {
        	$(this).html('<a href="javascript:openUpdateDriverViolationPage(' + beanList.get(i - 1).id + ')">' + beanList.get(i - 1).name + '</a>');
        }
    });

    Init_InfoTable(table.table_name);
}

function showTurnPage(){
    var m = new Map();
    m.put("status", status);

    tp.total = DriverViolationRPC.getDriverViolationCount(m);

    tp.show("turn","");
    tp.onPageChange = showList;
}

function initTabAndStatus()
{
    $(".fromTabs > li").each(function(){
        $(this).hover(
            function () {
                $(this).addClass("list_tab_over");
            },
            function () {
                $(this).removeClass("list_tab_over");
            }
        );

        $(this).click(
            function () {
                $(".fromTabs > li").removeClass("list_tab_cur");
                $(this).addClass("list_tab_cur");
                $(".infoListTable").addClass("hidden");

                if($(this).attr("num") != "" && $(this).attr("num") != null)
                {
                    $("#listTable_"+$(this).attr("num")).removeClass("hidden");
                    changeStatus(parseInt($(this).attr("num")));
                }
                else
                {
                    $("#listTable_"+$(this).index()).removeClass("hidden");
                    changeStatus($(this).index());
                }
            }
        );
    });
}

function changeStatus(num){
    switch(num){
        case 0:
            status = "0";
            break;
        case 1:
            status = "1";
            break;
        case 2:
            status = "2";
            break;
    }
    snum = num;
    reloadDriverViolationList();
}

//打开修改窗口
function openUpdateDriverViolationPage(id) {
    var c_id;
    if (id != null && id != '') {
        c_id = id;
    } else {
        c_id = table.getSelecteCheckboxValue("id");
    }
    top.OpenModalWindow("车辆违规信息", "/manager/project/dz_driver/viewDriverViolation.jsp?id=" + c_id +"&topnum="+top.curTabIndex, 535, 520);
}


//删除信息
function deleteDriverViolation() {
    var selectIDS = table.getSelecteCheckboxValue("id");
    var m = new Map();
    m.put("id", selectIDS);
    if (DriverViolationRPC.deleteDriverViolation(m)) {
        top.msgAlert("车辆违规信息" + WCMLang.Delete_success);
        reloadDriverViolationList();
    }
    else {
        top.msgWargin("车辆违规信息" + WCMLang.Delete_fail);
    }
}

function updateDriverViolationData() {
    if(!standard_checkInputInfo("driverViolation_table"))
    {
        return;
    }

    /*
    var phone=$.trim($("#phone").val());

    var reg = /^1[3|4|5|7|8][0-9]\d{4,8}$/;
    if(!reg.test(phone))
    {
        jQuery.simpleTips("phone","手机号码格式不对！",2000);
        return;
    }
    */

    var bean = BeanUtil.getCopy(DriverViolationBean);
    var bool = false;
    if (id != null && id != "") {
        bean.id = id;
        $("#driverViolation_table").autoBind(bean);
        bool = DriverViolationRPC.updateDriverViolation(bean);
    }
    else {
        $("#driverViolation_table").autoBind(bean);
        bean.id = 0;
        bean.status = "0";
        bool = DriverViolationRPC.insertDriverViolation(bean);
    }
    if (bool) {
        top.msgAlert("车辆违规信息保存成功");
        top.getCurrentFrameObj(topnum).reloadDriverViolationList();
        top.CloseModalWindow();
    } else {
        top.msgWargin("车辆违规信息保存失败，请重新操作");
    }
}

function addInfo() {
    top.OpenModalWindow("车辆违规信息", "/manager/project/dz_driver/viewDriverViolation.jsp?&topnum="+top.curTabIndex, 535, 520);
}

function savePublishFlag(publish_flag) {
    var selectIDS = table.getSelecteCheckboxValue("id");
    var m = new Map();
    m.put("status", publish_flag);
    m.put("id", selectIDS);

    if (DriverViolationRPC.publishDriverViolation(m)) {
        top.msgAlert("车辆违规信息状态设置成功");
        reloadDriverViolationList();
    } else {
        top.msgWargin("车辆违规信息状态设置失败，请重新操作");
    }
}
