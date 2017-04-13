/*     */ package com.cicro.wcm.dao.system.formodel;
/*     */ 
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.bean.system.formodel.ModelBean;
/*     */ import com.cicro.wcm.dao.PublicTableDAO;
/*     */ import com.cicro.wcm.db.DBManager;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class ModelDAO
/*     */ {
/*     */   public static List<ModelBean> getAllModelList()
/*     */   {
/*  32 */     return DBManager.queryFList("getAllFormModelList", "");
/*     */   }
/*     */ 
/*     */   public static ModelBean getModelBean(int model_id)
/*     */   {
/*  43 */     return (ModelBean)DBManager.queryFObj("getModelBean", model_id);
/*     */   }
/*     */ 
/*     */   public static boolean insertModel(ModelBean mb, SettingLogsBean stl)
/*     */   {
/*  53 */     int model_id = PublicTableDAO.getIDByTableName(PublicTableDAO.FORMODEL_TABLE_NAME);
/*  54 */     mb.setModel_id(model_id);
/*  55 */     if (DBManager.insert("insert_formModel", mb))
/*     */     {
/*  57 */       PublicTableDAO.insertSettingLogs("添加", "内容模型", model_id, stl);
/*  58 */       return true;
/*     */     }
/*  60 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateModel(ModelBean mb, SettingLogsBean stl)
/*     */   {
/*  70 */     if (DBManager.update("update_formModel", mb))
/*     */     {
/*  72 */       PublicTableDAO.insertSettingLogs("修改", "内容模型", mb.getModel_id(), stl);
/*  73 */       return true;
/*     */     }
/*  75 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteModel(String model_ids, SettingLogsBean stl)
/*     */   {
/*  85 */     Map m = new HashMap();
/*  86 */     m.put("model_ids", model_ids);
/*  87 */     if (DBManager.delete("delete_formModel", m))
/*     */     {
/*  89 */       PublicTableDAO.insertSettingLogs("删除", "内容模型", model_ids, stl);
/*  90 */       return true;
/*     */     }
/*  92 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateModelDisabled(String model_ids, String disabled, SettingLogsBean stl)
/*     */   {
/* 103 */     Map m = new HashMap();
/* 104 */     m.put("model_ids", model_ids);
/* 105 */     m.put("disabled", disabled);
/* 106 */     if (DBManager.delete("update_model_disabled", m))
/*     */     {
/* 108 */       PublicTableDAO.insertSettingLogs("修改", "内容模型状态", model_ids, stl);
/* 109 */       return true;
/*     */     }
/* 111 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean saveModelSort(String model_ids, SettingLogsBean stl)
/*     */   {
/* 123 */     if ((model_ids != null) && (!"".equals(model_ids))) {
/*     */       try
/*     */       {
/* 126 */         Map m = new HashMap();
/* 127 */         String[] tempA = model_ids.split(",");
/* 128 */         for (int i = 0; i < tempA.length; i++)
/*     */         {
/* 130 */           m.put("model_sort", Integer.valueOf(i + 1));
/* 131 */           m.put("model_id", tempA[i]);
/* 132 */           DBManager.update("sort_model", m);
/*     */         }
/* 134 */         PublicTableDAO.insertSettingLogs("保存排序", "内容模型", model_ids, stl);
/* 135 */         return true;
/*     */       }
/*     */       catch (Exception e) {
/* 138 */         e.printStackTrace();
/* 139 */         return false;
/*     */       }
/*     */     }
/*     */ 
/* 143 */     return true;
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.dao.system.formodel.ModelDAO
 * JD-Core Version:    0.6.2
 */