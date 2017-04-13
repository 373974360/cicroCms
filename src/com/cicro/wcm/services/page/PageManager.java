//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.cicro.wcm.services.page;

import com.cicro.util.DateUtil;
import com.cicro.util.FormatUtil;
import com.cicro.util.io.FileOperation;
import com.cicro.wcm.bean.control.SiteBean;
import com.cicro.wcm.bean.logs.SettingLogsBean;
import com.cicro.wcm.bean.page.PageBean;
import com.cicro.wcm.catchs.ISyncCatch;
import com.cicro.wcm.catchs.SyncCatchHandl;
import com.cicro.wcm.dao.PublicTableDAO;
import com.cicro.wcm.dao.page.PageDAO;
import com.cicro.wcm.rmi.file.FileRmiFactory;
import com.cicro.wcm.services.control.site.SiteManager;
import com.cicro.wcm.template.velocity.impl.VelocityPageContextImp;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;

public class PageManager implements ISyncCatch {
    private static TreeMap<Integer, PageBean> page_map = new TreeMap();

    static {
        reloadCatchHandl();
    }

    public PageManager() {
    }

    public void reloadCatch() {
        reloadCatchHandl();
    }

    public static void reloadCatchHandl() {
        page_map.clear();
        List page_list = PageDAO.getAllPageList();
        if(page_list != null && page_list.size() > 0) {
            for(int i = 0; i < page_list.size(); ++i) {
                page_map.put(Integer.valueOf(((PageBean)page_list.get(i)).getId()), (PageBean)page_list.get(i));
            }
        }

    }

    public static void reloadPage() {
    	reloadCatchHandl();
        //SyncCatchHandl.reladCatchForRMI("com.cicro.wcm.services.page.PageManager");
    }

    public static int getNewPageID() {
        return PublicTableDAO.getIDByTableName(PublicTableDAO.PAGE_TABLE_NAME);
    }

    public static String getPageJSONTreeStr(String app_id, String site_id) {
        List pl = getPageList(app_id, site_id, 0);
        return "[" + getPageJSONTreeStrHandl(pl) + "]";
    }

    public static String getPageJSONTreeStrHandl(List<PageBean> pl) {
        String json_str = "";

        for(Iterator var3 = pl.iterator(); var3.hasNext(); json_str = json_str + "}") {
            PageBean pb = (PageBean)var3.next();
            String url = pb.getPage_path() + "/" + pb.getPage_ename() + ".htm";
            url.replaceAll("//", "/");
            json_str = json_str + ",{";
            json_str = json_str + "\"id\":" + pb.getPage_id() + ",\"text\":\"" + pb.getPage_cname() + "\",\"attributes\":{\"app_id\":\"" + pb.getApp_id() + "\",\"url\":\"" + url + "\"}";
            List child_list = pb.getChild_list();
            if(child_list != null && child_list.size() > 0) {
                json_str = json_str + ",\"children\":[" + getPageJSONTreeStrHandl(child_list) + "]";
            }
        }

        if(json_str != null && !"".equals(json_str)) {
            json_str = json_str.substring(1);
        }

        return json_str;
    }

    public static List<PageBean> getPageList(String app_id, String site_id, int parent_id) {
        ArrayList pl = new ArrayList();
        Set s = page_map.keySet();
        Iterator var6 = s.iterator();

        while(var6.hasNext()) {
            int i = ((Integer)var6.next()).intValue();
            PageBean pb = (PageBean)page_map.get(Integer.valueOf(i));
            if(pb.getApp_id().equals(app_id) && pb.getSite_id().equals(site_id)) {
                pl.add(pb);
            }
        }

        return getPageChildList(pl, parent_id);
    }

    public static List<PageBean> getPageChildList(List<PageBean> pl, int parent_id) {
        ArrayList page_list = new ArrayList();

        for(int i = 0; i < pl.size(); ++i) {
            if(parent_id == ((PageBean)pl.get(i)).getParent_id()) {
                ((PageBean)pl.get(i)).setChild_list(getPageChildList(pl, ((PageBean)pl.get(i)).getPage_id()));
                page_list.add((PageBean)pl.get(i));
            }
        }

        return page_list;
    }

