/*    */ package com.cicro.wcm.bean.appeal.calendar;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class CalendarBean
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = -1851619970884172534L;
/*    */   private int ca_id;
/*    */   private String ca_name;
/*    */   private String start_dtime;
/*    */   private String end_dtime;
/*    */   private int ca_flag;
/*    */   private int ca_type;
/*    */ 
/*    */   public int getCa_id()
/*    */   {
/* 31 */     return this.ca_id;
/*    */   }
/*    */ 
/*    */   public void setCa_id(int caId) {
/* 35 */     this.ca_id = caId;
/*    */   }
/*    */ 
/*    */   public String getCa_name() {
/* 39 */     return this.ca_name;
/*    */   }
/*    */ 
/*    */   public void setCa_name(String caName) {
/* 43 */     this.ca_name = caName;
/*    */   }
/*    */ 
/*    */   public String getStart_dtime() {
/* 47 */     return this.start_dtime;
/*    */   }
/*    */ 
/*    */   public void setStart_dtime(String startDtime) {
/* 51 */     this.start_dtime = startDtime;
/*    */   }
/*    */ 
/*    */   public String getEnd_dtime() {
/* 55 */     return this.end_dtime;
/*    */   }
/*    */ 
/*    */   public void setEnd_dtime(String endDtime) {
/* 59 */     this.end_dtime = endDtime;
/*    */   }
/*    */ 
/*    */   public int getCa_flag() {
/* 63 */     return this.ca_flag;
/*    */   }
/*    */ 
/*    */   public void setCa_flag(int caFlag) {
/* 67 */     this.ca_flag = caFlag;
/*    */   }
/*    */ 
/*    */   public int getCa_type() {
/* 71 */     return this.ca_type;
/*    */   }
/*    */ 
/*    */   public void setCa_type(int caType) {
/* 75 */     this.ca_type = caType;
/*    */   }
/*    */ 
/*    */   public static long getSerialversionuid() {
/* 79 */     return -1851619970884172534L;
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.bean.appeal.calendar.CalendarBean
 * JD-Core Version:    0.6.2
 */