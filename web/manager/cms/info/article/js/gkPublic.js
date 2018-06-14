var DataDictRPC = jsonrpc.DataDictRPC;
var CategoryRPC = jsonrpc.CategoryRPC;
var GKNodeRPC = jsonrpc.GKNodeRPC;
var GKInfoRPC = jsonrpc.GKInfoRPC;
var IndexRPC = jsonrpc.IndexRPC;
var GKResFileBean = new Bean("com.cicro.wcm.bean.cms.info.GKResFileBean",true);
var cat_map = new Map();//主题分类列表数据
var imgDomain = "";
// add by zhangqiang 20130907
var KnowledgeRPC = jsonrpc.KnowledgeRPC; 
var InfoRelatedKcatRPC = jsonrpc.InfoRelatedKcatRPC
var InfoRelatedKcatBean = new Bean("com.cicro.wcm.services.extendfunction.infoRelatedKcat.InfoRelatedKcatBean");
var GKNodeBean;//节点对象

//公用初始加载方法
function reloadPublicGKInfo()
{	
	GKNodeBean = GKNodeRPC.getGKNodeBeanByNodeID(site_id);
	rela_site_id = GKNodeBean.rela_site_id;
	imgDomain = jsonrpc.MateInfoRPC.getImgDomain(rela_site_id);
	setSource();
	setAuthor();
	setPreTitle();
	showSelectDiv2("source","select",300);
	showSelectDiv2("author","select2",300);
	showSelectDiv2("editor","select3",300);
	showSelectDiv2("pre_title","select4",120);

		//显示知识库标签树的    add by zhangqiang 20130907
	showSelectKDiv("kcat_name","kcat_tree_div",300,"leftMenu_k");
	initKCatTree();	


	iniSQbox();
	getFirstChileCateID();
	if(mFlag == false)
	{//添加时要加载的方法
		$("#cat_id").val(cid);
		$("#site_id").val(site_id);		
		$("#input_user").val(LoginUserBean.user_id);
		//添加时设置权限
		setInfoStatusButton();
		
		$("#gk_year").val(getCurrentDate().substring(0,7));//生成年份
		$("#generate_dtime").val(getCurrentDate());//生成年份

		$("#gk_duty_dept").val(GKNodeBean.node_fullname);
		$("#gk_input_dept").val(GKNodeBean.node_fullname);
		publicUploadFileButtomLoad("uploadify_file",true,true,"",0,5,rela_site_id,"saveFileUrl");
		iniOpt();

		$("#source").val(SiteRPC.getSiteBeanBySiteID(jsonrpc.SiteRPC.getSiteIDByAppID(app_id)).site_name)
		$("#author").val(LoginUserBean.user_realname);
		$("#editor").val(LoginUserBean.user_realname);
	}
	
	initButtomStyle();
	init_input();
	
	loadDataDict();//加载数据字典项
	loadCateClass();//加载应用目录

	setUserOperate();//设置用户权限
	showCatId(cid);
	
	$("#title").blur(function(){
		var tags = InfoBaseRPC.getTagsForTitle($("#title").val());
		if(tags != "" && tags != null)
		{
			$("#topic_key").val(tags);
		}
	});
}

//修改信息时页面加载方法
function publicReloadUpdateGKInfos()
{	
	if(defaultBean.top_title != "")
	{
		$("#sttop").attr("checked",true);
		showTopTitle();
	}

	if(defaultBean.subtitle != "")
	{
		$("#stt").attr("checked",true);
		showSubTitle();
	}

	infoIdGoble = infoid;
	changeGKReason(defaultBean.gk_type);
	//修改时显示信息状态
	showInfoStatusText();
	showRelatedInfos(infoid);
	showStringLength('title','wordnum');

	$("#modify_user").val(LoginUserBean.user_id);			
	$("#app_id").val(app_id);
	showFocusWares(infoid);
	getGKResFileList(infoid);
	showCatId(defaultBean.cat_id);

	 // add by zhangqinag 2013-09-09 修改页面
	 var kcat_names = InfoRelatedKcatRPC.getRelatedKcatNames(infoid);
	 if(kcat_names != "")
	 {
		 $("#kcat_name").val(kcat_names);
	 }
}

//加载数据字典项
function loadDataDict()
{
	getDataList("gk_type","gk_gkfs");
	getDataList("gk_way","gk_gkxs");
	getDataList("gk_time_limit","gk_gkshixian");
	getDataList("gk_range","gk_gkfanwei");
	getDataList("gk_validity","gk_validity");
	getDataList("gk_format","gk_format");
	getDataList("carrier_type","gk_carrier");
	getDataList("language","gk_yuzhong");
	getDataList("file_head","file_head");
}

