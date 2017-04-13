/*    */ package com.cicro.wcm.db;
/*    */ 
/*    */ import com.jolbox.bonecp.BoneCPConfig;
/*    */ import com.jolbox.bonecp.BoneCPDataSource;
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ import java.io.PrintStream;
/*    */ import java.util.Properties;
/*    */ import javax.sql.DataSource;
/*    */ 
/*    */ public class BoneDataSourceFactory
/*    */ {
/*    */   private BoneCPDataSource dataSource;
/* 15 */   private static String data_type_name = "";
/*    */ 
/*    */   static {
/*    */     try {
/* 19 */       System.out.println("BoneDataSourceFactory -----  start ");
/* 20 */       InputStream input = new BoneDataSourceFactory().getClass().getClassLoader().getResourceAsStream("jdbc.properties");
/* 21 */       Properties p = new Properties();
/*    */       try {
/* 23 */         p.load(input);
/*    */       }
/*    */       catch (IOException e) {
/* 26 */         e.printStackTrace();
/*    */       }
/* 28 */       data_type_name = p.getProperty("dbtype");
/* 29 */       System.out.println("data_type_name------" + data_type_name);
/*    */     } catch (Exception e) {
/* 31 */       e.printStackTrace();
/*    */     }
/*    */   }
/*    */ 
/*    */   public DataSource getDataSource() {
/* 36 */     return this.dataSource;
/*    */   }
/*    */ 
/*    */   public static String getDataTypeName()
/*    */   {
/* 41 */     return data_type_name;
/*    */   }
/*    */ 
/*    */   public static void setDataTypeName(String data_type_name2)
/*    */   {
/* 46 */     data_type_name = data_type_name2;
/*    */   }
/*    */ 
/*    */   public void setProperties(Properties props) {
/* 50 */     BoneCPConfig bcpc = new BoneCPConfig();
/* 51 */     bcpc.setJdbcUrl(props.getProperty("url"));
/* 52 */     bcpc.setUsername(props.getProperty("username"));
/* 53 */     bcpc.setPassword(props.getProperty("password"));
/* 54 */     bcpc.setMaxConnectionsPerPartition(Integer.parseInt(props.getProperty("maximum-connection-count")));
/* 55 */     bcpc.setMinConnectionsPerPartition(Integer.parseInt(props.getProperty("minimum-connection-count")));
/*    */ 
/* 59 */     data_type_name = props.getProperty("alias");
/* 60 */     this.dataSource = new BoneCPDataSource(bcpc);
/* 61 */     this.dataSource.setDriverClass(props.getProperty("driver"));
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.db.BoneDataSourceFactory
 * JD-Core Version:    0.6.2
 */