/*     */ package com.cicro.wcm.services.org.role;
/*     */ 
/*     */ import com.cicro.util.jconfig.JconfigUtil;
/*     */ import com.cicro.util.jconfig.JconfigUtilContainer;
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.bean.org.app.AppBean;
/*     */ import com.cicro.wcm.bean.org.role.RoleBean;
/*     */ import com.cicro.wcm.bean.org.role.RoleUserBean;
/*     */ import com.cicro.wcm.bean.org.user.UserBean;
/*     */ import com.cicro.wcm.catchs.ISyncCatch;
/*     */ import com.cicro.wcm.catchs.SyncCatchHandl;
/*     */ import com.cicro.wcm.dao.org.role.RoleUserDAO;
/*     */ import com.cicro.wcm.services.org.app.AppManager;
/*     */ import com.cicro.wcm.services.org.group.GroupManager;
/*     */ import com.cicro.wcm.services.org.user.UserLogin;
/*     */ import com.cicro.wcm.services.org.user.UserManager;
/*     */ import java.io.PrintStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.Set;
/*     */ import java.util.TreeMap;
/*     */ 
/*     */ public class RoleUserManager
/*     */   implements ISyncCatch
/*     */ {
/*  39 */   private static TreeMap<String, RoleUserBean> role_user_map = new TreeMap();
/*     */ 
/*     */   static {
/*  42 */     reloadCatchHandl();
/*     */   }
/*     */ 
/*     */   public void reloadCatch()
/*     */   {
/*  47 */     reloadCatchHandl();
/*     */   }
/*     */ 
/*     */   public static void reloadCatchHandl()
/*     */   {
/*  52 */     List role_user_list = RoleUserDAO.getAllRoleUserList();
/*     */ 
/*  54 */     role_user_map.clear();
/*  55 */     if ((role_user_list != null) && (role_user_list.size() > 0))
/*  56 */       for (RoleUserBean rub : role_user_list)
/*  57 */         role_user_map.put(rub.getUser_role_id(), rub);
/*     */   }
/*     */ 
/*     */   public static void reloadRoleUser()
/*     */   {
/*  70 */     SyncCatchHandl.reladCatchForRMI("com.cicro.wcm.services.org.role.RoleUserManager");
/*     */   }
/*     */ 
/*     */   public static boolean isAppSuperManager(String user_id, String app_id)
/*     */   {
/*  80 */     String admin_role_id = JconfigUtilContainer.systemRole().getProperty(app_id, "", "role_id");
/*  81 */     List role_l = getAllUserRoleList(user_id);
/*  82 */     if ((role_l != null) && (role_l.size() > 0))
/*     */     {
/*  84 */       for (RoleBean rb : role_l)
/*     */       {
/*  86 */         if (admin_role_id.equals(rb.getRole_id()))
/*  87 */           return true;
/*     */       }
/*  89 */       return false;
/*     */     }
/*  91 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean isSiteManager(String user_id, String app_id, String site_id)
/*     */   {
/* 102 */     String admin_role_id = JconfigUtilContainer.systemRole().getProperty(app_id, "", "role_id");
/*     */ 
/* 104 */     Iterator iter = role_user_map.entrySet().iterator();
/* 105 */     while (iter.hasNext()) {
/* 106 */       Entry entry = (Entry)iter.next();
/* 107 */       RoleUserBean rub = (RoleUserBean)entry.getValue();
/*     */ 
/* 109 */       if ((admin_role_id.equals(rub.getRole_id())) && (user_id.equals(rub.getUser_id())) && ((app_id.equals(rub.getApp_id())) || ("system".equals(rub.getApp_id()))) && ((("ggfw".equals(site_id)) && ("ggfw".equals(app_id))) || ("".equals(site_id)) || (site_id.equals(rub.getSite_id()))))
/*     */       {
/* 111 */         return true;
/*     */       }
/*     */     }
/* 114 */     return false;
/*     */   }
/*     */ 
/*     */   public static List<RoleBean> getAllUserRoleList(String user_id)
/*     */   {
/* 126 */     Set role_s = new HashSet();
/* 127 */     Iterator iter = role_user_map.entrySet().iterator();
/* 128 */     while (iter.hasNext()) {
/* 129 */       Entry entry = (Entry)iter.next();
/* 130 */       RoleUserBean rub = (RoleUserBean)entry.getValue();
/*     */ 
/* 132 */       if (user_id.equals(rub.getUser_id()))
/*     */       {
/* 134 */         RoleBean rb = RoleManager.getRoleBeanByRoleID(rub.getRole_id());
/* 135 */         if (rb != null) {
/* 136 */           role_s.add(rb);
/*     */         }
/*     */       }
/*     */     }
/* 140 */     String user_group_ids = GroupManager.getGroupIDSByUserID(user_id);
/* 141 */     role_s.addAll(RoleUGroupManager.getRoleSetByUGroupID(user_group_ids));
/*     */ 
/* 143 */     if ((role_s != null) && (role_s.size() > 0))
/*     */     {
/* 145 */       List role_list = new ArrayList();
/* 146 */       role_list.addAll(role_s);
/* 147 */       return role_list;
/*     */     }
/* 149 */     return null;
/*     */   }
/*     */ 
/*     */   public static String getAllUserRoleIDS(String user_id)
/*     */   {
/* 159 */     String s = "";
/* 160 */     List l = getAllUserRoleList(user_id);
/* 161 */     if ((l != null) && (l.size() > 0))
/*     */     {
/* 163 */       for (RoleBean ab : l)
/*     */       {
/* 165 */         s = s + "," + ab.getRole_id();
/*     */       }
/* 167 */       s = s.substring(1);
/*     */     }
/* 169 */     return s;
/*     */   }
/*     */ 
/*     */   public static List<RoleBean> getRoleListByUserAppSite(String user_id, String app_id, String site_id)
/*     */   {
/* 180 */     List role_list = new ArrayList();
/*     */ 
/* 182 */     if ((("cms".equals(app_id)) || ("zwgk".equals(app_id))) && (UserLogin.isSiteManager(user_id, app_id, site_id)))
/*     */     {
/* 185 */       role_list.add(RoleManager.getRoleBeanByRoleID(JconfigUtilContainer.systemRole().getProperty(app_id, "", "role_id")));
/*     */     }
/*     */     else
/*     */     {
/* 189 */       Set role_s = new HashSet();
/* 190 */       role_s.addAll(getRoleListByUserAPP(user_id, app_id, site_id));
/*     */ 
/* 193 */       String user_group_ids = GroupManager.getGroupIDSByUserID(user_id);
/* 194 */       role_s.addAll(RoleUGroupManager.getRoleIDSByUGroupAPP(user_group_ids, app_id, site_id));
/* 195 */       if ((role_s != null) && (role_s.size() > 0))
/*     */       {
/* 197 */         role_list.addAll(role_s);
/*     */       }
/* 199 */       else return null;
/*     */     }
/* 201 */     return role_list;
/*     */   }
/*     */ 
/*     */   public static List<AppBean> getAllUserAppList(String user_id)
/*     */   {
/* 213 */     Set app_s = new HashSet();
/*     */ 
/* 215 */     Iterator iter = role_user_map.entrySet().iterator();
/* 216 */     while (iter.hasNext()) {
/* 217 */       Entry entry = (Entry)iter.next();
/* 218 */       RoleUserBean rub = (RoleUserBean)entry.getValue();
/*     */ 
/* 220 */       if (user_id.equals(rub.getUser_id()))
/*     */       {
/* 222 */         app_s.add(rub.getApp_id());
/*     */       }
/*     */     }
/* 225 */     String user_group_ids = GroupManager.getGroupIDSByUserID(user_id);
/* 226 */     app_s.addAll(RoleUGroupManager.getGroupAppIDS(user_group_ids));
/*     */ 
/* 228 */     if ((app_s != null) && (app_s.size() > 0))
/*     */     {
/* 230 */       return AppManager.getAppListByIDS(app_s.toString().substring(1, app_s.toString().length() - 1).replaceAll(" ", ""));
/*     */     }
/* 232 */     return null;
/*     */   }
/*     */ 
/*     */   public static List<String> getAllUserSiteList(String user_id, String app_id)
/*     */   {
/* 246 */     Set site_s = new HashSet();
/*     */ 
/* 248 */     Iterator iter = role_user_map.entrySet().iterator();
/* 249 */     while (iter.hasNext()) {
/* 250 */       Entry entry = (Entry)iter.next();
/* 251 */       RoleUserBean rub = (RoleUserBean)entry.getValue();
/*     */ 
/* 253 */       if ((user_id.equals(rub.getUser_id())) && (app_id.equals(rub.getApp_id())) && (!"".equals(rub.getSite_id())) && (!"null".equals(rub.getSite_id().toLowerCase())))
/*     */       {
/* 255 */         site_s.add(rub.getSite_id());
/*     */       }
/*     */     }
/* 258 */     String user_group_ids = GroupManager.getGroupIDSByUserID(user_id);
/* 259 */     site_s.addAll(RoleUGroupManager.getGroupSiteIDS(user_group_ids));
/*     */ 
/* 261 */     if ((site_s != null) && (site_s.size() > 0))
/*     */     {
/* 263 */       List site_list = new ArrayList();
/* 264 */       site_list.addAll(site_s);
/* 265 */       return site_list;
/*     */     }
/* 267 */     return null;
/*     */   }
/*     */ 
/*     */   public static Map<Integer, RoleBean> getRoleMapByUserID(String user_id)
/*     */   {
/* 278 */     TreeMap role_map = new TreeMap();
/* 279 */     Iterator iter = role_user_map.entrySet().iterator();
/* 280 */     while (iter.hasNext()) {
/* 281 */       Entry entry = (Entry)iter.next();
/* 282 */       RoleUserBean rub = (RoleUserBean)entry.getValue();
/*     */ 
/* 284 */       if (user_id.equals(rub.getUser_id()))
/*     */       {
/* 286 */         RoleBean rb = RoleManager.getRoleBeanByRoleID(rub.getRole_id());
/* 287 */         if (rb != null)
/* 288 */           role_map.put(Integer.valueOf(Integer.parseInt(rub.getRole_id())), rb);
/*     */       }
/*     */     }
/* 291 */     return role_map;
/*     */   }
/*     */ 
/*     */   public static List<RoleBean> getRoleListByUserID(String user_id)
/*     */   {
/* 302 */     List role_list = new ArrayList();
/*     */ 
/* 304 */     Iterator iter = getRoleMapByUserID(user_id).entrySet().iterator();
/* 305 */     while (iter.hasNext()) {
/* 306 */       Entry entry = (Entry)iter.next();
/* 307 */       role_list.add((RoleBean)entry.getValue());
/*     */     }
/*     */ 
/* 310 */     return role_list;
/*     */   }
/*     */ 
/*     */   public static List<RoleBean> getRoleIDSByUserID(String user_id)
/*     */   {
/* 321 */     List role_list = new ArrayList();
/* 322 */     Iterator iter = role_user_map.entrySet().iterator();
/* 323 */     while (iter.hasNext()) {
/* 324 */       Entry entry = (Entry)iter.next();
/* 325 */       RoleUserBean rub = (RoleUserBean)entry.getValue();
/*     */ 
/* 327 */       if (user_id.equals(rub.getUser_id()))
/*     */       {
/* 329 */         role_list.add(RoleManager.getRoleBeanByRoleID(rub.getRole_id()));
/*     */       }
/*     */     }
/*     */ 
/* 333 */     return role_list;
/*     */   }
/*     */ 
/*     */   public static List<RoleBean> getRoleListByUserAPP(String user_id, String app_id, String site_id)
/*     */   {
/* 344 */     List role_list = new ArrayList();
/* 345 */     Iterator iter = role_user_map.entrySet().iterator();
/* 346 */     while (iter.hasNext()) {
/* 347 */       Entry entry = (Entry)iter.next();
/* 348 */       RoleUserBean rub = (RoleUserBean)entry.getValue();
/*     */ 
/* 350 */       if ((user_id.equals(rub.getUser_id())) && ((app_id.equals(rub.getApp_id())) || ("system".equals(rub.getApp_id()))) && (("".equals(site_id)) || (site_id.equals(rub.getSite_id()))))
/*     */       {
/* 352 */         RoleBean r = RoleManager.getRoleBeanByRoleID(rub.getRole_id());
/* 353 */         if (r != null)
/* 354 */           role_list.add(r);
/*     */       }
/*     */     }
/* 357 */     return role_list;
/*     */   }
/*     */ 
/*     */   public static String getRoleIDSByUserAPP(String user_id, String app_id, String site_id)
/*     */   {
/* 368 */     String role_ids = "";
/* 369 */     Iterator iter = role_user_map.entrySet().iterator();
/* 370 */     while (iter.hasNext()) {
/* 371 */       Entry entry = (Entry)iter.next();
/* 372 */       RoleUserBean rub = (RoleUserBean)entry.getValue();
/*     */ 
/* 374 */       if ((user_id.equals(rub.getUser_id())) && (app_id.equals(rub.getApp_id())) && (("".equals(site_id)) || (site_id.equals(rub.getSite_id()))))
/*     */       {
/* 376 */         role_ids = role_ids + "," + rub.getRole_id();
/*     */       }
/*     */     }
/* 379 */     if ((role_ids != null) && (!"".equals(role_ids)))
/* 380 */       role_ids = role_ids.substring(1);
/* 381 */     return role_ids;
/*     */   }
/*     */ 
/*     */   public static List<UserBean> getUserListByRole(String role_id, String app_id, String site_id)
/*     */   {
/* 394 */     List user_list = new ArrayList();
/*     */ 
/* 396 */     Iterator iter = role_user_map.entrySet().iterator();
/* 397 */     while (iter.hasNext()) {
/* 398 */       Entry entry = (Entry)iter.next();
/* 399 */       RoleUserBean rub = (RoleUserBean)entry.getValue();
/*     */ 
/* 401 */       if ((role_id.equals(rub.getRole_id())) && (app_id.equals(rub.getApp_id())) && (("".equals(site_id)) || (site_id.equals(rub.getSite_id()))))
/*     */       {
/* 403 */         user_list.add(UserManager.getUserBeanByID(rub.getUser_id()));
/*     */       }
/*     */     }
/* 406 */     return user_list;
/*     */   }
/*     */ 
/*     */   public static String getUsersByRole(String role_id, String app_id, String site_id)
/*     */   {
/* 419 */     String user_ids = "";
/*     */ 
/* 421 */     Iterator iter = role_user_map.entrySet().iterator();
/* 422 */     while (iter.hasNext()) {
/* 423 */       Entry entry = (Entry)iter.next();
/* 424 */       RoleUserBean rub = (RoleUserBean)entry.getValue();
/*     */ 
/* 426 */       if ((role_id.equals(rub.getRole_id())) && (app_id.equals(rub.getApp_id())) && (("".equals(site_id)) || (site_id.equals(rub.getSite_id()))))
/*     */       {
/* 428 */         user_ids = user_ids + "," + rub.getUser_id();
/*     */       }
/*     */     }
/* 431 */     if ((user_ids != null) && (!"".equals(user_ids)))
/* 432 */       user_ids = user_ids.substring(1);
/* 433 */     return user_ids;
/*     */   }
/*     */ 
/*     */   public static boolean insertRoleUserByRole(RoleUserBean rub, String delete_user_ids, SettingLogsBean stl)
/*     */   {
/* 445 */     if (insertRoloUserHandl(rub, stl))
/*     */     {
/* 448 */       RoleUserDAO.deleteRoleUserByUserAppSite(rub.getRole_id(), delete_user_ids, rub.getApp_id(), rub.getSite_id());
/* 449 */       reloadRoleUser();
/* 450 */       return true;
/*     */     }
/* 452 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean insertRoloUserHandl(RoleUserBean rub, SettingLogsBean stl)
/*     */   {
/*     */     try
/*     */     {
/* 463 */       if (rub.getUser_id() != null)
/*     */       {
/* 465 */         if ((rub.getUser_id() != null) && (!"".equals(rub.getUser_id())))
/*     */         {
/* 467 */           String[] tempA = rub.getUser_id().split(",");
/* 468 */           for (int i = 0; i < tempA.length; i++)
/*     */           {
/* 470 */             if ((tempA[i] != null) && (!"".equals(tempA[i])))
/*     */             {
/* 472 */               rub.setUser_id(tempA[i]);
/* 473 */               RoleUserDAO.insertRoleUser(rub, stl);
/*     */             }
/*     */           }
/*     */         }
/* 477 */         reloadRoleUser();
/*     */       }
/* 479 */       return true;
/*     */     } catch (Exception e) {
/* 481 */       e.printStackTrace();
/* 482 */     }return false;
/*     */   }
/*     */ 
/*     */   public static boolean insertRoleUserByUser(RoleUserBean rab, SettingLogsBean stl)
/*     */   {
/* 495 */     if (RoleUserDAO.deleteRoleUserByUserAppSite("", rab.getUser_id(), rab.getApp_id(), rab.getSite_id())) {
/*     */       try
/*     */       {
/* 498 */         if (rab.getRole_id() != null)
/*     */         {
/* 500 */           String[] tempA = rab.getRole_id().split(",");
/* 501 */           for (int i = 0; i < tempA.length; i++)
/*     */           {
/* 503 */             rab.setRole_id(tempA[i]);
/* 504 */             RoleUserDAO.insertRoleUser(rab, stl);
/*     */           }
/*     */         }
/* 507 */         reloadRoleUser();
/* 508 */         return true;
/*     */       } catch (Exception e) {
/* 510 */         e.printStackTrace();
/* 511 */         return false;
/*     */       }
/*     */     }
/* 514 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteRoleUserByRoleID(String role_id)
/*     */   {
/* 524 */     if (RoleUserDAO.deleteRoleUserByRoleID(role_id))
/*     */     {
/* 526 */       reloadRoleUser();
/* 527 */       return true;
/*     */     }
/* 529 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteRoleUserByUser(String user_id)
/*     */   {
/* 539 */     if (RoleUserDAO.deleteRoleUserByUser(user_id))
/*     */     {
/* 541 */       reloadRoleUser();
/* 542 */       return true;
/*     */     }
/* 544 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteRoleUserByUserAndRoleID(String user_ids, String role_id)
/*     */   {
/* 556 */     if (RoleUserDAO.deleteRoleUserByUserAndRoleID(user_ids, role_id))
/*     */     {
/* 558 */       reloadRoleUser();
/* 559 */       return true;
/*     */     }
/* 561 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteRoleUserByUserRoleSite(String user_ids, String app_id, String site_id)
/*     */   {
/* 573 */     if (RoleUserDAO.deleteRoleUserByUserAppSite("", user_ids, app_id, site_id))
/*     */     {
/* 575 */       reloadRoleUser();
/* 576 */       return true;
/*     */     }
/* 578 */     return false;
/*     */   }
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/* 595 */     System.out.println(getAllUserSiteList("72", "zwgk"));
/*     */   }
/*     */ 
/*     */   public static void insertRoleUserByRoleTest()
/*     */   {
/* 601 */     RoleUserBean rab = new RoleUserBean();
/* 602 */     rab.setApp_id("system");
/* 603 */     rab.setRole_id("1,2,3,4,5,6");
/* 604 */     rab.setUser_id("1");
/* 605 */     rab.setSite_id("zg");
/*     */ 
/* 607 */     insertRoleUserByUser(rab, new SettingLogsBean());
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.org.role.RoleUserManager
 * JD-Core Version:    0.6.2
 */