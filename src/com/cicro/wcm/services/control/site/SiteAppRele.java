/*     */ package com.cicro.wcm.services.control.site;
/*     */ 
/*     */ import com.cicro.wcm.bean.control.SiteAppBean;
/*     */ import com.cicro.wcm.catchs.ISyncCatch;
/*     */ import com.cicro.wcm.catchs.SyncCatchHandl;
/*     */ import com.cicro.wcm.dao.control.SiteConfigDAO;
/*     */ import java.io.PrintStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ public class SiteAppRele
/*     */   implements ISyncCatch
/*     */ {
/*  22 */   private static List<SiteAppBean> site_app_list = new ArrayList();
/*     */ 
/*     */   static {
/*  25 */     reloadCatchHandl();
/*     */   }
/*     */ 
/*     */   public void reloadCatch()
/*     */   {
/*  30 */     reloadCatchHandl();
/*     */   }
/*     */ 
/*     */   public static void reloadCatchHandl()
/*     */   {
/*  35 */     site_app_list.clear();
/*  36 */     site_app_list = SiteConfigDAO.getSiteAppReleList();
/*     */   }
/*     */ 
/*     */   public static void reloadSiteAppRele()
/*     */   {
/*  41 */     SyncCatchHandl.reladCatchForRMI("com.cicro.wcm.services.control.site.SiteAppRele");
/*     */   }
/*     */ 
/*     */   public static SiteAppBean getSiteAppBean(String app_id)
/*     */   {
/*  46 */     if ((site_app_list != null) && (site_app_list.size() > 0))
/*     */     {
/*  48 */       for (int i = 0; i < site_app_list.size(); i++)
/*     */       {
/*  50 */         if (app_id.equals(((SiteAppBean)site_app_list.get(i)).getApp_id()))
/*     */         {
/*  52 */           return (SiteAppBean)site_app_list.get(i);
/*     */         }
/*     */       }
/*     */     }
/*  56 */     return null;
/*     */   }
/*     */ 
/*     */   public static String getSiteIDByAppID(String app_id)
/*     */   {
/*  66 */     String site_id = "";
/*  67 */     if ((site_app_list != null) && (site_app_list.size() > 0))
/*     */     {
/*  69 */       for (int i = 0; i < site_app_list.size(); i++)
/*     */       {
/*  71 */         if (app_id.equals(((SiteAppBean)site_app_list.get(i)).getApp_id()))
/*     */         {
/*  73 */           return ((SiteAppBean)site_app_list.get(i)).getSite_id();
/*     */         }
/*     */       }
/*     */     }
/*  77 */     return site_id;
/*     */   }
/*     */ 
/*     */   public static String getSiteReleAppIDS(String site_id)
/*     */   {
/*  87 */     String app_id = "";
/*  88 */     if ((site_app_list != null) && (site_app_list.size() > 0))
/*     */     {
/*  90 */       for (int i = 0; i < site_app_list.size(); i++)
/*     */       {
/*  92 */         if (site_id.equals(((SiteAppBean)site_app_list.get(i)).getSite_id()))
/*     */         {
/*  94 */           app_id = app_id + "," + ((SiteAppBean)site_app_list.get(i)).getApp_id();
/*     */         }
/*     */       }
/*  97 */       if (app_id.length() > 0)
/*  98 */         app_id = app_id.substring(1);
/*     */     }
/* 100 */     return app_id;
/*     */   }
/*     */ 
/*     */   public static boolean insertSiteReleApp(String site_id, String app_id)
/*     */   {
/* 110 */     if (SiteConfigDAO.insertSiteReleApp(site_id, app_id))
/*     */     {
/* 112 */       reloadSiteAppRele();
/* 113 */       return true;
/*     */     }
/* 115 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean insertSiteReleApp(SiteAppBean sab)
/*     */   {
/* 125 */     if (SiteConfigDAO.insertSiteReleApp(sab))
/*     */     {
/* 127 */       reloadSiteAppRele();
/* 128 */       return true;
/*     */     }
/* 130 */     return false;
/*     */   }
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/* 135 */     String ss = "/cicro/wcm/vhosts/img.site.com:9126/HIWCMmyt01/201109/201109061000052.png";
/* 136 */     System.out.println(ss.replaceAll("(.*)([:][0-9]*)(.*?)", "$1"));
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.control.site.SiteAppRele
 * JD-Core Version:    0.6.2
 */