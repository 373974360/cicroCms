/*     */ package com.cicro.wcm.dao.system.ware;
/*     */ 
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.bean.system.ware.WareReleUser;
/*     */ import com.cicro.wcm.dao.PublicTableDAO;
/*     */ import com.cicro.wcm.db.DBManager;
/*     */ import java.io.PrintStream;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class WareReleUserDAO
/*     */ {
/*     */   public static List<WareReleUser> getWareReleUserList()
/*     */   {
/*  27 */     return DBManager.queryFList("getWareReleUserList", "");
/*     */   }
/*     */ 
/*     */   public static boolean insertWareReleUserByCat(int wcat_id, String usre_ids, String group_ids, String app_id, String site_id, SettingLogsBean stl)
/*     */   {
/*  43 */     if (deleteWRUByCat(wcat_id, site_id)) {
/*     */       try {
/*  45 */         System.out.println("dao--------usre_ids--------" + usre_ids);
/*  46 */         if ((usre_ids != null) && (!"".equals(usre_ids)))
/*     */         {
/*  48 */           String[] userArr = usre_ids.split(",");
/*  49 */           insertWRUByCatHandl(wcat_id, userArr, 0, app_id, site_id);
/*     */         }
/*  51 */         if ((group_ids != null) && (!"".equals(group_ids)))
/*     */         {
/*  53 */           String[] groupArr = group_ids.split(",");
/*  54 */           insertWRUByCatHandl(wcat_id, groupArr, 1, app_id, site_id);
/*     */         }
/*  56 */         PublicTableDAO.insertSettingLogs("添加", "标签分类与用户关联，标签分类", wcat_id, stl);
/*  57 */         return true;
/*     */       }
/*     */       catch (Exception e)
/*     */       {
/*  61 */         e.printStackTrace();
/*  62 */         return false;
/*     */       }
/*     */     }
/*  65 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean insertWRUByCatHandl(int wcat_id, String[] tempA, int priv_type, String app_id, String site_id)
/*     */   {
/*  79 */     System.out.println("insertWRUByCatHandl--------tempA--------" + tempA);
/*     */     try {
/*  81 */       for (int i = 0; i < tempA.length; i++)
/*     */       {
/*  83 */         WareReleUser wru = new WareReleUser();
/*  84 */         wru.setId(PublicTableDAO.getIDByTableName(PublicTableDAO.WARE_RELE_USER_TABLE_NAME));
/*  85 */         wru.setWcat_id(wcat_id);
/*  86 */         wru.setPriv_type(priv_type);
/*  87 */         wru.setSite_id(site_id);
/*  88 */         wru.setApp_id(app_id);
/*  89 */         wru.setPrv_id(Integer.parseInt(tempA[i]));
/*  90 */         DBManager.insert("insert_ware_user", wru);
/*     */       }
/*  92 */       return true;
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/*  96 */       e.printStackTrace();
/*  97 */     }return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteWRUByCat(String wcat_id, String site_id)
/*     */   {
/* 109 */     Map m = new HashMap();
/* 110 */     m.put("wcat_id", wcat_id);
/* 111 */     m.put("site_id", site_id);
/* 112 */     return DBManager.delete("delete_ware_user_byCat", m);
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.dao.system.ware.WareReleUserDAO
 * JD-Core Version:    0.6.2
 */