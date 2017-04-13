/*     */ package com.cicro.wcm.services.control.site;
/*     */ 
/*     */ import com.cicro.util.jconfig.JconfigUtil;
/*     */ import com.cicro.util.jconfig.JconfigUtilContainer;
/*     */ import com.cicro.wcm.bean.control.SiteConfigBean;
/*     */ import com.cicro.wcm.bean.control.SiteVisitCountBean;
/*     */ import com.cicro.wcm.dao.PublicTableDAO;
/*     */ import com.cicro.wcm.dao.control.SiteVisitCountDAO;
/*     */ import com.cicro.wcm.services.control.config.SiteConfigManager;
/*     */ import com.cicro.wcm.services.control.domain.SiteDomainManager;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ 
/*     */ public class SiteVisitCountManager
/*     */ {
/*  29 */   private static Map<String, SiteVisitCountBean> count_map = new HashMap();
/*  30 */   private static int DEFAULT_FREQUENCY = Integer.parseInt(JconfigUtilContainer.bashConfig().getProperty("num", "30", "site_count_freq"));
/*     */ 
/*     */   static {
/*  33 */     reloadSiteVisitCount();
/*     */   }
/*     */ 
/*     */   public static void reloadSiteVisitCount()
/*     */   {
/*  38 */     count_map.clear();
/*  39 */     List l = SiteVisitCountDAO.getAllSiteVisitCount();
/*  40 */     if ((l != null) && (l.size() > 0))
/*     */     {
/*  42 */       for (SiteVisitCountBean svcb : l)
/*     */       {
/*  44 */         SiteConfigBean scb = SiteConfigManager.getConfigValues(svcb.getSite_id(), "click_step");
/*  45 */         if ((scb != null) && (scb.getConfig_value() != null) && (!"".equals(scb.getConfig_value())))
/*  46 */           svcb.setClick_step(Integer.parseInt(scb.getConfig_value()));
/*  47 */         svcb.setIs_exist(true);
/*  48 */         count_map.put(svcb.getSite_id(), svcb);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public static int getHit(String hit_type, HttpServletRequest request)
/*     */   {
/*  61 */     String site_id = SiteDomainManager.getSiteIDByUrl(request.getRequestURL().toString());
/*  62 */     if (hit_type.equals("day_hits"))
/*     */     {
/*  64 */       return ((SiteVisitCountBean)count_map.get(site_id)).getDay_hits();
/*     */     }
/*  66 */     if (hit_type.equals("week_hits"))
/*     */     {
/*  68 */       return ((SiteVisitCountBean)count_map.get(site_id)).getWeek_hits();
/*     */     }
/*  70 */     if (hit_type.equals("month_hits"))
/*     */     {
/*  72 */       return ((SiteVisitCountBean)count_map.get(site_id)).getMonth_hits();
/*     */     }
/*  74 */     return ((SiteVisitCountBean)count_map.get(site_id)).getHits();
/*     */   }
/*     */ 
/*     */   public static boolean updateHitForSite(String site_id, int count)
/*     */   {
/*     */     try
/*     */     {
/*  86 */       if (count_map.containsKey(site_id))
/*     */       {
/*  88 */         ((SiteVisitCountBean)count_map.get(site_id)).setHits(count);
/*     */       }
/*     */       else {
/*  91 */         SiteVisitCountBean svcb = new SiteVisitCountBean();
/*  92 */         svcb.setHits(count);
/*  93 */         svcb.setSite_id(site_id);
/*  94 */         count_map.put(site_id, svcb);
/*     */       }
/*  96 */       return true;
/*     */     }
/*     */     catch (Exception e) {
/*  99 */       e.printStackTrace();
/* 100 */     }return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateStep(String site_id, int step)
/*     */   {
/*     */     try
/*     */     {
/* 113 */       if (count_map.containsKey(site_id))
/*     */       {
/* 115 */         ((SiteVisitCountBean)count_map.get(site_id)).setClick_step(step);
/*     */       }
/*     */       else {
/* 118 */         SiteVisitCountBean svcb = new SiteVisitCountBean();
/* 119 */         svcb.setSite_id(site_id);
/* 120 */         svcb.setClick_step(step);
/* 121 */         count_map.put(site_id, svcb);
/*     */       }
/* 123 */       return true;
/*     */     }
/*     */     catch (Exception e) {
/* 126 */       e.printStackTrace();
/* 127 */     }return false;
/*     */   }
/*     */ 
/*     */   public static void addSiteHits(HttpServletRequest request)
/*     */   {
/* 138 */     String site_id = SiteDomainManager.getSiteIDByUrl(request.getRequestURL().toString());
/* 139 */     if (count_map.containsKey(site_id))
/*     */     {
/* 141 */       addSiteHits(site_id);
/*     */     }
/*     */     else {
/* 144 */       SiteVisitCountBean svcb = new SiteVisitCountBean();
/* 145 */       svcb.setSite_id(site_id);
/* 146 */       svcb.setDay_hits(svcb.getClick_step());
/* 147 */       svcb.setHits(svcb.getHits() + svcb.getClick_step());
/* 148 */       svcb.setMonth_hits(svcb.getClick_step());
/* 149 */       svcb.setWeek_hits(svcb.getClick_step());
/* 150 */       count_map.put(site_id, svcb);
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void addSiteHits(String site_id)
/*     */   {
/* 161 */     SiteVisitCountBean svcb = (SiteVisitCountBean)count_map.get(site_id);
/* 162 */     svcb.setTemp_count(svcb.getTemp_count() + 1);
/* 163 */     svcb.setDay_hits(svcb.getDay_hits() + svcb.getClick_step());
/* 164 */     svcb.setHits(svcb.getHits() + svcb.getClick_step());
/* 165 */     svcb.setMonth_hits(svcb.getMonth_hits() + svcb.getClick_step());
/* 166 */     svcb.setWeek_hits(svcb.getWeek_hits() + svcb.getClick_step());
/*     */ 
/* 168 */     if (svcb.getTemp_count() == DEFAULT_FREQUENCY)
/*     */     {
/* 171 */       int count = svcb.getTemp_count() * 1;
/* 172 */       Map m = new HashMap();
/* 173 */       m.put("site_id", site_id);
/* 174 */       m.put("app_id", "cms");
/* 175 */       m.put("hits", svcb.getHits());
/* 176 */       m.put("h_count", count);
/* 177 */       if (svcb.getIs_exist())
/*     */       {
/* 179 */         if (SiteVisitCountDAO.updateSiteHits(m));
/* 181 */         svcb.setTemp_count(0);
/*     */       }
/*     */       else
/*     */       {
/* 185 */         m.put("id", PublicTableDAO.getIDByTableName(PublicTableDAO.SITECOUNT_TABLE_NAME));
/* 186 */         if (SiteVisitCountDAO.insertSiteHits(m));
/* 188 */         svcb.setTemp_count(0);
/* 189 */         svcb.setIs_exist(true);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void clearHits(String hit_type)
/*     */   {
/* 202 */     Map m = new HashMap();
/* 203 */     if (hit_type.equals("week"))
/*     */     {
/* 205 */       m.put("week", "true");
/*     */     }
/* 207 */     if (hit_type.equals("month"))
/*     */     {
/* 209 */       m.put("month", "true");
/*     */     }
/* 211 */     SiteVisitCountDAO.clearSiteHits(m);
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.control.site.SiteVisitCountManager
 * JD-Core Version:    0.6.2
 */