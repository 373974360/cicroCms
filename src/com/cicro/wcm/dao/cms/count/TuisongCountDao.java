/*    */ package com.cicro.wcm.dao.cms.count;
/*    */ 
/*    */ import com.cicro.wcm.bean.cms.count.TuisongCountBean;
/*    */ import com.cicro.wcm.bean.cms.info.InfoBean;
/*    */ import com.cicro.wcm.db.DBManager;
/*    */ import com.cicro.wcm.services.cms.info.InfoBaseManager;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class TuisongCountDao
/*    */ {
/*    */   public static TuisongCountBean getCountListByCat(Map<String, String> mp)
/*    */   {
/* 25 */     int Tuisong = 0;
/* 26 */     int not_Tuisong = 0;
/* 27 */     String site_id = (String)mp.get("site_id");
/*    */ 
/* 29 */     List InfoList = DBManager.queryFList("getInfoListsByCat_SiteId", mp);
/* 30 */     TuisongCountBean tsBean = new TuisongCountBean();
/*    */ 
/* 32 */     for (int i = 0; i < InfoList.size(); i++)
/*    */     {
/* 34 */       InfoBean infob = null;
/* 35 */       tsBean = (TuisongCountBean)InfoList.get(i);
/* 36 */       int from_id = ((TuisongCountBean)InfoList.get(i)).getFrom_id();
/*    */ 
/* 45 */       infob = InfoBaseManager.getInfoById(from_id, site_id);
/* 46 */       if (infob == null)
/* 47 */         Tuisong++;
/*    */       else {
/* 49 */         not_Tuisong++;
/*    */       }
/* 51 */       tsBean.setIs_host(not_Tuisong);
/* 52 */       tsBean.setIsNot_host(Tuisong);
/* 53 */       tsBean.setApp_id(((TuisongCountBean)InfoList.get(i)).getApp_id());
/* 54 */       tsBean.setInfo_id(((TuisongCountBean)InfoList.get(i)).getInfo_id());
/* 55 */       tsBean.setSite_id(((TuisongCountBean)InfoList.get(i)).getSite_id());
/*    */     }
/* 57 */     return tsBean;
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.dao.cms.count.TuisongCountDao
 * JD-Core Version:    0.6.2
 */