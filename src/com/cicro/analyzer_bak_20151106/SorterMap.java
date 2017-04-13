/*    */ package com.cicro.analyzer_bak_20151106;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Collections;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import java.util.Map.Entry;
/*    */ 
/*    */ public class SorterMap
/*    */ {
/*    */   public static List<Entry<String, Integer>> sortMapValue(Map maps)
/*    */   {
/* 16 */     List info = new ArrayList(maps.entrySet());
/* 17 */     Collections.sort(info, new SorterMap.1());
/*    */ 
/* 22 */     return info;
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.analyzer.SorterMap
 * JD-Core Version:    0.6.2
 */