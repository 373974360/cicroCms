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
public class DxsjxInfoRPC {
    public DxsjxInfoRPC() {
    }

    public static List<DxsjxInfoBean> getDxsjxList(Map<String, String> m) {
        return DxsjxInfoManager.getDxsjxList(m);
    }

    public static String getDxsjxListCount(Map<String, String> m) {
        return DxsjxInfoManager.getDxsjxListCount(m);
    }

    public static DxsjxInfoBean getDxsjxBean(int id) {
        return DxsjxInfoManager.getDxsjxBean(id);
    }

    public static boolean updateDxsjx(Map<String, String> m, HttpServletRequest request) {
        SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
        if(stl != null){
            m.put("user_id",stl.getUser_id()+"");
        }
        m.put("audit_time", DateUtil.getCurrentDateTime());
        return DxsjxInfoManager.updateDxsjx(m);
    }

    public static boolean insertDxsjx(DxsjxInfoBean dxsjx) {
        return DxsjxInfoManager.insertDxsjx(dxsjx);
    }

    public static boolean deleteDxsjx(Map<String, String> m) {
        return DxsjxInfoManager.deleteDxsjx(m);
    }
}
