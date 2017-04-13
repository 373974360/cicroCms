/*    */ package com.cicro.wcm.services.system.filterWord;
/*    */ 
/*    */ import com.cicro.wcm.bean.system.filterWord.FilterWordBean;
/*    */ import com.cicro.wcm.dao.PublicTableDAO;
/*    */ import java.io.PrintStream;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class FilterWordRPC
/*    */ {
/*    */   public static FilterWordBean getFilterWordBean(int filterword_id)
/*    */   {
/* 21 */     return FilterWordManager.getFilterWordBean(filterword_id);
/*    */   }
/*    */ 
/*    */   public static boolean insertFilterWord(FilterWordBean fwd)
/*    */   {
/* 30 */     int id = PublicTableDAO.getIDByTableName(PublicTableDAO.FILTERWORD_TABLE_NAME);
/* 31 */     System.out.println("filterWord_id>>>>>>>>>>>>>" + id);
/* 32 */     fwd.setFilterword_id(id);
/*    */ 
/* 34 */     return FilterWordManager.insertFilterWord(fwd);
/*    */   }
/*    */ 
/*    */   public static boolean updateFilterWord(FilterWordBean fwd)
/*    */   {
/* 43 */     return FilterWordManager.updateFilterWord(fwd);
/*    */   }
/*    */ 
/*    */   public static boolean deleteFilterWord(String filterword_ids)
/*    */   {
/* 53 */     return FilterWordManager.deleteFilterWord(filterword_ids);
/*    */   }
/*    */ 
/*    */   public static List<FilterWordBean> getAllFilterWord(Map<String, String> m)
/*    */   {
/* 63 */     return FilterWordManager.getAllFilterWord(m);
/*    */   }
/*    */ 
/*    */   public static String getFilterWordCount(Map<String, String> map)
/*    */   {
/* 73 */     return FilterWordManager.getFilterWordCount(map);
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.system.filterWord.FilterWordRPC
 * JD-Core Version:    0.6.2
 */