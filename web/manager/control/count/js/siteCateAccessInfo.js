var CategoryRPC = jsonrpc.CategoryRPC;
var CategoryBean = new Bean("com.cicro.wcm.bean.cms.category.CategoryBean",true);
var AccessCountRPC = jsonrpc.AccessCountRPC;

var beanList = null;
var con_m = new Map();
var chartJsonData = ""; // bar图时的json字符串

// 显示统计结果列表
function showList(){	
	//得到父页面中设置的参数 --- start
	var start_day = top.getCurrentFrameObj().$("#start_day").val();
	var end_day = top.getCurrentFrameObj().$("#end_day").val();
	
	var mp = new Map();	
	mp.put("start_day",start_day);
	mp.put("end_day",end_day + " 23:59:59");
	mp.put("site_id", site_id);
	//得到父页面中设置的参数 --- end
	beanList = AccessCountRPC.getSiteCountListByCate(mp);
	beanList = List.toJSList(beanList);
	createTable();
}
// 生成内容表格
function createTable(){
	$("#treeTableCount").empty();
	var treeHtmls = "<thead>" +
    "<tr>" +
    "   <th width='30%'>栏目名称</th> "+
    "   <th width='10%'>访问量</th>   "+
    "</tr>" +
    "</thead>" +
    "<tbody>" ;
    if(beanList.size() != 0){
	for(var i=0;i<beanList.size();i++)
	{
		treeHtmls += setTreeNode(beanList.get(i),"");
	}
	treeHtmls += "</tbody>"+
	"<tfoot>" +
  	"<td colspan=\"2\"></td>"+ " </tfoot>";
 	}else{
		treeHtmls += "<tr><td colspan=\"2\">暂无数据...</td></tr>" + "</tbody>"+"<tfoot>" +"<td colspan=\"2\"></td>"+" </tfoot>";
	}
	$("#treeTableCount").append(treeHtmls);
	iniTreeTable("treeTableCount");
}
//组织树列表选项字符串
function setTreeNode(bean,parent_id)
{			
	var treeHtml = "";
	if(bean.is_leaf)
	{		
		var type_calss= "";
		if(parent_id !=""){  
		      type_calss = "class='child-of-node-"+parent_id+"'"
		}
		//alert("parent_id=="+parent_id+":::is_leaf==" + bean.cat_id);
		treeHtml+="<tr id='node-"+bean.cat_id+"' "+type_calss+" > <td><span class='file'>"+bean.cat_name+"</span></td>";
        treeHtml += setHandlList(bean);
		treeHtml+="</tr>";
	}else{
		var type_calss = "";
		if(parent_id !=""){  
		      type_calss = "class='child-of-node-"+parent_id+"'"
		}
		treeHtml+="<tr id='node-"+bean.cat_id+"' "+type_calss+" > <td><span class='folder'>"+bean.cat_name+"</span></td>";
		treeHtml += setHandlList(bean);
		treeHtml+="</tr>";
		var child_list = bean.child_list;
		  child_list = List.toJSList(child_list);
		  if(child_list.size() > 0)
		  { 
			 for(var i=0;i<child_list.size();i++)
			 {						
				treeHtml += setTreeNode(child_list.get(i), bean.cat_id+"");
			 }
		  }
	}
	return treeHtml;
}
// 组织统计的数据
function setHandlList(bean)
{
	var str = "";
	if(bean)
	{
		str+="<td>"+bean.inputCount +"</td>";
		str+="<td>"+bean.hostInfoCount +"</td>";
	}
	return str;
}