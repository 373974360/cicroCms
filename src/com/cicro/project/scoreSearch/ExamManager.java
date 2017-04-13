package com.cicro.project.scoreSearch;

import com.cicro.util.DateUtil;
import com.cicro.util.FormatUtil;
import com.cicro.wcm.bean.logs.SettingLogsBean;
import com.cicro.wcm.catchs.ISyncCatch;
import com.cicro.wcm.dao.PublicTableDAO;
import java.util.*;

public class ExamManager implements ISyncCatch {

    public static TreeMap<Integer, ExamBean> subMap = new TreeMap<Integer, ExamBean>();

    static {
        reloadCatchHandl();
    }

    public void reloadCatch() {
        reloadCatchHandl();
    }

    public static void reloadCatchHandl() {
        subMap.clear();
        try {
            List<ExamBean> etbList = getAllExamList();
            if (etbList != null && etbList.size() > 0) {
                for (int i = 0; i < etbList.size(); i++) {
                    subMap.put(etbList.get(i).getId(), etbList.get(i));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getExamCount(Map<String, String> m) {
        if (m.containsKey("key_word")) {
            if (!FormatUtil.isValiditySQL((String) m.get("key_word")))
                return "0";
        }
        return ExamDAO.getExamCount(m);
    }

    public static List<ExamBean> getExamList(Map<String, String> m) {

        if (m.containsKey("key_word")) {
            if (!FormatUtil.isValiditySQL((String) m.get("key_word")))
                return new ArrayList();
        }
        return ExamDAO.getExamList(m);
    }

    public static List<ExamBean> getAllExamList() {
        return ExamDAO.getAllExamList();
    }

    public static ExamBean getExamBean(String id) {
        return ExamDAO.getExamBean(id);
    }

    public static boolean insertExam(ExamBean hb, SettingLogsBean stl) {
        hb.setId(PublicTableDAO.getIDByTableName("dz_exam"));
        hb.setAddTime(DateUtil.getCurrentDateTime());
        boolean result = ExamDAO.insertExam(hb, stl);
        if(result){
            reloadCatchHandl();
        }
        return result;
    }

    public static boolean updateExam(ExamBean hb, SettingLogsBean stl) {
    	hb.setUpdateTime(DateUtil.getCurrentDateTime());
        boolean result = ExamDAO.updateExam(hb, stl);
        if(result){
            reloadCatchHandl();
        }
        return result;
    }

    public static boolean deleteExam(Map<String, String> m, SettingLogsBean stl) {
        boolean result = ExamDAO.deleteExam(m, stl);
        if(result){
            reloadCatchHandl();
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(DateUtil.getDateBefore("2015-12-10",1));
    }

}