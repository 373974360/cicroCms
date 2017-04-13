/*     */ package com.cicro.wcm.services.control.group;
/*     */ 
/*     */ import com.cicro.wcm.bean.control.SiteGroupBean;
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.services.Log.LogManager;
/*     */ import java.util.List;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ 
/*     */ public class SiteGroupRPC
/*     */ {
/*     */   public static SiteGroupBean getSGroupRoot(String sgroup_id)
/*     */   {
/*  32 */     return (SiteGroupBean)SiteGroupManager.getSGroupChildListByID(sgroup_id).get(0);
/*     */   }
/*     */ 
/*     */   public static boolean updateSiteGroup(SiteGroupBean sgb, HttpServletRequest request)
/*     */   {
/*  43 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/*  44 */     if (stl != null)
/*     */     {
/*  46 */       if (SiteGroupManager.updateSiteGroup(sgb, stl))
/*     */       {
/*  48 */         return true;
/*     */       }
/*  50 */       return false;
/*     */     }
/*  52 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean insertSiteGroup(SiteGroupBean sgb, HttpServletRequest request)
/*     */   {
/*  63 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/*  64 */     if (stl != null)
/*     */     {
/*  66 */       if (SiteGroupManager.insertSiteGroup(sgb, stl))
/*     */       {
/*  68 */         return true;
/*     */       }
/*  70 */       return false;
/*     */     }
/*  72 */     return false;
/*     */   }
/*     */ 
/*     */   public static String getGroupTreeJsonStr()
/*     */   {
/*  82 */     return SiteGroupManager.getGroupTreeJsonStr();
/*     */   }
/*     */ 
/*     */   public static List<SiteGroupBean> getSGroupChildListByID(String sgroup_id)
/*     */   {
/*  92 */     return SiteGroupManager.getSGroupChildListByID(sgroup_id);
/*     */   }
/*     */ 
/*     */   public static boolean deleteSiteGroup(String sgroup_ids, HttpServletRequest request)
/*     */   {
/* 102 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 103 */     if (stl != null)
/*     */     {
/* 105 */       if (SiteGroupManager.deleteSiteGroup(sgroup_ids, stl))
/*     */       {
/* 107 */         return true;
/*     */       }
/* 109 */       return false;
/*     */     }
/* 111 */     return false;
/*     */   }
/*     */ 
/*     */   public static int getSGroupSortByID(String sgroup_id)
/*     */   {
/* 121 */     return SiteGroupManager.getSGroupSortByID(sgroup_id);
/*     */   }
/*     */ 
/*     */   public static boolean saveSGroupSort(String sgroup_ids, HttpServletRequest request)
/*     */   {
/* 132 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 133 */     if (stl != null)
/*     */     {
/* 135 */       if (SiteGroupManager.saveSGroupSort(sgroup_ids, stl))
/*     */       {
/* 137 */         return true;
/*     */       }
/* 139 */       return false;
/*     */     }
/* 141 */     return false;
/*     */   }
/*     */ 
/*     */   public static SiteGroupBean getSGroupBeanByID(String sgroup_id)
/*     */   {
/* 155 */     return SiteGroupManager.getSGroupBeanByID(sgroup_id);
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.control.group.SiteGroupRPC
 * JD-Core Version:    0.6.2
 */