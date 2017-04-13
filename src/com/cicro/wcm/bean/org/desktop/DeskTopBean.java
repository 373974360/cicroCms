/*    */ package com.cicro.wcm.bean.org.desktop;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class DeskTopBean
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*  8 */   private int id = 0;
/*  9 */   private int user_id = 0;
/* 10 */   private String app_type = "";
/* 11 */   private String k_v = "";
/*    */ 
/* 13 */   public int getId() { return this.id; }
/*    */ 
/*    */   public void setId(int id) {
/* 16 */     this.id = id;
/*    */   }
/*    */   public int getUser_id() {
/* 19 */     return this.user_id;
/*    */   }
/*    */   public void setUser_id(int userId) {
/* 22 */     this.user_id = userId;
/*    */   }
/*    */   public String getApp_type() {
/* 25 */     return this.app_type;
/*    */   }
/*    */   public void setApp_type(String appType) {
/* 28 */     this.app_type = appType;
/*    */   }
/*    */   public String getK_v() {
/* 31 */     return this.k_v;
/*    */   }
/*    */   public void setK_v(String kV) {
/* 34 */     this.k_v = kV;
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.bean.org.desktop.DeskTopBean
 * JD-Core Version:    0.6.2
 */