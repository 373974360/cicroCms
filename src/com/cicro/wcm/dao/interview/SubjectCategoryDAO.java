/*     */ package com.cicro.wcm.dao.interview;
/*     */ 
/*     */ import com.cicro.wcm.bean.interview.SubjectCategory;
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.dao.PublicTableDAO;
/*     */ import com.cicro.wcm.db.DBManager;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class SubjectCategoryDAO
/*     */ {
/*     */   public static List<SubjectCategory> getAllSubjectCategoryList()
/*     */   {
/*  27 */     return DBManager.queryFList("getAllSubjectCategoryList", "");
/*     */   }
/*     */ 
/*     */   public static String getSubjectCountByCategoryID(int id)
/*     */   {
/*  38 */     return DBManager.getString("getSubjectCountByCategoryID", Integer.valueOf(id));
/*     */   }
/*     */ 
/*     */   public static String getSubCategoryCount(String con, String site_id)
/*     */   {
/*  48 */     Map m = new HashMap();
/*  49 */     m.put("con", con);
/*  50 */     m.put("site_id", site_id);
/*  51 */     return DBManager.getString("getSubCategoryCount", con);
/*     */   }
/*     */ 
/*     */   public static SubjectCategory getSubjectCategoryBean(int id)
/*     */   {
/*  61 */     return (SubjectCategory)DBManager.queryFObj("getSubjectCategoryBean", Integer.valueOf(id));
/*     */   }
/*     */ 
/*     */   public static SubjectCategory getSubjectCategoryBean(String category_id)
/*     */   {
/*  71 */     return (SubjectCategory)DBManager.queryFObj("getSubjectCategoryBeanByCId", category_id);
/*     */   }
/*     */ 
/*     */   public static List getSubCategoryAllName()
/*     */   {
/*  82 */     List l = DBManager.queryFList("getSubCategoryAllName", "");
/*  83 */     return l;
/*     */   }
/*     */ 
/*     */   public static List getSubCategoryList(Map m)
/*     */   {
/*  94 */     List l = DBManager.queryFList("getSubCategoryList", m);
/*  95 */     return l;
/*     */   }
/*     */ 
/*     */   public static boolean insertSubCategory(SubjectCategory sc, SettingLogsBean stl)
/*     */   {
/* 105 */     int id = PublicTableDAO.getIDByTableName(PublicTableDAO.INTERVIEW_SCATEGORY_TABLE_NAME);
/* 106 */     sc.setId(id);
/* 107 */     if (DBManager.insert("insertSubCategory", sc)) {
/* 108 */       PublicTableDAO.insertSettingLogs("添加", "访谈主题分类", id, stl);
/* 109 */       return true;
/*     */     }
/*     */ 
/* 112 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateSubCategory(SubjectCategory sc, SettingLogsBean stl)
/*     */   {
/* 122 */     if (DBManager.update("updateSubCategory", sc)) {
/* 123 */       PublicTableDAO.insertSettingLogs("修改", "访谈主题分类", sc.getId(), stl);
/* 124 */       return true;
/*     */     }
/*     */ 
/* 127 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteSubCategory(Map m, SettingLogsBean stl)
/*     */   {
/* 138 */     if (DBManager.update("deleteSubCategory", m)) {
/* 139 */       PublicTableDAO.insertSettingLogs("删除", "访谈主题分类", m.get("ids"), stl);
/* 140 */       return true;
/*     */     }
/*     */ 
/* 143 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateSubCategoryState(Map m, SettingLogsBean stl)
/*     */   {
/* 154 */     if (DBManager.update("updateSubCategoryStatus", m)) {
/* 155 */       PublicTableDAO.insertSettingLogs("修改", "访谈主题分类发布状态", m.get("ids"), stl);
/* 156 */       return true;
/*     */     }
/*     */ 
/* 159 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean saveSubCategorySort(String ids, SettingLogsBean stl)
/*     */   {
/* 169 */     if ((ids != null) && (!"".equals(ids)))
/*     */     {
/* 171 */       Map new_m = new HashMap();
/* 172 */       String[] tempA = ids.split(",");
/*     */       try {
/* 174 */         for (int i = 0; i < tempA.length; i++)
/*     */         {
/* 176 */           new_m.put("sort", i + 1);
/* 177 */           new_m.put("id", tempA[i]);
/* 178 */           DBManager.update("saveSubCategorySort", new_m);
/*     */         }
/*     */       }
/*     */       catch (Exception e) {
/* 182 */         e.printStackTrace();
/* 183 */         return false;
/*     */       }
/* 185 */       PublicTableDAO.insertSettingLogs("保存排序", "访谈主题分类", ids, stl);
/*     */     }
/* 187 */     return true;
/*     */   }
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.dao.interview.SubjectCategoryDAO
 * JD-Core Version:    0.6.2
 */