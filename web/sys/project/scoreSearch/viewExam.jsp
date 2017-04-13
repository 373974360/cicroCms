<%@ page contentType="text/html; charset=utf-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>考试信息</title>
    <meta name="generator" content="featon-Builder"/>
    <meta name="author" content="featon"/>
    <link type="text/css" rel="stylesheet" href="/manager/styles/main.css"/>
    <link type="text/css" rel="stylesheet" href="/manager/styles/sub.css"/>
    <jsp:include page="../../include/include_tools.jsp"/>

    <script type="text/javascript" src="../../js/pinyin/pinyin.dict.src.js"></script>
    <script type="text/javascript" src="../../js/pinyin/pinyin.js"></script>
    <script type="text/javascript" src="js/examList.js"></script>
    <SCRIPT LANGUAGE="JavaScript">

        var id = request.getParameter("id");
        var topnum = request.getParameter("topnum");

        var defaultBean;
        $(document).ready(function () {
            initButtomStyle();
            init_input();

            if ($.browser.msie && $.browser.version == "6.0" && $("html")[0].scrollHeight > $("html").height()) $("html").css("overflowY", "scroll");

            if (id != null && id.trim() != "") {
                defaultBean = ExamRPC.getExamBean(id);

                if (defaultBean) {
                    $("#examdiv").autoFill(defaultBean);
                }
            }
        });
    </SCRIPT>
</head>

<body>
<span class="blank5"></span>

<div style="width:475px;">
    <div id="examdiv">
        <input type="hidden" id="id" name="id" value=""/>
        <table style="width:475px;" class="table_form" border="0" cellpadding="0" cellspacing="0" id="exam_table"
               name="exam_table">
            <tr>
                <td colspan="6" style="font-size:14px; text-align:center;"><B>考试信息</B></td>
            </tr>
            <tr>
                <th><span class="f_red">*</span>名称：</th>
                <td colspan="4" style="text-align:left;">
                    <input type="text" id="name" name="name" class="width250"  value="" onblur="checkInputValue('name',false,300,'名称','')"/>
                </td>
            </tr>
            <tr>
                <th><span class="f_red">*</span>描述：</th>
                <td colspan="4" style="text-align:left;">
                    <textarea id="description" name="description" rows="10" class="width250" onblur="checkInputValue('description',false,300,'描述','')">
                    </textarea>
                </td>
            </tr>
        </table>
    </div>
    <span class="blank12"></span>

    <div class="line2h"></div>
    <span class="blank12"></span>
    <table class="table_option" border="0" cellpadding="0" cellspacing="0">
        <tr>
            <td align="center" valign="middle">
                <input name="btn1" type="button" onclick="updateExamData()" value="保存"/>
                <input id="userAddCancel" name="btn1" type="button" onclick="top.CloseModalWindow();"
                       value="取消"/>
            </td>
        </tr>
    </table>
    <span class="blank3"></span>
</div>
</body>
</html>
