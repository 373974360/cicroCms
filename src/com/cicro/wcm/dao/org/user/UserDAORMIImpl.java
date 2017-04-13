/*     */ package com.cicro.wcm.dao.org.user;
/*     */ 
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.bean.org.user.UserBean;
/*     */ import com.cicro.wcm.bean.org.user.UserLevelBean;
/*     */ import com.cicro.wcm.bean.org.user.UserRegisterBean;
/*     */ import com.cicro.wcm.dao.org.rmi.GetOrgRmi;
/*     */ import com.cicro.wcm.rmi.IOrgRmi;
/*     */ import java.rmi.RemoteException;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class UserDAORMIImpl
/*     */   implements IUserDAO
/*     */ {
/*     */   public List getAllUserList()
/*     */   {
/*     */     try
/*     */     {
/*  22 */       return GetOrgRmi.getorgRmi().getAllUserList();
/*     */     }
/*     */     catch (RemoteException re) {
/*  25 */       re.printStackTrace();
/*  26 */     }return null;
/*     */   }
/*     */ 
/*     */   public List<UserBean> getUserListByDeptIDForDB(Map<String, String> con_m)
/*     */   {
/*     */     try
/*     */     {
/*  37 */       return GetOrgRmi.getorgRmi().getUserListByDeptIDForDB(con_m);
/*     */     }
/*     */     catch (RemoteException re) {
/*  40 */       re.printStackTrace();
/*  41 */     }return null;
/*     */   }
/*     */ 
/*     */   public String getUserCountByDeptIDForDB(Map<String, String> con_m)
/*     */   {
/*     */     try
/*     */     {
/*  53 */       return GetOrgRmi.getorgRmi().getUserCountByDeptIDForDB(con_m);
/*     */     }
/*     */     catch (RemoteException re) {
/*  56 */       re.printStackTrace();
/*  57 */     }return null;
/*     */   }
/*     */ 
/*     */   public UserBean getUserBeanByID(String id)
/*     */   {
/*     */     try
/*     */     {
/*  68 */       return GetOrgRmi.getorgRmi().getUserBeanByID(id);
/*     */     }
/*     */     catch (RemoteException re) {
/*  71 */       re.printStackTrace();
/*  72 */     }return null;
/*     */   }
/*     */ 
/*     */   public boolean insertUser(UserBean ub, UserRegisterBean urb, boolean isAddReg, SettingLogsBean stl)
/*     */   {
/*     */     try
/*     */     {
/*  86 */       return GetOrgRmi.getorgRmi().insertUser(ub, urb, isAddReg, stl);
/*     */     }
/*     */     catch (RemoteException re) {
/*  89 */       re.printStackTrace();
/*  90 */     }return false;
/*     */   }
/*     */ 
/*     */   public boolean updateUser(UserBean ub, SettingLogsBean stl)
/*     */   {
/*     */     try
/*     */     {
/* 102 */       return GetOrgRmi.getorgRmi().updateUser(ub, stl);
/*     */     }
/*     */     catch (RemoteException re) {
/* 105 */       re.printStackTrace();
/* 106 */     }return false;
/*     */   }
/*     */ 
/*     */   public boolean saveUserSort(String ids, SettingLogsBean stl)
/*     */   {
/*     */     try
/*     */     {
/* 121 */       return GetOrgRmi.getorgRmi().saveUserSort(ids, stl);
/*     */     }
/*     */     catch (RemoteException re) {
/* 124 */       re.printStackTrace();
/* 125 */     }return false;
/*     */   }
/*     */ 
/*     */   public boolean moveUser(Map<String, String> m, SettingLogsBean stl)
/*     */   {
/*     */     try
/*     */     {
/* 137 */       return GetOrgRmi.getorgRmi().moveUser(m, stl);
/*     */     }
/*     */     catch (RemoteException re) {
/* 140 */       re.printStackTrace();
/* 141 */     }return false;
/*     */   }
/*     */ 
/*     */   public boolean updateUserStatus(int user_status, String user_ids, SettingLogsBean stl)
/*     */   {
/*     */     try
/*     */     {
/* 154 */       return GetOrgRmi.getorgRmi().updateUserStatus(user_status, user_ids, stl);
/*     */     }
/*     */     catch (RemoteException re) {
/* 157 */       re.printStackTrace();
/* 158 */     }return false;
/*     */   }
/*     */ 
/*     */   public boolean deleteUser(String user_ids, SettingLogsBean stl)
/*     */   {
/*     */     try
/*     */     {
/* 170 */       return GetOrgRmi.getorgRmi().deleteUser(user_ids, stl);
/*     */     }
/*     */     catch (RemoteException re) {
/* 173 */       re.printStackTrace();
/* 174 */     }return false;
/*     */   }
/*     */ 
/*     */   public boolean deleteUserByDeptID(String detp_ids, SettingLogsBean stl)
/*     */   {
/*     */     try
/*     */     {
/* 186 */       return GetOrgRmi.getorgRmi().deleteUserByDeptID(detp_ids, stl);
/*     */     }
/*     */     catch (RemoteException re) {
/* 189 */       re.printStackTrace();
/* 190 */     }return false;
/*     */   }
/*     */ 
/*     */   public List getAllUserLevelList()
/*     */   {
/*     */     try
/*     */     {
/* 203 */       return GetOrgRmi.getorgRmi().getAllUserLevelList();
/*     */     }
/*     */     catch (RemoteException re) {
/* 206 */       re.printStackTrace();
/* 207 */     }return null;
/*     */   }
/*     */ 
/*     */   public UserLevelBean getUserLevelBeanByID(String id)
/*     */   {
/*     */     try
/*     */     {
/* 218 */       return GetOrgRmi.getorgRmi().getUserLevelBeanByID(id);
/*     */     }
/*     */     catch (RemoteException re) {
/* 221 */       re.printStackTrace();
/* 222 */     }return null;
/*     */   }
/*     */ 
/*     */   public boolean insertUserLevel(UserLevelBean ulb, SettingLogsBean stl)
/*     */   {
/*     */     try
/*     */     {
/* 234 */       return GetOrgRmi.getorgRmi().insertUserLevel(ulb, stl);
/*     */     }
/*     */     catch (RemoteException re) {
/* 237 */       re.printStackTrace();
/* 238 */     }return false;
/*     */   }
/*     */ 
/*     */   public boolean updateUserLevel(UserLevelBean ulb, SettingLogsBean stl)
/*     */   {
/*     */     try
/*     */     {
/* 250 */       return GetOrgRmi.getorgRmi().updateUserLevel(ulb, stl);
/*     */     }
/*     */     catch (RemoteException re) {
/* 253 */       re.printStackTrace();
/* 254 */     }return false;
/*     */   }
/*     */ 
/*     */   public boolean deleteUserLevel(String ids, SettingLogsBean stl)
/*     */   {
/*     */     try
/*     */     {
/* 267 */       return GetOrgRmi.getorgRmi().deleteUserLevel(ids, stl);
/*     */     }
/*     */     catch (RemoteException re) {
/* 270 */       re.printStackTrace();
/* 271 */     }return false;
/*     */   }
/*     */ 
/*     */   public List getAllUserRegister()
/*     */   {
/*     */     try
/*     */     {
/* 285 */       return GetOrgRmi.getorgRmi().getAllUserRegister();
/*     */     }
/*     */     catch (RemoteException re) {
/* 288 */       re.printStackTrace();
/* 289 */     }return null;
/*     */   }
/*     */ 
/*     */   public String getUserRegisterCount(Map<String, String> con_m)
/*     */   {
/*     */     try
/*     */     {
/* 300 */       return GetOrgRmi.getorgRmi().getUserRegisterCount(con_m);
/*     */     }
/*     */     catch (RemoteException re) {
/* 303 */       re.printStackTrace();
/* 304 */     }return null;
/*     */   }
/*     */ 
/*     */   public List<UserRegisterBean> getAllUserRegisterForDB(Map<String, String> con_m)
/*     */   {
/*     */     try
/*     */     {
/* 316 */       return GetOrgRmi.getorgRmi().getAllUserRegisterForDB(con_m);
/*     */     }
/*     */     catch (RemoteException re) {
/* 319 */       re.printStackTrace();
/* 320 */     }return null;
/*     */   }
/*     */ 
/*     */   public UserRegisterBean getUserRegisterBeanByUname(String user_name)
/*     */   {
/*     */     try
/*     */     {
/* 334 */       return GetOrgRmi.getorgRmi().getUserRegisterBeanByUname(user_name);
/*     */     }
/*     */     catch (RemoteException re) {
/* 337 */       re.printStackTrace();
/* 338 */     }return null;
/*     */   }
/*     */ 
/*     */   public boolean insertRegister(UserRegisterBean urb, SettingLogsBean stl)
/*     */   {
/*     */     try
/*     */     {
/* 350 */       return GetOrgRmi.getorgRmi().insertRegister(urb, stl);
/*     */     }
/*     */     catch (RemoteException re) {
/* 353 */       re.printStackTrace();
/* 354 */     }return false;
/*     */   }
/*     */ 
/*     */   public boolean updateRegister(UserRegisterBean urb, SettingLogsBean stl)
/*     */   {
/*     */     try
/*     */     {
/* 366 */       return GetOrgRmi.getorgRmi().updateRegister(urb, stl);
/*     */     }
/*     */     catch (RemoteException re) {
/* 369 */       re.printStackTrace();
/* 370 */     }return false;
/*     */   }
/*     */ 
/*     */   public boolean updateRegisterStatus(int reg_status, String reg_ids, SettingLogsBean stl)
/*     */   {
/*     */     try
/*     */     {
/* 382 */       return GetOrgRmi.getorgRmi().updateRegisterStatus(reg_status, reg_ids, stl);
/*     */     }
/*     */     catch (RemoteException re) {
/* 385 */       re.printStackTrace();
/* 386 */     }return false;
/*     */   }
/*     */ 
/*     */   public boolean deleteRegisterByRID(String reg_ids, SettingLogsBean stl)
/*     */   {
/*     */     try
/*     */     {
/* 398 */       return GetOrgRmi.getorgRmi().deleteRegisterByRID(reg_ids, stl);
/*     */     }
/*     */     catch (RemoteException re) {
/* 401 */       re.printStackTrace();
/* 402 */     }return false;
/*     */   }
/*     */ 
/*     */   public boolean deleteRegisterByUserID(String user_ids, SettingLogsBean stl)
/*     */   {
/*     */     try
/*     */     {
/* 414 */       return GetOrgRmi.getorgRmi().deleteRegisterByUserID(user_ids, stl);
/*     */     }
/*     */     catch (RemoteException re) {
/* 417 */       re.printStackTrace();
/* 418 */     }return false;
/*     */   }
/*     */ 
/*     */   public boolean deleteRegisterByDeptID(String dept_ids, SettingLogsBean stl)
/*     */   {
/*     */     try
/*     */     {
/* 430 */       return GetOrgRmi.getorgRmi().deleteRegisterByDeptID(dept_ids, stl);
/*     */     }
/*     */     catch (RemoteException re) {
/* 433 */       re.printStackTrace();
/* 434 */     }return false;
/*     */   }
/*     */ 
/*     */   public boolean updatePasswordByUserID(UserRegisterBean urb, SettingLogsBean stl)
/*     */   {
/*     */     try
/*     */     {
/* 447 */       return GetOrgRmi.getorgRmi().updatePasswordByUserID(urb, stl);
/*     */     }
/*     */     catch (RemoteException re) {
/* 450 */       re.printStackTrace();
/* 451 */     }return false;
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.dao.org.user.UserDAORMIImpl
 * JD-Core Version:    0.6.2
 */