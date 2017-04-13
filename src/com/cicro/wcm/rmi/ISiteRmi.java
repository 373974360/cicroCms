package com.cicro.wcm.rmi;

import com.cicro.wcm.bean.control.SiteBean;
import com.cicro.wcm.bean.control.SiteDomainBean;
import com.cicro.wcm.bean.logs.SettingLogsBean;
import java.rmi.Remote;
import java.rmi.RemoteException;

public abstract interface ISiteRmi extends Remote
{
  public abstract boolean addSite(SiteBean paramSiteBean, SettingLogsBean paramSettingLogsBean)
    throws RemoteException;

  public abstract boolean updateSite(SiteBean paramSiteBean, SettingLogsBean paramSettingLogsBean)
    throws RemoteException;

  public abstract boolean updateSiteStatus(String paramString, int paramInt, SettingLogsBean paramSettingLogsBean)
    throws RemoteException;

  public abstract boolean deleteSite(String paramString, SettingLogsBean paramSettingLogsBean)
    throws RemoteException;

  public abstract boolean addAlias(SiteDomainBean paramSiteDomainBean, SettingLogsBean paramSettingLogsBean)
    throws RemoteException;

  public abstract boolean updateAlias(SiteDomainBean paramSiteDomainBean, SettingLogsBean paramSettingLogsBean)
    throws RemoteException;

  public abstract boolean deleteAlias(String paramString, SettingLogsBean paramSettingLogsBean)
    throws RemoteException;

  public abstract boolean addSiteInResourceServer(String paramString)
    throws RemoteException;

  public abstract byte[] copySiteResource(String paramString)
    throws RemoteException;

  public abstract boolean updateHitForSite(String paramString, int paramInt)
    throws RemoteException;
}

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.rmi.ISiteRmi
 * JD-Core Version:    0.6.2
 */