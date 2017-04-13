/*     */ package com.cicro.wcm.dao.system.template;
/*     */ 
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.bean.system.template.TemplateBean;
/*     */ import com.cicro.wcm.dao.PublicTableDAO;
/*     */ import com.cicro.wcm.db.DBManager;
/*     */ import java.io.PrintStream;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class TemplateDAO
/*     */ {
/*     */   public static List<TemplateBean> getAllTemplateList()
/*     */   {
/*  32 */     return DBManager.queryFList("getAllTemplateList", "");
/*     */   }
/*     */ 
/*     */   public static TemplateBean getTemplateBean(int t_id)
/*     */   {
/*  42 */     return (TemplateBean)DBManager.queryFObj("getTemplateBean", Integer.valueOf(t_id));
/*     */   }
/*     */ 
/*     */   public static String getTemplateCount(Map<String, String> con_map)
/*     */   {
/*  52 */     return DBManager.getString("getTemplateCount", con_map);
/*     */   }
/*     */ 
/*     */   public static List<TemplateBean> getTemplateListForDB(Map<String, String> con_map)
/*     */   {
/*  63 */     return DBManager.queryFList("getTemplateListForDB", con_map);
/*     */   }
/*     */ 
/*     */   public static boolean updateTemplate(TemplateBean tb, SettingLogsBean stl)
/*     */   {
/*  73 */     if (DBManager.update("updateTemplate", tb))
/*     */     {
/*  75 */       PublicTableDAO.insertSettingLogs("修改", "模板", tb.getT_id(), stl);
/*  76 */       return true;
/*     */     }
/*  78 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean cloneTemplate(TemplateBean tb)
/*     */   {
/*  87 */     int tbId = PublicTableDAO.getIDByTableName(PublicTableDAO.TEMPLATE_TABLE_NAME);
/*  88 */     tb.setId(tbId);
/*  89 */     return DBManager.insert("insertTemplate", tb);
/*     */   }
/*     */ 
/*     */   public static boolean addTemplate(TemplateBean tb, SettingLogsBean stl)
/*     */   {
/*  98 */     int tbId = PublicTableDAO.getIDByTableName(PublicTableDAO.TEMPLATE_TABLE_NAME);
/*  99 */     tb.setT_id(tbId);
/* 100 */     tb.setId(tbId);
/* 101 */     if (DBManager.insert("insertTemplate", tb))
/*     */     {
/* 103 */       PublicTableDAO.insertSettingLogs("添加", "模板", tbId, stl);
/* 104 */       return true;
/*     */     }
/* 106 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean delTemplate(String t_ids, String site_id, SettingLogsBean stl)
/*     */   {
/* 116 */     Map map = new HashMap();
/* 117 */     map.put("t_ids", t_ids);
/* 118 */     map.put("site_id", site_id);
/* 119 */     if (DBManager.delete("deleteTemplate", map))
/*     */     {
/* 121 */       PublicTableDAO.insertSettingLogs("删除", "模板", t_ids, stl);
/* 122 */       return true;
/*     */     }
/* 124 */     return false;
/*     */   }
/*     */ 
/*     */   public static void main(String[] args) {
/* 128 */     TemplateBean tb = new TemplateBean();
/* 129 */     tb.setApp_id("bbbbbbbbbbbb");
/* 130 */     tb.setSite_id("gx");
/* 131 */     tb.setT_id(1);
/* 132 */     tb.setT_ver(1);
/*     */ 
/* 135 */     Map map = new HashMap();
/* 136 */     map.put("t_ids", "1");
/* 137 */     if (DBManager.delete("deleteTemplate", map))
/* 138 */       System.out.println(getAllTemplateList());
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.dao.system.template.TemplateDAO
 * JD-Core Version:    0.6.2
 */