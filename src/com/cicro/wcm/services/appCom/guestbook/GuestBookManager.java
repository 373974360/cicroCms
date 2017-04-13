/*     */ package com.cicro.wcm.services.appCom.guestbook;
/*     */ 
/*     */ import com.cicro.util.DateUtil;
/*     */ import com.cicro.wcm.bean.appCom.guestbook.GuestBookBean;
/*     */ import com.cicro.wcm.bean.appCom.guestbook.GuestBookCategory;
/*     */ import com.cicro.wcm.bean.appCom.guestbook.GuestBookCount;
/*     */ import com.cicro.wcm.bean.appCom.guestbook.GuestBookSub;
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.dao.PublicTableDAO;
/*     */ import com.cicro.wcm.dao.appCom.guestbook.GuestBookDAO;
/*     */ import java.io.PrintStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class GuestBookManager
/*     */ {
/*     */   public static String getGuestbookCount(Map<String, String> m)
/*     */   {
/*  25 */     return GuestBookDAO.getGuestbookCount(m);
/*     */   }
/*     */ 
/*     */   public static List<GuestBookBean> getGuestbookList(Map<String, String> m)
/*     */   {
/*  35 */     return GuestBookDAO.getGuestbookList(m);
/*     */   }
/*     */ 
/*     */   public static GuestBookBean getGuestBookBean(String id)
/*     */   {
/*  45 */     return GuestBookDAO.getGuestBookBean(id);
/*     */   }
/*     */ 
/*     */   public static boolean insertGuestBook(GuestBookBean gb)
/*     */   {
/*  55 */     gb.setId(PublicTableDAO.getIDByTableName(PublicTableDAO.GUESTBOOK__TABLE_NAME));
/*  56 */     gb.setAdd_time(DateUtil.getCurrentDateTime());
/*  57 */     return GuestBookDAO.insertGuestBook(gb);
/*     */   }
/*     */ 
/*     */   public static boolean updateGuestBook(GuestBookBean gb, SettingLogsBean stl)
/*     */   {
/*  68 */     if ((gb.getReply_status() == 1) && ("".equals(gb.getReply_time())))
/*  69 */       gb.setReply_time(DateUtil.getCurrentDateTime());
/*  70 */     return GuestBookDAO.updateGuestBook(gb, stl);
/*     */   }
/*     */ 
/*     */   public static boolean replayGuestBook(GuestBookBean gb, SettingLogsBean stl)
/*     */   {
/*  81 */     return GuestBookDAO.replayGuestBook(gb, stl);
/*     */   }
/*     */ 
/*     */   public static boolean publishGuestBook(String ids, String publish_status, SettingLogsBean stl)
/*     */   {
/*  92 */     Map m = new HashMap();
/*  93 */     m.put("ids", ids);
/*  94 */     m.put("publish_status", publish_status);
/*  95 */     return GuestBookDAO.publishGuestBook(m, stl);
/*     */   }
/*     */ 
/*     */   public static boolean deleteGuestBook(String ids, SettingLogsBean stl)
/*     */   {
/* 106 */     Map m = new HashMap();
/* 107 */     m.put("ids", ids);
/* 108 */     return GuestBookDAO.deleteGuestBook(m, stl);
/*     */   }
/*     */ 
/*     */   public static boolean clickGuestBook(String id)
/*     */   {
/* 113 */     return GuestBookDAO.clickGuestBook(id);
/*     */   }
/*     */ 
/*     */   public static List<GuestBookCount> getGuestbookStatistics(Map<String, String> m)
/*     */   {
/* 124 */     List count_list = new ArrayList();
/* 125 */     List sub_list = GuestBookSubManager.getGuestBookSubListForIDS((String)m.get("site_id"), (String)m.get("gbs_ids"));
/* 126 */     if ((sub_list != null) && (sub_list.size() > 0))
/*     */     {
/* 128 */       for (GuestBookSub gbs : sub_list)
/*     */       {
/* 130 */         GuestBookCount gbc = new GuestBookCount();
/* 131 */         gbc.setGbs_id(gbs.getGbs_id());
/* 132 */         gbc.setTitle(gbs.getTitle());
/* 133 */         Map c_m = new HashMap();
/* 134 */         c_m.put("gbs_id", gbc.getGbs_id());
/* 135 */         if (m.containsKey("start_day"))
/* 136 */           c_m.put("start_day", (String)m.get("start_day"));
/* 137 */         if (m.containsKey("end_day")) {
/* 138 */           c_m.put("end_day", (String)m.get("end_day"));
/*     */         }
/* 140 */         gbc.setCount(Integer.parseInt(getGuestbookCount(c_m)));
/*     */ 
/* 143 */         c_m.put("publish_status", "1");
/* 144 */         gbc.setPublish_count(Integer.parseInt(getGuestbookCount(c_m)));
/*     */ 
/* 147 */         c_m.put("reply_status", "1");
/* 148 */         c_m.remove("publish_status");
/* 149 */         gbc.setReply_count(Integer.parseInt(getGuestbookCount(c_m)));
/*     */ 
/* 151 */         count_list.add(gbc);
/*     */       }
/*     */     }
/* 154 */     return count_list;
/*     */   }
/*     */ 
/*     */   public static List<GuestBookCount> getGBCategoryStatistics(Map<String, String> m)
/*     */   {
/* 164 */     List count_list = new ArrayList();
/* 165 */     List cat_list = GuestBookCategoryManager.getGuestBookCategoryListForIDS((String)m.get("site_id"), (String)m.get("cat_ids"));
/* 166 */     if ((cat_list != null) && (cat_list.size() > 0))
/*     */     {
/* 168 */       for (GuestBookCategory cat : cat_list)
/*     */       {
/* 170 */         GuestBookCount gbc = new GuestBookCount();
/* 171 */         gbc.setCat_id(cat.getCat_id());
/* 172 */         gbc.setTitle(cat.getTitle());
/* 173 */         Map c_m = new HashMap();
/* 174 */         c_m.put("site_id", (String)m.get("site_id"));
/* 175 */         c_m.put("cat_id", gbc.getCat_id());
/* 176 */         if (m.containsKey("start_day"))
/* 177 */           c_m.put("start_day", (String)m.get("start_day"));
/* 178 */         if (m.containsKey("end_day")) {
/* 179 */           c_m.put("end_day", (String)m.get("end_day"));
/*     */         }
/* 181 */         gbc.setCount(Integer.parseInt(GuestBookDAO.getGBCategoryStatistics(c_m)));
/*     */ 
/* 184 */         c_m.put("publish_status", "1");
/* 185 */         gbc.setPublish_count(Integer.parseInt(GuestBookDAO.getGBCategoryStatistics(c_m)));
/*     */ 
/* 188 */         c_m.put("reply_status", "1");
/* 189 */         c_m.remove("publish_status");
/* 190 */         gbc.setReply_count(Integer.parseInt(GuestBookDAO.getGBCategoryStatistics(c_m)));
/*     */ 
/* 192 */         count_list.add(gbc);
/*     */       }
/*     */     }
/* 195 */     return count_list;
/*     */   }
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/* 200 */     Map m = new HashMap();
/* 201 */     m.put("site_id", "HIWCMdemo");
/* 202 */     m.put("cat_ids", "16,17");
/* 203 */     System.out.println(getGBCategoryStatistics(m));
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.appCom.guestbook.GuestBookManager
 * JD-Core Version:    0.6.2
 */