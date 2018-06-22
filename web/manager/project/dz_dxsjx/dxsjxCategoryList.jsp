<%@ page contentType="text/html; charset=utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>分类管理</title>

    <jsp:include page="../../include/include_tools.jsp"/>
    <script type="text/javascript" src="js/dxsjxCategory.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            initButtomStyle();
            init_FromTabsStyle();
            if ($.browser.msie && $.browser.version == "6.0" && $("html")[0].scrollHeight > $("html").height()) $("html").css("overflowY", "scroll");
            initTable();
            reloadCategoryDataList();
        });


    </script>
</head>

<body>
<div>
    <table class="table_option" border="0" cellpadding="0" cellspacing="0">
        <tr>
            <td align="left" valign="middle" id="dept_search" class="search_td fromTabs">
                <input id="btn1" name="btn1" type="button" onclick="openAddCategoryPage();" value="添加"/>
                <input id="btn1" name="btn1" type="button"
                       onclick="updateRecord(table,'id','openUpdateCategoryDataPage()');" value="修改"/>
                <input id="btn3" name="btn3" type="button"
                       onclick="deleteRecord(table,'id','deleteCategoryData()');" value="删除"/>
                <span class="blank3"></span>
            </td>
        </tr>
    </table>
    <span class="blank3"></span>
    <div id="table"></div>
    <div id="turn"></div>
</div>
</body>
</html>