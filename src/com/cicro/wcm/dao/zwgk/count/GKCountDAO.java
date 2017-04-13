/*     */ package com.cicro.wcm.dao.zwgk.count;
/*     */ 
/*     */ import com.cicro.wcm.bean.zwgk.count.GKCountBean;
/*     */ import com.cicro.wcm.db.DBManager;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class GKCountDAO
/*     */ {
/*     */   public static List getCateInfoCount(Map<String, String> mp)
/*     */   {
/*  25 */     return DBManager.queryFList("getCatInfoCount", mp);
/*     */   }
/*     */ 
/*     */   public static List getGKCount(Map<String, String> mp)
/*     */   {
/*  37 */     return DBManager.queryFList("getGK_countList", mp);
/*     */   }
/*     */ 
/*     */   public static List getAllSiteGKCount(Map<String, String> mp)
/*     */   {
/*  48 */     return DBManager.queryFList("getAllSiteGKCount", mp);
/*     */   }
/*     */ 
/*     */   public static boolean insertGKCount(GKCountBean cb)
/*     */   {
/*  58 */     return DBManager.insert("insertGK_count", cb);
/*     */   }
/*     */ 
/*     */   public static boolean updateGKCount(GKCountBean cb)
/*     */   {
/*  68 */     return DBManager.update("updateGK_count", cb);
/*     */   }
/*     */ 
/*     */   public static boolean deleteGKCount(Map<String, String> mp)
/*     */   {
/*  79 */     return DBManager.delete("deleteGK_count", mp);
/*     */   }
/*     */ 
/*     */   public static List<GKCountBean> getGkInfoCountByStatus(Map mp)
/*     */   {
/*  90 */     return DBManager.queryFList("gk_count.getGkInfoCountByStatus", mp);
/*     */   }
/*     */ 
/*     */   public static List<GKCountBean> getGkInfoCountByStatusANDNode(Map mp)
/*     */   {
/* 100 */     return DBManager.queryFList("gk_count.getGkInfoCountByStatusANDNode", mp);
/*     */   }
/*     */ 
/*     */   public static void main(String[] arg)
/*     */   {
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.dao.zwgk.count.GKCountDAO
 * JD-Core Version:    0.6.2
 */