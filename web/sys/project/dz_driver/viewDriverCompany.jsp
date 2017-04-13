<%@ page contentType="text/html; charset=utf-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>企业信息</title>
    <meta name="generator" content="deya-Builder"/>
    <meta name="author" content="deya"/>
    <link type="text/css" rel="stylesheet" href="/manager/styles/main.css" />
    <link type="text/css" rel="stylesheet" href="/manager/styles/sub.css" />
    <jsp:include page="../../include/include_tools.jsp"/>
    <script type="text/javascript" src="js/driverCompanyList.js"></script>

    <SCRIPT LANGUAGE="JavaScript">

        var id = request.getParameter("id");
        var topnum = request.getParameter("topnum");
        var defaultBean;
        $(document).ready(function () {
            initButtomStyle();
            init_input();

            if ($.browser.msie && $.browser.version == "6.0" && $("html")[0].scrollHeight > $("html").height()) $("html").css("overflowY", "scroll");

            if (id != null && id.trim() != "") {
                defaultBean = DriverViolationRPC.getDriverCompanyBean(id);

                if (defaultBean) {
                    $("#driverCompany_table").autoFill(defaultBean);
                }
            }
        });
    </SCRIPT>
</head>

<body>
<div id="driverCompanyDiv">
<span class="blank12"></span>
<form id="form1" name="form1" action="#" method="post">
    <table id="driverCompany_table" class="table_form" border="0" cellpadding="0" cellspacing="0">
        <tbody>
        <tr>
            <th><span class="f_red">*</span>企业名称：</th>
            <td >
                <input id="simpleName" name="simpleName" type="text" class="width200" value="" onblur="checkInputValue('simpleName',false,100,'企业名称','')" />
                <input type="hidden" id="id" name="id" value="">
                <input type="hidden" id="status" name="status" value="">
            </td>
        </tr>
        <tr>
            <th><span class="f_red">*</span>企业全称：</th>
            <td >
                <input id="allName" name="allName" type="text" class="width200" value="" onblur="checkInputValue('allName',false,100,'企业全称','')"/>
            </td>
        </tr>
        <tr>
            <th><span class="f_red">*</span>联系电话：</th>
            <td >
                <input id="tel" name="tel" type="text" class="width200" value="" onblur="checkInputValue('tel',false,100,'联系电话','')"/>
            </td>
        </tr>
        <tr>
            <th><span class="f_red">*</span>企业地址：</th>
            <td >
                <input id="address" name="address" type="text" class="width200" value="" onblur="checkInputValue('address',false,500,'企业地址','')"/>
            </td>
        </tr>
        </tbody>
    </table>
    <span class="blank12"></span>
    <div class="line2h"></div>
    <span class="blank12"></span>
    <table class="table_option" border="0" cellpadding="0" cellspacing="0">
        <tr>
            <td align="left" valign="middle" style="text-indent:100px;">
                <input id="addButton" name="btn1" type="button" onclick="updateDriverCompanyData()" value="保存" />
                <input id="userAddReset" name="btn1" type="button" onclick="formReSet('driverCompany_table',id)" value="重置" />
                <input id="userAddCancel" name="btn1" type="button" onclick="top.CloseModalWindow();" value="取消" />
            </td>
        </tr>
    </table>
    <span class="blank3"></span>
</form>
</div>
</body>
</body>
</html>
