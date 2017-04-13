/*     */ package com.cicro.wcm.services.control.site;
/*     */ 
/*     */ import com.cicro.util.DateUtil;
/*     */ import com.cicro.util.FormatUtil;
/*     */ import com.cicro.util.jconfig.JconfigUtil;
/*     */ import com.cicro.util.jconfig.JconfigUtilContainer;
/*     */ import com.cicro.wcm.bean.control.SiteBean;
/*     */ import com.cicro.wcm.bean.control.SiteDomainBean;
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.bean.org.role.RoleUserBean;
/*     */ import com.cicro.wcm.catchs.ISyncCatch;
/*     */ import com.cicro.wcm.catchs.SyncCatchHandl;
/*     */ import com.cicro.wcm.dao.control.SiteDAO;
/*     */ import com.cicro.wcm.services.control.domain.SiteDomainManager;
/*     */ import com.cicro.wcm.services.minlu.MingLuManager;
/*     */ import com.cicro.wcm.services.org.role.RoleUserManager;
/*     */ import com.cicro.wcm.services.org.siteuser.SiteUserManager;
/*     */ import java.io.PrintStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.Set;
/*     */ 
/*     */ public class SiteManager
/*     */   implements ISyncCatch
/*     */ {
/*  39 */   private static List<SiteBean> site_list = new ArrayList();
/*  40 */   private static Map<String, SiteBean> site_map = new HashMap();
/*     */ 
/*     */   static {
/*  43 */     reloadCatchHandl();
/*     */   }
/*     */ 
/*     */   public void reloadCatch()
/*     */   {
/*  48 */     reloadCatchHandl();
/*     */   }
/*     */ 
/*     */   public static void reloadCatchHandl()
/*     */   {
/*  53 */     site_map.clear();
/*  54 */     site_list.clear();
/*  55 */     site_list = SiteDAO.getAllSiteList();
/*     */ 
/*  57 */     if ((site_list != null) && (site_list.size() > 0))
/*     */     {
/*  59 */       for (int i = 0; i < site_list.size(); i++)
/*     */       {
/*  61 */         ((SiteBean)site_list.get(i)).setSite_domain(SiteDomainManager.getSiteDomainBySiteID(((SiteBean)site_list.get(i)).getSite_id()));
/*  62 */         ((SiteBean)site_list.get(i)).setSite_manager(RoleUserManager.getUsersByRole(JconfigUtilContainer.systemRole().getProperty("control", "", "role_id"), "control", ((SiteBean)site_list.get(i)).getSite_id()));
/*  63 */         site_map.put(((SiteBean)site_list.get(i)).getSite_id(), (SiteBean)site_list.get(i));
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void reloadSiteList()
/*     */   {
/*  77 */     SyncCatchHandl.reladCatchForRMI("com.cicro.wcm.services.control.site.SiteManager");
/*     */   }
/*     */ 
/*     */   public static String getSitePort()
/*     */   {
/*  83 */     String port = JconfigUtilContainer.bashConfig().getProperty("port", "", "site_port");
/*  84 */     if ((port != null) && (!"".equals(port))) {
/*  85 */       return ":" + port;
/*     */     }
/*  87 */     return "";
/*     */   }
/*     */ 
/*     */   public static List<SiteBean> getSiteList()
/*     */   {
/*  98 */     List site_list = new ArrayList();
/*  99 */     Iterator iter = site_map.entrySet().iterator();
/* 100 */     while (iter.hasNext()) {
/* 101 */       Entry entry = (Entry)iter.next();
/* 102 */       site_list.add((SiteBean)entry.getValue());
/*     */     }
/* 104 */     Collections.sort(site_list, new SiteManager.SiteComparator());
/* 105 */     return site_list;
/*     */   }
/*     */ 
/*     */   public static List<SiteBean> getSiteListByServerID(String server_id)
/*     */   {
/* 117 */     List site_list = new ArrayList();
/* 118 */     Iterator iter = site_map.entrySet().iterator();
/* 119 */     while (iter.hasNext()) {
/* 120 */       Entry entry = (Entry)iter.next();
/* 121 */       SiteBean sb = (SiteBean)entry.getValue();
/* 122 */       if (server_id.equals(Integer.valueOf(sb.getServer_id())))
/* 123 */         site_list.add(sb);
/*     */     }
/* 125 */     return site_list;
/*     */   }
/*     */ 
/*     */   public static SiteBean getSiteBeanBySiteID(String site_id)
/*     */   {
/* 136 */     if (site_map.containsKey(site_id)) {
/* 137 */       return (SiteBean)site_map.get(site_id);
/*     */     }
/*     */ 
/* 140 */     SiteBean sb = SiteDAO.getSiteBean(site_id);
/* 141 */     if (sb != null)
/*     */     {
/* 143 */       site_map.put(site_id, sb);
/* 144 */       return sb;
/*     */     }
/* 146 */     return null;
/*     */   }
/*     */ 
/*     */   public static boolean siteIDISExist(String site_id)
/*     */   {
/* 158 */     Iterator iter = site_map.entrySet().iterator();
/* 159 */     while (iter.hasNext()) {
/* 160 */       Entry entry = (Entry)iter.next();
/* 161 */       SiteBean sb = (SiteBean)entry.getValue();
/* 162 */       if (site_id.equals(sb.getSite_id()))
/* 163 */         return true;
/*     */     }
/* 165 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean insertSite(SiteBean sb, SettingLogsBean stl)
/*     */   {
/*     */     try
/*     */     {
/* 177 */       SiteBean siteBeanParent = getSiteBeanBySiteID(sb.getParent_id());
/* 178 */       sb.setSite_position(siteBeanParent.getSite_position() + "$" + sb.getSite_id());
/*     */ 
/* 181 */       String add_time = DateUtil.getCurrentDateTime();
/* 182 */       sb.setSite_createtime(add_time);
/*     */ 
/* 185 */       if (SiteDAO.insertSite(sb, stl))
/*     */       {
/* 196 */         SiteDomainBean siteDomainBean = new SiteDomainBean();
/* 197 */         siteDomainBean.setSite_id(sb.getSite_id());
/* 198 */         siteDomainBean.setSite_domain(sb.getSite_domain());
/* 199 */         siteDomainBean.setIs_default(1);
/* 200 */         siteDomainBean.setIs_host(1);
/* 201 */         SiteDomainManager.insertSiteDomain(siteDomainBean, stl);
/*     */ 
/* 203 */         reloadSiteList();
/* 204 */         return true;
/*     */       }
/*     */ 
/* 207 */       return false;
/*     */     } catch (Exception e) {
/* 209 */       e.printStackTrace();
/* 210 */     }return false;
/*     */   }
/*     */ 
/*     */   public static boolean insertSiteUserManager(String app_id, String site_id, String insert_user_ids, String delete_user_ids, SettingLogsBean stl)
/*     */   {
/*     */     try
/*     */     {
/* 228 */       RoleUserBean rab = new RoleUserBean();
/* 229 */       rab.setApp_id(app_id);
/* 230 */       rab.setRole_id(JconfigUtilContainer.systemRole().getProperty(app_id, "", "role_id"));
/* 231 */       rab.setSite_id(site_id);
/* 232 */       rab.setUser_id(insert_user_ids);
/* 233 */       RoleUserManager.insertRoleUserByRole(rab, delete_user_ids, stl);
/*     */ 
/* 235 */       SiteUserManager.linkSiteUser(insert_user_ids, "", site_id, app_id, stl);
/* 236 */       return true;
/*     */     } catch (Exception e) {
/* 238 */       e.printStackTrace();
/* 239 */     }return false;
/*     */   }
/*     */ 
/*     */   public static String getUsersBySiteId(String app_id, String site_id)
/*     */   {
/*     */     try
/*     */     {
/* 254 */       String role_id = JconfigUtilContainer.systemRole().getProperty(app_id, "", "role_id");
/* 255 */       return RoleUserManager.getUsersByRole(role_id, app_id, site_id);
/*     */     } catch (Exception e) {
/* 257 */       e.printStackTrace();
/* 258 */     }return "";
/*     */   }
/*     */ 
/*     */   public static boolean insertSiteInit(SiteBean sb)
/*     */   {
/*     */     try
/*     */     {
/* 274 */       if (SiteDAO.insertSite(sb, null))
/*     */       {
/* 276 */         reloadSiteList();
/* 277 */         return true;
/*     */       }
/*     */ 
/* 280 */       return false;
/*     */     } catch (Exception e) {
/* 282 */       e.printStackTrace();
/* 283 */     }return false;
/*     */   }
/*     */ 
/*     */   public static int getSiteSortByID(String site_id)
/*     */   {
/*     */     try
/*     */     {
/* 295 */       List list = getSiteChildListByID(site_id);
/* 296 */       if ((list != null) && (list.size() > 0)) {
/* 297 */         return ((SiteBean)list.get(list.size() - 1)).getSite_sort();
/*     */       }
/* 299 */       return 0;
/*     */     }
/*     */     catch (Exception e) {
/* 302 */       e.printStackTrace();
/* 303 */     }return -1;
/*     */   }
/*     */ 
/*     */   public static boolean updateSite(SiteBean sb, SettingLogsBean stl)
/*     */   {
/* 316 */     if (SiteDAO.updateSite(sb, stl))
/*     */     {
/* 320 */       SiteDomainManager.updateSiteDomainByName(sb.getSite_domain(), sb.getSite_id());
/*     */ 
/* 329 */       reloadSiteList();
/* 330 */       return true;
/*     */     }
/*     */ 
/* 333 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateSiteStatus(String site_id, int site_status, SettingLogsBean stl)
/*     */   {
/* 345 */     if (SiteDAO.updateSiteStatus(site_id, site_status, stl))
/*     */     {
/* 347 */       reloadSiteList();
/* 348 */       return true;
/*     */     }
/*     */ 
/* 351 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean saveSiteSort(String site_ids, SettingLogsBean stl)
/*     */   {
/* 362 */     if (SiteDAO.saveSiteSort(site_ids, stl))
/*     */     {
/* 364 */       reloadSiteList();
/* 365 */       return true;
/*     */     }
/*     */ 
/* 368 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteSite(String site_id, SettingLogsBean stl)
/*     */   {
/* 380 */     if (SiteDAO.deleteSite(site_id, stl))
/*     */     {
/* 383 */       String user_ids = getUsersBySiteId("cms", site_id);
/* 384 */       String role_id = JconfigUtilContainer.systemRole().getProperty("cms", "", "role_id");
/*     */ 
/* 386 */       RoleUserManager.deleteRoleUserByUserRoleSite(user_ids, role_id, site_id);
/* 387 */       MingLuManager.deleteMingLuTemplate(site_id);
/* 388 */       reloadSiteList();
/* 389 */       return true;
/*     */     }
/*     */ 
/* 392 */     return false;
/*     */   }
/*     */ 
/*     */   public static List<SiteBean> getSiteChildListByID(String site_id)
/*     */   {
/* 429 */     List l = new ArrayList();
/* 430 */     if ((site_list != null) && (site_list.size() > 0))
/*     */     {
/* 432 */       for (int i = 0; i < site_list.size(); i++)
/*     */       {
/* 434 */         if (site_id.trim().equals(((SiteBean)site_list.get(i)).getParent_id())) {
/* 435 */           l.add((SiteBean)site_list.get(i));
/*     */         }
/*     */       }
/*     */     }
/* 439 */     return l;
/*     */   }
/*     */ 
/*     */   public static SiteBean getSiteRoot(String site_id)
/*     */   {
/* 450 */     return (SiteBean)getSiteChildListByID(site_id).get(0);
/*     */   }
/*     */ 
/*     */   public static String getSiteTreeJsonStr()
/*     */   {
/*     */     try
/*     */     {
/* 462 */       SiteBean siteBean = getSiteRoot("0");
/* 463 */       if (siteBean != null)
/*     */       {
/* 465 */         String json_str = "[{\"id\":\"" + siteBean.getSite_id() + "\",\"text\":\"" + siteBean.getSite_name() + "\",\"attributes\":{\"url\":\"" + 
/* 466 */           siteBean.getSgroup_id() + "\"}";
/* 467 */         String child_str = getSiteTreeJsonStrHandl(getChildSiteListByDeep(siteBean.getSite_id()));
/* 468 */         if ((child_str != null) && (!"".equals(child_str)))
/* 469 */           json_str = json_str + ",\"children\":[" + child_str + "]";
/* 470 */         return json_str + "}]";
/*     */       }
/*     */ 
/* 473 */       return "[]";
/*     */     } catch (Exception e) {
/* 475 */       e.printStackTrace();
/* 476 */     }return "[]";
/*     */   }
/*     */ 
/*     */   public static String getSiteTreeJsonStrHandl(List<SiteBean> all_site_list)
/*     */   {
/* 489 */     String json_str = "";
/* 490 */     if ((all_site_list != null) && (all_site_list.size() > 0))
/*     */     {
/* 492 */       for (int i = 0; i < all_site_list.size(); i++)
/*     */       {
/* 494 */         json_str = json_str + "{";
/* 495 */         json_str = json_str + "\"id\":\"" + ((SiteBean)all_site_list.get(i)).getSite_id() + "\",\"text\":\"" + ((SiteBean)all_site_list.get(i)).getSite_name() + "\",\"attributes\":{\"url\":\"" + 
/* 496 */           ((SiteBean)all_site_list.get(i)).getSite_id() + "\"}";
/* 497 */         List child_m_list = ((SiteBean)all_site_list.get(i)).getChild_list();
/* 498 */         if ((child_m_list != null) && (child_m_list.size() > 0))
/* 499 */           json_str = json_str + ",\"children\":[" + getSiteTreeJsonStrHandl(child_m_list) + "]";
/* 500 */         json_str = json_str + "}";
/* 501 */         if (i + 1 != all_site_list.size())
/* 502 */           json_str = json_str + ",";
/*     */       }
/*     */     }
/* 505 */     return json_str;
/*     */   }
/*     */ 
/*     */   public static List<SiteBean> getChildSiteListByDeep(String site_id)
/*     */   {
/* 515 */     List g_List = new ArrayList();
/* 516 */     Iterator iter = site_map.entrySet().iterator();
/* 517 */     while (iter.hasNext()) {
/* 518 */       Entry entry = (Entry)iter.next();
/* 519 */       SiteBean gb = (SiteBean)entry.getValue();
/* 520 */       if (site_id.equals(gb.getParent_id())) {
/* 521 */         gb.setChild_list(getChildSiteListByDeep(gb.getSite_id()));
/* 522 */         g_List.add((SiteBean)entry.getValue());
/*     */       }
/*     */     }
/* 525 */     Collections.sort(g_List, new SiteManager.SiteComparator());
/* 526 */     return g_List;
/*     */   }
/*     */ 
/*     */   public static String getSiteTempletPath(String site_id)
/*     */   {
/* 536 */     String path = "";
/* 537 */     SiteBean sb = getSiteBeanBySiteID(site_id);
/* 538 */     if (sb != null)
/*     */     {
/* 540 */       path = sb.getSite_path();
/* 541 */       path = FormatUtil.formatPath(path + JconfigUtilContainer.velocityTemplateConfig().getProperty("path", "", "templet_path"));
/*     */     }
/* 543 */     return path;
/*     */   }
/*     */ 
/*     */   public static String getSitePath(String site_id)
/*     */   {
/* 553 */     String path = "";
/* 554 */     SiteBean sb = getSiteBeanBySiteID(site_id);
/* 555 */     if (sb != null)
/*     */     {
/* 557 */       return sb.getSite_path();
/*     */     }
/* 559 */     return path;
/*     */   }
/*     */ 
/*     */   public static String getSiteByDomain(String site_domain)
/*     */   {
/* 569 */     return SiteDomainManager.getSiteByDomain(site_domain);
/*     */   }
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/* 575 */     String ss = "he per capita GDP will exceed US$ 4,500 by 2011, doubling that in 2005, assuring the well-off";
/* 576 */     System.out.println(getChildSiteListByDeep("0"));
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.control.site.SiteManager
 * JD-Core Version:    0.6.2
 */