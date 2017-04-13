/*     */ package com.cicro.wcm.services.template.snippet;
/*     */ 
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.bean.template.snippet.SnippetBean;
/*     */ import com.cicro.wcm.catchs.ISyncCatch;
/*     */ import com.cicro.wcm.catchs.SyncCatchHandl;
/*     */ import com.cicro.wcm.dao.PublicTableDAO;
/*     */ import com.cicro.wcm.dao.template.snippet.SnippetDAO;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.TreeMap;
/*     */ 
/*     */ public class SnippetManager
/*     */   implements ISyncCatch
/*     */ {
/*  30 */   private static TreeMap<Integer, SnippetBean> sni_map = new TreeMap();
/*     */ 
/*     */   static {
/*  33 */     reloadCatchHandl();
/*     */   }
/*     */ 
/*     */   public void reloadCatch()
/*     */   {
/*  38 */     reloadCatchHandl();
/*     */   }
/*     */ 
/*     */   public static void reloadCatchHandl()
/*     */   {
/*  43 */     sni_map.clear();
/*  44 */     List lt = SnippetDAO.getAllSnippetList();
/*  45 */     if ((lt != null) && (lt.size() > 0))
/*     */     {
/*  47 */       for (int i = 0; i < lt.size(); i++)
/*     */       {
/*  49 */         sni_map.put(Integer.valueOf(((SnippetBean)lt.get(i)).getSni_id()), (SnippetBean)lt.get(i));
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void reloadSnippet()
/*     */   {
/*  61 */     SyncCatchHandl.reladCatchForRMI("com.cicro.wcm.services.template.snippet.SnippetManager");
/*     */   }
/*     */ 
/*     */   public static List<SnippetBean> getAllSnippetList()
/*     */   {
/*  69 */     List lt = new ArrayList();
/*  70 */     Iterator it = sni_map.values().iterator();
/*  71 */     while (it.hasNext())
/*     */     {
/*  73 */       lt.add((SnippetBean)it.next());
/*     */     }
/*  75 */     return lt;
/*     */   }
/*     */ 
/*     */   public static String getSnippetCount()
/*     */   {
/*  84 */     return SnippetDAO.getSnippetCount();
/*     */   }
/*     */ 
/*     */   public static SnippetBean getSnippetBean(int ca_id)
/*     */   {
/*  93 */     if (sni_map.containsKey(Integer.valueOf(ca_id)))
/*     */     {
/*  95 */       return (SnippetBean)sni_map.get(Integer.valueOf(ca_id));
/*     */     }
/*     */ 
/*  98 */     SnippetBean ob = SnippetDAO.getSnippetBean(ca_id);
/*  99 */     sni_map.put(Integer.valueOf(ca_id), ob);
/* 100 */     return ob;
/*     */   }
/*     */ 
/*     */   public static int getInsertID()
/*     */   {
/* 110 */     return PublicTableDAO.getIDByTableName(PublicTableDAO.APPEAL_SNIPPET_TABLE_NAME);
/*     */   }
/*     */ 
/*     */   public static boolean insertSnippet(SnippetBean bean, SettingLogsBean stl)
/*     */   {
/* 118 */     if (SnippetDAO.insertSnippet(bean, stl))
/*     */     {
/* 120 */       reloadSnippet();
/* 121 */       return true;
/*     */     }
/*     */ 
/* 125 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateSnippet(SnippetBean bean, SettingLogsBean stl)
/*     */   {
/* 136 */     if (SnippetDAO.updateSnippet(bean, stl))
/*     */     {
/* 138 */       reloadSnippet();
/* 139 */       return true;
/*     */     }
/*     */ 
/* 143 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteSnippet(Map<String, String> mp, SettingLogsBean stl)
/*     */   {
/* 154 */     if (SnippetDAO.deleteSnippet(mp, stl))
/*     */     {
/* 156 */       reloadSnippet();
/* 157 */       return true;
/*     */     }
/*     */ 
/* 161 */     return false;
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.template.snippet.SnippetManager
 * JD-Core Version:    0.6.2
 */