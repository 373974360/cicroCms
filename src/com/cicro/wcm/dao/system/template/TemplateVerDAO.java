/*     */ package com.cicro.wcm.dao.system.template;
/*     */ 
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.bean.system.template.TemplateVerBean;
/*     */ import com.cicro.wcm.dao.PublicTableDAO;
/*     */ import com.cicro.wcm.db.DBManager;
/*     */ import java.io.PrintStream;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class TemplateVerDAO
/*     */ {
/*     */   public static List<TemplateVerBean> getAllTemplateVerList()
/*     */   {
/*  33 */     return DBManager.queryFList("getAllTemplateVerList", "");
/*     */   }
/*     */ 
/*     */   public static TemplateVerBean getTemplateVerBean(int t_id, String site_id, String ver)
/*     */   {
/*  43 */     Map map = new HashMap();
/*  44 */     map.put("t_id", t_id);
/*  45 */     map.put("site_id", site_id);
/*  46 */     map.put("t_ver", ver);
/*  47 */     return (TemplateVerBean)DBManager.queryFObj("getTemplateVerBean", map);
/*     */   }
/*     */ 
/*     */   public static String getTemplateVerCount(Map<String, String> con_map)
/*     */   {
/*  57 */     return DBManager.getString("getTemplateVerCount", con_map);
/*     */   }
/*     */ 
/*     */   public static List<TemplateVerBean> getTemplateVerListForDB(Map<String, String> con_map)
/*     */   {
/*  68 */     return DBManager.queryFList("getTemplateVerListForDB", con_map);
/*     */   }
/*     */ 
/*     */   public static boolean updateTemplateVer(TemplateVerBean tb, SettingLogsBean stl)
/*     */   {
/*  78 */     if (DBManager.update("updateTemplateVer", tb))
/*     */     {
/*  80 */       PublicTableDAO.insertSettingLogs("修改", "备份模板", tb.getT_id(), stl);
/*  81 */       return true;
/*     */     }
/*  83 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean addTemplateVer(TemplateVerBean tb, SettingLogsBean stl)
/*     */   {
/*  92 */     int id = PublicTableDAO.getIDByTableName(PublicTableDAO.TEMPLATE_VER_TABLE_NAME);
/*  93 */     tb.setId(id);
/*  94 */     if (DBManager.insert("insertTemplateVer", tb))
/*     */     {
/*  96 */       PublicTableDAO.insertSettingLogs("添加", "备份模板", tb.getT_id(), stl);
/*  97 */       return true;
/*     */     }
/*  99 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean cloneTemplateVer(TemplateVerBean tb)
/*     */   {
/* 108 */     tb.setId(PublicTableDAO.getIDByTableName(PublicTableDAO.TEMPLATE_VER_TABLE_NAME));
/* 109 */     return DBManager.insert("insertTemplateVer", tb);
/*     */   }
/*     */ 
/*     */   public static boolean delTemplateVer(String t_ids, String site_id, SettingLogsBean stl)
/*     */   {
/* 119 */     Map map = new HashMap();
/* 120 */     map.put("t_ids", t_ids);
/* 121 */     map.put("site_id", site_id);
/* 122 */     if (DBManager.delete("deleteTemplateVer", map))
/*     */     {
/* 124 */       PublicTableDAO.insertSettingLogs("删除", "备份模板", t_ids, stl);
/* 125 */       return true;
/*     */     }
/* 127 */     return false;
/*     */   }
/*     */ 
/*     */   public static String getTemplateVerNum(int t_id, String site_id) {
/* 131 */     Map map = new HashMap();
/* 132 */     map.put("t_id", t_id);
/* 133 */     map.put("site_id", site_id);
/* 134 */     return DBManager.getString("getNewTemplateVerNum", map);
/*     */   }
/*     */ 
/*     */   public static void main(String[] args) {
/* 138 */     TemplateVerBean teb = new TemplateVerBean();
/* 139 */     teb.setApp_id("aaaaaaaaaaaaaaa");
/* 140 */     teb.setCreat_dtime("aaaaaaaaaaaaaaaa");
/* 141 */     teb.setCreat_user(1);
/* 142 */     teb.setModify_dtime("aaaaaaaaaaaaaaaaa");
/* 143 */     teb.setModify_user(1);
/* 144 */     teb.setSite_id("gx");
/* 145 */     teb.setT_cname("cccccccccccccccccccccc");
/* 146 */     teb.setT_content("aaaaaaaaaaaaaaaaaa");
/* 147 */     teb.setT_ename("aaaaaaaaaaaaaaa");
/* 148 */     teb.setT_id(1);
/* 149 */     teb.setT_path("aaaaaaaaaaaaaaaaa");
/* 150 */     teb.setT_ver(1);
/* 151 */     teb.setTcat_id(1);
/*     */ 
/* 161 */     System.out.println(">>=" + getTemplateVerNum(2, "0"));
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.dao.system.template.TemplateVerDAO
 * JD-Core Version:    0.6.2
 */