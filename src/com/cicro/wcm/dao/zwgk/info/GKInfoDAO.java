//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.cicro.wcm.dao.zwgk.info;

import com.cicro.util.BeanToMapUtil;
import com.cicro.wcm.bean.cms.info.GKFbsznBean;
import com.cicro.wcm.bean.cms.info.GKInfoBean;
import com.cicro.wcm.bean.cms.info.GKResFileBean;
import com.cicro.wcm.bean.cms.info.RelatedInfoBean;
import com.cicro.wcm.dao.PublicTableDAO;
import com.cicro.wcm.db.DBManager;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

public class GKInfoDAO {
    public GKInfoDAO() {
    }

    public static String getBroGKInfoCountForSharedCategory(Map<String, String> m) {
        return DBManager.getString("getBroGKInfoCountForSharedCategory", m);
    }

    public static List<GKInfoBean> getBroGKInfoListForSharedCategory(Map<String, String> m) {
        return DBManager.queryFList("getBroGKInfoListForSharedCategory", m);
    }

    public static List<GKInfoBean> getBroGKInfoList(Map<String, String> m) {
        return DBManager.queryFList("getBroGKInfoList", m);
    }

    public static List<GKFbsznBean> getBroGKBSZNInfoList(Map<String, String> m) {
        return DBManager.queryFList("getBroGKBSZNInfoList", m);
    }

    public static String getBroGKInfoCount(Map<String, String> m) {
        return DBManager.getString("getBroGKInfoCount", m);
    }

    public static GKInfoBean getGKInfoBean(String info_id) {
        return (GKInfoBean)DBManager.queryFObj("getGKInfoBean", info_id);
    }

    public static List<RelatedInfoBean> getReleGKInfoListForAuto(Map<String, String> map) {
        return DBManager.queryFList("getReleGKInfoListForAuto", map);
    }

    public static List<GKInfoBean> getGKInfoList(Map<String, String> m) {
        return DBManager.queryFList("getGKInfoList", m);
    }

    public static String getInfoIdForGKIndex(String gk_index) {
        return DBManager.getString("getInfoIdForGKIndex", gk_index);
    }

    public static String getGKInfoCount(Map<String, String> m) {
        return DBManager.getString("getGKInfoCount", m);
    }

    public static boolean insertGKInfo(Object ob) {
        if(DBManager.insert("insert_gk_info", ob)) {
        	GKInfoBean gif = null;
            //如果ob是java.util.HashMap的则说明 是自定义表的数据  不处理
            if(ob instanceof HashMap){
                try {
                    gif = (GKInfoBean) BeanToMapUtil.convertMap(GKInfoBean.class,(HashMap)ob);
                } catch (Exception e) {
                    System.out.println("信息类型转换错误！！！！！！！");
                    e.printStackTrace();
                }
            }
            else{
                gif = (GKInfoBean)ob;
            }
            insertGKResFile(gif.getInfo_id(), gif.getFile_list());
            return true;
        } else {
            return false;
        }
    }

    public static boolean updateGKInfo(Object ob) {
        if(DBManager.update("update_gk_info", ob)) {
            try {
                String e = BeanUtils.getProperty(ob, "from_id");
                if("0".equals(e)) {
                    GKInfoBean gif = (GKInfoBean)ob;
                    insertGKResFile(gif.getInfo_id(), gif.getFile_list());
                }
            } catch (IllegalAccessException var3) {
                var3.printStackTrace();
            } catch (InvocationTargetException var4) {
                var4.printStackTrace();
            } catch (NoSuchMethodException var5) {
                var5.printStackTrace();
            }

            return true;
        } else {
            return false;
        }
    }

    public static boolean deleteGKInfo(String info_ids) {
        HashMap m = new HashMap();
        m.put("info_ids", info_ids);
        if(DBManager.delete("delete_gk_info", m)) {
            deleteGKResFile(info_ids);
            return true;
        } else {
            return false;
        }
    }

    public static boolean clearGKInfo(Map<String, String> m) {
        if(DBManager.delete("clear_gk_info", m)) {
            clearGKResFile(m);
            return true;
        } else {
            return false;
        }
    }

    public static boolean insertGKResFile(int info_id, List<GKResFileBean> gf_list) {
        try {
            if(!deleteGKResFile(String.valueOf(info_id))) {
                return false;
            } else {
                if(gf_list != null && gf_list.size() > 0) {
                    Iterator var3 = gf_list.iterator();

                    while(var3.hasNext()) {
                        GKResFileBean e = (GKResFileBean)var3.next();
                        e.setRes_id(PublicTableDAO.getIDByTableName(PublicTableDAO.INFO_RESFILE_TABLE_NAME));
                        DBManager.insert("insert_gk_resFile", e);
                    }
                }

                return true;
            }
        } catch (Exception var4) {
            var4.printStackTrace();
            return false;
        }
    }

    public static boolean deleteGKResFile(String info_id) {
        HashMap m = new HashMap();
        if(info_id.contains(",")) {
            m.put("info_ids", info_id);
        } else {
            m.put("info_id", info_id);
        }

        return deleteGKResFile((Map)m);
    }

    public static boolean deleteGKResFile(Map<String, String> m) {
        return DBManager.delete("delete_gk_resFile", m);
    }

    public static List<GKResFileBean> getGKResFileList(String info_id) {
        return DBManager.queryFList("getGKResFileList", info_id);
    }

    public static boolean clearGKResFile(Map<String, String> m) {
        return DBManager.delete("clear_gk_resFile", m);
    }

    public static String getGKYearStr(String info_id) {
        return DBManager.getString("getGKYearStr", info_id);
    }

    public static List<GKInfoBean> getAllGKInfoList(Map<String, String> m) {
        return DBManager.queryFList("getAllGKInfoList", m);
    }

    public static boolean updateGKIndex(String info_id, String gk_index, String gk_num) {
        HashMap m = new HashMap();
        m.put("gk_index", gk_index);
        m.put("gk_num", gk_num);
        m.put("info_id", info_id);
        return DBManager.update("update_gkinfo_index", m);
    }

    public static boolean deleteGKIndexSequence(String node_id) {
        HashMap m = new HashMap();
        if(node_id != null && !"".equals(node_id)) {
            m.put("site_id", node_id);
        }

        return DBManager.delete("delete_gk_sequence", m);
    }

    public static String getGKPublishStatistics(Map<String, String> m) {
        return DBManager.getString("getGKPublishStatistics", m);
    }
}
