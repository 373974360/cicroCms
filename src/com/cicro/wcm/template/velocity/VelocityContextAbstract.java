package com.cicro.wcm.template.velocity;

import com.cicro.util.FormatUtil;
import com.cicro.util.io.FileOperation;
import com.cicro.util.ip.Utils;
import com.cicro.util.jconfig.JconfigFactory;
import com.cicro.util.jconfig.JconfigUtil;
import com.cicro.wcm.bean.template.ClientIPBean;
import com.cicro.wcm.server.ServerManager;
import com.cicro.wcm.services.control.domain.SiteDomainManager;
import com.cicro.wcm.services.control.site.SiteManager;
import com.cicro.wcm.services.system.template.TemplateUtils;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

public abstract class VelocityContextAbstract
{
  private static Map<String, Object> map = new HashMap();

  protected String template_id = "";
  protected String site_id = "";

  protected VelocityContext vcontext = new VelocityContext();

  static
  {
    try
    {
      JconfigUtil jc = JconfigFactory.getJconfigUtilInstance("velocityTemplate");
      String[] classes = jc.getPropertyNamesByCategory("LoaderTemplateContextClass");
      for (String name : classes)
        try {
          map.put(name, Class.forName(jc.getProperty(name, null, "LoaderTemplateContextClass")));
        } catch (ClassNotFoundException e) {
          e.printStackTrace();
        }
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

  public VelocityContextAbstract()
  {
  }

  public VelocityContextAbstract(HttpServletRequest request)
  {
    inputParam(request);
  }

  public void inputParam(HttpServletRequest request)
  {
    this.vcontext.put("v_session", request.getSession());
    this.vcontext.put("v_request", request);
    ClientIPBean cip = new ClientIPBean();
    String ip = FormatUtil.getIpAddr(request);
    cip.setIp(ip);
    cip.setContrey(Utils.getCountry(ip));
    cip.setArea(Utils.getArea(ip));
    this.vcontext.put("ClientIP", cip);

    this.site_id = SiteDomainManager.getSiteIDByUrl(request.getRequestURL().toString());

    String params = FormatUtil.getParameterStrInRequest(request);
    try
    {
      params = new String(params.getBytes("ISO-8859-1"), "UTF-8");
    }
    catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }

    System.out.println("params---------" + params);

    if ((params != null) && (!"".equals(params)) && (FormatUtil.isValiditySQL(params)))
    {
      String[] tempA = params.split("&");
      for (int i = 0; i < tempA.length; i++)
      {
        int index_num = tempA[i].indexOf("=");
        String vals = tempA[i].substring(index_num + 1);
        if (FormatUtil.isNumeric(vals)) {
          try
          {
            this.vcontext.put(tempA[i].substring(0, index_num), Integer.valueOf(Integer.parseInt(vals)));
          }
          catch (NumberFormatException n) {
            n.printStackTrace();
            this.vcontext.put(tempA[i].substring(0, index_num), vals);
          }
        }
        else
          this.vcontext.put(tempA[i].substring(0, index_num), vals);
      }
      if (params.indexOf("cur_page") == -1) {
        this.vcontext.put("cur_page", Integer.valueOf(1));
      }
      this.vcontext.put("requet_params", params);
    }
  }

  public String parseTemplate()
  {
    try
    {
      if ((this.vcontext.get("site_id") == null) || ("".equals(this.vcontext.get("site_id"))) || ("null".equals(this.vcontext.get("site_id")))) {
        this.vcontext.put("site_id", this.site_id);
      }
      Template template = VelocityEngineInstance.getInstance().getTemplate(getTemplateFilePath());
      Writer writer = new StringWriter();
      loadClassContext();
      template.merge(this.vcontext, writer);

      return writer.toString();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "";
  }

  public String parseTemplate(String ware_content)
  {
    try
    {
      StringWriter w = new StringWriter();
      loadClassContext();
      VelocityEngineInstance.getInstance().evaluate(this.vcontext, w, "", ware_content);
      return w.toString();
    } catch (Exception e) {
      e.printStackTrace();
      return "<div style=\"display:none\">\n" + e.getLocalizedMessage() + "\n</div>";
    }
  }

  public void loadClassContext()
  {
    Set<String> keys = map.keySet();
    for (String key : keys)
      this.vcontext.put(key, map.get(key));
  }

  public void setCcontext(String key, Object o)
  {
    this.vcontext.put(key, o);
  }

  public String getTemplateFilePath()
  {
    String path = TemplateUtils.getTemplatePth(this.template_id + "_" + this.site_id);
    //System.out.println("getTemplateFilePath----" + this.template_id + "_" + this.site_id + "------" + path);
    if (ServerManager.isWindows()) {
      return path.substring(path.indexOf("\\vhost") + 8);
    }
    return path.substring(path.indexOf("/vhost") + 8);
  }

  public boolean save(String content)
  {
    String t_root_path = SiteManager.getSiteTempletPath(this.site_id);
    String templateFile = TemplateUtils.getTemplatePth(this.template_id + "_" + this.site_id);
    String filePath = FormatUtil.formatPath(t_root_path + "/" + templateFile, false);
    try {
      //System.out.println("filePath = save template file path ====================================" + templateFile);
      File f = new File(templateFile);
      TemplateUtils.mkDirectory(f);
      FileOperation.writeStringToFile(f, content, false);
      return true;
    } catch (IOException e) {
      e.printStackTrace();
    }

    return false;
  }

  public String getTemplate_id() {
    return this.template_id;
  }

  public String getSite_id() {
    return this.site_id;
  }

  public void setTemplate_id(String templateId)
  {
    this.template_id = templateId;
  }

  public void setSite_id(String siteId) {
    this.site_id = siteId;
  }
}