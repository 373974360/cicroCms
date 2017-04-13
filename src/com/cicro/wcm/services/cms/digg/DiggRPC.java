/*    */ package com.cicro.wcm.services.cms.digg;
/*    */ 
/*    */ import com.cicro.wcm.bean.cms.digg.InfoDiggBean;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class DiggRPC
/*    */ {
/*    */   public static List<InfoDiggBean> getInfoDiggList(Map mp)
/*    */   {
/* 18 */     return DiggManager.getInfoDiggList(mp);
/*    */   }
/*    */ 
/*    */   public static String getInfoDiggCnt(Map mp)
/*    */   {
/* 29 */     return DiggManager.getInfoDiggListCnt(mp);
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.cms.digg.DiggRPC
 * JD-Core Version:    0.6.2
 */