/*     */ package com.cicro.wcm.services.system.ware;
/*     */ 
/*     */ import com.cicro.util.jconfig.JconfigUtil;
/*     */ import com.cicro.util.jconfig.JconfigUtilContainer;
/*     */ import com.cicro.wcm.bean.control.SiteBean;
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.bean.system.ware.WareBean;
/*     */ import com.cicro.wcm.bean.system.ware.WareCategoryBean;
/*     */ import com.cicro.wcm.catchs.ISyncCatch;
/*     */ import com.cicro.wcm.catchs.SyncCatchHandl;
/*     */ import com.cicro.wcm.dao.system.ware.WareCategoryDAO;
/*     */ import com.cicro.wcm.services.control.site.SiteManager;
/*     */ import com.cicro.wcm.services.org.role.RoleUserManager;
/*     */ import java.io.PrintStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ 
/*     */ public class WareCategoryManager
/*     */   implements ISyncCatch
/*     */ {
/*  25 */   private static Map<String, WareCategoryBean> wareCate_map = new HashMap();
/*  26 */   private static String ware_list_path = JconfigUtilContainer.managerPagePath().getProperty("ware_update", "", "m_org_path");
/*     */ 
/*  28 */   static { reloadCatchHandl(); }
/*     */ 
/*     */ 
/*     */   public void reloadCatch()
/*     */   {
/*  33 */     reloadCatchHandl();
/*     */   }
/*     */ 
/*     */   public static void reloadCatchHandl()
/*     */   {
/*  38 */     List lt = WareCategoryDAO.getWareCategoryList();
/*  39 */     wareCate_map.clear();
/*  40 */     if (lt != null)
/*     */     {
/*  42 */       for (int i = 0; i < lt.size(); i++)
/*     */       {
/*  44 */         WareCategoryBean wcb = (WareCategoryBean)lt.get(i);
/*  45 */         String key = wcb.getId();
/*  46 */         wareCate_map.put(key, wcb);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void reloadMap()
/*     */   {
/*  56 */     SyncCatchHandl.reladCatchForRMI("com.cicro.wcm.services.system.ware.WareCategoryManager");
/*     */   }
/*     */ 
/*     */   public static List<WareCategoryBean> getWCategoryListBySite(String site_id)
/*     */   {
/*  66 */     List l = new ArrayList();
/*  67 */     Set s = wareCate_map.keySet();
/*  68 */     for (String i : s)
/*     */     {
/*  70 */       WareCategoryBean wcb = (WareCategoryBean)wareCate_map.get(i);
/*  71 */       if (site_id.equals(wcb.getSite_id()))
/*     */       {
/*  73 */         l.add(wcb);
/*     */       }
/*     */     }
/*  76 */     return l;
/*     */   }
/*     */ 
/*     */   public static String getJSONTreeBySiteUser(int user_id, String site_id)
/*     */   {
/*  87 */     Map m = new HashMap();
/*  88 */     m.put("site_id", site_id);
/*  89 */     m.put("app_id", "cms");
/*     */ 
/*  91 */     List wcb_list = new ArrayList();
/*     */ 
/*  94 */     if (RoleUserManager.isSiteManager(user_id, "cms", site_id))
/*  95 */       wcb_list = getWCategoryListBySite(site_id);
/*     */     else
/*  97 */       wcb_list = getWCategoryListBySiteUsers(user_id, site_id);
/*  98 */     SiteBean stb = SiteManager.getSiteBeanBySiteID(site_id);
/*     */ 
/* 100 */     String rootName = "root";
/* 101 */     if (stb != null)
/*     */     {
/* 103 */       rootName = stb.getSite_name();
/*     */     }
/* 105 */     String child_str = "";
/* 106 */     String json_str = "[{\"id\":0,\"text\":\"" + rootName + "\"";
/* 107 */     child_str = getChildStrBySpecificList(getChildListBySpecificList("0", wcb_list), wcb_list, m);
/* 108 */     if ((child_str != null) && (!"".equals(child_str)))
/*     */     {
/* 110 */       json_str = json_str + ",\"children\":[" + child_str + "]";
/*     */     }
/* 112 */     json_str = json_str + "}]";
/* 113 */     return json_str;
/*     */   }
/*     */ 
/*     */   private static String getChildStrBySpecificList(List<WareCategoryBean> lt, List<WareCategoryBean> sp_list, Map<String, String> m)
/*     */   {
/* 124 */     String json_str = "";
/* 125 */     if ((lt != null) && (lt.size() > 0))
/*     */     {
/* 127 */       for (int i = 0; i < lt.size(); i++)
/*     */       {
/* 129 */         List child_o_list = getChildListBySpecificList(((WareCategoryBean)lt.get(i)).getWcat_id(), sp_list);
/* 130 */         String ware_str = getWareJsonStr(((WareCategoryBean)lt.get(i)).getWcat_id(), m);
/* 131 */         if (((child_o_list != null) && (child_o_list.size() > 0)) || ((ware_str != null) && (!"".equals(ware_str))))
/*     */         {
/* 133 */           json_str = json_str + "{";
/* 134 */           json_str = json_str + "\"id\":" + ((WareCategoryBean)lt.get(i)).getWcat_id() + ",\"text\":\"" + ((WareCategoryBean)lt.get(i)).getWcat_name() + "\"";
/*     */ 
/* 136 */           String children_str = getChildStrBySpecificList(child_o_list, sp_list, m);
/* 137 */           if ((children_str != null) && (!"".equals(children_str)))
/*     */           {
/* 139 */             if ((ware_str != null) && (!"".equals(ware_str))) {
/* 140 */               children_str = children_str + "," + ware_str;
/*     */             }
/* 142 */             json_str = json_str + ",\"state\":'closed',\"children\":[" + children_str + "]";
/*     */           }
/* 146 */           else if ((ware_str != null) && (!"".equals(ware_str))) {
/* 147 */             json_str = json_str + ",\"state\":'closed',\"children\":[" + ware_str + "]";
/*     */           }
/* 149 */           json_str = json_str + "}";
/* 150 */           if (i + 1 != lt.size())
/* 151 */             json_str = json_str + ",";
/*     */         }
/*     */       }
/*     */     }
/* 155 */     if (json_str.endsWith(","))
/* 156 */       json_str = json_str.substring(0, json_str.length() - 1);
/* 157 */     return json_str;
/*     */   }
/*     */ 
/*     */   public static String getWareJsonStr(String wcat_id, Map<String, String> m)
/*     */   {
/* 162 */     List wl = WareManager.getWareList(wcat_id, m);
/* 163 */     if ((wl != null) && (wl.size() > 0))
/*     */     {
/* 165 */       String str = "";
/* 166 */       for (int i = 0; i < wl.size(); i++)
/*     */       {
/* 168 */         if (((WareBean)wl.get(i)).getWare_type() != 1)
/*     */         {
/* 170 */           str = str + ",{";
/* 171 */           str = str + "\"id\":" + ((WareBean)wl.get(i)).getWare_id() + ",\"text\":\"" + ((WareBean)wl.get(i)).getWare_name() + "\",\"attributes\":{\"url\":\"" + 
/* 172 */             ware_list_path + "?wareID=" + ((WareBean)wl.get(i)).getWare_id() + "\"}";
/* 173 */           str = str + "}";
/*     */         }
/*     */       }
/* 176 */       if ((str != null) && (!"".equals(str)))
/* 177 */         str = str.substring(1);
/* 178 */       return str;
/*     */     }
/* 180 */     return null;
/*     */   }
/*     */ 
/*     */   public static List<WareCategoryBean> getChildListBySpecificList(String parent_id, List<WareCategoryBean> sp_list)
/*     */   {
/* 191 */     List child_list = new ArrayList();
/* 192 */     if ((sp_list != null) && (sp_list.size() > 0))
/*     */     {
/* 194 */       for (int i = 0; i < sp_list.size(); i++)
/*     */       {
/* 196 */         if (parent_id.equals(((WareCategoryBean)sp_list.get(i)).getParent_id()))
/* 197 */           child_list.add((WareCategoryBean)sp_list.get(i));
/*     */       }
/*     */     }
/* 200 */     Collections.sort(child_list, new WareCategoryManager.WareCateComparator());
/* 201 */     return child_list;
/*     */   }
/*     */ 
/*     */   public static List<WareCategoryBean> getWCategoryListBySiteUsers(int user_id, String site_id)
/*     */   {
/* 212 */     List wcb_list = new ArrayList();
/* 213 */     Set wcat_set = WareReleUserManager.getWCatIDByUser(user_id, site_id);
/*     */ 
/* 215 */     Iterator it = wcat_set.iterator();
/*     */     String[] tempA;
/*     */     int i;
/* 216 */     for (; it.hasNext(); 
/* 223 */       i < tempA.length - 1)
/*     */     {
/* 217 */       WareCategoryBean wcb = (WareCategoryBean)it.next();
/* 218 */       wcb_list.add(wcb);
/* 219 */       String position = wcb.getWcat_position();
/* 220 */       position = position.substring(1, position.length() - 1);
/* 221 */       tempA = position.split("\\$");
/*     */ 
/* 223 */       i = 0; continue;
/*     */ 
/* 225 */       WareCategoryBean w_bean = getWareCteBeanByWID(tempA[i], site_id);
/* 226 */       if ((w_bean != null) && (!wcb_list.contains(w_bean)))
/*     */       {
/* 228 */         wcb_list.add(w_bean);
/*     */       }
/* 223 */       i++;
/*     */     }
/*     */ 
/* 232 */     return wcb_list;
/*     */   }
/*     */ 
/*     */   public static WareCategoryBean getWareCteBeanByWID(String wcat_id, String site_id)
/*     */   {
/* 243 */     Set s = wareCate_map.keySet();
/* 244 */     for (String i : s)
/*     */     {
/* 246 */       WareCategoryBean wcb = (WareCategoryBean)wareCate_map.get(i);
/* 247 */       if ((wcat_id.equals(wcb.getWcat_id())) && (site_id.equals(wcb.getSite_id())))
/*     */       {
/* 249 */         return wcb;
/*     */       }
/*     */     }
/* 252 */     return null;
/*     */   }
/*     */ 
/*     */   public static List<WareCategoryBean> getWareCateList(String id, Map<String, String> mp)
/*     */   {
/* 263 */     return getChildList(id, mp);
/*     */   }
/*     */ 
/*     */   public static WareCategoryBean getWareCategoryByID(String id)
/*     */   {
/* 273 */     WareCategoryBean wcb = (WareCategoryBean)wareCate_map.get(id);
/* 274 */     if (wcb == null)
/*     */     {
/* 276 */       reloadMap();
/* 277 */       wcb = (WareCategoryBean)wareCate_map.get(id);
/*     */     }
/* 279 */     return wcb;
/*     */   }
/*     */ 
/*     */   public static boolean insertWareCate(WareCategoryBean wcb, SettingLogsBean stl)
/*     */   {
/* 290 */     WareCategoryBean parentBean = (WareCategoryBean)wareCate_map.get(wcb.getParent_id());
/* 291 */     if (parentBean != null)
/*     */     {
/* 294 */       wcb.setWcat_level(parentBean.getWcat_level() + 1);
/*     */ 
/* 296 */       wcb.setWcat_position(parentBean.getWcat_position());
/*     */     }
/*     */ 
/* 299 */     if (WareCategoryDAO.insertWareCate(wcb, stl))
/*     */     {
/* 301 */       reloadMap();
/* 302 */       return true;
/*     */     }
/*     */ 
/* 306 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateWareCategory(WareCategoryBean wcb, SettingLogsBean stl)
/*     */   {
/* 318 */     if (WareCategoryDAO.updateWareCate(wcb, stl))
/*     */     {
/* 320 */       reloadMap();
/* 321 */       return true;
/*     */     }
/*     */ 
/* 325 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean saveSort(String ids, SettingLogsBean stl)
/*     */   {
/* 337 */     boolean flg = true;
/* 338 */     String[] arrIDS = ids.split(",");
/* 339 */     WareCategoryBean wcb = new WareCategoryBean();
/* 340 */     for (int i = 0; i < arrIDS.length; i++)
/*     */     {
/* 342 */       wcb.setId(arrIDS[i]);
/* 343 */       wcb.setSort_id(i);
/* 344 */       if (!WareCategoryDAO.saveWareCateSort(wcb, stl))
/*     */       {
/* 346 */         flg = false;
/*     */       }
/*     */     }
/* 349 */     reloadMap();
/* 350 */     return flg;
/*     */   }
/*     */ 
/*     */   public static String getAllChildCateIDS(Map<String, String> mp)
/*     */   {
/* 361 */     String old_ids = (String)mp.get("id");
/* 362 */     String[] arrIDS = old_ids.split(",");
/* 363 */     String ids = "";
/* 364 */     for (int i = 0; i < arrIDS.length; i++)
/*     */     {
/* 366 */       ids = ids + getAllChildIDS(arrIDS[i], mp);
/*     */     }
/*     */ 
/* 369 */     if (ids.startsWith(","))
/*     */     {
/* 371 */       ids = ids.substring(1);
/*     */     }
/* 373 */     return ids;
/*     */   }
/*     */ 
/*     */   public static boolean deleteWareCategory(Map<String, String> mp, SettingLogsBean stl)
/*     */   {
/* 387 */     String ids = getAllChildCateIDS(mp);
/* 388 */     mp.put("id", ids);
/*     */ 
/* 390 */     if (WareCategoryDAO.deleteWareCate(mp, stl))
/*     */     {
/* 392 */       reloadMap();
/*     */ 
/* 394 */       WareManager.deleteWareByWcatIDS(mp, stl);
/*     */ 
/* 396 */       WareReleUserManager.deleteWRUByCat((String)mp.get("id"), (String)mp.get("site_id"));
/* 397 */       return true;
/*     */     }
/*     */ 
/* 401 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean moveCategory(String id, String parent_id, SettingLogsBean stl)
/*     */   {
/* 414 */     boolean flg = true;
/* 415 */     WareCategoryBean bean = getWareCategoryByID(id);
/* 416 */     WareCategoryBean parentBean = getWareCategoryByID(parent_id);
/*     */ 
/* 418 */     bean.setParent_id(parent_id);
/* 419 */     bean.setWcat_level(parentBean.getWcat_level() + 1);
/* 420 */     bean.setWcat_position(parentBean.getWcat_position() + "$" + bean.getWcat_id());
/*     */ 
/* 422 */     if (WareCategoryDAO.updateWareCate(bean, stl))
/*     */     {
/* 424 */       Map mp = new HashMap();
/* 425 */       mp.put("site_id", bean.getSite_id());
/* 426 */       mp.put("app_id", bean.getApp_id());
/* 427 */       flg = moveChildList(bean, mp, stl) ? flg : false;
/*     */     }
/* 429 */     reloadMap();
/* 430 */     return flg;
/*     */   }
/*     */ 
/*     */   private static boolean moveChildList(WareCategoryBean parentBean, Map<String, String> mp, SettingLogsBean stl)
/*     */   {
/* 442 */     boolean flg = true;
/* 443 */     List lt = getChildList(parentBean.getId(), mp);
/* 444 */     if ((lt != null) && (lt.size() > 0))
/*     */     {
/* 446 */       for (int i = 0; i < lt.size(); i++)
/*     */       {
/* 448 */         WareCategoryBean wcb = (WareCategoryBean)lt.get(i);
/* 449 */         wcb.setWcat_level(parentBean.getWcat_level() + 1);
/* 450 */         wcb.setWcat_position(parentBean.getWcat_position() + "$" + wcb.getId());
/* 451 */         flg = WareCategoryDAO.updateWareCate(wcb, stl) ? flg : false;
/* 452 */         flg = moveChildList(wcb, mp, stl) ? flg : false;
/*     */       }
/*     */     }
/* 455 */     return flg;
/*     */   }
/*     */ 
/*     */   private static String getAllChildIDS(String id, Map<String, String> mp)
/*     */   {
/* 460 */     String ret = "," + id;
/* 461 */     List lt = getChildList(id, mp);
/* 462 */     if ((lt != null) && (lt.size() > 0))
/*     */     {
/* 464 */       for (int i = 0; i < lt.size(); i++)
/*     */       {
/* 466 */         ret = ret + getAllChildIDS(((WareCategoryBean)lt.get(i)).getId(), mp);
/*     */       }
/*     */     }
/* 469 */     return ret;
/*     */   }
/*     */ 
/*     */   public static String getJSONTreeStr(Map<String, String> mp)
/*     */   {
/* 478 */     String site_id = mp.get("site_id") == null ? "" : (String)mp.get("site_id");
/* 479 */     SiteBean stb = SiteManager.getSiteBeanBySiteID(site_id);
/*     */ 
/* 481 */     String rootName = "root";
/* 482 */     if (stb != null)
/*     */     {
/* 484 */       rootName = stb.getSite_name();
/*     */     }
/*     */ 
/* 487 */     String child_str = "";
/* 488 */     String json_str = "[{\"id\":0,\"text\":\"" + rootName + "\"";
/* 489 */     child_str = getJSONTreeChildStr(getChildList("0", mp), mp);
/* 490 */     if ((child_str != null) && (!"".equals(child_str)))
/*     */     {
/* 492 */       json_str = json_str + ",\"children\":[" + child_str + "]";
/*     */     }
/* 494 */     json_str = json_str + "}]";
/* 495 */     return json_str;
/*     */   }
/*     */ 
/*     */   private static String getJSONTreeChildStr(List<WareCategoryBean> lt, Map<String, String> mp)
/*     */   {
/* 506 */     String json_str = "";
/* 507 */     if ((lt != null) && (lt.size() > 0))
/*     */     {
/* 509 */       for (int i = 0; i < lt.size(); i++)
/*     */       {
/* 511 */         json_str = json_str + "{";
/* 512 */         json_str = json_str + "\"id\":" + ((WareCategoryBean)lt.get(i)).getWcat_id() + ",\"text\":\"" + ((WareCategoryBean)lt.get(i)).getWcat_name() + "\"";
/* 513 */         List child_o_list = getChildList(((WareCategoryBean)lt.get(i)).getWcat_id(), mp);
/* 514 */         if ((child_o_list != null) && (child_o_list.size() > 0))
/* 515 */           json_str = json_str + ",\"children\":[" + getJSONTreeChildStr(child_o_list, mp) + "]";
/* 516 */         json_str = json_str + "}";
/* 517 */         if (i + 1 != lt.size())
/* 518 */           json_str = json_str + ",";
/*     */       }
/*     */     }
/* 521 */     return json_str;
/*     */   }
/*     */ 
/*     */   private static List<WareCategoryBean> getChildList(String id, Map<String, String> mp)
/*     */   {
/* 531 */     List retList = new ArrayList();
/* 532 */     Iterator it = wareCate_map.values().iterator();
/* 533 */     while (it.hasNext())
/*     */     {
/* 535 */       WareCategoryBean wcb = (WareCategoryBean)it.next();
/* 536 */       if ((id.equals(wcb.getParent_id())) && (isSameAppAndSite(mp, wcb)))
/*     */       {
/* 538 */         retList.add(wcb);
/*     */       }
/*     */     }
/* 541 */     Collections.sort(retList, new WareCategoryManager.WareCateComparator());
/* 542 */     return retList;
/*     */   }
/*     */ 
/*     */   private static boolean isSameAppAndSite(Map<String, String> mp, WareCategoryBean wcb)
/*     */   {
/* 553 */     boolean sflg = false;
/* 554 */     boolean aflg = false;
/* 555 */     String site_id = (String)mp.get("site_id");
/* 556 */     String app_id = (String)mp.get("app_id");
/*     */ 
/* 559 */     if ("".equals(site_id))
/*     */     {
/* 561 */       sflg = true;
/*     */     }
/* 563 */     else if (site_id.equals(wcb.getSite_id()))
/*     */     {
/* 565 */       sflg = true;
/*     */     }
/*     */ 
/* 569 */     if (app_id.equals(wcb.getApp_id()))
/*     */     {
/* 571 */       aflg = true;
/*     */     }
/*     */     else
/*     */     {
/* 575 */       aflg = false;
/*     */     }
/* 577 */     aflg = true;
/* 578 */     return (sflg) && (aflg);
/*     */   }
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/* 608 */     System.out.println(getJSONTreeBySiteUser(1, "HIWCM8888"));
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.system.ware.WareCategoryManager
 * JD-Core Version:    0.6.2
 */