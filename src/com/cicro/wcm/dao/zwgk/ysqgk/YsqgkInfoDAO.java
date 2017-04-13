/*     */ package com.cicro.wcm.dao.zwgk.ysqgk;
/*     */ 
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.bean.zwgk.ysqgk.YsqgkBean;
/*     */ import com.cicro.wcm.bean.zwgk.ysqgk.YsqgkListBean;
/*     */ import com.cicro.wcm.dao.PublicTableDAO;
/*     */ import com.cicro.wcm.db.DBManager;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class YsqgkInfoDAO
/*     */ {
/*     */   public static List<YsqgkListBean> getYsqgkLists(Map<String, String> m)
/*     */   {
/*  40 */     return DBManager.queryFList("getYsqgkLists", m);
/*     */   }
/*     */ 
/*     */   public static int getYsqgkListsCount(Map<String, String> map)
/*     */   {
/*  50 */     return Integer.parseInt(DBManager.getString("getYsqgkListsCount", map));
/*     */   }
/*     */ 
/*     */   public static YsqgkBean getYsqgkBean(String ysq_id)
/*     */   {
/*  60 */     return (YsqgkBean)DBManager.queryFObj("getYsqgkBean", ysq_id);
/*     */   }
/*     */ 
/*     */   public static YsqgkBean getYsqgkBeanForQuery(Map<String, String> m)
/*     */   {
/*  70 */     return (YsqgkBean)DBManager.queryFObj("getYsqgkBeanForQuery", m);
/*     */   }
/*     */ 
/*     */   public static boolean insertYsqgkInfo(YsqgkBean ysqgk, SettingLogsBean stl)
/*     */   {
/*  81 */     if (DBManager.insert("insert_ysqgk_info", ysqgk))
/*     */     {
/*  83 */       PublicTableDAO.insertSettingLogs("添加", "依申请公开信息", "", stl);
/*  84 */       return true;
/*     */     }
/*  86 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateStatus(Map<String, String> map, SettingLogsBean stl)
/*     */   {
/*  97 */     if (DBManager.update("deal_ysqgk_info", map))
/*     */     {
/*  99 */       PublicTableDAO.insertSettingLogs("处理", "依申请公开信息", (String)map.get("ysq_id"), stl);
/* 100 */       return true;
/*     */     }
/* 102 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateYsqgkInfo(YsqgkBean ysqgk, SettingLogsBean stl)
/*     */   {
/* 112 */     if (DBManager.update("update_ysqgk_info", ysqgk))
/*     */     {
/* 114 */       PublicTableDAO.insertSettingLogs("修改", "依申请公开信息", ysqgk.getYsq_id(), stl);
/* 115 */       return true;
/*     */     }
/* 117 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean setDeleteState(Map<String, String> m, SettingLogsBean stl)
/*     */   {
/* 127 */     if (DBManager.delete("setDeleteState", m))
/*     */     {
/* 129 */       PublicTableDAO.insertSettingLogs("修改", "依申请公开信息为删除状态", (String)m.get("ysq_id"), stl);
/* 130 */       return true;
/*     */     }
/* 132 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean reBackInfos(Map<String, String> m, SettingLogsBean stl)
/*     */   {
/* 142 */     if (DBManager.delete("reBackInfos", m))
/*     */     {
/* 144 */       PublicTableDAO.insertSettingLogs("还原", "依申请公开信息", (String)m.get("ysq_id"), stl);
/* 145 */       return true;
/*     */     }
/* 147 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteYsqgkInfo(Map<String, String> m, SettingLogsBean stl)
/*     */   {
/* 157 */     if (DBManager.delete("delete_ysqgk_info", m))
/*     */     {
/* 159 */       PublicTableDAO.insertSettingLogs("彻底删除", "依申请公开信息", (String)m.get("ysq_id"), stl);
/* 160 */       return true;
/*     */     }
/* 162 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean clearDeleteYsqgkInfos(SettingLogsBean stl)
/*     */   {
/* 172 */     if (DBManager.delete("clear_hasdeleted_infos", ""))
/*     */     {
/* 174 */       PublicTableDAO.insertSettingLogs("删除", "依申请公开回收站信息", "", stl);
/* 175 */       return true;
/*     */     }
/* 177 */     return false;
/*     */   }
/*     */ 
/*     */   public static String getYsqStatistics(Map<String, String> m)
/*     */   {
/* 187 */     return DBManager.getString("getYsqStatistics", m);
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.dao.zwgk.ysqgk.YsqgkInfoDAO
 * JD-Core Version:    0.6.2
 */