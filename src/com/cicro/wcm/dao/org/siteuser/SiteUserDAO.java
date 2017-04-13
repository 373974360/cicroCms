/*    */ package com.cicro.wcm.dao.org.siteuser;
/*    */ 
/*    */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*    */ import com.cicro.wcm.bean.org.siteuser.SiteUserBean;
/*    */ import com.cicro.wcm.dao.PublicTableDAO;
/*    */ import com.cicro.wcm.db.DBManager;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class SiteUserDAO
/*    */ {
/*    */   private static final String INSERT_OPERATE = "添加";
/*    */   private static final String UPDATE_OPERATE = "修改";
/*    */   private static final String DELETE_OPERATE = "删除";
/*    */   private static final String TABLE_NAME = "站点用户表";
/*    */ 
/*    */   public static List<SiteUserBean> getAllSiteUserList()
/*    */   {
/* 36 */     return DBManager.queryFList("getAllSiteUserList", "");
/*    */   }
/*    */ 
/*    */   public static boolean insertSiteUser(SiteUserBean sub, SettingLogsBean stl)
/*    */   {
/* 47 */     if (DBManager.insert("insertSiteUser", sub))
/*    */     {
/* 49 */       PublicTableDAO.insertSettingLogs("添加", "站点用户表", sub.getUser_id(), stl);
/* 50 */       return true;
/*    */     }
/*    */ 
/* 54 */     return false;
/*    */   }
/*    */ 
/*    */   public static boolean deleteSiteUser(Map<String, String> map, SettingLogsBean stl)
/*    */   {
/* 66 */     if (DBManager.delete("deleteSiteUser", map))
/*    */     {
/* 68 */       PublicTableDAO.insertSettingLogs("删除", "站点用户表", (String)map.get("site_id"), stl);
/* 69 */       return true;
/*    */     }
/*    */ 
/* 73 */     return false;
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.dao.org.siteuser.SiteUserDAO
 * JD-Core Version:    0.6.2
 */