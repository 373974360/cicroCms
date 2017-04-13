package com.cicro.wcm.rmi;

import com.cicro.wcm.bean.cms.info.InfoBean;
import com.cicro.wcm.bean.logs.SettingLogsBean;
import com.cicro.wcm.bean.page.PageBean;
import com.cicro.wcm.bean.system.template.TemplateEditBean;
import com.cicro.wcm.bean.system.template.TemplateResourcesBean;
import com.cicro.wcm.bean.system.ware.WareBean;
import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

public abstract interface IFileRmi extends Remote
{
  public abstract boolean saveFile(String paramString1, String paramString2)
    throws RemoteException;

  public abstract boolean saveFile(String paramString1, String paramString2, String paramString3)
    throws RemoteException;

  public abstract boolean delFile(String paramString)
    throws RemoteException;

  public abstract boolean delDir(String paramString)
    throws RemoteException;

  public abstract boolean createPage(PageBean paramPageBean)
    throws RemoteException;

  public abstract String getFolderJSONStr(String paramString)
    throws RemoteException;

  public abstract void delSearchIndex(String paramString)
    throws RemoteException;

  public abstract List<TemplateResourcesBean> getResImageListBySiteID(String paramString)
    throws RemoteException;

  public abstract List<TemplateResourcesBean> getResourcesListBySiteID(String paramString)
    throws RemoteException;

  public abstract boolean addTemplateResourcesFolder(String paramString)
    throws RemoteException;

  public abstract boolean deleteTemplateResources(String paramString)
    throws RemoteException;

  public abstract boolean updateResourcesFile(String paramString1, String paramString2)
    throws RemoteException, IOException;

  public abstract String getResourcesFileContent(String paramString)
    throws RemoteException, IOException;

  public abstract boolean saveTemplateFile(TemplateEditBean paramTemplateEditBean)
    throws RemoteException;

  public abstract void createWarePage(WareBean paramWareBean)
    throws RemoteException;

  public abstract boolean insertInfo(Object paramObject, String paramString, SettingLogsBean paramSettingLogsBean)
    throws RemoteException;

  public abstract boolean updateInfo(Object paramObject, String paramString, SettingLogsBean paramSettingLogsBean)
    throws RemoteException;

  public abstract boolean infoGet(List<InfoBean> paramList, String paramString1, String paramString2, int paramInt1, int paramInt2, boolean paramBoolean, int paramInt3)
    throws RemoteException;

  public abstract boolean batchPublishContentHtml(Map<String, String> paramMap)
    throws RemoteException;

  public abstract boolean MoveInfo(List<InfoBean> paramList, int paramInt, String paramString1, String paramString2, SettingLogsBean paramSettingLogsBean)
    throws RemoteException;

  public abstract boolean updateInfoStatus(List<InfoBean> paramList, String paramString, SettingLogsBean paramSettingLogsBean)
    throws RemoteException;

  public abstract boolean updateInfoEvent(InfoBean paramInfoBean, SettingLogsBean paramSettingLogsBean)
    throws RemoteException;

  public abstract boolean passInfoStatus(List<InfoBean> paramList, String paramString, SettingLogsBean paramSettingLogsBean)
    throws RemoteException;

  public abstract boolean createContentHTML(List<InfoBean> paramList)
    throws RemoteException;

  public abstract boolean deleteInfo(List<InfoBean> paramList, SettingLogsBean paramSettingLogsBean)
    throws RemoteException;
}

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.rmi.IFileRmi
 * JD-Core Version:    0.6.2
 */