/*    */ package com.cicro.util;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ import java.util.Iterator;
/*    */ import java.util.Map;
/*    */ import java.util.Map.Entry;
/*    */ import java.util.Set;
/*    */ 
/*    */ public class MapManager
/*    */ {
/*    */   public static Map mapKeyValueToLow(Map m)
/*    */   {
/* 16 */     if ((m != null) && (m.size() > 0))
/*    */     {
/* 18 */       Map new_m = new HashMap();
/* 19 */       Iterator iter = m.entrySet().iterator();
/* 20 */       while (iter.hasNext()) {
/* 21 */         Entry entry = (Entry)iter.next();
/* 22 */         String key = (String)entry.getKey();
/* 23 */         Object val = entry.getValue();
/* 24 */         new_m.put(key.toLowerCase(), val);
/*    */       }
/* 26 */       return new_m;
/*    */     }
/*    */ 
/* 29 */     return m;
/*    */   }
/*    */ 
/*    */   public static Object getValue(Map<String, String> map, String key) {
/*    */     try {
/* 34 */       Object o = map.get(key);
/* 35 */       if (o == null) {
/* 36 */         o = map.get(key.toLowerCase());
/* 37 */         if (o == null)
/* 38 */           o = map.get(key.toUpperCase());
/*    */       }
/* 40 */       return null;
/*    */     }
/*    */     catch (Exception e)
/*    */     {
/* 47 */       e.printStackTrace();
/* 48 */     }return null;
/*    */   }
/*    */ 
/*    */   public static void main(String[] args)
/*    */   {
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.util.MapManager
 * JD-Core Version:    0.6.2
 */