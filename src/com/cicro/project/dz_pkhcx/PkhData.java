package com.cicro.project.dz_pkhcx;

import com.cicro.util.FormatUtil;
import com.cicro.wcm.bean.template.TurnPageBean;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PkhData
{
  private static int cur_page = 1;
  private static int page_size = 15;

  public static void getPkhSearchCon(String params, Map<String, String> con_map)
  {
    String orderby = "pxsj desc";
    String[] tempA = params.split(";");
    for (int i = 0; i < tempA.length; i++)
    {
      if (tempA[i].toLowerCase().startsWith("kw="))
      {
        String kw = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));

        if ((!"".equals(kw)) && (!kw.startsWith("$kw")) && (FormatUtil.isValiditySQL(kw)))
        {
          con_map.put("keyword", kw);
        }
      }
      if (tempA[i].toLowerCase().startsWith("hzxm="))
      {
        String hzxm = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));

        if ((!"".equals(hzxm)) && (!hzxm.startsWith("$hzxm")) && (FormatUtil.isValiditySQL(hzxm)))
        {
          con_map.put("hzxm", hzxm);
        }
      }
      if (tempA[i].toLowerCase().startsWith("hzzjhm="))
      {
        String hzzjhm = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));

        if ((!"".equals(hzzjhm)) && (!hzzjhm.startsWith("$hzzjhm")) && (FormatUtil.isValiditySQL(hzzjhm)))
        {
          con_map.put("hzzjhm", hzzjhm);
        }
      }
      if (tempA[i].toLowerCase().startsWith("nrjcsr="))
      {
        String nrjcsr = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));

        if ((!"".equals(nrjcsr)) && (!nrjcsr.startsWith("$nrjcsr")) && (FormatUtil.isValiditySQL(nrjcsr)))
        {
          con_map.put("nrjcsr", nrjcsr);
        }
      }
      if (tempA[i].toLowerCase().startsWith("hsx="))
      {
        String hsx = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));

        if ((!"".equals(hsx)) && (!hsx.startsWith("$hsx")) && (FormatUtil.isValiditySQL(hsx)))
        {
          con_map.put("hsx", hsx);
        }
      }
      if (tempA[i].toLowerCase().startsWith("zyzpyy="))
      {
        String zyzpyy = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));

        if ((!"".equals(zyzpyy)) && (!zyzpyy.startsWith("$zyzpyy")) && (FormatUtil.isValiditySQL(zyzpyy)))
        {
          con_map.put("zyzpyy", zyzpyy);
        }
      }
      if (tempA[i].toLowerCase().startsWith("tpbs="))
      {
        String tpbs = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));

        if ((!"".equals(tpbs)) && (!tpbs.startsWith("$tpbs")) && (FormatUtil.isValiditySQL(tpbs)))
        {
          con_map.put("tpbs", tpbs);
        }
      }
      if (tempA[i].toLowerCase().startsWith("lxdh="))
      {
        String lxdh = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));

        if ((!"".equals(lxdh)) && (!lxdh.startsWith("$lxdh")) && (FormatUtil.isValiditySQL(lxdh)))
        {
          con_map.put("lxdh", lxdh);
        }
      }
      if (tempA[i].toLowerCase().startsWith("khyh="))
      {
        String khyh = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));

        if ((!"".equals(khyh)) && (!khyh.startsWith("$khyh")) && (FormatUtil.isValiditySQL(khyh)))
        {
          con_map.put("khyh", khyh);
        }
      }
      if (tempA[i].toLowerCase().startsWith("yhzh="))
      {
        String yhzh = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));

        if ((!"".equals(yhzh)) && (!yhzh.startsWith("$yhzh")) && (FormatUtil.isValiditySQL(yhzh)))
        {
          con_map.put("yhzh", yhzh);
        }
      }
      if (tempA[i].toLowerCase().startsWith("sbbz="))
      {
        String sbbz = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));

        if ((!"".equals(sbbz)) && (!sbbz.startsWith("$sbbz")) && (FormatUtil.isValiditySQL(sbbz)))
        {
          con_map.put("sbbz", sbbz);
        }
      }
      if (tempA[i].toLowerCase().startsWith("sheng="))
      {
        String sheng = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));

        if ((!"".equals(sheng)) && (!sheng.startsWith("$sheng")) && (FormatUtil.isValiditySQL(sheng)))
        {
          con_map.put("sheng", sheng);
        }
      }
      if (tempA[i].toLowerCase().startsWith("shi="))
      {
        String shi = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));

        if ((!"".equals(shi)) && (!shi.startsWith("$shi")) && (FormatUtil.isValiditySQL(shi)))
        {
          con_map.put("shi", shi);
        }
      }
      if (tempA[i].toLowerCase().startsWith("quxian="))
      {
        String quxian = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));

        if ((!"".equals(quxian)) && (!quxian.startsWith("$quxian")) && (FormatUtil.isValiditySQL(quxian)))
        {
          con_map.put("quxian", quxian);
        }
      }
      if (tempA[i].toLowerCase().startsWith("xiangzhen="))
      {
        String xiangzhen = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));

        if ((!"".equals(xiangzhen)) && (!xiangzhen.startsWith("$xiangzhen")) && (FormatUtil.isValiditySQL(xiangzhen)))
        {
          con_map.put("xiangzhen", xiangzhen);
        }
      }
      if (tempA[i].toLowerCase().startsWith("xzc="))
      {
        String xzc = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));

        if ((!"".equals(xzc)) && (!xzc.startsWith("$xzc")) && (FormatUtil.isValiditySQL(xzc)))
        {
          con_map.put("xzc", xzc);
        }
      }
      if (tempA[i].toLowerCase().startsWith("zu="))
      {
        String zu = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));

        if ((!"".equals(zu)) && (!zu.startsWith("$zu")) && (FormatUtil.isValiditySQL(zu)))
        {
          con_map.put("zu", zu);
        }
      }
      if (tempA[i].toLowerCase().startsWith("lrr="))
      {
        String lrr = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));

        if ((!"".equals(lrr)) && (!lrr.startsWith("$lrr")) && (FormatUtil.isValiditySQL(lrr)))
        {
          con_map.put("lrr", lrr);
        }
      }
      if (tempA[i].toLowerCase().startsWith("lrsj="))
      {
        String lrsj = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));

        if ((!"".equals(lrsj)) && (!lrsj.startsWith("$lrsj")) && (FormatUtil.isValiditySQL(lrsj)))
        {
          con_map.put("lrsj", lrsj);
        }
      }
      if (tempA[i].toLowerCase().startsWith("shr="))
      {
        String shr = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));

        if ((!"".equals(shr)) && (!shr.startsWith("$shr")) && (FormatUtil.isValiditySQL(shr)))
        {
          con_map.put("shr", shr);
        }
      }
      if (tempA[i].toLowerCase().startsWith("shsj="))
      {
        String shsj = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));

        if ((!"".equals(shsj)) && (!shsj.startsWith("$shsj")) && (FormatUtil.isValiditySQL(shsj)))
        {
          con_map.put("shsj", shsj);
        }
      }
      if (tempA[i].toLowerCase().startsWith("orderby="))
      {
        String o_by = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));
        if ((!"".equals(o_by)) && (!o_by.startsWith("$orderby")) && (FormatUtil.isValiditySQL(o_by)))
        {
          orderby = o_by;
        }
      }
      if (tempA[i].toLowerCase().startsWith("size="))
      {
        String ps = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));
        if ((!"".equals(ps)) && (!ps.startsWith("$size")) && (FormatUtil.isNumeric(ps)))
          page_size = Integer.parseInt(ps);
      }
      if (tempA[i].toLowerCase().startsWith("cur_page="))
      {
        String cp = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));
        if ((!"".equals(cp)) && (!cp.startsWith("$cur_page")) && (FormatUtil.isNumeric(cp)))
          cur_page = Integer.parseInt(cp);
      }
    }
    con_map.put("page_size", page_size+"");
    con_map.put("current_page", cur_page+"");
    con_map.put("orderby", orderby);
    System.out.println(con_map);
  }

  public static TurnPageBean getPkhCount(String params)
  {
    Map con_map = new HashMap();
    getPkhSearchCon(params, con_map);

    TurnPageBean tpb = new TurnPageBean();
    tpb.setCount(Integer.parseInt(PkhManager.getPkhCount(con_map)));
    tpb.setCur_page(cur_page);
    tpb.setPage_size(page_size);
    tpb.setPage_count(tpb.getCount() / tpb.getPage_size() + 1);

    if ((tpb.getCount() % tpb.getPage_size() == 0) && (tpb.getPage_count() > 1)) {
      tpb.setPage_count(tpb.getPage_count() - 1);
    }
    if (cur_page > 1) {
      tpb.setPrev_num(cur_page - 1);
    }
    tpb.setNext_num(tpb.getPage_count());
    if (cur_page < tpb.getPage_count()) {
      tpb.setNext_num(cur_page + 1);
    }

    if (tpb.getPage_count() > 10)
    {
      if (cur_page > 5)
      {
        if (cur_page > tpb.getPage_count() - 4)
          tpb.setCurr_start_num(tpb.getPage_count() - 6);
        else
          tpb.setCurr_start_num(cur_page - 2);
      }
    }
    return tpb;
  }

  public static List<PkhBean> getPkhList(String params) {
    Map con_map = new HashMap();
    getPkhSearchCon(params, con_map);
    int start_page = Integer.parseInt((String)con_map.get("current_page"));
    int page_size = Integer.parseInt((String)con_map.get("page_size"));
    con_map.put("start_num", Integer.valueOf((start_page - 1) * page_size));
    con_map.put("page_size", Integer.valueOf(page_size));
    return PkhManager.getPkhList(con_map);
  }

  public static List<PkhBean> getAllPkhList(String params) {
    return PkhManager.getAllPkhList();
  }

  public static List<PkhBean> getPkhHotList(String params) {
    Map con_map = new HashMap();
    getPkhSearchCon(params, con_map);
    con_map.put("current_page", "1");
    int start_page = Integer.parseInt((String)con_map.get("current_page"));
    int page_size = Integer.parseInt((String)con_map.get("page_size"));
    con_map.put("start_num", Integer.valueOf((start_page - 1) * page_size));
    con_map.put("page_size", Integer.valueOf(page_size));
    return PkhManager.getPkhList(con_map);
  }

  public static PkhBean getPkhObject(String id)
  {
    return PkhManager.getPkhBean(id,true);
  }
}