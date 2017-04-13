/*    */ package com.cicro.wcm.dao.zwgk.index;
/*    */ 
/*    */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*    */ import com.cicro.wcm.bean.zwgk.index.IndexRoleBean;
/*    */ import com.cicro.wcm.dao.PublicTableDAO;
/*    */ import com.cicro.wcm.db.DBManager;
/*    */ import java.util.List;
/*    */ 
/*    */ public class IndexRoleDAO
/*    */ {
/*    */   public static List<IndexRoleBean> getAllIndexRole()
/*    */   {
/* 25 */     return DBManager.queryFList("getIndexRole", "");
/*    */   }
/*    */ 
/*    */   public static boolean updateIndexRole(IndexRoleBean irb, SettingLogsBean stl)
/*    */   {
/* 35 */     if (DBManager.update("updateIndexRole", irb)) {
/* 36 */       PublicTableDAO.insertSettingLogs("修改", "索引规则", irb.getId(), stl);
/* 37 */       return true;
/*    */     }
/* 39 */     return false;
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.dao.zwgk.index.IndexRoleDAO
 * JD-Core Version:    0.6.2
 */