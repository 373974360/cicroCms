/*    */ package com.cicro.wcm.services.model.services;
/*    */ 
/*    */ import com.cicro.wcm.dao.PublicTableDAO;
/*    */ import com.cicro.wcm.db.DBManager;
/*    */ import com.cicro.wcm.services.model.Fields;
/*    */ import java.io.PrintStream;
/*    */ import java.util.HashMap;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class FieldsDao
/*    */ {
/* 23 */   private static String FIELD_TABLE_NAME = "cs_field_data";
/*    */ 
/*    */   public static List<Fields> getFieldsList(Map map)
/*    */   {
/* 31 */     return DBManager.queryFList("fields.getFieldsList", map);
/*    */   }
/*    */ 
/*    */   public static int getFieldsListCount(Map map)
/*    */   {
/* 40 */     return Integer.valueOf(((Integer)DBManager.queryFObj("fields.getFieldsListCount", map)).intValue()).intValue();
/*    */   }
/*    */ 
/*    */   public static boolean addFields(Fields fields)
/*    */   {
/* 49 */     fields.setId(PublicTableDAO.getIDByTableName(FIELD_TABLE_NAME));
/* 50 */     System.out.println(fields.toString());
/* 51 */     return DBManager.insert("fields.addFields", fields);
/*    */   }
/*    */ 
/*    */   public static boolean deleteFields(String ids)
/*    */   {
/* 57 */     Map map = new HashMap();
/* 58 */     map.put("ids", ids);
/* 59 */     return DBManager.delete("fields.deleteFields", map);
/*    */   }
/*    */ 
/*    */   public static Fields getFieldById(String id)
/*    */   {
/* 65 */     Map map = new HashMap();
/* 66 */     map.put("id", id);
/* 67 */     return (Fields)DBManager.queryFObj("fields.getFieldById", map);
/*    */   }
/*    */ 
/*    */   public static boolean updateFieldsById(Fields fields)
/*    */   {
/* 77 */     return DBManager.update("fields.updateFieldsById", fields);
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.model.services.FieldsDao
 * JD-Core Version:    0.6.2
 */