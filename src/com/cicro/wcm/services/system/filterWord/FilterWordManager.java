/*     */ package com.cicro.wcm.services.system.filterWord;
/*     */ 
/*     */ import com.cicro.wcm.bean.system.filterWord.FilterWordBean;
/*     */ import com.cicro.wcm.catchs.ISyncCatch;
/*     */ import com.cicro.wcm.catchs.SyncCatchHandl;
/*     */ import com.cicro.wcm.dao.PublicTableDAO;
/*     */ import com.cicro.wcm.dao.system.filterWord.FilterWordDAO;
/*     */ import java.io.PrintStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class FilterWordManager
/*     */   implements ISyncCatch
/*     */ {
/*  23 */   private static List<FilterWordBean> fl = new ArrayList();
/*     */ 
/*     */   static {
/*  26 */     reloadCatchHandl();
/*     */   }
/*     */ 
/*     */   public void reloadCatch()
/*     */   {
/*  31 */     reloadCatchHandl();
/*     */   }
/*     */ 
/*     */   public static void reloadCatchHandl()
/*     */   {
/*  36 */     fl.clear();
/*  37 */     fl = FilterWordDAO.getFilterWordList();
/*     */   }
/*     */ 
/*     */   public static void reloadFilterWord()
/*     */   {
/*  42 */     SyncCatchHandl.reladCatchForRMI("com.cicro.wcm.services.system.filterWord.FilterWordManager");
/*     */   }
/*     */ 
/*     */   public static List<FilterWordBean> getFilterWordList()
/*     */   {
/*  47 */     return fl;
/*     */   }
/*     */ 
/*     */   public static FilterWordBean getFilterWordBean(int filterword_id)
/*     */   {
/*  56 */     if ((fl != null) && (fl.size() > 0))
/*     */     {
/*  58 */       for (FilterWordBean fwb : fl)
/*     */       {
/*  60 */         if (fwb.getFilterword_id() == filterword_id)
/*  61 */           return fwb;
/*     */       }
/*     */     }
/*  64 */     return FilterWordDAO.getFilterWordBean(filterword_id);
/*     */   }
/*     */ 
/*     */   public static boolean insertFilterWord(FilterWordBean fwd)
/*     */   {
/*  73 */     int id = PublicTableDAO.getIDByTableName(PublicTableDAO.FILTERWORD_TABLE_NAME);
/*  74 */     fwd.setFilterword_id(id);
/*     */ 
/*  76 */     return FilterWordDAO.insertFilterWord(fwd);
/*     */   }
/*     */ 
/*     */   public static boolean updateFilterWord(FilterWordBean fwd)
/*     */   {
/*  85 */     return FilterWordDAO.updateFilterWord(fwd);
/*     */   }
/*     */ 
/*     */   public static boolean deleteFilterWord(String filterword_ids)
/*     */   {
/*  95 */     return FilterWordDAO.deleteFilterWord(filterword_ids);
/*     */   }
/*     */ 
/*     */   public static List<FilterWordBean> getAllFilterWord(Map<String, String> m)
/*     */   {
/* 105 */     return FilterWordDAO.getAllFilterWord(m);
/*     */   }
/*     */ 
/*     */   public static String getFilterWordCount(Map<String, String> map)
/*     */   {
/* 115 */     return FilterWordDAO.getFilterWordCount(map);
/*     */   }
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/* 120 */     System.out.println(getFilterWordBean(2));
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.system.filterWord.FilterWordManager
 * JD-Core Version:    0.6.2
 */