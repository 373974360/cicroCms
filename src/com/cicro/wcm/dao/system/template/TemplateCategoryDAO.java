/*     */ package com.cicro.wcm.dao.system.template;
/*     */ 
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.bean.system.template.TemplateCategoryBean;
/*     */ import com.cicro.wcm.dao.PublicTableDAO;
/*     */ import com.cicro.wcm.db.DBManager;
/*     */ import java.io.PrintStream;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class TemplateCategoryDAO
/*     */ {
/*     */   public static List<TemplateCategoryBean> getTemplateCategoryListBySiteID(String site_id)
/*     */   {
/*  32 */     return DBManager.queryFList("getTemplateCategoryListBySiteID", site_id);
/*     */   }
/*     */ 
/*     */   public static List<TemplateCategoryBean> getAllTemplateCategoryList()
/*     */   {
/*  43 */     return DBManager.queryFList("getAllTemplateCategoryList", "");
/*     */   }
/*     */ 
/*     */   public static TemplateCategoryBean getTemplateCategoryBean(int tcat_id, String site_id)
/*     */   {
/*  53 */     Map map = new HashMap();
/*  54 */     map.put("tcat_id", tcat_id);
/*  55 */     map.put("site_id", site_id);
/*     */ 
/*  57 */     return (TemplateCategoryBean)DBManager.queryFObj("getTemplateCategoryBean", map);
/*     */   }
/*     */ 
/*     */   public static String getTemplateCategoryCount(Map<String, String> con_map)
/*     */   {
/*  67 */     return DBManager.getString("getTemplateCategoryCount", con_map);
/*     */   }
/*     */ 
/*     */   public static List<TemplateCategoryBean> getTemplateCategoryListForDB(Map<String, String> con_map)
/*     */   {
/*  78 */     return DBManager.queryFList("getTemplateCategoryListForDB", con_map);
/*     */   }
/*     */ 
/*     */   public static boolean updateTemplateCategory(TemplateCategoryBean tcb, SettingLogsBean stl)
/*     */   {
/*  88 */     if (DBManager.update("updateTemplateCategory", tcb))
/*     */     {
/*  90 */       PublicTableDAO.insertSettingLogs("修改", "模板分类", tcb.getTcat_id(), stl);
/*  91 */       return true;
/*     */     }
/*  93 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean addTemplateCategory(TemplateCategoryBean tcb, SettingLogsBean stl)
/*     */   {
/* 102 */     if (DBManager.insert("insertTemplateCategory", tcb))
/*     */     {
/* 104 */       PublicTableDAO.insertSettingLogs("添加", "模板分类", tcb.getTcat_id(), stl);
/* 105 */       return true;
/*     */     }
/* 107 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean cloneTemplateCategory(TemplateCategoryBean tcb)
/*     */   {
/* 116 */     tcb.setId(PublicTableDAO.getIDByTableName(PublicTableDAO.TEMPLATE_CATEGORY_TABLE_NAME));
/* 117 */     return DBManager.insert("insertTemplateCategory", tcb);
/*     */   }
/*     */ 
/*     */   public static boolean delTemplateCategory(String tcat_ids, String site_id, SettingLogsBean stl)
/*     */   {
/* 127 */     Map map = new HashMap();
/* 128 */     map.put("tcat_ids", tcat_ids);
/* 129 */     map.put("site_id", site_id);
/* 130 */     if (DBManager.delete("deleteTemplateCategory", map))
/*     */     {
/* 132 */       PublicTableDAO.insertSettingLogs("删除", "模板分类", tcat_ids, stl);
/* 133 */       return true;
/*     */     }
/* 135 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean sortTemplateCategory(String ids, SettingLogsBean stl)
/*     */   {
/*     */     try
/*     */     {
/* 147 */       Map m = new HashMap();
/* 148 */       String[] tempA = ids.split(",");
/* 149 */       for (int i = 0; i < tempA.length; i++)
/*     */       {
/* 151 */         m.put("sort_id", Integer.valueOf(i + 1));
/* 152 */         m.put("id", tempA[i]);
/* 153 */         DBManager.update("sort_template_category", m);
/*     */       }
/* 155 */       PublicTableDAO.insertSettingLogs("保存排序", "模板分类", ids, stl);
/* 156 */       return true;
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 160 */       e.printStackTrace();
/* 161 */     }return false;
/*     */   }
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/* 167 */     System.out.println(getAllTemplateCategoryList());
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.dao.system.template.TemplateCategoryDAO
 * JD-Core Version:    0.6.2
 */