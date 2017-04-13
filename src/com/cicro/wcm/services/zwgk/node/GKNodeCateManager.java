/*     */ package com.cicro.wcm.services.zwgk.node;
/*     */ 
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.bean.zwgk.node.GKNodeCategory;
/*     */ import com.cicro.wcm.catchs.ISyncCatch;
/*     */ import com.cicro.wcm.catchs.SyncCatchHandl;
/*     */ import com.cicro.wcm.dao.PublicTableDAO;
/*     */ import com.cicro.wcm.dao.zwgk.node.GKNodeCategoryDAO;
/*     */ import java.io.PrintStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ 
/*     */ public class GKNodeCateManager
/*     */   implements ISyncCatch
/*     */ {
/*  30 */   private static Map<Integer, GKNodeCategory> gnc_map = new HashMap();
/*  31 */   private static int ROOT_ID = 0;
/*     */ 
/*  33 */   static { reloadCatchHandl(); }
/*     */ 
/*     */ 
/*     */   public void reloadCatch()
/*     */   {
/*  38 */     reloadCatchHandl();
/*     */   }
/*     */ 
/*     */   public static void reloadCatchHandl()
/*     */   {
/*  43 */     gnc_map.clear();
/*  44 */     List l = GKNodeCategoryDAO.getAllNodeCategoryList();
/*  45 */     if ((l != null) && (l.size() > 0))
/*     */     {
/*  47 */       for (int i = 0; i < l.size(); i++)
/*     */       {
/*  49 */         gnc_map.put(Integer.valueOf(((GKNodeCategory)l.get(i)).getNodcat_id()), (GKNodeCategory)l.get(i));
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void reloadGKNodeCategory()
/*     */   {
/*  56 */     SyncCatchHandl.reladCatchForRMI("com.cicro.wcm.services.zwgk.node.GKNodeCateManager");
/*     */   }
/*     */ 
/*     */   public static int getNewNodCatID()
/*     */   {
/*  65 */     return PublicTableDAO.getIDByTableName(PublicTableDAO.GK_NODE_CATEGORY);
/*     */   }
/*     */ 
/*     */   public static GKNodeCategory getNodeCategoryBean(int id)
/*     */   {
/*  75 */     if (id == ROOT_ID)
/*     */     {
/*  77 */       GKNodeCategory gnc = new GKNodeCategory();
/*  78 */       gnc.setNodcat_id(ROOT_ID);
/*  79 */       gnc.setNod_position("$" + ROOT_ID + "$");
/*  80 */       return gnc;
/*     */     }
/*  82 */     if (gnc_map.containsKey(Integer.valueOf(id)))
/*     */     {
/*  84 */       return (GKNodeCategory)gnc_map.get(Integer.valueOf(id));
/*     */     }
/*     */ 
/*  87 */     GKNodeCategory gnc = GKNodeCategoryDAO.getNodeCategoryBean(id);
/*  88 */     if (gnc != null)
/*     */     {
/*  90 */       gnc_map.put(Integer.valueOf(id), gnc);
/*  91 */       return gnc;
/*     */     }
/*  93 */     return null;
/*     */   }
/*     */ 
/*     */   public static boolean insertGKNodeCategory(GKNodeCategory gnc, SettingLogsBean stl)
/*     */   {
/* 105 */     if (gnc.getParent_id() == ROOT_ID)
/*     */     {
/* 107 */       gnc.setNod_position("$0$" + gnc.getNodcat_id() + "$");
/*     */     }
/*     */     else
/*     */     {
/* 111 */       GKNodeCategory parentB = getNodeCategoryBean(gnc.getParent_id());
/* 112 */       gnc.setNod_position(parentB.getNod_position() + gnc.getNodcat_id() + "$");
/*     */     }
/*     */ 
/* 115 */     if (GKNodeCategoryDAO.insertGKNodeCategory(gnc, stl))
/*     */     {
/* 117 */       reloadGKNodeCategory();
/* 118 */       return true;
/*     */     }
/* 120 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateGKNodeCategory(GKNodeCategory gnc, SettingLogsBean stl)
/*     */   {
/* 131 */     if (GKNodeCategoryDAO.updateGKNodeCategory(gnc, stl))
/*     */     {
/* 133 */       reloadGKNodeCategory();
/* 134 */       return true;
/*     */     }
/* 136 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean moveGKNodeCategory(String nodcat_ids, int parent_id, SettingLogsBean stl)
/*     */   {
/* 147 */     GKNodeCategory gnc = getNodeCategoryBean(parent_id);
/* 148 */     if (gnc != null)
/*     */     {
/* 150 */       String[] tempA = nodcat_ids.split(",");
/* 151 */       for (int i = 0; i < tempA.length; i++)
/*     */       {
/* 153 */         moveGKNodeCategoryHandl(Integer.parseInt(tempA[i]), parent_id, gnc.getNod_position());
/*     */       }
/* 155 */       reloadGKNodeCategory();
/* 156 */       return true;
/*     */     }
/*     */ 
/* 159 */     return false;
/*     */   }
/*     */ 
/*     */   public static void moveGKNodeCategoryHandl(int nodcat_id, int parent_id, String nod_position)
/*     */   {
/* 165 */     String position = nod_position + nodcat_id + "$";
/* 166 */     Map m = new HashMap();
/* 167 */     m.put("parent_id", parent_id);
/* 168 */     m.put("nodcat_id", nodcat_id);
/* 169 */     m.put("nod_position", position);
/* 170 */     if (GKNodeCategoryDAO.moveGKNodeCategory(m))
/*     */     {
/* 172 */       List l = getChildCategoryList(nodcat_id);
/* 173 */       if ((l != null) && (l.size() > 0))
/*     */       {
/* 175 */         for (int i = 0; i < l.size(); i++)
/*     */         {
/* 177 */           moveGKNodeCategoryHandl(((GKNodeCategory)l.get(i)).getNodcat_id(), nodcat_id, position);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public static boolean sortGKNodeCategory(String nodcat_ids, SettingLogsBean stl)
/*     */   {
/* 192 */     if (GKNodeCategoryDAO.sortGKNodeCategory(nodcat_ids, stl))
/*     */     {
/* 194 */       reloadGKNodeCategory();
/* 195 */       return true;
/*     */     }
/* 197 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteGKNodeCategory(String nodcat_ids, SettingLogsBean stl)
/*     */   {
/* 208 */     String child_ids = getAllChildCategoryIDS(nodcat_ids);
/* 209 */     if ((child_ids != null) && (!"".equals(child_ids)))
/* 210 */       nodcat_ids = nodcat_ids + "," + child_ids;
/* 211 */     if (GKNodeCategoryDAO.deleteGKNodeCategory(nodcat_ids, stl))
/*     */     {
/* 213 */       reloadGKNodeCategory();
/* 214 */       return true;
/*     */     }
/* 216 */     return false;
/*     */   }
/*     */ 
/*     */   public static String getGKNodeCategroyJSONROOTTreeStr()
/*     */   {
/* 225 */     String json_str = "";
/* 226 */     json_str = "[{\"id\":" + ROOT_ID + ",\"text\":\"公开节点分类\",\"attributes\":{\"url\":\"\",\"handl\":\"\"}";
/* 227 */     String child_str = getGKNodeCategroyJSONTreeStr();
/*     */ 
/* 229 */     if ((child_str != null) && (child_str.length() > 3)) {
/* 230 */       json_str = json_str + ",\"children\":" + child_str;
/*     */     }
/* 232 */     json_str = json_str + "}]";
/* 233 */     return json_str;
/*     */   }
/*     */ 
/*     */   public static String getGKNodeCategroyJSONTreeStr()
/*     */   {
/* 243 */     String json_str = "";
/* 244 */     json_str = "[";
/* 245 */     List list = getChildCategoryList(ROOT_ID);
/* 246 */     if ((list != null) && (list.size() > 0))
/*     */     {
/* 248 */       json_str = json_str + getGKNodeCategroyJSONTreeStrHandl(list);
/*     */     }
/* 250 */     json_str = json_str + "]";
/* 251 */     return json_str;
/*     */   }
/*     */ 
/*     */   public static String getGKNodeCategroyJSONTreeStrHandl(List<GKNodeCategory> child_list)
/*     */   {
/* 261 */     String json_str = "";
/* 262 */     if ((child_list != null) && (child_list.size() > 0))
/*     */     {
/* 264 */       for (int i = 0; i < child_list.size(); i++)
/*     */       {
/* 266 */         json_str = json_str + "{";
/* 267 */         List child_m_list = getChildCategoryList(((GKNodeCategory)child_list.get(i)).getNodcat_id());
/* 268 */         if ((child_m_list != null) && (child_m_list.size() > 0))
/*     */         {
/* 270 */           json_str = json_str + "\"id\":" + ((GKNodeCategory)child_list.get(i)).getNodcat_id() + ",\"text\":\"" + ((GKNodeCategory)child_list.get(i)).getNodcat_name() + "\",\"attributes\":{\"url\":\"\",\"handl\":\"\"}";
/*     */ 
/* 272 */           json_str = json_str + ",\"children\":[" + getGKNodeCategroyJSONTreeStrHandl(child_m_list) + "]";
/*     */         } else {
/* 274 */           json_str = json_str + "\"id\":" + ((GKNodeCategory)child_list.get(i)).getNodcat_id() + ",\"text\":\"" + ((GKNodeCategory)child_list.get(i)).getNodcat_name() + "\",\"attributes\":{\"url\":\"\",\"handl\":\"\"}";
/* 275 */         }json_str = json_str + "}";
/* 276 */         if (i + 1 != child_list.size())
/* 277 */           json_str = json_str + ",";
/*     */       }
/*     */     }
/* 280 */     return json_str;
/*     */   }
/*     */ 
/*     */   public static String getGKNodeTreebyCateID(int nodcat_id)
/*     */   {
/* 290 */     String json_str = "";
/* 291 */     json_str = "[";
/* 292 */     List list = getChildCategoryList(nodcat_id);
/* 293 */     if ((list != null) && (list.size() > 0))
/*     */     {
/* 295 */       json_str = json_str + getGKNodeTreebyCateIDHandl(list);
/*     */     }
/* 297 */     json_str = json_str + "]";
/* 298 */     return json_str;
/*     */   }
/*     */ 
/*     */   public static String getGKNodeTreebyCateIDHandl(List<GKNodeCategory> child_list)
/*     */   {
/* 308 */     String json_str = "";
/* 309 */     if ((child_list != null) && (child_list.size() > 0))
/*     */     {
/* 311 */       for (int i = 0; i < child_list.size(); i++)
/*     */       {
/* 313 */         json_str = json_str + "{";
/* 314 */         String node_str = GKNodeManager.getGKNodeJSONStrByCateID(((GKNodeCategory)child_list.get(i)).getNodcat_id());
/*     */ 
/* 316 */         List child_m_list = getChildCategoryList(((GKNodeCategory)child_list.get(i)).getNodcat_id());
/* 317 */         if ((child_m_list != null) && (child_m_list.size() > 0))
/*     */         {
/* 319 */           json_str = json_str + "\"id\":" + ((GKNodeCategory)child_list.get(i)).getNodcat_id() + ",\"text\":\"" + ((GKNodeCategory)child_list.get(i)).getNodcat_name() + "\",\"attributes\":{\"url\":\"\",\"handl\":\"\"}";
/*     */ 
/* 321 */           json_str = json_str + ",\"children\":[" + getGKNodeTreebyCateIDHandl(child_m_list);
/* 322 */           if ((node_str != null) && (!"".equals(node_str)))
/* 323 */             json_str = json_str + "," + node_str;
/* 324 */           json_str = json_str + "]";
/*     */         }
/*     */         else {
/* 327 */           json_str = json_str + "\"id\":" + ((GKNodeCategory)child_list.get(i)).getNodcat_id() + ",\"text\":\"" + ((GKNodeCategory)child_list.get(i)).getNodcat_name() + "\",\"attributes\":{\"url\":\"\",\"handl\":\"\"}";
/* 328 */           if ((node_str != null) && (!"".equals(node_str)))
/* 329 */             json_str = json_str + ",\"children\":[" + node_str + "]";
/*     */         }
/* 331 */         json_str = json_str + "}";
/* 332 */         if (i + 1 != child_list.size())
/* 333 */           json_str = json_str + ",";
/*     */       }
/*     */     }
/* 336 */     return json_str;
/*     */   }
/*     */ 
/*     */   public static boolean hasChildNodeByCategory(int nodcat_id)
/*     */   {
/* 346 */     Set set = gnc_map.keySet();
/* 347 */     List list = new ArrayList();
/* 348 */     for (Iterator localIterator = set.iterator(); localIterator.hasNext(); ) { int i = ((Integer)localIterator.next()).intValue();
/* 349 */       GKNodeCategory gnc = (GKNodeCategory)gnc_map.get(Integer.valueOf(i));
/* 350 */       if (gnc.getParent_id() == nodcat_id)
/* 351 */         return true;
/*     */     }
/* 353 */     return false;
/*     */   }
/*     */ 
/*     */   public static List<GKNodeCategory> getNodeListForCatID(int nodcat_id)
/*     */   {
/* 363 */     List cat_list = getChildCategoryList(nodcat_id);
/* 364 */     if ((cat_list != null) && (cat_list.size() > 0))
/*     */     {
/* 366 */       for (GKNodeCategory cat_bean : cat_list)
/*     */       {
/* 368 */         cat_bean.setNode_list(GKNodeManager.getGKNodeListByCateID(cat_bean.getNodcat_id()));
/* 369 */         cat_bean.setClass_list(getNodeListForCatID(cat_bean.getNodcat_id()));
/*     */       }
/*     */     }
/* 372 */     return cat_list;
/*     */   }
/*     */ 
/*     */   public static List<GKNodeCategory> getChildCategoryList(int nodcat_id)
/*     */   {
/* 382 */     Set set = gnc_map.keySet();
/* 383 */     List list = new ArrayList();
/* 384 */     for (Iterator localIterator = set.iterator(); localIterator.hasNext(); ) { int i = ((Integer)localIterator.next()).intValue();
/* 385 */       GKNodeCategory gnc = (GKNodeCategory)gnc_map.get(Integer.valueOf(i));
/* 386 */       if ((gnc.getParent_id() == nodcat_id) && (gnc.getNodcat_id() != nodcat_id))
/* 387 */         list.add((GKNodeCategory)gnc_map.get(Integer.valueOf(i)));
/*     */     }
/* 389 */     if ((list != null) && (list.size() > 0))
/* 390 */       Collections.sort(list, new GKNodeCateManager.GKNodeCategoryComparator());
/* 391 */     return list;
/*     */   }
/*     */ 
/*     */   public static String getAllChildCategoryIDS(String nodcat_ids)
/*     */   {
/* 401 */     String ids = "";
/* 402 */     String[] tempA = nodcat_ids.split(",");
/* 403 */     for (int i = 0; i < tempA.length; i++)
/*     */     {
/* 405 */       String c_ids = getAllChildCategoryIDS(Integer.parseInt(tempA[i]));
/* 406 */       if ((c_ids != null) && (!"".equals(c_ids)))
/* 407 */         ids = ids + "," + c_ids;
/*     */     }
/* 409 */     if (ids.length() > 0) {
/* 410 */       ids = ids.substring(1);
/*     */     }
/* 412 */     return ids;
/*     */   }
/*     */ 
/*     */   public static String getAllChildCategoryIDS(int nodcat_id)
/*     */   {
/* 422 */     GKNodeCategory gnc = getNodeCategoryBean(nodcat_id);
/* 423 */     if (gnc != null)
/*     */     {
/* 425 */       String cat_position = gnc.getNod_position();
/* 426 */       Set set = gnc_map.keySet();
/* 427 */       String cat_ids = "";
/* 428 */       for (Iterator localIterator = set.iterator(); localIterator.hasNext(); ) { int i = ((Integer)localIterator.next()).intValue();
/* 429 */         gnc = (GKNodeCategory)gnc_map.get(Integer.valueOf(i));
/* 430 */         if ((gnc.getNod_position().startsWith(cat_position)) && (!cat_position.equals(gnc.getNod_position())))
/* 431 */           cat_ids = cat_ids + "," + gnc.getNodcat_id();
/*     */       }
/* 433 */       if (cat_ids.length() > 0)
/* 434 */         cat_ids = cat_ids.substring(1);
/* 435 */       return cat_ids;
/*     */     }
/* 437 */     return null;
/*     */   }
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/* 461 */     System.out.println(getGKNodeTreebyCateID(0));
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.zwgk.node.GKNodeCateManager
 * JD-Core Version:    0.6.2
 */