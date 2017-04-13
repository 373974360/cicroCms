/*     */ package com.cicro.wcm.dao.org.rmi;
/*     */ 
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.bean.org.dept.DeptBean;
/*     */ import com.cicro.wcm.bean.org.dept.DeptLevelBean;
/*     */ import com.cicro.wcm.bean.org.user.UserBean;
/*     */ import com.cicro.wcm.bean.org.user.UserLevelBean;
/*     */ import com.cicro.wcm.bean.org.user.UserRegisterBean;
/*     */ import com.cicro.wcm.dao.org.OrgDAOFactory;
/*     */ import com.cicro.wcm.dao.org.dept.IDeptDAO;
/*     */ import com.cicro.wcm.dao.org.user.IUserDAO;
/*     */ import com.cicro.wcm.rmi.IOrgRmi;
/*     */ import com.cicro.wcm.services.org.user.UserRegisterManager;
/*     */ import java.rmi.RemoteException;
/*     */ import java.rmi.server.UnicastRemoteObject;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class OrgRmiImpl extends UnicastRemoteObject
/*     */   implements IOrgRmi
/*     */ {
/*     */   private static final long serialVersionUID = -7888454141880733613L;
/*     */ 
/*     */   public OrgRmiImpl()
/*     */     throws RemoteException
/*     */   {
/*     */   }
/*     */ 
/*     */   public int getDeptID()
/*     */     throws RemoteException
/*     */   {
/*  38 */     return OrgDAOFactory.getDeptDao().getDeptID();
/*     */   }
/*     */ 
/*     */   public List getAllDeptList()
/*     */     throws RemoteException
/*     */   {
/*  49 */     return OrgDAOFactory.getDeptDao().getAllDeptList();
/*     */   }
/*     */ 
/*     */   public List<DeptBean> getChildDeptListForDB(Map<String, String> con_m)
/*     */     throws RemoteException
/*     */   {
/*  61 */     return OrgDAOFactory.getDeptDao().getChildDeptListForDB(con_m);
/*     */   }
/*     */ 
/*     */   public DeptBean getDeptBeanByID(String id)
/*     */     throws RemoteException
/*     */   {
/*  71 */     return OrgDAOFactory.getDeptDao().getDeptBeanByID(id);
/*     */   }
/*     */ 
/*     */   public boolean insertDept(DeptBean db, SettingLogsBean stl)
/*     */     throws RemoteException
/*     */   {
/*  81 */     return OrgDAOFactory.getDeptDao().insertDept(db, stl);
/*     */   }
/*     */ 
/*     */   public boolean updateDept(DeptBean db, SettingLogsBean stl)
/*     */     throws RemoteException
/*     */   {
/*  91 */     return OrgDAOFactory.getDeptDao().updateDept(db, stl);
/*     */   }
/*     */ 
/*     */   public boolean moveDept(Map<String, String> m, SettingLogsBean stl)
/*     */     throws RemoteException
/*     */   {
/* 102 */     return OrgDAOFactory.getDeptDao().moveDept(m, stl);
/*     */   }
/*     */ 
/*     */   public boolean deleteDept(String ids, SettingLogsBean stl)
/*     */     throws RemoteException
/*     */   {
/* 112 */     return OrgDAOFactory.getDeptDao().deleteDept(ids, stl);
/*     */   }
/*     */ 
/*     */   public boolean saveDeptSort(String ids, SettingLogsBean stl)
/*     */     throws RemoteException
/*     */   {
/* 124 */     return OrgDAOFactory.getDeptDao().saveDeptSort(ids, stl);
/*     */   }
/*     */ 
/*     */   public List getAllDeptManagerList()
/*     */     throws RemoteException
/*     */   {
/* 137 */     return OrgDAOFactory.getDeptDao().getAllDeptManagerList();
/*     */   }
/*     */ 
/*     */   public boolean insertDetpManager(String user_ids, String dept_id, SettingLogsBean stl)
/*     */     throws RemoteException
/*     */   {
/* 151 */     return OrgDAOFactory.getDeptDao().insertDetpManager(user_ids, dept_id, stl);
/*     */   }
/*     */ 
/*     */   public boolean updateDetpManager(String user_ids, String dept_id, SettingLogsBean stl)
/*     */     throws RemoteException
/*     */   {
/* 165 */     return OrgDAOFactory.getDeptDao().updateDetpManager(user_ids, dept_id, stl);
/*     */   }
/*     */ 
/*     */   public boolean deleteDeptManager(String user_ids, String dept_ids, SettingLogsBean stl)
/*     */     throws RemoteException
/*     */   {
/* 177 */     return OrgDAOFactory.getDeptDao().deleteDeptManager(user_ids, dept_ids, stl);
/*     */   }
/*     */ 
/*     */   public List getAllDeptLevelList()
/*     */     throws RemoteException
/*     */   {
/* 191 */     return OrgDAOFactory.getDeptDao().getAllDeptLevelList();
/*     */   }
/*     */ 
/*     */   public DeptLevelBean getDeptLevelBeanByID(String id)
/*     */     throws RemoteException
/*     */   {
/* 202 */     return OrgDAOFactory.getDeptDao().getDeptLevelBeanByID(id);
/*     */   }
/*     */ 
/*     */   public boolean insertDeptLevel(DeptLevelBean dlb, SettingLogsBean stl)
/*     */     throws RemoteException
/*     */   {
/* 211 */     return OrgDAOFactory.getDeptDao().insertDeptLevel(dlb, stl);
/*     */   }
/*     */ 
/*     */   public boolean updateDeptLevel(DeptLevelBean dlb, SettingLogsBean stl)
/*     */     throws RemoteException
/*     */   {
/* 220 */     return OrgDAOFactory.getDeptDao().updateDeptLevel(dlb, stl);
/*     */   }
/*     */ 
/*     */   public boolean deleteDeptLevel(String ids, SettingLogsBean stl)
/*     */     throws RemoteException
/*     */   {
/* 229 */     return OrgDAOFactory.getDeptDao().deleteDeptLevel(ids, stl);
/*     */   }
/*     */ 
/*     */   public List getAllUserList()
/*     */     throws RemoteException
/*     */   {
/* 241 */     return OrgDAOFactory.getUserDao().getAllUserList();
/*     */   }
/*     */ 
/*     */   public List getUserListByDeptIDForDB(Map<String, String> conn_m)
/*     */     throws RemoteException
/*     */   {
/* 251 */     return OrgDAOFactory.getUserDao().getUserListByDeptIDForDB(conn_m);
/*     */   }
/*     */ 
/*     */   public String getUserCountByDeptIDForDB(Map<String, String> conn_m)
/*     */     throws RemoteException
/*     */   {
/* 261 */     return OrgDAOFactory.getUserDao().getUserCountByDeptIDForDB(conn_m);
/*     */   }
/*     */ 
/*     */   public UserBean getUserBeanByID(String id)
/*     */     throws RemoteException
/*     */   {
/* 270 */     return OrgDAOFactory.getUserDao().getUserBeanByID(id);
/*     */   }
/*     */ 
/*     */   public boolean saveUserSort(String user_ids, SettingLogsBean stl)
/*     */   {
/* 283 */     return OrgDAOFactory.getUserDao().saveUserSort(user_ids, stl);
/*     */   }
/*     */ 
/*     */   public boolean insertUser(UserBean ub, UserRegisterBean urb, boolean isAddReg, SettingLogsBean stl)
/*     */     throws RemoteException
/*     */   {
/* 295 */     return OrgDAOFactory.getUserDao().insertUser(ub, urb, isAddReg, stl);
/*     */   }
/*     */ 
/*     */   public boolean updateUser(UserBean ub, SettingLogsBean stl)
/*     */     throws RemoteException
/*     */   {
/* 305 */     return OrgDAOFactory.getUserDao().updateUser(ub, stl);
/*     */   }
/*     */ 
/*     */   public boolean moveUser(Map<String, String> m, SettingLogsBean stl)
/*     */     throws RemoteException
/*     */   {
/* 315 */     return OrgDAOFactory.getUserDao().moveUser(m, stl);
/*     */   }
/*     */ 
/*     */   public boolean updateUserStatus(int user_status, String user_ids, SettingLogsBean stl)
/*     */     throws RemoteException
/*     */   {
/* 326 */     return OrgDAOFactory.getUserDao().updateUserStatus(user_status, user_ids, stl);
/*     */   }
/*     */ 
/*     */   public boolean deleteUser(String ids, SettingLogsBean stl)
/*     */     throws RemoteException
/*     */   {
/* 336 */     return OrgDAOFactory.getUserDao().deleteUser(ids, stl);
/*     */   }
/*     */ 
/*     */   public boolean deleteUserByDeptID(String detp_ids, SettingLogsBean stl)
/*     */     throws RemoteException
/*     */   {
/* 346 */     return OrgDAOFactory.getUserDao().deleteUserByDeptID(detp_ids, stl);
/*     */   }
/*     */ 
/*     */   public List<UserLevelBean> getAllUserLevelList()
/*     */     throws RemoteException
/*     */   {
/* 357 */     return OrgDAOFactory.getUserDao().getAllUserLevelList();
/*     */   }
/*     */ 
/*     */   public UserLevelBean getUserLevelBeanByID(String id)
/*     */     throws RemoteException
/*     */   {
/* 366 */     return OrgDAOFactory.getUserDao().getUserLevelBeanByID(id);
/*     */   }
/*     */ 
/*     */   public boolean insertUserLevel(UserLevelBean ulb, SettingLogsBean stl)
/*     */     throws RemoteException
/*     */   {
/* 376 */     return OrgDAOFactory.getUserDao().insertUserLevel(ulb, stl);
/*     */   }
/*     */ 
/*     */   public boolean updateUserLevel(UserLevelBean ulb, SettingLogsBean stl)
/*     */     throws RemoteException
/*     */   {
/* 386 */     return OrgDAOFactory.getUserDao().updateUserLevel(ulb, stl);
/*     */   }
/*     */ 
/*     */   public boolean deleteUserLevel(String ids, SettingLogsBean stl)
/*     */     throws RemoteException
/*     */   {
/* 397 */     return OrgDAOFactory.getUserDao().deleteUserLevel(ids, stl);
/*     */   }
/*     */ 
/*     */   public List<UserRegisterBean> getAllUserRegister()
/*     */     throws RemoteException
/*     */   {
/* 409 */     return OrgDAOFactory.getUserDao().getAllUserRegister();
/*     */   }
/*     */ 
/*     */   public String getUserRegisterCount(Map<String, String> con_m)
/*     */   {
/* 418 */     return OrgDAOFactory.getUserDao().getUserRegisterCount(con_m);
/*     */   }
/*     */ 
/*     */   public List<UserRegisterBean> getAllUserRegisterForDB(Map<String, String> con_m)
/*     */   {
/* 428 */     return OrgDAOFactory.getUserDao().getAllUserRegisterForDB(con_m);
/*     */   }
/*     */ 
/*     */   public UserRegisterBean getUserRegisterBeanByUname(String user_name)
/*     */     throws RemoteException
/*     */   {
/* 439 */     return OrgDAOFactory.getUserDao().getUserRegisterBeanByUname(user_name);
/*     */   }
/*     */ 
/*     */   public boolean insertRegister(UserRegisterBean urb, SettingLogsBean stl)
/*     */     throws RemoteException
/*     */   {
/* 449 */     return OrgDAOFactory.getUserDao().insertRegister(urb, stl);
/*     */   }
/*     */ 
/*     */   public boolean updateRegister(UserRegisterBean urb, SettingLogsBean stl)
/*     */     throws RemoteException
/*     */   {
/* 459 */     return OrgDAOFactory.getUserDao().updateRegister(urb, stl);
/*     */   }
/*     */ 
/*     */   public boolean updateRegisterStatus(int reg_status, String reg_ids, SettingLogsBean stl)
/*     */     throws RemoteException
/*     */   {
/* 469 */     return OrgDAOFactory.getUserDao().updateRegisterStatus(reg_status, reg_ids, stl);
/*     */   }
/*     */ 
/*     */   public boolean deleteRegisterByRID(String reg_ids, SettingLogsBean stl)
/*     */     throws RemoteException
/*     */   {
/* 479 */     return OrgDAOFactory.getUserDao().deleteRegisterByRID(reg_ids, stl);
/*     */   }
/*     */ 
/*     */   public boolean deleteRegisterByUserID(String user_ids, SettingLogsBean stl)
/*     */     throws RemoteException
/*     */   {
/* 489 */     return OrgDAOFactory.getUserDao().deleteRegisterByUserID(user_ids, stl);
/*     */   }
/*     */ 
/*     */   public boolean deleteRegisterByDeptID(String dept_ids, SettingLogsBean stl)
/*     */     throws RemoteException
/*     */   {
/* 499 */     return OrgDAOFactory.getUserDao().deleteRegisterByDeptID(dept_ids, stl);
/*     */   }
/*     */ 
/*     */   public boolean updatePasswordByUserID(UserRegisterBean urb, SettingLogsBean stl)
/*     */     throws RemoteException
/*     */   {
/* 509 */     return OrgDAOFactory.getUserDao().updatePasswordByUserID(urb, stl);
/*     */   }
/*     */ 
/*     */   public List<UserBean> getUserByOrgid(Long orgid)
/*     */     throws RemoteException
/*     */   {
/* 518 */     List list = new ArrayList();
/*     */     try {
/* 520 */       Map conn_m = new HashMap();
/* 521 */       conn_m.put("dept_id", orgid.toString());
/* 522 */       list = getUserListByDeptIDForDB(conn_m);
/*     */     } catch (Exception e) {
/* 524 */       e.printStackTrace();
/*     */     }
/*     */ 
/* 527 */     return list;
/*     */   }
/*     */ 
/*     */   public UserBean getUserByUserid(Long userid) throws RemoteException
/*     */   {
/* 532 */     return getUserBeanByID(userid.toString());
/*     */   }
/*     */ 
/*     */   public UserBean CheckUserLoginReturnUser(String username, String password) throws RemoteException
/*     */   {
/*     */     try {
/* 538 */       String ms = UserRegisterManager.checkUserLogin(username, password);
/* 539 */       if ("0".equals(ms))
/*     */       {
/* 541 */         UserRegisterBean userRegisterBean = getUserRegisterBeanByUname(username);
/* 542 */         return getUserBeanByID(userRegisterBean.getUser_id());
/*     */       }
/* 544 */       return null;
/*     */     }
/*     */     catch (Exception e) {
/* 547 */       e.printStackTrace();
/* 548 */     }return null;
/*     */   }
/*     */ 
/*     */   public DeptBean getOrgByUserid(Long userid)
/*     */     throws RemoteException
/*     */   {
/* 556 */     DeptBean deptBean = null;
/*     */     try {
/* 558 */       UserBean userBean = getUserBeanByID(userid.toString());
/* 559 */       deptBean = getDeptBeanByID(userBean.getDept_id());
/*     */     } catch (Exception e) {
/* 561 */       e.printStackTrace();
/*     */     } finally {
/* 563 */       return deptBean;
/*     */     }
/*     */   }
/*     */ 
/*     */   public DeptBean getOrgByOrgid(long orgid) throws RemoteException
/*     */   {
/* 569 */     return getDeptBeanByID(orgid);
/*     */   }
/*     */ 
/*     */   public List<DeptBean> getChildrenOrg(long orgid) throws RemoteException
/*     */   {
/* 574 */     List list = new ArrayList();
/*     */     try {
/* 576 */       Map conM = new HashMap();
/* 577 */       conM.put("dept_id", orgid);
/* 578 */       list = getChildDeptListForDB(conM);
/*     */     } catch (Exception e) {
/* 580 */       e.printStackTrace();
/*     */     } finally {
/* 582 */       return list;
/*     */     }
/*     */   }
/*     */ 
/*     */   public UserBean getUserByUname(String uname) throws RemoteException
/*     */   {
/* 588 */     UserRegisterBean userRegisterBean = getUserRegisterBeanByUname(uname);
/* 589 */     return getUserBeanByID(userRegisterBean.getUser_id());
/*     */   }
/*     */ 
/*     */   public boolean updatePassword(Long userid, String password)
/*     */     throws RemoteException
/*     */   {
/* 595 */     boolean result = false;
/*     */     try {
/* 597 */       SettingLogsBean stl = null;
/* 598 */       UserRegisterBean urb = new UserRegisterBean();
/* 599 */       urb.setUser_id(Integer.parseInt(userid));
/* 600 */       urb.setPassword(password);
/* 601 */       result = updatePasswordByUserID(urb, stl);
/*     */     } catch (Exception e) {
/* 603 */       e.printStackTrace();
/* 604 */       result = false;
/*     */     } finally {
/* 606 */       return result;
/*     */     }
/*     */   }
/*     */ 
/*     */   public Long getUserId(String username)
/*     */     throws RemoteException
/*     */   {
/* 613 */     return Long.valueOf(getUserRegisterBeanByUname(username).getUser_id());
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.dao.org.rmi.OrgRmiImpl
 * JD-Core Version:    0.6.2
 */