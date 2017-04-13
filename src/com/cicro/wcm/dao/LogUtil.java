/*    */ package com.cicro.wcm.dao;
/*    */ 
/*    */ import java.io.PrintStream;
/*    */ import java.lang.reflect.Field;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ 
/*    */ public class LogUtil
/*    */ {
/*    */   public static final String AUDIT = "审核";
/*    */   public static final String DEAL = "处理";
/*    */   public static final String RELEASE = "发布";
/*    */   public static final String ADD = "添加";
/*    */   public static final String UPDATE = "修改";
/*    */   public static final String DELETE = "删除";
/*    */   public static final String PHPSICALLY_DEL = "彻底删除";
/*    */   public static final String SAVE_SORT = "保存排序";
/*    */   public static final String MOVE = "移动";
/*    */   public static final String SHIFT = "转移";
/*    */   public static final String SUBMIT = "提交";
/*    */   public static final String LOGIN = "登录";
/*    */   public static final String LOGOUT = "退出";
/* 24 */   private static LogUtil util = new LogUtil();
/*    */ 
/*    */   public static List<String> getAllString() {
/* 27 */     List ret = new ArrayList();
/* 28 */     Class c = LogUtil.class;
/*    */ 
/* 30 */     Field[] arr_f = c.getFields();
/* 31 */     for (Field f : arr_f) {
/* 32 */       String s = "";
/*    */       try {
/* 34 */         s = (String)f.get(util);
/*    */       } catch (Exception ex) {
/* 36 */         continue;
/*    */       }
/* 38 */       ret.add(s);
/*    */     }
/* 40 */     return ret;
/*    */   }
/*    */ 
/*    */   public static void main(String[] args) {
/* 44 */     List lt = getAllString();
/* 45 */     for (String s : lt) {
/* 46 */       System.out.println(s);
/*    */     }
/* 48 */     System.out.println("------");
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.dao.LogUtil
 * JD-Core Version:    0.6.2
 */