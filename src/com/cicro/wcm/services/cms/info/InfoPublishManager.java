package com.cicro.wcm.services.cms.info;

import com.cicro.util.FormatUtil;
import com.cicro.util.io.FileOperation;
import com.cicro.util.jconfig.JconfigUtil;
import com.cicro.util.jconfig.JconfigUtilContainer;
import com.cicro.util.labelUtil.AutoPagerHandl;
import com.cicro.wcm.bean.cms.category.CategoryBean;
import com.cicro.wcm.bean.cms.info.InfoBean;
import com.cicro.wcm.dao.PublicTableDAO;
import com.cicro.wcm.db.DBManager;
import com.cicro.wcm.services.cms.category.CategoryManager;
import com.cicro.wcm.services.cms.category.CategoryModelManager;
import com.cicro.wcm.services.cms.category.CategoryUtil;
import com.cicro.wcm.services.control.site.SiteAppRele;
import com.cicro.wcm.services.control.site.SiteManager;
import com.cicro.wcm.services.search.SearchInnerManager;
import com.cicro.wcm.services.system.assist.HotWordManager;
import com.cicro.wcm.services.system.formodel.ModelManager;
import com.cicro.wcm.template.velocity.impl.VelocityInfoContextImp;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.beanutils.BeanUtils;

public class InfoPublishManager
{
  private static String rs = JconfigUtilContainer.bashConfig().getProperty("on_off", "", "staticPageList");

