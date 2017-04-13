/*    */ package com.cicro.wcm.bean.search_bak_20151106;
/*    */ 
/*    */ import java.io.PrintStream;
/*    */ 
/*    */ public class PageControl
/*    */ {
/*    */   private int curPage;
/*    */   private int maxPage;
/*    */   private Long maxRowCount;
/* 13 */   private int rowsPerPage = 10;
/* 14 */   private int start = 0;
/*    */ 
/*    */   public int getCurPage() {
/* 17 */     return this.curPage;
/*    */   }
/*    */ 
/*    */   public void setCurPage(int curPage) {
/* 21 */     this.curPage = curPage;
/*    */   }
/*    */ 
/*    */   public int getMaxPage() {
/* 25 */     return this.maxPage;
/*    */   }
/*    */ 
/*    */   public void setMaxPage(int maxPage) {
/* 29 */     this.maxPage = maxPage;
/*    */   }
/*    */ 
/*    */   public Long getMaxRowCount() {
/* 33 */     return this.maxRowCount;
/*    */   }
/*    */ 
/*    */   public void setMaxRowCount(Long amaxRowCountxRowCount) {
/* 37 */     this.maxRowCount = amaxRowCountxRowCount;
/*    */   }
/*    */ 
/*    */   public int getRowsPerPage() {
/* 41 */     return this.rowsPerPage;
/*    */   }
/*    */ 
/*    */   public void setRowsPerPage(int rowsPerPage) {
/* 45 */     this.rowsPerPage = (rowsPerPage == 0 ? this.rowsPerPage : rowsPerPage);
/* 46 */     System.out.println("this.rowsPerPage===" + this.rowsPerPage);
/*    */   }
/*    */ 
/*    */   public void countMaxPage() {
/* 50 */     if (this.maxRowCount.longValue() % this.rowsPerPage == 0L)
/* 51 */       this.maxPage = ((int)(this.maxRowCount.longValue() / this.rowsPerPage));
/*    */     else
/* 53 */       this.maxPage = ((int)(this.maxRowCount.longValue() / this.rowsPerPage + 1L));
/*    */   }
/*    */ 
/*    */   public int getStart()
/*    */   {
/* 58 */     return this.start;
/*    */   }
/*    */ 
/*    */   public void setStart(int start) {
/* 62 */     this.start = start;
/*    */   }
/*    */ 
/*    */   public void countStart() {
/* 66 */     this.start = ((this.curPage - 1) * this.rowsPerPage);
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.bean.search.PageControl
 * JD-Core Version:    0.6.2
 */