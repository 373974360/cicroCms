<%@ page contentType="text/html; charset=utf-8"%>
<%
String app_id = request.getParameter("app_id");
String model = request.getParameter("model");
if(app_id == null || app_id.equals("null")){
	app_id = "";
}
if(model == null || !model.matches("[0-9]*")){
	model = "0";
}
%>
<script type="text/javascript" src="js/gkPublic.js"></script>
<input id="model_id" type="hidden" class="width200" value="<%=model%>" />
<input id="app_id" type="hidden" class="width200" value="<%=app_id%>" />
<input id="info_id" type="hidden" class="width200" value="0" />
<input id="cat_id" type="hidden" class="width200" value="0" />
<input id="from_id" type="hidden" class="width200" value="0" />
<input id="wf_id" type="hidden" class="width200" value="0" />
<input id="step_id" type="hidden" class="width200" value="0" />
<input id="final_status" type="hidden" class="width200" value="0" />
<input id="hits" type="hidden" class="width200" value="0" />
<input id="day_hits" type="hidden" class="width200" value="0" />
<input id="week_hits" type="hidden" class="width200" value="0" />
<input id="month_hits" type="hidden" class="width200" value="0" />
<input id="lasthit_dtime" type="hidden" class="width200" value="" />
<input id="comment_num" type="hidden" class="width200" value="0" />
<input id="input_user" type="hidden" class="width200" value="0" />
<input id="input_dtime" type="hidden" class="width200" value="" />
<input id="modify_user" type="hidden" class="width200" value="0" />
<input id="modify_dtime" type="hidden" class="width200" value="" />
<input id="opt_dtime" type="hidden" class="width200" value="" />
<input id="is_auto_up" type="hidden" class="width200" value="0" />
<input id="is_auto_down" type="hidden" class="width200" value="0" />
<input id="is_host" type="hidden" class="width200" value="0" />
<input id="title_hashkey" type="hidden" class="width200" value="0" />
<input id="site_id" type="hidden" class="width200" value="0" />
<input id="i_ver" type="hidden" class="width200" value="0" />
<input id="page_count" type="hidden" class="width200" value="0" />
<input id="prepage_wcount" type="hidden" class="width200" value="0" />
<input id="word_count" type="hidden" class="width200" value="0" />
<table id="" class="table_form table_option" border="0" cellpadding="0" cellspacing="0">
	<tbody>
		<tr>
			<th><span class="f_red">*</span>所属栏目：</th>
			<td style=" width:200px;">
				<span class="f_red" id="showCatId">分类ID</span>
			</td>
			<!--   add by zhangqiang 20130912 -->
			<th style="width:80px;"  id="t5">知识库标签：</th>
			<td style="text-align:center;width:200px;" align="center">
			     <input type="text"   id="kcat_name" class="width200" readonly="readonly"  value=""/>
			     <input type="hidden" id="kcat_id" class="width200" readonly="readonly"    value=""/>
			 	 <div id="kcat_tree_div" class="select_div tip hidden border_color" style="width:200px;height:300px;overflow:hidden;border:1px #7f9db9 solid;" >
					  <div id="leftMenuBox_k">
						   <div id="leftMenu_k" class="contentBox6 textLeft" style="overflow:auto">
								<ul id="leftMenuTree_k" class="easyui-tree" animate="true" style="width:200px;height:300px;">
								</ul>
						   </div>
					  </div>
				</div>
			 </td>
			<td></td>
		</tr>
	</tbody>
</table>
<table id="" class="table_form" border="0" cellpadding="0" cellspacing="0">
	<tbody>
		<tr>
			<th><span class="f_red">*</span>信息标题：</th>
			<td>
				<input type="text" id="pre_title" value="" style="width:60px; height:18px; overflow:hidden;" />
				<div id="select4" class="select_div tip hidden border_color selectDiv" style="width:134px; height:30px;overflow-x:hidden; overflow:auto; " >
					<div id="leftMenuBox">
						<ul id="selectList_pre" class="listLi"  style="width:134px; height:30px; text-align: left;">
						</ul>
					</div>
				</div>
				<input id="title" name="title" type="text" class="width350" value="" onkeypress="showStringLength('title','wordnum')" onkeyup="showStringLength('title','wordnum')"  onblur="checkInputValue('title',false,640,'信息标题','')"/>
				<span id="wordnum">0</span>字
				<input id="sttop" name="dd" type="checkbox"  onclick="showTopTitle()"/><label for="sttop">上标题</label>
				<input id="stt" name="dd" type="checkbox"  onclick="showSubTitle()"/><label for="stt">副标题</label>	
			</td>
		</tr>
		<tr id="topTitleTr" style="display:none;">
			<th>上标题：</th>
			<td>
				<input id="top_title" name="top_title" type="text" class="width350" value="" onkeypress="showStringLength('top_title','wordnum3')" onkeyup="showStringLength('top_title','wordnum3')" onblur="checkInputValue('top_title',true,640,'上标题','')"/>
				<span id="wordnum3">0</span>字
			</td>
		</tr>
		<tr id="subTitleTr" style="display:none;">
			<th>副标题：</th>
			<td>
				<input id="subtitle" name="subtitle" type="text" class="width350" value="" onkeypress="showStringLength('subtitle','wordnum2')" onkeyup="showStringLength('subtitle','wordnum2')" onblur="checkInputValue('subtitle',true,640,'副标题','')"/>
				<span id="wordnum2">0</span>字
			</td>
		</tr>
   </tbody>
