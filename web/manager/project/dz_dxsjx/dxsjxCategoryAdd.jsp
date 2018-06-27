<%@ page contentType="text/html; charset=utf-8" %>
<%
    String id = request.getParameter("id");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>分类维护</title>

    <jsp:include page="../../include/include_tools.jsp"/>
    <script type="text/javascript" src="js/dxsjxCategory.js"></script>
    <script type="text/javascript">

        var id = "<%=id%>";
        var defaultBean;

        $(document).ready(function () {
            initButtomStyle();
            init_input();

            if (id != "" && id != "null" && id != null) {
                defaultBean = DxsjxCategoryRPC.getDxsjxCategoryBean(id);
                if (defaultBean) {
                    $("#Category_table").autoFill(defaultBean);
                }
                $("#id").val(id);
                $("#addButton").click(updateCategoryData);
            }
            else {
                $("#addButton").click(addCategoryData);
            }
        });
    </script>
</head>

<body>
<span class="blank12"></span>
<form id="form1" name="form1" action="#" method="post">
    <table id="Category_table" class="table_form" border="0" cellpadding="0" cellspacing="0">
        <tbody>
        <tr>
            <th><span class="f_red">*</span>分类名称：</th>
            <td class="width250">
                <input id="name" name="name" type="text" class="width200" value=""
                       onblur="checkInputValue('name',false,80,'分类名称','')"/>
                <input type="hidden" name="id" id="id" value="0"/>
            </td>
        </tr>
        <tr>
            <th><span class="f_red">*</span>总选中人数：</th>
            <td class="width250">
                <input id="total" name="total" type="text" class="width200" value=""
                       onblur="checkInputValue('total',false,80,'总选中人数','')"/>
            </td>
        </tr>
        <tr>
            <th><span class="f_red">*</span>每次摇号选中人数：</th>
            <td class="width250">
                <input id="size" name="size" type="text" class="width200" value=""
                       onblur="checkInputValue('total',false,80,'每次摇号选中人数','')"/>
            </td>
        </tr>
        </tbody>
    </table>
    <span class="blank12"></span>
    <div class="line2h"></div>
    <span class="blank3"></span>
    <table class="table_option" border="0" cellpadding="0" cellspacing="0">
        <tr>
            <td align="left" valign="middle" style="text-indent:100px;">
                <input id="addButton" name="btn1" type="button" onclick="" value="保存"/>
                <input id="userAddReset" name="btn1" type="button" onclick="formReSet('Category_table',id)"
                       value="重置"/>
                <input id="userAddCancel" name="btn1" type="button" onclick="closePage();" value="取消"/>
            </td>
        </tr>
    </table>
    <span class="blank3"></span>
</form>
</body>
</html>
