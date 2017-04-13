/*     */ package com.cicro.wcm.services.cms.category;
/*     */ 
/*     */ import com.cicro.wcm.bean.cms.category.CateClassBean;
/*     */ import com.cicro.wcm.bean.cms.category.CategoryBean;
/*     */ import com.cicro.wcm.bean.cms.category.SMCategoryBean;
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.catchs.ISyncCatch;
/*     */ import com.cicro.wcm.catchs.SyncCatchHandl;
/*     */ import com.cicro.wcm.dao.PublicTableDAO;
/*     */ import com.cicro.wcm.dao.cms.category.CateClassDao;
/*     */ import java.io.PrintStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.TreeMap;
/*     */ 
/*     */ public class CateClassManager
/*     */   implements ISyncCatch
/*     */ {
/*     */   private static final int BASIS_DIR = 0;
/*  44 */   private static TreeMap<String, CateClassBean> cateClass_Map = new TreeMap();
/*  45 */   private static Map<String, SMCategoryBean> sm_category_map = new HashMap();
/*     */ 
/*     */   static {
/*  48 */     reloadCatchHandl();
/*     */   }
/*     */ 
/*     */   public void reloadCatch()
/*     */   {
/*  53 */     reloadCatchHandl();
/*     */   }
/*     */ 
/*     */   public static void reloadCatchHandl()
/*     */   {
/*  58 */     cateClass_Map.clear();
/*     */     try {
/*  60 */       List lt = CateClassDao.getAllCateClassList();
/*  61 */       sm_category_map.clear();
/*  62 */       if ((lt != null) && (lt.size() > 0))
/*     */       {
/*  64 */         for (int i = 0; i < lt.size(); i++)
/*     */         {
/*  66 */           cateClass_Map.put(((CateClassBean)lt.get(i)).getClass_id(), (CateClassBean)lt.get(i));
/*     */         }
/*     */       }
/*     */     }
/*     */     catch (Exception e) {
/*  71 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void reloadCateClass()
/*     */   {
/*  80 */     SyncCatchHandl.reladCatchForRMI("com.cicro.wcm.services.cms.category.CateClassManager");
/*     */   }
/*     */ 
/*     */   public static void clearSMCateMap()
/*     */   {
/*  85 */     sm_category_map.clear();
/*     */   }
/*     */ 
/*     */   public static SMCategoryBean getSMCategoryList(String ename)
/*     */   {
/*  96 */     CateClassBean ccb = getCateClassBeanByEName(ename);
/*     */ 
/*  98 */     if (ccb != null)
/*     */     {
/* 100 */       if (sm_category_map.containsKey(ename))
/*     */       {
/* 102 */         return (SMCategoryBean)sm_category_map.get(ename);
/*     */       }
/*     */ 
/* 105 */       CategoryBean cgb = CategoryManager.getCategoryBeanByClassID(ccb.getClass_id());
/* 106 */       SMCategoryBean smcb = CategoryManager.getAllChildForSMCategoryBean(cgb);
/* 107 */       sm_category_map.put(ename, smcb);
/* 108 */       return smcb;
/*     */     }
/*     */ 
/* 111 */     return null;
/*     */   }
/*     */ 
/*     */   public static CateClassBean getCateClassBeanByEName(String ename)
/*     */   {
/* 122 */     Set s = cateClass_Map.keySet();
/* 123 */     for (String str : s)
/*     */     {
/* 125 */       CateClassBean ccb = (CateClassBean)cateClass_Map.get(str);
/* 126 */       if (ccb.getClass_ename().equals(ename))
/*     */       {
/* 128 */         return ccb;
/*     */       }
/*     */     }
/* 131 */     return null;
/*     */   }
/*     */ 
/*     */   public static Map<String, CateClassBean> getCateClassMap()
/*     */   {
/* 140 */     return cateClass_Map;
/*     */   }
/*     */ 
/*     */   public static List<CateClassBean> getBasisCateClassList()
/*     */   {
/* 150 */     List ret = new ArrayList();
/* 151 */     Iterator it = cateClass_Map.values().iterator();
/* 152 */     while (it.hasNext())
/*     */     {
/* 154 */       CateClassBean ccb = (CateClassBean)it.next();
/* 155 */       if (ccb.getClass_type() == 0)
/*     */       {
/* 157 */         ret.add(ccb);
/*     */       }
/*     */     }
/* 160 */     return ret;
/*     */   }
/*     */ 
/*     */   public static List<CateClassBean> getAllCateClassList()
/*     */   {
/* 169 */     List ret = new ArrayList();
/*     */ 
/* 171 */     Map mp = new TreeMap();
/* 172 */     Iterator val_it = cateClass_Map.values().iterator();
/* 173 */     while (val_it.hasNext())
/*     */     {
/* 175 */       CateClassBean ccb = (CateClassBean)val_it.next();
/* 176 */       mp.put(Integer.valueOf(ccb.getClass_id()), ccb);
/*     */     }
/*     */ 
/* 179 */     Iterator it = mp.keySet().iterator();
/* 180 */     while (it.hasNext())
/*     */     {
/* 182 */       int key = ((Integer)it.next()).intValue();
/* 183 */       ret.add((CateClassBean)mp.get(Integer.valueOf(key)));
/*     */     }
/* 185 */     return ret;
/*     */   }
/*     */ 
/*     */   public static List<CateClassBean> getCateClassListByApp(String app_id)
/*     */   {
/* 196 */     Set set = cateClass_Map.keySet();
/* 197 */     List list = new ArrayList();
/* 198 */     for (String i : set) {
/* 199 */       CateClassBean ccb = (CateClassBean)cateClass_Map.get(i);
/* 200 */       if ((ccb.getApp_ids().contains(app_id)) && (ccb.getClass_type() == 1))
/* 201 */         list.add((CateClassBean)cateClass_Map.get(i));
/*     */     }
/* 203 */     return list;
/*     */   }
/*     */ 
/*     */   public static CateClassBean getCateClassBeanById(String id)
/*     */   {
/* 213 */     if (cateClass_Map.containsKey(id)) {
/* 214 */       return (CateClassBean)cateClass_Map.get(id);
/*     */     }
/*     */ 
/* 217 */     CateClassBean ccb = CateClassDao.getCateClassBeanByID(id);
/* 218 */     if (ccb != null)
/*     */     {
/* 220 */       cateClass_Map.put(ccb.getClass_id(), ccb);
/* 221 */       return ccb;
/*     */     }
/* 223 */     return null;
/*     */   }
/*     */ 
/*     */   public static boolean insertCateClass(CateClassBean ccb, SettingLogsBean stl)
/*     */   {
/* 236 */     int class_id = PublicTableDAO.getIDByTableName(PublicTableDAO.INFO_CLASS_TABLE_NAME);
/* 237 */     ccb.setClass_id(class_id);
/* 238 */     if (CateClassDao.insertCateClass(ccb, stl))
/*     */     {
/* 240 */       reloadCateClass();
/* 241 */       CategoryManager.insertCategoryByClass(ccb, stl);
/* 242 */       return true;
/*     */     }
/*     */ 
/* 246 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateCateClass(CateClassBean ccb, SettingLogsBean stl)
/*     */   {
/* 259 */     if (CateClassDao.updateCateClass(ccb, stl))
/*     */     {
/* 261 */       reloadCateClass();
/* 262 */       CategoryManager.updateCategoryByClass(ccb.getClass_ename(), ccb.getClass_cname(), ccb.getClass_id());
/* 263 */       return true;
/*     */     }
/*     */ 
/* 267 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteCateClass(String ids, SettingLogsBean stl)
/*     */   {
/* 280 */     if (CateClassDao.deleteCateClass(ids, stl))
/*     */     {
/* 282 */       reloadCateClass();
/* 283 */       CategoryManager.deleteCategoryByClassID(ids);
/* 284 */       return true;
/*     */     }
/*     */ 
/* 288 */     return false;
/*     */   }
/*     */ 
/*     */   public static void main(String[] arg)
/*     */   {
/* 294 */     System.out.println(getSMCategoryList("ztfl"));
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.cms.category.CateClassManager
 * JD-Core Version:    0.6.2
 */