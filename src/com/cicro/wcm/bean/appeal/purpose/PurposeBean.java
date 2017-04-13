/*    */ package com.cicro.wcm.bean.appeal.purpose;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class PurposeBean
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = -8581745683360549586L;
/*    */   private int pur_id;
/*    */   private String pur_name;
/* 12 */   private int sort_id = 999;
/*    */ 
/*    */   public int getPur_id()
/*    */   {
/* 16 */     return this.pur_id;
/*    */   }
/*    */   public String getPur_name() {
/* 19 */     return this.pur_name;
/*    */   }
/*    */   public int getSort_id() {
/* 22 */     return this.sort_id;
/*    */   }
/*    */   public void setPur_id(int purId) {
/* 25 */     this.pur_id = purId;
/*    */   }
/*    */   public void setPur_name(String purName) {
/* 28 */     this.pur_name = purName;
/*    */   }
/*    */   public void setSort_id(int sortId) {
/* 31 */     this.sort_id = sortId;
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.bean.appeal.purpose.PurposeBean
 * JD-Core Version:    0.6.2
 */