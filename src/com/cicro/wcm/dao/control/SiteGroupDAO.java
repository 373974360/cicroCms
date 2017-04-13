/*     */ package com.cicro.wcm.dao.control;
/*     */ 
/*     */ import com.cicro.wcm.bean.control.SiteGroupBean;
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.dao.PublicTableDAO;
/*     */ import com.cicro.wcm.db.DBManager;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class SiteGroupDAO
/*     */ {
/*     */   public static List getSiteGroupList()
/*     */   {
/*  32 */     return DBManager.queryFList("getSiteGroupList", "");
/*     */   }
/*     */ 
/*     */   public static boolean insertSiteGroup(SiteGroupBean sgb, SettingLogsBean stl)
/*     */   {
/*  43 */     if (DBManager.insert("insert_site_group", sgb))
/*     */     {
/*  45 */       PublicTableDAO.insertSettingLogs("添加", "网站群", sgb.getSgroup_id(), stl);
/*  46 */       return true;
/*     */     }
/*     */ 
/*  49 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateSiteGroup(SiteGroupBean sgb, SettingLogsBean stl)
/*     */   {
/*  62 */     if (DBManager.update("update_site_group", sgb))
/*     */     {
/*  64 */       PublicTableDAO.insertSettingLogs("修改", "网站群", sgb.getSgroup_id(), stl);
/*  65 */       return true;
/*     */     }
/*     */ 
/*  68 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean saveSGroupSort(String sgroup_ids, SettingLogsBean stl)
/*     */   {
/*  81 */     if ((sgroup_ids != null) && (!"".equals(sgroup_ids))) {
/*     */       try
/*     */       {
/*  84 */         Map m = new HashMap();
/*  85 */         String[] tempA = sgroup_ids.split(",");
/*  86 */         for (int i = 0; i < tempA.length; i++)
/*     */         {
/*  88 */           m.put("sgroup_sort", Integer.valueOf(i + 1));
/*  89 */           m.put("sgroup_id", tempA[i]);
/*  90 */           DBManager.update("save_site_group_sort", m);
/*     */         }
/*  92 */         PublicTableDAO.insertSettingLogs("保存排序", "网站群", sgroup_ids, stl);
/*  93 */         return true;
/*     */       }
/*     */       catch (Exception e)
/*     */       {
/*  97 */         e.printStackTrace();
/*  98 */         return false;
/*     */       }
/*     */     }
/*     */ 
/* 102 */     return true;
/*     */   }
/*     */ 
/*     */   public static boolean deleteSiteGroup(String sgroup_id, SettingLogsBean stl)
/*     */   {
/* 113 */     if (DBManager.delete("delete_site_group", sgroup_id))
/*     */     {
/* 115 */       PublicTableDAO.insertSettingLogs("删除", "网站群", sgroup_id, stl);
/* 116 */       return true;
/*     */     }
/*     */ 
/* 119 */     return false;
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.dao.control.SiteGroupDAO
 * JD-Core Version:    0.6.2
 */