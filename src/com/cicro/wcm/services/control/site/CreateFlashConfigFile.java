/*    */ package com.cicro.wcm.services.control.site;
/*    */ 
/*    */ import com.cicro.util.io.FileOperation;
/*    */ import com.cicro.util.jconfig.JconfigUtil;
/*    */ import com.cicro.util.jconfig.JconfigUtilContainer;
/*    */ import com.cicro.wcm.bean.control.SiteBean;
/*    */ import com.cicro.wcm.bean.control.SiteDomainBean;
/*    */ import com.cicro.wcm.services.control.domain.SiteDomainManager;
/*    */ import java.util.Iterator;
/*    */ import java.util.List;
/*    */ 
/*    */ public class CreateFlashConfigFile
/*    */ {
/* 24 */   private static String img_site_path = JconfigUtilContainer.bashConfig().getProperty("save_path", "", "resource_server");
/*    */ 
/*    */   public static boolean CreateFlashFile() {
/* 27 */     String xml = getCrossdomainXML();
/* 28 */     List siteList = SiteManager.getSiteList();
/* 29 */     for (SiteBean site : siteList)
/*    */     {
/* 31 */       String site_path = site.getSite_path() + "/crossdomain.xml";
/*    */       try {
/* 33 */         FileOperation.writeStringToFile(site_path, xml, false); } catch (Exception e) {
/* 34 */         e.printStackTrace();
/*    */       }
/*    */     }
/*    */     try { FileOperation.writeStringToFile(img_site_path + "/crossdomain.xml", xml, false); } catch (Exception e) {
/* 38 */       e.printStackTrace();
/* 39 */     }return true;
/*    */   }
/*    */ 
/*    */   public static String getCrossdomainXML()
/*    */   {
/* 44 */     String xml = "<?xml version=\"1.0\"?>\n\r";
/* 45 */     xml = xml + "<cross-domain-policy>\n\r";
/* 46 */     xml = xml + "<site-control permitted-cross-domain-policies=\"master-only\"/>\n\r";
/* 47 */     List siteList = SiteManager.getSiteList();
/* 48 */     if ((siteList != null) && (siteList.size() > 0))
/*    */     {
/*    */       Iterator localIterator2;
/* 50 */       label171: for (Iterator localIterator1 = siteList.iterator(); localIterator1.hasNext(); 
/* 55 */         localIterator2.hasNext())
/*    */       {
/* 50 */         SiteBean site = (SiteBean)localIterator1.next();
/*    */ 
/* 52 */         List domainList = SiteDomainManager.getDomainListBySiteID(site.getSite_id());
/* 53 */         if ((domainList == null) || (domainList.size() <= 0))
/*    */           break label171;
/* 55 */         localIterator2 = domainList.iterator(); continue; SiteDomainBean domain = (SiteDomainBean)localIterator2.next();
/* 56 */         xml = xml + "<allow-access-from domain=\"" + domain.getSite_domain() + "\" />\n\r";
/*    */       }
/*    */     }
/*    */ 
/* 60 */     xml = xml + "</cross-domain-policy>";
/* 61 */     return xml;
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.control.site.CreateFlashConfigFile
 * JD-Core Version:    0.6.2
 */