/*     */ package com.cicro.wcm.services.sendInfo;
/*     */ 
/*     */ import com.cicro.wcm.bean.cms.info.InfoBean;
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.bean.sendInfo.ReceiveCatConf;
/*     */ import com.cicro.wcm.bean.sendInfo.ReceiveConfigBean;
/*     */ import com.cicro.wcm.bean.sendInfo.ReceiveInfoBean;
/*     */ import com.cicro.wcm.bean.sendInfo.SendConfigBean;
/*     */ import com.cicro.wcm.bean.sendInfo.SendRecordBean;
/*     */ import com.cicro.wcm.bean.sendInfo.SendRecordCount;
/*     */ import com.cicro.wcm.services.Log.LogManager;
/*     */ import com.cicro.wcm.webServices.sendInfo.ISendInfo;
/*     */ import com.cicro.wcm.webServices.sendInfo.SendClient;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ 
/*     */ public class SendInfoRPC
/*     */ {
/*     */   public static String getRecieveSiteJSONTree(String type)
/*     */   {
/*  29 */     return ReceiveConfigManager.getRecieveSiteJSONTree(type);
/*     */   }
/*     */ 
/*     */   public static List<ReceiveConfigBean> getReceiveConfigList()
/*     */   {
/*  39 */     return ReceiveConfigManager.getReceiveConfigList();
/*     */   }
/*     */ 
/*     */   public static Map<String, Object> getReceiveConfigForSiteID(String site_id)
/*     */   {
/*  49 */     Map m = new HashMap();
/*  50 */     m.put("rcfBean", ReceiveConfigManager.getReceiveConfigForSiteID(site_id));
/*  51 */     m.put("catList", ReceiveConfigManager.getReceiveCatConfList(site_id));
/*  52 */     return m;
/*     */   }
/*     */ 
/*     */   public static boolean insertReceiveConfig(ReceiveConfigBean rcf, List<ReceiveCatConf> l, HttpServletRequest request)
/*     */   {
/*  63 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/*  64 */     if (stl != null) {
/*  65 */       return ReceiveConfigManager.insertReceiveConfig(rcf, l, stl);
/*     */     }
/*  67 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateReceiveConfig(ReceiveConfigBean rcf, List<ReceiveCatConf> l, HttpServletRequest request)
/*     */   {
/*  78 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/*  79 */     if (stl != null) {
/*  80 */       return ReceiveConfigManager.updateReceiveConfig(rcf, l, stl);
/*     */     }
/*  82 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateReceiveConfigStatus(Map<String, String> m, HttpServletRequest request)
/*     */   {
/*  93 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/*  94 */     if (stl != null) {
/*  95 */       return ReceiveConfigManager.updateReceiveConfigStatus(m, stl);
/*     */     }
/*  97 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteReceiveConfig(String site_ids, HttpServletRequest request)
/*     */   {
/* 108 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 109 */     if (stl != null) {
/* 110 */       return ReceiveConfigManager.deleteReceiveConfig(site_ids, stl);
/*     */     }
/* 112 */     return false;
/*     */   }
/*     */ 
/*     */   public static String getReceiveSiteList(String site_domain)
/*     */   {
/*     */     try
/*     */     {
/* 125 */       return SendClient.getServicesObj(site_domain).getReceiveConfigForJSON();
/*     */     }
/*     */     catch (Exception e) {
/* 128 */       e.printStackTrace();
/* 129 */     }return "";
/*     */   }
/*     */ 
/*     */   public static List<SendConfigBean> getSendConfigList()
/*     */   {
/* 135 */     return SendConfigManager.getSendConfigList();
/*     */   }
/*     */ 
/*     */   public static SendConfigBean getSendConfigBean(String site_id)
/*     */   {
/* 140 */     return SendConfigManager.getSendConfigBean(site_id);
/*     */   }
/*     */ 
/*     */   public static boolean insertSendConfig(List<SendConfigBean> l, HttpServletRequest request)
/*     */   {
/* 145 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 146 */     if (stl != null)
/*     */     {
/* 148 */       return SendConfigManager.insertSendConfig(l, stl);
/*     */     }
/* 150 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteSendConfig(Map<String, String> m, HttpServletRequest request)
/*     */   {
/* 161 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 162 */     if (stl != null)
/*     */     {
/* 164 */       return SendConfigManager.deleteSendConfig(m, stl);
/*     */     }
/* 166 */     return false;
/*     */   }
/*     */ 
/*     */   public static String getSendSiteJSONTree(String type)
/*     */   {
/* 176 */     return SendConfigManager.getSendSiteJSONTree(type);
/*     */   }
/*     */ 
/*     */   public static String getReceiveCategoryList(String site_domain)
/*     */   {
/* 187 */     return SendInfoManager.getReceiveCategoryList(site_domain);
/*     */   }
/*     */ 
/*     */   public static String insertSendInfo(List<SendRecordBean> sendRecordList, List<InfoBean> infoList, HttpServletRequest request)
/*     */   {
/* 197 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 198 */     if (stl != null)
/*     */     {
/* 200 */       return SendInfoManager.insertSendInfo(sendRecordList, infoList, stl);
/*     */     }
/* 202 */     return "false";
/*     */   }
/*     */ 
/*     */   public static String getReceiveInfoCount(Map<String, String> m)
/*     */   {
/* 214 */     return ReceiveInfoManager.getReceiveInfoCount(m);
/*     */   }
/*     */ 
/*     */   public static List<ReceiveInfoBean> getReceiveInfoList(Map<String, String> m)
/*     */   {
/* 224 */     return ReceiveInfoManager.getReceiveInfoList(m);
/*     */   }
/*     */ 
/*     */   public static boolean adoptReceiveInfo(Map<String, String> m, HttpServletRequest request)
/*     */   {
/* 234 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 235 */     if (stl != null)
/*     */     {
/* 237 */       return ReceiveInfoManager.adoptReceiveInfo(m, stl);
/*     */     }
/* 239 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteReceiveInfo(Map<String, String> m, HttpServletRequest request)
/*     */   {
/* 249 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 250 */     if (stl != null)
/*     */     {
/* 252 */       return ReceiveInfoManager.deleteReceiveInfo(m, stl);
/*     */     }
/* 254 */     return false;
/*     */   }
/*     */ 
/*     */   public static List<ReceiveInfoBean> getReceiveCateInfoList(String site_id)
/*     */   {
/* 264 */     return ReceiveInfoManager.getReceiveCateInfoList(site_id);
/*     */   }
/*     */ 
/*     */   public static String getSendRecordCount(Map<String, String> m)
/*     */   {
/* 276 */     return SendRecordManager.getSendRecordCount(m);
/*     */   }
/*     */ 
/*     */   public static List<SendRecordBean> getSendRecordList(Map<String, String> m)
/*     */   {
/* 286 */     return SendRecordManager.getSendRecordList(m);
/*     */   }
/*     */ 
/*     */   public static boolean deleteSendRecord(Map<String, String> m, HttpServletRequest request)
/*     */   {
/* 297 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 298 */     if (stl != null)
/*     */     {
/* 300 */       return SendRecordManager.deleteSendRecord(m, stl);
/*     */     }
/* 302 */     return false;
/*     */   }
/*     */ 
/*     */   public static List<SendRecordCount> getSendRecordUserCount(Map<String, String> m)
/*     */   {
/* 315 */     return SendCountManager.getSendRecordUserCount(m);
/*     */   }
/*     */ 
/*     */   public static List<SendRecordCount> getSendCateListForRecord(Map<String, String> m)
/*     */   {
/* 326 */     return SendCountManager.getSendCateListForRecord(m);
/*     */   }
/*     */ 
/*     */   public static List<SendRecordBean> getReceiveSiteListForRecord(String site_id)
/*     */   {
/* 336 */     return SendCountManager.getReceiveSiteListForRecord(site_id);
/*     */   }
/*     */ 
/*     */   public static List<ReceiveInfoBean> getSendSiteList(String site_id)
/*     */   {
/* 348 */     return ReceiveCountManager.getSendSiteList(site_id);
/*     */   }
/*     */ 
/*     */   public static List<SendRecordCount> getSendSiteCountForReceive(Map<String, String> m)
/*     */   {
/* 358 */     return ReceiveCountManager.getSendSiteCountForReceive(m);
/*     */   }
/*     */ 
/*     */   public static List<SendRecordCount> getReceiveCateListForRecord(Map<String, String> m)
/*     */   {
/* 368 */     return ReceiveCountManager.getReceiveCateListForRecord(m);
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.sendInfo.SendInfoRPC
 * JD-Core Version:    0.6.2
 */