/*    */ package com.cicro.wcm.bean.interview;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class GuestBean
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 3026305367824566220L;
/* 17 */   private String user_num = "";
/* 18 */   private String user_name = "";
/* 19 */   private String nick_name = "";
/* 20 */   private String ip = "";
/*    */ 
/* 22 */   public String getIp() { return this.ip; }
/*    */ 
/*    */   public void setIp(String ip) {
/* 25 */     this.ip = ip;
/*    */   }
/*    */   public String getNick_name() {
/* 28 */     return this.nick_name;
/*    */   }
/*    */   public void setNick_name(String nick_name) {
/* 31 */     this.nick_name = nick_name;
/*    */   }
/*    */   public String getUser_num() {
/* 34 */     return this.user_num;
/*    */   }
/*    */   public void setUser_num(String user_num) {
/* 37 */     this.user_num = user_num;
/*    */   }
/*    */   public String getUser_name() {
/* 40 */     return this.user_name;
/*    */   }
/*    */   public void setUser_name(String user_name) {
/* 43 */     this.user_name = user_name;
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.bean.interview.GuestBean
 * JD-Core Version:    0.6.2
 */