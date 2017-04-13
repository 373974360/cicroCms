/*    */ package com.cicro.wcm.dao.template.snippet;
/*    */ 
/*    */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*    */ import com.cicro.wcm.bean.template.snippet.SnippetBean;
/*    */ import com.cicro.wcm.dao.PublicTableDAO;
/*    */ import com.cicro.wcm.db.DBManager;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class SnippetDAO
/*    */ {
/*    */   public static List<SnippetBean> getAllSnippetList()
/*    */   {
/* 32 */     return DBManager.queryFList("getAllSnippet", "");
/*    */   }
/*    */ 
/*    */   public static SnippetBean getSnippetBean(int sni_id)
/*    */   {
/* 42 */     return (SnippetBean)DBManager.queryFObj("getSnippetBean", Integer.valueOf(sni_id));
/*    */   }
/*    */ 
/*    */   public static String getSnippetCount()
/*    */   {
/* 47 */     return DBManager.getString("getSnippetCount", "");
/*    */   }
/*    */ 
/*    */   public static boolean insertSnippet(SnippetBean bean, SettingLogsBean stl)
/*    */   {
/* 57 */     if (DBManager.insert("insert_Snippet", bean)) {
/* 58 */       PublicTableDAO.insertSettingLogs("添加", "代码片断", bean.getSni_id(), stl);
/* 59 */       return true;
/*    */     }
/* 61 */     return false;
/*    */   }
/*    */ 
/*    */   public static boolean updateSnippet(SnippetBean bean, SettingLogsBean stl)
/*    */   {
/* 72 */     if (DBManager.update("update_Snippet", bean)) {
/* 73 */       PublicTableDAO.insertSettingLogs("修改", "代码片断", bean.getSni_id(), stl);
/* 74 */       return true;
/*    */     }
/* 76 */     return false;
/*    */   }
/*    */ 
/*    */   public static boolean deleteSnippet(Map<String, String> mp, SettingLogsBean stl)
/*    */   {
/* 88 */     if (DBManager.delete("delete_Snippet_ids", mp)) {
/* 89 */       PublicTableDAO.insertSettingLogs("删除", "代码片断", (String)mp.get("sni_id"), stl);
/* 90 */       return true;
/*    */     }
/* 92 */     return false;
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.dao.template.snippet.SnippetDAO
 * JD-Core Version:    0.6.2
 */