/*    */ package com.cicro.project.dz_pxxx;
/*    */ 
/*    */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*    */ import com.cicro.wcm.dao.PublicTableDAO;
/*    */ import com.cicro.wcm.db.DBManager;

/*    */ import java.util.HashMap;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class PxxxBmDAO
/*    */ {
/*    */   public static String getPxxxBmCount(Map<String, String> m)
/*    */   {
/* 14 */     return DBManager.getString("getPxxxBmCount", m);
/*    */   }
/*    */ 
/*    */   public static List<PxxxBmBean> getPxxxBmList(Map<String, String> m)
/*    */   {
/* 19 */     return DBManager.queryFList("getPxxxBmList", m);
/*    */   }
/*    */ 
/*    */   public static PxxxBmBean getPxxxBmBean(String id)
/*    */   {
/* 24 */     Map m = new HashMap();
/* 25 */     m.put("id", id);
/* 28 */     return (PxxxBmBean)DBManager.queryFObj("getPxxxBmBean", m);
/*    */   }
/*    */ 
/*    */   public static List<PxxxBmBean> getAllPxxxBmList()
/*    */   {
/* 33 */     return DBManager.queryFList("getAllPxxxBmList", "");
/*    */   }

		   public static List<PxxxBmBean> getAllPxxxBmByPxID(Map<String, String> m)
		   {
			   return DBManager.queryFList("getAllPxxxBmByPxID", m);
		   }
/*    */ 
/*    */   public static boolean insertPxxxBm(PxxxBmBean xmk, SettingLogsBean stl)
/*    */   {
/* 39 */     if (DBManager.insert("insertPxxxBm", xmk))
/*    */     {
/* 41 */       PublicTableDAO.insertSettingLogs("添加", "培训报名信息", xmk.getId()+"", stl);
/* 42 */       return true;
/*    */     }
/* 44 */     return false;
/*    */   }
/*    */ 
/*    */   public static boolean insertPxxxBm(PxxxBmBean xmk)
/*    */   {
/* 49 */     return DBManager.insert("insertPxxxBm", xmk);
/*    */   }
/*    */ 
/*    */   public static boolean updatePxxxBm(PxxxBmBean xmk, SettingLogsBean stl)
/*    */   {
/* 55 */     if (DBManager.update("updatePxxx", xmk))
/*    */     {
/* 57 */       PublicTableDAO.insertSettingLogs("修改", "培训报名信息", xmk.getId()+"", stl);
/* 58 */       return true;
/*    */     }
/* 60 */     return false;
/*    */   }
/*    */ 
/*    */   public static boolean publishPxxxBm(Map<String, String> m, SettingLogsBean stl)
/*    */   {
/* 65 */     if (DBManager.update("publishPxxxBm", m))
/*    */     {
/* 67 */       PublicTableDAO.insertSettingLogs("发布设置", "培训报名信息", (String)m.get("ids"), stl);
/* 68 */       return true;
/*    */     }
/* 70 */     return false;
/*    */   }
/*    */ 
/*    */   public static boolean deletePxxxBm(Map<String, String> m, SettingLogsBean stl)
/*    */   {
/* 75 */     if (DBManager.delete("deletePxxxBm", m))
/*    */     {
/* 77 */       PublicTableDAO.insertSettingLogs("删除", "培训报名信息", (String)m.get("ids"), stl);
/* 78 */       return true;
/*    */     }
/* 80 */     return false;
/*    */   }
/*    */ }

/* Location:           E:\Xshell\219.144.222.46(省水保)\classes\com.zip
 * Qualified Name:     com.cicro.project.sb_xmgl.Sb_xmglDAO
 * JD-Core Version:    0.6.2
 */