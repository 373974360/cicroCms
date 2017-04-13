/*    */ package com.cicro.project.dz_ddmf;
/*    */ 
/*    */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*    */ import com.cicro.wcm.dao.PublicTableDAO;
/*    */ import com.cicro.wcm.db.DBManager;
/*    */ import java.util.HashMap;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class DdmfDAO
/*    */ {
/*    */   public static String getDdmfCount(Map<String, String> m)
/*    */   {
/* 14 */     return DBManager.getString("getDdmfCount", m);
/*    */   }
/*    */ 
/*    */   public static List<DdmfBean> getDdmfList(Map<String, String> m)
/*    */   {
/* 19 */     return DBManager.queryFList("getDdmfList", m);
/*    */   }
/*    */ 
/*    */   public static DdmfBean getDdmfBean(String id, boolean is_browser)
/*    */   {
/* 24 */     Map m = new HashMap();
/* 25 */     m.put("id", id);
/* 28 */     return (DdmfBean)DBManager.queryFObj("getDdmfBean", m);
/*    */   }
/*    */ 
/*    */   public static List<DdmfBean> getAllDdmfList()
/*    */   {
/* 33 */     return DBManager.queryFList("getAllDdmfList", "");
/*    */   }
/*    */ 
/*    */   public static boolean insertDdmf(DdmfBean Ddmf, SettingLogsBean stl)
/*    */   {
/* 39 */     if (DBManager.insert("insertDdmf", Ddmf))
/*    */     {
/* 41 */       PublicTableDAO.insertSettingLogs("添加", "道德模范", Ddmf.getId()+"", stl);
/* 42 */       return true;
/*    */     }
/* 44 */     return false;
/*    */   }
/*    */ 
/*    */   public static boolean insertDdmf(DdmfBean Ddmf)
/*    */   {
/* 49 */     return DBManager.insert("insertDdmf", Ddmf);
/*    */   }
/*    */ 
/*    */   public static boolean updateDdmf(DdmfBean Ddmf, SettingLogsBean stl)
/*    */   {
/* 55 */     if (DBManager.update("updateDdmf", Ddmf))
/*    */     {
/* 57 */       PublicTableDAO.insertSettingLogs("修改", "道德模范", Ddmf.getId()+"", stl);
/* 58 */       return true;
/*    */     }
/* 60 */     return false;
/*    */   }
/*    */ 
/*    */   public static boolean deleteDdmf(Map<String, String> m, SettingLogsBean stl)
/*    */   {
/* 75 */     if (DBManager.delete("deleteDdmf", m))
/*    */     {
/* 77 */       PublicTableDAO.insertSettingLogs("删除", "道德模范", (String)m.get("ids"), stl);
/* 78 */       return true;
/*    */     }
/* 80 */     return false;
/*    */   }
/*    */ }

/* Location:           E:\Xshell\219.144.222.46(省水保)\classes\com.zip
 * Qualified Name:     com.cicro.project.sb_xmgl.Sb_xmglDAO
 * JD-Core Version:    0.6.2
 */