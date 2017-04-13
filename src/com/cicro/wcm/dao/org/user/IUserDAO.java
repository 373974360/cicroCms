package com.cicro.wcm.dao.org.user;

import com.cicro.wcm.bean.logs.SettingLogsBean;
import com.cicro.wcm.bean.org.user.UserBean;
import com.cicro.wcm.bean.org.user.UserLevelBean;
import com.cicro.wcm.bean.org.user.UserRegisterBean;
import java.util.List;
import java.util.Map;

public abstract interface IUserDAO
{
  public abstract List getAllUserList();

  public abstract List<UserBean> getUserListByDeptIDForDB(Map<String, String> paramMap);

  public abstract String getUserCountByDeptIDForDB(Map<String, String> paramMap);

  public abstract UserBean getUserBeanByID(String paramString);

  public abstract boolean insertUser(UserBean paramUserBean, UserRegisterBean paramUserRegisterBean, boolean paramBoolean, SettingLogsBean paramSettingLogsBean);

  public abstract boolean updateUser(UserBean paramUserBean, SettingLogsBean paramSettingLogsBean);

  public abstract boolean saveUserSort(String paramString, SettingLogsBean paramSettingLogsBean);

  public abstract boolean moveUser(Map<String, String> paramMap, SettingLogsBean paramSettingLogsBean);

  public abstract boolean updateUserStatus(int paramInt, String paramString, SettingLogsBean paramSettingLogsBean);

  public abstract boolean deleteUser(String paramString, SettingLogsBean paramSettingLogsBean);

  public abstract boolean deleteUserByDeptID(String paramString, SettingLogsBean paramSettingLogsBean);

  public abstract List<UserLevelBean> getAllUserLevelList();

  public abstract UserLevelBean getUserLevelBeanByID(String paramString);

  public abstract boolean insertUserLevel(UserLevelBean paramUserLevelBean, SettingLogsBean paramSettingLogsBean);

  public abstract boolean updateUserLevel(UserLevelBean paramUserLevelBean, SettingLogsBean paramSettingLogsBean);

  public abstract boolean deleteUserLevel(String paramString, SettingLogsBean paramSettingLogsBean);

  public abstract List getAllUserRegister();

  public abstract String getUserRegisterCount(Map<String, String> paramMap);

  public abstract List<UserRegisterBean> getAllUserRegisterForDB(Map<String, String> paramMap);

  public abstract UserRegisterBean getUserRegisterBeanByUname(String paramString);

  public abstract boolean insertRegister(UserRegisterBean paramUserRegisterBean, SettingLogsBean paramSettingLogsBean);

  public abstract boolean updateRegister(UserRegisterBean paramUserRegisterBean, SettingLogsBean paramSettingLogsBean);

  public abstract boolean updateRegisterStatus(int paramInt, String paramString, SettingLogsBean paramSettingLogsBean);

  public abstract boolean deleteRegisterByRID(String paramString, SettingLogsBean paramSettingLogsBean);

  public abstract boolean deleteRegisterByUserID(String paramString, SettingLogsBean paramSettingLogsBean);

  public abstract boolean deleteRegisterByDeptID(String paramString, SettingLogsBean paramSettingLogsBean);

  public abstract boolean updatePasswordByUserID(UserRegisterBean paramUserRegisterBean, SettingLogsBean paramSettingLogsBean);
}

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.dao.org.user.IUserDAO
 * JD-Core Version:    0.6.2
 */