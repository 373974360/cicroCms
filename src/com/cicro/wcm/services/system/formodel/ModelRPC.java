/*     */ package com.cicro.wcm.services.system.formodel;
/*     */ 
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.bean.system.formodel.ModelBean;
/*     */ import com.cicro.wcm.bean.system.formodel.ModelFieldBean;
/*     */ import com.cicro.wcm.services.Log.LogManager;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ 
/*     */ public class ModelRPC
/*     */ {
/*     */   public static Map<Integer, ModelBean> getModelMap()
/*     */   {
/*  16 */     return ModelManager.getModelMap();
/*     */   }
/*     */ 
/*     */   public static List<ModelBean> getModelList()
/*     */   {
/*  27 */     return ModelManager.getModelList();
/*     */   }
/*     */ 
/*     */   public static String getModelEName(int model_id)
/*     */   {
/*  38 */     return ModelManager.getModelEName(model_id);
/*     */   }
/*     */ 
/*     */   public static ModelBean getModelBeanByEName(String model_ename)
/*     */   {
/*  47 */     return ModelManager.getModelBeanByEName(model_ename);
/*     */   }
/*     */ 
/*     */   public static List<ModelBean> getCANModelList()
/*     */   {
/*  57 */     return ModelManager.getCANModelList();
/*     */   }
/*     */ 
/*     */   public static String getModelAddPage(int model_id)
/*     */   {
/*  68 */     return ModelManager.getModelAddPage(model_id);
/*     */   }
/*     */ 
/*     */   public static String getModelViewPage(int model_id)
/*     */   {
/*  79 */     return ModelManager.getModelViewPage(model_id);
/*     */   }
/*     */ 
/*     */   public static List<ModelBean> getCANModelListByAppID(String app_id)
/*     */   {
/*  90 */     return ModelManager.getCANModelListByAppID(app_id);
/*     */   }
/*     */ 
/*     */   public static ModelBean getModelBean(int model_id)
/*     */   {
/* 100 */     return ModelManager.getModelBean(model_id);
/*     */   }
/*     */ 
/*     */   public static boolean insertModel(ModelBean mb, HttpServletRequest request)
/*     */   {
/* 110 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 111 */     if (stl != null)
/*     */     {
/* 113 */       return ModelManager.insertModel(mb, stl);
/*     */     }
/* 115 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateModel(ModelBean mb, HttpServletRequest request)
/*     */   {
/* 125 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 126 */     if (stl != null)
/*     */     {
/* 128 */       return ModelManager.updateModel(mb, stl);
/*     */     }
/* 130 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateModelDisabled(String model_ids, String disabled, HttpServletRequest request)
/*     */   {
/* 141 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 142 */     if (stl != null)
/*     */     {
/* 144 */       return ModelManager.updateModelDisabled(model_ids, disabled, stl);
/*     */     }
/* 146 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteModel(String model_ids, HttpServletRequest request)
/*     */   {
/* 156 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 157 */     if (stl != null)
/*     */     {
/* 159 */       return ModelManager.deleteModel(model_ids, stl);
/*     */     }
/* 161 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean saveModelSort(String model_ids, HttpServletRequest request)
/*     */   {
/* 171 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 172 */     if (stl != null)
/*     */     {
/* 174 */       return ModelManager.saveModelSort(model_ids, stl);
/*     */     }
/* 176 */     return false;
/*     */   }
/*     */ 
/*     */   public static List<ModelFieldBean> getFieldListByModelID(int model_id)
/*     */   {
/* 187 */     return ModelFieldManager.getFieldListByModelID(model_id);
/*     */   }
/*     */ 
/*     */   public static List<ModelFieldBean> getUDefinedFieldsByModelID(int model_id)
/*     */   {
/* 196 */     return ModelFieldManager.getUDefinedFieldsByModelID(model_id);
/*     */   }
/*     */ 
/*     */   public static ModelFieldBean getModelFieldBean(int modelField_id)
/*     */   {
/* 207 */     return ModelFieldManager.getModelFieldBean(modelField_id);
/*     */   }
/*     */ 
/*     */   public static boolean insertModelField(ModelFieldBean mfb, HttpServletRequest request)
/*     */   {
/* 218 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 219 */     if (stl != null)
/*     */     {
/* 221 */       return ModelFieldManager.insertModelField(mfb, stl);
/*     */     }
/* 223 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateModelField(ModelFieldBean mfb, HttpServletRequest request)
/*     */   {
/* 234 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 235 */     if (stl != null)
/*     */     {
/* 237 */       return ModelFieldManager.updateModelField(mfb, stl);
/*     */     }
/* 239 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteModelField(String field_ids, HttpServletRequest request)
/*     */   {
/* 250 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 251 */     if (stl != null)
/*     */     {
/* 253 */       return ModelFieldManager.deleteModelField(field_ids, stl);
/*     */     }
/* 255 */     return false;
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.system.formodel.ModelRPC
 * JD-Core Version:    0.6.2
 */