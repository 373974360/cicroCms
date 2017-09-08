package com.cicro.project.dz_pxxx;

import com.cicro.util.FormatUtil;
import com.cicro.wcm.bean.logs.SettingLogsBean;
import com.cicro.wcm.dao.PublicTableDAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PxxxBmManager {
    public static String getPxxxBmCount(Map<String, String> m) {
        if (m.containsKey("key_word")) {
            if (!FormatUtil.isValiditySQL((String) m.get("key_word")))
                return "0";
        }
        return PxxxBmDAO.getPxxxBmCount(m);
    }

    public static List<PxxxBmBean> getPxxxBmList(Map<String, String> m) {

        if (m.containsKey("key_word")) {
            if (!FormatUtil.isValiditySQL((String) m.get("key_word")))
                return new ArrayList();
        }
        return PxxxBmDAO.getPxxxBmList(m);
    }

    public static List<PxxxBmBean> getAllPxxxBmList() {
        return PxxxBmDAO.getAllPxxxBmList();
    }

    public static List<PxxxBmBean> getAllPxxxBmByPxID(Map<String, String> m) {
        return PxxxBmDAO.getAllPxxxBmByPxID(m);
    }

    public static PxxxBmBean getPxxxBmBean(String id) {
        return PxxxBmDAO.getPxxxBmBean(id);
    }

    public static List<PxxxBmBean> isExistByPxIdAndNameId(Map<String, String> m){
        return PxxxBmDAO.isExistByPxIdAndNameId(m);
    }

    public static PxxxBmBean insertPxxxBm(PxxxBmBean hb, SettingLogsBean stl) {
        hb.setId(PublicTableDAO.getIDByTableName("dz_pxxx"));
        PxxxBmDAO.insertPxxxBm(hb, stl);
        return hb;
    }

    public static PxxxBmBean updatePxxxBm(PxxxBmBean hb, SettingLogsBean stl) {
        PxxxBmDAO.updatePxxxBm(hb, stl);
        return hb;
    }

    public static boolean deletePxxxBm(Map<String, String> m, SettingLogsBean stl) {
        return PxxxBmDAO.deletePxxxBm(m, stl);
    }
}
