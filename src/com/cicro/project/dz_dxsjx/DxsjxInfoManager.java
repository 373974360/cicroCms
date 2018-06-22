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
public class DxsjxInfoManager {
    public DxsjxInfoManager() {
    }

    public static List<DxsjxInfoBean> getDxsjxList(Map<String, String> m) {
        return DxsjxInfoDAO.getDxsjxList(m);
    }

    public static String getDxsjxListCount(Map<String, String> m) {
        return DxsjxInfoDAO.getDxsjxListCount(m);
    }

    public static DxsjxInfoBean getDxsjxBean(int id) {
        return DxsjxInfoDAO.getDxsjxBean(id);
    }

    public static boolean insertDxsjx(DxsjxInfoBean dxsjx) {
        dxsjx.setId(PublicTableDAO.getIDByTableName("dz_dxsjx_info"));
        dxsjx.setAdd_time(DateUtil.getCurrentDateTime());
        return DxsjxInfoDAO.insertDxsjx(dxsjx);
    }

    public static boolean deleteDxsjx(Map<String, String> m) {
        return DxsjxInfoDAO.deleteDxsjx(m);
    }

    public static boolean updateDxsjx(Map<String, String> m) {
        return DxsjxInfoDAO.updateDxsjx(m);
    }
}
