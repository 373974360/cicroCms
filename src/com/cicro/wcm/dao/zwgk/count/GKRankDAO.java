/*    */ package com.cicro.wcm.dao.zwgk.count;
/*    */ 
/*    */ import com.cicro.wcm.bean.zwgk.count.GKCountBean;
/*    */ import com.cicro.wcm.db.DBManager;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class GKRankDAO
/*    */ {
/*    */   public static List<Map> GKWorkLoadRank(Map<String, String> mp)
/*    */   {
/* 20 */     return DBManager.queryFList("gk_xxtb_rank", mp);
/*    */   }
/*    */ 
/*    */   public static List<GKCountBean> GKInfoCountRank(Map<String, String> mp)
/*    */   {
/* 31 */     return DBManager.queryFList("gk_jdxx_rank", mp);
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.dao.zwgk.count.GKRankDAO
 * JD-Core Version:    0.6.2
 */