/*    */ package com.cicro.wcm.services.zwgk.index;
/*    */ 
/*    */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*    */ import com.cicro.wcm.bean.zwgk.index.IndexRoleBean;
/*    */ import com.cicro.wcm.services.Log.LogManager;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ 
/*    */ public class IndexRPC
/*    */ {
/*    */   public static Map<String, String> getIndex(String nodeId, String catId, String ymd, String seq)
/*    */   {
/* 26 */     return IndexManager.getIndex(nodeId, catId, ymd, seq);
/*    */   }
/*    */ 
/*    */   public static List<IndexRoleBean> getRoleList()
/*    */   {
/* 35 */     return IndexManager.getRoleList();
/*    */   }
/*    */ 
/*    */   public static boolean updateIndexRole(List<IndexRoleBean> lt, HttpServletRequest request)
/*    */   {
/* 46 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 47 */     if (stl != null)
/*    */     {
/* 49 */       return IndexManager.updateIndexRole(lt, stl);
/*    */     }
/*    */ 
/* 53 */     return false;
/*    */   }
/*    */ 
/*    */   public static boolean resetSequence(String nodeId, String catId, String ymd, HttpServletRequest request)
/*    */   {
/* 67 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 68 */     if (stl != null)
/*    */     {
/* 70 */       return IndexManager.resetSequence(nodeId, catId, ymd, stl);
/*    */     }
/*    */ 
/* 74 */     return false;
/*    */   }
/*    */ 
/*    */   public static void main(String[] args)
/*    */   {
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.zwgk.index.IndexRPC
 * JD-Core Version:    0.6.2
 */