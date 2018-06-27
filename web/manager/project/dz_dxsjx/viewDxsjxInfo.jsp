<%@ page contentType="text/html; charset=utf-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title></title>
    <meta name="generator" content="featon-Builder"/>
    <meta name="author" content="featon"/>
    <jsp:include page="../../include/include_tools.jsp"/>
    <script type="text/javascript" src="/wcm.files/js/jwplayer/jwplayer.js"></script>
    <script type="text/javascript" src="js/dxsjxInfo.js"></script>
    <SCRIPT LANGUAGE="JavaScript">
        <!--
        var topnum = request.getParameter("topnum");
        var id = request.getParameter("id");

        var defaultBean;
        $(document).ready(function () {

            initButtomStyle();
            init_input();

            if ($.browser.msie && $.browser.version == "6.0" && $("html")[0].scrollHeight > $("html").height()) $("html").css("overflowY", "scroll");

            if (id != null && id.trim() != "") {
                defaultBean = DxsjxInfoRPC.getDxsjxBean(id);

                if (defaultBean) {
                    $("#picviewdiv").autoFill(defaultBean);
                    $("#zp").attr("src",defaultBean.zp);
                    $("#tjbxz").attr("href",defaultBean.tjb);
                }
            }

        });

        function updateDxsjxInfo2(status)
        {
            if (id != null && id.trim() != "") {
                m.put("id",id);
                m.put("status",status);
                if(DxsjxInfoRPC.updateDxsjx(m))
                {
                    top.msgAlert("大学生见习报名信息"+WCMLang.Add_success);
                    top.getCurrentFrameObj(topnum).reloadPicViewList();
                    top.tab_colseOnclick(top.curTabIndex)
                }
                else
                {
                    top.msgWargin("大学生见习报名信息"+WCMLang.Add_fail);
                    top.getCurrentFrameObj(topnum).reloadPicViewList();
                    top.tab_colseOnclick(top.curTabIndex)
                }
            }

        }

        //-->
    </SCRIPT>
</head>

<body>
<span class="blank12"></span>
<form id="form1" name="form1" action="#" method="post">
    <div id="picviewdiv">

        <table id="gq_tab" class="table_form" border="1" cellpadding="0" cellspacing="0" style="width:800px;margin:20px;line-height:30px;">
            <tbody>
            <tr>
                <th>姓名：</th>
                <td id="xm" colspan="2"></td>
                <th>性别：</th>
                <td id="xb"  colspan="2"></td>
                <td colspan="2" rowspan="6">
                    <img src="" id="zp" width="180" height="220"/>
                </td>
            </tr>
            <tr>
                <th>民族：</th>
                <td id="mz" colspan="2"></td>
                <th>籍贯：</th>
                <td id="jg" colspan="2"></td>
            </tr>
            <tr>
                <th>年龄：</th>
                <td id="nl" colspan="2"></td>
                <th>QQ：</th>
                <td id="qq" colspan="2"></td>
            </tr>
            <tr>
                <th>政治面貌：</th>
                <td id="zzmm" colspan="2"></td>
                <th>健康状况：</th>
                <td id="jkzk" colspan="2"></td>
            </tr>
            <tr>
                <th>身份证号码：</th>
                <td id="sfzhm" colspan="2"></td>
                <th>联系电话：</th>
                <td id="lxdh" colspan="2"></td>
            </tr>
            <tr>
                <th>所在学校：</th>
                <td id="xxmc" colspan="2"></td>
                <th>所学专业：</th>
                <td id="sxzy" colspan="2"></td>
            </tr>
            <tr>
                <th rowspan="3">家庭成员：</th>
                <th style="text-align: center">称谓</th>
                <th style="text-align: center">姓名</th>
                <th colspan="4" style="text-align: center">工作单位</th>
                <th style="text-align: center">联系电话</th>
            </tr>
            <tr>
                <td id="jtcy1_cw"></td>
                <td id="jtcy1_xm"></td>
                <td id="jtcy1_gzdw" colspan="4"></td>
                <td id="jtcy1_lxdh"></td>
            </tr>
            <tr>
                <td id="jtcy2_cw"></td>
                <td id="jtcy2_xm"></td>
                <td id="jtcy2_gzdw" colspan="4"></td>
                <td id="jtcy2_lxdh"></td>
            </tr>
            <tr>
                <th>家庭详细住址：</th>
                <td id="brtc" colspan="7"></td>
            </tr>
            <tr>
                <th>个人自荐申请：</th>
                <td id="grzjsq" colspan="7"></td>
            </tr>
            <tr>
                <th>推荐表（电子版）：</th>
                <td  colspan="7"><a href="#" id="tjbxz" target="_blank">点击下载</a> </td>
            </tr>
            </tbody>
        </table>
    </div>
    <span class="blank12"></span>
    <div class="line2h"></div>
    <span class="blank3"></span>
    <table class="table_option" border="0" cellpadding="0" cellspacing="0">
        <tr>
            <td align="left" valign="middle" style="text-indent:100px;">
                <input id="btn3" name="btn3" type="button" onclick="updateDxsjxInfo2(1)" value="审核通过" />
                <input id="btn3" name="btn3" type="button" onclick="updateDxsjxInfo2(2)" value="审核不通过" />
                <input id="userAddCancel" name="btn1" type="button" onclick="top.tab_colseOnclick(top.curTabIndex);"
                       value="关闭"/>
            </td>
        </tr>
    </table>
    <span class="blank3"></span>

</form>
</body>
</html>
