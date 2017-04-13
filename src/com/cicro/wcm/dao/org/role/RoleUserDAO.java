/*     */ package com.cicro.wcm.dao.org.role;
/*     */ 
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.bean.org.role.RoleUserBean;
/*     */ import com.cicro.wcm.dao.PublicTableDAO;
/*     */ import com.cicro.wcm.db.DBManager;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class RoleUserDAO
/*     */ {
/*     */   public static List getAllRoleUserList()
/*     */   {
/*  32 */     return DBManager.queryFList("getAllRoleUserList", "");
/*     */   }
/*     */ 
/*     */   public static boolean insertRoleUser(RoleUserBean rab, SettingLogsBean stl)
/*     */   {
/*  43 */     int id = PublicTableDAO.getIDByTableName(PublicTableDAO.ROLEUSER_TABLE_NAME);
/*  44 */     rab.setUser_role_id(id);
/*  45 */     if (DBManager.insert("insert_role_user", rab))
/*     */     {
/*  47 */       PublicTableDAO.insertSettingLogs("添加", "角色与用户关联", id, stl);
/*  48 */       return true;
/*     */     }
/*  50 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteRoleUserByRoleID(String role_id)
/*     */   {
/*  60 */     Map m = new HashMap();
/*  61 */     m.put("role_id", role_id);
/*  62 */     return DBManager.delete("delete_role_userByRoleID", m);
/*     */   }
/*     */ 
/*     */   public static boolean deleteRoleUserByRole(String role_id, String app_id, String site_id)
/*     */   {
/*  74 */     Map m = new HashMap();
/*  75 */     m.put("role_id", role_id);
/*  76 */     m.put("app_id", app_id);
/*  77 */     if ((site_id != null) && (!"".equals(site_id)))
/*  78 */       m.put("site_id", site_id);
/*  79 */     return DBManager.delete("delete_role_userByRoleAndAppID", m);
/*     */   }
/*     */ 
/*     */   public static boolean deleteRoleUserByUserAndRoleID(String user_ids, String role_id)
/*     */   {
/*  90 */     Map m = new HashMap();
/*  91 */     m.put("user_ids", user_ids);
/*  92 */     m.put("role_id", role_id);
/*  93 */     return DBManager.delete("delete_role_userByUserAndRoleID", m);
/*     */   }
/*     */ 
/*     */   public static boolean deleteRoleUserByUserAppSite(String role_id, String user_ids, String app_id, String site_id)
/*     */   {
/* 106 */     if ((user_ids == null) || ("".equals(user_ids.trim())))
/* 107 */       return true;
/* 108 */     Map m = new HashMap();
/* 109 */     m.put("user_ids", user_ids);
/* 110 */     m.put("app_id", app_id);
/* 111 */     if ((site_id != null) && (!"".equals(site_id.trim())))
/* 112 */       m.put("site_id", site_id);
/* 113 */     if ((role_id != null) && (!"".equals(role_id.trim())))
/* 114 */       m.put("role_id", role_id);
/* 115 */     return DBManager.delete("delete_role_userByUserAndAppSite", m);
/*     */   }
/*     */ 
/*     */   public static boolean deleteRoleUserByUser(String user_id)
/*     */   {
/* 125 */     Map m = new HashMap();
/* 126 */     m.put("user_id", user_id);
/* 127 */     return DBManager.delete("delete_role_userByUser", m);
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.dao.org.role.RoleUserDAO
 * JD-Core Version:    0.6.2
 */