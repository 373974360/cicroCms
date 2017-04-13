/*     */ package com.cicro.wcm.services.control.config;
/*     */ 
/*     */ import com.cicro.wcm.bean.control.SiteConfigBean;
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.catchs.ISyncCatch;
/*     */ import com.cicro.wcm.catchs.SyncCatchHandl;
/*     */ import com.cicro.wcm.dao.control.SiteConfigDAO;
/*     */ import java.io.PrintStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class SiteConfigManager
/*     */   implements ISyncCatch
/*     */ {
/*  27 */   private static Map<String, List> config_map = new HashMap();
/*  28 */   private static List<SiteConfigBean> config_list = null;
/*     */ 
/*     */   static
/*     */   {
/*  37 */     reloadCatchHandl();
/*     */   }
/*     */ 
/*     */   public void reloadCatch()
/*     */   {
/*  42 */     reloadCatchHandl();
/*     */   }
/*     */ 
/*     */   public static void reloadCatchHandl()
/*     */   {
/*  47 */     config_map.clear();
/*  48 */     config_list = SiteConfigDAO.getAllSiteConfigList();
/*  49 */     if ((config_list != null) && (config_list.size() > 0))
/*     */     {
/*  51 */       for (int i = 0; i < config_list.size(); i++)
/*     */       {
/*  53 */         if (config_map.containsKey(((SiteConfigBean)config_list.get(i)).getSite_id()))
/*     */         {
/*  55 */           ((List)config_map.get(((SiteConfigBean)config_list.get(i)).getSite_id())).add(config_list.get(i));
/*     */         }
/*     */         else {
/*  58 */           List c_list = new ArrayList();
/*  59 */           c_list.add((SiteConfigBean)config_list.get(i));
/*  60 */           config_map.put(((SiteConfigBean)config_list.get(i)).getSite_id(), c_list);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void reloadSiteConfigList()
/*     */   {
/*  74 */     SyncCatchHandl.reladCatchForRMI("com.cicro.wcm.services.control.config.SiteConfigManager");
/*     */   }
/*     */ 
/*     */   public static List getConfigListBySiteID(String site_id)
/*     */   {
/*     */     try
/*     */     {
/*  87 */       List list = null;
/*  88 */       list = (List)config_map.get(site_id);
/*  89 */       if (list == null);
/*  90 */       return new ArrayList();
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/*  94 */       e.printStackTrace();
/*  95 */     }return new ArrayList();
/*     */   }
/*     */ 
/*     */   public static SiteConfigBean getConfigValues(String site_id, String key)
/*     */   {
/* 108 */     List l = getConfigListBySiteID(site_id);
/* 109 */     if ((l != null) && (l.size() > 0))
/*     */     {
/* 111 */       for (SiteConfigBean scfb : l)
/*     */       {
/* 113 */         if (key.equals(scfb.getConfig_key()))
/* 114 */           return scfb;
/*     */       }
/*     */     }
/* 117 */     return null;
/*     */   }
/*     */ 
/*     */   public static SiteConfigBean getConfigByConfigID(String config_id)
/*     */   {
/* 128 */     SiteConfigBean configBean = new SiteConfigBean();
/*     */     try {
/* 130 */       for (int i = 0; i < config_list.size(); i++)
/*     */       {
/* 132 */         configBean = (SiteConfigBean)config_list.get(i);
/*     */ 
/* 134 */         if (config_id.equals(configBean.getConfig_id())) {
/* 135 */           return configBean;
/*     */         }
/*     */       }
/* 138 */       return new SiteConfigBean();
/*     */     } catch (Exception e) {
/* 140 */       e.printStackTrace();
/* 141 */     }return new SiteConfigBean();
/*     */   }
/*     */ 
/*     */   public static boolean insertSiteConfig(List<SiteConfigBean> l, SettingLogsBean stl)
/*     */   {
/* 152 */     if (SiteConfigDAO.insertSiteConfig(l, stl))
/*     */     {
/* 154 */       reloadSiteConfigList();
/* 155 */       return true;
/*     */     }
/*     */ 
/* 158 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean insertSiteConfig(SiteConfigBean scb)
/*     */   {
/* 168 */     if (SiteConfigDAO.insertSiteConfigHandl(scb))
/*     */     {
/* 170 */       reloadSiteConfigList();
/* 171 */       return true;
/*     */     }
/*     */ 
/* 174 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateSiteConfig(SiteConfigBean scb)
/*     */   {
/* 184 */     SiteConfigDAO.updateSiteConfig(scb);
/* 185 */     reloadSiteConfigList();
/* 186 */     return true;
/*     */   }
/*     */ 
/*     */   public static boolean deleteSiteConfig(String config_ids)
/*     */   {
/*     */     try
/*     */     {
/* 198 */       SiteConfigDAO.deleteSiteConfig(config_ids);
/* 199 */       reloadSiteConfigList();
/* 200 */       return true;
/*     */     } catch (Exception e) {
/* 202 */       e.printStackTrace();
/* 203 */     }return false;
/*     */   }
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/* 212 */     System.out.println(config_map.get("nx"));
/*     */   }
/*     */ 
/*     */   public static void testInsert()
/*     */   {
/* 217 */     List c_list = new ArrayList();
/* 218 */     SiteConfigBean scb = new SiteConfigBean();
/* 219 */     scb.setSite_id("nx");
/* 220 */     scb.setConfig_key("path1");
/* 221 */     scb.setConfig_value("path_value1");
/*     */ 
/* 223 */     SiteConfigBean scb2 = new SiteConfigBean();
/* 224 */     scb2.setSite_id("nx");
/* 225 */     scb2.setConfig_key("name1");
/* 226 */     scb2.setConfig_value("name_value1");
/*     */ 
/* 228 */     SiteConfigBean scb3 = new SiteConfigBean();
/* 229 */     scb3.setSite_id("nx");
/* 230 */     scb3.setConfig_key("ssss1");
/* 231 */     scb3.setConfig_value("ssss_value1");
/* 232 */     c_list.add(scb);
/* 233 */     c_list.add(scb2);
/* 234 */     c_list.add(scb3);
/*     */ 
/* 236 */     insertSiteConfig(c_list, new SettingLogsBean());
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.control.config.SiteConfigManager
 * JD-Core Version:    0.6.2
 */