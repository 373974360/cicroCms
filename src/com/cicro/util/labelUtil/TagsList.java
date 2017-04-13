/*    */ package com.cicro.util.labelUtil;
/*    */ 
/*    */ import java.util.Arrays;
/*    */ 
/*    */ class TagsList
/*    */ {
/*    */   private String[] data;
/*  7 */   private int size = 0;
/*    */ 
/*    */   public TagsList(int size) {
/* 10 */     this.data = new String[size];
/*    */   }
/*    */ 
/*    */   public TagsList() {
/* 14 */     this(10);
/*    */   }
/*    */ 
/*    */   public void add(String str) {
/* 18 */     ensureCapacity(this.size + 1);
/* 19 */     this.data[(this.size++)] = str;
/*    */   }
/*    */ 
/*    */   public String get(int index) {
/* 23 */     if (index < this.size) {
/* 24 */       return this.data[index];
/*    */     }
/* 26 */     return null;
/*    */   }
/*    */ 
/*    */   public boolean remove(String str)
/*    */   {
/* 31 */     for (int index = this.size - 1; index >= 0; index--) {
/* 32 */       if (str.equals(this.data[index])) {
/* 33 */         this.data[index] = null;
/* 34 */         return true;
/*    */       }
/*    */     }
/* 37 */     return false;
/*    */   }
/*    */ 
/*    */   public boolean remove(int index)
/*    */   {
/* 42 */     if (index < this.data.length) {
/* 43 */       this.data[index] = null;
/* 44 */       return true;
/*    */     }
/* 46 */     return false;
/*    */   }
/*    */ 
/*    */   public int size() {
/* 50 */     return this.size;
/*    */   }
/*    */ 
/*    */   public void ensureCapacity(int minSize)
/*    */   {
/* 55 */     int oldCapacity = this.data.length;
/* 56 */     if (minSize > oldCapacity) {
/* 57 */       int newCapacity = oldCapacity * 3 / 2 + 1 > minSize ? 
/* 58 */         oldCapacity * 3 / 2 + 1 : minSize;
/* 59 */       this.data = ((String[])Arrays.copyOf(this.data, newCapacity));
/*    */     }
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.util.labelUtil.TagsList
 * JD-Core Version:    0.6.2
 */