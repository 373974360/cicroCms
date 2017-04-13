/*    */ package com.cicro.wcm.bean.appeal.satisfaction;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ 
/*    */ public class SatisfactionBean
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 6521099637603667064L;
/*    */   private int sat_id;
/*    */   private String sat_item;
/*    */   private int sat_score;
/* 19 */   private List<SatisfactionBean> SatisfactionOptions_list = new ArrayList();
/*    */ 
/*    */   public List<SatisfactionBean> getSatisfactionOptions_list() {
/* 22 */     return this.SatisfactionOptions_list;
/*    */   }
/*    */ 
/*    */   public void setSatisfactionOptions_list(List<SatisfactionBean> satisfactionOptionsList) {
/* 26 */     this.SatisfactionOptions_list = satisfactionOptionsList;
/*    */   }
/*    */   public int getSat_id() {
/* 29 */     return this.sat_id;
/*    */   }
/*    */   public String getSat_item() {
/* 32 */     return this.sat_item;
/*    */   }
/*    */   public int getSat_score() {
/* 35 */     return this.sat_score;
/*    */   }
/*    */   public void setSat_id(int satId) {
/* 38 */     this.sat_id = satId;
/*    */   }
/*    */   public void setSat_item(String satItem) {
/* 41 */     this.sat_item = satItem;
/*    */   }
/*    */   public void setSat_score(int satScore) {
/* 44 */     this.sat_score = satScore;
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.bean.appeal.satisfaction.SatisfactionBean
 * JD-Core Version:    0.6.2
 */