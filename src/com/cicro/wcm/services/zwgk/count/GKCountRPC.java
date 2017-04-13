/*     */ package com.cicro.wcm.services.zwgk.count;
/*     */ 
/*     */ import com.cicro.wcm.bean.zwgk.count.GKCountBean;
/*     */ import com.cicro.wcm.bean.zwgk.count.GKysqCountBean;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class GKCountRPC
/*     */ {
/*     */   public static boolean CateInfoCounting(String site_id, String app_id, String date)
/*     */   {
/*  20 */     return GKCountManager.CateInfoCounting(site_id, app_id, date);
/*     */   }
/*     */ 
/*     */   public static List getGKCountList(String site_id)
/*     */   {
/*  32 */     return GKCountManager.getGKCountList(site_id);
/*     */   }
/*     */ 
/*     */   public static List<GKCountBean> getGKCountListByDate(String site_id, String startDate, String endDate)
/*     */   {
/*  44 */     return GKCountManager.getGKCountList(site_id, startDate, endDate);
/*     */   }
/*     */ 
/*     */   public static List<GKCountBean> getAllSiteGKCountList(String startDate, String endDate, String node_ids)
/*     */   {
/*  56 */     return GKCountManager.getAllSiteGKCountList(startDate, endDate, node_ids);
/*     */   }
/*     */ 
/*     */   public static List<GKCountBean> getAllSiteGKCountList(Map<String, String> mp)
/*     */   {
/*  66 */     return GKCountManager.getAllSiteGKCountList(mp);
/*     */   }
/*     */ 
/*     */   public static List<List> GKWorkLoadRank(Map<String, String> mp)
/*     */   {
/*  80 */     return GKRankManager.GKWorkLoadRank(mp);
/*     */   }
/*     */ 
/*     */   public static List<GKCountBean> GKInfoCountRank(Map<String, String> mp)
/*     */   {
/*  91 */     return GKRankManager.GKInfoCountRank(mp);
/*     */   }
/*     */ 
/*     */   public static List<GKysqCountBean> getYSQStateCnt(Map<String, String> mp)
/*     */   {
/* 102 */     return GKysqCountManager.getYSQStateCnt(mp);
/*     */   }
/*     */ 
/*     */   public static List<GKysqCountBean> getYSQStateCntByNode(Map<String, String> mp)
/*     */   {
/* 112 */     return GKysqCountManager.getYSQStateCntByNode(mp);
/*     */   }
/*     */ 
/*     */   public static List<GKysqCountBean> getYSQTypeCount(Map<String, String> mp)
/*     */   {
/* 121 */     return GKysqCountManager.getYSQTypeCount(mp);
/*     */   }
/*     */ 
/*     */   public static String gkInfoOutExcel(List listBean, List headerList)
/*     */   {
/* 131 */     return GKCountExcelOut.gkInfoOutExcel(listBean, headerList);
/*     */   }
/*     */ 
/*     */   public static String gkWorkInfoOutExcel(List listBean, List headerList)
/*     */   {
/* 141 */     return GKCountExcelOut.gkWorkInfoOutExcel(listBean, headerList);
/*     */   }
/*     */ 
/*     */   public static String ysqgkInfoOutExcel(List listBean, List headerList)
/*     */   {
/* 151 */     return GKCountExcelOut.ysqgkInfoOutExcel(listBean, headerList);
/*     */   }
/*     */ 
/*     */   public static String ysqgkTypeInfoOutExcel(List listBean, List headerList)
/*     */   {
/* 161 */     return GKCountExcelOut.ysqgkTypeInfoOutExcel(listBean, headerList);
/*     */   }
/*     */ 
/*     */   public static String gkTreeInfoOutExcel(List headerList, String site_id, String startDate, String endDate)
/*     */   {
/* 171 */     return GKCountExcelOut.gkTreeInfoOutExcel(headerList, site_id, startDate, endDate);
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.zwgk.count.GKCountRPC
 * JD-Core Version:    0.6.2
 */