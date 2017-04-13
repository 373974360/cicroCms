/*     */ package com.cicro.wcm.services.survey;
/*     */ 
/*     */ import com.cicro.util.CalculateNumber;
/*     */ import com.cicro.wcm.bean.survey.StatisticsBean;
/*     */ import com.cicro.wcm.dao.survey.StatisticsDAO;
/*     */ import java.io.PrintStream;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class StatisticsService
/*     */ {
/*     */   public static String getStatisticsCount(String con)
/*     */   {
/*  49 */     return StatisticsDAO.getStatisticsCount(con);
/*     */   }
/*     */ 
/*     */   public static List getStatisticsList(String con, int start_num, int page_size)
/*     */   {
/*  73 */     Map m = new HashMap();
/*     */ 
/*  75 */     m.put("start_num", Integer.valueOf(start_num));
/*     */ 
/*  77 */     m.put("page_size", Integer.valueOf(page_size));
/*     */ 
/*  79 */     m.put("con", con);
/*     */ 
/*  81 */     return StatisticsDAO.getStatisticsList(m);
/*     */   }
/*     */ 
/*     */   public static Map getStatisticsDataBySurvey(String s_id, String con)
/*     */   {
/* 161 */     HashMap m = new HashMap();
/*     */ 
/* 163 */     String answer_count = StatisticsDAO.getStatisticsCountBySurvey(s_id);
/*     */ 
/* 165 */     m.put("answer_count", answer_count);
/*     */     try
/*     */     {
/* 171 */       HashMap con_m = new HashMap();
/*     */ 
/* 173 */       con_m.put("s_id", s_id);
/*     */ 
/* 175 */       if ((con != null) && (!"".equals(con)))
/*     */       {
/* 177 */         con_m.put("con", con);
/*     */       }
/* 179 */       List sL = StatisticsDAO.getStatisticsDataBySurvey(con_m);
/*     */ 
/* 181 */       if ((sL != null) && (sL.size() > 0))
/*     */       {
/* 186 */         Map map = new HashMap();
/* 187 */         for (int i = 0; i < sL.size(); i++) {
/* 188 */           StatisticsBean sb = (StatisticsBean)sL.get(i);
/* 189 */           String it_id = sb.getItem_id();
/* 190 */           int count = sb.getCounts();
/* 191 */           if (map.containsKey(it_id))
/* 192 */             map.put(it_id, Integer.valueOf(count + ((Integer)map.get(it_id)).intValue()));
/*     */           else {
/* 194 */             map.put(it_id, Integer.valueOf(count));
/*     */           }
/*     */ 
/*     */         }
/*     */ 
/* 199 */         for (int i = 0; i < sL.size(); i++)
/*     */         {
/* 204 */           String it_id = ((StatisticsBean)sL.get(i)).getItem_id();
/* 205 */           ((StatisticsBean)sL.get(i)).setProportion(CalculateNumber.getRate(((StatisticsBean)sL.get(i)).getCounts(), map.get(it_id), 2));
/*     */ 
/* 208 */           StatisticsBean sb = (StatisticsBean)sL.get(i);
/*     */ 
/* 210 */           m.put(sb.getItem_id() + "_" + sb.getItem_num(), sb);
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 224 */       e.printStackTrace();
/*     */     }
/*     */ 
/* 228 */     return m;
/*     */   }
/*     */ 
/*     */   public static String getVoteCountBySurveyItem(String s_id, String item_id)
/*     */   {
/* 248 */     HashMap m = new HashMap();
/*     */ 
/* 250 */     m.put("s_id", s_id);
/*     */ 
/* 252 */     m.put("item_id", item_id);
/*     */ 
/* 254 */     return StatisticsDAO.getVoteCountBySurveyItem(m);
/*     */   }
/*     */ 
/*     */   public static Map getVoteTotalBySurveyItem(String s_id, String item_id)
/*     */   {
/* 324 */     HashMap returnM = new HashMap();
/*     */     try
/*     */     {
/* 327 */       HashMap m = new HashMap();
/* 328 */       m.put("s_id", s_id);
/* 329 */       m.put("item_id", item_id);
/*     */ 
/* 331 */       List sL = StatisticsDAO.getVoteTotalBySurveyItem(m);
/* 332 */       if ((sL != null) && (sL.size() > 0))
/*     */       {
/* 335 */         Map map = new HashMap();
/* 336 */         for (int i = 0; i < sL.size(); i++) {
/* 337 */           StatisticsBean sb = (StatisticsBean)sL.get(i);
/* 338 */           String it_id = sb.getItem_id();
/* 339 */           int count = sb.getCounts();
/* 340 */           if (map.containsKey(it_id))
/* 341 */             map.put(it_id, Integer.valueOf(count + ((Integer)map.get(it_id)).intValue()));
/*     */           else {
/* 343 */             map.put(it_id, Integer.valueOf(count));
/*     */           }
/*     */         }
/*     */ 
/* 347 */         for (int i = 0; i < sL.size(); i++)
/*     */         {
/* 349 */           String it_id = ((StatisticsBean)sL.get(i)).getItem_id();
/* 350 */           ((StatisticsBean)sL.get(i)).setProportion(CalculateNumber.getRate(((StatisticsBean)sL.get(i)).getCounts(), map.get(it_id), 2));
/*     */ 
/* 352 */           StatisticsBean sb = (StatisticsBean)sL.get(i);
/* 353 */           returnM.put(sb.getItem_id() + "_" + sb.getItem_num(), sb);
/*     */         }
/*     */       }
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 359 */       e.printStackTrace();
/*     */     }
/* 361 */     return returnM;
/*     */   }
/*     */ 
/*     */   public static String getItemTextCount(String s_id, String item_id, String item_value, String is_text, String search_con)
/*     */   {
/* 380 */     String con = "";
/*     */ 
/* 382 */     Map m = new HashMap();
/*     */ 
/* 384 */     m.put("s_id", s_id);
/*     */ 
/* 386 */     m.put("item_id", item_id);
/*     */ 
/* 388 */     m.put("item_value", item_value);
/*     */ 
/* 390 */     if ("true".equals(is_text))
/*     */     {
/* 392 */       con = "and item_text is not null";
/*     */     }
/* 394 */     m.put("con", con);
/*     */ 
/* 396 */     if ((search_con != null) && (!"".equals(search_con)))
/*     */     {
/* 400 */       m.put("search_con", search_con);
/*     */     }
/*     */ 
/* 404 */     return StatisticsDAO.getItemTextCount(m);
/*     */   }
/*     */ 
/*     */   public static List getItemTextList(String s_id, String item_id, String item_value, String is_text, int start_num, int page_size, String search_con)
/*     */   {
/* 424 */     String con = "";
/*     */ 
/* 426 */     Map m = new HashMap();
/*     */ 
/* 428 */     m.put("s_id", s_id);
/*     */ 
/* 430 */     m.put("item_id", item_id);
/*     */ 
/* 432 */     m.put("item_value", item_value);
/*     */ 
/* 434 */     if ("true".equals(is_text))
/*     */     {
/* 436 */       con = "and item_text is not null";
/*     */     }
/*     */ 
/* 440 */     m.put("con", con);
/*     */ 
/* 442 */     m.put("start_num", Integer.valueOf(start_num));
/*     */ 
/* 444 */     m.put("page_size", Integer.valueOf(page_size));
/*     */ 
/* 446 */     if ((search_con != null) && (!"".equals(search_con)))
/*     */     {
/* 450 */       m.put("search_con", search_con);
/*     */ 
/* 452 */       return StatisticsDAO.getItemTextList2(m);
/*     */     }
/*     */ 
/* 458 */     return StatisticsDAO.getItemTextList(m);
/*     */   }
/*     */ 
/*     */   public static List getAnswerItemDetail(String answer_id)
/*     */   {
/* 478 */     return StatisticsDAO.getAnswerItemDetail(answer_id);
/*     */   }
/*     */ 
/*     */   public static String getAnswerListCount(String s_id, String search_con)
/*     */   {
/* 500 */     Map m = new HashMap();
/*     */ 
/* 502 */     m.put("s_id", s_id);
/*     */ 
/* 504 */     if ((search_con != null) && (!"".equals(search_con)))
/*     */     {
/* 508 */       m.put("search_con", search_con);
/*     */     }
/*     */ 
/* 512 */     return StatisticsDAO.getAnswerListCount(m);
/*     */   }
/*     */ 
/*     */   public static List getAnswerList(String s_id, String search_con, int start_num, int page_size)
/*     */   {
/* 538 */     Map m = new HashMap();
/*     */ 
/* 540 */     m.put("start_num", Integer.valueOf(start_num));
/*     */ 
/* 542 */     m.put("page_size", Integer.valueOf(page_size));
/*     */ 
/* 544 */     m.put("s_id", s_id);
/*     */ 
/* 546 */     if ((search_con != null) && (!"".equals(search_con)))
/*     */     {
/* 550 */       m.put("search_con", search_con);
/*     */ 
/* 552 */       return StatisticsDAO.getAnswerList2(m);
/*     */     }
/*     */ 
/* 558 */     return StatisticsDAO.getAnswerList(m);
/*     */   }
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/* 568 */     HashMap con_m = new HashMap();
/*     */ 
/* 570 */     con_m.put("s_id", "36938365-9eb2-4b74-bcf1-09469873f4ee");
/*     */ 
/* 572 */     con_m.put("con", "and  (item_id = 'item_2'  and item_value = '1'  and item_value = '2' )  and  (item_id = 'item_9' and  item_value like '%11%')");
/*     */ 
/* 576 */     Map m = new HashMap();
/*     */ 
/* 578 */     m = getStatisticsDataBySurvey("36938365-9eb2-4b74-bcf1-09469873f4ee", "");
/*     */ 
/* 582 */     System.out.println(m.get(""));
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.survey.StatisticsService
 * JD-Core Version:    0.6.2
 */