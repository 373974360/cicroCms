/*     */ package com.cicro.wcm.services.appeal.count;
/*     */ 
/*     */ import com.cicro.util.CalculateNumber;
/*     */ import com.cicro.wcm.bean.appeal.count.CategoryBean;
/*     */ import com.cicro.wcm.bean.appeal.count.HandleBean;
/*     */ import com.cicro.wcm.bean.appeal.cpDept.CpDeptBean;
/*     */ import com.cicro.wcm.dao.appeal.count.CountDAO;
/*     */ import java.io.PrintStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class CountDeptTreeServices
/*     */ {
/*     */   private static String getDeptList(String s, String e, String b_ids)
/*     */   {
/*  39 */     String strResult = "";
/*  40 */     System.out.println("-----getDeptList-----start--------");
/*     */     try {
/*  42 */       List beanList = getListNew(s, e, b_ids);
/*  43 */       return strResult + setTreeNode((CategoryBean)beanList.get(0));
/*     */     }
/*     */     catch (Exception t) {
/*  46 */       t.printStackTrace();
/*  47 */     }return strResult;
/*     */   }
/*     */ 
/*     */   private static String setTreeNode(CategoryBean bean)
/*     */   {
/*  56 */     StringBuffer sb = new StringBuffer();
/*     */     try
/*     */     {
/*  59 */       if (bean.getCategory_type().equals("leaf"))
/*     */       {
/*  61 */         String type_calss = "";
/*  62 */         if (bean.getP_id().equals("0")) {
/*  63 */           type_calss = "class='child-of-node-" + bean.getP_id() + "'";
/*     */         }
/*  65 */         sb.append("<tr id='node-" + bean.getCategory_id() + "' " + type_calss + " > <td><span class='file'>" + bean.getCategory_name() + "</span></td>");
/*  66 */         sb.append(setHandlList(bean.getChild_list()));
/*  67 */         sb.append("</tr>");
/*     */       } else {
/*  69 */         String type_calss = "";
/*  70 */         if (bean.getP_id().equals("0")) {
/*  71 */           type_calss = "class='child-of-node-" + bean.getP_id() + "'";
/*     */         }
/*  73 */         sb.append("<tr id='node-" + bean.getCategory_id() + "' " + type_calss + " > <td><span class='folder'>" + bean.getCategory_name() + "</span></td>");
/*  74 */         sb.append(setHandlList(bean.getChild_list()));
/*  75 */         sb.append("</tr>");
/*  76 */         List child_list = bean.getChild_list();
/*  77 */         if (child_list.size() > 0) {
/*  78 */           for (int i = 0; i < child_list.size(); i++)
/*     */           {
/*  80 */             sb.append(setTreeNode((CategoryBean)child_list.get(i)));
/*     */           }
/*     */         }
/*     */       }
/*     */ 
/*  85 */       return sb.toString();
/*     */     } catch (Exception e) {
/*  87 */       e.printStackTrace();
/*  88 */     }return sb.toString();
/*     */   }
/*     */ 
/*     */   private static String setHandlList(List<HandleBean> l)
/*     */   {
/*  95 */     StringBuffer sb = new StringBuffer("");
/*  96 */     if (l != null)
/*     */     {
/*  98 */       for (int i = 0; i < l.size(); i++)
/*     */       {
/* 100 */         sb.append("<td>" + ((HandleBean)l.get(i)).getHandle_name() + "</td>");
/*     */       }
/*     */     }
/* 103 */     return sb.toString();
/*     */   }
/*     */ 
/*     */   private static List<CpDeptBean> getDeptList()
/*     */   {
/* 111 */     return CountServicesUtil.getDeptmentList();
/*     */   }
/*     */ 
/*     */   public static List<CategoryBean> getListNew(String s, String e, String b_ids)
/*     */   {
/* 123 */     System.out.println("---getListNew ---- --  start ---");
/* 124 */     List l = new ArrayList();
/*     */     try {
/* 126 */       s = CountUtil.getTimeS(s);
/* 127 */       e = CountUtil.getTimeE(e);
/* 128 */       Map map = new HashMap();
/* 129 */       map.put("s", s);
/* 130 */       map.put("e", e);
/* 131 */       map.put("b_ids", b_ids);
/* 132 */       map.put("sq_flag", "0");
/* 133 */       List listDept = getLsitCpDeptBeanByPaerentId(0);
/* 134 */       System.out.println("listDept.size()====" + listDept.size());
/* 135 */       for (CpDeptBean bean : listDept) {
/* 136 */         CategoryBean cb = new CategoryBean();
/* 137 */         cb.setCategory_id(String.valueOf(bean.getDept_id()));
/* 138 */         cb.setCategory_name(bean.getDept_name());
/* 139 */         cb.setP_id(String.valueOf(bean.getParent_id()));
/* 140 */         cb.setHandle_list(getPublicHandl(bean.getDept_id(), map));
/* 141 */         cb.setChild_list(getchildList(bean.getDept_id(), map));
/* 142 */         l.add(cb);
/*     */       }
/* 144 */       System.out.println("---getListNew ---- --  end 11111111111 ---");
/*     */     } catch (Exception t) {
/* 146 */       t.printStackTrace();
/*     */     }
/* 148 */     System.out.println("---getListNew ---- --  end 2222222222222 ---");
/* 149 */     return l;
/*     */   }
/*     */ 
/*     */   public static List<CategoryBean> getListtTest(String s, String e, String b_ids) {
/* 153 */     System.out.println("---getListtTest ---- --  start ---");
/* 154 */     List l = new ArrayList();
/*     */     try {
/* 156 */       s = CountUtil.getTimeS(s);
/* 157 */       e = CountUtil.getTimeE(e);
/* 158 */       Map map = new HashMap();
/* 159 */       map.put("s", s);
/* 160 */       map.put("e", e);
/* 161 */       map.put("b_ids", b_ids);
/* 162 */       map.put("sq_flag", "0");
/* 163 */       List listDept = getLsitCpDeptBeanByPaerentId(0);
/* 164 */       System.out.println("listDept.size()====" + listDept.size());
/* 165 */       for (CpDeptBean bean : listDept) {
/* 166 */         CategoryBean cb = new CategoryBean();
/* 167 */         cb.setCategory_id(String.valueOf(bean.getDept_id()));
/* 168 */         cb.setCategory_name(bean.getDept_name());
/* 169 */         cb.setP_id(String.valueOf(bean.getParent_id()));
/* 170 */         cb.setHandle_list(getPublicHandl(bean.getDept_id(), map));
/* 171 */         cb.setChild_list(getchildList(bean.getDept_id(), map));
/* 172 */         l.add(cb);
/*     */       }
/* 174 */       System.out.println("---getListtTest ---- --  end 11111111111 ---");
/*     */     } catch (Exception t) {
/* 176 */       t.printStackTrace();
/*     */     }
/* 178 */     System.out.println("---getListtTest ---- --  end 2222222222222 ---");
/* 179 */     return l;
/*     */   }
/*     */ 
/*     */   public static List<CategoryBean> getListType(String s, String e, String b_ids)
/*     */   {
/* 190 */     List l = new ArrayList();
/*     */     try {
/* 192 */       s = CountUtil.getTimeS(s);
/* 193 */       e = CountUtil.getTimeE(e);
/* 194 */       Map map = new HashMap();
/* 195 */       map.put("s", s);
/* 196 */       map.put("e", e);
/* 197 */       map.put("b_ids", b_ids);
/* 198 */       map.put("type", "2");
/* 199 */       List listDept = getLsitCpDeptBeanByPaerentId(0);
/* 200 */       for (CpDeptBean bean : listDept) {
/* 201 */         CategoryBean cb = new CategoryBean();
/* 202 */         cb.setCategory_id(String.valueOf(bean.getDept_id()));
/* 203 */         cb.setCategory_name(bean.getDept_name());
/* 204 */         cb.setP_id(String.valueOf(bean.getParent_id()));
/* 205 */         cb.setHandle_list(getPublicHandlType(bean.getDept_id(), map));
/* 206 */         cb.setChild_list(getchildList(bean.getDept_id(), map));
/* 207 */         l.add(cb);
/*     */       }
/*     */     } catch (Exception t) {
/* 210 */       t.printStackTrace();
/*     */     }
/* 212 */     return l;
/*     */   }
/*     */ 
/*     */   public static List<CategoryBean> getListCaution(String s, String e, String b_ids)
/*     */   {
/* 223 */     List l = new ArrayList();
/*     */     try {
/* 225 */       s = CountUtil.getTimeS(s);
/* 226 */       e = CountUtil.getTimeE(e);
/* 227 */       Map map = new HashMap();
/* 228 */       map.put("s", s);
/* 229 */       map.put("e", e);
/* 230 */       map.put("b_ids", b_ids);
/* 231 */       map.put("type", "3");
/* 232 */       List listDept = getLsitCpDeptBeanByPaerentId(0);
/* 233 */       for (CpDeptBean bean : listDept) {
/* 234 */         CategoryBean cb = new CategoryBean();
/* 235 */         cb.setCategory_id(String.valueOf(bean.getDept_id()));
/* 236 */         cb.setCategory_name(bean.getDept_name());
/* 237 */         cb.setP_id(String.valueOf(bean.getParent_id()));
/* 238 */         cb.setHandle_list(getPublicHandlCaution(bean.getDept_id(), map));
/* 239 */         cb.setChild_list(getchildList(bean.getDept_id(), map));
/* 240 */         l.add(cb);
/*     */       }
/*     */     } catch (Exception t) {
/* 243 */       t.printStackTrace();
/*     */     }
/* 245 */     return l;
/*     */   }
/*     */ 
/*     */   public static List<CategoryBean> getListPur(String s, String e, String b_ids)
/*     */   {
/* 257 */     List l = new ArrayList();
/*     */     try {
/* 259 */       s = CountUtil.getTimeS(s);
/* 260 */       e = CountUtil.getTimeE(e);
/* 261 */       Map map = new HashMap();
/* 262 */       map.put("s", s);
/* 263 */       map.put("e", e);
/* 264 */       map.put("b_ids", b_ids);
/* 265 */       map.put("type", "4");
/* 266 */       List listDept = getLsitCpDeptBeanByPaerentId(0);
/* 267 */       for (CpDeptBean bean : listDept) {
/* 268 */         CategoryBean cb = new CategoryBean();
/* 269 */         cb.setCategory_id(String.valueOf(bean.getDept_id()));
/* 270 */         cb.setCategory_name(bean.getDept_name());
/* 271 */         cb.setP_id(String.valueOf(bean.getParent_id()));
/* 272 */         cb.setHandle_list(getPublicHandlPur(bean.getDept_id(), map));
/* 273 */         cb.setChild_list(getchildList(bean.getDept_id(), map));
/* 274 */         l.add(cb);
/*     */       }
/*     */     } catch (Exception t) {
/* 277 */       t.printStackTrace();
/*     */     }
/* 279 */     return l;
/*     */   }
/*     */ 
/*     */   public static List<CategoryBean> getListAllSatisfactionDept(String s, String e, String b_ids)
/*     */   {
/* 291 */     List l = new ArrayList();
/*     */     try {
/* 293 */       s = CountUtil.getTimeS(s);
/* 294 */       e = CountUtil.getTimeE(e);
/* 295 */       Map map = new HashMap();
/* 296 */       map.put("s", s);
/* 297 */       map.put("e", e);
/* 298 */       map.put("b_ids", b_ids);
/* 299 */       map.put("type", "5");
/* 300 */       List listDept = getLsitCpDeptBeanByPaerentId(0);
/* 301 */       for (CpDeptBean bean : listDept) {
/* 302 */         CategoryBean cb = new CategoryBean();
/* 303 */         cb.setCategory_id(String.valueOf(bean.getDept_id()));
/* 304 */         cb.setCategory_name(bean.getDept_name());
/* 305 */         cb.setP_id(String.valueOf(bean.getParent_id()));
/* 306 */         cb.setHandle_list(getPublicHandlSatisfactionDept(bean.getDept_id(), map));
/* 307 */         cb.setChild_list(getchildList(bean.getDept_id(), map));
/* 308 */         l.add(cb);
/*     */       }
/*     */     } catch (Exception t) {
/* 311 */       t.printStackTrace();
/*     */     }
/* 313 */     return l;
/*     */   }
/*     */ 
/*     */   public static List<CategoryBean> getchildList(int p_id, Map map)
/*     */   {
/* 323 */     List list = new ArrayList();
/*     */     try {
/* 325 */       List listDept = getLsitCpDeptBeanByPaerentId(p_id);
/* 326 */       System.out.println("getchildList ---- " + listDept.size() + "---" + p_id);
/* 327 */       for (int i = 0; i < listDept.size(); i++) {
/* 328 */         CpDeptBean bean = (CpDeptBean)listDept.get(i);
/* 329 */         CategoryBean cb = new CategoryBean();
/* 330 */         cb.setCategory_id(String.valueOf(bean.getDept_id()));
/* 331 */         cb.setCategory_name(bean.getDept_name());
/* 332 */         cb.setP_id(String.valueOf(bean.getParent_id()));
/*     */ 
/* 334 */         String type = (String)map.get("type");
/* 335 */         if ((type != null) && ("2".equals(type)))
/* 336 */           cb.setHandle_list(getPublicHandlType(bean.getDept_id(), map));
/* 337 */         else if ((type != null) && ("3".equals(type)))
/* 338 */           cb.setHandle_list(getPublicHandlCaution(bean.getDept_id(), map));
/* 339 */         else if ((type != null) && ("4".equals(type)))
/* 340 */           cb.setHandle_list(getPublicHandlPur(bean.getDept_id(), map));
/* 341 */         else if ((type != null) && ("5".equals(type)))
/* 342 */           cb.setHandle_list(getPublicHandlSatisfactionDept(bean.getDept_id(), map));
/*     */         else {
/* 344 */           cb.setHandle_list(getPublicHandl(bean.getDept_id(), map));
/*     */         }
/* 346 */         cb.setChild_list(getchildList(bean.getDept_id(), map));
/* 347 */         if (getLsitCpDeptBeanByPaerentId(bean.getDept_id()).size() == 0) {
/* 348 */           cb.setCategory_type("leaf");
/*     */         }
/* 350 */         list.add(cb);
/*     */       }
/*     */     } catch (Exception e) {
/* 353 */       e.printStackTrace();
/*     */     }
/* 355 */     return list;
/*     */   }
/*     */ 
/*     */   public static List<CpDeptBean> getLsitCpDeptBeanByPaerentId(int p_id)
/*     */   {
/* 364 */     List list = new ArrayList();
/*     */     try {
/* 366 */       List list0 = getDeptList();
/* 367 */       for (int i = 0; i < list0.size(); i++) {
/* 368 */         CpDeptBean dept = (CpDeptBean)list0.get(i);
/* 369 */         int id = dept.getParent_id();
/* 370 */         if (p_id == id)
/* 371 */           list.add(dept);
/*     */       }
/*     */     }
/*     */     catch (Exception e) {
/* 375 */       e.printStackTrace();
/*     */     }
/* 377 */     return list;
/*     */   }
/*     */ 
/*     */   public static List getPublicHandl(int dep_id, Map map)
/*     */   {
/* 389 */     List l = new ArrayList();
/*     */     try {
/* 391 */       String s = (String)map.get("s");
/* 392 */       String e = (String)map.get("e");
/* 393 */       String b_ids = (String)map.get("b_ids");
/*     */ 
/* 395 */       s = CountUtil.getTimeS(s);
/* 396 */       e = CountUtil.getTimeE(e);
/* 397 */       if (b_ids.endsWith(",")) {
/* 398 */         b_ids = b_ids.substring(0, b_ids.length() - 1);
/*     */       }
/*     */ 
/* 402 */       map.put("model_id", b_ids);
/* 403 */       map.put("do_dept", Integer.valueOf(dep_id));
/* 404 */       map.remove("timeout_flag");
/* 405 */       map.remove("sq_status");
/* 406 */       HandleBean hb = new HandleBean();
/* 407 */       hb.setHandle_id("h000" + dep_id + "1");
/* 408 */       String countAll = CountDAO.getCountByModelIdAndDept(map);
/* 409 */       hb.setHandle_name(countAll);
/* 410 */       l.add(hb);
/*     */ 
/* 413 */       HandleBean hb2 = new HandleBean();
/* 414 */       hb2.setHandle_id("h000" + dep_id + "2");
/* 415 */       map.put("sq_status", "0");
/* 416 */       String countdai = CountDAO.getCountByModelIdAndDept(map);
/* 417 */       hb2.setHandle_name(countdai);
/* 418 */       l.add(hb2);
/*     */ 
/* 421 */       HandleBean hb3 = new HandleBean();
/* 422 */       hb3.setHandle_id("h000" + dep_id + "3");
/* 423 */       map.put("sq_status", "1");
/*     */ 
/* 425 */       String countchu = CountDAO.getCountByModelIdAndDept(map);
/* 426 */       hb3.setHandle_name(countchu);
/*     */ 
/* 428 */       l.add(hb3);
/*     */ 
/* 431 */       HandleBean hb4 = new HandleBean();
/* 432 */       hb4.setHandle_id("h000" + dep_id + "3");
/* 433 */       map.put("sq_status", "2");
/* 434 */       String countshen = CountDAO.getCountByModelIdAndDept(map);
/* 435 */       hb4.setHandle_name(countshen);
/* 436 */       l.add(hb4);
/*     */ 
/* 439 */       HandleBean hb5 = new HandleBean();
/* 440 */       hb5.setHandle_id("h000" + dep_id + "3");
/* 441 */       map.put("sq_status", "3");
/* 442 */       String countend = CountDAO.getCountByModelIdAndDept(map);
/* 443 */       hb5.setHandle_name(countend);
/* 444 */       l.add(hb5);
/*     */ 
/* 447 */       HandleBean hb5_1 = new HandleBean();
/* 448 */       hb5_1.setHandle_id("h000" + dep_id + "3");
/* 449 */       int countwei = Integer.parseInt(countdai) + Integer.parseInt(countshen) + Integer.parseInt(countchu);
/* 450 */       hb5_1.setHandle_name(countwei);
/* 451 */       l.add(hb5_1);
/*     */ 
/* 473 */       HandleBean hb5_2 = new HandleBean();
/* 474 */       hb5_2.setHandle_id("h000" + dep_id + "3");
/* 475 */       map.put("sq_status", "3");
/* 476 */       map.put("timeout_flag", "0");
/* 477 */       String countend_2 = CountDAO.getCountByModelIdAndDept(map);
/* 478 */       hb5_2.setHandle_name(countend_2);
/* 479 */       l.add(hb5_2);
/*     */ 
/* 482 */       HandleBean hb5_3 = new HandleBean();
/* 483 */       hb5_3.setHandle_id("h000" + dep_id + "3");
/* 484 */       int countend_3 = Integer.parseInt(countend) - Integer.parseInt(countend_2);
/* 485 */       hb5_3.setHandle_name(countend_3);
/* 486 */       l.add(hb5_3);
/*     */ 
/* 491 */       HandleBean hb5_4 = new HandleBean();
/* 492 */       hb5_4.setHandle_id("h000" + dep_id + "3");
/* 493 */       String countEndRate_4 = "0%";
/* 494 */       if ((countAll != null) && (!countAll.equals("0"))) {
/* 495 */         countEndRate_4 = CalculateNumber.getRate(countend_2, countAll, 0);
/*     */       }
/* 497 */       hb5_4.setHandle_name(countEndRate_4);
/* 498 */       l.add(hb5_4);
/*     */ 
/* 501 */       HandleBean hb6 = new HandleBean();
/* 502 */       hb6.setHandle_id("h000" + dep_id + "3");
/* 503 */       String countEndRate = "0%";
/* 504 */       if ((countAll != null) && (!countAll.equals("0"))) {
/* 505 */         countEndRate = CalculateNumber.getRate(countend, countAll, 0);
/*     */       }
/* 507 */       hb6.setHandle_name(countEndRate);
/* 508 */       l.add(hb6);
/*     */ 
/* 510 */       return l;
/*     */     } catch (Exception e) {
/* 512 */       e.printStackTrace();
/* 513 */     }return l;
/*     */   }
/*     */ 
/*     */   public static List getPublicHandlType(int dep_id, Map map)
/*     */   {
/* 525 */     List l = new ArrayList();
/*     */     try {
/* 527 */       String s = (String)map.get("s");
/* 528 */       String e = (String)map.get("e");
/* 529 */       String b_ids = (String)map.get("b_ids");
/*     */ 
/* 531 */       s = CountUtil.getTimeS(s);
/* 532 */       e = CountUtil.getTimeE(e);
/* 533 */       if (b_ids.endsWith(",")) {
/* 534 */         b_ids = b_ids.substring(0, b_ids.length() - 1);
/*     */       }
/*     */ 
/* 538 */       map.put("model_id", b_ids);
/* 539 */       map.put("do_dept", Integer.valueOf(dep_id));
/* 540 */       map.remove("sq_flag");
/* 541 */       HandleBean hb = new HandleBean();
/* 542 */       hb.setHandle_id("h000" + dep_id + "1");
/* 543 */       String countAll = CountDAO.getCountByModelIdAndDept(map);
/* 544 */       hb.setHandle_name(countAll);
/* 545 */       l.add(hb);
/*     */ 
/* 548 */       HandleBean hb2 = new HandleBean();
/* 549 */       hb2.setHandle_id("h000" + dep_id + "2");
/* 550 */       map.put("sq_flag", "0");
/* 551 */       String countNormal = CountDAO.getCountByModelIdAndDept(map);
/* 552 */       hb2.setHandle_name(countNormal);
/* 553 */       l.add(hb2);
/*     */ 
/* 556 */       HandleBean hb3 = new HandleBean();
/* 557 */       hb3.setHandle_id("h000" + dep_id + "3");
/* 558 */       map.put("sq_flag", "-1");
/* 559 */       String countInvalid = CountDAO.getCountByModelIdAndDept(map);
/* 560 */       hb3.setHandle_name(countInvalid);
/* 561 */       l.add(hb3);
/*     */ 
/* 564 */       HandleBean hb4 = new HandleBean();
/* 565 */       hb4.setHandle_id("h000" + dep_id + "3");
/* 566 */       map.put("sq_flag", "1");
/* 567 */       String countRepeat = CountDAO.getCountByModelIdAndDept(map);
/* 568 */       hb4.setHandle_name(countRepeat);
/* 569 */       l.add(hb4);
/*     */ 
/* 572 */       HandleBean hb5 = new HandleBean();
/* 573 */       hb5.setHandle_id("h000" + dep_id + "3");
/* 574 */       map.put("sq_flag", "2");
/* 575 */       String countNohandle = CountDAO.getCountByModelIdAndDept(map);
/* 576 */       hb5.setHandle_name(countNohandle);
/* 577 */       l.add(hb5);
/*     */ 
/* 579 */       return l;
/*     */     } catch (Exception e) {
/* 581 */       e.printStackTrace();
/* 582 */     }return l;
/*     */   }
/*     */ 
/*     */   public static List getPublicHandlCaution(int dep_id, Map map)
/*     */   {
/* 594 */     List l = new ArrayList();
/*     */     try {
/* 596 */       String s = (String)map.get("s");
/* 597 */       String e = (String)map.get("e");
/* 598 */       String b_ids = (String)map.get("b_ids");
/*     */ 
/* 600 */       s = CountUtil.getTimeS(s);
/* 601 */       e = CountUtil.getTimeE(e);
/* 602 */       if (b_ids.endsWith(",")) {
/* 603 */         b_ids = b_ids.substring(0, b_ids.length() - 1);
/*     */       }
/*     */ 
/* 607 */       map.put("model_id", b_ids);
/* 608 */       map.put("do_dept", Integer.valueOf(dep_id));
/* 609 */       HandleBean hb = new HandleBean();
/* 610 */       hb.setHandle_id("h000" + dep_id + "1");
/*     */ 
/* 612 */       String count = CountDAO.getCountByModelIdAndDept(map);
/* 613 */       hb.setHandle_name(count);
/* 614 */       l.add(hb);
/*     */ 
/* 617 */       HandleBean hb2 = new HandleBean();
/* 618 */       hb2.setHandle_id("h000" + dep_id + "2");
/* 619 */       map.put("timeout_flag", "1");
/* 620 */       String countOver = CountDAO.getCountByModelIdAndDept(map);
/* 621 */       hb2.setHandle_name(countOver);
/* 622 */       l.add(hb2);
/*     */ 
/* 625 */       HandleBean hb3 = new HandleBean();
/* 626 */       hb3.setHandle_id("h000" + dep_id + "3");
/* 627 */       map.remove("timeout_flag");
/* 628 */       map.put("alarm_flag", "1");
/* 629 */       String countWarn = CountDAO.getCountByModelIdAndDept(map);
/* 630 */       hb3.setHandle_name(countWarn);
/* 631 */       l.add(hb3);
/*     */ 
/* 634 */       HandleBean hb4 = new HandleBean();
/* 635 */       hb4.setHandle_id("h000" + dep_id + "3");
/* 636 */       map.put("alarm_flag", "2");
/* 637 */       String countYellow = CountDAO.getCountByModelIdAndDept(map);
/* 638 */       hb4.setHandle_name(countYellow);
/* 639 */       l.add(hb4);
/*     */ 
/* 642 */       HandleBean hb5 = new HandleBean();
/* 643 */       hb5.setHandle_id("h000" + dep_id + "3");
/* 644 */       map.put("alarm_flag", "3");
/* 645 */       String countRed = CountDAO.getCountByModelIdAndDept(map);
/* 646 */       hb5.setHandle_name(countRed);
/* 647 */       l.add(hb5);
/*     */ 
/* 650 */       HandleBean hb6 = new HandleBean();
/* 651 */       hb6.setHandle_id("h000" + dep_id + "3");
/* 652 */       map.remove("alarm_flag");
/* 653 */       map.put("supervise_flag", "1");
/* 654 */       String countSupervise = CountDAO.getCountByModelIdAndDept(map);
/* 655 */       hb6.setHandle_name(countSupervise);
/* 656 */       l.add(hb6);
/*     */ 
/* 659 */       HandleBean hb7 = new HandleBean();
/* 660 */       hb7.setHandle_id("h000" + dep_id + "3");
/* 661 */       map.remove("supervise_flag");
/* 662 */       map.put("limit_flag", "1");
/* 663 */       String countLimit = CountDAO.getCountByModelIdAndDept(map);
/* 664 */       hb7.setHandle_name(countLimit);
/* 665 */       l.add(hb7);
/*     */ 
/* 667 */       return l;
/*     */     } catch (Exception e) {
/* 669 */       e.printStackTrace();
/* 670 */     }return l;
/*     */   }
/*     */ 
/*     */   public static List getPublicHandlPur(int dep_id, Map map)
/*     */   {
/* 684 */     List l = new ArrayList();
/*     */     try {
/* 686 */       String s = (String)map.get("s");
/* 687 */       String e = (String)map.get("e");
/* 688 */       String b_ids = (String)map.get("b_ids");
/*     */ 
/* 690 */       s = CountUtil.getTimeS(s);
/* 691 */       e = CountUtil.getTimeE(e);
/* 692 */       if (b_ids.endsWith(",")) {
/* 693 */         b_ids = b_ids.substring(0, b_ids.length() - 1);
/*     */       }
/*     */ 
/* 697 */       map.put("model_id", b_ids);
/* 698 */       map.put("do_dept", Integer.valueOf(dep_id));
/*     */ 
/* 701 */       List listPurpose = CountServicesUtil.getPurposeList();
/* 702 */       List tempList = new ArrayList();
/* 703 */       int countAll = 0;
/* 704 */       for (int i = 0; i < listPurpose.size(); i++) {
/* 705 */         map.put("pur_id", ((Map)listPurpose.get(i)).get("id"));
/* 706 */         String count = CountDAO.getCountByModelIdAndDept(map);
/*     */ 
/* 708 */         countAll += Integer.valueOf(count).intValue();
/* 709 */         tempList.add(count);
/*     */       }
/* 711 */       HandleBean hb = new HandleBean();
/* 712 */       hb.setHandle_id("h000" + dep_id + "1");
/* 713 */       hb.setHandle_name(String.valueOf(countAll));
/*     */ 
/* 715 */       l.add(hb);
/*     */ 
/* 717 */       for (String str : tempList) {
/* 718 */         HandleBean hb2 = new HandleBean();
/* 719 */         hb2.setHandle_id("h000" + dep_id + "2");
/* 720 */         hb2.setHandle_name(str);
/*     */ 
/* 722 */         l.add(hb2);
/*     */       }
/*     */ 
/* 726 */       return l;
/*     */     } catch (Exception e) {
/* 728 */       e.printStackTrace();
/* 729 */     }return l;
/*     */   }
/*     */ 
/*     */   public static List getPublicHandlSatisfactionDept(int dep_id, Map map)
/*     */   {
/* 741 */     List l = new ArrayList();
/*     */     try {
/* 743 */       String s = (String)map.get("s");
/* 744 */       String e = (String)map.get("e");
/* 745 */       String b_ids = (String)map.get("b_ids");
/*     */ 
/* 747 */       s = CountUtil.getTimeS(s);
/* 748 */       e = CountUtil.getTimeE(e);
/* 749 */       if (b_ids.endsWith(",")) {
/* 750 */         b_ids = b_ids.substring(0, b_ids.length() - 1);
/*     */       }
/*     */ 
/* 754 */       map.put("model_id", b_ids);
/* 755 */       map.put("do_dept", Integer.valueOf(dep_id));
/*     */ 
/* 758 */       List listSatisfaction = CountServicesUtil.getSatisfactionList();
/*     */ 
/* 760 */       map.put("sq_status", "2");
/* 761 */       String countEnd = CountDAO.getCountByModelIdAndDept(map);
/* 762 */       HandleBean hb = new HandleBean();
/* 763 */       hb.setHandle_id("h000" + dep_id + "1");
/* 764 */       hb.setHandle_name(String.valueOf(countEnd));
/* 765 */       l.add(hb);
/*     */ 
/* 768 */       map.put("sat_score", "0");
/* 769 */       String countRecord = CountDAO.getCountByModelIdAndDept(map);
/* 770 */       HandleBean hb1 = new HandleBean();
/* 771 */       hb1.setHandle_id("h000" + dep_id + "1");
/* 772 */       hb1.setHandle_name(String.valueOf(countRecord));
/* 773 */       l.add(hb1);
/*     */ 
/* 775 */       int countAll = 0;
/* 776 */       for (int i = 0; i < listSatisfaction.size(); i++) {
/* 777 */         map.put("sat_id", ((Map)listSatisfaction.get(i)).get("id"));
/* 778 */         String count = CountDAO.getCountByModelIdAndDeptSat(map);
/* 779 */         if ((count == null) || (count.trim().equals(""))) {
/* 780 */           count = "0";
/*     */         }
/* 782 */         HandleBean hbBean = new HandleBean();
/* 783 */         hbBean.setHandle_id("h000" + dep_id + "1");
/* 784 */         hbBean.setHandle_name(String.valueOf(count));
/* 785 */         l.add(hbBean);
/* 786 */         countAll += Integer.valueOf(count).intValue();
/*     */       }
/*     */ 
/* 789 */       HandleBean hb4 = new HandleBean();
/* 790 */       hb4.setHandle_id("h000" + dep_id + "1");
/* 791 */       hb4.setHandle_name(String.valueOf(countAll));
/* 792 */       l.add(hb4);
/*     */ 
/* 794 */       return l;
/*     */     } catch (Exception e) {
/* 796 */       e.printStackTrace();
/* 797 */     }return l;
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.appeal.count.CountDeptTreeServices
 * JD-Core Version:    0.6.2
 */