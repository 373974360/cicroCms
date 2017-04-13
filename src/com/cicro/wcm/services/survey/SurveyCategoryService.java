/*     */ package com.cicro.wcm.services.survey;
/*     */ 
/*     */ import com.cicro.util.DateUtil;
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.bean.survey.SurveyCategory;
/*     */ import com.cicro.wcm.catchs.ISyncCatch;
/*     */ import com.cicro.wcm.catchs.SyncCatchHandl;
/*     */ import com.cicro.wcm.dao.survey.SurveyCategoryDAO;
/*     */ import java.io.PrintStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.UUID;
/*     */ 
/*     */ public class SurveyCategoryService
/*     */   implements ISyncCatch
/*     */ {
/*  18 */   private static Map<String, SurveyCategory> cat_map = new HashMap();
/*     */ 
/*     */   static {
/*  21 */     reloadCatchHandl();
/*     */   }
/*     */ 
/*     */   public void reloadCatch()
/*     */   {
/*  26 */     reloadCatchHandl();
/*     */   }
/*     */ 
/*     */   public static void reloadCatchHandl()
/*     */   {
/*  31 */     cat_map.clear();
/*  32 */     List l = SurveyCategoryDAO.getAllSurveyCategoryList();
/*  33 */     if ((l != null) && (l.size() > 0))
/*     */     {
/*  35 */       for (int i = 0; i < l.size(); i++)
/*     */       {
/*  37 */         cat_map.put(((SurveyCategory)l.get(i)).getCategory_id(), (SurveyCategory)l.get(i));
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void reloadSurveyCategory()
/*     */   {
/*  44 */     SyncCatchHandl.reladCatchForRMI("com.cicro.wcm.services.survey.SurveyCategoryService");
/*     */   }
/*     */ 
/*     */   public static List getSurveyCategoryListBrowser(String con, int start_num, int page_size, String site_id)
/*     */   {
/*  56 */     Map m = new HashMap();
/*  57 */     m.put("start_num", Integer.valueOf(start_num));
/*  58 */     m.put("page_size", Integer.valueOf(page_size));
/*  59 */     con = con + " and publish_status = 1 ";
/*  60 */     m.put("con", con);
/*  61 */     m.put("site_id", site_id);
/*  62 */     return SurveyCategoryDAO.getSurveyCategoryList(m);
/*     */   }
/*     */ 
/*     */   public static String getSurveyCategoryCount(String con, String site_id)
/*     */   {
/*  72 */     Map m = new HashMap();
/*  73 */     m.put("con", con);
/*  74 */     m.put("site_id", site_id);
/*  75 */     return SurveyCategoryDAO.getSurveyCategoryCount(m);
/*     */   }
/*     */ 
/*     */   public static List getSurveyCategoryList(String con, int start_num, int page_size, String site_id)
/*     */   {
/*  87 */     Map m = new HashMap();
/*  88 */     m.put("start_num", Integer.valueOf(start_num));
/*  89 */     m.put("page_size", Integer.valueOf(page_size));
/*  90 */     m.put("con", con);
/*  91 */     m.put("site_id", site_id);
/*  92 */     return SurveyCategoryDAO.getSurveyCategoryList(m);
/*     */   }
/*     */ 
/*     */   public static SurveyCategory getSurveyCategoryBean(String category_id)
/*     */   {
/* 102 */     if (cat_map.containsKey(category_id))
/*     */     {
/* 104 */       return (SurveyCategory)cat_map.get(category_id);
/*     */     }
/*     */ 
/* 107 */     SurveyCategory sc = SurveyCategoryDAO.getSurveyCategoryBean(category_id);
/* 108 */     if (sc != null)
/*     */     {
/* 110 */       cat_map.put(category_id, sc);
/* 111 */       return sc;
/*     */     }
/* 113 */     return null;
/*     */   }
/*     */ 
/*     */   public static List getAllSurveyCategoryName(String site_id)
/*     */   {
/* 124 */     List l = new ArrayList();
/* 125 */     Set s = cat_map.keySet();
/* 126 */     for (String i : s)
/*     */     {
/* 128 */       SurveyCategory sc = (SurveyCategory)cat_map.get(i);
/* 129 */       if ((site_id.equals(sc.getSite_id())) && (sc.getIs_delete() == 0) && (sc.getPublish_status() == 1))
/* 130 */         l.add(sc);
/*     */     }
/* 132 */     return l;
/*     */   }
/*     */ 
/*     */   public static boolean insertSurveyCategory(SurveyCategory sc, SettingLogsBean stl)
/*     */   {
/* 142 */     String c_time = DateUtil.getCurrentDateTime();
/* 143 */     sc.setCategory_id(UUID.randomUUID().toString());
/* 144 */     if (sc.getPublish_status() == 1)
/* 145 */       sc.setPublish_time(c_time);
/* 146 */     sc.setAdd_time(c_time);
/* 147 */     if (SurveyCategoryDAO.insertSurveyCategory(sc, stl))
/*     */     {
/* 149 */       reloadSurveyCategory();
/* 150 */       return true;
/*     */     }
/* 152 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateSurveyCategory(SurveyCategory sc, SettingLogsBean stl)
/*     */   {
/* 162 */     String c_time = DateUtil.getCurrentDateTime();
/* 163 */     if (sc.getPublish_status() == 1)
/*     */     {
/* 165 */       if ("".equals(sc.getPublish_time())) {
/* 166 */         sc.setPublish_time(c_time);
/*     */       }
/*     */     }
/*     */     else {
/* 170 */       sc.setPublish_time("");
/*     */     }
/* 172 */     sc.setUpdate_time(c_time);
/* 173 */     if (SurveyCategoryDAO.updateSurveyCategory(sc, stl))
/*     */     {
/* 175 */       reloadSurveyCategory();
/* 176 */       return true;
/*     */     }
/* 178 */     return false;
/*     */   }
/*     */ 
/*     */   public static String getSurveyCountByCategoryID(int id)
/*     */   {
/* 188 */     return SurveyCategoryDAO.getSurveyCountByCategoryID(id);
/*     */   }
/*     */ 
/*     */   public static boolean deleteSurvey(String ids, String user_name, SettingLogsBean stl)
/*     */   {
/* 200 */     Map m = new HashMap();
/* 201 */     m.put("ids", ids);
/* 202 */     m.put("user_name", user_name);
/* 203 */     m.put("current_time", DateUtil.getCurrentDateTime());
/* 204 */     if (SurveyCategoryDAO.deleteSurveyCategory(m, stl))
/*     */     {
/* 206 */       reloadSurveyCategory();
/* 207 */       return true;
/*     */     }
/* 209 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateSurveyCategoryPublishStatus(String ids, String publish_status, String user_name, SettingLogsBean stl)
/*     */   {
/* 221 */     Map m = new HashMap();
/* 222 */     m.put("ids", ids);
/* 223 */     m.put("user_name", user_name);
/* 224 */     m.put("current_time", DateUtil.getCurrentDateTime());
/* 225 */     m.put("publish_status", publish_status);
/* 226 */     if (SurveyCategoryDAO.updateSurveyCategoryPublishStatus(m, stl))
/*     */     {
/* 228 */       reloadSurveyCategory();
/* 229 */       return true;
/*     */     }
/* 231 */     return false;
/*     */   }
/*     */ 
/*     */   public static String getSurveyTemplate(String template_type, String site_id)
/*     */   {
/* 241 */     Set s = cat_map.keySet();
/* 242 */     for (String i : s)
/*     */     {
/* 244 */       SurveyCategory sc = (SurveyCategory)cat_map.get(i);
/* 245 */       if (site_id.equals(sc.getSite_id()))
/*     */       {
/* 247 */         if ("list".equals(template_type))
/*     */         {
/* 249 */           if (!"".equals(sc.getTemplate_list_path()))
/* 250 */             return sc.getTemplate_list_path();
/*     */         }
/* 252 */         if ("content".equals(template_type))
/*     */         {
/* 254 */           if (!"".equals(sc.getTemplate_content_path()))
/* 255 */             return sc.getTemplate_content_path();
/*     */         }
/* 257 */         if ("result".equals(template_type))
/*     */         {
/* 259 */           if (!"".equals(sc.getTemplate_result_path()))
/* 260 */             return sc.getTemplate_result_path();
/*     */         }
/*     */       }
/*     */     }
/* 264 */     return "";
/*     */   }
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/* 269 */     System.out.println(getAllSurveyCategoryName("11111ddd"));
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.survey.SurveyCategoryService
 * JD-Core Version:    0.6.2
 */