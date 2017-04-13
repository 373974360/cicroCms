package com.cicro.project.scoreSearch;

import com.cicro.wcm.bean.logs.SettingLogsBean;
import com.cicro.wcm.services.Log.LogManager;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public class ExamRPC {

    public static String getExamCount(Map<String, String> m) {
        return ExamManager.getExamCount(m);
    }

    public static List<ExamBean> getExamList(Map<String, String> m) {
        return ExamManager.getExamList(m);
    }

    public static List<ExamBean> getAllExamList() {
        return ExamManager.getAllExamList();
    }

    public static ExamBean getExamBean(String id) {
        ExamBean sub = ExamDAO.getExamBean(id);
        return sub;
    }

    public static boolean insertExam(ExamBean hb, HttpServletRequest request) {
        SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
        if(stl != null){
            return ExamManager.insertExam(hb,stl);
        }else
            return false;
    }

    public static boolean updateExam(ExamBean hb, HttpServletRequest request) {
        SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
        if(stl != null){
            return ExamManager.updateExam(hb, stl);
        }else
            return false;
    }

    public static boolean deleteExam(Map<String, String> m,HttpServletRequest request) {
        SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
        if(stl != null){
            return ExamManager.deleteExam(m, stl);
        }else
            return false;
    }
}