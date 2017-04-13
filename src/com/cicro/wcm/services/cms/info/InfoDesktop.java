/*     */ package com.cicro.wcm.services.cms.info;
/*     */ 
/*     */ import com.cicro.wcm.bean.cms.category.CategoryBean;
/*     */ import com.cicro.wcm.dao.cms.info.InfoDAO;
/*     */ import com.cicro.wcm.dao.zwgk.info.GKInfoDAO;
/*     */ import com.cicro.wcm.services.cms.category.CategoryManager;
/*     */ import com.cicro.wcm.services.cms.category.CategoryUtil;
/*     */ import com.cicro.wcm.services.cms.workflow.WorkFlowManager;
/*     */ import java.io.PrintStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class InfoDesktop
/*     */ {
/*     */   public static Map<String, Object> getWaitVerifyInfoList(Map<String, String> m)
/*     */   {
/*  39 */     int user_id = Integer.parseInt((String)m.get("user_id"));
/*  40 */     String site_id = (String)m.get("site_id");
/*  41 */     Map return_map = new HashMap();
/*     */ 
/*  44 */     String cat_ids = CategoryUtil.getCategoryIDSByUser(0, site_id, user_id);
/*     */ 
/*  46 */     String sql = getWaitVerifyInfoSql(user_id, cat_ids, (String)m.get("app_id"), site_id);
/*     */ 
/*  48 */     if ((sql == null) || ("".equals(sql)))
/*     */     {
/*  50 */       List l = new ArrayList();
/*  51 */       return_map.put("info_count", "0");
/*  52 */       return_map.put("info_list", l);
/*     */     }
/*     */     else
/*     */     {
/*  56 */       m.put("searchString2", sql);
/*  57 */       if ("cms".equals(m.get("app_id")))
/*     */       {
/*  59 */         return_map.put("info_count", Integer.valueOf(InfoDAO.getInfoCount(m)));
/*  60 */         return_map.put("info_List", InfoDAO.getInfoBeanList(m));
/*     */       }
/*     */       else {
/*  63 */         return_map.put("info_count", GKInfoDAO.getGKInfoCount(m));
/*  64 */         return_map.put("info_List", GKInfoDAO.getGKInfoList(m));
/*     */       }
/*     */     }
/*  67 */     return return_map;
/*     */   }
/*     */ 
/*     */   public static String getWaitVerifyInfoSql(String user_id, String category_ids, String app_id, String site_id)
/*     */   {
/*  80 */     String conn = "";
/*  81 */     String[] tempA = category_ids.split(",");
/*  82 */     if ((tempA != null) && (tempA.length > 0))
/*     */     {
/*  84 */       for (int i = 0; i < tempA.length; i++)
/*     */       {
/*  86 */         if ((tempA[i] != null) && (!"".equals(tempA[i])))
/*     */         {
/*  88 */           CategoryBean cb = CategoryManager.getCategoryBeanCatID(Integer.parseInt(tempA[i]), site_id);
/*  89 */           if (cb != null)
/*     */           {
/*  91 */             if (cb.getWf_id() != 0)
/*     */             {
/*  94 */               int step_id = WorkFlowManager.getMaxStepIDByUserID(cb.getWf_id(), user_id, app_id, site_id);
/*  95 */               conn = conn + "or (ci.cat_id = " + tempA[i] + " and ci.step_id < " + step_id + ") ";
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/* 100 */       if ((conn != null) && (!"".equals(conn)))
/*     */       {
/* 102 */         conn = conn.substring(2);
/* 103 */         conn = " and (" + conn + ")";
/*     */       }
/*     */     }
/*     */ 
/* 107 */     return conn;
/*     */   }
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/* 112 */     Map con_map = new HashMap();
/* 113 */     con_map.put("user_id", "199");
/* 114 */     con_map.put("info_status", "2");
/* 115 */     con_map.put("final_status", "0");
/* 116 */     con_map.put("start_num", "0");
/* 117 */     con_map.put("page_size", "15");
/* 118 */     con_map.put("sort_name", "ci.info_id");
/* 119 */     con_map.put("sort_type", "desc");
/* 120 */     con_map.put("site_id", "GKxfj");
/* 121 */     con_map.put("app_id", "zwgk");
/* 122 */     System.out.println(getWaitVerifyInfoList(con_map));
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.cms.info.InfoDesktop
 * JD-Core Version:    0.6.2
 */