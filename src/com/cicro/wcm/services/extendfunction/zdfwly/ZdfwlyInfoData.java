/*     */ package com.cicro.wcm.services.extendfunction.zdfwly;
/*     */ 
/*     */ import com.cicro.util.DateUtil;
/*     */ import com.cicro.util.FormatUtil;
/*     */ import com.cicro.wcm.bean.template.TurnPageBean;
/*     */ import java.io.PrintStream;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class ZdfwlyInfoData
/*     */ {
/*  12 */   private static int cur_page = 1;
/*  13 */   private static int page_size = 15;
/*     */ 
/*     */   public static void getZdfwlyInfoSearchCon(String params, Map<String, String> con_map)
/*     */   {
/*  17 */     String orderby = "input_dtime desc";
/*  18 */     String[] tempA = params.split(";");
/*  19 */     for (int i = 0; i < tempA.length; i++)
/*     */     {
/*  21 */       if (tempA[i].toLowerCase().startsWith("kw="))
/*     */       {
/*  23 */         String kw = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));
/*     */ 
/*  25 */         if ((!"".equals(kw)) && (!kw.startsWith("$kw")) && (FormatUtil.isValiditySQL(kw)))
/*     */         {
/*  27 */           con_map.put("keyword", kw);
/*     */         }
/*     */       }
/*     */ 
/*  31 */       if (tempA[i].toLowerCase().startsWith("name="))
/*     */       {
/*  33 */         String name = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));
/*     */ 
/*  35 */         if ((!"".equals(name)) && (!name.startsWith("$name")) && (FormatUtil.isValiditySQL(name)))
/*     */         {
/*  37 */           con_map.put("name", name);
/*     */         }
/*     */       }
/*  40 */       if (tempA[i].toLowerCase().startsWith("gender="))
/*     */       {
/*  42 */         String gender = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));
/*     */ 
/*  44 */         if ((!"".equals(gender)) && (!gender.startsWith("$gender")) && (FormatUtil.isValiditySQL(gender)))
/*     */         {
/*  46 */           con_map.put("gender", gender);
/*     */         }
/*     */       }
/*  49 */       if (tempA[i].toLowerCase().startsWith("orderby="))
/*     */       {
/*  51 */         String o_by = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));
/*  52 */         if ((!"".equals(o_by)) && (!o_by.startsWith("$orderby")) && (FormatUtil.isValiditySQL(o_by)))
/*     */         {
/*  54 */           orderby = o_by;
/*     */         }
/*     */       }
/*  57 */       if (tempA[i].toLowerCase().startsWith("size="))
/*     */       {
/*  59 */         String ps = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));
/*  60 */         if ((!"".equals(ps)) && (!ps.startsWith("$size")) && (FormatUtil.isNumeric(ps)))
/*  61 */           page_size = Integer.parseInt(ps);
/*     */       }
/*  63 */       if (tempA[i].toLowerCase().startsWith("cur_page="))
/*     */       {
/*  65 */         String cp = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));
/*  66 */         if ((!"".equals(cp)) && (!cp.startsWith("$cur_page")) && (FormatUtil.isNumeric(cp)))
/*  67 */           cur_page = Integer.parseInt(cp);
/*     */       }
/*     */     }
/*  70 */     con_map.put("current_data", DateUtil.getCurrentDate());
/*  71 */     con_map.put("page_size", page_size+"");
/*  72 */     con_map.put("current_page", cur_page+"");
/*  73 */     con_map.put("orderby", orderby);
/*  74 */     System.out.println(con_map);
/*     */   }
/*     */ 
/*     */   public static TurnPageBean getZdfwlyInfoCount(String params)
/*     */   {
/*  79 */     Map con_map = new HashMap();
/*  80 */     getZdfwlyInfoSearchCon(params, con_map);
/*     */ 
/*  82 */     TurnPageBean tpb = new TurnPageBean();
/*  83 */     tpb.setCount(Integer.parseInt(ZdfwlyInfoManager.getZdfwlyInfoCount(con_map)));
/*  84 */     tpb.setCur_page(cur_page);
/*  85 */     tpb.setPage_size(page_size);
/*  86 */     tpb.setPage_count(tpb.getCount() / tpb.getPage_size() + 1);
/*     */ 
/*  88 */     if ((tpb.getCount() % tpb.getPage_size() == 0) && (tpb.getPage_count() > 1)) {
/*  89 */       tpb.setPage_count(tpb.getPage_count() - 1);
/*     */     }
/*  91 */     if (cur_page > 1) {
/*  92 */       tpb.setPrev_num(cur_page - 1);
/*     */     }
/*  94 */     tpb.setNext_num(tpb.getPage_count());
/*  95 */     if (cur_page < tpb.getPage_count()) {
/*  96 */       tpb.setNext_num(cur_page + 1);
/*     */     }
/*     */ 
/*  99 */     if (tpb.getPage_count() > 10)
/*     */     {
/* 101 */       if (cur_page > 5)
/*     */       {
/* 103 */         if (cur_page > tpb.getPage_count() - 4)
/* 104 */           tpb.setCurr_start_num(tpb.getPage_count() - 6);
/*     */         else
/* 106 */           tpb.setCurr_start_num(cur_page - 2);
/*     */       }
/*     */     }
/* 109 */     return tpb;
/*     */   }
/*     */ 
/*     */   public static List<ZdfwlyInfoBean> getZdfwlyInfoList(String params) {
/* 113 */     Map con_map = new HashMap();
/* 114 */     getZdfwlyInfoSearchCon(params, con_map);
/* 115 */     int start_page = Integer.parseInt((String)con_map.get("current_page"));
/* 116 */     int page_size = Integer.parseInt((String)con_map.get("page_size"));
/* 117 */     con_map.put("start_num", Integer.valueOf((start_page - 1) * page_size));
/* 118 */     con_map.put("page_size", Integer.valueOf(page_size));
/* 119 */     return ZdfwlyInfoManager.getAllZdfwlyInfoList(con_map);
/*     */   }
/*     */ 
/*     */   public static List<ZdfwlyInfoBean> getZdfwlyInfoHotList(String params) {
/* 123 */     Map con_map = new HashMap();
/* 124 */     getZdfwlyInfoSearchCon(params, con_map);
/* 125 */     con_map.put("current_page", "1");
/* 126 */     int start_page = Integer.parseInt((String)con_map.get("current_page"));
/* 127 */     int page_size = Integer.parseInt((String)con_map.get("page_size"));
/* 128 */     con_map.put("start_num", Integer.valueOf((start_page - 1) * page_size));
/* 129 */     con_map.put("page_size", Integer.valueOf(page_size));
/* 130 */     return ZdfwlyInfoManager.getAllZdfwlyInfoList(con_map);
/*     */   }
/*     */ 
/*     */   public static ZdfwlyInfoBean getZdfwlyInfoObject(String id)
/*     */   {
/* 135 */     return ZdfwlyInfoManager.getZdfwlyInfoBean(id);
/*     */   }
/*     */ }

/* Location:           E:\Xshell\61.150.72.149(渭南96)\com.zip
 * Qualified Name:     com.cicro.project.wzjc.ZdfwlyInfoData
 * JD-Core Version:    0.6.2
 */