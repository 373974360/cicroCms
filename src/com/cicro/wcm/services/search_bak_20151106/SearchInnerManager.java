/*    */ package com.cicro.wcm.services.search_bak_20151106;
/*    */ 
/*    */ import com.cicro.wcm.services.search.index.util.Queue;
/*    */ import java.util.Date;
/*    */ import java.util.HashMap;
/*    */ import java.util.HashSet;
/*    */ import java.util.Map;
/*    */ import java.util.Set;
/*    */ import java.util.Timer;
/*    */ 
/*    */ public class SearchInnerManager
/*    */ {
/* 24 */   private static Set infoSetAdd = new HashSet();
/* 25 */   private static Set infoSetDel = new HashSet();
/*    */ 
/* 27 */   private static Queue queue = new Queue();
/*    */ 
/*    */   static
/*    */   {
/* 31 */     Timer timer = new Timer();
/* 32 */     Date now = new Date();
/* 33 */     SearchInnerManager.SearchIndexTask task = new SearchInnerManager.SearchIndexTask();
/*    */ 
/* 35 */     timer.schedule(task, now, 1800000L);
/*    */   }
/*    */ 
/*    */   public static void infoSetAdd(String id)
/*    */   {
/* 41 */     Map map = new HashMap();
/* 42 */     map.put("id", id);
/* 43 */     map.put("flag", "1");
/* 44 */     queue.put(map);
/*    */   }
/*    */ 
/*    */   public static void infoSetDel(String id)
/*    */   {
/* 49 */     Map map = new HashMap();
/* 50 */     map.put("id", id);
/* 51 */     map.put("flag", "0");
/* 52 */     queue.put(map);
/*    */   }
/*    */ 
/*    */   public static void main(String[] arr)
/*    */   {
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.search.SearchInnerManager
 * JD-Core Version:    0.6.2
 */