//根据数据字典名称，加载到列表中
function getDataList(selectName,dic_name)
{
	var default_val = "";
	var dic_list = DataDictRPC.getDataDictList(dic_name);
	dic_list = List.toJSList(dic_list);
	if(dic_list != null && dic_list.size() > 0)
	{
		for(var i=0;i<dic_list.size();i++)
		{
			$("#"+selectName).addOptionsSingl(dic_list.get(i).dict_id,dic_list.get(i).dict_name);
			if(dic_list.get(i).is_defult == 1)
				default_val = dic_list.get(i).dict_id;
		}
		$("#"+selectName+" option[value='"+default_val+"']").attr("selected",true);
	}	
}

//加载应用目录
function loadCateClass()
{
	getCateClassList("topic_id_td","ztfl");
	getCateClassList("theme_id_td","tcfl");
	getCateClassList("serve_id_td","fwdxfl");
}

//根据应用目英文名称取得栏目列表加载到列表中
function getCateClassList(td_name,catecass_ename)
{
	var smcat_bean = CategoryRPC.getSMCategoryList(catecass_ename);		
	if(smcat_bean != null)
	{
		var cat_list = smcat_bean.sm_list;//取根节点的子节点		
		setCategoryListToSelect($("#"+td_name+" select"),cat_list);
	}
	$("#"+td_name+" select option").first().change();
}

//联动select切换事件　 将得到的子栏目列表写入到select框中
/*
function setChileListToSelect(index_num,select_cat_id,td_name)
{	
	try{
		$("#"+td_name+" select").slice(index_num+1).remove();
	}catch(e){}

	var child_list = cat_map.get(select_cat_id).sm_list;
	child_list = List.toJSList(child_list);
	
	if(child_list != null && child_list.size() > 0)
	{
		$("#"+td_name).append('<select id="first_top" class="input_select" style="width:150px;" onchange="setChileListToSelect('+index_num+1+',this.value,\''+td_name+'\')"><option value="0"></option></select>');		
		setCategoryListToSelect($("#"+td_name+" select").eq(index_num+1),child_list);
		$("#"+td_name+" select").eq(index_num+1).find("option").first().change();
	}else
	{		
		$("#"+td_name+" input[type='hidden']").val(select_cat_id);
		$("#"+td_name+" input[type='text']").val(CategoryRPC.getCategoryCName(select_cat_id,""));
	}	
}
*/

function setChileListToSelect(index_num,select_cat_id,td_name)
{	
	try{
		$("#"+td_name+" select").slice(index_num+1).remove();
	}catch(e){}

	var child_list = cat_map.get(select_cat_id).sm_list;
	child_list = List.toJSList(child_list);
	
	if(child_list != null && child_list.size() > 0)
	{
		$("#"+td_name).append('<select id="first_top" class="input_select" style="width:150px;" onchange="setChileListToSelect('+index_num+1+',this.value,\''+td_name+'\')"><option value="0"></option></select>');		
		setCategoryListToSelect($("#"+td_name+" select").eq(index_num+1),child_list);
		$("#"+td_name+" select").eq(index_num+1).find("option").first().change();
	}else
	{		
		$("#"+td_name+" input[type='hidden']").val(select_cat_id);
		if(td_name='topic_id_td'){
            $("#"+td_name+" input[type='text']").val($("#"+td_name+" select").eq(0).find("option:selected").text()+'\\'+CategoryRPC.getCategoryCName(select_cat_id,""));
        
         }else{
             $("#"+td_name+" input[type='text']").val(CategoryRPC.getCategoryCName(select_cat_id,""));
         }
	}	
}

//将得到的栏目列表写入到select框中
function setCategoryListToSelect(obj,cat_list)
{
	cat_list = List.toJSList(cat_list);
	for(var i=0;i<cat_list.size();i++)
	{
		$(obj).addOptionsSingl(cat_list.get(i).cat_id,cat_list.get(i).cat_cname);	
		cat_map.put(cat_list.get(i).cat_id,cat_list.get(i));
	}
}

//显示手动编码窗口
function showHandlNum(obj)
{
	if($(obj).attr("checked") == true)
	{
		$("#shoudong_num").show();
	}
	else
	{
		$("#shoudong_num").hide();
	}
}

