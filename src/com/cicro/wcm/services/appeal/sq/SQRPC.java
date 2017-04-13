/*     */ package com.cicro.wcm.services.appeal.sq;
/*     */ 
/*     */ import com.cicro.util.DateUtil;
/*     */ import com.cicro.wcm.bean.appeal.sq.ProcessBean;
/*     */ import com.cicro.wcm.bean.appeal.sq.SQAttachment;
/*     */ import com.cicro.wcm.bean.appeal.sq.SQBean;
/*     */ import com.cicro.wcm.bean.appeal.sq.SQCustom;
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.bean.org.user.LoginUserBean;
/*     */ import com.cicro.wcm.bean.system.assist.TagsBean;
/*     */ import com.cicro.wcm.dao.appeal.sq.SQDAO;
/*     */ import com.cicro.wcm.db.DBManager;
/*     */ import com.cicro.wcm.services.Log.LogManager;
/*     */ import com.cicro.wcm.services.org.user.UserLogin;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ 
/*     */ public class SQRPC
/*     */ {
/*     */   public static SQBean getSQSimpleBean(int sq_id)
/*     */   {
/*  24 */     return SQManager.getSQSimpleBean(sq_id);
/*     */   }
/*     */ 
/*     */   public static long getAppealFileSize()
/*     */   {
/*  34 */     return SQManager.getAppealFileSize();
/*     */   }
/*     */ 
/*     */   public static List<SQBean> getBroSQListByMemberID(String me_id)
/*     */   {
/*  44 */     return SQManager.getBroSQListByMemberID(me_id);
/*     */   }
/*     */ 
/*     */   public static boolean isAppealManager(int user_id)
/*     */   {
/*  54 */     return SQManager.isAppealManager(user_id);
/*     */   }
/*     */ 
/*     */   public static String getSqCount(Map<String, String> m, HttpServletRequest request)
/*     */   {
/*  64 */     return SQManager.getSqCount(m, UserLogin.getUserBySession(request).getUser_id());
/*     */   }
/*     */ 
/*     */   public static String getTransactSQCount(Map<String, String> m, HttpServletRequest request)
/*     */   {
/*  74 */     return SQManager.getTransactSQCount(m, UserLogin.getUserBySession(request).getUser_id());
/*     */   }
/*     */ 
/*     */   public static List<SQBean> getSqList(Map<String, String> m, HttpServletRequest request)
/*     */   {
/*  84 */     return SQManager.getSqList(m, UserLogin.getUserBySession(request).getUser_id());
/*     */   }
/*     */ 
/*     */   public static List<SQBean> getTransactSQList(Map<String, String> m, HttpServletRequest request)
/*     */   {
/*  94 */     return SQManager.getTransactSQList(m, UserLogin.getUserBySession(request).getUser_id());
/*     */   }
/*     */ 
/*     */   public static SQBean getSqBean(int sq_id)
/*     */   {
/* 104 */     return SQManager.getSqBean(sq_id);
/*     */   }
/*     */ 
/*     */   public static List<SQCustom> getSQCustomList(int sq_id)
/*     */   {
/* 114 */     return SQManager.getSQCustomList(sq_id);
/*     */   }
/*     */ 
/*     */   public static String getQueryCode(int model)
/*     */   {
/* 124 */     return SQManager.getQueryCode(model);
/*     */   }
/*     */ 
/*     */   public static String withdrawSQForProcess(String sq_id, int user_id)
/*     */   {
/* 134 */     return SQManager.withdrawSQForProcess(sq_id, user_id);
/*     */   }
/*     */ 
/*     */   public static boolean setSQClickCount(int sq_id)
/*     */   {
/* 144 */     return SQManager.setSQClickCount(sq_id);
/*     */   }
/*     */ 
/*     */   public static boolean updateSQ(SQBean sb, String tag_ids, HttpServletRequest request)
/*     */   {
/* 156 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 157 */     if (stl != null)
/*     */     {
/* 159 */       return SQManager.updateSQ(sb, tag_ids, stl);
/*     */     }
/* 161 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateSQCustom(List<SQCustom> l)
/*     */   {
/* 171 */     return SQManager.updateSQCustom(l);
/*     */   }
/*     */ 
/*     */   public static boolean updateStatus(Map<String, String> m, HttpServletRequest request)
/*     */   {
/* 181 */     if ("".equals(m.get("do_dept")))
/*     */     {
/* 183 */       LoginUserBean lub = UserLogin.getUserBySession(request);
/* 184 */       m.put("do_dept", "");
/*     */     }
/* 186 */     return SQManager.updateStatus(m);
/*     */   }
/*     */ 
/*     */   public static boolean deleteSQ(String sq_ids, HttpServletRequest request)
/*     */   {
/* 197 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 198 */     if (stl != null)
/*     */     {
/* 200 */       return SQManager.deleteSQ(sq_ids, stl);
/*     */     }
/* 202 */     return false;
/*     */   }
/*     */ 
/*     */   public static List<SQBean> getReduplicateSQList(Map<String, String> m)
/*     */   {
/* 212 */     return SQManager.getReduplicateSQList(m);
/*     */   }
/*     */ 
/*     */   public static boolean updateProcessNote(Map<String, String> m, HttpServletRequest request)
/*     */   {
/* 234 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 235 */     if (stl != null)
/*     */     {
/* 237 */       return SQManager.updateProcessNote(m, stl);
/*     */     }
/* 239 */     return false;
/*     */   }
/*     */ 
/*     */   public static List<ProcessBean> getProcessListBySqID(int sq_id)
/*     */   {
/* 244 */     return SQManager.getProcessListBySqID(sq_id);
/*     */   }
/*     */ 
/*     */   public static boolean insertProcess(ProcessBean pb, SQAttachment sqa, HttpServletRequest request)
/*     */   {
/* 255 */     LoginUserBean lub = UserLogin.getUserBySession(request);
/* 256 */     pb.setUser_id(lub.getUser_id());
/* 257 */     pb.setUser_realname(lub.getUser_realname());
/* 258 */     return SQManager.insertProcess(pb, sqa);
/*     */   }
/*     */ 
/*     */   public static List<TagsBean> getSQTagList(int sq_id)
/*     */   {
/* 269 */     return SQManager.getSQTagList(sq_id);
/*     */   }
/*     */ 
/*     */   public static List<SQAttachment> getSQAttachmentList(String sq_ids, String relevance_type)
/*     */   {
/* 280 */     return SQManager.getSQAttachmentList(sq_ids, relevance_type);
/*     */   }
/*     */ 
/*     */   public static synchronized SQBean insertSQ(SQBean sb, SQAttachment sqa)
/*     */   {
/* 285 */     return SQManager.insertSQ(sb, sqa);
/*     */   }
/*     */ 
/*     */   public static boolean publishLettersByIds(Map<String, String> m, HttpServletRequest request)
/*     */   {
/* 295 */     m.put("publish_dtime", DateUtil.getCurrentDateTime());
/* 296 */     return DBManager.update("publishLettersByIds", m);
/*     */   }
/*     */ 
/*     */   public static boolean deleteProcessByProID(String pro_id)
/*     */   {
/* 306 */     return SQDAO.deleteProcessByProID(Integer.parseInt(pro_id));
/*     */   }
/*     */ 
/*     */   public static List<SQBean> getexportData(Map<String, String> m)
/*     */   {
/* 316 */     return SQManagerExtend.getexportData(m);
/*     */   }
/*     */ 
/*     */   public static String CateAccessCountsOutExcel(List listBean, List headerList)
/*     */   {
/* 327 */     return SQManagerExtend.CateAccessCountsOutExcel(listBean, headerList);
/*     */   }
/*     */ 
/*     */   public static boolean batchIsNotOpenOk(Map<String, String> m)
/*     */   {
/* 338 */     return SQManagerExtend.batchIsNotOpenOk(m);
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.appeal.sq.SQRPC
 * JD-Core Version:    0.6.2
 */