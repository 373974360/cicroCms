package com.cicro.project.scoreSearch;

import com.cicro.util.DateUtil;
import com.cicro.wcm.bean.logs.SettingLogsBean;
import com.cicro.wcm.dao.PublicTableDAO;
import java.util.*;


public class ScoreManager {
    public static String getScoreCount(Map<String, String> m) {
        return ScoreDAO.getScoreCount(m);
    }

    public static List<ScoreBean> getScoreList(Map<String, String> m) {
        return setExamName(ScoreDAO.getScoreList(m));
    }

    public static List<ScoreBean> getAllScoreList() {
        return setExamName(ScoreDAO.getAllScoreList());
    }


    public static ScoreBean getScoreBean(String id) {
        return setExamName(ScoreDAO.getScoreBean(id));
    }

    public static boolean insertScore(ScoreBean hb, SettingLogsBean stl) {
        hb.setId(PublicTableDAO.getIDByTableName("dz_score"));
        return ScoreDAO.insertScore(hb, stl);
    }

    public static boolean updateScore(ScoreBean hb, SettingLogsBean stl) {
        return ScoreDAO.updateScore(hb, stl);
    }

    public static boolean deleteScore(Map<String, String> m, SettingLogsBean stl) {
        return ScoreDAO.deleteScore(m, stl);
    }
    
    
    public static ScoreBean setExamName(ScoreBean sb)
    {
        ExamBean eb = ExamManager.getExamBean(sb.getExamId()+"");
        sb.setExamName(eb.getName());
        return sb;
    }

    public static List<ScoreBean> setExamName(List<ScoreBean> scoreList)
    {
        if(scoreList != null && scoreList.size() > 0)
        {
        	List<ExamBean> examList = ExamManager.getAllExamList();
            for(ScoreBean sb : scoreList)
            {
            	for(ExamBean exb : examList){
            		if(sb.getExamId() == exb.getId()){
            			sb.setExamName(exb.getName());
            		}
            	}
            }
        }
        return scoreList;
    }
    

    public static void main(String[] args) {
        System.out.println(DateUtil.getDateBefore("2015-12-10",1));
    }

}