</table>
<table id="" class="table_form" border="0" cellpadding="0" cellspacing="0">
	<tbody>
		<tr>
			<th>来源：</th>
			<td width="150">
				<div id="a11">
					<input type="text" id="source" value="" style="width:145px; height:18px; overflow:hidden;" />
					<div id="select" class="select_div tip hidden border_color selectDiv" style="width:149px; height:30px; overflow:auto; " >
						<div id="leftMenuBox">
							<ul id="selectList" class="listLi"  style="width:149px; height:30px; text-align: left;">
							</ul>
						</div>
					</div>
				</div>
			</td>
			<th style="width:40px;">作者：</th>
			<td width="142">
				<div id="a12">
					<input type="text" id="author" value="" style="width:141px; height:18px; overflow:hidden;" />
					<div id="select2" class="select_div tip hidden border_color selectDiv" style="width:134px; height:30px; overflow:auto; " >
						<div id="leftMenuBox">
							<ul id="selectList2" class="listLi"  style="width:134px; height:30px; text-align: left;">
							</ul>
						</div>
					</div>
				</div>
			</td>			
			<th style="width:40px;">编辑：</th>
			<td width="178">
				<div id="a12">
					<input type="text" id="editor" value="" style="width:173px; height:18px; overflow:hidden;" />
					<div id="select3" class="select_div tip hidden border_color selectDiv" style="width:134px; height:30px; overflow:auto; " >
						<div id="leftMenuBox">
							<ul id="selectList3" class="listLi"  style="width:134px; height:30px; text-align: left;">
							</ul>
						</div>
					</div>
				</div>
			</td>		
			<td></td>
		</tr>
	</tbody>
</table>
<table id="" class="table_form" border="0" cellpadding="0" cellspacing="0">
	<tbody>
        <tr>
			<th>公开方式：</th>
			<td width="135px">
				<select id="gk_type" class="gk_type" style="width:150px;" onchange="changeGKReason(this.value)">
					
				</select>
			</td>		
			<th style="width:85px;">显示时间：</th>
			<td width="110px">
				<input id="released_dtime" name="released_dtime" type="text"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true,readOnly:true,maxDate:'%y-%M-%d'})" readonly="readonly" />
			</td>		
			<th style="width:85px;">签发日期：</th>
			<td>
				<input id="generate_dtime" name="generate_dtime" type="text" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true,readOnly:true})" readonly="readonly" />
			</td>
		</tr>
	</tbody>
</table>

<table id="gk_no_reason_tab" class="table_form hidden" border="0" cellpadding="0" cellspacing="0">
	<tbody>
		<tr >
			<th style="vertical-align:top;">不公开理由：</th>
			<td>
				<input id="gk_no_reason" name="gk_no_reason" type="text" class="width350" maxlength="80" value="" onblur="checkInputValue('gk_no_reason',true,900,'不公开理由','')"/>
			</td>
		</tr>
	</tbody>
</table>

<table id="" class="table_form" border="0" cellpadding="0" cellspacing="0">
	<tbody>
        <tr>
			<th>文号：</th>
			<td width="135px">
				<input id="doc_no" name="doc_no" type="text" style="width:140px;" maxlength="80" value="" onblur="checkInputValue('doc_no',true,160,'文号','')"/>
			</td>		
			<th style="width:85px;">签发人：</th>
			<td width="110px">
				<input id="gk_signer" name="gk_signer" type="text" class="width100" maxlength="80" value="" onblur="checkInputValue('gk_signer',true,80,'签发人','')"/>
			</td>		
			<th style="width:85px;">文件题头：</th>
			<td>
				<select id="file_head" name="file_head" style="width:133px;">
				   <option value=""></option>
				</select>
			</td>
		</tr>
	</tbody>
</table>