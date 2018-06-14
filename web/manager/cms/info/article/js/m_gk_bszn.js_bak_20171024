var GKFbsznBean = new Bean("com.cicro.wcm.bean.cms.info.GKFbsznBean",true);

function getV(contetnid){
	return KE.html(contetnid);
}

function setV(contetnid,v){
	KE.html(contetnid, v);
}

//添加Info
var old_remote_img = "";//需要替换的
var replace_img_content = "";//替换图片后的内容变量
var is_upload_remote_img = false;//是否上传成功标识
var img_total = 0;//需要上传的总数
var already_remote_count = 0;//已上传的总数
function addInfoData()
{	
	var bean = BeanUtil.getCopy(GKFbsznBean);	
	$("#gk_f_bszn_table").autoBind(bean);
	if(!standard_checkInputInfo("gk_f_bszn_table"))
	{
		return;
	}

	bean.gk_bllc = getV("gk_bllc");
	bean.gk_jdjc = getV("gk_jdjc");
	bean.gk_zrzj = getV("gk_zrzj");
	bean.gk_wszx = getV("gk_wszx");
	bean.gk_wsbl = getV("gk_wsbl");
	bean.gk_ztcx = getV("gk_ztcx");
	bean.gk_wsts = getV("gk_wsts");
	bean.gk_sxyj = getV("gk_sxyj");
	bean.gk_bltj = getV("gk_bltj");
	bean.gk_blsx = getV("gk_blsx");
	bean.gk_sfyj = getV("gk_sfyj");
	bean.gk_zxqd = getV("gk_zxqd");
	bean.gk_bgsj = getV("gk_bgsj");		
	bean.gk_sfbz = getV("gk_sfbz");
	bean.gk_blshixian = getV("gk_blshixian");
	bean.gk_cclx = getV("gk_cclx");
	
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

	setCateClassToBean(bean);
	if(setGKNumHandlForInsert(bean) == false)
	{
		return;
	}
	publicSaveInfoEvent(bean,"gkfbszn","insert");
	
}
//修改
function updateInfoData()
{
	var bean = BeanUtil.getCopy(GKFbsznBean);	
	$("#gk_f_bszn_table").autoBind(bean);
	

	if(!standard_checkInputInfo("gk_f_bszn_table"))
	{
		return;
	}  
	
	bean.gk_bllc = getV("gk_bllc");
	bean.gk_jdjc = getV("gk_jdjc");
	bean.gk_zrzj = getV("gk_zrzj");
	bean.gk_wszx = getV("gk_wszx");
	bean.gk_wsbl = getV("gk_wsbl");
	bean.gk_ztcx = getV("gk_ztcx");
	bean.gk_wsts = getV("gk_wsts");
	bean.gk_sxyj = getV("gk_sxyj");
	bean.gk_bltj = getV("gk_bltj");
	bean.gk_blsx = getV("gk_blsx");
	bean.gk_sfyj = getV("gk_sfyj");
	bean.gk_zxqd = getV("gk_zxqd");
	bean.gk_bgsj = getV("gk_bgsj");
	bean.gk_sfbz = getV("gk_sfbz");
	bean.gk_blshixian = getV("gk_blshixian");
	bean.gk_cclx = getV("gk_cclx");
	
	bean.info_status = defaultBean.info_status;

	setCateClassToBean(bean);
	//修改的时候不用判断索引码，重新生成的话，如果有重复的不会修改原索引码
	publicSaveInfoEvent(bean,"gkfbszn","update");
}

