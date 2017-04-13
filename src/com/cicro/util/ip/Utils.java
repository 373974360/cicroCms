/*     */ package com.cicro.util.ip;
/*     */ 
/*     */ import java.io.PrintStream;
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import java.util.StringTokenizer;
/*     */ 
/*     */ public class Utils
/*     */ {
/*     */   public static byte[] getIpByteArrayFromString(String ip)
/*     */   {
/*  17 */     byte[] ret = new byte[4];
/*  18 */     StringTokenizer st = new StringTokenizer(ip, ".");
/*     */     try {
/*  20 */       ret[0] = ((byte)(Integer.parseInt(st.nextToken()) & 0xFF));
/*  21 */       ret[1] = ((byte)(Integer.parseInt(st.nextToken()) & 0xFF));
/*  22 */       ret[2] = ((byte)(Integer.parseInt(st.nextToken()) & 0xFF));
/*  23 */       ret[3] = ((byte)(Integer.parseInt(st.nextToken()) & 0xFF));
/*     */     } catch (Exception e) {
/*  25 */       System.out.println(e.getMessage());
/*     */     }
/*  27 */     return ret;
/*     */   }
/*     */ 
/*     */   public static String getString(String s, String srcEncoding, String destEncoding)
/*     */   {
/*     */     try
/*     */     {
/*  40 */       return new String(s.getBytes(srcEncoding), destEncoding); } catch (UnsupportedEncodingException e) {
/*     */     }
/*  42 */     return s;
/*     */   }
/*     */ 
/*     */   public static String getString(byte[] b, String encoding)
/*     */   {
/*     */     try
/*     */     {
/*  54 */       return new String(b, encoding); } catch (UnsupportedEncodingException e) {
/*     */     }
/*  56 */     return new String(b);
/*     */   }
/*     */ 
/*     */   public static String getString(byte[] b, int offset, int len, String encoding)
/*     */   {
/*     */     try
/*     */     {
/*  70 */       return new String(b, offset, len, encoding); } catch (UnsupportedEncodingException e) {
/*     */     }
/*  72 */     return new String(b, offset, len);
/*     */   }
/*     */ 
/*     */   public static String getIpStringFromBytes(byte[] ip)
/*     */   {
/*  81 */     StringBuffer sb = new StringBuffer();
/*  82 */     sb.append(ip[0] & 0xFF);
/*  83 */     sb.append('.');
/*  84 */     sb.append(ip[1] & 0xFF);
/*  85 */     sb.append('.');
/*  86 */     sb.append(ip[2] & 0xFF);
/*  87 */     sb.append('.');
/*  88 */     sb.append(ip[3] & 0xFF);
/*  89 */     return sb.toString();
/*     */   }
/*     */ 
/*     */   public static String getCountry(String ip)
/*     */   {
/*  99 */     String address = "未知";
/*     */     try {
/* 101 */       IPSeeker seeker = IPSeeker.getInstance();
/* 102 */       String country = seeker.getCountry(ip);
/* 103 */       address = country;
/*     */     } catch (Exception e) {
/* 105 */       System.out.println("com.cicro.util.ip.util.getAddress:" + e.toString());
/*     */     }
/*     */ 
/* 108 */     return address.trim();
/*     */   }
/*     */ 
/*     */   public static String getArea(String ip)
/*     */   {
/* 119 */     String address = "未知";
/*     */     try {
/* 121 */       IPSeeker seeker = IPSeeker.getInstance();
/* 122 */       String area = seeker.getArea(ip);
/* 123 */       address = area;
/*     */     } catch (Exception e) {
/* 125 */       System.out.println("com.cicro.util.ip.util.getAddress:" + e.toString());
/*     */     }
/*     */ 
/* 128 */     return address.trim();
/*     */   }
/*     */ 
/*     */   public static String getAddress(String ip)
/*     */   {
/* 138 */     String address = "未知";
/*     */     try {
/* 140 */       IPSeeker seeker = IPSeeker.getInstance();
/* 141 */       String country = seeker.getCountry(ip);
/* 142 */       String area = seeker.getArea(ip);
/* 143 */       address = country + " " + area;
/*     */     } catch (Exception e) {
/* 145 */       System.out.println("com.cicro.util.ip.util.getAddress:" + e.toString());
/*     */     }
/*     */ 
/* 148 */     return address.trim();
/*     */   }
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/*     */     try
/*     */     {
/* 155 */       System.out.println(getAddress("219.140.56.171"));
/* 156 */       System.out.println(getAddress("61.129.90.90"));
/* 157 */       System.out.println(getAddress("58.47.143.8"));
/* 158 */       System.out.println(getAddress("123.120.1.119"));
/* 159 */       System.out.println(getAddress("222.90.66.54"));
/*     */     } catch (Exception e) {
/* 161 */       System.out.println(e);
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.util.ip.Utils
 * JD-Core Version:    0.6.2
 */