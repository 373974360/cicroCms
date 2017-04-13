/*    */ package com.cicro.wcm.services.system.error;
/*    */ 
/*    */ import com.cicro.wcm.bean.system.error.ErrorReportBean;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ 
/*    */ public class ErrorReportRPC
/*    */ {
/*    */   public static List<ErrorReportBean> getErrorReportList(Map map)
/*    */   {
/* 20 */     return ErrorReportService.getErrorReportList(map);
/*    */   }
/*    */ 
/*    */   public static int getErrorReportListCount(Map map)
/*    */   {
/* 29 */     return ErrorReportService.getErrorReportListCount(map);
/*    */   }
/*    */ 
/*    */   public static boolean addErrorReport(ErrorReportBean errorReportBean, HttpServletRequest request)
/*    */   {
/* 38 */     return ErrorReportService.addErrorReport(errorReportBean, request);
/*    */   }
/*    */ 
/*    */   public static boolean deleteErrorReports(String obtids)
/*    */   {
/* 49 */     return ErrorReportService.deleteErrorReports(obtids);
/*    */   }
/*    */ 
/*    */   public static ErrorReportBean getErrorReportById(int id)
/*    */   {
/* 58 */     return ErrorReportService.getErrorReportById(id);
/*    */   }
/*    */ 
/*    */   public static boolean updateErrorReportById(ErrorReportBean errorReportBean)
/*    */   {
/* 67 */     return ErrorReportService.updateErrorReportById(errorReportBean);
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.system.error.ErrorReportRPC
 * JD-Core Version:    0.6.2
 */