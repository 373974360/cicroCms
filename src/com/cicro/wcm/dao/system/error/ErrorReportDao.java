/*    */ package com.cicro.wcm.dao.system.error;
/*    */ 
/*    */ import com.cicro.util.DateUtil;
/*    */ import com.cicro.wcm.bean.system.error.ErrorReportBean;
/*    */ import com.cicro.wcm.dao.PublicTableDAO;
/*    */ import com.cicro.wcm.db.DBManager;
/*    */ import java.util.HashMap;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class ErrorReportDao
/*    */ {
/*    */   public static List<ErrorReportBean> getErrorReportList(Map map)
/*    */   {
/* 34 */     return DBManager.queryFList("error_report.getErrorReportList", map);
/*    */   }
/*    */ 
/*    */   public static int getErrorReportListCount(Map map)
/*    */   {
/* 44 */     return Integer.valueOf(((Integer)DBManager.queryFObj("error_report.getErrorReportListCount", map)).intValue()).intValue();
/*    */   }
/*    */ 
/*    */   public static boolean addErrorReport(ErrorReportBean errorReportBean)
/*    */   {
/* 54 */     errorReportBean.setId(PublicTableDAO.getIDByTableName(PublicTableDAO.ERROR_TABLE_NAME));
/*    */ 
/* 57 */     return DBManager.insert("error_report.addErrorReport", errorReportBean);
/*    */   }
/*    */ 
/*    */   public static boolean deleteErrorReports(String ids)
/*    */   {
/* 67 */     Map map = new HashMap();
/* 68 */     map.put("ids", ids.trim());
/* 69 */     return DBManager.delete("error_report.deleteErrorReports", map);
/*    */   }
/*    */ 
/*    */   public static ErrorReportBean getErrorReportById(int id)
/*    */   {
/* 79 */     Map map = new HashMap();
/* 80 */     map.put("id", Integer.valueOf(id));
/* 81 */     return (ErrorReportBean)DBManager.queryFObj("error_report.getErrorReportById", map);
/*    */   }
/*    */ 
/*    */   public static boolean updateErrorReportById(ErrorReportBean errorReportBean)
/*    */   {
/* 90 */     errorReportBean.setO_time(DateUtil.getCurrentDateTime());
/* 91 */     return DBManager.update("error_report.updateErrorReportById", errorReportBean);
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.dao.system.error.ErrorReportDao
 * JD-Core Version:    0.6.2
 */