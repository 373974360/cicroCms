/*     */ package com.cicro.wcm.services.cms.category;
/*     */ 
/*     */ import com.cicro.wcm.bean.cms.category.CategoryBean;
/*     */ import com.cicro.wcm.bean.cms.category.CategoryModel;
/*     */ import com.cicro.wcm.bean.system.formodel.ModelBean;
/*     */ import com.cicro.wcm.catchs.ISyncCatch;
/*     */ import com.cicro.wcm.catchs.SyncCatchHandl;
/*     */ import com.cicro.wcm.dao.cms.category.CategoryModelDAO;
/*     */ import com.cicro.wcm.services.system.formodel.ModelManager;
/*     */ import java.io.PrintStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class CategoryModelManager
/*     */   implements ISyncCatch
/*     */ {
/*  28 */   private static Map<String, List<CategoryModel>> cmm = new HashMap();
/*     */ 
/*     */   static {
/*  31 */     reloadCatchHandl();
/*     */   }
/*     */ 
/*     */   public void reloadCatch()
/*     */   {
/*  36 */     reloadCatchHandl();
/*     */   }
/*     */ 
/*     */   public static void reloadCatchHandl()
/*     */   {
/*  41 */     cmm.clear();
/*     */     try {
/*  43 */       List l = CategoryModelDAO.getCategoryReleModelList();
/*  44 */       if ((l != null) && (l.size() > 0))
/*     */       {
/*  46 */         String temp_key = "";
/*  47 */         for (int i = 0; i < l.size(); i++)
/*     */         {
/*  49 */           temp_key = ((CategoryModel)l.get(i)).getCat_id() + "_" + ((CategoryModel)l.get(i)).getSite_id();
/*     */ 
/*  51 */           if (cmm.containsKey(temp_key))
/*     */           {
/*  53 */             ((List)cmm.get(temp_key)).add((CategoryModel)l.get(i));
/*     */           }
/*     */           else {
/*  56 */             List map_list = new ArrayList();
/*  57 */             map_list.add((CategoryModel)l.get(i));
/*  58 */             cmm.put(temp_key, map_list);
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     catch (Exception e) {
/*  64 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void reloadCategoryModel()
/*     */   {
/*  70 */     SyncCatchHandl.reladCatchForRMI("com.cicro.wcm.services.cms.category.CategoryModelManager");
/*     */   }
/*     */ 
/*     */   public static String getTemplateID(String cat_id, String site_id, int model_id)
/*     */   {
/*  80 */     String tid = "";
/*  81 */     if (("10".equals(cat_id)) || ("11".equals(cat_id)) || ("12".equals(cat_id)))
/*     */     {
/*  83 */       site_id = "GK";
/*  84 */       tid = getTemplateIDHandl(cat_id, site_id, model_id);
/*     */     }
/*     */     else {
/*  87 */       tid = getTemplateIDHandl(cat_id, site_id, model_id);
/*     */     }
/*  89 */     if ("0".equals(tid))
/*     */     {
/*  92 */       CategoryBean cb = CategoryManager.getCategoryBeanCatID(Integer.parseInt(cat_id), site_id);
/*  93 */       String root_tree_id = cb.getCat_position().split("\\$")[2];
/*  94 */       CategoryBean root_cb = CategoryManager.getCategoryBeanCatID(Integer.parseInt(root_tree_id), site_id);
/*     */ 
/*  96 */       if (root_cb.getCat_type() == 1)
/*     */       {
/*  98 */         tid = getTemplateIDHandl(root_cb.getCat_id(), site_id, model_id);
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 124 */     return tid;
/*     */   }
/*     */ 
/*     */   public static String getTemplateIDHandl(String cat_id, String site_id, int model_id)
/*     */   {
/* 129 */     List l = getCategoryReleModelList(cat_id, site_id);
/* 130 */     if ((l != null) && (l.size() > 0))
/*     */     {
/* 132 */       for (CategoryModel c : l)
/*     */       {
/* 134 */         if (c.getModel_id() == model_id)
/* 135 */           return c.getTemplate_content();
/*     */       }
/*     */     }
/* 138 */     return "0";
/*     */   }
/*     */ 
/*     */   public static List<ModelBean> getCateReleModelBeanList(String cat_id, String site_id)
/*     */   {
/* 149 */     List mb_list = new ArrayList();
/* 150 */     List l = getCategoryReleModelList(cat_id, site_id);
/* 151 */     if ((l != null) && (l.size() > 0))
/*     */     {
/* 153 */       for (CategoryModel cm : l)
/*     */       {
/* 155 */         ModelBean mb = ModelManager.getModelBean(cm.getModel_id());
/* 156 */         if (mb.getDisabled() == 0)
/* 157 */           mb_list.add(mb);
/*     */       }
/*     */     }
/* 160 */     ModelManager.sortModelList(mb_list);
/* 161 */     return mb_list;
/*     */   }
/*     */ 
/*     */   public static List<CategoryModel> getCategoryReleModelList(String cat_id, String site_id)
/*     */   {
/* 174 */     String temp_key = "";
/* 175 */     CategoryBean cb = CategoryManager.getCategoryBeanCatID(Integer.parseInt(cat_id), site_id);
/* 176 */     if (cb != null)
/*     */     {
/* 178 */       temp_key = cb.getId() + "_" + site_id;
/*     */ 
/* 180 */       if (cmm.containsKey(temp_key)) {
/* 181 */         return (List)cmm.get(temp_key);
/*     */       }
/*     */ 
/* 184 */       temp_key = cb.getCat_id() + "_" + site_id;
/* 185 */       if (cmm.containsKey(temp_key)) {
/* 186 */         return (List)cmm.get(temp_key);
/*     */       }
/* 188 */       return null;
/*     */     }
/*     */ 
/* 192 */     cb = CategoryManager.getCategoryBean(Integer.parseInt(cat_id));
/* 193 */     if (cb != null)
/*     */     {
/* 195 */       temp_key = cat_id + "_" + site_id;
/*     */ 
/* 197 */       if (cmm.containsKey(temp_key)) {
/* 198 */         return (List)cmm.get(temp_key);
/*     */       }
/* 200 */       return (List)cmm.get(cb.getCat_id() + "_" + site_id);
/*     */     }
/* 202 */     return null;
/*     */   }
/*     */ 
/*     */   public static boolean insertCategoryModel(List<CategoryModel> l)
/*     */   {
/* 215 */     if ((l != null) && (l.size() > 0))
/*     */     {
/* 217 */       if (CategoryModelDAO.insertCategoryModel(l))
/*     */       {
/* 219 */         reloadCategoryModel();
/* 220 */         return true;
/*     */       }
/*     */ 
/* 223 */       return false;
/*     */     }
/* 225 */     return true;
/*     */   }
/*     */ 
/*     */   public static boolean updateCategoryModel(List<CategoryModel> l, String cat_id, String site_id)
/*     */   {
/* 237 */     if (CategoryModelDAO.updateCategoryModel(l, cat_id, site_id))
/*     */     {
/* 239 */       reloadCategoryModel();
/* 240 */       return true;
/*     */     }
/*     */ 
/* 243 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteCategoryModel(String cat_id, String site_id)
/*     */   {
/* 254 */     if (CategoryModelDAO.deleteCategoryModel(cat_id, site_id))
/*     */     {
/* 256 */       reloadCategoryModel();
/* 257 */       return true;
/*     */     }
/*     */ 
/* 260 */     return false;
/*     */   }
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/* 265 */     System.out.println(getCategoryReleModelList("759", ""));
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.cms.category.CategoryModelManager
 * JD-Core Version:    0.6.2
 */