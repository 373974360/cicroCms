/*    */ package com.cicro.wcm.bean.member;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class MemberCategoryBean
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = -1763371067078587728L;
/* 12 */   private String mcat_id = "";
/* 13 */   private String mcat_name = "";
/* 14 */   private String mcat_memo = "";
/* 15 */   private int sort_id = 999;
/*    */ 
/*    */   public String getMcat_id() {
/* 18 */     return this.mcat_id;
/*    */   }
/*    */   public String getMcat_name() {
/* 21 */     return this.mcat_name;
/*    */   }
/*    */   public String getMcat_memo() {
/* 24 */     return this.mcat_memo;
/*    */   }
/*    */   public int getSort_id() {
/* 27 */     return this.sort_id;
/*    */   }
/*    */ 
/*    */   public void setMcat_id(String mcatId) {
/* 31 */     this.mcat_id = mcatId;
/*    */   }
/*    */   public void setMcat_name(String mcatName) {
/* 34 */     this.mcat_name = mcatName;
/*    */   }
/*    */   public void setMcat_memo(String mcatMemo) {
/* 37 */     this.mcat_memo = mcatMemo;
/*    */   }
/*    */   public void setSort_id(int sortId) {
/* 40 */     this.sort_id = sortId;
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.bean.member.MemberCategoryBean
 * JD-Core Version:    0.6.2
 */