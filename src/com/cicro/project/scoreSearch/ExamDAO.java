package com.cicro.project.scoreSearch;

import com.cicro.wcm.bean.logs.SettingLogsBean;
import com.cicro.wcm.dao.PublicTableDAO;
import com.cicro.wcm.db.DBManager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExamDAO {
    public static String getExamCount(Map<String, String> m) {
        return DBManager.getString("getExamCount", m);
    }

    public static List<ExamBean> getExamList(Map<String, String> m) {			
        return DBManager.queryFList("getExamList", m);
    }

    public static ExamBean getExamBean(String id) {
        Map m = new HashMap();
        m.put("id", id);
        return (ExamBean) DBManager.queryFObj("getExamBean", m);
    }

    public static List<ExamBean> getAllExamList() {
        return DBManager.queryFList("getAllExamList", "");
    }

    public static boolean insertExam(ExamBean pb, SettingLogsBean stl) {
        if (DBManager.insert("insertExam", pb)) {
            PublicTableDAO.insertSettingLogs("添加", "考试信息", pb.getId() + "", stl);
            return true;
        }
        return false;
    }

    public static boolean updateExam(ExamBean pb, SettingLogsBean stl) {
        if (DBManager.update("updateExam", pb)) {
            PublicTableDAO.insertSettingLogs("修改", "考试信息", pb.getId() + "", stl);
            return true;
        }
        return false;
    }

    public static boolean deleteExam(Map<String, String> m, SettingLogsBean stl) {
        if (DBManager.delete("deleteExam", m)) {
            PublicTableDAO.insertSettingLogs("删除", "考试信息", (String) m.get("ids"), stl);
            return true;
        }
        return false;
    }
}