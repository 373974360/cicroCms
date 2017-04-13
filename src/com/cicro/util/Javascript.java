/*     */ package com.cicro.util;
/*     */ 
/*     */ public class Javascript
/*     */ {
/*     */   public static String alert(String msg)
/*     */   {
/*  18 */     StringBuffer sb = new StringBuffer();
/*  19 */     sb.append("<script language='javascript'>");
/*  20 */     sb.append("alert('" + msg + "');");
/*  21 */     sb.append("</script>");
/*     */ 
/*  23 */     return sb.toString();
/*     */   }
/*     */ 
/*     */   public static String location(String url)
/*     */   {
/*  32 */     StringBuffer sb = new StringBuffer();
/*  33 */     sb.append("<script language='javascript'>");
/*  34 */     sb.append("location='" + url + "';");
/*  35 */     sb.append("</script>");
/*     */ 
/*  37 */     return sb.toString();
/*     */   }
/*     */ 
/*     */   public static String location(String url, String obj)
/*     */   {
/*  47 */     StringBuffer sb = new StringBuffer();
/*  48 */     sb.append("<script language='javascript'>");
/*  49 */     sb.append(obj + ".location='" + url + "';");
/*  50 */     sb.append("</script>");
/*     */ 
/*  52 */     return sb.toString();
/*     */   }
/*     */ 
/*     */   public static String reload()
/*     */   {
/*  60 */     StringBuffer sb = new StringBuffer();
/*  61 */     sb.append("<script language='javascript'>");
/*  62 */     sb.append("location.reload();");
/*  63 */     sb.append("</script>");
/*     */ 
/*  65 */     return sb.toString();
/*     */   }
/*     */ 
/*     */   public static String reload(String obj)
/*     */   {
/*  74 */     StringBuffer sb = new StringBuffer();
/*  75 */     sb.append("<script language='javascript'>");
/*  76 */     sb.append(obj + ".location.reload();");
/*  77 */     sb.append("</script>");
/*     */ 
/*  79 */     return sb.toString();
/*     */   }
/*     */ 
/*     */   public static String history(int num)
/*     */   {
/*  88 */     StringBuffer sb = new StringBuffer();
/*  89 */     sb.append("<script language='javascript'>");
/*  90 */     sb.append("history.go(" + num + ");");
/*  91 */     sb.append("</script>");
/*     */ 
/*  93 */     return sb.toString();
/*     */   }
/*     */ 
/*     */   public static String close()
/*     */   {
/* 101 */     StringBuffer sb = new StringBuffer();
/* 102 */     sb.append("<script language='javascript'>");
/* 103 */     sb.append("window.close();");
/* 104 */     sb.append("</script>");
/*     */ 
/* 106 */     return sb.toString();
/*     */   }
/*     */ 
/*     */   public static String close(String obj)
/*     */   {
/* 115 */     StringBuffer sb = new StringBuffer();
/* 116 */     sb.append("<script language='javascript'>");
/* 117 */     sb.append(obj + ".close();");
/* 118 */     sb.append("</script>");
/*     */ 
/* 120 */     return sb.toString();
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.util.Javascript
 * JD-Core Version:    0.6.2
 */