/*     */ package com.cicro.wcm.dao.control;
/*     */ 
/*     */ import com.cicro.util.DateUtil;
/*     */ import com.cicro.wcm.bean.control.SiteBean;
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.dao.PublicTableDAO;
/*     */ import com.cicro.wcm.db.DBManager;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class SiteDAO
/*     */ {
/*     */   public static List<SiteBean> getAllSiteList()
/*     */   {
/*  33 */     return DBManager.queryFList("getAllSiteList", "");
/*     */   }
/*     */ 
/*     */   public static SiteBean getSiteBean(String site_id)
/*     */   {
/*  43 */     return (SiteBean)DBManager.queryFObj("getSiteBean", site_id);
/*     */   }
/*     */ 
/*     */   public static boolean insertSite(SiteBean sb, SettingLogsBean stl)
/*     */   {
/*  54 */     if (DBManager.insert("insert_site", sb))
/*     */     {
/*  56 */       PublicTableDAO.insertSettingLogs("添加", "站点", sb.getSite_id(), stl);
/*  57 */       return true;
/*     */     }
/*     */ 
/*  60 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateSite(SiteBean sb, SettingLogsBean stl)
/*     */   {
/*  72 */     if (DBManager.update("update_site", sb))
/*     */     {
/*  74 */       PublicTableDAO.insertSettingLogs("修改", "站点", sb.getSite_id(), stl);
/*  75 */       return true;
/*     */     }
/*     */ 
/*  78 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateSiteStatus(String site_id, int site_status, SettingLogsBean stl)
/*     */   {
/*  92 */     Map m = new HashMap();
/*  93 */     m.put("site_id", site_id);
/*  94 */     m.put("site_status", Integer.valueOf(site_status));
/*  95 */     if (site_status == 1)
/*     */     {
/*  97 */       m.put("site_pausetime", DateUtil.getCurrentDateTime());
/*     */     }
/*     */     else {
/* 100 */       m.put("site_pausetime", "");
/*     */     }
/* 102 */     if (DBManager.update("update_site_status", m))
/*     */     {
/* 104 */       PublicTableDAO.insertSettingLogs("修改", "站点", site_id, stl);
/* 105 */       return true;
/*     */     }
/*     */ 
/* 108 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean saveSiteSort(String site_ids, SettingLogsBean stl)
/*     */   {
/* 122 */     if ((site_ids != null) && (!"".equals(site_ids))) {
/*     */       try
/*     */       {
/* 125 */         Map m = new HashMap();
/* 126 */         String[] tempA = site_ids.split(",");
/* 127 */         for (int i = 0; i < tempA.length; i++)
/*     */         {
/* 129 */           m.put("site_sort", Integer.valueOf(i + 1));
/* 130 */           m.put("site_id", tempA[i]);
/* 131 */           DBManager.update("save_site_sort", m);
/*     */         }
/* 133 */         PublicTableDAO.insertSettingLogs("保存排序", "站点", "", stl);
/* 134 */         return true;
/*     */       }
/*     */       catch (Exception e) {
/* 137 */         e.printStackTrace();
/* 138 */         return false;
/*     */       }
/*     */     }
/*     */ 
/* 142 */     return true;
/*     */   }
/*     */ 
/*     */   public static boolean deleteSite(String site_id, SettingLogsBean stl)
/*     */   {
/* 154 */     if (DBManager.update("delete_site", site_id))
/*     */     {
/* 156 */       PublicTableDAO.insertSettingLogs("删除", "站点", site_id, stl);
/* 157 */       return true;
/*     */     }
/*     */ 
/* 160 */     return false;
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.dao.control.SiteDAO
 * JD-Core Version:    0.6.2
 */