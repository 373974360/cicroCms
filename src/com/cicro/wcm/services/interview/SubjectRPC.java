/*     */ package com.cicro.wcm.services.interview;
/*     */ 
/*     */ import com.cicro.wcm.bean.interview.SubReleInfo;
/*     */ import com.cicro.wcm.bean.interview.SubjectActor;
/*     */ import com.cicro.wcm.bean.interview.SubjectBean;
/*     */ import com.cicro.wcm.bean.interview.SubjectCategory;
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.services.Log.LogManager;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ 
/*     */ public class SubjectRPC
/*     */ {
/*     */   public static List getSubjectRecommendList(String con, int start_num, int page_size)
/*     */   {
/*  47 */     return SubjectServices.getSubjectRecommendList(con, start_num, page_size);
/*     */   }
/*     */ 
/*     */   public static boolean updateSubjectRecommend(String ids, String recommend_flag)
/*     */   {
/*  58 */     return SubjectServices.updateSubjectRecommend(ids, recommend_flag);
/*     */   }
/*     */ 
/*     */   public static String getSubjectCount(String con, String login_user_name)
/*     */   {
/*  68 */     return SubjectServices.getSubjectCount(con, login_user_name);
/*     */   }
/*     */ 
/*     */   public static String getSubjectManagerCount(String con, String site_id)
/*     */   {
/*  78 */     return SubjectServices.getSubjectManagerCount(con, site_id);
/*     */   }
/*     */ 
/*     */   public static SubjectBean getSubjectObj(int id)
/*     */   {
/*  88 */     return SubjectServices.getSubjectObj(id);
/*     */   }
/*     */ 
/*     */   public static SubjectBean getSubjectObjBySubID(String sub_id,boolean is_sync)
/*     */   {
			  if(is_sync)
			  {
				  SubjectServices.reloadCatchHandl();
			  }
/*  98 */     return SubjectServices.getSubjectObjBySubID(sub_id);
/*     */   }
/*     */ 
/*     */   public static boolean insertSubject(SubjectBean sub, HttpServletRequest request)
/*     */   {
/* 108 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 109 */     if (stl != null)
/*     */     {
/* 111 */       return SubjectServices.insertSubject(sub, stl);
/*     */     }
/* 113 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateSubject(SubjectBean sub, HttpServletRequest request)
/*     */   {
/* 122 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 123 */     if (stl != null)
/*     */     {
/* 125 */       return SubjectServices.updateSubject(sub, stl);
/*     */     }
/* 127 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteSubject(String ids, String user_name, HttpServletRequest request)
/*     */   {
/* 138 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/*     */ 
/* 140 */     if (stl != null)
/*     */     {
/* 142 */       return SubjectServices.deleteSubject(ids, user_name, stl);
/*     */     }
/* 144 */     return false;
/*     */   }
/*     */ 
/*     */   public static List getSubjectList(String con, int start_num, int page_size, String login_user_name)
/*     */   {
/* 155 */     return SubjectServices.getSubjectList(con, start_num, page_size, login_user_name);
/*     */   }
/*     */ 
/*     */   public static List getSubjectManagerList(String con, int start_num, int page_size, String site_id)
/*     */   {
/* 166 */     return SubjectServices.getSubjectManagerList(con, start_num, page_size, site_id);
/*     */   }
/*     */ 
/*     */   public static boolean updateSubjectStatus(String ids, String filds, String status_flag, String oper_name, String oper_message, String user_name, String user_id, HttpServletRequest request)
/*     */   {
/* 182 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 183 */     if (stl != null)
/*     */     {
/* 185 */       return SubjectServices.updateSubjectStatus(ids, filds, status_flag, oper_name, oper_message, user_name, user_id, stl);
/*     */     }
/* 187 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean subjectSubmit(String ids, int status_flag, String user_id, HttpServletRequest request)
/*     */   {
/* 199 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 200 */     if (stl != null)
/*     */     {
/* 202 */       return SubjectServices.subjectSubmit(ids, status_flag, user_id, stl);
/*     */     }
/* 204 */     return false;
/*     */   }
/*     */ 
/*     */   public static SubjectBean getHistoryRecord(String sub_id)
/*     */   {
/* 214 */     return SubjectServices.getHistoryRecord(sub_id);
/*     */   }
/*     */ 
/*     */   public static boolean updateHistoryRecord(SubjectBean sb, HttpServletRequest request)
/*     */   {
/* 225 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 226 */     if (stl != null)
/*     */     {
/* 228 */       return SubjectServices.updateHistoryRecord(sb, stl);
/*     */     }
/* 230 */     return false;
/*     */   }
/*     */ 
/*     */   public static List getResouseList(String sub_id, String affix_status, String affix_type)
/*     */   {
/* 244 */     return SubjectServices.getResouseList(sub_id, affix_status, affix_type);
/*     */   }
/*     */ 
/*     */   public static List getResouseListByManager(String sub_id)
/*     */   {
/* 254 */     return SubjectServices.getResouseListByManager(sub_id);
/*     */   }
/*     */ 
/*     */   public static String getSubjectCountByCategoryID(int id)
/*     */   {
/* 267 */     return SubjectCategoryServices.getSubjectCountByCategoryID(id);
/*     */   }
/*     */ 
/*     */   public static String getSubCategoryCount(String con, String site_id)
/*     */   {
/* 276 */     return SubjectCategoryServices.getSubCategoryCount(con, site_id);
/*     */   }
/*     */ 
/*     */   public static List getSubCategoryAllName(String site_id)
/*     */   {
/* 287 */     return SubjectCategoryServices.getSubCategoryAllName(site_id);
/*     */   }
/*     */ 
/*     */   public static SubjectCategory getSubjectCategoryBeanByID(int id)
/*     */   {
/* 297 */     return SubjectCategoryServices.getSubjectCategoryBean(id);
/*     */   }
/*     */ 
/*     */   public static SubjectCategory getSubjectCategoryBeanByCID(String category_id)
/*     */   {
/* 307 */     return SubjectCategoryServices.getSubjectCategoryBean(category_id);
/*     */   }
/*     */ 
/*     */   public static SubjectCategory getSubjectCategoryBean(String category_id)
/*     */   {
/* 317 */     return SubjectCategoryServices.getSubjectCategoryBean(category_id);
/*     */   }
/*     */ 
/*     */   public List getSubCategoryList(String con, int start_num, int page_size, String site_id)
/*     */   {
/* 329 */     return SubjectCategoryServices.getSubCategoryList(con, start_num, page_size, site_id);
/*     */   }
/*     */ 
/*     */   public static boolean insertSubCategory(SubjectCategory sc, HttpServletRequest request)
/*     */   {
/* 338 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 339 */     if (stl != null)
/*     */     {
/* 341 */       return SubjectCategoryServices.insertSubCategory(sc, stl);
/*     */     }
/* 343 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateSubCategory(SubjectCategory sc, HttpServletRequest request)
/*     */   {
/* 353 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 354 */     if (stl != null)
/*     */     {
/* 356 */       return SubjectCategoryServices.updateSubCategory(sc, stl);
/*     */     }
/* 358 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteSubCategory(String ids, String user_name, HttpServletRequest request)
/*     */   {
/* 368 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 369 */     if (stl != null)
/*     */     {
/* 371 */       return SubjectCategoryServices.deleteSubCategory(ids, user_name, stl);
/*     */     }
/* 373 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateSubCategoryState(String ids, int status_flag, String user_name, HttpServletRequest request)
/*     */   {
/* 384 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 385 */     if (stl != null)
/*     */     {
/* 387 */       return SubjectCategoryServices.updateSubCategoryState(ids, status_flag, user_name, stl);
/*     */     }
/* 389 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean saveSubCategorySort(String ids, HttpServletRequest request)
/*     */   {
/* 399 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 400 */     if (stl != null)
/*     */     {
/* 402 */       return SubjectCategoryServices.saveSubCategorySort(ids, stl);
/*     */     }
/* 404 */     return false;
/*     */   }
/*     */ 
/*     */   public static List getAllActorName(String sub_id)
/*     */   {
/* 418 */     return SubjectActorServices.getAllActorName(sub_id);
/*     */   }
/*     */ 
/*     */   public static List getActorList(String con, String sub_id)
/*     */   {
/* 429 */     return SubjectActorServices.getActorList(con, sub_id);
/*     */   }
/*     */ 
/*     */   public static SubjectActor getSubActor(int id)
/*     */   {
/* 439 */     return SubjectActorServices.getSubActor(id);
/*     */   }
/*     */ 
/*     */   public static boolean insertSubActor(SubjectActor sa, HttpServletRequest request)
/*     */   {
/* 449 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 450 */     if (stl != null)
/*     */     {
/* 452 */       return SubjectActorServices.insertSubActor(sa, stl);
/*     */     }
/* 454 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateSubActor(SubjectActor sa, HttpServletRequest request)
/*     */   {
/* 464 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 465 */     if (stl != null)
/*     */     {
/* 467 */       return SubjectActorServices.updateSubActor(sa, stl);
/*     */     }
/* 469 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteSubActor(String ids, String user_name, HttpServletRequest request)
/*     */   {
/* 479 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 480 */     if (stl != null)
/*     */     {
/* 482 */       return SubjectActorServices.deleteSubActor(ids, user_name, stl);
/*     */     }
/* 484 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean saveSubActorSort(String ids, HttpServletRequest request)
/*     */   {
/* 494 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 495 */     if (stl != null)
/*     */     {
/* 497 */       return SubjectActorServices.saveSubActorSort(ids, stl);
/*     */     }
/* 499 */     return false;
/*     */   }
/*     */ 
/*     */   public static String getReleInfoCountBySub_id(String sub_id)
/*     */   {
/* 508 */     return SubReleInfoServices.getReleInfoCountBySub_id(sub_id);
/*     */   }
/*     */ 
/*     */   public static List getReleInfoListBySub_id(String con, int start_num, int page_size, String sub_id)
/*     */   {
/* 519 */     return SubReleInfoServices.getReleInfoListBySub_id(con, start_num, page_size, sub_id);
/*     */   }
/*     */ 
/*     */   public static SubReleInfo getSubReleInfo(int id)
/*     */   {
/* 524 */     return SubReleInfoServices.getSubReleInfo(id);
/*     */   }
/*     */ 
/*     */   public static boolean insertReleInfo(SubReleInfo sri, HttpServletRequest request)
/*     */   {
/* 529 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 530 */     if (stl != null)
/*     */     {
/* 532 */       return SubReleInfoServices.insertReleInfo(sri, stl);
/*     */     }
/* 534 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateReleInfo(SubReleInfo sri, HttpServletRequest request)
/*     */   {
/* 539 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 540 */     if (stl != null)
/*     */     {
/* 542 */       return SubReleInfoServices.updateReleInfo(sri, stl);
/*     */     }
/* 544 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteReleInfo(String ids, String user_name, HttpServletRequest request)
/*     */   {
/* 549 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 550 */     if (stl != null)
/*     */     {
/* 552 */       return SubReleInfoServices.deleteReleInfo(ids, user_name, stl);
/*     */     }
/* 554 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean publishReleInfo(String ids, String user_name, int publish_flag, HttpServletRequest request)
/*     */   {
/* 559 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 560 */     if (stl != null)
/*     */     {
/* 562 */       return SubReleInfoServices.publishReleInfo(ids, user_name, publish_flag, stl);
/*     */     }
/* 564 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean saveReleInfoSort(String ids, HttpServletRequest request)
/*     */   {
/* 574 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 575 */     if (stl != null)
/*     */     {
/* 577 */       return SubReleInfoServices.saveReleInfoSort(ids, stl);
/*     */     }
/* 579 */     return false;
/*     */   }
/*     */ 
/*     */   public static Map getSubjectCategoryCount(String start_time, String end_time, String time_type, String site_id)
/*     */   {
/* 590 */     return SubjectCountServices.getSubjectCategoryCount(start_time, end_time, time_type, site_id);
/*     */   }
/*     */ 
/*     */   public static Map getHotCount(String start_time, String end_time, String category_ids, String order_type, int count_num, String time_type)
/*     */   {
/* 604 */     return SubjectCountServices.getHotCount(start_time, end_time, category_ids, order_type, count_num, time_type);
/*     */   }
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.interview.SubjectRPC
 * JD-Core Version:    0.6.2
 */