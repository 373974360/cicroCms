/*    */ package com.cicro.util;
/*    */ 
/*    */ import java.io.PrintStream;
/*    */ 
/*    */ public class StringManager
/*    */ {
/*    */   public static String replace(String strValue, String strS, String strT)
/*    */   {
/* 26 */     String strTemp1 = "";
/* 27 */     String strTemp2 = strValue;
/*    */ 
/* 29 */     String strReturn = "";
/*    */ 
/* 31 */     if (strTemp2 == null) return strValue;
/*    */ 
/* 33 */     int nPosition = strTemp2.indexOf(strS);
/* 34 */     while (nPosition != -1)
/*    */     {
/* 36 */       strTemp1 = strTemp2.substring(0, nPosition);
/* 37 */       strTemp2 = strTemp2.substring(nPosition + strS.length(), strTemp2.length());
/* 38 */       strReturn = strReturn + strTemp1 + strT;
/* 39 */       nPosition = strTemp2.indexOf(strS);
/*    */     }
/* 41 */     strReturn = strReturn + strTemp2;
/*    */ 
/* 43 */     return strReturn;
/*    */   }
/*    */ 
/*    */   public static int getLength(String text)
/*    */   {
/* 54 */     int length = 0;
/* 55 */     for (int i = 0; i < text.length(); i++) {
/* 56 */       if (new String(text.charAt(i)).getBytes().length > 1)
/* 57 */         length += 2;
/*    */       else {
/* 59 */         length++;
/*    */       }
/*    */     }
/* 62 */     return length / 2;
/*    */   }
/*    */ 
/*    */   public static void main(String[] args) {
/* 66 */     System.out.println(replace("AAAA{#subStr_@_回忆三部曲 (合订版)二最}难懂的动画片--二_@_12#}BBB", "{#subStr_@_回忆三部曲 (合订版)二最}难懂的动画片--二_@_12#}", "111(2{33}22)11"));
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.util.StringManager
 * JD-Core Version:    0.6.2
 */