//重新生成索引号
function getNewGKIndex()
{
	var year = $("#gk_year").val();
	var n_num = $("#gk_num").val();
	var index_map = IndexRPC.getIndex(site_id,cid,year,n_num);
	index_map = Map.toJSMap(index_map);
	if(index_map != null)
	{
		if(GKInfoRPC.getInfoIdForGKIndex(index_map.get("gk_index")) == "")
		{
			//$("#gk_year").val(index_map.get("gk_year"));
			$("#gk_num").val(index_map.get("gk_num"));
			$("#gk_index").val(index_map.get("gk_index"));
			$("#gk_year").removeAttr("onfocus");
			$("#gk_num").attr("readOnly","readOnly");
		}else
		{
			top.msgWargin("索引码"+index_map.get("gk_index")+"已重复，请重新输入顺序号,或清空索引号自动获取");
		}
	}else
	{
		top.msgWargin("索引码生成失败，请重新尝试生成");
	}
}

//添加时判断流水号是否为空
function setGKNumHandlForInsert(bean)
{
	bean.gk_num = 100;
	if($("#gk_index_handl").attr("checked") == true && $("#gk_index").val() != "")
	{//手动生成的,且索引号不为空	
		if(GKInfoRPC.getInfoIdForGKIndex($("#gk_index").val()) == "")
		{//判断是否重复
			bean.gk_index = $("#gk_index").val();
			bean.gk_year = $("#gk_year").val();
			bean.gk_num = $("#gk_num").val();
			return true;
		}else
		{
			var im = getGKIndexAuto();
			if(im != null)
			{
				bean.gk_index = im.get("gk_index");
				bean.gk_year = im.get("gk_year");
				bean.gk_num = im.get("gk_num");
				return true;
			}
		}		
	}else
	{	
		var im = getGKIndexAuto();
		if(im != null)
		{
			bean.gk_index = im.get("gk_index");
			bean.gk_year = im.get("gk_year");
			bean.gk_num = im.get("gk_num");
			return true;
		}
	}
	return false;
}

//自动获取索引码
function getGKIndexAuto()
{
	var index_map = IndexRPC.getIndex(site_id,cid,"","");
	index_map = Map.toJSMap(index_map);
	if(index_map != null)
	{
		//判断是否重复		
		if(GKInfoRPC.getInfoIdForGKIndex(index_map.get("gk_index")) == "")
		{
			return index_map;
		}else
		{
			return index_map;
			//getGKIndex();
			//top.msgWargin("该目录没有设置类目编号，请先设置类目编号");
		}
	}
	else
	{
		getGKIndex();
	}
}

//将选中的分类法写入对象中
function setCateClassToBean(bean)
{
	/*
	bean.topic_id = $("#topic_id_td select").last().find("option[selected=true]").val();
	bean.topic_name = $("#topic_id_td select").last().find("option[selected=true]").text();
	bean.theme_id = $("#theme_id_td select").last().find("option[selected=true]").val();
	bean.theme_name = $("#theme_id_td select").last().find("option[selected=true]").text();
	bean.serve_id = $("#serve_id_td select").last().find("option[selected=true]").val();
	bean.serve_name = $("#serve_id_td select").last().find("option[selected=true]").text();
	*/

	//写入附件信息
	var file_list = new List();
	$("#add_file_table tr").each(function(i){
		var gFile = BeanUtil.getCopy(GKResFileBean);
		gFile.info_id = bean.info_id;
		gFile.res_name = $(this).find("#res_name").val();
		gFile.res_url = $(this).find("#res_url").val();
		gFile.sort_id = i+1;
		file_list.add(gFile);
	});
	bean.file_list = file_list;	
}

//切换公开方式解发事件
function changeGKReason(val)
{
	if(val == 1)
	{
		$("#gk_no_reason_tab").show();
	}else
	{
		$("#gk_no_reason_tab").hide();
	}
}

//添加附件
function addFileStep(url,c_name)
{	
	var tmpTR = "";
		tmpTR  = "<tr>"
		tmpTR +="	<td style=\"width:120px;\"><input type='text' id='res_name' name='res_name' class='width200' maxlength='100' value='"+c_name+"'/></td>";
		tmpTR +="	<td style=\"width:120px;\"><input type='text' id='res_url' name='res_url' class='width200' maxlength='200' value='"+url+"'/></td>";
		tmpTR +="	<td>";
		tmpTR +="		<ul class=\"optUL\">";
		tmpTR +="			<li class=\"opt_up ico_up\" title=\"上移\"></li>";
		tmpTR +="			<li class=\"opt_down ico_down\" title=\"下移\"></li>";
		tmpTR +="			<li class=\"opt_delete ico_delete\" title=\"删除\"></li>";
		tmpTR +="		</ul>";
		tmpTR +="	</td>";
		tmpTR +="</tr>";

		$("#add_file_table").append(tmpTR);		
}

//将返回的附件地址写入到控件中
function saveFileUrl(url,c_names)
{
	var tempA = url.split(",");
	var temp_names = c_names.split(",");
	for(var i=0;i<tempA.length;i++)
	{
		addFileStep(tempA[i],temp_names[i]);
	}
	init_input();
	cur_sort_tablename = "add_file_table";
	resetNum();
}

