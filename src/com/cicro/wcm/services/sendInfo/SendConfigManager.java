/*     */ package com.cicro.wcm.services.sendInfo;
/*     */ 
/*     */ import com.cicro.util.jconfig.JconfigUtil;
/*     */ import com.cicro.util.jconfig.JconfigUtilContainer;
/*     */ import com.cicro.wcm.bean.control.SiteBean;
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.bean.sendInfo.SendConfigBean;
/*     */ import com.cicro.wcm.bean.sendInfo.SendRecordBean;
/*     */ import com.cicro.wcm.catchs.ISyncCatch;
/*     */ import com.cicro.wcm.catchs.SyncCatchHandl;
/*     */ import com.cicro.wcm.dao.sendInfo.SendConfigDAO;
/*     */ import com.cicro.wcm.services.control.site.SiteManager;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ 
/*     */ public class SendConfigManager
/*     */   implements ISyncCatch
/*     */ {
/*  19 */   private static String send_record_page = JconfigUtilContainer.managerPagePath().getProperty("send_record_page", "", "m_org_path");
/*  20 */   private static String send_count_page = JconfigUtilContainer.managerPagePath().getProperty("send_count_page", "", "m_org_path");
/*  21 */   private static Map<String, SendConfigBean> s_conf_map = new HashMap();
/*     */ 
/*     */   static {
/*  24 */     reloadCatchHandl();
/*     */   }
/*     */ 
/*     */   public void reloadCatch()
/*     */   {
/*  29 */     reloadCatchHandl();
/*     */   }
/*     */ 
/*     */   public static void reloadCatchHandl()
/*     */   {
/*  34 */     s_conf_map.clear();
/*     */     try {
/*  36 */       List l = SendConfigDAO.getSendConfigList();
/*  37 */       if ((l != null) && (l.size() > 0))
/*     */       {
/*  39 */         for (SendConfigBean scf : l)
/*     */         {
/*  41 */           s_conf_map.put(scf.getSite_id(), scf);
/*     */         }
/*     */       }
/*     */     }
/*     */     catch (Exception e) {
/*  46 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void reloadSendConfig()
/*     */   {
/*  52 */     SyncCatchHandl.reladCatchForRMI("com.cicro.wcm.services.sendInfo.SendConfigManager");
/*     */   }
/*     */ 
/*     */   public static List<SendConfigBean> getSendConfigList()
/*     */   {
/*  57 */     List l = new ArrayList();
/*  58 */     Set set = s_conf_map.keySet();
/*  59 */     if ((set != null) && (set.size() > 0))
/*     */     {
/*  61 */       for (String s : set)
/*     */       {
/*  63 */         l.add((SendConfigBean)s_conf_map.get(s));
/*     */       }
/*     */     }
/*  66 */     return l;
/*     */   }
/*     */ 
/*     */   public static SendConfigBean getSendConfigBean(String site_id)
/*     */   {
/*  71 */     return (SendConfigBean)s_conf_map.get(site_id);
/*     */   }
/*     */ 
/*     */   public static boolean insertSendConfig(List<SendConfigBean> l, SettingLogsBean stl)
/*     */   {
/*  83 */     if (SendConfigDAO.insertSendConfig(l, stl))
/*     */     {
/*  85 */       reloadSendConfig();
/*  86 */       return true;
/*     */     }
/*  88 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateSendConfig(SendConfigBean scb, SettingLogsBean stl)
/*     */   {
/*  99 */     if (SendConfigDAO.updateSendConfig(scb, stl))
/*     */     {
/* 101 */       reloadSendConfig();
/* 102 */       return true;
/*     */     }
/* 104 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteSendConfig(Map<String, String> m, SettingLogsBean stl)
/*     */   {
/* 115 */     if (SendConfigDAO.deleteSendConfig(m, stl))
/*     */     {
/* 117 */       reloadSendConfig();
/* 118 */       return true;
/*     */     }
/* 120 */     return false;
/*     */   }
/*     */ 
/*     */   public static String getSendSiteJSONTree(String type)
/*     */   {
/* 130 */     String json = "";
/* 131 */     json = "[{\"id\":0,\"text\":\"报送站点\",\"attributes\":{\"url\":\"\"},\"children\":[";
/* 132 */     List l = SendConfigDAO.getSendSiteList();
/* 133 */     if ((l != null) && (l.size() > 0))
/*     */     {
/* 135 */       int i = 1;
/* 136 */       for (SendRecordBean scfb : l)
/*     */       {
/* 138 */         if (i > 1)
/* 139 */           json = json + ",";
/* 140 */         if ((type != null) && (!"".equals(type)))
/* 141 */           json = json + "{\"id\":" + i + ",\"text\":\"" + SiteManager.getSiteBeanBySiteID(scfb.getSend_site_id()).getSite_name() + "\",\"attributes\":{\"url\":\"" + send_count_page + "?type=" + type + "&site_id=" + scfb.getSend_site_id() + "\"}}";
/*     */         else
/* 143 */           json = json + "{\"id\":" + i + ",\"text\":\"" + SiteManager.getSiteBeanBySiteID(scfb.getSend_site_id()).getSite_name() + "\",\"attributes\":{\"url\":\"" + send_record_page + "?site_id=" + scfb.getSend_site_id() + "\"}}";
/* 144 */         i++;
/*     */       }
/*     */     }
/* 147 */     json = json + "]}]";
/* 148 */     return json;
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.sendInfo.SendConfigManager
 * JD-Core Version:    0.6.2
 */