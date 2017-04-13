/*     */ package com.cicro.wcm.dao.interview;
/*     */ 
/*     */ import com.cicro.wcm.bean.interview.SubReleInfo;
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.dao.PublicTableDAO;
/*     */ import com.cicro.wcm.db.DBManager;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class SubReleInfoDAO
/*     */ {
/*     */   public static String getReleInfoListCountBySub_id(Map<String, String> m)
/*     */   {
/*  31 */     return DBManager.getString("getReleInfoListCountBySub_id", m);
/*     */   }
/*     */ 
/*     */   public static String getReleInfoCountBySub_id(String sub_id)
/*     */   {
/*  36 */     return DBManager.getString("getReleInfoCountBySub_id", sub_id);
/*     */   }
/*     */ 
/*     */   public static List getReleInfoListBySub_id(Map m)
/*     */   {
/*  47 */     List l = DBManager.queryFList("getReleInfoListBySub_id", m);
/*  48 */     return l;
/*     */   }
/*     */ 
/*     */   public static SubReleInfo getSubReleInfo(int id)
/*     */   {
/*  58 */     SubReleInfo sri = (SubReleInfo)DBManager.queryFObj("getReleInfo", Integer.valueOf(id));
/*  59 */     return sri;
/*     */   }
/*     */ 
/*     */   public static SubReleInfo getSubReleInfo(String id)
/*     */   {
/*  69 */     SubReleInfo sri = (SubReleInfo)DBManager.queryFObj("getReleInfoByUUID", id);
/*  70 */     return sri;
/*     */   }
/*     */ 
/*     */   public static boolean insertReleInfo(SubReleInfo sri, SettingLogsBean stl)
/*     */   {
/*  80 */     int id = PublicTableDAO.getIDByTableName(PublicTableDAO.INTERVIEW_RELEINFO_TABLE_NAME);
/*  81 */     sri.setId(id);
/*  82 */     if (DBManager.insert("cp_releinfo_insert", sri))
/*     */     {
/*  84 */       PublicTableDAO.insertSettingLogs("添加", "访谈相关信息", id, stl);
/*  85 */       return true;
/*     */     }
/*     */ 
/*  88 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateReleInfo(SubReleInfo sri, SettingLogsBean stl)
/*     */   {
/*  98 */     if (DBManager.insert("cp_releinfo_update", sri))
/*     */     {
/* 100 */       PublicTableDAO.insertSettingLogs("修改", "访谈相关信息", sri.getId(), stl);
/* 101 */       return true;
/*     */     }
/*     */ 
/* 104 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteReleInfo(Map m, SettingLogsBean stl)
/*     */   {
/* 116 */     if (DBManager.update("cp_releinfo_delete", m))
/*     */     {
/* 118 */       PublicTableDAO.insertSettingLogs("删除", "访谈相关信息", m.get("ids"), stl);
/* 119 */       return true;
/*     */     }
/*     */ 
/* 122 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean publishReleInfo(Map m, SettingLogsBean stl)
/*     */   {
/* 133 */     if (DBManager.update("cp_releinfo_updateStatus", m))
/*     */     {
/* 135 */       PublicTableDAO.insertSettingLogs("发布", "访谈相关信息", m.get("ids"), stl);
/* 136 */       return true;
/*     */     }
/*     */ 
/* 139 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean saveReleInfoSort(Map m, SettingLogsBean stl)
/*     */   {
/* 150 */     String ids = (String)m.get("ids");
/* 151 */     if ((ids != null) && (!"".equals(ids)))
/*     */     {
/* 153 */       Map new_m = new HashMap();
/* 154 */       String[] tempA = ids.split(",");
/*     */       try {
/* 156 */         for (int i = 0; i < tempA.length; i++)
/*     */         {
/* 158 */           new_m.put("sort", i + 1);
/* 159 */           new_m.put("id", tempA[i]);
/* 160 */           DBManager.update("cp_releinfo_sort", new_m);
/*     */         }
/*     */       }
/*     */       catch (Exception e) {
/* 164 */         e.printStackTrace();
/* 165 */         return false;
/*     */       }
/* 167 */       PublicTableDAO.insertSettingLogs("保存排序", "访谈相关信息", ids, stl);
/*     */     }
/* 169 */     return true;
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.dao.interview.SubReleInfoDAO
 * JD-Core Version:    0.6.2
 */