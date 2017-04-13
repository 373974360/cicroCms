/*     */ package com.cicro.wcm.services.init;
/*     */ 
/*     */ import com.cicro.util.jconfig.JconfigUtil;
/*     */ import com.cicro.util.jconfig.JconfigUtilContainer;
/*     */ import com.cicro.util.xml.XmlManager;
/*     */ import com.cicro.wcm.dao.PublicTableDAO;
/*     */ import com.cicro.wcm.db.BoneDataSourceFactory;
/*     */ import java.io.PrintStream;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import org.w3c.dom.Document;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.NodeList;
/*     */ 
/*     */ public class InitService
/*     */ {
/*  23 */   private static String rootPath = JconfigUtilContainer.bashConfig().getProperty("path", "", "class_Path") + "/initdb/";
/*     */ 
/*     */   public static void initMain() {
/*     */     try {
/*  27 */       if (!getTable("cs_wcm_sequence")) {
/*  28 */         initTable();
/*  29 */         initSql();
/*     */       }
/*     */     } catch (Exception e) {
/*  32 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void initTable()
/*     */   {
/*     */     try {
/*  39 */       String data_type_name = BoneDataSourceFactory.getDataTypeName();
/*     */ 
/*  41 */       String fileStr = rootPath + "initTable.xml";
/*  42 */       Document document = XmlManager.createDOM(fileStr);
/*  43 */       NodeList fileNodeList = XmlManager.queryNodeList(document, "root/file");
/*  44 */       System.out.println("*******initSql********start*****");
/*  45 */       for (int i = 0; i < fileNodeList.getLength(); i++) {
/*  46 */         Node fileNode = fileNodeList.item(i);
/*  47 */         String fileName = XmlManager.queryNodeValue(fileNode, ".");
/*  48 */         fileName = data_type_name + "/" + fileName;
/*  49 */         System.out.println("fileName====" + fileName);
/*  50 */         String sqlFileStr = rootPath + fileName;
/*  51 */         Document documentSql = XmlManager.createDOM(sqlFileStr);
/*  52 */         NodeList sqlNodeList = XmlManager.queryNodeList(documentSql, "root/table/sql");
/*  53 */         for (int j = 0; j < sqlNodeList.getLength(); j++) {
/*  54 */           Node sqlNode = sqlNodeList.item(j);
/*  55 */           String sqlName = XmlManager.queryNodeValue(sqlNode, ".");
/*  56 */           System.out.println("sqlName====" + sqlName);
/*  57 */           Map map = new HashMap();
/*  58 */           map.put("sql", sqlName);
/*  59 */           PublicTableDAO.createSql(map);
/*     */         }
/*  61 */         System.out.println(fileName + " end");
/*     */       }
/*  63 */       System.out.println("*******initSql********end*****");
/*     */     } catch (Exception e) {
/*  65 */       e.printStackTrace();
/*  66 */       System.out.println("error initSql!!");
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void initSql()
/*     */   {
/*     */     try {
/*  73 */       String fileStr = rootPath + "initSql.xml";
/*  74 */       Document documentSql = XmlManager.createDOM(fileStr);
/*  75 */       NodeList sqlNodeList = XmlManager.queryNodeList(documentSql, "root/table/sql");
/*  76 */       for (int j = 0; j < sqlNodeList.getLength(); j++) {
/*  77 */         Node sqlNode = sqlNodeList.item(j);
/*  78 */         String sqlName = XmlManager.queryNodeValue(sqlNode, ".");
/*  79 */         System.out.println("sqlName====" + sqlName);
/*  80 */         Map map = new HashMap();
/*  81 */         map.put("sql", sqlName);
/*  82 */         PublicTableDAO.createSql(map);
/*     */       }
/*     */     } catch (Exception e) {
/*  85 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public static boolean getTable(String tableName)
/*     */   {
/*     */     try
/*     */     {
/*  96 */       String sql = "select count(*) from " + tableName;
/*  97 */       Map map = new HashMap();
/*  98 */       map.put("sql", sql);
/*  99 */       String count = PublicTableDAO.getTable(map);
/* 100 */       if (count == null) {
/* 101 */         return false;
/*     */       }
/* 103 */       Integer.valueOf(count);
/* 104 */       if (Integer.valueOf(count).intValue() >= 0) {
/* 105 */         return true;
/*     */       }
/* 107 */       return false;
/*     */     } catch (Exception e) {
/* 109 */       e.printStackTrace();
/* 110 */     }return false;
/*     */   }
/*     */ 
/*     */   public static void DropTableAll()
/*     */   {
/*     */     try
/*     */     {
/* 117 */       String fileStr = rootPath + "tableList.xml";
/* 118 */       Document document = XmlManager.createDOM(fileStr);
/* 119 */       NodeList fileNodeList = XmlManager.queryNodeList(document, "root/file");
/* 120 */       System.out.println("*******initSql********start*****");
/* 121 */       for (int i = 0; i < fileNodeList.getLength(); i++) {
/* 122 */         Node fileNode = fileNodeList.item(i);
/* 123 */         String tableName = XmlManager.queryNodeValue(fileNode, ".");
/* 124 */         System.out.println("tableName====" + tableName);
/* 125 */         String sql = " drop table " + tableName;
/* 126 */         Map map = new HashMap();
/* 127 */         map.put("sql", sql);
/* 128 */         PublicTableDAO.createSql(map);
/*     */       }
/*     */     } catch (Exception e) {
/* 131 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/* 137 */     initTable();
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.init.InitService
 * JD-Core Version:    0.6.2
 */