/*     */ package com.cicro.wcm.template.velocity.data;
/*     */ 
/*     */ import com.cicro.util.FormatUtil;
/*     */ import com.cicro.wcm.bean.comment.CommentBean;
/*     */ import com.cicro.wcm.bean.template.TurnPageBean;
/*     */ import com.cicro.wcm.services.comment.CommentService;
/*     */ import java.io.PrintStream;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class NewComment
/*     */ {
/*  13 */   private static int cur_page = 1;
/*  14 */   private static int page_size = 10;
/*     */ 
/*     */   public static TurnPageBean getCommentCount(String param)
/*     */   {
/*  18 */     TurnPageBean tpb = new TurnPageBean();
/*  19 */     tpb.setCount(Integer.parseInt(CommentService.getCommentMainCountForBrowser(getSearchMap(param))));
/*  20 */     tpb.setCur_page(cur_page);
/*  21 */     tpb.setPage_size(page_size);
/*  22 */     tpb.setPage_count(tpb.getCount() / tpb.getPage_size() + 1);
/*  23 */     if ((tpb.getCount() % tpb.getPage_size() == 0) && (tpb.getPage_count() > 1)) {
/*  24 */       tpb.setPage_count(tpb.getPage_count() - 1);
/*     */     }
/*  26 */     if (cur_page > 1) {
/*  27 */       tpb.setPrev_num(cur_page - 1);
/*     */     }
/*  29 */     tpb.setNext_num(tpb.getPage_count());
/*  30 */     if (cur_page < tpb.getPage_count())
/*  31 */       tpb.setNext_num(cur_page + 1);
/*  32 */     return tpb;
/*     */   }
/*     */ 
/*     */   public static Map<String, String> getSearchMap(String params)
/*     */   {
/*  37 */     Map m = new HashMap();
/*  38 */     if ((params != null) && (!"".equals(params)))
/*     */     {
/*  40 */       String[] tempA = params.split(";");
/*  41 */       for (int i = 0; i < tempA.length; i++)
/*     */       {
/*  43 */         if (tempA[i].toLowerCase().startsWith("site_id="))
/*     */         {
/*  45 */           String site_id = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));
/*  46 */           if ((!"".equals(site_id)) && (!site_id.startsWith("$site_id")) && (FormatUtil.isValiditySQL(site_id))) {
/*  47 */             m.put("site_id", site_id);
/*     */           }
/*     */         }
/*  50 */         if (tempA[i].toLowerCase().startsWith("size="))
/*     */         {
/*  52 */           String ps = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));
/*  53 */           if ((!"".equals(ps)) && (!ps.startsWith("$size")) && (FormatUtil.isNumeric(ps))) {
/*  54 */             page_size = Integer.parseInt(ps);
/*     */           }
/*     */         }
/*  57 */         if (tempA[i].toLowerCase().startsWith("cur_page="))
/*     */         {
/*  59 */           String cp = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));
/*  60 */           if ((!"".equals(cp)) && (!cp.startsWith("$cur_page")) && (FormatUtil.isNumeric(cp)))
/*  61 */             cur_page = Integer.parseInt(cp);
/*     */         }
/*  63 */         if (tempA[i].toLowerCase().startsWith("id="))
/*     */         {
/*  65 */           String id = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));
/*  66 */           if ((!"".equals(id)) && (!id.startsWith("$id")) && (FormatUtil.isNumeric(id)))
/*  67 */             m.put("id", id);
/*     */         }
/*  69 */         if (tempA[i].toLowerCase().startsWith("type="))
/*     */         {
/*  71 */           String type = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));
/*  72 */           if ((!"".equals(type)) && (!type.startsWith("$type")) && (FormatUtil.isValiditySQL(type)))
/*     */           {
/*  74 */             if ("info".equals(type))
/*  75 */               m.put("info_type", "1");
/*  76 */             if ("survey".equals(type))
/*     */             {
/*  78 */               m.put("survey", "2");
/*  79 */               m.put("info_type", "2");
/*     */             }
/*  81 */             if ("appeal".equals(type))
/*  82 */               m.put("info_type", "3");
/*     */           }
/*     */         }
/*     */       }
/*  86 */       m.put("page_size", page_size);
/*  87 */       m.put("current_page", cur_page);
/*     */     }
/*  89 */     return m;
/*     */   }
/*     */ 
/*     */   public static Map<String, Object> getCommentMap(String param)
/*     */   {
/*  94 */     Map con_m = getSearchMap(param);
/*  95 */     int start_page = Integer.parseInt((String)con_m.get("current_page"));
/*  96 */     int page_size = Integer.parseInt((String)con_m.get("page_size"));
/*  97 */     con_m.put("start_num", (start_page - 1) * page_size);
/*  98 */     con_m.put("page_size", page_size);
/*     */ 
/* 100 */     if ("1".equals(con_m.get("info_type")))
/*     */     {
/* 102 */       return CommentService.getCommentMainListBrowserForInfo(con_m);
/*     */     }
/* 104 */     return null;
/*     */   }
/*     */ 
/*     */   public static List<CommentBean> getHotCommentInfo(String param)
/*     */   {
/* 114 */     return CommentService.getHotCommentInfo(getSearchMap(param));
/*     */   }
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/* 119 */     Map m = getCommentMap("size=5;type=info;id=6951;site_id=HIWCMdemo");
/*     */ 
/* 121 */     System.out.println(((CommentBean)getHotCommentInfo("size=5;type=info;id=6951;site_id=HIWCMdemo").get(0)).getCount());
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.template.velocity.data.NewComment
 * JD-Core Version:    0.6.2
 */