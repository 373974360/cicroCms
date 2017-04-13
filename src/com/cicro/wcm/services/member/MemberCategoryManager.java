/*     */ package com.cicro.wcm.services.member;
/*     */ 
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.bean.member.MemberCategoryBean;
/*     */ import com.cicro.wcm.catchs.ISyncCatch;
/*     */ import com.cicro.wcm.catchs.SyncCatchHandl;
/*     */ import com.cicro.wcm.dao.member.MemberDAO;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.TreeMap;
/*     */ 
/*     */ public class MemberCategoryManager
/*     */   implements ISyncCatch
/*     */ {
/*  19 */   private static Map<String, MemberCategoryBean> cate_map = new TreeMap();
/*     */ 
/*     */   static {
/*  22 */     reloadCatchHandl();
/*     */   }
/*     */ 
/*     */   public void reloadCatch()
/*     */   {
/*  27 */     reloadCatchHandl();
/*     */   }
/*     */ 
/*     */   public static void reloadCatchHandl()
/*     */   {
/*  32 */     cate_map.clear();
/*  33 */     List lt = MemberDAO.getAllMemberCategoryList();
/*  34 */     if ((lt != null) && (lt.size() > 0))
/*     */     {
/*  36 */       for (int i = 0; i < lt.size(); i++)
/*     */       {
/*  38 */         cate_map.put(((MemberCategoryBean)lt.get(i)).getMcat_id(), (MemberCategoryBean)lt.get(i));
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void reloadMap()
/*     */   {
/*  48 */     SyncCatchHandl.reladCatchForRMI("com.cicro.wcm.services.member.MemberCategoryManager");
/*     */   }
/*     */ 
/*     */   public static List<MemberCategoryBean> getAllMemberCateGoryList()
/*     */   {
/*  57 */     List ret = new ArrayList();
/*  58 */     Iterator it = cate_map.values().iterator();
/*  59 */     while (it.hasNext())
/*     */     {
/*  61 */       ret.add((MemberCategoryBean)it.next());
/*     */     }
/*  63 */     Collections.sort(ret, new MemberCategoryManager.CateComparator());
/*     */ 
/*  65 */     return ret;
/*     */   }
/*     */ 
/*     */   public static MemberCategoryBean getMemberCategoryByID(String id)
/*     */   {
/*  75 */     MemberCategoryBean mcb = (MemberCategoryBean)cate_map.get(id);
/*  76 */     return mcb;
/*     */   }
/*     */ 
/*     */   public static boolean insertMemberCategory(MemberCategoryBean mcb, SettingLogsBean stl)
/*     */   {
/*  85 */     if (MemberDAO.insertMemberCategory(mcb, stl))
/*     */     {
/*  87 */       reloadMap();
/*  88 */       return true;
/*     */     }
/*     */ 
/*  92 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateMemberCategory(MemberCategoryBean mcb, SettingLogsBean stl)
/*     */   {
/* 104 */     if (MemberDAO.updateMemberCategory(mcb, stl))
/*     */     {
/* 106 */       reloadMap();
/* 107 */       return true;
/*     */     }
/*     */ 
/* 111 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean saveMemberCategorySort(String mcat_id, SettingLogsBean stl)
/*     */   {
/* 123 */     if (MemberDAO.saveMemberCategorySort(mcat_id, stl))
/*     */     {
/* 125 */       reloadMap();
/* 126 */       return true;
/*     */     }
/*     */ 
/* 130 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteMemberCategory(Map<String, String> mp, SettingLogsBean stl)
/*     */   {
/* 142 */     if (MemberDAO.deleteMemberCategory(mp, stl))
/*     */     {
/* 144 */       reloadMap();
/* 145 */       return true;
/*     */     }
/*     */ 
/* 149 */     return false;
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.member.MemberCategoryManager
 * JD-Core Version:    0.6.2
 */