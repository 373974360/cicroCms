package com.cicro.wcm.rmi;

import com.cicro.wcm.bean.logs.SettingLogsBean;
import com.cicro.wcm.bean.org.dept.DeptBean;
import com.cicro.wcm.bean.org.dept.DeptLevelBean;
import com.cicro.wcm.bean.org.user.UserBean;
import com.cicro.wcm.bean.org.user.UserLevelBean;
import com.cicro.wcm.bean.org.user.UserRegisterBean;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

public abstract interface IOrgRmi extends Remote
{
  public abstract List getAllDeptList()
    throws RemoteException;

  public abstract int getDeptID()
    throws RemoteException;

  public abstract List<DeptBean> getChildDeptListForDB(Map<String, String> paramMap)
    throws RemoteException;

  public abstract DeptBean getDeptBeanByID(String paramString)
    throws RemoteException;

  public abstract boolean insertDept(DeptBean paramDeptBean, SettingLogsBean paramSettingLogsBean)
    throws RemoteException;

  public abstract boolean updateDept(DeptBean paramDeptBean, SettingLogsBean paramSettingLogsBean)
    throws RemoteException;

  public abstract boolean moveDept(Map<String, String> paramMap, SettingLogsBean paramSettingLogsBean)
    throws RemoteException;

  public abstract boolean deleteDept(String paramString, SettingLogsBean paramSettingLogsBean)
    throws RemoteException;

  public abstract boolean saveDeptSort(String paramString, SettingLogsBean paramSettingLogsBean)
    throws RemoteException;

  public abstract List getAllDeptManagerList()
    throws RemoteException;

  public abstract boolean insertDetpManager(String paramString1, String paramString2, SettingLogsBean paramSettingLogsBean)
    throws RemoteException;

  public abstract boolean updateDetpManager(String paramString1, String paramString2, SettingLogsBean paramSettingLogsBean)
    throws RemoteException;

  public abstract boolean deleteDeptManager(String paramString1, String paramString2, SettingLogsBean paramSettingLogsBean)
    throws RemoteException;

  public abstract List getAllDeptLevelList()
    throws RemoteException;

  public abstract DeptLevelBean getDeptLevelBeanByID(String paramString)
    throws RemoteException;

  public abstract boolean insertDeptLevel(DeptLevelBean paramDeptLevelBean, SettingLogsBean paramSettingLogsBean)
    throws RemoteException;

  public abstract boolean updateDeptLevel(DeptLevelBean paramDeptLevelBean, SettingLogsBean paramSettingLogsBean)
    throws RemoteException;

  public abstract boolean deleteDeptLevel(String paramString, SettingLogsBean paramSettingLogsBean)
    throws RemoteException;

  public abstract List getAllUserList()
    throws RemoteException;

  public abstract List<UserBean> getUserListByDeptIDForDB(Map<String, String> paramMap)
    throws RemoteException;

  public abstract String getUserCountByDeptIDForDB(Map<String, String> paramMap)
    throws RemoteException;

  public abstract UserBean getUserBeanByID(String paramString)
    throws RemoteException;

  public abstract boolean insertUser(UserBean paramUserBean, UserRegisterBean paramUserRegisterBean, boolean paramBoolean, SettingLogsBean paramSettingLogsBean)
    throws RemoteException;

  public abstract boolean updateUser(UserBean paramUserBean, SettingLogsBean paramSettingLogsBean)
    throws RemoteException;

  public abstract boolean saveUserSort(String paramString, SettingLogsBean paramSettingLogsBean)
    throws RemoteException;

  public abstract boolean moveUser(Map<String, String> paramMap, SettingLogsBean paramSettingLogsBean)
    throws RemoteException;

  public abstract boolean updateUserStatus(int paramInt, String paramString, SettingLogsBean paramSettingLogsBean)
    throws RemoteException;

  public abstract boolean deleteUser(String paramString, SettingLogsBean paramSettingLogsBean)
    throws RemoteException;

  public abstract boolean deleteUserByDeptID(String paramString, SettingLogsBean paramSettingLogsBean)
    throws RemoteException;

  public abstract List getAllUserLevelList()
    throws RemoteException;

  public abstract UserLevelBean getUserLevelBeanByID(String paramString)
    throws RemoteException;

  public abstract boolean insertUserLevel(UserLevelBean paramUserLevelBean, SettingLogsBean paramSettingLogsBean)
    throws RemoteException;

  public abstract boolean updateUserLevel(UserLevelBean paramUserLevelBean, SettingLogsBean paramSettingLogsBean)
    throws RemoteException;

  public abstract boolean deleteUserLevel(String paramString, SettingLogsBean paramSettingLogsBean)
    throws RemoteException;

  public abstract List<UserRegisterBean> getAllUserRegister()
    throws RemoteException;

  public abstract String getUserRegisterCount(Map<String, String> paramMap)
    throws RemoteException;

  public abstract List<UserRegisterBean> getAllUserRegisterForDB(Map<String, String> paramMap)
    throws RemoteException;

  public abstract UserRegisterBean getUserRegisterBeanByUname(String paramString)
    throws RemoteException;

  public abstract boolean insertRegister(UserRegisterBean paramUserRegisterBean, SettingLogsBean paramSettingLogsBean)
    throws RemoteException;

  public abstract boolean updateRegister(UserRegisterBean paramUserRegisterBean, SettingLogsBean paramSettingLogsBean)
    throws RemoteException;

  public abstract boolean updateRegisterStatus(int paramInt, String paramString, SettingLogsBean paramSettingLogsBean)
    throws RemoteException;

  public abstract boolean deleteRegisterByRID(String paramString, SettingLogsBean paramSettingLogsBean)
    throws RemoteException;

  public abstract boolean deleteRegisterByUserID(String paramString, SettingLogsBean paramSettingLogsBean)
    throws RemoteException;

  public abstract boolean deleteRegisterByDeptID(String paramString, SettingLogsBean paramSettingLogsBean)
    throws RemoteException;

  public abstract boolean updatePasswordByUserID(UserRegisterBean paramUserRegisterBean, SettingLogsBean paramSettingLogsBean)
    throws RemoteException;

  public abstract List<UserBean> getUserByOrgid(Long paramLong)
    throws RemoteException;

  public abstract UserBean getUserByUserid(Long paramLong)
    throws RemoteException;

  public abstract UserBean CheckUserLoginReturnUser(String paramString1, String paramString2)
    throws RemoteException;

  public abstract DeptBean getOrgByUserid(Long paramLong)
    throws RemoteException;

  public abstract DeptBean getOrgByOrgid(long paramLong)
    throws RemoteException;

  public abstract List<DeptBean> getChildrenOrg(long paramLong)
    throws RemoteException;

  public abstract UserBean getUserByUname(String paramString)
    throws RemoteException;

  public abstract boolean updatePassword(Long paramLong, String paramString)
    throws RemoteException;

  public abstract Long getUserId(String paramString)
    throws RemoteException;
}

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.rmi.IOrgRmi
 * JD-Core Version:    0.6.2
 */