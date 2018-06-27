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
public class DxsjxCategoryDAO {
    public DxsjxCategoryDAO() {
    }

    public static List<DxsjxCategoryBean> getDxsjxCategoryList(Map<String, String> m) {
        return DBManager.queryFList("getDxsjxCategoryList", m);
    }

    public static String getDxsjxCategoryListCount(Map<String, String> m) {
        return DBManager.getString("getDxsjxCategoryListCount", m);
    }

    public static DxsjxCategoryBean getDxsjxCategoryBean(int id) {
        return (DxsjxCategoryBean)DBManager.queryFObj("getDxsjxCategoryBean", id);
    }

    public static boolean insertDxsjxCategory(DxsjxCategoryBean dxsjx) {
        return DBManager.insert("insertDxsjxCategory", dxsjx);
    }

    public static boolean deleteDxsjxCategory(Map<String, String> m) {
        return DBManager.delete("deleteDxsjxCategory", m);
    }

    public static boolean updateDxsjxCategory(DxsjxCategoryBean dxsjx) {
        return DBManager.update("updateDxsjxCategory", dxsjx);
    }

    public static boolean updateDxsjxCategoryStatus(Map<String, String> m) {
        return DBManager.update("updateDxsjxCategoryStatus", m);
    }


}
