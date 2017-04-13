/*    */ package com.cicro.wcm.services.model.services;
/*    */ 
/*    */ import com.cicro.wcm.services.model.Fields;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class FormRPC
/*    */ {
/*    */   public static List<Fields> getFormFieldsListByModelId(Map map)
/*    */   {
/* 26 */     return FormService.getFormFieldsListByModelId(map);
/*    */   }
/*    */ 
/*    */   public static List<Fields> getFormFieldsListByModelIdN3(Map map)
/*    */   {
/* 35 */     return FormService.getFormFieldsListByModelIdN3(map);
/*    */   }
/*    */ 
/*    */   public static List<Fields> getFormFieldsListByModelIdN(Map map)
/*    */   {
/* 44 */     return FormService.getFormFieldsListByModelIdN(map);
/*    */   }
/*    */ 
/*    */   public static int getFormFieldsListByModelIdCount(Map map)
/*    */   {
/* 54 */     return FormService.getFormFieldsListByModelIdCount(map);
/*    */   }
/*    */ 
/*    */   public static boolean updateForm(Map map)
/*    */   {
/* 59 */     return FormService.updateForm(map);
/*    */   }
/*    */ 
/*    */   public static boolean saveFormSort(String ids)
/*    */   {
/* 65 */     return FormService.saveFormSort(ids);
/*    */   }
/*    */ 
/*    */   public static Fields getFormFieldById(String id)
/*    */   {
/* 71 */     return FormService.getFormFieldById(id);
/*    */   }
/*    */ 
/*    */   public static boolean updateFormFieldsById(Fields fields)
/*    */   {
/* 80 */     return FormService.updateFormFieldsById(fields);
/*    */   }
/*    */ 
/*    */   public static boolean deleteFormFields(String ids, String model_id)
/*    */   {
/* 85 */     return FormService.deleteFormFields(ids, model_id);
/*    */   }
/*    */ 
/*    */   public static boolean updateFormFieldFlag(String field_flag, String action, String id)
/*    */   {
/* 91 */     return FormService.updateFormFieldFlag(field_flag, action, id);
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.model.services.FormRPC
 * JD-Core Version:    0.6.2
 */