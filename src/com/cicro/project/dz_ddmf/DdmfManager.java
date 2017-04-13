/*    */ package com.cicro.project.dz_ddmf;
/*    */ 
/*    */ import com.cicro.util.DateUtil;
/*    */ import com.cicro.util.FormatUtil;
/*    */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*    */ import com.cicro.wcm.dao.PublicTableDAO;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class DdmfManager
/*    */ {
/*    */   public static String getDdmfCount(Map<String, String> m)
/*    */   {
/* 15 */     if (m.containsKey("key_word"))
/*    */     {
/* 17 */       if (!FormatUtil.isValiditySQL((String)m.get("key_word")))
/* 18 */         return "0";
/*    */     }
/* 20 */     return DdmfDAO.getDdmfCount(m);
/*    */   }
/*    */ 
/*    */   public static List<DdmfBean> getDdmfList(Map<String, String> m) {
/* 24 */     if (m.containsKey("key_word"))
/*    */     {
/* 26 */       if (!FormatUtil.isValiditySQL((String)m.get("key_word")))
/* 27 */         return new ArrayList();
/*    */     }
/* 29 */     return DdmfDAO.getDdmfList(m);
/*    */   }
/*    */ 
/*    */   public static List<DdmfBean> getAllDdmfList()
/*    */   {
/* 34 */     return DdmfDAO.getAllDdmfList();
/*    */   }
/*    */ 
/*    */   public static DdmfBean getDdmfBean(String id, boolean is_browser)
/*    */   {
/* 39 */     return DdmfDAO.getDdmfBean(id, is_browser);
/*    */   }
/*    */ 
/*    */   public static boolean insertDdmf(DdmfBean hb, SettingLogsBean stl)
/*    */   {
/* 44 */     hb.setId(PublicTableDAO.getIDByTableName("dz_ddmf"));
/* 45 */     return DdmfDAO.insertDdmf(hb, stl);
/*    */   }
/*    */ 
/*    */   public static boolean updateDdmf(DdmfBean hb, SettingLogsBean stl)
/*    */   {
/* 56 */     return DdmfDAO.updateDdmf(hb, stl);
/*    */   }
/*    */ 
/*    */   public static boolean deleteDdmf(Map<String, String> m, SettingLogsBean stl)
/*    */   {
/* 70 */     return DdmfDAO.deleteDdmf(m, stl);

/*    */   }
/*    */ }

/* Location:           E:\Xshell\219.144.222.46(省水保)\classes\com.zip
 * Qualified Name:     com.cicro.project.sb_xmgl.Sb_xmglManager
 * JD-Core Version:    0.6.2
 */