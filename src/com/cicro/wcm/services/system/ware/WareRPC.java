/*     */ package com.cicro.wcm.services.system.ware;
/*     */ 
/*     */ import com.cicro.wcm.bean.cms.info.InfoBean;
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.bean.system.ware.WareBean;
/*     */ import com.cicro.wcm.bean.system.ware.WareCategoryBean;
/*     */ import com.cicro.wcm.bean.system.ware.WareInfoBean;
/*     */ import com.cicro.wcm.bean.system.ware.WareInfos;
/*     */ import com.cicro.wcm.bean.system.ware.WareReleUser;
/*     */ import com.cicro.wcm.bean.system.ware.WareVerBean;
/*     */ import com.cicro.wcm.services.Log.LogManager;
/*     */ import java.io.IOException;
/*     */ import java.io.PrintStream;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ 
/*     */ public class WareRPC
/*     */ {
/*     */   public static String getJSONTreeBySiteUser(int user_id, String site_id)
/*     */   {
/*  23 */     return WareCategoryManager.getJSONTreeBySiteUser(user_id, site_id);
/*     */   }
/*     */ 
/*     */   public static String getJSONTreeStr(Map<String, String> mp)
/*     */   {
/*  28 */     return WareCategoryManager.getJSONTreeStr(mp);
/*     */   }
/*     */ 
/*     */   public static List<WareCategoryBean> getWareCateList(String id, Map<String, String> mp)
/*     */   {
/*  33 */     return WareCategoryManager.getWareCateList(id, mp);
/*     */   }
/*     */ 
/*     */   public static WareCategoryBean getWareCategoryByID(String id)
/*     */   {
/*  38 */     return WareCategoryManager.getWareCategoryByID(id);
/*     */   }
/*     */ 
/*     */   public static boolean insertWareCate(WareCategoryBean wcb, HttpServletRequest request)
/*     */   {
/*  43 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/*  44 */     if (stl != null)
/*     */     {
/*  46 */       return WareCategoryManager.insertWareCate(wcb, stl);
/*     */     }
/*     */ 
/*  49 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateWareCate(WareCategoryBean wcb, HttpServletRequest request)
/*     */   {
/*  54 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/*  55 */     if (stl != null)
/*     */     {
/*  57 */       return WareCategoryManager.updateWareCategory(wcb, stl);
/*     */     }
/*     */ 
/*  60 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean saveSort(String ids, HttpServletRequest request)
/*     */   {
/*  65 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/*  66 */     if (stl != null)
/*     */     {
/*  68 */       return WareCategoryManager.saveSort(ids, stl);
/*     */     }
/*     */ 
/*  71 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteWareCategory(Map<String, String> mp, HttpServletRequest request)
/*     */   {
/*  76 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/*  77 */     if (stl != null)
/*     */     {
/*  79 */       return WareCategoryManager.deleteWareCategory(mp, stl);
/*     */     }
/*     */ 
/*  82 */     return false;
/*     */   }
/*     */ 
/*     */   public static List<WareBean> getHandWareList(String site_id)
/*     */   {
/*  87 */     return WareManager.getWareListByType(site_id, 2);
/*     */   }
/*     */ 
/*     */   public static List<WareBean> getHandWareListByCateID(Map<String, String> mp)
/*     */   {
/*  92 */     return WareManager.getWareListByTypeAndCateID(mp, 2);
/*     */   }
/*     */ 
/*     */   public static boolean createHtmlPageMutil(String ware_ids, String site_id)
/*     */   {
/*  97 */     return WareManager.createHtmlPageMutil(ware_ids, site_id);
/*     */   }
/*     */ 
/*     */   public static boolean createHtmlPage(String ware_id, String site_id)
/*     */   {
/* 102 */     return WareManager.createWarePage(ware_id, site_id);
/*     */   }
/*     */ 
/*     */   public static WareBean getWareBeanByWareID(String ware_id, String site_id)
/*     */   {
/* 107 */     return WareManager.getWareBeanByWareID(ware_id, site_id);
/*     */   }
/*     */ 
/*     */   public static WareBean getWareByID(String id)
/*     */   {
/* 112 */     return WareManager.getWareByID(id);
/*     */   }
/*     */ 
/*     */   public static List<WareBean> getWareList(String wcat_id, Map<String, String> mp)
/*     */   {
/* 117 */     return WareManager.getWareList(wcat_id, mp);
/*     */   }
/*     */ 
/*     */   public static List<WareBean> getAllWareList(Map<String, String> mp)
/*     */   {
/* 122 */     return WareManager.getWareListOfSite(mp);
/*     */   }
/*     */ 
/*     */   public static boolean insertWare(WareBean wb, HttpServletRequest request)
/*     */     throws IOException
/*     */   {
/* 128 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 129 */     if (stl != null)
/*     */     {
/* 131 */       return WareManagerExtend.insertWare(wb, stl);
/*     */     }
/*     */ 
/* 134 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateWare(WareBean wb, String recoveryType, HttpServletRequest request)
/*     */     throws IOException
/*     */   {
/* 140 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 141 */     if (stl != null)
/*     */     {
/* 143 */       return WareManagerExtend.updateWare(wb, recoveryType, stl);
/*     */     }
/*     */ 
/* 146 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateWareContent(WareBean wb, HttpServletRequest request)
/*     */   {
/* 151 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 152 */     if (stl != null)
/*     */     {
/* 154 */       return WareManager.updateWareContent(wb, stl);
/*     */     }
/*     */ 
/* 157 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean savaWareSort(String ids, HttpServletRequest request)
/*     */   {
/* 162 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 163 */     if (stl != null)
/*     */     {
/* 165 */       return WareManager.savaWareSort(ids, stl);
/*     */     }
/*     */ 
/* 168 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteWare(Map<String, String> mp, HttpServletRequest request)
/*     */   {
/* 173 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 174 */     if (stl != null)
/*     */     {
/* 176 */       return WareManager.deleteWare(mp, stl);
/*     */     }
/*     */ 
/* 179 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean moveWareToOtherCategory(Map<String, String> mp, HttpServletRequest request)
/*     */   {
/* 184 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 185 */     if (stl != null)
/*     */     {
/* 187 */       return WareManager.moveWareToOtherCategory(mp, stl);
/*     */     }
/*     */ 
/* 190 */     return false;
/*     */   }
/*     */ 
/*     */   public static List<WareReleUser> getWareReleUserListByCat(int wcat_id, String site_id)
/*     */   {
/* 195 */     return WareReleUserManager.getWareReleUserListByCat(wcat_id, site_id);
/*     */   }
/*     */ 
/*     */   public static boolean insertWareReleUserByCat(int wcat_id, String usre_ids, String group_ids, String app_id, String site_id, HttpServletRequest request)
/*     */   {
/* 200 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 201 */     if (stl != null)
/*     */     {
/* 203 */       return WareReleUserManager.insertWareReleUserByCat(wcat_id, usre_ids, group_ids, app_id, site_id, stl);
/*     */     }
/*     */ 
/* 206 */     return false;
/*     */   }
/*     */ 
/*     */   public static String getWareInfoRefCount(Map<String, String> m)
/*     */   {
/* 211 */     return WareInfoManager.getWareInfoRefCount(m);
/*     */   }
/*     */ 
/*     */   public static List<InfoBean> getWareInfoRefList(Map<String, String> m)
/*     */   {
/* 216 */     return WareInfoManager.getWareInfoRefList(m);
/*     */   }
/*     */ 
/*     */   public static List<WareBean> getWareListByRefInfo(String info_id, String app_id, String site_id)
/*     */   {
/* 221 */     return WareInfoManager.getWareListByRefInfo(info_id, app_id, site_id);
/*     */   }
/*     */ 
/*     */   public static boolean insertWareInfoRef(String info_ids, int ware_id, int user_id, String app_id, String site_id, HttpServletRequest request)
/*     */   {
/* 226 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 227 */     if (stl != null)
/*     */     {
/* 229 */       return WareInfoManager.insertWareInfoRef(info_ids, ware_id, user_id, app_id, site_id, stl);
/*     */     }
/*     */ 
/* 232 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteWareInfoRef(String info_ids, String ware_ids, String app_id, String site_id, HttpServletRequest request)
/*     */   {
/* 237 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 238 */     if (stl != null)
/*     */     {
/* 240 */       return WareInfoManager.deleteWareInfoRef(info_ids, ware_ids, app_id, site_id, stl);
/*     */     }
/*     */ 
/* 243 */     return false;
/*     */   }
/*     */ 
/*     */   public static int insertWareInfo(int ware_id, int sort_id, int info_num, String app_id, String site_id)
/*     */   {
/* 248 */     return WareInfoManager.insertWareInfo(ware_id, sort_id, info_num, app_id, site_id);
/*     */   }
/*     */ 
/*     */   public static boolean sortWareInfo(String winfo_ids)
/*     */   {
/* 253 */     return WareInfoManager.sortWareInfo(winfo_ids);
/*     */   }
/*     */ 
/*     */   public static boolean deleteWareInfoByWInfoID(int ware_id, String winfo_id, int info_num, String app_id, String site_id, HttpServletRequest request)
/*     */   {
/* 258 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 259 */     if (stl != null)
/*     */     {
/* 261 */       return WareInfoManager.deleteWareInfoByWInfoID(ware_id, winfo_id, info_num, app_id, site_id, stl);
/*     */     }
/*     */ 
/* 264 */     return false;
/*     */   }
/*     */ 
/*     */   public static List<WareInfoBean> getWareInfoList(Map<String, String> m)
/*     */   {
/* 269 */     return WareInfoManager.getWareInfoList(m);
/*     */   }
/*     */ 
/*     */   public static WareInfos getWareInfosBean(int id)
/*     */   {
/* 274 */     return WareInfoManager.getWareInfosBean(id);
/*     */   }
/*     */ 
/*     */   public static int getNewWareInfosID()
/*     */   {
/* 279 */     return WareInfoManager.getNewWareInfosID();
/*     */   }
/*     */ 
/*     */   public static boolean insertWareInfos(WareInfos wif, HttpServletRequest request)
/*     */   {
/* 284 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 285 */     if (stl != null)
/*     */     {
/* 287 */       return WareInfoManager.insertWareInfos(wif, stl);
/*     */     }
/*     */ 
/* 290 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateWareInfos(WareInfos wif, HttpServletRequest request)
/*     */   {
/* 295 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 296 */     if (stl != null)
/*     */     {
/* 298 */       return WareInfoManager.updateWareInfos(wif, stl);
/*     */     }
/*     */ 
/* 301 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean sortWInfos(String ids)
/*     */   {
/* 306 */     return WareInfoManager.sortWInfos(ids);
/*     */   }
/*     */ 
/*     */   public static boolean deleteWareInfosByID(int id, HttpServletRequest request)
/*     */   {
/* 311 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 312 */     if (stl != null)
/*     */     {
/* 314 */       return WareInfoManager.deleteWareInfosByID(id, stl);
/*     */     }
/*     */ 
/* 317 */     return false;
/*     */   }
/*     */ 
/*     */   public static List<WareVerBean> getWareVerList(Map<String, String> m)
/*     */   {
/* 325 */     return WareManagerExtend.getWareVerList(m);
/*     */   }
/*     */ 
/*     */   public static String getWareVerListCount(String ware_id)
/*     */   {
/* 330 */     return WareManagerExtend.getWareVerListCount(ware_id);
/*     */   }
/*     */ 
/*     */   public static boolean recoveryWareVer(String wareid, String siteid, String verNum, HttpServletRequest request) throws IOException
/*     */   {
/* 335 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 336 */     if (stl != null)
/*     */     {
/* 338 */       return WareManagerExtend.recoveryWareVer(wareid, siteid, verNum, stl);
/*     */     }
/* 340 */     return false;
/*     */   }
/*     */ 
/*     */   public static void main(String[] arg)
/*     */   {
/* 346 */     Map m = new HashMap();
/* 347 */     m.put("app_id", "cms");
/* 348 */     m.put("site_id", "CMScattt");
/* 349 */     System.out.println(getWareList("1", m));
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.system.ware.WareRPC
 * JD-Core Version:    0.6.2
 */