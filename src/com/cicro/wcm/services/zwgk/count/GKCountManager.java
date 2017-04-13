/*     */ package com.cicro.wcm.services.zwgk.count;
/*     */ 
/*     */ import com.cicro.util.DateUtil;
/*     */ import com.cicro.wcm.bean.cms.category.CategoryBean;
/*     */ import com.cicro.wcm.bean.zwgk.count.GKCountBean;
/*     */ import com.cicro.wcm.bean.zwgk.node.GKNodeBean;
/*     */ import com.cicro.wcm.dao.zwgk.count.GKCountDAO;
/*     */ import com.cicro.wcm.services.cms.category.CategoryManager;
/*     */ import com.cicro.wcm.services.zwgk.node.GKNodeCateManager;
/*     */ import com.cicro.wcm.services.zwgk.node.GKNodeManager;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Calendar;
/*     */ import java.util.Collections;
/*     */ import java.util.GregorianCalendar;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.TreeMap;
/*     */ 
/*     */ public class GKCountManager
/*     */ {
/*     */   public static boolean CateInfoCounting()
/*     */   {
/*  40 */     return CateInfoCounting(null, "zwgk", "");
/*     */   }
/*     */ 
/*     */   public static boolean CateInfoCountingByDate(String day)
/*     */   {
/*  53 */     return CateInfoCounting(null, "zwgk", day);
/*     */   }
/*     */ 
/*     */   public static boolean CateInfoCounting(String site_id)
/*     */   {
/*  65 */     return CateInfoCounting(site_id, "zwgk", "");
/*     */   }
/*     */ 
/*     */   public static boolean CateInfoCounting(String site_id, String app_id, String time)
/*     */   {
/*  82 */     Map mp = new HashMap();
/*  83 */     if ((site_id != null) && ("".equals(site_id)));
/*  86 */     site_id = null;
/*     */ 
/*  88 */     mp.put("site_id", site_id);
/*     */ 
/*  90 */     if (("".equals(time)) || (time == null)) {
/*  91 */       Calendar c = new GregorianCalendar();
/*  92 */       c.add(5, -1);
/*     */ 
/*  94 */       time = DateUtil.getDateString(c.getTime());
/*  95 */       String start_day = time + " 00:00:00";
/*  96 */       String end_day = time + " 59:59:59";
/*  97 */       mp.put("start_day", start_day);
/*  98 */       mp.put("end_day", end_day);
/*     */     } else {
/* 100 */       time = time.substring(0, 10);
/* 101 */       String start_day = time + " 00:00:00";
/* 102 */       String end_day = time + " 59:59:59";
/* 103 */       mp.put("start_day", start_day);
/* 104 */       mp.put("end_day", end_day);
/*     */     }
/* 106 */     List lt = GKCountDAO.getCateInfoCount(mp);
/*     */ 
/* 108 */     mp.put("update_time", time);
/* 109 */     GKCountDAO.deleteGKCount(mp);
/* 110 */     return saveCateInfo(lt, time, app_id, site_id);
/*     */   }
/*     */ 
/*     */   public static List<GKCountBean> getGKCountList(String site_id)
/*     */   {
/* 121 */     Calendar c = new GregorianCalendar();
/* 122 */     c.add(5, -1);
/*     */ 
/* 124 */     String date = DateUtil.getDateString(c.getTime());
/* 125 */     return getGKCountList(site_id, date, date);
/*     */   }
/*     */ 
/*     */   public static List<GKCountBean> getGKCountList(String site_id, String startDate, String endDate)
/*     */   {
/* 141 */     String cat_id = null;
/* 142 */     return getGKCountList(site_id, startDate, endDate, cat_id);
/*     */   }
/*     */ 
/*     */   public static List<GKCountBean> getGKCountList(String site_id, String startDate, String endDate, String cat_id)
/*     */   {
/*     */     try
/*     */     {
/* 161 */       Map mp = new HashMap();
/* 162 */       mp.put("site_id", site_id);
/* 163 */       if ("".equals(startDate)) {
/* 164 */         startDate = null;
/*     */       }
/* 166 */       mp.put("start_day", startDate);
/*     */ 
/* 170 */       mp.put("end_day", endDate);
/*     */ 
/* 174 */       mp.put("cat_id", cat_id);
/*     */ 
/* 179 */       List all_list = GKCountDAO.getGkInfoCountByStatusANDNode(mp);
/*     */ 
/* 181 */       mp.put("gk_type", "0");
/* 182 */       List z_list = GKCountDAO.getGkInfoCountByStatusANDNode(mp);
/*     */ 
/* 184 */       mp.put("gk_type", "1");
/* 185 */       List b_list = GKCountDAO.getGkInfoCountByStatusANDNode(mp);
/* 186 */       for (GKCountBean gkNodeBeanAll : all_list) {
/* 187 */         int cat_ido = gkNodeBeanAll.getCat_id();
/*     */ 
/* 189 */         if ((z_list != null) && (z_list.size() > 0)) {
/* 190 */           for (GKCountBean gkCountBean : z_list) {
/* 191 */             if (cat_ido == gkCountBean.getCat_id()) {
/* 192 */               gkNodeBeanAll.setZ_count(gkCountBean.getInfo_count());
/* 193 */               break;
/*     */             }
/* 195 */             gkNodeBeanAll.setZ_count(0);
/*     */           }
/*     */         }
/*     */         else {
/* 199 */           gkNodeBeanAll.setZ_count(0);
/*     */         }
/*     */ 
/* 203 */         if ((b_list != null) && (b_list.size() > 0)) {
/* 204 */           for (GKCountBean gkCountBean : b_list) {
/* 205 */             if (cat_ido == gkCountBean.getCat_id()) {
/* 206 */               gkNodeBeanAll.setB_count(gkCountBean.getInfo_count());
/* 207 */               break;
/*     */             }
/* 209 */             gkNodeBeanAll.setB_count(0);
/*     */           }
/*     */         }
/*     */         else {
/* 213 */           gkNodeBeanAll.setB_count(0);
/*     */         }
/*     */ 
/* 217 */         gkNodeBeanAll.setY_count(gkNodeBeanAll.getInfo_count() - gkNodeBeanAll.getZ_count() - gkNodeBeanAll.getB_count());
/*     */       }
/*     */ 
/* 220 */       return addCatInfo(all_list, site_id);
/*     */     } catch (Exception e) {
/* 222 */       e.printStackTrace();
/* 223 */     }return null;
/*     */   }
/*     */ 
/*     */   private static List<GKCountBean> addCatInfo(List<GKCountBean> lt, String site_id)
/*     */   {
/* 240 */     List retlt = new ArrayList();
/* 241 */     Map mp = new HashMap();
/* 242 */     for (GKCountBean gkbean : lt) {
/* 243 */       mp.put(Integer.valueOf(gkbean.getCat_id()), gkbean);
/*     */     }
/* 245 */     List catlt = CategoryManager.getCategoryListBySiteID(
/* 246 */       site_id, 0);
/*     */ 
/* 248 */     for (CategoryBean cb : catlt) {
/* 249 */       GKCountBean bean = getCateChildList(cb, mp);
/* 250 */       retlt.add(bean);
/*     */     }
/*     */ 
/* 253 */     return retlt;
/*     */   }
/*     */ 
/*     */   private static GKCountBean getCateChildList(CategoryBean catbean, Map<Integer, GKCountBean> mp)
/*     */   {
/* 268 */     GKCountBean retbean = new GKCountBean();
/* 269 */     retbean.setCat_id(catbean.getCat_id());
/* 270 */     retbean.setCat_name(catbean.getCat_cname());
/*     */ 
/* 272 */     List sublist = CategoryManager.getAllChildCategoryList(
/* 273 */       catbean.getCat_id(), catbean.getSite_id());
/* 274 */     for (int i = 0; i < sublist.size(); i++) {
/* 275 */       if (((CategoryBean)sublist.get(i)).getCat_id() == catbean.getCat_id()) {
/* 276 */         sublist.remove(i);
/* 277 */         break;
/*     */       }
/*     */     }
/* 280 */     if (sublist.size() != 0) {
/* 281 */       retbean.setIs_leaf(false);
/* 282 */       List childList = new ArrayList();
/* 283 */       for (CategoryBean cb : sublist) {
/* 284 */         GKCountBean gkbean = getCateChildList(cb, mp);
/* 285 */         retbean.setB_count(retbean.getB_count() + gkbean.getB_count());
/* 286 */         retbean.setZ_count(retbean.getZ_count() + gkbean.getZ_count());
/* 287 */         retbean.setY_count(retbean.getY_count() + gkbean.getY_count());
/* 288 */         retbean.setInfo_count(retbean.getInfo_count() + 
/* 289 */           gkbean.getInfo_count());
/*     */ 
/* 291 */         childList.add(gkbean);
/*     */       }
/* 293 */       retbean.setChild_list(childList);
/*     */     }
/* 295 */     else if (mp.containsKey(Integer.valueOf(retbean.getCat_id()))) {
/* 296 */       GKCountBean countBean = (GKCountBean)mp.get(Integer.valueOf(retbean.getCat_id()));
/* 297 */       retbean.setB_count(countBean.getB_count());
/* 298 */       retbean.setZ_count(countBean.getZ_count());
/* 299 */       retbean.setY_count(countBean.getY_count());
/* 300 */       retbean.setInfo_count(countBean.getInfo_count());
/*     */     }
/*     */ 
/* 303 */     return retbean;
/*     */   }
/*     */ 
/*     */   private static boolean saveCateInfo(List<Map> lt, String updateTime, String appId, String siteId)
/*     */   {
/* 316 */     Map bean_mp = new HashMap();
/* 317 */     for (int i = 0; i < lt.size(); i++) {
/* 318 */       fillBean((Map)lt.get(i), bean_mp);
/*     */     }
/*     */ 
/* 321 */     boolean flg = true;
/* 322 */     for (GKCountBean bean : bean_mp.values()) {
/* 323 */       bean.setUpdate_time(updateTime);
/* 324 */       bean.setApp_id(appId);
/* 325 */       if ((siteId != null) && (!"".equals(siteId))) {
/* 326 */         bean.setSite_id(siteId);
/*     */       }
/* 328 */       int infoCount = bean.getB_count() + bean.getY_count() + 
/* 329 */         bean.getZ_count();
/* 330 */       bean.setInfo_count(infoCount);
/* 331 */       flg = GKCountDAO.insertGKCount(bean) ? flg : false;
/*     */     }
/* 333 */     return flg;
/*     */   }
/*     */ 
/*     */   private static void fillBean(Map mp, Map<String, GKCountBean> bean_mp)
/*     */   {
/* 346 */     String key = mp.get("CAT_ID").toString();
/*     */     GKCountBean bean;
/*     */     GKCountBean bean;
/* 348 */     if (bean_mp.containsKey(key)) {
/* 349 */       bean = (GKCountBean)bean_mp.get(key);
/*     */     } else {
/* 351 */       bean = new GKCountBean();
/* 352 */       if ((key != null) && (!"".equals(key)))
/* 353 */         bean.setCat_id(Integer.valueOf(key).intValue());
/* 354 */       if (mp.get("SITE_ID") != null)
/* 355 */         bean.setSite_id(mp.get("SITE_ID").toString());
/* 356 */       bean_mp.put(key, bean);
/*     */     }
/*     */ 
/* 359 */     String type = mp.get("GK_TYPE").toString();
/* 360 */     String cnt = mp.get("COUNT").toString();
/* 361 */     if ("0".equals(type))
/* 362 */       bean.setZ_count(Integer.valueOf(cnt).intValue());
/* 363 */     else if ("1".equals(type))
/* 364 */       bean.setB_count(Integer.valueOf(cnt).intValue());
/* 365 */     else if ("2".equals(type))
/* 366 */       bean.setY_count(Integer.valueOf(cnt).intValue());
/*     */   }
/*     */ 
/*     */   public static List<GKCountBean> getAllSiteGKCountList(Map<String, String> mp)
/*     */   {
/* 378 */     List ret = new ArrayList();
/*     */ 
/* 380 */     List lt = getAllSiteGKCountList((String)mp.get("start_day"), 
/* 381 */       (String)mp
/* 381 */       .get("end_day"), null);
/* 382 */     String sort_type = (String)mp.get("type");
/* 383 */     int sort_num = Integer.valueOf((String)mp.get("num")).intValue();
/*     */ 
/* 385 */     List ascList = sortGKCountBean(sort_type, lt);
/*     */ 
/* 387 */     for (int i = ret.size(); (i > ret.size() - sort_num) && (i > 0); i--) {
/* 388 */       ret.add((GKCountBean)ascList.get(i - 1));
/*     */     }
/*     */ 
/* 391 */     return ret;
/*     */   }
/*     */ 
/*     */   private static List<GKCountBean> sortGKCountBean(String sort_type, List<GKCountBean> lt)
/*     */   {
/* 405 */     TreeMap beanMap = new TreeMap();
/* 406 */     if ("info_count".equals(sort_type)) {
/* 407 */       for (GKCountBean gkbean : lt)
/* 408 */         beanMap.put(Integer.valueOf(gkbean.getInfo_count()), gkbean);
/*     */     }
/* 410 */     else if ("z_count".equals(sort_type)) {
/* 411 */       for (GKCountBean gkbean : lt)
/* 412 */         beanMap.put(Integer.valueOf(gkbean.getZ_count()), gkbean);
/*     */     }
/* 414 */     else if ("y_count".equals(sort_type)) {
/* 415 */       for (GKCountBean gkbean : lt)
/* 416 */         beanMap.put(Integer.valueOf(gkbean.getY_count()), gkbean);
/*     */     }
/* 418 */     else if ("b_count".equals(sort_type)) {
/* 419 */       for (GKCountBean gkbean : lt) {
/* 420 */         beanMap.put(Integer.valueOf(gkbean.getB_count()), gkbean);
/*     */       }
/*     */     }
/*     */ 
/* 424 */     List retList = new ArrayList();
/* 425 */     for (GKCountBean bean : beanMap.values()) {
/* 426 */       retList.add(bean);
/*     */     }
/* 428 */     return retList;
/*     */   }
/*     */ 
/*     */   public static List<GKCountBean> getAllSiteGKCountList(String startDate, String endDate, String node_ids)
/*     */   {
/*     */     try
/*     */     {
/* 478 */       Set set = new HashSet();
/*     */ 
/* 480 */       Map mp = new HashMap();
/* 481 */       mp.put("start_day", startDate);
/* 482 */       mp.put("end_day", endDate);
/* 483 */       if ((node_ids != null) && (node_ids != "")) {
/* 484 */         mp.put("site_ids", node_ids);
/* 485 */         String[] arr = node_ids.replace("'", "").split(",");
/* 486 */         for (String s : arr) {
/* 487 */           set.add(s);
/*     */         }
/*     */       }
/*     */ 
/* 491 */       Map cnt_mp = new HashMap();
/*     */ 
/* 494 */       List gkNodeList = GKNodeManager.getAllGKNodeList();
/*     */ 
/* 496 */       if (gkNodeList.size() == set.size()) {
/* 497 */         mp.remove("site_ids");
/*     */       }
/*     */ 
/* 500 */       mp.put("gk_type", "0");
/* 501 */       List z_list = GKCountDAO.getGkInfoCountByStatus(mp);
/*     */ 
/* 503 */       mp.put("gk_type", "1");
/* 504 */       List b_list = GKCountDAO.getGkInfoCountByStatus(mp);
/*     */ 
/* 506 */       mp.put("gk_type", "2");
/* 507 */       List y_list = GKCountDAO.getGkInfoCountByStatus(mp);
/* 508 */       for (GKNodeBean gkNodeBean : gkNodeList) {
/* 509 */         String site_id = gkNodeBean.getNode_id();
/* 510 */         GKCountBean countBean = new GKCountBean();
/* 511 */         countBean.setSite_id(site_id);
/*     */ 
/* 514 */         if ((z_list != null) && (z_list.size() > 0)) {
/* 515 */           for (GKCountBean gkCountBean : z_list) {
/* 516 */             if (site_id.equals(gkCountBean.getSite_id())) {
/* 517 */               countBean.setZ_count(gkCountBean.getInfo_count());
/* 518 */               break;
/*     */             }
/* 520 */             countBean.setZ_count(0);
/*     */           }
/*     */         }
/*     */         else {
/* 524 */           countBean.setZ_count(0);
/*     */         }
/*     */ 
/* 528 */         if ((b_list != null) && (b_list.size() > 0)) {
/* 529 */           for (GKCountBean gkCountBean : b_list) {
/* 530 */             if (site_id.equals(gkCountBean.getSite_id())) {
/* 531 */               countBean.setB_count(gkCountBean.getInfo_count());
/* 532 */               break;
/*     */             }
/* 534 */             countBean.setB_count(0);
/*     */           }
/*     */         }
/*     */         else {
/* 538 */           countBean.setB_count(0);
/*     */         }
/*     */ 
/* 542 */         if ((y_list != null) && (y_list.size() > 0)) {
/* 543 */           for (GKCountBean gkCountBean : y_list) {
/* 544 */             if (site_id.equals(gkCountBean.getSite_id())) {
/* 545 */               countBean.setY_count(gkCountBean.getInfo_count());
/* 546 */               break;
/*     */             }
/* 548 */             countBean.setY_count(0);
/*     */           }
/*     */         }
/*     */         else {
/* 552 */           countBean.setY_count(0);
/*     */         }
/* 554 */         countBean.setInfo_count(countBean.getZ_count() + countBean.getB_count() + countBean.getY_count());
/* 555 */         cnt_mp.put(countBean.getSite_id(), countBean);
/*     */       }
/*     */ 
/* 563 */       List list = addSiteInfo(cnt_mp, set);
/*     */ 
/* 566 */       InfoCountComparator infoCountComparator = new InfoCountComparator();
/* 567 */       Collections.sort(list, infoCountComparator);
/*     */ 
/* 569 */       return list;
/*     */     } catch (Exception e) {
/* 571 */       e.printStackTrace();
/* 572 */     }return null;
/*     */   }
/*     */ 
/*     */   private static List<GKCountBean> addSiteInfo(Map<String, GKCountBean> cnt_mp, Set<String> set)
/*     */   {
/* 584 */     List ret_lt = new ArrayList();
/* 585 */     List node_lt = new ArrayList();
/* 586 */     String node_cates = GKNodeCateManager.getAllChildCategoryIDS("0");
/* 587 */     String[] arr_node = node_cates.split(",");
/*     */     List lt;
/* 588 */     for (int i = 0; i < arr_node.length; i++) {
/* 589 */       lt = GKNodeManager.getGKNodeListByCateID(
/* 590 */         Integer.valueOf(arr_node[i]).intValue());
/* 591 */       node_lt.addAll(lt);
/*     */     }
/* 593 */     for (GKNodeBean bean : node_lt)
/*     */     {
/* 597 */       if ((set.size() == 0) || (set.contains(bean.getNode_id())))
/*     */       {
/*     */         GKCountBean retbean;
/*     */         GKCountBean retbean;
/* 601 */         if (cnt_mp.containsKey(bean.getNode_id())) {
/* 602 */           retbean = (GKCountBean)cnt_mp.get(bean.getNode_id());
/*     */         } else {
/* 604 */           retbean = new GKCountBean();
/* 605 */           retbean.setSite_id(bean.getNode_id());
/*     */         }
/* 607 */         retbean.setSite_name(bean.getNode_name());
/* 608 */         ret_lt.add(retbean);
/*     */       }
/*     */     }
/* 611 */     return ret_lt;
/*     */   }
/*     */ 
/*     */   public static void main(String[] arg)
/*     */   {
/* 616 */     Map mp = new HashMap();
/* 617 */     mp.put("gk_type", "0");
/* 618 */     List z_list = GKCountDAO.getGkInfoCountByStatus(mp);
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.zwgk.count.GKCountManager
 * JD-Core Version:    0.6.2
 */