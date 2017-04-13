//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.cicro.project.salarySearch;

import com.cicro.project.salarySearch.ExcelTitleBean;
import com.cicro.project.salarySearch.ExcelTitleManager;
import com.cicro.project.salarySearch.SalaryBean;
import com.cicro.project.salarySearch.SalaryManager;
import com.cicro.project.salarySearch.SalaryUserBean;
import com.cicro.project.salarySearch.SalaryUserManager;
import com.cicro.util.DateUtil;
import com.cicro.wcm.bean.logs.SettingLogsBean;
import com.cicro.wcm.dao.PublicTableDAO;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class ExcelHandleUtil {
    private static String rootPath = "/cicro/wcm";

    public ExcelHandleUtil() {
    }

    public static boolean importSalaryData(String excel_path, String site_id, String date) {
        try {
            String var18 = rootPath + excel_path;
            File e = new File(var18);
            Workbook book = Workbook.getWorkbook(e);
            if(book != null) {
                Sheet sheet = book.getSheet(0);

                for(int sheet2 = 4; sheet2 < sheet.getRows(); ++sheet2) {
                    Cell[] i = sheet.getRow(sheet2);
                    String cell = i[0].getContents().trim();
                    if(cell != null && !"".equals(cell)) {
                        SalaryUserBean temp = SalaryUserManager.getUserByCell(i);
                        if(temp != null) {
                            HashMap sub = new HashMap();
                            sub.put("typeId", "1");
                            List m = ExcelTitleManager.getExcelTitleList(sub);
                            int etList = 4;
                            SalaryBean start = new SalaryBean();
                            start.setId(PublicTableDAO.getIDByTableName("dz_salary"));
                            start.setUserId(temp.getId());
                            start.setSalaryDate(date);
                            start.setExcelTitleId(1);
                            start.setAddTime(DateUtil.getCurrentDateTime());
                            start.setStatus("0");
                            String slb = "";
                            Iterator etb = m.iterator();

                            while(etb.hasNext()) {
                                ExcelTitleBean excelData = (ExcelTitleBean)etb.next();
                                if(excelData.getIsShow() == 1) {
                                    slb = slb + i[etList].getContents().trim() + "&";
                                    ++etList;
                                }
                            }

                            start.setExcelData(slb.substring(0, slb.length() - 1));
                            SalaryManager.insertSalary(start, (SettingLogsBean)null);
                        }
                    }
                }

                Sheet var21 = book.getSheet(1);

                for(int var22 = 4; var22 < var21.getRows(); ++var22) {
                    Cell[] var23 = var21.getRow(var22);
                    String var24 = var23[0].getContents().trim();
                    if(var24 != null && !"".equals(var24)) {
                        SalaryUserBean var25 = SalaryUserManager.getUserByCell(var23);
                        if(var25 != null) {
                            HashMap var26 = new HashMap();
                            var26.put("typeId", "2");
                            List var27 = ExcelTitleManager.getExcelTitleList(var26);
                            int var28 = 4;
                            SalaryBean var29 = new SalaryBean();
                            var29.setId(PublicTableDAO.getIDByTableName("dz_salary"));
                            var29.setUserId(var25.getId());
                            var29.setSalaryDate(date);
                            var29.setExcelTitleId(2);
                            var29.setAddTime(DateUtil.getCurrentDateTime());
                            var29.setStatus("0");
                            String var30 = "";
                            Iterator var181 = var27.iterator();

                            while(var181.hasNext()) {
                                ExcelTitleBean var31 = (ExcelTitleBean)var181.next();
                                if(var31.getIsShow() == 1) {
                                    var30 = var30 + var23[var28].getContents().trim() + "&";
                                    ++var28;
                                }
                            }

                            var29.setExcelData(var30.substring(0, var30.length() - 1));
                            SalaryManager.insertSalary(var29, (SettingLogsBean)null);
                        }
                    }
                }
            }

            e.delete();
            return true;
        } catch (BiffException var19) {
            var19.printStackTrace();
            return false;
        } catch (IOException var20) {
            var20.printStackTrace();
            return false;
        }
    }
}
