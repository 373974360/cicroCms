var VideoBean = new Bean("com.cicro.wcm.bean.cms.info.VideoBean",true);

function getV(){
	return KE.html("info_content");
}

function setV(v){
	KE.html("info_content", v);
}

// 视频格式类型
var video_form = "asf,avi,mpg,mpeg,mpe,mov,rmvb,wmv,wav,mid,midi,mp3,mpa,mp2,ra,ram,rm,wma,flv,mp4";
// 添加视频新闻操作
function addInfoData()
{
	var bean = BeanUtil.getCopy(VideoBean);	
	$("#info_article_table").autoBind(bean);
	bean.info_content = getV();
	if(!standard_checkInputInfo("info_article_table"))
	{
		return;
	}

	// bean.info_content = replace_img_content;	
	// 审核状态设置
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
	
	if(check_videoPath()==0)
	{
		top.msgAlert("视频不能为空，请上传视频!");
		return;
	}
	if(check_videoPath()==1)
	{
		top.msgAlert("视频格式不正确，请重新上传!");
		return;
	}
	publicSaveInfoEvent(bean,"video","insert");	
}

// 修改视频新闻操作
function updateInfoData()
{
	var bean = BeanUtil.getCopy(VideoBean);	
	$("#info_article_table").autoBind(bean);
	if(!standard_checkInputInfo("info_article_table"))
	{
		return;
	}
	bean.info_content = getV();
	bean.info_status = defaultBean.info_status;
	
	// 视频的格式和非空检查
	if(check_videoPath()==0)
	{
		top.msgAlert("视频不能为空，请上传视频!");
		return;
	}
	if(check_videoPath()==1)
	{
		top.msgAlert("视频格式不正确，请重新上传!");
		return;
	}
	publicSaveInfoEvent(bean,"video","update");		
}

// 检查视频是否为空
function check_videoPath()
{
	if($("#video_path").val().length == 0)
	{
		return 0; // 视频为空，没有上传视频
	}
	if(!check_form())
	{
		return 1; // 视频格式不正确
	}
	return 9;
}

// 检查视频格式
function check_form()
{
	var path = $("#video_path").val();
	var lt = path.split(".");
	
	if(lt.length == 0)
	{
		return false;
	}
	var suffix = lt[lt.length-1];
	if( video_form.indexOf(suffix) == -1 )
	{
		return false;
	}
	return true;
}

