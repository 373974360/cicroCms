/*    */ package com.cicro.wcm.bean.template;
/*    */ 
/*    */ public class TurnPageBean
/*    */ {
/*  4 */   private int cur_page = 1;
/*  5 */   private int count = 0;
/*  6 */   private int page_count = 1;
/*  7 */   private int prev_num = 1;
/*  8 */   private int next_num = 1;
/*  9 */   private int page_size = 10;
/* 10 */   private int curr_start_num = 3;
/*    */ 
/* 12 */   public int getCurr_start_num() { return this.curr_start_num; }
/*    */ 
/*    */   public void setCurr_start_num(int currStartNum) {
/* 15 */     this.curr_start_num = currStartNum;
/*    */   }   
/*    */   public int getCur_page() {
/* 18 */     return this.cur_page;
/*    */   }
/*    */   public void setCur_page(int curPage) {
/* 21 */     this.cur_page = curPage;
/*    */   }
/*    */   public int getCount() {
/* 24 */     return this.count;
/*    */   }
/*    */   public void setCount(int count) {
/* 27 */     this.count = count;
/*    */   }
/*    */   public int getPage_count() {
/* 30 */     return this.page_count;
/*    */   }
/*    */   public void setPage_count(int pageCount) {
/* 33 */     this.page_count = pageCount;
/*    */   }
/*    */   public int getPrev_num() {
/* 36 */     return this.prev_num;
/*    */   }
/*    */   public void setPrev_num(int prevNum) {
/* 39 */     this.prev_num = prevNum;
/*    */   }
/*    */   public int getNext_num() {
/* 42 */     return this.next_num;
/*    */   }
/*    */   public void setNext_num(int nextNum) {
/* 45 */     this.next_num = nextNum;
/*    */   }
/*    */   public int getPage_size() {
/* 48 */     return this.page_size;
/*    */   }
/*    */   public void setPage_size(int pageSize) {
/* 51 */     this.page_size = pageSize;
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.bean.template.TurnPageBean
 * JD-Core Version:    0.6.2
 */