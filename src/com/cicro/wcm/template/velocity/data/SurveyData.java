/*     */ package com.cicro.wcm.template.velocity.data;
/*     */ 
/*     */ import com.cicro.util.DateUtil;
/*     */ import com.cicro.util.FormatUtil;
/*     */ import com.cicro.util.jconfig.JconfigUtil;
/*     */ import com.cicro.util.jconfig.JconfigUtilContainer;
/*     */ import com.cicro.wcm.bean.survey.SurveyBean;
/*     */ import com.cicro.wcm.bean.survey.SurveyCategory;
/*     */ import com.cicro.wcm.bean.template.TurnPageBean;
/*     */ import com.cicro.wcm.services.survey.SurveyCategoryService;
/*     */ import com.cicro.wcm.services.survey.SurveyService;
/*     */ import java.util.List;
/*     */ 
/*     */ public class SurveyData
/*     */ {
/*  15 */   private static int cur_page = 1;
/*  16 */   private static int page_size = 10;
/*     */ 
/*     */   public static TurnPageBean getSurveyCount(String params)
/*     */   {
/*  20 */     TurnPageBean tpb = new TurnPageBean();
/*  21 */     tpb.setCount(Integer.parseInt(SurveyService.getSurveyListCountBrowser(getSurveySearchCon(params))));
/*  22 */     tpb.setCur_page(cur_page);
/*  23 */     tpb.setPage_size(page_size);
/*  24 */     tpb.setPage_count(tpb.getCount() / tpb.getPage_size() + 1);
/*  25 */     if ((tpb.getCount() % tpb.getPage_size() == 0) && (tpb.getPage_count() > 1)) {
/*  26 */       tpb.setPage_count(tpb.getPage_count() - 1);
/*     */     }
/*  28 */     if (cur_page > 1) {
/*  29 */       tpb.setPrev_num(cur_page - 1);
/*     */     }
/*  31 */     tpb.setNext_num(tpb.getPage_count());
/*  32 */     if (cur_page < tpb.getPage_count())
/*  33 */       tpb.setNext_num(cur_page + 1);
/*  34 */     return tpb;
/*     */   }
/*     */ 
/*     */   public static List<SurveyBean> getSurveyList(String params)
/*     */   {
/*  40 */     int current_page = 1;
/*  41 */     int page_sizes = Integer.parseInt(JconfigUtilContainer.velocityTemplateConfig().getProperty("num", "15", "show_list_num"));
/*  42 */     String order_by = "sub.id desc";
/*  43 */     String con = getSurveySearchCon(params);
/*  44 */     String[] tempA = params.split(";");
/*  45 */     for (int i = 0; i < tempA.length; i++)
/*     */     {
/*  47 */       if (tempA[i].toLowerCase().startsWith("orderby="))
/*     */       {
/*  49 */         String ob = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));
/*     */ 
/*  51 */         if ((!"".equals(ob)) && (!ob.startsWith("$orderby")))
/*  52 */           order_by = ob;
/*     */       }
/*  54 */       if (tempA[i].toLowerCase().startsWith("size="))
/*     */       {
/*  56 */         String ps = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));
/*  57 */         if ((!"".equals(ps)) && (!ps.startsWith("$size")) && (FormatUtil.isNumeric(ps)))
/*  58 */           page_sizes = Integer.parseInt(ps);
/*     */       }
/*  60 */       if (tempA[i].toLowerCase().startsWith("cur_page="))
/*     */       {
/*  62 */         String cp = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));
/*  63 */         if ((!"".equals(cp)) && (!cp.startsWith("$cur_page")) && (FormatUtil.isNumeric(cp)))
/*  64 */           current_page = Integer.parseInt(cp);
/*     */       }
/*     */     }
/*  67 */     return SurveyService.getSurveyListBrowser(con, (current_page - 1) * page_sizes, page_sizes, order_by);
/*     */   }
/*     */ 
/*     */   public static String getSurveySearchCon(String params)
/*     */   {
/*  72 */     String con = "";
/*  73 */     String[] tempA = params.split(";");
/*  74 */     for (int i = 0; i < tempA.length; i++)
/*     */     {
/*  76 */       if (tempA[i].toLowerCase().startsWith("cat_id="))
/*     */       {
/*  78 */         String cid = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));
/*     */ 
/*  80 */         if ((!"".equals(cid)) && (!cid.startsWith("$cat_id")) && (FormatUtil.isValiditySQL(cid)))
/*     */         {
/*  82 */           if (cid.indexOf(",") > 0)
/*  83 */             cid = cid.replaceAll(",", "','");
/*  84 */           con = con + " and cs.category_id in ('" + cid + "')";
/*     */         }
/*     */       }
/*  87 */       if (tempA[i].toLowerCase().startsWith("site_id="))
/*     */       {
/*  89 */         con = con + " and cs.site_id = '" + FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1)) + "'";
/*     */       }
/*  91 */       if (tempA[i].toLowerCase().startsWith("size="))
/*     */       {
/*  93 */         String ps = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));
/*  94 */         if ((!"".equals(ps)) && (!ps.startsWith("$size")) && (FormatUtil.isNumeric(ps)))
/*  95 */           page_size = Integer.parseInt(ps);
/*     */       }
/*  97 */       if (tempA[i].toLowerCase().startsWith("cur_page="))
/*     */       {
/*  99 */         String cp = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));
/* 100 */         if ((!"".equals(cp)) && (!cp.startsWith("$cur_page")) && (FormatUtil.isNumeric(cp)))
/* 101 */           cur_page = Integer.parseInt(cp);
/*     */       }
/* 103 */       if (tempA[i].toLowerCase().startsWith("is_end="))
/*     */       {
/* 105 */         String is_end = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));
/* 106 */         if ("true".equals(is_end))
/*     */         {
/* 109 */           con = con + " and cs.end_time < '" + DateUtil.getCurrentDate() + "'";
/*     */         }
/* 111 */         if ("false".equals(is_end))
/*     */         {
/* 113 */           con = con + " and (cs.end_time = '' or cs.end_time is null or cs.end_time >= '" + DateUtil.getCurrentDate() + "')";
/*     */         }
/*     */       }
/*     */     }
/* 117 */     return con;
/*     */   }
/*     */ 
/*     */   public static TurnPageBean getSurveyRecommendCount(String params)
/*     */   {
/* 122 */     TurnPageBean tpb = new TurnPageBean();
/* 123 */     tpb.setCount(Integer.parseInt(SurveyService.getSurveyRecommendListCount(getSurveySearchCon(params))));
/* 124 */     tpb.setCur_page(cur_page);
/* 125 */     tpb.setPage_size(page_size);
/* 126 */     tpb.setPage_count(tpb.getCount() / tpb.getPage_size() + 1);
/* 127 */     if ((tpb.getCount() / tpb.getPage_size() == 0) && (tpb.getPage_count() > 1)) {
/* 128 */       tpb.setPage_count(tpb.getPage_count() - 1);
/*     */     }
/* 130 */     if (cur_page > 1) {
/* 131 */       tpb.setPrev_num(cur_page - 1);
/*     */     }
/* 133 */     tpb.setNext_num(tpb.getPage_count());
/* 134 */     if (cur_page < tpb.getPage_count())
/* 135 */       tpb.setNext_num(cur_page + 1);
/* 136 */     return tpb;
/*     */   }
/*     */ 
/*     */   public static List<SurveyBean> getSurveyRecommendList(String params)
/*     */   {
/* 142 */     int current_page = 1;
/* 143 */     int page_sizes = Integer.parseInt(JconfigUtilContainer.velocityTemplateConfig().getProperty("num", "15", "show_list_num"));
/* 144 */     String order_by = "sub.id desc";
/* 145 */     String con = getSurveySearchCon(params);
/* 146 */     String[] tempA = params.split(";");
/* 147 */     for (int i = 0; i < tempA.length; i++)
/*     */     {
/* 149 */       if (tempA[i].toLowerCase().startsWith("size="))
/*     */       {
/* 151 */         String ps = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));
/* 152 */         if ((!"".equals(ps)) && (!ps.startsWith("$size")) && (FormatUtil.isNumeric(ps)))
/* 153 */           page_sizes = Integer.parseInt(ps);
/*     */       }
/* 155 */       if (tempA[i].toLowerCase().startsWith("cur_page="))
/*     */       {
/* 157 */         String cp = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));
/* 158 */         if ((!"".equals(cp)) && (!cp.startsWith("$cur_page")) && (FormatUtil.isNumeric(cp)))
/* 159 */           current_page = Integer.parseInt(cp);
/*     */       }
/*     */     }
/* 162 */     return SurveyService.getSurveyRecommendList(con, (current_page - 1) * page_sizes, page_sizes);
/*     */   }
/*     */ 
/*     */   public static SurveyCategory getSurveyCategoryObject(String category_id, String site_id)
/*     */   {
/* 167 */     return SurveyCategoryService.getSurveyCategoryBean(category_id);
/*     */   }
/*     */ 
/*     */   public static SurveyBean getSurveyBean(String s_id, String site_id)
/*     */   {
/* 172 */     return SurveyService.getSurveyBean(s_id);
/*     */   }
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.template.velocity.data.SurveyData
 * JD-Core Version:    0.6.2
 */