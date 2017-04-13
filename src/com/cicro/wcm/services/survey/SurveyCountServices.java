/*     */ package com.cicro.wcm.services.survey;
/*     */ 
/*     */ import com.cicro.util.DateUtil;
/*     */ import com.cicro.util.FormatUtil;
/*     */ import com.cicro.util.OutExcel;
/*     */ import com.cicro.util.jconfig.JconfigFactory;
/*     */ import com.cicro.util.jconfig.JconfigUtil;
/*     */ import com.cicro.wcm.bean.survey.SurveyCategory;
/*     */ import com.cicro.wcm.bean.survey.SurveyCount;
/*     */ import com.cicro.wcm.dao.survey.SurveyCountDAO;
/*     */ import java.io.File;
/*     */ import java.io.PrintStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.Set;
/*     */ 
/*     */ public class SurveyCountServices
/*     */ {
/*     */   public static Map getSurveyCategoryCount(String start_time, String end_time, String time_type, String site_id)
/*     */   {
/*  25 */     String con = " and cs." + time_type + " > '" + start_time + "' and cs." + time_type + " < '" + end_time + "'";
/*     */ 
/*  28 */     Map count_map = new HashMap();
/*     */ 
/*  30 */     List category_list = SurveyCountDAO.getAllSurveyCategory(site_id);
/*  31 */     if ((category_list != null) && (category_list.size() > 0))
/*     */     {
/*  34 */       setCategroyMap(count_map, category_list);
/*     */ 
/*  36 */       Map m = new HashMap();
/*  37 */       m.put("con", con);
/*  38 */       m.put("site_id", site_id);
/*  39 */       List main_list = SurveyCountDAO.getSurveyCategoryCount(m);
/*  40 */       setCountMap(count_map, main_list);
/*     */ 
/*  42 */       List subject_list = SurveyCountDAO.getSurveySubjectCount(m);
/*  43 */       setSubjectCountToMap(count_map, subject_list);
/*     */ 
/*  45 */       List answer_list = SurveyCountDAO.getSurveyAnswerCount(m);
/*  46 */       setAnswerCountToMap(count_map, answer_list);
/*     */ 
/*  48 */       con = con + " and cs.publish_status=1";
/*  49 */       m.put("con", con);
/*  50 */       List public_list = SurveyCountDAO.getSurveyCategoryCount(m);
/*  51 */       setPublishCountToMap(count_map, public_list);
/*     */     }
/*     */ 
/*  56 */     String file_name = DateUtil.getCurrentDateTime("yyyyMMddHHmmss");
/*  57 */     createExcel(file_name, getCategorySubjectData(start_time, end_time, count_map, time_type), "category", "问卷类型统计列表");
/*  58 */     count_map.put("file_path", DateUtil.getCurrentDate() + "/" + file_name + ".xls");
/*     */ 
/*  60 */     return count_map;
/*     */   }
/*     */ 
/*     */   public static String getTimeTypeText(String time_type)
/*     */   {
/*  65 */     String str = "按照创建时间统计";
/*  66 */     if ("publish_time".equals(time_type))
/*  67 */       str = "按照发布时间统计";
/*  68 */     if ("start_time".equals(time_type))
/*  69 */       str = "按照直播时间统计";
/*  70 */     return str;
/*     */   }
/*     */ 
/*     */   public static String[][] getCategorySubjectData(String start_time, String end_time, Map m, String time_type)
/*     */   {
/*  82 */     String str = getTimeTypeText(time_type);
/*     */ 
/*  84 */     String[][] data = new String[m.size() + 4][5];
/*  85 */     data[0][0] = "报表生成时间：";
/*  86 */     data[0][1] = DateUtil.getCurrentDateTime();
/*  87 */     data[1][0] = "统计条件：";
/*  88 */     data[1][1] = ("统计方式：" + str);
/*  89 */     data[2][1] = ("日期范围：" + start_time + " -- " + end_time);
/*  90 */     data[3][0] = "问卷分类";
/*  91 */     data[3][1] = "问卷数";
/*  92 */     data[3][2] = "发布问卷数";
/*  93 */     data[3][3] = "问题总数";
/*  94 */     data[3][4] = "答案数";
/*     */ 
/*  97 */     Iterator iter = m.entrySet().iterator();
/*  98 */     int i = 4;
/*  99 */     while (iter.hasNext()) {
/* 100 */       Entry entry = (Entry)iter.next();
/* 101 */       SurveyCount sc = (SurveyCount)entry.getValue();
/* 102 */       data[i][0] = sc.getC_name();
/* 103 */       data[i][1] = sc.getSur_count();
/* 104 */       data[i][2] = sc.getPublish_count();
/* 105 */       data[i][3] = sc.getSubject_count();
/* 106 */       data[i][4] = sc.getAnswer_count();
/* 107 */       i++;
/*     */     }
/* 109 */     return data;
/*     */   }
/*     */ 
/*     */   public static void createExcel(String file_name, String[][] data, String type, String sheelName)
/*     */   {
/*     */     try {
/* 115 */       JconfigUtil bc = JconfigFactory.getJconfigUtilInstance("bashConfig");
/* 116 */       String path = bc.getProperty("path", "", "manager_path").trim() + "/survey/count";
/* 117 */       String tempPath = path + "/" + DateUtil.getCurrentDate();
/* 118 */       File file2 = new File(FormatUtil.formatPath(tempPath));
/* 119 */       if (!file2.exists()) {
/* 120 */         file2.mkdir();
/*     */       }
/* 122 */       String xFile = FormatUtil.formatPath(tempPath + "/" + file_name + ".xls");
/*     */ 
/* 124 */       OutExcel.deleteFile(path);
/*     */ 
/* 126 */       OutExcel oe = new OutExcel(sheelName);
/* 127 */       if ("category".equals(type))
/* 128 */         oe.doOut(xFile, data, 1, 2);
/*     */       else
/* 130 */         oe.doOut(xFile, data, 1, 4);
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 134 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void setCategroyMap(Map m, List<SurveyCount> category_list)
/*     */   {
/* 146 */     for (int i = 0; i < category_list.size(); i++)
/*     */     {
/* 148 */       m.put(((SurveyCount)category_list.get(i)).getCategory_id(), category_list.get(i));
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void setCountMap(Map m, List<SurveyCount> main_list)
/*     */   {
/* 160 */     for (int i = 0; i < main_list.size(); i++)
/*     */       try
/*     */       {
/* 163 */         SurveyCount sc = (SurveyCount)m.get(((SurveyCount)main_list.get(i)).getCategory_id());
/* 164 */         sc.setSur_count(((SurveyCount)main_list.get(i)).getSur_count());
/*     */       }
/*     */       catch (Exception e) {
/* 167 */         System.out.println("setChatCountToMap category is null " + ((SurveyCount)main_list.get(i)).getCategory_id());
/* 168 */         e.printStackTrace();
/*     */       }
/*     */   }
/*     */ 
/*     */   public static void setSubjectCountToMap(Map m, List<SurveyCount> subject_list)
/*     */   {
/* 181 */     for (int i = 0; i < subject_list.size(); i++)
/*     */       try
/*     */       {
/* 184 */         SurveyCount sc = (SurveyCount)m.get(((SurveyCount)subject_list.get(i)).getCategory_id());
/* 185 */         sc.setSubject_count(((SurveyCount)subject_list.get(i)).getSubject_count());
/*     */       }
/*     */       catch (Exception e) {
/* 188 */         System.out.println("setSubjectCountToMap category is null " + ((SurveyCount)subject_list.get(i)).getCategory_id());
/* 189 */         e.printStackTrace();
/*     */       }
/*     */   }
/*     */ 
/*     */   public static void setAnswerCountToMap(Map m, List<SurveyCount> answer_list)
/*     */   {
/* 202 */     for (int i = 0; i < answer_list.size(); i++)
/*     */       try
/*     */       {
/* 205 */         SurveyCount sc = (SurveyCount)m.get(((SurveyCount)answer_list.get(i)).getCategory_id());
/* 206 */         sc.setAnswer_count(((SurveyCount)answer_list.get(i)).getAnswer_count());
/*     */       }
/*     */       catch (Exception e) {
/* 209 */         System.out.println("setAnswerCountToMap category is null " + ((SurveyCount)answer_list.get(i)).getCategory_id());
/* 210 */         e.printStackTrace();
/*     */       }
/*     */   }
/*     */ 
/*     */   public static void setPublishCountToMap(Map m, List<SurveyCount> public_list)
/*     */   {
/* 223 */     for (int i = 0; i < public_list.size(); i++)
/*     */       try
/*     */       {
/* 226 */         SurveyCount sc = (SurveyCount)m.get(((SurveyCount)public_list.get(i)).getCategory_id());
/* 227 */         sc.setPublish_count(((SurveyCount)public_list.get(i)).getPublish_count());
/*     */       }
/*     */       catch (Exception e) {
/* 230 */         System.out.println("setPublishCountToMap category is null " + ((SurveyCount)public_list.get(i)).getCategory_id());
/* 231 */         e.printStackTrace();
/*     */       }
/*     */   }
/*     */ 
/*     */   public static Map getHotCount(String start_time, String end_time, String category_ids, int count_num, String time_type, String site_id)
/*     */   {
/* 248 */     String con = " and cs.start_time > '" + start_time + "' and cs.start_time < '" + end_time + "'";
/* 249 */     Map m = new HashMap();
/* 250 */     m.put("con", con);
/* 251 */     m.put("category_ids", category_ids);
/* 252 */     m.put("site_id", site_id);
/* 253 */     List count_list = new ArrayList();
/* 254 */     Map count_map = new HashMap();
/* 255 */     setListToMap(count_list, m, count_num);
/* 256 */     count_map.put("count_list", count_list);
/*     */ 
/* 259 */     String file_name = DateUtil.getCurrentDateTime("yyyyMMddHHmmss");
/* 260 */     createExcel(file_name, getHotCountData(start_time, end_time, category_ids, count_list, count_num, time_type), "hot", "问卷热度排行");
/* 261 */     count_map.put("file_path", DateUtil.getCurrentDate() + "/" + file_name + ".xls");
/* 262 */     return count_map;
/*     */   }
/*     */ 
/*     */   public static String[][] getHotCountData(String start_time, String end_time, String category_ids, List<SurveyCount> count_list, int count_num, String time_type)
/*     */   {
/* 274 */     String str = getTimeTypeText(time_type);
/* 275 */     String co_num = "";
/* 276 */     if (count_num == 0)
/* 277 */       co_num = "所有的";
/*     */     else
/* 279 */       co_num = count_num;
/* 280 */     String[][] data = new String[count_list.size() + 6][4];
/* 281 */     data[0][0] = "报表生成时间：";
/* 282 */     data[0][1] = DateUtil.getCurrentDateTime();
/* 283 */     data[1][0] = "统计条件：";
/* 284 */     data[1][1] = ("统计方式：" + str);
/* 285 */     data[2][1] = ("日期范围：" + start_time + " -- " + end_time);
/* 286 */     data[3][1] = ("显示条数：" + co_num);
/* 287 */     data[4][1] = ("所属分类：" + getCategoryNames(category_ids));
/* 288 */     data[5][0] = "问卷名称";
/* 289 */     data[5][1] = "问卷分类";
/* 290 */     data[5][3] = "问题总数";
/* 291 */     data[5][2] = "答卷数";
/*     */ 
/* 293 */     if ((count_list != null) && (count_list.size() > 0))
/*     */     {
/* 295 */       for (int i = 0; i < count_list.size(); i++)
/*     */       {
/* 297 */         data[(i + 6)][0] = ((SurveyCount)count_list.get(i)).getS_name();
/* 298 */         data[(i + 6)][1] = ((SurveyCount)count_list.get(i)).getC_name();
/* 299 */         data[(i + 6)][2] = ((SurveyCount)count_list.get(i)).getSubject_count();
/* 300 */         data[(i + 6)][3] = ((SurveyCount)count_list.get(i)).getAnswer_count();
/*     */       }
/*     */     }
/* 303 */     return data;
/*     */   }
/*     */ 
/*     */   public static String getCategoryNames(String category_ids)
/*     */   {
/* 313 */     String names = "";
/*     */ 
/* 325 */     category_ids = category_ids.replaceAll("'", "");
/* 326 */     String[] tempA = category_ids.split(",");
/* 327 */     if ((tempA != null) && (tempA.length > 0))
/*     */     {
/* 329 */       for (int i = 0; i < tempA.length; i++)
/*     */       {
/* 331 */         SurveyCategory sc = SurveyCategoryService.getSurveyCategoryBean(tempA[i]);
/* 332 */         if (sc != null)
/* 333 */           names = names + "," + sc.getC_name();
/*     */       }
/* 335 */       names = names.substring(1);
/*     */     }
/* 337 */     return names;
/*     */   }
/*     */ 
/*     */   public static void setSubjectMap(Map m, List<SurveyCount> list)
/*     */   {
/* 348 */     for (int i = 0; i < list.size(); i++)
/*     */     {
/* 350 */       m.put(((SurveyCount)list.get(i)).getS_id(), list.get(i));
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void setListToMap(List<SurveyCount> count_list, Map m, int count_num)
/*     */   {
/* 357 */     List answer_list = SurveyCountDAO.getHotCount_answer(m);
/*     */ 
/* 359 */     List subject_list = SurveyCountDAO.getSurveySubjectCountBySub(m);
/*     */ 
/* 361 */     Map list_map = new HashMap();
/*     */ 
/* 363 */     if ((answer_list != null) && (answer_list.size() > 0))
/*     */     {
/* 365 */       setSubjectMap(list_map, subject_list);
/*     */ 
/* 367 */       if ((answer_list.size() < count_num) || (count_num == 0)) {
/* 368 */         count_num = answer_list.size();
/*     */       }
/* 370 */       for (int i = 0; i < count_num; i++)
/*     */       {
/* 372 */         if (list_map.containsKey(((SurveyCount)answer_list.get(i)).getS_id()))
/*     */         {
/* 374 */           SurveyCount sc = (SurveyCount)list_map.get(((SurveyCount)answer_list.get(i)).getS_id());
/* 375 */           ((SurveyCount)answer_list.get(i)).setSubject_count(sc.getSubject_count());
/*     */         }
/*     */ 
/* 378 */         count_list.add((SurveyCount)answer_list.get(i));
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/* 387 */     System.out.println(getHotCount("2011-08-15", "2011-08-15", "'57d2664d-75e8-4fc0-a335-6b597d8dc091'", 10, "add_time", "11111ddd"));
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.survey.SurveyCountServices
 * JD-Core Version:    0.6.2
 */