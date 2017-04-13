/*     */ package com.cicro.wcm.dao.material;
/*     */ 
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.bean.material.MateInfoBean;
/*     */ import com.cicro.wcm.dao.PublicTableDAO;
/*     */ import com.cicro.wcm.db.DBManager;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class MateInfoDao
/*     */ {
/*     */   public static List<MateInfoBean> getMateInfoList(Map<String, String> con_m)
/*     */   {
/*  22 */     return DBManager.queryFList("getMateInfoList", con_m);
/*     */   }
/*     */ 
/*     */   public static MateInfoBean getMateInfoByArr_id(String arr_id)
/*     */   {
/*  31 */     return (MateInfoBean)DBManager.queryFObj("getMateInfoBean", arr_id);
/*     */   }
/*     */ 
/*     */   public static String getMateInfoListCounts(Map<String, String> con_m)
/*     */   {
/*  40 */     return DBManager.getString("getMateInfoListCounts", con_m);
/*     */   }
/*     */ 
/*     */   public static boolean insertMateInfo(MateInfoBean mfb, SettingLogsBean stl)
/*     */   {
/*  50 */     if (DBManager.insert("insertMateInfo", mfb))
/*     */     {
/*  52 */       PublicTableDAO.insertSettingLogs("添加", "素材", mfb.getAtt_id(), stl);
/*  53 */       return true;
/*     */     }
/*  55 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateMateInfo(MateInfoBean mfb, SettingLogsBean stl)
/*     */   {
/*  65 */     if (DBManager.update("updateMateInfo", mfb))
/*     */     {
/*  67 */       PublicTableDAO.insertSettingLogs("修改", "素材", mfb.getAtt_id(), stl);
/*  68 */       return true;
/*     */     }
/*  70 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteMateInfo(String att_id, SettingLogsBean stl)
/*     */   {
/*  81 */     Map m = new HashMap();
/*  82 */     m.put("att_id", att_id);
/*  83 */     if (DBManager.update("deleteMateInfos", m))
/*     */     {
/*  85 */       PublicTableDAO.insertSettingLogs("删除", "素材", att_id, stl);
/*  86 */       return true;
/*     */     }
/*  88 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean moveMateInfo(String f_id, String att_ids)
/*     */   {
/*  99 */     Map m = new HashMap();
/* 100 */     m.put("f_id", f_id);
/* 101 */     m.put("att_ids", att_ids);
/* 102 */     return DBManager.update("move_mate_info", m);
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.dao.material.MateInfoDao
 * JD-Core Version:    0.6.2
 */