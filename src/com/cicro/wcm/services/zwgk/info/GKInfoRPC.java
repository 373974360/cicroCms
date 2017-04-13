/*    */ package com.cicro.wcm.services.zwgk.info;
/*    */ 
/*    */ import com.cicro.wcm.bean.cms.info.GKInfoBean;
/*    */ import com.cicro.wcm.bean.cms.info.GKResFileBean;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class GKInfoRPC
/*    */ {
/*    */   public static List<GKInfoBean> getGKInfoList(Map<String, String> m)
/*    */   {
/* 16 */     return GKInfoManager.getGKInfoList(m);
/*    */   }
/*    */ 
/*    */   public static String getGKInfoCount(Map<String, String> m)
/*    */   {
/* 25 */     return GKInfoManager.getGKInfoCount(m);
/*    */   }
/*    */ 
/*    */   public static String getInfoIdForGKIndex(String gk_index)
/*    */   {
/* 35 */     return GKInfoManager.getInfoIdForGKIndex(gk_index);
/*    */   }
/*    */ 
/*    */   public static List<GKResFileBean> getGKResFileList(String info_id)
/*    */   {
/* 45 */     return GKInfoManager.getGKResFileList(info_id);
/*    */   }
/*    */ 
/*    */   public static boolean reloadGKIndex(Map<String, String> m)
/*    */   {
/* 54 */     return GKInfoManager.reloadGKIndex(m);
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.zwgk.info.GKInfoRPC
 * JD-Core Version:    0.6.2
 */