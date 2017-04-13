/*     */ package com.cicro.wcm.dao.appeal.model;
/*     */ 
/*     */ import com.cicro.wcm.bean.appeal.model.CPFrom;
/*     */ import com.cicro.wcm.bean.appeal.model.ModelBean;
/*     */ import com.cicro.wcm.bean.appeal.model.ModelReleDept;
/*     */ import com.cicro.wcm.bean.appeal.model.ModelReleUser;
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
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
/*  35 */     return DBManager.queryFList("getAllSQModelList", "");
/*     */   }
/*     */ 
/*     */   public static String getSQModelCount(Map<String, String> m)
/*     */   {
/*  45 */     return DBManager.getString("getSQModelCount", m);
/*     */   }
/*     */ 
/*     */   public static List<ModelBean> getSQModelListForDB(Map<String, String> m)
/*     */   {
/*  56 */     return DBManager.queryFList("getSQModelListForDB", m);
/*     */   }
/*     */ 
/*     */   public static ModelBean getModelBean(int model_id)
/*     */   {
/*  67 */     return (ModelBean)DBManager.queryFObj("getSQModelBean", Integer.valueOf(model_id));
/*     */   }
/*     */ 
/*     */   public static boolean insertModel(ModelBean ob, SettingLogsBean stl)
/*     */   {
/*  77 */     if (DBManager.insert("insert_SQModel", ob)) {
/*  78 */       PublicTableDAO.insertSettingLogs("添加", "诉求业务", ob.getModel_id(), stl);
/*  79 */       return true;
/*     */     }
/*  81 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateModel(ModelBean ob, SettingLogsBean stl)
/*     */   {
/*  92 */     if (DBManager.update("update_SQModel", ob))
/*     */     {
/*  94 */       PublicTableDAO.insertSettingLogs("修改", "诉求业务", ob.getModel_id(), stl);
/*  95 */       return true;
/*     */     }
/*  97 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteModel(String model_ids, SettingLogsBean stl)
/*     */   {
/* 109 */     Map m = new HashMap();
/* 110 */     m.put("model_ids", model_ids);
/* 111 */     if (DBManager.delete("delete_SQModel", m))
/*     */     {
/* 113 */       PublicTableDAO.insertSettingLogs("删除", "诉求业务", model_ids, stl);
/* 114 */       return true;
/*     */     }
/* 116 */     return false;
/*     */   }
/*     */ 
/*     */   public static List<ModelReleDept> getModelReleDeptList()
/*     */   {
/* 128 */     return DBManager.queryFList("getModelReleDeptList", "");
/*     */   }
/*     */ 
/*     */   public static boolean insertModelReleDept(ModelReleDept mrd)
/*     */   {
/* 138 */     return DBManager.insert("insert_SQModel_dept", mrd);
/*     */   }
/*     */ 
/*     */   public static boolean deleteModelReleDept(Map<String, String> m)
/*     */   {
/* 148 */     return DBManager.delete("delete_SQModel_dept", m);
/*     */   }
/*     */ 
/*     */   public static List<ModelReleUser> getModelReleUserList()
/*     */   {
/* 162 */     return DBManager.queryFList("getModelReleUserList", "");
/*     */   }
/*     */ 
/*     */   public static boolean insertModelReleUser(String model_ids, int user_id, SettingLogsBean stl)
/*     */   {
/* 173 */     Map m = new HashMap();
/* 174 */     m.put("user_ids", user_id);
/* 175 */     if (deleteModelReleUser(m))
/*     */     {
/* 177 */       if ((model_ids != null) && (!"".equals(model_ids))) {
/*     */         try
/*     */         {
/* 180 */           String[] model_tempA = model_ids.split(",");
/* 181 */           ModelReleUser mru = new ModelReleUser();
/* 182 */           mru.setUser_id(user_id);
/* 183 */           for (int i = 0; i < model_tempA.length; i++)
/*     */           {
/* 185 */             mru.setModel_id(Integer.parseInt(model_tempA[i]));
/* 186 */             DBManager.insert("insert_SQModel_user", mru);
/*     */           }
/* 188 */           PublicTableDAO.insertSettingLogs("添加", "诉求系统用户与业务关联用户", user_id, stl);
/*     */         }
/*     */         catch (Exception e) {
/* 191 */           e.printStackTrace();
/* 192 */           return false;
/*     */         }
/*     */       }
/* 195 */       return true;
/*     */     }
/* 197 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean insertModelReleUserByModel(int model_id, String user_ids, SettingLogsBean stl)
/*     */   {
/* 208 */     Map m = new HashMap();
/* 209 */     m.put("model_ids", model_id);
/* 210 */     if (deleteModelReleUser(m))
/*     */     {
/* 212 */       if ((user_ids != null) && (!"".equals(user_ids))) {
/*     */         try
/*     */         {
/* 215 */           String[] user_tempA = user_ids.split(",");
/* 216 */           ModelReleUser mru = new ModelReleUser();
/* 217 */           mru.setModel_id(model_id);
/* 218 */           for (int i = 0; i < user_tempA.length; i++)
/*     */           {
/* 220 */             mru.setUser_id(Integer.parseInt(user_tempA[i]));
/* 221 */             DBManager.insert("insert_SQModel_user", mru);
/*     */           }
/* 223 */           PublicTableDAO.insertSettingLogs("添加", "诉求系统用户与业务关联 业务", model_id, stl);
/*     */         }
/*     */         catch (Exception e) {
/* 226 */           e.printStackTrace();
/* 227 */           return false;
/*     */         }
/*     */       }
/* 230 */       return true;
/*     */     }
/* 232 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteModelReleUser(Map<String, String> m)
/*     */   {
/* 242 */     return DBManager.delete("delete_SQModel_user", m);
/*     */   }
/*     */ 
/*     */   public static List<CPFrom> getAllCPFormList()
/*     */   {
/* 250 */     return DBManager.queryFList("getAllCPFormList", "");
/*     */   }
/*     */ 
/*     */   public static List<CPFrom> getCPFormListByModel(int model_id)
/*     */   {
/* 255 */     return DBManager.queryFList("getCPFormListByModel", Integer.valueOf(model_id));
/*     */   }
/*     */ 
/*     */   public static boolean insertCPFrom(int model_id, List<CPFrom> l, SettingLogsBean stl)
/*     */   {
/* 260 */     if (deleteCPFrom(model_id))
/*     */     {
/* 262 */       if ((l != null) && (l.size() > 0)) {
/*     */         try
/*     */         {
/* 265 */           for (int i = 0; i < l.size(); i++)
/*     */           {
/* 267 */             ((CPFrom)l.get(i)).setField_id(i + 1);
/* 268 */             ((CPFrom)l.get(i)).setModel_id(model_id);
/* 269 */             DBManager.insert("insert_cp_from", l.get(i));
/*     */           }
/*     */         }
/*     */         catch (Exception e) {
/* 273 */           e.printStackTrace();
/* 274 */           return false;
/*     */         }
/*     */       }
/* 277 */       PublicTableDAO.insertSettingLogs("修改", "诉求系统扩展字段 业务", model_id, stl);
/* 278 */       return true;
/*     */     }
/* 280 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteCPFrom(String model_ids)
/*     */   {
/* 285 */     Map m = new HashMap();
/* 286 */     m.put("model_id", model_ids);
/* 287 */     return DBManager.delete("delete_cp_from", m);
/*     */   }
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/* 293 */     Map m = new HashMap();
/* 294 */     m.put("model_ids", "1,2");
/* 295 */     m.put("dept_ids", "1,2");
/* 296 */     deleteModelReleDept(m);
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.dao.appeal.model.ModelDAO
 * JD-Core Version:    0.6.2
 */