/*    */ package com.cicro.wcm.services.search_bak_20151106.index.util;
/*    */ 
/*    */ import java.io.PrintStream;
/*    */ import java.util.LinkedList;
/*    */ 
/*    */ public class Queue
/*    */ {
/*  7 */   private static LinkedList list = new LinkedList();
/*    */ 
/*    */   public void put(Object v) {
/* 10 */     list.addFirst(v);
/*    */   }
/*    */ 
/*    */   public synchronized Object get() {
/* 14 */     return list.removeLast();
/*    */   }
/*    */ 
/*    */   public synchronized boolean isEmpty()
/*    */   {
/* 19 */     return list.isEmpty();
/*    */   }
/*    */ 
/*    */   public synchronized boolean isContains(Object o) {
/* 23 */     if (list.contains(o)) {
/* 24 */       return true;
/*    */     }
/* 26 */     return false;
/*    */   }
/*    */ 
/*    */   public synchronized boolean remove(Object o) {
/* 30 */     if (list.contains(o)) {
/* 31 */       list.remove(o);
/* 32 */       return true;
/*    */     }
/* 34 */     return false;
/*    */   }
/*    */ 
/*    */   public static void main(String[] args) {
/* 38 */     Queue queue = new Queue();
/* 39 */     for (int i = 0; i < 10; i++) {
/* 40 */       queue.put(Integer.toString(i));
/*    */     }
/*    */ 
/* 43 */     int i = 0;
/* 44 */     while (!queue.isEmpty()) {
/* 45 */       i++;
/* 46 */       System.out.println(queue.get());
/* 47 */       if (i == 5) {
/* 48 */         queue.put(Integer.toString(11));
/*    */       }
/*    */     }
/* 51 */     System.out.println("##############################");
/* 52 */     while (!queue.isEmpty())
/* 53 */       System.out.println(queue.get());
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.search.index.util.Queue
 * JD-Core Version:    0.6.2
 */