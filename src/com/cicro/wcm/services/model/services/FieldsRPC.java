/*    */ package com.cicro.wcm.services.model.services;
/*    */ 
/*    */ import com.cicro.wcm.services.model.Fields;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class FieldsRPC
/*    */ {
/*    */   public static List<Fields> getFieldsList(Map map)
/*    */   {
/* 27 */     return FieldsService.getFieldsList(map);
/*    */   }
/*    */ 
/*    */   public static int getFieldsListCount(Map map)
/*    */   {
/* 37 */     return FieldsService.getFieldsListCount(map);
/*    */   }
/*    */ 
/*    */   public static boolean addFields(Fields fields)
/*    */   {
/* 46 */     return FieldsService.addFields(fields);
/*    */   }
/*    */ 
/*    */   public static boolean deleteFields(String ids)
/*    */   {
/* 51 */     return FieldsService.deleteFields(ids);
/*    */   }
/*    */ 
/*    */   public static Fields getFieldById(String id)
/*    */   {
/* 57 */     return FieldsService.getFieldById(id);
/*    */   }
/*    */ 
/*    */   public static boolean updateFieldsById(Fields fields)
/*    */   {
/* 66 */     return FieldsService.updateFieldsById(fields);
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.model.services.FieldsRPC
 * JD-Core Version:    0.6.2
 */