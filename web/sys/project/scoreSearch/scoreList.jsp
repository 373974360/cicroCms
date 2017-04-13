<%@ page contentType="text/html; charset=utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>成绩信息列表</title>
    <meta name="generator" content="featon-Builder"/>
    <meta name="author" content="featon"/>
    <jsp:include page="../../include/include_tools.jsp"/>
    <script type="text/javascript" src="js/scoreList.js"></script>
    <script type="text/javascript">
        var id = request.getParameter("id");
        var snum = 0;
        $(document).ready(function () {
            initButtomStyle();
            init_FromTabsStyle();
            if ($.browser.msie && $.browser.version == "6.0" && $("html")[0].scrollHeight > $("html").height()) $("html").css("overflowY", "scroll");

            initTable();
            reloadScoreList();
        });

    </script>
</head>

<body>
<div>
    <table class="table_option" border="0" cellpadding="0" cellspacing="0" >
		<tr>		
			<td class="fromTabs">
				<span class="blank3"></span>
			</td>
			<td align="left" valign="middle" id="dept_search" class="search_td fromTabs" >				
				姓名：<input id="name" id="name" type="text" class="input_text" value=""  />
				身份证号：<input id="sfzh" id="sfzh" type="text" class="input_text" value=""  />
				<input id="btnSearch" type="button" class="btn x2" value="搜索" onclick="SearchHandl()"/>
				<span class="blank3"></span>
			</td>		
		</tr>
	</table>
    <span class="blank5"></span>

    <div id="table"></div>
    <!-- 列表DIV -->
	<div id="turn"></div>
    <!-- 翻页DIV -->
    <span class="blank5"></span>
</div>
</body>
</html>