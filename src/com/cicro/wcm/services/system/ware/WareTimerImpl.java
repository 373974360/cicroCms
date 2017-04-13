/*    */ package com.cicro.wcm.services.system.ware;
/*    */ 
/*    */ import com.cicro.util.DateUtil;
/*    */ import com.cicro.wcm.bean.system.ware.WareBean;
/*    */ import com.cicro.wcm.dao.system.ware.WareDAO;
/*    */ import com.cicro.wcm.timer.TimerListener;
/*    */ import com.cicro.wcm.timer.TimerTaskJob;
/*    */ import java.io.IOException;
/*    */ import java.util.List;
/*    */ 
/*    */ public class WareTimerImpl
/*    */   implements TimerListener
/*    */ {
/*    */   static
/*    */   {
/* 14 */     TimerTaskJob.registerPublishListener(new WareTimerImpl());
/*    */   }
/*    */ 
/*    */   public void timingTask()
/*    */   {
/* 19 */     List ware_list = WareDAO.getTimerWareList(DateUtil.getCurrentDateTime());
/* 20 */     if ((ware_list != null) && (ware_list.size() > 0))
/*    */     {
/* 22 */       for (int i = 0; i < ware_list.size(); i++)
/*    */         try
/*    */         {
/* 25 */           WareManager.createWarePage((WareBean)ware_list.get(i));
/*    */         }
/*    */         catch (IOException e) {
/* 28 */           e.printStackTrace();
/*    */         }
/*    */     }
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.system.ware.WareTimerImpl
 * JD-Core Version:    0.6.2
 */