/*     */ package com.cicro.wcm.services.appeal.count;
/*     */ 
/*     */ import com.cicro.util.io.FileOperation;
/*     */ import java.io.File;
/*     */ import java.io.PrintStream;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Calendar;
/*     */ import java.util.Date;
/*     */ import java.util.GregorianCalendar;
/*     */ import java.util.List;
/*     */ 
/*     */ public class CountUtil
/*     */ {
/*     */   public static String getYesterDayDate()
/*     */   {
/*  25 */     String str = "";
/*     */     try {
/*  27 */       Calendar rightNow = Calendar.getInstance();
/*  28 */       GregorianCalendar gc1 = new GregorianCalendar(rightNow.get(1), rightNow.get(2), 
/*  29 */         rightNow.get(5));
/*  30 */       gc1.add(5, -1);
/*  31 */       Date d1 = gc1.getTime();
/*  32 */       SimpleDateFormat df = new SimpleDateFormat("yyyy_MM_dd");
/*  33 */       String dd = df.format(d1);
/*  34 */       return dd;
/*     */     }
/*     */     catch (Exception e) {
/*  37 */       e.printStackTrace();
/*  38 */     }return str;
/*     */   }
/*     */ 
/*     */   public static String getNowDayDate()
/*     */   {
/*  48 */     String str = "";
/*     */     try {
/*  50 */       Calendar rightNow = Calendar.getInstance();
/*  51 */       GregorianCalendar gc1 = new GregorianCalendar(rightNow.get(1), rightNow.get(2), 
/*  52 */         rightNow.get(5));
/*  53 */       Date d1 = gc1.getTime();
/*  54 */       SimpleDateFormat df = new SimpleDateFormat("yyyy_MM_dd");
/*  55 */       String dd = df.format(d1);
/*  56 */       return dd;
/*     */     }
/*     */     catch (Exception e) {
/*  59 */       e.printStackTrace();
/*  60 */     }return str;
/*     */   }
/*     */ 
/*     */   public static String getNowDayDateTime()
/*     */   {
/*  69 */     String str = "";
/*     */     try {
/*  71 */       Date d1 = new Date();
/*  72 */       SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
/*  73 */       String dd = df.format(d1);
/*  74 */       return dd;
/*     */     }
/*     */     catch (Exception e) {
/*  77 */       e.printStackTrace();
/*  78 */     }return str;
/*     */   }
/*     */ 
/*     */   public static String getEnglish(int count)
/*     */   {
/*  88 */     StringBuffer sb = new StringBuffer();
/*     */     try
/*     */     {
/*  92 */       char[] allChar = new char[26];
/*  93 */       for (int i = 97; i < 123; i++) {
/*  94 */         allChar[(i - 97)] = ((char)i);
/*     */       }
/*     */ 
/*  97 */       List contentList = new ArrayList();
/*  98 */       while (count > 0)
/*     */       {
/* 100 */         double numDouble = Math.random() * 26.0D;
/*     */ 
/* 102 */         int position = (int)numDouble / 1;
/*     */ 
/* 104 */         if (!contentList.contains(String.valueOf(allChar[position])))
/*     */         {
/* 107 */           contentList.add(String.valueOf(allChar[position]));
/* 108 */           count--;
/*     */         }
/*     */       }
/* 111 */       for (int i = 0; i < contentList.size(); i++)
/*     */       {
/* 113 */         sb.append((String)contentList.get(i));
/*     */       }
/*     */ 
/* 116 */       return sb.toString();
/*     */     }
/*     */     catch (Exception e) {
/* 119 */       e.printStackTrace();
/* 120 */     }return sb.toString();
/*     */   }
/*     */ 
/*     */   public static void deleteFile(String path)
/*     */   {
/*     */     try
/*     */     {
/* 132 */       File fileRoot = new File(path);
/* 133 */       if (fileRoot.exists()) {
/* 134 */         File[] fileAll = fileRoot.listFiles();
/* 135 */         for (File file : fileAll) {
/* 136 */           if ((file.isDirectory()) && 
/* 137 */             (file.getName().startsWith("2"))) {
/* 138 */             String nowDate = getNowDayDate();
/* 139 */             if (!file.getName().equals(nowDate))
/* 140 */               FileOperation.deleteDir(file.getPath());
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 147 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public static String getTimeS(String s)
/*     */   {
/* 154 */     String time = "";
/*     */     try {
/* 156 */       return s.trim() + " 00:00:01";
/*     */     }
/*     */     catch (Exception e) {
/* 159 */       e.printStackTrace();
/* 160 */     }return time;
/*     */   }
/*     */ 
/*     */   public static String getTimeE(String s)
/*     */   {
/* 166 */     String time = "";
/*     */     try {
/* 168 */       return s.trim() + " 59:59:59";
/*     */     }
/*     */     catch (Exception e) {
/* 171 */       e.printStackTrace();
/* 172 */     }return time;
/*     */   }
/*     */ 
/*     */   public static void main(String[] str)
/*     */   {
/* 177 */     System.out.println(getNowDayDate());
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.appeal.count.CountUtil
 * JD-Core Version:    0.6.2
 */