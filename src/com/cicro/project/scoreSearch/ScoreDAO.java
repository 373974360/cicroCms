package com.cicro.project.scoreSearch;

import com.cicro.wcm.bean.logs.SettingLogsBean;
import com.cicro.wcm.dao.PublicTableDAO;
import com.cicro.wcm.db.DBManager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScoreDAO {
    public static String getScoreCount(Map<String, String> m) {
        return DBManager.getString("getScoreCount", m);
    }

    public static List<ScoreBean> getScoreList(Map<String, String> m) {
        return DBManager.queryFList("getScoreList", m);
    }

    public static ScoreBean getScoreBean(String id) {
        Map m = new HashMap();
        m.put("id", id);
        return (ScoreBean) DBManager.queryFObj("getScoreBean", m);
    }

    public static List<ScoreBean> getAllScoreList() {
        return DBManager.queryFList("getAllScoreList", "");
    }


    public static boolean insertScore(ScoreBean pb, SettingLogsBean stl) {
        if (DBManager.insert("insertScore", pb)) {
            PublicTableDAO.insertSettingLogs("添加", "成绩信息", pb.getId() + "", stl);
            return true;
        }
        return false;
    }

    public static boolean insertScore(ScoreBean pb) {
        return DBManager.insert("insertScore", pb);
    }

    public static boolean updateScore(ScoreBean pb, SettingLogsBean stl) {
        if (DBManager.update("updateScore", pb)) {
            PublicTableDAO.insertSettingLogs("修改", "成绩信息", pb.getId() + "", stl);
            return true;
        }
        return false;
    }

    public static boolean deleteScore(Map<String, String> m, SettingLogsBean stl) {
        if (DBManager.delete("deleteScore", m)) {
            PublicTableDAO.insertSettingLogs("删除", "成绩信息", (String) m.get("ids"), stl);
            return true;
        }
        return false;
    }
}