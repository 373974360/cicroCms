/*    */ package com.cicro.project.dz_pxxx;
/*    */ 
/*    */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*    */ import com.cicro.wcm.dao.PublicTableDAO;
/*    */ import com.cicro.wcm.db.DBManager;
/*    */ import java.util.HashMap;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class PxxxDAO
/*    */ {
/*    */   public static String getPxxxCount(Map<String, String> m)
/*    */   {
/* 14 */     return DBManager.getString("getPxxxCount", m);
/*    */   }
/*    */ 
/*    */   public static List<PxxxBean> getPxxxList(Map<String, String> m)
/*    */   {
/* 19 */     return DBManager.queryFList("getPxxxList", m);
/*    */   }
/*    */ 
/*    */   public static PxxxBean getPxxxBean(String id)
/*    */   {
/* 24 */     Map m = new HashMap();
/* 25 */     m.put("id", id);
/* 28 */     return (PxxxBean)DBManager.queryFObj("getPxxxBean", m);
/*    */   }
/*    */ 
/*    */   public static List<PxxxBean> getAllPxxxList()
/*    */   {
/* 33 */     return DBManager.queryFList("getAllPxxxList", "");
/*    */   }
/*    */ 
/*    */   public static boolean insertPxxx(PxxxBean xmk, SettingLogsBean stl)
/*    */   {
/* 39 */     if (DBManager.insert("insertPxxx", xmk))
/*    */     {
/* 41 */       PublicTableDAO.insertSettingLogs("添加", "培训信息", xmk.getId()+"", stl);
/* 42 */       return true;
/*    */     }
/* 44 */     return false;
/*    */   }
/*    */ 
/*    */   public static boolean insertPxxx(PxxxBean xmk)
/*    */   {
/* 49 */     return DBManager.insert("insertPxxx", xmk);
/*    */   }
/*    */ 
/*    */   public static boolean updatePxxx(PxxxBean xmk, SettingLogsBean stl)
/*    */   {
/* 55 */     if (DBManager.update("updatePxxx", xmk))
/*    */     {
/* 57 */       PublicTableDAO.insertSettingLogs("修改", "培训信息", xmk.getId()+"", stl);
/* 58 */       return true;
/*    */     }
/* 60 */     return false;
/*    */   }
/*    */ 
/*    */   public static boolean publishPxxx(Map<String, String> m, SettingLogsBean stl)
/*    */   {
/* 65 */     if (DBManager.update("publishPxxx", m))
/*    */     {
/* 67 */       PublicTableDAO.insertSettingLogs("发布设置", "培训信息", (String)m.get("ids"), stl);
/* 68 */       return true;
/*    */     }
/* 70 */     return false;
/*    */   }
/*    */ 
/*    */   public static boolean deletePxxx(Map<String, String> m, SettingLogsBean stl)
/*    */   {
/* 75 */     if (DBManager.delete("deletePxxx", m))
/*    */     {
/* 77 */       PublicTableDAO.insertSettingLogs("删除", "培训信息", (String)m.get("ids"), stl);
/* 78 */       return true;
/*    */     }
/* 80 */     return false;
/*    */   }
/*    */ }

/* Location:           E:\Xshell\219.144.222.46(省水保)\classes\com.zip
 * Qualified Name:     com.cicro.project.sb_xmgl.Sb_xmglDAO
 * JD-Core Version:    0.6.2
 */