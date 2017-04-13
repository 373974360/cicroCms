/*     */ package com.cicro.wcm.services.zwgk.ysqgk;
/*     */ 
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.bean.zwgk.ysqgk.YsqgkPhrasalBean;
/*     */ import com.cicro.wcm.catchs.ISyncCatch;
/*     */ import com.cicro.wcm.catchs.SyncCatchHandl;
/*     */ import com.cicro.wcm.dao.PublicTableDAO;
/*     */ import com.cicro.wcm.dao.zwgk.ysqgk.YsqgkPhrasalDAO;
/*     */ import java.io.PrintStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.TreeMap;
/*     */ 
/*     */ public class YsqgkPhrasaManager
/*     */   implements ISyncCatch
/*     */ {
/*  24 */   private static Map<String, YsqgkPhrasalBean> ysqpb_map = new TreeMap();
/*     */ 
/*     */   static {
/*  27 */     reloadCatchHandl();
/*     */   }
/*     */ 
/*     */   public void reloadCatch()
/*     */   {
/*  32 */     reloadCatchHandl();
/*     */   }
/*     */ 
/*     */   public static void reloadCatchHandl()
/*     */   {
/*  37 */     ysqpb_map.clear();
/*  38 */     List lt = YsqgkPhrasalDAO.getYsqgkPhrasaList();
/*     */ 
/*  40 */     if ((lt != null) && (lt.size() > 0))
/*     */     {
/*  42 */       for (int i = 0; i < lt.size(); i++)
/*     */       {
/*  44 */         ysqpb_map.put(((YsqgkPhrasalBean)lt.get(i)).getGph_id(), (YsqgkPhrasalBean)lt.get(i));
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   private static void reloadMap()
/*     */   {
/*  53 */     SyncCatchHandl.reladCatchForRMI("com.cicro.wcm.services.zwgk.ysqgk.YsqgkPhrasaManager");
/*     */   }
/*     */ 
/*     */   public static List<YsqgkPhrasalBean> getYsqgkPhrasaList()
/*     */   {
/*  62 */     List ret = new ArrayList();
/*  63 */     Iterator it = ysqpb_map.values().iterator();
/*  64 */     while (it.hasNext())
/*     */     {
/*  66 */       ret.add((YsqgkPhrasalBean)it.next());
/*     */     }
/*  68 */     Collections.sort(ret, new YsqgkPhrasaManager.CateComparator());
/*  69 */     return ret;
/*     */   }
/*     */ 
/*     */   public static List<YsqgkPhrasalBean> getYsqgkPhrasaListByType(int gph_type)
/*     */   {
/*  78 */     List ret = new ArrayList();
/*  79 */     Iterator it = ysqpb_map.values().iterator();
/*  80 */     while (it.hasNext())
/*     */     {
/*  82 */       YsqgkPhrasalBean ypb = (YsqgkPhrasalBean)it.next();
/*  83 */       if (ypb.getGph_type() == gph_type)
/*     */       {
/*  85 */         ret.add(ypb);
/*     */       }
/*     */     }
/*  88 */     Collections.sort(ret, new YsqgkPhrasaManager.CateComparator());
/*  89 */     return ret;
/*     */   }
/*     */ 
/*     */   public static String getGph_id()
/*     */   {
/*  98 */     return PublicTableDAO.getIDByTableName(PublicTableDAO.YSQGK_PHRASAL_TABLE_NAME);
/*     */   }
/*     */ 
/*     */   public static boolean insertYsqgkPhrasal(YsqgkPhrasalBean ypb, SettingLogsBean stl)
/*     */   {
/* 107 */     if (YsqgkPhrasalDAO.insertYPhrasal(ypb, stl))
/*     */     {
/* 109 */       reloadMap();
/* 110 */       return true;
/*     */     }
/*     */ 
/* 114 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateYsqgkPhrasal(YsqgkPhrasalBean ypb, SettingLogsBean stl)
/*     */   {
/* 126 */     if (YsqgkPhrasalDAO.updateYPhrasal(ypb, stl))
/*     */     {
/* 128 */       reloadMap();
/* 129 */       return true;
/*     */     }
/*     */ 
/* 133 */     return false;
/*     */   }
/*     */ 
/*     */   public static YsqgkPhrasalBean getYsqgkPhrasalById(String gph_id)
/*     */   {
/* 144 */     YsqgkPhrasalBean ypb = (YsqgkPhrasalBean)ysqpb_map.get(gph_id);
/* 145 */     return ypb;
/*     */   }
/*     */ 
/*     */   public static boolean savePhrasalSort(String gph_id, SettingLogsBean stl)
/*     */   {
/* 156 */     if (YsqgkPhrasalDAO.saveYPhrasalSort(gph_id, stl))
/*     */     {
/* 158 */       reloadMap();
/* 159 */       return true;
/*     */     }
/*     */ 
/* 163 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deletePhrasalBeans(Map<String, String> mp, SettingLogsBean stl)
/*     */   {
/* 174 */     if (YsqgkPhrasalDAO.deleteYPhrasal(mp, stl))
/*     */     {
/* 176 */       reloadMap();
/* 177 */       return true;
/*     */     }
/*     */ 
/* 181 */     return false;
/*     */   }
/*     */ 
/*     */   public static void main(String[] ages)
/*     */   {
/* 212 */     List lt = YsqgkPhrasalDAO.getYsqgkPhrasaList();
/* 213 */     System.out.println(lt.size());
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.zwgk.ysqgk.YsqgkPhrasaManager
 * JD-Core Version:    0.6.2
 */