/*    */ package com.cicro.wcm.template.velocity.impl;
/*    */ 
/*    */ import com.cicro.wcm.bean.appeal.model.ModelBean;
/*    */ import com.cicro.wcm.services.appeal.model.ModelManager;
/*    */ import com.cicro.wcm.services.control.site.SiteAppRele;
/*    */ import com.cicro.wcm.template.velocity.VelocityContextAbstract;
/*    */ import java.io.PrintStream;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ 
/*    */ public class VelocityCommentContextImp extends VelocityContextAbstract
/*    */ {
/*    */   public VelocityCommentContextImp(HttpServletRequest request)
/*    */   {
/* 12 */     super(request);
/*    */   }
/*    */ 
/*    */   public void setTemplateID(String app_ids, String site_ids, String model_id)
/*    */   {
/* 25 */     if ((model_id != null) && (!"".equals(model_id)))
/*    */     {
/* 27 */       String app_id = "appeal";
/*    */       try {
/* 29 */         if ("appeal".equals(app_ids))
/*    */         {
/* 31 */           this.site_id = SiteAppRele.getSiteIDByAppID(app_id);
/* 32 */           this.template_id = ModelManager.getModelBean(Integer.parseInt(model_id)).getTemplate_comment();
/*    */         }
/*    */       }
/*    */       catch (Exception e) {
/* 36 */         e.printStackTrace();
/* 37 */         System.out.println("setTemplateID -- template_id is null app_id:" + app_id + " site_id:" + this.site_id + " id:" + model_id);
/*    */       }
/*    */     }
/*    */   }
/*    */ 
/*    */   public static void main(String[] args)
/*    */   {
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.template.velocity.impl.VelocityCommentContextImp
 * JD-Core Version:    0.6.2
 */