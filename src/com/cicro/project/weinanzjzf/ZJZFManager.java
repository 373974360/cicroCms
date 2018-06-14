package com.cicro.project.weinanzjzf;

import com.cicro.util.DateUtil;
import com.cicro.wcm.dao.PublicTableDAO;

import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Author: like
 * @Date: 2018-06-14 11:54
 * @Version: 1.0
 * @Created in idea by autoCode
 */
public class ZJZFManager {
    public ZJZFManager() {
    }

    public static List<ZJZFBean> getGongMinList(Map<String, String> m) {
        return ZJZFDAO.getGongMinList(m);
    }

    public static String getGongMinListCount(Map<String, String> m) {
        return ZJZFDAO.getGongMinListCount(m);
    }

    public static ZJZFBean getGongMinBean(int id) {
        return ZJZFDAO.getGongMinBean(id);
    }

    public static boolean insertZJZF(ZJZFBean zjzf) {
        zjzf.setId(PublicTableDAO.getIDByTableName("project_zjzf"));
        zjzf.setAdd_time(DateUtil.getCurrentDateTime());
        return ZJZFDAO.insertZJZF(zjzf);
    }

    public static boolean deleteZJZF(Map<String, String> m) {
        return ZJZFDAO.deleteZJZF(m);
    }
}
