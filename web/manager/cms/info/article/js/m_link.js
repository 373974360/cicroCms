var InfoBean = new Bean("com.cicro.wcm.bean.cms.info.InfoBean",true);



function addInfoData()
{	
	var bean = BeanUtil.getCopy(InfoBean);	
	$("#info_article_table").autoBind(bean);
	if(!standard_checkInputInfo("info_article_table"))
	{
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

	publicSaveInfoEvent(bean,"link","insert");		
}
//修改
function updateInfoData()
{	
	var bean = BeanUtil.getCopy(InfoBean);	
	$("#info_article_table").autoBind(bean);

	if(!standard_checkInputInfo("info_article_table"))
	{
		return;
	}	

	bean.info_status = defaultBean.info_status;
	
	publicSaveInfoEvent(bean,"link","update");	
	
}

function closePage(){
	top.CloseModalWindow();
}