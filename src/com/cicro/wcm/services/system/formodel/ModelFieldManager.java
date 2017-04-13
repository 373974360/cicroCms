/*     */ package com.cicro.wcm.services.system.formodel;
/*     */ 
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.bean.system.formodel.ModelFieldBean;
/*     */ import com.cicro.wcm.catchs.ISyncCatch;
/*     */ import com.cicro.wcm.catchs.SyncCatchHandl;
/*     */ import com.cicro.wcm.dao.PublicTableDAO;
/*     */ import com.cicro.wcm.dao.system.formodel.ModelFieldDAO;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.Set;
/*     */ import java.util.TreeMap;
/*     */ 
/*     */ public class ModelFieldManager
/*     */   implements ISyncCatch
/*     */ {
/*  27 */   private static TreeMap<Integer, ModelFieldBean> mf_map = new TreeMap();
/*     */ 
/*     */   static {
/*  30 */     reloadCatchHandl();
/*     */   }
/*     */ 
/*     */   public void reloadCatch()
/*     */   {
/*  35 */     reloadCatchHandl();
/*     */   }
/*     */ 
/*     */   public static void reloadCatchHandl()
/*     */   {
/*  40 */     List mf_list = ModelFieldDAO.getAllModelFieldList();
/*  41 */     mf_map.clear();
/*  42 */     if ((mf_list != null) && (mf_list.size() > 0))
/*  43 */       for (int i = 0; i < mf_list.size(); i++)
/*     */       {
/*  45 */         mf_map.put(Integer.valueOf(((ModelFieldBean)mf_list.get(i)).getField_id()), (ModelFieldBean)mf_list.get(i));
/*     */       }
/*     */   }
/*     */ 
/*     */   public static void reloadModelField()
/*     */   {
/*  57 */     SyncCatchHandl.reladCatchForRMI("com.cicro.wcm.services.system.formodel.ModelFieldManager");
/*     */   }
/*     */ 
/*     */   public static List<ModelFieldBean> getFieldListByModelID(int model_id)
/*     */   {
/*  68 */     List mf_list = new ArrayList();
/*  69 */     Iterator iter = mf_map.entrySet().iterator();
/*  70 */     while (iter.hasNext()) {
/*  71 */       Map.Entry entry = (Map.Entry)iter.next();
/*  72 */       ModelFieldBean mfb = (ModelFieldBean)entry.getValue();
/*  73 */       if (mfb.getModel_id() == model_id)
/*  74 */         mf_list.add(mfb);
/*     */     }
/*  76 */     return mf_list;
/*     */   }
/*     */ 
/*     */   public static List<ModelFieldBean> getUDefinedFieldsByModelID(int model_id)
/*     */   {
/*  87 */     List mf_list = new ArrayList();
/*  88 */     Iterator iter = mf_map.entrySet().iterator();
/*  89 */     while (iter.hasNext()) {
/*  90 */       Map.Entry entry = (Map.Entry)iter.next();
/*  91 */       ModelFieldBean mfb = (ModelFieldBean)entry.getValue();
/*     */ 
/*  93 */       if ((mfb.getModel_id() == model_id) && (mfb.getTable_name().equals(PublicTableDAO.INFO_UDEFINED_TABLE_NAME)))
/*  94 */         mf_list.add(mfb);
/*     */     }
/*  96 */     return mf_list;
/*     */   }
/*     */ 
/*     */   public static ModelFieldBean getModelFieldBean(int modelField_id)
/*     */   {
/* 107 */     if (mf_map.containsKey(Integer.valueOf(modelField_id))) {
/* 108 */       return (ModelFieldBean)mf_map.get(Integer.valueOf(modelField_id));
/*     */     }
/* 110 */     ModelFieldBean mfb = ModelFieldDAO.getModelFieldBean(modelField_id);
/* 111 */     if (mfb != null)
/*     */     {
/* 113 */       mf_map.put(Integer.valueOf(modelField_id), mfb);
/* 114 */       return mfb;
/*     */     }
/* 116 */     return null;
/*     */   }
/*     */ 
/*     */   public static boolean insertModelField(ModelFieldBean mfb, SettingLogsBean stl)
/*     */   {
/* 127 */     if (ModelFieldDAO.insertModelField(mfb, stl))
/*     */     {
/* 129 */       reloadModelField();
/* 130 */       return true;
/*     */     }
/*     */ 
/* 133 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateModelField(ModelFieldBean mfb, SettingLogsBean stl)
/*     */   {
/* 143 */     if (ModelFieldDAO.updateModelField(mfb, stl))
/*     */     {
/* 145 */       reloadModelField();
/* 146 */       return true;
/*     */     }
/*     */ 
/* 149 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteModelField(String field_ids, SettingLogsBean stl)
/*     */   {
/* 159 */     if (ModelFieldDAO.deleteModelField(field_ids, stl))
/*     */     {
/* 161 */       reloadModelField();
/* 162 */       return true;
/*     */     }
/*     */ 
/* 165 */     return false;
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.system.formodel.ModelFieldManager
 * JD-Core Version:    0.6.2
 */