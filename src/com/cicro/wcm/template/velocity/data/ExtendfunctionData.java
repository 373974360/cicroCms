/*     */ package com.cicro.wcm.template.velocity.data;
/*     */ 
/*     */ import com.cicro.util.FormatUtil;
/*     */ import com.cicro.wcm.bean.template.TurnPageBean;
/*     */ import com.cicro.wcm.services.extendfunction.infoRelatedKcat.InfoRelatedKcatBean;
/*     */ import com.cicro.wcm.services.extendfunction.infoRelatedKcat.InfoRelatedKcatManager;
/*     */ import com.cicro.wcm.services.extendfunction.knowledgeTab.KnowledgeCateBean;
/*     */ import com.cicro.wcm.services.extendfunction.knowledgeTab.KnowledgeCateManager;
/*     */ import java.io.PrintStream;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class ExtendfunctionData
/*     */ {
/*  58 */   private static int cur_page = 1;
/*  59 */   private static int page_size = 15;
/*     */ 
/*     */   public static List<InfoRelatedKcatBean> removeHTMLTag(List<InfoRelatedKcatBean> l)
/*     */   {
/*  63 */     if ((l != null) && (l.size() > 0))
/*     */     {
/*  65 */       for (InfoRelatedKcatBean info : l)
/*     */       {
/*  67 */         info.setTitle(info.getTitle().replaceAll("<[Bb][Rr]/?>", ""));
/*     */       }
/*     */     }
/*  70 */     return l;
/*     */   }
/*     */ 
/*     */   public static List<InfoRelatedKcatBean> removeSQBeanHTMLTag(List<InfoRelatedKcatBean> l)
/*     */   {
/*  75 */     if ((l != null) && (l.size() > 0))
/*     */     {
/*  77 */       for (InfoRelatedKcatBean info : l)
/*     */       {
/*  79 */         info.setTitle(info.getTitle().replaceAll("<[Bb][Rr]/?>", ""));
/*     */       }
/*     */     }
/*  82 */     return l;
/*     */   }
/*     */ 
/*     */   public static List<InfoRelatedKcatBean> getCGGRelatedKcatInfoList(String params)
/*     */   {
/*  91 */     Map con_map = new HashMap();
/*  92 */     getInfoSearchCon(params, con_map);
/*  93 */     return removeHTMLTag(InfoRelatedKcatManager.getCGGRelatedKcatInfoList(con_map));
/*     */   }
/*     */ 
/*     */   public static TurnPageBean getCGGRelatedKcatInfoListCount(String params)
/*     */   {
/* 103 */     Map con_map = new HashMap();
/* 104 */     getInfoSearchCon(params, con_map);
/*     */ 
/* 106 */     TurnPageBean tpb = new TurnPageBean();
/* 107 */     tpb.setCount(Integer.parseInt(InfoRelatedKcatManager.getCGGRelatedKcatInfoListCounts(con_map)));
/* 108 */     tpb.setCur_page(cur_page);
/* 109 */     tpb.setPage_size(page_size);
/* 110 */     tpb.setPage_count(tpb.getCount() / tpb.getPage_size() + 1);
/*     */ 
/* 112 */     if ((tpb.getCount() % tpb.getPage_size() == 0) && (tpb.getPage_count() > 1)) {
/* 113 */       tpb.setPage_count(tpb.getPage_count() - 1);
/*     */     }
/* 115 */     if (cur_page > 1) {
/* 116 */       tpb.setPrev_num(cur_page - 1);
/*     */     }
/* 118 */     tpb.setNext_num(tpb.getPage_count());
/* 119 */     if (cur_page < tpb.getPage_count()) {
/* 120 */       tpb.setNext_num(cur_page + 1);
/*     */     }
/*     */ 
/* 123 */     if (tpb.getPage_count() > 10)
/*     */     {
/* 126 */       if (cur_page > 5)
/*     */       {
/* 128 */         if (cur_page > tpb.getPage_count() - 4)
/* 129 */           tpb.setCurr_start_num(tpb.getPage_count() - 6);
/*     */         else
/* 131 */           tpb.setCurr_start_num(cur_page - 2);
/*     */       }
/*     */     }
/* 134 */     return tpb;
/*     */   }
/*     */ 
/*     */   public static void getInfoSearchCon(String params, Map<String, String> con_map)
/*     */   {
/* 145 */     String orderby = "i.released_dtime desc";
/* 146 */     String[] tempA = params.split(";");
/* 147 */     for (int i = 0; i < tempA.length; i++)
/*     */     {
/* 149 */       if (tempA[i].toLowerCase().startsWith("site_id="))
/*     */       {
/* 151 */         String site_id = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));
/*     */ 
/* 153 */         if ((!"".equals(site_id)) && (!site_id.startsWith("$site_id")) && (FormatUtil.isValiditySQL(site_id)))
/*     */         {
/* 155 */           con_map.put("site_id", site_id);
/*     */         }
/*     */       }
/* 158 */       if (tempA[i].toLowerCase().startsWith("kcat_id="))
/*     */       {
/* 160 */         String kcat_id = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));
/* 161 */         if ((!"".equals(kcat_id)) && (!kcat_id.startsWith("$kcat_id")) && (FormatUtil.isValiditySQL(kcat_id)))
/*     */         {
/* 163 */           con_map.put("kcat_id", kcat_id);
/*     */         }
/*     */       }
/* 166 */       if (tempA[i].toLowerCase().startsWith("app_id="))
/*     */       {
/* 168 */         String app_id = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));
/* 169 */         if ((!"".equals(app_id)) && (!app_id.startsWith("$app_id")) && (FormatUtil.isValiditySQL(app_id)))
/*     */         {
/* 171 */           con_map.put("app_id", app_id);
/*     */         }
/*     */       }
/* 174 */       if (tempA[i].toLowerCase().startsWith("size="))
/*     */       {
/* 176 */         String ps = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));
/* 177 */         if ((!"".equals(ps)) && (!ps.startsWith("$size")) && (FormatUtil.isNumeric(ps)))
/* 178 */           page_size = Integer.parseInt(ps);
/*     */       }
/* 180 */       if (tempA[i].toLowerCase().startsWith("cur_page="))
/*     */       {
/* 182 */         String cp = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));
/* 183 */         if ((!"".equals(cp)) && (!cp.startsWith("$cur_page")) && (FormatUtil.isNumeric(cp)))
/* 184 */           cur_page = Integer.parseInt(cp);
/*     */       }
/*     */     }
/* 187 */     con_map.put("page_size", page_size+"");
/* 188 */     con_map.put("current_page", cur_page+"");
/* 189 */     con_map.put("start_num", (cur_page - 1) * page_size+"");
/* 190 */     con_map.put("orderby", orderby);
/*     */ 
/* 192 */     System.out.println(con_map);
/*     */   }
/*     */ 
/*     */   public static String getKnowledgeCateBroJSONTreeStr(String site_id, String app_id)
/*     */   {
/* 202 */     return KnowledgeCateManager.getKnowledgeCateBroJSONTreeStr(site_id, app_id);
/*     */   }
/*     */ 
/*     */   public static KnowledgeCateBean getKCteBeanByWID(String kcat_id, String site_id)
/*     */   {
/* 212 */     return KnowledgeCateManager.getKCteBeanByWID(kcat_id, site_id);
/*     */   }
/*     */ 
/*     */   public static List<InfoRelatedKcatBean> getSQRelatedKcatInfoList(String params)
/*     */   {
/* 222 */     Map con_map = new HashMap();
/* 223 */     getSQSearchCon(params, con_map);
/* 224 */     return removeSQBeanHTMLTag(InfoRelatedKcatManager.getSQRelatedKcatInfoList(con_map));
/*     */   }
/*     */ 
/*     */   public static void getSQSearchCon(String params, Map<String, String> con_map)
/*     */   {
/* 229 */     String orderby = "sq.publish_dtime desc";
/* 230 */     String[] tempA = params.split(";");
/* 231 */     for (int i = 0; i < tempA.length; i++)
/*     */     {
/* 233 */       if (tempA[i].toLowerCase().startsWith("kcat_id="))
/*     */       {
/* 235 */         String kcat_id = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));
/* 236 */         if ((!"".equals(kcat_id)) && (!kcat_id.startsWith("$kcat_id")) && (FormatUtil.isValiditySQL(kcat_id)))
/*     */         {
/* 238 */           con_map.put("kcat_id", kcat_id);
/*     */         }
/*     */       }
/* 241 */       if (tempA[i].toLowerCase().startsWith("app_id="))
/*     */       {
/* 243 */         String app_id = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));
/* 244 */         if ((!"".equals(app_id)) && (!app_id.startsWith("$app_id")) && (FormatUtil.isValiditySQL(app_id)))
/*     */         {
/* 246 */           con_map.put("app_id", app_id);
/*     */         }
/*     */       }
/* 249 */       if (tempA[i].toLowerCase().startsWith("size="))
/*     */       {
/* 251 */         String ps = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));
/* 252 */         if ((!"".equals(ps)) && (!ps.startsWith("$size")) && (FormatUtil.isNumeric(ps)))
/* 253 */           page_size = Integer.parseInt(ps);
/*     */       }
/* 255 */       if (tempA[i].toLowerCase().startsWith("cur_page="))
/*     */       {
/* 257 */         String cp = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));
/* 258 */         if ((!"".equals(cp)) && (!cp.startsWith("$cur_page")) && (FormatUtil.isNumeric(cp)))
/* 259 */           cur_page = Integer.parseInt(cp);
/*     */       }
/*     */     }
/* 262 */     con_map.put("page_size", page_size+"");
/* 263 */     con_map.put("current_page", cur_page+"");
/* 264 */     con_map.put("start_num", (cur_page - 1) * page_size+"");
/* 265 */     con_map.put("orderby", orderby);
/*     */   }
/*     */ 
/*     */   public static TurnPageBean getSQRelatedKcatInfoListCount(String params)
/*     */   {
/* 275 */     Map con_map = new HashMap();
/* 276 */     getSQSearchCon(params, con_map);
/*     */ 
/* 278 */     TurnPageBean tpb = new TurnPageBean();
/* 279 */     tpb.setCount(Integer.parseInt(InfoRelatedKcatManager.getSQRelatedKcatInfoListCounts(con_map)));
/* 280 */     tpb.setCur_page(cur_page);
/* 281 */     tpb.setPage_size(page_size);
/* 282 */     tpb.setPage_count(tpb.getCount() / tpb.getPage_size() + 1);
/*     */ 
/* 284 */     if ((tpb.getCount() % tpb.getPage_size() == 0) && (tpb.getPage_count() > 1)) {
/* 285 */       tpb.setPage_count(tpb.getPage_count() - 1);
/*     */     }
/* 287 */     if (cur_page > 1) {
/* 288 */       tpb.setPrev_num(cur_page - 1);
/*     */     }
/* 290 */     tpb.setNext_num(tpb.getPage_count());
/* 291 */     if (cur_page < tpb.getPage_count()) {
/* 292 */       tpb.setNext_num(cur_page + 1);
/*     */     }
/*     */ 
/* 295 */     if (tpb.getPage_count() > 10)
/*     */     {
/* 298 */       if (cur_page > 5)
/*     */       {
/* 300 */         if (cur_page > tpb.getPage_count() - 4)
/* 301 */           tpb.setCurr_start_num(tpb.getPage_count() - 6);
/*     */         else
/* 303 */           tpb.setCurr_start_num(cur_page - 2);
/*     */       }
/*     */     }
/* 306 */     return tpb;
/*     */   }
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\
 * Qualified Name:     com.cicro.wcm.template.velocity.data.ExtendfunctionData
 * JD-Core Version:    0.6.2
 */