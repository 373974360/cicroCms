package com.cicro.wcm.services.search;

import javax.servlet.http.HttpServletRequest;import com.cicro.wcm.services.appeal.model.ModelManager;import com.cicro.wcm.services.control.site.SiteAppRele;

public class VelocitySearchContextImp extends VelocityContextAbstract{
		public VelocitySearchContextImp()	{ 		super();	}	
	public VelocitySearchContextImp(HttpServletRequest request)
	{
		super(request);
	}		public void vcontextPut(String key,Object o){		vcontext.put(key, o);		}	
		/**     * 手动设置模板ID          * @param String site_ids     * @param String template_ids     * @return boolean     * */	public void setTemplateID(String site_ids,String template_ids)	{		site_id = site_ids;		template_id = template_ids;	}
	/**
     * 设置业务属性
     * @param String model_id 业务ID
     * @param temp_type 模板类型　form,list,content
     * @return boolean
     * */
	public void setModelID(String model_id,String temp_type)
	{
		site_id = SiteAppRele.getSiteIDByAppID("appeal");
		if(model_id != null && !"".equals(model_id))
		{
			vcontext.put("model_id", model_id);			
			try{
				if("form".equals(temp_type))
					template_id = ModelManager.getModelBean(Integer.parseInt(model_id)).getTemplate_form()+"";
				if("list".equals(temp_type))
					template_id = ModelManager.getModelBean(Integer.parseInt(model_id)).getTemplate_list()+"";
				if("content".equals(temp_type))
					template_id = ModelManager.getModelBean(Integer.parseInt(model_id)).getTemplate_content()+"";
				if("print".equals(temp_type))
					template_id = ModelManager.getModelBean(Integer.parseInt(model_id)).getTemplate_print()+"";
				if("search".equals(temp_type))
					template_id = ModelManager.getModelBean(Integer.parseInt(model_id)).getTemplate_search_list()+"";
			}catch(Exception e)
			{
				e.printStackTrace();
				System.out.println("setModelID -- ModelBean is null id:"+model_id);
			}			
		}else
		{
			template_id = ModelManager.getModelTemplate(temp_type);
		}
	}
	
	
	/**
	 * 
	 * @param args void
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//VelocityAppealContextImp vii = new VelocityAppealContextImp();
		//vii.getList("model_id=25&cur_page=1");	 
		//vii.getContent("40");
		//vii.getContext("model_id=25,30,33&curr_page=1");
		//vii.setModelID("30","list");
		//System.out.println(vii.parseTemplate());
	}
}
