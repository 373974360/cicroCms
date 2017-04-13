/*    */ package com.cicro.wcm.services.control.rmi;
/*    */ 
/*    */ import com.cicro.wcm.bean.control.SiteBean;
/*    */ import com.cicro.wcm.bean.control.SiteDomainBean;
/*    */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*    */ import com.cicro.wcm.rmi.ISiteRmi;
/*    */ import com.cicro.wcm.services.control.site.SiteOperation;
/*    */ import com.cicro.wcm.services.control.site.SiteVisitCountManager;
/*    */ import java.rmi.RemoteException;
/*    */ import java.rmi.server.UnicastRemoteObject;
/*    */ 
/*    */ public class SiteRmiImpl extends UnicastRemoteObject
/*    */   implements ISiteRmi
/*    */ {
/*    */   private static final long serialVersionUID = -8865285105158846507L;
/*    */ 
/*    */   public SiteRmiImpl()
/*    */     throws RemoteException
/*    */   {
/*    */   }
/*    */ 
/*    */   public boolean addSite(SiteBean sb, SettingLogsBean stl)
/*    */     throws RemoteException
/*    */   {
/* 33 */     return SiteOperation.addSite(sb, stl);
/*    */   }
/*    */ 
/*    */   public boolean updateSite(SiteBean sb, SettingLogsBean stl) throws RemoteException {
/* 37 */     return SiteOperation.updateSite(sb, stl);
/*    */   }
/*    */ 
/*    */   public boolean updateSiteStatus(String site_id, int site_status, SettingLogsBean stl) throws RemoteException {
/* 41 */     return SiteOperation.updateSiteStatus(site_id, site_status, stl);
/*    */   }
/*    */ 
/*    */   public boolean deleteSite(String site_id, SettingLogsBean stl) throws RemoteException {
/* 45 */     return SiteOperation.deleteSite(site_id, stl);
/*    */   }
/*    */ 
/*    */   public boolean addAlias(SiteDomainBean sdb, SettingLogsBean stl) throws RemoteException {
/* 49 */     return SiteOperation.addAlias(sdb, stl);
/*    */   }
/*    */ 
/*    */   public boolean updateAlias(SiteDomainBean sdb, SettingLogsBean stl) throws RemoteException {
/* 53 */     return SiteOperation.updateAlias(sdb, stl);
/*    */   }
/*    */ 
/*    */   public boolean deleteAlias(String domain_ids, SettingLogsBean stl) throws RemoteException {
/* 57 */     return SiteOperation.deleteAlias(domain_ids, stl);
/*    */   }
/*    */ 
/*    */   public boolean addSiteInResourceServer(String site_id) throws RemoteException {
/* 61 */     return SiteOperation.addSiteInResourceServer(site_id);
/*    */   }
/*    */ 
/*    */   public byte[] copySiteResource(String site_id) throws RemoteException {
/* 65 */     return SiteOperation.copySiteResource(site_id);
/*    */   }
/*    */ 
/*    */   public boolean updateHitForSite(String site_id, int count)
/*    */   {
/* 76 */     return SiteVisitCountManager.updateHitForSite(site_id, count);
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.control.rmi.SiteRmiImpl
 * JD-Core Version:    0.6.2
 */