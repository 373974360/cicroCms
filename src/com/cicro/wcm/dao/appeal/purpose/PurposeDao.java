/*     */ package com.cicro.wcm.dao.appeal.purpose;
/*     */ 
/*     */ import com.cicro.wcm.bean.appeal.purpose.PurposeBean;
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.dao.PublicTableDAO;
/*     */ import com.cicro.wcm.db.DBManager;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class PurposeDao
/*     */ {
/*     */   public static List<PurposeBean> getAllPurposeList()
/*     */   {
/*  21 */     return DBManager.queryFList("getAllPurposeList", "");
/*     */   }
/*     */ 
/*     */   public static String getPurposeCount()
/*     */   {
/*  31 */     return DBManager.getString("getPurposeCount", "");
/*     */   }
/*     */ 
/*     */   public static boolean insertPurpose(PurposeBean pb, SettingLogsBean stl)
/*     */   {
/*  42 */     int pur_id = PublicTableDAO.getIDByTableName(PublicTableDAO.APPEAL_PROCESS_TABLE_NAME);
/*     */ 
/*  44 */     if (DBManager.insert("insertPurpose", pb))
/*     */     {
/*  46 */       PublicTableDAO.insertSettingLogs("添加", "诉求目的", pb.getPur_id(), stl);
/*  47 */       return true;
/*     */     }
/*     */ 
/*  51 */     return false;
/*     */   }
/*     */ 
/*     */   public static PurposeBean getPurposeBeanByID(String id)
/*     */   {
/*  62 */     return (PurposeBean)DBManager.queryFObj("getPurposeBeanByID", id);
/*     */   }
/*     */ 
/*     */   public static boolean updatePurpose(PurposeBean pb, SettingLogsBean stl)
/*     */   {
/*  74 */     if (DBManager.update("updatePurpose", pb))
/*     */     {
/*  76 */       PublicTableDAO.insertSettingLogs("修改", "诉求目的", pb.getPur_id(), stl);
/*  77 */       return true;
/*     */     }
/*     */ 
/*  81 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean savePurposeSort(String pur_id, SettingLogsBean stl)
/*     */   {
/*  93 */     if ((pur_id != null) && (!"".equals(pur_id))) {
/*     */       try
/*     */       {
/*  96 */         Map m = new HashMap();
/*  97 */         String[] tempA = pur_id.split(",");
/*  98 */         for (int i = 0; i < tempA.length; i++)
/*     */         {
/* 100 */           m.put("sort_id", Integer.valueOf(i + 1));
/* 101 */           m.put("pur_id", tempA[i]);
/* 102 */           DBManager.update("update_Purpose_sort", m);
/*     */         }
/* 104 */         PublicTableDAO.insertSettingLogs("保存排序", "诉求目的", pur_id, stl);
/* 105 */         return true;
/*     */       }
/*     */       catch (Exception e) {
/* 108 */         e.printStackTrace();
/* 109 */         return false;
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 114 */     return true;
/*     */   }
/*     */ 
/*     */   public static boolean deletePurpose(Map<String, String> mp, SettingLogsBean stl)
/*     */   {
/* 125 */     if (DBManager.delete("deletePurpose", mp))
/*     */     {
/* 127 */       PublicTableDAO.insertSettingLogs("删除", "诉求目的", (String)mp.get("pur_ids"), stl);
/* 128 */       return true;
/*     */     }
/*     */ 
/* 132 */     return false;
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.dao.appeal.purpose.PurposeDao
 * JD-Core Version:    0.6.2
 */