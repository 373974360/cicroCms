/*    */ package com.cicro.wcm.services.system.template;
/*    */ 
/*    */ import com.cicro.wcm.bean.system.template.TemplateBean;
/*    */ import com.cicro.wcm.bean.system.template.TemplateCategoryBean;
/*    */ import com.cicro.wcm.bean.system.template.TemplateEditBean;
/*    */ import com.cicro.wcm.dao.system.template.TemplateCategoryDAO;
/*    */ import com.cicro.wcm.dao.system.template.TemplateDAO;
/*    */ import com.cicro.wcm.dao.system.template.TemplateEditDAO;
/*    */ import com.cicro.wcm.services.control.site.ICloneSite;
/*    */ import com.cicro.wcm.template.TemplateBase;
/*    */ import java.io.PrintStream;
/*    */ import java.util.List;
/*    */ 
/*    */ public class TemplateCloneImpl
/*    */   implements ICloneSite
/*    */ {
/*    */   public boolean cloneSite(String site_id, String s_site_id)
/*    */   {
/*    */     try
/*    */     {
/* 20 */       templateCategoryClone(site_id, s_site_id);
/* 21 */       templateClone(site_id, s_site_id);
/* 22 */       return true;
/*    */     }
/*    */     catch (Exception e) {
/* 25 */       e.printStackTrace();
/* 26 */     }return false;
/*    */   }
/*    */ 
/*    */   public static boolean templateCategoryClone(String site_id, String s_site_id)
/*    */   {
/*    */     try
/*    */     {
/* 33 */       List l = TemplateCategoryDAO.getTemplateCategoryListBySiteID(s_site_id);
/* 34 */       if ((l != null) && (l.size() > 0))
/*    */       {
/* 36 */         for (TemplateCategoryBean tcb : l)
/*    */         {
/* 38 */           tcb.setSite_id(site_id);
/* 39 */           TemplateCategoryDAO.cloneTemplateCategory(tcb);
/*    */         }
/* 41 */         TemplateCategoryManager.reloadTemplateCategory();
/*    */       }
/* 43 */       return true;
/*    */     }
/*    */     catch (Exception e) {
/* 46 */       e.printStackTrace();
/* 47 */     }return false;
/*    */   }
/*    */ 
/*    */   public static boolean templateClone(String site_id, String s_site_id)
/*    */   {
/*    */     try
/*    */     {
/* 54 */       List l = TemplateEditDAO.getAllTemplateEditList("", s_site_id, "");
/* 55 */       if ((l != null) && (l.size() > 0))
/*    */       {
/* 57 */         for (TemplateEditBean teb : l)
/*    */         {
/* 59 */           teb.setSite_id(site_id);
/* 60 */           teb.setT_ver(1);
/* 61 */           if (TemplateEditDAO.cloneTemplateEdit(teb))
/*    */           {
/* 68 */             TemplateBean tb = new TemplateBean();
/* 69 */             tb.setT_id(teb.getT_id());
/* 70 */             tb.setT_ver(teb.getT_ver());
/* 71 */             tb.setSite_id(site_id);
/* 72 */             tb.setApp_id(teb.getApp_id());
/* 73 */             TemplateDAO.cloneTemplate(tb);
/*    */ 
/* 75 */             TemplateUtils.setTemplatePath(teb);
/*    */ 
/* 78 */             TemplateBase.saveTemplateFile(teb);
/*    */           }
/*    */         }
/*    */       }
/* 82 */       return true;
/*    */     }
/*    */     catch (Exception e) {
/* 85 */       e.printStackTrace();
/* 86 */     }return false;
/*    */   }
/*    */ 
/*    */   public static void main(String[] args)
/*    */   {
/* 92 */     System.out.println(TemplateUtils.getTemplatePath("190_HIWCM9999"));
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.system.template.TemplateCloneImpl
 * JD-Core Version:    0.6.2
 */