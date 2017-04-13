/*     */ package com.cicro.wcm.dao.org.user;
/*     */ 
/*     */ import com.cicro.util.CryptoTools;
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.bean.org.user.UserBean;
/*     */ import com.cicro.wcm.bean.org.user.UserLevelBean;
/*     */ import com.cicro.wcm.bean.org.user.UserRegisterBean;
/*     */ import com.cicro.wcm.dao.PublicTableDAO;
/*     */ import com.cicro.wcm.dao.org.dept.DeptDAODBImpl;
/*     */ import com.cicro.wcm.db.DBManager;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class UserDAODBImpl
/*     */   implements IUserDAO
/*     */ {
/*     */   public List getAllUserList()
/*     */   {
/*  36 */     return DBManager.queryFList("getAllUserList", "");
/*     */   }
/*     */ 
/*     */   public List<UserBean> getUserListByDeptIDForDB(Map conn_m)
/*     */   {
/*  47 */     return DBManager.queryFList("getAllUserListByDeptID", conn_m);
/*     */   }
/*     */ 
/*     */   public String getUserCountByDeptIDForDB(Map<String, String> con_m)
/*     */   {
/*  58 */     return DBManager.getString("getUserCountByDeptIDForDB", con_m);
/*     */   }
/*     */ 
/*     */   public UserBean getUserBeanByID(String id)
/*     */   {
/*  67 */     return (UserBean)DBManager.queryFObj("getUserBeanByID", id);
/*     */   }
/*     */ 
/*     */   public boolean insertUser(UserBean ub, UserRegisterBean urb, boolean isAddReg, SettingLogsBean stl)
/*     */   {
/*  79 */     int id = PublicTableDAO.getIDByTableName(PublicTableDAO.USER_TABLE_NAME);
/*  80 */     ub.setUser_id(id);
/*  81 */     if (DBManager.insert("insert_user", ub))
/*     */     {
/*  83 */       PublicTableDAO.insertSettingLogs("添加", "用户", id, stl);
/*  84 */       if (isAddReg)
/*     */       {
/*  86 */         urb.setUser_id(id);
/*  87 */         insertRegister(urb, stl);
/*     */       }
/*  89 */       return true;
/*     */     }
/*  91 */     return false;
/*     */   }
/*     */ 
/*     */   public boolean updateUser(UserBean ub, SettingLogsBean stl)
/*     */   {
/* 101 */     if (DBManager.update("update_user", ub))
/*     */     {
/* 103 */       PublicTableDAO.insertSettingLogs("修改", "用户", ub.getUser_id(), stl);
/* 104 */       return true;
/*     */     }
/*     */ 
/* 107 */     return false;
/*     */   }
/*     */ 
/*     */   public boolean saveUserSort(String ids, SettingLogsBean stl)
/*     */   {
/* 120 */     String[] tempA = ids.split(",");
/* 121 */     Map m = new HashMap();
/*     */     try
/*     */     {
/* 124 */       for (int i = 0; i < tempA.length; i++)
/*     */       {
/* 126 */         m.put("user_id", tempA[i]);
/* 127 */         m.put("sort", i + 1);
/* 128 */         DBManager.update("sort_user", m);
/*     */       }
/* 130 */       PublicTableDAO.insertSettingLogs("保存排序", "用户", ids, stl);
/* 131 */       return true;
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 135 */       e.printStackTrace();
/* 136 */     }return false;
/*     */   }
/*     */ 
/*     */   public boolean moveUser(Map<String, String> m, SettingLogsBean stl)
/*     */   {
/* 147 */     if (DBManager.update("move_user", m))
/*     */     {
/* 149 */       PublicTableDAO.insertSettingLogs("移动", "用户", (String)m.get("user_ids"), stl);
/* 150 */       return true;
/*     */     }
/*     */ 
/* 153 */     return false;
/*     */   }
/*     */ 
/*     */   public boolean updateUserStatus(int user_status, String user_ids, SettingLogsBean stl)
/*     */   {
/* 165 */     Map m = new HashMap();
/* 166 */     m.put("user_status", Integer.valueOf(user_status));
/* 167 */     m.put("ids", user_ids);
/* 168 */     if (DBManager.update("update_userStatus", m))
/*     */     {
/* 170 */       PublicTableDAO.insertSettingLogs("修改", "用户状态", user_ids, stl);
/* 171 */       return true;
/*     */     }
/*     */ 
/* 174 */     return false;
/*     */   }
/*     */ 
/*     */   public boolean deleteUser(String user_ids, SettingLogsBean stl)
/*     */   {
/* 184 */     Map m = new HashMap();
/* 185 */     m.put("ids", user_ids);
/* 186 */     if (DBManager.update("delete_user", m))
/*     */     {
/* 189 */       DeptDAODBImpl.deleteDeptManagerByUserID(user_ids, stl);
/* 190 */       PublicTableDAO.insertSettingLogs("删除", "用户", user_ids, stl);
/* 191 */       return true;
/*     */     }
/* 193 */     return false;
/*     */   }
/*     */ 
/*     */   public boolean deleteUserByDeptID(String detp_ids, SettingLogsBean stl)
/*     */   {
/* 203 */     Map m = new HashMap();
/* 204 */     m.put("ids", detp_ids);
/* 205 */     if (DBManager.update("delete_userByDeptID", m))
/*     */     {
/* 207 */       PublicTableDAO.insertSettingLogs("删除", "部门 用户", detp_ids, stl);
/* 208 */       return true;
/*     */     }
/* 210 */     return false;
/*     */   }
/*     */ 
/*     */   public List getAllUserLevelList()
/*     */   {
/* 221 */     return DBManager.queryFList("getAllUserLevelList", "");
/*     */   }
/*     */ 
/*     */   public UserLevelBean getUserLevelBeanByID(String id)
/*     */   {
/* 230 */     return (UserLevelBean)DBManager.queryFObj("getUserLevelBeanByID", id);
/*     */   }
/*     */ 
/*     */   public boolean insertUserLevel(UserLevelBean ulb, SettingLogsBean stl)
/*     */   {
/* 240 */     int id = PublicTableDAO.getIDByTableName(PublicTableDAO.USERLEVEL_TABLE_NAME);
/* 241 */     ulb.setUserlevel_id(id);
/* 242 */     if (DBManager.insert("insert_userLevel", ulb))
/*     */     {
/* 244 */       PublicTableDAO.insertSettingLogs("添加", "用户级别", id, stl);
/* 245 */       return true;
/*     */     }
/* 247 */     return false;
/*     */   }
/*     */ 
/*     */   public boolean updateUserLevel(UserLevelBean ulb, SettingLogsBean stl)
/*     */   {
/* 257 */     if (DBManager.update("update_userLevel", ulb))
/*     */     {
/* 259 */       PublicTableDAO.insertSettingLogs("修改", "用户级别", ulb.getUserlevel_id(), stl);
/* 260 */       return true;
/*     */     }
/*     */ 
/* 263 */     return false;
/*     */   }
/*     */ 
/*     */   public boolean deleteUserLevel(String ids, SettingLogsBean stl)
/*     */   {
/* 274 */     Map m = new HashMap();
/* 275 */     m.put("ids", ids);
/* 276 */     if (DBManager.update("delete_userLevel", m))
/*     */     {
/* 278 */       PublicTableDAO.insertSettingLogs("删除", "用户级别", ids, stl);
/* 279 */       return true;
/*     */     }
/* 281 */     return false;
/*     */   }
/*     */ 
/*     */   public List getAllUserRegister()
/*     */   {
/* 293 */     return DBManager.queryFList("getAllUserRegister", "");
/*     */   }
/*     */ 
/*     */   public String getUserRegisterCount(Map<String, String> con_m)
/*     */   {
/* 302 */     return DBManager.getString("getUserRegisterCount", con_m);
/*     */   }
/*     */ 
/*     */   public List<UserRegisterBean> getAllUserRegisterForDB(Map<String, String> con_m)
/*     */   {
/* 313 */     return DBManager.queryFList("getAllUserRegisterForDB", con_m);
/*     */   }
/*     */ 
/*     */   public UserRegisterBean getUserRegisterBeanByUname(String user_name)
/*     */   {
/* 325 */     return (UserRegisterBean)DBManager.queryFObj("getUserRegisterBeanByUname", user_name);
/*     */   }
/*     */ 
/*     */   public boolean insertRegister(UserRegisterBean urb, SettingLogsBean stl)
/*     */   {
/* 335 */     int id = PublicTableDAO.getIDByTableName(PublicTableDAO.USERREGISTER_TABLE_NAME);
/* 336 */     urb.setRegister_id(id);
/* 337 */     CryptoTools ct = new CryptoTools();
/* 338 */     urb.setPassword(ct.encode(urb.getPassword()));
/* 339 */     if (DBManager.insert("insert_register", urb))
/*     */     {
/* 341 */       PublicTableDAO.insertSettingLogs("添加", "帐号", id, stl);
/* 342 */       return true;
/*     */     }
/* 344 */     return false;
/*     */   }
/*     */ 
/*     */   public boolean updateRegister(UserRegisterBean urb, SettingLogsBean stl)
/*     */   {
/* 354 */     CryptoTools ct = new CryptoTools();
/* 355 */     urb.setPassword(ct.encode(urb.getPassword()));
/* 356 */     if (DBManager.update("update_register", urb))
/*     */     {
/* 358 */       PublicTableDAO.insertSettingLogs("修改", "帐号", urb.getRegister_id(), stl);
/* 359 */       return true;
/*     */     }
/*     */ 
/* 362 */     return false;
/*     */   }
/*     */ 
/*     */   public boolean updateRegisterStatus(int reg_status, String reg_ids, SettingLogsBean stl)
/*     */   {
/* 373 */     Map m = new HashMap();
/* 374 */     m.put("register_status", Integer.valueOf(reg_status));
/* 375 */     m.put("ids", reg_ids);
/* 376 */     if (DBManager.update("update_registerStatus", m))
/*     */     {
/* 378 */       PublicTableDAO.insertSettingLogs("修改", "帐号", reg_ids, stl);
/* 379 */       return true;
/*     */     }
/*     */ 
/* 382 */     return false;
/*     */   }
/*     */ 
/*     */   public boolean deleteRegisterByRID(String reg_ids, SettingLogsBean stl)
/*     */   {
/* 392 */     Map m = new HashMap();
/* 393 */     m.put("ids", reg_ids);
/* 394 */     if (DBManager.update("delete_registerByRID", m))
/*     */     {
/* 396 */       PublicTableDAO.insertSettingLogs("删除", "帐号", reg_ids, stl);
/* 397 */       return true;
/*     */     }
/* 399 */     return false;
/*     */   }
/*     */ 
/*     */   public boolean deleteRegisterByUserID(String user_ids, SettingLogsBean stl)
/*     */   {
/* 409 */     Map m = new HashMap();
/* 410 */     m.put("user_ids", user_ids);
/* 411 */     if (DBManager.update("delete_registerByUserID", m))
/*     */     {
/* 413 */       PublicTableDAO.insertSettingLogs("删除", "帐号 用户", user_ids, stl);
/* 414 */       return true;
/*     */     }
/* 416 */     return false;
/*     */   }
/*     */ 
/*     */   public boolean deleteRegisterByDeptID(String dept_ids, SettingLogsBean stl)
/*     */   {
/* 426 */     Map m = new HashMap();
/* 427 */     m.put("dept_ids", dept_ids);
/* 428 */     if (DBManager.update("delete_registerByDeptID", m))
/*     */     {
/* 430 */       PublicTableDAO.insertSettingLogs("删除", "帐号 部门", dept_ids, stl);
/* 431 */       return true;
/*     */     }
/* 433 */     return false;
/*     */   }
/*     */ 
/*     */   public boolean updatePasswordByUserID(UserRegisterBean urb, SettingLogsBean stl)
/*     */   {
/* 444 */     if (DBManager.update("update_passwordByUserID", urb))
/*     */     {
/* 446 */       PublicTableDAO.insertSettingLogs("修改", "帐号密码 用户", urb.getUser_id(), stl);
/* 447 */       return true;
/*     */     }
/* 449 */     return false;
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.dao.org.user.UserDAODBImpl
 * JD-Core Version:    0.6.2
 */