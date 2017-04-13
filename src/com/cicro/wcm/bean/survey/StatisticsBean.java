/*    */ package com.cicro.wcm.bean.survey;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class StatisticsBean
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = -3337185805933363838L;
/* 17 */   private String item_id = "";
/* 18 */   private String item_num = "";
/* 19 */   private int counts = 0;
/* 20 */   private String proportion = "";
/*    */ 
/* 22 */   public int getCounts() { return this.counts; }
/*    */ 
/*    */   public void setCounts(int counts) {
/* 25 */     this.counts = counts;
/*    */   }
/*    */   public String getItem_id() {
/* 28 */     return this.item_id;
/*    */   }
/*    */   public void setItem_id(String item_id) {
/* 31 */     this.item_id = item_id;
/*    */   }
/*    */   public String getItem_num() {
/* 34 */     return this.item_num;
/*    */   }
/*    */   public void setItem_num(String item_num) {
/* 37 */     this.item_num = item_num;
/*    */   }
/*    */   public String getProportion() {
/* 40 */     return this.proportion;
/*    */   }
/*    */   public void setProportion(String proportion) {
/* 43 */     this.proportion = proportion;
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.bean.survey.StatisticsBean
 * JD-Core Version:    0.6.2
 */