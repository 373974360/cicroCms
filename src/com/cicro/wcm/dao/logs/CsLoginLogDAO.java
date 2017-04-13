/*    */ package com.cicro.wcm.dao.logs;
/*    */ 
/*    */ import com.cicro.util.DateUtil;
/*    */ import com.cicro.wcm.bean.logs.LoginLogsBean;
/*    */ import com.cicro.wcm.dao.PublicTableDAO;
/*    */ import com.cicro.wcm.db.DBManager;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class CsLoginLogDAO
/*    */ {
/*    */   public static boolean insertLog(LoginLogsBean llb)
/*    */   {
/* 19 */     int id = PublicTableDAO.getIDByTableName(PublicTableDAO.LOGINLOGS_TABLE_NAME);
/* 20 */     llb.setAudit_id(id);
/* 21 */     llb.setAudit_time(DateUtil.getCurrentDateTime());
/* 22 */     return DBManager.insert("insert_LogLogin", llb);
/*    */   }
/*    */ 
/*    */   public static boolean deleteLog(Map<String, String> mp)
/*    */   {
/* 31 */     return DBManager.delete("delete_LogLogin", mp);
/*    */   }
/*    */ 
/*    */   public static boolean updateLog(LoginLogsBean llb)
/*    */   {
/* 40 */     return DBManager.update("update_LogLogin", llb);
/*    */   }
/*    */ 
/*    */   public static List<LoginLogsBean> searchLog(Map<String, String> mp)
/*    */   {
/* 50 */     return DBManager.queryFList("searchLogLoginBean", mp);
/*    */   }
/*    */ 
/*    */   public static String searchLogCnt(Map<String, String> mp)
/*    */   {
/* 59 */     return (String)DBManager.queryFObj("searchLogLoginBeanCnt", mp);
/*    */   }
/*    */ 
/*    */   public static LoginLogsBean getLog(String audit_id)
/*    */   {
/* 68 */     return (LoginLogsBean)DBManager.queryFObj("getLogLoginBean", audit_id);
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.dao.logs.CsLoginLogDAO
 * JD-Core Version:    0.6.2
 */