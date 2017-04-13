/*    */ package com.cicro.util;
/*    */ 
/*    */ import java.io.PrintStream;
/*    */ import java.text.NumberFormat;
/*    */ 
/*    */ public class CalculateNumber
/*    */ {
/*    */   public static String getRate(String strUp, String strDown)
/*    */   {
/*    */     try
/*    */     {
/* 29 */       float floatUp = Float.parseFloat(strUp);
/* 30 */       float floatDown = Float.parseFloat(strDown);
/* 31 */       int intResult = (int)(floatUp / floatDown * 10000.0D);
/*    */ 
/* 33 */       return intResult / 100.0D + "%"; } catch (Exception e) {
/*    */     }
/* 35 */     return "0.0%";
/*    */   }
/*    */ 
/*    */   public static String getRate(String strUp, String strDown, int nums)
/*    */   {
/* 47 */     float floatUp = Float.parseFloat(strUp);
/* 48 */     float floatDown = Float.parseFloat(strDown);
/*    */ 
/* 50 */     NumberFormat nf = NumberFormat.getPercentInstance();
/* 51 */     nf.setMinimumFractionDigits(nums);
/* 52 */     double tt = floatUp / floatDown;
/* 53 */     return nf.format(tt);
/*    */   }
/*    */ 
/*    */   public static void main(String[] args) {
/* 57 */     System.out.println(getRate("18", "23", 0));
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.util.CalculateNumber
 * JD-Core Version:    0.6.2
 */