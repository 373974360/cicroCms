/*     */ package com.cicro.wcm.services.system.ware;
/*     */ 
/*     */ import com.cicro.util.DateUtil;
/*     */ import com.cicro.wcm.bean.cms.info.InfoBean;
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.bean.system.ware.WareBean;
/*     */ import com.cicro.wcm.bean.system.ware.WareInfoBean;
/*     */ import com.cicro.wcm.bean.system.ware.WareInfoRef;
/*     */ import com.cicro.wcm.bean.system.ware.WareInfos;
/*     */ import com.cicro.wcm.dao.PublicTableDAO;
/*     */ import com.cicro.wcm.dao.system.ware.WareDAO;
/*     */ import com.cicro.wcm.dao.system.ware.WareInfoDAO;
/*     */ import java.io.PrintStream;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class WareInfoManager
/*     */ {
/*     */   public static String getWareInfoRefCount(Map<String, String> m)
/*     */   {
/*  38 */     return WareInfoDAO.getWareInfoRefCount(m);
/*     */   }
/*     */ 
/*     */   public static List<InfoBean> getWareInfoRefList(Map<String, String> m)
/*     */   {
/*  48 */     return WareInfoDAO.getWareInfoRefList(m);
/*     */   }
/*     */ 
/*     */   public static List<WareBean> getWareListByRefInfo(String info_id, String app_id, String site_id)
/*     */   {
/*  60 */     Map m = new HashMap();
/*  61 */     m.put("info_id", info_id);
/*  62 */     m.put("app_id", app_id);
/*  63 */     m.put("site_id", site_id);
/*  64 */     return WareInfoDAO.getWareListByRefInfo(m);
/*     */   }
/*     */ 
/*     */   public static boolean insertWareInfoRef(String info_ids, int ware_id, int user_id, String app_id, String site_id, SettingLogsBean stl)
/*     */   {
/*     */     try
/*     */     {
/*  80 */       if ((info_ids != null) && (!"".equals(info_ids)))
/*     */       {
/*  82 */         String[] tempA = info_ids.split(",");
/*  83 */         WareInfoRef wir = new WareInfoRef();
/*  84 */         wir.setWare_id(ware_id);
/*  85 */         wir.setSite_id(site_id);
/*  86 */         wir.setApp_id(app_id);
/*  87 */         wir.setRef_user(user_id);
/*  88 */         wir.setUpdate_dtime(DateUtil.getCurrentDateTime());
/*  89 */         for (int i = 0; i < tempA.length; i++)
/*     */         {
/*  91 */           wir.setInfo_id(Integer.parseInt(tempA[i]));
/*  92 */           WareInfoDAO.insertWareInfoRef(wir);
/*     */         }
/*  94 */         PublicTableDAO.insertSettingLogs("添加", "推荐信息", info_ids, stl);
/*  95 */         return true;
/*     */       }
/*  97 */       return false;
/*     */     }
/*     */     catch (Exception e) {
/* 100 */       e.printStackTrace();
/* 101 */     }return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteWareInfoRef(String info_ids, String ware_ids, String app_id, String site_id, SettingLogsBean stl)
/*     */   {
/* 113 */     Map m = new HashMap();
/* 114 */     m.put("app_id", app_id);
/* 115 */     m.put("site_id", site_id);
/* 116 */     if ((info_ids != null) && (!"".equals(info_ids.trim())))
/* 117 */       m.put("info_ids", info_ids);
/* 118 */     if ((ware_ids != null) && (!"".equals(ware_ids.trim()))) {
/* 119 */       m.put("ware_ids", ware_ids);
/*     */     }
/* 121 */     return WareInfoDAO.deleteWareInfoRef(m, stl);
/*     */   }
/*     */ 
/*     */   public static boolean insertWareInfoByRowNum(int ware_id, int row_num, String app_id, String site_id)
/*     */   {
/*     */     try
/*     */     {
/* 138 */       WareInfoBean wib = new WareInfoBean();
/* 139 */       wib.setApp_id(app_id);
/* 140 */       wib.setSite_id(site_id);
/* 141 */       wib.setWare_id(ware_id);
/* 142 */       for (int i = 0; i < row_num; i++)
/*     */       {
/* 144 */         wib.setSort_id(i + 1);
/* 145 */         WareInfoDAO.insertWareInfo(wib);
/*     */       }
/* 147 */       return true;
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 151 */       e.printStackTrace();
/* 152 */     }return false;
/*     */   }
/*     */ 
/*     */   public static int insertWareInfo(int ware_id, int sort_id, int info_num, String app_id, String site_id)
/*     */   {
/* 167 */     WareInfoBean wib = new WareInfoBean();
/* 168 */     int id = PublicTableDAO.getIDByTableName(PublicTableDAO.WARE_INFO_TABLE_NAME);
/* 169 */     wib.setWinfo_id(id);
/* 170 */     wib.setApp_id(app_id);
/* 171 */     wib.setSite_id(site_id);
/* 172 */     wib.setWare_id(ware_id);
/* 173 */     wib.setSort_id(sort_id);
/* 174 */     if (WareInfoDAO.insertWareInfo(wib))
/*     */     {
/* 176 */       Map m = new HashMap();
/* 177 */       m.put("app_id", app_id);
/* 178 */       m.put("site_id", site_id);
/* 179 */       m.put("ware_id", ware_id);
/* 180 */       m.put("info_num", info_num);
/* 181 */       WareDAO.updateWareInfoNum(m);
/* 182 */       return id;
/*     */     }
/* 184 */     return 0;
/*     */   }
/*     */ 
/*     */   public static boolean sortWareInfo(String winfo_ids)
/*     */   {
/* 194 */     return WareInfoDAO.sortWareInfo(winfo_ids);
/*     */   }
/*     */ 
/*     */   public static boolean deleteWareInfoByWInfoID(int ware_id, String winfo_id, int info_num, String app_id, String site_id, SettingLogsBean stl)
/*     */   {
/* 209 */     if (WareInfoDAO.deleteWareInfoByWInfoID(winfo_id, stl))
/*     */     {
/* 211 */       Map m = new HashMap();
/* 212 */       m.put("app_id", app_id);
/* 213 */       m.put("site_id", site_id);
/* 214 */       m.put("ware_id", ware_id);
/* 215 */       m.put("info_num", info_num);
/* 216 */       WareDAO.updateWareInfoNum(m);
/* 217 */       return true;
/*     */     }
/* 219 */     return false;
/*     */   }
/*     */ 
/*     */   public static List<WareInfoBean> getWareInfoList(Map<String, String> m)
/*     */   {
/* 230 */     List l = WareInfoDAO.getWareInfoList(m);
/* 231 */     if ((l != null) && (l.size() > 0))
/*     */     {
/*     */       Iterator localIterator2;
/* 233 */       label110: for (Iterator localIterator1 = l.iterator(); localIterator1.hasNext(); 
/* 238 */         localIterator2.hasNext())
/*     */       {
/* 233 */         WareInfoBean wib = (WareInfoBean)localIterator1.next();
/*     */ 
/* 235 */         List info_list = wib.getInfos_list();
/* 236 */         if ((info_list == null) || (info_list.size() <= 0))
/*     */           break label110;
/* 238 */         localIterator2 = info_list.iterator(); continue; WareInfos info = (WareInfos)localIterator2.next();
/*     */ 
/* 240 */         info.setTitle(info.getTitle().replaceAll("<[Bb][Rr]/?>", ""));
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 245 */     return l;
/*     */   }
/*     */ 
/*     */   public static WareInfos getWareInfosBean(int id)
/*     */   {
/* 255 */     return WareInfoDAO.getWareInfosBean(id);
/*     */   }
/*     */ 
/*     */   public static int getNewWareInfosID()
/*     */   {
/* 264 */     return WareInfoDAO.getNewWareInfosID();
/*     */   }
/*     */ 
/*     */   public static boolean insertWareInfos(WareInfos wif, SettingLogsBean stl)
/*     */   {
/* 275 */     return WareInfoDAO.insertWareInfos(wif, stl);
/*     */   }
/*     */ 
/*     */   public static boolean updateWareInfos(WareInfos wif, SettingLogsBean stl)
/*     */   {
/* 286 */     return WareInfoDAO.updateWareInfos(wif, stl);
/*     */   }
/*     */ 
/*     */   public static boolean sortWInfos(String ids)
/*     */   {
/* 296 */     return WareInfoDAO.sortWInfos(ids);
/*     */   }
/*     */ 
/*     */   public static boolean deleteWareInfosByID(int id, SettingLogsBean stl)
/*     */   {
/* 307 */     return WareInfoDAO.deleteWareInfosByID(id, stl);
/*     */   }
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/* 313 */     Map m = new HashMap();
/* 314 */     m.put("ware_id", "115");
/* 315 */     m.put("app_id", "cms");
/* 316 */     m.put("site_id", "11111ddd");
/* 317 */     System.out.println(((WareInfoBean)getWareInfoList(m).get(5)).getInfos_list());
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.system.ware.WareInfoManager
 * JD-Core Version:    0.6.2
 */