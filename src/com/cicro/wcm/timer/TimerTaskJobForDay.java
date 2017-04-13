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
/*    */ public class TimerTaskJobForDay
/*    */   implements Job
/*    */ {
/* 20 */   private static Set timerListeners_day = new HashSet();
/* 21 */   private static JconfigUtil bc = JconfigFactory.getJconfigUtilInstance("startListener");
/*    */ 
/*    */   static
/*    */   {
/*    */     try
/*    */     {
/* 28 */       String[] class_arr = bc.getPropertyNamesByCategory("timerlistener_day_class");
/* 29 */       if ((class_arr != null) && (class_arr.length > 0))
/*    */       {
/* 32 */         for (int i = class_arr.length; i > 0; i--)
/*    */         {
/* 35 */           Class.forName(bc.getProperty(class_arr[(i - 1)], "", "timerlistener_day_class"));
/*    */         }
/*    */       }
/*    */     }
/*    */     catch (Exception e)
/*    */     {
/* 41 */       e.printStackTrace();
/*    */     }
/*    */   }
/*    */ 
/*    */   public void execute(JobExecutionContext context) throws JobExecutionException
/*    */   {
/*    */     try
/*    */     {
/* 49 */       for (Iterator localIterator = timerListeners_day.iterator(); localIterator.hasNext(); ) { Object o = localIterator.next();
/*    */ 
/* 51 */         TimerListener timerListener = (TimerListener)o;
/* 52 */         timerListener.timingTask(); }
/*    */     }
/*    */     catch (Exception e)
/*    */     {
/* 56 */       e.printStackTrace();
/*    */     }
/*    */   }
/*    */ 
/*    */   public static void registerPublishListener(TimerListener timerListener)
/*    */   {
/* 62 */     if (timerListener == null) {
/* 63 */       return;
/*    */     }
/* 65 */     timerListeners_day.add(timerListener);
/*    */   }
/*    */ 
/*    */   public static void removePublishListener(TimerListener timerListener) {
/* 69 */     if (timerListener == null) {
/* 70 */       return;
/*    */     }
/* 72 */     timerListeners_day.remove(timerListener);
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.timer.TimerTaskJobForDay
 * JD-Core Version:    0.6.2
 */