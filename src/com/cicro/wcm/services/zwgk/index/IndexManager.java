package com.cicro.wcm.services.zwgk.index;

import com.cicro.util.FormatUtil;
import com.cicro.wcm.bean.cms.category.CategoryBean;
import com.cicro.wcm.bean.logs.SettingLogsBean;
import com.cicro.wcm.bean.zwgk.index.IndexRoleBean;
import com.cicro.wcm.bean.zwgk.index.IndexSequenceBean;
import com.cicro.wcm.bean.zwgk.node.GKNodeBean;
import com.cicro.wcm.catchs.ISyncCatch;
import com.cicro.wcm.catchs.SyncCatchHandl;
import com.cicro.wcm.dao.zwgk.index.IndexRoleDAO;
import com.cicro.wcm.dao.zwgk.index.IndexSequenceDAO;
import com.cicro.wcm.services.cms.category.CategoryManager;
import com.cicro.wcm.services.zwgk.node.GKNodeManager;
import java.io.PrintStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class IndexManager
  implements ISyncCatch
{
  private static final int VALID_NUM = 1;
  private static final int SEQ_START_NUM = 1;
  private static final String GK_NODE_NUM = "100";
  private static final int NOD_NUM = 0;
  private static final int CAT_NUM = 1;
  private static Map<String, IndexRoleBean> role_mp = new TreeMap();

  static
  {
    reloadCatchHandl();
  }

  public void reloadCatch()
  {
    reloadCatchHandl();
  }

  public static void reloadCatchHandl()
  {
    role_mp.clear();
    List lt = IndexRoleDAO.getAllIndexRole();
    for (int i = 0; i < lt.size(); i++)
    {
      IndexRoleBean irb = (IndexRoleBean)lt.get(i);
      role_mp.put(irb.getIr_id(), irb);
    }
  }

  public static void reLoadRoleMap()
  {
    SyncCatchHandl.reladCatchForRMI("com.cicro.wcm.services.zwgk.index.IndexManager");
  }

  public static List<IndexRoleBean> getRoleList()
  {
    List lt = new ArrayList(role_mp.values());
    return lt;
  }

  public static boolean updateIndexRole(List<IndexRoleBean> lt, SettingLogsBean stl)
  {
    boolean flg = true;
    for (int i = 0; i < lt.size(); i++)
    {
      flg = IndexRoleDAO.updateIndexRole((IndexRoleBean)lt.get(i), stl) ? flg : false;
    }
    reLoadRoleMap();
    return flg;
  }

  public static Map<String, String> getIndex(String nodeId, String catId, String seqYmd, String seq)
  {
    IndexSequenceBean isb = new IndexSequenceBean();
    isb.setSeq_ymd(seqYmd);
    isb.setSite_id(nodeId);
    System.out.println("isb----------" + isb);
    return getIndex(nodeId, catId, isb, seq);
  }

  public static Map<String, String> getIndex(String nodeId, String catId, IndexSequenceBean isb, String seq)
  {
    Map retMp = new HashMap();
    String ret = "";

    IndexRoleBean roleBean = (IndexRoleBean)role_mp.get("F");

    IndexRoleBean irb = (IndexRoleBean)role_mp.get("A");
    System.out.println(role_mp);
    System.out.println(1);
    System.out.println(irb.getIs_valid());
    System.out.println("nodeId-------"+nodeId);
    System.out.println("catId-------"+catId);
    if (irb.getIs_valid() == 1)
    {
      ret = ret + irb.getIr_value() + irb.getIr_space();
    }

    irb = (IndexRoleBean)role_mp.get("B");
    if (irb.getIs_valid() == 1)
    {
      GKNodeBean gkNodeBean = GKNodeManager.getGKNodeBeanByNodeID(nodeId);
      ret = ret + gkNodeBean.getDept_code() + irb.getIr_space();
    }

    irb = (IndexRoleBean)role_mp.get("C");
    if (irb.getIs_valid() == 1)
    {
      CategoryBean cb = CategoryManager.getCategoryBeanCatID(Integer.parseInt(catId), nodeId);
      if (cb == null) {
        return retMp;
      }
      String[] t_oisition = cb.getCat_position().substring(1, cb.getCat_position().length() - 1).split("\\$");
      if ("3".equals(irb.getIr_value()))
      {
        String code = cb.getCat_code();
        isb.setSeq_type(1);
        isb.setSeq_item(code);
        ret = ret + code;
      }
      else if ((t_oisition.length == Integer.parseInt(irb.getIr_value()) + 1) || (t_oisition.length < Integer.parseInt(irb.getIr_value()) + 1))
      {
        String code = cb.getCat_code();
        isb.setSeq_type(1);
        isb.setSeq_item(code);
        ret = ret + code;
      }
      else {
        String code = CategoryManager.getCategoryBeanCatID(Integer.parseInt(t_oisition[(Integer.parseInt(irb.getIr_value()) + 1)]), nodeId).getCat_code();
        isb.setSeq_type(1);
        isb.setSeq_item(code);
        ret = ret + code;
      }

      ret = ret + irb.getIr_space();
    }

    irb = (IndexRoleBean)role_mp.get("D");
    System.out.println("isb.getSeq_ymd()----------" + isb.getSeq_ymd());
    String date = getSeqYMD(isb.getSeq_ymd(), irb.getIr_value());
    isb.setSeq_ymd(date);
    retMp.put("gk_year", date);
    if (irb.getIs_valid() == 1)
    {
      ret = ret + date + irb.getIr_space();
    }

    int sequence = 1;

    if (("".equals(seq)) || (seq == null)) {
      try
      {
        sequence = getSerialNum(irb.getIr_value(), isb);
      }
      catch (Exception e) {
        return null;
      }
    }
    else
    {
      sequence = Integer.valueOf(seq).intValue();
    }

    IndexRoleBean digitBean = (IndexRoleBean)role_mp.get("E");
    int digit = Integer.valueOf(digitBean.getIr_value()).intValue();

    ret = ret + FormatUtil.intToString(sequence, digit, "0");

    retMp.put("gk_num", sequence);
    retMp.put("gk_index", ret);
    return retMp;
  }

  private static boolean insertSeqence(IndexSequenceBean isb)
  {
    if (IndexSequenceDAO.insertSequence(isb))
    {
      return true;
    }

    return false;
  }

  private static boolean updateSequence(IndexSequenceBean isb)
  {
    return IndexSequenceDAO.updateSequence(isb);
  }

  public static boolean resetSequence(String nodeId, String catId, String ymd, SettingLogsBean stl)
  {
    IndexRoleBean roleBean = (IndexRoleBean)role_mp.get("F");

    IndexRoleBean irb = (IndexRoleBean)role_mp.get("D");

    IndexSequenceBean isb = new IndexSequenceBean();
    isb.setSeq_ymd(ymd);
    isb.setSite_id(nodeId);

    if (roleBean.getIr_value() == "100")
    {
      isb.setSeq_type(0);
      isb.setSeq_item(nodeId);
    }
    else
    {
      isb.setSeq_type(1);
      isb.setSeq_item(catId);
    }

    String date = getSeqYMD(isb.getSeq_ymd(), irb.getIr_value());
    isb.setSeq_ymd(date);

    return IndexSequenceDAO.resetSequence(isb, stl);
  }

  private static String getSeqYMD(String date, String pattern)
  {
    String ret = "";
    DateFormat df = new SimpleDateFormat(pattern);
    if (("".equals(date)) || (date == null))
    {
      ret = df.format(new Date());
    }
    else {
      DateFormat strToDate = new SimpleDateFormat("yyyy-MM");
      Date d;
      try {
        d = strToDate.parse(date);
      }
      catch (ParseException e)
      {
        d = new Date();
      }
      ret = df.format(d);
    }
    return ret;
  }

  private static IndexSequenceBean getSequenceBean(IndexSequenceBean isb)
  {
    return IndexSequenceDAO.getSequenceBean(isb);
  }

  private static int getSerialNum(String format, IndexSequenceBean isb)
    throws Exception
  {
    IndexSequenceBean resultBean = getSequenceBean(isb);
    if (resultBean == null)
    {
      isb.setSeq_cur_value(1);

      if (!insertSeqence(isb))
      {
        throw new Exception("新建流水号失败！");
      }
      resultBean = isb;
    }

    updateSequence(isb);
    return resultBean.getSeq_cur_value();
  }

  public static void main(String[] s)
  {
    System.out.println(getIndex("GKrmzf", "1835", "2011-01", ""));
    System.out.println("eee");
  }
}