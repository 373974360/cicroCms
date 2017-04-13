/*     */ package com.cicro.wcm.db;
/*     */ 
/*     */ import java.util.List;
/*     */ import org.apache.ibatis.session.Configuration;
/*     */ import org.apache.ibatis.session.SqlSession;
/*     */ import org.apache.ibatis.session.SqlSessionFactory;
/*     */ 
/*     */ public class DBManager
/*     */ {
/*     */   public static String getString(String sqlName, Object o)
/*     */   {
/*  35 */     SqlSession s = IbatisSessionFactory.getInstance().openSession();
/*     */     try {
/*  37 */       Object tempO = s.selectOne(getSqlNameByDataType(sqlName), o);
/*  38 */       if (tempO == null) {
/*  39 */         return "";
/*     */       }
/*  41 */       return tempO.toString();
/*     */     } finally {
/*  43 */       s.close();
/*     */     }
/*     */   }
/*     */ 
/*     */   public static Object queryFObj(String sqlName, Object o)
/*     */   {
/*  56 */     SqlSession s = IbatisSessionFactory.getInstance().openSession();
/*     */     try {
/*  58 */       return s.selectOne(getSqlNameByDataType(sqlName), o);
/*     */     } finally {
/*  60 */       s.close();
/*     */     }
/*     */   }
/*     */ 
/*     */   public static List queryFList(String sqlName, Object o)
/*     */   {
/*  73 */     SqlSession s = IbatisSessionFactory.getInstance().openSession();
/*     */     try {
/*  75 */       return s.selectList(getSqlNameByDataType(sqlName), o);
/*     */     } finally {
/*  77 */       s.close();
/*     */     }
/*     */   }
/*     */ 
/*     */   public static boolean insert(String sqlName, Object o)
/*     */   {
/*  91 */     SqlSession s = IbatisSessionFactory.getInstance().openSession();
/*     */     try {
/*  93 */       s.insert(getSqlNameByDataType(sqlName), o);
/*     */ 
/*  95 */       s.commit();
/*     */     } catch (Exception ex) {
/*  97 */       ex.printStackTrace();
/*  98 */       return false;
/*     */     }
/*     */     finally {
/* 101 */       s.close();
/*     */     }
/* 103 */     return true;
/*     */   }
/*     */ 
/*     */   public static boolean update(String sqlName, Object o)
/*     */   {
/* 116 */     SqlSession s = IbatisSessionFactory.getInstance().openSession();
/*     */     try {
/* 118 */       s.update(getSqlNameByDataType(sqlName), o);
/* 119 */       s.commit();
/*     */     } catch (Exception ex) {
/* 121 */       ex.printStackTrace();
/* 122 */       return false;
/*     */     }
/*     */     finally {
/* 125 */       s.close();
/*     */     }
/* 127 */     return true;
/*     */   }
/*     */ 
/*     */   public static boolean delete(String sqlName, Object o)
/*     */   {
/* 139 */     SqlSession s = IbatisSessionFactory.getInstance().openSession();
/*     */     try {
/* 141 */       s.delete(getSqlNameByDataType(sqlName), o);
/* 142 */       s.commit();
/*     */     } catch (Exception ex) {
/* 144 */       ex.printStackTrace();
/* 145 */       return false;
/*     */     }
/*     */     finally {
/* 148 */       s.close();
/*     */     }
/* 150 */     return true;
/*     */   }
/*     */ 
/*     */   public static boolean executeProcedure(String proName, Object o)
/*     */   {
/* 164 */     SqlSession s = IbatisSessionFactory.getInstance().openSession();
/*     */     try {
/* 166 */       s.selectOne(proName, o);
/* 167 */       s.commit();
/* 168 */       return true;
/*     */     } catch (Exception ex) {
/* 170 */       ex.printStackTrace();
/* 171 */       return false;
/*     */     }
/*     */     finally {
/* 174 */       s.close();
/*     */     }
/*     */   }
/*     */ 
/*     */   public static boolean executeProcedure(String proName)
/*     */   {
/* 187 */     SqlSession s = IbatisSessionFactory.getInstance().openSession();
/*     */     try {
/* 189 */       s.selectOne(proName);
/* 190 */       s.commit();
/* 191 */       return true;
/*     */     } catch (Exception ex) {
/* 193 */       ex.printStackTrace();
/* 194 */       return false;
/*     */     } finally {
/* 196 */       s.close();
/*     */     }
/*     */   }
/*     */ 
/*     */   public static String getSqlNameByDataType(String sqlName)
/*     */   {
/* 209 */     String data_type_name = BoneDataSourceFactory.getDataTypeName();
/*     */ 
/* 211 */     if ((!"oracle".equals(data_type_name)) && (IbatisSessionFactory.getInstance().getConfiguration().hasStatement(sqlName + "_" + data_type_name)))
/* 212 */       sqlName = sqlName + "_" + data_type_name;
/* 213 */     return sqlName;
/*     */   }
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.db.DBManager
 * JD-Core Version:    0.6.2
 */