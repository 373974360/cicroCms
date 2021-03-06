var PicBean = new Bean("com.cicro.wcm.bean.cms.info.PicBean",true);
var PicItemBean = new Bean("com.cicro.wcm.bean.cms.info.PicItemBean",true);

function addPicInfo()
{
	var bean = BeanUtil.getCopy(PicBean);	
	$("#info_article_table").autoBind(bean);
	if(!standard_checkInputInfo("info_article_table"))
	{
		return;
	}
	if($("#add_pic_table img").length == 0)
	{
		top.msgAlert("图片不能为空，请选择图片");
		return;
	}
	var st = $(":radio[name='info_status'][checked]").val();
	bean.info_status = (st == null ? "2" : st);

	infoNextId = 0;
	if(infoIdGoble == 0){
		infoNextId = InfoBaseRPC.getInfoId();
	}else{
		infoNextId = infoIdGoble;
	}

	no1 = infoNextId;
	bean.id = infoNextId;
	bean.info_id = infoNextId;
	bean.item_list = getItemList(bean.info_id);

	publicSaveInfoEvent(bean,"pic","insert");
}

function updatePicInfo()
{
	var bean = BeanUtil.getCopy(PicBean);	
	$("#info_article_table").autoBind(bean);
	if(!standard_checkInputInfo("info_article_table"))
	{
		return;
	}
	if($("#add_pic_table img").length == 0)
	{
		top.msgAlert("图片不能为空，请选择图片");
		return;
	}
	bean.info_status = defaultBean.info_status;
	bean.info_id = infoid;
	bean.item_list = getItemList(bean.info_id);
	
	publicSaveInfoEvent(bean,"pic","update");	
}

function getItemList(info_id)
{
	var item_list = new List();
	$("#add_pic_table tr").each(function(i){
		var ib = BeanUtil.getCopy(PicItemBean);
		ib.info_id = info_id;
		ib.pic_title = $(this).find("input").val();
		ib.pic_path = $(this).find("img").attr("src");		
		ib.pic_note = $(this).find("textarea").val();
		ib.pic_sort = i+1;
		if(i == 0)
		{
			//把详细内容写入到第一个对象中
			ib.pic_content = KE.html("pic_content");
		}
		item_list.add(ib);
	});
	return item_list;
}