  public static void cancelAfterEvent(InfoBean ib)
  {
    try {
      deleteHtmlPage(ib);

      resetCategoryPage(ib.getCat_id(), ib.getSite_id());

      SearchInnerManager.infoSetDel(ib.getInfo_id()+"");
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }

  public static void cancelAfterEvent(List<InfoBean> l, Set<Integer> cat_ids, String site_id)
  {
    for (InfoBean ib : l) {
      try
      {
        deleteHtmlPage(ib);

        resetCategoryPage(cat_ids, site_id);

        SearchInnerManager.infoSetDel(ib.getInfo_id()+"");
      }
      catch (Exception e) {
        e.printStackTrace();
      }
    }

    if ("on".equals(rs))
      InfoExpandManager.CreateListPage(cat_ids);
  }

  public static void cancelInfoSearch(List<InfoBean> l)
  {
    for (InfoBean ib : l)
      try
      {
        SearchInnerManager.infoSetDel(ib.getInfo_id()+"");
      }
      catch (Exception e) {
        e.printStackTrace();
      }
  }

  public static void cancelInfoSearchAndPage(List<InfoBean> l)
  {
    for (InfoBean ib : l)
      try
      {
        deleteHtmlPage(ib);
        SearchInnerManager.infoSetDel(ib.getInfo_id()+"");
      }
      catch (Exception e) {
        e.printStackTrace();
      }
  }

  public static boolean publishAfterEvent(InfoBean info)
  {
    try
    {
      createContentHTML(info);

      resetCategoryPage(info.getCat_id(), info.getSite_id());
    }
    catch (IOException e) {
      e.printStackTrace();
    }

    InfoBaseManager.syncInfoToSite(info);

    if ("on".equals(rs))
    {
      CategoryBean catebean = CategoryManager.getCategoryBean(info.getCat_id());
      if (catebean != null) {
        InfoExpandManager.CreateListPage(catebean.getCat_id(), catebean.getApp_id(), catebean.getSite_id());
      }

    }

    return true;
  }

  public static boolean publishAfterEvent(List<InfoBean> l, Set<Integer> cat_ids, String site_id)
  {
    try
    {
      createContentHTML(l);

      resetCategoryPage(cat_ids, site_id);
    }
    catch (IOException e) {
      e.printStackTrace();
    }

    InfoBaseManager.syncInfoToSite(l);

    return true;
  }

  public static boolean createContentHTML(List<InfoBean> l)
    throws IOException
  {
    Set cat_ids = new HashSet();
    int index = 1;
    for (InfoBean ib : l) {
      try
      {
        System.out.println("总共需要生成" + l.size() + "条信息，当前第" + index + "条");
        createContentHTML(ib);
        cat_ids.add(Integer.valueOf(ib.getCat_id()));
        index++;
      }
      catch (Exception e) {
        e.printStackTrace();
        return false;
      }
    }

    if ("on".equals(rs)) {
      InfoExpandManager.CreateListPage(cat_ids);
    }
    return true;
  }

  public static boolean createContentHTML(InfoBean ib) throws IOException
  {
    try {
      if (ib != null)
      {
        String model_ename = ModelManager.getModelEName(ib.getModel_id());

        if ((ib.getInfo_status() == 8) && (!InfoBaseManager.LINK_MODEL_ENAME.equals(model_ename)))
        {
          String temp_site_id = "";
          if (!ib.getApp_id().equals("cms"))
          {
            temp_site_id = SiteAppRele.getSiteIDByAppID(ib.getApp_id());
          }
          else
            temp_site_id = ib.getSite_id();
          int cat_id = ib.getCat_id();
          int model_id = ib.getModel_id();
          String template_id = CategoryModelManager.getTemplateID(cat_id+"", ib.getSite_id(), model_id);

          if (((InfoBaseManager.ARTICLE_MODEL_ENAME.equals(model_ename)) || (InfoBaseManager.GKTYGS_MODEL_ENAME.equals(model_ename))) && (ib.getPage_count() > 0))
          {
            Object o = ModelUtil.select(ib.getInfo_id()+"", temp_site_id, model_ename);
            String item_name = "";
            if (InfoBaseManager.ARTICLE_MODEL_ENAME.equals(model_ename))
              item_name = "info_content";
            if (InfoBaseManager.GKTYGS_MODEL_ENAME.equals(model_ename))
              item_name = "gk_content";
            String content = BeanUtils.getProperty(o, item_name);
            String savePath = "";

            if (ib.getIs_am_tupage() == 1)
            {
              for (int i = 0; i < ib.getPage_count(); i++)
              {
                BeanUtils.setProperty(o, item_name, AutoPagerHandl.splitContent(content, i + 1, ib.getTupage_num()));
                VelocityInfoContextImp vici = new VelocityInfoContextImp(o, temp_site_id, template_id, i + 1);
                String vol_content = vici.parseTemplate();
                String content_url = ib.getContent_url();
                if (i > 0)
                  content_url = content_url.substring(0, content_url.indexOf(".htm")) + "_" + (i + 1) + ".htm";
                if ((content_url == "") || (content_url == null) || (content_url == "null") || ("".equals(content_url))) {
                  content_url = CategoryUtil.getFoldePathByCategoryID(ib.getCat_id(), ib.getApp_id(), ib.getSite_id());
                  content_url = content_url + ib.getInfo_id() + ".htm";

                  Map m = new HashMap();
                  m.put("sql", "update cs_info set content_url ='" + content_url + "' where info_id=" + ib.getInfo_id());
                  PublicTableDAO.createSql(m);
                }
                savePath = FormatUtil.formatPath(SiteManager.getSitePath(temp_site_id) + content_url);
                FileOperation.writeStringToFile(savePath, vol_content, false, "utf-8");
              }
            }
            else
            {
              content = HotWordManager.replaceContentHotWord(content);
              String[] tempA = content.split("<p class=\"ke-pageturning\">.*?</p>");

              for (int i = 0; i < tempA.length; i++)
              {
                BeanUtils.setProperty(o, item_name, tempA[i]);
                VelocityInfoContextImp vici = new VelocityInfoContextImp(o, temp_site_id, template_id, i + 1);
                String vol_content = vici.parseTemplate();
                String content_url = ib.getContent_url().trim();
                if (i > 0) {
                  content_url = content_url.substring(0, content_url.indexOf(".htm")) + "_" + (i + 1) + ".htm";
                }
                if ((content_url == "") || (content_url == null) || (content_url == "null") || ("".equals(content_url))) {
                  content_url = CategoryUtil.getFoldePathByCategoryID(ib.getCat_id(), ib.getApp_id(), ib.getSite_id());
                  content_url = content_url + ib.getInfo_id() + ".htm";

                  Map m = new HashMap();
                  m.put("sql", "update cs_info set content_url ='" + content_url + "' where info_id=" + ib.getInfo_id());
                  PublicTableDAO.createSql(m);
                }

                savePath = FormatUtil.formatPath(SiteManager.getSitePath(temp_site_id) + content_url);
                FileOperation.writeStringToFile(savePath, vol_content, false, "utf-8");
              }
            }
          }
          else
          {
            String content_url = ib.getContent_url();
            if ((content_url == "") || (content_url == null) || (content_url == "null") || ("".equals(content_url))) {
              content_url = CategoryUtil.getFoldePathByCategoryID(ib.getCat_id(), ib.getApp_id(), ib.getSite_id());
              content_url = content_url + ib.getInfo_id() + ".htm";
              Map m = new HashMap();
              m.put("sql", "update cs_info set content_url ='" + content_url + "' where info_id=" + ib.getInfo_id());
              PublicTableDAO.createSql(m);
            }
            VelocityInfoContextImp vici = new VelocityInfoContextImp(ib.getInfo_id()+"", temp_site_id, template_id, model_ename);
            String content = vici.parseTemplate();
            String savePath = FormatUtil.formatPath(SiteManager.getSitePath(temp_site_id) + content_url);
            FileOperation.writeStringToFile(savePath, content, false, "utf-8");
          }
        }
      }

      SearchInnerManager.infoSetAdd(ib.getInfo_id()+"");
    }
    catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    return true;
  }

  public static void resetCategoryPage(int cat_id, String site_id)
  {
    CategoryBean cb = CategoryManager.getCategoryBeanCatID(cat_id, site_id);
    if (cb != null)
    {
      resetCategoryPageHandl(cb, site_id);
    }
  }

  public static void resetCategoryPage(Set<Integer> cat_ids, String site_id)
  {
    if (cat_ids != null)
    {
      Iterator it = cat_ids.iterator();
      while (it.hasNext())
        resetCategoryPage(((Integer)it.next()).intValue(), site_id);
    }
  }

  public static void resetCategoryPageHandl(CategoryBean cb, String site_id)
  {
    String[] tempA = cb.getCat_position().split("\\$");
    for (int i = 2; i < tempA.length; i++)
    {
      if ((tempA[i] != null) && (!"".equals(tempA[i])))
      {
        CategoryBean bean = CategoryManager.getCategoryBeanCatID(Integer.parseInt(tempA[i]), site_id);
        if (bean != null)
        {
          if ((bean.getIs_generate_index() == 1) && ("cms".equals(bean.getApp_id())))
          {
            createCategoryIndexPage(bean, site_id);
          }
        }
      }
    }
  }

  public static boolean createCategoryIndexPage(CategoryBean cb, String site_id)
  {
    int template_id = cb.getTemplate_index();
    if (template_id == 0)
    {
      String root_tree_id = cb.getCat_position().split("\\$")[2];
      CategoryBean root_cb = CategoryManager.getCategoryBeanCatID(Integer.parseInt(root_tree_id), site_id);

      if (root_cb.getCat_type() == 1)
      {
        template_id = root_cb.getTemplate_index();
      }
    }
    VelocityInfoContextImp v_index = new VelocityInfoContextImp(cb.getCat_id(), site_id, template_id+"");
    String html = v_index.parseTemplate();
    String savePath = FormatUtil.formatPath(SiteManager.getSitePath(site_id) + CategoryUtil.getFoldePathByCategoryID(cb.getCat_id(), "cms", site_id) + "index.html");
    try {
      FileOperation.writeStringToFile(savePath, html, false, "utf-8");
    }
    catch (IOException e) {
      e.printStackTrace();
    }
    return true;
  }

  public static void deleteHtmlPage(InfoBean ib)
  {
    int info_id = ib.getInfo_id();
    int cat_id = ib.getCat_id();
    String app_id = ib.getApp_id();
    String site_id = ib.getSite_id();
    if (!ib.getApp_id().equals("cms"))
    {
      site_id = SiteAppRele.getSiteIDByAppID(app_id);
    }
    String savePath = FormatUtil.formatPath(SiteManager.getSitePath(site_id) + CategoryUtil.getFoldePathByCategoryID(cat_id, app_id, ib.getSite_id()) + info_id);

    if (ib.getPage_count() > 1)
    {
      for (int i = 0; i < ib.getPage_count(); i++)
      {
        String temp_path = "";
        if (i > 0)
        {
          temp_path = savePath + "_" + (i + 1) + ".htm";
        }
        else
        {
          temp_path = savePath + ".htm";
        }
        deleteHtmlPageHandl(temp_path);
      }
    }
    else
    {
      savePath = savePath + ".htm";
      deleteHtmlPageHandl(savePath);
    }
  }

  public static void deleteHtmlPageHandl(String savePath)
  {
    File f = new File(savePath);
    if (f.exists())
      f.delete();
  }

  public static List<InfoBean> getAllInfoForUpdateUrl(Map<String, String> m)
  {
    return DBManager.queryFList("getAllInfoForUpdateUrl", m);
  }

  public static void main(String[] args)
  {
    String content_url = "/bendigk/1072.htm";
    content_url = content_url.substring(0, content_url.indexOf(".htm")) + "_1.htm";
    System.out.println(content_url);
  }
}