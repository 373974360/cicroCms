<%@ page contentType="text/html; charset=utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title></title>
    <meta name="generator" content="featon-Builder"/>
    <meta name="author" content="featon"/>
    <jsp:include page="../../include/include_tools.jsp"/>
    <style>
        * {
            padding: 0;
            margin: 0;
        }

        body {
            background: url('img/bg.png') no-repeat;
            background-size: cover;
        }

        .container {
            width: 75%;
            margin: 0 auto;
            margin-top: 85px;
            text-align: center;
        }

        #title {
            width: 80%;
            margin: 0 auto;
            color: #e4ff00;
            font-weight: bold;
            font-size: 40px;
            text-align: center;
        }

        .result-box {
            clear: both;
            background-color: #fff;
            text-align: center;
            line-height: 57px;
            font-size: 34px;
            width: 75%;
            height: 57px;
            margin: 0 auto;
            margin-top: 70px;
        }

        button {
            width: 200px;
            height: 50px;
            line-height: 50px;
            margin-top: 40px;
            border: none;
            color: red;
            font-size: 24px;
        }

        button:focus {
            outline: none;
        }

        .start {
            background: url("img/btn.png") top center no-repeat;
            cursor: pointer;
            font-weight: bold;
            width: 202px;
            height: 66px;
        }
        #result{
            height: 350px;
            overflow-x: hidden;
            margin: 0 auto;
            width: 75%;
            margin-top: 70px;
            background: #ffffff;
            text-align: left;
        }
        .result-div{
            width: 209px;
            float: left;
            display: inline;
            text-align: center;
            margin-top: 28px;
        }
        .result-div p{
            display: inline-block;
            width: 180px;
            text-align: center;
            line-height: 30px;
            font-weight: bold;
        }
    </style>
    <script language="JavaScript">
        var yxzrs = 0;
        var total = 0;
        var size = 0;
        var key = 0; //中奖下标
        var time = 0; //定时器
        var time2 = 0; //定时器
        var every = 0;
        var id = request.getParameter("id");
        var data = new Array();
        var isLast = false;
        var categoryBean;
        $(document).ready(function () {
            categoryBean = jsonrpc.DxsjxCategoryRPC.getDxsjxCategoryBean(id);
            total = categoryBean.total;
            size = categoryBean.size;
            if (categoryBean.status == 2) {
                alert("该次报名已经完成摇号，不能再次摇号！");
                $("#button").removeClass("start").text("摇号结束").attr("disabled","disabled");
                return;
            } else {
                $("#title").prepend(categoryBean.name);
                var map = new Map();
                map.put("category_id", id);
                map.put("start_num", 0);
                map.put("page_size", 5000);
                map.put("orderby", "id desc");
                var infoList = jsonrpc.DxsjxInfoRPC.getDxsjxList(map);
                infoList = List.toJSList(infoList);
                for (var i = 0; i < infoList.size(); i++) {
                    if(infoList.get(i).status == 1){
                        data.push(infoList.get(i));
                    }
                    if (infoList.get(i).status == 3) {
                        yxzrs += 1;
                    }
                }
                if(yxzrs + size > total){
                    size = total - yxzrs;
                    isLast = true;
                }
            }
        });



        //点击按钮
        function start() {
            if (data.length <= 0) {
                alert("没有可以被选中的数据！");
                return;
            }
            if (total == null || total == "" || total == "0" || total == undefined) {
                alert("请先设置总选中人数");
                return;
            }
            if (size == null || size == "" || size == "0" || size == undefined) {
                alert("请先设置当次抽中人数");
                return;
            }
            every = 0;
            clearInterval(time);
            clearInterval(time2);
            time = setInterval('trunNum()', 10);
            time2 = setInterval('endTrun()', 1000);
            $("#button").text("正在摇号").attr("disabled","disabled");
        }

        function trunNum() {
            key = Math.floor(Math.random() * (data.length - 1));
            var tel = data[key].xm + '****' + data[key].xxmc + '****' + data[key].sxzy;
            $(".result-box").text(tel);
        }

        //停止转动数字
        function endTrun() {
            every += 1;
            showResult(data[key]);
            data.splice(key, 1);
            if(every >= size){
                clearInterval(time);
                clearInterval(time2);
                $("#button").text("摇号结束");
                if(isLast){
                    var map = new Map();
                    map.put("id",id);
                    map.put("status",2);
                    jsonrpc.DxsjxCategoryRPC.updateDxsjxCategoryStatus(map);
                }else{
                    if(categoryBean.status == 0){
                        var map = new Map();
                        map.put("id",id);
                        map.put("status",1);
                        jsonrpc.DxsjxCategoryRPC.updateDxsjxCategoryStatus(map);
                    }
                }
            }
        }

        function showResult(bean){
            var html = '<div class="result-div"><img width="180" height="235" src="'+bean.zp+'" /><p>'+bean.xm+'</p><p style="color:#a29f9f">'+bean.xxmc + '  ' + bean.sxzy+'</p></div>';
            $("#result").prepend(html);
            var map = new Map();
            map.put("id",bean.id);
            map.put("status",3);
            jsonrpc.DxsjxInfoRPC.updateDxsjx(map);
        }

    </script>
</head>

<body>
<div class="container">
    <div id="title">摇号公示</div>
    <div class="result-box"></div>
    <button class="start" id="button" onClick="start()">开始摇号</button>
    <div id="result">

    </div>
</div>
</body>
</html>
