/*    */ package com.cicro.util.quartz;
/*    */ 
/*    */ public class FormatRssCronExpression
/*    */ {
/*    */   public static String formatCronExp(String time)
/*    */   {
/*  6 */     String tmp = "";
/*  7 */     if (time.contains("m")) {
/*  8 */       tmp = time.replaceAll("m", "");
/*  9 */       return "0 0/" + tmp + " * * * ?";
/* 10 */     }if (time.contains("h")) {
/* 11 */       tmp = time.replaceAll("h", "");
/* 12 */       if (Integer.parseInt(tmp) > 23) {
/* 13 */         return "0 0 0/1 * * ?";
/*    */       }
/* 15 */       return "0 0 0/" + tmp + " * * ?";
/* 16 */     }if (time.contains("d")) {
/* 17 */       tmp = time.replaceAll("d", "");
/* 18 */       if (Integer.parseInt(tmp) > 31) {
/* 19 */         return "0 0 0 0/1 * ?";
/*    */       }
/* 21 */       return "0 0 0 0/" + tmp + " * ?";
/*    */     }
/* 23 */     return "0 0 0/1 * * ?";
/*    */   }
/*    */ 
/*    */   public static void main(String[] args)
/*    */   {
/* 28 */     System.out.println(formatCronExp("1m"));
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.util.quartz.FormatRssCronExpression
 * JD-Core Version:    0.6.2
 */