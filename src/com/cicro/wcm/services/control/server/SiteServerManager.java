/*     */ package com.cicro.wcm.services.control.server;
/*     */ 
/*     */ import com.cicro.wcm.bean.control.SiteBean;
/*     */ import com.cicro.wcm.bean.control.SiteServerBean;
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.catchs.ISyncCatch;
/*     */ import com.cicro.wcm.catchs.SyncCatchHandl;
/*     */ import com.cicro.wcm.dao.control.SiteServerDAO;
/*     */ import com.cicro.wcm.server.ServerManager;
/*     */ import com.cicro.wcm.services.control.site.SiteManager;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class SiteServerManager
/*     */   implements ISyncCatch
/*     */ {
/*  28 */   private static List<SiteServerBean> server_list = new ArrayList();
/*  29 */   public static boolean IS_MUTILPUBLISHSERVER = false;
/*     */ 
/*     */   static {
/*  32 */     reloadCatchHandl();
/*     */   }
/*     */ 
/*     */   public void reloadCatch()
/*     */   {
/*  37 */     reloadCatchHandl();
/*     */   }
/*     */ 
/*     */   public static void reloadCatchHandl()
/*     */   {
/*  42 */     server_list.clear();
/*  43 */     server_list = SiteServerDAO.getSiteServerList();
/*     */ 
/*  45 */     IS_MUTILPUBLISHSERVER = isMultiPublishServer();
/*     */   }
/*     */ 
/*     */   public static void reloadServerList()
/*     */   {
/*  56 */     SyncCatchHandl.reladCatchForRMI("com.cicro.wcm.services.control.server.SiteServerManager");
/*     */   }
/*     */ 
/*     */   public static List<SiteServerBean> getSiteServerListByPage(Map<String, String> m)
/*     */   {
/*  65 */     return SiteServerDAO.getSiteServerListByPage(m);
/*     */   }
/*     */ 
/*     */   public static List<SiteServerBean> getServerList()
/*     */   {
/*  78 */     return server_list;
/*     */   }
/*     */ 
/*     */   public static List<SiteServerBean> getServerListBySType(int server_type)
/*     */   {
/*  90 */     List l = new ArrayList();
/*  91 */     if ((server_list != null) && (server_list.size() > 0))
/*     */     {
/*  93 */       for (int i = 0; i < server_list.size(); i++)
/*     */       {
/*  95 */         if (server_type == ((SiteServerBean)server_list.get(i)).getServer_type())
/*  96 */           l.add((SiteServerBean)server_list.get(i));
/*     */       }
/*     */     }
/*  99 */     return l;
/*     */   }
/*     */ 
/*     */   public static boolean isMultiPublishServer()
/*     */   {
/* 109 */     List l = getServerListBySType(2);
/* 110 */     if ((l != null) && (l.size() > 0))
/*     */     {
/* 112 */       return l.size() != 1;
/*     */     }
/* 114 */     return false;
/*     */   }
/*     */ 
/*     */   public static String getResourceServerIP()
/*     */   {
/* 125 */     List l = getServerListBySType(4);
/* 126 */     if ((l != null) && (l.size() > 0))
/*     */     {
/* 128 */       return ((SiteServerBean)l.get(0)).getServer_ip();
/*     */     }
/* 130 */     return "";
/*     */   }
/*     */ 
/*     */   public static SiteServerBean getServerBeanByID(String server_id)
/*     */   {
/* 142 */     SiteServerBean sb = new SiteServerBean();
/* 143 */     if ((server_list != null) && (server_list.size() > 0))
/*     */     {
/* 145 */       for (int i = 0; i < server_list.size(); i++)
/*     */       {
/* 147 */         if (server_id.equals(((SiteServerBean)server_list.get(i)).getServer_id())) {
/* 148 */           sb = (SiteServerBean)server_list.get(i);
/*     */         }
/*     */       }
/*     */     }
/* 152 */     return sb;
/*     */   }
/*     */ 
/*     */   public static boolean insertSiteServer(SiteServerBean ssb, SettingLogsBean stl)
/*     */   {
/* 163 */     if (SiteServerDAO.insertSiteServer(ssb, stl))
/*     */     {
/* 165 */       reloadServerList();
/* 166 */       return true;
/*     */     }
/* 168 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateSiteServer(SiteServerBean ssb, SettingLogsBean stl)
/*     */   {
/* 179 */     if (SiteServerDAO.updateSiteServer(ssb, stl))
/*     */     {
/* 181 */       reloadServerList();
/* 182 */       return true;
/*     */     }
/* 184 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteSiteServer(String server_ids, SettingLogsBean stl)
/*     */   {
/* 195 */     if (SiteServerDAO.deleteSiteServer(server_ids, stl))
/*     */     {
/* 197 */       reloadServerList();
/* 198 */       return true;
/*     */     }
/* 200 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean isTheSameServer(String site_id)
/*     */   {
/* 210 */     SiteBean si = SiteManager.getSiteBeanBySiteID(site_id);
/* 211 */     if (si == null) {
/* 212 */       return true;
/*     */     }
/* 214 */     return getServerBeanByID(si.getServer_id()).getServer_ip().equals(ServerManager.LOCAL_IP);
/*     */   }
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/* 222 */     reloadServerList();
/*     */   }
/*     */ 
/*     */   public static void insertSiteServer()
/*     */   {
/* 227 */     SiteServerBean ssb = new SiteServerBean();
/* 228 */     ssb.setServer_id(1);
/* 229 */     ssb.setServer_ip("192.168.12.18");
/* 230 */     ssb.setServer_memo("resource");
/* 231 */     ssb.setServer_name("resource");
/* 232 */     ssb.setServer_type(4);
/* 233 */     insertSiteServer(ssb, new SettingLogsBean());
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.control.server.SiteServerManager
 * JD-Core Version:    0.6.2
 */