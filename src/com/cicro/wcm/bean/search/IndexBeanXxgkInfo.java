package com.cicro.wcm.bean.search;

/**
 * <p>搜索索引信息公开扩展类</p>
 * <p>搜索索引信息公开类的属性</p>
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>Company: Cicro</p>
 * @author lisupei
 * @version 1.0
 */
public class IndexBeanXxgkInfo extends IndexBean {

	private String wnumber; //文号  --- doc_no
	private String code;    //索引号 --- gk_index
    private String publish_time; //发布日期   
    private String c_time; //生成日期    --- generate_dtime 
    private String take_time; //生效时间    --- effect_dtime  
    private String over_time; //废止时间    --- aboli_dtime   
    private String object_words; //关键词    --- 主题关键词 (topic_key) + 位置关键词(place_key)
    private String form_type; //体裁分类    --- theme_name
    private String content_type; //主题分类    --- topic_name
    private String publish_org; //发布机构    --- gk_input_dept
    private String categoryId; //栏目
    
    private String typed ; //政务公开中的小分类
	
	public IndexBeanXxgkInfo(){
		super("zwgk");  //政务公开
		setTyped("tygs");  //通用格式   
	} 

	public String getWnumber() {
		return wnumber;
	}

	public void setWnumber(String wnumber) {
		this.wnumber = wnumber;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getPublish_time() {
		return publish_time;
	}

	public void setPublish_time(String publishTime) {
		publish_time = publishTime;
	}

	public String getTake_time() {
		return take_time;
	}

	public void setTake_time(String takeTime) {
		take_time = takeTime;
	}

	public String getOver_time() {
		return over_time;
	}

	public void setOver_time(String overTime) {
		over_time = overTime;
	}

	public String getObject_words() {
		return object_words;
	}

	public void setObject_words(String objectWords) {
		object_words = objectWords;
	}

	public String getForm_type() {
		return form_type;
	}

	public void setForm_type(String formType) {
		form_type = formType;
	}

	public String getContent_type() {
		return content_type;
	}

	public void setContent_type(String contentType) {
		content_type = contentType;
	}

	public String getPublish_org() {
		return publish_org;
	}

	public void setPublish_org(String publishOrg) {
		publish_org = publishOrg;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getC_time() {
		return c_time;
	}

	public void setC_time(String cTime) {
		c_time = cTime;
	}

	public String getTyped() {
		return typed;
	}

	public void setTyped(String typed) {
		this.typed = typed;
	}
	
	
    
}
