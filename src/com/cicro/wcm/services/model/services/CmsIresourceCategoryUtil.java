/*    */ package com.cicro.wcm.services.model.services;
/*    */ 
/*    */ import com.cicro.xjbt.siteRele.SiteReleHandl;
/*    */ import java.io.InputStream;
/*    */ import java.io.PrintStream;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Arrays;
/*    */ import java.util.List;
/*    */ import java.util.Properties;
/*    */ 
/*    */ public class CmsIresourceCategoryUtil
/*    */ {
/* 14 */   public static Properties p = new Properties();
/*    */ 
/* 16 */   static { reloadProperties(); }
/*    */ 
/*    */   public static void reloadProperties()
/*    */   {
/*    */     try {
/* 21 */       InputStream inputStream = new CmsIresourceCategoryUtil().getClass().getClassLoader().getResourceAsStream("cms_iresource_cat.properties");
/* 22 */       p.load(inputStream);
/*    */     } catch (Exception e) {
/* 24 */       e.printStackTrace();
/*    */     }
/*    */   }
/*    */ 
/*    */   public static String getCmscatidByResourceid(String treeId) {
/* 29 */     String result = "";
/*    */     try
/*    */     {
/* 32 */       result = p.getProperty(treeId);
/* 33 */       if (result == null) { result = p.getProperty("all_category_id");
/* 35 */         if (result != null); }
/* 36 */       return "";
/*    */     }
/*    */     catch (Exception e)
/*    */     {
/* 42 */       e.printStackTrace();
/* 43 */     }return result;
/*    */   }
/*    */ 
/*    */   public static List<String> getSiteIdByDeptTreePosition(String dept_position)
/*    */   {
/* 50 */     List list = new ArrayList();
/*    */     try
/*    */     {
/* 53 */       String str = "";
/* 54 */       str = SiteReleHandl.getSiteIDSForDeptPosition(dept_position);
/* 55 */       if ((str == null) || ("".equals(str))) {
/* 56 */         return list;
/*    */       }
/* 58 */       System.out.println("getSiteIdByDeptTreePosition-----str::" + str);
/* 59 */       List list2 = Arrays.asList(str.split(","));
/* 60 */       for (String s : list2) {
/* 61 */         if ((s != null) && (!"".equals(s))) {
/* 62 */           list.add(s);
/*    */         }
/*    */       }
/*    */ 
/* 66 */       return list;
/*    */     } catch (Exception e) {
/* 68 */       e.printStackTrace();
/* 69 */     }return list;
/*    */   }
/*    */ 
/*    */   public static void main(String[] args)
/*    */   {
/* 79 */     String str = getCmscatidByResourceid("ss");
/* 80 */     System.out.println(str);
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.model.services.CmsIresourceCategoryUtil
 * JD-Core Version:    0.6.2
 */