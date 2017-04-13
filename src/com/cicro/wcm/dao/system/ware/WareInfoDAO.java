/*     */ package com.cicro.wcm.dao.system.ware;
/*     */ 
/*     */ import com.cicro.wcm.bean.cms.info.InfoBean;
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.bean.system.ware.WareBean;
/*     */ import com.cicro.wcm.bean.system.ware.WareInfoBean;
/*     */ import com.cicro.wcm.bean.system.ware.WareInfoRef;
/*     */ import com.cicro.wcm.bean.system.ware.WareInfos;
/*     */ import com.cicro.wcm.dao.PublicTableDAO;
/*     */ import com.cicro.wcm.db.DBManager;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class WareInfoDAO
/*     */ {
/*     */   public static String getWareInfoRefCount(Map<String, String> m)
/*     */   {
/*  36 */     return DBManager.getString("getWareInfoRefCount", m);
/*     */   }
/*     */ 
/*     */   public static List<InfoBean> getWareInfoRefList(Map<String, String> m)
/*     */   {
/*  47 */     return DBManager.queryFList("getWareInfoRefList", m);
/*     */   }
/*     */ 
/*     */   public static List<WareBean> getWareListByRefInfo(Map<String, String> m)
/*     */   {
/*  58 */     return DBManager.queryFList("getWareListByRefInfo", m);
/*     */   }
/*     */ 
/*     */   public static boolean insertWareInfoRef(WareInfoRef wir)
/*     */   {
/*  69 */     wir.setRef_id(PublicTableDAO.getIDByTableName(PublicTableDAO.WARE_INFO_REF_TABLE_NAME));
/*  70 */     return DBManager.insert("insert_ware_info_ref", wir);
/*     */   }
/*     */ 
/*     */   public static boolean deleteWareInfoRef(Map<String, String> m, SettingLogsBean stl)
/*     */   {
/*  81 */     if (DBManager.insert("delete_ware_info_ref", m))
/*     */     {
/*  83 */       if (m.containsKey("info_ids"))
/*  84 */         PublicTableDAO.insertSettingLogs("删除", "推荐信息 信息", (String)m.get("info_ids"), stl);
/*     */       else
/*  86 */         PublicTableDAO.insertSettingLogs("删除", "推荐信息 标签", (String)m.get("ware_ids"), stl);
/*  87 */       return true;
/*     */     }
/*  89 */     return false;
/*     */   }
/*     */ 
/*     */   public static List<WareInfoBean> getWareInfoList(Map<String, String> m)
/*     */   {
/* 102 */     return DBManager.queryFList("getWareInfoList", m);
/*     */   }
/*     */ 
/*     */   public static WareInfos getWareInfosBean(int id)
/*     */   {
/* 112 */     return (WareInfos)DBManager.queryFObj("getWareInfosBean", Integer.valueOf(id));
/*     */   }
/*     */ 
/*     */   public static boolean insertWareInfo(WareInfoBean wib)
/*     */   {
/* 125 */     return DBManager.insert("insert_ware_info", wib);
/*     */   }
/*     */ 
/*     */   public static boolean sortWareInfo(String winfo_ids)
/*     */   {
/* 135 */     if ((winfo_ids != null) && (!"".equals(winfo_ids)))
/*     */     {
/* 137 */       String[] tempA = winfo_ids.split(",");
/* 138 */       Map m = new HashMap();
/* 139 */       for (int i = 0; i < tempA.length; i++)
/*     */       {
/* 141 */         m.put("winfo_id", tempA[i]);
/* 142 */         m.put("sort_id", i + 1);
/* 143 */         DBManager.update("sort_ware_info", m);
/*     */       }
/*     */     }
/* 146 */     return true;
/*     */   }
/*     */ 
/*     */   public static boolean deleteWareInfoByWInfoID(String winfo_id, SettingLogsBean stl)
/*     */   {
/* 157 */     if (DBManager.delete("delete_ware_info_byWinfoId", winfo_id))
/*     */     {
/* 159 */       DBManager.delete("delete_ware_infos_byWinfoId", winfo_id);
/* 160 */       return true;
/*     */     }
/*     */ 
/* 163 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteWareInfoByWareID(Map<String, String> m, SettingLogsBean stl)
/*     */   {
/* 174 */     return DBManager.delete("delete_ware_info_byWareId", m);
/*     */   }
/*     */ 
/*     */   public static int getNewWareInfosID()
/*     */   {
/* 185 */     return PublicTableDAO.getIDByTableName(PublicTableDAO.WARE_INFOS_TABLE_NAME);
/*     */   }
/*     */ 
/*     */   public static boolean insertWareInfos(WareInfos wif, SettingLogsBean stl)
/*     */   {
/* 196 */     return DBManager.insert("insert_ware_infos", wif);
/*     */   }
/*     */ 
/*     */   public static boolean updateWareInfos(WareInfos wif, SettingLogsBean stl)
/*     */   {
/* 207 */     return DBManager.update("update_ware_infos", wif);
/*     */   }
/*     */ 
/*     */   public static boolean sortWInfos(String ids)
/*     */   {
/* 217 */     if ((ids != null) && (!"".equals(ids)))
/*     */     {
/* 219 */       String[] tempA = ids.split(",");
/* 220 */       Map m = new HashMap();
/* 221 */       for (int i = 0; i < tempA.length; i++)
/*     */       {
/* 223 */         m.put("id", tempA[i]);
/* 224 */         m.put("sort_id", i + 1);
/* 225 */         DBManager.update("sort_w_infos", m);
/*     */       }
/*     */     }
/* 228 */     return true;
/*     */   }
/*     */ 
/*     */   public static boolean deleteWareInfosByID(int id, SettingLogsBean stl)
/*     */   {
/* 239 */     return DBManager.delete("delete_ware_infos_byId", Integer.valueOf(id));
/*     */   }
/*     */ 
/*     */   public static boolean deleteWareInfosByWareID(Map<String, String> m, SettingLogsBean stl)
/*     */   {
/* 250 */     return DBManager.delete("delete_ware_infos_byWareId", m);
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.dao.system.ware.WareInfoDAO
 * JD-Core Version:    0.6.2
 */