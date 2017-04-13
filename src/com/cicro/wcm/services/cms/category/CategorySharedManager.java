/*     */ package com.cicro.wcm.services.cms.category;
/*     */ 
/*     */ import com.cicro.wcm.bean.cms.category.CategoryBean;
/*     */ import com.cicro.wcm.bean.cms.category.CategorySharedBean;
/*     */ import com.cicro.wcm.bean.control.SiteBean;
/*     */ import com.cicro.wcm.catchs.ISyncCatch;
/*     */ import com.cicro.wcm.catchs.SyncCatchHandl;
/*     */ import com.cicro.wcm.dao.cms.category.CategorySharedDAO;
/*     */ import com.cicro.wcm.services.control.site.SiteManager;
/*     */ import java.io.PrintStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ 
/*     */ public class CategorySharedManager
/*     */   implements ISyncCatch
/*     */ {
/*  29 */   private static List<CategorySharedBean> cs_list = new ArrayList();
/*     */ 
/*     */   static {
/*  32 */     reloadCatchHandl();
/*     */   }
/*     */ 
/*     */   public void reloadCatch()
/*     */   {
/*  37 */     reloadCatchHandl();
/*     */   }
/*     */ 
/*     */   public static void reloadCatchHandl()
/*     */   {
/*  42 */     cs_list.clear();
/*  43 */     cs_list = CategorySharedDAO.getAllCategorySharedList();
/*     */   }
/*     */ 
/*     */   public static void reloadCategoryShared()
/*     */   {
/*  54 */     SyncCatchHandl.reladCatchForRMI("com.cicro.wcm.services.cms.category.CategorySharedManager");
/*     */   }
/*     */ 
/*     */   public static List<CategorySharedBean> getCategorySharedListBySCS(String site_id, int cat_id, int shared_type)
/*     */   {
/*  66 */     List list = new ArrayList();
/*  67 */     if ((cs_list != null) && (cs_list.size() > 0))
/*     */     {
/*  69 */       for (int i = 0; i < cs_list.size(); i++)
/*     */       {
/*  71 */         if ((site_id.equals(((CategorySharedBean)cs_list.get(i)).getS_site_id())) && (cat_id == ((CategorySharedBean)cs_list.get(i)).getCat_id()) && (shared_type == ((CategorySharedBean)cs_list.get(i)).getShared_type()))
/*     */         {
/*  73 */           list.add((CategorySharedBean)cs_list.get(i));
/*     */         }
/*     */       }
/*     */     }
/*  77 */     return list;
/*     */   }
/*     */ 
/*     */   public static List<SiteBean> getAllowReceiveSite(String t_site_id)
/*     */   {
/*  87 */     return getAllowSiteByType(t_site_id, 1);
/*     */   }
/*     */ 
/*     */   public static List<SiteBean> getAllowSharedSite(String t_site_id)
/*     */   {
/*  97 */     return getAllowSiteByType(t_site_id, 0);
/*     */   }
/*     */ 
/*     */   public static String getAllowSharedSiteJSONStr(String t_site_id)
/*     */   {
/* 102 */     String str = "";
/* 103 */     List l = getAllowSiteByType(t_site_id, 0);
/* 104 */     if ((l != null) && (l.size() > 0))
/*     */     {
/* 106 */       for (int i = 0; i < l.size(); i++)
/*     */       {
/* 108 */         if (l.get(i) != null)
/* 109 */           str = str + ",{\"id\":" + (1000 + i) + ",\"text\":\"" + ((SiteBean)l.get(i)).getSite_name() + "\",\"attributes\":{\"real_site_id\":\"" + ((SiteBean)l.get(i)).getSite_id() + "\"}}";
/*     */       }
/* 111 */       if (!"".equals(str))
/* 112 */         str = str.substring(1);
/*     */     }
/*     */     else {
/* 115 */       str = "{\"id\":1001,\"text\":\"" + SiteManager.getSiteBeanBySiteID(t_site_id).getSite_name() + "\",\"attributes\":{\"real_site_id\":\"" + t_site_id + "\"}}";
/*     */     }
/* 117 */     return "[" + str + "]";
/*     */   }
/*     */ 
/*     */   public static List<SiteBean> getAllowSiteByType(String t_site_id, int shared_type)
/*     */   {
/* 128 */     List site_list = new ArrayList();
/* 129 */     Set site_set = new HashSet();
/* 130 */     if ((cs_list != null) && (cs_list.size() > 0))
/*     */     {
/* 132 */       for (int i = 0; i < cs_list.size(); i++)
/*     */       {
/* 134 */         if (((CategorySharedBean)cs_list.get(i)).getShared_type() == shared_type)
/*     */         {
/* 136 */           if ((((CategorySharedBean)cs_list.get(i)).getRange_type() == 1) || (t_site_id.equals(((CategorySharedBean)cs_list.get(i)).getT_site_id())))
/* 137 */             site_set.add(SiteManager.getSiteBeanBySiteID(((CategorySharedBean)cs_list.get(i)).getS_site_id()));
/*     */         }
/*     */       }
/* 140 */       site_list.addAll(site_set);
/*     */     }
/* 142 */     return site_list;
/*     */   }
/*     */ 
/*     */   public static String getReceiveCategoryTreeBySiteID(String s_site_id, String t_site_id)
/*     */   {
/* 153 */     String json_str = "";
/* 154 */     List cat_list = new ArrayList();
/* 155 */     getSharedCategoryListBySiteID(cat_list, s_site_id, t_site_id, 1);
/* 156 */     json_str = "[" + CategoryTreeUtil.getCategoryTreeJsonStrHandl(cat_list) + "]";
/* 157 */     return json_str;
/*     */   }
/*     */ 
/*     */   public static String getSharedCategoryTreeBySiteID(String s_site_id, String t_site_id)
/*     */   {
/* 168 */     String json_str = "";
/* 169 */     List cat_list = new ArrayList();
/* 170 */     getSharedCategoryListBySiteID(cat_list, s_site_id, t_site_id, 0);
/* 171 */     json_str = "[" + CategoryTreeUtil.getCategoryTreeJsonStrHandl(cat_list) + "]";
/* 172 */     return json_str;
/*     */   }
/*     */ 
/*     */   public static void getSharedCategoryListBySiteID(List<CategoryBean> cat_list, String s_site_id, String t_site_id, int shared_type)
/*     */   {
/* 185 */     Set cat_set = new HashSet();
/* 186 */     if ((cs_list != null) && (cs_list.size() > 0))
/*     */     {
/* 188 */       for (int i = 0; i < cs_list.size(); i++)
/*     */       {
/* 190 */         if ((((CategorySharedBean)cs_list.get(i)).getShared_type() == shared_type) && (s_site_id.equals(((CategorySharedBean)cs_list.get(i)).getS_site_id())))
/*     */         {
/* 192 */           if ((((CategorySharedBean)cs_list.get(i)).getRange_type() == 1) || (t_site_id.equals(((CategorySharedBean)cs_list.get(i)).getT_site_id())))
/* 193 */             cat_set.add(CategoryManager.getCategoryBean(((CategorySharedBean)cs_list.get(i)).getCat_id()));
/*     */         }
/*     */       }
/* 196 */       cat_list.addAll(cat_set);
/*     */     }
/*     */   }
/*     */ 
/*     */   public static boolean insertCategoryShared(CategorySharedBean csb)
/*     */   {
/* 208 */     if (csb.getRange_type() == 0)
/*     */     {
/* 210 */       if (!"".equals(csb.getT_site_id().trim())) {
/*     */         try
/*     */         {
/* 213 */           String[] tempA = csb.getT_site_id().split(",");
/* 214 */           for (int i = 0; i < tempA.length; i++)
/*     */           {
/* 216 */             CategorySharedBean new_csb = csb;
/* 217 */             new_csb.setT_site_id(tempA[i]);
/* 218 */             CategorySharedDAO.insertCategoryShared(new_csb);
/*     */           }
/*     */         }
/*     */         catch (Exception e) {
/* 222 */           e.printStackTrace();
/* 223 */           return false;
/*     */         }
/*     */       }
/* 226 */       reloadCategoryShared();
/* 227 */       return true;
/*     */     }
/*     */ 
/* 230 */     csb.setT_site_id("");
/* 231 */     if (CategorySharedDAO.insertCategoryShared(csb))
/*     */     {
/* 233 */       reloadCategoryShared();
/* 234 */       return true;
/*     */     }
/* 236 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateCategoryShared(CategorySharedBean csb)
/*     */   {
/* 248 */     CategorySharedDAO.deleteCategoryShared(csb.getS_site_id(), csb.getCat_id(), csb.getShared_type());
/* 249 */     return insertCategoryShared(csb);
/*     */   }
/*     */ 
/*     */   public static boolean deleteCategorySharedByCatID(String cat_ids, String site_id)
/*     */   {
/* 259 */     if (CategorySharedDAO.deleteCategorySharedByCatID(cat_ids, site_id))
/*     */     {
/* 261 */       reloadCategoryShared();
/* 262 */       return true;
/*     */     }
/* 264 */     return false;
/*     */   }
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/* 269 */     System.out.println(getAllowSharedSiteJSONStr("11111ddd"));
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.cms.category.CategorySharedManager
 * JD-Core Version:    0.6.2
 */