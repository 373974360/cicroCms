/*     */ package com.cicro.wcm.services.org.role;
/*     */ 
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.bean.org.role.RoleBean;
/*     */ import com.cicro.wcm.bean.org.role.RoleUGroupBean;
/*     */ import com.cicro.wcm.bean.org.role.RoleUserBean;
/*     */ import com.cicro.wcm.services.Log.LogManager;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ 
/*     */ public class RoleRPC
/*     */ {
/*     */   public static String getRoleIDSByUserAPP(String user_id, String app_id, String site_id)
/*     */   {
/*  24 */     return RoleUserManager.getRoleIDSByUserAPP(user_id, app_id, site_id);
/*     */   }
/*     */ 
/*     */   public static Map<String, RoleBean> getRoleMap()
/*     */   {
/*  35 */     return RoleManager.getRoleMap();
/*     */   }
/*     */ 
/*     */   public static List<RoleBean> getRoleListForDB(Map<String, String> m)
/*     */   {
/*  46 */     return RoleManager.getRoleListForDB(m);
/*     */   }
/*     */ 
/*     */   public static String getRoleCount(Map<String, String> m)
/*     */   {
/*  58 */     return RoleManager.getRoleCount(m);
/*     */   }
/*     */ 
/*     */   public static String getRoleCountForDB(Map<String, String> m)
/*     */   {
/*  70 */     return RoleManager.getRoleCountForDB(m);
/*     */   }
/*     */ 
/*     */   public static RoleBean getRoleBeanByID(String role_id)
/*     */   {
/*  82 */     return RoleManager.getRoleBeanByRoleID(role_id);
/*     */   }
/*     */ 
/*     */   public static List<RoleBean> getRoleListByAPPAndSite(String app_id, String site_id)
/*     */   {
/*  94 */     return RoleManager.getRoleListByAPPAndSite(app_id, site_id);
/*     */   }
/*     */ 
/*     */   public static boolean insertRole(RoleBean rb, HttpServletRequest request)
/*     */   {
/* 105 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 106 */     if (stl != null)
/*     */     {
/* 108 */       return RoleManager.insertRole(rb, stl);
/*     */     }
/* 110 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateRole(RoleBean rb, HttpServletRequest request)
/*     */   {
/* 121 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 122 */     if (stl != null)
/*     */     {
/* 124 */       return RoleManager.updateRole(rb, stl);
/*     */     }
/* 126 */     return false;
/*     */   }
/*     */ 
/*     */   public static String deleteRoleBeforeChecked(String role_id, String app_id, String site_id)
/*     */   {
/* 138 */     return RoleManager.deleteRoleBeforeChecked(role_id, app_id, site_id);
/*     */   }
/*     */ 
/*     */   public static boolean deleteRole(String role_ids, HttpServletRequest request)
/*     */   {
/* 149 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 150 */     if (stl != null)
/*     */     {
/* 152 */       return RoleManager.deleteRole(role_ids, stl);
/*     */     }
/* 154 */     return false;
/*     */   }
/*     */ 
/*     */   public static String getUsersByRole(String role_id, String app_id, String site_id)
/*     */   {
/* 166 */     return RoleUserManager.getUsersByRole(role_id, app_id, site_id);
/*     */   }
/*     */ 
/*     */   public static String getGroupsByRole(String role_id, String app_id, String site_id)
/*     */   {
/* 178 */     return RoleUGroupManager.getGroupsByRole(role_id, app_id, site_id);
/*     */   }
/*     */ 
/*     */   public static boolean insertRoleUserByRole(RoleUserBean rub, String delete_user_ids, HttpServletRequest request)
/*     */   {
/* 190 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 191 */     if (stl != null)
/*     */     {
/* 193 */       return RoleUserManager.insertRoleUserByRole(rub, delete_user_ids, stl);
/*     */     }
/* 195 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean insertRoleUserByUser(RoleUserBean rub, HttpServletRequest request)
/*     */   {
/* 206 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 207 */     if (stl != null)
/*     */     {
/* 209 */       return RoleUserManager.insertRoleUserByUser(rub, stl);
/*     */     }
/* 211 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean insertRoleUGroupByRole(RoleUGroupBean rugb, String delete_group_ids, HttpServletRequest request)
/*     */   {
/* 223 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 224 */     if (stl != null)
/*     */     {
/* 226 */       return RoleUGroupManager.insertRoleUGroupByRole(rugb, delete_group_ids, stl);
/*     */     }
/* 228 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean insertRoleUGroupByGroup(RoleUGroupBean rugb, HttpServletRequest request)
/*     */   {
/* 239 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 240 */     if (stl != null)
/*     */     {
/* 242 */       return RoleUGroupManager.insertRoleUserByUGroup(rugb, stl);
/*     */     }
/* 244 */     return false;
/*     */   }
/*     */ 
/*     */   public static String getOptIDSByRoleID(String role_id)
/*     */   {
/* 255 */     return RoleOptManager.getOptIDSByRoleID(role_id);
/*     */   }
/*     */ 
/*     */   public static boolean insertOptReleRole(String role_id, String opt_ids, HttpServletRequest request)
/*     */   {
/* 267 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 268 */     if (stl != null)
/*     */     {
/* 270 */       return RoleOptManager.insertOptReleRole(role_id, opt_ids, stl);
/*     */     }
/* 272 */     return false;
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.org.role.RoleRPC
 * JD-Core Version:    0.6.2
 */