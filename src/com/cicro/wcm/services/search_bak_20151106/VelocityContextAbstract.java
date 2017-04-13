/*     */ package com.cicro.wcm.services.search_bak_20151106;
/*     */ 
/*     */ import com.cicro.util.FormatUtil;
/*     */ import com.cicro.util.io.FileOperation;
/*     */ import com.cicro.util.ip.Utils;
/*     */ import com.cicro.util.jconfig.JconfigFactory;
/*     */ import com.cicro.util.jconfig.JconfigUtil;
/*     */ import com.cicro.wcm.bean.template.ClientIPBean;
/*     */ import com.cicro.wcm.services.control.domain.SiteDomainManager;
/*     */ import com.cicro.wcm.services.control.site.SiteManager;
/*     */ import com.cicro.wcm.services.system.template.TemplateUtils;
/*     */ import com.cicro.wcm.template.velocity.VelocityEngineInstance;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.io.PrintStream;
/*     */ import java.io.StringWriter;
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import java.io.Writer;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import org.apache.velocity.Template;
/*     */ import org.apache.velocity.VelocityContext;
/*     */ import org.apache.velocity.app.VelocityEngine;
/*     */ 
/*     */ public abstract class VelocityContextAbstract
/*     */ {
/*  40 */   private static Map<String, Object> map = new HashMap();
/*     */ 
/*  59 */   protected String template_id = "";
/*  60 */   protected String site_id = "";
/*     */ 
/*  62 */   protected VelocityContext vcontext = new VelocityContext();
/*     */ 
/*     */   static
/*     */   {
/*     */     try
/*     */     {
/*  45 */       JconfigUtil jc = JconfigFactory.getJconfigUtilInstance("velocityTemplate");
/*  46 */       String[] classes = jc.getPropertyNamesByCategory("LoaderTemplateContextClass");
/*  47 */       for (String name : classes)
/*     */         try {
/*  49 */           map.put(name, Class.forName(jc.getProperty(name, null, "LoaderTemplateContextClass")));
/*     */         } catch (ClassNotFoundException e) {
/*  51 */           e.printStackTrace();
/*     */         }
/*     */     }
/*     */     catch (Exception e) {
/*  55 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public VelocityContextAbstract()
/*     */   {
/*     */   }
/*     */ 
/*     */   public VelocityContextAbstract(HttpServletRequest request)
/*     */   {
/*  75 */     inputParam(request);
/*     */   }
/*     */ 
/*     */   public void inputParam(HttpServletRequest request)
/*     */   {
/*  80 */     this.vcontext.put("v_session", request.getSession());
/*  81 */     this.vcontext.put("v_request", request);
/*  82 */     ClientIPBean cip = new ClientIPBean();
/*  83 */     String ip = FormatUtil.getIpAddr(request);
/*  84 */     cip.setIp(ip);
/*  85 */     cip.setContrey(Utils.getCountry(ip));
/*  86 */     cip.setArea(Utils.getArea(ip));
/*  87 */     this.vcontext.put("ClientIP", cip);
/*     */ 
/*  89 */     this.site_id = SiteDomainManager.getSiteIDByUrl(request.getRequestURL().toString());
/*     */ 
/*  91 */     String params = FormatUtil.getParameterStrInRequest(request);
/*     */     try {
/*  93 */       params = new String(params.getBytes("ISO-8859-1"), "UTF-8");
/*     */     }
/*     */     catch (UnsupportedEncodingException e) {
/*  96 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public String parseTemplate()
/*     */   {
/*     */     try
/*     */     {
/* 134 */       if ((this.vcontext.get("site_id") == null) || ("".equals(this.vcontext.get("site_id"))) || ("null".equals(this.vcontext.get("site_id")))) {
/* 135 */         this.vcontext.put("site_id", this.site_id);
/*     */       }
/* 137 */       Template template = VelocityEngineInstance.getInstance().getTemplate(getTemplateFilePath());
/* 138 */       Writer writer = new StringWriter();
/* 139 */       loadClassContext();
/* 140 */       template.merge(this.vcontext, writer);
/* 141 */       save(writer.toString());
/*     */ 
/* 143 */       return writer.toString();
/*     */     } catch (Exception e) {
/* 145 */       e.printStackTrace();
/* 146 */     }return "";
/*     */   }
/*     */ 
/*     */   public String parseTemplate(String ware_content)
/*     */   {
/*     */     try
/*     */     {
/* 153 */       StringWriter w = new StringWriter();
/* 154 */       loadClassContext();
/* 155 */       VelocityEngineInstance.getInstance().evaluate(this.vcontext, w, "", ware_content);
/* 156 */       return w.toString();
/*     */     } catch (Exception e) {
/* 158 */       e.printStackTrace();
/* 159 */     }return "";
/*     */   }
/*     */ 
/*     */   public void loadClassContext()
/*     */   {
/* 168 */     Set keys = map.keySet();
/* 169 */     for (String key : keys)
/* 170 */       this.vcontext.put(key, map.get(key));
/*     */   }
/*     */ 
/*     */   public String getTemplateFilePath()
/*     */   {
/* 181 */     String path = TemplateUtils.getTemplatePth(this.template_id + "_" + this.site_id);
/* 182 */     System.out.println("getTemplateFilePath----" + this.template_id + "_" + this.site_id + "------" + path);
/* 183 */     return path.substring(path.indexOf("/vhost") + 8);
/*     */   }
/*     */ 
/*     */   public boolean save(String content)
/*     */   {
/* 192 */     String t_root_path = SiteManager.getSiteTempletPath(this.site_id);
/* 193 */     String templateFile = TemplateUtils.getTemplatePth(this.template_id + "_" + this.site_id);
/* 194 */     String filePath = FormatUtil.formatPath(t_root_path + "/" + templateFile, false);
/*     */     try {
/* 196 */       System.out.println("filePath = save template file path ====================================" + filePath);
/* 197 */       File f = new File(filePath);
/* 198 */       TemplateUtils.mkDirectory(f);
/* 199 */       FileOperation.writeStringToFile(f, content, false);
/* 200 */       return true;
/*     */     } catch (IOException e) {
/* 202 */       e.printStackTrace();
/*     */     }
/*     */ 
/* 205 */     return false;
/*     */   }
/*     */ 
/*     */   public String getTemplate_id() {
/* 209 */     return this.template_id;
/*     */   }
/*     */ 
/*     */   public String getSite_id() {
/* 213 */     return this.site_id;
/*     */   }
/*     */ 
/*     */   public void setTemplate_id(String templateId)
/*     */   {
/* 219 */     this.template_id = templateId;
/*     */   }
/*     */ 
/*     */   public void setSite_id(String siteId) {
/* 223 */     this.site_id = siteId;
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.search.VelocityContextAbstract
 * JD-Core Version:    0.6.2
 */