package com.cicro.wcm.services.system.template;

import com.cicro.util.FormatUtil;
import com.cicro.util.jconfig.JconfigFactory;
import com.cicro.util.jconfig.JconfigUtil;
import com.cicro.wcm.bean.system.template.TemplateCategoryBean;
import com.cicro.wcm.bean.system.template.TemplateEditBean;
import com.cicro.wcm.catchs.ISyncCatch;
import com.cicro.wcm.catchs.SyncCatchHandl;
import com.cicro.wcm.services.control.site.SiteManager;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TemplateUtils
  implements ISyncCatch
{
  private static Map<String, String> templatePathMap = new HashMap();
  private static String template_default_path = JconfigFactory.getJconfigUtilInstance("velocityTemplate").getProperty("path", "", "template_default_path");

  static {
    reloadCatchHandl();
  }

  public void reloadCatch()
  {
    reloadCatchHandl();
  }

  public static void reloadCatchHandl() {
    List<TemplateEditBean> tebs = TemplateEditManager.getAllTemplateEditList();
    templatePathMap.clear();
    if (tebs.size() > 0)
      for (TemplateEditBean teb : tebs) {
        TemplateCategoryBean tcb = TemplateCategoryManager.getTemplateCategoryBean(teb.getTcat_id(), teb.getSite_id(), "");
        if (tcb == null) {
          System.out.println("ERROR:此模板没有所属分类。。。。。。tid=" + teb.getTcat_id());
          templatePathMap.put(teb.getT_id() + "_" + teb.getSite_id(), FormatUtil.formatPath(new StringBuilder(String.valueOf(template_default_path)).append("/").append(teb.getSite_id()).toString(), true) + teb.getT_id() + "_" + teb.getT_ename() + ".vm");
        }
        else {
          templatePathMap.put(teb.getT_id() + "_" + teb.getSite_id(), FormatUtil.formatPath(new StringBuilder(String.valueOf(SiteManager.getSiteTempletPath(teb.getSite_id()))).append("/").append(tcb.getTcat_position()).toString(), true) + teb.getT_id() + "_" + teb.getT_ename() + ".vm");
        }
      }
  }

  public static void initTemplatePathMap() {
    SyncCatchHandl.reladCatchForRMI("com.cicro.wcm.services.system.template.TemplateUtils");
  }

  public static String getTemplatePath(String key) {
    return (String)templatePathMap.get(key);
  }

  public static String getTemplatePth(String key) {
    return getTemplatePath(key);
  }

  public static void setTemplatePath(String key, String value) {
    templatePathMap.remove(key);
    templatePathMap.put(key, value);
  }

  public static void setTemplatePath(TemplateEditBean teb)
  {
    TemplateCategoryBean tcb = TemplateCategoryManager.getTemplateCategoryBean(teb.getTcat_id(), teb.getSite_id(), "");
    templatePathMap.put(teb.getT_id() + "_" + teb.getSite_id(), FormatUtil.formatPath(new StringBuilder(String.valueOf(SiteManager.getSiteTempletPath(teb.getSite_id()))).append("/").append(tcb.getTcat_position()).toString(), true) + teb.getT_id() + "_" + teb.getT_ename() + ".vm");
  }

  public static boolean delTemplatePath(String key) {
    templatePathMap.remove(key);
    return true;
  }

  public static String formatSymbolString(String str, String symbol) {
    if (str == null) {
      return "";
    }
    if (str.trim().startsWith(symbol)) {
      str = str.substring(1);
    }
    if (str.trim().endsWith(symbol)) {
      str = str.substring(0, str.length() - 1);
    }
    return str;
  }

  public static void mkDirectory(File f)
  {
    if (!f.exists())
      if (f.getPath().toLowerCase().endsWith(".vm")) {
        f.getParentFile().mkdirs();
        try {
          f.createNewFile();
        } catch (IOException e) {
          e.printStackTrace();
        }
      } else {
        f.mkdirs();
      }
  }

  public static void showMap()
  {
	  /*
    System.out.println("templatePathMap start****************************************************************************");
    System.out.println(templatePathMap);
    System.out.println("templatePathMap end******************************************************************************");
    */
  }
  public static void main(String[] args) throws Exception {
    System.out.println(templatePathMap);
  }
}