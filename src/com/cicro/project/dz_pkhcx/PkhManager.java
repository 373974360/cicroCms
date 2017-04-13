package com.cicro.project.dz_pkhcx;

import com.cicro.util.DateUtil;
import com.cicro.util.FormatUtil;
import com.cicro.wcm.bean.logs.SettingLogsBean;
import com.cicro.wcm.dao.PublicTableDAO;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PkhManager
{
  public static String getPkhCount(Map<String, String> m)
  {
    if (m.containsKey("key_word"))
    {
      if (!FormatUtil.isValiditySQL((String)m.get("key_word")))
        return "0";
    }
    return PkhDAO.getPkhCount(m);
  }

  public static List<PkhBean> getPkhList(Map<String, String> m) {
    if (m.containsKey("key_word"))
    {
      if (!FormatUtil.isValiditySQL((String)m.get("key_word")))
        return new ArrayList();
    }
    return PkhDAO.getPkhList(m);
  }

  public static List<PkhBean> getAllPkhList()
  {
    return PkhDAO.getAllPkhList();
  }

  public static PkhBean getPkhBean(String id, boolean is_browser)
  {
    return PkhDAO.getPkhBean(id, is_browser);
  }

  public static boolean insertPkh(PkhBean hb, SettingLogsBean stl)
  {
    hb.setId(PublicTableDAO.getIDByTableName("dz_pkhxx"));
    return PkhDAO.insertPkh(hb, stl);
  }

  public static boolean updatePkh(PkhBean hb, SettingLogsBean stl)
  {
    return PkhDAO.updatePkh(hb, stl);
  }

  public static boolean deletePkh(Map<String, String> m, SettingLogsBean stl)
  {
    return PkhDAO.deletePkh(m, stl);

  }
}