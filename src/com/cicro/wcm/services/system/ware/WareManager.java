/*     */ package com.cicro.wcm.services.system.ware;
/*     */ 
/*     */ import com.cicro.util.DateUtil;
/*     */ import com.cicro.util.FormatUtil;
/*     */ import com.cicro.util.io.FileOperation;
/*     */ import com.cicro.wcm.bean.control.SiteBean;
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.bean.system.ware.WareBean;
/*     */ import com.cicro.wcm.catchs.ISyncCatch;
/*     */ import com.cicro.wcm.catchs.SyncCatchHandl;
/*     */ import com.cicro.wcm.dao.PublicTableDAO;
/*     */ import com.cicro.wcm.dao.system.ware.WareDAO;
/*     */ import com.cicro.wcm.dao.system.ware.WareInfoDAO;
/*     */ import com.cicro.wcm.rmi.file.FileRmiFactory;
/*     */ import com.cicro.wcm.services.control.site.SiteManager;
/*     */ import com.cicro.wcm.template.velocity.impl.VelocityWareContextImp;

/*     */ import java.io.IOException;
/*     */ import java.io.PrintStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
import java.util.Comparator;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ 
/*     */ public class WareManager
/*     */   implements ISyncCatch
/*     */ {
/*  31 */   private static Map<String, WareBean> ware_map = new HashMap();
/*     */ 
/*     */   static {
/*  34 */     reloadCatchHandl();
/*     */   }
/*     */ 
/*     */   public void reloadCatch()
/*     */   {
/*  39 */     reloadCatchHandl();
/*     */   }
/*     */ 
/*     */   public static void reloadCatchHandl()
/*     */   {
/*     */     try {
/*  45 */       List lt = WareDAO.getAllWareList();
/*  46 */       ware_map.clear();
/*  47 */       if (lt != null)
/*     */       {
/*  49 */         for (int i = 0; i < lt.size(); i++)
/*     */         {
/*  51 */           ware_map.put(((WareBean)lt.get(i)).getId(), (WareBean)lt.get(i));
/*     */         }
/*     */       }
/*     */     }
/*     */     catch (Exception e) {
/*  56 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void reloadMap()
/*     */   {
/*  65 */     SyncCatchHandl.reladCatchForRMI("com.cicro.wcm.services.system.ware.WareManager");
/*     */   }
/*     */ 
/*     */   public static List<WareBean> getWareListByType(String site_id, int ware_type)
/*     */   {
/*  76 */     List l = new ArrayList();
/*  77 */     Set<String> s = ware_map.keySet();
/*  78 */     for (String i : s)
/*     */     {
/*  80 */       WareBean w = (WareBean)ware_map.get(i);
/*  81 */       if ((w.getSite_id().equals(site_id)) && (w.getWare_type() == ware_type))
/*     */       {
/*  83 */         l.add(w);
/*     */       }
/*     */     }
/*  86 */     return l;
/*     */   }
/*     */ 
/*     */   public static List<WareBean> getWareListByTypeAndCateID(Map<String, String> mp, int ware_type)
/*     */   {
/*  97 */     String cat_id = WareCategoryManager.getAllChildCateIDS(mp);
/*  98 */     return getWareListByCateType(cat_id, ware_type, mp);
/*     */   }
/*     */ 
/*     */   public static WareBean getWareByID(String id)
/*     */   {
/* 108 */     return WareDAO.getWareByID(id);
/*     */   }
/*     */ 
/*     */   public static WareBean getWareBeanByWareID(String ware_id, String site_id)
/*     */   {
/* 119 */     Map m = new HashMap();
/* 120 */     m.put("ware_id", ware_id);
/* 121 */     m.put("site_id", site_id);
/* 122 */     return WareDAO.getWareBeanByWareID(m);
/*     */   }
/*     */ 
/*     */   public static boolean insertWare(WareBean wb, SettingLogsBean stl)
/*     */     throws IOException
/*     */   {
/* 134 */     int id = PublicTableDAO.getIDByTableName(PublicTableDAO.WARE_TABLE_NAME);
/* 135 */     wb.setId(id+"");
/* 136 */     wb.setWare_id(id+"");
/* 137 */     if (WareDAO.insertWare(wb, stl))
/*     */     {
/* 139 */       if (wb.getWare_type() == 2)
/*     */       {
/* 141 */         WareInfoManager.insertWareInfoByRowNum(id, wb.getInfo_num(), wb.getApp_id(), wb.getSite_id());
/*     */       }
/* 143 */       reloadMap();
/* 144 */       if (((wb.getWare_type() == 0) || (wb.getWare_type() == 1)) && (!"".equals(wb.getWare_content().trim())))
/* 145 */         createWarePage(wb);
/* 146 */       return true;
/*     */     }
/*     */ 
/* 149 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateWareContent(WareBean wb, SettingLogsBean stl)
/*     */   {
/* 161 */     if (WareDAO.updateWareContent(wb, stl)) {
/*     */       try
/*     */       {
/* 164 */         createWarePage(wb);
/* 165 */         return true;
/*     */       }
/*     */       catch (IOException e) {
/* 168 */         return false;
/*     */       }
/*     */     }
/*     */ 
/* 172 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean createHtmlPageMutil(String ware_ids, String site_id)
/*     */   {
/* 183 */     if ((ware_ids != null) && (!"".equals(ware_ids))) {
/*     */       try
/*     */       {
/* 186 */         String[] arrTemp = ware_ids.split(",");
/* 187 */         for (int i = 0; i < arrTemp.length; i++)
/*     */         {
/* 189 */           createWarePage(arrTemp[i], site_id);
/*     */         }
/* 191 */         return true;
/*     */       }
/*     */       catch (Exception e) {
/* 194 */         e.printStackTrace();
/* 195 */         return false;
/*     */       }
/*     */     }
/* 198 */     return true;
/*     */   }
/*     */ 
/*     */   public static void createWarePage(WareBean wb)
/*     */     throws IOException
/*     */   {
/* 210 */     FileRmiFactory.createWarePage(wb);
/*     */   }
/*     */ 
/*     */   public static void createWarePageHandl(WareBean wb) throws IOException
/*     */   {
/* 215 */     SiteBean sb = SiteManager.getSiteBeanBySiteID(wb.getSite_id());
/* 216 */     if (sb != null)
/*     */     {
/* 218 */       String save_path = sb.getSite_path() + "/include";
/* 219 */       save_path = FormatUtil.formatPath(save_path + "/ware_" + wb.getWare_id() + ".html");
/* 220 */       String content = "";
/* 221 */       VelocityWareContextImp vwc = new VelocityWareContextImp(wb);
/* 222 */       content = vwc.parseTemplate(wb.getWare_content().replaceAll("\\$site_id", wb.getSite_id()));
/*     */ 
/* 224 */       FileOperation.writeStringToFile(save_path, content, false, "utf-8");
/*     */ 
/* 226 */       Map m = new HashMap();
/* 227 */       m.put("update_dtime", DateUtil.getCurrentDateTime());
/* 228 */       m.put("id", wb.getId());
/*     */ 
/* 231 */       if (wb.getWare_type() > 0)
/*     */       {
/* 233 */         m.put("next_dtime", DateUtil.getDateTimeAfter(DateUtil.getCurrentDateTime(), wb.getWare_interval()));
/*     */       }
/* 235 */       WareDAO.updateWareTime(m);
/*     */     }
/*     */   }
/*     */ 
/*     */   public static boolean createWarePage(String ware_id, String site_id)
/*     */   {
/* 247 */     WareBean wb = getWareBeanByWareID(ware_id, site_id);
/* 248 */     if (wb == null)
/*     */     {
/* 250 */       return false;
/*     */     }
/*     */ 
/*     */     try
/*     */     {
/* 255 */       createWarePage(wb);
/* 256 */       return true;
/*     */     }
/*     */     catch (IOException e) {
/* 259 */       e.printStackTrace();
/* 260 */     }return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateWare(WareBean wb, SettingLogsBean stl)
/*     */     throws IOException
/*     */   {
/* 274 */     if (WareDAO.updateWare(wb, stl))
/*     */     {
/* 276 */       reloadMap();
/* 277 */       if ((wb.getWare_type() == 0) || (wb.getWare_type() == 1) || (wb.getWare_type() == 2))
/* 278 */         createWarePage(wb);
/* 279 */       return true;
/*     */     }
/*     */ 
/* 283 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean savaWareSort(String ids, SettingLogsBean stl)
/*     */   {
/* 295 */     boolean flg = true;
/* 296 */     String[] arrIDS = ids.split(",");
/* 297 */     for (int i = 0; i < arrIDS.length; i++)
/*     */     {
/* 299 */       WareBean wb = new WareBean();
/* 300 */       wb.setId(arrIDS[i]);
/* 301 */       wb.setSort_id(i);
/* 302 */       flg = WareDAO.savaWareSort(wb, stl) ? flg : false;
/*     */     }
/* 304 */     reloadMap();
/* 305 */     return flg;
/*     */   }
/*     */ 
/*     */   public static boolean deleteWare(Map<String, String> mp, SettingLogsBean stl)
/*     */   {
/* 316 */     if (WareDAO.deleteWare(mp, stl))
/*     */     {
/* 318 */       reloadMap();
/* 319 */       WareInfoDAO.deleteWareInfoByWareID(mp, stl);
/* 320 */       WareInfoDAO.deleteWareInfosByWareID(mp, stl);
/*     */ 
/* 322 */       deleteWarePage(mp);
/* 323 */       return true;
/*     */     }
/*     */ 
/* 327 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteWareByWcatIDS(Map<String, String> mp, SettingLogsBean stl)
/*     */   {
/* 339 */     if (WareDAO.deleteWareByWcatIDS(mp, stl))
/*     */     {
/* 341 */       mp.put("ware_id", getWareIDSByCat((String)mp.get("id"), mp));
/* 342 */       deleteWarePage(mp);
/* 343 */       reloadMap();
/* 344 */       return true;
/*     */     }
/*     */ 
/* 348 */     return false;
/*     */   }
/*     */ 
/*     */   public static void deleteWarePage(Map<String, String> mp)
/*     */   {
/* 359 */     String[] tempA = ((String)mp.get("ware_id")).split(",");
/* 360 */     SiteBean sb = SiteManager.getSiteBeanBySiteID((String)mp.get("site_id"));
/* 361 */     String save_path = sb.getSite_path() + "/include";
/* 362 */     if (sb != null)
/*     */     {
/* 364 */       for (int i = 0; i < tempA.length; i++)
/*     */       {
/* 366 */         save_path = FormatUtil.formatPath(save_path + "/ware_" + tempA[i] + ".html");
/*     */ 
/* 369 */         FileRmiFactory.delFile((String)mp.get("site_id"), save_path);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public static List<WareBean> getWareList(String wcat_id, Map<String, String> mp)
/*     */   {
/* 381 */     List retList = new ArrayList();
/* 382 */     Iterator it = ware_map.values().iterator();
/* 383 */     while (it.hasNext())
/*     */     {
/* 385 */       WareBean wb = (WareBean)it.next();
/* 386 */       if ((wcat_id.equals(wb.getWcat_id())) && (isSameAppAndSite(mp, wb)))
/*     */       {
/* 388 */         retList.add(wb);
/*     */       }
/*     */     }
/* 391 */     Collections.sort(retList, new WareComparator());
/* 392 */     return retList;
/*     */   }
/*     */ 
/*     */   public static List<WareBean> getWareListByCateType(String wcat_ids, int ware_type, Map<String, String> mp)
/*     */   {
/* 404 */     List retList = new ArrayList();
/* 405 */     Iterator it = ware_map.values().iterator();
/* 406 */     wcat_ids = "," + wcat_ids + ",";
/* 407 */     while (it.hasNext())
/*     */     {
/* 409 */       WareBean wb = (WareBean)it.next();
/* 410 */       if ((wb.getWare_type() == ware_type) && (wcat_ids.contains("," + wb.getWcat_id() + ",")) && (isSameAppAndSite(mp, wb)))
/*     */       {
/* 412 */         retList.add(wb);
/*     */       }
/*     */     }
/* 415 */     Collections.sort(retList, new WareComparator());
/* 416 */     return retList;
/*     */   }
/*     */ 
/*     */   public static String getWareIDSByCat(String wcat_ids, Map<String, String> mp)
/*     */   {
/* 426 */     String ware_ids = "";
/* 427 */     wcat_ids = "," + wcat_ids + ",";
/* 428 */     Iterator it = ware_map.values().iterator();
/* 429 */     while (it.hasNext())
/*     */     {
/* 431 */       WareBean wb = (WareBean)it.next();
/* 432 */       if ((wcat_ids.contains("," + wb.getWcat_id() + ",")) && (isSameAppAndSite(mp, wb)))
/*     */       {
/* 434 */         ware_ids = ware_ids + "," + wb.getWare_id();
/*     */       }
/*     */     }
/* 437 */     if ((ware_ids != null) && (!"".equals(ware_ids)) && (ware_ids.length() > 0))
/* 438 */       ware_ids = ware_ids.substring(1);
/* 439 */     return ware_ids;
/*     */   }
/*     */ 
/*     */   public static List<WareBean> getWareListOfSite(Map<String, String> mp)
/*     */   {
/* 449 */     List retList = new ArrayList();
/* 450 */     Iterator it = ware_map.values().iterator();
/* 451 */     while (it.hasNext())
/*     */     {
/* 453 */       WareBean wb = (WareBean)it.next();
/* 454 */       if (isSameAppAndSite(mp, wb))
/*     */       {
/* 456 */         retList.add(wb);
/*     */       }
/*     */     }
/* 459 */     Collections.sort(retList, new WareComparator());
/* 460 */     return retList;
/*     */   }
/*     */ 
/*     */   public static boolean moveWareToOtherCategory(Map<String, String> mp, SettingLogsBean stl) {
/* 464 */     if (WareDAO.moveWareToOtherCategory(mp, stl))
/*     */     {
/* 466 */       reloadMap();
/* 467 */       return true;
/*     */     }
/*     */ 
/* 470 */     return false;
/*     */   }
/*     */ 
/*     */   private static boolean isSameAppAndSite(Map<String, String> mp, WareBean wb)
/*     */   {
/* 481 */     boolean sflg = false;
/* 482 */     boolean aflg = false;
/* 483 */     String site_id = (String)mp.get("site_id");
/* 484 */     String app_id = (String)mp.get("app_id");
/*     */ 
/* 487 */     if ("".equals(site_id))
/*     */     {
/* 489 */       sflg = true;
/*     */     }
/* 491 */     else if (site_id.equals(wb.getSite_id()))
/*     */     {
/* 493 */       sflg = true;
/*     */     }
/*     */ 
/* 497 */     if (app_id.equals(wb.getApp_id()))
/*     */     {
/* 499 */       aflg = true;
/*     */     }
/*     */     else
/*     */     {
/* 503 */       aflg = false;
/*     */     }
/* 505 */     aflg = true;
/* 506 */     return (sflg) && (aflg);
/*     */   }
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/* 537 */     String xml = "site_id=$site_id"; 
/*     */ 
/* 539 */     System.out.println(xml.replaceAll("\\$site_id", "11111ddd"));
/*     */   }
/*     */ }
class WareComparator
/*     */   implements Comparator<WareBean>
/*     */ {
/*     */   public int compare(WareBean o1, WareBean o2)
/*     */   {
/* 514 */     int flg = 0;
/* 515 */     if (o1.getSort_id() > o2.getSort_id())
/*     */     {
/* 517 */       flg = 1;
/*     */     }
/* 519 */     else if (o1.getSort_id() == o2.getSort_id())
/*     */     {
				if(Integer.parseInt(o1.getId()) > Integer.parseInt(o2.getId()))
				{
/* 521 */       	flg = 1;
				}
				else
				{
					flg = -1;
				}
/*     */     }
/*     */     else
/*     */     {
/* 525 */       flg = -1;
/*     */     }
/* 527 */     return flg;
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.system.ware.WareManager
 * JD-Core Version:    0.6.2
 */