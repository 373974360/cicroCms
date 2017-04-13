package com.cicro.wcm.rmi.file;

import java.io.File;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;

import com.cicro.util.FormatUtil;
import com.cicro.util.io.FileOperation;
import com.cicro.util.jconfig.JconfigUtilContainer;
import com.cicro.wcm.bean.cms.info.InfoBean;
import com.cicro.wcm.bean.control.SiteBean;
import com.cicro.wcm.bean.logs.SettingLogsBean;
import com.cicro.wcm.bean.page.PageBean;
import com.cicro.wcm.bean.system.template.TemplateEditBean;
import com.cicro.wcm.bean.system.template.TemplateResourcesBean;
import com.cicro.wcm.bean.system.ware.WareBean;
import com.cicro.wcm.rmi.IFileRmi;
import com.cicro.wcm.services.cms.info.InfoBaseManager;
import com.cicro.wcm.services.cms.info.InfoPublishManager;
import com.cicro.wcm.services.cms.info.ModelUtil;
import com.cicro.wcm.services.control.server.SiteServerManager;
import com.cicro.wcm.services.control.site.SiteManager;
import com.cicro.wcm.services.page.PageManager;
import com.cicro.wcm.services.search.SearchInnerManager;
import com.cicro.wcm.services.system.template.TemplateResourcesManager;
import com.cicro.wcm.services.system.ware.WareManager;
import com.cicro.wcm.template.TemplateBase;

public class FileRmiFactory
{
  public static IFileRmi getFileRmiObj(String site_id)
  {
    SiteBean si = SiteManager.getSiteBeanBySiteID(site_id);
    if (si == null)
      return null;
    String server_ip = SiteServerManager.getServerBeanByID(si.getServer_id()+"").getServer_ip();
    try
    {
      Context namingContext = new InitialContext();
      String path = "rmi://" + server_ip + ":" + JconfigUtilContainer.bashConfig().getProperty("port", "", "rmi_config") + "/fileRmi";
      return (IFileRmi)namingContext.lookup(path);
    }
    catch (Exception e) {
      e.printStackTrace();
    }return null;
  }

  public static boolean saveFile(String site_id, String savePath, String file_name, String content)
  {
    if ((!SiteServerManager.IS_MUTILPUBLISHSERVER) || (SiteServerManager.isTheSameServer(site_id)))
    {
      File f = new File(savePath);
      if (!f.exists())
      {
        f.mkdirs();
      }
      savePath = FormatUtil.formatPath(savePath + "/" + file_name);
      try {
        return FileOperation.writeStringToFile(savePath, content, false, "utf-8");
      }
      catch (IOException e) {
        e.printStackTrace();
        return false;
      }
    }

    IFileRmi ifr = getFileRmiObj(site_id);
    try {
      return ifr.saveFile(savePath, file_name, content);
    }
    catch (RemoteException e) {
      e.printStackTrace();
    }return false;
  }

  public static boolean saveFile(String site_id, String savePath, String content)
  {
    if ((!SiteServerManager.IS_MUTILPUBLISHSERVER) || (SiteServerManager.isTheSameServer(site_id)))
    {
      try
      {
        return FileOperation.writeStringToFile(savePath, content, false, "utf-8");
      }
      catch (IOException e) {
        e.printStackTrace();
        return false;
      }
    }

    IFileRmi ifr = getFileRmiObj(site_id);
    try {
      return ifr.saveFile(savePath, content);
    }
    catch (RemoteException e) {
      e.printStackTrace();
    }return false;
  }

  public static boolean delFile(String site_id, String savePath)
  {
    if ((!SiteServerManager.IS_MUTILPUBLISHSERVER) || (SiteServerManager.isTheSameServer(site_id)))
    {
      try
      {
        File f = new File(savePath);
        if (f.exists()) {
          f.delete();
        }
        return true;
      } catch (Exception e) {
        e.printStackTrace();
        return false;
      }
    }

    IFileRmi ifr = getFileRmiObj(site_id);
    try {
      return ifr.delFile(savePath);
    }
    catch (RemoteException e) {
      e.printStackTrace();
    }return false;
  }

