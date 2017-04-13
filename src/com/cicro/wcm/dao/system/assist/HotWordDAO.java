/*     */ package com.cicro.wcm.dao.system.assist;
/*     */ 
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.bean.system.assist.HotWordBean;
/*     */ import com.cicro.wcm.dao.PublicTableDAO;
/*     */ import com.cicro.wcm.db.DBManager;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class HotWordDAO
/*     */ {
/*     */   public static List<HotWordBean> getAllHotWordList()
/*     */   {
/*  32 */     return DBManager.queryFList("getAllHotWordList", "");
/*     */   }
/*     */ 
/*     */   public static HotWordBean getHotWordBean(int hot_id)
/*     */   {
/*  42 */     return (HotWordBean)DBManager.queryFObj("getHotWordBean", Integer.valueOf(hot_id));
/*     */   }
/*     */ 
/*     */   public static String getHotWordCount(Map<String, String> con_map)
/*     */   {
/*  52 */     return DBManager.getString("getHotWordCount", con_map);
/*     */   }
/*     */ 
/*     */   public static List<HotWordBean> getHotWordListForDB(Map<String, String> con_map)
/*     */   {
/*  63 */     return DBManager.queryFList("getHotWordListForDB", con_map);
/*     */   }
/*     */ 
/*     */   public static boolean updateHotWord(HotWordBean hw, SettingLogsBean stl)
/*     */   {
/*  72 */     if (DBManager.update("update_hotWord", hw))
/*     */     {
/*  74 */       PublicTableDAO.insertSettingLogs("修改", "热词", hw.getHot_id(), stl);
/*  75 */       return true;
/*     */     }
/*  77 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean addHotWord(HotWordBean hw, SettingLogsBean stl)
/*     */   {
/*  86 */     int hot_id = PublicTableDAO.getIDByTableName(PublicTableDAO.HOTWORD_TABLE_NAME);
/*  87 */     hw.setHot_id(hot_id);
/*  88 */     if (DBManager.insert("insert_hotWord", hw))
/*     */     {
/*  90 */       PublicTableDAO.insertSettingLogs("添加", "热词", hot_id, stl);
/*  91 */       return true;
/*     */     }
/*  93 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean delHotWord(String hot_ids, SettingLogsBean stl)
/*     */   {
/* 103 */     Map map = new HashMap();
/* 104 */     map.put("hot_ids", hot_ids);
/* 105 */     if (DBManager.delete("deleteHotWord", map))
/*     */     {
/* 107 */       PublicTableDAO.insertSettingLogs("删除", "热词", hot_ids, stl);
/* 108 */       return true;
/*     */     }
/* 110 */     return false;
/*     */   }
/*     */ 
/*     */   public static void main(String[] args) {
/* 114 */     HotWordBean hw = new HotWordBean();
/* 115 */     hw.setHot_id(1);
/* 116 */     hw.setHot_name("hot01");
/* 117 */     hw.setHot_url("www.baidu.com");
/* 118 */     hw.setApp_id("appid");
/* 119 */     hw.setSite_id("wcm");
/* 120 */     DBManager.insert("insert_hotWord", hw);
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.dao.system.assist.HotWordDAO
 * JD-Core Version:    0.6.2
 */