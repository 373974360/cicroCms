/*     */ package com.cicro.util;
/*     */ 
/*     */ import com.cicro.util.jconfig.JconfigUtilContainer;
/*     */ import com.cicro.wcm.services.control.domain.SiteDomainManager;

/*     */ import java.io.File;
/*     */ 
/*     */ public class UploadManagerPlay
/*     */ {
/*   9 */   private static String root_path = "/cicro/wcm";
/*  10 */   private static String hostRoot_path = "/cicro/wcm/vhosts";
/*  11 */   private static String public_save_path = "/cicro/wcm/vhosts/img.site.com/ROOT";
/*  12 */   private static String img_domain = "img.xahrss.gov.cn";
/*  13 */   private static String site_port = "";
			private static String GMpath = "";
/*     */ 
/*     */   public static String getUploadSecretKey()
/*     */   {
/*  23 */     String key = RandomStrg.getRandomStr("", RandomStrg.getRandomStr("0-9", "1")) + "," + DateUtil.dateToTimestamp();
/*     */ 
/*  25 */     CryptoTools ct = new CryptoTools();
/*     */ 
/*  28 */     return ct.encode(key).replace("+", "WcMrEPlAcE").substring(3);
/*     */   }
/*     */ 
/*     */   public static boolean checkUploadSecretKey(String key)
/*     */   {
/*     */     try
/*     */     {
/*  39 */       key = "=#=" + key;
/*  40 */       CryptoTools ct = new CryptoTools();
/*  41 */       key = ct.decode(key.replace("WcMrEPlAcE", "+"));
/*  42 */       key = key.substring(key.indexOf(",") + 1);
/*     */ 
/*  44 */       int times = 60 * 15;			//此处参数为upload_check_times
/*  45 */       long timel = Long.parseLong(key);
/*  48 */       return DateUtil.dateToTimestamp() - timel < 1000 * times + 100;
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/*  52 */       e.printStackTrace();
/*  53 */     }return false;
/*     */   }
/*     */ 
/*     */   public static String getUploadFilePath2()
/*     */   {
/*  65 */     String savePath = "";
/*  66 */     if ((img_domain != null) && (!"".equals(img_domain.trim())))
/*     */     {
/*  68 */       savePath = FormatUtil.formatPath(public_save_path);
/*  69 */       return savePath;
/*     */     }
/*     */ 
/*  72 */     return FormatUtil.formatPath(root_path);
/*     */   }
/*     */ 
/*     */   public static String getUploadFilePath(String site_id)
/*     */   {
/*  87 */     String savePath = "";
/*  88 */     if ((img_domain != null) && (!"".equals(img_domain.trim())))
/*     */     {
/*  90 */       savePath = FormatUtil.formatPath(public_save_path);
/*     */     }
/*     */     else
/*     */     {
/*  95 */       savePath = hostRoot_path + "/" + SiteDomainManager.getDefaultSiteDomainBySiteID(site_id) + "/ROOT";
/*     */     }
/*  97 */     savePath = FormatUtil.formatPath(savePath + getUploadFileSitePath(site_id));
/*  98 */     File f1 = new File(savePath);
/*  99 */     if (!f1.exists()) {
/* 100 */       f1.mkdirs();
/*     */     }
/* 102 */     return savePath;
/*     */   }

/*     */   public static String getUploadFileSitePath(String site_id)
/*     */   {
/* 115 */     return "/" + site_id + "/" + DateUtil.getCurrentDateTime("yyyyMM") + "/";
/*     */   }
/*     */ 
/*     */   public static String getImgBrowserUrl(String site_id)
/*     */   {
/* 128 */     if ((img_domain != null) && (!"".equals(img_domain.trim()))) {
/* 129 */       if ((site_port != null) && (!"".equals(site_port.trim())))
/*     */       {
/* 131 */         return "http://" + JconfigUtilContainer.bashConfig().getProperty("img_domain", "", "resource_server") + ":" + site_port;
/*     */       }
/* 133 */       return "http://" + JconfigUtilContainer.bashConfig().getProperty("img_domain", "", "resource_server");
/*     */     }
/*     */ 	  
/* 137 */     //if ((site_id != null) && (!"".equals(site_id.trim())))
/*     */    // {
/* 139 */      // if ((site_port != null) && (!"".equals(site_port.trim())))
/*     */      // {
/* 141 */      //   return "http://" + SiteDomainManager.getDefaultSiteDomainBySiteID(site_id) + ":" + site_port;
/*     */      // }
/* 143 */     //  return "http://" + SiteDomainManager.getDefaultSiteDomainBySiteID(site_id);
/*     */    // }
/*     */ 
/* 146 */     return "";
/*     */   }
			public static String getGMPath()
/*     */   {
/* 115 */     return GMpath;
/*     */   }
/*     */ 
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/* 153 */     //String str = getUploadSecretKey();
				String key = "PAriOyELHU8HKPmMaLRKFQ==";
				System.out.println(checkUploadSecretKey(key));
			  CryptoTools ct = new CryptoTools();
/*  41 */       key = ct.decode(key.replace("WcMrEPlAcE", "+"));
/*     */ 	  
/* 155 */     //System.out.println("www.so.com:99".replaceAll("(.*)([:][0-9]*)(.*?)", "$1"));
//System.out.println(key);
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.util.UploadManager
 * JD-Core Version:    0.6.2
 */