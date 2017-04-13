/*     */ package com.cicro.wcm.services.model.services;
/*     */ 
/*     */ import com.cicro.wcm.dao.PublicTableDAO;
/*     */ import com.cicro.wcm.db.DBManager;
/*     */ import com.cicro.wcm.services.model.Fields;
/*     */ import java.io.PrintStream;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class FormDao
/*     */ {
/*  23 */   private static String FIELD_TABLE_NAME = "cs_form_data";
/*     */ 
/*     */   public static List<Fields> getFormFieldsListByModelId(Map map)
/*     */   {
/*  31 */     return DBManager.queryFList("form.getFormFieldsListByModelId", map);
/*     */   }
/*     */ 
/*     */   public static List<Fields> getFormFieldsListByModelIdN(Map map)
/*     */   {
/*  40 */     return DBManager.queryFList("form.getFormFieldsListByModelIdN", map);
/*     */   }
/*     */ 
/*     */   public static int getFormFieldsListByModelIdCount(Map map)
/*     */   {
/*  49 */     return Integer.valueOf(((Integer)DBManager.queryFObj("form.getFormFieldsListByModelIdCount", map)).intValue()).intValue();
/*     */   }
/*     */ 
/*     */   public static int getFormFieldsListByFromIdsCount(String from_ids)
/*     */   {
/*  58 */     Map map = new HashMap();
/*  59 */     map.put("from_ids", from_ids);
/*  60 */     return Integer.valueOf(((Integer)DBManager.queryFObj("form.getFormFieldsListByFromIdsCount", map)).intValue()).intValue();
/*     */   }
/*     */ 
/*     */   public static boolean deleteFormFieldsByModeIdAndFromId(String ids, String model_ids)
/*     */   {
/*  66 */     Map map = new HashMap();
/*  67 */     map.put("from_ids", ids);
/*  68 */     map.put("model_id", model_ids);
/*  69 */     System.out.println("deleteFormFieldsByModeIdAndFromId map :: " + map);
/*  70 */     return DBManager.delete("form.deleteFormFieldsByModeIdAndFromId", map);
/*     */   }
/*     */ 
/*     */   public static boolean addFields(Fields fields)
/*     */   {
/*  79 */     fields.setId(PublicTableDAO.getIDByTableName(FIELD_TABLE_NAME));
/*     */ 
/*  81 */     return DBManager.insert("form.addFormFields", fields);
/*     */   }
/*     */ 
/*     */   public static boolean saveFormSort(String ids)
/*     */   {
/*  88 */     if ((ids != null) && (!"".equals(ids))) {
/*     */       try
/*     */       {
/*  91 */         Map m = new HashMap();
/*  92 */         String[] tempA = ids.split(",");
/*  93 */         for (int i = 0; i < tempA.length; i++)
/*     */         {
/*  95 */           m.put("field_sort", Integer.valueOf(i + 1));
/*  96 */           m.put("id", tempA[i]);
/*  97 */           DBManager.update("form.sortFormFields", m);
/*     */         }
/*  99 */         return true;
/*     */       }
/*     */       catch (Exception e) {
/* 102 */         e.printStackTrace();
/* 103 */         return false;
/*     */       }
/*     */     }
/*     */ 
/* 107 */     return true;
/*     */   }
/*     */ 
/*     */   public static Fields getFormFieldById(String id)
/*     */   {
/* 113 */     Map map = new HashMap();
/* 114 */     map.put("id", id);
/* 115 */     return (Fields)DBManager.queryFObj("form.getFormFieldById", map);
/*     */   }
/*     */ 
/*     */   public static boolean updateFormFieldsById(Fields fields)
/*     */   {
/* 125 */     return DBManager.update("form.updateFormFieldsById", fields);
/*     */   }
/*     */ 
/*     */   public static boolean deleteFormFields(String ids)
/*     */   {
/* 130 */     Map map = new HashMap();
/* 131 */     map.put("ids", ids);
/* 132 */     return DBManager.delete("form.deleteFormFields", map);
/*     */   }
/*     */ 
/*     */   public static boolean updateFormFieldFlag(String field_flag, String action, String id)
/*     */   {
/* 137 */     Map map = new HashMap();
/* 138 */     map.put("id", id);
/* 139 */     map.put("field_flag", field_flag);
/* 140 */     map.put("action", action);
/* 141 */     return DBManager.delete("form.updateFormFieldFlag", map);
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.model.services.FormDao
 * JD-Core Version:    0.6.2
 */