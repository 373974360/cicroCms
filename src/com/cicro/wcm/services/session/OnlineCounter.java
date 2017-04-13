/*    */ package com.cicro.wcm.services.session;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class OnlineCounter
/*    */ {
/*  7 */   private static Map<String, Integer> mapAll = new HashMap();
/*    */ 
/*    */   public static long getOnline(String site_id) {
/* 10 */     Integer count = Integer.valueOf(0);
/*    */     try {
/* 12 */       count = (Integer)mapAll.get(site_id);
/*    */ 
/* 14 */       if (count == null) {
/* 15 */         count = Integer.valueOf(0);
/* 16 */       } else if (count.intValue() < 0) {
/* 17 */         count = Integer.valueOf(0);
/* 18 */         mapAll.put("site_id", Integer.valueOf(2));
/*    */       }
/*    */     } catch (Exception e) {
/* 21 */       e.printStackTrace();
/*    */     } finally {
/* 23 */       return count.intValue();
/*    */     }
/*    */   }
/*    */ 
/* 27 */   public static void raise(String site_id) { Integer count = Integer.valueOf(0);
/*    */     try {
/* 29 */       count = (Integer)mapAll.get(site_id);
/*    */ 
/* 31 */       if (count == null) {
/* 32 */         count = Integer.valueOf(0);
/*    */       }
/*    */ 
/* 35 */       mapAll.put(site_id, count = Integer.valueOf(count.intValue() + 1));
/*    */     }
/*    */     catch (Exception e) {
/* 38 */       e.printStackTrace();
/*    */     } }
/*    */ 
/*    */   public static void reduce(String site_id)
/*    */   {
/* 43 */     Integer count = Integer.valueOf(0);
/*    */     try {
/* 45 */       count = (Integer)mapAll.get(site_id);
/* 46 */       if (count == null) {
/* 47 */         count = Integer.valueOf(0);
/* 48 */         mapAll.put(site_id, count);
/*    */       } else {
/* 50 */         mapAll.put(site_id, count = Integer.valueOf(count.intValue() - 1));
/*    */       }
/*    */     } catch (Exception e) {
/* 53 */       e.printStackTrace();
/*    */     }
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.session.OnlineCounter
 * JD-Core Version:    0.6.2
 */