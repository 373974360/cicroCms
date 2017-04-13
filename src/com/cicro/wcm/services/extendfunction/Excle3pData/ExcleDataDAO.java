/*    */ package com.cicro.wcm.services.extendfunction.Excle3pData;
/*    */ 
/*    */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*    */ import com.cicro.wcm.dao.PublicTableDAO;
/*    */ import com.cicro.wcm.db.DBManager;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class ExcleDataDAO
/*    */ {
/*    */   public static boolean insertExcleInfoMation(ExcleDataBean exb)
/*    */   {
/* 13 */     return DBManager.insert("excle_data.insertExcleInfoMation", exb);
/*    */   }
/*    */ 
/*    */   public static String checkIsExit(String from_url) {
/* 17 */     return DBManager.getString("excle_data.checkIsExit", from_url);
/*    */   }
/*    */ 
/*    */   public static boolean deleteExcleInfo(Map<String, String> map, SettingLogsBean stl) {
/* 21 */     if (DBManager.delete("deleteExcleInfo", map)) {
/* 22 */       PublicTableDAO.insertSettingLogs("删除", "excle导入信息", "", stl);
/* 23 */       return true;
/*    */     }
/* 25 */     return false;
/*    */   }
/*    */ }

/* Location:           E:\公司产品包\定制包\extendfunction.zip
 * Qualified Name:     extendfunction.Excle3pData.ExcleDataDAO
 * JD-Core Version:    0.6.2
 */