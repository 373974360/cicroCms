package com.cicro.wcm.dao.org.dept;

import com.cicro.wcm.bean.logs.SettingLogsBean;
import com.cicro.wcm.bean.org.dept.DeptBean;
import com.cicro.wcm.bean.org.dept.DeptLevelBean;
import java.util.List;
import java.util.Map;

public abstract interface IDeptDAO
{
  public abstract List getAllDeptList();

  public abstract int getDeptID();

  public abstract List<DeptBean> getChildDeptListForDB(Map<String, String> paramMap);

  public abstract DeptBean getDeptBeanByID(String paramString);

  public abstract boolean insertDept(DeptBean paramDeptBean, SettingLogsBean paramSettingLogsBean);

  public abstract boolean updateDept(DeptBean paramDeptBean, SettingLogsBean paramSettingLogsBean);

  public abstract boolean moveDept(Map<String, String> paramMap, SettingLogsBean paramSettingLogsBean);

  public abstract boolean deleteDept(String paramString, SettingLogsBean paramSettingLogsBean);

  public abstract boolean saveDeptSort(String paramString, SettingLogsBean paramSettingLogsBean);

  public abstract List getAllDeptManagerList();

  public abstract boolean insertDetpManager(String paramString1, String paramString2, SettingLogsBean paramSettingLogsBean);

  public abstract boolean updateDetpManager(String paramString1, String paramString2, SettingLogsBean paramSettingLogsBean);

  public abstract boolean deleteDeptManager(String paramString1, String paramString2, SettingLogsBean paramSettingLogsBean);

  public abstract List getAllDeptLevelList();

  public abstract DeptLevelBean getDeptLevelBeanByID(String paramString);

  public abstract boolean insertDeptLevel(DeptLevelBean paramDeptLevelBean, SettingLogsBean paramSettingLogsBean);

  public abstract boolean updateDeptLevel(DeptLevelBean paramDeptLevelBean, SettingLogsBean paramSettingLogsBean);

  public abstract boolean deleteDeptLevel(String paramString, SettingLogsBean paramSettingLogsBean);
}

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.dao.org.dept.IDeptDAO
 * JD-Core Version:    0.6.2
 */