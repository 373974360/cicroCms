/*     */ package com.cicro.wcm.dao.system.template;
/*     */ 
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.bean.system.template.TemplateClassBean;
/*     */ import com.cicro.wcm.dao.PublicTableDAO;
/*     */ import com.cicro.wcm.db.DBManager;
/*     */ import java.io.PrintStream;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class TemplateClassDAO
/*     */ {
/*     */   public static List<TemplateClassBean> getAllTemplateClassList()
/*     */   {
/*  32 */     return DBManager.queryFList("getAllTemplateClassList", "");
/*     */   }
/*     */ 
/*     */   public static TemplateClassBean getTemplateClassBean(int tclass_id)
/*     */   {
/*  42 */     return (TemplateClassBean)DBManager.queryFObj("getTemplateClassBean", Integer.valueOf(tclass_id));
/*     */   }
/*     */ 
/*     */   public static String getTemplateClassCount(Map<String, String> con_map)
/*     */   {
/*  52 */     return DBManager.getString("getTemplateClassCount", con_map);
/*     */   }
/*     */ 
/*     */   public static List<TemplateClassBean> getTemplateClassListForDB(Map<String, String> con_map)
/*     */   {
/*  63 */     return DBManager.queryFList("getTemplateClassListForDB", con_map);
/*     */   }
/*     */ 
/*     */   public static boolean updateTemplateClass(TemplateClassBean tcb, SettingLogsBean stl)
/*     */   {
/*  73 */     if (DBManager.update("updateTemplateClass", tcb))
/*     */     {
/*  75 */       PublicTableDAO.insertSettingLogs("修改", "模板目录", tcb.getTclass_id(), stl);
/*  76 */       return true;
/*     */     }
/*  78 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean addTemplateClass(TemplateClassBean tb, SettingLogsBean stl)
/*     */   {
/*  87 */     int tclassId = PublicTableDAO.getIDByTableName(PublicTableDAO.TEMPLATE_CLASS_TABLE_NAME);
/*  88 */     tb.setTclass_id(tclassId);
/*  89 */     if (DBManager.insert("insertTemplateClass", tb))
/*     */     {
/*  91 */       PublicTableDAO.insertSettingLogs("添加", "模板目录", tclassId, stl);
/*  92 */       return true;
/*     */     }
/*  94 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean delTemplateClass(String tclass_ids, SettingLogsBean stl)
/*     */   {
/* 104 */     Map map = new HashMap();
/* 105 */     map.put("tclass_ids", tclass_ids);
/* 106 */     if (DBManager.delete("deleteTemplateClass", map))
/*     */     {
/* 108 */       PublicTableDAO.insertSettingLogs("删除", "模板目录", tclass_ids, stl);
/* 109 */       return true;
/*     */     }
/* 111 */     return false;
/*     */   }
/*     */ 
/*     */   public static void main(String[] args) {
/* 115 */     TemplateClassBean tcb = new TemplateClassBean();
/* 116 */     tcb.setApp_id("11");
/* 117 */     tcb.setTclass_cname("bbbbbbbbbbbbbb");
/* 118 */     tcb.setTclass_ename("bbbbbbbbbbbbbb");
/* 119 */     tcb.setTclass_id(1);
/* 120 */     tcb.setTclass_memo("bbbbbbbbbbbbbbbbbbbbbbbb");
/*     */ 
/* 124 */     Map map = new HashMap();
/* 125 */     map.put("tclass_ids", "1");
/* 126 */     if (DBManager.delete("deleteTemplateClass", map))
/* 127 */       System.out.println(getAllTemplateClassList());
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.dao.system.template.TemplateClassDAO
 * JD-Core Version:    0.6.2
 */