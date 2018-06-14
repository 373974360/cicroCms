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
public class DxsjxManager {
    public DxsjxManager() {
    }

    public static List<DxsjxBean> getDxsjxList(Map<String, String> m) {
        return DxsjxDAO.getDxsjxList(m);
    }

    public static String getDxsjxListCount(Map<String, String> m) {
        return DxsjxDAO.getDxsjxListCount(m);
    }

    public static DxsjxBean getDxsjxBean(int id) {
        return DxsjxDAO.getDxsjxBean(id);
    }

    public static boolean insertDxsjx(DxsjxBean dxsjx) {
        dxsjx.setId(PublicTableDAO.getIDByTableName("dz_dxsjx"));
        dxsjx.setAdd_time(DateUtil.getCurrentDateTime());
        return DxsjxDAO.insertDxsjx(dxsjx);
    }

    public static boolean deleteDxsjx(Map<String, String> m) {
        return DxsjxDAO.deleteDxsjx(m);
    }
}