  public static boolean delDir(String site_id, String savePath)
  {
    if ((!SiteServerManager.IS_MUTILPUBLISHSERVER) || (SiteServerManager.isTheSameServer(site_id)))
    {
      return FileOperation.deleteDir(savePath);
    }

    IFileRmi ifr = getFileRmiObj(site_id);
    try {
      return ifr.delDir(savePath);
    }
    catch (RemoteException e) {
      e.printStackTrace();
    }return false;
  }

  public static boolean createPage(PageBean pb)
  {
    if ((!SiteServerManager.IS_MUTILPUBLISHSERVER) || (SiteServerManager.isTheSameServer(pb.getSite_id())))
    {
      try
      {
        return PageManager.createPageHandl(pb);
      }
      catch (IOException e) {
        e.printStackTrace();
        return false;
      }

    }

    IFileRmi ifr = getFileRmiObj(pb.getSite_id());
    try {
      return ifr.createPage(pb);
    }
    catch (RemoteException e) {
      e.printStackTrace();
    }return false;
  }

  public static void delSearchIndex(String site_id, String info_ids)
  {
    if ((!SiteServerManager.IS_MUTILPUBLISHSERVER) || (SiteServerManager.isTheSameServer(site_id)))
    {
      SearchInnerManager.infoSetDel(info_ids);
    }
    else {
      IFileRmi ifr = getFileRmiObj(site_id);
      try {
        ifr.delSearchIndex(info_ids);
      }
      catch (RemoteException e) {
        e.printStackTrace();
      }
    }
  }

  public static List<TemplateResourcesBean> getResImageListBySiteID(String site_id)
  {
    if ((!SiteServerManager.IS_MUTILPUBLISHSERVER) || (SiteServerManager.isTheSameServer(site_id)))
    {
      return TemplateResourcesManager.getResImageListBySiteID(site_id);
    }

    IFileRmi ifr = getFileRmiObj(site_id);
    try {
      return ifr.getResImageListBySiteID(site_id);
    }
    catch (RemoteException e) {
      e.printStackTrace();
    }return null;
  }

  public static boolean addTemplateResourcesFolder(String file_path)
  {
    if ((!SiteServerManager.IS_MUTILPUBLISHSERVER) || (SiteServerManager.isTheSameServer(file_path)))
    {
      return TemplateResourcesManager.addTemplateResourcesFolder(file_path);
    }

    IFileRmi ifr = getFileRmiObj(file_path);
    try {
      return ifr.addTemplateResourcesFolder(file_path);
    }
    catch (RemoteException e) {
      e.printStackTrace();
    }return false;
  }

  public static List<TemplateResourcesBean> getResourcesListBySiteID(String site_id)
  {
    if ((!SiteServerManager.IS_MUTILPUBLISHSERVER) || (SiteServerManager.isTheSameServer(site_id)))
    {
      return TemplateResourcesManager.getResourcesListBySiteID(site_id);
    }

    IFileRmi ifr = getFileRmiObj(site_id);
    try {
      return ifr.getResourcesListBySiteID(site_id);
    }
    catch (RemoteException e) {
      e.printStackTrace();
    }return null;
  }

  public static String getFolderJSONStr(String site_id)
  {
    if ((!SiteServerManager.IS_MUTILPUBLISHSERVER) || (SiteServerManager.isTheSameServer(site_id)))
    {
      return TemplateResourcesManager.getFolderJSONStr(site_id);
    }

    IFileRmi ifr = getFileRmiObj(site_id);
    try {
      return ifr.getFolderJSONStr(site_id);
    }
    catch (RemoteException e) {
      e.printStackTrace();
    }return null;
  }

  public static boolean deleteTemplateResources(String file_path, String site_id)
  {
    if ((!SiteServerManager.IS_MUTILPUBLISHSERVER) || (SiteServerManager.isTheSameServer(site_id)))
    {
      return TemplateResourcesManager.deleteTemplateResources(file_path);
    }

    IFileRmi ifr = getFileRmiObj(site_id);
    try {
      return ifr.deleteTemplateResources(file_path);
    }
    catch (RemoteException e) {
      e.printStackTrace();
    }return false;
  }

