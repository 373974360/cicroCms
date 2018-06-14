package com.cicro.project.weinanzjzf;

import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Author: like
 * @Date: 2018-06-14 11:54
 * @Version: 1.0
 * @Created in idea by autoCode
 */
public class ZJZFRPC {
    public ZJZFRPC() {
    }

    public static List<ZJZFBean> getGongMinList(Map<String, String> m) {
        return ZJZFManager.getGongMinList(m);
    }

    public static String getGongMinListCount(Map<String, String> m) {
        return ZJZFManager.getGongMinListCount(m);
    }

    public static ZJZFBean getGongMinBean(int id) {
        return ZJZFManager.getGongMinBean(id);
    }

    public static boolean insertZJZF(ZJZFBean zjzf) {
        return ZJZFManager.insertZJZF(zjzf);
    }

    public static boolean deleteZJZF(Map<String, String> m) {
        return ZJZFManager.deleteZJZF(m);
    }
}
