/*     */ package com.cicro.wcm.dao.org.role;
/*     */ 
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.bean.org.role.RoleAppBean;
/*     */ import com.cicro.wcm.bean.org.role.RoleBean;
/*     */ import com.cicro.wcm.dao.PublicTableDAO;
/*     */ import com.cicro.wcm.db.DBManager;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class RoleDAO
/*     */ {
/*     */   public static List<RoleBean> getRoleListBySiteID(String site_id)
/*     */   {
/*  33 */     return DBManager.queryFList("getRoleListBySiteID", site_id);
/*     */   }
/*     */ 
/*     */   public static List<RoleBean> getAllRoleList()
/*     */   {
/*  44 */     return DBManager.queryFList("getAllRoleList", "");
/*     */   }
/*     */ 
/*     */   public static String getRoleCountForDB(Map<String, String> m)
/*     */   {
/*  56 */     return DBManager.getString("getRoleCountForDB", m);
/*     */   }
/*     */ 
/*     */   public static List getRoleListByDB(Map m)
/*     */   {
/*  71 */     return DBManager.queryFList("getRoleListByDB", m);
/*     */   }
/*     */ 
/*     */   public static RoleBean getRoleBeanByRoleID(String id)
/*     */   {
/*  81 */     return (RoleBean)DBManager.queryFObj("getRoleBeanByRoleID", id);
/*     */   }
/*     */ 
/*     */   public static boolean insertRole(RoleBean rb, SettingLogsBean stl)
/*     */   {
/*  91 */     int id = PublicTableDAO.getIDByTableName(PublicTableDAO.ROLE_TABLE_NAME);
/*  92 */     rb.setRole_id(id);
/*  93 */     if (DBManager.insert("insert_role", rb))
/*     */     {
/*  96 */       insertRoleApp(id, rb.getApp_id() + "," + rb.getA_app_id(), rb.getSite_id());
/*  97 */       if (stl != null)
/*  98 */         PublicTableDAO.insertSettingLogs("添加", "角色", id, stl);
/*  99 */       return true;
/*     */     }
/* 101 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateRole(RoleBean rb, SettingLogsBean stl)
/*     */   {
/* 111 */     if (DBManager.update("update_role", rb))
/*     */     {
/* 113 */       updateRoleApp(rb.getRole_id(), rb.getApp_id() + "," + rb.getA_app_id(), rb.getSite_id());
/* 114 */       PublicTableDAO.insertSettingLogs("修改", "角色", rb.getRole_id(), stl);
/* 115 */       return true;
/*     */     }
/* 117 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteRole(String role_ids, SettingLogsBean stl)
/*     */   {
/* 127 */     Map m = new HashMap();
/* 128 */     m.put("role_id", role_ids);
/* 129 */     if (DBManager.delete("delete_role", m))
/*     */     {
/* 132 */       deleteRoleApp(role_ids);
/*     */ 
/* 134 */       RoleOptDAO.deleteOptReleRoleByRoleID(role_ids);
/* 135 */       PublicTableDAO.insertSettingLogs("删除", "角色", role_ids, stl);
/* 136 */       return true;
/*     */     }
/* 138 */     return false;
/*     */   }
/*     */ 
/*     */   public static List getAllRoleAppList()
/*     */   {
/* 144 */     return DBManager.queryFList("getAllRoleAppList", "");
/*     */   }
/*     */ 
/*     */   public static boolean insertRoleApp(int role_id, String app_ids, String site_id)
/*     */   {
/*     */     try
/*     */     {
/* 157 */       RoleAppBean rab = new RoleAppBean();
/* 158 */       rab.setRole_id(role_id);
/* 159 */       rab.setSite_id(site_id);
/*     */ 
/* 161 */       String[] tempA = app_ids.split(",");
/* 162 */       for (int i = 0; i < tempA.length; i++)
/*     */       {
/* 164 */         if ((tempA[i] != null) && (!"".equals(tempA[i])))
/*     */         {
/* 166 */           rab.setRole_app_id(PublicTableDAO.getIDByTableName(PublicTableDAO.ROLEAPP_TABLE_NAME));
/* 167 */           rab.setApp_id(tempA[i]);
/*     */ 
/* 169 */           DBManager.insert("insert_role_app", rab);
/*     */         }
/*     */       }
/* 172 */       return true;
/*     */     }
/*     */     catch (Exception e) {
/* 175 */       e.printStackTrace();
/* 176 */     }return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateRoleApp(int role_id, String app_ids, String site_id)
/*     */   {
/* 189 */     if (deleteRoleApp(role_id))
/*     */     {
/* 191 */       return insertRoleApp(role_id, app_ids, site_id);
/*     */     }
/* 193 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteRoleApp(String role_id)
/*     */   {
/* 202 */     Map m = new HashMap();
/* 203 */     m.put("role_id", role_id);
/* 204 */     return DBManager.delete("delete_role_app", m);
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.dao.org.role.RoleDAO
 * JD-Core Version:    0.6.2
 */