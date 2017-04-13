/*    */ package com.cicro.wcm.template.velocity.impl;
/*    */ 
/*    */ import com.cicro.wcm.bean.survey.SurveyCategory;
/*    */ import com.cicro.wcm.services.survey.SurveyCategoryService;
/*    */ import com.cicro.wcm.template.velocity.VelocityContextAbstract;
/*    */ import java.io.PrintStream;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import org.apache.velocity.VelocityContext;
/*    */ 
/*    */ public class VelocitySurveyContextImp extends VelocityContextAbstract
/*    */ {
/*    */   public VelocitySurveyContextImp(HttpServletRequest request)
/*    */   {
/* 12 */     super(request);
/*    */   }
/*    */ 
/*    */   public void setTemplateID(String cat_id, String temp_type)
/*    */   {
/* 25 */     String app_id = "survey";
/*    */ 
/* 27 */     if ((cat_id != null) && (!"".equals(cat_id)))
/*    */     {
/* 29 */       this.vcontext.put("cat_id", cat_id);
/*    */       try {
/* 31 */         if ("list".equals(temp_type))
/* 32 */           this.template_id = SurveyCategoryService.getSurveyCategoryBean(cat_id).getTemplate_list_path();
/* 33 */         if ("content".equals(temp_type))
/* 34 */           this.template_id = SurveyCategoryService.getSurveyCategoryBean(cat_id).getTemplate_content_path();
/* 35 */         if (!"result".equals(temp_type)) return;
/* 36 */         this.template_id = SurveyCategoryService.getSurveyCategoryBean(cat_id).getTemplate_result_path();
/*    */       }
/*    */       catch (Exception e)
/*    */       {
/* 40 */         e.printStackTrace();
/* 41 */         System.out.println("setTemplateID -- template_id is null app_id:" + app_id + " site_id:" + this.site_id + " id:" + cat_id);
/*    */       }
/*    */     }
/*    */     else {
/* 45 */       this.template_id = SurveyCategoryService.getSurveyTemplate(temp_type, this.site_id);
/* 46 */       System.out.println("VelocitySurveyContextImp----" + this.site_id + "-----" + this.template_id);
/*    */     }
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.template.velocity.impl.VelocitySurveyContextImp
 * JD-Core Version:    0.6.2
 */