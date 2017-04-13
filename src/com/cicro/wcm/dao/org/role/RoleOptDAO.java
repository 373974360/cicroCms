/*     */ package com.cicro.wcm.dao.org.role;
/*     */ 
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.bean.org.role.RoleOptBean;
/*     */ import com.cicro.wcm.dao.PublicTableDAO;
/*     */ import com.cicro.wcm.db.DBManager;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class RoleOptDAO
/*     */ {
/*     */   public static List getAllRoleReleOptList()
/*     */   {
/*  32 */     return DBManager.queryFList("getAllRoleReleOptList", "");
/*     */   }
/*     */ 
/*     */   public static List getOptListByRoleID()
/*     */   {
/*  43 */     return DBManager.queryFList("getOptListByRoleID", "");
/*     */   }
/*     */ 
/*     */   public static boolean insertOptReleRole(String role_id, String opt_ids, SettingLogsBean stl)
/*     */   {
/*  55 */     if (deleteOptReleRoleByRoleID(role_id))
/*     */     {
/*     */       try
/*     */       {
/*  59 */         String[] tempA = opt_ids.split(",");
/*  60 */         for (int i = 0; i < tempA.length; i++)
/*     */         {
/*  62 */           if ((tempA[i] != null) && (!"".equals(tempA[i])))
/*     */           {
/*  64 */             RoleOptBean rob = new RoleOptBean();
/*  65 */             rob.setRole_opt_id(PublicTableDAO.getIDByTableName(PublicTableDAO.ROLEOPT_TABLE_NAME));
/*  66 */             rob.setRole_id(Integer.parseInt(role_id));
/*  67 */             rob.setOpt_id(Integer.parseInt(tempA[i]));
/*  68 */             DBManager.insert("insert_optReleRole", rob);
/*     */           }
/*     */         }
/*  71 */         if (stl != null)
/*  72 */           PublicTableDAO.insertSettingLogs("修改", "角色与权限关联", role_id, stl);
/*  73 */         return true;
/*     */       }
/*     */       catch (Exception e) {
/*  76 */         e.printStackTrace();
/*  77 */         return false;
/*     */       }
/*     */     }
/*  80 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteOptReleRoleByReleID(String role_opt_id)
/*     */   {
/*  91 */     Map m = new HashMap();
/*  92 */     m.put("role_opt_id", role_opt_id);
/*  93 */     return DBManager.delete("delete_optReleRole_byReleID", m);
/*     */   }
/*     */ 
/*     */   public static boolean deleteOptReleRoleByRoleID(String role_id)
/*     */   {
/* 103 */     Map m = new HashMap();
/* 104 */     m.put("role_id", role_id);
/* 105 */     return DBManager.delete("delete_optReleRole_byRoleID", m);
/*     */   }
/*     */ 
/*     */   public static boolean deleteOptReleRoleByOptID(String opt_id)
/*     */   {
/* 115 */     Map m = new HashMap();
/* 116 */     m.put("opt_id", opt_id);
/* 117 */     return DBManager.delete("delete_optReleRole_byOptID", m);
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.dao.org.role.RoleOptDAO
 * JD-Core Version:    0.6.2
 */