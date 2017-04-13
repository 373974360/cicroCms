package com.cicro.project.dz_pkhcx;

import com.cicro.wcm.bean.logs.SettingLogsBean;
import com.cicro.wcm.dao.PublicTableDAO;
import com.cicro.wcm.db.DBManager;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PkhDAO
{
  public static String getPkhCount(Map<String, String> m)
  {
    return DBManager.getString("getPkhCount", m);
  }

  public static List<PkhBean> getPkhList(Map<String, String> m)
  {
    return DBManager.queryFList("getPkhList", m);
  }

  public static PkhBean getPkhBean(String id, boolean is_browser)
  {
    Map m = new HashMap();
    m.put("id", id);
    return (PkhBean)DBManager.queryFObj("getPkhBean", m);
  }

  public static List<PkhBean> getAllPkhList()
  {
    return DBManager.queryFList("getAllPkhList", "");
  }

  public static boolean insertPkh(PkhBean Pkh, SettingLogsBean stl)
  {
    if (DBManager.insert("insertPkh", Pkh))
    {
      PublicTableDAO.insertSettingLogs("添加", "贫困户信息", Pkh.getId()+"", stl);
      return true;
    }
    return false;
  }

  public static boolean insertPkh(PkhBean Pkh)
  {
    return DBManager.insert("insertPkh", Pkh);
  }

  public static boolean updatePkh(PkhBean Pkh, SettingLogsBean stl)
  {
    if (DBManager.update("updatePkh", Pkh))
    {
      PublicTableDAO.insertSettingLogs("修改", "贫困户信息", Pkh.getId()+"", stl);
      return true;
    }
    return false;
  }

  public static boolean deletePkh(Map<String, String> m, SettingLogsBean stl)
  {
    if (DBManager.delete("deletePkh", m))
    {
      PublicTableDAO.insertSettingLogs("删除", "贫困户信息", (String)m.get("ids"), stl);
      return true;
    }
    return false;
  }
}