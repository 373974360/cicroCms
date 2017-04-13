/*     */ package com.cicro.wcm.services.appeal.sq;
/*     */ 
/*     */ import com.cicro.util.jconfig.JconfigFactory;
/*     */ import com.cicro.util.jconfig.JconfigUtil;
/*     */ import com.cicro.wcm.bean.appeal.model.ModelBean;
/*     */ import com.cicro.wcm.bean.appeal.sq.SQBean;
/*     */ 
/*     */ public class SQSMSFactory
/*     */ {
/*     */   private static ISQSMS sqSMS;
/*     */ 
/*     */   static
/*     */   {
/*  23 */     JconfigUtil bc = JconfigFactory.getJconfigUtilInstance("project_config");
/*  24 */     String sq_sms_class = bc.getProperty("sq_sms_class", "", "sqsms");
/*     */     try
/*     */     {
/*  27 */       if ((sq_sms_class != null) && (!"".equals(sq_sms_class)))
/*  28 */         sqSMS = (ISQSMS)Class.forName(sq_sms_class).newInstance();
/*     */     }
/*     */     catch (Exception e) {
/*  31 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void sendSMSForAdd(SQBean sb, ModelBean mb)
/*     */   {
/*     */     try
/*     */     {
/*  44 */       if (sqSMS != null) {
/*  45 */         sqSMS.sendSMSToAdminForAdd(sb, mb);
/*     */       }
/*  47 */       if (sqSMS != null)
/*  48 */         sqSMS.sendSMSToClientForAdd(sb, mb);
/*     */     }
/*     */     catch (Exception e) {
/*  51 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void sendSMSForResult(SQBean sb, ModelBean mb)
/*     */   {
/*     */     try
/*     */     {
/*  64 */       if (sqSMS != null) {
/*  65 */         sqSMS.sendSMSToAdminForResult(sb, mb);
/*     */       }
/*  67 */       if (sqSMS != null)
/*  68 */         sqSMS.sendSMSToClientForResult(sb, mb);
/*     */     }
/*     */     catch (Exception e) {
/*  71 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void sendSMSForSupervise(SQBean sb, ModelBean mb)
/*     */   {
/*     */     try
/*     */     {
/*  84 */       if (sqSMS != null)
/*  85 */         sqSMS.sendSMSForSupervise(sb, mb);
/*     */     }
/*     */     catch (Exception e) {
/*  88 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void sendSMSForTrans(SQBean sb, ModelBean mb)
/*     */   {
/*     */     try
/*     */     {
/* 101 */       if (sqSMS != null)
/* 102 */         sqSMS.sendSMSForTrans(sb, mb);
/*     */     }
/*     */     catch (Exception e) {
/* 105 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void sendSMSForPublish(SQBean sb, ModelBean mb)
/*     */   {
/*     */     try
/*     */     {
/* 118 */       if (sqSMS != null)
/* 119 */         sqSMS.sendSMSForPublish(sb, mb);
/*     */     }
/*     */     catch (Exception e) {
/* 122 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.appeal.sq.SQSMSFactory
 * JD-Core Version:    0.6.2
 */