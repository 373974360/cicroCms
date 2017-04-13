/*    */ package com.cicro.wcm.dao.zwgk;
/*    */ 
/*    */ import com.cicro.wcm.db.DBManager;
/*    */ import java.io.PrintStream;
/*    */ import java.text.DateFormat;
/*    */ import java.text.SimpleDateFormat;
/*    */ import java.util.Date;
/*    */ 
/*    */ public class NxCount
/*    */ {
/* 17 */   private static final DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm");
/* 18 */   private static String date_flag = "";
/* 19 */   private static int i_totle = 7;
/* 20 */   private static int i_xm = 7;
/* 21 */   private static int i_xy = 7;
/* 22 */   private static int i_other = 7;
/*    */ 
/*    */   static
/*    */   {
/* 41 */     updateCount();
/*    */   }
/*    */ 
/*    */   public static String getI_totle()
/*    */   {
/* 25 */     return i_totle;
/*    */   }
/*    */ 
/*    */   public static String getI_xm() {
/* 29 */     return i_xm;
/*    */   }
/*    */ 
/*    */   public static String getI_xy() {
/* 33 */     return i_xy;
/*    */   }
/*    */ 
/*    */   public static String getI_other() {
/* 37 */     return i_other;
/*    */   }
/*    */ 
/*    */   private static void setDateFlag(String now)
/*    */   {
/* 45 */     date_flag = now;
/*    */   }
/*    */ 
/*    */   private static void query()
/*    */   {
/* 52 */     i_totle = ((Integer)DBManager.queryFObj("getNxGcjsCnt", "")).intValue();
/* 53 */     i_xm = ((Integer)DBManager.queryFObj("getNxGcjsXmCnt", "")).intValue();
/* 54 */     i_xy = ((Integer)DBManager.queryFObj("getNxGcjsXyCnt", "")).intValue();
/* 55 */     i_other = i_totle - i_xm - i_xy;
/*    */   }
/*    */ 
/*    */   public static void updateCount()
/*    */   {
/* 62 */     String now = df.format(new Date());
/* 63 */     if (now.equals(date_flag)) {
/* 64 */       return;
/*    */     }
/* 66 */     setDateFlag(now);
/* 67 */     query();
/*    */   }
/*    */ 
/*    */   public static void main(String[] arg)
/*    */   {
/* 76 */     System.out.printf("总数" + getI_totle(), new Object[0]);
/* 77 */     System.out.printf("信息" + getI_xm(), new Object[0]);
/* 78 */     System.out.printf("信用" + getI_xy(), new Object[0]);
/* 79 */     System.out.printf("其他" + getI_other(), new Object[0]);
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.dao.zwgk.NxCount
 * JD-Core Version:    0.6.2
 */