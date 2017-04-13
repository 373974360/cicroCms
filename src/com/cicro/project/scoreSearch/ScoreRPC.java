package com.cicro.project.scoreSearch;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.cicro.util.CryptoTools;

public class ScoreRPC {

    public static boolean importScoreData(String excel_path,String ecamId,HttpServletRequest request)
    {
        return ExcelHandleUtil.importScoreData(excel_path,ecamId,request);
    }

    public static boolean deleteScoreData(Map<String,String> m,HttpServletRequest request)
    {
        return ScoreManager.deleteScore(m,null);
    }

    public static List<ScoreBean> searchScore(Map<String,String> m)
    {
        return ScoreManager.getScoreList(m);
    }
    
    public static String searchScoreCount(Map<String,String> m)
    {
        return ScoreManager.getScoreCount(m);
    }
    
    public static ScoreBean getScoreBean(String id){
    	return ScoreManager.getScoreBean(id);
    }
    						
    public static void main(String[] args) {
        System.out.println(new CryptoTools().decode("=#=WS8IX5sqB4k="));
    }
}