<%@ page contentType="text/html; charset=utf-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>车辆违规信息</title>
    <meta name="generator" content="deya-Builder"/>
    <meta name="author" content="deya"/>
    <link type="text/css" rel="stylesheet" href="/sys/styles/main.css"/>
    <link type="text/css" rel="stylesheet" href="/sys/styles/sub.css"/>
    <script type="text/javascript" src="../../js/My97DatePicker/WdatePicker.js"></script>
    <jsp:include page="../../include/include_tools.jsp"/>

    <script type="text/javascript" src="js/driverViolationList.js"></script>
    <style>
        input[type="text"] {
            width: 60%;
            height: 100%;
        }

        input[type="password"] {
            width: 60%;
            height: 100%;
        }

        table tr, td {
            height: 24px;
            line-height: 24px;
        }

        table tr {
            text-align: center;
            vertical-align: middle;
        }

        .table_form td {
            text-align: center;
        }

        select {
            height: 24px;
            line-height: 24px;
            width: 285px;
        }
    </style>
    <SCRIPT LANGUAGE="JavaScript">

        var topnum = request.getParameter("topnum");
        var id = request.getParameter("id");

        var defaultBean;
        $(document).ready(function () {
            initButtomStyle();
            init_input();

            if ($.browser.msie && $.browser.version == "6.0" && $("html")[0].scrollHeight > $("html").height()) $("html").css("overflowY", "scroll");

            var map = new Map();
            map.put("status", "0");
            map.put("sort_name", "id");
            map.put("sort_type", "desc");
            var compList = DriverViolationRPC.getAllDriverCompanyList(map);
            compList = List.toJSList(compList);
            for (var i = 0; i < compList.size(); i++) {
                var selObj = $("#companyId");
                var value = compList.get(i).id;
                var text = compList.get(i).simpleName;
                selObj.append("<option value='" + value + "'>" + text + "</option>");
            }
            if (id != null && id.trim() != "") {
                defaultBean = DriverViolationRPC.getDriverViolationBean(id);
                if (defaultBean) {
                    $("#driverViolationdiv").autoFill(defaultBean);
                }
            }
        });
    </SCRIPT>
</head>

<body>
<span class="blank12"></span>

<div style="width:555px;">
    <div id="driverViolationdiv">
        <input type="hidden" id="id" name="id" value=""/>
        <table style="width:555px;" class="table_form" border="0" cellpadding="0" cellspacing="0" id="driverViolation_table"
               name="driverViolation_table">
            <tr>
                <td colspan="6" style="font-size:14px; text-align:center;"><B>车辆违规信息</B></td>
            </tr>
            <tr>
                <td><span class="f_red">*</span>姓名</td>
                <td colspan="4" style="text-align:left;">
                    <input type="text" id="name" name="name" class="width200"  value="" onblur="checkInputValue('name',false,300,'提交站点','')"/>
                </td>
            </tr>
            <tr>
                <td><span class="f_red">*</span>车牌号码</td>
                <td colspan="4" style="text-align:left;">
                    <input type="text" id="carNo" name="carNo" class="width200" value="" onblur="checkInputValue('carNo',false,20,'提交人','')"/>
                </td>
            </tr>
            <tr>
                <td><span class="f_red">*</span>违规时间</td>
                <td colspan="4" style="text-align:left;">
                	<input id="violationTime" name="violationTime" type="text"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true,readOnly:true})" readonly="readonly" />
                </td>
            </tr>
            <tr>
                <td>违规原因</td>
                <td colspan="4" style="text-align:left;">
                    <textarea id="reason" name="reason" rows="5" cols="61"></textarea>
                </td>
            </tr>
            <tr>
                <td>处理意见</td>
                <td colspan="4" style="text-align:left;">
                    <textarea id="advice" name="advice" rows="5" cols="61"></textarea>
                </td>
            </tr>
             <tr>
                <td>企业名称</td>
                <td colspan="4" style="text-align:left;">
                    <select id="companyId" name="companyId"></select>
                </td>
            </tr>
        </table>
    </div>
    <span class="blank12"></span>

    <div class="line2h"></div>
    <span class="blank3"></span>
    <table class="table_option" border="0" cellpadding="0" cellspacing="0">
        <tr>
            <td align="center" valign="middle">
                <input name="btn1" type="button" onclick="updateSiteErrorData()" value="保存"/>
                <input id="userAddCancel" name="btn1" type="button" onclick="top.tab_colseOnclick(top.curTabIndex);"
                       value="取消"/>
            </td>
        </tr>
    </table>
    <span class="blank3"></span>
</div>
</body>
</html>
