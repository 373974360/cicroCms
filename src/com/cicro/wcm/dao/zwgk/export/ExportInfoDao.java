/*    */ package com.cicro.wcm.dao.zwgk.export;
/*    */ 
/*    */ import com.cicro.wcm.bean.zwgk.export.ExportInfo;
/*    */ import com.cicro.wcm.db.DBManager;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class ExportInfoDao
/*    */ {
/*    */   public static List<ExportInfo> getGkinfoByNodeAndCat(Map map)
/*    */   {
/* 13 */     return DBManager.queryFList("exprot_info.getGkinfoByNodeAndCat", map);
/*    */   }
/*    */ 
/*    */   public static List<ExportInfo> getGkinfoCardByNodeAndCat(Map map)
/*    */   {
/* 18 */     return DBManager.queryFList("exprot_info.getGkinfoCardByNodeAndCat", map);
/*    */   }
/*    */ 
/*    */   public static List<ExportInfo> getGkGXinfoByNodeAndCat(Map map)
/*    */   {
/* 23 */     return DBManager.queryFList("exprot_info.getGkGXinfoByNodeAndCat", map);
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.dao.zwgk.export.ExportInfoDao
 * JD-Core Version:    0.6.2
 */