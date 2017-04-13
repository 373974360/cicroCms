/*    */ package com.cicro.wcm.template.velocity.impl;
/*    */ 
/*    */ import com.cicro.wcm.bean.zwgk.ysqgk.YsqgkConfigBean;
/*    */ import com.cicro.wcm.services.zwgk.ysqgk.YsqgkConfigManager;
/*    */ import com.cicro.wcm.template.velocity.VelocityContextAbstract;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import org.apache.velocity.VelocityContext;
/*    */ 
/*    */ public class VelocityYSQGKContextImp extends VelocityContextAbstract
/*    */ {
/*    */   public VelocityYSQGKContextImp(HttpServletRequest request)
/*    */   {
/* 12 */     super(request);
/*    */   }
/*    */ 
/*    */   public void setTemplateID(String site_ids, String template_ids)
/*    */   {
/* 17 */     this.site_id = site_ids;
/* 18 */     this.template_id = template_ids;
/* 19 */     this.vcontext.put("site_id", site_ids);
/*    */   }
/*    */ 
/*    */   public void setYSQGKTemplateID(String site_ids, String temp_type)
/*    */   {
/* 24 */     this.site_id = site_ids;
/* 25 */     this.vcontext.put("site_id", site_ids);
/* 26 */     YsqgkConfigBean ycb = YsqgkConfigManager.getYsqgkConfigBean();
/* 27 */     if (ycb != null)
/*    */     {
/* 29 */       if ("form".equals(temp_type))
/* 30 */         this.template_id = ycb.getTemplate_form();
/* 31 */       if ("list".equals(temp_type))
/* 32 */         this.template_id = ycb.getTemplate_list();
/* 33 */       if ("content".equals(temp_type))
/* 34 */         this.template_id = ycb.getTemplate_content();
/* 35 */       if ("over".equals(temp_type))
/* 36 */         this.template_id = ycb.getTemplate_over();
/* 37 */       if ("print".equals(temp_type))
/* 38 */         this.template_id = ycb.getTemplate_print();
/* 39 */       if ("query".equals(temp_type))
/* 40 */         this.template_id = ycb.getTemplate_query();
/*    */     }
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.template.velocity.impl.VelocityYSQGKContextImp
 * JD-Core Version:    0.6.2
 */