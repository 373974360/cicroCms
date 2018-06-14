package com.cicro.project.dz_dxsjx;

import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Author: like
 * @Date: 2018-06-14 11:54
 * @Version: 1.0
 * @Created in idea by autoCode
 */
public class DxsjxRPC {
    public DxsjxRPC() {
    }

    public static List<DxsjxBean> getDxsjxList(Map<String, String> m) {
        return DxsjxManager.getDxsjxList(m);
    }

    public static String getDxsjxListCount(Map<String, String> m) {
        return DxsjxManager.getDxsjxListCount(m);
    }

    public static DxsjxBean getDxsjxBean(int id) {
        return DxsjxManager.getDxsjxBean(id);
    }

    public static boolean insertDxsjx(DxsjxBean dxsjx) {
        return DxsjxManager.insertDxsjx(dxsjx);
    }

    public static boolean deleteDxsjx(Map<String, String> m) {
        return DxsjxManager.deleteDxsjx(m);
    }
}
