<%@ page contentType="text/html; charset=utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>全镇贫困户信息</title>
<meta name="generator" content="deya-Builder" />
<meta name="author" content="deya" />
<jsp:include page="../../include/include_tools.jsp"/>
<script type="text/javascript" src="js/pkhList.js"></script>
<script type="text/javascript" src="/manager/js/My97DatePicker/WdatePicker.js"></script>
<style>

tr,td,th{ height:30px; line-height:30px; border:1px solid #bbccde}
table tr{ text-align:center; vertical-align:middle;}
.thTitle{ font-weight:bold; text-align:center; color:#416aa3;}
.thTitle p{color:#416aa3;}
input[type="text"]{border:none; border:0px; width:85%; height:100%; background:none; padding-left:15px;}
select{ margin-left:7px; width:85%;}
</style>
<SCRIPT LANGUAGE="JavaScript">
	
var topnum = request.getParameter("topnum");
var id = request.getParameter("id");

var defaultBean;
$(document).ready(function () {				
	initButtomStyle();
	init_FromTabsStyle();
	init_input();
	
	if($.browser.msie&&$.browser.version=="6.0"&&$("html")[0].scrollHeight>$("html").height()) $("html").css("overflowY","scroll");
				
	if(id != null && id.trim() != "")
	{
		defaultBean = PkhRPC.getPkhBean(id,false);

		if(defaultBean)
		{
			$("#pkhdiv").autoFill(defaultBean);
			$("input[name='hsx'][value='"+ defaultBean.hsx+"']").attr("checked",true);
			$("input[name='zyzpyy'][value='"+ defaultBean.zyzpyy+"']").attr("checked",true);
			$("input[name='tpbs'][value='"+ defaultBean.tpbs+"']").attr("checked",true);
			$("input[name='khyh'][value='"+ defaultBean.khyh+"']").attr("checked",true);
		}		
	}
}); 


/*
function inisb_xmglForm()
{  
  var validator = $("#form1").validate({
    debug:true,
    rules: {
      company: {
        required: true
      },
      name:{
        required: true,
      },
    gender:{
        required: true,
      },
    zhiwu:{
        required: true,
      },
    bianhao:{
        required: true,
      }
    },
    messages: {
      company: {
        required: "　请输入单位名称！"
      },
      
      name:{
        required: "　请输入姓名！",
      },
    gender:{
        required: "　请选择性别！",
      },
    zhiwu:{
        required: "　请输入职务！",
      },
    bianhao:{
        required: "　请输入编号！",
      }
    },
    
    errorPlacement: function(error, element) {
      //error.appendTo( element.parent("td").find("div.cError") );
      error.appendTo(element.parent());
    },

    submitHandler: function(form) {
		document.getElementById('form1').submit();  
    },

    success: function(label) {
      
    }
  });
  
}
*/
	//-->
	</SCRIPT>	
</head>

<body>
<span class="blank12"></span>
<div style="width:755px;">
<div id="pkhdiv" >
<!-- 隐含字段区域　开始 -->
<!-- 隐含字段区域　结束 -->

	<table  style="width:750px;" border="1" cellpadding="0" cellspacing="0" id="pkh_table" name = "pkh_table"  style="line-height:30px;">
	  <tbody>
		<tr>
		  <td colspan="7" class="thTitle">全镇贫困户信息</td>
		</tr>
		<tr>
		  <td class="thTitle">户主姓名</td>
		  <td colspan="3"><input type="text" id="hzxm" name="hzxm"  /></td>
		  <td class="thTitle">户主证件号码</td>
		  <td colspan="2"><input type="text" id="hzzjhm" name="hzzjhm"  /></td>
		</tr>
		<tr>
		  <td class="thTitle">人数</td>
		  <td colspan="3"><input type="text" id="rs" name="rs"  /></td>
		  <td class="thTitle"><p >年人均纯收入</p></td>
		  <td colspan="2"><input type="text" id="nrjcsr" name="nrjcsr"  /></td>
		</tr>
		<tr>
		  <td class="thTitle"><p >户属性</p></td>
		  <td colspan="3">
		  	<select id ="hsx" name ="hsx">
		  		<option value="扶贫户">扶贫户</option>
		  		<option value="低保户">低保户</option>
		  		<option value="低保贫困户">低保贫困户</option>
		  		<option value="五保户">五保户</option>
		  	</select>
		  </td>
		  <td class="thTitle"><p >主要致贫原因</p></td>
		  <td colspan="2">
		  	<select id ="zyzpyy" name ="zyzpyy">
		  		<option value="因病">因病</option>
		  		<option value="因残">因残</option>
		  		<option value="因灾">因灾</option>
		  		<option value="因学">因学</option>
		  		<option value="缺土地">缺土地</option>
		  		<option value="缺水">缺水</option>
		  		<option value="缺资金">缺资金</option>
		  		<option value="缺劳力">缺劳力</option>
		  		<option value="缺技术">缺技术</option>
		  		<option value="交通条件落后">交通条件落后</option>
		  		<option value="自身发展动力不足">自身发展动力不足</option>
		  		<option value="其它">其它</option>
		  	</select>
		  </td>
		</tr>
		<tr>
		  <td class="thTitle">脱贫标识</td>
		  <td colspan="3">
		  	<select id ="tpbs" name ="tpbs">
		  		<option value="未脱贫">未脱贫</option>
		  		<option value="脱贫">脱贫</option>
		  	</select>
		  </td>
		  <td class="thTitle"><p >联系电话</p></td>
		  <td colspan="2"><input type="text" id="lxdh" name="lxdh"  /></td>
		</tr>
		<tr>
		  <td class="thTitle"><p >开户银行</p></td>
		  <td colspan="3">
		  	<select id ="khyh" name ="khyh">
		  		<option value="陕西信合">陕西信合</option>
		  		<option value="中国农业银行">中国农业银行</option>
		  		<option value="中国邮政储蓄银行">中国邮政储蓄银行</option>
		  	</select>
		  </td>
		  <td class="thTitle"><p >银行账号</p></td>
		  <td colspan="2"><input type="text" id="yhzh" name="yhzh"  /></td>
		</tr>
		<tr>
		  <td class="thTitle">识别标准</td>
		  <td colspan="3">
		  	<select id ="sbbz" name ="sbbz">
		  		<option value="国家标准">国家标准</option>
		  		<option value="省定标准">省定标准</option>
		  	</select>
		  </td>
		  <td class="thTitle"><p >省(区，市)</p></td>
		  <td colspan="2">
		  	<select id ="sheng" name ="sheng">
		  		<option value="陕西省">陕西省</option>
		  	</select>
		  </td>
		</tr>
		<tr>
		  <td class="thTitle"><p >市(地、州、盟)</p></td>
		  <td colspan="3">
		  	<select id ="shi" name ="shi">
		  		<option value="汉中市">汉中市</option>
		  	</select>
		  </td>
		  <td class="thTitle"><p >县(市、区、旗)</p></td>
		  <td colspan="2">
		  	<select id ="quxian" name ="quxian">
		  		<option value="宁强县">宁强县</option>
		  	</select>
		  </td>
		</tr>
		<tr>
		  <td class="thTitle">镇(乡)</td>
		  <td colspan="3">
		  	<select id ="xiangzhen" name ="xiangzhen">
		  		<option value="汉源镇">汉源镇</option>
		  	</select>
		  </td>
		  <td class="thTitle"><p >行政村</p></td>
		  <td colspan="2"><input type="text" id="xzc" name="xzc"  /></td>
		</tr>
		<tr>
		  <td class="thTitle"><p >组</p></td>
		  <td colspan="6"><input type="text" id="zu" name="zu"  /></td>
		</tr>
		<tr>
		  <td class="thTitle">录入人</td>
		  <td colspan="3"><input type="text" id="lrr" name="lrr"  /></td>
		  <td class="thTitle"><p >录入时间</p></td>
		  <td colspan="2">
		  	<input id="lrsj" name="lrsj" type="text" value="" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true,readOnly:true})" readonly="readonly"/>
		  </td>
		</tr>
		<tr>
		  <td class="thTitle"><p >审核人</p></td>
		  <td colspan="3"><input type="text" id="shr" name="shr"  /></td>
		  <td class="thTitle"><p >审核时间</p></td>
		  <td colspan="2">
		  	<input id="shsj" name="shsj" type="text" value="" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true,readOnly:true})" readonly="readonly"/>	
		  </td>
		</tr>
		</tbody>
	</table>
</div>
<span class="blank12"></span>
<span class="blank3"></span>
<table class="table_option" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td align="center" valign="middle" style="text-indent:100px;">
			<input  name="btn1" type="button" onclick="updatePkhData()"  value="保存" />	
			<input id="userAddCancel" name="btn1" type="button" onclick="top.tab_colseOnclick(top.curTabIndex);" value="取消" />	
		</td>
	</tr>
</table>
<span class="blank3"></span>
</div>
</body>
</html>
