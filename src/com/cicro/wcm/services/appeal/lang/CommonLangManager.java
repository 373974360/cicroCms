/*     */ package com.cicro.wcm.services.appeal.lang;
/*     */ 
/*     */ import com.cicro.wcm.bean.appeal.lang.CommonLangBean;
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.dao.appeal.lang.CommonLangDAO;
/*     */ import java.io.PrintStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class CommonLangManager
/*     */ {
/*  17 */   private static Map<String, CommonLangBean> lang_mp = new HashMap();
/*     */ 
/*     */   static
/*     */   {
/*  21 */     reload();
/*     */   }
/*     */ 
/*     */   private static void reload()
/*     */   {
/*  29 */     lang_mp.clear();
/*  30 */     List lt = CommonLangDAO.getAllCommonLangList();
/*  31 */     if ((lt != null) && (lt.size() > 0))
/*     */     {
/*  33 */       for (int i = 0; i < lt.size(); i++)
/*     */       {
/*  35 */         lang_mp.put(((CommonLangBean)lt.get(i)).getPh_id(), (CommonLangBean)lt.get(i));
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public static List<CommonLangBean> getCommonLangList(Map<String, String> mp)
/*     */   {
/*  47 */     if ("all".equals(mp.get("type")))
/*     */     {
/*  49 */       return CommonLangDAO.getAllCommonLangListByDB(mp);
/*     */     }
/*     */ 
/*  53 */     return CommonLangDAO.getCommonLangList(mp);
/*     */   }
/*     */ 
/*     */   public static String getCommonLangCnt(Map<String, String> mp)
/*     */   {
/*  64 */     return CommonLangDAO.getCommonLangCnt(mp);
/*     */   }
/*     */ 
/*     */   public static CommonLangBean getCommonLangByID(String id)
/*     */   {
/*  74 */     CommonLangBean ret = (CommonLangBean)lang_mp.get(id);
/*  75 */     if (ret == null)
/*     */     {
/*  77 */       ret = CommonLangDAO.getCommonLangByID(id);
/*  78 */       lang_mp.put(id, ret);
/*     */     }
/*  80 */     return ret;
/*     */   }
/*     */ 
/*     */   public static List<CommonLangBean> getCommonLangListByType(int type)
/*     */   {
/*  90 */     List ret = new ArrayList();
/*  91 */     Iterator it = lang_mp.values().iterator();
/*  92 */     while (it.hasNext())
/*     */     {
/*  94 */       CommonLangBean clb = (CommonLangBean)it.next();
/*  95 */       if (clb.getPh_type() == type)
/*     */       {
/*  97 */         ret.add(clb);
/*     */       }
/*     */     }
/* 100 */     Collections.sort(ret, new CommonLangManager.CommonLangComparator());
/* 101 */     return ret;
/*     */   }
/*     */ 
/*     */   public static boolean insertCommonLang(CommonLangBean clb, SettingLogsBean stl)
/*     */   {
/* 112 */     if (CommonLangDAO.insertCommonLang(clb, stl))
/*     */     {
/* 114 */       reload();
/* 115 */       return true;
/*     */     }
/*     */ 
/* 119 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateCommonLang(CommonLangBean clb, SettingLogsBean stl)
/*     */   {
/* 131 */     if (CommonLangDAO.updateCommonLang(clb, stl))
/*     */     {
/* 133 */       reload();
/* 134 */       return true;
/*     */     }
/*     */ 
/* 138 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean saveSort(String ids, SettingLogsBean stl)
/*     */   {
/* 150 */     CommonLangBean clb = new CommonLangBean();
/* 151 */     if ((ids != null) && (ids.trim() != ""))
/*     */     {
/* 153 */       String[] arrIDS = ids.trim().split(",");
/* 154 */       for (int i = 0; i < arrIDS.length; i++)
/*     */       {
/* 156 */         clb.setSort_id(i + 1);
/* 157 */         clb.setPh_id(arrIDS[i]);
/* 158 */         if (!CommonLangDAO.updateCommonLangSort(clb, stl))
/*     */         {
/* 160 */           reload();
/* 161 */           return false;
/*     */         }
/*     */       }
/*     */     }
/* 165 */     reload();
/* 166 */     return true;
/*     */   }
/*     */ 
/*     */   public static boolean deleteCommonLang(Map<String, String> mp, SettingLogsBean stl)
/*     */   {
/* 177 */     if (CommonLangDAO.deleteCommonLang(mp, stl))
/*     */     {
/* 179 */       reload();
/* 180 */       return true;
/*     */     }
/*     */ 
/* 184 */     return false;
/*     */   }
/*     */ 
/*     */   public static void main(String[] arg)
/*     */   {
/* 190 */     Map mp = new HashMap();
/* 191 */     mp.put("ph_type", "2");
/* 192 */     List lt = getCommonLangListByType(1);
/*     */ 
/* 195 */     System.out.println(lt.size());
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.appeal.lang.CommonLangManager
 * JD-Core Version:    0.6.2
 */