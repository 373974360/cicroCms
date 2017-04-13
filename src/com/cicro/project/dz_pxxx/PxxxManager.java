/*    */ package com.cicro.project.dz_pxxx;
/*    */ 
/*    */ import com.cicro.util.FormatUtil;
/*    */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*    */ import com.cicro.wcm.dao.PublicTableDAO;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class PxxxManager
/*    */ {
/*    */   public static String getPxxxCount(Map<String, String> m)
/*    */   {
/* 15 */     if (m.containsKey("key_word"))
/*    */     {
/* 17 */       if (!FormatUtil.isValiditySQL((String)m.get("key_word")))
/* 18 */         return "0";
/*    */     }
/* 20 */     return PxxxDAO.getPxxxCount(m);
/*    */   }
/*    */ 
/*    */   public static List<PxxxBean> getPxxxList(Map<String, String> m) {
/* 24 */     if (m.containsKey("key_word"))
/*    */     {
/* 26 */       if (!FormatUtil.isValiditySQL((String)m.get("key_word")))
/* 27 */         return new ArrayList();
/*    */     }
/* 29 */     return PxxxDAO.getPxxxList(m);
/*    */   }
/*    */ 
/*    */   public static List<PxxxBean> getAllPxxxList()
/*    */   {
/* 34 */     return PxxxDAO.getAllPxxxList();
/*    */   }
/*    */ 
/*    */   public static PxxxBean getPxxxBean(String id)
/*    */   {
/* 39 */     return PxxxDAO.getPxxxBean(id);
/*    */   }
/*    */ 
/*    */   public static boolean insertPxxx(PxxxBean hb, SettingLogsBean stl)
/*    */   {
/* 44 */     hb.setId(PublicTableDAO.getIDByTableName("dz_pxxx"));
/* 45 */     return PxxxDAO.insertPxxx(hb, stl);
/*    */   }
/*    */ 
/*    */   public static boolean insertSb_Pxxx(PxxxBean hb)
/*    */   {
/* 50 */     hb.setId(PublicTableDAO.getIDByTableName("dz_pxxx"));
/* 51 */     return PxxxDAO.insertPxxx(hb);
/*    */   }
/*    */ 
/*    */   public static boolean updatePxxx(PxxxBean hb, SettingLogsBean stl)
/*    */   {
/* 56 */     return PxxxDAO.updatePxxx(hb, stl);
/*    */   }
/*    */ 
/*    */   public static boolean deletePxxx(Map<String, String> m, SettingLogsBean stl)
/*    */   {
/* 70 */     return PxxxDAO.deletePxxx(m, stl);
/*    */   }
/*    */ }

/* Location:           E:\Xshell\219.144.222.46(省水保)\classes\com.zip
 * Qualified Name:     com.cicro.project.sb_xmgl.Sb_xmglManager
 * JD-Core Version:    0.6.2
 */