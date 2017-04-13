/*     */ package com.cicro.wcm.dao.system.template;
/*     */ 
/*     */ import com.cicro.util.DateUtil;
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.bean.system.template.TemplateEditBean;
/*     */ import com.cicro.wcm.dao.PublicTableDAO;
/*     */ import com.cicro.wcm.db.DBManager;
/*     */ import java.io.PrintStream;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class TemplateEditDAO
/*     */ {
/*     */   public static List<TemplateEditBean> getAllTemplateEditList()
/*     */   {
/*  26 */     return DBManager.queryFList("getAllTemplateEditList", "");
/*     */   }
/*     */ 
/*     */   public static List<TemplateEditBean> getAllTemplateEditList(String tcat_id, String site_id, String app_id)
/*     */   {
/*  36 */     Map map = new HashMap();
/*  37 */     map.put("site_id", site_id);
/*     */ 
/*  39 */     map.put("tcat_id", tcat_id);
/*  40 */     return DBManager.queryFList("getAllTemplateEditList", map);
/*     */   }
/*     */ 
/*     */   public static TemplateEditBean getTemplateEditBean(int t_id, String site_id, String app_id)
/*     */   {
/*  50 */     Map map = new HashMap();
/*  51 */     map.put("t_id", t_id);
/*  52 */     map.put("site_id", site_id);
/*     */ 
/*  54 */     return (TemplateEditBean)DBManager.queryFObj("getTemplateEditBean", map);
/*     */   }
/*     */ 
/*     */   public static String getTemplateEditCount(Map<String, String> con_map)
/*     */   {
/*  64 */     System.out.println("getTemplateEditCount*****************************************" + con_map);
/*  65 */     return DBManager.getString("getTemplateEditCount", con_map);
/*     */   }
/*     */ 
/*     */   public static List<TemplateEditBean> getTemplateEditListForDB(Map<String, String> con_map)
/*     */   {
/*  76 */     System.out.println("getTemplateEditListForDB*****************************************" + con_map);
/*  77 */     return DBManager.queryFList("getTemplateEditListForDB", con_map);
/*     */   }
/*     */ 
/*     */   public static boolean updateTemplateEdit(TemplateEditBean tb, SettingLogsBean stl)
/*     */   {
/*  87 */     if (DBManager.update("updateTemplateEdit", tb))
/*     */     {
/*  89 */       PublicTableDAO.insertSettingLogs("修改", "模板主体", tb.getT_id(), stl);
/*  90 */       return true;
/*     */     }
/*  92 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean addTemplateEdit(TemplateEditBean tb, SettingLogsBean stl)
/*     */   {
/* 101 */     if (DBManager.insert("insertTemplateEdit", tb))
/*     */     {
/* 103 */       PublicTableDAO.insertSettingLogs("添加", "模板主体", tb.getId(), stl);
/* 104 */       return true;
/*     */     }
/* 106 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean cloneTemplateEdit(TemplateEditBean tb)
/*     */   {
/* 115 */     tb.setId(PublicTableDAO.getIDByTableName(PublicTableDAO.TEMPLATE_EDIT_TABLE_NAME));
/* 116 */     return DBManager.insert("insertTemplateEdit", tb);
/*     */   }
/*     */ 
/*     */   public static boolean delTemplateEdit(String t_ids, String site_id, SettingLogsBean stl)
/*     */   {
/* 126 */     Map map = new HashMap();
/* 127 */     map.put("t_ids", t_ids);
/* 128 */     map.put("site_id", site_id);
/* 129 */     if (DBManager.delete("deleteTemplateEdit", map))
/*     */     {
/* 131 */       PublicTableDAO.insertSettingLogs("删除", "模板主体", t_ids, stl);
/* 132 */       return true;
/*     */     }
/* 134 */     return false;
/*     */   }
/*     */ 
/*     */   public static void main(String[] args) {
/* 138 */     TemplateEditBean teb = new TemplateEditBean();
/* 139 */     teb.setApp_id("cms");
/* 140 */     teb.setCreat_dtime(DateUtil.getCurrentDateTime());
/* 141 */     teb.setCreat_user(1);
/* 142 */     teb.setModify_dtime(DateUtil.getCurrentDateTime());
/* 143 */     teb.setModify_user(1);
/* 144 */     teb.setSite_id("test");
/* 145 */     teb.setT_cname("aaaaaaaaaaaaaaaaaa");
/* 146 */     teb.setT_content("aaaaaaaaaaaaaaaaaa");
/* 147 */     teb.setT_ename("aaaaaaaaaaaaaaa");
/* 148 */     teb.setT_id(2);
/* 149 */     teb.setT_path("aaaaaaaaaaaaaaaaa");
/* 150 */     teb.setT_ver(0);
/* 151 */     teb.setTcat_id(7);
/*     */ 
/* 153 */     teb.setId(9);
/* 154 */     DBManager.insert("insertTemplateEdit", teb);
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.dao.system.template.TemplateEditDAO
 * JD-Core Version:    0.6.2
 */