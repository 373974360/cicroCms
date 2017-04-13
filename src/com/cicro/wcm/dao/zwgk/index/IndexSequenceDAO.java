/*    */ package com.cicro.wcm.dao.zwgk.index;
/*    */ 
/*    */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*    */ import com.cicro.wcm.bean.zwgk.index.IndexSequenceBean;
/*    */ import com.cicro.wcm.dao.PublicTableDAO;
/*    */ import com.cicro.wcm.db.DBManager;
/*    */ import java.io.PrintStream;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class IndexSequenceDAO
/*    */ {
/*    */   public static IndexSequenceBean getSequenceBean(IndexSequenceBean isb)
/*    */   {
/* 20 */     Map m = new HashMap();
/* 21 */     m.put("seq_type", isb.getSeq_type());
/* 22 */     m.put("seq_ymd", isb.getSeq_ymd());
/* 23 */     m.put("site_id", isb.getSite_id());
/* 24 */     if (!"".equals(isb.getSeq_item().trim()))
/* 25 */       m.put("seq_item", isb.getSeq_item());
/* 26 */     return (IndexSequenceBean)DBManager.queryFObj("getIndexSequenceBean", m);
/*    */   }
/*    */ 
/*    */   public static boolean insertSequence(IndexSequenceBean isb)
/*    */   {
/* 37 */     int id = PublicTableDAO.getIDByTableName(PublicTableDAO.GK_INDEX_SEQUENCE);
/* 38 */     isb.setId(id);
/* 39 */     if (DBManager.insert("insertIndexSequence", isb)) {
/* 40 */       return true;
/*    */     }
/* 42 */     return false;
/*    */   }
/*    */ 
/*    */   public static boolean updateSequence(IndexSequenceBean isb)
/*    */   {
/* 54 */     Map m = new HashMap();
/* 55 */     m.put("seq_type", isb.getSeq_type());
/* 56 */     m.put("seq_ymd", isb.getSeq_ymd());
/* 57 */     m.put("site_id", isb.getSite_id());
/* 58 */     if (!"".equals(isb.getSeq_item().trim()))
/* 59 */       m.put("seq_item", isb.getSeq_item());
/* 60 */     return DBManager.update("updateIndexSequence", m);
/*    */   }
/*    */ 
/*    */   public static boolean resetSequence(IndexSequenceBean isb, SettingLogsBean stl)
/*    */   {
/* 72 */     Map m = new HashMap();
/* 73 */     m.put("seq_type", isb.getSeq_type());
/* 74 */     m.put("seq_ymd", isb.getSeq_ymd());
/* 75 */     m.put("site_id", isb.getSite_id());
/* 76 */     if (!"".equals(isb.getSeq_item().trim()))
/* 77 */       m.put("seq_item", isb.getSeq_item());
/* 78 */     if (DBManager.update("resetIndexSequence", m)) {
/* 79 */       PublicTableDAO.insertSettingLogs("修改", "流水号生产信息", isb.getId(), stl);
/* 80 */       return true;
/*    */     }
/* 82 */     return false;
/*    */   }
/*    */ 
/*    */   public static void main(String[] arg)
/*    */   {
/* 90 */     IndexSequenceBean pa = new IndexSequenceBean();
/* 91 */     pa.setSeq_item("");
/* 92 */     pa.setSeq_type(1);
/* 93 */     pa.setApp_id("1");
/* 94 */     pa.setSite_id("1");
/* 95 */     IndexSequenceBean i = getSequenceBean(pa);
/*    */ 
/* 97 */     i.setSeq_cur_value(999);
/*    */ 
/* 99 */     if (updateSequence(i))
/*    */     {
/* 101 */       System.out.println("OK");
/*    */     }
/*    */     else
/*    */     {
/* 105 */       System.out.println("u_false");
/*    */     }
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.dao.zwgk.index.IndexSequenceDAO
 * JD-Core Version:    0.6.2
 */