  public static boolean updateResourcesFile(String file_path, String file_content, String site_id)
    throws IOException
  {
    if ((!SiteServerManager.IS_MUTILPUBLISHSERVER) || (SiteServerManager.isTheSameServer(site_id)))
    {
      return TemplateResourcesManager.updateResourcesFile(file_path, file_content);
    }

    IFileRmi ifr = getFileRmiObj(site_id);
    try {
      return ifr.updateResourcesFile(file_path, file_content);
    }
    catch (RemoteException e) {
      e.printStackTrace();
    }return false;
  }

  public static String getResourcesFileContent(String file_path, String site_id)
    throws IOException
  {
    if ((!SiteServerManager.IS_MUTILPUBLISHSERVER) || (SiteServerManager.isTheSameServer(site_id)))
    {
      return TemplateResourcesManager.getResourcesFileContent(file_path);
    }

    IFileRmi ifr = getFileRmiObj(site_id);
    try {
      return ifr.getResourcesFileContent(file_path);
    }
    catch (RemoteException e) {
      e.printStackTrace();
    }return "";
  }

  public static boolean saveTemplateFile(String site_id, TemplateEditBean teb)
  {
    if ((!SiteServerManager.IS_MUTILPUBLISHSERVER) || (SiteServerManager.isTheSameServer(site_id)))
    {
      return TemplateBase.saveTemplateFileHandl(teb);
    }

    IFileRmi ifr = getFileRmiObj(site_id);
    try {
      return ifr.saveTemplateFile(teb);
    }
    catch (RemoteException e) {
      e.printStackTrace();
    }return false;
  }

  public static void createWarePage(WareBean wb)
  {
    if ((!SiteServerManager.IS_MUTILPUBLISHSERVER) || (SiteServerManager.isTheSameServer(wb.getSite_id())))
    {
      try
      {
        WareManager.createWarePageHandl(wb);
      }
      catch (IOException e) {
        e.printStackTrace();
      }
    }
    else {
      IFileRmi ifr = getFileRmiObj(wb.getSite_id());
      try {
        ifr.createWarePage(wb);
      }
      catch (RemoteException e) {
        e.printStackTrace();
      }
    }
  }

  public static boolean insertInfo(String rmi_site_id, Object ob, String model_name, SettingLogsBean stl)
  {
    if ((!SiteServerManager.IS_MUTILPUBLISHSERVER) || (SiteServerManager.isTheSameServer(rmi_site_id)))
    {
      return ModelUtil.insert(ob, model_name, stl);
    }

    IFileRmi ifr = getFileRmiObj(rmi_site_id);
    try {
      return ifr.insertInfo(ob, model_name, stl);
    }
    catch (RemoteException e) {
      e.printStackTrace();
    }return false;
  }

  public static boolean updateInfo(String rmi_site_id, Object ob, String model_name, SettingLogsBean stl)
  {
    if ((!SiteServerManager.IS_MUTILPUBLISHSERVER) || (SiteServerManager.isTheSameServer(rmi_site_id)))
    {
      return ModelUtil.update(ob, model_name, stl);
    }

    IFileRmi ifr = getFileRmiObj(rmi_site_id);
    try {
      return ifr.updateInfo(ob, model_name, stl);
    }
    catch (RemoteException e) {
      e.printStackTrace();
    }return false;
  }

  public static boolean infoGet(String rmi_site_id, List<InfoBean> l, String s_site_id, String s_app_id, int cat_id, int get_type, boolean is_publish, int user_id)
    throws RemoteException
  {
    if ((!SiteServerManager.IS_MUTILPUBLISHSERVER) || (SiteServerManager.isTheSameServer(rmi_site_id)))
    {
      return InfoBaseManager.infoGet(l, s_site_id, s_app_id, cat_id, get_type, is_publish, user_id);
    }

    IFileRmi ifr = getFileRmiObj(rmi_site_id);
    try {
      return ifr.infoGet(l, s_site_id, s_app_id, cat_id, get_type, is_publish, user_id);
    }
    catch (RemoteException e) {
      e.printStackTrace();
    }return false;
  }

