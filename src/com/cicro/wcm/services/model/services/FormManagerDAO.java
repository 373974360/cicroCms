/*    */ package com.cicro.wcm.services.model.services;
/*    */ 
/*    */ import com.cicro.wcm.db.DBManager;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class FormManagerDAO
/*    */ {
/*    */   public static boolean updateFieldDesc(String type, Map map)
/*    */   {
/*    */     try
/*    */     {
/* 16 */       if ("addFieldVarchar".equals(type))
/* 17 */         DBManager.update("formMapper.addFieldVarchar", map);
/* 18 */       else if ("addFieldLongtext".equals(type))
/* 19 */         DBManager.update("formMapper.addFieldLongtext", map);
/* 20 */       else if ("addFieldBigint".equals(type)) {
/* 21 */         DBManager.update("formMapper.addFieldBigint", map);
/*    */       }
/*    */ 
/* 24 */       return true;
/*    */     } catch (Exception e) {
/*    */     }
/* 27 */     return false;
/*    */   }
/*    */ 
/*    */   public static boolean updateFieldDescByUpdate(Map map)
/*    */   {
/*    */     try
/*    */     {
/* 36 */       DBManager.update("formMapper.modifyFieldTypeToLongText1", map);
/* 37 */       DBManager.update("formMapper.modifyFieldTypeToLongText2", map);
/* 38 */       DBManager.update("formMapper.modifyFieldTypeToLongText3", map);
/* 39 */       DBManager.update("formMapper.modifyFieldTypeToLongText4", map);
/*    */ 
/* 41 */       return true;
/*    */     }
/*    */     catch (Exception e) {
/* 44 */       e.printStackTrace();
/* 45 */     }return false;
/*    */   }
/*    */ 
/*    */   public static boolean updateFieldDescByUpdateVarchar(Map map)
/*    */   {
/*    */     try
/*    */     {
/* 53 */       DBManager.update("formMapper.modifyFieldTypeToVarchar1", map);
/* 54 */       DBManager.update("formMapper.modifyFieldTypeToVarchar2", map);
/* 55 */       DBManager.update("formMapper.modifyFieldTypeToVarchar3", map);
/* 56 */       DBManager.update("formMapper.modifyFieldTypeToVarchar4", map);
/*    */ 
/* 58 */       return true;
/*    */     }
/*    */     catch (Exception e) {
/* 61 */       e.printStackTrace();
/* 62 */     }return false;
/*    */   }
/*    */ 
/*    */   public static boolean deleteFieldDesc(Map map)
/*    */   {
/*    */     try
/*    */     {
/* 69 */       DBManager.update("formMapper.deleteField", map);
/* 70 */       return true;
/*    */     }
/*    */     catch (Exception e) {
/* 73 */       e.printStackTrace();
/* 74 */     }return false;
/*    */   }
/*    */ 
/*    */   public static boolean createTable(Map map)
/*    */   {
/*    */     try
/*    */     {
/* 85 */       DBManager.update("formMapper.createTable", map);
/* 86 */       return true;
/*    */     } catch (Exception e) {
/* 88 */       e.printStackTrace();
/* 89 */     }return false;
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.model.services.FormManagerDAO
 * JD-Core Version:    0.6.2
 */