/*     */ package com.cicro.wcm.template.velocity.data;
/*     */ 
/*     */ import com.cicro.util.CalculateNumber;
/*     */ import com.cicro.util.DateUtil;
/*     */ import com.cicro.util.FormatUtil;
/*     */ import com.cicro.util.jconfig.JconfigUtilContainer;
/*     */ import com.cicro.wcm.bean.appeal.count.CountBean;
/*     */ import com.cicro.wcm.bean.appeal.cpDept.CpDeptBean;
/*     */ import com.cicro.wcm.bean.appeal.model.ModelBean;
/*     */ import com.cicro.wcm.bean.appeal.purpose.PurposeBean;
/*     */ import com.cicro.wcm.bean.appeal.satisfaction.SatisfactionBean;
/*     */ import com.cicro.wcm.bean.appeal.sq.ProcessBean;
/*     */ import com.cicro.wcm.bean.appeal.sq.SQBean;
/*     */ import com.cicro.wcm.bean.appeal.sq.SQCustom;
/*     */ import com.cicro.wcm.bean.member.MemberBean;
/*     */ import com.cicro.wcm.bean.system.assist.TagsBean;
/*     */ import com.cicro.wcm.bean.template.TurnPageBean;
/*     */ import com.cicro.wcm.dao.appeal.sq.SQDAO;
/*     */ import com.cicro.wcm.services.appeal.cpDept.CpDeptManager;
/*     */ import com.cicro.wcm.services.appeal.model.ModelManager;
/*     */ import com.cicro.wcm.services.appeal.purpose.PurposeManager;
/*     */ import com.cicro.wcm.services.appeal.satisfaction.SatisfactionManager;
/*     */ import com.cicro.wcm.services.appeal.sq.SQManager;
/*     */ import com.cicro.wcm.services.member.MemberLogin;
/*     */ import com.cicro.wcm.services.system.assist.TagsManager;

/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;

