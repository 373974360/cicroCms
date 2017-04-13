/*     */ package com.cicro.wcm.services.control.domain;
/*     */ 
/*     */ import com.cicro.util.FormatUtil;
/*     */ import com.cicro.wcm.bean.control.SiteDomainBean;
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.catchs.ISyncCatch;
/*     */ import com.cicro.wcm.catchs.SyncCatchHandl;
/*     */ import com.cicro.wcm.dao.control.SiteDomainDAO;
/*     */ import com.cicro.wcm.services.control.site.SiteManager;
/*     */ import java.io.PrintStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ 
/*     */ public class SiteDomainManager
/*     */   implements ISyncCatch
/*     */ {
/*  18 */   private static Map<String, List<SiteDomainBean>> domain_map = new HashMap();
/*     */ 
/*     */   static
/*     */   {
/*  27 */     reloadCatchHandl();
/*     */   }
/*     */ 
/*     */   public void reloadCatch()
/*     */   {
/*  32 */     reloadCatchHandl();
/*     */   }
/*     */ 
/*     */   public static void reloadCatchHandl()
/*     */   {
/*  37 */     domain_map.clear();
/*  38 */     List l = SiteDomainDAO.getSiteDomainList();
/*  39 */     if ((l != null) && (l.size() > 0))
/*     */     {
/*  41 */       for (int i = 0; i < l.size(); i++)
/*     */       {
/*  43 */         if (domain_map.containsKey(((SiteDomainBean)l.get(i)).getSite_id()))
/*     */         {
/*  45 */           List sl = (List)domain_map.get(((SiteDomainBean)l.get(i)).getSite_id());
/*  46 */           sl.add((SiteDomainBean)l.get(i));
/*  47 */           domain_map.put(((SiteDomainBean)l.get(i)).getSite_id(), sl);
/*     */         }
/*     */         else {
/*  50 */           List sl = new ArrayList();
/*  51 */           sl.add((SiteDomainBean)l.get(i));
/*  52 */           domain_map.put(((SiteDomainBean)l.get(i)).getSite_id(), sl);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void reloadSiteDomainList()
/*     */   {
/*  68 */     SyncCatchHandl.reladCatchForRMI("com.cicro.wcm.services.control.domain.SiteDomainManager");
/*     */   }
/*     */ 
/*     */   public static List<SiteDomainBean> getDomainListBySiteID(String site_id)
/*     */   {
/*  79 */     return (List)domain_map.get(site_id);
/*     */   }
/*     */ 
/*     */   public static SiteDomainBean getSiteDomainBeanByID(String domain_id)
/*     */   {
/*  90 */     Set set = domain_map.keySet();
/*     */     Iterator localIterator2;
/*  91 */     for (Iterator localIterator1 = set.iterator(); localIterator1.hasNext(); 
/*  94 */       localIterator2.hasNext())
/*     */     {
/*  91 */       String s = (String)localIterator1.next();
/*     */ 
/*  93 */       List dList = (List)domain_map.get(s);
/*  94 */       localIterator2 = dList.iterator(); continue; SiteDomainBean sb = (SiteDomainBean)localIterator2.next();
/*     */ 
/*  96 */       if (domain_id.equals(sb.getDomain_id()))
/*     */       {
/*  98 */         return sb;
/*     */       }
/*     */     }
/*     */ 
/* 102 */     return null;
/*     */   }
/*     */ 
/*     */   public static String getSiteIDByDomain(String site_domain)
/*     */   {
/* 114 */     String site_id = "";
/* 115 */     Set set = domain_map.keySet();
/*     */     Iterator localIterator2;
/* 116 */     for (Iterator localIterator1 = set.iterator(); localIterator1.hasNext(); 
/* 119 */       localIterator2.hasNext())
/*     */     {
/* 116 */       String s = (String)localIterator1.next();
/*     */ 
/* 118 */       List dList = (List)domain_map.get(s);
/* 119 */       localIterator2 = dList.iterator(); continue; SiteDomainBean sb = (SiteDomainBean)localIterator2.next();
/*     */ 
/* 121 */       if (site_domain.equals(sb.getSite_domain()))
/*     */       {
/* 123 */         site_id = sb.getSite_id();
/*     */       }
/*     */     }
/*     */ 
/* 127 */     return site_id;
/*     */   }
/*     */ 
/*     */   public static String getSiteIDByUrl(String url)
/*     */   {
/* 138 */     return getSiteIDByDomain(FormatUtil.getDomainForUrl(url));
/*     */   }
/*     */ 
/*     */   public static SiteDomainBean getSiteDomainBeanByName(String site_domain)
/*     */   {
/* 150 */     Set set = domain_map.keySet();
/*     */     Iterator localIterator2;
/* 151 */     for (Iterator localIterator1 = set.iterator(); localIterator1.hasNext(); 
/* 154 */       localIterator2.hasNext())
/*     */     {
/* 151 */       String s = (String)localIterator1.next();
/*     */ 
/* 153 */       List dList = (List)domain_map.get(s);
/* 154 */       localIterator2 = dList.iterator(); continue; SiteDomainBean sb = (SiteDomainBean)localIterator2.next();
/*     */ 
/* 156 */       if (site_domain.equals(sb.getSite_domain()))
/*     */       {
/* 158 */         return sb;
/*     */       }
/*     */     }
/*     */ 
/* 162 */     return null;
/*     */   }
/*     */ 
/*     */   public static String getSiteByDomain(String site_domain)
/*     */   {
/* 172 */     SiteDomainBean sdb = getSiteDomainBeanByName(site_domain);
/* 173 */     if (sdb != null) {
/* 174 */       return sdb.getSite_id();
/*     */     }
/* 176 */     return "";
/*     */   }
/*     */ 
/*     */   public static boolean defaultDomainIsExist(String new_site_domain, String site_id)
/*     */   {
/* 188 */     String site_domain = getSiteDomainBySiteID(site_id);
/* 189 */     if (new_site_domain.equals(site_domain))
/*     */     {
/* 191 */       return true;
/*     */     }
/*     */ 
/* 195 */     if (siteDomainISExist(new_site_domain))
/*     */     {
/* 197 */       return false;
/*     */     }
/*     */ 
/* 200 */     return true;
/*     */   }
/*     */ 
/*     */   public static String getSiteDomainBySiteID(String site_id)
/*     */   {
/* 212 */     String domain_name = "";
/* 213 */     List l = getDomainListBySiteID(site_id);
/* 214 */     if ((l != null) && (l.size() > 0))
/*     */     {
/* 216 */       for (SiteDomainBean sdb : l)
/*     */       {
/* 218 */         if ((site_id.equals(sdb.getSite_id())) && (sdb.getIs_host() == 1))
/* 219 */           domain_name = sdb.getSite_domain();
/*     */       }
/*     */     }
/* 222 */     return domain_name;
/*     */   }
/*     */ 
/*     */   public static String getDefaultSiteDomainBySiteID(String site_id)
/*     */   {
/* 233 */     String domain_name = "";
/* 234 */     List l = getDomainListBySiteID(site_id);
/* 235 */     if ((l != null) && (l.size() > 0))
/*     */     {
/* 237 */       for (SiteDomainBean sdb : l)
/*     */       {
/* 239 */         if ((site_id.equals(sdb.getSite_id())) && (sdb.getIs_default() == 1))
/* 240 */           domain_name = sdb.getSite_domain();
/*     */       }
/*     */     }
/* 243 */     if ("".equals(domain_name))
/*     */     {
/* 245 */       return getSiteDomainBySiteID(site_id);
/*     */     }
/* 247 */     return domain_name;
/*     */   }
/*     */ 
/*     */   public static boolean siteDomainISExist(String site_domain)
/*     */   {
/* 258 */     Set set = domain_map.keySet();
/*     */     Iterator localIterator2;
/* 259 */     for (Iterator localIterator1 = set.iterator(); localIterator1.hasNext(); 
/* 262 */       localIterator2.hasNext())
/*     */     {
/* 259 */       String s = (String)localIterator1.next();
/*     */ 
/* 261 */       List dList = (List)domain_map.get(s);
/* 262 */       localIterator2 = dList.iterator(); continue; SiteDomainBean sb = (SiteDomainBean)localIterator2.next();
/*     */ 
/* 264 */       if (site_domain.equals(sb.getSite_domain()))
/*     */       {
/* 266 */         return true;
/*     */       }
/*     */     }
/*     */ 
/* 270 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean siteDomainISExistNoHost(String site_domain)
/*     */   {
/* 281 */     Set set = domain_map.keySet();
/*     */     Iterator localIterator2;
/* 282 */     for (Iterator localIterator1 = set.iterator(); localIterator1.hasNext(); 
/* 285 */       localIterator2.hasNext())
/*     */     {
/* 282 */       String s = (String)localIterator1.next();
/*     */ 
/* 284 */       List dList = (List)domain_map.get(s);
/* 285 */       localIterator2 = dList.iterator(); continue; SiteDomainBean sb = (SiteDomainBean)localIterator2.next();
/*     */ 
/* 287 */       if ((site_domain.equals(sb.getSite_domain())) && ("0".equals(Integer.valueOf(sb.getIs_host()))))
/*     */       {
/* 289 */         return true;
/*     */       }
/*     */     }
/*     */ 
/* 293 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean insertSiteDomain(SiteDomainBean sdb, SettingLogsBean stl)
/*     */   {
/* 304 */     if (!siteDomainISExist(sdb.getSite_domain()))
/*     */     {
/* 306 */       if (SiteDomainDAO.insertSiteDomain(sdb, stl))
/*     */       {
/* 308 */         reloadSiteDomainList();
/* 309 */         SiteManager.reloadSiteList();
/* 310 */         return true;
/*     */       }
/*     */ 
/* 313 */       return false;
/*     */     }
/*     */ 
/* 316 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateSiteDomain(SiteDomainBean sdb, SettingLogsBean stl)
/*     */   {
/* 328 */     SiteDomainBean sdb2 = getSiteDomainBeanByID(sdb.getDomain_id());
/*     */ 
/* 330 */     if (!sdb2.getSite_domain().equals(sdb.getSite_domain()))
/*     */     {
/* 332 */       if (SiteDomainDAO.updateSiteDomain(sdb, stl))
/*     */       {
/* 334 */         reloadSiteDomainList();
/* 335 */         SiteManager.reloadSiteList();
/* 336 */         return true;
/*     */       }
/*     */ 
/* 339 */       return false;
/*     */     }
/* 341 */     return false;
/*     */   }
/*     */ 
/*     */   public static int updateSiteDomainByName(String new_site_domain, String site_id)
/*     */   {
/* 355 */     String site_domain = getSiteDomainBySiteID(site_id);
/*     */ 
/* 357 */     if (!new_site_domain.equals(site_domain))
/*     */     {
/* 359 */       Map m = new HashMap();
/* 360 */       m.put("new_site_domain", new_site_domain);
/* 361 */       m.put("site_domain", site_domain);
/* 362 */       if (SiteDomainDAO.updateSiteDomainByName(m))
/*     */       {
/* 364 */         reloadSiteDomainList();
/* 365 */         SiteManager.reloadSiteList();
/* 366 */         return 1;
/*     */       }
/*     */ 
/* 369 */       return -1;
/*     */     }
/* 371 */     return 0;
/*     */   }
/*     */ 
/*     */   public static boolean updateSDomainStatus(String domain_id, String site_id, SettingLogsBean stl)
/*     */   {
/* 383 */     if (SiteDomainDAO.updateSDomainStatus(domain_id, site_id, stl))
/*     */     {
/* 385 */       reloadSiteDomainList();
/* 386 */       SiteManager.reloadSiteList();
/* 387 */       return true;
/*     */     }
/*     */ 
/* 390 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteSiteDomainByID(String domain_ids, SettingLogsBean stl)
/*     */   {
/* 401 */     Map m = new HashMap();
/* 402 */     m.put("domain_id", domain_ids);
/* 403 */     if (SiteDomainDAO.deleteSiteDomainByID(m, stl))
/*     */     {
/* 405 */       reloadSiteDomainList();
/* 406 */       SiteManager.reloadSiteList();
/* 407 */       return true;
/*     */     }
/*     */ 
/* 410 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteSiteDomainBySiteID(String site_id, SettingLogsBean stl)
/*     */   {
/* 421 */     if (SiteDomainDAO.deleteSiteDomainBySiteID(site_id, stl))
/*     */     {
/* 423 */       reloadSiteDomainList();
/* 424 */       SiteManager.reloadSiteList();
/* 425 */       return true;
/*     */     }
/*     */ 
/* 428 */     return false;
/*     */   }
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/* 434 */     System.out.println(getDomainListBySiteID("HIWCMeeee"));
/*     */   }
/*     */ 
/*     */   public static void testInsertSiteDomain()
/*     */   {
/* 441 */     SiteDomainBean sdb = new SiteDomainBean();
/* 442 */     sdb.setDomain_id(16);
/* 443 */     sdb.setIs_default(1);
/* 444 */     sdb.setSite_domain("www.sx.gov.cn");
/* 445 */     sdb.setSite_id("site_id");
/* 446 */     updateSiteDomain(sdb, new SettingLogsBean());
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.control.domain.SiteDomainManager
 * JD-Core Version:    0.6.2
 */