  public static boolean batchPublishContentHtml(String site_id, Map<String, String> map)
  {
    if ((!SiteServerManager.IS_MUTILPUBLISHSERVER) || (SiteServerManager.isTheSameServer(site_id)))
    {
      return InfoBaseManager.batchPublishContentHtml(map);
    }

    IFileRmi ifr = getFileRmiObj(site_id);
    try {
      return ifr.batchPublishContentHtml(map);
    }
    catch (RemoteException e) {
      e.printStackTrace();
    }return false;
  }

  public static boolean MoveInfo(String rmi_site_id, List<InfoBean> l, int to_cat_id, String app_id, String site_id, SettingLogsBean stl)
  {
    if ((!SiteServerManager.IS_MUTILPUBLISHSERVER) || (SiteServerManager.isTheSameServer(site_id)))
    {
      return InfoBaseManager.MoveInfo(l, to_cat_id, app_id, site_id, stl);
    }

    IFileRmi ifr = getFileRmiObj(rmi_site_id);
    try {
      return ifr.MoveInfo(l, to_cat_id, app_id, site_id, stl);
    }
    catch (RemoteException e) {
      e.printStackTrace();
    }return false;
  }

  public static boolean updateInfoStatus(String rmi_site_id, List<InfoBean> l, String status, SettingLogsBean stl)
  {
    if ((!SiteServerManager.IS_MUTILPUBLISHSERVER) || (SiteServerManager.isTheSameServer(rmi_site_id)))
    {
      return InfoBaseManager.updateInfoStatus(l, status, stl);
    }

    IFileRmi ifr = getFileRmiObj(rmi_site_id);
    try {
      return ifr.updateInfoStatus(l, status, stl);
    }
    catch (RemoteException e) {
      e.printStackTrace();
    }return false;
  }

  public static boolean updateInfoEvent(String rmi_site_id, InfoBean info, SettingLogsBean stl)
  {
    if ((!SiteServerManager.IS_MUTILPUBLISHSERVER) || (SiteServerManager.isTheSameServer(rmi_site_id)))
    {
      return InfoBaseManager.updateInfoEvent(info, stl);
    }

    IFileRmi ifr = getFileRmiObj(rmi_site_id);
    try {
      return ifr.updateInfoEvent(info, stl);
    }
    catch (RemoteException e) {
      e.printStackTrace();
    }return false;
  }

  public static boolean passInfoStatus(String rmi_site_id, List<InfoBean> info_list, String user_id, SettingLogsBean stl)
  {
    if ((!SiteServerManager.IS_MUTILPUBLISHSERVER) || (SiteServerManager.isTheSameServer(rmi_site_id)))
    {
      return InfoBaseManager.passInfoStatus(info_list, user_id, stl);
    }

    IFileRmi ifr = getFileRmiObj(rmi_site_id);
    try {
      return ifr.passInfoStatus(info_list, user_id, stl);
    }
    catch (RemoteException e) {
      e.printStackTrace();
    }return false;
  }

  public static boolean createContentHTML(String rmi_site_id, List<InfoBean> l)
  {
    if ((!SiteServerManager.IS_MUTILPUBLISHSERVER) || (SiteServerManager.isTheSameServer(rmi_site_id))) {
      try
      {
        return InfoPublishManager.createContentHTML(l);
      }
      catch (IOException e) {
        e.printStackTrace();
        return false;
      }
    }

    IFileRmi ifr = getFileRmiObj(rmi_site_id);
    try {
      return ifr.createContentHTML(l);
    }
    catch (RemoteException e) {
      e.printStackTrace();
    }return false;
  }

  public static boolean deleteInfo(String rmi_site_id, List<InfoBean> l, SettingLogsBean stl)
  {
    if ((!SiteServerManager.IS_MUTILPUBLISHSERVER) || (SiteServerManager.isTheSameServer(rmi_site_id)))
    {
      return InfoBaseManager.deleteInfo(l, stl);
    }
    InfoBaseManager.deleteInfo(l, stl);
    IFileRmi ifr = getFileRmiObj(rmi_site_id);
    try {
      return ifr.deleteInfo(l, stl);
    }
    catch (RemoteException e) {
      e.printStackTrace();
    }return false;
  }
}