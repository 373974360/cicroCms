/*    */ package com.cicro.wcm.services.appeal.sq;
/*    */ 
/*    */ import com.cicro.util.DateUtil;
/*    */ import com.cicro.wcm.bean.appeal.model.ModelBean;
/*    */ import com.cicro.wcm.bean.appeal.sq.SQBean;
/*    */ import com.cicro.wcm.dao.appeal.sq.SQDAO;
/*    */ import com.cicro.wcm.services.appeal.calendar.CalendarManager;
/*    */ import com.cicro.wcm.services.appeal.model.ModelManager;
/*    */ import com.cicro.wcm.timer.TimerListener;
/*    */ import com.cicro.wcm.timer.TimerTaskJobForDay;
/*    */ import java.util.HashMap;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class SQTaskHandl
/*    */   implements TimerListener
/*    */ {
/*    */   static
/*    */   {
/* 17 */     TimerTaskJobForDay.registerPublishListener(new SQTaskHandl());
/*    */   }
/*    */ 
/*    */   public void timingTask()
/*    */   {
/* 22 */     List l = SQDAO.getNotEndSQList();
/* 23 */     if ((l != null) && (l.size() > 0))
/*    */     {
/* 25 */       Map m = new HashMap();
/* 26 */       String now_date = DateUtil.getCurrentDate();
/* 27 */       for (int i = 0; i < l.size(); i++)
/*    */         try
/*    */         {
/* 30 */           SQBean sb = (SQBean)l.get(i);
/* 31 */           int sq_id = sb.getSq_id();
/* 32 */           int model_id = sb.getModel_id();
/* 33 */           String sq_dtime = sb.getSq_dtime();
/* 34 */           int time_limit = sb.getTime_limit();
/* 35 */           int warn_num = -5;
/* 36 */           int yellow_num = -2;
/* 37 */           int red_num = 2;
/* 38 */           ModelBean mb = ModelManager.getModelBean(model_id);
/*    */ 
/* 40 */           if (mb != null)
/*    */           {
/* 45 */             warn_num = mb.getWarn_num();
/* 46 */             yellow_num = mb.getYellow_num();
/* 47 */             red_num = mb.getRed_num();
/*    */ 
/* 49 */             if ((sq_dtime != null) && (!"".equals(sq_dtime)))
/*    */             {
/* 51 */               sq_dtime = sq_dtime.substring(0, 10);
/*    */ 
/* 57 */               m.clear();
/*    */ 
/* 59 */               int work_date = CalendarManager.getWorkDays(sq_dtime, now_date);
/*    */ 
/* 62 */               if (work_date > time_limit)
/*    */               {
/* 64 */                 m.put("timeout_flag", "1");
/*    */               }
/*    */ 
/* 67 */               if (work_date > time_limit + warn_num)
/*    */               {
/* 69 */                 m.put("alarm_flag", "1");
/*    */               }
/*    */ 
/* 72 */               if (work_date > time_limit + yellow_num)
/*    */               {
/* 74 */                 m.put("alarm_flag", "2");
/*    */               }
/*    */ 
/* 77 */               if (work_date > time_limit + red_num)
/*    */               {
/* 79 */                 m.put("alarm_flag", "3");
/*    */               }
/*    */ 
/* 83 */               if (m.size() > 0)
/*    */               {
/* 85 */                 m.put("sq_id", sq_id);
/* 86 */                 SQDAO.updateStatus(m);
/*    */               }
/*    */             }
/*    */           }
/*    */         } catch (Exception e) { e.printStackTrace(); }
/*    */ 
/*    */     }
/*    */   }
/*    */ 
/*    */   public static void main(String[] args)
/*    */   {
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.appeal.sq.SQTaskHandl
 * JD-Core Version:    0.6.2
 */