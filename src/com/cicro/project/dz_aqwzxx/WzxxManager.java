/*    */ package com.cicro.project.dz_aqwzxx;
/*    */ 
/*    */ import com.cicro.util.DateUtil;
/*    */ import com.cicro.util.FormatUtil;
/*    */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*    */ import com.cicro.wcm.dao.PublicTableDAO;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class WzxxManager
/*    */ {
/*    */   public static String getWzxxCount(Map<String, String> m)
/*    */   {
/* 15 */     if (m.containsKey("key_word"))
/*    */     {
/* 17 */       if (!FormatUtil.isValiditySQL((String)m.get("key_word")))
/* 18 */         return "0";
/*    */     }
/* 20 */     return WzxxDAO.getWzxxCount(m);
/*    */   }
/*    */ 
/*    */   public static List<WzxxBean> getWzxxList(Map<String, String> m) {
/* 24 */     if (m.containsKey("key_word"))
/*    */     {
/* 26 */       if (!FormatUtil.isValiditySQL((String)m.get("key_word")))
/* 27 */         return new ArrayList();
/*    */     }
/* 29 */     return WzxxDAO.getWzxxList(m);
/*    */   }
/*    */ 
/*    */   public static List<WzxxBean> getAllWzxxList()
/*    */   {
/* 34 */     return WzxxDAO.getAllWzxxList();
/*    */   }
/*    */ 
/*    */   public static WzxxBean getWzxxBean(String id, boolean is_browser)
/*    */   {
/* 39 */     return WzxxDAO.getWzxxBean(id, is_browser);
/*    */   }
/*    */ 
/*    */   public static boolean insertWzxx(WzxxBean hb, SettingLogsBean stl)
/*    */   {
/* 44 */     hb.setId(PublicTableDAO.getIDByTableName("dz_aqwzxx"));
/* 45 */     return WzxxDAO.insertWzxx(hb, stl);
/*    */   }
/*    */ 
/*    */   public static boolean updateWzxx(WzxxBean hb, SettingLogsBean stl)
/*    */   {
/* 56 */     return WzxxDAO.updateWzxx(hb, stl);
/*    */   }
/*    */ 
/*    */   public static boolean deleteWzxx(Map<String, String> m, SettingLogsBean stl)
/*    */   {
/* 70 */     return WzxxDAO.deleteWzxx(m, stl);

/*    */   }
/*    */ }

/* Location:           E:\Xshell\219.144.222.46(省水保)\classes\com.zip
 * Qualified Name:     com.cicro.project.sb_xmgl.Sb_xmglManager
 * JD-Core Version:    0.6.2
 */