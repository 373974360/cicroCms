/*     */ package com.cicro.wcm.services.search_bak_20151106.index.util;
/*     */ 
/*     */ import com.cicro.util.io.FileOperation;
/*     */ import com.cicro.wcm.services.search.SearchForInterface;
/*     */ import com.cicro.wcm.services.search.index.IndexManager;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.io.PrintStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class IndexUtil
/*     */ {
/*  17 */   static String indexFile = SearchForInterface.getIndexPathRoot() + File.separator + "site.txt";
/*  18 */   static List listSite = new ArrayList();
/*     */ 
/*     */   static {
/*  21 */     reloadAll();
/*     */   }
/*     */ 
/*     */   public static void reloadAll()
/*     */   {
/*     */     try {
/*  27 */       if (new File(indexFile).exists()) {
/*  28 */         String strSite = FileOperation.readFileToString(new File(indexFile));
/*  29 */         String[] site = strSite.split(",");
/*  30 */         List arrayList = Arrays.asList(site);
/*  31 */         listSite = new ArrayList(arrayList);
/*     */       }
/*     */     } catch (IOException e) {
/*  34 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void clearList()
/*     */   {
/*  40 */     listSite.clear();
/*     */   }
/*     */ 
/*     */   public static synchronized void writeSiteToFile()
/*     */   {
/*     */     try {
/*  46 */       StringBuffer sb = new StringBuffer();
/*  47 */       List siteList = SearchForInterface.getSiteList();
/*  48 */       for (int i = 0; i < siteList.size(); i++) {
/*  49 */         Map map = (Map)siteList.get(i);
/*  50 */         String site_id = (String)map.get("site_id");
/*  51 */         if (i == siteList.size() - 1)
/*  52 */           sb.append(site_id);
/*     */         else {
/*  54 */           sb.append(site_id + ",");
/*     */         }
/*     */       }
/*     */ 
/*  58 */       System.out.println("sb.toString()===" + sb.toString());
/*  59 */       if (IndexManager.isIndexDirExists()) {
/*  60 */         FileOperation.writeStringToFile(new File(indexFile), sb.toString(), false);
/*     */       }
/*  62 */       reloadAll();
/*     */     } catch (Exception e) {
/*  64 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public static synchronized void delelteSiteToFile(String site_id)
/*     */   {
/*     */     try {
/*  71 */       if (listSite.contains(site_id)) {
/*  72 */         listSite.remove(site_id);
/*  73 */         StringBuffer sb = new StringBuffer();
/*  74 */         String str = readListString();
/*  75 */         sb.append(str);
/*  76 */         if (IndexManager.isIndexDirExists()) {
/*  77 */           FileOperation.writeStringToFile(new File(indexFile), sb.toString(), false);
/*     */         }
/*     */       }
/*  80 */       reloadAll();
/*     */     } catch (Exception e) {
/*  82 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public static synchronized void addSiteToFile(String site_id)
/*     */   {
/*     */     try {
/*  89 */       if (!listSite.contains(site_id)) {
/*  90 */         StringBuffer sb = new StringBuffer();
/*  91 */         String str = readListString();
/*  92 */         sb.append(str);
/*  93 */         if (str.equals(""))
/*  94 */           sb.append(site_id);
/*     */         else {
/*  96 */           sb.append("," + site_id);
/*     */         }
/*  98 */         if (IndexManager.isIndexDirExists()) {
/*  99 */           FileOperation.writeStringToFile(new File(indexFile), sb.toString(), false);
/*     */         }
/*     */       }
/* 102 */       reloadAll();
/*     */     } catch (Exception e) {
/* 104 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public static boolean isHasIndex(String site_id)
/*     */   {
/* 111 */     if (!IndexManager.isIndexDirExists()) {
/* 112 */       return false;
/*     */     }
/* 114 */     if (listSite.contains(site_id)) {
/* 115 */       return true;
/*     */     }
/* 117 */     return false;
/*     */   }
/*     */ 
/*     */   public static String readListString()
/*     */   {
/* 125 */     StringBuffer sb = new StringBuffer();
/* 126 */     if (listSite.size() == 0) {
/* 127 */       return "";
/*     */     }
/* 129 */     for (int i = 0; i < listSite.size(); i++) {
/* 130 */       if (i == listSite.size() - 1)
/* 131 */         sb.append((String)listSite.get(i));
/*     */       else {
/* 133 */         sb.append((String)listSite.get(i) + ",");
/*     */       }
/*     */     }
/* 136 */     return sb.toString();
/*     */   }
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/* 144 */     String[] strArray = new String[6];
/* 145 */     for (int i = 0; i < 4; ) {
/* 146 */       strArray[i] = String.valueOf(i++);
/*     */     }
/* 148 */     List testList = Arrays.asList(strArray);
/* 149 */     testList.add(String.valueOf(10));
/* 150 */     testList.add(String.valueOf(11));
/* 151 */     System.out.println(testList);
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.search.index.util.IndexUtil
 * JD-Core Version:    0.6.2
 */