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
            background: url('img/bg.jpg') no-repeat;
            background-size: cover;
        }

        .container {
            width: 800px;
            margin: 0 auto;
            margin-top: 200px;
            text-align: center;
        }

        #title {
            width: 480px;
            margin: 0 auto;
            color: #e4ff00;
            font-weight: bold;
            font-size: 28px;
            text-align: center;
        }

        .desc {
            height: auto;
            overflow: hidden;
            margin-top: 30px;
            margin-bottom: 30px;
            text-align: center;
            color: #ffffff;
            line-height: 40px;
            font-size: 20px;
        }

        .desc span {
            margin-right: 50px;
            font-weight: bold;
            color: #e4ff00;
        }

        .desc input {
            line-height: 30px;
            height: 30px;
            width: 80px;
            font-size: 24px;
        }

        .result-box {
            clear: both;
            background-color: #fff;
            text-align: center;
            line-height: 50px;
            font-size: 34px;
            width: 350px;
            height: 50px;
            margin: 0 auto;
        }

        button {
            width: 200px;
            height: 50px;
            line-height: 50px;
            margin-top: 30px;
            border: none;
            color: #fff;
            font-size: 24px;
        }

        button:focus {
            outline: none;
        }

        .start {
            background-color: #428bca;
        }

        .end {
            background-color: #d9534f;
        }
        #result{
            height: 250px;
            overflow-x: hidden;
            margin-top: 50px;
            background: #ffffff;
            text-align: left;
        }
        #result span{
            display: inline-block;
            height: 20px;
            line-height: 20px;
            margin: 10px;
            background: #10ff00;
            color: #4c3934;
            font-size: 16px;
            padding: 5px;
        }
        #save{
            display: none;
            margin: 0 auto;
            margin-top: 30px;
        }
    </style>
    <script language="JavaScript">
        var ybmrs = 0;
        var ytgrs = 0;
        var yxzrs = 0;
        var id = request.getParameter("id");
        var data = new Array();
        $(document).ready(function () {
            var categoryBean = jsonrpc.DxsjxCategoryRPC.getDxsjxCategoryBean(id);
            if (categoryBean.status != 0) {
                alert("该次报名已经摇过号，不能再次摇号！");
                $("#button").removeClass("start").addClass("end").text("摇号结束").attr("disabled","disabled");
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
                ybmrs = infoList.size();
                for (var i = 0; i < infoList.size(); i++) {
                    data.push(infoList.get(i));
                    if (infoList.get(i).status == 1) {
                        ytgrs += 1;
                    }
                    if (infoList.get(i).status == 3) {
                        yxzrs += 1;
                    }
                }
                $("#ybmrs").html(ybmrs);
                $("#ytgrs").html(ytgrs);
                $("#yxzrs").html(yxzrs);
            }
        });

        var total = 0;
        var size = 0;
        var result = new Array();
        var resultIds = new Array();
        var btn = true; //按钮状态未开始还是结束
        var key = 0; //中奖下标
        var time = 0; //定时器
        var time2 = 0; //定时器
        var every = 0;

        //点击按钮
        function start() {
            total = $("#total").val();
            if (total == null || total == "" || total == undefined) {
                alert("请先设置总选中人数");
                return;
            } else {
                total = parseInt(total);
                $("#total").attr("readonly", "readonly");
            }
            size = $("#size").val();
            if (size == null || size == "" || size == undefined) {
                alert("请先设置当次抽中人数");
                return;
            } else {
                size = parseInt(size);
                if(result.length + size > total){
                    alert("已超出总选中人数，请重新设置当次抽中人数");
                    return;
                }else{
                    $("#size").attr("readonly", "readonly");
                }
            }
            if (btn) {
                if ((result.length >= total)) {
                    $("button").hide();
                    alert('摇号已结束！！！');
                    return;
                } else {
                    btn = false;
                    $("#button").removeClass("start").addClass("end").text("正在摇号").attr("disabled","disabled");
                    startTrun();
                }
            }
        }

        function trunNum() {
            key = Math.floor(Math.random() * (data.length - 1));
            var tel = data[key].xm + '****' + data[key].xxmc + '****' + data[key].sxzy;
            $(".result-box").text(tel);
        }

        //开始转动数字
        function startTrun() {
            every = 0;
            clearInterval(time);
            clearInterval(time2);
            time = setInterval('trunNum()', 10);
            time2 = setInterval('endTrun()', 5000);
        }

        //停止转动数字
        function endTrun() {
            every += 1;
            var tel = data[key].xm + '****' + data[key].xxmc + '****' + data[key].sxzy;
            $("#result").append("<span>" + tel + "</span>");
            result.push(data[key]);
            resultIds.push(data[key].id);
            data.splice(key, 1);
            $("#yxzrs").html(result.length);
            console.log(result);
            if(every >= size){
                clearInterval(time);
                clearInterval(time2);
                btn = true;
                if(result.length == total){
                    $("#button").text("摇号结束");
                    $("#save").css("display","block");
                }else{
                    $("#button").removeClass("end").addClass("start").text("开始摇号").removeAttr("disabled");
                    $("#size").removeAttr("readonly");
                }
            }

        }

        function save(){
            var map = new Map();
            map.put("id",id);
            map.put("status",1);
            jsonrpc.DxsjxCategoryRPC.updateDxsjxCategoryStatus(map);
            var map2 = new Map();
            map2.put("id",resultIds.join(","));
            map2.put("status",3);
            jsonrpc.DxsjxInfoRPC.updateDxsjx(map2);
            alert('摇号结果保存成功！');
            $("#save").css("display","none");
        }

    </script>
</head>

<body>
<div class="container">
    <div id="title">摇号公示</div>
    <div class="desc">
        已报名人数：<span id="ybmrs"></span>
        已通过审核人数：<span id="ytgrs"></span>
        已选中人数：<span id="yxzrs"></span>
    </div>
    <div class="desc">总选中人数：<input id="total"/>&nbsp;&nbsp;&nbsp;当次抽中人数：<input id="size"/></div>
    <div class="result-box"></div>
    <button class="start" id="button" onClick="start()">开始摇号</button>
    <div id="result">

    </div>
    <button id="save" class="start" onClick="save()">保存结果</button>
</div>
</body>
</html>
