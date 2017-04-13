/*    */ package com.cicro.wcm.services.cms.info;
/*    */ 
/*    */ import com.cicro.util.DateUtil;
/*    */ import com.cicro.wcm.bean.cms.info.InfoBean;
/*    */ import com.cicro.wcm.dao.cms.info.InfoDAO;
/*    */ import com.cicro.wcm.timer.TimerListener;
/*    */ import com.cicro.wcm.timer.TimerTaskJob;
/*    */ import java.util.HashMap;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class InfoTimerImpl
/*    */   implements TimerListener
/*    */ {
/*    */   static
/*    */   {
/* 15 */     TimerTaskJob.registerPublishListener(new InfoTimerImpl());
/*    */   }
/*    */ 
/*    */   public void timingTask()
/*    */   {
/* 20 */     String cancel_ids = "";
/* 21 */     List info_list = InfoDAO.getAtuoPublishInfoList();
/* 22 */     if ((info_list != null) && (info_list.size() > 0))
/*    */     {
/* 24 */       for (InfoBean info : info_list)
/*    */       {
/* 26 */         if (info.getInfo_status() == 8)
/*    */         {
/* 28 */           cancel_ids = cancel_ids + "," + info.getInfo_id();
/* 29 */           InfoPublishManager.cancelAfterEvent(info);
/*    */         }
/*    */         else {
/* 32 */           info.setInfo_status(8);
/* 33 */           Map m = new HashMap();
/* 34 */           m.put("info_id", info.getInfo_id());
/* 35 */           m.put("info_status", "8");
/* 36 */           m.put("auto_type", "is_auto_up");
/* 37 */           m.put("auto_time", "up_dtime");
/* 38 */           if ((info.getReleased_dtime() == null) || ("".equals(info.getReleased_dtime())))
/* 39 */             m.put("released_dtime", DateUtil.getCurrentDateTime());
/*    */           else
/* 41 */             m.put("released_dtime", info.getReleased_dtime());
/* 42 */           InfoDAO.updateAutoPublishVal(m);
/* 43 */           InfoPublishManager.publishAfterEvent(info);
/*    */         }
/*    */       }
/* 46 */       if ((cancel_ids != null) && (!"".equals(cancel_ids)))
/*    */       {
/* 48 */         cancel_ids = cancel_ids.substring(1);
/* 49 */         Map m = new HashMap();
/* 50 */         m.put("info_ids", cancel_ids);
/* 51 */         m.put("info_status", "3");
/* 52 */         m.put("auto_type", "is_auto_down");
/* 53 */         m.put("auto_time", "down_dtime");
/* 54 */         m.put("released_dtime", "");
/* 55 */         InfoDAO.updateAutoPublishVal(m);
/*    */       }
/*    */     }
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.cms.info.InfoTimerImpl
 * JD-Core Version:    0.6.2
 */