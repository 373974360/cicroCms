/*     */ package com.cicro.wcm.services.interview;
/*     */ 
/*     */ import com.cicro.util.DateUtil;
/*     */ import com.cicro.util.FormatUtil;
/*     */ import com.cicro.util.OutExcel;
/*     */ import com.cicro.util.jconfig.JconfigFactory;
/*     */ import com.cicro.util.jconfig.JconfigUtil;
/*     */ import com.cicro.wcm.bean.interview.SubjectCategory;
/*     */ import com.cicro.wcm.bean.interview.SubjectCount;
/*     */ import com.cicro.wcm.dao.interview.SubjectCategoryDAO;
/*     */ import com.cicro.wcm.dao.interview.SubjectCountDAO;
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
/*     */ public class SubjectCountServices
/*     */ {
/*     */   public static Map getSubjectCategoryCount(String start_time, String end_time, String time_type, String site_id)
/*     */   {
/*  40 */     String con = " and cs." + time_type + " > '" + start_time + " 00:00' and cs." + time_type + " < '" + end_time + " 23:59'";
/*     */ 
/*  43 */     Map count_map = new HashMap();
/*     */ 
/*  45 */     List category_list = SubjectCountDAO.getAllSubjectCategory(site_id);
/*  46 */     if ((category_list != null) && (category_list.size() > 0))
/*     */     {
/*  49 */       setCategroyMap(count_map, category_list);
/*     */ 
/*  51 */       Map m = new HashMap();
/*  52 */       m.put("con", con);
/*  53 */       List main_list = SubjectCountDAO.getSubjectCategoryCount(m);
/*  54 */       setCountMap(count_map, main_list);
/*     */ 
/*  56 */       List chat_list = SubjectCountDAO.getSubjectCategoryCount_chat(m);
/*  57 */       setChatCountToMap(count_map, chat_list);
/*     */ 
/*  59 */       con = con + " and cs.publish_status=1";
/*  60 */       m.put("con", con);
/*  61 */       List public_list = SubjectCountDAO.getSubjectCategoryCount(m);
/*  62 */       setPublishCountToMap(count_map, public_list);
/*     */ 
/*  64 */       List user_list = SubjectCountDAO.getSubjectCategoryCount_user(m);
/*  65 */       setUserCountToMap(count_map, user_list);
/*     */ 
/*  67 */       con = con + " and cs.recommend_flag=1";
/*  68 */       m.put("con", con);
/*  69 */       List recommend_list = SubjectCountDAO.getSubjectCategoryCount(m);
/*  70 */       setRecommendCountToMap(count_map, recommend_list);
/*     */     }
/*     */ 
/*  74 */     String file_name = DateUtil.getCurrentDateTime("yyyyMMddHHmmss");
/*  75 */     createExcel(file_name, getCategorySubjectData(start_time, end_time, count_map, time_type), "category", "访谈模型统计列表");
/*  76 */     count_map.put("file_path", DateUtil.getCurrentDate() + "/" + file_name + ".xls");
/*     */ 
/*  78 */     return count_map;
/*     */   }
/*     */ 
/*     */   public static String getTimeTypeText(String time_type)
/*     */   {
/*  83 */     String str = "按照创建时间统计";
/*  84 */     if ("publish_time".equals(time_type))
/*  85 */       str = "按照发布时间统计";
/*  86 */     if ("start_time".equals(time_type))
/*  87 */       str = "按照直播时间统计";
/*  88 */     return str;
/*     */   }
/*     */ 
/*     */   public static String[][] getCategorySubjectData(String start_time, String end_time, Map m, String time_type)
/*     */   {
/* 100 */     String str = getTimeTypeText(time_type);
/*     */ 
/* 102 */     String[][] data = new String[m.size() + 4][6];
/* 103 */     data[0][0] = "报表生成时间：";
/* 104 */     data[0][1] = DateUtil.getCurrentDateTime();
/* 105 */     data[1][0] = "统计条件：";
/* 106 */     data[1][1] = ("统计方式：" + str);
/* 107 */     data[2][1] = ("日期范围：" + start_time + " -- " + end_time);
/* 108 */     data[3][0] = "访谈模型";
/* 109 */     data[3][1] = "创建主题数";
/* 110 */     data[3][2] = "发布主题数";
/* 111 */     data[3][3] = "推荐主题数";
/* 112 */     data[3][4] = "访问人数";
/* 113 */     data[3][5] = "网友发言数";
/*     */ 
/* 116 */     Iterator iter = m.entrySet().iterator();
/* 117 */     int i = 4;
/* 118 */     while (iter.hasNext()) {
/* 119 */       Entry entry = (Entry)iter.next();
/* 120 */       SubjectCount sc = (SubjectCount)entry.getValue();
/* 121 */       data[i][0] = sc.getCategory_name();
/* 122 */       data[i][1] = sc.getSub_count();
/* 123 */       data[i][2] = sc.getPublish_count();
/* 124 */       data[i][3] = sc.getRecommend_count();
/* 125 */       data[i][4] = sc.getUser_count();
/* 126 */       data[i][5] = sc.getChat_count();
/* 127 */       i++;
/*     */     }
/* 129 */     return data;
/*     */   }
/*     */ 
/*     */   public static void createExcel(String file_name, String[][] data, String type, String sheelName)
/*     */   {
/*     */     try {
/* 135 */       JconfigUtil bc = JconfigFactory.getJconfigUtilInstance("bashConfig");
/* 136 */       String path = bc.getProperty("path", "", "manager_path").trim() + "/interview/count";
/* 137 */       String tempPath = path + "/" + DateUtil.getCurrentDate();
/*     */ 
/* 139 */       File file2 = new File(FormatUtil.formatPath(tempPath));
/* 140 */       if (!file2.exists()) {
/* 141 */         file2.mkdir();
/*     */       }
/* 143 */       String xFile = FormatUtil.formatPath(tempPath + "/" + file_name + ".xls");
/*     */ 
/* 146 */       OutExcel.deleteFile(path);
/*     */ 
/* 148 */       OutExcel oe = new OutExcel(sheelName);
/* 149 */       if ("category".equals(type))
/* 150 */         oe.doOut(xFile, data, 1, 2);
/*     */       else
/* 152 */         oe.doOut(xFile, data, 1, 4);
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 156 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void setCategroyMap(Map m, List<SubjectCount> category_list)
/*     */   {
/* 168 */     for (int i = 0; i < category_list.size(); i++)
/*     */     {
/* 170 */       m.put(((SubjectCount)category_list.get(i)).getCategory_id(), category_list.get(i));
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void setCountMap(Map m, List<SubjectCount> main_list)
/*     */   {
/* 182 */     for (int i = 0; i < main_list.size(); i++)
/*     */       try
/*     */       {
/* 185 */         SubjectCount sc = (SubjectCount)m.get(((SubjectCount)main_list.get(i)).getCategory_id());
/* 186 */         sc.setSub_count(((SubjectCount)main_list.get(i)).getSub_count());
/*     */       }
/*     */       catch (Exception e) {
/* 189 */         System.out.println("setChatCountToMap category is null " + ((SubjectCount)main_list.get(i)).getCategory_id());
/* 190 */         e.printStackTrace();
/*     */       }
/*     */   }
/*     */ 
/*     */   public static void setChatCountToMap(Map m, List<SubjectCount> chat_list)
/*     */   {
/* 203 */     for (int i = 0; i < chat_list.size(); i++)
/*     */       try
/*     */       {
/* 206 */         SubjectCount sc = (SubjectCount)m.get(((SubjectCount)chat_list.get(i)).getCategory_id());
/* 207 */         sc.setChat_count(((SubjectCount)chat_list.get(i)).getChat_count());
/*     */       }
/*     */       catch (Exception e) {
/* 210 */         System.out.println("setChatCountToMap category is null " + ((SubjectCount)chat_list.get(i)).getCategory_id());
/* 211 */         e.printStackTrace();
/*     */       }
/*     */   }
/*     */ 
/*     */   public static void setPublishCountToMap(Map m, List<SubjectCount> public_list)
/*     */   {
/* 224 */     for (int i = 0; i < public_list.size(); i++)
/*     */       try
/*     */       {
/* 227 */         SubjectCount sc = (SubjectCount)m.get(((SubjectCount)public_list.get(i)).getCategory_id());
/* 228 */         sc.setPublish_count(((SubjectCount)public_list.get(i)).getSub_count());
/*     */       }
/*     */       catch (Exception e) {
/* 231 */         System.out.println("setChatCountToMap category is null " + ((SubjectCount)public_list.get(i)).getCategory_id());
/* 232 */         e.printStackTrace();
/*     */       }
/*     */   }
/*     */ 
/*     */   public static void setUserCountToMap(Map m, List<SubjectCount> public_list)
/*     */   {
/* 245 */     for (int i = 0; i < public_list.size(); i++)
/*     */       try
/*     */       {
/* 248 */         SubjectCount sc = (SubjectCount)m.get(((SubjectCount)public_list.get(i)).getCategory_id());
/* 249 */         sc.setUser_count(((SubjectCount)public_list.get(i)).getUser_count());
/*     */       }
/*     */       catch (Exception e) {
/* 252 */         System.out.println("setChatCountToMap category is null " + ((SubjectCount)public_list.get(i)).getCategory_id());
/* 253 */         e.printStackTrace();
/*     */       }
/*     */   }
/*     */ 
/*     */   public static void setRecommendCountToMap(Map m, List<SubjectCount> recommend_list)
/*     */   {
/* 266 */     for (int i = 0; i < recommend_list.size(); i++)
/*     */       try
/*     */       {
/* 269 */         SubjectCount sc = (SubjectCount)m.get(((SubjectCount)recommend_list.get(i)).getCategory_id());
/* 270 */         sc.setRecommend_count(((SubjectCount)recommend_list.get(i)).getSub_count());
/*     */       }
/*     */       catch (Exception e)
/*     */       {
/* 274 */         System.out.println("setChatCountToMap category is null " + ((SubjectCount)recommend_list.get(i)).getCategory_id());
/* 275 */         e.printStackTrace();
/*     */       }
/*     */   }
/*     */ 
/*     */   public static Map getHotCount(String start_time, String end_time, String category_ids, String order_type, int count_num, String time_type)
/*     */   {
/* 292 */     String con = " and cs." + time_type + " > '" + start_time + " 00:00' and cs." + time_type + " < '" + end_time + " 23:59'";
/* 293 */     Map m = new HashMap();
/* 294 */     m.put("con", con);
/* 295 */     m.put("category_ids", category_ids);
/* 296 */     List count_list = new ArrayList();
/* 297 */     Map count_map = new HashMap();
/* 298 */     setListToMap(count_list, order_type, m, count_num);
/* 299 */     count_map.put("count_list", count_list);
/*     */ 
/* 302 */     String file_name = DateUtil.getCurrentDateTime("yyyyMMddHHmmss");
/* 303 */     createExcel(file_name, getHotCountData(start_time, end_time, category_ids, count_list, order_type, count_num, time_type), "hot", "访谈热度排行");
/* 304 */     count_map.put("file_path", DateUtil.getCurrentDate() + "/" + file_name + ".xls");
/* 305 */     return count_map;
/*     */   }
/*     */ 
/*     */   public static String[][] getHotCountData(String start_time, String end_time, String category_ids, List<SubjectCount> count_list, String order_type, int count_num, String time_type)
/*     */   {
/* 317 */     String str = getTimeTypeText(time_type);
/* 318 */     String mesg = "按照访问人数";
/* 319 */     if ("chat".equals(order_type))
/* 320 */       mesg = "按照网友发言条数";
/* 321 */     String co_num = "";
/* 322 */     if (count_num == 0)
/* 323 */       co_num = "所有的";
/*     */     else
/* 325 */       co_num = count_num;
/* 326 */     String[][] data = new String[count_list.size() + 6][4];
/* 327 */     data[0][0] = "报表生成时间：";
/* 328 */     data[0][1] = DateUtil.getCurrentDateTime();
/* 329 */     data[1][0] = "统计条件：";
/* 330 */     data[1][1] = ("统计方式：" + str);
/* 331 */     data[2][1] = ("日期范围：" + start_time + " -- " + end_time);
/* 332 */     data[3][1] = ("排行依据：" + mesg + "  显示条数：" + co_num);
/* 333 */     data[4][1] = ("所属模型：" + getCategoryNames(category_ids));
/* 334 */     data[5][0] = "访谈主题名称";
/* 335 */     data[5][1] = "访谈模型";
/* 336 */     data[5][2] = "访问人数";
/* 337 */     data[5][3] = "网友发言数";
/*     */ 
/* 339 */     if ((count_list != null) && (count_list.size() > 0))
/*     */     {
/* 341 */       for (int i = 0; i < count_list.size(); i++)
/*     */       {
/* 343 */         data[(i + 6)][0] = ((SubjectCount)count_list.get(i)).getSub_name();
/* 344 */         data[(i + 6)][1] = ((SubjectCount)count_list.get(i)).getCategory_name();
/* 345 */         data[(i + 6)][2] = ((SubjectCount)count_list.get(i)).getUser_count();
/* 346 */         data[(i + 6)][3] = ((SubjectCount)count_list.get(i)).getChat_count();
/*     */       }
/*     */     }
/* 349 */     return data;
/*     */   }
/*     */ 
/*     */   public static String getCategoryNames(String category_ids)
/*     */   {
/* 359 */     String names = "";
/* 360 */     List l = SubjectCategoryDAO.getSubCategoryAllName();
/* 361 */     if ((l != null) && (l.size() > 0))
/*     */     {
/* 363 */       for (int i = 0; i < l.size(); i++)
/*     */       {
/* 365 */         if (category_ids.contains(((SubjectCategory)l.get(i)).getCategory_id()))
/* 366 */           names = names + "," + ((SubjectCategory)l.get(i)).getCategory_name();
/*     */       }
/* 368 */       names = names.substring(1);
/*     */     }
/* 370 */     return names;
/*     */   }
/*     */ 
/*     */   public static void setSubjectMap(Map m, List<SubjectCount> list)
/*     */   {
/* 381 */     for (int i = 0; i < list.size(); i++)
/*     */     {
/* 383 */       m.put(((SubjectCount)list.get(i)).getSub_id(), list.get(i));
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void setListToMap(List<SubjectCount> count_list, String order_type, Map m, int count_num)
/*     */   {
/* 390 */     List user_list = SubjectCountDAO.getHotCount_user(m);
/*     */ 
/* 392 */     List chat_list = SubjectCountDAO.getHotCount_chat(m);
/*     */ 
/* 394 */     Map list_map = new HashMap();
/*     */ 
/* 396 */     if ("user".equals(order_type))
/*     */     {
/* 398 */       if ((user_list != null) && (user_list.size() > 0))
/*     */       {
/* 400 */         setSubjectMap(list_map, chat_list);
/*     */ 
/* 402 */         if ((user_list.size() < count_num) || (count_num == 0)) {
/* 403 */           count_num = user_list.size();
/*     */         }
/* 405 */         for (int i = 0; i < count_num; i++)
/*     */         {
/* 407 */           if (list_map.containsKey(((SubjectCount)user_list.get(i)).getSub_id()))
/*     */           {
/* 409 */             SubjectCount sc = (SubjectCount)list_map.get(((SubjectCount)user_list.get(i)).getSub_id());
/* 410 */             ((SubjectCount)user_list.get(i)).setChat_count(sc.getChat_count());
/*     */           }
/*     */ 
/* 413 */           count_list.add((SubjectCount)user_list.get(i));
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/* 418 */     if ("chat".equals(order_type))
/*     */     {
/* 420 */       if ((chat_list != null) && (chat_list.size() > 0))
/*     */       {
/* 423 */         setSubjectMap(list_map, user_list);
/* 424 */         if ((chat_list.size() < count_num) || (count_num == 0))
/* 425 */           count_num = chat_list.size();
/* 426 */         for (int i = 0; i < count_num; i++)
/*     */         {
/* 428 */           if (list_map.containsKey(((SubjectCount)chat_list.get(i)).getSub_id()))
/*     */           {
/* 430 */             SubjectCount sc = (SubjectCount)list_map.get(((SubjectCount)user_list.get(i)).getSub_id());
/* 431 */             ((SubjectCount)chat_list.get(i)).setChat_count(sc.getUser_count());
/*     */           }
/* 433 */           count_list.add((SubjectCount)chat_list.get(i));
/*     */         }
/*     */ 
/* 436 */         if (count_list.size() < user_list.size())
/*     */         {
/* 438 */           for (int i = 0; i < count_num; i++)
/*     */           {
/* 440 */             if (!((SubjectCount)user_list.get(i)).getSub_id().equals(((SubjectCount)chat_list.get(i)).getSub_id()))
/*     */             {
/* 442 */               count_list.add((SubjectCount)user_list.get(i));
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */       else
/*     */       {
/* 449 */         if ((user_list.size() < count_num) || (count_num == 0)) {
/* 450 */           count_num = user_list.size();
/*     */         }
/* 452 */         for (int i = 0; i < count_num; i++)
/*     */         {
/* 454 */           count_list.add((SubjectCount)user_list.get(i));
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/* 466 */     System.out.println(getHotCount("2010-06-01", "2010-07-30", "'05c209d4-48f0-406f-8045-ddeeda6b005e','62edc16e-889c-4440-a57a-384bb30fe6fd','8ffe0b67-e64c-48d1-a6a4-ef43a49dbc54','a6ae3d74-70af-4c73-afca-5bd105b1c779','117ac189-3818-4bb8-afdb-f5f3eeb81161','6f5668b3-b131-4062-83ba-5df285b66901','f0af2ac1-9837-453d-a9ca-05ddfdb3d863','bd5c629c-727c-4202-b052-8f13ee13334c','a6bf8cf8-5eee-4cef-ac59-fd5cad642c94','eefb91b0-968d-45cb-8dac-1297d085b2d1','07fabb64-8517-4a9e-99be-70d9a7796f3c'", "user", 0, "publish_time"));
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.interview.SubjectCountServices
 * JD-Core Version:    0.6.2
 */