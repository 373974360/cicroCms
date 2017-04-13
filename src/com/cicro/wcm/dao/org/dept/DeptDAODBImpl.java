/*     */ package com.cicro.wcm.dao.org.dept;
/*     */ 
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.bean.org.dept.DeptBean;
/*     */ import com.cicro.wcm.bean.org.dept.DeptLevelBean;
/*     */ import com.cicro.wcm.dao.PublicTableDAO;
/*     */ import com.cicro.wcm.dao.org.user.IUserDAO;
/*     */ import com.cicro.wcm.dao.org.user.UserDAODBImpl;
/*     */ import com.cicro.wcm.db.DBManager;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class DeptDAODBImpl
/*     */   implements IDeptDAO
/*     */ {
/*  26 */   private static IUserDAO userDAO = new UserDAODBImpl();
/*     */ 
/*     */   public List getAllDeptList()
/*     */   {
/*  35 */     return DBManager.queryFList("getAllDeptList", "");
/*     */   }
/*     */ 
/*     */   public List<DeptBean> getChildDeptListForDB(Map<String, String> con_m)
/*     */   {
/*  48 */     return DBManager.queryFList("getChildDeptListForBD", con_m);
/*     */   }
/*     */ 
/*     */   public DeptBean getDeptBeanByID(String id)
/*     */   {
/*  58 */     return (DeptBean)DBManager.queryFObj("getDeptBeanByID", id);
/*     */   }
/*     */ 
/*     */   public int getDeptID()
/*     */   {
/*  67 */     return PublicTableDAO.getIDByTableName(PublicTableDAO.DEPT_TABLE_NAME);
/*     */   }
/*     */ 
/*     */   public boolean insertDept(DeptBean db, SettingLogsBean stl)
/*     */   {
/*  77 */     db.setTree_position(db.getTree_position() + db.getDept_id() + "$");
/*  78 */     if (DBManager.insert("insert_dept", db))
/*     */     {
/*  82 */       PublicTableDAO.insertSettingLogs("添加", "部门", db.getDept_id(), stl);
/*  83 */       return true;
/*     */     }
/*  85 */     return false;
/*     */   }
/*     */ 
/*     */   public boolean updateDept(DeptBean db, SettingLogsBean stl)
/*     */   {
/*  95 */     if (DBManager.update("update_dept", db))
/*     */     {
/*  98 */       PublicTableDAO.insertSettingLogs("修改", "部门", db.getDept_id(), stl);
/*  99 */       return true;
/*     */     }
/*     */ 
/* 102 */     return false;
/*     */   }
/*     */ 
/*     */   public boolean moveDept(Map<String, String> m, SettingLogsBean stl)
/*     */   {
/* 113 */     if (DBManager.update("move_table", m))
/*     */     {
/* 115 */       PublicTableDAO.insertSettingLogs("移动", "部门", (String)m.get("dept_ids"), stl);
/* 116 */       return true;
/*     */     }
/*     */ 
/* 119 */     return false;
/*     */   }
/*     */ 
/*     */   public boolean deleteDept(String ids, SettingLogsBean stl)
/*     */   {
/* 130 */     Map m = new HashMap();
/* 131 */     m.put("ids", ids);
/* 132 */     if (DBManager.update("delete_dept", m))
/*     */     {
/* 135 */       deleteDeptManager("", ids, stl);
/*     */ 
/* 137 */       userDAO.deleteUserByDeptID(ids, stl);
/* 138 */       PublicTableDAO.insertSettingLogs("删除", "部门", ids, stl);
/* 139 */       return true;
/*     */     }
/* 141 */     return false;
/*     */   }
/*     */ 
/*     */   public boolean saveDeptSort(String ids, SettingLogsBean stl)
/*     */   {
/* 154 */     String[] tempA = ids.split(",");
/* 155 */     Map m = new HashMap();
/*     */     try
/*     */     {
/* 158 */       for (int i = 0; i < tempA.length; i++)
/*     */       {
/* 160 */         m.put("dept_id", tempA[i]);
/* 161 */         m.put("dept_sort", Integer.valueOf(i + 1));
/* 162 */         DBManager.update("savesort_dept", m);
/*     */       }
/* 164 */       PublicTableDAO.insertSettingLogs("保存排序", "部门", ids, stl);
/* 165 */       return true;
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 169 */       e.printStackTrace();
/* 170 */     }return false;
/*     */   }
/*     */ 
/*     */   public List getAllDeptManagerList()
/*     */   {
/* 184 */     return DBManager.queryFList("getAllDeptManagerList", "");
/*     */   }
/*     */ 
/*     */   public boolean insertDetpManager(String user_ids, String dept_id, SettingLogsBean stl)
/*     */   {
/* 199 */     if ((user_ids == null) || ("".equals(user_ids)))
/* 200 */       return true;
/* 201 */     Map m = new HashMap();
/*     */     try
/*     */     {
/* 204 */       String[] tempA = user_ids.split(",");
/* 205 */       for (int i = 0; i < tempA.length; i++)
/*     */       {
/* 207 */         if ((tempA[i] != null) && (!"".equals(tempA[i])))
/*     */         {
/* 209 */           m.put("dept_manager_id", Integer.valueOf(PublicTableDAO.getIDByTableName(PublicTableDAO.DEPTMANAGER_TABLE_NAME)));
/* 210 */           m.put("dept_id", dept_id);
/* 211 */           m.put("user_id", tempA[i]);
/* 212 */           DBManager.insert("insert_detp_manager", m);
/*     */         }
/*     */       }
/* 215 */       PublicTableDAO.insertSettingLogs("添加", "部门管理员 部门", dept_id + " 人员ID为 " + user_ids, stl);
/* 216 */       return true;
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 220 */       e.printStackTrace();
/* 221 */     }return false;
/*     */   }
/*     */ 
/*     */   public boolean updateDetpManager(String user_ids, String dept_id, SettingLogsBean stl)
/*     */   {
/* 237 */     if (deleteDeptManager("", dept_id, stl))
/*     */     {
/* 239 */       return insertDetpManager(user_ids, dept_id, stl);
/*     */     }
/* 241 */     return false;
/*     */   }
/*     */ 
/*     */   public boolean deleteDeptManager(String user_ids, String dept_id, SettingLogsBean stl)
/*     */   {
/* 252 */     Map m = new HashMap();
/*     */ 
/* 254 */     m.put("detp_ids", dept_id);
/* 255 */     if ((user_ids != null) && (!"".equals(user_ids)))
/* 256 */       m.put("user_ids", user_ids);
/* 257 */     if (DBManager.delete("delete_detp_manager_byDeptID", m))
/*     */     {
/* 259 */       PublicTableDAO.insertSettingLogs("删除", "部门管理员 部门", dept_id + " 人员ID为 " + user_ids, stl);
/* 260 */       return true;
/*     */     }
/* 262 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteDeptManagerByUserID(String user_ids, SettingLogsBean stl)
/*     */   {
/* 274 */     Map m = new HashMap();
/*     */ 
/* 276 */     m.put("ids", user_ids);
/* 277 */     if (DBManager.delete("delete_detp_manager_byUserID", m))
/*     */     {
/* 279 */       PublicTableDAO.insertSettingLogs("删除", "部门管理员 人员", user_ids, stl);
/* 280 */       return true;
/*     */     }
/*     */ 
/* 283 */     return false;
/*     */   }
/*     */ 
/*     */   public List getAllDeptLevelList()
/*     */   {
/* 296 */     return DBManager.queryFList("getAllDeptLevelList", "");
/*     */   }
/*     */ 
/*     */   public boolean insertDeptLevel(DeptLevelBean dlb, SettingLogsBean stl)
/*     */   {
/* 306 */     int id = PublicTableDAO.getIDByTableName(PublicTableDAO.DEPTLEVEL_TABLE_NAME);
/* 307 */     dlb.setDeptlevel_id(id);
/* 308 */     if (DBManager.insert("insert_deptLevel", dlb))
/*     */     {
/* 310 */       PublicTableDAO.insertSettingLogs("添加", "部门级别", id, stl);
/* 311 */       return true;
/*     */     }
/* 313 */     return false;
/*     */   }
/*     */ 
/*     */   public DeptLevelBean getDeptLevelBeanByID(String id)
/*     */   {
/* 325 */     return (DeptLevelBean)DBManager.queryFObj("getDeptLevelBeanByID", id);
/*     */   }
/*     */ 
/*     */   public boolean updateDeptLevel(DeptLevelBean dlb, SettingLogsBean stl)
/*     */   {
/* 335 */     if (DBManager.update("update_deptLevel", dlb))
/*     */     {
/* 337 */       PublicTableDAO.insertSettingLogs("修改", "部门级别", dlb.getDeptlevel_id(), stl);
/* 338 */       return true;
/*     */     }
/* 340 */     return false;
/*     */   }
/*     */ 
/*     */   public boolean deleteDeptLevel(String ids, SettingLogsBean stl)
/*     */   {
/* 350 */     Map m = new HashMap();
/* 351 */     m.put("ids", ids);
/* 352 */     if (DBManager.update("delete_deptLevel", m))
/*     */     {
/* 354 */       PublicTableDAO.insertSettingLogs("删除", "部门级别", ids, stl);
/* 355 */       return true;
/*     */     }
/* 357 */     return false;
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.dao.org.dept.DeptDAODBImpl
 * JD-Core Version:    0.6.2
 */