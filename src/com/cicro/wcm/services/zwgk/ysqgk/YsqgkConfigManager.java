/*    */ package com.cicro.wcm.services.zwgk.ysqgk;
/*    */ 
/*    */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*    */ import com.cicro.wcm.bean.zwgk.ysqgk.YsqgkConfigBean;
/*    */ import com.cicro.wcm.dao.PublicTableDAO;
/*    */ import com.cicro.wcm.dao.zwgk.ysqgk.YsqgkConfigDAO;
/*    */ 
/*    */ public class YsqgkConfigManager
/*    */ {
/* 11 */   private static YsqgkConfigBean ykcb = null;
/*    */ 
/*    */   static {
/* 14 */     getYsqgkConfigBeanForDB();
/*    */   }
/*    */ 
/*    */   public static boolean insertYsqgkConfig(YsqgkConfigBean ysqgk, SettingLogsBean stl)
/*    */   {
/* 24 */     if (deleteYsqgkConfig(stl)) {
/* 25 */       int ysq_id = PublicTableDAO.getIDByTableName(PublicTableDAO.YSQGK_CONFIG_TABLE_NAME);
/* 26 */       ysqgk.setId(ysq_id);
/*    */ 
/* 28 */       if (YsqgkConfigDAO.insertYsqgkConfig(ysqgk, stl))
/*    */       {
/* 30 */         ykcb = ysqgk;
/* 31 */         return true;
/*    */       }
/* 33 */       return false;
/*    */     }
/* 35 */     return true;
/*    */   }
/*    */ 
/*    */   public static boolean deleteYsqgkConfig(SettingLogsBean stl)
/*    */   {
/* 45 */     if (YsqgkConfigDAO.deleteYsqgkConfig(stl))
/*    */     {
/* 47 */       ykcb = null;
/* 48 */       return true;
/*    */     }
/* 50 */     return false;
/*    */   }
/*    */ 
/*    */   public static YsqgkConfigBean getYsqgkConfigBean()
/*    */   {
/* 59 */     return ykcb;
/*    */   }
/*    */ 
/*    */   public static void getYsqgkConfigBeanForDB()
/*    */   {
/* 64 */     ykcb = YsqgkConfigDAO.getYsqgkConfigBean();
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.zwgk.ysqgk.YsqgkConfigManager
 * JD-Core Version:    0.6.2
 */