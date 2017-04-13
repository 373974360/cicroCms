/*     */ package com.cicro.wcm.services.survey;
/*     */ 
/*     */ import com.cicro.util.DateUtil;
/*     */ import com.cicro.util.FormatUtil;
/*     */ import com.cicro.wcm.bean.survey.SurveyAnswer;
/*     */ import com.cicro.wcm.bean.survey.SurveyBean;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpSession;
/*     */ 
/*     */ public class AnswerRPC
/*     */ {
/*     */   public static String getCurrentDate()
/*     */   {
/*  16 */     return DateUtil.getCurrentDate();
/*     */   }
/*     */ 
/*     */   public static boolean isSubmitTimeout(String s_id, String s_time, HttpServletRequest request)
/*     */   {
/*     */     try
/*     */     {
/*  29 */       return AnswerServer.isSubmitTimeout(s_id, s_time, request.getRemoteAddr());
/*     */     } catch (Exception e) {
/*  31 */       e.printStackTrace();
/*  32 */     }return true;
/*     */   }
/*     */ 
/*     */   public static String getAnswerCountByIP(String s_id, HttpServletRequest request)
/*     */   {
/*  44 */     return AnswerServer.getAnswerCountByIP(s_id, FormatUtil.getIpAddr(request));
/*     */   }
/*     */ 
/*     */   public static String getSurveyCount_browser(String con)
/*     */   {
/*  54 */     return AnswerServer.getSurveyCount_browser(con);
/*     */   }
/*     */ 
/*     */   public static List getSurveyList_browser(String con, int start_num, int page_size)
/*     */   {
/*  64 */     return AnswerServer.getSurveyList_browser(con, start_num, page_size);
/*     */   }
/*     */ 
/*     */   public static String insertSurveyAnswer(SurveyAnswer sa, String auth_code, HttpServletRequest request)
/*     */   {
/*  74 */     SurveyBean sb = SurveyRPC.getSurveyBean(sa.getS_id());
/*  75 */     if (sb.getIs_checkcode() == 1)
/*     */     {
/*  77 */       String codeSession = (String)request.getSession().getAttribute("valiCode");
/*  78 */       if (!codeSession.equals(auth_code))
/*     */       {
/*  80 */         return "coderror";
/*     */       }
/*     */     }
/*     */ 
/*  84 */     if (sb.getIp_fre() != 0)
/*     */     {
/*  86 */       if (Integer.parseInt(getAnswerCountByIP(sa.getS_id(), request)) > sb.getIp_fre())
/*     */       {
/*  88 */         return "ipfull";
/*     */       }
/*     */     }
/*  91 */     if ((sb.getSpacing_interval() != null) && (!"".equals(sb.getSpacing_interval())))
/*     */     {
/*  93 */       if (!isSubmitTimeout(sa.getS_id(), sb.getSpacing_interval(), request))
/*     */       {
/*  95 */         return "timeout";
/*     */       }
/*     */     }
/*  98 */     sa.setIp(FormatUtil.getIpAddr(request));
/*  99 */     boolean b = AnswerServer.insertSurveyAnswer(sa);
/* 100 */     if (b)
/*     */     {
/* 102 */       request.getSession().setAttribute("valiCode", "");
/* 103 */       return "true";
/*     */     }
/* 105 */     return "false";
/*     */   }
/*     */ 
/*     */   public static boolean insertSurveyAnswerBefore(SurveyAnswer sa, HttpServletRequest request)
/*     */   {
/* 115 */     sa.setIp(request.getRemoteAddr());
/* 116 */     return AnswerServer.insertSurveyAnswer(sa);
/*     */   }
/*     */ 
/*     */   public static Map getVoteTotalBySurveyItem(String s_id, String item_id)
/*     */   {
/* 127 */     return StatisticsService.getVoteTotalBySurveyItem(s_id, item_id);
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.survey.AnswerRPC
 * JD-Core Version:    0.6.2
 */