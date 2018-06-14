<%@ page contentType="text/html; charset=utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>报名网站信息</title>
<meta name="generator" content="featon-Builder" />
<meta name="author" content="featon" />
<jsp:include page="../../include/include_tools.jsp"/>
<script type="text/javascript" src="js/wzxxList.js"></script>
<script type="text/javascript" src="/manager/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="/manager/js/kindeditor/kindeditor.js"></script>
<style>

tr,td,th{ height:30px; line-height:30px; border:1px solid #bbccde}
table tr{ text-align:center; vertical-align:middle;}
.thTitle{ font-weight:bold; text-align:center; color:#416aa3;}
.thTitle p{color:#416aa3;}
input[type="text"]{border:none; border:0px; width:85%; height:100%; background:none; padding-left:15px;}
input[type="radio"],input[type="checkbox"]{ margin-left:15px;}
</style>
<SCRIPT LANGUAGE="JavaScript">
	
var site_id = request.getParameter("site_id");
var topnum = request.getParameter("topnum");
var id = request.getParameter("id");
var img_count = 0;

//alert(site_id + topnum + id);
var defaultBean;
$(document).ready(function () {				
	initButtomStyle();
	init_FromTabsStyle();
	init_input();
	init_editer_turnpage("wzjyfw");
	init_editer_turnpage("wzjj");
	init_editer_turnpage("hjry");
	//inisb_xmglForm();
	
	if($.browser.msie&&$.browser.version=="6.0"&&$("html")[0].scrollHeight>$("html").height()) $("html").css("overflowY","scroll");
				
	if(id != null && id.trim() != "")
	{
		defaultBean = WzxxRPC.getWzxxBean(id,false);

		if(defaultBean)
		{
			$("#xmkdiv").autoFill(defaultBean);
			if(defaultBean.bmlx == "1")
			{
				$("#grxx").removeClass("hidden");
				$("#dwmc2").val(defaultBean.dwmc);
				$("#lxdz2").val(defaultBean.lxdz);
				$("#gsfr2").val(defaultBean.gsfr);
				$("#yb2").val(defaultBean.yb);
			}
			else
			{
				$("#dwxx").removeClass("hidden");
				$("#dwmc").val(defaultBean.dwmc);
				$("#lxdz").val(defaultBean.lxdz);
				$("#gsfr").val(defaultBean.gsfr);
				$("#yb").val(defaultBean.yb);
				$("#zczj").val(defaultBean.zczj);
				$("#wzbl").val(defaultBean.wzbl);
				$("input[name='dwxz'][value='"+ defaultBean.dwxz+"']").attr("checked",true);
			}
			KE.html("wzjyfw", defaultBean.wzjyfw);
			KE.html("wzjj", defaultBean.wzjj);
			KE.html("hjry", defaultBean.hjry);
			$("input[name='cplx'][value='"+ defaultBean.cplx+"']").attr("checked",true);
			$("input[name='zcyh'][value='"+ defaultBean.zcyh+"']").attr("checked",true);
			$("input[name='iscf'][value='"+ defaultBean.iscf+"']").attr("checked",true);
			$("input[name='isglzd'][value='"+ defaultBean.isglzd+"']").attr("checked",true);
			$("input[name='isaqlc'][value='"+ defaultBean.isaqlc+"']").attr("checked",true);
			$("input[name='aqysl'][value='"+ defaultBean.aqysl+"']").attr("checked",true);
			//$("input[name='fjpd'][value='"+ defaultBean.fjpd+"']").attr("checked",true);
			$("input[name='baxx'][value='"+ defaultBean.baxx+"']").attr("checked",true);
			var str= new Array();   
			str = defaultBean.smrz.split("$");      
			for (i=0;i<str.length ;i++ )   
			{   
				$("input[name='smrz'][value='"+ str[i] +"']").attr("checked",true);
			}
			

			//$("input[name='cplx'][value=0]").attr("checked",'checked');
			//$("input[type=radio][value=0]").attr("checked",'checked');
			//$("input[type=radio][value=0]").attr("checked",'checked');
			//$("input[type=radio][value=0]").attr("checked",'checked');
			//$("input[type=radio][value=0]").attr("checked",'checked');
			

		}		
	}
	//$("#housediv input").attr("disabled",true);  是否可编辑
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
<div id="xmkdiv" >
<!-- 隐含字段区域　开始 -->
 <input type="hidden" id="auther_type" name="auther_type" value="1"/>
 <!-- 隐含字段区域　结束 -->

	<table  style="width:750px;" border="1" cellpadding="0" cellspacing="0" id="wzxx_table" name = "wzxx_table"  style="line-height:30px;">
	  <tbody>
		<tr>
		  <td colspan="7" class="thTitle">一、网站信息</td>
		</tr>
		<tr>
		  <td class="thTitle">网站名称</td>
		  <td colspan="3"><input type="text" id="wzmc" name="wzmc"  /></td>
		  <td class="thTitle">网站网址</td>
		  <td colspan="2"><input type="text" id="wzwz" name="wzwz"  /></td>
		</tr>
		<tr>
		  <td class="thTitle">建站时间</td>
		  <td colspan="3"><input type="text" id="jzsj" name="jzsj"  /></td>
		  <td class="thTitle"><p >近3个月Alexa全球排名</p></td>
		  <td colspan="2"><input type="text" id="alexa" name="alexa"  /></td>
		</tr>
		<tr>
		  <td class="thTitle"><p >ICP证号</p></td>
		  <td colspan="3"><input type="text" id="icp" name="icp"  /></td>
		  <td class="thTitle"><p >所属辖区</p></td>
		  <td colspan="2"><input type="text" id="ssxq" name="ssxq"  /></td>
		</tr>
		<tr>
		  <td class="thTitle"><p >参评类型</p></td>
		  <td colspan="6">
		    <input type="radio" name="cplx" value="政府网站" />政府网站
			<input type="radio" name="cplx" value="电子商务类" />电子商务类
			<input type="radio" name="cplx" value="生活服务类" />生活服务类
			<input type="radio" name="cplx" value="交互论坛类" />交互论坛类
			<input type="radio" name="cplx" value="休闲娱乐类" />休闲娱乐类
			<input type="radio" name="cplx" value="综合类" />综合类
			<input type="radio" name="cplx" value="其它类别" />其它类别</td>
		</tr>
		<tr>
		  <td class="thTitle"><p >网站经营范围</p></td>
		  <td colspan="6">
			<textarea id="wzjyfw" name="wzjyfw" style="width:630px;;height:250px;visibility:hidden;"></textarea>
		  </td>
		</tr>
		<tr>
		  <td class="thTitle"><p >网站简介(100字以内)</p></td>
		  <td colspan="6">
			<textarea id="wzjj" name="wzjj" style="width:630px;;height:250px;visibility:hidden;"></textarea>
		  </td>
		</tr>
		<tr>
		  <td class="thTitle"><p >2011年起所获奖项/荣誉情况</p></td>
		  <td colspan="6">
			<textarea id="hjry" name="hjry" style="width:630px;;height:250px;visibility:hidden;"></textarea>
		  </td>
		</tr>
		<tr>
		  <td class="thTitle"><p >网站安全 </p>
		  <p >基本情况 </p></td>
		  <td colspan="6" align="left" style="padding-left:20px"><p >（注：统计数据均截止到2012年9月底） </p>
			<p >1.网站注册用户数 </p>
			<p >
				<input type="radio" name="zcyh" value="10万以下" />10万以下
				<input type="radio" name="zcyh" value="10－100万" />10－100万
				<input type="radio" name="zcyh" value="100－500万" />100－500万
				<input type="radio" name="zcyh" value="500万以上 " />500万以上 
			</p>
			<p >2.网站注册用户实名认证情况 </p>
			<p >
				<input type="checkbox" value="无认证" name="smrz" >无认证
				<input type="checkbox" value="邮箱认证" name="smrz">邮箱认证
				<input type="checkbox" value="手机号认证" name="smrz">手机号认证
				<input type="checkbox" value="有效证件认证" name="smrz">有效证件认证
				<input type="checkbox" value="其它" name="smrz">其它</p>
			<p >3.网站今年是否受到公安机关行政处罚 </p>
			<p ><input type="radio" name="iscf" value="是" />是
				<input type="radio" name="iscf" value="否" />否</p>
			<p >4.网站是否制定并落实网站安全管理制度 </p>
			<p ><input type="radio" name="isglzd" value="是" />是
				<input type="radio" name="isglzd" value="否" />否</p>
			<p >5.网站是否制定信息安全事件处置流程 </p>
			<p ><input type="radio" name="isaqlc" value="是" />是
				<input type="radio" name="isaqlc" value="否" />否</p></p>
			<p >6.网站配备的合格安全员数量情况（合格是指安全员持证，且证件有效） </p>
			<p >
				<input type="radio" value="无" name="aqysl" >无
				<input type="radio" value="1名" name="aqysl" >1名
				<input type="radio" value="2名" name="aqysl">2名
				<input type="radio" value="3-4名" name="aqysl">3-4名
				<input type="radio" value="4名以上" name="aqysl">4名以上</p>
				<input type="hidden" value="" name="fjpd" id="fjpd">
			<p >7.网站的信息系统是否按照国家信息系统等级保护制度要求准确定级和备案 </p>
		  <p ><input type="radio" value="尚未定级" name="baxx"  checked="checked" >尚未定级
				<input type="radio" value="已进行二级备案" name="baxx">已进行二级备案
				<input type="radio" value="已进行三级备案" name="baxx">已进行三级备案</p></td>
		</tr>
		<tr>
		  <td class="thTitle" rowspan="2"><p >网站安全技术负责人</p></td>
		  <td class="thTitle"><p >姓名</p></td>
		  <td><input type="text" id="aqjsxm" name="aqjsxm"  /></td>
		  <td class="thTitle"><p >职务</p></td>
		  <td><input type="text" id="aqjszw" name="aqjszw"  /></td>
		  <td class="thTitle"><p >传真</p></td>
		  <td><input type="text" id="aqjscz" name="aqjscz"  /></td>
		</tr>
		<tr>
		  <td class="thTitle"><p >手机</p></td>
		  <td><input type="text" id="aqjssj" name="aqjssj"  /></td>
		  <td class="thTitle"><p >邮箱</p></td>
		  <td colspan="3"><input type="text" id="aqjsyx" name="aqjsyx"  /></td>
		</tr>
		</tbody>
	</table>
	<div class="blank9"></div>
	 <table class="hidden"  style="width:750px;" border="1" cellpadding="0" cellspacing="0" id="dwxx" name = "dwxx"  style="line-height:30px;">
          <tbody>
            <tr>
              <td colspan="7"  class="thTitle" style="text-align:left; text-indent:2em;"><p >二、主办单位信息</p></td>
            </tr>
            <tr>
              <td class="thTitle"><p >单位名称</p></td>
              <td colspan="2"><input type="text" id="dwmc" name="dwmc"  /></td>
              <td class="thTitle" colspan="2"><p >公司法人</p></td>
              <td colspan="2"><input type="text" id="gsfr" name="gsfr"  /></td>
            </tr>
            <tr>
              <td class="thTitle"><p >联系地址</p></td>
              <td colspan="2"><input type="text" id="lxdz" name="lxdz"  /></td>
              <td class="thTitle" colspan="2"><p >邮编</p></td>
              <td colspan="2"><input type="text" id="yb" name="yb"  /></td>
            </tr>
            <tr>
              <td class="thTitle"><p >注册资金</p></td>
              <td colspan="2"><input type="text" id="zczj" name="zczj"  /></td>
              <td class="thTitle" colspan="2"><p >其中外资（含港澳台）比例</p></td>
              <td colspan="2"><input type="text" id="wzbl" name="wzbl"  /></td>
            </tr>
            <tr>
              <td class="thTitle"><p >单位性质</p></td>
              <td colspan="7" style="text-align:left;">
			    <input type="radio" value="机关事业单位" name="dwxz" >机关事业单位
                <input type="radio" value="国有" name="dwxz" >国有
                <input type="radio" value="私营" name="dwxz">私营
                <input type="radio" value="外资" name="dwxz">外资
                <input type="radio" value="股份制" name="dwxz">股份制
                <input type="radio" value="社团" name="dwxz">社团
                <input type="radio" value="其它" name="dwxz">其它</p>
              </td>
            </tr>
      </tbody>
        </table>
     <div class="blank9"></div>
	<table class="hidden"  style="width:750px;" border="1" cellpadding="0" cellspacing="0" id="grxx" name = "grxx"  style="line-height:30px;">
	  <tbody>
		<tr>
		  <td colspan="7"  class="thTitle"><p >二、网站开办者信息</p></td>
		</tr>
		<tr>
		  <td class="thTitle"><p >姓名</p></td>
		  <td colspan="2"><input type="text" id="dwmc" name="dwmc2"  /></td>
		  <td class="thTitle"><p >联系地址</p></td>
		  <td colspan="3"><input type="text" id="lxdz" name="lxdz2"  /></td>
		</tr>
		<tr>
		  <td class="thTitle"><p >身份证号</p></td>
		  <td colspan="2"><input type="text" id="gsfr" name="gsfr2"  /></td>
		  <td class="thTitle"><p >电话</p></td>
		  <td colspan="3"><input type="text" id="yb" name="yb2"  /></td>
		</tr>
		</tbody>
	</table>
	<div class="blank9"></div>
	<table  style="width:750px;" border="1" cellpadding="0" cellspacing="0" style="line-height:30px;">
	  <tbody>
		<tr>
		  <td class="thTitle" rowspan="2" width="150px"><p >联系人信息</p></td>
		  <td class="thTitle" width="70px"><p >姓名</p></td>
		  <td width="120px"><input type="text" id="lxrxm" name="lxrxm"  /></td>
		  <td class="thTitle" width="70px"><p >职务</p></td>
		  <td width="160px"><input type="text" id="zw" name="zw"  /></td>
		  <td  class="thTitle" width="60px"><p >传真</p></td>
		  <td><input type="text" id="cz" name="cz"  /></td>
		</tr>
		<tr>
		  <td class="thTitle"><p >手机</p></td>
		  <td><input type="text" id="phone" name="phone"  /></td>
		  <td class="thTitle"><p >邮箱</p></td>
		  <td colspan="3"><input type="text" id="email" name="email"  /></td>
		</tr>
	  </tbody>
	</table>
</div>
<span class="blank12"></span>
<span class="blank3"></span>
<table class="table_option" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td align="center" valign="middle" style="text-indent:100px;">
			<input  name="btn1" type="button" onclick="updateWzxxData()"  value="保存" />	
			<input id="userAddCancel" name="btn1" type="button" onclick="top.tab_colseOnclick(top.curTabIndex);" value="取消" />	
		</td>
	</tr>
</table>
<span class="blank3"></span>
</div>
</body>
</html>
