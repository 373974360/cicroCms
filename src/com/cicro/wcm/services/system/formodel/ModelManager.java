/*     */ package com.cicro.wcm.services.system.formodel;
/*     */ 
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.bean.system.formodel.ModelBean;
/*     */ import com.cicro.wcm.catchs.ISyncCatch;
/*     */ import com.cicro.wcm.catchs.SyncCatchHandl;
/*     */ import com.cicro.wcm.dao.system.formodel.ModelDAO;
/*     */ import com.cicro.wcm.services.model.services.FormUtil;
/*     */ import java.io.PrintStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.Set;
/*     */ import java.util.TreeMap;
/*     */ 
/*     */ public class ModelManager
/*     */   implements ISyncCatch
/*     */ {
/*  31 */   private static TreeMap<Integer, ModelBean> md_map = new TreeMap();
/*     */ 
/*     */   static {
/*  34 */     reloadCatchHandl();
/*     */   }
/*     */ 
/*     */   public void reloadCatch()
/*     */   {
/*  39 */     reloadCatchHandl();
/*     */   }
/*     */ 
/*     */   public static void reloadCatchHandl()
/*     */   {
/*  44 */     List model_list = ModelDAO.getAllModelList();
/*  45 */     md_map.clear();
/*  46 */     if ((model_list != null) && (model_list.size() > 0))
/*     */     {
/*  48 */       for (int i = 0; i < model_list.size(); i++)
/*     */       {
/*  50 */         md_map.put(Integer.valueOf(((ModelBean)model_list.get(i)).getModel_id()), (ModelBean)model_list.get(i));
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void reloadModel()
/*     */   {
/*  63 */     SyncCatchHandl.reladCatchForRMI("com.cicro.wcm.services.system.formodel.ModelManager");
/*     */   }
/*     */ 
/*     */   public static Map<Integer, ModelBean> getModelMap()
/*     */   {
/*  68 */     return md_map;
/*     */   }
/*     */ 
/*     */   public static List<ModelBean> getModelList()
/*     */   {
/*  80 */     List m_list = new ArrayList();
/*  81 */     Iterator iter = md_map.entrySet().iterator();
/*  82 */     while (iter.hasNext()) {
/*  83 */       Entry entry = (Entry)iter.next();
/*  84 */       m_list.add((ModelBean)entry.getValue());
/*     */     }
/*  86 */     Collections.sort(m_list, new ModelManager.ModelComparator());
/*  87 */     return m_list;
/*     */   }
/*     */ 
/*     */   public static List<ModelBean> getCANModelList()
/*     */   {
/*  99 */     List m_list = new ArrayList();
/* 100 */     Iterator iter = md_map.entrySet().iterator();
/* 101 */     while (iter.hasNext()) {
/* 102 */       Entry entry = (Entry)iter.next();
/* 103 */       ModelBean mb = (ModelBean)entry.getValue();
/* 104 */       if (mb.getDisabled() == 0)
/* 105 */         m_list.add(mb);
/*     */     }
/* 107 */     Collections.sort(m_list, new ModelManager.ModelComparator());
/* 108 */     return m_list;
/*     */   }
/*     */ 
/*     */   public static List<ModelBean> getCANModelListByAppID(String app_id)
/*     */   {
/* 120 */     List m_list = new ArrayList();
/* 121 */     Iterator iter = md_map.entrySet().iterator();
/* 122 */     while (iter.hasNext()) {
/* 123 */       Entry entry = (Entry)iter.next();
/* 124 */       ModelBean mb = (ModelBean)entry.getValue();
/* 125 */       if ((mb.getDisabled() == 0) && (app_id.trim().equals(mb.getApp_id().trim())))
/* 126 */         m_list.add(mb);
/*     */     }
/* 128 */     Collections.sort(m_list, new ModelManager.ModelComparator());
/* 129 */     return m_list;
/*     */   }
/*     */ 
/*     */   public static ModelBean getModelBean(int model_id)
/*     */   {
/* 139 */     if (md_map.containsKey(Integer.valueOf(model_id))) {
/* 140 */       return (ModelBean)md_map.get(Integer.valueOf(model_id));
/*     */     }
/*     */ 
/* 143 */     ModelBean mb = ModelDAO.getModelBean(model_id);
/* 144 */     if (mb != null)
/*     */     {
/* 146 */       md_map.put(Integer.valueOf(model_id), mb);
/* 147 */       return mb;
/*     */     }
/* 149 */     return null;
/*     */   }
/*     */ 
/*     */   public static ModelBean getModelBeanByEName(String model_ename)
/*     */   {
/* 160 */     Set s = md_map.keySet();
/* 161 */     for (Iterator localIterator = s.iterator(); localIterator.hasNext(); ) { int i = ((Integer)localIterator.next()).intValue();
/*     */ 
/* 163 */       ModelBean mb = (ModelBean)md_map.get(Integer.valueOf(i));
/* 164 */       System.out.println(mb.getModel_ename());
/* 165 */       if (mb.getModel_ename().equals(model_ename))
/*     */       {
/* 167 */         return mb;
/*     */       }
/*     */     }
/* 170 */     return null;
/*     */   }
/*     */ 
/*     */   public static String getModelAddPage(int model_id)
/*     */   {
/* 182 */     ModelBean m = getModelBean(model_id);
/* 183 */     if (m != null) {
/* 184 */       return m.getAdd_page();
/*     */     }
/* 186 */     return "";
/*     */   }
/*     */ 
/*     */   public static String getModelEName(int model_id)
/*     */   {
/* 197 */     ModelBean m = getModelBean(model_id);
/* 198 */     if (m != null) {
/* 199 */       return m.getModel_ename();
/*     */     }
/* 201 */     return "";
/*     */   }
/*     */ 
/*     */   public static String getModelViewPage(int model_id)
/*     */   {
/* 212 */     ModelBean m = getModelBean(model_id);
/* 213 */     if (m != null) {
/* 214 */       return m.getView_page();
/*     */     }
/* 216 */     return "";
/*     */   }
/*     */ 
/*     */   public static boolean insertModel(ModelBean mb, SettingLogsBean stl)
/*     */   {
/* 227 */     if (ModelDAO.insertModel(mb, stl))
/*     */     {
/* 231 */       FormUtil.createTable(mb.getTable_name().toLowerCase());
/*     */ 
/* 233 */       reloadCatchHandl();
/* 234 */       reloadModel();
/* 235 */       return true;
/*     */     }
/* 237 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateModel(ModelBean mb, SettingLogsBean stl)
/*     */   {
/* 247 */     if (ModelDAO.updateModel(mb, stl))
/*     */     {
/* 249 */       reloadModel();
/* 250 */       return true;
/*     */     }
/* 252 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteModel(String model_ids, SettingLogsBean stl)
/*     */   {
/* 263 */     String[] id = model_ids.split(",");
/* 264 */     StringBuffer sb = new StringBuffer();
/* 265 */     for (int i = 0; i < id.length; i++) {
/* 266 */       String s_id = id[i];
/* 267 */       if ((s_id != null) && (!"".equals(s_id))) {
/* 268 */         ModelBean mb = getModelBean(Integer.valueOf(s_id).intValue());
/* 269 */         if (mb != null) {
/* 270 */           ModelDAO.deleteModel(s_id, stl);
/*     */ 
/* 273 */           FormUtil.dropTable(mb.getTable_name().toLowerCase());
/*     */         }
/*     */       }
/*     */     }
/* 277 */     reloadCatchHandl();
/* 278 */     reloadModel();
/*     */ 
/* 287 */     return true;
/*     */   }
/*     */ 
/*     */   public static boolean saveModelSort(String model_ids, SettingLogsBean stl)
/*     */   {
/* 298 */     if (ModelDAO.saveModelSort(model_ids, stl))
/*     */     {
/* 300 */       reloadModel();
/* 301 */       return true;
/*     */     }
/* 303 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateModelDisabled(String model_ids, String disabled, SettingLogsBean stl)
/*     */   {
/* 314 */     if (ModelDAO.updateModelDisabled(model_ids, disabled, stl))
/*     */     {
/* 316 */       reloadModel();
/* 317 */       return true;
/*     */     }
/* 319 */     return false;
/*     */   }
/*     */ 
/*     */   public static void sortModelList(List<ModelBean> m_list)
/*     */   {
/* 324 */     Collections.sort(m_list, new ModelManager.ModelComparator());
/*     */   }
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/* 346 */     System.out.println(getModelBeanByEName("infoLink"));
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.system.formodel.ModelManager
 * JD-Core Version:    0.6.2
 */