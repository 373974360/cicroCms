/*     */ package com.cicro.wcm.services.cms.count;
/*     */ 
/*     */ import com.cicro.util.FormatUtil;
/*     */ import com.cicro.util.OutExcel;
/*     */ import com.cicro.util.jconfig.JconfigUtil;
/*     */ import com.cicro.util.jconfig.JconfigUtilContainer;
/*     */ import com.cicro.wcm.bean.cms.count.SiteCountBean;
/*     */ import com.cicro.wcm.services.appeal.count.CountUtil;
/*     */ import java.io.File;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class SiteCountExcelOut
/*     */ {
/*     */   public static String[] getFileUrl()
/*     */   {
/*  32 */     String root_path = JconfigUtilContainer.bashConfig().getProperty("path", "", "manager_path");
/*  33 */     String path = FormatUtil.formatPath(root_path + "/control/count/file/");
/*     */ 
/*  35 */     File filePath = new File(path);
/*  36 */     if (!filePath.exists()) {
/*  37 */       filePath.mkdir();
/*     */     }
/*  39 */     CountUtil.deleteFile(path);
/*     */ 
/*  42 */     String nowDate = CountUtil.getNowDayDate();
/*  43 */     String fileTemp2 = FormatUtil.formatPath(path + File.separator + nowDate);
/*     */ 
/*  45 */     File file2 = new File(fileTemp2);
/*  46 */     if (!file2.exists()) {
/*  47 */       file2.mkdir();
/*     */     }
/*  49 */     String nowTime = CountUtil.getNowDayDateTime();
/*  50 */     String xls = nowTime + CountUtil.getEnglish(1) + ".xls";
/*  51 */     String xlsFile = fileTemp2 + File.separator + xls;
/*  52 */     String urlFile = "/manager/control/count/file/" + nowDate + File.separator + xls;
/*     */ 
/*  54 */     String[] str = { xlsFile, urlFile };
/*     */ 
/*  56 */     return str;
/*     */   }
/*     */ 
/*     */   public static String siteInfoOutExcelByUser(List listBean, List headerList)
/*     */   {
/*     */     try
/*     */     {
/*  69 */       String[] fileStr = getFileUrl();
/*     */ 
/*  72 */       int size = headerList.size();
/*  73 */       String[] head = (String[])headerList.toArray(new String[size]);
/*     */ 
/*  77 */       String[][] data = new String[listBean.size()][8];
/*  78 */       for (int i = 0; i < listBean.size(); i++) {
/*  79 */         SiteCountBean countBean = (SiteCountBean)listBean.get(i);
/*  80 */         data[i][0] = countBean.getUser_name();
/*  81 */         data[i][1] = String.valueOf(countBean.getDept_name());
/*  82 */         data[i][2] = String.valueOf(countBean.getInputCount());
/*  83 */         data[i][3] = String.valueOf(countBean.getHostInfoCount());
/*  84 */         data[i][4] = String.valueOf(countBean.getRefInfoCount());
/*  85 */         data[i][5] = String.valueOf(countBean.getLinkInfoCount());
/*  86 */         data[i][6] = String.valueOf(countBean.getReleasedCount());
/*  87 */         countBean.setReleaseRate();
/*  88 */         data[i][7] = String.valueOf(countBean.getReleaseRate());
/*     */       }
/*     */ 
/*  91 */       OutExcel oe = new OutExcel("站点信息统计表");
/*  92 */       oe.doOut(fileStr[0], head, data);
/*     */ 
/*  94 */       return fileStr[1];
/*     */     } catch (Exception e) {
/*  96 */       e.printStackTrace();
/*  97 */     }return "";
/*     */   }
/*     */ 
/*     */   public static String orgTreeInfoOutExcel(List headerList, Map mp)
/*     */   {
/*     */     try
/*     */     {
/* 110 */       int num = 0;
/* 111 */       String[] fileStr = getFileUrl();
/*     */ 
/* 114 */       List listBean = SiteCountManager.getSiteCountListByInput(mp);
/*     */ 
/* 116 */       Map map = new HashMap();
/* 117 */       List list = new ArrayList();
/* 118 */       map.put("numcount", Integer.valueOf(1));
/* 119 */       for (int i = 0; i < listBean.size(); i++) {
/* 120 */         SiteCountBean countBean = (SiteCountBean)listBean.get(i);
/* 121 */         int mun = 1;
/* 122 */         doOutTreeBean(countBean, mun, map, list);
/*     */       }
/*     */ 
/* 125 */       List listResult = new ArrayList();
/* 126 */       int numcount = Integer.valueOf(map.get("numcount").toString()).intValue();
/* 127 */       for (int i = 0; i < list.size(); i++) {
/* 128 */         Map mapO = (Map)list.get(i);
/* 129 */         int numO = Integer.valueOf(mapO.get("num").toString()).intValue();
/* 130 */         String dept_name = mapO.get("dept_name").toString();
/*     */ 
/* 132 */         List listCur = new ArrayList();
/* 133 */         for (int j = 1; j <= numcount; j++) {
/* 134 */           if (j == numO)
/* 135 */             listCur.add(dept_name);
/*     */           else {
/* 137 */             listCur.add("");
/*     */           }
/*     */         }
/* 140 */         int num1 = listCur.size();
/* 141 */         listCur.add(String.valueOf(mapO.get("inputCount")));
/* 142 */         listCur.add(String.valueOf(mapO.get("hostInfoCount")));
/* 143 */         listCur.add(String.valueOf(mapO.get("refInfoCount")));
/* 144 */         listCur.add(String.valueOf(mapO.get("linkInfoCount")));
/* 145 */         listCur.add(String.valueOf(mapO.get("releasedCount")));
/* 146 */         listCur.add(String.valueOf(mapO.get("releaseRate")));
/* 147 */         int num2 = listCur.size();
/* 148 */         num = num2 - num1;
/* 149 */         listResult.add(listCur);
/*     */       }
/*     */ 
/* 153 */       int size = headerList.size();
/* 154 */       String[] head = (String[])headerList.toArray(new String[size]);
/*     */ 
/* 157 */       String[][] data = new String[listResult.size()][numcount + num];
/* 158 */       for (int i = 0; i < listResult.size(); i++) {
/* 159 */         List listCur = (List)listResult.get(i);
/* 160 */         for (int j = 0; j < numcount + num; j++) {
/* 161 */           data[i][j] = ((String)listCur.get(j));
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/* 166 */       OutExcel oe = new OutExcel("按部门统计");
/* 167 */       oe.doOutTree(fileStr[0], head, data, numcount);
/*     */ 
/* 169 */       return fileStr[1];
/*     */     } catch (Exception e) {
/* 171 */       e.printStackTrace();
/* 172 */     }return "";
/*     */   }
/*     */ 
/*     */   public static void setNumCount(Map map, int num)
/*     */   {
/* 178 */     if (num > Integer.valueOf(map.get("numcount").toString()).intValue())
/* 179 */       map.put("numcount", Integer.valueOf(num));
/*     */   }
/*     */ 
/*     */   public static void doOutTreeBean(SiteCountBean countBean, int num, Map map, List list)
/*     */   {
/* 185 */     boolean is_leaf = countBean.isIs_leaf();
/* 186 */     if (is_leaf)
/*     */     {
/* 188 */       Map map1 = new HashMap();
/* 189 */       map1.put("num", Integer.valueOf(num));
/* 190 */       map1.put("dept_name", countBean.getDept_name());
/* 191 */       map1.put("inputCount", Integer.valueOf(countBean.getInputCount()));
/* 192 */       map1.put("hostInfoCount", Integer.valueOf(countBean.getHostInfoCount()));
/* 193 */       map1.put("refInfoCount", Integer.valueOf(countBean.getRefInfoCount()));
/* 194 */       map1.put("linkInfoCount", Integer.valueOf(countBean.getLinkInfoCount()));
/* 195 */       map1.put("releasedCount", Integer.valueOf(countBean.getReleasedCount()));
/* 196 */       map1.put("releaseRate", countBean.getReleaseRate());
/* 197 */       list.add(map1);
/*     */     }
/*     */     else {
/* 200 */       Map map1 = new HashMap();
/* 201 */       map1.put("num", Integer.valueOf(num));
/* 202 */       map1.put("dept_name", countBean.getDept_name());
/* 203 */       map1.put("inputCount", Integer.valueOf(countBean.getInputCount()));
/* 204 */       map1.put("hostInfoCount", Integer.valueOf(countBean.getHostInfoCount()));
/* 205 */       map1.put("refInfoCount", Integer.valueOf(countBean.getRefInfoCount()));
/* 206 */       map1.put("linkInfoCount", Integer.valueOf(countBean.getLinkInfoCount()));
/* 207 */       map1.put("releasedCount", Integer.valueOf(countBean.getReleasedCount()));
/* 208 */       map1.put("releaseRate", countBean.getReleaseRate());
/* 209 */       list.add(map1);
/* 210 */       List listChild = countBean.getChild_list();
/*     */ 
/* 212 */       if ((listChild != null) && (!"".equals(listChild)) && (listChild.size() > 0)) {
/* 213 */         num++;
/* 214 */         setNumCount(map, num);
/* 215 */         for (int i = 0; i < listChild.size(); i++) {
/* 216 */           SiteCountBean siteCountBean = (SiteCountBean)listChild.get(i);
/* 217 */           if (siteCountBean != null)
/* 218 */             doOutTreeBean(siteCountBean, num, map, list);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.cms.count.SiteCountExcelOut
 * JD-Core Version:    0.6.2
 */