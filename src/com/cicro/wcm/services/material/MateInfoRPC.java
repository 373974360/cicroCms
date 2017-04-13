//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.cicro.wcm.services.material;

import com.cicro.util.UploadManager;
import com.cicro.wcm.bean.logs.SettingLogsBean;
import com.cicro.wcm.bean.material.MateInfoBean;
import com.cicro.wcm.services.Log.LogManager;
import com.cicro.wcm.services.material.MateInfoManager;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

public class MateInfoRPC {
    public MateInfoRPC() {
    }

    public static String getUploadSecretKey() {
        return UploadManager.getUploadSecretKey();
    }

    public static String getImgDomain(String site_id) {
        //return UploadManager.getImgBrowserUrl(site_id);
    	return "";
    }

    public static String getArrIdFromTable() {
        return MateInfoManager.getArrIdFromTable();
    }

    public static List<MateInfoBean> getMateInfoList(Map<String, String> con_m) {
        return MateInfoManager.getMateInfoList(con_m);
    }

    public static MateInfoBean getMateInfoBean(String arr_id) {
        return MateInfoManager.getMateInfoBean(arr_id);
    }

    public static String getMateInfoListCounts(Map<String, String> con_m) {
        return MateInfoManager.getMateInfoListCounts(con_m);
    }

    public boolean insertMateInfoBean(MateInfoBean mfb, HttpServletRequest request) {
        SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
        return stl != null?MateInfoManager.insertMateInfo(mfb, stl):false;
    }

    public boolean updateMateInfoBean(MateInfoBean mfb, HttpServletRequest request) {
        SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
        return stl != null?MateInfoManager.updateMateInfo(mfb, stl):false;
    }

    public static boolean moveMateInfo(String f_id, String att_ids) {
        return MateInfoManager.moveMateInfo(f_id, att_ids);
    }

    public static boolean deleteMateInfo(String opt_id, HttpServletRequest request) {
        SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
        return stl != null?MateInfoManager.deleteMateInfo(opt_id, stl):false;
    }
}
