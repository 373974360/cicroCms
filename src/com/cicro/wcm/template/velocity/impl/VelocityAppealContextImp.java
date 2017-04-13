/*    */ package com.cicro.wcm.template.velocity.impl;
/*    */ 
/*    */ import com.cicro.wcm.bean.appeal.model.ModelBean;
/*    */ import com.cicro.wcm.services.appeal.model.ModelManager;
/*    */ import com.cicro.wcm.services.control.site.SiteAppRele;
/*    */ import com.cicro.wcm.template.velocity.VelocityContextAbstract;
/*    */ import java.io.PrintStream;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import org.apache.velocity.VelocityContext;
/*    */ 
/*    */ public class VelocityAppealContextImp extends VelocityContextAbstract
/*    */ {
/*    */   public VelocityAppealContextImp(HttpServletRequest request)
/*    */   {
/* 21 */     super(request);
/*    */   }
/*    */ 
/*    */   public void vcontextPut(String key, Object o)
/*    */   {
/* 27 */     this.vcontext.put(key, o);
/*    */   }
/*    */ 
/*    */   public void setTemplateID(String site_ids, String template_ids)
/*    */   {
/* 38 */     this.site_id = site_ids;
/* 39 */     this.template_id = template_ids;
/*    */   }
/*    */ 
/*    */   public void setModelID(String model_id, String temp_type)
/*    */   {
/* 58 */     this.site_id = SiteAppRele.getSiteIDByAppID("appeal");
/*    */ 
/* 60 */     if ((model_id != null) && (!"".equals(model_id)))
/*    */     {
/* 64 */       this.vcontext.put("model_id", model_id);
/*    */       try
/*    */       {
/* 68 */         if ("form".equals(temp_type))
/*    */         {
/* 70 */           this.template_id = ModelManager.getModelBean(Integer.parseInt(model_id)).getTemplate_form();
/*    */         }
/* 72 */         if ("list".equals(temp_type))
/*    */         {
/* 74 */           this.template_id = ModelManager.getModelBean(Integer.parseInt(model_id)).getTemplate_list();
/*    */         }
/* 76 */         if ("content".equals(temp_type))
/*    */         {
/* 78 */           this.template_id = ModelManager.getModelBean(Integer.parseInt(model_id)).getTemplate_content();
/*    */         }
/* 80 */         if ("print".equals(temp_type))
/*    */         {
/* 82 */           this.template_id = ModelManager.getModelBean(Integer.parseInt(model_id)).getTemplate_print();
/*    */         }
/* 84 */         if (!"search".equals(temp_type))
/*    */           return;
/* 86 */         this.template_id = ModelManager.getModelBean(Integer.parseInt(model_id)).getTemplate_search_list();
/*    */       }
/*    */       catch (Exception e)
/*    */       {
/* 92 */         e.printStackTrace();
/*    */ 
/* 94 */         System.out.println("setModelID -- ModelBean is null id:" + model_id);
/*    */       }
/*    */ 
/*    */     }
/*    */     else
/*    */     {
/* 102 */       this.template_id = ModelManager.getModelTemplate(temp_type);
/*    */     }
/*    */   }
/*    */ 
/*    */   public static void main(String[] args)
/*    */   {
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.template.velocity.impl.VelocityAppealContextImp
 * JD-Core Version:    0.6.2
 */