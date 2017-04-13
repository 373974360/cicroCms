/*    */ package com.cicro.wcm.template.velocity.impl;
/*    */ 
/*    */ import com.cicro.wcm.bean.interview.SubjectCategory;
/*    */ import com.cicro.wcm.services.interview.SubjectCategoryServices;
/*    */ import com.cicro.wcm.template.velocity.VelocityContextAbstract;
/*    */ import java.io.PrintStream;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import org.apache.velocity.VelocityContext;
/*    */ 
/*    */ public class VelocityInterViewContextImp extends VelocityContextAbstract
/*    */ {
/*    */   public VelocityInterViewContextImp(HttpServletRequest request)
/*    */   {
/* 12 */     super(request);
/*    */   }
/*    */ 
/*    */   public void vcontextPut(String key, Object o) {
/* 16 */     this.vcontext.put(key, o);
/*    */   }
/*    */ 
/*    */   public void setTemplateID(String cat_id, String temp_type)
/*    */   {
/* 29 */     String app_id = "interview";
/* 30 */     if ((this.site_id == null) || ("".equals(this.site_id))) {
/* 31 */       this.site_id = this.vcontext.get("site_id").toString();
/*    */     }
/* 33 */     System.out.println("VelocityInterViewContextImp---------------" + this.site_id);
/* 34 */     if ((cat_id != null) && (!"".equals(cat_id)))
/*    */     {
/* 36 */       this.vcontext.put("cat_id", cat_id);
/*    */       try
/*    */       {
/* 39 */         if ("list".equals(temp_type))
/* 40 */           this.template_id = SubjectCategoryServices.getSubjectCategoryBean(cat_id).getM_hlist_path();
/* 41 */         if ("live".equals(temp_type)) {
/* 42 */           this.template_id = SubjectCategoryServices.getSubjectCategoryBean(cat_id).getM_on_path();
/*    */         }
/* 44 */         if ("forecastList".equals(temp_type))
/* 45 */           this.template_id = SubjectCategoryServices.getSubjectCategoryBean(cat_id).getM_forecast_path();
/* 46 */         if ("historyContent".equals(temp_type))
/* 47 */           this.template_id = SubjectCategoryServices.getSubjectCategoryBean(cat_id).getM_h_path();
/* 48 */         if ("infoList".equals(temp_type))
/* 49 */           this.template_id = SubjectCategoryServices.getSubjectCategoryBean(cat_id).getM_rlist_path();
/* 50 */         if (!"infoContent".equals(temp_type)) return;
/* 51 */         this.template_id = SubjectCategoryServices.getSubjectCategoryBean(cat_id).getM_rcontent_list();
/*    */       }
/*    */       catch (Exception e)
/*    */       {
/* 55 */         e.printStackTrace();
/* 56 */         System.out.println("setTemplateID -- template_id is null app_id:" + app_id + " site_id:" + this.site_id + " id:" + cat_id);
/*    */       }
/*    */     }
/*    */     else
/*    */     {
/* 61 */       this.template_id = SubjectCategoryServices.getInterViewTemplate(temp_type, this.site_id);
/* 62 */       System.out.println("VelocityInterViewContextImp----" + this.site_id + "-----" + this.template_id);
/*    */     }
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.template.velocity.impl.VelocityInterViewContextImp
 * JD-Core Version:    0.6.2
 */