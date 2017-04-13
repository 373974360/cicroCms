/*    */ package com.cicro.project.dz_pxxx;
/*    */ 
/*    */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*    */ import com.cicro.wcm.dao.PublicTableDAO;
/*    */ import com.cicro.wcm.db.DBManager;

/*    */ import java.util.HashMap;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class PxxxKcDAO
/*    */ {
/*    */   public static String getPxxxKcCount(Map<String, String> m)
/*    */   {
/* 14 */     return DBManager.getString("getPxxxKcCount", m);
/*    */   }
/*    */ 
/*    */   public static List<PxxxKcBean> getPxxxKcList(Map<String, String> m)
/*    */   {
/* 19 */     return DBManager.queryFList("getPxxxKcList", m);
/*    */   }
/*    */ 
/*    */   public static PxxxKcBean getPxxxKcBean(String id)
/*    */   {
/* 24 */     Map m = new HashMap();
/* 25 */     m.put("id", id);
/* 28 */     return (PxxxKcBean)DBManager.queryFObj("getPxxxKcBean", m);
/*    */   }
/*    */ 
/*    */   public static List<PxxxKcBean> getAllPxxxKcList()
/*    */   {
/* 33 */     return DBManager.queryFList("getAllPxxxKcList", "");
/*    */   }
		   public static List<PxxxKcBean> getAllPxxxKcByPxID(String pxid)
		   {
			   Map m = new HashMap();
   /* 25 */    m.put("pxid", pxid);
   /* 28 */     return DBManager.queryFList("getAllPxxxKcByPxID", m);
		   }
/*    */ 
/*    */   public static boolean insertPxxxKc(PxxxKcBean xmk, SettingLogsBean stl)
/*    */   {
/* 39 */     if (DBManager.insert("insertPxxxKc", xmk))
/*    */     {
/* 41 */       PublicTableDAO.insertSettingLogs("添加", "培训课程信息", xmk.getId()+"", stl);
/* 42 */       return true;
/*    */     }
/* 44 */     return false;
/*    */   }
/*    */ 
/*    */   public static boolean insertPxxxKc(PxxxKcBean xmk)
/*    */   {
/* 49 */     return DBManager.insert("insertPxxxKc", xmk);
/*    */   }
/*    */ 
/*    */   public static boolean updatePxxxKc(PxxxKcBean xmk, SettingLogsBean stl)
/*    */   {
/* 55 */     if (DBManager.update("updatePxxxKc", xmk))
/*    */     {
/* 57 */       PublicTableDAO.insertSettingLogs("修改", "培训课程信息", xmk.getId()+"", stl);
/* 58 */       return true;
/*    */     }
/* 60 */     return false;
/*    */   }
/*    */ 
/*    */   public static boolean publishPxxxKc(Map<String, String> m, SettingLogsBean stl)
/*    */   {
/* 65 */     if (DBManager.update("publishPxxxKc", m))
/*    */     {
/* 67 */       PublicTableDAO.insertSettingLogs("发布设置", "培训课程信息", (String)m.get("ids"), stl);
/* 68 */       return true;
/*    */     }
/* 70 */     return false;
/*    */   }
/*    */ 
/*    */   public static boolean deletePxxxKc(Map<String, String> m, SettingLogsBean stl)
/*    */   {
/* 75 */     if (DBManager.delete("deletePxxxKc", m))
/*    */     {
/* 77 */       PublicTableDAO.insertSettingLogs("删除", "培训课程信息", (String)m.get("ids"), stl);
/* 78 */       return true;
/*    */     }
/* 80 */     return false;
/*    */   }
/*    */ }

/* Location:           E:\Xshell\219.144.222.46(省水保)\classes\com.zip
 * Qualified Name:     com.cicro.project.sb_xmgl.Sb_xmglDAO
 * JD-Core Version:    0.6.2
 */