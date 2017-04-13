/*     */ package com.cicro.util;
/*     */ 
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import java.net.URLDecoder;
/*     */ import java.net.URLEncoder;
/*     */ 
/*     */ public class Encode
/*     */ {
/*     */   public static String utf8ToSystem(String str)
/*     */   {
/*  23 */     return encode(str, "UTF-8", System.getProperty("file.encoding"));
/*     */   }
/*     */ 
/*     */   public static String systemToUtf8(String str)
/*     */   {
/*  32 */     return encode(str, System.getProperty("file.encoding"), "UTF-8");
/*     */   }
/*     */ 
/*     */   public static String gbkToSystem(String str)
/*     */   {
/*  41 */     return encode(str, "GBK", System.getProperty("file.encoding"));
/*     */   }
/*     */ 
/*     */   public static String systemToGbk(String str)
/*     */   {
/*  50 */     return encode(str, System.getProperty("file.encoding"), "GBK");
/*     */   }
/*     */ 
/*     */   public static String iso_8859_1ToSystem(String str)
/*     */   {
/*  59 */     return encode(str, "ISO_8859_1", System.getProperty("file.encoding"));
/*     */   }
/*     */ 
/*     */   public static String systemToIso_8859_1(String str)
/*     */   {
/*  68 */     return encode(str, System.getProperty("file.encoding"), "ISO_8859_1");
/*     */   }
/*     */ 
/*     */   public static String iso_8859_1ToGbk(String str)
/*     */   {
/*  77 */     return encode(str, "ISO_8859_1", "GBK");
/*     */   }
/*     */ 
/*     */   public static String gbkToIso_8859_1(String str)
/*     */   {
/*  86 */     return encode(str, "GBK", "ISO_8859_1");
/*     */   }
/*     */ 
/*     */   public static String utf8ToGbk(String str)
/*     */   {
/*  95 */     return encode(str, "UTF-8", "GBK");
/*     */   }
/*     */ 
/*     */   public static String gbkToUtf8(String str)
/*     */   {
/* 104 */     return encode(str, "GBK", "UTF-8");
/*     */   }
/*     */ 
/*     */   public static String utf8ToIso_8859_1(String str)
/*     */   {
/* 113 */     return encode(str, "UTF-8", "ISO_8859_1");
/*     */   }
/*     */ 
/*     */   public static String iso_8859_1ToUtf8(String str)
/*     */   {
/* 122 */     return encode(str, "ISO_8859_1", "UTF-8");
/*     */   }
/*     */ 
/*     */   public static String urlEncode(String str)
/*     */   {
/* 132 */     str = urlEncode(str, System.getProperty("file.encoding"));
/* 133 */     return str;
/*     */   }
/*     */ 
/*     */   public static String urlEncode(String str, String encoding)
/*     */   {
/*     */     try
/*     */     {
/* 145 */       str = URLEncoder.encode(str, encoding);
/*     */     }
/*     */     catch (UnsupportedEncodingException ex) {
/* 148 */       ex.printStackTrace(System.out);
/* 149 */       return null;
/*     */     }
/* 151 */     return str;
/*     */   }
/*     */ 
/*     */   public static String urlDecode(String str)
/*     */   {
/* 161 */     str = urlDecode(str, System.getProperty("file.encoding"));
/* 162 */     return str;
/*     */   }
/*     */ 
/*     */   public static String urlDecode(String str, String encoding)
/*     */   {
/*     */     try
/*     */     {
/* 174 */       str = URLDecoder.decode(str, encoding);
/*     */     }
/*     */     catch (UnsupportedEncodingException ex) {
/* 177 */       ex.printStackTrace(System.out);
/* 178 */       return null;
/*     */     }
/* 180 */     return str;
/*     */   }
/*     */ 
/*     */   public static String urlEncodeForLinux(String str, String encoding)
/*     */   {
/* 191 */     str = gbkToSystem(str);
/* 192 */     str = urlEncode(str, encoding);
/* 193 */     str = str.replaceAll("\\+", "%20");
/*     */ 
/* 195 */     return str;
/*     */   }
/*     */ 
/*     */   public static String encode(String str, String encodeStr, String decodeStr)
/*     */   {
/*     */     try
/*     */     {
/* 207 */       str = new String(str.getBytes(encodeStr), decodeStr);
/*     */     }
/*     */     catch (UnsupportedEncodingException ex) {
/* 210 */       ex.printStackTrace(System.out);
/* 211 */       return null;
/*     */     }
/*     */ 
/* 214 */     return str;
/*     */   }
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/* 220 */     String s = "本地+ 本地";
/* 221 */     s = urlEncode(s, "UTF-8");
/*     */ 
/* 223 */     s = "%2Froot%2Fimage%2F+plmm";
/*     */ 
/* 225 */     s = urlDecode(s, "UTF-8");
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.util.Encode
 * JD-Core Version:    0.6.2
 */