/*     */ package com.cicro.wcm.services.appeal.count;
/*     */ 
/*     */ import com.cicro.util.CalculateNumber;
/*     */ import com.cicro.wcm.bean.appeal.count.CountBean;
/*     */ import com.cicro.wcm.bean.appeal.count.LetterCountBean;
/*     */ import com.cicro.wcm.bean.appeal.cpDept.CpDeptBean;
/*     */ import com.cicro.wcm.bean.appeal.cpUser.CPUserReleInfo;
/*     */ import com.cicro.wcm.dao.appeal.count.CountDAO;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class CountServices
/*     */ {
/*     */   public static List<CountBean> getCountAimHandle(String s, String e, String b_ids)
/*     */   {
/*  37 */     List list = new ArrayList();
/*     */     try {
/*  39 */       s = CountUtil.getTimeS(s);
/*  40 */       e = CountUtil.getTimeE(e);
/*  41 */       if (b_ids.endsWith(",")) {
/*  42 */         b_ids = b_ids.substring(0, b_ids.length() - 1);
/*     */       }
/*     */ 
/*  46 */       List listPurpose = CountServicesUtil.getPurposeList();
/*  47 */       for (Map mapPurpose : listPurpose) {
/*  48 */         String purposeId = (String)mapPurpose.get("id");
/*  49 */         String purposeName = (String)mapPurpose.get("name");
/*     */ 
/*  51 */         CountBean countBean1 = new CountBean();
/*     */ 
/*  53 */         countBean1.setPurpose_name(purposeName);
/*     */ 
/*  57 */         Map map = new HashMap();
/*  58 */         map.put("model_id", b_ids);
/*  59 */         map.put("pur_id", purposeId);
/*  60 */         map.put("s", s);
/*  61 */         map.put("e", e);
/*     */ 
/*  63 */         String countAll = CountDAO.getCountByModelIdAndPur(map);
/*     */ 
/*  65 */         map.put("sq_status", "0");
/*  66 */         String countDai = CountDAO.getCountByModelIdAndPurStatus(map);
/*     */ 
/*  68 */         map.put("sq_status", "1");
/*  69 */         String countChu = CountDAO.getCountByModelIdAndPurStatus(map);
/*     */ 
/*  71 */         map.put("sq_status", "2");
/*  72 */         String countShen = CountDAO.getCountByModelIdAndPurStatus(map);
/*     */ 
/*  74 */         map.put("sq_status", "3");
/*  75 */         String countEnd = CountDAO.getCountByModelIdAndPurStatus(map);
/*     */ 
/*  77 */         String countEndRate = "0%";
/*  78 */         if (!"0".equals(countAll)) {
/*  79 */           countEndRate = CalculateNumber.getRate(countEnd, countAll, 0);
/*     */         }
/*     */ 
/*  82 */         countBean1.setCountall(countAll);
/*  83 */         countBean1.setCountdai(countDai);
/*  84 */         countBean1.setCountchu(countChu);
/*  85 */         countBean1.setCountshen(countShen);
/*  86 */         countBean1.setCountend(countEnd);
/*  87 */         countBean1.setCountendp(countEndRate);
/*  88 */         list.add(countBean1);
/*     */       }
/*     */ 
/*  91 */       return list;
/*     */     } catch (Exception t) {
/*  93 */       t.printStackTrace();
/*  94 */     }return list;
/*     */   }
/*     */ 
/*     */   public static List<CountBean> getCountAimType(String s, String e, String b_ids)
/*     */   {
/* 107 */     List list = new ArrayList();
/*     */     try {
/* 109 */       s = CountUtil.getTimeS(s);
/* 110 */       e = CountUtil.getTimeE(e);
/* 111 */       if (b_ids.endsWith(",")) {
/* 112 */         b_ids = b_ids.substring(0, b_ids.length() - 1);
/*     */       }
/*     */ 
/* 116 */       List listPurpose = CountServicesUtil.getPurposeList();
/* 117 */       for (Map mapPurpose : listPurpose) {
/* 118 */         String purposeId = (String)mapPurpose.get("id");
/* 119 */         String purposeName = (String)mapPurpose.get("name");
/*     */ 
/* 121 */         CountBean countBean1 = new CountBean();
/*     */ 
/* 123 */         countBean1.setPurpose_name(purposeName);
/*     */ 
/* 125 */         Map map = new HashMap();
/* 126 */         map.put("model_id", b_ids);
/* 127 */         map.put("pur_id", purposeId);
/* 128 */         map.put("s", s);
/* 129 */         map.put("e", e);
/*     */ 
/* 131 */         String count = CountDAO.getCountByModelIdAndFlag(map);
/*     */ 
/* 133 */         map.put("sq_flag", "0");
/* 134 */         String countNormal = CountDAO.getCountByModelIdAndFlag(map);
/*     */ 
/* 136 */         map.put("sq_flag", "-1");
/* 137 */         String countInvalid = CountDAO.getCountByModelIdAndFlag(map);
/*     */ 
/* 139 */         map.put("sq_flag", "1");
/* 140 */         String countRepeat = CountDAO.getCountByModelIdAndFlag(map);
/*     */ 
/* 142 */         map.put("sq_flag", "2");
/* 143 */         String countNohandle = CountDAO.getCountByModelIdAndFlag(map);
/*     */ 
/* 145 */         countBean1.setCount(count);
/* 146 */         countBean1.setCount_normal(countNormal);
/* 147 */         countBean1.setCount_invalid(countInvalid);
/* 148 */         countBean1.setCount_repeat(countRepeat);
/* 149 */         countBean1.setCount_nohandle(countNohandle);
/* 150 */         list.add(countBean1);
/*     */       }
/*     */ 
/* 153 */       return list;
/*     */     } catch (Exception t) {
/* 155 */       t.printStackTrace();
/* 156 */     }return list;
/*     */   }
/*     */ 
/*     */   public static List<CountBean> getCountAimWarn(String s, String e, String b_ids)
/*     */   {
/* 170 */     List list = new ArrayList();
/*     */     try {
/* 172 */       s = CountUtil.getTimeS(s);
/* 173 */       e = CountUtil.getTimeE(e);
/* 174 */       if (b_ids.endsWith(",")) {
/* 175 */         b_ids = b_ids.substring(0, b_ids.length() - 1);
/*     */       }
/*     */ 
/* 179 */       List listPurpose = CountServicesUtil.getPurposeList();
/* 180 */       for (Map mapPurpose : listPurpose) {
/* 181 */         String purposeId = (String)mapPurpose.get("id");
/* 182 */         String purposeName = (String)mapPurpose.get("name");
/*     */ 
/* 184 */         CountBean countBean1 = new CountBean();
/*     */ 
/* 186 */         countBean1.setPurpose_name(purposeName);
/*     */ 
/* 188 */         Map map = new HashMap();
/* 189 */         map.put("model_id", b_ids);
/* 190 */         map.put("pur_id", purposeId);
/* 191 */         map.put("s", s);
/* 192 */         map.put("e", e);
/*     */ 
/* 194 */         String count = CountDAO.getCountByModelIdAndAlarm(map);
/*     */ 
/* 196 */         map.put("timeout_flag", "1");
/* 197 */         String countOver = CountDAO.getCountByModelIdAndAlarm(map);
/*     */ 
/* 199 */         map.remove("timeout_flag");
/* 200 */         map.put("alarm_flag", "1");
/* 201 */         String countWarn = CountDAO.getCountByModelIdAndAlarm(map);
/*     */ 
/* 203 */         map.put("alarm_flag", "2");
/* 204 */         String countYellow = CountDAO.getCountByModelIdAndAlarm(map);
/*     */ 
/* 206 */         map.put("alarm_flag", "3");
/* 207 */         String countRed = CountDAO.getCountByModelIdAndAlarm(map);
/*     */ 
/* 209 */         map.remove("alarm_flag");
/* 210 */         map.put("supervise_flag", "1");
/* 211 */         String countSupervise = CountDAO.getCountByModelIdAndAlarm(map);
/*     */ 
/* 213 */         map.remove("supervise_flag");
/* 214 */         map.put("limit_flag", "1");
/* 215 */         String countLimit = CountDAO.getCountByModelIdAndAlarm(map);
/*     */ 
/* 217 */         countBean1.setCount(count);
/* 218 */         countBean1.setCount_over(countOver);
/* 219 */         countBean1.setCount_warn(countWarn);
/* 220 */         countBean1.setCount_yellow(countYellow);
/* 221 */         countBean1.setCount_red(countRed);
/* 222 */         countBean1.setCount_supervise(countSupervise);
/* 223 */         countBean1.setCount_limit(countLimit);
/*     */ 
/* 225 */         list.add(countBean1);
/*     */       }
/*     */ 
/* 228 */       return list;
/*     */     } catch (Exception t) {
/* 230 */       t.printStackTrace();
/* 231 */     }return list;
/*     */   }
/*     */ 
/*     */   public static List<CountBean> getCountDeptHandle(String s, String e, String b_ids)
/*     */   {
/* 244 */     List list = new ArrayList();
/*     */     try {
/* 246 */       s = CountUtil.getTimeS(s);
/* 247 */       e = CountUtil.getTimeE(e);
/* 248 */       if (b_ids.endsWith(",")) {
/* 249 */         b_ids = b_ids.substring(0, b_ids.length() - 1);
/*     */       }
/*     */ 
/* 254 */       List listDept = CountServicesUtil.getDeptmentList();
/* 255 */       for (CpDeptBean deptBean : listDept) {
/* 256 */         String dep_id = String.valueOf(deptBean.getDept_id());
/* 257 */         CountBean countBean1 = new CountBean();
/*     */ 
/* 260 */         countBean1.setDept_name(deptBean.getDept_name());
/*     */ 
/* 263 */         countBean1.setNode_level(Integer.valueOf(deptBean.getDept_level()));
/*     */ 
/* 265 */         Map map = new HashMap();
/* 266 */         map.put("model_id", b_ids);
/* 267 */         map.put("s", s);
/* 268 */         map.put("e", e);
/* 269 */         map.put("do_dept", dep_id);
/* 270 */         map.put("sq_flag", "0");
/*     */ 
/* 272 */         String countAll = CountDAO.getCountByModelIdAndDept(map);
/*     */ 
/* 275 */         map.put("sq_status", "0");
/* 276 */         String countDai = CountDAO.getCountByModelIdAndDept(map);
/*     */ 
/* 278 */         map.put("sq_status", "1");
/* 279 */         String countChu = CountDAO.getCountByModelIdAndDept(map);
/*     */ 
/* 281 */         map.put("sq_status", "2");
/* 282 */         String countShen = CountDAO.getCountByModelIdAndDept(map);
/*     */ 
/* 284 */         map.put("sq_status", "3");
/* 285 */         String countEnd = CountDAO.getCountByModelIdAndDept(map);
/*     */ 
/* 291 */         map.put("sq_status", "3");
/* 292 */         map.put("timeout_flag", "0");
/* 293 */         String countend_2 = CountDAO.getCountByModelIdAndDept(map);
/* 294 */         countBean1.setCount_over(countend_2);
/*     */ 
/* 296 */         countBean1.setCount_warn(Integer.parseInt(countEnd) - Integer.parseInt(countend_2));
/*     */ 
/* 298 */         String countEndRate_4 = "0%";
/* 299 */         if ((countAll != null) && (!countAll.equals("0"))) {
/* 300 */           countEndRate_4 = CalculateNumber.getRate(countend_2, countAll, 0);
/*     */         }
/* 302 */         countBean1.setCount_yellow(countEndRate_4);
/*     */ 
/* 305 */         String countEndRate = "0%";
/* 306 */         if (!"0".equals(countAll)) {
/* 307 */           countEndRate = CalculateNumber.getRate(countEnd, countAll, 0);
/*     */         }
/*     */ 
/* 310 */         countBean1.setCountall(countAll);
/* 311 */         countBean1.setCountdai(countDai);
/* 312 */         countBean1.setCountchu(countChu);
/* 313 */         countBean1.setCountshen(countShen);
/* 314 */         countBean1.setCountend(countEnd);
/* 315 */         int countwei = Integer.parseInt(countDai) + Integer.parseInt(countShen) + Integer.parseInt(countChu);
/* 316 */         countBean1.setCountwei(countwei);
/* 317 */         countBean1.setCountendp(countEndRate);
/* 318 */         list.add(countBean1);
/*     */       }
/*     */ 
/* 321 */       return list;
/*     */     } catch (Exception t) {
/* 323 */       t.printStackTrace();
/* 324 */     }return list;
/*     */   }
/*     */ 
/*     */   public static List<CountBean> getCountUsersHandle(String s, String e, String b_ids)
/*     */   {
/* 336 */     List list = new ArrayList();
/*     */     try {
/* 338 */       s = CountUtil.getTimeS(s);
/* 339 */       e = CountUtil.getTimeE(e);
/* 340 */       if (b_ids.endsWith(",")) {
/* 341 */         b_ids = b_ids.substring(0, b_ids.length() - 1);
/*     */       }
/*     */ 
/* 344 */       List listUser = CountServicesUtil.getCPUserReleList();
/* 345 */       for (CPUserReleInfo CPUser : listUser) {
/* 346 */         String user_id = String.valueOf(CPUser.getUser_id());
/* 347 */         CountBean countBean1 = new CountBean();
/*     */ 
/* 349 */         countBean1.setUser_id(user_id);
/* 350 */         countBean1.setUser_realname(CPUser.getUser_realname());
/*     */ 
/* 352 */         Map map = new HashMap();
/* 353 */         map.put("model_id", b_ids);
/* 354 */         map.put("s", s);
/* 355 */         map.put("e", e);
/* 356 */         map.put("user_id", user_id);
/* 357 */         map.put("sq_flag", "0");
/*     */ 
/* 359 */         String countAll = CountDAO.getCountByModelIdAndUserID(map);
/*     */ 
/* 362 */         map.put("sq_status", "0");
/* 363 */         String countDai = CountDAO.getCountByModelIdAndUserID(map);
/*     */ 
/* 365 */         map.put("sq_status", "1");
/* 366 */         String countChu = CountDAO.getCountByModelIdAndUserID(map);
/*     */ 
/* 368 */         map.put("sq_status", "2");
/* 369 */         String countShen = CountDAO.getCountByModelIdAndUserID(map);
/*     */ 
/* 371 */         map.put("sq_status", "3");
/* 372 */         String countEnd = CountDAO.getCountByModelIdAndUserID(map);
/*     */ 
/* 375 */         String countEndRate = "0%";
/* 376 */         if (!"0".equals(countAll)) {
/* 377 */           countEndRate = CalculateNumber.getRate(countEnd, countAll, 0);
/*     */         }
/*     */ 
/* 380 */         countBean1.setCountall(countAll);
/* 381 */         countBean1.setCountdai(countDai);
/* 382 */         countBean1.setCountchu(countChu);
/* 383 */         countBean1.setCountshen(countShen);
/* 384 */         countBean1.setCountend(countEnd);
/* 385 */         int countwei = Integer.parseInt(countDai) + Integer.parseInt(countShen) + Integer.parseInt(countChu);
/* 386 */         countBean1.setCountwei(countwei);
/* 387 */         countBean1.setCountendp(countEndRate);
/* 388 */         list.add(countBean1);
/*     */       }
/*     */ 
/* 391 */       return list;
/*     */     } catch (Exception t) {
/* 393 */       t.printStackTrace();
/* 394 */     }return list;
/*     */   }
/*     */ 
/*     */   public static List<CountBean> getCountDeptType(String s, String e, String b_ids)
/*     */   {
/* 406 */     List list = new ArrayList();
/*     */     try {
/* 408 */       s = CountUtil.getTimeS(s);
/* 409 */       e = CountUtil.getTimeE(e);
/* 410 */       if (b_ids.endsWith(",")) {
/* 411 */         b_ids = b_ids.substring(0, b_ids.length() - 1);
/*     */       }
/*     */ 
/* 416 */       List listDept = CountServicesUtil.getDeptmentList();
/* 417 */       for (CpDeptBean deptBean : listDept) {
/* 418 */         String dep_id = String.valueOf(deptBean.getDept_id());
/* 419 */         CountBean countBean1 = new CountBean();
/*     */ 
/* 421 */         countBean1.setDept_name(deptBean.getDept_name());
/*     */ 
/* 423 */         Map map = new HashMap();
/* 424 */         map.put("model_id", b_ids);
/* 425 */         map.put("s", s);
/* 426 */         map.put("e", e);
/* 427 */         map.put("do_dept", dep_id);
/*     */ 
/* 429 */         String count = CountDAO.getCountByModelIdAndDept(map);
/*     */ 
/* 432 */         map.put("sq_flag", "0");
/* 433 */         String countNormal = CountDAO.getCountByModelIdAndDept(map);
/*     */ 
/* 435 */         map.put("sq_flag", "-1");
/* 436 */         String countInvalid = CountDAO.getCountByModelIdAndDept(map);
/*     */ 
/* 438 */         map.put("sq_flag", "1");
/* 439 */         String countRepeat = CountDAO.getCountByModelIdAndDept(map);
/*     */ 
/* 441 */         map.put("sq_flag", "2");
/* 442 */         String countNohandle = CountDAO.getCountByModelIdAndDept(map);
/*     */ 
/* 444 */         countBean1.setCount(count);
/* 445 */         countBean1.setCount_normal(countNormal);
/* 446 */         countBean1.setCount_invalid(countInvalid);
/* 447 */         countBean1.setCount_repeat(countRepeat);
/* 448 */         countBean1.setCount_nohandle(countNohandle);
/* 449 */         list.add(countBean1);
/*     */       }
/*     */ 
/* 452 */       return list;
/*     */     } catch (Exception t) {
/* 454 */       t.printStackTrace();
/* 455 */     }return list;
/*     */   }
/*     */ 
/*     */   public static List<CountBean> getCountDeptCaution(String s, String e, String b_ids)
/*     */   {
/* 468 */     List list = new ArrayList();
/*     */     try {
/* 470 */       s = CountUtil.getTimeS(s);
/* 471 */       e = CountUtil.getTimeE(e);
/* 472 */       if (b_ids.endsWith(",")) {
/* 473 */         b_ids = b_ids.substring(0, b_ids.length() - 1);
/*     */       }
/*     */ 
/* 478 */       List listDept = CountServicesUtil.getDeptmentList();
/* 479 */       for (CpDeptBean deptBean : listDept) {
/* 480 */         String dep_id = String.valueOf(deptBean.getDept_id());
/* 481 */         CountBean countBean1 = new CountBean();
/*     */ 
/* 483 */         countBean1.setDept_name(deptBean.getDept_name());
/*     */ 
/* 485 */         Map map = new HashMap();
/* 486 */         map.put("model_id", b_ids);
/* 487 */         map.put("s", s);
/* 488 */         map.put("e", e);
/* 489 */         map.put("do_dept", dep_id);
/*     */ 
/* 491 */         String count = CountDAO.getCountByModelIdAndDept(map);
/*     */ 
/* 494 */         map.put("timeout_flag", "1");
/* 495 */         String countOver = CountDAO.getCountByModelIdAndDept(map);
/*     */ 
/* 497 */         map.remove("timeout_flag");
/* 498 */         map.put("alarm_flag", "1");
/* 499 */         String countWarn = CountDAO.getCountByModelIdAndDept(map);
/*     */ 
/* 501 */         map.put("alarm_flag", "2");
/* 502 */         String countYellow = CountDAO.getCountByModelIdAndDept(map);
/*     */ 
/* 504 */         map.put("alarm_flag", "3");
/* 505 */         String countRed = CountDAO.getCountByModelIdAndDept(map);
/*     */ 
/* 507 */         map.remove("alarm_flag");
/* 508 */         map.put("supervise_flag", "1");
/* 509 */         String countSupervise = CountDAO.getCountByModelIdAndDept(map);
/*     */ 
/* 511 */         map.remove("supervise_flag");
/* 512 */         map.put("limit_flag", "1");
/* 513 */         String countLimit = CountDAO.getCountByModelIdAndDept(map);
/*     */ 
/* 515 */         countBean1.setCount(count);
/* 516 */         countBean1.setCount_over(countOver);
/* 517 */         countBean1.setCount_warn(countWarn);
/* 518 */         countBean1.setCount_yellow(countYellow);
/* 519 */         countBean1.setCount_red(countRed);
/* 520 */         countBean1.setCount_supervise(countSupervise);
/* 521 */         countBean1.setCount_limit(countLimit);
/* 522 */         list.add(countBean1);
/*     */       }
/*     */ 
/* 525 */       return list;
/*     */     } catch (Exception t) {
/* 527 */       t.printStackTrace();
/* 528 */     }return list;
/*     */   }
/*     */ 
/*     */   public static List<Object[]> getCountDeptPur(String s, String e, String b_ids)
/*     */   {
/* 541 */     List list = new ArrayList();
/*     */     try {
/* 543 */       s = CountUtil.getTimeS(s);
/* 544 */       e = CountUtil.getTimeE(e);
/* 545 */       if (b_ids.endsWith(",")) {
/* 546 */         b_ids = b_ids.substring(0, b_ids.length() - 1);
/*     */       }
/*     */ 
/* 550 */       List listPurpose = CountServicesUtil.getPurposeList();
/*     */ 
/* 553 */       List listDept = CountServicesUtil.getDeptmentList();
/* 554 */       for (CpDeptBean deptBean : listDept) {
/* 555 */         String dep_id = String.valueOf(deptBean.getDept_id());
/* 556 */         Object[] objects = new Object[listPurpose.size() + 2];
/* 557 */         objects[0] = deptBean.getDept_name();
/*     */ 
/* 559 */         Map map = new HashMap();
/* 560 */         map.put("model_id", b_ids);
/* 561 */         map.put("s", s);
/* 562 */         map.put("e", e);
/* 563 */         map.put("do_dept", dep_id);
/*     */ 
/* 565 */         int countAll = 0;
/*     */ 
/* 567 */         for (int i = 0; i < listPurpose.size(); i++) {
/* 568 */           map.put("pur_id", ((Map)listPurpose.get(i)).get("id"));
/* 569 */           String count = CountDAO.getCountByModelIdAndDept(map);
/* 570 */           objects[(i + 2)] = count;
/* 571 */           countAll += Integer.valueOf(count).intValue();
/*     */         }
/* 573 */         objects[1] = String.valueOf(countAll);
/* 574 */         list.add(objects);
/*     */       }
/*     */ 
/* 577 */       return list;
/*     */     } catch (Exception t) {
/* 579 */       t.printStackTrace();
/* 580 */     }return list;
/*     */   }
/*     */ 
/*     */   public static List<Object[]> getCountRecordDept(String s, String e, String b_ids)
/*     */   {
/* 593 */     List list = new ArrayList();
/*     */     try {
/* 595 */       s = CountUtil.getTimeS(s);
/* 596 */       e = CountUtil.getTimeE(e);
/* 597 */       if (b_ids.endsWith(",")) {
/* 598 */         b_ids = b_ids.substring(0, b_ids.length() - 1);
/*     */       }
/*     */ 
/* 602 */       List listSatisfaction = CountServicesUtil.getSatisfactionList();
/*     */ 
/* 605 */       List listDept = CountServicesUtil.getDeptmentList();
/* 606 */       for (CpDeptBean deptBean : listDept) {
/* 607 */         String dep_id = String.valueOf(deptBean.getDept_id());
/* 608 */         Object[] objects = new Object[listSatisfaction.size() + 4];
/* 609 */         objects[0] = deptBean.getDept_name();
/*     */ 
/* 611 */         Map map = new HashMap();
/* 612 */         map.put("model_id", b_ids);
/* 613 */         map.put("s", s);
/* 614 */         map.put("e", e);
/* 615 */         map.put("do_dept", dep_id);
/*     */ 
/* 618 */         map.put("sq_status", "2");
/* 619 */         String countEnd = CountDAO.getCountByModelIdAndDept(map);
/* 620 */         objects[1] = countEnd;
/*     */ 
/* 622 */         map.put("sat_score", "0");
/* 623 */         String countRecord = CountDAO.getCountByModelIdAndDept(map);
/* 624 */         objects[2] = countRecord;
/*     */ 
/* 626 */         int countAll = 0;
/* 627 */         for (int i = 0; i < listSatisfaction.size(); i++) {
/* 628 */           map.put("sat_id", ((Map)listSatisfaction.get(i)).get("id"));
/* 629 */           String count = CountDAO.getCountByModelIdAndDeptSat(map);
/* 630 */           if ((count == null) || (count.trim().equals(""))) {
/* 631 */             count = "0";
/*     */           }
/* 633 */           objects[(i + 3)] = count;
/* 634 */           countAll += Integer.valueOf(count).intValue();
/*     */         }
/* 636 */         objects[(listSatisfaction.size() + 3)] = String.valueOf(countAll);
/* 637 */         list.add(objects);
/*     */       }
/*     */ 
/* 640 */       return list;
/*     */     } catch (Exception t) {
/* 642 */       t.printStackTrace();
/* 643 */     }return list;
/*     */   }
/*     */ 
/*     */   public static List<Object[]> getCountRecordPur(String s, String e, String b_ids)
/*     */   {
/* 656 */     List list = new ArrayList();
/*     */     try {
/* 658 */       s = CountUtil.getTimeS(s);
/* 659 */       e = CountUtil.getTimeE(e);
/* 660 */       if (b_ids.endsWith(",")) {
/* 661 */         b_ids = b_ids.substring(0, b_ids.length() - 1);
/*     */       }
/*     */ 
/* 665 */       List listSatisfaction = CountServicesUtil.getSatisfactionList();
/*     */ 
/* 668 */       List listPurpose = CountServicesUtil.getPurposeList();
/* 669 */       for (Map mapPurpose : listPurpose) {
/* 670 */         String purposeId = (String)mapPurpose.get("id");
/* 671 */         String purposeName = (String)mapPurpose.get("name");
/*     */ 
/* 673 */         Object[] objects = new Object[listSatisfaction.size() + 4];
/*     */ 
/* 675 */         objects[0] = purposeName;
/*     */ 
/* 677 */         Map map = new HashMap();
/* 678 */         map.put("model_id", b_ids);
/* 679 */         map.put("pur_id", purposeId);
/* 680 */         map.put("s", s);
/* 681 */         map.put("e", e);
/*     */ 
/* 683 */         map.put("sq_status", "2");
/* 684 */         String countEnd = CountDAO.getCountByModelIdAndPur(map);
/* 685 */         objects[1] = countEnd;
/*     */ 
/* 687 */         map.put("sat_score", "0");
/* 688 */         String countRecord = CountDAO.getCountByModelIdAndDept(map);
/* 689 */         objects[2] = countRecord;
/*     */ 
/* 691 */         int countAll = 0;
/* 692 */         for (int i = 0; i < listSatisfaction.size(); i++) {
/* 693 */           map.put("sat_id", ((Map)listSatisfaction.get(i)).get("id"));
/* 694 */           String count = CountDAO.getCountByModelIdAndDeptSat(map);
/* 695 */           if ((count == null) || (count.trim().equals(""))) {
/* 696 */             count = "0";
/*     */           }
/* 698 */           objects[(i + 3)] = count;
/* 699 */           countAll += Integer.valueOf(count).intValue();
/*     */         }
/* 701 */         objects[(listSatisfaction.size() + 3)] = String.valueOf(countAll);
/* 702 */         list.add(objects);
/*     */       }
/*     */ 
/* 705 */       return list;
/*     */     } catch (Exception t) {
/* 707 */       t.printStackTrace();
/* 708 */     }return list;
/*     */   }
/*     */ 
/*     */   public static List<LetterCountBean> getListByModelIdAndDept(Map map)
/*     */   {
/* 719 */     List list = new ArrayList();
/*     */     try {
/* 721 */       return CountDAO.getListByModelIdAndDept(map);
/*     */     }
/*     */     catch (Exception e) {
/* 724 */       e.printStackTrace();
/* 725 */     }return list;
/*     */   }
/*     */ 
/*     */   public static String getListByModelIdAndDeptCount(Map map)
/*     */   {
/* 735 */     String count = "0";
/*     */     try {
/* 737 */       return CountDAO.getListByModelIdAndDeptCount(map);
/*     */     }
/*     */     catch (Exception e) {
/* 740 */       e.printStackTrace();
/* 741 */     }return count;
/*     */   }
/*     */ 
/*     */   public static List<LetterCountBean> getListByModelIdAndUserId(Map map)
/*     */   {
/* 752 */     List list = new ArrayList();
/*     */     try {
/* 754 */       return CountDAO.getListByModelIdAndUserID(map);
/*     */     }
/*     */     catch (Exception e) {
/* 757 */       e.printStackTrace();
/* 758 */     }return list;
/*     */   }
/*     */ 
/*     */   public static String getListByModelIdAndUserCount(Map map)
/*     */   {
/* 768 */     String count = "0";
/*     */     try {
/* 770 */       return CountDAO.getListByModelIdAndUserIDCount(map);
/*     */     }
/*     */     catch (Exception e) {
/* 773 */       e.printStackTrace();
/* 774 */     }return count;
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.appeal.count.CountServices
 * JD-Core Version:    0.6.2
 */