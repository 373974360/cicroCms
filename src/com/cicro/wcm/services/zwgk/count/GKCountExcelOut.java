/*     */ package com.cicro.wcm.services.zwgk.count;
/*     */ 
/*     */ import com.cicro.util.FormatUtil;
/*     */ import com.cicro.util.OutExcel;
/*     */ import com.cicro.util.jconfig.JconfigUtil;
/*     */ import com.cicro.util.jconfig.JconfigUtilContainer;
/*     */ import com.cicro.wcm.bean.zwgk.count.GKCountBean;
/*     */ import com.cicro.wcm.bean.zwgk.count.GKysqCountBean;
/*     */ import com.cicro.wcm.services.appeal.count.CountUtil;
/*     */ import java.io.File;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class GKCountExcelOut
/*     */ {
/*     */   public static String[] getFileUrl()
/*     */   {
/*  31 */     String root_path = JconfigUtilContainer.bashConfig().getProperty("path", "", "manager_path");
/*  32 */     String path = FormatUtil.formatPath(root_path + "/zwgk/count/file/");
/*  33 */     File filePath = new File(path);
/*  34 */     if (!filePath.exists()) {
/*  35 */       filePath.mkdir();
/*     */     }
/*     */ 
/*  38 */     CountUtil.deleteFile(path);
/*     */ 
/*  41 */     String nowDate = CountUtil.getNowDayDate();
/*  42 */     String fileTemp2 = FormatUtil.formatPath(path + File.separator + nowDate);
/*     */ 
/*  44 */     File file2 = new File(fileTemp2);
/*  45 */     if (!file2.exists()) {
/*  46 */       file2.mkdir();
/*     */     }
/*  48 */     String nowTime = CountUtil.getNowDayDateTime();
/*  49 */     String xls = nowTime + CountUtil.getEnglish(1) + ".xls";
/*  50 */     String xlsFile = fileTemp2 + File.separator + xls;
/*  51 */     String urlFile = "/manager/zwgk/count/file/" + nowDate + File.separator + xls;
/*     */ 
/*  53 */     String[] str = { xlsFile, urlFile };
/*     */ 
/*  55 */     return str;
/*     */   }
/*     */ 
/*     */   public static String gkInfoOutExcel(List listBean, List headerList)
/*     */   {
/*     */     try
/*     */     {
/*  68 */       String[] fileStr = getFileUrl();
/*     */ 
/*  71 */       int size = headerList.size();
/*  72 */       String[] head = (String[])headerList.toArray(new String[size]);
/*     */ 
/*  75 */       String[][] data = new String[listBean.size()][5];
/*  76 */       for (int i = 0; i < listBean.size(); i++) {
/*  77 */         GKCountBean countBean = (GKCountBean)listBean.get(i);
/*  78 */         data[i][0] = countBean.getSite_name();
/*  79 */         data[i][1] = String.valueOf(countBean.getInfo_count());
/*  80 */         data[i][2] = String.valueOf(countBean.getZ_count());
/*  81 */         data[i][3] = String.valueOf(countBean.getY_count());
/*  82 */         data[i][4] = String.valueOf(countBean.getB_count());
/*     */       }
/*     */ 
/*  85 */       OutExcel oe = new OutExcel("政务公开信息量统计");
/*  86 */       oe.doOut(fileStr[0], head, data);
/*     */ 
/*  88 */       return fileStr[1];
/*     */     } catch (Exception e) {
/*  90 */       e.printStackTrace();
/*  91 */     }return "";
/*     */   }
/*     */ 
/*     */   public static String gkWorkInfoOutExcel(List listBean, List headerList)
/*     */   {
/*     */     try
/*     */     {
/* 105 */       String[] fileStr = getFileUrl();
/*     */ 
/* 108 */       int size = headerList.size();
/* 109 */       String[] head = (String[])headerList.toArray(new String[size]);
/*     */ 
/* 112 */       String[][] data = new String[listBean.size()][5];
/* 113 */       for (int i = 0; i < listBean.size(); i++) {
/* 114 */         List list = (List)listBean.get(i);
/* 115 */         data[i][0] = String.valueOf(list.get(0));
/* 116 */         data[i][1] = String.valueOf(list.get(1));
/* 117 */         data[i][2] = String.valueOf(list.get(2));
/* 118 */         data[i][3] = String.valueOf(list.get(3));
/* 119 */         data[i][4] = String.valueOf(list.get(4) + "%");
/*     */       }
/*     */ 
/* 122 */       OutExcel oe = new OutExcel("政务公开信息量统计");
/* 123 */       oe.doOut(fileStr[0], head, data);
/*     */ 
/* 125 */       return fileStr[1];
/*     */     } catch (Exception e) {
/* 127 */       e.printStackTrace();
/* 128 */     }return "";
/*     */   }
/*     */ 
/*     */   public static String ysqgkInfoOutExcel(List listBean, List headerList)
/*     */   {
/*     */     try
/*     */     {
/* 142 */       String[] fileStr = getFileUrl();
/*     */ 
/* 145 */       int size = headerList.size();
/* 146 */       String[] head = (String[])headerList.toArray(new String[size]);
/*     */ 
/* 149 */       String[][] data = new String[listBean.size()][9];
/* 150 */       for (int i = 0; i < listBean.size(); i++) {
/* 151 */         GKysqCountBean countBean = (GKysqCountBean)listBean.get(i);
/* 152 */         if (headerList.contains("统计年月"))
/* 153 */           data[i][0] = countBean.getPut_dtime();
/*     */         else {
/* 155 */           data[i][0] = countBean.getNode_name();
/*     */         }
/* 157 */         data[i][1] = String.valueOf(countBean.getTotalCnt());
/* 158 */         data[i][2] = String.valueOf(countBean.getUnAcceptCnt());
/* 159 */         data[i][3] = String.valueOf(countBean.getAcceptedCnt());
/* 160 */         data[i][4] = String.valueOf(countBean.getReplyCnt());
/* 161 */         data[i][5] = String.valueOf(countBean.getInvalidCnt());
/* 162 */         data[i][6] = String.valueOf(countBean.getThirdCnt());
/* 163 */         data[i][7] = String.valueOf(countBean.getDelayCnt());
/* 164 */         data[i][8] = String.valueOf(countBean.getTimeoutCnt());
/*     */       }
/*     */ 
/* 167 */       OutExcel oe = new OutExcel("依申请公开信息量统计");
/* 168 */       oe.doOut(fileStr[0], head, data);
/*     */ 
/* 170 */       return fileStr[1];
/*     */     } catch (Exception e) {
/* 172 */       e.printStackTrace();
/* 173 */     }return "";
/*     */   }
/*     */ 
/*     */   public static String ysqgkTypeInfoOutExcel(List listBean, List headerList)
/*     */   {
/*     */     try
/*     */     {
/* 188 */       String[] fileStr = getFileUrl();
/*     */ 
/* 191 */       int size = headerList.size();
/* 192 */       String[] head = (String[])headerList.toArray(new String[size]);
/*     */ 
/* 195 */       String[][] data = new String[listBean.size()][7];
/* 196 */       for (int i = 0; i < listBean.size(); i++) {
/* 197 */         GKysqCountBean countBean = (GKysqCountBean)listBean.get(i);
/* 198 */         data[i][0] = countBean.getNode_name();
/* 199 */         data[i][1] = String.valueOf(countBean.getType_total());
/* 200 */         data[i][2] = String.valueOf(countBean.getQbgk_cnt());
/* 201 */         data[i][3] = String.valueOf(countBean.getBfgk_cnt());
/* 202 */         data[i][4] = String.valueOf(countBean.getBygk_cnt());
/* 203 */         data[i][5] = String.valueOf(countBean.getFbdwxx_cnt());
/* 204 */         data[i][6] = String.valueOf(countBean.getXxbcz_cnt());
/*     */       }
/*     */ 
/* 207 */       OutExcel oe = new OutExcel("依申请公开信息量统计");
/* 208 */       oe.doOut(fileStr[0], head, data);
/*     */ 
/* 210 */       return fileStr[1];
/*     */     } catch (Exception e) {
/* 212 */       e.printStackTrace();
/* 213 */     }return "";
/*     */   }
/*     */ 
/*     */   public static String gkTreeInfoOutExcel(List headerList, String site_id, String startDate, String endDate)
/*     */   {
/*     */     try
/*     */     {
/* 227 */       int num = 0;
/* 228 */       String[] fileStr = getFileUrl();
/*     */ 
/* 230 */       List listBean = GKCountManager.getGKCountList(site_id, startDate, endDate);
/*     */ 
/* 232 */       Map map = new HashMap();
/* 233 */       List list = new ArrayList();
/* 234 */       map.put("numcount", Integer.valueOf(1));
/* 235 */       for (int i = 0; i < listBean.size(); i++) {
/* 236 */         GKCountBean countBean = (GKCountBean)listBean.get(i);
/* 237 */         int mun = 1;
/* 238 */         doOutTreeBean(countBean, mun, map, list);
/*     */       }
/*     */ 
/* 241 */       List listResult = new ArrayList();
/* 242 */       int numcount = Integer.valueOf(map.get("numcount").toString()).intValue();
/* 243 */       for (int i = 0; i < list.size(); i++) {
/* 244 */         Map mapO = (Map)list.get(i);
/* 245 */         int numO = Integer.valueOf(mapO.get("num").toString()).intValue();
/* 246 */         String cat_name = mapO.get("cat_name").toString();
/* 247 */         List listCur = new ArrayList();
/* 248 */         for (int j = 1; j <= numcount; j++) {
/* 249 */           if (j == numO)
/* 250 */             listCur.add(cat_name);
/*     */           else {
/* 252 */             listCur.add("");
/*     */           }
/*     */         }
/* 255 */         int num1 = listCur.size();
/* 256 */         listCur.add(String.valueOf(mapO.get("info_count")));
/* 257 */         listCur.add(String.valueOf(mapO.get("z_count")));
/* 258 */         listCur.add(String.valueOf(mapO.get("y_count")));
/* 259 */         listCur.add(String.valueOf(mapO.get("b_count")));
/* 260 */         int num2 = listCur.size();
/* 261 */         num = num2 - num1;
/* 262 */         listResult.add(listCur);
/*     */       }
/*     */ 
/* 266 */       int size = headerList.size();
/* 267 */       String[] head = (String[])headerList.toArray(new String[size]);
/*     */ 
/* 270 */       String[][] data = new String[listResult.size()][numcount + num];
/* 271 */       for (int i = 0; i < listResult.size(); i++) {
/* 272 */         List listCur = (List)listResult.get(i);
/* 273 */         for (int j = 0; j < numcount + num; j++) {
/* 274 */           data[i][j] = ((String)listCur.get(j));
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/* 279 */       OutExcel oe = new OutExcel("信息公开统计");
/* 280 */       oe.doOutTree(fileStr[0], head, data, numcount);
/*     */ 
/* 282 */       return fileStr[1];
/*     */     } catch (Exception e) {
/* 284 */       e.printStackTrace();
/* 285 */     }return "";
/*     */   }
/*     */ 
/*     */   public static void setNumCount(Map map, int num)
/*     */   {
/* 291 */     if (num > Integer.valueOf(map.get("numcount").toString()).intValue())
/* 292 */       map.put("numcount", Integer.valueOf(num));
/*     */   }
/*     */ 
/*     */   public static void doOutTreeBean(GKCountBean countBean, int num, Map map, List list)
/*     */   {
/* 298 */     boolean is_leaf = countBean.isIs_leaf();
/* 299 */     if (is_leaf)
/*     */     {
/* 301 */       Map map1 = new HashMap();
/* 302 */       map1.put("num", Integer.valueOf(num));
/* 303 */       map1.put("cat_name", countBean.getCat_name());
/* 304 */       map1.put("info_count", Integer.valueOf(countBean.getInfo_count()));
/* 305 */       map1.put("z_count", Integer.valueOf(countBean.getZ_count()));
/* 306 */       map1.put("y_count", Integer.valueOf(countBean.getY_count()));
/* 307 */       map1.put("b_count", Integer.valueOf(countBean.getB_count()));
/* 308 */       list.add(map1);
/*     */     }
/*     */     else {
/* 311 */       Map map1 = new HashMap();
/* 312 */       map1.put("num", Integer.valueOf(num));
/* 313 */       map1.put("cat_name", countBean.getCat_name());
/* 314 */       map1.put("info_count", Integer.valueOf(countBean.getInfo_count()));
/* 315 */       map1.put("z_count", Integer.valueOf(countBean.getZ_count()));
/* 316 */       map1.put("y_count", Integer.valueOf(countBean.getY_count()));
/* 317 */       map1.put("b_count", Integer.valueOf(countBean.getB_count()));
/* 318 */       list.add(map1);
/* 319 */       List listChild = countBean.getChild_list();
/*     */ 
/* 321 */       if ((listChild != null) && (!"".equals(listChild)) && (listChild.size() > 0)) {
/* 322 */         num++;
/* 323 */         setNumCount(map, num);
/* 324 */         for (int i = 0; i < listChild.size(); i++) {
/* 325 */           GKCountBean gkCountBean = (GKCountBean)listChild.get(i);
/* 326 */           if (gkCountBean != null)
/* 327 */             doOutTreeBean(gkCountBean, num, map, list);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.zwgk.count.GKCountExcelOut
 * JD-Core Version:    0.6.2
 */