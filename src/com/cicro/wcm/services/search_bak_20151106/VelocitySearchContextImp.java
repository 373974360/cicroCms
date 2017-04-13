/*    */ package com.cicro.wcm.services.search_bak_20151106;
/*    */ 
/*    */ import com.cicro.wcm.bean.appeal.model.ModelBean;
/*    */ import com.cicro.wcm.services.appeal.model.ModelManager;
/*    */ import com.cicro.wcm.services.control.site.SiteAppRele;
/*    */ import java.io.PrintStream;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import com.cicro.wcm.services.search.*;
import org.apache.velocity.VelocityContext;
/*    */ 
/*    */ public class VelocitySearchContextImp extends com.cicro.wcm.services.search.VelocityContextAbstract
/*    */ {
/*    */   public VelocitySearchContextImp()
/*    */   {
/*    */   }
/*    */ 
/*    */   public VelocitySearchContextImp(HttpServletRequest request)
/*    */   {
/* 28 */     super(request);
/*    */   }
/*    */ 
/*    */   public void vcontextPut(String key, Object o)
/*    */   {
/* 34 */     this.vcontext.put(key, o);
/*    */   }
/*    */ 
/*    */   public void setTemplateID(String site_ids, String template_ids)
/*    */   {
/* 45 */     this.site_id = site_ids;
/* 46 */     this.template_id = template_ids;
/*    */   }
/*    */ 
/*    */   public void setModelID(String model_id, String temp_type)
/*    */   {
/* 65 */     this.site_id = SiteAppRele.getSiteIDByAppID("appeal");
/*    */ 
/* 67 */     if ((model_id != null) && (!"".equals(model_id)))
/*    */     {
/* 71 */       this.vcontext.put("model_id", model_id);
/*    */       try
/*    */       {
/* 75 */         if ("form".equals(temp_type))
/*    */         {
/* 77 */           this.template_id = ModelManager.getModelBean(Integer.parseInt(model_id)).getTemplate_form();
/*    */         }
/* 79 */         if ("list".equals(temp_type))
/*    */         {
/* 81 */           this.template_id = ModelManager.getModelBean(Integer.parseInt(model_id)).getTemplate_list();
/*    */         }
/* 83 */         if ("content".equals(temp_type))
/*    */         {
/* 85 */           this.template_id = ModelManager.getModelBean(Integer.parseInt(model_id)).getTemplate_content();
/*    */         }
/* 87 */         if ("print".equals(temp_type))
/*    */         {
/* 89 */           this.template_id = ModelManager.getModelBean(Integer.parseInt(model_id)).getTemplate_print();
/*    */         }
/* 91 */         if (!"search".equals(temp_type))
/*    */           return;
/* 93 */         this.template_id = ModelManager.getModelBean(Integer.parseInt(model_id)).getTemplate_search_list();
/*    */       }
/*    */       catch (Exception e)
/*    */       {
/* 99 */         e.printStackTrace();
/*    */ 
/* 101 */         System.out.println("setModelID -- ModelBean is null id:" + model_id);
/*    */       }
/*    */ 
/*    */     }
/*    */     else
/*    */     {
/* 109 */       this.template_id = ModelManager.getModelTemplate(temp_type);
/*    */     }
/*    */   }
/*    */ 
/*    */   public static void main(String[] args)
/*    */   {
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.search.VelocitySearchContextImp
 * JD-Core Version:    0.6.2
 */