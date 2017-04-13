/*     */ package com.cicro.wcm.dao.appeal.lang;
/*     */ 
/*     */ import com.cicro.wcm.bean.appeal.lang.CommonLangBean;
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.dao.PublicTableDAO;
/*     */ import com.cicro.wcm.db.DBManager;
/*     */ import java.io.PrintStream;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class CommonLangDAO
/*     */ {
/*     */   public static List<CommonLangBean> getAllCommonLangList()
/*     */   {
/*  20 */     return DBManager.queryFList("getAllCommonList", "");
/*     */   }
/*     */ 
/*     */   public static List<CommonLangBean> getAllCommonLangListByDB(Map<String, String> mp)
/*     */   {
/*  29 */     return DBManager.queryFList("getAllCommonLangListByDB", mp);
/*     */   }
/*     */ 
/*     */   public static CommonLangBean getCommonLangByID(String id)
/*     */   {
/*  39 */     return (CommonLangBean)DBManager.queryFObj("getCommonLangByID", id);
/*     */   }
/*     */ 
/*     */   public static List<CommonLangBean> getCommonLangList(Map<String, String> mp)
/*     */   {
/*  49 */     return DBManager.queryFList("getCommonLangList", mp);
/*     */   }
/*     */ 
/*     */   public static String getCommonLangCnt(Map<String, String> mp)
/*     */   {
/*  59 */     return (String)DBManager.queryFObj("getCommonLangListCnt", mp);
/*     */   }
/*     */ 
/*     */   public static boolean insertCommonLang(CommonLangBean clb, SettingLogsBean stl)
/*     */   {
/*  70 */     String id = PublicTableDAO.getIDByTableName(PublicTableDAO.APPEAL_PHRASAL_TABLE_NAME);
/*  71 */     clb.setPh_id(id);
/*  72 */     if (DBManager.insert("insertCommonLang", clb))
/*     */     {
/*  74 */       PublicTableDAO.insertSettingLogs("添加", "常用语", id, stl);
/*  75 */       return true;
/*     */     }
/*     */ 
/*  79 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateCommonLang(CommonLangBean clb, SettingLogsBean stl)
/*     */   {
/*  91 */     if (DBManager.update("updateCommonLang", clb))
/*     */     {
/*  93 */       PublicTableDAO.insertSettingLogs("修改", "常用语", clb.getPh_id(), stl);
/*  94 */       return true;
/*     */     }
/*     */ 
/*  98 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateCommonLangSort(CommonLangBean clb, SettingLogsBean stl)
/*     */   {
/* 110 */     if (DBManager.update("updateCommonLangSort", clb))
/*     */     {
/* 112 */       PublicTableDAO.insertSettingLogs("修改", "常用语", clb.getPh_id(), stl);
/* 113 */       return true;
/*     */     }
/*     */ 
/* 117 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteCommonLang(Map<String, String> mp, SettingLogsBean stl)
/*     */   {
/* 123 */     if (DBManager.delete("deleteCommonLang", mp))
/*     */     {
/* 125 */       PublicTableDAO.insertSettingLogs("删除", "常用语", (String)mp.get("ph_id"), stl);
/* 126 */       return true;
/*     */     }
/*     */ 
/* 130 */     return false;
/*     */   }
/*     */ 
/*     */   public static void main(String[] arg)
/*     */   {
/* 136 */     Map mp = new HashMap();
/* 137 */     mp.put("ph_id", "1");
/* 138 */     mp.put("ph_type", "2");
/* 139 */     mp.put("sort_id", "888");
/*     */ 
/* 142 */     System.out.println("111");
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.dao.appeal.lang.CommonLangDAO
 * JD-Core Version:    0.6.2
 */