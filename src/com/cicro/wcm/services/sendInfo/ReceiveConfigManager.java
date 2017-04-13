/*     */ package com.cicro.wcm.services.sendInfo;
/*     */ 
/*     */ import com.cicro.util.jconfig.JconfigUtil;
/*     */ import com.cicro.util.jconfig.JconfigUtilContainer;
/*     */ import com.cicro.wcm.bean.cms.category.CategoryBean;
/*     */ import com.cicro.wcm.bean.control.SiteBean;
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.bean.sendInfo.ReceiveCatConf;
/*     */ import com.cicro.wcm.bean.sendInfo.ReceiveConfigBean;
/*     */ import com.cicro.wcm.catchs.ISyncCatch;
/*     */ import com.cicro.wcm.catchs.SyncCatchHandl;
/*     */ import com.cicro.wcm.dao.PublicTableDAO;
/*     */ import com.cicro.wcm.dao.sendInfo.ReceiveConfigDAO;
/*     */ import com.cicro.wcm.rmi.file.FileRmiFactory;
/*     */ import com.cicro.wcm.services.cms.category.CategoryManager;
/*     */ import com.cicro.wcm.services.control.domain.SiteDomainManager;
/*     */ import com.cicro.wcm.services.control.site.SiteManager;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class ReceiveConfigManager
/*     */   implements ISyncCatch
/*     */ {
/*  22 */   private static String receive_info_page = JconfigUtilContainer.managerPagePath().getProperty("receive_info_page", "", "m_org_path");
/*  23 */   private static String receive_count_page = JconfigUtilContainer.managerPagePath().getProperty("receive_count_page", "", "m_org_path");
/*     */ 
/*  25 */   private static List<ReceiveConfigBean> recConfig_list = new ArrayList();
/*  26 */   private static Map<String, List<ReceiveCatConf>> rec_cat_map = new HashMap();
/*     */ 
/*  28 */   static { reloadCatchHandl(); }
/*     */ 
/*     */ 
/*     */   public void reloadCatch()
/*     */   {
/*  33 */     reloadCatchHandl();
/*     */   }
/*     */ 
/*     */   public static void reloadCatchHandl()
/*     */   {
/*     */     try {
/*  39 */       recConfig_list.clear();
/*  40 */       rec_cat_map.clear();
/*  41 */       recConfig_list = ReceiveConfigDAO.getReceiveConfigList();
/*  42 */       List l = ReceiveConfigDAO.getReceiveCatConfList();
/*  43 */       if ((l != null) && (l.size() > 0))
/*     */       {
/*  45 */         for (ReceiveCatConf rcc : l)
/*     */         {
/*  47 */           if (rec_cat_map.containsKey(rcc.getSite_id()))
/*     */           {
/*  49 */             ((List)rec_cat_map.get(rcc.getSite_id())).add(rcc);
/*     */           }
/*     */           else {
/*  52 */             List rcfl = new ArrayList();
/*  53 */             rcfl.add(rcc);
/*  54 */             rec_cat_map.put(rcc.getSite_id(), rcfl);
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     catch (Exception e) {
/*  60 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void reloadReceiveConfig()
/*     */   {
/*  71 */     SyncCatchHandl.reladCatchForRMI("com.cicro.wcm.services.sendInfo.ReceiveConfigManager");
/*     */   }
/*     */ 
/*     */   public static List<ReceiveConfigBean> getReceiveConfigList()
/*     */   {
/*  81 */     if ((recConfig_list != null) && (recConfig_list.size() > 0))
/*     */     {
/*  83 */       for (int i = 0; i < recConfig_list.size(); i++) {
/*     */         try
/*     */         {
/*  86 */           ((ReceiveConfigBean)recConfig_list.get(i)).setSite_name(SiteManager.getSiteBeanBySiteID(((ReceiveConfigBean)recConfig_list.get(i)).getSite_id()).getSite_name());
/*     */         }
/*     */         catch (Exception e) {
/*  89 */           e.printStackTrace();
/*     */         }
/*     */       }
/*     */     }
/*  93 */     return recConfig_list;
/*     */   }
/*     */ 
/*     */   public static ReceiveConfigBean getReceiveConfigForID(int id)
/*     */   {
/* 103 */     if ((recConfig_list != null) && (recConfig_list.size() > 0))
/*     */     {
/* 105 */       for (ReceiveConfigBean rcb : recConfig_list)
/*     */       {
/* 107 */         if (rcb.getId() == id)
/* 108 */           return rcb;
/*     */       }
/*     */     }
/* 111 */     return null;
/*     */   }
/*     */ 
/*     */   public static ReceiveConfigBean getReceiveConfigForSiteID(String site_id)
/*     */   {
/* 121 */     if ((recConfig_list != null) && (recConfig_list.size() > 0))
/*     */     {
/* 123 */       for (ReceiveConfigBean rcb : recConfig_list)
/*     */       {
/* 125 */         if (site_id.equals(rcb.getSite_id()))
/* 126 */           return rcb;
/*     */       }
/*     */     }
/* 129 */     return null;
/*     */   }
/*     */ 
/*     */   public static boolean insertReceiveConfig(ReceiveConfigBean rcf, List<ReceiveCatConf> l, SettingLogsBean stl)
/*     */   {
/* 140 */     rcf.setId(PublicTableDAO.getIDByTableName(PublicTableDAO.RECEIVE_CONFIG_TABLE_NAME));
/* 141 */     if (ReceiveConfigDAO.insertReceiveConfig(rcf, stl))
/*     */     {
/* 143 */       ReceiveConfigDAO.insertReceiveCatConf(l, stl);
/* 144 */       saveReceiveCateConfigFile(l, rcf.getSite_id());
/* 145 */       reloadReceiveConfig();
/* 146 */       return true;
/*     */     }
/* 148 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateReceiveConfig(ReceiveConfigBean rcf, List<ReceiveCatConf> l, SettingLogsBean stl)
/*     */   {
/* 159 */     if (ReceiveConfigDAO.updateReceiveConfig(rcf, stl))
/*     */     {
/* 161 */       ReceiveConfigDAO.updateReceiveCatConf(rcf.getSite_id(), l, stl);
/* 162 */       saveReceiveCateConfigFile(l, rcf.getSite_id());
/* 163 */       reloadReceiveConfig();
/* 164 */       return true;
/*     */     }
/* 166 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateReceiveConfigStatus(Map<String, String> m, SettingLogsBean stl)
/*     */   {
/* 177 */     if (ReceiveConfigDAO.updateReceiveConfigStatus(m, stl))
/*     */     {
/* 179 */       if ("0".equals(m.get("receive_status")))
/*     */       {
/* 182 */         String[] arr = ((String)m.get("ids")).split(",");
/* 183 */         for (int i = 0; i < arr.length; i++)
/*     */         {
/* 185 */           ReceiveConfigBean rcf = getReceiveConfigForID(Integer.parseInt(arr[i]));
/* 186 */           saveReceiveCateConfigFile(getReceiveCatConfList(rcf.getSite_id()), rcf.getSite_id());
/*     */         }
/*     */       }
/*     */       else
/*     */       {
/* 191 */         String[] arr = ((String)m.get("ids")).split(",");
/* 192 */         for (int i = 0; i < arr.length; i++)
/*     */         {
/* 194 */           ReceiveConfigBean rcf = getReceiveConfigForID(Integer.parseInt(arr[i]));
/* 195 */           deleteReceiveCateConfigFile(rcf.getSite_id());
/*     */         }
/*     */       }
/* 198 */       reloadReceiveConfig();
/* 199 */       return true;
/*     */     }
/* 201 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteReceiveConfig(String site_ids, SettingLogsBean stl)
/*     */   {
/* 212 */     if (ReceiveConfigDAO.deleteReceiveConfig(site_ids, stl))
/*     */     {
/* 214 */       String[] arr = site_ids.split(",");
/* 215 */       for (int i = 0; i < arr.length; i++)
/*     */       {
/* 217 */         deleteReceiveCateConfigFile(arr[i]);
/*     */       }
/* 219 */       reloadReceiveConfig();
/* 220 */       return true;
/*     */     }
/* 222 */     return false;
/*     */   }
/*     */ 
/*     */   public static List<ReceiveCatConf> getReceiveCatConfList(String site_id)
/*     */   {
/* 232 */     if (rec_cat_map.containsKey(site_id)) {
/* 233 */       return (List)rec_cat_map.get(site_id);
/*     */     }
/* 235 */     return null;
/*     */   }
/*     */ 
/*     */   public static void deleteReceiveCateConfigFile(String site_id)
/*     */   {
/* 245 */     FileRmiFactory.delFile(site_id, SiteManager.getSitePath(site_id) + "/sendConfig/sendConfig");
/*     */   }
/*     */ 
/*     */   public static void saveReceiveCateConfigFile(List<ReceiveCatConf> l, String site_id)
/*     */   {
/* 255 */     String xml = "";
/* 256 */     if ((l != null) && (l.size() > 0))
/*     */     {
/* 258 */       xml = "[{\"id\":\"" + site_id + "\",\"text\":\"" + SiteManager.getSiteBeanBySiteID(site_id).getSite_name() + "\",\"children\":";
/* 259 */       xml = xml + "[";
/* 260 */       xml = xml + getJSONForReceiveCatList(l, site_id);
/* 261 */       xml = xml + "]";
/* 262 */       xml = xml + "}]";
/* 263 */       String savePath = SiteManager.getSitePath(site_id) + "/sendConfig";
/* 264 */       FileRmiFactory.saveFile(site_id, savePath, "sendConfig", xml);
/*     */     }
/*     */   }
/*     */ 
/*     */   public static String getJSONForReceiveCatList(List<ReceiveCatConf> l, String site_id)
/*     */   {
/* 296 */     String json = "";
/* 297 */     for (ReceiveCatConf rcf : l) {
/*     */       try
/*     */       {
/* 300 */         if (rcf.getSort_id() > 0)
/* 301 */           json = json + ",";
/* 302 */         json = json + "{\"id\":\"" + rcf.getCat_id() + "\",\"text\":\"" + CategoryManager.getCategoryBeanCatID(rcf.getCat_id(), site_id).getCat_cname() + "\"}";
/*     */       }
/*     */       catch (Exception e)
/*     */       {
/* 306 */         e.printStackTrace();
/*     */       }
/*     */     }
/* 309 */     return json;
/*     */   }
/*     */ 
/*     */   public static String getReceiveConfigForJSON()
/*     */   {
/* 320 */     String json = "";
/* 321 */     if ((recConfig_list != null) && (recConfig_list.size() > 0))
/*     */     {
/* 323 */       for (ReceiveConfigBean rcf : recConfig_list) {
/*     */         try
/*     */         {
/* 326 */           if (rcf.getReceive_status() == 0)
/*     */           {
/* 328 */             List l = getReceiveCatConfList(rcf.getSite_id());
/* 329 */             if ((l != null) && (l.size() > 0))
/* 330 */               json = json + ",{\"id\":\"" + rcf.getSite_id() + "\",\"text\":\"" + SiteManager.getSiteBeanBySiteID(rcf.getSite_id()).getSite_name() + "\",\"domain\":\"" + SiteDomainManager.getDefaultSiteDomainBySiteID(rcf.getSite_id()) + SiteManager.getSitePort() + "\",\"children\":[" + getJSONForReceiveCatList(l, rcf.getSite_id()) + "]}";
/*     */           }
/*     */         }
/*     */         catch (Exception e) {
/* 334 */           e.printStackTrace();
/*     */         }
/*     */       }
/* 337 */       json = "[" + json.substring(1) + "]";
/*     */     }
/* 339 */     return json;
/*     */   }
/*     */ 
/*     */   public static String getReceiveConfigForXML()
/*     */   {
/* 350 */     String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>";
/* 351 */     xml = xml + "<cicro>";
/* 352 */     if ((recConfig_list != null) && (recConfig_list.size() > 0))
/*     */     {
/* 354 */       xml = xml + "<site_list>";
/* 355 */       for (ReceiveConfigBean rcf : recConfig_list) {
/*     */         try
/*     */         {
/* 358 */           if (rcf.getReceive_status() == 0)
/*     */           {
/* 360 */             List l = getReceiveCatConfList(rcf.getSite_id());
/* 361 */             if ((l != null) && (l.size() > 0))
/*     */             {
/* 363 */               xml = xml + "<site>";
/* 364 */               xml = xml + "<site_id>" + rcf.getSite_id() + "</site_id>";
/* 365 */               xml = xml + "<site_name>" + SiteManager.getSiteBeanBySiteID(rcf.getSite_id()).getSite_name() + "</site_name>";
/* 366 */               xml = xml + "<site_domain>" + SiteDomainManager.getDefaultSiteDomainBySiteID(rcf.getSite_id()) + SiteManager.getSitePort() + "</site_domain>";
/* 367 */               xml = xml + "<category_list>";
/* 368 */               for (ReceiveCatConf rcc : l)
/*     */               {
/* 370 */                 xml = xml + "<category>";
/* 371 */                 xml = xml + "<category_id>" + rcc.getCat_id() + "</category_id>";
/* 372 */                 xml = xml + "<category_name><![CDATA[" + CategoryManager.getCategoryBeanCatID(rcc.getCat_id(), rcf.getSite_id()).getCat_cname() + "]]></category_name>";
/* 373 */                 xml = xml + "</category>";
/*     */               }
/* 375 */               xml = xml + "</category_list>";
/* 376 */               xml = xml + "</site>";
/*     */             }
/*     */           }
/*     */         }
/*     */         catch (Exception e) {
/* 381 */           e.printStackTrace();
/*     */         }
/*     */       }
/* 384 */       xml = xml + "</site_list>";
/*     */     }
/* 386 */     xml = xml + "</cicro>";
/* 387 */     return xml;
/*     */   }
/*     */ 
/*     */   public static String getRecieveSiteJSONTree(String type)
/*     */   {
/* 397 */     String json = "";
/* 398 */     json = "[{\"id\":0,\"text\":\"接收站点\",\"attributes\":{\"url\":\"\"},\"children\":[";
/* 399 */     List l = getReceiveConfigList();
/* 400 */     if ((l != null) && (l.size() > 0))
/*     */     {
/* 402 */       int i = 1;
/* 403 */       for (ReceiveConfigBean rcf : l)
/*     */       {
/* 405 */         if (i > 1)
/* 406 */           json = json + ",";
/* 407 */         if ((type != null) && (!"".equals(type)))
/* 408 */           json = json + "{\"id\":" + i + ",\"text\":\"" + SiteManager.getSiteBeanBySiteID(rcf.getSite_id()).getSite_name() + "\",\"attributes\":{\"url\":\"" + receive_count_page + "?type=" + type + "&site_id=" + rcf.getSite_id() + "\"}}";
/*     */         else
/* 410 */           json = json + "{\"id\":" + i + ",\"text\":\"" + SiteManager.getSiteBeanBySiteID(rcf.getSite_id()).getSite_name() + "\",\"attributes\":{\"url\":\"" + receive_info_page + "?site_id=" + rcf.getSite_id() + "\"}}";
/* 411 */         i++;
/*     */       }
/*     */     }
/* 414 */     json = json + "]}]";
/* 415 */     return json;
/*     */   }
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.sendInfo.ReceiveConfigManager
 * JD-Core Version:    0.6.2
 */