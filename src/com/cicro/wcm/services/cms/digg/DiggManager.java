/*     */ package com.cicro.wcm.services.cms.digg;
/*     */ 
/*     */ import com.cicro.util.DateUtil;
/*     */ import com.cicro.wcm.bean.cms.digg.InfoDiggBean;
/*     */ import com.cicro.wcm.bean.cms.digg.InfoDiggLogBean;
/*     */ import com.cicro.wcm.dao.cms.digg.DiggDAO;
/*     */ import com.cicro.wcm.services.cms.category.CategoryManager;
/*     */ import java.io.PrintStream;
/*     */ import java.util.Calendar;
/*     */ import java.util.Date;
/*     */ import java.util.GregorianCalendar;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class DiggManager
/*     */ {
/*     */   public static String getInfoDiggListCnt(Map<String, String> mp)
/*     */   {
/*  35 */     if ((mp.get("start_day") == null) || ("".equals(mp.get("start_day"))))
/*     */     {
/*  37 */       Calendar c = new GregorianCalendar();
/*  38 */       c.add(1, -1);
/*  39 */       mp.remove("start_day");
/*  40 */       String start_time = DateUtil.getDateString(c.getTime()) + " 00:00:00";
/*  41 */       mp.put("start_day", start_time);
/*     */     }
/*  43 */     String cat_id = (String)mp.get("cat_id");
/*  44 */     if ((cat_id != null) && (!"".equals(cat_id)))
/*     */     {
/*  46 */       String ids = CategoryManager.getAllChildCategoryIDS(cat_id, (String)mp.get("site_id"));
/*  47 */       if ((ids != null) && (!"".equals(ids)))
/*     */       {
/*  49 */         cat_id = cat_id + "," + ids;
/*     */       }
/*  51 */       mp.remove("cat_id");
/*  52 */       mp.put("cat_id", cat_id);
/*     */     }
/*  54 */     return DiggDAO.getInfoDiggCnt(mp);
/*     */   }
/*     */ 
/*     */   public static List<InfoDiggBean> getInfoDiggList(Map<String, String> mp)
/*     */   {
/*  65 */     if ((mp.get("start_day") == null) || ("".equals(mp.get("start_day"))))
/*     */     {
/*  67 */       Calendar c = new GregorianCalendar();
/*  68 */       c.add(1, -1);
/*  69 */       mp.remove("start_day");
/*  70 */       String start_time = DateUtil.getDateString(c.getTime()) + " 00:00:00";
/*  71 */       mp.put("start_day", start_time);
/*     */     }
/*  73 */     String cat_id = (String)mp.get("cat_id");
/*  74 */     if ((cat_id != null) && (!"".equals(cat_id)))
/*     */     {
/*  76 */       String ids = CategoryManager.getAllChildCategoryIDS(cat_id, (String)mp.get("site_id"));
/*  77 */       if ((ids != null) && (!"".equals(ids)))
/*     */       {
/*  79 */         cat_id = cat_id + "," + ids;
/*     */       }
/*  81 */       mp.remove("cat_id");
/*  82 */       mp.put("cat_id", cat_id);
/*     */     }
/*  84 */     return DiggDAO.getInfoDigg(mp);
/*     */   }
/*     */ 
/*     */   public static InfoDiggBean getInfoDigg(String info_id)
/*     */   {
/*  89 */     return DiggDAO.getInfoDigg(info_id);
/*     */   }
/*     */ 
/*     */   public static boolean recordInfoDigg(InfoDiggBean digg)
/*     */   {
/* 100 */     String info_id = digg.getInfo_id();
/* 101 */     if (getInfoDigg(info_id) == null)
/*     */     {
/* 103 */       return DiggDAO.insertInfoDigg(digg);
/*     */     }
/*     */ 
/* 107 */     return DiggDAO.updateInfoDigg(digg);
/*     */   }
/*     */ 
/*     */   public static boolean insertInfoDiggLog(InfoDiggLogBean log)
/*     */   {
/* 121 */     String diggDtime = DateUtil.getDateTimeString(new Date());
/* 122 */     log.setDigg_dtime(diggDtime);
/* 123 */     return DiggDAO.insertInfoDiggLog(log);
/*     */   }
/*     */ 
/*     */   public static void main(String[] args) {
/* 127 */     String cat_id = "354";
/* 128 */     String ids = CategoryManager.getAllChildCategoryIDS("354", "11111ddd");
/* 129 */     if ((ids != null) && (!"".equals(ids)))
/*     */     {
/* 131 */       cat_id = cat_id + "," + ids;
/*     */     }
/* 133 */     Map mp = new HashMap();
/* 134 */     mp.put("cat_id", cat_id);
/* 135 */     mp.put("site_id", "11111ddd");
/*     */ 
/* 137 */     mp.put("page_size", "15");
/* 138 */     mp.put("start_num", "0");
/* 139 */     List lt = getInfoDiggList(mp);
/* 140 */     System.out.println(lt.size());
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.cms.digg.DiggManager
 * JD-Core Version:    0.6.2
 */