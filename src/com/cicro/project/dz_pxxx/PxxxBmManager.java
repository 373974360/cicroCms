/*    */ package com.cicro.project.dz_pxxx;
/*    */ 
/*    */ import com.cicro.util.FormatUtil;
/*    */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*    */ import com.cicro.wcm.dao.PublicTableDAO;

/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class PxxxBmManager
/*    */ {
/*    */   public static String getPxxxBmCount(Map<String, String> m)
/*    */   {
/* 15 */     if (m.containsKey("key_word"))
/*    */     {
/* 17 */       if (!FormatUtil.isValiditySQL((String)m.get("key_word")))
/* 18 */         return "0";
/*    */     }
/* 20 */     return PxxxBmDAO.getPxxxBmCount(m);
/*    */   }
/*    */ 
/*    */   public static List<PxxxBmBean> getPxxxBmList(Map<String, String> m) {
			 
/* 24 */     if (m.containsKey("key_word"))
/*    */     {
/* 26 */       if (!FormatUtil.isValiditySQL((String)m.get("key_word")))
/* 27 */         return new ArrayList();
/*    */     }
/* 29 */     return PxxxBmDAO.getPxxxBmList(m);
/*    */   }
/*    */ 
/*    */   public static List<PxxxBmBean> getAllPxxxBmList()
/*    */   {
/* 34 */     return PxxxBmDAO.getAllPxxxBmList();
/*    */   }
		   public static List<PxxxBmBean> getAllPxxxBmByPxID(Map<String, String> m)
		   {
			   return PxxxBmDAO.getAllPxxxBmByPxID(m);
		   }
/*    */ 
/*    */   public static PxxxBmBean getPxxxBmBean(String id)
/*    */   {
/* 39 */     return PxxxBmDAO.getPxxxBmBean(id);
/*    */   }
/*    */ 
/*    */   public static boolean insertPxxxBm(PxxxBmBean hb, SettingLogsBean stl)
/*    */   {
/* 44 */     hb.setId(PublicTableDAO.getIDByTableName("dz_pxxx"));
/* 45 */     return PxxxBmDAO.insertPxxxBm(hb, stl);
/*    */   }
/*    */ 
/*    */   public static boolean updatePxxxBm(PxxxBmBean hb, SettingLogsBean stl)
/*    */   {
/* 56 */     return PxxxBmDAO.updatePxxxBm(hb, stl);
/*    */   }
/*    */ 
/*    */   public static boolean deletePxxxBm(Map<String, String> m, SettingLogsBean stl)
/*    */   {
/* 70 */     return PxxxBmDAO.deletePxxxBm(m, stl);
/*    */   }
/*    */ }

/* Location:           E:\Xshell\219.144.222.46(省水保)\classes\com.zip
 * Qualified Name:     com.cicro.project.sb_xmgl.Sb_xmglManager
 * JD-Core Version:    0.6.2
 */