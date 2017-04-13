/*     */ package com.cicro.wcm.template.velocity.data;
/*     */ 
/*     */ import com.cicro.util.FormatUtil;
/*     */ import com.cicro.util.jconfig.JconfigUtil;
/*     */ import com.cicro.util.jconfig.JconfigUtilContainer;
/*     */ import com.cicro.wcm.bean.appCom.guestbook.GuestBookBean;
/*     */ import com.cicro.wcm.bean.appCom.guestbook.GuestBookCategory;
/*     */ import com.cicro.wcm.bean.appCom.guestbook.GuestBookClass;
/*     */ import com.cicro.wcm.bean.appCom.guestbook.GuestBookSub;
/*     */ import com.cicro.wcm.bean.template.TurnPageBean;
/*     */ import com.cicro.wcm.services.appCom.guestbook.GuestBookCategoryManager;
/*     */ import com.cicro.wcm.services.appCom.guestbook.GuestBookClassManager;
/*     */ import com.cicro.wcm.services.appCom.guestbook.GuestBookManager;
/*     */ import com.cicro.wcm.services.appCom.guestbook.GuestBookSubManager;
/*     */ import java.io.PrintStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class GuestBookData
/*     */ {
/*  21 */   private static int cur_page = 1;
/*  22 */   private static int page_size = 10;
/*     */ 
/*     */   public static Map<String, String> getSearchMap(String params)
/*     */   {
/*  26 */     Map m = new HashMap();
/*  27 */     m.put("publish_status", "1");
/*  28 */     m.put("sort_name", "add_time");
/*  29 */     m.put("sort_type", "desc");
/*  30 */     String current_page = "1";
/*  31 */     String page_sizes = JconfigUtilContainer.velocityTemplateConfig().getProperty("num", "15", "show_list_num");
/*  32 */     String[] tempA = params.split(";");
/*  33 */     for (int i = 0; i < tempA.length; i++)
/*     */     {
/*  35 */       if (tempA[i].toLowerCase().startsWith("gbs_id="))
/*     */       {
/*  37 */         String gbs_id = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));
/*  38 */         if ((!"".equals(gbs_id)) && (!gbs_id.startsWith("$gbs_id")) && (FormatUtil.isValiditySQL(gbs_id)))
/*     */         {
/*  40 */           m.put("gbs_id", gbs_id);
/*  41 */           GuestBookSub gbs = GuestBookSubManager.getGuestBookSub(Integer.parseInt(gbs_id));
/*  42 */           if (gbs != null)
/*     */           {
/*  45 */             if (gbs.getIs_receive_show() == 1)
/*     */             {
/*  47 */               m.put("reply_status", "1");
/*     */             }
/*     */           }
/*  50 */           if (gbs.getPublish_status() == 0)
/*     */           {
/*  53 */             m.put("gbs_id", "0");
/*  54 */             return m;
/*     */           }
/*     */         }
/*     */       }
/*  58 */       if (tempA[i].toLowerCase().startsWith("orderby="))
/*     */       {
/*  60 */         String ob = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));
/*  61 */         if ((!"".equals(ob)) && (!ob.startsWith("$orderby")) && (FormatUtil.isValiditySQL(ob)))
/*     */         {
/*  63 */           String[] o = ob.split(" ");
/*  64 */           if (o.length > 1)
/*     */           {
/*  66 */             m.put("sort_name", o[0]);
/*  67 */             m.put("sort_type", o[1]);
/*     */           } else {
/*  69 */             m.put("sort_name", ob);
/*     */           }
/*     */         }
/*     */       }
/*  72 */       if (tempA[i].toLowerCase().startsWith("reply_status="))
/*     */       {
/*  74 */         String reply_status = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));
/*  75 */         if ((!"".equals(reply_status)) && (!reply_status.startsWith("$reply_status")) && (FormatUtil.isValiditySQL(reply_status)))
/*     */         {
/*  77 */           m.put("reply_status", reply_status);
/*     */         }
/*     */       }
/*  80 */       if (tempA[i].toLowerCase().startsWith("keyword="))
/*     */       {
/*  82 */         String keyword = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));
/*  83 */         if ((!"".equals(keyword)) && (!keyword.startsWith("$keyword")) && (FormatUtil.isValiditySQL(keyword)))
/*     */         {
/*  85 */           m.put("keyword", keyword);
/*     */         }
/*     */       }
/*  88 */       if (tempA[i].toLowerCase().startsWith("start_time="))
/*     */       {
/*  90 */         String start_time = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));
/*  91 */         if ((!"".equals(start_time)) && (!start_time.startsWith("$start_time")) && (FormatUtil.isValiditySQL(start_time)))
/*     */         {
/*  93 */           m.put("start_day", start_time + " 00:00:00");
/*     */         }
/*     */       }
/*  96 */       if (tempA[i].toLowerCase().startsWith("end_time="))
/*     */       {
/*  98 */         String end_time = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));
/*  99 */         if ((!"".equals(end_time)) && (!end_time.startsWith("$end_time")) && (FormatUtil.isValiditySQL(end_time)))
/*     */         {
/* 101 */           m.put("end_day", end_time + " 23:59:59");
/*     */         }
/*     */       }
/* 104 */       if (tempA[i].toLowerCase().startsWith("size="))
/*     */       {
/* 106 */         String ps = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));
/* 107 */         if ((!"".equals(ps)) && (!ps.startsWith("$size")) && (FormatUtil.isNumeric(ps)))
/* 108 */           page_sizes = ps;
/*     */       }
/* 110 */       if (tempA[i].toLowerCase().startsWith("cur_page="))
/*     */       {
/* 112 */         String cp = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));
/* 113 */         if ((!"".equals(cp)) && (!cp.startsWith("$cur_page")) && (FormatUtil.isNumeric(cp)))
/* 114 */           current_page = cp;
/*     */       }
/*     */     }
/* 117 */     m.put("page_size", page_sizes);
/* 118 */     m.put("cur_page", current_page);
/* 119 */     cur_page = Integer.parseInt(current_page);
/* 120 */     return m;
/*     */   }
/*     */ 
/*     */   public static TurnPageBean getGuestBookCount(String params)
/*     */   {
/* 125 */     TurnPageBean tpb = new TurnPageBean();
/* 126 */     Map m = getSearchMap(params);
/*     */ 
/* 128 */     tpb.setCount(Integer.parseInt(GuestBookManager.getGuestbookCount(m)));
/* 129 */     tpb.setCur_page(cur_page);
/* 130 */     tpb.setPage_size(page_size);
/* 131 */     tpb.setPage_count(tpb.getCount() / tpb.getPage_size() + 1);
/*     */ 
/* 133 */     if ((tpb.getCount() % tpb.getPage_size() == 0) && (tpb.getPage_count() > 1)) {
/* 134 */       tpb.setPage_count(tpb.getPage_count() - 1);
/*     */     }
/* 136 */     if (cur_page > 1) {
/* 137 */       tpb.setPrev_num(cur_page - 1);
/*     */     }
/* 139 */     tpb.setNext_num(tpb.getPage_count());
/* 140 */     if (cur_page < tpb.getPage_count())
/* 141 */       tpb.setNext_num(cur_page + 1);
/* 142 */     return tpb;
/*     */   }
/*     */ 
/*     */   public static List<GuestBookBean> getGuestBookList(String params)
/*     */   {
/* 147 */     Map m = getSearchMap(params);
/* 148 */     String current_page = (String)m.get("cur_page");
/* 149 */     String page_sizes = (String)m.get("page_size");
/* 150 */     int start_page = 1;
/* 151 */     if ((current_page != null) && (!"".equals(current_page)) && (!"0".equals(current_page))) {
/*     */       try
/*     */       {
/* 154 */         start_page = Integer.parseInt(current_page);
/*     */       }
/*     */       catch (Exception e) {
/* 157 */         e.printStackTrace();
/*     */       }
/*     */     }
/* 160 */     if ((page_sizes != null) && (!"".equals(page_sizes))) {
/*     */       try
/*     */       {
/* 163 */         page_size = Integer.parseInt(page_sizes);
/*     */       }
/*     */       catch (Exception e) {
/* 166 */         e.printStackTrace();
/*     */       }
/*     */     }
/* 169 */     m.put("start_num", (start_page - 1) * page_size);
/* 170 */     return GuestBookManager.getGuestbookList(m);
/*     */   }
/*     */ 
/*     */   public static GuestBookSub getGBSubject(String gbs_id)
/*     */   {
/* 176 */     if ((!"".equals(gbs_id)) && (gbs_id != null)) {
/* 177 */       return GuestBookSubManager.getGuestBookSub(Integer.parseInt(gbs_id));
/*     */     }
/* 179 */     return null;
/*     */   }
/*     */ 
/*     */   public static GuestBookCategory getGBCategory(String cat_id)
/*     */   {
/* 185 */     if ((!"".equals(cat_id)) && (cat_id != null)) {
/* 186 */       return GuestBookCategoryManager.getGuestBookCategoryBean(Integer.parseInt(cat_id));
/*     */     }
/* 188 */     return null;
/*     */   }
/*     */ 
/*     */   public static List<GuestBookCategory> getGuestBookCategoryList(String site_id)
/*     */   {
/* 194 */     List l = new ArrayList();
/* 195 */     List all_list = GuestBookCategoryManager.getGuestBookCategoryList(site_id);
/* 196 */     if ((all_list != null) && (all_list.size() > 0))
/*     */     {
/* 198 */       for (GuestBookCategory cat : all_list)
/*     */       {
/* 200 */         if (cat.getPublish_status() == 1)
/* 201 */           l.add(cat);
/*     */       }
/*     */     }
/* 204 */     return l;
/*     */   }
/*     */ 
/*     */   public static Map<String, String> getSearchSubjectMap(String params)
/*     */   {
/* 209 */     Map m = new HashMap();
/* 210 */     m.put("publish_status", "1");
/* 211 */     m.put("sort_name", "id");
/* 212 */     m.put("sort_type", "desc");
/* 213 */     String current_page = "1";
/* 214 */     String page_sizes = JconfigUtilContainer.velocityTemplateConfig().getProperty("num", "15", "show_list_num");
/* 215 */     String[] tempA = params.split(";");
/* 216 */     for (int i = 0; i < tempA.length; i++)
/*     */     {
/* 218 */       if (tempA[i].toLowerCase().startsWith("cat_id="))
/*     */       {
/* 220 */         String cat_id = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));
/* 221 */         if ((!"".equals(cat_id)) && (!cat_id.startsWith("$cat_id")) && (FormatUtil.isValiditySQL(cat_id)))
/*     */         {
/* 223 */           m.put("cat_id", cat_id);
/*     */         }
/*     */       }
/* 226 */       if (tempA[i].toLowerCase().startsWith("site_id="))
/*     */       {
/* 228 */         String site_id = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));
/* 229 */         if ((!"".equals(site_id)) && (!site_id.startsWith("$site_id")) && (FormatUtil.isValiditySQL(site_id)))
/*     */         {
/* 231 */           m.put("site_id", site_id);
/*     */         }
/*     */       }
/* 234 */       if (tempA[i].toLowerCase().startsWith("orderby="))
/*     */       {
/* 236 */         String ob = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));
/* 237 */         if ((!"".equals(ob)) && (!ob.startsWith("$orderby")) && (FormatUtil.isValiditySQL(ob)))
/*     */         {
/* 239 */           String[] o = ob.split(" ");
/* 240 */           if (o.length > 1)
/*     */           {
/* 242 */             m.put("sort_name", o[0]);
/* 243 */             m.put("sort_type", o[1]);
/*     */           } else {
/* 245 */             m.put("sort_name", ob);
/*     */           }
/*     */         }
/*     */       }
/* 248 */       if (tempA[i].toLowerCase().startsWith("size="))
/*     */       {
/* 250 */         String ps = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));
/* 251 */         if ((!"".equals(ps)) && (!ps.startsWith("$size")) && (FormatUtil.isNumeric(ps)))
/* 252 */           page_sizes = ps;
/*     */       }
/* 254 */       if (tempA[i].toLowerCase().startsWith("cur_page="))
/*     */       {
/* 256 */         String cp = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));
/* 257 */         if ((!"".equals(cp)) && (!cp.startsWith("$cur_page")) && (FormatUtil.isNumeric(cp)))
/* 258 */           current_page = cp;
/*     */       }
/*     */     }
/* 261 */     m.put("page_size", page_sizes);
/* 262 */     m.put("cur_page", current_page);
/* 263 */     cur_page = Integer.parseInt(current_page);
/* 264 */     return m;
/*     */   }
/*     */ 
/*     */   public static TurnPageBean getGBSubjectCount(String params)
/*     */   {
/* 269 */     TurnPageBean tpb = new TurnPageBean();
/* 270 */     Map m = getSearchSubjectMap(params);
/*     */ 
/* 272 */     tpb.setCount(Integer.parseInt(GuestBookSubManager.getGuestBookSubCount(m)));
/* 273 */     tpb.setCur_page(cur_page);
/* 274 */     tpb.setPage_size(page_size);
/* 275 */     tpb.setPage_count(tpb.getCount() / tpb.getPage_size() + 1);
/*     */ 
/* 277 */     if ((tpb.getCount() % tpb.getPage_size() == 0) && (tpb.getPage_count() > 1)) {
/* 278 */       tpb.setPage_count(tpb.getPage_count() - 1);
/*     */     }
/* 280 */     if (cur_page > 1) {
/* 281 */       tpb.setPrev_num(cur_page - 1);
/*     */     }
/* 283 */     tpb.setNext_num(tpb.getPage_count());
/* 284 */     if (cur_page < tpb.getPage_count())
/* 285 */       tpb.setNext_num(cur_page + 1);
/* 286 */     return tpb;
/*     */   }
/*     */ 
/*     */   public static List<GuestBookSub> getGBSubjectList(String params)
/*     */   {
/* 291 */     Map m = getSearchSubjectMap(params);
/* 292 */     String current_page = (String)m.get("cur_page");
/* 293 */     String page_sizes = (String)m.get("page_size");
/* 294 */     int start_page = 1;
/* 295 */     if ((current_page != null) && (!"".equals(current_page)) && (!"0".equals(current_page))) {
/*     */       try
/*     */       {
/* 298 */         start_page = Integer.parseInt(current_page);
/*     */       }
/*     */       catch (Exception e) {
/* 301 */         e.printStackTrace();
/*     */       }
/*     */     }
/* 304 */     if ((page_sizes != null) && (!"".equals(page_sizes))) {
/*     */       try
/*     */       {
/* 307 */         page_size = Integer.parseInt(page_sizes);
/*     */       }
/*     */       catch (Exception e) {
/* 310 */         e.printStackTrace();
/*     */       }
/*     */     }
/* 313 */     m.put("start_num", (start_page - 1) * page_size);
/*     */ 
/* 315 */     return GuestBookSubManager.getGuestBookSubListForDB(m);
/*     */   }
/*     */ 
/*     */   public static List<GuestBookClass> getGuestBookClassList(String site_id, String cat_id)
/*     */   {
/* 321 */     if ((cat_id != null) && (!"".equals(cat_id))) {
/* 322 */       return GuestBookClassManager.getGuestBookClassList(site_id, Integer.parseInt(cat_id));
/*     */     }
/* 324 */     return null;
/*     */   }
/*     */ 
/*     */   public static GuestBookBean getGuestBookObject(String id)
/*     */   {
/* 330 */     if ((!"".equals(id)) && (id != null)) {
/* 331 */       return GuestBookManager.getGuestBookBean(id);
/*     */     }
/* 333 */     return null;
/*     */   }
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/* 338 */     System.out.println(getGBSubjectCount("site_id=CMStest;cat_id=2;size=10;cur_page=1"));
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.template.velocity.data.GuestBookData
 * JD-Core Version:    0.6.2
 */