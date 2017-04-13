/*    */ package com.cicro.wcm.timer;
/*    */ 
/*    */ import com.cicro.util.jconfig.JconfigFactory;
/*    */ import com.cicro.util.jconfig.JconfigUtil;
/*    */ import java.util.HashSet;
/*    */ import java.util.Iterator;
/*    */ import java.util.Set;
/*    */ import org.quartz.Job;
/*    */ import org.quartz.JobExecutionContext;
/*    */ import org.quartz.JobExecutionException;
/*    */ 
/*    */ public class TimerTaskJob
/*    */   implements Job
/*    */ {
/* 15 */   private static Set timerListeners = new HashSet();
/* 16 */   private static JconfigUtil bc = JconfigFactory.getJconfigUtilInstance("startListener");
/*    */ 
/*    */   static
/*    */   {
/*    */     try
/*    */     {
/* 23 */       String[] class_arr = bc.getPropertyNamesByCategory("timerlistenerclass");
/* 24 */       if ((class_arr != null) && (class_arr.length > 0))
/*    */       {
/* 27 */         for (int i = class_arr.length; i > 0; i--)
/*    */         {
/* 30 */           Class.forName(bc.getProperty(class_arr[(i - 1)], "", "timerlistenerclass"));
/*    */         }
/*    */       }
/*    */     }
/*    */     catch (Exception e)
/*    */     {
/* 36 */       e.printStackTrace();
/*    */     }
/*    */   }
/*    */ 
/*    */   public void execute(JobExecutionContext context) throws JobExecutionException
/*    */   {
/*    */     try
/*    */     {
/* 44 */       for (Iterator localIterator = timerListeners.iterator(); localIterator.hasNext(); ) { Object o = localIterator.next();
/*    */ 
/* 46 */         TimerListener timerListener = (TimerListener)o;
/* 47 */         timerListener.timingTask(); }
/*    */     }
/*    */     catch (Exception e)
/*    */     {
/* 51 */       e.printStackTrace();
/*    */     }
/*    */   }
/*    */ 
/*    */   public static void registerPublishListener(TimerListener timerListener)
/*    */   {
/* 57 */     if (timerListener == null) {
/* 58 */       return;
/*    */     }
/* 60 */     timerListeners.add(timerListener);
/*    */   }
/*    */ 
/*    */   public static void removePublishListener(TimerListener timerListener) {
/* 64 */     if (timerListener == null) {
/* 65 */       return;
/*    */     }
/* 67 */     timerListeners.remove(timerListener);
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.timer.TimerTaskJob
 * JD-Core Version:    0.6.2
 */