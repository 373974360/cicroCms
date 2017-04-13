/*    */ package com.cicro.wcm.services.extendfunction.zdfwly;
/*    */ 
/*    */ import com.cicro.wcm.db.DBManager;
/*    */ import java.io.PrintStream;
/*    */ import java.util.HashMap;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class ZdfwlyInfoDAO
/*    */ {
/*    */   public static String getZdfwlyInfoCount(Map<String, String> m)
/*    */   {
/* 12 */     return DBManager.getString("getZdfwlyInfoCount", m);
/*    */   }
/*    */ 
/*    */   public static List<ZdfwlyInfoBean> getZdfwlyInfoList(Map<String, String> m)
/*    */   {
/* 17 */     return DBManager.queryFList("getZdfwlyInfoList", m);
/*    */   }
/*    */ 
/*    */   public static ZdfwlyInfoBean getZdfwlyInfoBean(String id)
/*    */   {
/* 22 */     Map m = new HashMap();
/* 23 */     m.put("id", id);
/* 24 */     System.out.println(DBManager.queryFObj("getZdfwlyInfoBean", m) + "****************************");
/* 25 */     return (ZdfwlyInfoBean)DBManager.queryFObj("getZdfwlyInfoBean", m);
/*    */   }
/*    */ 
/*    */   public static List<ZdfwlyInfoBean> getAllZdfwlyInfoList()
/*    */   {
/* 30 */     return DBManager.queryFList("getAllZdfwlyInfoList", "");
/*    */   }
/*    */ 
/*    */   public static boolean insertZdfwlyInfo(ZdfwlyInfoBean mb)
/*    */   {
/* 36 */     return DBManager.insert("insertZdfwlyInfo", mb);
/*    */   }
/*    */ 
/*    */   public static boolean updateZdfwlyInfo(ZdfwlyInfoBean mb)
/*    */   {
/* 41 */     if (DBManager.update("updateZdfwlyInfo", mb))
/*    */     {
/* 43 */       return true;
/*    */     }
/* 45 */     return false;
/*    */   }
/*    */ 
/*    */   public static boolean publishZdfwlyInfo(Map<String, String> m)
/*    */   {
/* 50 */     if (DBManager.update("publishZdfwlyInfo", m))
/*    */     {
/* 52 */       return true;
/*    */     }
/* 54 */     return false;
/*    */   }
/*    */ 
/*    */   public static boolean deleteZdfwlyInfo(Map<String, String> m)
/*    */   {
/* 59 */     if (DBManager.delete("deleteZdfwlyInfo", m))
/*    */     {
/* 61 */       return true;
/*    */     }
/* 63 */     return false;
/*    */   }
/*    */ }

/* Location:           E:\Xshell\61.150.72.149(渭南96)\com.zip
 * Qualified Name:     com.cicro.project.wzjc.JdyDAO
 * JD-Core Version:    0.6.2
 */