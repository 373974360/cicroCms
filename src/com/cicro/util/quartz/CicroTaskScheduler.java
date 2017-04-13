/*     */ package com.cicro.util.quartz;
/*     */ 
/*     */ import java.text.ParseException;
/*     */ import java.util.Arrays;
/*     */ import java.util.Date;
/*     */ import org.quartz.CronExpression;
/*     */ import org.quartz.CronTrigger;
/*     */ import org.quartz.Job;
/*     */ import org.quartz.JobDetail;
/*     */ import org.quartz.Scheduler;
/*     */ import org.quartz.SchedulerException;
/*     */ import org.quartz.SchedulerFactory;
/*     */ import org.quartz.SimpleTrigger;
/*     */ import org.quartz.Trigger;
/*     */ import org.quartz.impl.StdSchedulerFactory;
/*     */ 
/*     */ public class CicroTaskScheduler
/*     */ {
/*  12 */   private static Scheduler scheduler = null;
/*     */ 
/*     */   static {
/*  15 */     initCicroTaskScheduler();
/*     */   }
/*     */ 
/*     */   public static void initCicroTaskScheduler() {
/*  19 */     SchedulerFactory schedulerFactory = new StdSchedulerFactory();
/*     */     try {
/*  21 */       scheduler = schedulerFactory.getScheduler();
/*     */ 
/*  23 */       scheduler.start();
/*     */     }
/*     */     catch (SchedulerException e) {
/*  26 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public static Scheduler getSch() throws SchedulerException {
/*  31 */     return scheduler;
/*     */   }
/*     */ 
/*     */   public static void addCornTask(String jobname, String groupname, Job jobclass, String cronExp)
/*     */     throws SchedulerException
/*     */   {
/*  46 */     JobDetail jobDetail = 
/*  47 */       new JobDetail(jobname, groupname, jobclass.getClass());
/*  48 */     CronTrigger cronTrigger = new CronTrigger(jobname, groupname);
/*     */     try
/*     */     {
/*  51 */       if ((cronExp == null) || (cronExp.trim().equals("")) || (cronExp.trim() == "")) {
/*  52 */         cronExp = "0 0 0 0/1 * ?";
/*     */       }
/*     */ 
/*  55 */       CronExpression cexp = new CronExpression(cronExp);
/*     */ 
/*  57 */       cronTrigger.setCronExpression(cexp);
/*     */     } catch (Exception e) {
/*  59 */       e.printStackTrace();
/*     */     }
/*     */ 
/*  63 */     scheduler.scheduleJob(jobDetail, cronTrigger);
/*     */   }
/*     */ 
/*     */   public static void addSimpleTask(String jobName, String jobGroup, Job job, Date startTime, int repeatInterval, int repeatCount)
/*     */     throws SchedulerException
/*     */   {
/*  79 */     long ctime = System.currentTimeMillis();
/*  80 */     JobDetail jobDetail = 
/*  81 */       new JobDetail(jobName, jobGroup, job.getClass());
/*     */ 
/*  83 */     SimpleTrigger simpleTrigger = 
/*  84 */       new SimpleTrigger(jobName, jobGroup);
/*  85 */     if (startTime == null) {
/*  86 */       startTime = new Date(ctime);
/*     */     }
/*  88 */     simpleTrigger.setStartTime(startTime);
/*  89 */     simpleTrigger.setRepeatInterval(repeatInterval);
/*  90 */     simpleTrigger.setRepeatCount(repeatCount);
/*     */ 
/*  92 */     if (!Arrays.asList(scheduler.getJobNames(jobName)).contains(jobName))
/*  93 */       scheduler.scheduleJob(jobDetail, simpleTrigger);
/*     */     else;
/*     */   }
/*     */ 
/*     */   public static void startTask()
/*     */     throws SchedulerException
/*     */   {
/* 104 */     scheduler.start();
/*     */   }
/*     */ 
/*     */   public static void endTask()
/*     */     throws SchedulerException
/*     */   {
/* 112 */     scheduler.shutdown();
/*     */   }
/*     */ 
/*     */   public static boolean removeJob(String jobName, String groupName)
/*     */   {
/*     */     try
/*     */     {
/* 123 */       scheduler.deleteJob(jobName, groupName);
/*     */ 
/* 125 */       return true;
/*     */     }
/*     */     catch (SchedulerException e) {
/* 128 */       e.printStackTrace();
/* 129 */     }return false;
/*     */   }
/*     */ 
/*     */   public static void modifyJobTime(String triggerName, String triggerGroupName, String time)
/*     */     throws SchedulerException, ParseException
/*     */   {
/* 145 */     Trigger trigger = scheduler.getTrigger(triggerName, triggerGroupName);
/* 146 */     if (trigger != null) {
/* 147 */       CronTrigger ct = (CronTrigger)trigger;
/*     */ 
/* 149 */       ct.setCronExpression(time);
/*     */ 
/* 151 */       scheduler.resumeTrigger(triggerName, triggerGroupName);
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.util.quartz.CicroTaskScheduler
 * JD-Core Version:    0.6.2
 */