/*    */ package com.cicro.wcm.dao.org.role;
/*    */ 
/*    */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*    */ import com.cicro.wcm.bean.org.role.RoleUGroupBean;
/*    */ import com.cicro.wcm.dao.PublicTableDAO;
/*    */ import com.cicro.wcm.db.DBManager;
/*    */ import java.util.HashMap;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class RoleUGroupDAO
/*    */ {
/*    */   public static List getAllRoleUGroupList()
/*    */   {
/* 32 */     return DBManager.queryFList("getAllRoleUGroupList", "");
/*    */   }
/*    */ 
/*    */   public static boolean insertRoleUGroup(RoleUGroupBean rugb, SettingLogsBean stl)
/*    */   {
/* 43 */     int id = PublicTableDAO.getIDByTableName(PublicTableDAO.ROLEGROUP_TABLE_NAME);
/* 44 */     rugb.setGroup_role_id(id);
/* 45 */     if (DBManager.insert("insert_role_uGroup", rugb))
/*    */     {
/* 47 */       PublicTableDAO.insertSettingLogs("添加", "角色与用户组关联", id, stl);
/* 48 */       return true;
/*    */     }
/* 50 */     return false;
/*    */   }
/*    */ 
/*    */   public static boolean deleteRoleUGroupByRoleID(String role_id)
/*    */   {
/* 60 */     Map m = new HashMap();
/* 61 */     m.put("role_id", role_id);
/* 62 */     return DBManager.delete("delete_role_uGroupByRoleID", m);
/*    */   }
/*    */ 
/*    */   public static boolean deleteRoleUGroupByRole(String group_ids, String role_id, String app_id, String site_id)
/*    */   {
/* 75 */     if ((group_ids == null) || ("".equals(group_ids.trim())))
/*    */     {
/* 77 */       return true;
/*    */     }
/* 79 */     Map m = new HashMap();
/* 80 */     m.put("group_ids", group_ids);
/* 81 */     m.put("role_id", role_id);
/* 82 */     m.put("app_id", app_id);
/* 83 */     if ((site_id != null) && (!"".equals(site_id)))
/* 84 */       m.put("site_id", site_id);
/* 85 */     return DBManager.delete("delete_role_uGroupByRole", m);
/*    */   }
/*    */ 
/*    */   public static boolean deleteRoleUGroupByGroup(String group_id)
/*    */   {
/* 95 */     Map m = new HashMap();
/* 96 */     m.put("group_id", group_id);
/* 97 */     return DBManager.delete("delete_role_uGroupByGroup", m);
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.dao.org.role.RoleUGroupDAO
 * JD-Core Version:    0.6.2
 */