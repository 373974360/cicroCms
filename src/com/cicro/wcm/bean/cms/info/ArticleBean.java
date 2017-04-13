/*    */ package com.cicro.wcm.bean.cms.info;
/*    */ 
/*    */ public class ArticleBean extends InfoBean
/*    */ {
/*    */   private static final long serialVersionUID = -6598237580496572399L;
/* 13 */   private String info_content = "";
/*    */   private int page_count;
/*    */   private int prepage_wcount;
/*    */   private int word_count;
/*    */ 
/*    */   public String getInfo_content()
/*    */   {
/* 19 */     return this.info_content;
/*    */   }
/*    */   public int getPage_count() {
/* 22 */     return this.page_count;
/*    */   }
/*    */   public int getPrepage_wcount() {
/* 25 */     return this.prepage_wcount;
/*    */   }
/*    */   public int getWord_count() {
/* 28 */     return this.word_count;
/*    */   }
/*    */   public void setInfo_content(String info_content) {
/* 31 */     this.info_content = info_content;
/*    */   }
/*    */   public void setPage_count(int page_count) {
/* 34 */     this.page_count = page_count;
/*    */   }
/*    */   public void setPrepage_wcount(int prepage_wcount) {
/* 37 */     this.prepage_wcount = prepage_wcount;
/*    */   }
/*    */   public void setWord_count(int word_count) {
/* 40 */     this.word_count = word_count;
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.bean.cms.info.ArticleBean
 * JD-Core Version:    0.6.2
 */