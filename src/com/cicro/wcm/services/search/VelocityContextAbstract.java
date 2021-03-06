package com.cicro.wcm.services.search;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;

import com.cicro.util.FormatUtil;
import com.cicro.util.io.FileOperation;
import com.cicro.util.jconfig.JconfigFactory;
import com.cicro.util.jconfig.JconfigUtil;
import com.cicro.wcm.bean.template.ClientIPBean;
import com.cicro.wcm.services.control.domain.SiteDomainManager;
import com.cicro.wcm.services.control.site.SiteManager;
import com.cicro.wcm.services.system.template.TemplateUtils;
import com.cicro.wcm.template.velocity.VelocityEngineInstance;


/*
  <category name="LoaderTemplateContextClass">
    <property name="Appeal" value="com.cicro.wcm.template.velocity.data.AppealData"/>
    <property name="News" value="com.cicro.wcm.template.velocity.data.NewsData"/>
  </category>
 */
/**
 * @author 符江波
 * @version 1.0
 * @date   2011-4-22 下午03:26:28
 */
public abstract class VelocityContextAbstract {
	private static Map<String, Object> map = new HashMap<String, Object>();
	
	static {
		//从配置文件中得来
		try {
			JconfigUtil jc = JconfigFactory.getJconfigUtilInstance("velocityTemplate");
			String[] classes = jc.getPropertyNamesByCategory("LoaderTemplateContextClass");
			for(String name : classes){
				try{
					map.put(name, Class.forName(jc.getProperty(name, null, "LoaderTemplateContextClass")));
				}catch(ClassNotFoundException e){
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected String template_id = "";
	protected String site_id = "";
	
	protected VelocityContext vcontext = new VelocityContext();
	
	public VelocityContextAbstract()
	{
		
	}
	
	/**
	 * 处理request提交过来的参数，并put进vcontext
	 * @return boolean
	 */
	public VelocityContextAbstract(HttpServletRequest request)
	{
		inputParam(request);
	}
	
	public void inputParam(HttpServletRequest request)
	{
		vcontext.put("v_session", request.getSession());
		vcontext.put("v_request", request);//request对象
		ClientIPBean cip = new ClientIPBean();
		String ip = FormatUtil.getIpAddr(request);
		cip.setIp(ip);
		cip.setContrey(com.cicro.util.ip.Utils.getCountry(ip));
		cip.setArea(com.cicro.util.ip.Utils.getArea(ip));
		vcontext.put("ClientIP", cip);//当前用户IP，地址对象
		
		site_id = SiteDomainManager.getSiteIDByUrl(request.getRequestURL().toString());		
		
		String params = FormatUtil.getParameterStrInRequest(request);
		try {
			params = new String(params.getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*
		if(params != null && !"".equals(params) && FormatUtil.isValiditySQL(params))
		{
			String[] tempA = params.split("&");
			for (int i=0;i<tempA.length;i++)
			{
				int index_num = tempA[i].indexOf("=");
				String vals = tempA[i].substring(index_num+1);
				if(FormatUtil.isNumeric(vals))
				{
					try{
						vcontext.put(tempA[i].substring(0,index_num), Integer.parseInt(vals));
					}catch(NumberFormatException n)
					{
						n.printStackTrace();
						vcontext.put(tempA[i].substring(0,index_num), vals);
					}
				}
				else
					vcontext.put(tempA[i].substring(0,index_num), vals);
			}
			if(params.indexOf("cur_page") == -1)
				vcontext.put("cur_page", 1);
			
			vcontext.put("requet_params", params);
		}
		*/
	}
	
	/**
	 * 模板解析并保存解析后 的文件
	 * @return boolean
	 */
	public String parseTemplate(){
		try{
		    if(vcontext.get("site_id") == null || "".equals(vcontext.get("site_id")) || "null".equals(vcontext.get("site_id")))
		    	vcontext.put("site_id", site_id);
		    
			Template template = VelocityEngineInstance.getInstance().getTemplate(getTemplateFilePath());
			Writer writer = new StringWriter();
			loadClassContext();
			template.merge(vcontext, writer);
			save(writer.toString());
			
			return writer.toString();
		}catch(Exception e){
			e.printStackTrace();
			return "";
		}
	}
	
	public String parseTemplate(String ware_content)
	{		
		try{
			StringWriter w = new StringWriter();
			loadClassContext();
			VelocityEngineInstance.getInstance().evaluate( vcontext, w, "", ware_content ); 
			return w.toString();
		}catch(Exception e){
			e.printStackTrace();
			return "";
		}
	}
	
	/**
	 * 加载所有前台类
	 *  void
	 */
	public void loadClassContext(){
		Set<String> keys = map.keySet();
		for(String key : keys){
			vcontext.put(key, map.get(key));
		}
	}
	
	/**
	 * 得到模板的保存路径,供解析时调用
	 * @return String
	 */
	public String getTemplateFilePath(){//根据模板template_id+"_"+site_id+"_"+app_id得到模板的保存路径,供解析时调用
		//return "./src/com/cicro/wcm/template/velocity/impl/HelloWorld.vm";
		//System.out.println("getTemplateFilePath----------"+template_id+"_"+site_id+"_"+app_id);		
		String path = TemplateUtils.getTemplatePth(template_id+"_"+site_id);		
		System.out.println("getTemplateFilePath----"+template_id+"_"+site_id+"------"+path);
		return path.substring(path.indexOf("/vhost")+8);
		//return "\\WEB-INF\\classes\\test.vm";
	}
	
	/**
	 * 根据save_path和name把解析后的文件保存成htm
	 * @param content void
	 */
	public boolean save(String content){//根据save_path和name把解析后的文件保存成htm
		String t_root_path = SiteManager.getSiteTempletPath(site_id);
		String templateFile = TemplateUtils.getTemplatePth(template_id+"_"+site_id);
		String filePath = FormatUtil.formatPath(t_root_path+"/"+templateFile, false);
		try {
			System.out.println("filePath = save template file path ===================================="+filePath);
			File f = new File(filePath); 
			TemplateUtils.mkDirectory(f);
			FileOperation.writeStringToFile(f, content, false);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		//System.out.println(content);
		return false;
	}
	
	public String getTemplate_id() {
		return template_id;
	}

	public String getSite_id() {
		return site_id;
	}

	

	public void setTemplate_id(String templateId) {
		template_id = templateId;
	}

	public void setSite_id(String siteId) {
		site_id = siteId;
	}
	
}
