/*    */ package com.cicro.wcm.services.system.template;
/*    */ 
/*    */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*    */ import com.cicro.wcm.bean.system.template.TemplateClassBean;
/*    */ import com.cicro.wcm.dao.system.template.TemplateClassDAO;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class TemplateClassManager
/*    */ {
/*    */   public static TemplateClassBean getTemplateClassBean(int t_id)
/*    */   {
/* 23 */     return TemplateClassDAO.getTemplateClassBean(t_id);
/*    */   }
/*    */ 
/*    */   public static String getTemplateClassCount(Map<String, String> con_map)
/*    */   {
/* 33 */     return TemplateClassDAO.getTemplateClassCount(con_map);
/*    */   }
/*    */ 
/*    */   public static boolean updateTemplateClass(TemplateClassBean template, SettingLogsBean stl)
/*    */   {
/* 42 */     return TemplateClassDAO.updateTemplateClass(template, stl);
/*    */   }
/*    */ 
/*    */   public static boolean addTemplateClass(TemplateClassBean template, SettingLogsBean stl)
/*    */   {
/* 51 */     if (template == null) {
/* 52 */       return false;
/*    */     }
/* 54 */     return TemplateClassDAO.addTemplateClass(template, stl);
/*    */   }
/*    */ 
/*    */   public static List<TemplateClassBean> getAllTemplateClassList()
/*    */   {
/* 63 */     return TemplateClassDAO.getAllTemplateClassList();
/*    */   }
/*    */ 
/*    */   public static List<TemplateClassBean> getTemplateClassListForDB(Map<String, String> con_map)
/*    */   {
/* 73 */     return TemplateClassDAO.getTemplateClassListForDB(con_map);
/*    */   }
/*    */ 
/*    */   public static boolean delTemplateClassById(String t_ids, SettingLogsBean stl)
/*    */   {
/* 82 */     if ((t_ids == null) || (t_ids.equals(""))) {
/* 83 */       return false;
/*    */     }
/* 85 */     return TemplateClassDAO.delTemplateClass(t_ids, stl);
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.system.template.TemplateClassManager
 * JD-Core Version:    0.6.2
 */