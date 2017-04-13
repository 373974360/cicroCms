/*     */ package com.cicro.wcm.services.cms.count;
/*     */ 
/*     */ import com.cicro.wcm.bean.cms.category.CategoryBean;
/*     */ import com.cicro.wcm.bean.cms.category.ZTCategoryBean;
/*     */ import com.cicro.wcm.bean.cms.count.SiteCountBean;
/*     */ import com.cicro.wcm.bean.control.SiteBean;
/*     */ import com.cicro.wcm.bean.org.dept.DeptBean;
/*     */ import com.cicro.wcm.dao.cms.count.SiteCountDAO;
/*     */ import com.cicro.wcm.services.cms.category.CategoryManager;
/*     */ import com.cicro.wcm.services.cms.category.ZTCategoryManager;
/*     */ import com.cicro.wcm.services.control.site.SiteManager;
/*     */ import com.cicro.wcm.services.org.dept.DeptManager;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class SiteCountManager
/*     */ {
/*     */   public static List<SiteCountBean> getSiteCountListByInputUser(Map mp)
/*     */   {
/*     */     try
/*     */     {
/*  26 */       List listAll = SiteCountDAO.getSiteCountListByInput(mp);
/*     */ 
/*  28 */       mp.put("is_host", "0");
/*  29 */       List listZ = SiteCountDAO.getSiteCountListByInput(mp);
/*     */ 
/*  31 */       mp.put("is_host", "1");
/*  32 */       List listY = SiteCountDAO.getSiteCountListByInput(mp);
/*     */ 
/*  34 */       mp.put("is_host", "2");
/*  35 */       List listL = SiteCountDAO.getSiteCountListByInput(mp);
/*     */ 
/*  37 */       mp.put("info_status", "8");
/*  38 */       mp.remove("is_host");
/*  39 */       List listP = SiteCountDAO.getSiteCountListByInput(mp);
/*     */ 
/*  41 */       for (SiteCountBean siteCountBean : listAll) {
/*  42 */         int input_user = siteCountBean.getInput_user();
/*  43 */         siteCountBean.setAllCount(siteCountBean.getIcount());
/*  44 */         siteCountBean.setInputCount(siteCountBean.getIcount());
/*     */ 
/*  47 */         if ((listZ != null) && (listZ.size() > 0)) {
/*  48 */           for (SiteCountBean siteCountBeanZ : listZ)
/*  49 */             if (input_user == siteCountBeanZ.getInput_user()) {
/*  50 */               siteCountBean.setHostInfoCount(siteCountBeanZ.getIcount());
/*  51 */               break;
/*     */             }
/*     */         }
/*     */         else {
/*  55 */           siteCountBean.setHostInfoCount(0);
/*     */         }
/*     */ 
/*  59 */         if ((listY != null) && (listY.size() > 0)) {
/*  60 */           for (SiteCountBean siteCountBeanY : listY)
/*  61 */             if (input_user == siteCountBeanY.getInput_user()) {
/*  62 */               siteCountBean.setRefInfoCount(siteCountBeanY.getIcount());
/*  63 */               break;
/*     */             }
/*     */         }
/*     */         else {
/*  67 */           siteCountBean.setRefInfoCount(0);
/*     */         }
/*     */ 
/*  71 */         if ((listL != null) && (listL.size() > 0)) {
/*  72 */           for (SiteCountBean siteCountBeanL : listL)
/*  73 */             if (input_user == siteCountBeanL.getInput_user()) {
/*  74 */               siteCountBean.setLinkInfoCount(siteCountBeanL.getIcount());
/*  75 */               break;
/*     */             }
/*     */         }
/*     */         else {
/*  79 */           siteCountBean.setLinkInfoCount(0);
/*     */         }
/*     */ 
/*  83 */         if ((listP != null) && (listP.size() > 0)) {
/*  84 */           for (SiteCountBean siteCountBeanP : listP)
/*  85 */             if (input_user == siteCountBeanP.getInput_user()) {
/*  86 */               siteCountBean.setReleasedCount(siteCountBeanP.getIcount());
/*  87 */               break;
/*     */             }
/*     */         }
/*     */         else {
/*  91 */           siteCountBean.setReleasedCount(0);
/*     */         }
/*     */ 
/*  94 */         siteCountBean.setReleaseRate();
/*     */       }
/*     */ 
/*  98 */       return listAll;
/*     */     } catch (Exception e) {
/* 100 */       e.printStackTrace();
/* 101 */     }return null;
/*     */   }
/*     */ 
/*     */   public static List<SiteCountBean> getSiteCountListByInput(Map mp)
/*     */   {
/* 107 */     List listResult = new ArrayList();
/*     */     try
/*     */     {
/* 110 */       List listAll = getSiteCountListByInputUser(mp);
/*     */ 
/* 112 */       List deptBeans = DeptManager.getChildDeptListByID("0");
/* 113 */       for (DeptBean deptBean : deptBeans) {
/* 114 */         SiteCountBean bean = getCateChildList(deptBean, listAll);
/* 115 */         listResult.add(bean);
/*     */       }
/*     */ 
/* 118 */       return listResult;
/*     */     } catch (Exception e) {
/* 120 */       e.printStackTrace();
/* 121 */     }return listResult;
/*     */   }
/*     */ 
/*     */   private static SiteCountBean getCateChildList(DeptBean deptBean, List<SiteCountBean> list)
/*     */   {
/* 127 */     SiteCountBean siteCountBean = new SiteCountBean();
/* 128 */     siteCountBean.setDept_id(deptBean.getDept_id());
/* 129 */     siteCountBean.setDept_name(deptBean.getDept_name());
/* 130 */     siteCountBean.setTree_position(deptBean.getTree_position());
/*     */ 
/* 132 */     List deptBeans = DeptManager.getChildDeptListByID(String.valueOf(deptBean.getDept_id()));
/*     */     DeptBean beanR;
/* 133 */     if (deptBeans.size() != 0) {
/* 134 */       siteCountBean.setIs_leaf(false);
/* 135 */       List childList = new ArrayList();
/* 136 */       int inputCount = 0;
/* 137 */       int hostInfoCount = 0;
/* 138 */       int refInfoCount = 0;
/* 139 */       int linkInfoCount = 0;
/* 140 */       int releasedCount = 0;
/*     */ 
/* 142 */       for (SiteCountBean bean : list) {
/* 143 */         if (siteCountBean.getDept_id() == bean.getDept_id()) {
/* 144 */           inputCount += bean.getInputCount();
/* 145 */           hostInfoCount += bean.getHostInfoCount();
/* 146 */           refInfoCount += bean.getRefInfoCount();
/* 147 */           linkInfoCount += bean.getLinkInfoCount();
/* 148 */           releasedCount += bean.getReleasedCount();
/*     */         }
/*     */       }
/* 151 */       for (??? = deptBeans.iterator(); ???.hasNext(); ) { beanR = (DeptBean)???.next();
/* 152 */         SiteCountBean refBean = getCateChildList(beanR, list);
/* 153 */         childList.add(refBean);
/*     */       }
/* 155 */       siteCountBean.setInputCount(inputCount);
/* 156 */       siteCountBean.setHostInfoCount(hostInfoCount);
/* 157 */       siteCountBean.setRefInfoCount(refInfoCount);
/* 158 */       siteCountBean.setLinkInfoCount(linkInfoCount);
/* 159 */       siteCountBean.setReleasedCount(releasedCount);
/* 160 */       siteCountBean.setReleaseRate();
/* 161 */       siteCountBean.setChild_list(childList);
/*     */     } else {
/* 163 */       int inputCount = 0;
/* 164 */       int hostInfoCount = 0;
/* 165 */       int refInfoCount = 0;
/* 166 */       int linkInfoCount = 0;
/* 167 */       int releasedCount = 0;
/* 168 */       for (SiteCountBean bean : list) {
/* 169 */         if (siteCountBean.getDept_id() == bean.getDept_id()) {
/* 170 */           inputCount += bean.getInputCount();
/* 171 */           hostInfoCount += bean.getHostInfoCount();
/* 172 */           refInfoCount += bean.getRefInfoCount();
/* 173 */           linkInfoCount += bean.getLinkInfoCount();
/* 174 */           releasedCount += bean.getReleasedCount();
/*     */         }
/*     */       }
/* 177 */       siteCountBean.setInputCount(inputCount);
/* 178 */       siteCountBean.setHostInfoCount(hostInfoCount);
/* 179 */       siteCountBean.setRefInfoCount(refInfoCount);
/* 180 */       siteCountBean.setLinkInfoCount(linkInfoCount);
/* 181 */       siteCountBean.setReleasedCount(releasedCount);
/* 182 */       siteCountBean.setReleaseRate();
/*     */     }
/*     */ 
/* 185 */     return siteCountBean;
/*     */   }
/*     */ 
/*     */   public static List<SiteCountBean> getSiteCountListBySite(Map mp)
/*     */   {
/*     */     try
/*     */     {
/* 193 */       List listAll = SiteCountDAO.getSiteCountListBySite(mp);
/*     */ 
/* 195 */       mp.put("is_host", "0");
/* 196 */       List listZ = SiteCountDAO.getSiteCountListBySite(mp);
/*     */ 
/* 198 */       mp.put("is_host", "1");
/* 199 */       List listY = SiteCountDAO.getSiteCountListBySite(mp);
/*     */ 
/* 201 */       mp.put("is_host", "2");
/* 202 */       List listL = SiteCountDAO.getSiteCountListBySite(mp);
/*     */ 
/* 204 */       mp.put("info_status", "8");
/* 205 */       mp.remove("is_host");
/* 206 */       List listP = SiteCountDAO.getSiteCountListBySite(mp);
/*     */ 
/* 208 */       for (SiteCountBean siteCountBean : listAll) {
/* 209 */         String site_id = siteCountBean.getSite_id();
/* 210 */         siteCountBean.setAllCount(siteCountBean.getIcount());
/* 211 */         siteCountBean.setInputCount(siteCountBean.getIcount());
/*     */ 
/* 214 */         if ((listZ != null) && (listZ.size() > 0)) {
/* 215 */           for (SiteCountBean siteCountBeanZ : listZ)
/* 216 */             if (site_id.equals(siteCountBeanZ.getSite_id())) {
/* 217 */               siteCountBean.setHostInfoCount(siteCountBeanZ.getIcount());
/* 218 */               break;
/*     */             }
/*     */         }
/*     */         else {
/* 222 */           siteCountBean.setHostInfoCount(0);
/*     */         }
/*     */ 
/* 226 */         if ((listY != null) && (listY.size() > 0)) {
/* 227 */           for (SiteCountBean siteCountBeanY : listY)
/* 228 */             if (site_id.equals(siteCountBeanY.getSite_id())) {
/* 229 */               siteCountBean.setRefInfoCount(siteCountBeanY.getIcount());
/* 230 */               break;
/*     */             }
/*     */         }
/*     */         else {
/* 234 */           siteCountBean.setRefInfoCount(0);
/*     */         }
/*     */ 
/* 238 */         if ((listL != null) && (listL.size() > 0)) {
/* 239 */           for (SiteCountBean siteCountBeanL : listL)
/* 240 */             if (site_id.equals(siteCountBeanL.getSite_id())) {
/* 241 */               siteCountBean.setLinkInfoCount(siteCountBeanL.getIcount());
/* 242 */               break;
/*     */             }
/*     */         }
/*     */         else {
/* 246 */           siteCountBean.setLinkInfoCount(0);
/*     */         }
/*     */ 
/* 250 */         if ((listP != null) && (listP.size() > 0)) {
/* 251 */           for (SiteCountBean siteCountBeanP : listP)
/* 252 */             if (site_id.equals(siteCountBeanP.getSite_id())) {
/* 253 */               siteCountBean.setReleasedCount(siteCountBeanP.getIcount());
/* 254 */               break;
/*     */             }
/*     */         }
/*     */         else {
/* 258 */           siteCountBean.setReleasedCount(0);
/*     */         }
/* 260 */         siteCountBean.setReleaseRate();
/*     */       }
/*     */ 
/* 264 */       SiteCountComparator countComparator = new SiteCountComparator();
/* 265 */       Collections.sort(listAll, countComparator);
/*     */ 
/* 267 */       return listAll;
/*     */     } catch (Exception e) {
/* 269 */       e.printStackTrace();
/* 270 */     }return null;
/*     */   }
/*     */ 
/*     */   public static List<SiteCountBean> getSiteCountListBySiteCate(Map mp)
/*     */   {
/*     */     try
/*     */     {
/* 278 */       List listAll = SiteCountDAO.getSiteCountListBySiteCate(mp);
/*     */ 
/* 280 */       mp.put("is_host", "0");
/* 281 */       List listZ = SiteCountDAO.getSiteCountListBySiteCate(mp);
/*     */ 
/* 283 */       mp.put("is_host", "1");
/* 284 */       List listY = SiteCountDAO.getSiteCountListBySiteCate(mp);
/*     */ 
/* 286 */       mp.put("is_host", "2");
/* 287 */       List listL = SiteCountDAO.getSiteCountListBySiteCate(mp);
/*     */ 
/* 289 */       mp.put("info_status", "8");
/* 290 */       mp.remove("is_host");
/* 291 */       List listP = SiteCountDAO.getSiteCountListBySiteCate(mp);
/*     */ 
/* 293 */       for (SiteCountBean siteCountBean : listAll) {
/* 294 */         int cat_id = siteCountBean.getCat_id();
/* 295 */         siteCountBean.setAllCount(siteCountBean.getIcount());
/* 296 */         siteCountBean.setInputCount(siteCountBean.getIcount());
/*     */ 
/* 299 */         if ((listZ != null) && (listZ.size() > 0)) {
/* 300 */           for (SiteCountBean siteCountBeanZ : listZ)
/* 301 */             if (cat_id == siteCountBeanZ.getCat_id()) {
/* 302 */               siteCountBean.setHostInfoCount(siteCountBeanZ.getIcount());
/* 303 */               break;
/*     */             }
/*     */         }
/*     */         else {
/* 307 */           siteCountBean.setHostInfoCount(0);
/*     */         }
/*     */ 
/* 311 */         if ((listY != null) && (listY.size() > 0)) {
/* 312 */           for (SiteCountBean siteCountBeanY : listY)
/* 313 */             if (cat_id == siteCountBeanY.getCat_id()) {
/* 314 */               siteCountBean.setRefInfoCount(siteCountBeanY.getIcount());
/* 315 */               break;
/*     */             }
/*     */         }
/*     */         else {
/* 319 */           siteCountBean.setRefInfoCount(0);
/*     */         }
/*     */ 
/* 323 */         if ((listL != null) && (listL.size() > 0)) {
/* 324 */           for (SiteCountBean siteCountBeanL : listL)
/* 325 */             if (cat_id == siteCountBeanL.getCat_id()) {
/* 326 */               siteCountBean.setLinkInfoCount(siteCountBeanL.getIcount());
/* 327 */               break;
/*     */             }
/*     */         }
/*     */         else {
/* 331 */           siteCountBean.setLinkInfoCount(0);
/*     */         }
/*     */ 
/* 335 */         if ((listP != null) && (listP.size() > 0)) {
/* 336 */           for (SiteCountBean siteCountBeanP : listP)
/* 337 */             if (cat_id == siteCountBeanP.getCat_id()) {
/* 338 */               siteCountBean.setReleasedCount(siteCountBeanP.getIcount());
/* 339 */               break;
/*     */             }
/*     */         }
/*     */         else {
/* 343 */           siteCountBean.setReleasedCount(0);
/*     */         }
/* 345 */         siteCountBean.setReleaseRate();
/*     */       }
/* 347 */       return listAll;
/*     */     } catch (Exception e) {
/* 349 */       e.printStackTrace();
/* 350 */     }return null;
/*     */   }
/*     */ 
/*     */   public static List<SiteCountBean> getSiteCountListByCate(Map mp)
/*     */   {
/* 357 */     List listResult = new ArrayList();
/*     */     try
/*     */     {
/* 360 */       List listAll = getSiteCountListBySiteCate(mp);
/*     */ 
/* 372 */       String site_id = (String)mp.get("site_id");
/* 373 */       SiteBean sb = SiteManager.getSiteBeanBySiteID(site_id);
/* 374 */       CategoryBean categoryBeanRoot = new CategoryBean();
/* 375 */       categoryBeanRoot.setCat_cname(sb.getSite_name());
/* 376 */       categoryBeanRoot.setCat_id(0);
/* 377 */       categoryBeanRoot.setSite_id(site_id);
/*     */ 
/* 380 */       SiteCountBean bean = getSiteChildListCate(categoryBeanRoot, listAll);
/* 381 */       listResult.add(bean);
/*     */ 
/* 384 */       int mun = -1;
/*     */ 
/* 386 */       SiteCountBean zt = new SiteCountBean();
/* 387 */       List childListZt = new ArrayList();
/* 388 */       int inputCountZt = 0;
/* 389 */       int hostInfoCountZt = 0;
/* 390 */       int refInfoCountZt = 0;
/* 391 */       int linkInfoCountZt = 0;
/* 392 */       int releasedCountZt = 0;
/* 393 */       List ztCategoryBeans = ZTCategoryManager.getZTCategoryList(site_id);
/* 394 */       if (ztCategoryBeans.size() > 0) {
/* 395 */         zt.setCat_id(mun--);
/* 396 */         zt.setIs_leaf(false);
/* 397 */         zt.setCat_name("专题信息");
/* 398 */         zt.setSite_id(site_id);
/*     */       }
/*     */ 
/* 402 */       for (ZTCategoryBean ztCategoryBean : ztCategoryBeans)
/*     */       {
/* 405 */         SiteCountBean beanZt = new SiteCountBean();
/* 406 */         beanZt.setCat_id(mun--);
/* 407 */         beanZt.setIs_leaf(false);
/* 408 */         beanZt.setCat_name(ztCategoryBean.getZt_cat_name());
/* 409 */         beanZt.setSite_id(site_id);
/* 410 */         List childList = new ArrayList();
/* 411 */         int inputCount = 0;
/* 412 */         int hostInfoCount = 0;
/* 413 */         int refInfoCount = 0;
/* 414 */         int linkInfoCount = 0;
/* 415 */         int releasedCount = 0;
/*     */ 
/* 417 */         List ztBeans = CategoryManager.getZTCategoryListBySiteAndType(ztCategoryBean.getZt_cat_id(), site_id);
/* 418 */         for (CategoryBean bean2 : ztBeans) {
/* 419 */           SiteCountBean siteCountBean = getSiteChildListCateZt(bean2, listAll);
/* 420 */           inputCount += siteCountBean.getInputCount();
/* 421 */           hostInfoCount += siteCountBean.getHostInfoCount();
/* 422 */           refInfoCount += siteCountBean.getRefInfoCount();
/* 423 */           linkInfoCount += siteCountBean.getLinkInfoCount();
/* 424 */           releasedCount += siteCountBean.getReleasedCount();
/* 425 */           childList.add(siteCountBean);
/*     */         }
/* 427 */         beanZt.setInputCount(inputCount);
/* 428 */         beanZt.setHostInfoCount(hostInfoCount);
/* 429 */         beanZt.setRefInfoCount(refInfoCount);
/* 430 */         beanZt.setLinkInfoCount(linkInfoCount);
/* 431 */         beanZt.setReleasedCount(releasedCount);
/* 432 */         beanZt.setReleaseRate();
/* 433 */         beanZt.setChild_list(childList);
/*     */ 
/* 435 */         inputCountZt += beanZt.getInputCount();
/* 436 */         hostInfoCountZt += beanZt.getHostInfoCount();
/* 437 */         refInfoCountZt += beanZt.getRefInfoCount();
/* 438 */         linkInfoCountZt += beanZt.getLinkInfoCount();
/* 439 */         releasedCountZt += beanZt.getReleasedCount();
/* 440 */         childListZt.add(beanZt);
/*     */       }
/*     */ 
/* 444 */       zt.setInputCount(inputCountZt);
/* 445 */       zt.setHostInfoCount(hostInfoCountZt);
/* 446 */       zt.setRefInfoCount(refInfoCountZt);
/* 447 */       zt.setLinkInfoCount(linkInfoCountZt);
/* 448 */       zt.setReleasedCount(releasedCountZt);
/* 449 */       zt.setReleaseRate();
/* 450 */       zt.setChild_list(childListZt);
/* 451 */       if (ztCategoryBeans.size() > 0) {
/* 452 */         listResult.add(zt);
/*     */       }
/*     */ 
/* 456 */       return listResult;
/*     */     } catch (Exception e) {
/* 458 */       e.printStackTrace();
/* 459 */     }return listResult;
/*     */   }
/*     */ 
/*     */   private static SiteCountBean getSiteChildListCate(CategoryBean categoryBean, List<SiteCountBean> list)
/*     */   {
/* 466 */     SiteCountBean siteCountBean = new SiteCountBean();
/* 467 */     siteCountBean.setCat_id(categoryBean.getCat_id());
/* 468 */     siteCountBean.setCat_name(categoryBean.getCat_cname());
/*     */ 
/* 470 */     List cateBeans = CategoryManager.getChildCategoryList(categoryBean.getCat_id(), categoryBean.getSite_id());
/*     */     CategoryBean beanR;
/* 471 */     if (cateBeans.size() != 0) {
/* 472 */       siteCountBean.setIs_leaf(false);
/* 473 */       List childList = new ArrayList();
/* 474 */       int inputCount = 0;
/* 475 */       int hostInfoCount = 0;
/* 476 */       int refInfoCount = 0;
/* 477 */       int linkInfoCount = 0;
/* 478 */       int releasedCount = 0;
/*     */ 
/* 480 */       for (SiteCountBean bean : list) {
/* 481 */         if (siteCountBean.getCat_id() == bean.getCat_id()) {
/* 482 */           inputCount += bean.getInputCount();
/* 483 */           hostInfoCount += bean.getHostInfoCount();
/* 484 */           refInfoCount += bean.getRefInfoCount();
/* 485 */           linkInfoCount += bean.getLinkInfoCount();
/* 486 */           releasedCount += bean.getReleasedCount();
/*     */         }
/*     */       }
/*     */ 
/* 490 */       for (??? = cateBeans.iterator(); ???.hasNext(); ) { beanR = (CategoryBean)???.next();
/* 491 */         if (beanR.getCat_type() == 0)
/*     */         {
/* 494 */           SiteCountBean refBean = getSiteChildListCate(beanR, list);
/* 495 */           inputCount += refBean.getInputCount();
/* 496 */           hostInfoCount += refBean.getHostInfoCount();
/* 497 */           refInfoCount += refBean.getRefInfoCount();
/* 498 */           linkInfoCount += refBean.getLinkInfoCount();
/* 499 */           releasedCount += refBean.getReleasedCount();
/*     */ 
/* 501 */           childList.add(refBean);
/*     */         } }
/* 503 */       siteCountBean.setInputCount(inputCount);
/* 504 */       siteCountBean.setHostInfoCount(hostInfoCount);
/* 505 */       siteCountBean.setRefInfoCount(refInfoCount);
/* 506 */       siteCountBean.setLinkInfoCount(linkInfoCount);
/* 507 */       siteCountBean.setReleasedCount(releasedCount);
/* 508 */       siteCountBean.setReleaseRate();
/* 509 */       siteCountBean.setChild_list(childList);
/*     */     } else {
/* 511 */       int inputCount = 0;
/* 512 */       int hostInfoCount = 0;
/* 513 */       int refInfoCount = 0;
/* 514 */       int linkInfoCount = 0;
/* 515 */       int releasedCount = 0;
/* 516 */       for (SiteCountBean bean : list) {
/* 517 */         if (siteCountBean.getCat_id() == bean.getCat_id()) {
/* 518 */           inputCount += bean.getInputCount();
/* 519 */           hostInfoCount += bean.getHostInfoCount();
/* 520 */           refInfoCount += bean.getRefInfoCount();
/* 521 */           linkInfoCount += bean.getLinkInfoCount();
/* 522 */           releasedCount += bean.getReleasedCount();
/*     */         }
/*     */       }
/* 525 */       siteCountBean.setInputCount(inputCount);
/* 526 */       siteCountBean.setHostInfoCount(hostInfoCount);
/* 527 */       siteCountBean.setRefInfoCount(refInfoCount);
/* 528 */       siteCountBean.setLinkInfoCount(linkInfoCount);
/* 529 */       siteCountBean.setReleasedCount(releasedCount);
/* 530 */       siteCountBean.setReleaseRate();
/*     */     }
/*     */ 
/* 533 */     return siteCountBean;
/*     */   }
/*     */ 
/*     */   private static SiteCountBean getSiteChildListCateZt(CategoryBean categoryBean, List<SiteCountBean> list)
/*     */   {
/* 539 */     SiteCountBean siteCountBean = new SiteCountBean();
/* 540 */     siteCountBean.setCat_id(categoryBean.getCat_id());
/* 541 */     siteCountBean.setCat_name(categoryBean.getCat_cname());
/*     */ 
/* 543 */     List cateBeans = CategoryManager.getChildCategoryList(categoryBean.getCat_id(), categoryBean.getSite_id());
/*     */     CategoryBean beanR;
/* 544 */     if (cateBeans.size() != 0) {
/* 545 */       siteCountBean.setIs_leaf(false);
/* 546 */       List childList = new ArrayList();
/* 547 */       int inputCount = 0;
/* 548 */       int hostInfoCount = 0;
/* 549 */       int refInfoCount = 0;
/* 550 */       int linkInfoCount = 0;
/* 551 */       int releasedCount = 0;
/*     */ 
/* 553 */       for (SiteCountBean bean : list) {
/* 554 */         if (siteCountBean.getCat_id() == bean.getCat_id()) {
/* 555 */           inputCount += bean.getInputCount();
/* 556 */           hostInfoCount += bean.getHostInfoCount();
/* 557 */           refInfoCount += bean.getRefInfoCount();
/* 558 */           linkInfoCount += bean.getLinkInfoCount();
/* 559 */           releasedCount += bean.getReleasedCount();
/*     */         }
/*     */       }
/*     */ 
/* 563 */       for (??? = cateBeans.iterator(); ???.hasNext(); ) { beanR = (CategoryBean)???.next();
/*     */ 
/* 567 */         SiteCountBean refBean = getSiteChildListCate(beanR, list);
/* 568 */         inputCount += refBean.getInputCount();
/* 569 */         hostInfoCount += refBean.getHostInfoCount();
/* 570 */         refInfoCount += refBean.getRefInfoCount();
/* 571 */         linkInfoCount += refBean.getLinkInfoCount();
/* 572 */         releasedCount += refBean.getReleasedCount();
/*     */ 
/* 574 */         childList.add(refBean);
/*     */       }
/* 576 */       siteCountBean.setInputCount(inputCount);
/* 577 */       siteCountBean.setHostInfoCount(hostInfoCount);
/* 578 */       siteCountBean.setRefInfoCount(refInfoCount);
/* 579 */       siteCountBean.setLinkInfoCount(linkInfoCount);
/* 580 */       siteCountBean.setReleasedCount(releasedCount);
/* 581 */       siteCountBean.setReleaseRate();
/* 582 */       siteCountBean.setChild_list(childList);
/*     */     } else {
/* 584 */       int inputCount = 0;
/* 585 */       int hostInfoCount = 0;
/* 586 */       int refInfoCount = 0;
/* 587 */       int linkInfoCount = 0;
/* 588 */       int releasedCount = 0;
/* 589 */       for (SiteCountBean bean : list) {
/* 590 */         if (siteCountBean.getCat_id() == bean.getCat_id()) {
/* 591 */           inputCount += bean.getInputCount();
/* 592 */           hostInfoCount += bean.getHostInfoCount();
/* 593 */           refInfoCount += bean.getRefInfoCount();
/* 594 */           linkInfoCount += bean.getLinkInfoCount();
/* 595 */           releasedCount += bean.getReleasedCount();
/*     */         }
/*     */       }
/* 598 */       siteCountBean.setInputCount(inputCount);
/* 599 */       siteCountBean.setHostInfoCount(hostInfoCount);
/* 600 */       siteCountBean.setRefInfoCount(refInfoCount);
/* 601 */       siteCountBean.setLinkInfoCount(linkInfoCount);
/* 602 */       siteCountBean.setReleasedCount(releasedCount);
/* 603 */       siteCountBean.setReleaseRate();
/*     */     }
/*     */ 
/* 606 */     return siteCountBean;
/*     */   }
/*     */ 
/*     */   public static List<SiteCountBean> getDeptInfoCountBySiteAndNum(Map map)
/*     */   {
/* 613 */     List listResult = new ArrayList();
/*     */     try
/*     */     {
/* 616 */       List listResultAll = new ArrayList();
/* 617 */       int num_size = Integer.valueOf((String)map.get("num_size")).intValue();
/* 618 */       List listResultTemp = getSiteCountListByInput(map);
/* 619 */       for (int i = 0; i < listResultTemp.size(); i++) {
/* 620 */         SiteCountBean siteCountBean = (SiteCountBean)listResultTemp.get(i);
/* 621 */         if (siteCountBean.isIs_leaf()) {
/* 622 */           listResultAll.add(siteCountBean);
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/* 627 */       DeptSiteCountComparator deptSiteCountComparator = new DeptSiteCountComparator();
/* 628 */       Collections.sort(listResultAll, deptSiteCountComparator);
/*     */ 
/* 630 */       for (int i = 0; i < listResultAll.size(); i++) {
/* 631 */         if (i >= num_size) {
/*     */           break;
/*     */         }
/* 634 */         listResult.add((SiteCountBean)listResultAll.get(i));
/*     */       }
/*     */ 
/* 638 */       return listResult;
/*     */     } catch (Exception e) {
/* 640 */       e.printStackTrace();
/* 641 */     }return listResult;
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.cms.count.SiteCountManager
 * JD-Core Version:    0.6.2
 */