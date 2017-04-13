/*     */ package com.cicro.wcm.services.appeal.category;
/*     */ 
/*     */ import com.cicro.util.jconfig.JconfigUtil;
/*     */ import com.cicro.util.jconfig.JconfigUtilContainer;
/*     */ import com.cicro.wcm.bean.appeal.category.CategoryBean;
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.catchs.ISyncCatch;
/*     */ import com.cicro.wcm.catchs.SyncCatchHandl;
/*     */ import com.cicro.wcm.dao.PublicTableDAO;
/*     */ import com.cicro.wcm.dao.appeal.category.CategoryDao;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.Set;
/*     */ import java.util.TreeMap;
/*     */ 
/*     */ public class CategoryManager
/*     */   implements ISyncCatch
/*     */ {
/*  25 */   private static TreeMap<String, CategoryBean> category_map = new TreeMap();
/*  26 */   public static int ROOT_CAT_ID = 1;
/*     */ 
/*     */   static {
/*  29 */     reloadCatchHandl();
/*     */   }
/*     */ 
/*     */   public void reloadCatch()
/*     */   {
/*  34 */     reloadCatchHandl();
/*     */   }
/*     */ 
/*     */   public static void reloadCatchHandl() {
/*  38 */     List appCate_List = CategoryDao.getAllApp_categroyList();
/*  39 */     category_map.clear();
/*  40 */     if ((appCate_List != null) && (appCate_List.size() > 0))
/*  41 */       for (int i = 0; i < appCate_List.size(); i++)
/*  42 */         category_map.put(((CategoryBean)appCate_List.get(i)).getCat_id(), (CategoryBean)appCate_List.get(i));
/*     */   }
/*     */ 
/*     */   public static void reloadAppCate()
/*     */   {
/*  56 */     SyncCatchHandl.reladCatchForRMI("com.cicro.wcm.services.appeal.category.CategoryManager");
/*     */   }
/*     */ 
/*     */   public static int getAppealCategoryID()
/*     */   {
/*  66 */     return PublicTableDAO.getIDByTableName(PublicTableDAO.APPEAL_CATEGORY_TABLE_NAME);
/*     */   }
/*     */ 
/*     */   public static String getAppCateTreeJsonStr()
/*     */   {
/*  76 */     CategoryBean mb = getApp_categoryBean(ROOT_CAT_ID);
/*  77 */     if (mb != null)
/*     */     {
/*  79 */       String json_str = "[{\"id\":" + ROOT_CAT_ID + ",\"text\":\"" + mb.getCat_cname() + "\" ";
/*     */ 
/*  81 */       String child_str = getAppCateTreeJsonStrHandl(getChildAppCateList(ROOT_CAT_ID));
/*  82 */       if ((child_str != null) && (!"".equals(child_str)))
/*  83 */         json_str = json_str + ",\"children\":[" + child_str + "]";
/*  84 */       json_str = json_str + "}]";
/*  85 */       return json_str;
/*     */     }
/*  87 */     return "[]";
/*     */   }
/*     */ 
/*     */   public static String getAppCateTreeJsonStrHandl(List<CategoryBean> all_AppCate_list)
/*     */   {
/*  98 */     String json_str = "";
/*  99 */     if ((all_AppCate_list != null) && (all_AppCate_list.size() > 0))
/*     */     {
/* 101 */       for (int i = 0; i < all_AppCate_list.size(); i++)
/*     */       {
/* 103 */         json_str = json_str + "{";
/* 104 */         json_str = json_str + "\"id\":" + ((CategoryBean)all_AppCate_list.get(i)).getCat_id() + ",\"text\":\"" + ((CategoryBean)all_AppCate_list.get(i)).getCat_cname() + "\",\"attributes\":{\"url\":\"" + JconfigUtilContainer.managerPagePath().getProperty("AppCate_list", "", "m_org_path") + "?AppCateID=" + ((CategoryBean)all_AppCate_list.get(i)).getCat_id() + "\"}";
/* 105 */         List child_m_list = getChildAppCateList(((CategoryBean)all_AppCate_list.get(i)).getCat_id());
/* 106 */         if ((child_m_list != null) && (child_m_list.size() > 0))
/* 107 */           json_str = json_str + ",\"state\":'closed',\"children\":[" + getAppCateTreeJsonStrHandl(child_m_list) + "]";
/* 108 */         json_str = json_str + "}";
/* 109 */         if (i + 1 != all_AppCate_list.size())
/* 110 */           json_str = json_str + ",";
/*     */       }
/*     */     }
/* 113 */     return json_str;
/*     */   }
/*     */ 
/*     */   public static CategoryBean getApp_categoryBean(int cat_id)
/*     */   {
/* 123 */     if (category_map.containsKey(cat_id))
/*     */     {
/* 125 */       return (CategoryBean)category_map.get(cat_id);
/*     */     }
/*     */ 
/* 128 */     CategoryBean mb = CategoryDao.getapp_categoryBean(cat_id);
/* 129 */     if (mb != null)
/*     */     {
/* 131 */       category_map.put(cat_id, mb);
/* 132 */       return mb;
/*     */     }
/* 134 */     return null;
/*     */   }
/*     */ 
/*     */   public static List<CategoryBean> getapp_categoryBeanByOptID(String cat_id)
/*     */   {
/* 146 */     List AppCate_list = new ArrayList();
/* 147 */     Iterator iter = category_map.entrySet().iterator();
/* 148 */     while (iter.hasNext()) {
/* 149 */       Entry entry = (Entry)iter.next();
/* 150 */       String key = (String)entry.getKey();
/* 151 */       if (cat_id.equals(((CategoryBean)category_map.get(key)).getCat_id())) {
/* 152 */         AppCate_list.add((CategoryBean)entry.getValue());
/*     */       }
/*     */     }
/* 155 */     return null;
/*     */   }
/*     */ 
/*     */   public static String getChildAppCateCount(String AppCate_id)
/*     */   {
/* 166 */     int count = 0;
/* 167 */     Iterator iter = category_map.entrySet().iterator();
/* 168 */     while (iter.hasNext()) {
/* 169 */       Entry entry = (Entry)iter.next();
/* 170 */       String key = (String)entry.getKey();
/* 171 */       if (AppCate_id.equals(((CategoryBean)category_map.get(key)).getParent_id())) {
/* 172 */         count++;
/*     */       }
/*     */     }
/* 175 */     return count;
/*     */   }
/*     */ 
/*     */   public static List<CategoryBean> getChildAppCateList(String cat_id)
/*     */   {
/* 186 */     List m_List = new ArrayList();
/* 187 */     Iterator iter = category_map.entrySet().iterator();
/* 188 */     while (iter.hasNext()) {
/* 189 */       Entry entry = (Entry)iter.next();
/* 190 */       CategoryBean mb = (CategoryBean)entry.getValue();
/* 191 */       if (cat_id.equals(mb.getParent_id())) {
/* 192 */         m_List.add((CategoryBean)entry.getValue());
/*     */       }
/*     */     }
/* 195 */     Collections.sort(m_List, new CategoryManager.AppCateComparator());
/* 196 */     return m_List;
/*     */   }
/*     */ 
/*     */   public static List<CategoryBean> getALLChildAppCateListByID(int cat_id)
/*     */   {
/* 228 */     CategoryBean mb = getApp_categoryBean(cat_id);
/* 229 */     if (mb != null) {
/* 230 */       return getALLChildAppCateListByID(mb);
/*     */     }
/* 232 */     return null;
/*     */   }
/*     */ 
/*     */   public static String getALLChildAppCateIDSByID(String cat_ids)
/*     */   {
/* 242 */     String m_ids = "";
/* 243 */     if ((cat_ids != null) && (!"".equals(cat_ids)))
/*     */     {
/* 245 */       String[] AppCate_a = cat_ids.split(",");
/* 246 */       for (int i = 0; i < AppCate_a.length; i++)
/*     */       {
/* 248 */         List m_List = getALLChildAppCateListByID(Integer.parseInt(AppCate_a[i]));
/* 249 */         if ((m_List != null) && (m_List.size() > 0))
/*     */         {
/* 251 */           for (int j = 0; j < m_List.size(); j++)
/*     */           {
/* 253 */             m_ids = m_ids + "," + ((CategoryBean)m_List.get(j)).getCat_id();
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/* 258 */     return m_ids;
/*     */   }
/*     */ 
/*     */   public static List<CategoryBean> getALLChildAppCateListByID(CategoryBean mb)
/*     */   {
/* 268 */     String AppCate_position = mb.getCat_position();
/* 269 */     List m_List = new ArrayList();
/* 270 */     Iterator iter = category_map.entrySet().iterator();
/* 271 */     while (iter.hasNext()) {
/* 272 */       Entry entry = (Entry)iter.next();
/* 273 */       String key = (String)entry.getKey();
/* 274 */       if ((((CategoryBean)category_map.get(key)).getCat_position().startsWith(AppCate_position)) && (!((CategoryBean)category_map.get(key)).getCat_position().equals(AppCate_position))) {
/* 275 */         m_List.add((CategoryBean)entry.getValue());
/*     */       }
/*     */     }
/* 278 */     Collections.sort(m_List, new CategoryManager.AppCateComparator());
/* 279 */     return m_List;
/*     */   }
/*     */ 
/*     */   public static boolean insertAppCate(CategoryBean mb, SettingLogsBean stl)
/*     */   {
/* 290 */     mb.setCat_position(getApp_categoryBean(mb.getParent_id()).getCat_position());
/* 291 */     if (CategoryDao.insertApp_categroy(mb, stl))
/*     */     {
/* 293 */       reloadAppCate();
/* 294 */       return true;
/*     */     }
/*     */ 
/* 297 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateAppCate(CategoryBean mb, SettingLogsBean stl)
/*     */   {
/* 308 */     if (CategoryDao.updateApp_categroy(mb, stl))
/*     */     {
/* 310 */       reloadAppCate();
/* 311 */       return true;
/*     */     }
/*     */ 
/* 314 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean saveAppCateSort(String cat_id, SettingLogsBean stl)
/*     */   {
/* 325 */     if (CategoryDao.saveApp_categroySort(cat_id, stl))
/*     */     {
/* 327 */       reloadAppCate();
/* 328 */       return true;
/*     */     }
/*     */ 
/* 331 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean moveAppCate(String parent_id, String AppCate_ids, SettingLogsBean stl)
/*     */   {
/* 342 */     String parent_tree_position = getApp_categoryBean(Integer.parseInt(parent_id)).getCat_position();
/*     */ 
/* 344 */     if ((AppCate_ids != null) && (!"".equals(AppCate_ids))) {
/*     */       try
/*     */       {
/* 347 */         String[] tempA = AppCate_ids.split(",");
/* 348 */         for (int i = 0; i < tempA.length; i++)
/*     */         {
/* 350 */           moveAppCateHandl(tempA[i], parent_id, parent_tree_position, stl);
/*     */         }
/* 352 */         reloadAppCate();
/* 353 */         return true;
/*     */       }
/*     */       catch (Exception e) {
/* 356 */         e.printStackTrace();
/* 357 */         return false;
/*     */       }
/*     */     }
/* 360 */     return true;
/*     */   }
/*     */ 
/*     */   public static void moveAppCateHandl(String cat_id, String parent_id, String AppCate_position, SettingLogsBean stl)
/*     */   {
/* 365 */     String position = AppCate_position + cat_id + "$";
/* 366 */     Map new_m = new HashMap();
/* 367 */     new_m.put("cat_id", cat_id);
/* 368 */     new_m.put("parent_id", parent_id);
/* 369 */     new_m.put("cat_position", position);
/* 370 */     if (CategoryDao.moveApp_categroy(new_m, stl))
/*     */     {
/* 372 */       List m_list = getChildAppCateList(cat_id);
/* 373 */       if ((m_list != null) && (m_list.size() > 0))
/*     */       {
/* 375 */         for (int i = 0; i < m_list.size(); i++)
/*     */         {
/* 377 */           moveAppCateHandl(((CategoryBean)m_list.get(i)).getCat_id(), cat_id, position, stl);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public static boolean deleteAppCate(String ca_id, SettingLogsBean stl)
/*     */   {
/* 392 */     ca_id = ca_id + getALLChildAppCateIDSByID(ca_id);
/* 393 */     if (CategoryDao.deleteApp_categroy(ca_id, stl))
/*     */     {
/* 395 */       reloadAppCate();
/* 396 */       return true;
/*     */     }
/*     */ 
/* 399 */     return false;
/*     */   }
/*     */ 
/*     */   public static Set<CategoryBean> getAppCateSetOptID(String opt_ids)
/*     */   {
/* 484 */     opt_ids = "," + opt_ids + ",";
/*     */ 
/* 486 */     Set AppCate_set = new HashSet();
/* 487 */     Iterator iter = category_map.entrySet().iterator();
/* 488 */     while (iter.hasNext()) {
/* 489 */       Entry entry = (Entry)iter.next();
/* 490 */       String key = (String)entry.getKey();
/*     */ 
/* 492 */       if (opt_ids.contains("," + ((CategoryBean)category_map.get(key)).getCat_id() + ",")) {
/* 493 */         AppCate_set.add((CategoryBean)category_map.get(key));
/* 494 */         setAppCateByAppCatePosition(AppCate_set, ((CategoryBean)category_map.get(key)).getCat_position());
/*     */       }
/*     */     }
/*     */ 
/* 498 */     return AppCate_set;
/*     */   }
/*     */ 
/*     */   public static void setAppCateByAppCatePosition(Set<CategoryBean> AppCate_set, String AppCate_position)
/*     */   {
/* 509 */     String[] tempA = AppCate_position.split("\\$");
/*     */ 
/* 513 */     for (int i = 2; i < tempA.length - 1; i++)
/*     */     {
/* 515 */       if ((tempA[i] != null) && (!"".equals(tempA[i])))
/* 516 */         AppCate_set.add((CategoryBean)category_map.get(tempA[i]));
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/*     */   }
/*     */ 
/*     */   public static void insertAppCateTest()
/*     */   {
/*     */   }
/*     */ 
/*     */   public static void updateAppCateTest()
/*     */   {
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.appeal.category.CategoryManager
 * JD-Core Version:    0.6.2
 */