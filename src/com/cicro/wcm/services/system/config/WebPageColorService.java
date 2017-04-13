/*     */ package com.cicro.wcm.services.system.config;
/*     */ 
/*     */ import com.cicro.util.io.FileOperation;
/*     */ import com.cicro.util.jconfig.JconfigUtil;
/*     */ import com.cicro.util.jconfig.JconfigUtilContainer;
/*     */ import com.cicro.wcm.services.control.site.SiteManager;
/*     */ import java.io.File;
/*     */ import java.io.PrintStream;
/*     */ 
/*     */ public class WebPageColorService
/*     */ {
/*     */   private static final String GROUPCSS = "group.css";
/*     */   private static final String SITESS = "site.css";
/*     */   private static final String GROUPCSSHTML = "html{filter:progid:DXImageTransform.Microsoft.BasicImage(grayscale=1);}";
/*     */   private static final String GROUPCSSHTMLANNOTATION = "/*html{filter:progid:DXImageTransform.Microsoft.BasicImage(grayscale=1);}*/";
/*     */   private static final String GROUPCSSHTML_PREFIX = "html{filter:progid";
/*     */   private static final String GROUPCSSHTML_SUFFIX = "BasicImage(grayscale=1);}";
/*     */   private static final String GROUPCSSHTMLANNOTATION_PREFIX = "/*html{filter:progid";
/*     */   private static final String GROUPCSSHTMLANNOTATION_SUFFIX = "BasicImage(grayscale=1);}*/";
/*     */ 
/*     */   public static String getGroupCssFile()
/*     */   {
/*  34 */     String wcm_files_path = JconfigUtilContainer.bashConfig().getProperty("path", "", "wcm_files");
/*  35 */     String group_css = wcm_files_path + File.separator + "styles" + File.separator + "group.css";
/*  36 */     return group_css;
/*     */   }
/*     */ 
/*     */   public static boolean setGroupWebPageGrey()
/*     */   {
/*     */     try
/*     */     {
/*  45 */       String group_css = getGroupCssFile();
/*  46 */       String content = FileOperation.readFileToString(group_css, "utf-8");
/*  47 */       if (content.contains("/*html{filter:progid:DXImageTransform.Microsoft.BasicImage(grayscale=1);}*/")) {
/*  48 */         content = getGreyCode(content);
/*  49 */         FileOperation.writeStringToFile(group_css, content, false, "utf-8");
/*     */       } else {
/*  51 */         content = "html{filter:progid:DXImageTransform.Microsoft.BasicImage(grayscale=1);}" + content;
/*  52 */         FileOperation.writeStringToFile(group_css, content, false, "utf-8");
/*     */       }
/*  54 */       return true;
/*     */     } catch (Exception e) {
/*  56 */       e.printStackTrace();
/*  57 */     }return false;
/*     */   }
/*     */ 
/*     */   public static boolean setGroupWebPageNoGrey()
/*     */   {
/*     */     try
/*     */     {
/*  67 */       String group_css = getGroupCssFile();
/*  68 */       String content = FileOperation.readFileToString(group_css, "utf-8");
/*  69 */       if (!content.contains("/*html{filter:progid:DXImageTransform.Microsoft.BasicImage(grayscale=1);}*/")) {
/*  70 */         content = getNoGreyCode(content);
/*  71 */         FileOperation.writeStringToFile(group_css, content, false, "utf-8");
/*     */       }
/*  73 */       return true;
/*     */     } catch (Exception e) {
/*  75 */       e.printStackTrace();
/*  76 */     }return false;
/*     */   }
/*     */ 
/*     */   public static String getNoGreyCode(String content)
/*     */   {
/*  82 */     int start = content.indexOf("html{filter:progid");
/*  83 */     int end = content.indexOf("BasicImage(grayscale=1);}") + "BasicImage(grayscale=1);}".length();
/*  84 */     if ((start >= 0) && (end > 0)) {
/*  85 */       System.out.println(start);
/*  86 */       System.out.println(end);
/*  87 */       String temp1 = content.substring(0, start);
/*  88 */       String temp2 = content.substring(end, content.length());
/*  89 */       content = temp1 + "/*html{filter:progid:DXImageTransform.Microsoft.BasicImage(grayscale=1);}*/" + temp2;
/*     */     }
/*  91 */     return content;
/*     */   }
/*     */ 
/*     */   public static String getGreyCode(String content)
/*     */   {
/*  96 */     int start = content.indexOf("/*html{filter:progid");
/*  97 */     int end = content.indexOf("BasicImage(grayscale=1);}*/") + "BasicImage(grayscale=1);}*/".length();
/*  98 */     if ((start >= 0) && (end > 0)) {
/*  99 */       System.out.println(start);
/* 100 */       System.out.println(end);
/* 101 */       String temp1 = content.substring(0, start);
/* 102 */       String temp2 = content.substring(end, content.length());
/* 103 */       content = temp1 + "html{filter:progid:DXImageTransform.Microsoft.BasicImage(grayscale=1);}" + temp2;
/*     */     }
/* 105 */     return content;
/*     */   }
/*     */ 
/*     */   public static String getSiteCssFile(String site_id)
/*     */   {
/* 111 */     String site_root_path = SiteManager.getSitePath(site_id);
/* 112 */     String site_css = site_root_path + File.separator + "styles" + File.separator + "site.css";
/* 113 */     return site_css;
/*     */   }
/*     */ 
/*     */   public static boolean setGroupWebPageGreyNoGrey()
/*     */   {
/*     */     try
/*     */     {
/* 122 */       String group_css = getGroupCssFile();
/* 123 */       String content = FileOperation.readFileToString(group_css, "utf-8");
/* 124 */       if (content.contains("html{filter:progid:DXImageTransform.Microsoft.BasicImage(grayscale=1);}")) {
/* 125 */         if (content.contains("/*html{filter:progid:DXImageTransform.Microsoft.BasicImage(grayscale=1);}*/"))
/* 126 */           setGroupWebPageGrey();
/*     */         else
/* 128 */           setGroupWebPageNoGrey();
/*     */       }
/*     */       else {
/* 131 */         setGroupWebPageGrey();
/*     */       }
/* 133 */       return true;
/*     */     } catch (Exception e) {
/* 135 */       e.printStackTrace();
/* 136 */     }return false;
/*     */   }
/*     */ 
/*     */   public static boolean setSiteWebPageGreyORNoGrey(String site_id)
/*     */   {
/*     */     try
/*     */     {
/* 145 */       String site_css = getSiteCssFile(site_id);
/* 146 */       String content = FileOperation.readFileToString(site_css, "utf-8");
/* 147 */       if (content.contains("html{filter:progid:DXImageTransform.Microsoft.BasicImage(grayscale=1);}")) {
/* 148 */         if (content.contains("/*html{filter:progid:DXImageTransform.Microsoft.BasicImage(grayscale=1);}*/"))
/* 149 */           setSiteWebPageGrey(site_id);
/*     */         else
/* 151 */           setSiteWebPageNoGrey(site_id);
/*     */       }
/*     */       else {
/* 154 */         setSiteWebPageGrey(site_id);
/*     */       }
/* 156 */       return true;
/*     */     } catch (Exception e) {
/* 158 */       e.printStackTrace();
/* 159 */     }return false;
/*     */   }
/*     */ 
/*     */   public static boolean setSiteWebPageGrey(String site_id)
/*     */   {
/*     */     try
/*     */     {
/* 170 */       String site_css = getSiteCssFile(site_id);
/* 171 */       String content = FileOperation.readFileToString(site_css, "utf-8");
/* 172 */       if (content.contains("/*html{filter:progid:DXImageTransform.Microsoft.BasicImage(grayscale=1);}*/")) {
/* 173 */         content = getGreyCode(content);
/* 174 */         FileOperation.writeStringToFile(site_css, content, false, "utf-8");
/*     */       } else {
/* 176 */         content = "html{filter:progid:DXImageTransform.Microsoft.BasicImage(grayscale=1);}" + content;
/* 177 */         FileOperation.writeStringToFile(site_css, content, false, "utf-8");
/*     */       }
/* 179 */       return true;
/*     */     } catch (Exception e) {
/* 181 */       e.printStackTrace();
/* 182 */     }return false;
/*     */   }
/*     */ 
/*     */   public static boolean setSiteWebPageNoGrey(String site_id)
/*     */   {
/*     */     try
/*     */     {
/* 192 */       String site_css = getSiteCssFile(site_id);
/* 193 */       String content = FileOperation.readFileToString(site_css, "utf-8");
/* 194 */       if (!content.contains("/*html{filter:progid:DXImageTransform.Microsoft.BasicImage(grayscale=1);}*/")) {
/* 195 */         content = getNoGreyCode(content);
/* 196 */         FileOperation.writeStringToFile(site_css, content, false, "utf-8");
/*     */       }
/* 198 */       return true;
/*     */     } catch (Exception e) {
/* 200 */       e.printStackTrace();
/* 201 */     }return false;
/*     */   }
/*     */ 
/*     */   public static void main(String[] arr)
/*     */   {
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.system.config.WebPageColorService
 * JD-Core Version:    0.6.2
 */