    public static PageBean getPageBean(int id) {
        if(page_map.containsKey(Integer.valueOf(id))) {
            return (PageBean)page_map.get(Integer.valueOf(id));
        } else {
            PageBean pb = PageDAO.getPageBean(id);
            if(pb != null) {
                page_map.put(Integer.valueOf(id), pb);
                return pb;
            } else {
                return null;
            }
        }
    }

    public static boolean pageFileIsExist(String page_path, String page_ename, String site_id) {
        String site_path = SiteManager.getSitePath(site_id);
        String file_path = FormatUtil.formatPath(site_path + page_path + "/" + page_ename);
        File file = new File(file_path + ".htm");
        return file.exists();
    }

    public static boolean pageIsExist(String page_path, String page_ename, String site_id) {
        Set pm = page_map.keySet();
        Iterator var5 = pm.iterator();

        PageBean pb;
        do {
            if(!var5.hasNext()) {
                return false;
            }

            int i = ((Integer)var5.next()).intValue();
            pb = (PageBean)page_map.get(Integer.valueOf(i));
        } while(!page_path.equals(pb.getPage_path()) || !page_ename.equals(pb.getPage_ename()) || !site_id.equals(pb.getSite_id()));

        return true;
    }

    public static boolean insertPage(PageBean pb, SettingLogsBean stl) {
        if(pb.getPage_interval() > 0) {
            pb.setNext_dtime(DateUtil.getDateTimeAfter(DateUtil.getCurrentDateTime(), pb.getPage_interval()));
        }

        if(PageDAO.insertPage(pb, stl)) {
            reloadPage();
            return true;
        } else {
            return false;
        }
    }

    public static boolean updatePage(PageBean pb, SettingLogsBean stl) {
        deletePageFile(pb.getId());
        if(PageDAO.updatePage(pb, stl)) {
            reloadPage();
            return true;
        } else {
            return false;
        }
    }

    public static boolean deletePage(int id, SettingLogsBean stl) {
        if(PageDAO.deletePage(id, stl)) {
            deletePageFile(id);
            reloadPage();
            return true;
        } else {
            return false;
        }
    }

    public static boolean deletePageFile(int id) {
        PageBean pb = getPageBean(id);
        if(pb != null) {
            SiteBean sb = SiteManager.getSiteBeanBySiteID(pb.getSite_id());
            if(sb != null) {
                String save_path = sb.getSite_path();
                save_path = FormatUtil.formatPath(save_path + pb.getPage_path() + "/" + pb.getPage_ename() + ".htm");
                return FileRmiFactory.delFile(pb.getSite_id(), save_path);
            }
        }

        return false;
    }

    public static boolean createHtmlPage(PageBean pb) throws IOException {
        return FileRmiFactory.createPage(pb);
    }

    public static boolean createPageHandl(PageBean pb) throws IOException {
        SiteBean sb = SiteManager.getSiteBeanBySiteID(pb.getSite_id());
        if(sb != null) {
            VelocityPageContextImp vpc = new VelocityPageContextImp();
            String html_content = vpc.getHtmlContent(pb);
            String save_path = sb.getSite_path();
            save_path = FormatUtil.formatPath(save_path + pb.getPage_path());
            File f = new File(save_path);
            if(!f.exists()) {
                f.mkdirs();
            }

            save_path = FormatUtil.formatPath(save_path + "/" + pb.getPage_ename() + ".htm");
            FileOperation.writeStringToFile(save_path, html_content, false, "utf-8");
            HashMap m = new HashMap();
            m.put("last_dtime", DateUtil.getCurrentDateTime());
            m.put("id", String.valueOf(pb.getId()));
            if(pb.getPage_interval() > 0) {
                m.put("next_dtime", DateUtil.getDateTimeAfter(DateUtil.getCurrentDateTime(), pb.getPage_interval()));
            }

            PageDAO.updatePageTime(m);
            reloadPage();
            return true;
        } else {
            return false;
        }
    }

    public static boolean createHtmlPage(int id) throws IOException {
        PageBean pb = getPageBean(id);
        return pb != null?createHtmlPage(pb):false;
    }

    public static void main(String[] args) throws IOException {
        System.out.println("//index.htm".replaceAll("//", "/"));
    }
}
