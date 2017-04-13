/*    */ package com.cicro.wcm.bean.zwgk.ysqgk;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class YsqgkListBean
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = -7344433054755245726L;
/* 15 */   private int ysq_id = 0;
/* 16 */   private String ysq_code = "";
/* 17 */   private int ysq_type = 0;
/* 18 */   private String name = "";
/* 19 */   private String put_dtime = "";
/* 20 */   private int publish_state = 0;
/* 21 */   private int is_third = 0;
/* 22 */   private int is_extend = 0;
/* 23 */   private int timeout_flag = 0;
/* 24 */   private String node_name = "";
/* 25 */   private int do_state = 0;
/*    */ 
/*    */   public int getYsq_id()
/*    */   {
/* 29 */     return this.ysq_id;
/*    */   }
/*    */   public String getYsq_code() {
/* 32 */     return this.ysq_code;
/*    */   }
/*    */   public int getYsq_type() {
/* 35 */     return this.ysq_type;
/*    */   }
/*    */   public String getName() {
/* 38 */     return this.name;
/*    */   }
/*    */   public String getPut_dtime() {
/* 41 */     return this.put_dtime;
/*    */   }
/*    */   public int getPublish_state() {
/* 44 */     return this.publish_state;
/*    */   }
/*    */   public int getIs_third() {
/* 47 */     return this.is_third;
/*    */   }
/*    */   public int getIs_extend() {
/* 50 */     return this.is_extend;
/*    */   }
/*    */   public int getTimeout_flag() {
/* 53 */     return this.timeout_flag;
/*    */   }
/*    */   public void setYsq_id(int ysqId) {
/* 56 */     this.ysq_id = ysqId;
/*    */   }
/*    */   public void setYsq_code(String ysqCode) {
/* 59 */     this.ysq_code = ysqCode;
/*    */   }
/*    */   public void setYsq_type(int ysqType) {
/* 62 */     this.ysq_type = ysqType;
/*    */   }
/*    */   public void setName(String name) {
/* 65 */     this.name = name;
/*    */   }
/*    */   public void setPut_dtime(String putDtime) {
/* 68 */     this.put_dtime = putDtime;
/*    */   }
/*    */   public void setPublish_state(int publishState) {
/* 71 */     this.publish_state = publishState;
/*    */   }
/*    */   public void setIs_third(int isThird) {
/* 74 */     this.is_third = isThird;
/*    */   }
/*    */   public void setIs_extend(int isExtend) {
/* 77 */     this.is_extend = isExtend;
/*    */   }
/*    */   public void setTimeout_flag(int timeoutFlag) {
/* 80 */     this.timeout_flag = timeoutFlag;
/*    */   }
/*    */   public String getNode_name() {
/* 83 */     return this.node_name;
/*    */   }
/*    */   public void setNode_name(String nodeName) {
/* 86 */     this.node_name = nodeName;
/*    */   }
/*    */   public int getDo_state() {
/* 89 */     return this.do_state;
/*    */   }
/*    */   public void setDo_state(int doState) {
/* 92 */     this.do_state = doState;
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.bean.zwgk.ysqgk.YsqgkListBean
 * JD-Core Version:    0.6.2
 */