/*    */ package com.cicro.wcm.services.search_bak_20151106.search.util;
/*    */ 
/*    */ import java.io.PrintStream;
/*    */ import java.math.BigDecimal;
/*    */ 
/*    */ public class Arith
/*    */ {
/*    */   private static final int DEF_DIV_SCALE = 10;
/*    */ 
/*    */   public static void main(String[] arr)
/*    */   {
/* 10 */     double a = 0.111D;
/* 11 */     double b = 111.11D;
/* 12 */     System.out.println(add(Double.valueOf(a), Double.valueOf(b)));
/*    */   }
/*    */ 
/*    */   public static Double add(Double v1, Double v2)
/*    */   {
/* 23 */     BigDecimal b1 = new BigDecimal(v1.toString());
/* 24 */     BigDecimal b2 = new BigDecimal(v2.toString());
/* 25 */     return new Double(b1.add(b2).doubleValue());
/*    */   }
/*    */ 
/*    */   public static Double sub(Double v1, Double v2)
/*    */   {
/* 36 */     BigDecimal b1 = new BigDecimal(v1.toString());
/* 37 */     BigDecimal b2 = new BigDecimal(v2.toString());
/* 38 */     return new Double(b1.subtract(b2).doubleValue());
/*    */   }
/*    */ 
/*    */   public static Double mul(Double v1, Double v2)
/*    */   {
/* 49 */     BigDecimal b1 = new BigDecimal(v1.toString());
/* 50 */     BigDecimal b2 = new BigDecimal(v2.toString());
/* 51 */     return new Double(b1.multiply(b2).doubleValue());
/*    */   }
/*    */ 
/*    */   public static Double div(Double v1, Double v2)
/*    */   {
/* 62 */     BigDecimal b1 = new BigDecimal(v1.toString());
/* 63 */     BigDecimal b2 = new BigDecimal(v2.toString());
/* 64 */     return new Double(b1.divide(b2, 10, 4)
/* 65 */       .doubleValue());
/*    */   }
/*    */ 
/*    */   public static Double div(Double v1, Double v2, int scale)
/*    */   {
/* 77 */     if (scale < 0) {
/* 78 */       throw new IllegalArgumentException(
/* 79 */         "The scale must be a positive integer or zero");
/*    */     }
/* 81 */     BigDecimal b1 = new BigDecimal(v1.toString());
/* 82 */     BigDecimal b2 = new BigDecimal(v2.toString());
/* 83 */     return new Double(b1.divide(b2, scale, 4).doubleValue());
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.search.search.util.Arith
 * JD-Core Version:    0.6.2
 */