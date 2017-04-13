/*    */ package com.cicro.wcm.services.cms.info;
/*    */ 
/*    */ import com.cicro.util.DateUtil;
/*    */ import com.cicro.wcm.dao.cms.info.InfoDAO;
/*    */ import com.cicro.wcm.services.control.site.SiteVisitCountManager;
/*    */ import com.cicro.wcm.timer.TimerListener;
/*    */ import com.cicro.wcm.timer.TimerTaskJobForDay;
/*    */ import java.text.ParseException;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class InfoTimerForDayImpl
/*    */   implements TimerListener
/*    */ {
/*    */   static
/*    */   {
/* 21 */     TimerTaskJobForDay.registerPublishListener(new InfoTimerForDayImpl());
/*    */   }
/*    */ 
/*    */   public void timingTask()
/*    */   {
/* 27 */     Map m = new HashMap();
/* 28 */     m.put("item_name", "day_hits");
/* 29 */     InfoDAO.clearInfoHits(m);
/* 30 */     SiteVisitCountManager.clearHits("");
/*    */     try
/*    */     {
/* 33 */       if (DateUtil.getDayOfWek(DateUtil.getDate(DateUtil.getCurrentDateTime())) == 2)
/*    */       {
/* 35 */         m.put("item_name", "week_hits");
/* 36 */         InfoDAO.clearInfoHits(m);
/* 37 */         SiteVisitCountManager.clearHits("week");
/*    */       }
/*    */     }
/*    */     catch (ParseException e) {
/* 41 */       e.printStackTrace();
/*    */     }
/*    */ 
/*    */     try
/*    */     {
/* 46 */       if (DateUtil.getDayOfMonth(DateUtil.getDate(DateUtil.getCurrentDateTime())) == 1)
/*    */       {
/* 48 */         m.put("item_name", "month_hits");
/* 49 */         InfoDAO.clearInfoHits(m);
/* 50 */         SiteVisitCountManager.clearHits("month");
/*    */       }
/*    */     }
/*    */     catch (ParseException e) {
/* 54 */       e.printStackTrace();
/*    */     }
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.cms.info.InfoTimerForDayImpl
 * JD-Core Version:    0.6.2
 */