/*    */ package com.cicro.wcm.bean.appeal.model;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class ModelReleDept
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 4493098208357772087L;
/*    */   private int model_id;
/*    */   private int dept_id;
/*    */ 
/*    */   public int getModel_id()
/*    */   {
/* 13 */     return this.model_id;
/*    */   }
/*    */   public void setModel_id(int modelId) {
/* 16 */     this.model_id = modelId;
/*    */   }
/*    */   public int getDept_id() {
/* 19 */     return this.dept_id;
/*    */   }
/*    */   public void setDept_id(int deptId) {
/* 22 */     this.dept_id = deptId;
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.bean.appeal.model.ModelReleDept
 * JD-Core Version:    0.6.2
 */