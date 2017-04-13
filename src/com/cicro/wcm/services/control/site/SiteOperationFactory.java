/*     */ package com.cicro.wcm.services.control.site;
/*     */ 
/*     */ import com.cicro.util.jconfig.JconfigUtil;
/*     */ import com.cicro.util.jconfig.JconfigUtilContainer;
/*     */ import com.cicro.wcm.bean.control.SiteBean;
/*     */ import com.cicro.wcm.bean.control.SiteDomainBean;
/*     */ import com.cicro.wcm.bean.control.SiteServerBean;
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.rmi.ISiteRmi;
/*     */ import com.cicro.wcm.services.control.domain.SiteDomainManager;
/*     */ import com.cicro.wcm.services.control.server.SiteServerManager;
/*     */ import java.io.PrintStream;
/*     */ import javax.naming.Context;
/*     */ import javax.naming.InitialContext;
/*     */ import javax.naming.NamingException;
/*     */ 
/*     */ public class SiteOperationFactory
/*     */ {
/*     */   public static ISiteRmi getSiteRMIForSiteID(String site_id)
/*     */   {
/*  40 */     SiteBean sb = SiteManager.getSiteBeanBySiteID(site_id);
/*  41 */     if (sb != null)
/*     */     {
/*  43 */       return getSiteRmigetSiteRMIForServerID(sb.getServer_id());
/*     */     }
/*  45 */     return null;
/*     */   }
/*     */ 
/*     */   public static ISiteRmi getSiteRmigetSiteRMIForServerID(String server_id)
/*     */   {
/*  55 */     String server_ip = SiteServerManager.getServerBeanByID(server_id).getServer_ip();
/*  56 */     System.out.println("getSiteRmigetSiteRMIForServerID-------------" + server_ip);
/*  57 */     return getSiteRMIForServerIP(server_ip);
/*     */   }
/*     */ 
/*     */   public static ISiteRmi getSiteRMIForServerIP(String server_ip)
/*     */   {
/*     */     try
/*     */     {
/*  68 */       Context namingContext = new InitialContext();
/*  69 */       String path = "rmi://" + server_ip + ":" + JconfigUtilContainer.bashConfig().getProperty("port", "", "rmi_config") + "/siteRmi";
/*  70 */       System.out.println("getSiteRMIForServerIP-------------" + path);
/*  71 */       return (ISiteRmi)namingContext.lookup(path);
/*     */     }
/*     */     catch (Exception e) {
/*  74 */       e.printStackTrace();
/*  75 */     }return null;
/*     */   }
/*     */ 
/*     */   public static boolean addSite(SiteBean sb, SettingLogsBean stl)
/*     */   {
/*     */     try
/*     */     {
/*  88 */       boolean is_add = false;
/*  89 */       if (!SiteServerManager.IS_MUTILPUBLISHSERVER)
/*     */       {
/*  91 */         is_add = SiteOperation.addSite(sb, stl);
/*     */       }
/*     */       else
/*     */       {
/*  95 */         ISiteRmi siteRmi = getSiteRmigetSiteRMIForServerID(sb.getServer_id());
/*  96 */         is_add = siteRmi.addSite(sb, stl);
/*     */       }
/*     */ 
/*  99 */       if (is_add)
/*     */       {
/* 102 */         addSiteInResourceServer(sb.getSite_id());
/* 103 */         SiteManager.reloadSiteList();
/* 104 */         SiteDomainManager.reloadSiteDomainList();
/* 105 */         return true;
/*     */       }
/*     */ 
/* 108 */       return false;
/*     */     }
/*     */     catch (Exception e) {
/* 111 */       e.printStackTrace();
/* 112 */     }return false;
/*     */   }
/*     */ 
/*     */   public static boolean addSiteInResourceServer(String site_id)
/*     */   {
/*     */     try
/*     */     {
/* 124 */       String resource_server_ip = SiteServerManager.getResourceServerIP();
/* 125 */       if ((resource_server_ip != null) && (!"".equals(resource_server_ip)))
/*     */       {
/* 127 */         boolean is_add = false;
/*     */         ISiteRmi siteRmi;
/* 128 */         if (!SiteServerManager.IS_MUTILPUBLISHSERVER)
/*     */         {
/* 130 */           is_add = SiteOperation.addSiteInResourceServer(site_id);
/*     */         }
/*     */         else
/*     */         {
/* 134 */           siteRmi = getSiteRMIForServerIP(resource_server_ip);
/* 135 */         }return siteRmi.addSiteInResourceServer(site_id);
/*     */       }
/*     */ 
/* 139 */       return false;
/*     */     }
/*     */     catch (Exception e) {
/* 142 */       e.printStackTrace();
/* 143 */       System.out.println(" resource server ip is null ");
/* 144 */     }return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateSite(SiteBean sb, SettingLogsBean stl)
/*     */   {
/*     */     try
/*     */     {
/* 157 */       boolean is_update = false;
/*     */       ISiteRmi siteRmi;
/* 158 */       if (!SiteServerManager.IS_MUTILPUBLISHSERVER)
/*     */       {
/* 160 */         is_update = SiteOperation.updateSite(sb, stl);
/*     */       }
/*     */       else
/*     */       {
/* 164 */         siteRmi = getSiteRMIForServerIP(sb.getServer_id());
/* 165 */       }return siteRmi.updateSite(sb, stl);
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 171 */       e.printStackTrace();
/* 172 */     }return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateSiteStatus(String site_id, int site_status, SettingLogsBean stl)
/*     */   {
/*     */     try
/*     */     {
/* 186 */       boolean is_update = false;
/*     */       ISiteRmi siteRmi;
/* 187 */       if (!SiteServerManager.IS_MUTILPUBLISHSERVER)
/*     */       {
/* 189 */         is_update = SiteOperation.updateSiteStatus(site_id, site_status, stl);
/*     */       }
/*     */       else
/*     */       {
/* 193 */         siteRmi = getSiteRMIForSiteID(site_id);
/* 194 */       }return siteRmi.updateSiteStatus(site_id, site_status, stl);
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 199 */       e.printStackTrace();
/* 200 */     }return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteSite(String site_id, SettingLogsBean stl)
/*     */   {
/*     */     try
/*     */     {
/* 213 */       boolean is_delete = false;
/* 214 */       if (!SiteServerManager.IS_MUTILPUBLISHSERVER)
/*     */       {
/* 216 */         is_delete = SiteOperation.deleteSite(site_id, stl);
/*     */       }
/*     */       else
/*     */       {
/* 220 */         ISiteRmi siteRmi = getSiteRMIForSiteID(site_id);
/* 221 */         is_delete = siteRmi.deleteSite(site_id, stl);
/*     */       }
/*     */ 
/* 225 */       if (is_delete)
/*     */       {
/* 227 */         SiteManager.reloadSiteList();
/* 228 */         SiteDomainManager.reloadSiteDomainList();
/* 229 */         return true;
/*     */       }
/* 231 */       return false;
/*     */     }
/*     */     catch (Exception e) {
/* 234 */       e.printStackTrace();
/* 235 */     }return false;
/*     */   }
/*     */ 
/*     */   public static boolean addAlias(SiteDomainBean sdb, SettingLogsBean stl)
/*     */   {
/*     */     try
/*     */     {
/* 248 */       boolean is_add = false;
/* 249 */       if (!SiteServerManager.IS_MUTILPUBLISHSERVER)
/*     */       {
/* 251 */         is_add = SiteOperation.addAlias(sdb, stl);
/*     */       }
/*     */       else
/*     */       {
/* 255 */         ISiteRmi siteRmi = getSiteRMIForSiteID(sdb.getSite_id());
/* 256 */         is_add = siteRmi.addAlias(sdb, stl);
/*     */       }
/*     */ 
/* 260 */       if (is_add)
/*     */       {
/* 262 */         SiteDomainManager.reloadSiteDomainList();
/* 263 */         return true;
/*     */       }
/* 265 */       return false;
/*     */     }
/*     */     catch (Exception e) {
/* 268 */       e.printStackTrace();
/* 269 */     }return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateAlias(SiteDomainBean sdb, SettingLogsBean stl)
/*     */   {
/*     */     try
/*     */     {
/* 282 */       boolean is_update = false;
/* 283 */       if (!SiteServerManager.IS_MUTILPUBLISHSERVER)
/*     */       {
/* 285 */         is_update = SiteOperation.updateAlias(sdb, stl);
/*     */       }
/*     */       else
/*     */       {
/* 289 */         ISiteRmi siteRmi = getSiteRMIForSiteID(sdb.getSite_id());
/* 290 */         is_update = siteRmi.updateAlias(sdb, stl);
/*     */       }
/*     */ 
/* 293 */       if (is_update)
/*     */       {
/* 295 */         SiteDomainManager.reloadSiteDomainList();
/* 296 */         return true;
/*     */       }
/* 298 */       return false;
/*     */     }
/*     */     catch (Exception e) {
/* 301 */       e.printStackTrace();
/* 302 */     }return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteAlias(String site_id, String domain_ids, SettingLogsBean stl)
/*     */   {
/*     */     try
/*     */     {
/* 316 */       boolean is_delete = false;
/* 317 */       if (!SiteServerManager.IS_MUTILPUBLISHSERVER)
/*     */       {
/* 319 */         is_delete = SiteOperation.deleteAlias(domain_ids, stl);
/*     */       }
/*     */       else
/*     */       {
/* 323 */         ISiteRmi siteRmi = getSiteRMIForSiteID(site_id);
/* 324 */         is_delete = siteRmi.deleteAlias(domain_ids, stl);
/*     */       }
/*     */ 
/* 327 */       if (is_delete)
/*     */       {
/* 329 */         SiteDomainManager.reloadSiteDomainList();
/* 330 */         return true;
/*     */       }
/* 332 */       return false;
/*     */     }
/*     */     catch (Exception e) {
/* 335 */       e.printStackTrace();
/* 336 */     }return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateHitForSite(String site_id, int count)
/*     */   {
/*     */     try
/*     */     {
/* 349 */       if ((!SiteServerManager.IS_MUTILPUBLISHSERVER) || (SiteServerManager.isTheSameServer(site_id)))
/*     */       {
/* 352 */         return SiteVisitCountManager.updateHitForSite(site_id, count);
/*     */       }
/*     */ 
/* 355 */       ISiteRmi siteRmi = getSiteRMIForSiteID(site_id);
/* 356 */       return siteRmi.updateHitForSite(site_id, count);
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 360 */       e.printStackTrace();
/* 361 */     }return false;
/*     */   }
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/*     */     try
/*     */     {
/* 369 */       Context namingContext = new InitialContext();
/* 370 */       System.out.println(namingContext.lookup("rmi://192.168.0.150:1102/siteRmi"));
/*     */     }
/*     */     catch (NamingException e)
/*     */     {
/* 374 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.control.site.SiteOperationFactory
 * JD-Core Version:    0.6.2
 */