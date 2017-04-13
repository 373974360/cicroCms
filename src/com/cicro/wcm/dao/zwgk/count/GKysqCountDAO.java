/*    */ package com.cicro.wcm.dao.zwgk.count;
/*    */ 
/*    */ import com.cicro.wcm.db.DBManager;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class GKysqCountDAO
/*    */ {
/*    */   public static List getStateCount(Map<String, String> mp)
/*    */   {
/* 17 */     return DBManager.queryFList("ysq_state_count", mp);
/*    */   }
/*    */ 
/*    */   public static List getTypeCount(Map<String, String> mp)
/*    */   {
/* 27 */     return DBManager.queryFList("ysq_type_count", mp);
/*    */   }
/*    */ 
/*    */   public static List getStateCountByNode(Map<String, String> mp)
/*    */   {
/* 38 */     return DBManager.queryFList("ysq_state_count_node", mp);
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.dao.zwgk.count.GKysqCountDAO
 * JD-Core Version:    0.6.2
 */