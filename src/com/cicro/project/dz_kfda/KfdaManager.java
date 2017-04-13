/*    */ package com.cicro.project.dz_kfda;
/*    */ 
/*    */ import com.cicro.util.DateUtil;
/*    */ import com.cicro.util.FormatUtil;
/*    */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*    */ import com.cicro.wcm.dao.PublicTableDAO;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class KfdaManager
/*    */ {
/*    */   public static String getKfdaCount(Map<String, String> m)
/*    */   {
/* 15 */     if (m.containsKey("key_word"))
/*    */     {
/* 17 */       if (!FormatUtil.isValiditySQL((String)m.get("key_word")))
/* 18 */         return "0";
/*    */     }
/* 20 */     return KfdaDAO.getKfdaCount(m);
/*    */   }
/*    */ 
/*    */   public static List<KfdaBean> getKfdaList(Map<String, String> m) {
/* 24 */     if (m.containsKey("key_word"))
/*    */     {
/* 26 */       if (!FormatUtil.isValiditySQL((String)m.get("key_word")))
/* 27 */         return new ArrayList();
/*    */     }
/* 29 */     return KfdaDAO.getKfdaList(m);
/*    */   }
/*    */ 
/*    */   public static List<KfdaBean> getAllKfdaList()
/*    */   {
/* 34 */     return KfdaDAO.getAllKfdaList();
/*    */   }
/*    */ 
/*    */   public static KfdaBean getKfdaBean(String id)
/*    */   {
/* 39 */     return KfdaDAO.getKfdaBean(id);
/*    */   }
/*    */ 
/*    */   public static boolean insertKfda(KfdaBean hb, SettingLogsBean stl)
/*    */   {
/* 44 */     hb.setId(PublicTableDAO.getIDByTableName("dz_kfda_sj"));
/* 45 */     return KfdaDAO.insertKfda(hb, stl);
/*    */   }
/*    */ 
/*    */   public static boolean insertSb_Kfda(KfdaBean hb)
/*    */   {
/* 50 */     hb.setId(PublicTableDAO.getIDByTableName("dz_kfda_sj"));
			 hb.setInputTime(DateUtil.getCurrentDateTime());
/* 51 */     return KfdaDAO.insertKfda(hb);
/*    */   }
/*    */ 
/*    */   public static boolean updateKfda(KfdaBean hb, SettingLogsBean stl)
/*    */   {
/* 56 */     return KfdaDAO.updateKfda(hb, stl);
/*    */   }
/*    */ 
/*    */   public static boolean deleteKfda(Map<String, String> m, SettingLogsBean stl)
/*    */   {
/* 70 */     return KfdaDAO.deleteKfda(m, stl);
/*    */   }
/*    */ }

/* Location:           E:\Xshell\219.144.222.46(省水保)\classes\com.zip
 * Qualified Name:     com.cicro.project.sb_xmgl.Sb_xmglManager
 * JD-Core Version:    0.6.2
 */