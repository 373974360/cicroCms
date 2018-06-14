package com.cicro.project.weinanzjzf;

import com.cicro.wcm.db.DBManager;

import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Author: like
 * @Date: 2018-06-14 11:54
 * @Version: 1.0
 * @Created in idea by autoCode
 */
public class ZJZFDAO {
    public ZJZFDAO() {
    }

    public static List<ZJZFBean> getGongMinList(Map<String, String> m) {
        return DBManager.queryFList("getGongMinList", m);
    }

    public static String getGongMinListCount(Map<String, String> m) {
        return DBManager.getString("getGongMinListCount", m);
    }

    public static ZJZFBean getGongMinBean(int id) {
        return (ZJZFBean)DBManager.queryFObj("getGongMinBean", id);
    }

    public static boolean insertZJZF(ZJZFBean zjzf) {
        return DBManager.insert("insert_wnzjzf_gongmin", zjzf);
    }

    public static boolean deleteZJZF(Map<String, String> m) {
        return DBManager.delete("delete_wnzjzf_gongmin", m);
    }
}
