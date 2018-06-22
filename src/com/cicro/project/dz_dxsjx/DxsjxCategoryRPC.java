package com.cicro.project.dz_dxsjx;

import com.cicro.util.DateUtil;
import com.cicro.wcm.bean.logs.SettingLogsBean;
import com.cicro.wcm.services.Log.LogManager;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Author: like
 * @Date: 2018-06-14 11:54
 * @Version: 1.0
 * @Created in idea by autoCode
 */
public class DxsjxCategoryRPC {
    public DxsjxCategoryRPC() {
    }

    public static List<DxsjxCategoryBean> getDxsjxCategoryList(Map<String, String> m) {
        return DxsjxCategoryManager.getDxsjxCategoryList(m);
    }

    public static String getDxsjxCategoryListCount(Map<String, String> m) {
        return DxsjxCategoryManager.getDxsjxCategoryListCount(m);
    }

    public static DxsjxCategoryBean getDxsjxCategoryBean(int id) {
        return DxsjxCategoryManager.getDxsjxCategoryBean(id);
    }

    public static boolean updateDxsjxCategory(Map<String, String> m) {
        return DxsjxCategoryManager.updateDxsjxCategory(m);
    }

    public static boolean updateDxsjxCategoryStatus(Map<String, String> m) {
        return DxsjxCategoryManager.updateDxsjxCategoryStatus(m);
    }

    public static boolean insertDxsjxCategory(DxsjxCategoryBean dxsjx) {
        return DxsjxCategoryManager.insertDxsjxCategory(dxsjx);
    }

    public static boolean deleteDxsjxCategory(Map<String, String> m) {
        return DxsjxCategoryManager.deleteDxsjxCategory(m);
    }
}
