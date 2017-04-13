/*     */ package com.cicro.wcm.dao.system.ware;
/*     */ 
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.bean.system.ware.WareBean;
/*     */ import com.cicro.wcm.bean.system.ware.WareVerBean;
/*     */ import com.cicro.wcm.dao.PublicTableDAO;
/*     */ import com.cicro.wcm.db.DBManager;
/*     */ import com.cicro.wcm.server.ServerManager;
/*     */ import java.io.PrintStream;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class WareDAO
/*     */ {
/* 163 */   private static String FIELD_TABLE_NAME = "cs_ware_ver";
/*     */ 
/*     */   public static List<WareBean> getWareListBySiteID(String site_id)
/*     */   {
/*  18 */     return DBManager.queryFList("getWareListBySiteID", site_id);
/*     */   }
/*     */ 
/*     */   public static List<WareBean> getTimerWareList(String current_time)
/*     */   {
/*  23 */     Map m = new HashMap();
/*  24 */     m.put("current_time", current_time);
/*  25 */     String ip = ServerManager.LOCAL_IP;
/*  26 */     if ((ip != null) && (!"".equals(ip)) && (!"127.0.0.1".equals(ip)))
/*  27 */       m.put("server_ip", ServerManager.LOCAL_IP);
/*  28 */     return DBManager.queryFList("getTimerWareList", m);
/*     */   }
/*     */ 
/*     */   public static List<WareBean> getAllWareList()
/*     */   {
/*  33 */     return DBManager.queryFList("getAllWareList", "");
/*     */   }
/*     */ 
/*     */   public static WareBean getWareByID(String id)
/*     */   {
/*  38 */     return (WareBean)DBManager.queryFObj("getWareByID", id);
/*     */   }
/*     */ 
/*     */   public static WareBean getWareBeanByWareID(Map<String, String> m)
/*     */   {
/*  43 */     return (WareBean)DBManager.queryFObj("getWareBeanByWareID", m);
/*     */   }
/*     */ 
/*     */   public static boolean cloneWare(WareBean wb)
/*     */   {
/*  48 */     return DBManager.insert("insertWare", wb);
/*     */   }
/*     */ 
/*     */   public static boolean insertWare(WareBean wb, SettingLogsBean stl)
/*     */   {
/*  53 */     if (DBManager.insert("insertWare", wb))
/*     */     {
/*  55 */       PublicTableDAO.insertSettingLogs("添加", "信息标签", wb.getId(), stl);
/*  56 */       return true;
/*     */     }
/*     */ 
/*  59 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateWare(WareBean wb, SettingLogsBean stl)
/*     */   {
/*  64 */     if (DBManager.update("updateWare", wb))
/*     */     {
/*  66 */       PublicTableDAO.insertSettingLogs("修改", "信息标签", wb.getId(), stl);
/*  67 */       return true;
/*     */     }
/*     */ 
/*  70 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateWareContent(WareBean wb, SettingLogsBean stl)
/*     */   {
/*  75 */     if (DBManager.update("updateWareContent", wb))
/*     */     {
/*  77 */       PublicTableDAO.insertSettingLogs("修改", "信息标签内容", wb.getId(), stl);
/*  78 */       return true;
/*     */     }
/*     */ 
/*  81 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateWareInfoNum(Map<String, String> m)
/*     */   {
/*  86 */     return DBManager.update("update_ware_infoNum", m);
/*     */   }
/*     */ 
/*     */   public static boolean updateWareTime(Map<String, String> m)
/*     */   {
/*  91 */     return DBManager.update("updateWareTime", m);
/*     */   }
/*     */ 
/*     */   public static boolean savaWareSort(WareBean wb, SettingLogsBean stl)
/*     */   {
/*  96 */     if (DBManager.update("saveWareSort", wb))
/*     */     {
/*  98 */       PublicTableDAO.insertSettingLogs("修改", "信息标签排序", wb.getId(), stl);
/*  99 */       return true;
/*     */     }
/*     */ 
/* 102 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteWare(Map<String, String> mp, SettingLogsBean stl)
/*     */   {
/* 107 */     if (DBManager.delete("deleteWare", mp))
/*     */     {
/* 109 */       PublicTableDAO.insertSettingLogs("删除", "信息标签", (String)mp.get("ids"), stl);
/* 110 */       return true;
/*     */     }
/*     */ 
/* 113 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteWareByWcatIDS(Map<String, String> mp, SettingLogsBean stl)
/*     */   {
/* 118 */     if (DBManager.delete("deleteWareByWcatIDS", mp))
/*     */     {
/* 120 */       PublicTableDAO.insertSettingLogs("删除", "信息标签", (String)mp.get("wcat_ids"), stl);
/* 121 */       return true;
/*     */     }
/*     */ 
/* 124 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean moveWareToOtherCategory(Map<String, String> mp, SettingLogsBean stl)
/*     */   {
/* 129 */     if (DBManager.update("moveWareToOtherCategory", mp))
/*     */     {
/* 131 */       PublicTableDAO.insertSettingLogs("转移", "信息标签", (String)mp.get("ware_ids"), stl);
/* 132 */       return true;
/*     */     }
/*     */ 
/* 135 */     return false;
/*     */   }
/*     */ 
/*     */   public static List<WareVerBean> getWareVerList(Map<String, String> m)
/*     */   {
/* 144 */     return DBManager.queryFList("getWareVerList", m);
/*     */   }
/*     */ 
/*     */   public static String getWareVerListCount(String ware_id)
/*     */   {
/* 149 */     Map m = new HashMap();
/* 150 */     m.put("ware_id", ware_id);
/* 151 */     return DBManager.getString("getWareVerListCount", m);
/*     */   }
/*     */ 
/*     */   public static String getWareVerNum(String ware_id, String site_id)
/*     */   {
/* 156 */     Map map = new HashMap();
/* 157 */     map.put("ware_id", ware_id);
/* 158 */     map.put("site_id", site_id);
/* 159 */     return DBManager.getString("getWareVerNum", map);
/*     */   }
/*     */ 
/*     */   public static boolean addWareVer(WareVerBean wvb, SettingLogsBean stl)
/*     */   {
/* 167 */     int tableId = PublicTableDAO.getIDByTableName(FIELD_TABLE_NAME);
/* 168 */     wvb.setId(tableId);
/* 169 */     if (DBManager.insert("addWareVer", wvb))
/*     */     {
/* 171 */       PublicTableDAO.insertSettingLogs("添加", "备份标签", wvb.getWare_id(), stl);
/* 172 */       return true;
/*     */     }
/* 174 */     return false;
/*     */   }
/*     */ 
/*     */   public static WareVerBean getWareObjbyVerNum(String wareid, String siteid, String verNum)
/*     */   {
/* 180 */     Map m = new HashMap();
/* 181 */     m.put("ware_id", wareid);
/* 182 */     m.put("site_id", siteid);
/* 183 */     m.put("ware_ver", verNum);
/* 184 */     return (WareVerBean)DBManager.queryFObj("getWareObjbyVerNum", m);
/*     */   }
/*     */ 
/*     */   public static String getWareIDBywareID(String ware_id)
/*     */   {
/* 189 */     Map m = new HashMap();
/* 190 */     m.put("ware_id", ware_id);
/* 191 */     return DBManager.getString("getWareIDBywareID", m);
/*     */   }
/*     */ 
/*     */   public static void main(String[] arg)
/*     */   {
/* 197 */     System.out.println(getTimerWareList("2011-05,21 00:00:00"));
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.dao.system.ware.WareDAO
 * JD-Core Version:    0.6.2
 */