package com.cicro.project.dz_dxsjx;

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
public class DxsjxInfoDAO {
    public DxsjxInfoDAO() {
    }

    public static List<DxsjxInfoBean> getDxsjxList(Map<String, String> m) {
        return DBManager.queryFList("getDxsjxList", m);
    }

    public static String getDxsjxListCount(Map<String, String> m) {
        return DBManager.getString("getDxsjxListCount", m);
    }

    public static DxsjxInfoBean getDxsjxBean(int id) {
        return (DxsjxInfoBean)DBManager.queryFObj("getDxsjxBean", id);
    }

    public static boolean insertDxsjx(DxsjxInfoBean dxsjx) {
        return DBManager.insert("insertDxsjx", dxsjx);
    }

    public static boolean deleteDxsjx(Map<String, String> m) {
        return DBManager.delete("deleteDxsjx", m);
    }

    public static boolean updateDxsjx(Map<String, String> m) {
        return DBManager.update("updateDxsjx", m);
    }
}
