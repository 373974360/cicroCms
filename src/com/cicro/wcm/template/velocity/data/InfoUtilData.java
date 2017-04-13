//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.cicro.wcm.template.velocity.data;

import com.cicro.util.DateUtil;
import com.cicro.util.FormatUtil;
import com.cicro.wcm.bean.cms.category.CateCurPositionBean;
import com.cicro.wcm.bean.cms.category.CategoryBean;
import com.cicro.wcm.bean.cms.category.SMCategoryBean;
import com.cicro.wcm.bean.cms.count.CmsCountBean;
import com.cicro.wcm.bean.cms.count.InfoAccessBean;
import com.cicro.wcm.bean.cms.count.InfoCountBean;
import com.cicro.wcm.bean.cms.count.SiteCountBean;
import com.cicro.wcm.bean.cms.count.SiteInfoTuisongBean;
import com.cicro.wcm.bean.cms.info.ArticleBean;
import com.cicro.wcm.bean.cms.info.GKFbsznBean;
import com.cicro.wcm.bean.cms.info.GKInfoBean;
import com.cicro.wcm.bean.cms.info.GKResFileBean;
import com.cicro.wcm.bean.cms.info.InfoBean;
import com.cicro.wcm.bean.cms.info.PicBean;
import com.cicro.wcm.bean.cms.info.RelatedInfoBean;
import com.cicro.wcm.bean.cms.info.VideoBean;
import com.cicro.wcm.bean.control.SiteBean;
import com.cicro.wcm.bean.template.TurnPageBean;
import com.cicro.wcm.bean.zwgk.appcatalog.AppCatalogBean;
import com.cicro.wcm.bean.zwgk.node.GKNodeBean;
import com.cicro.wcm.bean.zwgk.node.GKNodeCategory;
import com.cicro.wcm.bean.zwgk.ysqgk.YsqgkBean;
import com.cicro.wcm.bean.zwgk.ysqgk.YsqgkListBean;
import com.cicro.wcm.dao.zwgk.ysqgk.YsqgkInfoDAO;
import com.cicro.wcm.services.browserapi.BrowserAPIService;
import com.cicro.wcm.services.cms.category.CategoryBrowserTreeUtil;
import com.cicro.wcm.services.cms.category.CategoryManager;
import com.cicro.wcm.services.cms.category.CategoryUtil;
import com.cicro.wcm.services.cms.count.AccessCountManager;
import com.cicro.wcm.services.cms.count.TuisongCountManager;
import com.cicro.wcm.services.cms.info.InfoBaseManager;
import com.cicro.wcm.services.cms.info.InfoExpandManager;
import com.cicro.wcm.services.cms.info.ModelUtil;
import com.cicro.wcm.services.cms.rss.RssReaderManager;
import com.cicro.wcm.services.control.domain.SiteDomainManager;
import com.cicro.wcm.services.control.site.SiteManager;
import com.cicro.wcm.services.model.WcmZykFile;
import com.cicro.wcm.services.model.services.WcmZykInfoService;
import com.cicro.wcm.services.session.OnlineCounter;
import com.cicro.wcm.services.system.formodel.ModelManager;
import com.cicro.wcm.services.zwgk.appcatalog.AppCatalogManager;
import com.cicro.wcm.services.zwgk.info.GKInfoManager;
import com.cicro.wcm.services.zwgk.node.GKNodeCateManager;
import com.cicro.wcm.services.zwgk.node.GKNodeManager;
import com.cicro.wcm.services.zwgk.ysqgk.YsqgkInfoManager;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class InfoUtilData {
    public InfoUtilData() {
    }

    public static List<InfoBean> removeHTMLTag(List<InfoBean> l) {
        if(l != null && l.size() > 0) {
            Iterator var2 = l.iterator();

            while(var2.hasNext()) {
                InfoBean info = (InfoBean)var2.next();
                info.setTitle(info.getTitle().replaceAll("<[Bb][Rr]/?>", ""));
            }
        }

        return l;
    }

    public static List<GKInfoBean> removeHTMLTagForGK(List<GKInfoBean> l) {
        if(l != null && l.size() > 0) {
            Iterator var2 = l.iterator();

            while(var2.hasNext()) {
                InfoBean info = (InfoBean)var2.next();
                info.setTitle(info.getTitle().replaceAll("<[Bb][Rr]/?>", ""));
            }
        }

        return l;
    }

    public static List<InfoBean> getInfoList(String params) {
        HashMap con_map = new HashMap();
        getInfoSearchCon(params, con_map, "cms");
        return removeHTMLTag(InfoBaseManager.getBroInfoList(con_map));
    }

    public static List<InfoBean> getInfoHotList(String params) {
        HashMap con_map = new HashMap();
        getInfoSearchCon(params, con_map, "cms");
        con_map.put("current_page", "1");
        return removeHTMLTag(InfoBaseManager.getBroInfoList(con_map));
    }

    public static TurnPageBean getInfoCount(String params) {
        HashMap con_map = new HashMap();
        getInfoSearchCon(params, con_map, "cms");
        TurnPageBean tpb = new TurnPageBean();
        tpb.setCount(Integer.parseInt(InfoBaseManager.getBroInfoCount(con_map)));
        int cur_page = Integer.parseInt((String)con_map.get("current_page"));
        int page_size = Integer.parseInt((String)con_map.get("page_size"));
        tpb.setCur_page(cur_page);
        tpb.setPage_size(page_size);
        tpb.setPage_count(tpb.getCount() / tpb.getPage_size() + 1);
        if(tpb.getCount() % tpb.getPage_size() == 0 && tpb.getPage_count() > 1) {
            tpb.setPage_count(tpb.getPage_count() - 1);
        }

        if(cur_page > 1) {
            tpb.setPrev_num(cur_page - 1);
        }

        tpb.setNext_num(tpb.getPage_count());
        if(cur_page < tpb.getPage_count()) {
            tpb.setNext_num(cur_page + 1);
        }

        if(tpb.getPage_count() > 10 && cur_page > 5) {
            if(cur_page > tpb.getPage_count() - 4) {
                tpb.setCurr_start_num(tpb.getPage_count() - 6);
            } else {
                tpb.setCurr_start_num(cur_page - 2);
            }
        }

        return tpb;
    }

    public static List<InfoBean> getFWInfoList(String params) {
        HashMap con_map = new HashMap();
        getInfoSearchCon(params, con_map, "ggfw");
        return removeHTMLTag(InfoBaseManager.getBroInfoList(con_map));
    }

    public static List<InfoBean> getFWInfoHotList(String params) {
        HashMap con_map = new HashMap();
        getInfoSearchCon(params, con_map, "ggfw");
        con_map.put("current_page", "1");
        return removeHTMLTag(InfoBaseManager.getBroInfoList(con_map));
    }

    public static String getCategoryTree(String site_id, String cat_id) {
        return CategoryBrowserTreeUtil.getBroCategoryTreeByCategoryID(Integer.parseInt(cat_id), site_id);
    }

    public static List<CategoryBean> getZTCategoryList(String zt_cat_id, String site_id) {
        return CategoryManager.getZTCategoryListBySiteAndType(Integer.parseInt(zt_cat_id), site_id);
    }

    public static String getSharedCategoryTree(String cat_id) {
        return cat_id != null && !"".equals(cat_id)?CategoryBrowserTreeUtil.getBroCategoryTreeByCategoryID(Integer.parseInt(cat_id), ""):"";
    }

    public static String getFWCategoryTree(String cat_id) {
        return cat_id != null && !"".equals(cat_id)?CategoryBrowserTreeUtil.getBroCategoryTreeByCategoryID(Integer.parseInt(cat_id), "ggfw"):"";
    }

    public static List<GKInfoBean> getGKInfoListForSharedCate(String params) {
        HashMap con_map = new HashMap();
        getInfoSearchCon(params, con_map, "");
        return GKInfoManager.getBroGKInfoListForSharedCategory(con_map);
    }

    public static TurnPageBean getGKInfoCountForSharedCate(String params) {
        HashMap con_map = new HashMap();
        getInfoSearchCon(params, con_map, "");
        TurnPageBean tpb = new TurnPageBean();
        tpb.setCount(Integer.parseInt(GKInfoManager.getBroGKInfoCountForSharedCategory(con_map)));
        int cur_page = Integer.parseInt((String)con_map.get("current_page"));
        int page_size = Integer.parseInt((String)con_map.get("page_size"));
        tpb.setCur_page(cur_page);
        tpb.setPage_size(page_size);
        tpb.setPage_count(tpb.getCount() / tpb.getPage_size() + 1);
        if(tpb.getCount() % tpb.getPage_size() == 0 && tpb.getPage_count() > 1) {
            tpb.setPage_count(tpb.getPage_count() - 1);
        }

        if(cur_page > 1) {
            tpb.setPrev_num(cur_page - 1);
        }

        tpb.setNext_num(tpb.getPage_count());
        if(cur_page < tpb.getPage_count()) {
            tpb.setNext_num(cur_page + 1);
        }

        return tpb;
    }

    public static TurnPageBean getFWInfoCount(String params) {
        HashMap con_map = new HashMap();
        getInfoSearchCon(params, con_map, "ggfw");
        TurnPageBean tpb = new TurnPageBean();
        tpb.setCount(Integer.parseInt(InfoBaseManager.getBroInfoCount(con_map)));
        int cur_page = Integer.parseInt((String)con_map.get("current_page"));
        int page_size = Integer.parseInt((String)con_map.get("page_size"));
        tpb.setCur_page(cur_page);
        tpb.setPage_size(page_size);
        tpb.setPage_count(tpb.getCount() / tpb.getPage_size() + 1);
        if(tpb.getCount() % tpb.getPage_size() == 0 && tpb.getPage_count() > 1) {
            tpb.setPage_count(tpb.getPage_count() - 1);
        }

        if(cur_page > 1) {
            tpb.setPrev_num(cur_page - 1);
        }

        tpb.setNext_num(tpb.getPage_count());
        if(cur_page < tpb.getPage_count()) {
            tpb.setNext_num(cur_page + 1);
        }

        return tpb;
    }

    public static List<GKInfoBean> getGKInfoList(String params) {
        HashMap con_map = new HashMap();
        getInfoSearchCon(params, con_map, "zwgk");
        return GKInfoManager.getBroGKInfoList(con_map);
    }

    public static List<GKInfoBean> getGKInfoHotList(String params) {
        HashMap con_map = new HashMap();
        getInfoSearchCon(params, con_map, "zwgk");
        con_map.put("current_page", "1");
        return GKInfoManager.getBroGKInfoList(con_map);
    }

    public static List<GKFbsznBean> getGKBSZNInfoHotList(String params) {
        HashMap con_map = new HashMap();
        getInfoSearchCon(params, con_map, "zwgk");
        con_map.put("current_page", "1");
        return GKInfoManager.getBroGKBSZNInfoList(con_map);
    }

    public static List<GKFbsznBean> getGKBSZNInfoList(String params) {
        HashMap con_map = new HashMap();
        getInfoSearchCon(params, con_map, "zwgk");
        return GKInfoManager.getBroGKBSZNInfoList(con_map);
    }

    public static void addInfoHits(String info_id, String lastHit_date) {
        InfoBaseManager.addInfoHits(info_id, "1", lastHit_date);
    }

    public static TurnPageBean getGKInfoCount(String params) {
        HashMap con_map = new HashMap();
        getInfoSearchCon(params, con_map, "zwgk");
        TurnPageBean tpb = new TurnPageBean();
        tpb.setCount(Integer.parseInt(GKInfoManager.getBroGKInfoCount(con_map)));
        int cur_page = Integer.parseInt((String)con_map.get("current_page"));
        int page_size = Integer.parseInt((String)con_map.get("page_size"));
        tpb.setCur_page(cur_page);
        tpb.setPage_size(page_size);
        tpb.setPage_count(tpb.getCount() / tpb.getPage_size() + 1);
        if(tpb.getCount() % tpb.getPage_size() == 0 && tpb.getPage_count() > 1) {
            tpb.setPage_count(tpb.getPage_count() - 1);
        }

        if(cur_page > 1) {
            tpb.setPrev_num(cur_page - 1);
        }

        tpb.setNext_num(tpb.getPage_count());
        if(cur_page < tpb.getPage_count()) {
            tpb.setNext_num(cur_page + 1);
        }

        return tpb;
    }

    public static void getInfoSearchCon(String params, Map<String, String> con_map, String app_id) {
        int cur_page = 1;
        int page_size = 15;
        String is_shared = "";
        String cat_id = "";
        String node_id = "";
        String orderby = "ci.released_dtime desc";
        boolean interval = false;
        String[] tempA = params.split(";");

        for(int i = 0; i < tempA.length; ++i) {
            String cat_class_id;
            String weight;
            if(tempA[i].toLowerCase().startsWith("catalog_id=")) {
                cat_class_id = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));
                if(!"".equals(cat_class_id) && !cat_class_id.startsWith("$catalog_id") && FormatUtil.isValiditySQL(cat_class_id)) {
                    try {
                        weight = AppCatalogManager.getAppCatalogBean(Integer.parseInt(cat_class_id)).getC_sql();
                        if(weight != null && !"".equals(weight)) {
                            con_map.put("appcatalog_sql", weight);
                        } else {
                            con_map.put("appcatalog_sql", " 1=2 ");
                        }
                    } catch (Exception var16) {
                        var16.printStackTrace();
                    }
                }
            }

            if(tempA[i].toLowerCase().startsWith("kw=")) {
                cat_class_id = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));
                if(!"".equals(cat_class_id) && !cat_class_id.startsWith("$kw") && FormatUtil.isValiditySQL(cat_class_id)) {
                    con_map.put("kw", cat_class_id);
                    interval = true;
                }
            }

            if(tempA[i].toLowerCase().startsWith("start_time=")) {
                cat_class_id = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));
                if(!"".equals(cat_class_id) && !cat_class_id.startsWith("$start_time") && FormatUtil.isValiditySQL(cat_class_id)) {
                    con_map.put("start_time", cat_class_id);
                    interval = true;
                }
            }

            if(tempA[i].toLowerCase().startsWith("end_time=")) {
                cat_class_id = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));
                if(!"".equals(cat_class_id) && !cat_class_id.startsWith("$end_time") && FormatUtil.isValiditySQL(cat_class_id)) {
                    con_map.put("end_time", cat_class_id);
                    interval = true;
                }
            }

            if(tempA[i].toLowerCase().startsWith("month_day=")) {
                cat_class_id = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));
                if(!"".equals(cat_class_id) && !cat_class_id.startsWith("$month_day") && FormatUtil.isValiditySQL(cat_class_id)) {
                    con_map.put("month_day", cat_class_id);
                }
            }

            if(tempA[i].toLowerCase().startsWith("node_id=")) {
                node_id = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));
                if(!"".equals(node_id) && !node_id.startsWith("$node_id") && FormatUtil.isValiditySQL(node_id)) {
                    con_map.put("node_id", node_id);
                }
            }

            if(tempA[i].toLowerCase().startsWith("site_id=")) {
                cat_class_id = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));
                if(!"".equals(cat_class_id) && !cat_class_id.startsWith("$site_id") && FormatUtil.isValiditySQL(cat_class_id)) {
                    con_map.put("site_id", cat_class_id);
                }
            }

            if(tempA[i].toLowerCase().startsWith("is_show_thumb=")) {
                cat_class_id = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));
                if("true".equals(cat_class_id)) {
                    con_map.put("thumb_url", "true");
                }
            }

            if(tempA[i].toLowerCase().startsWith("cat_id=")) {
                cat_id = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));
            }

            if(tempA[i].toLowerCase().startsWith("tag=")) {
                cat_class_id = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));
                if(!"".equals(cat_class_id) && !cat_class_id.startsWith("$tag") && FormatUtil.isValiditySQL(cat_class_id)) {
                    weight = "";
                    String[] weight_end = cat_class_id.split(",");

                    for(int w_cons = 0; w_cons < weight_end.length; ++w_cons) {
                        if(w_cons > 0) {
                            weight = weight + "or";
                        }

                        weight = weight + " ci.tags like \'%" + weight_end[w_cons] + "%\' ";
                    }

                    con_map.put("tag_sql", "(" + weight + ")");
                }
            }

            if(tempA[i].toLowerCase().startsWith("orderby=")) {
                cat_class_id = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));
                if(!"".equals(cat_class_id) && !cat_class_id.startsWith("$orderby") && FormatUtil.isValiditySQL(cat_class_id)) {
                    orderby = cat_class_id;
                }
            }

            if(tempA[i].toLowerCase().startsWith("weight=")) {
                cat_class_id = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));
                if(!"".equals(cat_class_id) && !cat_class_id.startsWith("$weight") && FormatUtil.isValiditySQL(cat_class_id)) {
                    if(cat_class_id.contains(",")) {
                        weight = cat_class_id.substring(0, cat_class_id.indexOf(","));
                        String var17 = cat_class_id.substring(cat_class_id.indexOf(",") + 1);
                        String var18 = "";
                        if(weight != null && !"".equals(weight.trim())) {
                            var18 = " and ci.weight >= " + weight;
                        }

                        if(var17 != null && !"".equals(var17.trim())) {
                            var18 = var18 + " and ci.weight <= " + var17;
                        }

                        con_map.put("weight_con", var18);
                    } else {
                        con_map.put("weight", cat_class_id);
                    }
                }
            }

            if(tempA[i].toLowerCase().startsWith("model_id=")) {
                cat_class_id = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));
                if(!"".equals(cat_class_id) && !cat_class_id.startsWith("$model_id") && FormatUtil.isNumeric(cat_class_id)) {
                    con_map.put("model_id", cat_class_id);
                }
            }

            if(tempA[i].toLowerCase().startsWith("size=")) {
                cat_class_id = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));
                if(!"".equals(cat_class_id) && !cat_class_id.startsWith("$size") && FormatUtil.isNumeric(cat_class_id)) {
                    page_size = Integer.parseInt(cat_class_id);
                }
            }

            if(tempA[i].toLowerCase().startsWith("cur_page=")) {
                cat_class_id = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));
                if(!"".equals(cat_class_id) && !cat_class_id.startsWith("$cur_page") && FormatUtil.isNumeric(cat_class_id)) {
                    cur_page = Integer.parseInt(cat_class_id);
                }
            }

            if(tempA[i].toLowerCase().startsWith("gk_index=")) {
                cat_class_id = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));
                if(!"".equals(cat_class_id) && !cat_class_id.startsWith("$gk_index") && FormatUtil.isValiditySQL(cat_class_id)) {
                    con_map.put("gk_index", cat_class_id);
                }
            }

            if(tempA[i].toLowerCase().startsWith("doc_no=")) {
                cat_class_id = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));
                if(!"".equals(cat_class_id) && !cat_class_id.startsWith("$doc_no") && FormatUtil.isValiditySQL(cat_class_id)) {
                    con_map.put("doc_no", cat_class_id);
                }
            }

            if(tempA[i].toLowerCase().startsWith("title=")) {
                cat_class_id = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));
                if(!"".equals(cat_class_id) && !cat_class_id.startsWith("$title") && FormatUtil.isValiditySQL(cat_class_id)) {
                    con_map.put("title", cat_class_id);
                }
            }

            if(tempA[i].toLowerCase().startsWith("sub_title=")) {
                cat_class_id = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));
                if(!"".equals(cat_class_id) && !cat_class_id.startsWith("$sub_title") && FormatUtil.isValiditySQL(cat_class_id)) {
                    con_map.put("sub_title", cat_class_id);
                }
            }

            if(tempA[i].toLowerCase().startsWith("top_title=")) {
                cat_class_id = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));
                if(!"".equals(cat_class_id) && !cat_class_id.startsWith("$top_title") && FormatUtil.isValiditySQL(cat_class_id)) {
                    con_map.put("top_title", cat_class_id);
                }
            }

            if(tempA[i].toLowerCase().startsWith("gk_duty_dept=")) {
                cat_class_id = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));
                if(!"".equals(cat_class_id) && !cat_class_id.startsWith("$gk_duty_dept") && FormatUtil.isValiditySQL(cat_class_id)) {
                    con_map.put("gk_duty_dept", cat_class_id);
                }
            }

            if(tempA[i].toLowerCase().startsWith("gk_input_dept=")) {
                cat_class_id = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));
                if(!"".equals(cat_class_id) && !cat_class_id.startsWith("$gk_input_dept") && FormatUtil.isValiditySQL(cat_class_id)) {
                    con_map.put("gk_input_dept", cat_class_id);
                }
            }

            if(tempA[i].toLowerCase().startsWith("description=")) {
                cat_class_id = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));
                if(!"".equals(cat_class_id) && !cat_class_id.startsWith("$description") && FormatUtil.isValiditySQL(cat_class_id)) {
                    con_map.put("description", cat_class_id);
                }
            }

            if(tempA[i].toLowerCase().startsWith("topic_key=")) {
                cat_class_id = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));
                if(!"".equals(cat_class_id) && !cat_class_id.startsWith("$topic_key") && FormatUtil.isValiditySQL(cat_class_id)) {
                    con_map.put("topic_key", cat_class_id);
                }
            }

            if(tempA[i].toLowerCase().startsWith("gen_st=")) {
                cat_class_id = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));
                if(!"".equals(cat_class_id) && !cat_class_id.startsWith("$gen_st") && FormatUtil.isValiditySQL(cat_class_id)) {
                    con_map.put("gen_st", cat_class_id);
                }
            }

            if(tempA[i].toLowerCase().startsWith("gen_et=")) {
                cat_class_id = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));
                if(!"".equals(cat_class_id) && !cat_class_id.startsWith("$gen_et") && FormatUtil.isValiditySQL(cat_class_id)) {
                    con_map.put("gen_et", cat_class_id);
                }
            }

            if(tempA[i].toLowerCase().startsWith("is_shared=")) {
                is_shared = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));
            }

            if(tempA[i].toLowerCase().startsWith("cat_class_id=")) {
                cat_class_id = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));
                if(!"".equals(cat_class_id) && !cat_class_id.startsWith("$cat_class_id") && FormatUtil.isValiditySQL(cat_class_id)) {
                    if(cat_class_id.indexOf(",") > -1) {
                        con_map.put("cat_sql", " ca.cat_class_id in (" + cat_class_id + ")");
                    } else {
                        con_map.put("cat_sql", " ca.cat_class_id = " + cat_class_id);
                    }
                }
            }
        }

        if(!"".equals(cat_id) && !"0".equals(cat_id) && !cat_id.startsWith("$cat_id") && FormatUtil.isValiditySQL(cat_id)) {
            if(!"".equals(node_id) && !"true".equals(is_shared)) {
                getCategorySearchSql(cat_id, con_map, app_id);
            } else if("zwgk".equals(app_id)) {
                if(cat_id.indexOf(",") > -1) {
                    con_map.put("cat_sql", "gk.topic_id in (" + cat_id + ") or gk.theme_id in (" + cat_id + ") or gk.serve_id in (" + cat_id + ") or ca.cat_class_id in (" + cat_id + ")");
                } else {
                    con_map.put("cat_sql", "gk.topic_id=" + cat_id + " or gk.theme_id=" + cat_id + " or gk.serve_id=" + cat_id + " or ca.cat_class_id = " + cat_id);
                }
            } else {
                getCategorySearchSql(cat_id, con_map, app_id);
            }
        }

        if(interval) {
            con_map.remove("month_day");
        }

        con_map.put("page_size", String.valueOf(page_size));
        con_map.put("current_page", String.valueOf(cur_page));
        con_map.put("orderby", orderby);
    }

    public static void getCategorySearchSql(String cat_id, Map<String, String> con_map, String app_id) {
        if(!"10".equals(cat_id) && !"11".equals(cat_id) && !"12".equals(cat_id)) {
            String site_id = (String)con_map.get("site_id");
            if("zwgk".equals(app_id)) {
                site_id = (String)con_map.get("node_id");
            }

            if("ggfw".equals(app_id)) {
                site_id = "ggfw";
                con_map.put("site_id", site_id);
            }

            String constr = "";
            if(!"zwgk".equals(app_id) && !"ggfw".equals(app_id)) {
                constr = " and site_id=\'" + site_id + "\'";
            }

            String cat_sql = "";
            String[] tempA = cat_id.split(",");

            for(int i = 0; i < tempA.length; ++i) {
                CategoryBean cb = CategoryManager.getCategoryBeanCatID(Integer.parseInt(tempA[i]), site_id);
                if(cb != null) {
                    cat_sql = cat_sql + " ca.cat_id in ( select cat_id from cs_info_category where cat_position like \'" + cb.getCat_position() + "%\' " + constr + " ) ";
                    cat_sql = cat_sql + " or";
                }
            }

            if(cat_sql.endsWith("or")) {
                cat_sql = cat_sql.substring(0, cat_sql.length() - 2);
            }

            if(cat_sql != null && !"".equals(cat_sql)) {
                con_map.put("cat_sql", cat_sql);
            }
        } else {
            con_map.put("cat_sql", "ci.cat_id = " + cat_id);
            if(con_map.containsKey("node_id")) {
                con_map.put("site_id", (String)con_map.get("node_id"));
            } else {
                con_map.remove("site_id");
            }
        }

    }

    public static List<RelatedInfoBean> getRelatedInfoList(String info_id) {
        HashMap m = new HashMap();
        m.put("info_id", info_id);
        List l = InfoBaseManager.getBroRelatedInfoList(m);
        if(l != null && l.size() > 0) {
            Iterator var4 = l.iterator();

            while(var4.hasNext()) {
                RelatedInfoBean info = (RelatedInfoBean)var4.next();
                info.setTitle(info.getTitle().replaceAll("<[Bb][Rr]/?>", ""));
            }
        }

        return l;
    }

    public static List<GKResFileBean> getGKResourceFile(String info_id) {
        return GKInfoManager.getGKResFileList(info_id);
    }

    public static String getSiteDomain(String site_id) {
        return "http://" + SiteDomainManager.getSiteDomainBySiteID(site_id);
    }

    public static List<CateCurPositionBean> getCategoryPosition(String cat_id, String site_id, String node_id, String page_type) {
        if(node_id != null && !"".equals(node_id) && !"$node_id".equals(node_id)) {
            site_id = node_id;
        }

        return CategoryUtil.getCategoryPosition(cat_id, site_id, page_type);
    }

    public static CategoryBean getCategoryObject(String cat_id, String site_id, String node_id) {
        if(node_id != null && !"".equals(node_id) && !"$node_id".equals(node_id)) {
            site_id = node_id;
        }

        CategoryBean cb = CategoryManager.getCategoryBeanCatID(Integer.parseInt(cat_id), site_id);
        if(cb != null) {
            cb.setIs_sub(CategoryManager.isHasChildNode(cb.getCat_id(), cb.getSite_id()));
        }

        return cb;
    }

    public static List<CategoryBean> getChildCategoryList(String cat_id, String site_id) {
        return CategoryManager.getChildCategoryListForBrowser(Integer.parseInt(cat_id), site_id);
    }

    public static List<AppCatalogBean> getChildGKAppCatalogList(String cata_id) {
        return AppCatalogManager.getChildCatalogList(Integer.parseInt(cata_id));
    }

    public static List<SMCategoryBean> getGKCategoryList(String node_id) {
        CategoryBean cb = CategoryManager.getCategoryBeanCatID(0, node_id);
        return CategoryManager.getAllChildForSMCategoryBean(cb).getSm_list();
    }

    public static String getGKNodeName(String node_id) {
        return GKNodeManager.getNodeNameByNodeID(node_id);
    }

    public static String getGKCategoryTree(String node_id) {
        List list = CategoryManager.getCategoryListBySiteID(node_id, 0);
        return "[" + CategoryBrowserTreeUtil.getBroCategoryTreeJsonStrHandl(list) + "]";
    }

    public static List<GKNodeCategory> getGKNodeList(String cate_id) {
        int cat_id = 0;
        if(cate_id != null && !"".equals(cate_id.trim())) {
            cat_id = Integer.parseInt(cate_id);
        }

        return GKNodeCateManager.getNodeListForCatID(cat_id);
    }

    public static String getGKAppCatalogTree(String cata_id) {
        return AppCatalogManager.getAppCatalogTree(Integer.parseInt(cata_id));
    }

    public static ArticleBean getArticleObject(String info_id) {
        return (ArticleBean)ModelUtil.select(info_id, "", "article");
    }

    public static Map getArticleCustomObject(String info_id) {
        return (Map)ModelUtil.select(info_id, "", "article_custom");
    }

    public static String getSiteName(String site_id) {
        if(site_id != null && !"".equals(site_id)) {
            SiteBean sb = SiteManager.getSiteBeanBySiteID(site_id);
            return sb != null?sb.getSite_name():"";
        } else {
            return "";
        }
    }

    public static List<InfoCountBean> getGKPubInfoCount(int row_count) {
        HashMap m = new HashMap();
        return InfoBaseManager.getInfoTotalForApp(row_count, "zwgk", m);
    }

    public static List<InfoCountBean> getGKPubInfoCount(String params) {
        HashMap m = new HashMap();
        int row_count = getPubInfoCountConMap(params, m);
        return InfoBaseManager.getInfoTotalForApp(row_count, "zwgk", m);
    }

    public static int getPubInfoCountConMap(String params, Map<String, String> m) {
        int row_count = 10;
        String[] tempA = params.split(";");

        for(int i = 0; i < tempA.length; ++i) {
            String count_type;
            if(tempA[i].toLowerCase().startsWith("row_count=")) {
                count_type = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));
                if(!"".equals(count_type) && !count_type.startsWith("$row_count") && FormatUtil.isValiditySQL(count_type)) {
                    row_count = Integer.parseInt(count_type);
                }
            }

            if(tempA[i].toLowerCase().startsWith("count_type=")) {
                count_type = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));
                if(!"".equals(count_type) && !count_type.startsWith("$count_type") && FormatUtil.isValiditySQL(count_type)) {
                    if("ultimo".equals(count_type)) {
                        String dt = "";
                        String d = DateUtil.getCurrentDate();
                        String[] dateA = d.split("-");
                        int year = Integer.parseInt(dateA[0]);
                        int month = Integer.parseInt(dateA[1]);
                        if(month == 1) {
                            --year;
                            month = 12;
                        } else {
                            --month;
                        }

                        if(month < 10) {
                            dt = year + "-0" + month;
                        } else {
                            dt = year + "-" + month;
                        }

                        m.put("count_month", dt);
                    }

                    if("instant".equals(count_type)) {
                        m.put("count_month", DateUtil.getCurrentDateTime("yyyy-MM"));
                    }
                }
            }
        }

        return row_count;
    }

    public static List<InfoCountBean> getSitePubInfoCount(int row_count) {
        HashMap m = new HashMap();
        return InfoBaseManager.getInfoTotalForApp(row_count, "cms", m);
    }

    public static List<InfoCountBean> getSitePubInfoCount(String params) {
        HashMap m = new HashMap();
        int row_count = getPubInfoCountConMap(params, m);
        return InfoBaseManager.getInfoTotalForApp(row_count, "cms", m);
    }

    public static List<SiteCountBean> getSiteInfoCountList(String param) {
        String[] tempA = param.split(";");
        HashMap m = new HashMap();

        for(int i = 0; i < tempA.length; ++i) {
            String count_type;
            if(tempA[i].toLowerCase().startsWith("site_id=")) {
                count_type = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));
                if(!"".equals(count_type) && !count_type.startsWith("$site_id") && FormatUtil.isValiditySQL(count_type)) {
                    m.put("site_ids", count_type);
                }
            }

            if(tempA[i].toLowerCase().startsWith("start_time=")) {
                count_type = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));
                if(!"".equals(count_type) && !count_type.startsWith("$start_time") && FormatUtil.isValiditySQL(count_type)) {
                    m.put("start_day", count_type);
                }
            }

            if(tempA[i].toLowerCase().startsWith("end_time=")) {
                count_type = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));
                if(!"".equals(count_type) && !count_type.startsWith("$end_time") && FormatUtil.isValiditySQL(count_type)) {
                    m.put("end_day", count_type);
                }
            }

            if(tempA[i].toLowerCase().startsWith("row_count=")) {
                count_type = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));
                if(!"".equals(count_type) && !count_type.startsWith("$row_count") && FormatUtil.isValiditySQL(count_type)) {
                    m.put("num", count_type);
                }
            }

            if(tempA[i].toLowerCase().startsWith("count_type=")) {
                count_type = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));
                if(!"".equals(count_type) && !count_type.startsWith("$count_type") && FormatUtil.isValiditySQL(count_type)) {
                    if("ultimo".equals(count_type)) {
                        m.put("atype", "lastmonth");
                    }

                    if("instant".equals(count_type)) {
                        m.put("atype", "currmonth");
                    }
                }
            }
        }

        if(!m.containsKey("num")) {
            m.put("num", "20");
        }

        return BrowserAPIService.getSiteCountListByMap(m);
    }

    public static List<SiteCountBean> getSiteCountListForDept(String params) {
        int row_count = 10;
        String[] tempA = params.split(";");
        HashMap m = new HashMap();

        for(int i = 0; i < tempA.length; ++i) {
            String cate_ids;
            if(tempA[i].toLowerCase().startsWith("site_id=")) {
                cate_ids = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));
                if(!"".equals(cate_ids) && !cate_ids.startsWith("$site_id") && FormatUtil.isValiditySQL(cate_ids)) {
                    m.put("site_ids", cate_ids);
                }
            }

            if(tempA[i].toLowerCase().startsWith("start_day=")) {
                cate_ids = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));
                if(!"".equals(cate_ids) && !cate_ids.startsWith("$start_day") && FormatUtil.isValiditySQL(cate_ids)) {
                    m.put("start_day", cate_ids);
                }
            }

            if(tempA[i].toLowerCase().startsWith("end_day=")) {
                cate_ids = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));
                if(!"".equals(cate_ids) && !cate_ids.startsWith("$end_day") && FormatUtil.isValiditySQL(cate_ids)) {
                    m.put("end_day", cate_ids);
                }
            }

            if(tempA[i].toLowerCase().startsWith("row_count=")) {
                cate_ids = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));
                if(!"".equals(cate_ids) && !cate_ids.startsWith("$row_count") && FormatUtil.isValiditySQL(cate_ids)) {
                    row_count = Integer.parseInt(cate_ids);
                }
            }

            if(tempA[i].toLowerCase().startsWith("count_type=")) {
                cate_ids = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));
                if(!"".equals(cate_ids) && !cate_ids.startsWith("$count_type") && FormatUtil.isValiditySQL(cate_ids)) {
                    if("ultimo".equals(cate_ids)) {
                        m.put("atype", "lastmonth");
                    }

                    if("instant".equals(cate_ids)) {
                        m.put("atype", "currmonth");
                    }
                }
            }

            if(tempA[i].toLowerCase().startsWith("cate_ids=")) {
                cate_ids = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));
                if(!"".equals(cate_ids) && !cate_ids.startsWith("$cate_ids") && FormatUtil.isValiditySQL(cate_ids)) {
                    m.put("cate_ids", cate_ids);
                }
            }
        }

        m.put("num", Integer.valueOf(row_count));
        return BrowserAPIService.getDeptNameListByMap(m);
    }

    public static List<InfoCountBean> getSiteAccessStatistics(String item_name, int row_count) {
        return InfoBaseManager.getSiteAccessStatistics(item_name, row_count, "cms");
    }

    public static List<InfoCountBean> getGKAccessStatistics(String item_name, int row_count) {
        return InfoBaseManager.getSiteAccessStatistics(item_name, row_count, "zwgk");
    }

    public static List<InfoCountBean> getInfoTotalForSiteUser(String site_id, int row_count) {
        return InfoBaseManager.getInfoTotalForSiteUser(site_id, row_count);
    }

    public static String getGKPublishStatistics(String type) {
        return GKInfoManager.getGKPublishStatistics(type);
    }

    public static String getYsqStatistics() {
        HashMap m = new HashMap();
        return YsqgkInfoManager.getYsqStatistics(m);
    }

    public static List<InfoBean> getRssInfoList(String params) {
        String[] tempA = params.split(";");
        String rss_url = "";
        int cur_page = 1;
        int size = 10;

        for(int i = 0; i < tempA.length; ++i) {
            String sizeStr;
            if(tempA[i].toLowerCase().startsWith("rss_url=")) {
                sizeStr = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));
                if(!"".equals(sizeStr) && !rss_url.startsWith("$rss_url")) {
                    rss_url = sizeStr;
                }
            }

            if(tempA[i].toLowerCase().startsWith("cur_page=")) {
                sizeStr = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));
                if(!"".equals(sizeStr) && !sizeStr.startsWith("$cur_page")) {
                    cur_page = Integer.parseInt(sizeStr);
                }
            }

            if(tempA[i].toLowerCase().startsWith("size=")) {
                sizeStr = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));
                if(!"".equals(sizeStr) && !sizeStr.startsWith("$size")) {
                    size = Integer.parseInt(sizeStr);
                }
            }
        }

        return RssReaderManager.getRssInfoList(rss_url, cur_page, size);
    }

    public static TurnPageBean getRssInfoCount(String params) {
        String[] tempA = params.split(";");
        String rss_url = "";
        int cur_page = 1;
        int size = 10;

        int count;
        for(count = 0; count < tempA.length; ++count) {
            String tpb;
            if(tempA[count].toLowerCase().startsWith("rss_url=")) {
                tpb = FormatUtil.formatNullString(tempA[count].substring(tempA[count].indexOf("=") + 1));
                if(!"".equals(tpb) && !rss_url.startsWith("$rss_url")) {
                    rss_url = tpb;
                }
            }

            if(tempA[count].toLowerCase().startsWith("cur_page=")) {
                tpb = FormatUtil.formatNullString(tempA[count].substring(tempA[count].indexOf("=") + 1));
                if(!"".equals(tpb) && !tpb.startsWith("$cur_page")) {
                    cur_page = Integer.parseInt(tpb);
                }
            }

            if(tempA[count].toLowerCase().startsWith("size=")) {
                tpb = FormatUtil.formatNullString(tempA[count].substring(tempA[count].indexOf("=") + 1));
                if(!"".equals(tpb) && !tpb.startsWith("$size")) {
                    size = Integer.parseInt(tpb);
                }
            }
        }

        count = RssReaderManager.getRssInfoCount(rss_url);
        TurnPageBean var7 = new TurnPageBean();
        var7.setCount(count);
        var7.setCur_page(cur_page);
        var7.setPage_size(size);
        var7.setPage_count(var7.getCount() / var7.getPage_size() + 1);
        if(var7.getCount() % var7.getPage_size() == 0 && var7.getPage_count() > 1) {
            var7.setPage_count(var7.getPage_count() - 1);
        }

        if(cur_page > 1) {
            var7.setPrev_num(cur_page - 1);
        }

        var7.setNext_num(var7.getPage_count());
        if(cur_page < var7.getPage_count()) {
            var7.setNext_num(cur_page + 1);
        }

        return var7;
    }

    public static List<YsqgkListBean> getYsqgkList(String params) {
        HashMap con_map = new HashMap();
        getYsqgkSeachCon(params, con_map);
        return YsqgkInfoDAO.getYsqgkLists(con_map);
    }

    public static TurnPageBean getYsqgkCount(String params) {
        HashMap con_map = new HashMap();
        getYsqgkSeachCon(params, con_map);
        TurnPageBean tpb = new TurnPageBean();
        tpb.setCount(YsqgkInfoDAO.getYsqgkListsCount(con_map));
        int cur_page = Integer.parseInt((String)con_map.get("current_page"));
        int page_size = Integer.parseInt((String)con_map.get("page_size"));
        tpb.setCur_page(cur_page);
        tpb.setPage_size(page_size);
        tpb.setPage_count(tpb.getCount() / tpb.getPage_size() + 1);
        if(tpb.getCount() % tpb.getPage_size() == 0 && tpb.getPage_count() > 1) {
            tpb.setPage_count(tpb.getPage_count() - 1);
        }

        if(cur_page > 1) {
            tpb.setPrev_num(cur_page - 1);
        }

        tpb.setNext_num(tpb.getPage_count());
        if(cur_page < tpb.getPage_count()) {
            tpb.setNext_num(cur_page + 1);
        }

        return tpb;
    }

    public static YsqgkBean getYsqObject(String ysq_id) {
        return YsqgkInfoManager.getYsqgkBean(ysq_id);
    }

    public static YsqgkBean getYsqgkObject(String ysq_code, String query_code) {
        return YsqgkInfoManager.getYsqgkBeanForQuery(ysq_code, query_code);
    }

    public static void getYsqgkSeachCon(String params, Map<String, String> con_map) {
        int cur_page = 1;
        int page_size = 15;
        String orderby = "ysq_id desc";
        String[] tempA = params.split(";");

        for(int oy = 0; oy < tempA.length; ++oy) {
            String sizeStr;
            if(tempA[oy].toLowerCase().startsWith("orderby=")) {
                sizeStr = FormatUtil.formatNullString(tempA[oy].substring(tempA[oy].indexOf("=") + 1));
                if(!"".equals(sizeStr) && !sizeStr.startsWith("$orderby") && FormatUtil.isValiditySQL(sizeStr)) {
                    orderby = sizeStr;
                }
            }

            if(tempA[oy].toLowerCase().startsWith("node_id=")) {
                sizeStr = FormatUtil.formatNullString(tempA[oy].substring(tempA[oy].indexOf("=") + 1));
                if(!"".equals(sizeStr) && !sizeStr.startsWith("$node_id") && FormatUtil.isValiditySQL(sizeStr)) {
                    con_map.put("node_id", sizeStr);
                }
            }
            
            if(tempA[oy].toLowerCase().startsWith("offer_type=")) {
            	String offer_type = FormatUtil.formatNullString(tempA[oy].substring(tempA[oy].indexOf("=") + 1));
                if(!"".equals(offer_type) && !offer_type.startsWith("$offer_type") && FormatUtil.isValiditySQL(offer_type)) {
                    con_map.put("offer_type", offer_type);
                }
            }

            if(tempA[oy].toLowerCase().startsWith("publish_state=")) {
                sizeStr = FormatUtil.formatNullString(tempA[oy].substring(tempA[oy].indexOf("=") + 1));
                if(!"".equals(sizeStr) && !sizeStr.startsWith("$publish_state") && FormatUtil.isValiditySQL(sizeStr)) {
                    con_map.put("publish_state", sizeStr);
                }
            }

            if(tempA[oy].toLowerCase().startsWith("do_state=")) {
                sizeStr = FormatUtil.formatNullString(tempA[oy].substring(tempA[oy].indexOf("=") + 1));
                if(!"".equals(sizeStr) && !sizeStr.startsWith("$do_state") && FormatUtil.isValiditySQL(sizeStr)) {
                    con_map.put("do_state", sizeStr);
                }
            }

            if(tempA[oy].toLowerCase().startsWith("start_time=")) {
                sizeStr = FormatUtil.formatNullString(tempA[oy].substring(tempA[oy].indexOf("=") + 1));
                if(!"".equals(sizeStr) && !sizeStr.startsWith("$start_time") && FormatUtil.isValiditySQL(sizeStr)) {
                    con_map.put("start_time", sizeStr);
                }
            }

            if(tempA[oy].toLowerCase().startsWith("end_time=")) {
                sizeStr = FormatUtil.formatNullString(tempA[oy].substring(tempA[oy].indexOf("=") + 1));
                if(!"".equals(sizeStr) && !sizeStr.startsWith("$end_time") && FormatUtil.isValiditySQL(sizeStr)) {
                    con_map.put("end_time", sizeStr);
                }
            }

            if(tempA[oy].toLowerCase().startsWith("cur_page=")) {
                sizeStr = FormatUtil.formatNullString(tempA[oy].substring(tempA[oy].indexOf("=") + 1));
                if(!"".equals(sizeStr) && !sizeStr.startsWith("$cur_page")) {
                    cur_page = Integer.parseInt(sizeStr);
                }
            }

            if(tempA[oy].toLowerCase().startsWith("size=")) {
                sizeStr = FormatUtil.formatNullString(tempA[oy].substring(tempA[oy].indexOf("=") + 1));
                if(!"".equals(sizeStr) && !sizeStr.startsWith("$size")) {
                    page_size = Integer.parseInt(sizeStr);
                }
            }
        }

        con_map.put("final_status", "0");
        con_map.put("start_num", String.valueOf((cur_page - 1) * page_size));
        con_map.put("page_size", String.valueOf(page_size));
        con_map.put("current_page", String.valueOf(cur_page));
        String[] var8 = orderby.split(" ");
        con_map.put("sort_name", var8[0]);
        con_map.put("sort_type", var8[1]);
    }

    public static List<SiteInfoTuisongBean> getTuiSongCountsList(String params) {
        HashMap con_map = new HashMap();
        String[] tempA = params.split(";");

        for(int i = 0; i < tempA.length; ++i) {
            String count_type;
            if(tempA[i].toLowerCase().startsWith("app_id=")) {
                count_type = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));
                if(!"".equals(count_type) && !count_type.startsWith("$app_id") && FormatUtil.isValiditySQL(count_type)) {
                    con_map.put("app_id", count_type);
                }
            }

            if(tempA[i].toLowerCase().startsWith("site_id=")) {
                count_type = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));
                if(!"".equals(count_type) && !count_type.startsWith("$site_id") && FormatUtil.isValiditySQL(count_type)) {
                    con_map.put("site_id", count_type);
                }
            }

            if(tempA[i].toLowerCase().startsWith("start_time=")) {
                count_type = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));
                if(!"".equals(count_type) && !count_type.startsWith("$start_time") && FormatUtil.isValiditySQL(count_type)) {
                    con_map.put("start_time", count_type);
                }
            }

            if(tempA[i].toLowerCase().startsWith("end_time=")) {
                count_type = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));
                if(!"".equals(count_type) && !count_type.startsWith("$end_time") && FormatUtil.isValiditySQL(count_type)) {
                    con_map.put("end_time", count_type);
                }
            }

            if(tempA[i].toLowerCase().startsWith("count_type=")) {
                count_type = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));
                if(!"".equals(count_type) && !count_type.startsWith("$count_type") && FormatUtil.isValiditySQL(count_type)) {
                    if("ultimo".equals(count_type)) {
                        con_map.put("atype", "lastmonth");
                    }

                    if("instant".equals(count_type)) {
                        con_map.put("atype", "currmonth");
                    }
                }
            }
        }

        return TuisongCountManager.getOneSiteTuisCounts(con_map);
    }

    public static List<WcmZykFile> getZyFileListByInfoId(String info_id, String fieldName) {
        return WcmZykInfoService.getZykinfoFileListByInfoId(info_id, fieldName);
    }

    public static List<CmsCountBean> getSiteCountListForSource(String params) {
        int row_count = 10;
        String[] tempA = params.split(";");
        HashMap m = new HashMap();

        for(int i = 0; i < tempA.length; ++i) {
            String cate_ids;
            if(tempA[i].toLowerCase().startsWith("site_id=")) {
                cate_ids = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));
                if(!"".equals(cate_ids) && !cate_ids.startsWith("$site_id") && FormatUtil.isValiditySQL(cate_ids)) {
                    m.put("site_ids", cate_ids);
                }
            }

            if(tempA[i].toLowerCase().startsWith("start_day=")) {
                cate_ids = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));
                if(!"".equals(cate_ids) && !cate_ids.startsWith("$start_day") && FormatUtil.isValiditySQL(cate_ids)) {
                    m.put("start_day", cate_ids);
                }
            }

            if(tempA[i].toLowerCase().startsWith("end_day=")) {
                cate_ids = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));
                if(!"".equals(cate_ids) && !cate_ids.startsWith("$end_day") && FormatUtil.isValiditySQL(cate_ids)) {
                    m.put("end_day", cate_ids);
                }
            }

            if(tempA[i].toLowerCase().startsWith("row_count=")) {
                cate_ids = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));
                if(!"".equals(cate_ids) && !cate_ids.startsWith("$row_count") && FormatUtil.isValiditySQL(cate_ids)) {
                    row_count = Integer.parseInt(cate_ids);
                }
            }

            if(tempA[i].toLowerCase().startsWith("count_type=")) {
                cate_ids = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));
                if(!"".equals(cate_ids) && !cate_ids.startsWith("$count_type") && FormatUtil.isValiditySQL(cate_ids)) {
                    if("ultimo".equals(cate_ids)) {
                        m.put("atype", "lastmonth");
                    }

                    if("instant".equals(cate_ids)) {
                        m.put("atype", "currmonth");
                    }
                }
            }

            if(tempA[i].toLowerCase().startsWith("cate_ids=")) {
                cate_ids = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));
                if(!"".equals(cate_ids) && !cate_ids.startsWith("$cate_ids") && FormatUtil.isValiditySQL(cate_ids)) {
                    m.put("cate_ids", cate_ids);
                }
            }
        }

        m.put("num", Integer.valueOf(row_count));
        return BrowserAPIService.getSourceNameListByMap(m);
    }

    public static List<InfoAccessBean> getOrderInfoListsForBor(String param) {
        String[] tempA = param.split(";");
        HashMap m = new HashMap();

        for(int i = 0; i < tempA.length; ++i) {
            String type;
            if(tempA[i].toLowerCase().startsWith("site_id=")) {
                type = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));
                if(!"".equals(type) && !type.startsWith("$site_id") && FormatUtil.isValiditySQL(type)) {
                    m.put("site_id", type);
                }
            }

            if(tempA[i].toLowerCase().startsWith("start_time=")) {
                type = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));
                if(!"".equals(type) && !type.startsWith("$start_time") && FormatUtil.isValiditySQL(type)) {
                    m.put("start_day", type);
                }
            }

            if(tempA[i].toLowerCase().startsWith("end_time=")) {
                type = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));
                if(!"".equals(type) && !type.startsWith("$end_time") && FormatUtil.isValiditySQL(type)) {
                    m.put("end_day", type);
                }
            }

            if(tempA[i].toLowerCase().startsWith("row_count=")) {
                type = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));
                if(!"".equals(type) && !type.startsWith("$row_count") && FormatUtil.isValiditySQL(type)) {
                    m.put("row_count", type);
                }
            }

            if(tempA[i].toLowerCase().startsWith("type=")) {
                type = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));
                if(!"".equals(type) && !type.startsWith("$type") && FormatUtil.isValiditySQL(type)) {
                    m.put("type", type);
                }
            }
        }

        if(((String)m.get("type")).equals("cate")) {
            return AccessCountManager.getCatOrderListByCat_id(m);
        } else {
            return AccessCountManager.getInfoOrderListByInfo_id(m);
        }
    }

    public static VideoBean getVideoBeanByInfoId(String info_id, String site_id) {
        return (VideoBean)ModelUtil.select(info_id, site_id, "video");
    }

    public static PicBean getPicBeanByInfoId(String info_id, String site_id) {
        return (PicBean)ModelUtil.select(info_id, site_id, "pic");
    }

    public static Map<String, String> getDayAccessCountList(String site_id, String constant) {
        return AccessCountManager.getDayAccessCountList(site_id, constant);
    }

    public static long getOnline(String site_id) {
        return OnlineCounter.getOnline(site_id);
    }

    public static List<InfoBean> getCateAllInfoList(String params) {
        HashMap con_map = new HashMap();
        getInfoSearchCon(params, con_map, "cms");
        return removeHTMLTag(InfoBaseManager.getBroInfoList(con_map));
    }

    public static List<CategoryBean> getChildCategoryList2(String cat_id, String site_id) {
        return CategoryManager.getChildCategoryListForBrowser2(Integer.parseInt(cat_id), site_id);
    }

    public static String getStaticListPageUrl(String cat_id) {
        return InfoExpandManager.getStaticListPageUrl(cat_id);
    }

    public static GKFbsznBean getGKFbsznObject(String info_id) {
        return (GKFbsznBean)ModelUtil.select(info_id, "", "gkfbszn");
    }

    public static Object getInfoObject(String info_id) {
        InfoBean ib = InfoBaseManager.getInfoById(info_id);
        return ib != null?ModelUtil.select(info_id, ib.getSite_id(), ModelManager.getModelEName(ib.getModel_id())):null;
    }

    public static AppCatalogBean getGKAppCatalogBean(String cata_id) {
        return AppCatalogManager.getAppCatalogBean(Integer.parseInt(cata_id));
    }

    public static List<RelatedInfoBean> getRelatedInfoListByID(String info_id) {
        HashMap m = new HashMap();
        m.put("info_id", info_id);
        List l = InfoBaseManager.getRelatedInfoList(m);
        if(l != null && l.size() > 0) {
            Iterator var4 = l.iterator();

            while(var4.hasNext()) {
                RelatedInfoBean info = (RelatedInfoBean)var4.next();
                info.setTitle(info.getTitle().replaceAll("<[Bb][Rr]/?>", ""));
            }
        }

        return l;
    }

    public static GKNodeBean getGKNodeBeanByNodeID(String node_id) {
        return GKNodeManager.getGKNodeBeanByNodeID(node_id);
    }

    public static void main(String[] args) {
        float a = Float.parseFloat("0.8") * 100.0F;
        System.out.println((int)a);
    }
}
