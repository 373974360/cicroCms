package com.cicro.project.scoreSearch;
import com.cicro.util.DateUtil;
import com.cicro.wcm.bean.logs.SettingLogsBean;
import com.cicro.wcm.services.Log.LogManager;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class ExcelHandleUtil {
	private static String rootPath = "/cicro/wcm";

	public ExcelHandleUtil() {
	}

	public static boolean importScoreData(String excel_path, String examId,HttpServletRequest request) {
    	
    	try {
    		String filePath = rootPath + excel_path;
            System.out.println(filePath + "****************************");
            File e = new File(filePath);
            Workbook book = Workbook.getWorkbook(e);
            int sfzhCellNumber = 0;
            int xmCellNumber = 0;
            int zkzhCellNumber = 0;
            if(book != null) {
                Sheet sheet = book.getSheet(0);    
                for(int i = 0; i < sheet.getRows(); ++i) {
                    Cell[] cell = sheet.getRow(i);
                    String temp = cell[0].getContents().trim();
                    if(temp == null || "".equals(temp))
                    {
                        continue;
                    }else{
                    	if( i == 0){
                    		for(int index = 0; index < cell.length; index++){
                                Cell target = cell[index];
                                if(target.getContents() != null && target.getContents().trim().indexOf("身份证号") >= 0){
                                    sfzhCellNumber = index;
                                }
                                if(target.getContents() != null && target.getContents().trim().indexOf("姓名") >= 0){
                                	xmCellNumber = index;
                                }
                                if(target.getContents() != null && target.getContents().trim().indexOf("考号") >= 0){
                                	zkzhCellNumber = index;
                                }
                            }
                    	}else{
                    		ScoreBean scoreBean = new ScoreBean();
                    		scoreBean.setExamId(Integer.parseInt(examId));
                    		scoreBean.setSfzh(cell[sfzhCellNumber].getContents().trim());
                    		scoreBean.setXm(cell[xmCellNumber].getContents().trim());
                    		scoreBean.setZkzh(cell[zkzhCellNumber].getContents().trim());
                    		scoreBean.setImportDate(DateUtil.getCurrentDateTime());
                    		scoreBean.setStatus("0");
                    		String excelData = "";
                            Map<String,String> m = new HashMap();
                            m.put("examId",examId);
                            List<ExcelTitleBean> etList = ExcelTitleManager.getExcelTitleList(m);
                            int start = 0;
                            for(int j = 0; j < etList.size(); j++)
                            {
                            	ExcelTitleBean etb = etList.get(j);
                                if(etb.getIsShow() == 1)
                                {
                                	excelData += "&" + cell[j].getContents().trim();
                                }
                            }
                            if(excelData.length() > 0){
                            	excelData = excelData.substring(1);
                            }
                            scoreBean.setExcelData(excelData);
                            SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
                    		ScoreManager.insertScore(scoreBean, stl);
                    	}
                    }
                }
            }
            e.delete();
            return true;
        } catch (BiffException var17) {
            var17.printStackTrace();
            return false;
        } catch (IOException var18) {
            var18.printStackTrace();
            return false;
        }
	}
}