/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ 
/*     */ public class AppealData
/*     */ {
/*  45 */   private static int cur_page = 1;
/*  46 */   private static int page_size = 10;
/*     */ 
/*     */   public static TurnPageBean getAppealCount(String params)
/*     */   {
/*  50 */     TurnPageBean tpb = new TurnPageBean();
/*  51 */     String con = getSQSearchCon(params);
/*  52 */     if ((con == null) || ("".equals(con)))
/*  53 */       return tpb;
/*  54 */     tpb.setCount(Integer.parseInt(SQManager.getBrowserSQCount(con)));
/*  55 */     tpb.setCur_page(cur_page);
/*  56 */     tpb.setPage_size(page_size);
/*  57 */     tpb.setPage_count(tpb.getCount() / tpb.getPage_size() + 1);
/*     */ 
/*  59 */     if ((tpb.getCount() % tpb.getPage_size() == 0) && (tpb.getPage_count() > 1)) {
/*  60 */       tpb.setPage_count(tpb.getPage_count() - 1);
/*     */     }
/*  62 */     if (cur_page > 1) {
/*  63 */       tpb.setPrev_num(cur_page - 1);
/*     */     }
/*  65 */     tpb.setNext_num(tpb.getPage_count());
/*  66 */     if (cur_page < tpb.getPage_count())
/*  67 */       tpb.setNext_num(cur_page + 1);
/*  68 */     return tpb;
/*     */   }
/*     */ 
/*     */   public static List<SQBean> getAppealHotList(String params)
/*     */   {
/*  73 */     String con = "";
/*  74 */     String order_by = "hits desc";
/*  75 */     String page_sizes = JconfigUtilContainer.velocityTemplateConfig().getProperty("num", "15", "show_list_num");
/*  76 */     String[] tempA = params.split(";");
/*  77 */     for (int i = 0; i < tempA.length; i++)
/*     */     {
/*  79 */       if (tempA[i].toLowerCase().startsWith("weight="))
/*     */       {
/*  81 */         String weights = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));
/*  82 */         if ((!"".equals(weights)) && (!weights.startsWith("$weight")) && (FormatUtil.isValiditySQL(weights)))
/*     */         {
/*  84 */           if (weights.contains(","))
/*     */           {
/*  86 */             String weight = weights.substring(0, weights.indexOf(","));
/*  87 */             String weight_end = weights.substring(weights.indexOf(",") + 1);
/*  89 */             if ((weight != null) && (!"".equals(weight.trim())))
/*  90 */               con = con + " and weight >= " + weight;
/*  91 */             if ((weight_end != null) && (!"".equals(weight_end.trim())))
/*  92 */               con = con + " and weight <= " + weight_end;
/*     */           }
/*     */           else {
/*  95 */             con = con + " and weight = " + weights + " ";
/*     */           }
/*     */         }
/*     */       }
/*  99 */       if (tempA[i].toLowerCase().startsWith("orderby="))
/*     */       {
/* 101 */         String ob = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));
/*     */ 
/* 103 */         if ((!"".equals(ob)) && (!ob.startsWith("$orderby")))
/* 104 */           order_by = ob;
/*     */       }
/* 106 */       if (tempA[i].toLowerCase().startsWith("model_id="))
/*     */       {
/* 108 */         String mid = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));
/*     */ 
/* 110 */         if ((!"".equals(mid)) && (!mid.startsWith("$model_id")) && (FormatUtil.isValiditySQL(mid)))
/*     */         {
/* 112 */           con = con + " and model_id in (" + mid + ")";
/*     */         }
/*     */       }
/* 115 */       if (tempA[i].toLowerCase().startsWith("sq_status="))
/*     */       {
/* 117 */         String sq_status = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));
/*     */ 
/* 119 */         if ((!"".equals(sq_status)) && (!sq_status.startsWith("$sq_status")) && (FormatUtil.isValiditySQL(sq_status)))
/*     */         {
/* 121 */           con = con + " and sq_status in (" + sq_status + ")";
/*     */         }
/*     */       }
/* 124 */       if (tempA[i].toLowerCase().startsWith("hotype="))
/*     */       {
/* 126 */         String ob = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));
/* 127 */         if ((!"".equals(ob)) && (!ob.startsWith("$hotype")))
/* 128 */           order_by = ob;
/*     */       }
/* 130 */       if (tempA[i].toLowerCase().startsWith("size="))
/*     */       {
/* 132 */         String ps = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));
/* 133 */         if ((!"".equals(ps)) && (!ps.startsWith("$size")) && (FormatUtil.isNumeric(ps)))
/* 134 */           page_sizes = ps;
/*     */       }
/*     */     }
/* 137 */     order_by = order_by + " ,sq_dtime desc";
/* 138 */     return SQManager.getBrowserSQList(con, "1", page_sizes, order_by);
/*     */   }
/*     */ 
/*     */   public static List<SQBean> getAppealList(String params)
/*     */   {
/* 143 */     String current_page = "1";
/* 144 */     String page_sizes = JconfigUtilContainer.velocityTemplateConfig().getProperty("num", "15", "show_list_num");
/* 145 */     String order_by = "sq_id desc";
/* 146 */     String con = getSQSearchCon(params);
/*     */ 
/* 148 */     if ((con == null) || ("".equals(con)))
/* 149 */       return null;
/* 150 */     String[] tempA = params.split(";");
/* 151 */     for (int i = 0; i < tempA.length; i++)
/*     */     {
/* 153 */       if (tempA[i].toLowerCase().startsWith("orderby="))
/*     */       {
/* 155 */         String ob = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));
/*     */ 
/* 157 */         if ((!"".equals(ob)) && (!ob.startsWith("$orderby")))
/* 158 */           order_by = ob;
/*     */       }
/* 160 */       if (tempA[i].toLowerCase().startsWith("size="))
/*     */       {
/* 162 */         String ps = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));
/* 163 */         if ((!"".equals(ps)) && (!ps.startsWith("$size")) && (FormatUtil.isNumeric(ps)))
/* 164 */           page_sizes = ps;
/*     */       }
/* 166 */       if (tempA[i].toLowerCase().startsWith("cur_page="))
/*     */       {
/* 168 */         String cp = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));
/* 169 */         if ((!"".equals(cp)) && (!cp.startsWith("$cur_page")) && (FormatUtil.isNumeric(cp)))
/* 170 */           current_page = cp;
/*     */       }
/*     */     }
/* 173 */     order_by = order_by + " ,sq_dtime desc";
/* 174 */     return SQManager.getBrowserSQList(con, current_page, page_sizes, order_by);
/*     */   }
/*     */ 
/*     */   public static List<ProcessBean> getProcessList(int sq_id)
/*     */   {
/* 180 */     return SQManager.getProcessListBySqID(sq_id);
/*     */   }
/*     */ 
/*     */   public static CpDeptBean getCpDeptObject(int id)
/*     */   {
/* 186 */     return CpDeptManager.getCpDeptBean(id);
/*     */   }
/*     */ 
/*     */   public static String getSQSearchCon(String params)
/*     */   {
/* 191 */     String con = "";
/* 192 */     String[] tempA = params.split(";");
/* 193 */     for (int i = 0; i < tempA.length; i++)
/*     */     {
/* 195 */       if (tempA[i].toLowerCase().startsWith("weight="))
/*     */       {
/* 197 */         String weights = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));
/* 198 */         if ((!"".equals(weights)) && (!weights.startsWith("$weight")) && (FormatUtil.isValiditySQL(weights)))
/*     */         {
/* 200 */           if (weights.contains(","))
/*     */           {
/* 202 */             String weight = weights.substring(0, weights.indexOf(","));
/* 203 */             String weight_end = weights.substring(weights.indexOf(",") + 1);
/*     */ 
/* 205 */             if ((weight != null) && (!"".equals(weight.trim())))
/* 206 */               con = con + " and weight >= " + weight;
/* 207 */             if ((weight_end != null) && (!"".equals(weight_end.trim())))
/* 208 */               con = con + " and weight <= " + weight_end;
/*     */           }
/*     */           else {
/* 211 */             con = con + " and weight = " + weights + " ";
/*     */           }
/*     */         }
/*     */       }
/* 215 */       if (tempA[i].toLowerCase().startsWith("sq_status="))
/*     */       {
/* 217 */         String sq_status = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));
/*     */ 
/* 219 */         if ((!"".equals(sq_status)) && (!sq_status.startsWith("$sq_status")) && (FormatUtil.isValiditySQL(sq_status)))
/*     */         {
/* 221 */           con = con + " and sq_status in (" + sq_status + ")";
/*     */         }
/*     */       }
/* 224 */       if (tempA[i].toLowerCase().startsWith("dept_id="))
/*     */       {
/* 226 */         String dept_id = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));
/*     */ 
/* 228 */         if ((!"".equals(dept_id)) && (!dept_id.startsWith("$dept_id")) && (FormatUtil.isValiditySQL(dept_id)))
/*     */         {
/* 230 */           con = con + " and do_dept in (" + dept_id + ")";
/*     */         }
/*     */       }
/* 233 */       if (tempA[i].toLowerCase().startsWith("sub_dept_id="))
/*     */       {
/* 235 */         String sub_dept_id = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));
/*     */ 
/* 237 */         if ((!"".equals(sub_dept_id)) && (!sub_dept_id.startsWith("$sub_dept_id")) && (FormatUtil.isValiditySQL(sub_dept_id)))
/*     */         {
/* 239 */           con = con + " and submit_dept_id in (" + sub_dept_id + ")";
/*     */         }
/*     */       }
/* 242 */       if (tempA[i].toLowerCase().startsWith("related_dept="))
/*     */       {
/* 244 */         String related_dept = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));
/*     */ 
/* 246 */         if ((!"".equals(related_dept)) && (!related_dept.startsWith("$sub_dept_id")) && (FormatUtil.isValiditySQL(related_dept)))
/*     */         {
/* 248 */           con = con + " and (submit_dept_id in (" + related_dept + ") or do_dept in (" + related_dept + "))";
/*     */         }
/*     */       }
/*     */ 
/* 252 */       if (tempA[i].toLowerCase().startsWith("model_id="))
/*     */       {
/* 254 */         String mid = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));
/*     */ 
/* 256 */         if ((!"".equals(mid)) && (!mid.startsWith("$model_id")) && (FormatUtil.isValiditySQL(mid)))
/*     */         {
/* 258 */           con = con + " and model_id in (" + mid + ")";
/*     */         }
/*     */       }
/* 261 */       if (tempA[i].toLowerCase().startsWith("submit_name="))
/*     */       {
/* 263 */         String submit_name = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));
/*     */ 
/* 265 */         if ((!"".equals(submit_name)) && (!submit_name.startsWith("$submit_name")) && (FormatUtil.isValiditySQL(submit_name)))
/*     */         {
/* 267 */           con = con + " and submit_name = '" + submit_name + "'";
/*     */         }
/*     */       }
/* 270 */       if (tempA[i].toLowerCase().startsWith("sq_title="))
/*     */       {
/* 272 */         String st = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));
/*     */ 
/* 274 */         if ((!"".equals(st)) && (!st.startsWith("$sq_title")) && (FormatUtil.isValiditySQL(st)))
/*     */         {
/* 276 */           con = con + " and sq_title2 like '%" + st + "%'";
/*     */         }
/*     */       }
/* 279 */       if (tempA[i].toLowerCase().startsWith("start_time="))
/*     */       {
/* 281 */         String sd = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));
/* 282 */         if ((!"".equals(sd)) && (!sd.startsWith("$start_time")) && (FormatUtil.isValiditySQL(sd)))
/*     */         {
/* 284 */           con = con + " and sq_dtime > '" + sd + " 00:00:00' ";
/*     */         }
/*     */       }
/* 287 */       if (tempA[i].toLowerCase().startsWith("end_time="))
/*     */       {
/* 289 */         String ed = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));
/* 290 */         if ((!"".equals(ed)) && (!ed.startsWith("$end_time")) && (FormatUtil.isValiditySQL(ed)))
/*     */         {
/* 292 */           con = con + " and sq_dtime < '" + ed + " 23:59:59' ";
/*     */         }
/*     */       }
/* 295 */       if (tempA[i].toLowerCase().startsWith("sq_code="))
/*     */       {
/* 297 */         String sq = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));
/* 298 */         if ((!"".equals(sq)) && (!sq.startsWith("$sq_code")) && (FormatUtil.isValiditySQL(sq)))
/*     */         {
/* 300 */           con = con + " and sq_code = '" + sq + "' ";
/*     */         }
/*     */       }
/* 303 */       if (tempA[i].toLowerCase().startsWith("query_code="))
/*     */       {
/* 305 */         String qc = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));
/* 306 */         if ((!"".equals(qc)) && (!qc.startsWith("$query_code")) && (FormatUtil.isValiditySQL(qc)))
/*     */         {
/* 308 */           con = con + " and query_code = '" + qc + "' ";
/*     */         }
/*     */       }
/* 311 */       if (tempA[i].toLowerCase().startsWith("pur_id="))
/*     */       {
/* 313 */         String pur_id = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));
/* 314 */         if ((!"".equals(pur_id)) && (!pur_id.startsWith("$pur_id")) && (FormatUtil.isValiditySQL(pur_id)))
/*     */         {
/* 316 */           con = con + " and pur_id in (" + pur_id + ") ";
/*     */         }
/*     */       }
/* 319 */       if (tempA[i].toLowerCase().startsWith("cur_page="))
/*     */       {
/* 321 */         String cp = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));
/* 322 */         if ((!"".equals(cp)) && (!cp.startsWith("$cur_page")) && (FormatUtil.isNumeric(cp)) && (!"0".equals(cp)))
/* 323 */           cur_page = Integer.parseInt(cp);
/*     */       }
/* 325 */       if (tempA[i].toLowerCase().startsWith("size="))
/*     */       {
/* 327 */         String size = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));
/* 328 */         if ((!"".equals(size)) && (!size.startsWith("$size")) && (FormatUtil.isNumeric(size)))
/* 329 */           page_size = Integer.parseInt(size);
/*     */       }
/* 331 */       if (tempA[i].toLowerCase().startsWith("tag_id="))
/*     */       {
/* 333 */         String tag_id = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));
/* 334 */         if ((!"".equals(tag_id)) && (!tag_id.startsWith("$pur_id")) && (FormatUtil.isValiditySQL(tag_id)))
/*     */         {
/* 336 */           con = con + " and sq_id in ( select sq_id from cp_sq_tag where tag_id = " + tag_id + " ) ";
/*     */         }
/*     */       }
/* 339 */       if (tempA[i].toLowerCase().startsWith("showtype="))
/*     */       {
/* 342 */         con = con + " and 1=1 ";
/*     */       }
/*     */     }
/*     */ 
/* 346 */     return con;
/*     */   }
/*     */ 
/*     */   public static List<CpDeptBean> getAppealDeptList(String model_id)
/*     */   {
/* 352 */     return ModelManager.getModelDeptListByMID(Integer.parseInt(model_id));
/*     */   }
/*     */ 
/*     */   public static List<PurposeBean> getPurposeList()
/*     */   {
/* 358 */     return PurposeManager.getAllPurposeList();
/*     */   }
/*     */ 
/*     */   public static SQBean getAppealContent(String sq_id, HttpServletRequest request)
/*     */   {
/* 363 */     String sq_code = FormatUtil.formatNullString(request.getParameter("sq_code"));
/* 364 */     String query_code = FormatUtil.formatNullString(request.getParameter("query_code"));
/* 365 */     if (("m".equals(request.getParameter("st"))) || ((sq_code != null) && (!"".equals(sq_code)) && (query_code != null) && (!"".equals(query_code))))
/*     */     {
/* 367 */       return SQManager.getSqBean(Integer.parseInt(sq_id));
/*     */     }
/*     */ 
/* 371 */     String me_id = "";
/* 372 */     MemberBean mb = MemberLogin.getMemberBySession(request);
/*     */ 
/* 374 */     if (mb != null) {
/* 375 */       me_id = mb.getMe_id();
/*     */     }
/* 377 */     return SQManager.getBrowserSQBean(Integer.parseInt(sq_id), me_id);
/*     */   }
/*     */ 
/*     */   public static String getSQCustomValue(int sq_id, String cu_key)
/*     */   {
/* 389 */     return SQManager.getSQCustomValue(sq_id, cu_key);
/*     */   }
/*     */ 
/*     */   public static List<SatisfactionBean> getAppealSatisfactionList()
/*     */   {
/* 395 */     return SatisfactionManager.getSatisfactionList();
/*     */   }
/*     */ 
/*     */   public static List<ModelBean> getAppealModelList()
/*     */   {
/* 401 */     return ModelManager.getAllSQModelList();
/*     */   }
/*     */ 
/*     */   public static ModelBean getAppealModelObject(String model_id)
/*     */   {
/* 407 */     return ModelManager.getModelBean(Integer.parseInt(model_id));
/*     */   }
/*     */ 
/*     */   public static List<TagsBean> getAppealTagList()
/*     */   {
/* 413 */     return TagsManager.getTagListByAPPSite("appeal", "");
/*     */   }
/*     */ 
/*     */   public static String getAppealScore(String sq_id)
/*     */   {
/* 419 */     return SatisfactionManager.getSatScoreBySQID(sq_id);
/*     */   }
/*     */ 
/*     */   public static List<CountBean> getStatisForFinishDept(String model_ids, int row_count)
/*     */   {
/* 430 */     return SQManager.getSqFinishCountForDept(model_ids, row_count);
/*     */   }
/*     */ 
/*     */   public static String getAllAppealCount(String model_id, String count_type)
/*     */   {
/* 441 */     String c_type = "all";
/* 442 */     if ((count_type != null) && (!"".equals(count_type)))
/*     */     {
/* 444 */       c_type = count_type;
/*     */     }
/* 446 */     Map<String,String> m = new HashMap<String,String>();
/* 447 */     if ((model_id != null) && (!"".equals(model_id)))
/* 448 */       m.put("model_ids", model_id);
/* 449 */     if ("sl".equals(c_type))
/* 450 */       m.put("sl_con", "sl_con");
/* 451 */     if ("bj".equals(c_type))
/* 452 */       m.put("bj_con", "bj_con");
/* 453 */     if ("all".equals(c_type)) {
/* 454 */       m.remove("sl_con");
/* 455 */       m.remove("bj_con");
/*     */     }
/* 457 */     return SQDAO.getSQStatistics(m);
/*     */   }
/*     */ 
/*     */   public static String getAllAppealCount(String params)
/*     */   {
/* 468 */     Map<String,String> m = new HashMap<String,String>();
/* 469 */     String[] tempA = params.split(";");
/* 470 */     for (int i = 0; i < tempA.length; i++)
/*     */     {
/* 472 */       if (tempA[i].toLowerCase().startsWith("count_type="))
/*     */       {
/* 474 */         String count_type = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));
/* 475 */         System.out.println("count_type\t==\t" + count_type);
/* 476 */         if ((!"".equals(count_type)) && (!count_type.startsWith("$count_type")) && (FormatUtil.isValiditySQL(count_type)))
/*     */         {
/* 478 */           if ("sl".equals(count_type))
/* 479 */             m.put("sl_con", "sl_con");
/* 480 */           if ("bj".equals(count_type))
/* 481 */             m.put("bj_con", "bj_con");
/* 482 */           if ("wsl".equals(count_type))
/* 483 */             m.put("wsl_con", "wsl_con");
/*     */         }
/*     */       }
/* 486 */       if (tempA[i].toLowerCase().startsWith("count_data="))
/*     */       {
/* 488 */         String count_data = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));
/* 489 */         if ((!"".equals(count_data)) && (!count_data.startsWith("$count_data")) && (FormatUtil.isValiditySQL(count_data)))
/*     */         {
/* 491 */           String temp = (String)m.get("bj_con");
/* 492 */           if (("yesterday".equals(count_data)) && ("bj_con".equals(temp)))
/*     */           {
/* 494 */             m.put("overdtime_s", DateUtil.getDateBefore(DateUtil.getCurrentDateTime(), 1) + " 00:00:00");
/* 495 */             m.put("overdtime_e", DateUtil.getDateBefore(DateUtil.getCurrentDateTime(), 1) + " 23:59:59");
/* 496 */             m.remove("yesterday_con");
/*     */           } else {
/* 498 */             m.put("start_data", DateUtil.getDateBefore(DateUtil.getCurrentDateTime(), 1) + " 00:00:00");
/* 499 */             m.put("end_data", DateUtil.getDateBefore(DateUtil.getCurrentDateTime(), 1) + " 23:59:59");
/* 500 */             m.put("yesterday_con", "yesterday_con");
/*     */           }
/* 502 */           if ("ultimo".equals(count_data))
/*     */           {
/* 504 */             String dt = "";
/* 505 */             String d = DateUtil.getCurrentDate();
/* 506 */             String[] dateA = d.split("-");
/* 507 */             int year = Integer.parseInt(dateA[0]);
/* 508 */             int month = Integer.parseInt(dateA[1]);
/* 509 */             if (month == 1)
/*     */             {
/* 511 */               year--;
/* 512 */               month = 12;
/*     */             } else {
/* 514 */               month--;
/* 515 */             }if (month < 10)
/*     */             {
/* 517 */               dt = year + "-0" + month;
/*     */             }
/* 519 */             else dt = year + "-" + month;
/* 520 */             m.put("ultimo", dt);
/* 521 */             System.out.println("ultimo ======\t" + dt);
/* 522 */             m.remove("yesterday_con");
/*     */           }
/* 524 */           if ("instant".equals(count_data))
/*     */           {
/* 526 */             m.put("instant", DateUtil.getCurrentDateTime("yyyy-MM"));
/* 527 */             System.out.println("instant\t======\t" + DateUtil.getCurrentDateTime("yyyy-MM"));
/* 528 */             m.remove("yesterday_con");
/*     */           }
/* 530 */           if ("cur_data".equals(count_data))
/*     */           {
/* 532 */             m.put("cur_data", "cur_data");
/* 533 */             m.put("start_data", DateUtil.getCurrentDate() + " 00:00:00");
/* 534 */             System.out.println("cur_data ====== start_data ====== " + DateUtil.getCurrentDateTime("yyyy-MM"));
/* 535 */             m.remove("yesterday_con");
/*     */           }
/*     */         }
/*     */       }
/* 539 */       if (tempA[i].toLowerCase().startsWith("model_id="))
/*     */       {
/* 541 */         String model_id = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));
/* 542 */         if ((!"".equals(model_id)) && (!model_id.startsWith("$model_id")) && (FormatUtil.isValiditySQL(model_id)))
/*     */         {
/* 544 */           m.put("model_ids", model_id);
/*     */         }
/*     */       }
/* 547 */       if (tempA[i].toLowerCase().startsWith("do_dept="))
/*     */       {
/* 549 */         String do_dept = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));
/* 550 */         if ((!"".equals(do_dept)) && (!do_dept.startsWith("$do_dept")) && (FormatUtil.isValiditySQL(do_dept)))
/*     */         {
/* 552 */           m.put("do_dept", do_dept);
/*     */         }
/*     */       }
/* 555 */       if (tempA[i].toLowerCase().startsWith("submit_dept_id="))
/*     */       {
/* 557 */         String submit_dept_id = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));
/* 558 */         if ((!"".equals(submit_dept_id)) && (!submit_dept_id.startsWith("$submit_dept_id")) && (FormatUtil.isValiditySQL(submit_dept_id)))
/*     */         {
/* 560 */           m.put("submit_dept_id", submit_dept_id);
/*     */         }
/*     */       }
/*     */     }
/* 564 */     return SQDAO.getSQStatistics(m);
/*     */   }
/*     */ 
/*     */   public static List<CountBean> getAppealSatisfaction(String model_id, int row_count)
/*     */   {
/* 575 */     return SQManager.getSQSatisfaction(model_id, row_count);
/*     */   }
/*     */ 
/*     */   public static List<CountBean> getAppealFinishRate(String model_id, int row_count)
/*     */   {
/* 586 */     Map<String,String> m = new HashMap<String,String>();
/* 587 */     m.put("model_ids", model_id);
/* 588 */     return SQManager.getAppealFinishRate(m, row_count);
/*     */   }
/*     */ 
/*     */   public static List<CountBean> getAppealFinishRate(String params)
/*     */   {
/* 599 */     Map<String,String> m = new HashMap<String,String>();
/* 600 */     int row_count = paramsForMap(m, params).intValue();
/* 601 */     return SQManager.getAppealFinishRate(m, row_count);
/*     */   }
/*     */ 
/*     */   public static List<CountBean> getAppealFinishRateForAlarm(String params)
/*     */   {
/* 607 */     Map<String,String> m = new HashMap<String,String>();
/* 608 */     int row_count = paramsForMap(m, params).intValue();
/* 609 */     return SQManager.getAppealFinishRateForAlarm(m, row_count);
/*     */   }
/*     */ 
/*     */   public static Integer paramsForMap(Map<String, String> m, String params)
/*     */   {
/* 614 */     int row_count = 10;
/* 615 */     String[] tempA = params.split(";");
/* 616 */     for (int i = 0; i < tempA.length; i++)
/*     */     {
/* 618 */       if (tempA[i].toLowerCase().startsWith("model_id="))
/*     */       {
/* 620 */         String model_id = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));
/* 621 */         if ((!"".equals(model_id)) && (!model_id.startsWith("$model_id")) && (FormatUtil.isValiditySQL(model_id)))
/*     */         {
/* 623 */           m.put("model_ids", model_id);
/*     */         }
/*     */       }
/* 626 */       if (tempA[i].toLowerCase().startsWith("timeout_flag="))
/*     */       {
/* 628 */         String timeout_flag = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));
/* 629 */         if ((!"".equals(timeout_flag)) && (!timeout_flag.startsWith("$timeout_flag")) && (FormatUtil.isValiditySQL(timeout_flag)))
/*     */         {
/* 631 */           m.put("timeout_flag", timeout_flag);
/*     */         }
/*     */       }
/* 634 */       if (tempA[i].toLowerCase().startsWith("sq_status="))
/*     */       {
/* 636 */         String sq_status = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));
/* 637 */         if ((!"".equals(sq_status)) && (!sq_status.startsWith("$sq_status")) && (FormatUtil.isValiditySQL(sq_status)))
/*     */         {
/* 639 */           m.put("sq_status", sq_status);
/*     */         }
/*     */       }
/* 642 */       if (tempA[i].toLowerCase().startsWith("alarm_flag="))
/*     */       {
/* 644 */         String alarm_flag = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));
/* 645 */         if ((!"".equals(alarm_flag)) && (!alarm_flag.startsWith("$alarm_flag")) && (FormatUtil.isValiditySQL(alarm_flag)))
/*     */         {
/* 647 */           m.put("alarm_flag", alarm_flag);
/*     */         }
/*     */       }
/* 650 */       if (tempA[i].toLowerCase().startsWith("not_dept_id="))
/*     */       {
/* 652 */         String not_dept_id = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));
/* 653 */         if ((!"".equals(not_dept_id)) && (!not_dept_id.startsWith("$not_dept_id")) && (FormatUtil.isValiditySQL(not_dept_id)))
/*     */         {
/* 655 */           m.put("not_dept_id", not_dept_id);
/*     */         }
/*     */       }
/* 658 */       if (tempA[i].toLowerCase().startsWith("row_count="))
/*     */       {
/* 660 */         String row_counts = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));
/* 661 */         if ((!"".equals(row_counts)) && (!row_counts.startsWith("$row_count")) && (FormatUtil.isValiditySQL(row_counts)))
/*     */         {
/* 663 */           row_count = Integer.parseInt(row_counts);
/*     */         }
/*     */       }
/*     */     }
/* 667 */     return Integer.valueOf(row_count);
/*     */   }
/*     */ 
/*     */   public static List<CountBean> getAppealFinishRateForSort(String model_id, int row_count, String sort_type)
/*     */   {
/* 680 */     return SQManager.getAppealFinishRateForSort(SQManager.getAppealFinishRate2(model_id), sort_type, row_count);
/*     */   }
/*     */ 
/*     */   public static List<CountBean> getAppealFinishRate2(String model_id)
/*     */   {
/* 685 */     Map<String,String> m = new HashMap<String,String>();
/* 686 */     m.put("model_ids", model_id);
/*     */ 
/* 688 */     List<CountBean> l = SQDAO.getSqFinishCountForDept(m);
/* 689 */     List<CountBean> count_l = new ArrayList<CountBean>();
/* 690 */     if ((l != null) && (l.size() > 0))
/*     */     {
/* 692 */       for (CountBean cb : l) {
/*     */         try
/*     */         {
/* 695 */           cb.setDept_name(CpDeptManager.getCpDeptName(cb.getDept_id()));
/*     */ 
/* 697 */           m.put("bj_con", "bj_con");
/* 698 */           m.put("do_dept", cb.getDept_id()+"");
/* 699 */           String bj_count = SQDAO.getSQStatistics(m);
/* 700 */           cb.setCountendp(CalculateNumber.getRate(bj_count, cb.getCount()));
/* 701 */           cb.setCountwei(Integer.parseInt(cb.getCount()) - Integer.parseInt(bj_count)+"");
/* 702 */           count_l.add(cb);
/*     */         }
/*     */         catch (Exception e) {
/* 705 */           e.printStackTrace();
/*     */         }
/*     */       }
/*     */     }
/* 709 */     return count_l;
/*     */   }
/*     */ 
/*     */   public static String getModelIdByDept_id(String dept_id)
/*     */   {
/* 716 */     return SQManager.getModelIdByDept_id(dept_id);
/*     */   }
/*     */ 
/*     */   public static String getFirstModle_id(String mode_ids)
/*     */   {
/* 722 */     String[] temp = (String[])null;
/*     */ 
/* 724 */     if (mode_ids != "") {
/* 725 */       temp = mode_ids.split(",");
/*     */     }
/* 727 */     return temp[0];
/*     */   }
/*     */ 
/*     */   public static List<CountBean> getSQStatisticsForModel(String params)
/*     */   {
/* 733 */     Map<String,String> m = new HashMap<String,String>();
/* 734 */     int row_count = 10;
/* 735 */     String[] tempA = params.split(";");
/* 736 */     for (int i = 0; i < tempA.length; i++)
/*     */     {
/* 738 */       if (tempA[i].toLowerCase().startsWith("timeout_flag="))
/*     */       {
/* 740 */         String timeout_flag = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));
/* 741 */         if ((!"".equals(timeout_flag)) && (!timeout_flag.startsWith("$timeout_flag")) && (FormatUtil.isValiditySQL(timeout_flag)))
/*     */         {
/* 743 */           m.put("timeout_flag", timeout_flag);
/*     */         }
/*     */       }
/* 746 */       if (tempA[i].toLowerCase().startsWith("sq_status="))
/*     */       {
/* 748 */         String sq_status = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));
/* 749 */         if ((!"".equals(sq_status)) && (!sq_status.startsWith("$sq_status")) && (FormatUtil.isValiditySQL(sq_status)))
/*     */         {
/* 751 */           m.put("sq_status", sq_status);
/*     */         }
/*     */       }
/* 754 */       if (tempA[i].toLowerCase().startsWith("sq_flag="))
/*     */       {
/* 756 */         String sq_flag = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));
/* 757 */         if ((!"".equals(sq_flag)) && (!sq_flag.startsWith("$sq_flag")) && (FormatUtil.isValiditySQL(sq_flag)))
/*     */         {
/* 759 */           m.put("sq_flag", sq_flag);
/*     */         }
/*     */       }
/* 762 */       if (tempA[i].toLowerCase().startsWith("row_count="))
/*     */       {
/* 764 */         String row_counts = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));
/* 765 */         if ((!"".equals(row_counts)) && (!row_counts.startsWith("$row_count")) && (FormatUtil.isValiditySQL(row_counts)))
/*     */         {
/* 767 */           row_count = Integer.parseInt(row_counts);
/*     */         }
/*     */       }
/*     */     }
/* 771 */     return SQManager.getSQStatisticsForModel(m, row_count);
/*     */   }
/*     */ 
/*     */   public static Map<String, String> getSQCustomForBrower(int sq_id)
/*     */   {
/* 782 */     Map<String,String> m = new HashMap<String,String>();
/* 783 */     List<SQCustom> SQCustom = SQManager.getSQCustomList(sq_id);
/* 784 */     if ((SQCustom != null) && (SQCustom.size() > 0))
/*     */     {
/* 786 */       for (int i = 0; i < SQCustom.size(); i++)
/*     */       {
/* 788 */         m.put(((SQCustom)SQCustom.get(i)).getCu_key(), ((SQCustom)SQCustom.get(i)).getCu_value());
/*     */       }
/*     */     }
/* 791 */     return m;
/*     */   }
			 
			public static SQBean getSqBean(String sq_id)
			{
				 return SQManager.getSqBean(Integer.parseInt(sq_id));
			}
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
				String content = "<p style=\"text-align: center\"><embed height=\"480\" width=\"640\" src=\"/upload/2013-07/201307240912041.mpg\" mediatype=\"video\" autostart=\"true\" loop=\"true\" menu=\"true\" sck_id=\"201307240912041\"></embed></p>--- #turnpageflag# ---";
				
				int start = content.indexOf("src=\"") + 5;
				int end = content.indexOf(".wmv") + 4;
				System.out.println(start + "--" +end);
				if(end > 3 && end <= content.length())
				{
					System.out.println(content.substring(start,end));
				}
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.template.velocity.data.AppealData
 * JD-Core Version:    0.6.2
 */