//打开附件选择窗口
function selectFileHandle(handlName,input_type)
{
	openSelectMaterialPage(handlName,rela_site_id,input_type);	
}

//
function getGKResFileList(in_id)
{
	var file_list = GKInfoRPC.getGKResFileList(in_id);
	file_list = List.toJSList(file_list);
	if(file_list != null && file_list.size() > 0)
	{
		for(var i=0;i<file_list.size();i++)
		{
			addFileStep(file_list.get(i).res_url,file_list.get(i).res_name);
		}
		init_input();
		cur_sort_tablename = "add_file_table";
		resetNum();
	}
}


//  以下add by zhangqian 20130907
//知识库标签 js begin
var kcat_jdata = "";

function showSelectKDiv(input_name,div_name,height,tree_div_name)
	{
		$("input[id='"+input_name+"']").addClass("select_input_default");
		$("input[id='"+input_name+"']").hover(function(){			
			$(this).removeClass("select_input_default");
			$(this).addClass("select_input_selected");			
		},function(){
			$(this).removeClass("select_input_selected");
			$(this).addClass("select_input_default");			
		});
		$("input[id='"+input_name+"']").click(function(event){
			$("div.select_div").hide();//先关闭其它的div
			var thePos = $(this).position();
			$("#"+div_name).show();
			$("#"+div_name).css("background","#FFFFFF");
			$("#"+div_name).css("left",thePos.left);
			$("#"+div_name).css("top",thePos.top+19);
			$("#"+div_name).css("width",$(this).width()+3);
			$("#"+div_name).css("height",height+"px");
			if(tree_div_name != null)
				$("#"+tree_div_name).css("height",(height-12)+"px");
			else
				$("#leftMenu_k").css("height",(height-12)+"px");
			event.stopPropagation();
		});
		$("html").click(function(event){
			if($($(event.target)[0]).attr("class").indexOf("tree-") == -1)
			{
				$("div.select_div").hide();			
			}else
			{
				setAllIds_K();
			}
		});

}
function setAllIds_K()
{
	var kcat_name = showSelectNames_K("s");
	var kcat_id = showSelectIds_K("s");
	$("#kcat_name").val(kcat_name);
	$("#kcat_id").val(kcat_id);
}

function showSelectNames_K(t)
{
	var s = "";
	var names = "";
	$("#leftMenuTree_k li").each(function(i){
	 if(i>0)
	 {
		var o = $(this).find(".tree-checkbox1").parent();
		var id = o.attr("node-id");
		if(id != null && id != undefined && id != "undefined"){
		  if(s.indexOf(id) == -1)
	      {
			 s += "," + id;
			 names += "," + $(o).find(".tree-title").html();
		  }
		}
	 }
	});
	if(names.length > 1)
	{
	   return names.substring(1);
	}else{
	   return names;
	}
}
function showSelectIds_K(t)
{
	var s = "";
	var names = "";
	$("#leftMenuTree_k li").each(function(i){
	 if(i>0)
     {
		var o = $(this).find(".tree-checkbox1").parent();
		var id = o.attr("node-id");
		if(id != null && id != undefined && id != "undefined"){
		  if(s.indexOf(id) == -1){s += "," + id;}
		}
	 }
	});
	if(s.length > 1)
	{
	   return s.substring(1);
	}
}
	
function initKCatTree()
{  
	kcat_jdata = eval(KnowledgeRPC.getJSONTreeStr("","cms"));
	setLeftKinfotreeJsonData(kcat_jdata);	
	initKTree();
}
var isChangeTreeStatus = false;
function initKTree(){
	$("#leftMenuTree_k .tree-file").after('<SPAN class="tree-checkbox tree-checkbox0"></SPAN>');
	$("#leftMenuTree_k .tree-checkbox").click(function(){		
		if($(this).attr("class").indexOf("tree-checkbox0") > -1)
		{
			$(this).removeClass("tree-checkbox0");
			$(this).addClass("tree-checkbox1");
		}else
		{
			$(this).removeClass("tree-checkbox1");
			$(this).addClass("tree-checkbox0");			
		}
	});	
}
//左边栏目树对像赋值json格式数据
function setLeftKinfotreeJsonData(jsonData){
	isChangeTreeStatus = true;
	if(jsonData != undefined && jsonData.length>0)
	{
		$('#leftMenuTree_k').empty();
		$('#leftMenuTree_k').tree('append',{
			parent: (null),
			data:jsonData
		});	
	}
}


//知识库标签入库 -- end
