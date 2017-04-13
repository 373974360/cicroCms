/*     */ package com.cicro.wcm.services.zwgk.appcatalog;
/*     */ 
/*     */ import com.cicro.wcm.bean.cms.category.CategoryBean;
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.bean.zwgk.appcatalog.AppCatalogBean;
/*     */ import com.cicro.wcm.catchs.ISyncCatch;
/*     */ import com.cicro.wcm.catchs.SyncCatchHandl;
/*     */ import com.cicro.wcm.dao.PublicTableDAO;
/*     */ import com.cicro.wcm.dao.zwgk.appcatalog.AppCatalogDAO;
/*     */ import com.cicro.wcm.services.cms.category.CategoryManager;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ 
/*     */ public class AppCatalogManager
/*     */   implements ISyncCatch
/*     */ {
/*  31 */   public static Map<Integer, AppCatalogBean> cata_map = new HashMap();
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
/*  44 */     cata_map.clear();
/*  45 */     List l = AppCatalogDAO.getGKAppCatalogList();
/*  46 */     if ((l != null) && (l.size() > 0))
/*     */     {
/*  48 */       for (AppCatalogBean acb : l)
/*     */       {
/*  50 */         cata_map.put(Integer.valueOf(acb.getCata_id()), acb);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void reloadAppCatalog()
/*     */   {
/*  57 */     SyncCatchHandl.reladCatchForRMI("com.cicro.wcm.services.zwgk.appcatalog.AppCatalogManager");
/*     */   }
/*     */ 
/*     */   public static int getNewAppCatalogID()
/*     */   {
/*  62 */     return PublicTableDAO.getIDByTableName(PublicTableDAO.GK_APPCATALOG_TABLE_NAME);
/*     */   }
/*     */ 
/*     */   public static AppCatalogBean getAppCatalogBean(int cata_id)
/*     */   {
/*  72 */     if (cata_map.containsKey(Integer.valueOf(cata_id)))
/*     */     {
/*  74 */       return (AppCatalogBean)cata_map.get(Integer.valueOf(cata_id));
/*     */     }
/*     */ 
/*  77 */     AppCatalogBean acb = AppCatalogDAO.getAppCatalogBean(cata_id);
/*  78 */     if (acb != null)
/*     */     {
/*  80 */       cata_map.put(Integer.valueOf(acb.getCata_id()), acb);
/*  81 */       return acb;
/*     */     }
/*  83 */     return null;
/*     */   }
/*     */ 
/*     */   public static List<AppCatalogBean> getChildCatalogList(int cata_id)
/*     */   {
/*  94 */     Set set = cata_map.keySet();
/*  95 */     List l = new ArrayList();
/*  96 */     for (Iterator localIterator = set.iterator(); localIterator.hasNext(); ) { int i = ((Integer)localIterator.next()).intValue();
/*  97 */       AppCatalogBean acb = (AppCatalogBean)cata_map.get(Integer.valueOf(i));
/*  98 */       if (acb.getParent_id() == cata_id)
/*     */       {
/* 100 */         l.add(acb);
/*     */       }
/*     */     }
/* 103 */     if ((l != null) && (l.size() > 0))
/* 104 */       Collections.sort(l, new AppCatalogManager.AppCatalogComparator());
/* 105 */     return l;
/*     */   }
/*     */ 
/*     */   public static String getAllChildCatalogIDS(int cata_id)
/*     */   {
/* 115 */     String ids = "";
/* 116 */     AppCatalogBean acb = getAppCatalogBean(cata_id);
/* 117 */     if (acb != null)
/*     */     {
/* 119 */       String tree_position = acb.getTree_position();
/* 120 */       Set set = cata_map.keySet();
/* 121 */       for (Iterator localIterator = set.iterator(); localIterator.hasNext(); ) { int i = ((Integer)localIterator.next()).intValue();
/* 122 */         acb = (AppCatalogBean)cata_map.get(Integer.valueOf(i));
/* 123 */         if (acb.getTree_position().startsWith(tree_position))
/*     */         {
/* 125 */           ids = ids + "," + acb.getCata_id();
/*     */         }
/*     */       }
/* 128 */       if (ids.length() > 0)
/* 129 */         ids = ids.substring(1);
/*     */     }
/* 131 */     return ids;
/*     */   }
/*     */ 
/*     */   public static String getAllChildCatalogIDS(String cata_ids)
/*     */   {
/* 141 */     String ids = "";
/* 142 */     String[] tempA = cata_ids.split(",");
/* 143 */     for (int i = 0; i < tempA.length; i++)
/*     */     {
/* 145 */       String temp_id = getAllChildCatalogIDS(Integer.parseInt(tempA[i]));
/* 146 */       if ((temp_id != null) && (!"".equals(temp_id)))
/* 147 */         ids = ids + "," + temp_id;
/*     */     }
/* 149 */     if (ids.length() > 0) {
/* 150 */       ids = ids.substring(1);
/*     */     }
/* 152 */     return ids;
/*     */   }
/*     */ 
/*     */   public static boolean insertGKAppCatelog(AppCatalogBean acb, SettingLogsBean stl)
/*     */   {
/* 162 */     if (acb.getParent_id() == 0)
/*     */     {
/* 164 */       acb.setTree_position("$" + acb.getCata_id() + "$");
/*     */     }
/*     */     else {
/* 167 */       acb.setTree_position(getAppCatalogBean(acb.getParent_id()).getTree_position() + acb.getCata_id() + "$");
/*     */     }
/* 169 */     if (AppCatalogDAO.insertGKAppCatelog(acb))
/*     */     {
/* 171 */       reloadAppCatalog();
/* 172 */       PublicTableDAO.insertSettingLogs("添加", "公开应用目录", acb.getCata_id(), stl);
/* 173 */       return true;
/*     */     }
/* 175 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateGKAppCatelog(AppCatalogBean acb, SettingLogsBean stl)
/*     */   {
/* 185 */     if (AppCatalogDAO.updateGKAppCatelog(acb, stl))
/*     */     {
/* 187 */       reloadAppCatalog();
/* 188 */       return true;
/*     */     }
/* 190 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean sortGKAppCatelog(String ids, SettingLogsBean stl)
/*     */   {
/* 200 */     if (AppCatalogDAO.sortGKAppCatelog(ids, stl))
/*     */     {
/* 202 */       reloadAppCatalog();
/* 203 */       return true;
/*     */     }
/* 205 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean moveGKAppCatelog(int parent_id, String ids, SettingLogsBean stl)
/*     */   {
/* 216 */     AppCatalogBean acb = getAppCatalogBean(parent_id);
/* 217 */     if (acb != null)
/*     */     {
/* 219 */       String tree_position = acb.getTree_position();
/* 220 */       if ((ids != null) && (!"".equals(ids))) {
/*     */         try
/*     */         {
/* 223 */           String[] tempA = ids.split(",");
/* 224 */           for (int i = 0; i < tempA.length; i++)
/*     */           {
/* 226 */             moveGKAppCatelogHandl(tempA[i], parent_id, tree_position);
/*     */           }
/* 228 */           PublicTableDAO.insertSettingLogs("移动", "公开应用目录", ids, stl);
/* 229 */           reloadAppCatalog();
/* 230 */           return true;
/*     */         }
/*     */         catch (Exception e) {
/* 233 */           e.printStackTrace();
/* 234 */           return false;
/*     */         }
/*     */       }
/* 237 */       return true;
/*     */     }
/* 239 */     return false;
/*     */   }
/*     */ 
/*     */   public static void moveGKAppCatelogHandl(String cata_id, String parent_id, String parent_tree_position)
/*     */   {
/* 244 */     String position = parent_tree_position + cata_id + "$";
/* 245 */     Map new_m = new HashMap();
/* 246 */     new_m.put("cata_id", cata_id);
/* 247 */     new_m.put("parent_id", parent_id);
/* 248 */     new_m.put("tree_position", position);
/* 249 */     if (AppCatalogDAO.moveGKAppCatelog(new_m))
/*     */     {
/* 251 */       List l = getChildCatalogList(Integer.parseInt(cata_id));
/* 252 */       if ((l != null) && (l.size() > 0))
/*     */       {
/* 254 */         for (AppCatalogBean acb : l)
/*     */         {
/* 256 */           moveGKAppCatelogHandl(acb.getCata_id(), cata_id, position);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public static boolean updateGKAppCatelogSQL(String sql, String cata_id)
/*     */   {
/* 269 */     Map m = new HashMap();
/* 270 */     m.put("c_sql", sql);
/* 271 */     m.put("cata_id", cata_id);
/* 272 */     if (AppCatalogDAO.updateGKAppCatelogSQL(m))
/*     */     {
/* 274 */       reloadAppCatalog();
/* 275 */       return true;
/*     */     }
/* 277 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteGKAppCatelog(String cata_ids, SettingLogsBean stl)
/*     */   {
/* 287 */     String all_child_ids = getAllChildCatalogIDS(cata_ids);
/* 288 */     if (AppCatalogDAO.deleteGKAppCatelog(all_child_ids, stl))
/*     */     {
/* 291 */       AppCatalogDAO.deleteAppCateRegu(cata_ids);
/* 292 */       reloadAppCatalog();
/* 293 */       return true;
/*     */     }
/* 295 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean copyShareCategory(int parent_id, String selected_cat_ids, SettingLogsBean stl)
/*     */   {
/* 307 */     AppCatalogBean parent_acb = getAppCatalogBean(parent_id);
/* 308 */     String[] tempA = selected_cat_ids.split(",");
/*     */     try {
/* 310 */       for (int i = 0; i < tempA.length; i++)
/*     */       {
/* 313 */         CategoryBean cgb = CategoryManager.getCategoryBeanCatID(Integer.parseInt(tempA[i]), "");
/* 314 */         if (cgb != null)
/*     */         {
/* 316 */           copyCatalogHandl(parent_id, parent_acb.getTree_position(), cgb);
/*     */         }
/*     */       }
/* 319 */       PublicTableDAO.insertSettingLogs("拷贝目录", "公开应用目录", selected_cat_ids, stl);
/* 320 */       reloadAppCatalog();
/* 321 */       return true;
/*     */     }
/*     */     catch (Exception e) {
/* 324 */       e.printStackTrace();
/* 325 */     }return false;
/*     */   }
/*     */ 
/*     */   public static void copyCatalogHandl(int parent_id, String parent_tree_position, CategoryBean cgb)
/*     */   {
/* 331 */     AppCatalogBean acb = new AppCatalogBean();
/* 332 */     int id = getNewAppCatalogID();
/* 333 */     acb.setId(id);
/* 334 */     acb.setCata_id(id);
/* 335 */     acb.setTree_position(parent_tree_position + id + "$");
/* 336 */     acb.setParent_id(parent_id);
/* 337 */     acb.setCat_sort(cgb.getCat_sort());
/* 338 */     acb.setCata_cname(cgb.getCat_cname());
/* 339 */     acb.setCat_description(cgb.getCat_description());
/* 340 */     acb.setCat_keywords(cgb.getCat_keywords());
/* 341 */     acb.setCat_memo(cgb.getCat_memo());
/* 342 */     acb.setTemplate_index(cgb.getTemplate_index());
/* 343 */     acb.setTemplate_list(cgb.getTemplate_list());
/* 344 */     if (AppCatalogDAO.insertGKAppCatelog(acb))
/*     */     {
/* 347 */       List child_list = CategoryManager.getChildCategoryList(cgb.getCat_id(), "");
/* 348 */       if ((child_list != null) && (child_list.size() > 0))
/*     */       {
/* 350 */         for (CategoryBean cb : child_list)
/*     */         {
/* 352 */           copyCatalogHandl(id, acb.getTree_position(), cb);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public static String getAppCatalogTree(int cata_id)
/*     */   {
/* 383 */     String json_str = "";
/* 384 */     AppCatalogBean acb = getAppCatalogBean(cata_id);
/* 385 */     if (acb != null)
/*     */     {
/* 387 */       json_str = "[{\"id\":" + acb.getCata_id() + ",\"text\":\"" + acb.getCata_cname() + "\",\"attributes\":{\"url\":\"" + 
/* 388 */         acb.getJump_url() + "\",\"is_mutilpage\":\"" + acb.getIs_mutilpage() + "\"}";
/* 389 */       List l = getChildCatalogList(cata_id);
/* 390 */       if ((l != null) && (l.size() > 0))
/*     */       {
/* 392 */         json_str = json_str + ",\"children\":[" + getAppCatalogTreeHandl(l) + "]";
/*     */       }
/* 394 */       json_str = json_str + "}]";
/*     */     }
/*     */     else {
/* 397 */       json_str = "[]";
/*     */     }
/* 399 */     return json_str;
/*     */   }
/*     */ 
/*     */   public static String getAppCatalogTreeHandl(List<AppCatalogBean> l)
/*     */   {
/* 404 */     String json_str = "";
/* 405 */     for (AppCatalogBean acb : l)
/*     */     {
/* 407 */       json_str = json_str + ",{\"id\":" + acb.getCata_id() + ",\"text\":\"" + acb.getCata_cname() + "\",\"attributes\":{\"url\":\"" + 
/* 408 */         acb.getJump_url() + "\",\"is_mutilpage\":\"" + acb.getIs_mutilpage() + "\"}";
/* 409 */       List child_l = getChildCatalogList(acb.getCata_id());
/* 410 */       if ((child_l != null) && (child_l.size() > 0))
/*     */       {
/* 412 */         json_str = json_str + ",\"state\":'closed',\"children\":[" + getAppCatalogTreeHandl(child_l) + "]";
/*     */       }
/* 414 */       json_str = json_str + "}";
/*     */     }
/* 416 */     if ((json_str != null) && (json_str.length() > 0))
/* 417 */       json_str = json_str.substring(1);
/* 418 */     return json_str;
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.zwgk.appcatalog.AppCatalogManager
 * JD-Core Version:    0.6.2
 */