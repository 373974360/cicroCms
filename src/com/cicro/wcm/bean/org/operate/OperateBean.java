/*    */ package com.cicro.wcm.bean.org.operate;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class OperateBean
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 2025294211575431893L;
/*    */   private int opt_id;
/*    */   private int parent_id;
/* 10 */   private String tree_position = "";
/* 11 */   private String opt_name = "";
/* 12 */   private String app_id = "";
/* 13 */   private String controller = "";
/* 14 */   private String action = "";
/* 15 */   private String opt_flag = "";
/* 16 */   private String opt_memo = "";
/*    */ 
/* 18 */   public String getAction() { return this.action; }
/*    */ 
/*    */   public void setAction(String action) {
/* 21 */     this.action = action;
/*    */   }
/*    */   public String getApp_id() {
/* 24 */     return this.app_id;
/*    */   }
/*    */   public void setApp_id(String app_id) {
/* 27 */     this.app_id = app_id;
/*    */   }
/*    */ 
/*    */   public String getOpt_memo() {
/* 31 */     return this.opt_memo;
/*    */   }
/*    */   public void setOpt_memo(String optMemo) {
/* 34 */     this.opt_memo = optMemo;
/*    */   }
/*    */   public String getController() {
/* 37 */     return this.controller;
/*    */   }
/*    */   public void setController(String controller) {
/* 40 */     this.controller = controller;
/*    */   }
/*    */   public String getOpt_flag() {
/* 43 */     return this.opt_flag;
/*    */   }
/*    */   public void setOpt_flag(String opt_flag) {
/* 46 */     this.opt_flag = opt_flag;
/*    */   }
/*    */   public int getOpt_id() {
/* 49 */     return this.opt_id;
/*    */   }
/*    */   public void setOpt_id(int opt_id) {
/* 52 */     this.opt_id = opt_id;
/*    */   }
/*    */   public String getOpt_name() {
/* 55 */     return this.opt_name;
/*    */   }
/*    */   public void setOpt_name(String opt_name) {
/* 58 */     this.opt_name = opt_name;
/*    */   }
/*    */   public int getParent_id() {
/* 61 */     return this.parent_id;
/*    */   }
/*    */   public void setParent_id(int parent_id) {
/* 64 */     this.parent_id = parent_id;
/*    */   }
/*    */   public String getTree_position() {
/* 67 */     return this.tree_position;
/*    */   }
/*    */   public void setTree_position(String tree_position) {
/* 70 */     this.tree_position = tree_position;
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.bean.org.operate.OperateBean
 * JD-Core Version:    0.6.2
 */