/*     */ package com.cicro.wcm.services.system.assist;
/*     */ 
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.bean.system.assist.SourceBean;
/*     */ import com.cicro.wcm.catchs.ISyncCatch;
/*     */ import com.cicro.wcm.catchs.SyncCatchHandl;
/*     */ import com.cicro.wcm.dao.system.assist.SourceDAO;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ 
/*     */ public class SourceManager
/*     */   implements ISyncCatch
/*     */ {
/*  21 */   private static Map<Integer, SourceBean> source_map = new HashMap();
/*     */ 
/*     */   static {
/*  24 */     reloadCatchHandl();
/*     */   }
/*     */ 
/*     */   public void reloadCatch()
/*     */   {
/*  29 */     reloadCatchHandl();
/*     */   }
/*     */ 
/*     */   public static void reloadCatchHandl()
/*     */   {
/*  34 */     List source_list = SourceDAO.getAllSourceList();
/*  35 */     source_map.clear();
/*  36 */     if ((source_list != null) && (source_list.size() > 0))
/*     */     {
/*  38 */       for (int i = 0; i < source_list.size(); i++)
/*  39 */         source_map.put(Integer.valueOf(((SourceBean)source_list.get(i)).getSource_id()), (SourceBean)source_list.get(i));
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void reloadSource()
/*     */   {
/*  46 */     SyncCatchHandl.reladCatchForRMI("com.cicro.wcm.services.system.assist.SourceManager");
/*     */   }
/*     */ 
/*     */   public static SourceBean getSourceBean(int source_id)
/*     */   {
/*  56 */     if (source_map.containsKey(Integer.valueOf(source_id)))
/*     */     {
/*  58 */       return (SourceBean)source_map.get(Integer.valueOf(source_id));
/*     */     }
/*     */ 
/*  61 */     SourceBean sourceb = SourceDAO.getSourceBean(source_id);
/*  62 */     if (sourceb != null)
/*     */     {
/*  64 */       source_map.put(Integer.valueOf(source_id), sourceb);
/*  65 */       return sourceb;
/*     */     }
/*  67 */     return null;
/*     */   }
/*     */ 
/*     */   public static String getSourceCount(Map<String, String> con_map)
/*     */   {
/*  78 */     return SourceDAO.getSourceCount(con_map);
/*     */   }
/*     */ 
/*     */   public static boolean updateSource(SourceBean source, SettingLogsBean stl)
/*     */   {
/*  87 */     if (SourceDAO.updateSource(source, stl)) {
/*  88 */       source_map.remove(Integer.valueOf(source.getSource_id()));
/*  89 */       source_map.put(Integer.valueOf(source.getSource_id()), source);
/*  90 */       reloadSource();
/*  91 */       return true;
/*     */     }
/*  93 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean addSource(SourceBean source, SettingLogsBean stl)
/*     */   {
/* 102 */     if (SourceDAO.addSource(source, stl)) {
/* 103 */       source_map.put(Integer.valueOf(source.getSource_id()), source);
/* 104 */       reloadSource();
/* 105 */       return true;
/*     */     }
/* 107 */     return false;
/*     */   }
/*     */ 
/*     */   public static List<SourceBean> getAllSourceList()
/*     */   {
/* 116 */     Set set = source_map.keySet();
/* 117 */     List list = new ArrayList();
/* 118 */     for (Iterator localIterator = set.iterator(); localIterator.hasNext(); ) { int i = ((Integer)localIterator.next()).intValue();
/* 119 */       list.add((SourceBean)source_map.get(Integer.valueOf(i)));
/*     */     }
/* 121 */     return list;
/*     */   }
/*     */ 
/*     */   public static List<SourceBean> getSourceListForDB(Map<String, String> con_map)
/*     */   {
/* 131 */     return SourceDAO.getSourceListForDB(con_map);
/*     */   }
/*     */ 
/*     */   public static boolean delSourceById(String source_ids, SettingLogsBean stl)
/*     */   {
/* 140 */     if (SourceDAO.delSource(source_ids, stl)) {
/* 141 */       if (source_ids != null) {
/* 142 */         if (source_ids.indexOf(",") != -1) {
/* 143 */           for (String id : source_ids.split(","))
/* 144 */             source_map.remove(Integer.valueOf(Integer.parseInt(id)));
/*     */         }
/*     */         else {
/* 147 */           source_map.remove(Integer.valueOf(Integer.parseInt(source_ids)));
/*     */         }
/*     */       }
/* 150 */       reloadSource();
/* 151 */       return true;
/*     */     }
/* 153 */     return false;
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.system.assist.SourceManager
 * JD-Core Version:    0.6.2
 */