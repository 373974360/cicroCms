/*     */ package com.cicro.wcm.services.cms.count;
/*     */ 
/*     */ import com.cicro.wcm.bean.cms.category.CategoryBean;
/*     */ import com.cicro.wcm.bean.cms.category.ZTCategoryBean;
/*     */ import com.cicro.wcm.bean.cms.count.CmsCountBean;
/*     */ import com.cicro.wcm.bean.cms.count.SiteCountBean;
/*     */ import com.cicro.wcm.bean.cms.info.InfoBean;
/*     */ import com.cicro.wcm.bean.control.SiteBean;
/*     */ import com.cicro.wcm.dao.cms.count.CmsCountDAO;
/*     */ import com.cicro.wcm.services.cms.category.CategoryManager;
/*     */ import com.cicro.wcm.services.cms.category.CategoryManager.CategoryComparator;
/*     */ import com.cicro.wcm.services.cms.category.CategoryRPC;
/*     */ import com.cicro.wcm.services.cms.category.ZTCategoryManager;
/*     */ import com.cicro.wcm.services.control.site.SiteManager;
/*     */ import java.io.PrintStream;
/*     */ import java.text.ParseException;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collections;
/*     */ import java.util.Date;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.TreeMap;
/*     */ import net.sf.json.JSONArray;
/*     */ import net.sf.json.JSONObject;
/*     */ 
/*     */ public class CmsCountManager_20150603
/*     */ {
/*  35 */   static int num = 0;
/*     */ 
/*     */   public static List<CmsCountBean> getInfoCount(Map<String, String> mp)
/*     */   {
/*  46 */     formatDate(mp);
/*  47 */     if ((mp.get("byday") != null) || (mp.get("bymonth") != null)) {
/*  48 */       return getInfoCountByDate(mp);
/*     */     }
/*  50 */     return getInfoCountByCat(mp);
/*     */   }
/*     */ 
/*     */   private static List<CmsCountBean> getInfoCountByCat(Map<String, String> mp)
/*     */   {
/* 107 */     int cat_id = Integer.parseInt((String)mp.get("cat_id"));
/* 108 */     String tmp_id = (String)mp.get("p_id");
/* 109 */     int p_id = 0;
/* 110 */     String site_id = (String)mp.get("site_id");
/* 111 */     if (tmp_id != null)
/* 112 */       p_id = Integer.parseInt(tmp_id);
/*     */     else {
/* 114 */       p_id = 0;
/*     */     }
/* 116 */     List ret = new ArrayList();
/* 117 */     CmsCountBean retBean = null;
/*     */     CategoryBean bean;
/* 118 */     if ((p_id == -1) && (cat_id == -1))
/*     */     {
/* 120 */       List ztlist = ZTCategoryManager.getZTCategoryList(site_id);
/* 121 */       for (int i = 0; i < ztlist.size(); i++)
/*     */       {
/* 123 */         List tmp_ret = new ArrayList();
/* 124 */         int allCount = 0;
/* 125 */         int hostInfoCount = 0;
/* 126 */         int refInfoCount = 0;
/* 127 */         int linkInfoCount = 0;
/*     */ 
/* 129 */         List cat_list = CategoryManager.getZTCategoryListBySiteAndType(((ZTCategoryBean)ztlist.get(i)).getZt_cat_id(), site_id);
/* 130 */         for (int j = 0; j < cat_list.size(); j++)
/*     */         {
/* 132 */           List directSubNode = CategoryManager.getChildCategoryList(((CategoryBean)cat_list.get(j)).getCat_id(), site_id);
/* 133 */           for (Iterator localIterator = directSubNode.iterator(); localIterator.hasNext(); ) { bean = (CategoryBean)localIterator.next();
/*     */ 
/* 135 */             mp.remove("cat_id");
/*     */ 
/* 146 */             CategoryBean catebe = CategoryManager.getCategoryBeanCatID(bean.getCat_id(), site_id);
/* 147 */             String tree_posistion = catebe.getCat_position();
/* 148 */             String ids = "select cat_id from cs_info_category where cat_position like '%" + tree_posistion + "%' and site_id = '" + site_id + "' ";
/* 149 */             mp.put("cat_id", ids);
/*     */ 
/* 151 */             CmsCountBean tmp_retBean = CmsCountDAO.getCountListByCat(mp);
/* 152 */             if (tmp_retBean != null)
/*     */             {
/* 154 */               tmp_retBean.setCat_id(bean.getCat_id());
/* 155 */               tmp_retBean.setCat_name(bean.getCat_cname());
/* 156 */               tmp_ret.add(tmp_retBean);
/*     */             }
/*     */           }
/*     */         }
/* 160 */         if (tmp_ret != null)
/*     */         {
/* 162 */           for (int n = 0; n < tmp_ret.size(); n++)
/*     */           {
/* 164 */             CmsCountBean cmsb = (CmsCountBean)tmp_ret.get(n);
/* 165 */             if (cmsb != null)
/*     */             {
/* 167 */               allCount += cmsb.getAllCount();
/* 168 */               hostInfoCount += cmsb.getHostInfoCount();
/* 169 */               refInfoCount += cmsb.getRefInfoCount();
/* 170 */               linkInfoCount += cmsb.getLinkInfoCount();
/*     */             }
/*     */           }
/* 173 */           CmsCountBean retB = new CmsCountBean();
/* 174 */           retB.setCat_id(((ZTCategoryBean)ztlist.get(i)).getZt_cat_id());
/* 175 */           retB.setCat_name(((ZTCategoryBean)ztlist.get(i)).getZt_cat_name());
/* 176 */           retB.setAllCount(allCount);
/* 177 */           retB.setHostInfoCount(hostInfoCount);
/* 178 */           retB.setRefInfoCount(refInfoCount);
/* 179 */           retB.setLinkInfoCount(linkInfoCount);
/* 180 */           ret.add(retB);
/*     */         }
/*     */       }
/*     */     }
/*     */     else
/*     */     {
/*     */       int i;
/* 183 */       if ((p_id == -1) && (cat_id != -1))
/*     */       {
/* 185 */         int zt_id = cat_id;
/* 186 */         List tmp_ret = new ArrayList();
/* 187 */         List cat_list = CategoryManager.getZTCategoryListBySiteAndType(zt_id, site_id);
/* 188 */         if (cat_list.size() > 0)
/*     */         {
/* 190 */           for (i = 0; i < cat_list.size(); i++)
/*     */           {
/* 192 */             int allCount = 0;
/* 193 */             int hostInfoCount = 0;
/* 194 */             int refInfoCount = 0;
/* 195 */             int linkInfoCount = 0;
/* 196 */             List<CategoryBean> directSubNode = CategoryManager.getChildCategoryList(((CategoryBean)cat_list.get(i)).getCat_id(), site_id);
/* 197 */             for (CategoryBean bean : directSubNode)
/*     */             {
/* 199 */               mp.remove("cat_id");
/*     */ 
/* 207 */               CategoryBean catebe = CategoryManager.getCategoryBeanCatID(bean.getCat_id(), site_id);
/* 208 */               String tree_posistion = catebe.getCat_position();
/* 209 */               String ids = "select cat_id from cs_info_category where cat_position like '%" + tree_posistion + "%' and site_id = '" + site_id + "' ";
/* 210 */               mp.put("cat_id", ids);
/* 211 */               CmsCountBean tmp_retBean = CmsCountDAO.getCountListByCat(mp);
/* 212 */               if (tmp_retBean != null)
/*     */               {
/* 214 */                 tmp_retBean.setCat_id(bean.getCat_id());
/* 215 */                 tmp_retBean.setCat_name(bean.getCat_cname());
/* 216 */                 tmp_ret.add(tmp_retBean);
/*     */               }
/*     */             }
/* 219 */             if (tmp_ret != null)
/*     */             {
/* 221 */               for (int n = 0; n < tmp_ret.size(); n++)
/*     */               {
/* 223 */                 CmsCountBean cmsb = (CmsCountBean)tmp_ret.get(n);
/* 224 */                 if (cmsb != null)
/*     */                 {
/* 226 */                   int catid = cmsb.getCat_id();
/* 227 */                   CategoryBean cb = CategoryManager.getCategoryBean(catid);
/* 228 */                   if (cb.getCat_position().startsWith(((CategoryBean)cat_list.get(i)).getCat_position()))
/*     */                   {
/* 230 */                     allCount += cmsb.getAllCount();
/* 231 */                     hostInfoCount += cmsb.getHostInfoCount();
/* 232 */                     refInfoCount += cmsb.getRefInfoCount();
/* 233 */                     linkInfoCount += cmsb.getLinkInfoCount();
/*     */                   }
/*     */                 }
/*     */               }
/* 237 */               CmsCountBean retB = new CmsCountBean();
/* 238 */               retB.setCat_id(((CategoryBean)cat_list.get(i)).getCat_id());
/* 239 */               retB.setCat_name(((CategoryBean)cat_list.get(i)).getCat_cname());
/*     */ 
/* 241 */               retB.setAllCount(allCount);
/* 242 */               retB.setHostInfoCount(hostInfoCount);
/* 243 */               retB.setRefInfoCount(refInfoCount);
/* 244 */               retB.setLinkInfoCount(linkInfoCount);
/* 245 */               ret.add(retB);
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */       else {
/* 251 */         boolean isMinorNode = CategoryManager.isHasChildNode(cat_id, site_id);
/*     */ 
/* 253 */         if (!isMinorNode) {
/* 254 */           CmsCountBean bean = CmsCountDAO.getCountListByCat(mp);
/* 255 */           bean.setCat_id(cat_id);
/* 256 */           bean.setCat_name(CategoryManager.getCategoryBean(cat_id).getCat_cname());
/* 257 */           ret.add(bean);
/*     */         } else {
/* 259 */           List directSubNode = new ArrayList();
/*     */ 
/* 261 */           if (cat_id == 0)
/* 262 */             directSubNode = CategoryManager.getCategoryListBySiteID(site_id, 0);
/*     */           else {
/* 264 */             directSubNode = CategoryManager.getChildCategoryList(cat_id, site_id);
/*     */           }
/* 266 */           for (CategoryBean bean : directSubNode)
/*     */           {
/* 268 */             mp.remove("cat_id");
/*     */ 
/* 278 */             CategoryBean catebe = CategoryManager.getCategoryBeanCatID(bean.getCat_id(), site_id);
/* 279 */             String tree_posistion = catebe.getCat_position();
/* 280 */             String ids = "select cat_id from cs_info_category where cat_position like '%" + tree_posistion + "%' and site_id = '" + site_id + "' ";
/* 281 */             mp.put("cat_id", ids);
/* 282 */             retBean = CmsCountDAO.getCountListByCat(mp);
/* 283 */             retBean.setCat_id(bean.getCat_id());
/* 284 */             retBean.setCat_name(bean.getCat_cname());
/*     */ 
/* 286 */             if (retBean.getAllCount() != 0)
/* 287 */               ret.add(retBean);
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/* 292 */     return ret;
/*     */   }
/*     */ 
/*     */   private static List<CmsCountBean> getInfoCountByDate(Map<String, String> mp)
/*     */   {
/* 302 */     int cat_id = Integer.parseInt((String)mp.get("cat_id"));
/* 303 */     String site_id = (String)mp.get("site_id");
/*     */ 
/* 305 */     boolean isMinorNode = CategoryManager.isHasChildNode(cat_id, site_id);
/*     */ 
/* 315 */     String ids = "";
/* 316 */     if (isMinorNode) {
/* 317 */       CategoryBean catebe = CategoryManager.getCategoryBeanCatID(cat_id, site_id);
/* 318 */       String tree_posistion = catebe.getCat_position();
/* 319 */       ids = "select cat_id from cs_info_category where cat_position like '%" + tree_posistion + "%' and site_id='" + site_id + "' ";
/*     */     }
/* 321 */     mp.remove("cat_ids");
/* 322 */     mp.put("cat_ids", ids);
/* 323 */     Map p = CmsCountDAO.getCountListByDate(mp);
/* 324 */     List ret = new ArrayList();
/* 325 */     ret.addAll(p.values());
/* 326 */     Collections.sort(ret, new DescComparator());
/* 327 */     return ret;
/*     */   }
/*     */ 
/*     */   public static List<CmsCountBean> getInputCountList(Map<String, String> mp)
/*     */   {
/* 336 */     formatDate(mp);
/*     */ 
/* 338 */     mp.remove("info_status");
/* 339 */     Map m = CmsCountDAO.getInputCountList(mp);
/*     */ 
/* 342 */     mp.put("info_status", "8");
/* 343 */     Map m1 = CmsCountDAO.getInputCountList(mp);
/*     */ 
/* 346 */     mp.put("is_pic", "1");
/* 347 */     Map m2 = CmsCountDAO.getInputCountList(mp);
/*     */ 
/* 349 */     List ret = new ArrayList();
/*     */ 
/* 351 */     for (String s : m.keySet()) {
/* 352 */       CmsCountBean bean = (CmsCountBean)m.get(s);
/* 353 */       bean.setInputCount(bean.getCount());
/* 354 */       if (m1.containsKey(s)) {
/* 355 */         CmsCountBean temBean = (CmsCountBean)m1.get(s);
/* 356 */         bean.setReleasedCount(temBean.getCount());
/*     */       }
/* 358 */       if (m2.containsKey(s)) {
/* 359 */         CmsCountBean temBean = (CmsCountBean)m2.get(s);
/* 360 */         bean.setPicInfoCount(temBean.getCount());
/*     */       }
/* 362 */       bean.setReleaseRate();
/* 363 */       ret.add((CmsCountBean)m.get(s));
/*     */     }
/*     */ 
/* 366 */     Collections.sort(ret, new CntDescComparator());
/* 367 */     return ret;
/*     */   }
/*     */ 
/*     */   public static List<CmsCountBean> getInputCountListByUserID(Map<String, String> mp)
/*     */   {
/* 376 */     formatDate(mp);
/* 377 */     mp.remove("info_status");
/* 378 */     Map all_m = CmsCountDAO.getInputCountListByUserID(mp);
/*     */ 
/* 380 */     mp.put("info_status", "8");
/* 381 */     Map m = CmsCountDAO.getInputCountListByUserID(mp);
/*     */ 
/* 383 */     List ret = new ArrayList();
/* 384 */     for (String s : all_m.keySet()) {
/* 385 */       CmsCountBean bean = (CmsCountBean)all_m.get(s);
/* 386 */       bean.setInputCount(bean.getCount());
/* 387 */       if (m.containsKey(s)) {
/* 388 */         CmsCountBean temBean = (CmsCountBean)m.get(s);
/* 389 */         bean.setReleasedCount(temBean.getCount());
/*     */       }
/* 391 */       bean.setReleaseRate();
/* 392 */       ret.add((CmsCountBean)all_m.get(s));
/*     */     }
/* 394 */     Collections.sort(ret, new CntDescComparator());
/* 395 */     return ret;
/*     */   }
/*     */ 
/*     */   public static List<SiteCountBean> getInputCountListByUserIDCate(Map<String, String> mp)
/*     */   {
/* 404 */     num = 0;
/* 405 */     List resultList = new ArrayList();
/*     */     try {
/* 407 */       formatDate(mp);
/* 408 */       mp.remove("info_status");
/* 409 */       Map all_m = CmsCountDAO.getInputCountListByUserID(mp);
/*     */ 
/* 411 */       mp.put("info_status", "8");
/* 412 */       Map m = CmsCountDAO.getInputCountListByUserID(mp);
/* 413 */       mp.put("is_pic", "1");
/* 414 */       Map m1 = CmsCountDAO.getInputCountListByUserID(mp);
/*     */ 
/* 416 */       List ret = new ArrayList();
/* 417 */       for (String s : all_m.keySet()) {
/* 418 */         CmsCountBean bean = (CmsCountBean)all_m.get(s);
/* 419 */         bean.setInputCount(bean.getCount());
/* 420 */         if (m.containsKey(s)) {
/* 421 */           CmsCountBean temBean = (CmsCountBean)m.get(s);
/* 422 */           bean.setReleasedCount(temBean.getCount());
/*     */         }
/*     */ 
/* 425 */         if (m1.containsKey(s)) {
/* 426 */           CmsCountBean temBean = (CmsCountBean)m1.get(s);
/* 427 */           bean.setPicInfoCount(temBean.getCount());
/*     */         }
/*     */ 
/* 430 */         bean.setReleaseRate();
/* 431 */         ret.add((CmsCountBean)all_m.get(s));
/*     */       }
/* 433 */       Collections.sort(ret, new CntDescComparator());
/*     */ 
/* 437 */       String site_id = (String)mp.get("site_id");
/* 438 */       int user_id = Integer.valueOf(String.valueOf(mp.get("user_id"))).intValue();
/*     */ 
/* 440 */       String treeStr = CategoryRPC.getCategoryTreeByCategoryID(0, site_id);
/*     */ 
/* 442 */       String ztTreeStr = getZTCategoryTreeStrWidthZTName(site_id, user_id);
/* 443 */       String allStr = treeStr.substring(0, treeStr.length() - 1) + "," + ztTreeStr + "]";
/*     */ 
/* 445 */       JSONArray jsonArray = JSONArray.fromObject(allStr);
/*     */ 
/* 447 */       Iterator it = jsonArray.iterator();
/* 448 */       while (it.hasNext()) {
/* 449 */         JSONObject jsonObject = (JSONObject)it.next();
/* 450 */         SiteCountBean siteCountBean = doOutTreeBean(jsonObject, ret, site_id);
/*     */ 
/* 452 */         if (siteCountBean.getInputCount() != 0) {
/* 453 */           resultList.add(siteCountBean);
/*     */         }
/*     */       }
/* 456 */       return resultList;
/*     */     } catch (Exception e) {
/* 458 */       e.printStackTrace();
/* 459 */     }return resultList;
/*     */   }
/*     */ 
/*     */   public static SiteCountBean doOutTreeBean(JSONObject jsonObject, List<CmsCountBean> list, String site_id)
/*     */   {
/* 468 */     SiteCountBean siteCountBean = new SiteCountBean();
/*     */     try {
/* 470 */       String str = jsonObject.toString();
/* 471 */       JSONArray jsonArray = JSONArray.fromObject(jsonObject.get("children"));
/*     */       int picReleasedCount;
/* 472 */       if ((!jsonArray.toString().equals("[null]")) && (jsonArray != null) && (!"".equals(jsonArray)) && (jsonArray.size() > 0)) {
/* 473 */         int cat_id = Integer.valueOf(String.valueOf(jsonObject.get("id"))).intValue();
/* 474 */         siteCountBean.setCat_id(cat_id);
/* 475 */         siteCountBean.setIs_leaf(false);
/*     */ 
/* 477 */         SiteBean sb = SiteManager.getSiteBeanBySiteID(site_id);
/* 478 */         String cat_name = String.valueOf(jsonObject.get("text"));
/* 479 */         if ((cat_name == null) || ("".equals(cat_name))) {
/* 480 */           cat_name = sb.getSite_name();
/*     */         }
/* 482 */         siteCountBean.setCat_name(cat_name);
/*     */ 
/* 484 */         int inputCount = 0;
/* 485 */         int releasedCount = 0;
/* 486 */         picReleasedCount = 0;
/* 487 */         Iterator it = jsonArray.iterator();
/* 488 */         List childList = new ArrayList();
/* 489 */         while (it.hasNext()) {
/* 490 */           Object object = it.next();
/* 491 */           if (object != null) {
/* 492 */             JSONObject jsonObject2 = (JSONObject)object;
/* 493 */             if (jsonObject2 != null) {
/* 494 */               SiteCountBean countBean = doOutTreeBean(jsonObject2, list, site_id);
/* 495 */               inputCount += countBean.getInputCount();
/* 496 */               releasedCount += countBean.getReleasedCount();
/* 497 */               picReleasedCount += countBean.getPicReleasedCount();
/*     */ 
/* 499 */               if (countBean.getInputCount() != 0) {
/* 500 */                 childList.add(countBean);
/*     */               }
/*     */             }
/*     */           }
/*     */         }
/* 505 */         siteCountBean.setInputCount(inputCount);
/* 506 */         siteCountBean.setReleasedCount(releasedCount);
/* 507 */         siteCountBean.setPicReleasedCount(picReleasedCount);
/* 508 */         siteCountBean.setReleaseRate();
/* 509 */         siteCountBean.setChild_list(childList);
/*     */       } else {
/* 511 */         int cat_id = Integer.valueOf(String.valueOf(jsonObject.get("id"))).intValue();
/* 512 */         siteCountBean.setCat_id(cat_id);
/* 513 */         siteCountBean.setIs_leaf(true);
/* 514 */         siteCountBean.setCat_name(String.valueOf(jsonObject.get("text")));
/* 515 */         int inputCount = 0;
/* 516 */         int releasedCount = 0;
/* 517 */         int picReleasedCount = 0;
/* 518 */         for (CmsCountBean cmsCountBean : list) {
/* 519 */           if (cat_id == cmsCountBean.getCat_id()) {
/* 520 */             num += 1;
/* 521 */             System.out.println("cat_id --- " + cat_id);
/* 522 */             System.out.println("num --- " + num);
/*     */ 
/* 524 */             inputCount += cmsCountBean.getInputCount();
/* 525 */             releasedCount += cmsCountBean.getReleasedCount();
/* 526 */             picReleasedCount += cmsCountBean.getPicInfoCount();
/* 527 */             break;
/*     */           }
/*     */         }
/* 530 */         siteCountBean.setInputCount(inputCount);
/* 531 */         siteCountBean.setReleasedCount(releasedCount);
/* 532 */         siteCountBean.setPicReleasedCount(picReleasedCount);
/* 533 */         siteCountBean.setReleaseRate();
/*     */       }
/* 535 */       return siteCountBean;
/*     */     } catch (Exception e) {
/* 537 */       e.printStackTrace();
/* 538 */     }return siteCountBean;
/*     */   }
/*     */ 
/*     */   private static void formatDate(Map<String, String> mp)
/*     */   {
/* 553 */     String s_day = (String)mp.get("start_day");
/* 554 */     String e_day = (String)mp.get("end_day");
/*     */ 
/* 556 */     if (e_day == null) {
/* 557 */       return;
/*     */     }
/* 559 */     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
/*     */     try {
/* 561 */       Date date = sdf.parse(e_day);
/* 562 */       Date s_date = sdf.parse(s_day);
/* 563 */       e_day = sdf.format(date) + " 23:59:59";
/* 564 */       s_day = sdf.format(s_date);
/*     */     }
/*     */     catch (ParseException pex) {
/* 567 */       e_day = sdf.format(new Date()) + " 23:59:59";
/* 568 */       s_day = sdf.format(new Date());
/*     */     }
/*     */ 
/* 571 */     mp.remove("end_day");
/* 572 */     mp.put("end_day", e_day);
/* 573 */     mp.remove("start_day");
/* 574 */     mp.put("start_day", s_day);
/*     */   }
/*     */ 
/*     */   public static List<SiteCountBean> getInputCountListByCate(Map<String, String> mp)
/*     */   {
/* 584 */     List listResult = new ArrayList();
/*     */     try {
/* 586 */       String cat_ids = (String)mp.get("cat_ids");
/* 587 */       if (cat_ids == null) {
/* 588 */         cat_ids = "";
/*     */       }
/* 590 */       Set catSet = new HashSet();
/* 591 */       if (!cat_ids.equals("")) {
/* 592 */         List catList = Arrays.asList(cat_ids.split(","));
/*     */         Iterator localIterator2;
/* 593 */         label183: for (Iterator localIterator1 = catList.iterator(); localIterator1.hasNext(); 
/* 598 */           localIterator2.hasNext())
/*     */         {
/* 593 */           String cat_id = (String)localIterator1.next();
/* 594 */           if ((cat_id == null) || ("".equals(cat_id))) break label183;
/* 595 */           CategoryBean categoryBean = CategoryManager.getCategoryBean(Integer.parseInt(cat_id));
/* 596 */           String cat_position = categoryBean.getCat_position();
/* 597 */           List catpList = Arrays.asList(cat_position.split("\\$"));
/* 598 */           localIterator2 = catpList.iterator(); continue; String catp_id = (String)localIterator2.next();
/* 599 */           if ((catp_id != null) && (!"".equals(catp_id))) {
/* 600 */             catSet.add(catp_id);
/*     */           }
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/* 606 */       System.out.println("---getInputCountListByCate----catSet == " + catSet);
/* 607 */       listResult = getInputCountListByCate(mp, catSet);
/*     */     } catch (Exception e) {
/* 609 */       e.printStackTrace();
/*     */     } finally {
/* 611 */       return listResult;
/*     */     }
/*     */   }
/*     */ 
/*     */   public static List<SiteCountBean> getInputCountListByCate(Map<String, String> mp, Set<String> catSet)
/*     */   {
/* 622 */     List resultList = new ArrayList();
/*     */     try {
/* 624 */       formatDate(mp);
/* 625 */       System.out.println("---getInputCountListByCate---map = " + mp);
/* 626 */       mp.remove("info_status");
/* 627 */       Map all_m = CmsCountDAO.getInputCountListByCate(mp);
/*     */ 
/* 629 */       mp.put("info_status", "8");
/* 630 */       Map m = CmsCountDAO.getInputCountListByCate(mp);
/* 631 */       mp.put("is_pic", "1");
/* 632 */       Map m1 = CmsCountDAO.getInputCountListByCate(mp);
/*     */ 
/* 634 */       List ret = new ArrayList();
/* 635 */       for (String s : all_m.keySet()) {
/* 636 */         CmsCountBean bean = (CmsCountBean)all_m.get(s);
/* 637 */         bean.setInputCount(bean.getCount());
/* 638 */         if (m.containsKey(s)) {
/* 639 */           CmsCountBean temBean = (CmsCountBean)m.get(s);
/* 640 */           bean.setReleasedCount(temBean.getCount());
/*     */         }
/*     */ 
/* 643 */         if (m1.containsKey(s)) {
/* 644 */           CmsCountBean temBean = (CmsCountBean)m1.get(s);
/* 645 */           bean.setPicInfoCount(temBean.getCount());
/*     */         }
/*     */ 
/* 648 */         bean.setReleaseRate();
/* 649 */         ret.add((CmsCountBean)all_m.get(s));
/*     */       }
/* 651 */       Collections.sort(ret, new CntDescComparator());
/*     */ 
/* 654 */       String site_id = (String)mp.get("site_id");
/* 655 */       String input_users = (String)mp.get("input_user");
/* 656 */       if (input_users == null) {
/* 657 */         input_users = "0";
/*     */       }
/* 659 */       int user_id = Integer.parseInt(input_users);
/*     */ 
/* 661 */       String treeStr = CategoryRPC.getCategoryTreeBySiteID(site_id);
/* 662 */       System.out.println("---CmsCountManager--getInputCountListByCate---catetreeStr = " + treeStr);
/*     */ 
/* 664 */       String ztTreeStr = getZTCategoryTreeStrWidthZTName(site_id);
/* 665 */       String allStr = treeStr.substring(0, treeStr.length() - 1) + "," + ztTreeStr + "]";
/* 666 */       System.out.println("---CmsCountManager--getInputCountListByCate---treeStr   allStr\t==\t" + allStr);
/* 667 */       JSONArray jsonArray = JSONArray.fromObject(allStr);
/*     */ 
/* 669 */       Iterator it = jsonArray.iterator();
/* 670 */       while (it.hasNext()) {
/* 671 */         JSONObject jsonObject = (JSONObject)it.next();
/* 672 */         SiteCountBean siteCountBean = doOutTreeBeanByCate(jsonObject, ret, catSet);
/* 673 */         resultList.add(siteCountBean);
/*     */       }
/* 675 */       return resultList;
/*     */     } catch (Exception e) {
/* 677 */       e.printStackTrace();
/* 678 */     }return resultList;
/*     */   }
/*     */ 
/*     */   public static SiteCountBean doOutTreeBeanByCate(JSONObject jsonObject, List<CmsCountBean> list, Set<String> catSet)
/*     */   {
/* 684 */     SiteCountBean siteCountBean = new SiteCountBean();
/*     */     try {
/* 686 */       String str = jsonObject.toString();
/* 687 */       JSONArray jsonArray = JSONArray.fromObject(jsonObject.get("children"));
/*     */       List childList;
/* 688 */       if ((!jsonArray.toString().equals("[null]")) && (jsonArray != null) && (!"".equals(jsonArray)) && (jsonArray.size() > 0)) {
/* 689 */         int cat_id = Integer.valueOf(String.valueOf(jsonObject.get("id"))).intValue();
/*     */ 
/* 691 */         if ((catSet.size() > 0) && (!catSet.contains(cat_id))) {
/* 692 */           return null;
/*     */         }
/* 694 */         siteCountBean.setCat_id(cat_id);
/* 695 */         siteCountBean.setIs_leaf(false);
/* 696 */         siteCountBean.setCat_name(String.valueOf(jsonObject.get("text")));
/* 697 */         int inputCount = 0;
/* 698 */         int releasedCount = 0;
/* 699 */         int picReleasedCount = 0;
/* 700 */         Iterator it = jsonArray.iterator();
/* 701 */         childList = new ArrayList();
/* 702 */         while (it.hasNext()) {
/* 703 */           Object object = it.next();
/* 704 */           if (object != null) {
/* 705 */             JSONObject jsonObject2 = (JSONObject)object;
/* 706 */             if (jsonObject2 != null) {
/* 707 */               SiteCountBean countBean = doOutTreeBeanByCate(jsonObject2, list, catSet);
/* 708 */               if (countBean != null) {
/* 709 */                 inputCount += countBean.getInputCount();
/* 710 */                 releasedCount += countBean.getReleasedCount();
/* 711 */                 picReleasedCount += countBean.getPicReleasedCount();
/* 712 */                 childList.add(countBean);
/*     */               }
/*     */             }
/*     */           }
/*     */         }
/* 717 */         siteCountBean.setInputCount(inputCount);
/* 718 */         siteCountBean.setReleasedCount(releasedCount);
/* 719 */         siteCountBean.setPicReleasedCount(picReleasedCount);
/* 720 */         siteCountBean.setReleaseRate();
/* 721 */         siteCountBean.setChild_list(childList);
/*     */       }
/*     */       else {
/* 724 */         int cat_id = Integer.valueOf(String.valueOf(jsonObject.get("id"))).intValue();
/*     */ 
/* 726 */         if ((catSet.size() > 0) && (!catSet.contains(cat_id))) {
/* 727 */           return null;
/*     */         }
/* 729 */         siteCountBean.setCat_id(cat_id);
/* 730 */         siteCountBean.setIs_leaf(true);
/* 731 */         siteCountBean.setCat_name(String.valueOf(jsonObject.get("text")));
/* 732 */         int inputCount = 0;
/* 733 */         int releasedCount = 0;
/* 734 */         int picReleasedCount = 0;
/* 735 */         for (CmsCountBean cmsCountBean : list) {
/* 736 */           if (cat_id == cmsCountBean.getCat_id()) {
/* 737 */             inputCount += cmsCountBean.getInputCount();
/* 738 */             releasedCount += cmsCountBean.getReleasedCount();
/* 739 */             picReleasedCount += cmsCountBean.getPicInfoCount();
/* 740 */             break;
/*     */           }
/*     */         }
/* 743 */         siteCountBean.setInputCount(inputCount);
/* 744 */         siteCountBean.setReleasedCount(releasedCount);
/* 745 */         siteCountBean.setPicReleasedCount(picReleasedCount);
/* 746 */         siteCountBean.setReleaseRate();
/*     */       }
/* 748 */       return siteCountBean;
/*     */     } catch (Exception e) {
/* 750 */       e.printStackTrace();
/* 751 */     }return siteCountBean;
/*     */   }
/*     */ 
/*     */   public static String getZTCategoryTreeStrWidthZTName(String site_id, int user_id)
/*     */   {
/* 762 */     String roo_name = "专题栏目";
/* 763 */     String cate_str = ZTCategoryManager.getZTCategoryTreeJsonStr(site_id, user_id);
/* 764 */     if ((cate_str != null) && (!"[]".equals(cate_str))) {
/* 765 */       return "{\"id\":1,\"text\":\"" + roo_name + "\",\"attributes\":{\"url\":\"\",\"handl\":\"\"},\"state\":\"'closed'\",\"children\":" + cate_str + "}";
/*     */     }
/* 767 */     return "";
/*     */   }
/*     */ 
/*     */   public static String getZTCategoryTreeStrWidthZTName(String site_id)
/*     */   {
/* 777 */     String roo_name = "专题栏目";
/* 778 */     String cate_str = ZTCategoryManager.getZTCategoryTreeJsonStr(site_id);
/* 779 */     if ((cate_str != null) && (!"[]".equals(cate_str))) {
/* 780 */       return "{\"id\":0,\"text\":\"" + roo_name + "\",\"attributes\":{\"url\":\"\",\"handl\":\"\"},\"state\":\"'closed'\",\"children\":" + cate_str + "}";
/*     */     }
/* 782 */     return "";
/*     */   }
/*     */ 
/*     */   public static List<CategoryBean> getZTCategoryListByZTCat_id(int zt_cateid)
/*     */   {
/* 788 */     List l = new ArrayList();
/* 789 */     Set s = CategoryManager.category_m.keySet();
/* 790 */     for (Iterator localIterator = s.iterator(); localIterator.hasNext(); ) { int i = ((Integer)localIterator.next()).intValue();
/*     */ 
/* 792 */       CategoryBean zb = (CategoryBean)CategoryManager.category_m.get(Integer.valueOf(i));
/* 793 */       if (zt_cateid == zb.getZt_cat_id())
/* 794 */         l.add(zb);
/*     */     }
/* 796 */     if ((l != null) && (l.size() > 0))
/*     */     {
/* 798 */       Collections.sort(l, new CategoryManager.CategoryComparator());
/* 799 */     }return l;
/*     */   }
/*     */ 
/*     */   public static void main(String[] arg)
/*     */   {
/* 809 */     String str = "$1$2$3$";
/* 810 */     List catpList = Arrays.asList(str.split("\\$"));
/* 811 */     for (String st : catpList)
/* 812 */       if ((st != null) && (!"".equals(st)))
/* 813 */         System.out.println(st);
/*     */   }
/*     */ 
/*     */   public static List<InfoBean> getInfoListByUserIDTimeType(Map<String, String> map)
/*     */   {
/* 823 */     return CmsCountDAO.getInfoListByUserIDTimeType(map);
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.cms.count.CmsCountManager
 * JD-Core Version:    0.6.2
 */