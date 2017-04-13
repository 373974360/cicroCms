/*    */ package com.cicro.wcm.dao.zwgk.ysqgk;
/*    */ 
/*    */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*    */ import com.cicro.wcm.bean.zwgk.ysqgk.YsqgkConfigBean;
/*    */ import com.cicro.wcm.dao.PublicTableDAO;
/*    */ import com.cicro.wcm.db.DBManager;
/*    */ 
/*    */ public class YsqgkConfigDAO
/*    */ {
/*    */   public static YsqgkConfigBean getYsqgkConfigBean()
/*    */   {
/* 36 */     return (YsqgkConfigBean)DBManager.queryFObj("getYsqgkConfigBean", "");
/*    */   }
/*    */ 
/*    */   public static boolean insertYsqgkConfig(YsqgkConfigBean ysqgk, SettingLogsBean stl)
/*    */   {
/* 47 */     if (DBManager.insert("insert_ysqgk_config", ysqgk))
/*    */     {
/* 49 */       PublicTableDAO.insertSettingLogs("添加", "依申请公开配置", "", stl);
/* 50 */       return true;
/*    */     }
/* 52 */     return false;
/*    */   }
/*    */ 
/*    */   public static boolean deleteYsqgkConfig(SettingLogsBean stl)
/*    */   {
/* 62 */     if (DBManager.delete("delete_ysqgk_config", ""))
/*    */     {
/* 64 */       PublicTableDAO.insertSettingLogs("删除", "依申请公开配置", "", stl);
/* 65 */       return true;
/*    */     }
/* 67 */     return false;
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.dao.zwgk.ysqgk.YsqgkConfigDAO
 * JD-Core Version:    0.6.2
 */