/*     */ package com.cicro.wcm.services.interview;
/*     */ 
/*     */ import com.cicro.util.DateUtil;
/*     */ import com.cicro.wcm.bean.interview.SubjectBean;
/*     */ import com.cicro.wcm.bean.interview.SubjectResouse;
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.bean.org.dept.DeptBean;
/*     */ import com.cicro.wcm.bean.org.user.UserBean;
/*     */ import com.cicro.wcm.catchs.ISyncCatch;
/*     */ import com.cicro.wcm.catchs.SyncCatchHandl;
/*     */ import com.cicro.wcm.dao.interview.SubjectDAO;
/*     */ import com.cicro.wcm.services.org.dept.DeptManager;
/*     */ import com.cicro.wcm.services.org.user.UserManager;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.UUID;
/*     */ 
/*     */ public class SubjectServices
/*     */   implements ISyncCatch
/*     */ {
/*  30 */   private static Map<String, SubjectBean> sub_map = new HashMap();
/*     */ 
/*     */   static {
/*  33 */     reloadCatchHandl();
/*     */   }
/*     */ 
/*     */   public void reloadCatch()
/*     */   {
/*  38 */     reloadCatchHandl();
/*     */   }
/*     */ 
/*     */   public static void reloadCatchHandl()
/*     */   {
/*  43 */     sub_map.clear();
/*     */   }
/*     */ 
/*     */   public static void clearMap()
/*     */   {
/*  48 */     SyncCatchHandl.reladCatchForRMI("com.cicro.wcm.services.interview.SubjectServices");
/*     */   }
/*     */ 
/*     */   public static String getSubjectRecommendListCount(String con, String site_id)
/*     */   {
/*  58 */     Map m = new HashMap();
/*  59 */     m.put("con", con);
/*  60 */     m.put("site_id", site_id);
/*  61 */     return SubjectDAO.getSubjectRecommendListCount(m);
/*     */   }
/*     */ 
/*     */   public static String getSubjectBrowserListHandlCount(String con)
/*     */   {
/*  71 */     Map m = new HashMap();
/*  72 */     m.put("con", con);
/*  73 */     return SubjectDAO.getSubjectBrowserListHandlCount(m);
/*     */   }
/*     */ 
/*     */   public static List<SubjectBean> getSubjectBrowserList(String con, int start_num, int page_size, String order_by)
/*     */   {
/*  86 */     List sl = new ArrayList();
/*  87 */     Map m = new HashMap();
/*  88 */     m.put("start_num", Integer.valueOf(start_num));
/*  89 */     m.put("page_size", Integer.valueOf(page_size));
/*  90 */     m.put("con", con);
/*  91 */     m.put("order_by", order_by);
/*  92 */     sl = SubjectDAO.getSubjectBrowserList(m);
/*  93 */     if ((sl != null) && (sl.size() > 0))
/*     */     {
/*  95 */       for (int i = 0; i < sl.size(); i++)
/*     */       {
/*  97 */         ((SubjectBean)sl.get(i)).setActor_name(SubjectActorServices.getAllActorNames(((SubjectBean)sl.get(i)).getSub_id()).replaceAll(",", " "));
/*  98 */         List r = getResouseList(((SubjectBean)sl.get(i)).getSub_id(), "forecast", "pic");
/*  99 */         if ((r != null) && (r.size() > 0))
/* 100 */           ((SubjectBean)sl.get(i)).setS_for_pic(((SubjectResouse)r.get(0)).getAffix_path());
/*     */       }
/*     */     }
/* 103 */     return sl;
/*     */   }
/*     */ 
/*     */   public static List getSubjectRecommendList(String con, int start_num, int page_size)
/*     */   {
/* 114 */     List sl = new ArrayList();
/* 115 */     Map m = new HashMap();
/* 116 */     m.put("start_num", Integer.valueOf(start_num));
/* 117 */     m.put("page_size", Integer.valueOf(page_size));
/* 118 */     m.put("con", con);
/* 119 */     sl = SubjectDAO.getSubjectRecommendList(m);
/* 120 */     if ((sl != null) && (sl.size() > 0))
/*     */     {
/* 122 */       for (int i = 0; i < sl.size(); i++)
/*     */       {
/* 124 */         ((SubjectBean)sl.get(i)).setActor_name(SubjectActorServices.getAllActorNames(((SubjectBean)sl.get(i)).getSub_id()).replaceAll(",", " "));
/* 125 */         List r = getResouseList(((SubjectBean)sl.get(i)).getSub_id(), "forecast", "pic");
/* 126 */         if ((r != null) && (r.size() > 0))
/* 127 */           ((SubjectBean)sl.get(i)).setS_for_pic(((SubjectResouse)r.get(0)).getAffix_path());
/*     */       }
/*     */     }
/* 130 */     return sl;
/*     */   }
/*     */ 
/*     */   public static boolean updateSubjectRecommend(String ids, String recommend_flag)
/*     */   {
/* 141 */     Map m = new HashMap();
/* 142 */     m.put("ids", ids);
/* 143 */     m.put("recommend_flag", recommend_flag);
/* 144 */     if ("1".equals(recommend_flag))
/* 145 */       m.put("current_time", DateUtil.getCurrentDateTime());
/*     */     else
/* 147 */       m.put("current_time", "");
/* 148 */     return SubjectDAO.updateSubjectRecommend(m);
/*     */   }
/*     */ 
/*     */   public static String getSubjectCount(String con, String login_user_name)
/*     */   {
/* 158 */     Map m = new HashMap();
/* 159 */     m.put("login_user_name", login_user_name);
/* 160 */     m.put("con", con);
/* 161 */     return SubjectDAO.getSubjectCount(m);
/*     */   }
/*     */ 
/*     */   public static String getSubjectManagerCount(String con, String site_id)
/*     */   {
/* 170 */     Map m = new HashMap();
/* 171 */     m.put("con", con);
/* 172 */     m.put("site_id", site_id);
/* 173 */     return SubjectDAO.getSubjectManagerCount(m);
/*     */   }
/*     */ 
/*     */   public static List getSubjectList(String con, int start_num, int page_size, String login_user_name)
/*     */   {
/* 183 */     Map m = new HashMap();
/* 184 */     m.put("start_num", Integer.valueOf(start_num));
/* 185 */     m.put("page_size", Integer.valueOf(page_size));
/* 186 */     m.put("login_user_name", login_user_name);
/* 187 */     m.put("con", con);
/* 188 */     return SubjectDAO.getSubjectList(m);
/*     */   }
/*     */ 
/*     */   public static List getSubjectManagerList(String con, int start_num, int page_size, String site_id)
/*     */   {
/* 199 */     Map m = new HashMap();
/* 200 */     m.put("start_num", Integer.valueOf(start_num));
/* 201 */     m.put("page_size", Integer.valueOf(page_size));
/* 202 */     m.put("con", con);
/* 203 */     m.put("site_id", site_id);
/* 204 */     List l = SubjectDAO.getSubjectManagerList(m);
/* 205 */     if ((l != null) && (l.size() > 0))
/*     */     {
/* 207 */       for (int i = 0; i < l.size(); i++)
/*     */       {
/* 209 */         String apply_dept = ((SubjectBean)l.get(i)).getApply_dept();
/* 210 */         if ((apply_dept != null) && (!"".equals(apply_dept)))
/*     */         {
/* 212 */           DeptBean db = DeptManager.getDeptBeanByID(apply_dept);
/* 213 */           if (db != null)
/* 214 */             ((SubjectBean)l.get(i)).setApply_dept(db.getDept_fullname());
/*     */         }
/* 216 */         String apply_user = ((SubjectBean)l.get(i)).getApply_user();
/* 217 */         if ((apply_user != null) && (!"".equals(apply_user)))
/*     */         {
/* 219 */           UserBean ub = UserManager.getUserBeanByID(apply_user);
/* 220 */           if (ub != null)
/* 221 */             ((SubjectBean)l.get(i)).setApply_user(ub.getUser_realname());
/*     */         }
/*     */       }
/*     */     }
/* 225 */     return l;
/*     */   }
/*     */ 
/*     */   public static SubjectBean getHistoryRecord(String sub_id)
/*     */   {
/* 235 */     return SubjectDAO.getHistoryRecord(sub_id);
/*     */   }
/*     */ 
/*     */   public static boolean updateHistoryRecord(SubjectBean sb, SettingLogsBean stl)
/*     */   {
/* 245 */     sb.setUpdate_time(DateUtil.getCurrentDateTime());
/* 246 */     if (SubjectDAO.updateHistoryRecord(sb, stl))
/*     */     {
/* 248 */       clearMap();
/* 249 */       return true;
/*     */     }
/* 251 */     return false;
/*     */   }
/*     */ 
/*     */   public static SubjectBean getSubjectObj(int id)
/*     */   {
/* 261 */     return SubjectDAO.getSubjectObj(id);
/*     */   }
/*     */ 
/*     */   public static SubjectBean getSubjectObjBySubID(String sub_id)
/*     */   {
/* 271 */     if (sub_map.containsKey(sub_id)) {
/* 272 */       return (SubjectBean)sub_map.get(sub_id);
/*     */     }
/*     */ 
/* 275 */     SubjectBean sb = SubjectDAO.getSubjectObjBySubID(sub_id);
/* 276 */     if (sb != null)
/*     */     {
/* 278 */       sub_map.put(sub_id, sb);
/* 279 */       return sb;
/*     */     }
/* 281 */     return null;
/*     */   }
/*     */ 
/*     */   public static boolean insertSubject(SubjectBean sub, SettingLogsBean stl)
/*     */   {
/* 292 */     String current_time = DateUtil.getCurrentDateTime();
/* 293 */     String sub_id = UUID.randomUUID().toString();
/* 294 */     sub.setSub_id(sub_id);
/* 295 */     sub.setApply_time(current_time);
/*     */ 
/* 297 */     if (sub.getPublish_status() == 1) {
/* 298 */       sub.setPublish_time(current_time);
/*     */     }
/* 300 */     if (sub.getAudit_status() == 1)
/*     */     {
/* 302 */       sub.setAudit_user(sub.getApply_user());
/* 303 */       sub.setAudit_time(current_time);
/*     */     }
/* 305 */     sub_map.clear();
/* 306 */     if (SubjectDAO.insertSubject(sub, stl))
/*     */     {
/* 308 */       insertResouseBySubject(sub, current_time, stl);
/* 309 */       return true;
/*     */     }
/* 311 */     return false;
/*     */   }
/*     */ 
/*     */   public static void insertResouseBySubject(SubjectBean sub, String current_time, SettingLogsBean stl)
/*     */   {
/* 317 */     if ((sub.getS_for_video() != null) && (!"".equals(sub.getS_for_video().trim())))
/*     */     {
/* 319 */       SubjectResouse sr = new SubjectResouse();
/* 320 */       sr.setSub_id(sub.getSub_id());
/* 321 */       sr.setAffix_type("video");
/* 322 */       sr.setAffix_path(sub.getS_for_video());
/* 323 */       sr.setAffix_name("");
/* 324 */       sr.setDescription("");
/* 325 */       sr.setAffix_status("forecast");
/* 326 */       sr.setAdd_time(current_time);
/* 327 */       sr.setAdd_user(sub.getApply_user());
/* 328 */       SubjectDAO.insertResouse(sr, stl);
/*     */     }
/* 330 */     if ((sub.getS_for_pic() != null) && (!"".equals(sub.getS_for_pic().trim())))
/*     */     {
/* 332 */       SubjectResouse sr = new SubjectResouse();
/* 333 */       sr.setSub_id(sub.getSub_id());
/* 334 */       sr.setAffix_type("pic");
/* 335 */       sr.setAffix_path(sub.getS_for_pic());
/* 336 */       sr.setAffix_name("");
/* 337 */       sr.setDescription("");
/* 338 */       sr.setAffix_status("forecast");
/* 339 */       sr.setAdd_time(current_time);
/* 340 */       sr.setAdd_user(sub.getApply_user());
/* 341 */       SubjectDAO.insertResouse(sr, stl);
/*     */     }
/* 343 */     if ((sub.getS_live_video() != null) && (!"".equals(sub.getS_live_video().trim())))
/*     */     {
/* 345 */       SubjectResouse sr = new SubjectResouse();
/* 346 */       sr.setSub_id(sub.getSub_id());
/* 347 */       sr.setAffix_type("video");
/* 348 */       sr.setAffix_path(sub.getS_live_video());
/* 349 */       sr.setAffix_name("");
/* 350 */       sr.setDescription("");
/* 351 */       sr.setAffix_status("live");
/* 352 */       sr.setAdd_time(current_time);
/* 353 */       sr.setAdd_user(sub.getApply_user());
/* 354 */       SubjectDAO.insertResouse(sr, stl);
/*     */     }
/* 356 */     if ((sub.getS_history_video() != null) && (!"".equals(sub.getS_history_video().trim())))
/*     */     {
/* 358 */       SubjectResouse sr = new SubjectResouse();
/* 359 */       sr.setSub_id(sub.getSub_id());
/* 360 */       sr.setAffix_type("video");
/* 361 */       sr.setAffix_path(sub.getS_history_video());
/* 362 */       sr.setAffix_name("");
/* 363 */       sr.setDescription("");
/* 364 */       sr.setAffix_status("history");
/* 365 */       sr.setAdd_time(current_time);
/* 366 */       sr.setAdd_user(sub.getApply_user());
/* 367 */       SubjectDAO.insertResouse(sr, stl);
/*     */     }
/*     */   }
/*     */ 
/*     */   public static boolean updateSubject(SubjectBean sub, SettingLogsBean stl)
/*     */   {
/* 378 */     sub_map.clear();
/* 379 */     sub.setUpdate_time(DateUtil.getCurrentDateTime());
/* 380 */     if (SubjectDAO.updateSubject(sub, stl))
/*     */     {
/* 382 */       SubjectDAO.deleteResouseBySub(sub.getSub_id());
/* 383 */       insertResouseBySubject(sub, DateUtil.getCurrentDateTime(), stl);
/* 384 */       return true;
/*     */     }
/* 386 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteSubject(String ids, String user_name, SettingLogsBean stl)
/*     */   {
/* 397 */     sub_map.clear();
/* 398 */     Map m = new HashMap();
/* 399 */     m.put("ids", ids);
/* 400 */     m.put("user_name", user_name);
/* 401 */     m.put("current_time", DateUtil.getCurrentDateTime());
/* 402 */     return SubjectDAO.deleteSubject(m, stl);
/*     */   }
/*     */ 
/*     */   public static boolean updateSubjectStatus(String ids, String filds, String status_flag, String oper_name, String oper_message, String user_name, String user_id, SettingLogsBean stl)
/*     */   {
/* 418 */     sub_map.clear();
/* 419 */     Map m = new HashMap();
/* 420 */     m.put("ids", ids);
/* 421 */     m.put("filds", filds);
/* 422 */     m.put("status_flag", status_flag);
/* 423 */     m.put("oper_name", oper_name);
/* 424 */     m.put("oper_message", oper_message);
/* 425 */     m.put("user_name", user_name);
/* 426 */     m.put("user_id", user_id);
/* 427 */     m.put("current_time", DateUtil.getCurrentDateTime());
/* 428 */     if (SubjectDAO.updateSubjectStatus(m, stl))
/*     */     {
/* 431 */       if (("status".equals(filds)) && ("1".equals(status_flag)))
/*     */       {
/* 433 */         ChatRoomServices.reloadChat(ids);
/*     */       }
/* 435 */       return true;
/*     */     }
/*     */ 
/* 438 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean subjectSubmit(String ids, int status_flag, String user_id, SettingLogsBean stl)
/*     */   {
/* 451 */     sub_map.clear();
/* 452 */     Map m = new HashMap();
/* 453 */     m.put("ids", ids);
/* 454 */     m.put("status", status_flag);
/* 455 */     m.put("user_name", user_id);
/* 456 */     m.put("current_time", DateUtil.getCurrentDateTime());
/* 457 */     return SubjectDAO.subjectSubmit(m, stl);
/*     */   }
/*     */ 
/*     */   public static List getResouseList(String sub_id, String affix_status, String affix_type)
/*     */   {
/* 469 */     Map m = new HashMap();
/* 470 */     m.put("sub_id", sub_id);
/* 471 */     m.put("affix_status", affix_status);
/* 472 */     m.put("affix_type", affix_type);
/* 473 */     return SubjectDAO.getResouseList(m);
/*     */   }
/*     */ 
/*     */   public static List getResouseListByManager(String sub_id)
/*     */   {
/* 484 */     return SubjectDAO.getResouseListByManager(sub_id);
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.interview.SubjectServices
 * JD-Core Version:    0.6.2
 */