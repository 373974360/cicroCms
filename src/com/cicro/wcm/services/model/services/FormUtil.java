/*    */ package com.cicro.wcm.services.model.services;
/*    */ 
/*    */ import com.cicro.wcm.db.DBManager;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import java.util.UUID;
/*    */ 
/*    */ public class FormUtil
/*    */ {
/*    */   public static synchronized String getUniqueString()
/*    */   {
/* 29 */     String uniqueNumber = UUID.randomUUID().toString();
/* 30 */     return uniqueNumber;
/*    */   }
/*    */ 
/*    */   public static String formatQuote(String s)
/*    */   {
/* 40 */     if (s == null) {
/* 41 */       return "";
/*    */     }
/* 43 */     return s;
/*    */   }
/*    */ 
/*    */   public static boolean createTable(String tableName)
/*    */   {
/*    */     try
/*    */     {
/* 54 */       Map hmParam = new HashMap();
/* 55 */       hmParam.put("tableName", tableName);
/* 56 */       FormManagerDAO.createTable(hmParam);
/* 57 */       return true;
/*    */     }
/*    */     catch (Exception e) {
/* 60 */       e.printStackTrace();
/* 61 */     }return false;
/*    */   }
/*    */ 
/*    */   public static boolean dropTable(String tableName)
/*    */   {
/*    */     try
/*    */     {
/* 76 */       HashMap hmParam = new HashMap();
/* 77 */       hmParam.put("tableName", tableName);
/* 78 */       DBManager.update("formMapper.dropTable", hmParam);
/*    */ 
/* 80 */       return true;
/*    */     }
/*    */     catch (Exception e) {
/* 83 */       e.printStackTrace();
/* 84 */     }return false;
/*    */   }
/*    */ 
/*    */   public static boolean deleteFieldDesc(Map map)
/*    */   {
/* 92 */     return FormManagerDAO.deleteFieldDesc(map);
/*    */   }
/*    */ 
/*    */   public static boolean updateFieldDesc(String type, Map map)
/*    */   {
/* 98 */     return FormManagerDAO.updateFieldDesc(type, map);
/*    */   }
/*    */ 
/*    */   public static void main(String[] args)
/*    */   {
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.model.services.FormUtil
 * JD-Core Version:    0.6.2
 */