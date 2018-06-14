
function initTable()
{
	var previewTable = top.jsonrpc.SubscribeRPC.getPreviewSendContentBean(record_id);
	$("#preview_table").append(previewTable);
}

function deletePreviewInfo(id)
{
	top.jsonrpc.SubscribeRPC.deletePreviewInfobyId(showPreviewResult,id);
}

function showPreviewResult(resultPreview,e)
{
	if(e != null){return;}
	if(resultPreview){
		top.msgAlert("删除成功");
		$("#preview_table").empty();
		initTable();
	}else{
		top.msgAlert("删除失败");
	}		
}

//追加发送信息
function insertaddSendInfo()
{
	var add_type="conAdd";  //追加
	top.CloseModalWindow();
	top.OpenModalWindow("信息获取","/manager/extendfunction/subscribe/choicesendContent.jsp?site_id="+site_id+"&app_id="+app_id+"&add_type="+add_type+"&recordid="+record_id+"&sendTitle="+sendTitle,800,530);
}

//取消
function closePreview()
{
	top.CloseModalWindow();
}










