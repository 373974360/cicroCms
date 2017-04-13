/*     */ package com.cicro.wcm.template.velocity.data;
/*     */ 
/*     */ import com.cicro.util.FormatUtil;
/*     */ import com.cicro.util.jconfig.JconfigUtil;
/*     */ import com.cicro.util.jconfig.JconfigUtilContainer;
/*     */ import com.cicro.wcm.bean.interview.SubReleInfo;
/*     */ import com.cicro.wcm.bean.interview.SubjectActor;
/*     */ import com.cicro.wcm.bean.interview.SubjectBean;
/*     */ import com.cicro.wcm.bean.template.TurnPageBean;
/*     */ import com.cicro.wcm.services.interview.SubReleInfoServices;
/*     */ import com.cicro.wcm.services.interview.SubjectActorServices;
/*     */ import com.cicro.wcm.services.interview.SubjectServices;
/*     */ import java.io.PrintStream;
/*     */ import java.util.List;
/*     */ 
/*     */ public class InterViewData
/*     */ {
/*  16 */   private static int cur_page = 1;
/*  17 */   private static int page_size = 10;
/*     */ 
/*     */   public static TurnPageBean getInterViewCount(String params)
/*     */   {
/*  21 */     TurnPageBean tpb = new TurnPageBean();
/*  22 */     tpb.setCount(Integer.parseInt(SubjectServices.getSubjectBrowserListHandlCount(getInterViewSearchCon(params))));
/*  23 */     tpb.setCur_page(cur_page);
/*  24 */     tpb.setPage_size(page_size);
/*  25 */     tpb.setPage_count(Integer.parseInt(tpb.getCount() / tpb.getPage_size()) + 1);
/*     */ 
/*  27 */     if ((tpb.getCount() % tpb.getPage_size() == 0) && (tpb.getPage_count() > 1)) {
/*  28 */       tpb.setPage_count(tpb.getPage_count() - 1);
/*     */     }
/*  30 */     if (cur_page > 1) {
/*  31 */       tpb.setPrev_num(cur_page - 1);
/*     */     }
/*  33 */     tpb.setNext_num(tpb.getPage_count());
/*  34 */     if (cur_page < tpb.getPage_count())
/*  35 */       tpb.setNext_num(cur_page + 1);
/*  36 */     return tpb;
/*     */   }
/*     */ 
/*     */   public static List<SubjectBean> getInterViewList(String params)
/*     */   {
/*  41 */     int current_page = 1;
/*  42 */     int page_sizes = Integer.parseInt(JconfigUtilContainer.velocityTemplateConfig().getProperty("num", "15", "show_list_num"));
/*  43 */     String order_by = "sub.id desc";
/*  44 */     String con = getInterViewSearchCon(params);
/*  45 */     String[] tempA = params.split(";");
/*  46 */     for (int i = 0; i < tempA.length; i++)
/*     */     {
/*  48 */       if (tempA[i].toLowerCase().startsWith("orderby="))
/*     */       {
/*  50 */         String ob = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));
/*     */ 
/*  52 */         if ((!"".equals(ob)) && (!ob.startsWith("$orderby")))
/*  53 */           order_by = ob;
/*     */       }
/*  55 */       if (tempA[i].toLowerCase().startsWith("size="))
/*     */       {
/*  57 */         String ps = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));
/*  58 */         if ((!"".equals(ps)) && (!ps.startsWith("$size")) && (FormatUtil.isNumeric(ps)))
/*  59 */           page_sizes = Integer.parseInt(ps);
/*     */       }
/*  61 */       if (tempA[i].toLowerCase().startsWith("cur_page="))
/*     */       {
/*  63 */         String cp = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));
/*  64 */         if ((!"".equals(cp)) && (!cp.startsWith("$cur_page")) && (FormatUtil.isNumeric(cp)))
/*  65 */           current_page = Integer.parseInt(cp);
/*     */       }
/*     */     }
/*  68 */     return SubjectServices.getSubjectBrowserList(con, (current_page - 1) * page_sizes, page_sizes, order_by);
/*     */   }
/*     */ 
/*     */   public static String getInterViewSearchCon(String params)
/*     */   {
/*  73 */     String con = "";
/*  74 */     String[] tempA = params.split(";");
/*  75 */     for (int i = 0; i < tempA.length; i++)
/*     */     {
/*  77 */       if (tempA[i].toLowerCase().startsWith("site_id="))
/*     */       {
/*  79 */         con = con + " and sc.site_id = '" + FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1)) + "'";
/*     */       }
/*  81 */       if (tempA[i].toLowerCase().startsWith("cat_id="))
/*     */       {
/*  83 */         String cid = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));
/*     */ 
/*  85 */         if ((!"".equals(cid)) && (!cid.startsWith("$cat_id")) && (FormatUtil.isValiditySQL(cid)))
/*     */         {
/*  87 */           if (cid.indexOf(",") > 0)
/*  88 */             cid = cid.replaceAll(",", "','");
/*  89 */           con = con + " and sc.category_id in ('" + cid + "')";
/*     */         }
/*     */       }
/*  92 */       if (tempA[i].toLowerCase().startsWith("status="))
/*     */       {
/*  94 */         String st = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));
/*     */ 
/*  96 */         if ((!"".equals(st)) && (!st.startsWith("$status")) && (FormatUtil.isValiditySQL(st)))
/*     */         {
/*  98 */           if (st.indexOf(",") > -1)
/*  99 */             con = con + " and sub.status in (" + st + ")";
/*     */           else
/* 101 */             con = con + " and sub.status = " + st;
/*     */         }
/*     */       }
/* 104 */       if (tempA[i].toLowerCase().startsWith("size="))
/*     */       {
/* 106 */         String ps = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));
/* 107 */         if ((!"".equals(ps)) && (!ps.startsWith("$size")) && (FormatUtil.isNumeric(ps)))
/* 108 */           page_size = Integer.parseInt(ps);
/*     */       }
/* 110 */       if (tempA[i].toLowerCase().startsWith("cur_page="))
/*     */       {
/* 112 */         String cp = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));
/* 113 */         if ((!"".equals(cp)) && (!cp.startsWith("$cur_page")) && (FormatUtil.isNumeric(cp))) {
/* 114 */           cur_page = Integer.parseInt(cp);
/*     */         }
/*     */       }
/*     */     }
/* 118 */     return con;
/*     */   }
/*     */ 
/*     */   public static List<SubjectActor> getInterViewActorList(String sub_id)
/*     */   {
/* 128 */     return SubjectActorServices.getActorList("", sub_id);
/*     */   }
/*     */ 
/*     */   public static List<SubjectActor> getInterViewInfoList(String params)
/*     */   {
/* 134 */     String[] tempA = params.split(";");
/* 135 */     String sub_id = "";
/* 136 */     String con = " and publish_flag = 1";
/* 137 */     int page_sizes = Integer.parseInt(JconfigUtilContainer.velocityTemplateConfig().getProperty("num", "15", "show_list_num"));
/* 138 */     int current_page = 1;
/* 139 */     for (int i = 0; i < tempA.length; i++)
/*     */     {
/* 141 */       if (tempA[i].toLowerCase().startsWith("sub_id="))
/*     */       {
/* 143 */         String sid = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));
/*     */ 
/* 145 */         if ((!"".equals(sid)) && (!sid.startsWith("$cat_id")) && (FormatUtil.isValiditySQL(sid)))
/*     */         {
/* 147 */           sub_id = sid;
/*     */         }
/*     */       }
/* 150 */       if (tempA[i].toLowerCase().startsWith("size="))
/*     */       {
/* 152 */         String ps = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));
/* 153 */         if ((!"".equals(ps)) && (!ps.startsWith("$size")) && (FormatUtil.isNumeric(ps)))
/* 154 */           page_sizes = Integer.parseInt(ps);
/*     */       }
/* 156 */       if (tempA[i].toLowerCase().startsWith("cur_page="))
/*     */       {
/* 158 */         String cp = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));
/* 159 */         if ((!"".equals(cp)) && (!cp.startsWith("$cur_page")) && (FormatUtil.isNumeric(cp))) {
/* 160 */           current_page = Integer.parseInt(cp);
/*     */         }
/*     */       }
/*     */     }
/* 164 */     return SubReleInfoServices.getReleInfoListBySub_id(con, (current_page - 1) * page_sizes, page_sizes, sub_id);
/*     */   }
/*     */ 
/*     */   public static List<SubjectBean> getInterViewRecommendList(String params)
/*     */   {
/* 171 */     int current_page = 1;
/* 172 */     int page_sizes = Integer.parseInt(JconfigUtilContainer.velocityTemplateConfig().getProperty("num", "15", "show_list_num"));
/* 173 */     String[] tempA = params.split(";");
/* 174 */     for (int i = 0; i < tempA.length; i++)
/*     */     {
/* 176 */       if (tempA[i].toLowerCase().startsWith("size="))
/*     */       {
/* 178 */         String ps = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));
/* 179 */         if ((!"".equals(ps)) && (!ps.startsWith("$size")) && (FormatUtil.isNumeric(ps)))
/* 180 */           page_sizes = Integer.parseInt(ps);
/*     */       }
/* 182 */       if (tempA[i].toLowerCase().startsWith("cur_page="))
/*     */       {
/* 184 */         String cp = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));
/* 185 */         if ((!"".equals(cp)) && (!cp.startsWith("$cur_page")) && (FormatUtil.isNumeric(cp)))
/* 186 */           current_page = Integer.parseInt(cp);
/*     */       }
/*     */     }
/* 189 */     return SubjectServices.getSubjectRecommendList(getInterViewSearchCon(params), (current_page - 1) * page_sizes, page_sizes);
/*     */   }
/*     */ 
/*     */   public static SubReleInfo getInterViewInfoContent(int id)
/*     */   {
/* 195 */     return SubReleInfoServices.getSubReleInfo(id);
/*     */   }
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/* 200 */     System.out.println(getInterViewCount("status=0;size=5;cur_page=2;"));
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.template.velocity.data.InterViewData
 * JD-Core Version:    0.6.2
 */