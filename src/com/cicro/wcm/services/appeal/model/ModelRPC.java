/*     */ package com.cicro.wcm.services.appeal.model;
/*     */ 
/*     */ import com.cicro.wcm.bean.appeal.cpDept.CpDeptBean;
/*     */ import com.cicro.wcm.bean.appeal.cpLead.CpLeadBean;
/*     */ import com.cicro.wcm.bean.appeal.model.CPFrom;
/*     */ import com.cicro.wcm.bean.appeal.model.ModelBean;
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.services.Log.LogManager;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ 
/*     */ public class ModelRPC
/*     */ {
/*     */   public static List<ModelBean> getAllSQModelList()
/*     */   {
/*  25 */     return ModelManager.getAllSQModelList();
/*     */   }
/*     */ 
/*     */   public static List<CpDeptBean> getAppealDeptList(String model_id)
/*     */   {
/*  36 */     return ModelManager.getModelDeptListByMID(Integer.parseInt(model_id));
/*     */   }
/*     */ 
/*     */   public static String getSQModelCount(Map<String, String> m)
/*     */   {
/*  46 */     return ModelManager.getSQModelCount(m);
/*     */   }
/*     */ 
/*     */   public static List<ModelBean> getSQModelListForDB(Map<String, String> m)
/*     */   {
/*  56 */     return ModelManager.getSQModelListForDB(m);
/*     */   }
/*     */ 
/*     */   public static ModelBean getModelBean(int Model_id)
/*     */   {
/*  66 */     return ModelManager.getModelBean(Model_id);
/*     */   }
/*     */ 
/*     */   public static int getModelID()
/*     */   {
/*  76 */     return ModelManager.getModelID();
/*     */   }
/*     */ 
/*     */   public static boolean insertModel(ModelBean ob, String dept_ids, HttpServletRequest request)
/*     */   {
/*  87 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/*  88 */     if (stl != null)
/*     */     {
/*  90 */       return ModelManager.insertModel(ob, dept_ids, stl);
/*     */     }
/*  92 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateModel(ModelBean ob, String dept_ids, HttpServletRequest request)
/*     */   {
/* 105 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 106 */     if (stl != null)
/*     */     {
/* 108 */       return ModelManager.updateModel(ob, dept_ids, stl);
/*     */     }
/* 110 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteModel(String model_ids, HttpServletRequest request)
/*     */   {
/* 120 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 121 */     if (stl != null)
/*     */     {
/* 123 */       return ModelManager.deleteModel(model_ids, stl);
/*     */     }
/* 125 */     return false;
/*     */   }
/*     */ 
/*     */   public static Map<String, String> getModelDeptMapByMID(int model_id)
/*     */   {
/* 137 */     return ModelManager.getModelDeptMapByMID(model_id);
/*     */   }
/*     */ 
/*     */   public static Map<String, String> getModelLeadMapByMID(int model_id)
/*     */   {
/* 147 */     return ModelManager.getModelLeadMapByMID(model_id);
/*     */   }
/*     */ 
/*     */   public static List<CpLeadBean> getModelLeadListByMID(int model_id)
/*     */   {
/* 157 */     return ModelManager.getModelLeadListByMID(model_id);
/*     */   }
/*     */ 
/*     */   public static String getModelDeptIDSByMID(int model_id)
/*     */   {
/* 169 */     return ModelManager.getModelDeptIDSByMID(model_id);
/*     */   }
/*     */ 
/*     */   public static String getModelUserIDSByMID(int model_id)
/*     */   {
/* 181 */     return ModelManager.getModelUserIDSByMID(model_id);
/*     */   }
/*     */ 
/*     */   public static Map<String, String> getModelUserMapByMID(int model_id)
/*     */   {
/* 191 */     return ModelManager.getModelUserMapByMID(model_id);
/*     */   }
/*     */ 
/*     */   public static boolean insertModelReleUser(String model_ids, int user_id, HttpServletRequest request)
/*     */   {
/* 201 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 202 */     if (stl != null)
/*     */     {
/* 204 */       return ModelManager.insertModelReleUser(model_ids, user_id, stl);
/*     */     }
/* 206 */     return false;
/*     */   }
/*     */ 
/*     */   public static List<ModelBean> getModelListSByUserID(int user_id)
/*     */   {
/* 217 */     return ModelManager.getModelListSByUserID(user_id);
/*     */   }
/*     */ 
/*     */   public static boolean insertModelReleUserByModel(int model_id, String user_ids, HttpServletRequest request)
/*     */   {
/* 227 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 228 */     if (stl != null)
/*     */     {
/* 230 */       return ModelManager.insertModelReleUserByModel(model_id, user_ids, stl);
/*     */     }
/* 232 */     return false;
/*     */   }
/*     */ 
/*     */   public static List<CPFrom> getCPFormListByModel(int model_id)
/*     */   {
/* 239 */     return CPFromManager.getCPFormListByModel(model_id);
/*     */   }
/*     */ 
/*     */   public static boolean insertCPFrom(int model_id, List<CPFrom> l, HttpServletRequest request)
/*     */   {
/* 244 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 245 */     if (stl != null)
/*     */     {
/* 247 */       return CPFromManager.insertCPFrom(model_id, l, stl);
/*     */     }
/* 249 */     return false;
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.appeal.model.ModelRPC
 * JD-Core Version:    0.6.2
 */