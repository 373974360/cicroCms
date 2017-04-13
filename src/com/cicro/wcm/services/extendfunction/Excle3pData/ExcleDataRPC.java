/*    */ package com.cicro.wcm.services.extendfunction.Excle3pData;
/*    */ 
/*    */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*    */ import com.cicro.wcm.services.Log.LogManager;
/*    */ import java.util.Map;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ 
/*    */ public class ExcleDataRPC
/*    */ {
/*    */   public static boolean writeExcelData(String file_name, String site_id, HttpServletRequest request)
/*    */   {
/* 20 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 21 */     if (stl != null)
/*    */     {
/* 23 */       return ExcleDataManager.writeExcelData(file_name, site_id, stl);
/*    */     }
/* 25 */     return false;
/*    */   }
/*    */ 
/*    */   public static boolean deleteExcleInfo(Map<String, String> map, HttpServletRequest request)
/*    */   {
/* 31 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 32 */     if (stl != null) {
/* 33 */       return ExcleDataManager.deleteExcleInfo(map, stl);
/*    */     }
/* 35 */     return false;
/*    */   }
/*    */ }

/* Location:           E:\公司产品包\定制包\extendfunction.zip
 * Qualified Name:     extendfunction.Excle3pData.ExcleDataRPC
 * JD-Core Version:    0.6.2
 */