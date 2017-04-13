/*    */ package com.cicro.wcm.dao.appeal.satisfaction;
/*    */ 
/*    */ import com.cicro.wcm.bean.appeal.satisfaction.SatRecordBean;
/*    */ import com.cicro.wcm.bean.appeal.satisfaction.SatisfactionBean;
/*    */ import com.cicro.wcm.dao.PublicTableDAO;
/*    */ import com.cicro.wcm.db.DBManager;
/*    */ import java.util.List;
/*    */ 
/*    */ public class SatisfactionDAO
/*    */ {
/*    */   public static List getAllSatisfactionList()
/*    */   {
/* 22 */     return DBManager.queryFList("getSatisfactionList", "");
/*    */   }
/*    */ 
/*    */   public static boolean insertSatisfaction(SatisfactionBean sfb)
/*    */   {
/* 31 */     return DBManager.insert("insert_satisfaction", sfb);
/*    */   }
/*    */ 
/*    */   public static boolean deleteSatisfaction()
/*    */   {
/* 40 */     return DBManager.delete("deleteSatisfaction", "");
/*    */   }
/*    */ 
/*    */   public static SatisfactionBean getSatisfactionBean(int sf_id)
/*    */   {
/* 50 */     return (SatisfactionBean)DBManager.queryFObj("get_SatisfactionById", sf_id);
/*    */   }
/*    */ 
/*    */   public static boolean insertSatRecord(SatRecordBean srb)
/*    */   {
/* 60 */     srb.setRec_id(PublicTableDAO.getIDByTableName(PublicTableDAO.APPEAL_SATRECORD_TABLE_NAME));
/* 61 */     return DBManager.insert("insert_sat_record", srb);
/*    */   }
/*    */ 
/*    */   public static String getSatScoreBySQID(String sq_id)
/*    */   {
/* 71 */     return DBManager.getString("getSatScoreBySQID", sq_id);
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.dao.appeal.satisfaction.SatisfactionDAO
 * JD-Core Version:    0.6.2
 */