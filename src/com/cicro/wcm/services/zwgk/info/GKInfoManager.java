package com.cicro.wcm.services.zwgk.info;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sound.midi.MidiDevice.Info;

import com.cicro.util.DateUtil;
import com.cicro.wcm.bean.cms.info.GKFbsznBean;
import com.cicro.wcm.bean.cms.info.GKInfoBean;
import com.cicro.wcm.bean.cms.info.GKResFileBean;
import com.cicro.wcm.bean.cms.info.InfoBean;
import com.cicro.wcm.bean.org.user.UserBean;
import com.cicro.wcm.dao.cms.info.InfoDAO;
import com.cicro.wcm.dao.zwgk.info.GKInfoDAO;
import com.cicro.wcm.services.cms.info.InfoBaseManager;
import com.cicro.wcm.services.org.user.UserManager;
import com.cicro.wcm.services.zwgk.index.IndexManager;

public class GKInfoManager
{
  public static List<GKInfoBean> getBroGKInfoList(Map<String, String> map)
  {
    int start_page = Integer.parseInt((String)map.get("current_page"));
    int page_size = Integer.parseInt((String)map.get("page_size"));
    map.put("start_num", (start_page - 1) * page_size+"");
    map.put("page_size", page_size+"");
    return GKInfoDAO.getBroGKInfoList(map);
  }

  public static List<GKFbsznBean> getBroGKBSZNInfoList(Map<String, String> map)
  {
    int start_page = Integer.parseInt((String)map.get("current_page"));
    int page_size = Integer.parseInt((String)map.get("page_size"));
    map.put("start_num", (start_page - 1) * page_size+"");
    map.put("page_size", page_size+"");
    return GKInfoDAO.getBroGKBSZNInfoList(map);
  }

  public static String getBroGKInfoCountForSharedCategory(Map<String, String> m)
  {
    return GKInfoDAO.getBroGKInfoCountForSharedCategory(m);
  }

  public static List<GKInfoBean> getBroGKInfoListForSharedCategory(Map<String, String> map)
  {
    int start_page = Integer.parseInt((String)map.get("current_page"));
    int page_size = Integer.parseInt((String)map.get("page_size"));
    map.put("start_num", (start_page - 1) * page_size+"");
    map.put("page_size", page_size+"");
    return GKInfoDAO.getBroGKInfoListForSharedCategory(map);
  }

  public static String getBroGKInfoCount(Map<String, String> m)
  {
    return GKInfoDAO.getBroGKInfoCount(m);
  }

  public static GKInfoBean getGKInfoBean(String info_id)
  {
    return GKInfoDAO.getGKInfoBean(info_id);
  }

  public static List<GKInfoBean> getGKInfoList(Map<String, String> m)
  {
    InfoBaseManager.getSearchSQL(m);
    List<GKInfoBean> gkInfoList = GKInfoDAO.getGKInfoList(m);
	List<GKInfoBean> result = new ArrayList<GKInfoBean>();
	if(gkInfoList != null && gkInfoList.size() > 0)
	{
		Map<String, UserBean> userMap = UserManager.getUserMap();
		for(GKInfoBean bean : gkInfoList)
		{
			InfoBean ifb = InfoBaseManager.getInfoById(bean.getInfo_id()+"");
			if(ifb.getInput_user() != 0)
			{
				if(userMap.get(ifb.getInput_user()+"") != null)
				{
					bean.setInput_user_name(userMap.get(ifb.getInput_user()+"").getUser_realname());
				}
			}
			if(ifb.getModify_user() != 0)
			{
				if(userMap.get(ifb.getModify_user()+"") != null)
				{
					bean.setInput_user_name(userMap.get(ifb.getModify_user()+"").getUser_realname());
				}
			}
			result.add(bean);
		}
	}
	return result;
  }

  public static List<GKResFileBean> getGKResFileList(String info_id)
  {
    return GKInfoDAO.getGKResFileList(info_id);
  }

  public static String getInfoIdForGKIndex(String gk_index)
  {
    return GKInfoDAO.getInfoIdForGKIndex(gk_index);
  }

  public static String getGKInfoCount(Map<String, String> m)
  {
    InfoBaseManager.getSearchSQL(m);
    return GKInfoDAO.getGKInfoCount(m);
  }

  public static boolean reloadGKIndex(Map<String, String> map)
  {
    String node_id = "";
    if (map.containsKey("site_id"))
      node_id = (String)map.get("site_id");
    if (!GKInfoDAO.deleteGKIndexSequence(node_id))
    {
      return false;
    }
    List<GKInfoBean> l = GKInfoDAO.getAllGKInfoList(map);
    if ((l != null) && (l.size() > 0))
    {
      for (GKInfoBean gkb : l) {
        try
        {
          int info_id = gkb.getInfo_id();
          int cat_id = gkb.getCat_id();
          String year = gkb.getGk_year();
          String released_dtime = gkb.getReleased_dtime();
          String input_time = gkb.getInput_dtime();
          String site_id = gkb.getSite_id();
          String ymd = "";
          if ((released_dtime != null) && (!"".equals(released_dtime)))
          {
            ymd = released_dtime;
          }
          else {
            ymd = input_time;
          }

          Map m = IndexManager.getIndex(site_id, cat_id+"", ymd, "");
          if (GKInfoDAO.updateGKIndex(info_id+"", (String)m.get("gk_index"), (String)m.get("gk_num")))
          {
            List<InfoBean> il = InfoDAO.getFromInfoListByIDS(info_id+"");
            if ((il != null) && (il.size() > 0))
            {
              for (InfoBean ib : il)
              {
                GKInfoDAO.updateGKIndex(ib.getInfo_id()+"", (String)m.get("gk_index"), (String)m.get("gk_num"));
              }
            }
          }
        }
        catch (Exception e) {
          e.printStackTrace();
          return false;
        }
      }
    }
    return true;
  }

  public static String getGKYearStr(String info_id)
  {
    return GKInfoDAO.getGKYearStr(info_id);
  }

  public static String getGKPublishStatistics(String type)
  {
    Map m = new HashMap();
    if ("year".equals(type))
    {
      String y = DateUtil.getCurrentDateTime("yyyy");
      m.put("start_time", y + "-01-01 00:00:00");
      m.put("end_time", y + "-12-31 23:59:59");
    }
    if ("quarter".equals(type))
    {
      String y = DateUtil.getCurrentDateTime("yyyy");
      String M = DateUtil.getCurrentDateTime("MM");
      System.out.println(M);
      if ("01,02,03".contains(M))
      {
        m.put("start_time", y + "-01-01 00:00:00");
        m.put("end_time", y + "-03-31 23:59:59");
      }
      if ("04,05,06".contains(M)) {
        System.out.println("----------" + M);
        m.put("start_time", y + "-04-01 00:00:00");
        m.put("end_time", y + "-06-30 23:59:59");
      }
      if ("07,08,09".contains(M))
      {
        m.put("start_time", y + "-07-01 00:00:00");
        m.put("end_time", y + "-09-30 23:59:59");
      }
      if ("10,11,12".contains(M))
      {
        m.put("start_time", y + "-10-01 00:00:00");
        m.put("end_time", y + "-12-31 23:59:59");
      }
    }
    System.out.println(m);
    return GKInfoDAO.getGKPublishStatistics(m);
  }

  public static void main(String[] args)
  {
    System.out.println(getGKPublishStatistics("quarter"));
  }
}