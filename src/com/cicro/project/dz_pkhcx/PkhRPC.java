package com.cicro.project.dz_pkhcx;

import com.cicro.wcm.bean.logs.SettingLogsBean;
import com.cicro.wcm.services.Log.LogManager;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

public class PkhRPC
{
  public static String getPkhCount(Map<String, String> m)
  {
    return PkhManager.getPkhCount(m);
  }

  public static List<PkhBean> getPkhList(Map<String, String> m)
  {
    return PkhManager.getPkhList(m);
  }

  public static List<PkhBean> getAllPkhList()
  {
    return PkhManager.getAllPkhList();
  }

  public static PkhBean getPkhBean(String gq_id, boolean is_browser)
  {
    return PkhManager.getPkhBean(gq_id, is_browser);
  }

  public static boolean updatePkh(PkhBean hb, HttpServletRequest request)
  {
    SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
    if (stl != null) {
      return PkhManager.updatePkh(hb, stl);
    }
    return false;
  }

  public static boolean insertPkh(PkhBean hb, HttpServletRequest request)
  {
    SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
    if (stl != null) {
      return PkhManager.insertPkh(hb, stl);
    }
    return false;
  }

  public static boolean deletePkh(Map<String, String> m, HttpServletRequest request)
  {
    SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
    if (stl != null) {
      return PkhManager.deletePkh(m, stl);
    }
    return false;
  }
}