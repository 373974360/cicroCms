<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>采集规则管理</title>
<meta name="generator" content="cicro-Builder" />
<meta name="ware" content="cicro" />
<jsp:include page="../include/include_tools.jsp" />
<script type="text/javascript" src=/manager/js/tools.js></script>
<script type="text/javascript" src="js/rule_list.js"></script>
<script type="text/javascript">
$(document).ready(function(){	
	initButtomStyle();
	init_FromTabsStyle();
	if($.browser.msie&&$.browser.version=="6.0"&&$("html")[0].scrollHeight>$("html").height()) 
		$("html").css("overflowY","scroll"); 
	
	initTable();
	loadRuleTable();	
	initCollState();
});

function initCollState(){
	var RuleIdlist = CollectionDataRPC.getAllCollIsRuning();
	if(RuleIdlist!=null){
		RuleIdlist = List.toJSList(RuleIdlist);
		for(var i=0;i<RuleIdlist.size();i++){
			$("#collstate_"+RuleIdlist.get(i)).html("<font color='red'>开始</font>");
		}
	}
}

</script>
</head>
<body>
	<div>
		<span class="blank3"></span>
		<div id="rule_table"></div>
		<div id="rule_turn"></div>
		<span class="blank12"></span>
		<div class="line2h"></div>
		<span class="blank3"></span>
		<table class="table_option" border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td align="left" valign="middle">
					<input id="userAddCancel" name="btn1" type="button" onclick="openaddRuleTabPage()" value="添加" />
					<input id="userAddCancel" name="btn1" type="button" onclick="deleteRecord(table,'id','deleteRuleByid()');" value="删除" />
					<input id="userAddCancel" name="btn1" type="button" onclick="updateRecord(table,'id','updateRuleById()')" value="修改" />
				</td>
			</tr>
		</table>
	</div>
</body>
</html>