/*    */ package com.cicro.wcm.template.velocity.impl;
/*    */ 
/*    */ import com.cicro.wcm.bean.minlu.MingLuBean;
/*    */ import com.cicro.wcm.services.minlu.MingLuManager;
/*    */ import com.cicro.wcm.template.velocity.VelocityContextAbstract;
/*    */ import java.io.PrintStream;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import org.apache.velocity.VelocityContext;
/*    */ 
/*    */ public class VelocityMingLuContextImp extends VelocityContextAbstract
/*    */ {
/*    */   public VelocityMingLuContextImp(HttpServletRequest request)
/*    */   {
/* 12 */     super(request);
/*    */   }
/*    */ 
/*    */   public void vcontextPut(String key, Object o) {
/* 16 */     this.vcontext.put(key, o);
/*    */   }
/*    */ 
/*    */   public void setTemplateID(String site_ids, String template_ids)
/*    */   {
/* 27 */     this.site_id = site_ids;
/* 28 */     this.template_id = template_ids;
/*    */   }
/*    */ 
/*    */   public void setMLTemplateID(String t_site_id, String temp_type)
/*    */   {
/* 33 */     this.site_id = t_site_id;
/* 34 */     this.vcontext.put("site_id", this.site_id);
/* 35 */     MingLuBean mlb = MingLuManager.getMingLuBean(this.site_id);
/* 36 */     if (mlb != null)
/*    */     {
/* 38 */       if ("index".equals(temp_type))
/* 39 */         this.template_id = mlb.getTemplate_index();
/* 40 */       if ("list".equals(temp_type))
/* 41 */         this.template_id = mlb.getTemplate_list();
/* 42 */       if ("content".equals(temp_type))
/* 43 */         this.template_id = mlb.getTemplate_content();
/* 44 */       if ("infolist".equals(temp_type))
/* 45 */         this.template_id = mlb.getReinfo_temp_list();
/* 46 */       if ("infocontent".equals(temp_type))
/* 47 */         this.template_id = mlb.getReinfo_temp_content();
/* 48 */       if ("piclist".equals(temp_type))
/* 49 */         this.template_id = mlb.getReinfo_temp_pic_list();
/* 50 */       if ("piccontent".equals(temp_type))
/* 51 */         this.template_id = mlb.getReinfo_temp_pic_content();
/*    */     }
/*    */     else
/*    */     {
/* 55 */       System.out.println("setMLTemplateID -- MingLuBean is null id:" + t_site_id);
/*    */     }
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.template.velocity.impl.VelocityMingLuContextImp
 * JD-Core Version:    0.6.2
 */