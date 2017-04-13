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
/*     */ public class GkvisitorCountManager
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
/* 181 */       mp.put("is_pic", "1");
/* 182 */       List pic_list = GKCountDAO.getGkInfoCountByStatusANDNode(mp);
/* 183 */       mp.remove("is_pic");
/*     */ 
/* 185 */       mp.put("gk_type", "0");
/* 186 */       List z_list = GKCountDAO.getGkInfoCountByStatusANDNode(mp);
/*     */ 
/* 188 */       mp.put("gk_type", "1");
/* 189 */       List b_list = GKCountDAO.getGkInfoCountByStatusANDNode(mp);
/* 190 */       for (GKCountBean gkNodeBeanAll : all_list) {
/* 191 */         int cat_ido = gkNodeBeanAll.getCat_id();
/*     */ 
/* 193 */         if ((z_list != null) && (z_list.size() > 0)) {
/* 194 */           for (GKCountBean gkCountBean : z_list) {
/* 195 */             if (cat_ido == gkCountBean.getCat_id()) {
/* 196 */               gkNodeBeanAll.setZ_count(gkCountBean.getInfo_count());
/* 197 */               break;
/*     */             }
/* 199 */             gkNodeBeanAll.setZ_count(0);
/*     */           }
/*     */         }
/*     */         else {
/* 203 */           gkNodeBeanAll.setZ_count(0);
/*     */         }
/*     */ 
/* 207 */         if ((b_list != null) && (b_list.size() > 0)) {
/* 208 */           for (GKCountBean gkCountBean : b_list) {
/* 209 */             if (cat_ido == gkCountBean.getCat_id()) {
/* 210 */               gkNodeBeanAll.setB_count(gkCountBean.getInfo_count());
/* 211 */               break;
/*     */             }
/* 213 */             gkNodeBeanAll.setB_count(0);
/*     */           }
/*     */         }
/*     */         else {
/* 217 */           gkNodeBeanAll.setB_count(0);
/*     */         }
/*     */ 
/* 221 */         if ((pic_list != null) && (pic_list.size() > 0)) {
/* 222 */           for (GKCountBean gkCountBean : pic_list) {
/* 223 */             if (cat_ido == gkCountBean.getCat_id()) {
/* 224 */               gkNodeBeanAll.setPic_count(gkCountBean.getInfo_count());
/* 225 */               break;
/*     */             }
/* 227 */             gkNodeBeanAll.setPic_count(0);
/*     */           }
/*     */         }
/*     */         else {
/* 231 */           gkNodeBeanAll.setPic_count(0);
/*     */         }
/*     */ 
/* 235 */         gkNodeBeanAll.setY_count(gkNodeBeanAll.getInfo_count() - gkNodeBeanAll.getZ_count() - gkNodeBeanAll.getB_count());
/*     */       }
/*     */ 
/* 238 */       return addCatInfo(all_list, site_id);
/*     */     } catch (Exception e) {
/* 240 */       e.printStackTrace();
/* 241 */     }return null;
/*     */   }
/*     */ 
/*     */   private static List<GKCountBean> addCatInfo(List<GKCountBean> lt, String site_id)
/*     */   {
/* 258 */     List retlt = new ArrayList();
/* 259 */     Map mp = new HashMap();
/* 260 */     for (GKCountBean gkbean : lt) {
/* 261 */       mp.put(Integer.valueOf(gkbean.getCat_id()), gkbean);
/*     */     }
/* 263 */     List catlt = CategoryManager.getCategoryListBySiteID(
/* 264 */       site_id, 0);
/*     */ 
/* 266 */     for (CategoryBean cb : catlt) {
/* 267 */       GKCountBean bean = getCateChildList(cb, mp);
/* 268 */       retlt.add(bean);
/*     */     }
/*     */ 
/* 271 */     return retlt;
/*     */   }
/*     */ 
/*     */   private static GKCountBean getCateChildList(CategoryBean catbean, Map<Integer, GKCountBean> mp)
/*     */   {
/* 286 */     GKCountBean retbean = new GKCountBean();
/* 287 */     retbean.setCat_id(catbean.getCat_id());
/* 288 */     retbean.setCat_name(catbean.getCat_cname());
/*     */ 
/* 290 */     List sublist = CategoryManager.getAllChildCategoryList(
/* 291 */       catbean.getCat_id(), catbean.getSite_id());
/* 292 */     for (int i = 0; i < sublist.size(); i++) {
/* 293 */       if (((CategoryBean)sublist.get(i)).getCat_id() == catbean.getCat_id()) {
/* 294 */         sublist.remove(i);
/* 295 */         break;
/*     */       }
/*     */     }
/* 298 */     if (sublist.size() != 0) {
/* 299 */       retbean.setIs_leaf(false);
/* 300 */       List childList = new ArrayList();
/* 301 */       for (CategoryBean cb : sublist) {
/* 302 */         GKCountBean gkbean = getCateChildList(cb, mp);
/* 303 */         retbean.setB_count(retbean.getB_count() + gkbean.getB_count());
/* 304 */         retbean.setZ_count(retbean.getZ_count() + gkbean.getZ_count());
/* 305 */         retbean.setY_count(retbean.getY_count() + gkbean.getY_count());
/* 306 */         retbean.setInfo_count(retbean.getInfo_count() + 
/* 307 */           gkbean.getInfo_count());
/* 308 */         retbean.setPic_count(retbean.getPic_count() + gkbean.getPic_count());
/*     */ 
/* 310 */         childList.add(gkbean);
/*     */       }
/* 312 */       retbean.setChild_list(childList);
/*     */     }
/* 314 */     else if (mp.containsKey(Integer.valueOf(retbean.getCat_id()))) {
/* 315 */       GKCountBean countBean = (GKCountBean)mp.get(Integer.valueOf(retbean.getCat_id()));
/* 316 */       retbean.setB_count(countBean.getB_count());
/* 317 */       retbean.setZ_count(countBean.getZ_count());
/* 318 */       retbean.setY_count(countBean.getY_count());
/* 319 */       retbean.setInfo_count(countBean.getInfo_count());
/* 320 */       retbean.setPic_count(countBean.getPic_count());
/*     */     }
/*     */ 
/* 323 */     return retbean;
/*     */   }
/*     */ 
/*     */   private static boolean saveCateInfo(List<Map> lt, String updateTime, String appId, String siteId)
/*     */   {
/* 336 */     Map bean_mp = new HashMap();
/* 337 */     for (int i = 0; i < lt.size(); i++) {
/* 338 */       fillBean((Map)lt.get(i), bean_mp);
/*     */     }
/*     */ 
/* 341 */     boolean flg = true;
/* 342 */     for (GKCountBean bean : bean_mp.values()) {
/* 343 */       bean.setUpdate_time(updateTime);
/* 344 */       bean.setApp_id(appId);
/* 345 */       if ((siteId != null) && (!"".equals(siteId))) {
/* 346 */         bean.setSite_id(siteId);
/*     */       }
/* 348 */       int infoCount = bean.getB_count() + bean.getY_count() + 
/* 349 */         bean.getZ_count();
/* 350 */       bean.setInfo_count(infoCount);
/* 351 */       flg = GKCountDAO.insertGKCount(bean) ? flg : false;
/*     */     }
/* 353 */     return flg;
/*     */   }
/*     */ 
/*     */   private static void fillBean(Map mp, Map<String, GKCountBean> bean_mp)
/*     */   {
/* 366 */     String key = mp.get("CAT_ID").toString();
/*     */     GKCountBean bean;
/*     */     GKCountBean bean;
/* 368 */     if (bean_mp.containsKey(key)) {
/* 369 */       bean = (GKCountBean)bean_mp.get(key);
/*     */     } else {
/* 371 */       bean = new GKCountBean();
/* 372 */       if ((key != null) && (!"".equals(key)))
/* 373 */         bean.setCat_id(Integer.valueOf(key).intValue());
/* 374 */       if (mp.get("SITE_ID") != null)
/* 375 */         bean.setSite_id(mp.get("SITE_ID").toString());
/* 376 */       bean_mp.put(key, bean);
/*     */     }
/*     */ 
/* 379 */     String type = mp.get("GK_TYPE").toString();
/* 380 */     String cnt = mp.get("COUNT").toString();
/* 381 */     if ("0".equals(type))
/* 382 */       bean.setZ_count(Integer.valueOf(cnt).intValue());
/* 383 */     else if ("1".equals(type))
/* 384 */       bean.setB_count(Integer.valueOf(cnt).intValue());
/* 385 */     else if ("2".equals(type))
/* 386 */       bean.setY_count(Integer.valueOf(cnt).intValue());
/*     */   }
/*     */ 
/*     */   public static List<GKCountBean> getAllSiteGKCountList(Map<String, String> mp)
/*     */   {
/* 398 */     List ret = new ArrayList();
/*     */ 
/* 400 */     List lt = getAllSiteGKCountList((String)mp.get("start_day"), 
/* 401 */       (String)mp
/* 401 */       .get("end_day"), null);
/* 402 */     String sort_type = (String)mp.get("type");
/* 403 */     int sort_num = Integer.valueOf((String)mp.get("num")).intValue();
/*     */ 
/* 405 */     List ascList = sortGKCountBean(sort_type, lt);
/*     */ 
/* 407 */     for (int i = ret.size(); (i > ret.size() - sort_num) && (i > 0); i--) {
/* 408 */       ret.add((GKCountBean)ascList.get(i - 1));
/*     */     }
/*     */ 
/* 411 */     return ret;
/*     */   }
/*     */ 
/*     */   private static List<GKCountBean> sortGKCountBean(String sort_type, List<GKCountBean> lt)
/*     */   {
/* 425 */     TreeMap beanMap = new TreeMap();
/* 426 */     if ("info_count".equals(sort_type)) {
/* 427 */       for (GKCountBean gkbean : lt)
/* 428 */         beanMap.put(Integer.valueOf(gkbean.getInfo_count()), gkbean);
/*     */     }
/* 430 */     else if ("z_count".equals(sort_type)) {
/* 431 */       for (GKCountBean gkbean : lt)
/* 432 */         beanMap.put(Integer.valueOf(gkbean.getZ_count()), gkbean);
/*     */     }
/* 434 */     else if ("y_count".equals(sort_type)) {
/* 435 */       for (GKCountBean gkbean : lt)
/* 436 */         beanMap.put(Integer.valueOf(gkbean.getY_count()), gkbean);
/*     */     }
/* 438 */     else if ("b_count".equals(sort_type)) {
/* 439 */       for (GKCountBean gkbean : lt) {
/* 440 */         beanMap.put(Integer.valueOf(gkbean.getB_count()), gkbean);
/*     */       }
/*     */     }
/*     */ 
/* 444 */     List retList = new ArrayList();
/* 445 */     for (GKCountBean bean : beanMap.values()) {
/* 446 */       retList.add(bean);
/*     */     }
/* 448 */     return retList;
/*     */   }
/*     */ 
/*     */   public static List<GKCountBean> getAllSiteGKCountList(String startDate, String endDate, String node_ids)
/*     */   {
/*     */     try
/*     */     {
/* 498 */       Set set = new HashSet();
/*     */ 
/* 500 */       Map mp = new HashMap();
/* 501 */       mp.put("start_day", startDate);
/* 502 */       mp.put("end_day", endDate);
/* 503 */       if ((node_ids != null) && (node_ids != "")) {
/* 504 */         mp.put("site_ids", node_ids);
/* 505 */         String[] arr = node_ids.replace("'", "").split(",");
/* 506 */         for (String s : arr) {
/* 507 */           set.add(s);
/*     */         }
/*     */       }
/*     */ 
/* 511 */       Map cnt_mp = new HashMap();
/*     */ 
/* 514 */       List gkNodeList = GKNodeManager.getAllGKNodeList();
/*     */ 
/* 516 */       if (gkNodeList.size() == set.size()) {
/* 517 */         mp.remove("site_ids");
/*     */       }
/*     */ 
/* 520 */       mp.put("is_pic", "1");
/* 521 */       List pic_list = GKCountDAO.getGkInfoCountByStatus(mp);
/*     */ 
/* 523 */       mp.put("gk_type", "0");
/* 524 */       mp.remove("is_pic");
/* 525 */       List z_list = GKCountDAO.getGkInfoCountByStatus(mp);
/*     */ 
/* 527 */       mp.put("gk_type", "1");
/* 528 */       List b_list = GKCountDAO.getGkInfoCountByStatus(mp);
/*     */ 
/* 530 */       mp.put("gk_type", "2");
/* 531 */       List y_list = GKCountDAO.getGkInfoCountByStatus(mp);
/* 532 */       for (GKNodeBean gkNodeBean : gkNodeList) {
/* 533 */         String site_id = gkNodeBean.getNode_id();
/* 534 */         GKCountBean countBean = new GKCountBean();
/* 535 */         countBean.setSite_id(site_id);
/*     */ 
/* 538 */         if ((pic_list != null) && (pic_list.size() > 0)) {
/* 539 */           for (GKCountBean gkCountBean : pic_list)
/*     */           {
/* 541 */             if (site_id.equals(gkCountBean.getSite_id())) {
/* 542 */               countBean.setPic_count(gkCountBean.getInfo_count());
/* 543 */               break;
/*     */             }
/* 545 */             countBean.setPic_count(0);
/*     */           }
/*     */         }
/*     */         else {
/* 549 */           countBean.setPic_count(0);
/*     */         }
/*     */ 
/* 553 */         if ((z_list != null) && (z_list.size() > 0)) {
/* 554 */           for (GKCountBean gkCountBean : z_list) {
/* 555 */             if (site_id.equals(gkCountBean.getSite_id())) {
/* 556 */               countBean.setZ_count(gkCountBean.getInfo_count());
/* 557 */               break;
/*     */             }
/* 559 */             countBean.setZ_count(0);
/*     */           }
/*     */         }
/*     */         else {
/* 563 */           countBean.setZ_count(0);
/*     */         }
/*     */ 
/* 567 */         if ((b_list != null) && (b_list.size() > 0)) {
/* 568 */           for (GKCountBean gkCountBean : b_list) {
/* 569 */             if (site_id.equals(gkCountBean.getSite_id())) {
/* 570 */               countBean.setB_count(gkCountBean.getInfo_count());
/* 571 */               break;
/*     */             }
/* 573 */             countBean.setB_count(0);
/*     */           }
/*     */         }
/*     */         else {
/* 577 */           countBean.setB_count(0);
/*     */         }
/*     */ 
/* 581 */         if ((y_list != null) && (y_list.size() > 0)) {
/* 582 */           for (GKCountBean gkCountBean : y_list) {
/* 583 */             if (site_id.equals(gkCountBean.getSite_id())) {
/* 584 */               countBean.setY_count(gkCountBean.getInfo_count());
/* 585 */               break;
/*     */             }
/* 587 */             countBean.setY_count(0);
/*     */           }
/*     */         }
/*     */         else {
/* 591 */           countBean.setY_count(0);
/*     */         }
/* 593 */         countBean.setInfo_count(countBean.getZ_count() + countBean.getB_count() + countBean.getY_count());
/* 594 */         cnt_mp.put(countBean.getSite_id(), countBean);
/*     */       }
/*     */ 
/* 602 */       List list = addSiteInfo(cnt_mp, set);
/*     */ 
/* 605 */       InfoCountComparator infoCountComparator = new InfoCountComparator();
/* 606 */       Collections.sort(list, infoCountComparator);
/*     */ 
/* 608 */       return list;
/*     */     } catch (Exception e) {
/* 610 */       e.printStackTrace();
/* 611 */     }return null;
/*     */   }
/*     */ 
/*     */   private static List<GKCountBean> addSiteInfo(Map<String, GKCountBean> cnt_mp, Set<String> set)
/*     */   {
/* 623 */     List ret_lt = new ArrayList();
/* 624 */     List node_lt = new ArrayList();
/* 625 */     String node_cates = GKNodeCateManager.getAllChildCategoryIDS("0");
/* 626 */     String[] arr_node = node_cates.split(",");
/*     */     List lt;
/* 627 */     for (int i = 0; i < arr_node.length; i++) {
/* 628 */       lt = GKNodeManager.getGKNodeListByCateID(
/* 629 */         Integer.valueOf(arr_node[i]).intValue());
/* 630 */       node_lt.addAll(lt);
/*     */     }
/* 632 */     for (GKNodeBean bean : node_lt)
/*     */     {
/* 636 */       if ((set.size() == 0) || (set.contains(bean.getNode_id())))
/*     */       {
/*     */         GKCountBean retbean;
/*     */         GKCountBean retbean;
/* 640 */         if (cnt_mp.containsKey(bean.getNode_id())) {
/* 641 */           retbean = (GKCountBean)cnt_mp.get(bean.getNode_id());
/*     */         } else {
/* 643 */           retbean = new GKCountBean();
/* 644 */           retbean.setSite_id(bean.getNode_id());
/*     */         }
/* 646 */         retbean.setSite_name(bean.getNode_name());
/* 647 */         ret_lt.add(retbean);
/*     */       }
/*     */     }
/* 650 */     return ret_lt;
/*     */   }
/*     */ 
/*     */   public static void main(String[] arg)
/*     */   {
/* 655 */     Map mp = new HashMap();
/* 656 */     mp.put("gk_type", "0");
/* 657 */     List z_list = GKCountDAO.getGkInfoCountByStatus(mp);
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.zwgk.count.GkvisitorCountManager
 * JD-Core Version:    0.6.2
 */