/*     */ package com.cicro.wcm.services.org.siteuser;
/*     */ 
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.bean.org.dept.DeptBean;
/*     */ import com.cicro.wcm.bean.org.role.RoleBean;
/*     */ import com.cicro.wcm.bean.org.siteuser.SiteUserBean;
/*     */ import com.cicro.wcm.bean.org.user.UserBean;
/*     */ import com.cicro.wcm.catchs.ISyncCatch;
/*     */ import com.cicro.wcm.catchs.SyncCatchHandl;
/*     */ import com.cicro.wcm.dao.org.siteuser.SiteUserDAO;
/*     */ import com.cicro.wcm.services.cms.category.CategoryReleManager;
/*     */ import com.cicro.wcm.services.org.dept.DeptManager;
/*     */ import com.cicro.wcm.services.org.role.RoleUserManager;
/*     */ import com.cicro.wcm.services.org.user.UserManager;
/*     */ import java.io.PrintStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class SiteUserManager
/*     */   implements ISyncCatch
/*     */ {
/*  22 */   private static List<SiteUserBean> siteUser_lt = new ArrayList();
/*     */ 
/*     */   static {
/*  25 */     reloadCatchHandl();
/*     */   }
/*     */ 
/*     */   public void reloadCatch()
/*     */   {
/*  30 */     reloadCatchHandl();
/*     */   }
/*     */ 
/*     */   public static void reloadCatchHandl()
/*     */   {
/*  35 */     siteUser_lt.clear();
/*  36 */     siteUser_lt.addAll(SiteUserDAO.getAllSiteUserList());
/*     */   }
/*     */ 
/*     */   public static void reloadSiteuser()
/*     */   {
/*  41 */     SyncCatchHandl.reladCatchForRMI("com.cicro.wcm.services.org.siteuser.SiteUserManager");
/*     */   }
/*     */ 
/*     */   public static List<UserBean> getSiteUserListBySiteAppID(String app_id, String site_id)
/*     */   {
/*  52 */     List u_list = new ArrayList();
/*  53 */     List lt = getSiteUserList(app_id, site_id);
/*     */ 
/*  55 */     if ((lt != null) && (lt.size() > 0))
/*     */     {
/*  57 */       for (int i = 0; i < lt.size(); i++)
/*     */       {
/*  59 */         if ((app_id.equals(((SiteUserBean)lt.get(i)).getApp_id())) && ((site_id.equals(((SiteUserBean)lt.get(i)).getSite_id())) || ("".equals(site_id))))
/*     */         {
/*  61 */           UserBean u = UserManager.getUserBeanByID(((SiteUserBean)lt.get(i)).getUser_id());
/*  62 */           if (u != null)
/*  63 */             u_list.add(u);
/*     */         }
/*     */       }
/*     */     }
/*  67 */     return u_list;
/*     */   }
/*     */ 
/*     */   public static List<UserBean> getUserBeanBySite(String app_id, String site_id)
/*     */   {
/*  72 */     List lt = getSiteUserList(app_id, site_id);
/*  73 */     List userB = new ArrayList();
/*     */ 
/*  75 */     if ((lt != null) && (lt.size() > 0))
/*     */     {
/*  77 */       for (int i = 0; i < lt.size(); i++)
/*     */       {
/*  79 */         userB.add(UserManager.getUserBeanByID(((SiteUserBean)lt.get(i)).getUser_id()));
/*     */       }
/*     */     }
/*  82 */     return userB;
/*     */   }
/*     */ 
/*     */   public static List<SiteUserBean> getSiteUserList(String app_id, String site_id)
/*     */   {
/*  93 */     List lt = new ArrayList();
/*  94 */     if ((site_id == null) || ("".equals(site_id)))
/*     */     {
/*  96 */       lt.addAll(siteUser_lt);
/*  97 */       return lt;
/*     */     }
/*     */ 
/* 100 */     if ((app_id == null) || ("".equals(app_id)))
/*     */     {
/* 102 */       for (SiteUserBean ub : siteUser_lt)
/*     */       {
/* 104 */         if (site_id.equals(ub.getSite_id()))
/*     */         {
/* 106 */           lt.add(ub);
/*     */         }
/*     */       }
/*     */     }
/*     */     else
/*     */     {
/* 112 */       for (SiteUserBean ub : siteUser_lt)
/*     */       {
/* 114 */         if ((app_id.equals(ub.getApp_id())) && (site_id.equals(ub.getSite_id())))
/*     */         {
/* 116 */           UserBean u = UserManager.getUserBeanByID(ub.getUser_id());
/* 117 */           if (u != null) {
/* 118 */             lt.add(ub);
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/* 123 */     if ((lt != null) && (lt.size() > 0))
/*     */     {
/* 125 */       for (int i = 0; i < lt.size(); i++)
/*     */       {
/* 127 */         List role_l = RoleUserManager.getRoleListByUserAPP(((SiteUserBean)lt.get(i)).getUser_id(), ((SiteUserBean)lt.get(i)).getApp_id(), ((SiteUserBean)lt.get(i)).getSite_id());
/* 128 */         if ((role_l != null) && (role_l.size() > 0))
/*     */         {
/* 130 */           String role_names = "";
/* 131 */           for (int j = 0; j < role_l.size(); j++)
/*     */           {
/* 133 */             if (role_l.get(j) != null)
/* 134 */               role_names = role_names + "," + ((RoleBean)role_l.get(j)).getRole_name();
/*     */           }
/* 136 */           if ((role_names != null) && (!"".equals(role_names)))
/*     */           {
/* 138 */             role_names = role_names.substring(1);
/*     */           }
/* 140 */           ((SiteUserBean)lt.get(i)).setRole_names(role_names);
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/* 145 */     return lt;
/*     */   }
/*     */ 
/*     */   public static List<SiteUserBean> getSiteUserList(String site_id)
/*     */   {
/* 155 */     String app_id = null;
/* 156 */     return getSiteUserList(app_id, site_id);
/*     */   }
/*     */ 
/*     */   public static List<SiteUserBean> getSiteUserList()
/*     */   {
/* 165 */     List lt = new ArrayList();
/* 166 */     lt.addAll(siteUser_lt);
/* 167 */     return lt;
/*     */   }
/*     */ 
/*     */   public static Map<String, List<String>> getSiteUserInfo(SiteUserBean ub)
/*     */   {
/* 178 */     Map retMap = new HashMap();
/* 179 */     List lt = new ArrayList();
/*     */     try {
/* 181 */       String user_id = ub.getUser_id();
/* 182 */       String app_id = ub.getApp_id();
/*     */ 
/* 184 */       String user_name = UserManager.getUserBeanByID(user_id).getUser_realname();
/* 185 */       String dept_id = UserManager.getUserBeanByID(user_id).getDept_id();
/* 186 */       String dept_name = DeptManager.getDeptBeanByID(dept_id).getDept_name();
/* 187 */       lt.add(user_name);
/* 188 */       lt.add(dept_name);
/*     */ 
/* 190 */       String key = user_id + "_" + app_id;
/* 191 */       retMap.put(key, lt);
/*     */     }
/*     */     catch (Exception e) {
/* 194 */       e.printStackTrace();
/*     */     }
/* 196 */     return retMap;
/*     */   }
/*     */ 
/*     */   public static Map<String, List<String>> getSiteUserInfo(List<SiteUserBean> lt)
/*     */   {
/* 207 */     Map retMap = new HashMap();
/* 208 */     for (SiteUserBean ub : lt) {
/*     */       try
/*     */       {
/* 211 */         retMap.putAll(getSiteUserInfo(ub));
/*     */       }
/*     */       catch (Exception e) {
/* 214 */         e.printStackTrace();
/*     */       }
/*     */     }
/* 217 */     return retMap;
/*     */   }
/*     */ 
/*     */   public static String getUserIDS(String site_id, String app_id)
/*     */   {
/* 228 */     String ret = "";
/* 229 */     List lt = getSiteUserList(app_id, site_id);
/* 230 */     for (SiteUserBean ub : lt)
/*     */     {
/* 232 */       ret = ret + "," + ub.getUser_id();
/*     */     }
/* 234 */     if (ret.startsWith(","))
/*     */     {
/* 236 */       ret.substring(1);
/*     */     }
/*     */ 
/* 239 */     return ret;
/*     */   }
/*     */ 
/*     */   public static boolean insertSiteUser(SiteUserBean ub, SettingLogsBean stl)
/*     */   {
/* 250 */     if (SiteUserDAO.insertSiteUser(ub, stl))
/*     */     {
/* 252 */       reloadSiteuser();
/* 253 */       return true;
/*     */     }
/* 255 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean linkSiteUser(String insert_user_ids, String delete_user_ids, String site_id, String app_id, SettingLogsBean stl)
/*     */   {
/* 269 */     SiteUserBean ub = new SiteUserBean();
/* 270 */     ub.setSite_id(site_id);
/* 271 */     ub.setApp_id(app_id);
/* 272 */     if (deleteSiteUser(delete_user_ids, site_id, app_id, stl))
/*     */     {
/* 274 */       if ((insert_user_ids == null) || ("".equals(insert_user_ids)))
/* 275 */         return true;
/* 276 */       String[] ids = insert_user_ids.split(",");
/* 277 */       for (int i = 0; i < ids.length; i++)
/*     */       {
/* 279 */         ub.setUser_id(ids[i]);
/* 280 */         if (!insertSiteUser(ub, stl))
/*     */         {
/* 282 */           return false;
/*     */         }
/*     */       }
/* 285 */       return true;
/*     */     }
/* 287 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteSiteUser(String site_id, String app_id, SettingLogsBean stl)
/*     */   {
/* 299 */     String user_id = null;
/* 300 */     return deleteSiteUser(user_id, site_id, app_id, stl);
/*     */   }
/*     */ 
/*     */   public static boolean deleteSiteUser(String user_ids, String site_id, String app_id, SettingLogsBean stl)
/*     */   {
/* 313 */     if ((user_ids == null) || ("".equals(user_ids)))
/* 314 */       return true;
/* 315 */     Map map = new HashMap();
/* 316 */     String userValue = "".equals(user_ids) ? null : user_ids;
/* 317 */     map.put("user_ids", userValue);
/* 318 */     String siteValue = "".equals(site_id) ? null : site_id;
/* 319 */     map.put("site_id", siteValue);
/* 320 */     String appValue = "".equals(app_id) ? null : app_id;
/* 321 */     map.put("app_id", appValue);
/*     */ 
/* 323 */     if (SiteUserDAO.deleteSiteUser(map, stl))
/*     */     {
/* 325 */       reloadSiteuser();
/* 326 */       RoleUserManager.deleteRoleUserByUserRoleSite(user_ids, app_id, site_id);
/* 327 */       CategoryReleManager.deleteCateReleByPrv(0, user_ids, site_id);
/* 328 */       return true;
/*     */     }
/*     */ 
/* 332 */     return false;
/*     */   }
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/* 338 */     System.out.println(getUserBeanBySite("ggfw", "ggfw"));
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.org.siteuser.SiteUserManager
 * JD-Core Version:    0.6.2
 */