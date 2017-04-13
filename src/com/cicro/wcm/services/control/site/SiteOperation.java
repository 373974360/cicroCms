/*     */ package com.cicro.wcm.services.control.site;
/*     */ 
/*     */ import com.cicro.util.FormatUtil;
/*     */ import com.cicro.util.JarManager;
/*     */ import com.cicro.util.io.FileOperation;
/*     */ import com.cicro.util.jconfig.JconfigFactory;
/*     */ import com.cicro.util.jconfig.JconfigUtil;
/*     */ import com.cicro.util.jconfig.JconfigUtilContainer;
/*     */ import com.cicro.wcm.bean.control.SiteBean;
/*     */ import com.cicro.wcm.bean.control.SiteDomainBean;
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.server.ApacheConfigFactory;
/*     */ import com.cicro.wcm.server.IApacheConfig;
/*     */ import com.cicro.wcm.server.IServerConfig;
/*     */ import com.cicro.wcm.server.ServerConfigFactory;
/*     */ import com.cicro.wcm.services.control.domain.SiteDomainManager;
/*     */ import com.cicro.wcm.services.system.template.TemplateCategoryManager;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.io.PrintStream;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class SiteOperation
/*     */ {
/*     */   public static boolean addSite(SiteBean sb, SettingLogsBean stl)
/*     */   {
/*  45 */     sb.setSite_domain(sb.getSite_domain().trim());
/*  46 */     String site_path = FormatUtil.formatPath(JconfigUtilContainer.bashConfig().getProperty("path", "", "hostRoot_path") + "/" + sb.getSite_domain());
/*  47 */     System.out.println("site_path----" + site_path);
/*  48 */     System.out.println("site_path----" + FormatUtil.formatPath(new StringBuilder(String.valueOf(site_path)).append("/ROOT").toString()));
/*  49 */     sb.setSite_path(FormatUtil.formatPath(site_path + "/ROOT"));
/*     */ 
/*  51 */     if (SiteManager.insertSite(sb, stl))
/*     */     {
/*  54 */       if (createDirectory(site_path))
/*     */       {
/*  56 */         Map site_infos = new HashMap();
/*  57 */         site_infos.put("site_id", sb.getSite_id());
/*  58 */         site_infos.put("site_domain", sb.getSite_domain());
/*  59 */         site_infos.put("site_path", sb.getSite_path());
/*     */ 
/*  61 */         addSiteInfoToServer(site_infos);
/*     */ 
/*  63 */         addSiteInfoToApache(site_infos, "_config");
/*     */ 
/*  65 */         if ((sb.getClone_site_id() != null) && (!"".equals(sb.getClone_site_id()))) {
/*  66 */           cloneSite(sb.getSite_id(), sb.getClone_site_id());
/*     */         }
/*  68 */         addOtherConfig(sb.getSite_id());
/*     */ 
/*  70 */         CreateFlashConfigFile.CreateFlashFile();
/*  71 */         return true;
/*     */       }
/*     */ 
/*  74 */       return false;
/*     */     }
/*     */ 
/*  79 */     System.out.println("site insert db error!");
/*  80 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean addOtherConfig(String site_id)
/*     */   {
/*     */     try
/*     */     {
/*  92 */       TemplateCategoryManager.addDefaultZTCategory(site_id);
/*  93 */       return true;
/*     */     }
/*     */     catch (Exception e) {
/*  96 */       e.printStackTrace();
/*  97 */       System.out.println("addOtherConfig error");
/*  98 */     }return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateSite(SiteBean sb, SettingLogsBean stl)
/*     */   {
/* 110 */     sb.setSite_domain(sb.getSite_domain().trim());
/*     */ 
/* 112 */     if (SiteManager.updateSite(sb, stl))
/*     */     {
/* 114 */       String old_domain = SiteDomainManager.getSiteDomainBySiteID(sb.getSite_id());
/*     */ 
/* 116 */       int domain_flag = SiteDomainManager.updateSiteDomainByName(sb.getSite_domain(), sb.getSite_id());
/*     */ 
/* 119 */       if (domain_flag == 1)
/*     */       {
/* 121 */         Map site_infos = new HashMap();
/* 122 */         site_infos.put("old_site_domain", old_domain);
/* 123 */         site_infos.put("new_site_domain", sb.getSite_domain());
/*     */ 
/* 125 */         updateSiteInfoToServer(site_infos);
/*     */ 
/* 127 */         updateSiteInfoToApache(site_infos);
/*     */ 
/* 129 */         CreateFlashConfigFile.CreateFlashFile();
/* 130 */         return true;
/*     */       }
/* 132 */       if (domain_flag == -1)
/*     */       {
/* 134 */         return false;
/*     */       }
/* 136 */       return true;
/*     */     }
/* 138 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateSiteStatus(String site_id, int site_status, SettingLogsBean stl)
/*     */   {
/* 150 */     if ((site_id != null) && (!"".equals(site_id)))
/*     */     {
/* 152 */       SiteBean sb = SiteManager.getSiteBeanBySiteID(site_id);
/* 153 */       if (sb != null)
/*     */       {
/* 155 */         if (SiteManager.updateSiteStatus(site_id, site_status, stl))
/*     */         {
/* 157 */           Map site_infos = new HashMap();
/* 158 */           site_infos.put("site_id", sb.getSite_id());
/* 159 */           site_infos.put("site_path", sb.getSite_path());
/* 160 */           site_infos.put("site_domain", sb.getSite_domain());
/* 161 */           if (site_status == 1)
/* 162 */             return stopSiteInfoToApache(site_infos, "_config_stop");
/* 163 */           if (site_status == 0)
/* 164 */             return stopSiteInfoToApache(site_infos, "_config");
/*     */         }
/*     */         else {
/* 167 */           return false;
/*     */         }
/*     */       }
/* 170 */       else return false;
/*     */     }
/* 172 */     return true;
/*     */   }
/*     */ 
/*     */   public static boolean deleteSite(String site_id, SettingLogsBean stl)
/*     */   {
/* 183 */     if ((site_id != null) && (!"".equals(site_id)))
/*     */     {
/* 185 */       SiteBean sb = SiteManager.getSiteBeanBySiteID(site_id);
/* 186 */       if (sb != null)
/*     */       {
/* 188 */         if (SiteManager.deleteSite(site_id, stl))
/*     */         {
/* 190 */           Map site_infos = new HashMap();
/* 191 */           site_infos.put("site_id", sb.getSite_id());
/* 192 */           site_infos.put("site_domain", sb.getSite_domain());
/* 193 */           site_infos.put("site_path", sb.getSite_path());
/* 194 */           delSiteInfoByServer(site_infos);
/* 195 */           delSiteByApache(site_infos);
/*     */ 
/* 197 */           SiteDomainManager.deleteSiteDomainBySiteID(site_id, stl);
/*     */ 
/* 199 */           CreateFlashConfigFile.CreateFlashFile();
/* 200 */           return true;
/*     */         }
/*     */ 
/* 203 */         return false;
/*     */       }
/*     */ 
/* 206 */       return false;
/*     */     }
/* 208 */     return true;
/*     */   }
/*     */ 
/*     */   public static boolean addAlias(SiteDomainBean sdb, SettingLogsBean stl)
/*     */   {
/* 219 */     sdb.setSite_domain(sdb.getSite_domain().trim());
/*     */ 
/* 221 */     if (SiteDomainManager.insertSiteDomain(sdb, stl))
/*     */     {
/*     */       try
/*     */       {
/* 225 */         Map site_infos = new HashMap();
/* 226 */         site_infos.put("site_id", sdb.getSite_id());
/* 227 */         site_infos.put("site_domain", sdb.getSite_domain());
/* 228 */         site_infos.put("site_path", SiteManager.getSiteBeanBySiteID(sdb.getSite_id()).getSite_path());
/* 229 */         addAliasToServer(site_infos);
/*     */ 
/* 231 */         addSiteInfoToApache(site_infos, "_config");
/*     */ 
/* 233 */         CreateFlashConfigFile.CreateFlashFile();
/* 234 */         return true;
/*     */       }
/*     */       catch (Exception e) {
/* 237 */         return false;
/*     */       }
/*     */     }
/* 240 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateAlias(SiteDomainBean sdb, SettingLogsBean stl)
/*     */   {
/* 251 */     sdb.setSite_domain(sdb.getSite_domain().trim());
/*     */ 
/* 254 */     SiteDomainBean sdb_old = SiteDomainManager.getSiteDomainBeanByID(sdb.getDomain_id());
/*     */ 
/* 256 */     if (!sdb.getSite_domain().equals(sdb_old.getSite_domain()))
/*     */     {
/* 258 */       if (SiteDomainManager.updateSiteDomain(sdb, stl))
/*     */       {
/* 260 */         Map site_infos = new HashMap();
/* 261 */         site_infos.put("old_site_domain", sdb_old.getSite_domain());
/* 262 */         site_infos.put("new_site_domain", sdb.getSite_domain());
/*     */ 
/* 264 */         updateSiteInfoToServer(site_infos);
/*     */ 
/* 266 */         updateSiteInfoToApache(site_infos);
/*     */ 
/* 268 */         CreateFlashConfigFile.CreateFlashFile();
/* 269 */         return true;
/*     */       }
/* 271 */       return false;
/*     */     }
/* 273 */     return true;
/*     */   }
/*     */ 
/*     */   public static boolean deleteAlias(String domain_ids, SettingLogsBean stl)
/*     */   {
/* 284 */     if ((domain_ids != null) && (!"".equals(domain_ids))) {
/*     */       try
/*     */       {
/* 287 */         String[] tempA = domain_ids.split(",");
/* 288 */         for (int i = 0; i < tempA.length; i++)
/*     */         {
/* 290 */           SiteDomainBean sdb = SiteDomainManager.getSiteDomainBeanByID(tempA[i]);
/* 291 */           Map site_infos = new HashMap();
/* 292 */           site_infos.put("site_domain", sdb.getSite_domain());
/* 293 */           site_infos.put("site_id", sdb.getSite_id());
/*     */ 
/* 295 */           if (SiteDomainManager.deleteSiteDomainByID(tempA[i], stl))
/*     */           {
/* 297 */             deleteAliasToServer(site_infos);
/* 298 */             delSiteByApache(site_infos);
/*     */           }
/*     */         }
/*     */ 
/* 302 */         CreateFlashConfigFile.CreateFlashFile();
/* 303 */         return true;
/*     */       }
/*     */       catch (Exception e) {
/* 306 */         e.printStackTrace();
/* 307 */         return false;
/*     */       }
/*     */     }
/*     */ 
/* 311 */     return true;
/*     */   }
/*     */ 
/*     */   public static boolean createDirectory(String site_path)
/*     */   {
/*     */     try
/*     */     {
/* 323 */       File f = new File(site_path);
/* 324 */       if (!f.exists())
/*     */       {
/* 326 */         f.mkdir();
/*     */       }
/*     */ 
/* 329 */       String resource_file = FormatUtil.formatPath(JconfigUtilContainer.bashConfig().getProperty("path", "", "resource_file"));
/* 330 */       JarManager.decompress(resource_file, site_path);
/* 331 */       return true;
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 335 */       System.out.println("create site directory error!");
/* 336 */     }return false;
/*     */   }
/*     */ 
/*     */   public static boolean addSiteInResourceServer(String site_id)
/*     */   {
/* 347 */     String site_path = JconfigUtilContainer.bashConfig().getProperty("path", "", "resource_server");
/* 348 */     if ((site_path != null) && (!"".equals(site_path))) {
/*     */       try
/*     */       {
/* 351 */         site_path = FormatUtil.formatPath(site_path + "/" + site_id);
/* 352 */         File f = new File(site_path);
/* 353 */         if (!f.exists())
/*     */         {
/* 355 */           f.mkdirs();
/*     */         }
/* 357 */         return true;
/*     */       }
/*     */       catch (Exception e) {
/* 360 */         e.printStackTrace();
/* 361 */         return false;
/*     */       }
/*     */     }
/* 364 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean addSiteInfoToServer(Map<String, String> site_infos)
/*     */   {
/*     */     try
/*     */     {
/* 375 */       IServerConfig isconfig = ServerConfigFactory.getServerConfig();
/* 376 */       isconfig.addSite(site_infos);
/* 377 */       return true;
/*     */     }
/*     */     catch (Exception e) {
/* 380 */       e.printStackTrace();
/* 381 */     }return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateSiteInfoToServer(Map<String, String> site_infos)
/*     */   {
/*     */     try
/*     */     {
/* 393 */       IServerConfig isconfig = ServerConfigFactory.getServerConfig();
/* 394 */       isconfig.updateSite(site_infos);
/* 395 */       return true;
/*     */     }
/*     */     catch (Exception e) {
/* 398 */       e.printStackTrace();
/* 399 */     }return false;
/*     */   }
/*     */ 
/*     */   public static boolean delSiteInfoByServer(Map<String, String> site_infos)
/*     */   {
/*     */     try
/*     */     {
/* 411 */       IServerConfig isconfig = ServerConfigFactory.getServerConfig();
/* 412 */       isconfig.delSite(site_infos);
/* 413 */       return true;
/*     */     }
/*     */     catch (Exception e) {
/* 416 */       e.printStackTrace();
/* 417 */     }return false;
/*     */   }
/*     */ 
/*     */   public static boolean addSiteInfoToApache(Map<String, String> site_infos, String apacheConfig_name)
/*     */   {
/*     */     try
/*     */     {
/* 430 */       IApacheConfig iaconfig = ApacheConfigFactory.getApacheConfig();
/* 431 */       iaconfig.addSite(site_infos, apacheConfig_name);
/* 432 */       iaconfig.reset();
/* 433 */       return true;
/*     */     }
/*     */     catch (Exception e) {
/* 436 */       e.printStackTrace();
/* 437 */     }return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateSiteInfoToApache(Map<String, String> site_infos)
/*     */   {
/*     */     try
/*     */     {
/* 449 */       IApacheConfig iaconfig = ApacheConfigFactory.getApacheConfig();
/* 450 */       iaconfig.updateSite(site_infos);
/* 451 */       iaconfig.reset();
/* 452 */       return true;
/*     */     }
/*     */     catch (Exception e) {
/* 455 */       e.printStackTrace();
/* 456 */     }return false;
/*     */   }
/*     */ 
/*     */   public static boolean stopSiteInfoToApache(Map<String, String> site_infos, String apacheConfig_name)
/*     */   {
/*     */     try
/*     */     {
/* 469 */       IApacheConfig iaconfig = ApacheConfigFactory.getApacheConfig();
/*     */ 
/* 471 */       iaconfig.delMultiVhost((String)site_infos.get("site_id"));
/*     */ 
/* 473 */       iaconfig.addMultiVhost(site_infos, apacheConfig_name);
/* 474 */       iaconfig.reset();
/* 475 */       return true;
/*     */     }
/*     */     catch (Exception e) {
/* 478 */       e.printStackTrace();
/* 479 */     }return false;
/*     */   }
/*     */ 
/*     */   public static boolean delSiteByApache(Map<String, String> site_infos)
/*     */   {
/*     */     try
/*     */     {
/* 491 */       IApacheConfig iaconfig = ApacheConfigFactory.getApacheConfig();
/* 492 */       iaconfig.delVhost(site_infos);
/* 493 */       iaconfig.reset();
/* 494 */       return true;
/*     */     }
/*     */     catch (Exception e) {
/* 497 */       e.printStackTrace();
/* 498 */     }return false;
/*     */   }
/*     */ 
/*     */   public static void addAliasToServer(Map<String, String> site_infos)
/*     */   {
/*     */     try
/*     */     {
/* 510 */       IServerConfig isconfig = ServerConfigFactory.getServerConfig();
/* 511 */       isconfig.addAlias(site_infos);
/*     */     }
/*     */     catch (Exception e) {
/* 514 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void deleteAliasToServer(Map<String, String> site_infos)
/*     */   {
/*     */     try
/*     */     {
/* 527 */       IServerConfig isconfig = ServerConfigFactory.getServerConfig();
/* 528 */       isconfig.delAlias(site_infos);
/*     */     }
/*     */     catch (Exception e) {
/* 531 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public static byte[] copySiteResource(String s_site_id)
/*     */   {
/* 543 */     SiteBean s_sb = SiteManager.getSiteBeanBySiteID(s_site_id);
/* 544 */     String copy_dir = FormatUtil.formatPath(new StringBuilder(String.valueOf(s_sb.getSite_path())).append("/images").toString()) + "," + FormatUtil.formatPath(new StringBuilder(String.valueOf(s_sb.getSite_path())).append("/js").toString()) + "," + FormatUtil.formatPath(new StringBuilder(String.valueOf(s_sb.getSite_path())).append("/styles").toString());
/* 545 */     JarManager.compress("resource.jar", copy_dir, FormatUtil.formatPath(s_sb.getSite_path() + "/ROOT"), s_sb.getSite_path());
/*     */     try {
/* 547 */       byte[] b = FileOperation.readFileToBytes(FormatUtil.formatPath(s_sb.getSite_path() + "/resource.jar"));
/* 548 */       File f = new File(FormatUtil.formatPath(FormatUtil.formatPath(s_sb.getSite_path() + "/resource.jar")));
/* 549 */       f.delete();
/* 550 */       return b;
/*     */     }
/*     */     catch (IOException e) {
/* 553 */       e.printStackTrace();
/* 554 */     }return null;
/*     */   }
/*     */ 
/*     */   public static void cloneSite(String site_id, String s_site_id)
/*     */   {
/* 567 */     JconfigUtil bc = JconfigFactory.getJconfigUtilInstance("startListener");
/* 568 */     String[] class_arr = bc.getPropertyNamesByCategory("clonesiteclass");
/* 569 */     if ((class_arr != null) && (class_arr.length > 0))
/*     */     {
/* 571 */       for (int i = class_arr.length; i > 0; i--)
/*     */       {
/* 573 */         String class_path = bc.getProperty(class_arr[(i - 1)], "", "clonesiteclass");
/*     */         try {
/* 575 */           Class c = Class.forName(class_path);
/* 576 */           ICloneSite ics = (ICloneSite)c.newInstance();
/* 577 */           ics.cloneSite(site_id, s_site_id);
/*     */         }
/*     */         catch (InstantiationException e) {
/* 580 */           e.printStackTrace();
/*     */         }
/*     */         catch (IllegalAccessException e) {
/* 583 */           e.printStackTrace();
/*     */         }
/*     */         catch (ClassNotFoundException e) {
/* 586 */           e.printStackTrace();
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/* 594 */     JconfigUtil bc = JconfigFactory.getJconfigUtilInstance("startListener");
/* 595 */     String[] class_arr = bc.getPropertyNamesByCategory("clonesiteclass");
/* 596 */     if ((class_arr != null) && (class_arr.length > 0))
/*     */     {
/* 598 */       for (int i = class_arr.length; i > 0; i--)
/*     */       {
/* 600 */         String class_path = bc.getProperty(class_arr[(i - 1)], "", "clonesiteclass");
/* 601 */         System.out.println(class_arr[(i - 1)] + "   " + bc.getProperty(class_arr[(i - 1)], "", "clonesiteclass"));
/*     */       }
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.control.site.SiteOperation
 * JD-Core Version:    0.6.2
 */