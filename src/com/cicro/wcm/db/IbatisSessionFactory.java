/*    */ package com.cicro.wcm.db;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.PrintStream;
/*    */ import java.io.Reader;
/*    */ import java.sql.Connection;
/*    */ import org.apache.ibatis.io.Resources;
/*    */ import org.apache.ibatis.session.Configuration;
/*    */ import org.apache.ibatis.session.ExecutorType;
/*    */ import org.apache.ibatis.session.SqlSession;
/*    */ import org.apache.ibatis.session.SqlSessionFactory;
/*    */ import org.apache.ibatis.session.SqlSessionFactoryBuilder;
/*    */ import org.apache.ibatis.session.TransactionIsolationLevel;
/*    */ 
/*    */ public class IbatisSessionFactory
/*    */   implements SqlSessionFactory
/*    */ {
/* 45 */   private static SqlSessionFactory sqlSessionFactory = null;
/*    */ 
/*    */   public static SqlSessionFactory getInstance()
/*    */   {
/* 24 */     if (sqlSessionFactory == null) {
/* 25 */       initSqlSessionFactory();
/*    */     }
/* 27 */     return sqlSessionFactory;
/*    */   }
/*    */ 
/*    */   private static void initSqlSessionFactory() {
/* 31 */     String resource = "SqlMapConfig.xml";
/* 32 */     Reader reader = null;
/*    */     try {
/* 34 */       reader = Resources.getResourceAsReader(resource);
/* 35 */       SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
/*    */ 
/* 37 */       sqlSessionFactory = builder.build(reader);
/*    */     } catch (IOException e) {
/* 39 */       System.out.println("创建iBATIS数据工厂时出现错误！");
/* 40 */       e.printStackTrace();
/*    */     }
/*    */   }
/*    */ 
/*    */   public Configuration getConfiguration()
/*    */   {
/* 48 */     return sqlSessionFactory.getConfiguration();
/*    */   }
/*    */ 
/*    */   public SqlSession openSession() {
/* 52 */     return sqlSessionFactory.openSession();
/*    */   }
/*    */ 
/*    */   public SqlSession openSession(boolean arg0) {
/* 56 */     return sqlSessionFactory.openSession(arg0);
/*    */   }
/*    */ 
/*    */   public SqlSession openSession(Connection arg0) {
/* 60 */     return sqlSessionFactory.openSession(arg0);
/*    */   }
/*    */ 
/*    */   public SqlSession openSession(ExecutorType arg0) {
/* 64 */     return sqlSessionFactory.openSession(arg0);
/*    */   }
/*    */ 
/*    */   public SqlSession openSession(ExecutorType arg0, boolean arg1) {
/* 68 */     return sqlSessionFactory.openSession(arg0, arg1);
/*    */   }
/*    */ 
/*    */   public SqlSession openSession(ExecutorType arg0, Connection arg1) {
/* 72 */     return sqlSessionFactory.openSession(arg0, arg1);
/*    */   }
/*    */ 
/*    */   public SqlSession openSession(TransactionIsolationLevel arg0)
/*    */   {
/* 77 */     return sqlSessionFactory.openSession(arg0);
/*    */   }
/*    */ 
/*    */   public SqlSession openSession(ExecutorType arg0, TransactionIsolationLevel arg1)
/*    */   {
/* 83 */     return sqlSessionFactory.openSession(arg0, arg1);
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.db.IbatisSessionFactory
 * JD-Core Version:    0.6.2
 */