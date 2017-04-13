/*    */ package com.cicro.project.dz_kfda;
/*    */ 
/*    */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*    */ import com.cicro.wcm.dao.PublicTableDAO;
/*    */ import com.cicro.wcm.db.DBManager;
/*    */ import java.util.HashMap;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class KfdaDAO
/*    */ {
/*    */   public static String getKfdaCount(Map<String, String> m)
/*    */   {
/* 14 */     return DBManager.getString("getKfdaCount", m);
/*    */   }
/*    */ 
/*    */   public static List<KfdaBean> getKfdaList(Map<String, String> m)
/*    */   {
/* 19 */     return DBManager.queryFList("getKfdaList", m);
/*    */   }
/*    */ 
/*    */   public static KfdaBean getKfdaBean(String id)
/*    */   {
/* 24 */     Map m = new HashMap();
/* 25 */     m.put("id", id);
/* 28 */     return (KfdaBean)DBManager.queryFObj("getKfdaBean", m);
/*    */   }
/*    */ 
/*    */   public static List<KfdaBean> getAllKfdaList()
/*    */   {
/* 33 */     return DBManager.queryFList("getAllKfdaList", "");
/*    */   }
/*    */ 
/*    */   public static boolean insertKfda(KfdaBean xmk, SettingLogsBean stl)
/*    */   {
/* 39 */     if (DBManager.insert("insertKfda", xmk))
/*    */     {
/* 41 */       PublicTableDAO.insertSettingLogs("添加", "开放档案", xmk.getId()+"", stl);
/* 42 */       return true;
/*    */     }
/* 44 */     return false;
/*    */   }
/*    */ 
/*    */   public static boolean insertKfda(KfdaBean xmk)
/*    */   {
/* 49 */     return DBManager.insert("insertKfda", xmk);
/*    */   }
/*    */ 
/*    */   public static boolean updateKfda(KfdaBean xmk, SettingLogsBean stl)
/*    */   {
/* 55 */     if (DBManager.update("updateKfda", xmk))
/*    */     {
/* 57 */       PublicTableDAO.insertSettingLogs("修改", "开放档案", xmk.getId()+"", stl);
/* 58 */       return true;
/*    */     }
/* 60 */     return false;
/*    */   }
/*    */ 
/*    */   public static boolean publishKfda(Map<String, String> m, SettingLogsBean stl)
/*    */   {
/* 65 */     if (DBManager.update("publishKfda", m))
/*    */     {
/* 67 */       PublicTableDAO.insertSettingLogs("发布设置", "开放档案", (String)m.get("ids"), stl);
/* 68 */       return true;
/*    */     }
/* 70 */     return false;
/*    */   }
/*    */ 
/*    */   public static boolean deleteKfda(Map<String, String> m, SettingLogsBean stl)
/*    */   {
/* 75 */     if (DBManager.delete("deleteKfda", m))
/*    */     {
/* 77 */       PublicTableDAO.insertSettingLogs("删除", "开放档案", (String)m.get("ids"), stl);
/* 78 */       return true;
/*    */     }
/* 80 */     return false;
/*    */   }
/*    */ }

/* Location:           E:\Xshell\219.144.222.46(省水保)\classes\com.zip
 * Qualified Name:     com.cicro.project.sb_xmgl.Sb_xmglDAO
 * JD-Core Version:    0.6.2
 */