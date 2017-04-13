/*     */ package com.cicro.wcm.template.velocity.data;
/*     */ 
/*     */ import com.cicro.util.FormatUtil;
/*     */ import com.cicro.util.jconfig.JconfigUtilContainer;
/*     */ import com.cicro.wcm.bean.system.comment.CommentBean;
/*     */ import com.cicro.wcm.bean.template.TurnPageBean;
/*     */ import com.cicro.wcm.services.system.comment.CommentManager;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class CommentData
/*     */ {
/*  16 */   private static int cur_page = 1;
/*  17 */   private static int page_size = 10;
/*     */ 
/*     */   public static TurnPageBean getCommentCount(String params)
/*     */   {
/*  21 */     TurnPageBean tpb = new TurnPageBean();
/*  22 */     tpb.setCount(Integer.parseInt(CommentManager.getCommentListCnt(getCommentSearchCon(params))));
/*  23 */     tpb.setCur_page(cur_page);
/*  24 */     tpb.setPage_size(page_size);
/*  25 */     tpb.setPage_count(tpb.getCount() / tpb.getPage_size() + 1);
/*  26 */     if ((tpb.getCount() % tpb.getPage_size() == 0) && (tpb.getPage_count() > 1)) {
/*  27 */       tpb.setPage_count(tpb.getPage_count() - 1);
/*     */     }
/*  29 */     if (cur_page > 1) {
/*  30 */       tpb.setPrev_num(cur_page - 1);
/*     */     }
/*  32 */     tpb.setNext_num(tpb.getPage_count());
/*  33 */     if (cur_page < tpb.getPage_count())
/*  34 */       tpb.setNext_num(cur_page + 1);
/*  35 */     return tpb;
/*     */   }
/*     */ 
/*     */   public static List<CommentBean> getCommentList(String params) {
/*  39 */     String current_page = "1";
/*  40 */     String page_sizes = JconfigUtilContainer.velocityTemplateConfig().getProperty("num", "15", "show_list_num");
/*  41 */     Map m = getCommentSearchCon(params);
/*  42 */     String[] tempA = params.split(";");
/*  43 */     for (int i = 0; i < tempA.length; i++)
/*     */     {
/*  45 */       if (tempA[i].toLowerCase().startsWith("size="))
/*     */       {
/*  47 */         String ps = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));
/*  48 */         if ((!"".equals(ps)) && (!ps.startsWith("$size")) && (FormatUtil.isNumeric(ps))) {
/*  49 */           page_sizes = ps;
/*     */         }
/*     */       }
/*  52 */       if (tempA[i].toLowerCase().startsWith("cur_page="))
/*     */       {
/*  54 */         String cp = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));
/*  55 */         if ((!"".equals(cp)) && (!cp.startsWith("$cur_page")) && (FormatUtil.isNumeric(cp)))
/*  56 */           current_page = cp;
/*     */       }
/*     */     }
/*  59 */     m.put("search", "");
/*  60 */     m.put("page_size", page_sizes);
/*  61 */     m.put("start_num", (Integer.parseInt(current_page) - 1) * Integer.parseInt(page_sizes));
/*     */ 
/*  63 */     return CommentManager.getCommentList(m);
/*     */   }
/*     */ 
/*     */   public static Map<String, String> getCommentSearchCon(String params)
/*     */   {
/*  68 */     Map m = new HashMap();
/*  69 */     if ((params != null) && (!"".equals(params)))
/*     */     {
/*  71 */       String[] tempA = params.split(";");
/*  72 */       for (int i = 0; i < tempA.length; i++)
/*     */       {
/*  74 */         if (tempA[i].toLowerCase().startsWith("size="))
/*     */         {
/*  76 */           String ps = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));
/*  77 */           if ((!"".equals(ps)) && (!ps.startsWith("$size")) && (FormatUtil.isNumeric(ps))) {
/*  78 */             page_size = Integer.parseInt(ps);
/*     */           }
/*     */ 
/*     */         }
/*  82 */         else if (tempA[i].toLowerCase().startsWith("cur_page="))
/*     */         {
/*  84 */           String cp = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));
/*  85 */           if ((!"".equals(cp)) && (!cp.startsWith("$cur_page")) && (FormatUtil.isNumeric(cp)))
/*  86 */             cur_page = Integer.parseInt(cp);
/*     */         }
/*     */         else
/*     */         {
/*  90 */           int index_num = tempA[i].indexOf("=");
/*  91 */           String vals = tempA[i].substring(index_num + 1);
/*  92 */           if (!"".equals(FormatUtil.formatNullString(vals))) {
/*  93 */             m.put(tempA[i].substring(0, index_num), vals);
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*  98 */     m.put("is_deleted", "0");
/*  99 */     m.put("cmt_status", "1");
/* 100 */     return m;
/*     */   }
/*     */ 
/*     */   public static void main(String[] arg)
/*     */   {
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.template.velocity.data.CommentData
 * JD-Core Version:    0.6.2
 */