/*    */ package com.cicro.project.dz_pxxx;
/*    */ 
/*    */ import com.cicro.util.FormatUtil;
/*    */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*    */ import com.cicro.wcm.dao.PublicTableDAO;

/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class PxxxKcManager
/*    */ {
/*    */   public static String getPxxxKcCount(Map<String, String> m)
/*    */   {
/* 15 */     if (m.containsKey("key_word"))
/*    */     {
/* 17 */       if (!FormatUtil.isValiditySQL((String)m.get("key_word")))
/* 18 */         return "0";
/*    */     }
/* 20 */     return PxxxKcDAO.getPxxxKcCount(m);
/*    */   }
/*    */ 
/*    */   public static List<PxxxKcBean> getPxxxKcList(Map<String, String> m) {
/* 24 */     if (m.containsKey("key_word"))
/*    */     {
/* 26 */       if (!FormatUtil.isValiditySQL((String)m.get("key_word")))
/* 27 */         return new ArrayList();
/*    */     }
/* 29 */     return PxxxKcDAO.getPxxxKcList(m);
/*    */   }
/*    */ 
/*    */   public static List<PxxxKcBean> getAllPxxxKcList()
/*    */   {
/* 34 */     return PxxxKcDAO.getAllPxxxKcList();
/*    */   }
		   public static List<PxxxKcBean> getAllPxxxKcByPxID(String pxid)
		   {
			   return PxxxKcDAO.getAllPxxxKcByPxID(pxid);
		   }
/*    */ 
/*    */   public static PxxxKcBean getPxxxKcBean(String id)
/*    */   {
/* 39 */     return PxxxKcDAO.getPxxxKcBean(id);
/*    */   }
/*    */ 
/*    */   public static boolean insertPxxxKc(PxxxKcBean hb, SettingLogsBean stl)
/*    */   {
/* 44 */     hb.setId(PublicTableDAO.getIDByTableName("dz_pxxx"));
/* 45 */     return PxxxKcDAO.insertPxxxKc(hb, stl);
/*    */   }
/*    */ 
/*    */   public static boolean updatePxxxKc(PxxxKcBean hb, SettingLogsBean stl)
/*    */   {
/* 56 */     return PxxxKcDAO.updatePxxxKc(hb, stl);
/*    */   }
/*    */ 
/*    */   public static boolean deletePxxxKc(Map<String, String> m, SettingLogsBean stl)
/*    */   {
/* 70 */     return PxxxKcDAO.deletePxxxKc(m, stl);
/*    */   }
/*    */ }

/* Location:           E:\Xshell\219.144.222.46(省水保)\classes\com.zip
 * Qualified Name:     com.cicro.project.sb_xmgl.Sb_xmglManager
 * JD-Core Version:    0.6.2
 */