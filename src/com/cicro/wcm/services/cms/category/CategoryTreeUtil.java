package com.cicro.wcm.services.cms.category;

import com.cicro.util.DateUtil;
import com.cicro.util.FormatUtil;
import com.cicro.util.jconfig.JconfigUtil;
import com.cicro.util.jconfig.JconfigUtilContainer;
import com.cicro.wcm.bean.cms.category.CategoryBean;
import com.cicro.wcm.bean.control.SiteBean;
import com.cicro.wcm.bean.zwgk.node.GKNodeBean;
import com.cicro.wcm.services.control.site.SiteManager;
import com.cicro.wcm.services.zwgk.node.GKNodeManager;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CategoryTreeUtil
{
  public static HashMap<String, String> uset_cate_map = new HashMap();
  private static String article_page_path = JconfigUtilContainer.managerPagePath().getProperty("article_page", "", "m_org_path");
  private static String fw_page_path = JconfigUtilContainer.managerPagePath().getProperty("fw_page", "", "m_org_path");

  public static void reloadMap()
  {
    uset_cate_map.clear();
  }

  public static String getInfoCategoryTreeByUserID(String site_id, int uesr_id, int cat_type)
  {
    String key = uesr_id + "_" + site_id + "_" + cat_type;
    if (uset_cate_map.containsKey(key))
    {
      return (String)uset_cate_map.get(key);
    }

    String json_str = "";
    String roo_name = "";
    if (cat_type == 2) {
      roo_name = "服务目录";
    }
    else if (site_id.startsWith("GK"))
    {
      roo_name = GKNodeManager.getNodeNameByNodeID(site_id);
    }
    else {
      SiteBean sb = SiteManager.getSiteBeanBySiteID(site_id);
      roo_name = sb.getSite_name();
      if (CategoryManager.haveCategoryManagementAuthority(CategoryManager.ROOT_ID, site_id, uesr_id))
      {
        json_str = getCategoryTreeBySiteID(site_id, 0);
        uset_cate_map.put(key, json_str);
        return json_str;
      }

    }

    json_str = "[{\"id\":0,\"text\":\"" + roo_name + "\",\"attributes\":{\"url\":\"\",\"handl\":\"\"}";
    List list = CategoryManager.getCategoryListBySiteID(site_id, cat_type);
    System.out.println(list.size());
    if ((list != null) && (list.size() > 0))
    {
      json_str = json_str + ",\"children\":[";
      for (int i = 0; i < list.size(); i++)
      {
        String str = getCategoryTreeByUser(((CategoryBean)list.get(i)).getCat_id(), site_id, uesr_id);

        if ((str != null) && (!"".equals(str)))
        {
          if (json_str.endsWith("}"))
            json_str = json_str + ",";
          json_str = json_str + str;
        }
      }
      json_str = json_str + "]";
    }
    json_str = json_str + "}]";

    uset_cate_map.put(key, json_str);
    return json_str;
  }

  public static String getInfoCategoryTreeByUserID(String site_id, int cat_type)
  {
    String json_str = "";
    String roo_name = "";
    if (cat_type == 2) {
      roo_name = "服务目录";
    }
    else if (site_id.startsWith("GK"))
    {
      GKNodeBean gnb = GKNodeManager.getGKNodeBeanByNodeID(site_id);
      roo_name = gnb.getNode_name();
    }
    else {
      SiteBean sb = SiteManager.getSiteBeanBySiteID(site_id);
      roo_name = sb.getSite_name();
    }

    json_str = "[";
    List list = CategoryManager.getCategoryListBySiteID(site_id, cat_type);

    if ((list != null) && (list.size() > 0))
    {
      for (int i = 0; i < list.size(); i++)
      {
        String str = getCategoryTreeByCategoryID(((CategoryBean)list.get(i)).getCat_id(), site_id);
        if ((str != null) && (!"".equals(str)))
        {
          str = str.substring(1, str.length() - 1);
          if (json_str.endsWith("}"))
            json_str = json_str + ",";
          json_str = json_str + str;
        }
      }
    }
    json_str = json_str + "]";

    return json_str;
  }

  public static String getCategoryTreeBySiteID(String site_id, int cat_type)
  {
    String json_str = "";
    if (site_id.startsWith("GK"))
    {
      GKNodeBean gnb = GKNodeManager.getGKNodeBeanByNodeID(site_id);
      if (gnb != null)
      {
        json_str = "[{\"id\":0,\"text\":\"" + gnb.getNode_name() + "\",\"attributes\":{\"url\":\"\",\"handl\":\"\"}";
      }
    }
    else
    {
      SiteBean sb = SiteManager.getSiteBeanBySiteID(site_id);
      if (sb != null)
      {
        json_str = "[{\"id\":0,\"text\":\"" + sb.getSite_name() + "\",\"attributes\":{\"url\":\"\",\"handl\":\"\"}";
      }
    }
    List list = CategoryManager.getCategoryListBySiteID(site_id, cat_type);
    if ((list != null) && (list.size() > 0))
    {
      json_str = json_str + ",\"children\":[" + getCategoryTreeJsonStrHandl(list) + "]";
    }
    json_str = json_str + "}]";
    return json_str;
  }

  public static String getCategoryTreeByClassID(int class_id)
  {
    CategoryBean cbg = CategoryManager.getCategoryBeanByClassID(class_id);
    if (cbg != null)
    {
      return getCategoryTreeByCategory(cbg);
    }
    return "[]";
  }

  public static String getCategoryTreeByCategoryID(int cat_id, String site_id)
  {
    CategoryBean cbg = CategoryManager.getCategoryBeanCatID(cat_id, site_id);

    if ((cbg != null) && (cbg.getIs_archive() == 0))
    {
      return getCategoryTreeByCategory(cbg);
    }
    return "[]";
  }

  public static String getCategoryTreeByUser(int cat_id, String site_id, int user_id)
  {
    String str = "";
    if (CategoryManager.haveCategoryManagementAuthority(cat_id, site_id, user_id))
    {
      str = getCategoryTreeByCategoryID(cat_id, site_id);
      if ((str != null) && (!"".equals(str)))
        str = str.substring(1, str.length() - 1);
    }
    else
    {
      str = getCategoryTreeByUserHandl(cat_id, site_id, user_id);
    }

    return str;
  }

  public static String getCategoryTreeByUserHandl(int cat_id, String site_id, int user_id)
  {
    Set s = new HashSet();
    List l = CategoryManager.getAllChildCategoryList(cat_id, site_id);
    if ((l != null) && (l.size() > 0))
    {
      for (int i = 0; i < l.size(); i++)
      {
        if ((((CategoryBean)l.get(i)).getIs_archive() == 0) && (CategoryReleManager.isCategoryManagerByUser(user_id, ((CategoryBean)l.get(i)).getSite_id(), ((CategoryBean)l.get(i)).getCat_id())))
        {
          s.add((CategoryBean)l.get(i));

          CategoryManager.setCategoryByPosition(s, ((CategoryBean)l.get(i)).getCat_position(), ((CategoryBean)l.get(i)).getSite_id());
        }
      }
      StringBuffer sb = new StringBuffer();
      getCategoryTreeStrBySet(s, cat_id, site_id, user_id, sb);
      String temp_str = sb.toString();
      if (temp_str.endsWith(","))
        temp_str = temp_str.substring(0, temp_str.length() - 1);
      return temp_str;
    }

    return "";
  }

  public static void getCategoryTreeStrBySet(Set<CategoryBean> set, int cat_id, String site_id, int user_id, StringBuffer sb)
  {
    if ((set != null) && (set.size() > 0))
    {
      CategoryBean cbg = CategoryManager.getCategoryBeanCatID(cat_id, site_id);
      String manager_page = article_page_path;
      if ("ggfw".equals(cbg.getApp_id()))
        manager_page = fw_page_path;
      if (cbg.getIs_archive() == 0)
      {
        if (sb.toString().endsWith("}")) {
          sb.append(",");
        }
        if (CategoryManager.isHasChildNode(cat_id, site_id))
        {
          if (CategoryReleManager.isCategoryManagerByUser(user_id, site_id, cat_id))
          {
            String str = getCategoryTreeByCategoryID(cat_id, site_id);
            if ((str != null) && (!"".equals(str)))
              str = str.substring(1, str.length() - 1);
            sb.append(str);
          } else {
            sb.append("{\"id\":" + cbg.getCat_id() + ",\"text\":\"" + FormatUtil.formatJsonStr(cbg.getCat_cname()) + "\",\"attributes\":{\"url\":\"" + 
              manager_page + "?app_id=" + cbg.getApp_id() + "&cat_id=" + cbg.getCat_id() + "\",\"handl\":\"\"}");
            sb.append(",\"children\":[");

            List child_list = CategoryManager.getChildCategoryList(cbg.getCat_id(), site_id);
            for (int i = 0; i < child_list.size(); i++)
            {
              if (set.contains(child_list.get(i)))
              {
                getCategoryTreeStrBySet(set, ((CategoryBean)child_list.get(i)).getCat_id(), site_id, user_id, sb);
              }
            }
            sb.append("]}");
          }
        }
        else sb.append("{\"id\":" + cbg.getCat_id() + ",\"text\":\"" + FormatUtil.formatJsonStr(cbg.getCat_cname()) + "\",\"attributes\":{\"url\":\"" + 
            manager_page + "?app_id=" + cbg.getApp_id() + "&cat_id=" + cbg.getCat_id() + "\",\"handl\":\"\"}}");
      }
    }
  }

  public static String getCategoryTreeByCategory(CategoryBean cbg)
  {
    String json_str = "";
    try {
      String manager_page = article_page_path;
      if ("ggfw".equals(cbg.getApp_id())) {
        manager_page = fw_page_path;
      }
      List child_list = CategoryManager.getChildCategoryList(cbg.getCat_id(), cbg.getSite_id());
      json_str = "[{\"id\":" + cbg.getCat_id() + ",\"text\":\"" + FormatUtil.formatJsonStr(cbg.getCat_cname()) + "\",\"attributes\":{\"url\":\"" + 
        manager_page + "?app_id=" + cbg.getApp_id() + "&cat_id=" + cbg.getCat_id() + "\",\"handl\":\"\"}";
      if ((child_list != null) && (child_list.size() > 0))
      {
        json_str = json_str + ",\"state\":'closed',\"children\":[" + getCategoryTreeJsonStrHandl(child_list) + "]";
      }

      json_str = json_str + "}]";
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    return json_str;
  }

  public static String getCategoryTreeJsonStrHandl(List<CategoryBean> child_list)
  {
    String json_str = "";
    if ((child_list != null) && (child_list.size() > 0))
    {
      String manager_page = article_page_path;
      if ("ggfw".equals(((CategoryBean)child_list.get(0)).getApp_id())) {
        manager_page = fw_page_path;
      }
      for (int i = 0; i < child_list.size(); i++)
      {
        json_str = json_str + "{";
        List child_m_list = CategoryManager.getChildCategoryList(((CategoryBean)child_list.get(i)).getCat_id(), ((CategoryBean)child_list.get(i)).getSite_id());
        if ((child_m_list != null) && (child_m_list.size() > 0))
        {
          json_str = json_str + "\"id\":" + ((CategoryBean)child_list.get(i)).getCat_id() + ",\"text\":\"" + FormatUtil.formatJsonStr(((CategoryBean)child_list.get(i)).getCat_cname()) + "\",\"attributes\":{\"url\":\"" + 
            manager_page + "?app_id=" + ((CategoryBean)child_list.get(i)).getApp_id() + "&cat_id=" + ((CategoryBean)child_list.get(i)).getCat_id() + "\",\"handl\":\"\"}";
          json_str = json_str + ",\"state\":'closed'";
          json_str = json_str + ",\"children\":[" + getCategoryTreeJsonStrHandl(child_m_list) + "]";
        } else {
          json_str = json_str + "\"id\":" + ((CategoryBean)child_list.get(i)).getCat_id() + ",\"text\":\"" + FormatUtil.formatJsonStr(((CategoryBean)child_list.get(i)).getCat_cname()) + "\",\"attributes\":{\"url\":\"" + 
            manager_page + "?app_id=" + ((CategoryBean)child_list.get(i)).getApp_id() + "&cat_id=" + ((CategoryBean)child_list.get(i)).getCat_id() + "\",\"handl\":\"\"}";
        }json_str = json_str + "}";
        if (i + 1 != child_list.size())
          json_str = json_str + ",";
      }
    }
    return json_str;
  }

  public static String getZTCategoryTreeStr(int zt_cat_id, String site_id)
  {
    String str = "";

    List l = CategoryManager.getZTCategoryListBySiteAndType(zt_cat_id, site_id);
    if ((l != null) && (l.size() > 0))
    {
      for (int i = 0; i < l.size(); i++)
      {
        String temp_str = getCategoryTreeByCategoryID(((CategoryBean)l.get(i)).getCat_id(), site_id);

        if ((temp_str != null) && (!"".equals(temp_str)))
        {
          temp_str = temp_str.substring(1, temp_str.length() - 1);
          str = str + "," + temp_str;
        }
      }
      if (str.length() > 0)
        str = str.substring(1);
      return str;
    }

    return "";
  }

  public static String getZTCategoryTreeStr(int zt_cat_id, String site_id, int user_id)
  {
    String str = "";

    List l = CategoryManager.getZTCategoryListBySiteAndType(zt_cat_id, site_id);
    if ((l != null) && (l.size() > 0))
    {
      for (int i = 0; i < l.size(); i++)
      {
        String temp_str = getCategoryTreeByUser(((CategoryBean)l.get(i)).getCat_id(), site_id, user_id);
        if ((temp_str != null) && (!"".equals(temp_str)))
          str = str + "," + temp_str;
      }
      if (str.length() > 0)
        str = str.substring(1);
      return str;
    }

    return "";
  }

  public static String getAllFWTreeJSONStr()
  {
    List l = CategoryManager.getCategoryListBySiteID("ggfw", 2);
    if ((l != null) && (l.size() > 0))
    {
      return "[" + getCategoryTreeJsonStrHandl(l) + "]";
    }

    return "";
  }
  
  /*************************************栏目异步树结构**************************************************/
	
	/**
   * 根据站点ID,人员ID得到有权限管理的目录树
   * @param String site_id
   * @param int user_id
   * @param int cat_type 目录类型
   * @return String
   * */
	public static String getInfoCategoryTreeByUserIDSync(String site_id,int uesr_id,int pid)
	{
		String key = uesr_id+"_"+site_id+"_p"+pid;		
		if(uset_cate_map.containsKey(key))
		{
			return uset_cate_map.get(key);
		}else
		{
			String json_str = "";
			String roo_name = "";
			SiteBean sb = SiteManager.getSiteBeanBySiteID(site_id);
			if(sb == null)
			{
				GKNodeBean gnb = GKNodeManager.getGKNodeBeanByNodeID(site_id);
				roo_name = gnb.getNode_name();
			}
			else
			{
				roo_name = sb.getSite_name();
			}
			List<CategoryBean> list = CategoryManager.getCategoryListBySiteIDPid(site_id,pid);
			if(pid == 0)
			{
				json_str = "[{\"id\":0,\"text\":\""+roo_name+"\",\"attributes\":{\"url\":\"\",\"handl\":\"\"}";
				
				if(list != null && list.size() > 0)
				{
					json_str += ",\"children\":[";
					for(int i=0;i<list.size();i++)
					{
						String str = isCategoryOprate(list.get(i).getCat_id(),site_id,uesr_id);
						
						if(str != null && !"".equals(str))
						{
							if(json_str.endsWith("}"))
								json_str += ",";
							json_str += str;
						}
					}
					json_str +=	""+"]";
				}
				json_str += "}]";
			}
			else
			{
				json_str = "[";
				if(list != null && list.size() > 0)
				{
					for(int i=0;i<list.size();i++)
					{
						String str = isCategoryOprate(list.get(i).getCat_id(),site_id,uesr_id);
						
						if(str != null && !"".equals(str))
						{
							if(json_str.endsWith("}"))
								json_str += ",";
							json_str += str;
						}
					}
				}
				json_str +=	""+"]";
			}
			
			uset_cate_map.put(key, json_str);			
			return json_str;
		}
	}
	
	/**
   * 根据cat_id，site_id和user_id得到它能管理的目录节点树
   * @param int cat_id
   * @param String site_id
   * @param int user_id
   * @return CategoryBean
   * */
	public static String isCategoryOprate(int cat_id,String site_id,int user_id)
	{
		String json_str = "";
		CategoryBean cbg = CategoryManager.getCategoryBeanCatID(cat_id,site_id);
		String manager_page = article_page_path;
		//判断该用户是否有此枝节点的管理权限，如果有显示出来并且添加到tree
		if(CategoryManager.haveCategoryManagementAuthority(cat_id,site_id,user_id))
		{
			if(CategoryManager.isHasChildNode(cat_id,site_id))
			{
				json_str = "{\"id\":"+cbg.getCat_id()+",\"text\":\""+FormatUtil.formatJsonStr(cbg.getCat_cname())+"\",\"state\":\"closed\",\"attributes\":{\"url\":\""
						+manager_page+"?app_id="+cbg.getApp_id()+"&cat_id="+cbg.getCat_id()+"\",\"handl\":\"\"}}";
			}
			else
			{
				json_str = "{\"id\":"+cbg.getCat_id()+",\"text\":\""+FormatUtil.formatJsonStr(cbg.getCat_cname())+"\",\"attributes\":{\"url\":\""
						+manager_page+"?app_id="+cbg.getApp_id()+"&cat_id="+cbg.getCat_id()+"\",\"handl\":\"\"}}";
			}
		}
		else
		{
			if(CategoryReleManager.isCategoryManagerByUser(user_id,site_id,cat_id))
			{
				if(CategoryManager.isHasChildNode(cat_id,site_id))
				{
					json_str = "{\"id\":"+cbg.getCat_id()+",\"text\":\""+FormatUtil.formatJsonStr(cbg.getCat_cname())+"\",\"state\":\"closed\",\"attributes\":{\"url\":\""
							+manager_page+"?app_id="+cbg.getApp_id()+"&cat_id="+cbg.getCat_id()+"\",\"handl\":\"\"}}";
				}
				else
				{
					json_str = "{\"id\":"+cbg.getCat_id()+",\"text\":\""+FormatUtil.formatJsonStr(cbg.getCat_cname())+"\",\"attributes\":{\"url\":\""
							+manager_page+"?app_id="+cbg.getApp_id()+"&cat_id="+cbg.getCat_id()+"\",\"handl\":\"\"}}";
				}
			}
		}
		return json_str;
	}
	
	/*************************************栏目异步树结构**************************************************/

  public static void main(String[] args)
  {
	  String all_position = "$0$1485$,$0$1627$,$0$1812$,$0$1315$1330$,$0$1492$,$0$1679$,$0$1608$,$0$1683$,$0$14987$,$0$1604$,$0$1681$,$0$1684$,$0$1696$,$0$1578$,$0$1680$,$0$1682$,$0$1687$,$0$1704$,$0$1608$1610$1616$,$0$1634$,$0$1685$,$0$1697$,$0$1763$,$0$1773$,$0$1782$,$0$2158$6946$,$0$7014$,$0$7018$,$0$1315$,$0$1332$,$0$1491$,$0$1504$,$0$1508$,$0$7016$,$0$7020$,$0$7021$,$0$7022$,$0$7023$,$0$7026$,$0$7032$,$0$998$,$0$14893$,$0$14882$,$0$14892$,$0$14944$,$0$7019$,$0$997$,$0$3314$,$0$2158$,$0$996$,$0$7027$,$0$7033$,$0$7034$,$0$7038$,$0$7041$,$0$7042$,$0$7043$,$0$7044$,$0$7045$,$0$7047$,$0$7048$,$0$7050$,$0$7051$,$0$7052$,$0$7053$,$0$1018$1235$,$0$355$,$0$14923$,$0$14920$,$0$14922$,$0$14974$,$0$356$,$0$2158$2159$,$0$6810$,$0$2149$,$0$2158$2163$,$0$2377$,$0$2156$,$0$3844$,$0$3847$,$0$3848$,$0$3850$,$0$3851$,$0$3853$,$0$3854$,$0$3855$,$0$3959$,$0$3962$,$0$3963$,$0$3973$,$0$3974$,$0$3975$,$0$3976$,$0$3857$,$0$3858$,$0$3859$,$0$3860$,$0$3861$,$0$3886$,$0$3887$,$0$4297$,$0$4298$,$0$4301$,$0$4304$,$0$4305$,$0$4306$,$0$4308$,$0$4309$,$0$4310$,$0$4311$,$0$4312$,$0$4295$,$0$3506$3510$4417$4434$,$0$3506$3510$4418$4437$,$0$4157$,$0$4189$,$0$4198$,$0$4203$,$0$4206$,$0$4207$,$0$4211$,$0$4212$,$0$4213$,$0$4216$,$0$4217$,$0$4218$,$0$4220$,$0$4221$,$0$4222$,$0$4223$,$0$3888$,$0$3890$,$0$3891$,$0$4084$,$0$4085$,$0$4086$,$0$4088$,$0$4089$,$0$3921$,$0$3925$,$0$3932$,$0$3935$,$0$3938$,$0$3952$,$0$3978$,$0$3979$,$0$4053$,$0$4090$,$0$4091$,$0$4092$,$0$4093$,$0$4094$,$0$4128$,$0$4129$,$0$4130$,$0$2165$,$0$4007$,$0$4009$,$0$4011$,$0$4012$,$0$4013$,$0$4008$,$0$4014$,$0$4015$,$0$4016$,$0$4017$,$0$4132$,$0$4133$,$0$3907$,$0$3924$,$0$3931$,$0$3934$,$0$3958$,$0$3291$,$0$2138$,$0$3506$3510$3511$3512$,$0$4225$,$0$4238$,$0$4248$,$0$4250$,$0$4276$,$0$4357$,$0$4397$,$0$4398$,$0$3805$,$0$3795$,$0$4051$,$0$4061$,$0$4062$,$0$4063$,$0$4064$,$0$4065$,$0$4066$,$0$4067$,$0$4068$,$0$4069$,$0$3965$,$0$3972$,$0$3980$,$0$2865$,$0$2866$,$0$2867$,$0$2869$,$0$2870$,$0$2871$,$0$2872$,$0$2873$,$0$2874$,$0$2875$,$0$3506$3510$4414$4425$,$0$2136$,$0$2158$2161$,$0$4006$,$0$4019$,$0$4023$,$0$4027$,$0$4052$,$0$4059$,$0$4082$,$0$4083$,$0$4134$,$0$4135$,$0$4136$,$0$3892$,$0$3996$,$0$4137$,$0$4138$,$0$4151$,$0$3895$,$0$3896$,$0$3897$,$0$3898$,$0$3920$,$0$3937$,$0$3954$,$0$3968$,$0$3993$,$0$3994$,$0$3997$,$0$3998$,$0$4000$,$0$3893$,$0$3894$,$0$3899$,$0$3901$,$0$3902$,$0$3903$,$0$3904$,$0$3905$,$0$4018$,$0$4022$,$0$4026$,$0$4055$,$0$4073$,$0$4077$,$0$4117$,$0$4118$,$0$4119$,$0$4121$,$0$4070$,$0$3933$,$0$3960$,$0$3964$,$0$3992$,$0$4071$,$0$4072$,$0$4155$,$0$3789$,$0$4258$,$0$4277$,$0$4302$,$0$4348$,$0$4358$,$0$4401$,$0$3981$,$0$3982$,$0$3983$,$0$4060$,$0$4074$,$0$4122$,$0$4123$,$0$4124$,$0$4125$,$0$4126$,$0$4127$,$0$4152$,$0$4156$,$0$4158$,$0$4159$,$0$4161$,$0$4162$,$0$4163$,$0$4165$,$0$4166$,$0$4167$,$0$4168$,$0$4169$,$0$3984$,$0$3986$,$0$3987$,$0$3990$,$0$4001$,$0$4002$,$0$4003$,$0$4004$,$0$4005$,$0$4056$,$0$4170$,$0$4171$,$0$3969$,$0$4183$,$0$4184$,$0$4188$,$0$4190$,$0$4191$,$0$4193$,$0$4199$,$0$4200$,$0$4202$,$0$3821$,$0$3822$,$0$3824$,$0$3825$,$0$3820$,$0$3314$3334$,$0$4081$,$0$4185$,$0$4194$,$0$4204$,$0$4227$,$0$4228$,$0$4229$,$0$4231$,$0$4232$,$0$3506$,$0$4233$,$0$4234$,$0$4235$,$0$4236$,$0$4237$,$0$4240$,$0$4278$,$0$4284$,$0$4293$,$0$4303$,$0$3826$,$0$3827$,$0$3802$,$0$3828$,$0$3829$,$0$3830$,$0$3849$,$0$3919$,$0$3929$,$0$3936$,$0$3953$,$0$3967$,$0$3985$,$0$4080$,$0$4313$,$0$4314$,$0$4349$,$0$4392$,$0$4393$,$0$4394$,$0$4396$,$0$3288$,$0$3288$3289$,$0$3363$,$0$4239$,$0$4243$,$0$4247$,$0$4249$,$0$4251$,$0$4254$,$0$4259$,$0$4260$,$0$4261$,$0$4262$,$0$4410$,$0$4411$,$0$4413$,$0$3506$3510$4415$4428$,$0$4402$4402$,$0$3506$3510$4420$4443$,$0$3799$,$0$3807$,$0$3846$,$0$4187$,$0$4196$,$0$4226$,$0$4242$,$0$3803$,$0$3809$,$0$3810$,$0$3811$,$0$3813$,$0$3814$,$0$3815$,$0$3816$,$0$3817$,$0$3120$,$0$3121$,$0$3122$,$0$3124$,$0$3125$,$0$3126$,$0$3127$,$0$3128$,$0$3129$,$0$3130$,$0$4246$,$0$4255$,$0$4271$,$0$4281$,$0$4315$,$0$4316$,$0$4317$,$0$14153$,$0$14151$,$0$14159$,$0$4337$,$0$4338$,$0$4339$,$0$4341$,$0$4342$,$0$14165$,$0$4352$,$0$4403$,$0$4404$,$0$4405$,$0$4406$,$0$4407$,$0$4408$,$0$4409$,$0$3832$,$0$3833$,$0$3835$,$0$3836$,$0$3837$,$0$3838$,$0$3839$,$0$3840$,$0$3841$,$0$3842$,$0$3852$,$0$3862$,$0$3863$,$0$3875$,$0$3876$,$0$3877$,$0$3879$,$0$3880$,$0$3881$,$0$3882$,$0$3883$,$0$3884$,$0$3885$,$0$3939$,$0$3941$,$0$3942$,$0$3943$,$0$3945$,$0$3946$,$0$3947$,$0$3948$,$0$3949$,$0$3950$,$0$3951$,$0$3956$,$0$3970$,$0$4038$,$0$4039$,$0$4040$,$0$4041$,$0$4042$,$0$4044$,$0$4045$,$0$4046$,$0$4047$,$0$4048$,$0$4049$,$0$4050$,$0$4057$,$0$4075$,$0$4095$,$0$4096$,$0$4097$,$0$4099$,$0$4100$,$0$4101$,$0$4102$,$0$4104$,$0$4105$,$0$4106$,$0$4107$,$0$4108$,$0$4110$,$0$4111$,$0$4112$,$0$4113$,$0$4114$,$0$4115$,$0$4264$,$0$4265$,$0$3866$,$0$16302$3336$3361$,$0$4266$,$0$4267$,$0$4268$,$0$4269$,$0$4270$,$0$4275$,$0$4280$,$0$4282$,$0$3864$,$0$3865$,$0$3868$,$0$3869$,$0$3870$,$0$3871$,$0$3872$,$0$3873$,$0$3874$,$0$4103$,$0$4327$,$0$4690$,$0$4697$,$0$4703$,$0$4024$,$0$4028$,$0$4029$,$0$4030$,$0$4031$,$0$4033$,$0$4034$,$0$4035$,$0$4619$,$0$4621$,$0$4623$,$0$4625$,$0$4627$,$0$4629$,$0$4631$,$0$4633$,$0$4635$,$0$4637$,$0$4639$,$0$4641$,$0$4643$,$0$4648$,$0$4652$,$0$4659$,$0$4020$,$0$4150$,$0$4160$,$0$4192$,$0$4201$,$0$4209$,$0$4214$,$0$4224$,$0$3808$,$0$4664$,$0$4675$,$0$4681$,$0$4684$,$0$3818$,$0$3819$,$0$3800$,$0$3831$,$0$3314$3330$,$0$4328$,$0$4330$,$0$4331$,$0$4332$,$0$4335$,$0$4272$,$0$4283$,$0$4286$,$0$4287$,$0$4288$,$0$4289$,$0$4290$,$0$4291$,$0$4292$,$0$4299$,$0$3908$,$0$3909$,$0$3910$,$0$3911$,$0$3912$,$0$3913$,$0$3915$,$0$3916$,$0$3917$,$0$3918$,$0$4025$,$0$4116$,$0$4139$,$0$4140$,$0$4141$,$0$4143$,$0$4144$,$0$4145$,$0$4146$,$0$4147$,$0$4148$,$0$4149$,$0$4244$,$0$4256$,$0$4300$,$0$4326$,$0$4336$,$0$4343$,$0$3930$,$0$3940$,$0$3957$,$0$3971$,$0$3989$,$0$4058$,$0$4078$,$0$4079$,$0$4154$,$0$4195$,$0$3923$,$0$4205$,$0$4210$,$0$4215$,$0$4253$,$0$4279$,$0$4294$,$0$4346$,$0$4350$,$0$4353$,$0$4355$,$0$4356$,$0$3488$,$0$3798$,$0$3806$,$0$3843$,$0$3506$3510$4416$4431$,$0$3506$3510$4419$4440$,$0$3506$3510$4421$4446$,$0$3506$3510$4424$4455$,$0$4257$,$0$4273$,$0$4354$,$0$3506$3510$4423$4452$,$0$4245$,$0$3794$,$0$3804$,$0$3506$3510$4422$4448$,$0$16302$3325$,$0$3314$3331$,$0$4928$,$0$4929$,$0$4930$,$0$4932$,$0$4933$,$0$4934$,$0$4935$,$0$4936$,$0$4937$,$0$4938$,$0$2158$2161$4980$,$0$2158$2161$5021$,$0$2158$2161$5024$,$0$2158$4751$,$0$4940$,$0$2158$2161$4988$,$0$2158$2162$4971$,$0$2158$2162$4972$,$0$2158$2161$4981$,$0$2158$2161$4991$,$0$2158$2161$5005$,$0$2158$2161$5029$,$0$2158$2161$5031$,$0$2158$2161$5032$,$0$2158$2161$4993$,$0$2158$2161$5003$,$0$2158$2161$5004$,$0$2158$2161$5014$,$0$2158$2161$5040$,$0$4754$,$0$2158$2161$4987$,$0$2158$2161$4992$,$0$2158$2161$5011$,$0$2158$2161$5030$,$0$2158$2162$4973$,$0$2158$2162$4976$,$0$2158$2161$4994$,$0$2158$2161$5020$,$0$2158$2161$5033$,$0$2158$2161$5047$,$0$2158$2161$5051$,$0$2158$2161$5053$,$0$4752$,$0$2158$2162$4968$,$0$2158$2162$4977$,$0$2158$2161$4989$,$0$2158$2161$5028$,$0$2158$2161$4982$,$0$2158$2161$4997$,$0$2158$2162$4974$,$0$2158$2162$4975$,$0$2158$2161$4983$,$0$2158$2161$5022$,$0$2158$2161$5025$,$0$2158$2161$5036$,$0$6155$,$0$4718$,$0$4727$,$0$15645$,$0$2158$2162$4966$,$0$2158$2161$5000$,$0$2158$2161$5013$,$0$2158$2161$5039$,$0$2158$2161$5052$,$0$4738$,$0$4743$,$0$4747$,$0$4749$,$0$4755$,$0$4943$,$0$2158$2162$4967$,$0$2158$2162$4978$,$0$2158$2161$4985$,$0$2158$2161$4995$,$0$2158$2161$4999$,$0$2158$2161$5010$,$0$2158$2161$5037$,$0$2158$2161$5048$,$0$2158$2161$5049$,$0$2158$2161$5054$,$0$14902$14904$,$0$6322$,$0$6325$,$0$6329$,$0$6331$,$0$6336$,$0$14902$14905$,$0$6387$,$0$15042$15044$,$0$4753$,$0$2158$2161$4998$,$0$2158$2161$5002$,$0$2158$2161$5015$,$0$2158$2161$5027$,$0$2158$2162$4969$,$0$2158$2161$5001$,$0$2158$2161$5006$,$0$2158$2161$5044$,$0$2158$2161$5068$,$0$2158$2162$4970$,$0$2158$2161$4990$,$0$2158$2161$5043$,$0$7036$,$0$7058$,$0$7125$,$0$7140$,$0$7154$,$0$7162$,$0$14879$,$0$2158$2161$5012$,$0$2158$2161$5038$,$0$2158$2161$5041$,$0$7146$,$0$7123$,$0$7151$,$0$7175$,$0$7176$,$0$7177$,$0$7178$,$0$7179$,$0$7180$,$0$7181$,$0$6176$,$0$6340$,$0$14881$,$0$14883$14887$,$0$14888$,$0$6793$,$0$6315$,$0$6324$,$0$6335$,$0$14878$,$0$14880$,$0$6384$,$0$14890$,$0$14891$,$0$7063$,$0$6175$,$0$6326$,$0$6334$,$0$14942$,$0$7029$,$0$7049$,$0$7056$,$0$7061$,$0$7072$,$0$7083$,$0$7113$,$0$7127$,$0$7131$,$0$7133$,$0$7150$,$0$7157$,$0$6328$,$0$7135$,$0$7143$,$0$7159$,$0$7224$,$0$6313$,$0$6320$,$0$6323$,$0$6330$,$0$6332$,$0$6339$,$0$6341$,$0$6344$,$0$6355$,$0$14902$14903$,$0$6321$,$0$2158$2161$15100$,$0$14943$,$0$14946$,$0$14947$,$0$14948$,$0$14949$,$0$14950$,$0$7015$,$0$7017$,$0$7025$,$0$7031$,$0$7040$,$0$7054$,$0$7055$,$0$7065$,$0$7066$,$0$7067$,$0$7068$,$0$7070$,$0$7073$,$0$7074$,$0$7075$,$0$7077$,$0$7078$,$0$7079$,$0$7082$,$0$7084$,$0$7085$,$0$6174$,$0$14951$,$0$14952$,$0$14953$,$0$14954$,$0$7059$,$0$7064$,$0$7071$,$0$7076$,$0$7080$,$0$7086$,$0$7087$,$0$7088$,$0$7089$,$0$7090$,$0$7091$,$0$7093$,$0$7096$,$0$7097$,$0$7098$,$0$7099$,$0$7100$,$0$7101$,$0$14902$,$0$14902$14906$,$0$15123$,$0$6314$,$0$6317$,$0$6319$,$0$6333$,$0$6386$,$0$15141$,$0$15170$,$0$7030$,$0$7035$,$0$7060$,$0$7062$,$0$7164$,$0$7165$,$0$6318$,$0$6337$,$0$2158$2161$14955$,$0$7024$,$0$7039$,$0$7057$,$0$7115$,$0$7138$,$0$7144$,$0$7166$,$0$7167$,$0$7168$,$0$7169$,$0$7170$,$0$7171$,$0$7172$,$0$7173$,$0$7174$,$0$7069$,$0$7081$,$0$7092$,$0$7095$,$0$7104$,$0$7105$,$0$7106$,$0$7107$,$0$7108$,$0$7110$,$0$7114$,$0$7116$,$0$7117$,$0$7118$,$0$7119$,$0$7120$,$0$7121$,$0$7122$,$0$7124$,$0$7126$,$0$7128$,$0$7129$,$0$7130$,$0$7132$,$0$7134$,$0$7136$,$0$7137$,$0$7139$,$0$7141$,$0$7142$,$0$7147$,$0$7148$,$0$7149$,$0$7152$,$0$7153$,$0$7155$,$0$7156$,$0$7158$,$0$7160$,$0$7161$,$0$7163$,$0$9778$,$0$15692$,$0$14703$,$0$15693$15698$,$0$12352$,$0$12354$,$0$12194$,$0$12208$,$0$13221$,$0$12911$,$0$12005$,$0$14054$,$0$14190$,$0$14146$,$0$14147$,$0$11146$,$0$11989$,$0$11992$,$0$13037$,$0$12983$,$0$14913$,$0$12358$,$0$11987$,$0$11991$,$0$14357$,$0$16146$,$0$16147$,$0$12034$,$0$11989$,$0$11992$,$0$12034$,$0$11987$,$0$11991$,$0$12005$,$0$12062$,$0$15969$,$0$15970$,$0$11991$,$0$12035$,$0$16259$,$0$16260$,$0$16261$,$0$16263$,$0$16265$,$0$16266$,$0$16267$,$0$15997$,$0$15042$15045$,$0$12045$,$0$12351$,$0$16019$,$0$16021$,$0$16085$,$0$16099$,$0$16262$,$0$15042$,$0$15042$15046$,$0$14508$,$0$14512$,$0$14516$,$0$15401$,$0$11147$,$0$11589$,$0$14509$,$0$1812$1813$11252$11257$,$0$11987$,$0$11990$,$0$12356$,$0$12173$,$0$12221$,$0$12573$,$0$13661$,$0$12360$,$0$11986$,$0$12792$,$0$11986$,$0$11990$,$0$12004$,$0$12035$,$0$11986$,$0$11990$,$0$12004$,$0$12035$,$0$11989$,$0$11992$,$0$12034$,$0$13133$,$0$13137$,$0$16153$,$0$16167$,$0$12062$,$0$11989$,$0$11992$,$0$12034$,$0$11986$,$0$11990$,$0$12004$,$0$12035$,$0$11989$,$0$3506$3510$12659$12660$,$0$13264$,$0$11141$,$0$12020$,$0$13087$,$0$12035$,$0$12166$,$0$15872$,$0$15904$,$0$16006$,$0$13361$,$0$11991$,$0$11987$,$0$12005$,$0$12062$,$0$12674$,$0$15902$,$0$15290$,$0$11991$,$0$12744$,$0$11986$,$0$11989$,$0$11990$,$0$11992$,$0$12004$,$0$12034$,$0$12062$,$0$15310$,$0$15908$,$0$14767$,$0$14866$,$0$14867$,$0$11607$,$0$12025$,$0$3506$3510$12136$12137$,$0$12169$,$0$12174$,$0$12353$,$0$13171$,$0$11990$,$0$12005$,$0$12355$,$0$13984$,$0$13985$,$0$12062$,$0$11992$,$0$9743$,$0$11986$,$0$11990$,$0$12004$,$0$12035$,$0$13131$,$0$13249$,$0$13254$,$0$13558$,$0$12979$,$0$11986$,$0$11986$,$0$11990$,$0$12004$,$0$12035$,$0$11989$,$0$11992$,$0$12034$,$0$11986$,$0$11990$,$0$12004$,$0$12035$,$0$11986$,$0$11990$,$0$12004$,$0$12035$,$0$11989$,$0$12062$,$0$2158$12118$,$0$12193$,$0$3506$3510$12592$12593$,$0$15899$,$0$15901$,$0$15906$,$0$15907$,$0$13737$,$0$11987$,$0$11991$,$0$12005$,$0$12062$,$0$11987$,$0$11991$,$0$12005$,$0$12062$,$0$15664$,$0$15693$15696$,$0$11992$,$0$12034$,$0$11987$,$0$12004$,$0$12350$,$0$16011$,$0$16016$,$0$16084$,$0$16087$,$0$16089$,$0$16094$,$0$16095$,$0$16100$,$0$14081$,$0$14188$,$0$14152$,$0$14189$,$0$11992$,$0$12026$,$0$12170$,$0$13965$,$0$13966$,$0$13967$,$0$13968$,$0$13969$,$0$13972$,$0$14058$,$0$12023$,$0$12350$,$0$11987$,$0$11991$,$0$12005$,$0$12035$,$0$12350$,$0$14082$,$0$14083$,$0$14124$,$0$14187$,$0$14766$,$0$2158$2161$14506$,$0$14507$,$0$14510$,$0$14513$,$0$14514$,$0$14515$,$0$13987$,$0$13988$,$0$13989$,$0$11142$,$0$14511$,$0$11986$,$0$12168$,$0$12195$,$0$12357$,$0$13983$,$0$14034$,$0$14035$,$0$11989$,$0$11992$,$0$12034$,$0$11988$,$0$13022$,$0$6793$11268$,$0$11987$,$0$11991$,$0$12005$,$0$12062$,$0$11986$,$0$11990$,$0$12004$,$0$12035$,$0$11987$,$0$11991$,$0$12005$,$0$12062$,$0$11987$,$0$11991$,$0$12005$,$0$12062$,$0$11986$,$0$11990$,$0$12004$,$0$12035$,$0$11989$,$0$11992$,$0$12034$,$0$11987$,$0$11991$,$0$12005$,$0$12062$,$0$11989$,$0$11992$,$0$12034$,$0$11989$,$0$11992$,$0$12034$,$0$11987$,$0$11991$,$0$12005$,$0$12062$,$0$13019$,$0$13136$,$0$13139$,$0$13132$,$0$13134$,$0$13130$,$0$13135$,$0$13138$,$0$13165$,$0$13168$,$0$11621$,$0$14254$,$0$11986$,$0$11990$,$0$12004$,$0$12035$,$0$11986$,$0$12883$,$0$12178$,$0$11946$,$0$11990$,$0$12004$,$0$12035$,$0$11987$,$0$11991$,$0$12005$,$0$12062$,$0$11159$,$0$14715$,$0$14717$,$0$14730$,$0$15895$,$0$15897$,$0$15898$,$0$15905$,$0$15946$,$0$15962$,$0$15974$,$0$14830$,$0$15973$,$0$15977$,$0$16003$,$0$15903$,$0$15900$,$0$15920$,$0$11143$,$0$15403$,$0$15405$,$0$14062$,$0$14066$,$0$12165$,$0$12180$,$0$12186$,$0$12005$,$0$15971$,$0$15972$,$0$15980$,$0$15998$,$0$16024$,$0$16148$,$0$16158$,$0$12177$,$0$12361$,$0$15604$,$0$15662$,$0$14756$,$0$12021$,$0$12024$,$0$12179$,$0$11989$,$0$9726$,$0$9731$,$0$14373$,$0$11144$,$0$9720$,$0$9763$,$0$9807$,$0$16358$,$0$16381$,$0$16288$,$0$12004$,$0$11989$,$0$12035$,$0$9694$,$0$9750$,$0$16439$,$0$16395$,$0$15814$,$0$15826$,$0$16017$,$0$16020$,$0$16023$,$0$16025$,$0$16079$,$0$16082$,$0$16083$,$0$16092$,$0$16096$,$0$9752$,$0$14712$,$0$16321$,$0$9714$,$0$9732$,$0$9748$,$0$14157$,$0$15311$,$0$15558$,$0$11269$,$0$10156$,$0$9687$,$0$9695$,$0$15724$,$0$15786$,$0$14883$,$0$14883$14884$,$0$14883$14885$,$0$14883$14886$,$0$14889$,$0$14894$,$0$9733$,$0$9742$,$0$9759$,$0$13870$,$0$13889$,$0$16514$,$0$15062$,$0$14156$,$0$13819$,$0$11606$,$0$12034$,$0$11992$,$0$12359$,$0$13992$,$0$11987$,$0$11991$,$0$12005$,$0$12062$,$0$11989$,$0$11992$,$0$12034$,$0$11990$,$0$12034$,$0$14158$,$0$14160$,$0$14161$,$0$9762$,$0$12006$,$0$12022$,$0$12004$,$0$12172$,$0$9724$,$0$9740$,$0$12034$,$0$12164$,$0$9751$,$0$14358$,$0$11145$,$0$12171$,$0$16521$,$0$14713$,$0$14714$,$0$14921$,$0$16922$,$0$14718$,$0$14719$,$0$15766$,$0$15799$,$0$16519$,$0$16520$,$0$16543$,$0$16582$,$0$16591$,$0$16592$,$0$16593$,$0$16594$,$0$16614$,$0$16915$,$0$15308$,$0$15686$,$0$15537$,$0$15684$,$0$15792$,$0$14977$,$0$15086$,$0$16595$,$0$15835$,$0$16067$,$0$16022$,$0$16026$,$0$16028$,$0$16029$,$0$16080$,$0$16086$,$0$16090$,$0$16091$,$0$16093$,$0$16097$,$0$16098$,$0$16157$,$0$16214$,$0$16436$,$0$16437$,$0$16438$,$0$16440$,$0$15621$,$0$15649$,$0$15693$,$0$15693$15697$,$0$16018$,$0$16081$,$0$16088$,$0$16442$,$0$16954$,$0$16264$,$0$14823$,$0$16985$,$0$13165$16971$,$0$16302$,$0$16411$,$0$16435$,$0$16441$,$0$16443$,$0$16513$,$0$16601$,$0$16865$,$0$16553$,$0$16590$,$0$2158$2161$16596$,$0$16917$,$0$16605$,$0$16869$";
	  String[] split = all_position.split(",");
	  System.out.println(DateUtil.getCurrentDate());
	  for(int i =0; i<split.length; i++)
	  {
		  if("$0$2158$2159".contains(split[i]))
	      {
			  System.out.println("**************");
	      }
	  }
	  System.out.println(DateUtil.getCurrentDate());
    //System.out.println(getCategoryTreeByUserHandl(0, "HIWCMdemo", 193));
  }
}