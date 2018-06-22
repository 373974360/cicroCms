package com.cicro.project.dz_dxsjx;

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
public class DxsjxCategoryManager {
    public DxsjxCategoryManager() {
    }

    public static List<DxsjxCategoryBean> getDxsjxCategoryList(Map<String, String> m) {
        return DxsjxCategoryDAO.getDxsjxCategoryList(m);
    }

    public static String getDxsjxCategoryListCount(Map<String, String> m) {
        return DxsjxCategoryDAO.getDxsjxCategoryListCount(m);
    }

    public static DxsjxCategoryBean getDxsjxCategoryBean(int id) {
        return DxsjxCategoryDAO.getDxsjxCategoryBean(id);
    }

    public static boolean insertDxsjxCategory(DxsjxCategoryBean dxsjx) {
        dxsjx.setId(PublicTableDAO.getIDByTableName("dz_dxsjx_category"));
        dxsjx.setAdd_time(DateUtil.getCurrentDateTime());
        return DxsjxCategoryDAO.insertDxsjxCategory(dxsjx);
    }

    public static boolean deleteDxsjxCategory(Map<String, String> m) {
        return DxsjxCategoryDAO.deleteDxsjxCategory(m);
    }

    public static boolean updateDxsjxCategory(Map<String, String> m) {
        return DxsjxCategoryDAO.updateDxsjxCategory(m);
    }

    public static boolean updateDxsjxCategoryStatus(Map<String, String> m) {
        return DxsjxCategoryDAO.updateDxsjxCategoryStatus(m);
    }
}
