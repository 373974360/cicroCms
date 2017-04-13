/*    */ package com.cicro.analyzer_bak_20151106;
/*    */ 
/*    */ import java.util.Comparator;
/*    */ import java.util.Map.Entry;
/*    */ 
/*    */ class SorterMap$1
/*    */   implements Comparator<Map.Entry<String, Integer>>
/*    */ {
/*    */   public int compare(Map.Entry<String, Integer> obj1, Map.Entry<String, Integer> obj2)
/*    */   {
/* 19 */     return ((Integer)obj2.getValue()).intValue() - ((Integer)obj1.getValue()).intValue();
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.analyzer.SorterMap.1
 * JD-Core Version:    0.6.2
 */