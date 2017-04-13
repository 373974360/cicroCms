/*    */ package com.cicro.wcm.dao.minlu;
/*    */ 
/*    */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*    */ import com.cicro.wcm.bean.minlu.MingLuBean;
/*    */ import com.cicro.wcm.dao.PublicTableDAO;
/*    */ import com.cicro.wcm.db.DBManager;
/*    */ import java.util.List;
/*    */ 
/*    */ public class MingLuDAO
/*    */ {
/*    */   public static List<MingLuBean> getMingLuReleTemplateList()
/*    */   {
/* 20 */     return DBManager.queryFList("getMingLuReleTemplateList", "");
/*    */   }
/*    */ 
/*    */   public static MingLuBean getMingLuBean(String site_id)
/*    */   {
/* 30 */     return (MingLuBean)DBManager.queryFObj("getMingLuBean", site_id);
/*    */   }
/*    */ 
/*    */   public static boolean insertMingLuTemplate(MingLuBean ml, SettingLogsBean stl)
/*    */   {
/* 41 */     if (DBManager.insert("insert_minlu_template", ml))
/*    */     {
/* 43 */       PublicTableDAO.insertSettingLogs("添加", "公务员名录配置信息 站点ID", ml.getSite_id(), stl);
/* 44 */       return true;
/*    */     }
/* 46 */     return false;
/*    */   }
/*    */ 
/*    */   public static boolean updateMingLuTemplate(MingLuBean ml, SettingLogsBean stl)
/*    */   {
/* 58 */     if (DBManager.update("update_minlu_template", ml))
/*    */     {
/* 60 */       PublicTableDAO.insertSettingLogs("修改", "公务员名录配置信息 站点ID", ml.getSite_id(), stl);
/* 61 */       return true;
/*    */     }
/* 63 */     return false;
/*    */   }
/*    */ 
/*    */   public static boolean deleteMingLuTemplate(String site_id)
/*    */   {
/* 73 */     return DBManager.delete("delete_minlu_template", site_id);
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.dao.minlu.MingLuDAO
 * JD-Core Version:    0.6.2
 */