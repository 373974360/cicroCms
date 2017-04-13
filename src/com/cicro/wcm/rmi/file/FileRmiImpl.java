/*     */ package com.cicro.wcm.rmi.file;
/*     */ 
/*     */ import com.cicro.util.FormatUtil;
/*     */ import com.cicro.util.io.FileOperation;
/*     */ import com.cicro.wcm.bean.cms.info.InfoBean;
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.bean.page.PageBean;
/*     */ import com.cicro.wcm.bean.system.template.TemplateEditBean;
/*     */ import com.cicro.wcm.bean.system.template.TemplateResourcesBean;
/*     */ import com.cicro.wcm.bean.system.ware.WareBean;
/*     */ import com.cicro.wcm.rmi.IFileRmi;
/*     */ import com.cicro.wcm.services.cms.info.InfoBaseManager;
/*     */ import com.cicro.wcm.services.cms.info.InfoPublishManager;
/*     */ import com.cicro.wcm.services.cms.info.ModelUtil;
/*     */ import com.cicro.wcm.services.page.PageManager;
/*     */ import com.cicro.wcm.services.search.SearchInnerManager;
/*     */ import com.cicro.wcm.services.system.template.TemplateResourcesManager;
/*     */ import com.cicro.wcm.services.system.ware.WareManager;
/*     */ import com.cicro.wcm.template.TemplateBase;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.rmi.RemoteException;
/*     */ import java.rmi.server.UnicastRemoteObject;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class FileRmiImpl extends UnicastRemoteObject
/*     */   implements IFileRmi
/*     */ {
/*     */   private static final long serialVersionUID = -8265188463990287682L;
/*     */ 
/*     */   public FileRmiImpl()
/*     */     throws RemoteException
/*     */   {
/*     */   }
/*     */ 
/*     */   public boolean saveFile(String savePath, String content)
/*     */     throws RemoteException
/*     */   {
/*     */     try
/*     */     {
/*  40 */       return FileOperation.writeStringToFile(savePath, content, false, "utf-8");
/*     */     }
/*     */     catch (IOException e) {
/*  43 */       e.printStackTrace();
/*  44 */     }return false;
/*     */   }
/*     */ 
/*     */   public boolean saveFile(String savePath, String file_name, String content)
/*     */     throws RemoteException
/*     */   {
/*  50 */     File f = new File(savePath);
/*  51 */     if (!f.exists())
/*     */     {
/*  53 */       f.mkdirs();
/*     */     }
/*  55 */     savePath = FormatUtil.formatPath(savePath + "/" + file_name);
/*     */     try {
/*  57 */       return FileOperation.writeStringToFile(savePath, content, false, "utf-8");
/*     */     }
/*     */     catch (IOException e) {
/*  60 */       e.printStackTrace();
/*  61 */     }return false;
/*     */   }
/*     */ 
/*     */   public boolean createPage(PageBean pb) throws RemoteException
/*     */   {
/*     */     try
/*     */     {
/*  68 */       return PageManager.createPageHandl(pb);
/*     */     }
/*     */     catch (IOException e) {
/*  71 */       e.printStackTrace();
/*  72 */     }return false;
/*     */   }
/*     */ 
/*     */   public boolean delFile(String savePath) throws RemoteException
/*     */   {
/*     */     try
/*     */     {
/*  79 */       if (savePath.endsWith("ROOT/"))
/*     */       {
/*  81 */         return true;
/*     */       }
/*     */ 
/*  84 */       File f = new File(savePath);
/*  85 */       if (f.exists()) {
/*  86 */         f.delete();
/*     */       }
/*  88 */       return true;
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/*  92 */       e.printStackTrace();
/*  93 */     }return false;
/*     */   }
/*     */ 
/*     */   public boolean delDir(String savePath)
/*     */     throws RemoteException
/*     */   {
/* 100 */     if (savePath.endsWith("ROOT/"))
/*     */     {
/* 102 */       return true;
/*     */     }
/* 104 */     return FileOperation.deleteDir(savePath);
/*     */   }
/*     */ 
/*     */   public void saveSearchIndex(String info_ids) throws RemoteException
/*     */   {
/* 109 */     SearchInnerManager.infoSetAdd(info_ids);
/*     */   }
/*     */ 
/*     */   public void delSearchIndex(String info_ids) throws RemoteException
/*     */   {
/* 114 */     SearchInnerManager.infoSetDel(info_ids);
/*     */   }
/*     */ 
/*     */   public String getFolderJSONStr(String site_id)
/*     */     throws RemoteException
/*     */   {
/* 120 */     return TemplateResourcesManager.getFolderJSONStr(site_id);
/*     */   }
/*     */ 
/*     */   public List<TemplateResourcesBean> getResImageListBySiteID(String site_id)
/*     */     throws RemoteException
/*     */   {
/* 126 */     return TemplateResourcesManager.getResImageListBySiteID(site_id);
/*     */   }
/*     */ 
/*     */   public List<TemplateResourcesBean> getResourcesListBySiteID(String site_id)
/*     */     throws RemoteException
/*     */   {
/* 132 */     return TemplateResourcesManager.getResourcesListBySiteID(site_id);
/*     */   }
/*     */ 
/*     */   public boolean addTemplateResourcesFolder(String file_path)
/*     */     throws RemoteException
/*     */   {
/* 138 */     return TemplateResourcesManager.addTemplateResourcesFolder(file_path);
/*     */   }
/*     */ 
/*     */   public boolean deleteTemplateResources(String file_path)
/*     */   {
/* 144 */     return TemplateResourcesManager.deleteTemplateResources(file_path);
/*     */   }
/*     */ 
/*     */   public boolean updateResourcesFile(String file_path, String file_content)
/*     */     throws IOException
/*     */   {
/* 150 */     return TemplateResourcesManager.updateResourcesFile(file_path, file_content);
/*     */   }
/*     */ 
/*     */   public String getResourcesFileContent(String file_path)
/*     */     throws IOException
/*     */   {
/* 156 */     return TemplateResourcesManager.getResourcesFileContent(file_path);
/*     */   }
/*     */ 
/*     */   public boolean saveTemplateFile(TemplateEditBean teb)
/*     */     throws RemoteException
/*     */   {
/* 162 */     return TemplateBase.saveTemplateFileHandl(teb);
/*     */   }
/*     */ 
/*     */   public void createWarePage(WareBean wb) throws RemoteException
/*     */   {
/*     */     try
/*     */     {
/* 169 */       WareManager.createWarePageHandl(wb);
/*     */     }
/*     */     catch (IOException e) {
/* 172 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public boolean insertInfo(Object ob, String model_name, SettingLogsBean stl)
/*     */     throws RemoteException
/*     */   {
/* 185 */     return ModelUtil.insert(ob, model_name, stl);
/*     */   }
/*     */ 
/*     */   public boolean updateInfo(Object ob, String model_name, SettingLogsBean stl)
/*     */     throws RemoteException
/*     */   {
/* 197 */     return ModelUtil.update(ob, model_name, stl);
/*     */   }
/*     */ 
/*     */   public boolean infoGet(List<InfoBean> l, String s_site_id, String s_app_id, int cat_id, int get_type, boolean is_publish, int user_id)
/*     */     throws RemoteException
/*     */   {
/* 203 */     return InfoBaseManager.infoGet(l, s_site_id, s_app_id, cat_id, get_type, is_publish, user_id);
/*     */   }
/*     */ 
/*     */   public boolean batchPublishContentHtml(Map<String, String> map)
/*     */     throws RemoteException
/*     */   {
/* 213 */     return InfoBaseManager.batchPublishContentHtml(map);
/*     */   }
/*     */ 
/*     */   public boolean MoveInfo(List<InfoBean> l, int to_cat_id, String app_id, String site_id, SettingLogsBean stl)
/*     */     throws RemoteException
/*     */   {
/* 226 */     return InfoBaseManager.MoveInfo(l, to_cat_id, app_id, site_id, stl);
/*     */   }
/*     */ 
/*     */   public boolean updateInfoStatus(List<InfoBean> l, String status, SettingLogsBean stl)
/*     */     throws RemoteException
/*     */   {
/* 237 */     return InfoBaseManager.updateInfoStatus(l, status, stl);
/*     */   }
/*     */ 
/*     */   public boolean updateInfoEvent(InfoBean info, SettingLogsBean stl)
/*     */     throws RemoteException
/*     */   {
/* 248 */     return InfoBaseManager.updateInfoEvent(info, stl);
/*     */   }
/*     */ 
/*     */   public boolean passInfoStatus(List<InfoBean> info_list, String user_id, SettingLogsBean stl)
/*     */     throws RemoteException
/*     */   {
/* 259 */     return InfoBaseManager.passInfoStatus(info_list, user_id, stl);
/*     */   }
/*     */ 
/*     */   public boolean createContentHTML(List<InfoBean> l)
/*     */     throws RemoteException
/*     */   {
/*     */     try
/*     */     {
/* 270 */       return InfoPublishManager.createContentHTML(l);
/*     */     }
/*     */     catch (IOException e) {
/* 273 */       e.printStackTrace();
/* 274 */     }return false;
/*     */   }
/*     */ 
/*     */   public boolean deleteInfo(List<InfoBean> l, SettingLogsBean stl)
/*     */     throws RemoteException
/*     */   {
/* 285 */     return InfoBaseManager.deleteInfo(l, stl);
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.rmi.file.FileRmiImpl
 * JD-Core Version:    0.6.2
 */