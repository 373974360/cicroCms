/*     */ package com.cicro.wcm.services.survey;
/*     */ 
/*     */ import com.cicro.util.DateUtil;
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.bean.survey.SurveyBean;
/*     */ import com.cicro.wcm.bean.survey.SurveyChildItem;
/*     */ import com.cicro.wcm.bean.survey.SurveySub;
/*     */ import com.cicro.wcm.bean.survey.SurveySuvItem;
/*     */ import com.cicro.wcm.catchs.ISyncCatch;
/*     */ import com.cicro.wcm.catchs.SyncCatchHandl;
/*     */ import com.cicro.wcm.dao.survey.SurveyDAO;
/*     */ import java.io.PrintStream;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.UUID;
/*     */ import org.apache.commons.collections.map.LRUMap;
/*     */ 
/*     */ public class SurveyService
/*     */   implements ISyncCatch
/*     */ {
/*  27 */   private static LRUMap survey_Map = new LRUMap(100);
/*     */ 
/*  31 */   private static LRUMap survey_sub_Map = new LRUMap(100);
/*     */ 
/*     */   static
/*     */   {
/*  36 */     reloadCatchHandl();
/*     */   }
/*     */ 
/*     */   public void reloadCatch()
/*     */   {
/*  41 */     reloadCatchHandl();
/*     */   }
/*     */ 
/*     */   public static void reloadCatchHandl()
/*     */   {
/*     */     try {
/*  47 */       survey_Map.clear();
/*  48 */       survey_sub_Map.clear();
/*  49 */       List survey_list = SurveyDAO.getAllSurveyObjBYPublish();
/*  50 */       if ((survey_list != null) && (survey_list.size() > 0))
/*     */       {
/*  52 */         for (int i = 0; i < survey_list.size(); i++)
/*     */         {
/*  54 */           survey_Map.put(((SurveyBean)survey_list.get(i)).getS_id(), survey_list.get(i));
/*     */         }
/*     */       }
/*     */     }
/*     */     catch (Exception e) {
/*  59 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void reLoadSurveyBean()
/*     */   {
/*  67 */     SyncCatchHandl.reladCatchForRMI("com.cicro.wcm.services.survey.SurveyService");
/*     */   }
/*     */ 
/*     */   public static void reLoadSurveyBeanBySID(String s_id)
/*     */   {
/*  74 */     survey_Map.remove(s_id);
/*  75 */     survey_Map.put(s_id, getSurveyBean(s_id));
/*     */   }
/*     */ 
/*     */   public static String getSurveyRecommendListCount(String con)
/*     */   {
/*  85 */     Map m = new HashMap();
/*  86 */     m.put("con", con);
/*  87 */     return SurveyDAO.getSurveyRecommendListCount(m);
/*     */   }
/*     */ 
/*     */   public static String getSurveyListCountBrowser(String con)
/*     */   {
/*  97 */     Map m = new HashMap();
/*  98 */     con = con + " and cs.publish_status = 1 ";
/*  99 */     m.put("con", con);
/* 100 */     return SurveyDAO.getSurveyListCountBrowser(m);
/*     */   }
/*     */ 
/*     */   public static List getSurveyListBrowser(String con, int start_num, int page_size, String ordery_by)
/*     */   {
/* 114 */     Map m = new HashMap();
/* 115 */     m.put("ordery_by", ordery_by);
/* 116 */     m.put("start_num", Integer.valueOf(start_num));
/* 117 */     m.put("page_size", Integer.valueOf(page_size));
/* 118 */     con = con + " and cs.publish_status = 1 ";
/* 119 */     m.put("con", con);
/* 120 */     return SurveyDAO.getSurveyList(m);
/*     */   }
/*     */ 
/*     */   public static List getSurveyRecommendList(String con, int start_num, int page_size)
/*     */   {
/* 130 */     Map m = new HashMap();
/* 131 */     m.put("start_num", Integer.valueOf(start_num));
/* 132 */     m.put("page_size", Integer.valueOf(page_size));
/* 133 */     m.put("con", con);
/* 134 */     return SurveyDAO.getSurveyRecommendList(m);
/*     */   }
/*     */ 
/*     */   public static boolean updateSurveyRecommend(String ids, String recommend_flag)
/*     */   {
/* 145 */     Map m = new HashMap();
/* 146 */     m.put("ids", ids);
/* 147 */     m.put("recommend_flag", recommend_flag);
/* 148 */     if ("1".equals(recommend_flag))
/* 149 */       m.put("current_time", DateUtil.getCurrentDateTime());
/*     */     else
/* 151 */       m.put("current_time", "");
/* 152 */     return SurveyDAO.updateSurveyRecommend(m);
/*     */   }
/*     */ 
/*     */   public static String getSurveyCount(String con, String site_id)
/*     */   {
/* 162 */     Map m = new HashMap();
/* 163 */     m.put("con", con);
/* 164 */     m.put("site_id", site_id);
/* 165 */     return SurveyDAO.getSurveyCount(m);
/*     */   }
/*     */ 
/*     */   public static List getSurveyList(String con, int start_num, int page_size, String site_id)
/*     */   {
/* 177 */     Map m = new HashMap();
/* 178 */     m.put("start_num", Integer.valueOf(start_num));
/* 179 */     m.put("page_size", Integer.valueOf(page_size));
/* 180 */     m.put("con", con);
/* 181 */     m.put("site_id", site_id);
/* 182 */     return SurveyDAO.getSurveyList(m);
/*     */   }
/*     */ 
/*     */   public static SurveyBean getSurveyBean(String s_id)
/*     */   {
/* 193 */     if (survey_Map.containsKey(s_id))
/*     */     {
/* 195 */       return (SurveyBean)survey_Map.get(s_id);
/*     */     }
/*     */ 
/* 198 */     SurveyBean sb = SurveyDAO.getSurveyBean(s_id);
/* 199 */     survey_Map.put("s_id", sb);
/* 200 */     return sb;
/*     */   }
/*     */ 
/*     */   public static List getSurveySubjectBean(String s_id)
/*     */   {
/* 211 */     if (survey_sub_Map.containsKey(s_id))
/*     */     {
/* 213 */       return (List)survey_sub_Map.get(s_id);
/*     */     }
/*     */ 
/* 217 */     List l = SurveyDAO.getSurveySubjectBean(s_id);
/* 218 */     survey_sub_Map.put(s_id, l);
/* 219 */     return l;
/*     */   }
/*     */ 
/*     */   public static List getSurveySubjectSingle(String s_id)
/*     */   {
/* 230 */     return SurveyDAO.getSurveySubjectSingle(s_id);
/*     */   }
/*     */ 
/*     */   public static boolean updateSurvey(SurveyBean sb, List l, SettingLogsBean stl)
/*     */   {
/* 240 */     sb.setUpdate_time(DateUtil.getCurrentDateTime());
/*     */     try {
/* 242 */       if (SurveyDAO.updateSurvey(sb, stl))
/*     */       {
/* 245 */         SurveyDAO.deleteSubjectItem(sb.getS_id(), stl);
/* 246 */         insertSubjectItme(sb.getS_id(), l, stl);
/*     */       }
/*     */ 
/* 249 */       reLoadSurveyBeanBySID(sb.getS_id());
/*     */ 
/* 251 */       survey_sub_Map.remove(sb.getS_id());
/* 252 */       return true;
/*     */     } catch (Exception e) {
/*     */     }
/* 255 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean setSurveyAttr(SurveyBean sb, SettingLogsBean stl)
/*     */   {
/* 266 */     sb.setUpdate_time(DateUtil.getCurrentDateTime());
/* 267 */     if (SurveyDAO.setSurveyAttr(sb, stl))
/*     */     {
/* 270 */       reLoadSurveyBeanBySID(sb.getS_id());
/* 271 */       return true;
/*     */     }
/* 273 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean insertSurvey(SurveyBean sb, List l, SettingLogsBean stl)
/*     */   {
/* 283 */     String survey_id = UUID.randomUUID().toString();
/* 284 */     sb.setAdd_time(DateUtil.getCurrentDateTime());
/* 285 */     sb.setS_id(survey_id);
/*     */ 
/* 288 */     if (SurveyDAO.insertSurvey(sb, stl))
/*     */     {
/* 290 */       insertSubjectItme(survey_id, l, stl);
/*     */     }
/* 292 */     return true;
/*     */   }
/*     */ 
/*     */   public static void insertSubjectItme(String survey_id, List l, SettingLogsBean stl)
/*     */   {
/* 298 */     if ((l != null) && (l.size() > 0))
/*     */     {
/* 300 */       List subList = l;
/* 301 */       for (int i = 0; i < subList.size(); i++)
/*     */       {
/* 303 */         String sub_id = UUID.randomUUID().toString();
/* 304 */         SurveySub sub = (SurveySub)subList.get(i);
/* 305 */         sub.setSurvey_id(survey_id);
/* 306 */         sub.setSubject_id(sub_id);
/*     */ 
/* 308 */         SurveyDAO.insertSurveySub(sub, stl);
/*     */ 
/* 310 */         if ((sub.getItemList() != null) && (sub.getItemList().size() > 0))
/*     */         {
/* 312 */           List itemList = sub.getItemList();
/* 313 */           for (int j = 0; j < itemList.size(); j++)
/*     */           {
/* 315 */             SurveySuvItem item = (SurveySuvItem)itemList.get(j);
/* 316 */             item.setSurvey_id(survey_id);
/* 317 */             item.setSubject_id(sub_id);
/*     */ 
/* 319 */             SurveyDAO.insertSurveySubItem(item);
/*     */ 
/* 322 */             List childList = item.getChildList();
/* 323 */             if ((childList != null) && (childList.size() > 0))
/*     */             {
/* 325 */               for (int k = 0; k < childList.size(); k++)
/*     */               {
/* 327 */                 SurveyChildItem ci = (SurveyChildItem)childList.get(k);
/* 328 */                 ci.setSubject_id(sub_id);
/*     */ 
/* 330 */                 SurveyDAO.insertSurveyChildItem(ci);
/*     */               }
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public static boolean insertSurveySub(List l)
/*     */   {
/* 347 */     if ((l != null) && (l.size() > 0))
/*     */     {
/* 349 */       List subList = l;
/* 350 */       for (int i = 0; i < subList.size(); i++)
/*     */       {
/* 352 */         SurveySub sub = (SurveySub)subList.get(i);
/* 353 */         if ((sub.getItemList() != null) && (sub.getItemList().size() > 0))
/*     */         {
/* 355 */           List itemList = sub.getItemList();
/* 356 */           for (int j = 0; j < itemList.size(); j++)
/*     */           {
/* 358 */             SurveySuvItem localSurveySuvItem = (SurveySuvItem)itemList.get(j);
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/* 364 */     return true;
/*     */   }
/*     */ 
/*     */   public static boolean publishSurvey(String ids, String publish_status, String user_name, SettingLogsBean stl)
/*     */   {
/* 376 */     Map m = new HashMap();
/* 377 */     m.put("ids", ids);
/* 378 */     m.put("user_name", user_name);
/* 379 */     m.put("current_time", DateUtil.getCurrentDateTime());
/* 380 */     m.put("publish_status", publish_status);
/* 381 */     if (SurveyDAO.publishSurvey(m, stl))
/*     */     {
/* 383 */       reLoadSurveyBean();
/* 384 */       return true;
/*     */     }
/* 386 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteSurvey(String ids, String user_name, SettingLogsBean stl)
/*     */   {
/* 397 */     Map m = new HashMap();
/* 398 */     m.put("ids", ids);
/* 399 */     m.put("user_name", user_name);
/* 400 */     m.put("current_time", DateUtil.getCurrentDateTime());
/* 401 */     if (SurveyDAO.deleteSurvey(m, stl))
/*     */     {
/* 403 */       reLoadSurveyBean();
/* 404 */       return true;
/*     */     }
/*     */ 
/* 407 */     return false;
/*     */   }
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/* 412 */     System.out.println(getSurveySubjectSingle("d25fb4f2-06dc-4be0-902e-c33ca1ee8ca5"));
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.survey.SurveyService
 * JD-Core Version:    0.6.2
 */