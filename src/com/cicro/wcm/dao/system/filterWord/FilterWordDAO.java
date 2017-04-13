/*    */ package com.cicro.wcm.dao.system.filterWord;
/*    */ 
/*    */ import com.cicro.wcm.bean.system.filterWord.FilterWordBean;
/*    */ import com.cicro.wcm.db.DBManager;
/*    */ import java.util.HashMap;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class FilterWordDAO
/*    */ {
/*    */   public static List<FilterWordBean> getFilterWordList()
/*    */   {
/* 27 */     return DBManager.queryFList("getFilterWordList", "");
/*    */   }
/*    */ 
/*    */   public static List<FilterWordBean> getAllFilterWord(Map<String, String> m)
/*    */   {
/* 38 */     return DBManager.queryFList("getAllFilterWord", m);
/*    */   }
/*    */ 
/*    */   public static String getFilterWordCount(Map<String, String> map)
/*    */   {
/* 47 */     return DBManager.getString("getFilterWordCount", map);
/*    */   }
/*    */ 
/*    */   public static FilterWordBean getFilterWordBean(int filterword_id)
/*    */   {
/* 56 */     return (FilterWordBean)DBManager.queryFObj("getFilterWordBean", Integer.valueOf(filterword_id));
/*    */   }
/*    */ 
/*    */   public static boolean insertFilterWord(FilterWordBean fwd)
/*    */   {
/* 65 */     return DBManager.insert("insert_FilterWord", fwd);
/*    */   }
/*    */ 
/*    */   public static boolean updateFilterWord(FilterWordBean fwd)
/*    */   {
/* 74 */     return DBManager.update("update_FilterWord", fwd);
/*    */   }
/*    */ 
/*    */   public static boolean deleteFilterWord(String filterword_ids)
/*    */   {
/* 83 */     Map m = new HashMap();
/* 84 */     m.put("filterword_ids", filterword_ids);
/* 85 */     return DBManager.delete("delete_FilterWord", m);
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.dao.system.filterWord.FilterWordDAO
 * JD-Core Version:    0.6.2
 */