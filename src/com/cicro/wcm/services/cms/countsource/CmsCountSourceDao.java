/*    */ package com.cicro.wcm.services.cms.countsource;
/*    */ 
/*    */ import com.cicro.util.MapManager;
/*    */ import com.cicro.wcm.bean.cms.count.CmsCountBean;
/*    */ import com.cicro.wcm.db.DBManager;
/*    */ import java.io.PrintStream;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class CmsCountSourceDao
/*    */ {
/*    */   public static List<CmsCountBean> getInfoCountListBySource(Map<String, String> mp)
/*    */   {
/* 15 */     List list = new ArrayList();
/*    */     try {
/* 17 */       System.out.println("CmsCountSourceDao---getInfoCountListBySource---map--" + mp);
/* 18 */       List result = DBManager.queryFList("sqlmap.cms.infocount_source.getInfoCountListBySource", mp);
/*    */ 
/* 20 */       for (Map bean : result) {
/* 21 */         CmsCountBean cntBean = new CmsCountBean();
/* 22 */         bean = MapManager.mapKeyValueToLow(bean);
/*    */ 
/* 24 */         String cat_name = (String)bean.get("source");
/* 25 */         if ((cat_name == null) || ("".equals(cat_name))) {
/* 26 */           cat_name = "未知来源";
/*    */         }
/* 28 */         String count = bean.get("count").toString();
/*    */ 
/* 30 */         cntBean.setCat_name(cat_name);
/* 31 */         cntBean.setCount(Integer.valueOf(count).intValue());
/*    */ 
/* 33 */         list.add(cntBean);
/*    */       }
/*    */     } catch (Exception e) {
/* 36 */       e.printStackTrace();
/*    */     }
/* 38 */     return list;
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.cms.countsource.CmsCountSourceDao
 * JD-Core Version:    0.6.2
 */