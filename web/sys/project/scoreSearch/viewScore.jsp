<%@ page contentType="text/html; charset=utf-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>成绩信息</title>
    <meta name="generator" content="featon-Builder"/>
    <meta name="author" content="featon"/>
    <link type="text/css" rel="stylesheet" href="/manager/styles/main.css"/>
    <link type="text/css" rel="stylesheet" href="/manager/styles/sub.css"/>
    <jsp:include page="../../include/include_tools.jsp"/>

    <script type="text/javascript" src="../../js/pinyin/pinyin.dict.src.js"></script>
    <script type="text/javascript" src="../../js/pinyin/pinyin.js"></script>
    <script type="text/javascript" src="js/scoreList.js"></script>
    <SCRIPT LANGUAGE="JavaScript">

        var id = request.getParameter("id");
        var topnum = request.getParameter("topnum");

        var defaultBean;
        $(document).ready(function () {
            initButtomStyle();
            init_input();

            if ($.browser.msie && $.browser.version == "6.0" && $("html")[0].scrollHeight > $("html").height()) $("html").css("overflowY", "scroll");

            if (id != null && id.trim() != "") {
                defaultBean = ScoreRPC.getScoreBean(id);

                if (defaultBean) {
                	var excelTitleList = jsonrpc.ExcelTitleRPC.getExcelTitleListByExamID(defaultBean.examId);
            		excelTitleList = List.toJSList(excelTitleList);
            		var scoreData = defaultBean.excelData.split("&");
            		var j = 0;
            		for(var i = excelTitleList.size() - 1; i >= 0; i--){
            			var excelTitleBean = excelTitleList.get(i);
            			$("#score_table").append("<tr><th>");
            			$("#score_table").append(excelTitleBean.cname)
            			$("#score_table").append(":");
            			$("#score_table").append("</th><td>");
            			$("#score_table").append(scoreData[j]);
            			$("#score_table").append("</td></tr>");
            			j++;
            		}
                }
            }
        });
    </SCRIPT>
</head>

<body>
<span class="blank5"></span>

<div style="width:475px;">
    <div id="scorediv">
        <input type="hidden" id="id" name="id" value=""/>
        <table style="width:475px;" class="table_form" border="0" cellpadding="0" cellspacing="0" id="score_table"
               name="score_table">
            <tr>
                <td colspan="6" style="font-size:14px; text-align:center;"><B>考试信息</B></td>
            </tr>
        </table>
    </div>
    <span class="blank12"></span>

    <div class="line2h"></div>
    <span class="blank12"></span>
    <table class="table_option" border="0" cellpadding="0" cellspacing="0">
        <tr>
            <td align="center" valign="middle">
                <input id="userAddCancel" name="btn1" type="button" onclick="top.CloseModalWindow();"
                       value="关闭"/>
            </td>
        </tr>
    </table>
    <span class="blank3"></span>
</div>
</body>
</html>
