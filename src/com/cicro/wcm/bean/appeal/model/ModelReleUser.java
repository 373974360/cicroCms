/*    */ package com.cicro.wcm.bean.appeal.model;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class ModelReleUser
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 7402961441938300532L;
/*    */   private int model_id;
/*    */   private int user_id;
/*    */ 
/*    */   public int getModel_id()
/*    */   {
/* 11 */     return this.model_id;
/*    */   }
/*    */   public void setModel_id(int modelId) {
/* 14 */     this.model_id = modelId;
/*    */   }
/*    */   public int getUser_id() {
/* 17 */     return this.user_id;
/*    */   }
/*    */   public void setUser_id(int userId) {
/* 20 */     this.user_id = userId;
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.bean.appeal.model.ModelReleUser
 * JD-Core Version:    0.6.2
 */