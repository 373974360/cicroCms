/*     */ package com.cicro.wcm.services.extendfunction.knowledgeTab;
/*     */ 
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.catchs.ISyncCatch;
/*     */ import com.cicro.wcm.catchs.SyncCatchHandl;

/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
import java.util.Comparator;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ 
/*     */ public class KnowledgeCateManager
/*     */   implements ISyncCatch
/*     */ {
/*  21 */   private static Map<String, KnowledgeCateBean> kCate_map = new HashMap();
/*     */ 
/*     */   static {
/*  24 */     reloadCatchHandl();
/*     */   }
/*     */ 
/*     */   public void reloadCatch()
/*     */   {
/*  29 */     reloadCatchHandl();
/*     */   }
/*     */ 
/*     */   public static void reloadCatchHandl()
/*     */   {
/*  34 */     List lt = KnowledgeCateDAO.getKCategoryList();
/*  35 */     kCate_map.clear();
/*  36 */     if (lt != null)
/*     */     {
/*  38 */       for (int i = 0; i < lt.size(); i++)
/*     */       {
/*  40 */         KnowledgeCateBean wcb = (KnowledgeCateBean)lt.get(i);
/*  41 */         String key = wcb.getId()+"";
/*  42 */         kCate_map.put(key, wcb);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void reloadMap()
/*     */   {
/*  52 */     SyncCatchHandl.reladCatchForRMI("com.cicro.wcm.services.extendfunction.knowledgeTab.KnowledgeCateManager");
/*     */   }
/*     */ 
/*     */   public static List<KnowledgeCateBean> getWCategoryListBySite(String site_id)
/*     */   {
/*  62 */     List l = new ArrayList();
/*  63 */     Set<String> s = kCate_map.keySet();
/*  64 */     for (String i : s)
/*     */     {
/*  66 */       KnowledgeCateBean wcb = (KnowledgeCateBean)kCate_map.get(i);
/*  67 */       if (site_id.equals(wcb.getSite_id()))
/*     */       {
/*  69 */         l.add(wcb);
/*     */       }
/*     */     }
/*  72 */     return l;
/*     */   }
/*     */ 
/*     */   public static List<KnowledgeCateBean> getChildListBySpecificList(String parent_id, List<KnowledgeCateBean> sp_list)
/*     */   {
/*  83 */     List child_list = new ArrayList();
/*  84 */     if ((sp_list != null) && (sp_list.size() > 0))
/*     */     {
/*  86 */       for (int i = 0; i < sp_list.size(); i++)
/*     */       {
/*  88 */         if (parent_id.equals(((KnowledgeCateBean)sp_list.get(i)).getKparent_id()))
/*  89 */           child_list.add((KnowledgeCateBean)sp_list.get(i));
/*     */       }
/*     */     }
/*  92 */     Collections.sort(child_list, new WareCateComparator());
/*  93 */     return child_list;
/*     */   }
/*     */ 
/*     */   public static KnowledgeCateBean getKCteBeanByWID(String kcat_id, String site_id)
/*     */   {
/* 106 */     Set<String> s = kCate_map.keySet();
/* 107 */     for (String i : s)
/*     */     {
/* 109 */       KnowledgeCateBean wcb = (KnowledgeCateBean)kCate_map.get(i);
/* 110 */       if ((kcat_id.equals(wcb.getKcat_id())) && (site_id.equals(wcb.getSite_id())))
/*     */       {
/* 112 */         return wcb;
/*     */       }
/*     */     }
/* 115 */     return null;
/*     */   }
/*     */ 
/*     */   public static List<KnowledgeCateBean> getWareCateList(String id, Map<String, String> mp)
/*     */   {
/* 126 */     return getChildList(id, mp);
/*     */   }
/*     */ 
/*     */   public static KnowledgeCateBean getKCategoryByID(String id)
/*     */   {
/* 136 */     KnowledgeCateBean wcb = (KnowledgeCateBean)kCate_map.get(id);
/* 137 */     if (wcb == null)
/*     */     {
/* 139 */       reloadMap();
/* 140 */       wcb = (KnowledgeCateBean)kCate_map.get(id);
/*     */     }
/* 142 */     return wcb;
/*     */   }
/*     */ 
/*     */   public static boolean insertWareCate(KnowledgeCateBean wcb, SettingLogsBean stl)
/*     */   {
/* 153 */     KnowledgeCateBean parentBean = (KnowledgeCateBean)kCate_map.get(wcb.getKparent_id());
/* 154 */     if (parentBean != null)
/*     */     {
/* 157 */       wcb.setKcat_level(parentBean.getKcat_level() + 1);
/*     */ 
/* 159 */       wcb.setKcat_position(parentBean.getKcat_position().trim());
/*     */     }
/*     */     else {
/* 162 */       wcb.setKcat_level(1);
/*     */ 
/* 164 */       wcb.setKcat_position("");
/*     */     }
/* 166 */     if (KnowledgeCateDAO.insertKCate(wcb, stl))
/*     */     {
/* 168 */       reloadMap();
/* 169 */       return true;
/*     */     }
/*     */ 
/* 173 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateWareCategory(KnowledgeCateBean wcb, SettingLogsBean stl)
/*     */   {
/* 185 */     if (KnowledgeCateDAO.updateKCate(wcb, stl))
/*     */     {
/* 187 */       reloadMap();
/* 188 */       return true;
/*     */     }
/*     */ 
/* 192 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean saveSort(String ids, SettingLogsBean stl)
/*     */   {
/* 204 */     boolean flg = true;
/* 205 */     String[] arrIDS = ids.split(",");
/* 206 */     KnowledgeCateBean wcb = new KnowledgeCateBean();
/* 207 */     for (int i = 0; i < arrIDS.length; i++)
/*     */     {
/* 209 */       wcb.setId(Integer.parseInt(arrIDS[i]));
/* 210 */       wcb.setSort_id(i);
/* 211 */       if (!KnowledgeCateDAO.saveKCateSort(wcb, stl))
/*     */       {
/* 213 */         flg = false;
/*     */       }
/*     */     }
/* 216 */     reloadMap();
/* 217 */     return flg;
/*     */   }
/*     */ 
/*     */   public static String getAllChildCateIDS(Map<String, String> mp)
/*     */   {
/* 228 */     String old_ids = (String)mp.get("id");
/* 229 */     String[] arrIDS = old_ids.split(",");
/* 230 */     String ids = "";
/* 231 */     for (int i = 0; i < arrIDS.length; i++)
/*     */     {
/* 233 */       ids = ids + getAllChildIDS(arrIDS[i], mp);
/*     */     }
/*     */ 
/* 236 */     if (ids.startsWith(","))
/*     */     {
/* 238 */       ids = ids.substring(1);
/*     */     }
/* 240 */     return ids;
/*     */   }
/*     */ 
/*     */   public static boolean deleteWareCategory(Map<String, String> mp, SettingLogsBean stl)
/*     */   {
/* 254 */     String ids = getAllChildCateIDS(mp);
/* 255 */     mp.put("id", ids);
/*     */ 
/* 257 */     if (KnowledgeCateDAO.deleteKCate(mp, stl))
/*     */     {
/* 259 */       reloadMap();
/* 260 */       return true;
/*     */     }
/*     */ 
/* 264 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean moveCategory(String id, String parent_id, SettingLogsBean stl)
/*     */   {
/* 277 */     boolean flg = true;
/* 278 */     KnowledgeCateBean bean = getKCategoryByID(id);
/* 279 */     KnowledgeCateBean parentBean = getKCategoryByID(parent_id);
/*     */ 
/* 281 */     bean.setKparent_id(parent_id);
/* 282 */     bean.setKcat_level(parentBean.getKcat_level() + 1);
/* 283 */     bean.setKcat_position(parentBean.getKcat_position() + "$" + bean.getKcat_id());
/*     */ 
/* 285 */     if (KnowledgeCateDAO.updateKCate(bean, stl))
/*     */     {
/* 287 */       Map mp = new HashMap();
/* 288 */       mp.put("site_id", bean.getSite_id());
/* 289 */       mp.put("app_id", bean.getApp_id());
/* 290 */       flg = moveChildList(bean, mp, stl) ? flg : false;
/*     */     }
/* 292 */     reloadMap();
/* 293 */     return flg;
/*     */   }
/*     */ 
/*     */   private static boolean moveChildList(KnowledgeCateBean parentBean, Map<String, String> mp, SettingLogsBean stl)
/*     */   {
/* 305 */     boolean flg = true;
/* 306 */     List lt = getChildList(parentBean.getId()+"", mp);
/* 307 */     if ((lt != null) && (lt.size() > 0))
/*     */     {
/* 309 */       for (int i = 0; i < lt.size(); i++)
/*     */       {
/* 311 */         KnowledgeCateBean wcb = (KnowledgeCateBean)lt.get(i);
/* 312 */         wcb.setKcat_level(parentBean.getKcat_level() + 1);
/* 313 */         wcb.setKcat_position(parentBean.getKcat_position() + "$" + wcb.getId());
/* 314 */         flg = KnowledgeCateDAO.updateKCate(wcb, stl) ? flg : false;
/* 315 */         flg = moveChildList(wcb, mp, stl) ? flg : false;
/*     */       }
/*     */     }
/* 318 */     return flg;
/*     */   }
/*     */ 
/*     */   private static String getAllChildIDS(String id, Map<String, String> mp)
/*     */   {
/* 323 */     String ret = "," + id;
/* 324 */     List lt = getChildList(id, mp);
/* 325 */     if ((lt != null) && (lt.size() > 0))
/*     */     {
/* 327 */       for (int i = 0; i < lt.size(); i++)
/*     */       {
/* 329 */         ret = ret + getAllChildIDS(new StringBuilder(String.valueOf(((KnowledgeCateBean)lt.get(i)).getId())).toString(), mp);
/*     */       }
/*     */     }
/* 332 */     return ret;
/*     */   }
/*     */ 
/*     */   public static String getJSONTreeStr(String site_id, String app_id)
/*     */   {
/* 341 */     Map mp = new HashMap();
/* 342 */     mp.put("site_id", site_id);
/* 343 */     if (app_id != "") {
/* 344 */       mp.put("app_id", app_id);
/*     */     }
/*     */ 
/* 347 */     String rootName = "知识库标签管理";
/*     */ 
/* 352 */     String child_str = "";
/* 353 */     String json_str = "[{\"id\":0,\"text\":\"" + rootName + "\"";
/* 354 */     child_str = getJSONTreeChildStr(getChildList("0", mp), mp);
/* 355 */     if ((child_str != null) && (!"".equals(child_str)))
/*     */     {
/* 357 */       json_str = json_str + ",\"children\":[" + child_str + "]";
/*     */     }
/* 359 */     json_str = json_str + "}]";
/* 360 */     return json_str;
/*     */   }
/*     */ 
/*     */   private static String getJSONTreeChildStr(List<KnowledgeCateBean> lt, Map<String, String> mp)
/*     */   {
/* 371 */     String json_str = "";
/* 372 */     if ((lt != null) && (lt.size() > 0))
/*     */     {
/* 374 */       for (int i = 0; i < lt.size(); i++)
/*     */       {
/* 376 */         json_str = json_str + "{";
/* 377 */         json_str = json_str + "\"id\":" + ((KnowledgeCateBean)lt.get(i)).getKcat_id() + ",\"text\":\"" + ((KnowledgeCateBean)lt.get(i)).getKcat_name() + "\"";
/* 378 */         List child_o_list = getChildList(((KnowledgeCateBean)lt.get(i)).getKcat_id(), mp);
/* 379 */         if ((child_o_list != null) && (child_o_list.size() > 0))
/* 380 */           json_str = json_str + ",\"children\":[" + getJSONTreeChildStr(child_o_list, mp) + "]";
/* 381 */         json_str = json_str + "}";
/* 382 */         if (i + 1 != lt.size())
/* 383 */           json_str = json_str + ",";
/*     */       }
/*     */     }
/* 386 */     return json_str;
/*     */   }
/*     */ 
/*     */   private static List<KnowledgeCateBean> getChildList(String id, Map<String, String> mp)
/*     */   {
/* 396 */     List retList = new ArrayList();
/* 397 */     Iterator it = kCate_map.values().iterator();
/* 398 */     while (it.hasNext())
/*     */     {
/* 400 */       KnowledgeCateBean wcb = (KnowledgeCateBean)it.next();
/* 401 */       if ((id.equals(wcb.getKparent_id())) && (isSameAppAndSite(mp, wcb)))
/*     */       {
/* 403 */         retList.add(wcb);
/*     */       }
/*     */     }
/* 406 */     Collections.sort(retList, new WareCateComparator());
/* 407 */     return retList;
/*     */   }
/*     */ 
/*     */   private static boolean isSameAppAndSite(Map<String, String> mp, KnowledgeCateBean wcb)
/*     */   {
/* 418 */     boolean sflg = false;
/* 419 */     boolean aflg = false;
/* 420 */     String site_id = (String)mp.get("site_id");
/* 421 */     String app_id = (String)mp.get("app_id");
/*     */ 
/* 424 */     if ("".equals(site_id))
/*     */     {
/* 426 */       sflg = true;
/*     */     }
/* 428 */     else if (site_id.equals(wcb.getSite_id()))
/*     */     {
/* 430 */       sflg = true;
/*     */     }
/*     */ 
/* 442 */     aflg = true;
/* 443 */     return (sflg) && (aflg);
/*     */   }
/*     */ 
/*     */   public static String getKnowledgeCateBroJSONTreeStr(String site_id, String app_id)
/*     */   {
/* 476 */     Map mp = new HashMap();
/* 477 */     mp.put("site_id", site_id);
/* 478 */     if (app_id != "") {
/* 479 */       mp.put("app_id", app_id);
/*     */     }
/* 481 */     List list = getChildList("0", mp);
/* 482 */     return "[" + getKnowledgeCateBroJSONTreeStrHandl(list) + "]";
/*     */   }
/*     */ 
/*     */   public static String getKnowledgeCateBroJSONTreeStrHandl(List<KnowledgeCateBean> child_list)
/*     */   {
/* 492 */     String json_str = "";
/* 493 */     if ((child_list != null) && (child_list.size() > 0))
/*     */     {
/* 495 */       for (int i = 0; i < child_list.size(); i++)
/*     */       {
/* 497 */         json_str = json_str + ",{";
/* 498 */         List child_m_list = getChildList2(((KnowledgeCateBean)child_list.get(i)).getKcat_id());
/* 499 */         if ((child_m_list != null) && (child_m_list.size() > 0))
/*     */         {
/* 501 */           json_str = json_str + "\"id\":" + ((KnowledgeCateBean)child_list.get(i)).getKcat_id() + ",\"text\":\"" + ((KnowledgeCateBean)child_list.get(i)).getKcat_name() + "\",\"attributes\":{\"url\":\"\"}";
/* 502 */           json_str = json_str + ",\"state\":'closed'";
/* 503 */           json_str = json_str + ",\"children\":[" + getKnowledgeCateBroJSONTreeStrHandl(child_m_list) + "]";
/*     */         } else {
/* 505 */           json_str = json_str + "\"id\":" + ((KnowledgeCateBean)child_list.get(i)).getKcat_id() + ",\"text\":\"" + ((KnowledgeCateBean)child_list.get(i)).getKcat_name() + "\",\"attributes\":{\"url\":\"\"}";
/* 506 */         }json_str = json_str + "}";
/*     */       }
/*     */ 
/* 509 */       if ((json_str != null) && (!"".equals(json_str)))
/* 510 */         json_str = json_str.substring(1);
/*     */     }
/* 512 */     return json_str;
/*     */   }
/*     */ 
/*     */   private static List<KnowledgeCateBean> getChildList2(String id)
/*     */   {
/* 518 */     List retList = new ArrayList();
/* 519 */     Iterator it = kCate_map.values().iterator();
/* 520 */     while (it.hasNext())
/*     */     {
/* 522 */       KnowledgeCateBean wcb = (KnowledgeCateBean)it.next();
/* 523 */       if (id.equals(wcb.getKparent_id()))
/*     */       {
/* 525 */         retList.add(wcb);
/*     */       }
/*     */     }
/* 528 */     Collections.sort(retList, new WareCateComparator());
/* 529 */     return retList;
/*     */   }
/*     */ 
/*     */   public static String getChildListIdsByPid(String p_id)
/*     */   {
/* 536 */     String ids = "";
/* 537 */     Iterator it = kCate_map.values().iterator();
/* 538 */     while (it.hasNext())
/*     */     {
/* 540 */       KnowledgeCateBean wcb = (KnowledgeCateBean)it.next();
/* 541 */       if (wcb.getKparent_id().equals(p_id))
/*     */       {
/* 543 */         ids = ids + wcb.getKcat_id() + ",";
/*     */       }
/*     */     }
/* 546 */     if (ids.endsWith(","))
/*     */     {
/* 548 */       ids = ids.substring(0, ids.length() - 1);
/*     */     }
/* 550 */     return ids;
/*     */   }
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/*     */   }
/*     */ }

		class WareCateComparator
/*     */   implements Comparator<KnowledgeCateBean>
/*     */ {
/*     */   public int compare(KnowledgeCateBean o1, KnowledgeCateBean o2)
/*     */   {
/* 450 */     int flg = 0;
/* 451 */     if (o1.getSort_id() > o2.getSort_id())
/*     */     {
/* 453 */       flg = 1;
/*     */     }
/* 455 */     else if (o1.getSort_id() == o2.getSort_id())
/*     */     {
/* 457 */       flg = 0;
/*     */     }
/*     */     else
/*     */     {
/* 461 */       flg = -1;
/*     */     }
/* 463 */     return flg;
/*     */   }
/*     */ }

/* Location:           E:\公司产品包\定制包\extendfunction.zip
 * Qualified Name:     extendfunction.knowledgeTab.KnowledgeCateManager
 * JD-Core Version:    0.6.2
 */