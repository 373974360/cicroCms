/*     */ package com.cicro.wcm.dao.appeal.cpUser;
/*     */ 
/*     */ import com.cicro.wcm.bean.appeal.cpUser.CpUserBean;
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.dao.PublicTableDAO;
/*     */ import com.cicro.wcm.db.DBManager;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class CpUserDAO
/*     */ {
/*     */   public static List<CpUserBean> getAllCpUserList()
/*     */   {
/*  27 */     return DBManager.queryFList("getALLCpUserList", "");
/*     */   }
/*     */ 
/*     */   public static boolean insertCpUser(int dept_id, String user_ids, SettingLogsBean stl)
/*     */   {
/*  36 */     if ((user_ids != null) && (!"".equals(user_ids))) {
/*     */       try
/*     */       {
/*  39 */         String[] tempA = user_ids.split(",");
/*  40 */         CpUserBean cub = new CpUserBean();
/*  41 */         for (int i = 0; i < tempA.length; i++)
/*     */         {
/*  43 */           cub.setDept_id(dept_id);
/*  44 */           cub.setUser_id(Integer.parseInt(tempA[i]));
/*  45 */           DBManager.insert("insert_cp_user", cub);
/*     */         }
/*  47 */         PublicTableDAO.insertSettingLogs("添加", "诉求系统部门与用户关联", dept_id, stl);
/*     */       }
/*     */       catch (Exception e) {
/*  50 */         e.printStackTrace();
/*  51 */         return false;
/*     */       }
/*     */     }
/*  54 */     return true;
/*     */   }
/*     */ 
/*     */   public static boolean deleteCpUser(String dept_id, String userIds, SettingLogsBean stl)
/*     */   {
/*  63 */     Map m = new HashMap();
/*  64 */     if ((userIds != null) && (!"".equals(userIds)))
/*  65 */       m.put("userIds", userIds);
/*  66 */     m.put("dept_id", dept_id);
/*  67 */     if (DBManager.delete("delete_cp_user", m))
/*     */     {
/*  69 */       PublicTableDAO.insertSettingLogs("删除", "诉求系统部门与用户关联", dept_id, stl);
/*  70 */       return true;
/*     */     }
/*     */ 
/*  73 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateCpUser(CpUserBean user)
/*     */   {
/*  82 */     return DBManager.update("update_cp_dept", user);
/*     */   }
/*     */ 
/*     */   public static CpUserBean getCpUserBean(int user_id)
/*     */   {
/*  91 */     return (CpUserBean)DBManager.queryFObj("getCpUserBean", user_id);
/*     */   }
/*     */ 
/*     */   public static String getCpUserCount(Map<String, String> m)
/*     */   {
/* 101 */     return DBManager.getString("getCpUserCount", m);
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.dao.appeal.cpUser.CpUserDAO
 * JD-Core Version:    0.6.2
 */