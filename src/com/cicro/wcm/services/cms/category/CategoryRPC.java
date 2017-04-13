/*     */ package com.cicro.wcm.services.cms.category;
/*     */ 
/*     */ import com.cicro.wcm.bean.cms.category.CateClassBean;
/*     */ import com.cicro.wcm.bean.cms.category.CategoryBean;
/*     */ import com.cicro.wcm.bean.cms.category.CategoryGetRegu;
/*     */ import com.cicro.wcm.bean.cms.category.CategoryModel;
/*     */ import com.cicro.wcm.bean.cms.category.CategoryReleBean;
/*     */ import com.cicro.wcm.bean.cms.category.CategorySharedBean;
/*     */ import com.cicro.wcm.bean.cms.category.SMCategoryBean;
/*     */ import com.cicro.wcm.bean.cms.category.SyncBean;
/*     */ import com.cicro.wcm.bean.cms.category.ZTCategoryBean;
/*     */ import com.cicro.wcm.bean.control.SiteBean;
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.bean.org.app.AppBean;
/*     */ import com.cicro.wcm.bean.system.formodel.ModelBean;
/*     */ import com.cicro.wcm.services.Log.LogManager;
/*     */ import com.cicro.wcm.services.org.app.AppRPC;
/*     */ import com.cicro.wcm.services.page.PageManager;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ 
/*     */ public class CategoryRPC
/*     */ {
/*     */   public static CategoryBean getCategoryBeanByClassID(int class_id)
/*     */   {
/*  34 */     return CategoryManager.getCategoryBeanByClassID(class_id);
/*     */   }
/*     */ 
/*     */   public static String getCategoryCName(int cat_id, String site_id)
/*     */   {
/*  45 */     return CategoryUtil.getCategoryCName(cat_id, site_id);
/*     */   }
/*     */ 
/*     */   public static String getAllFWTreeJSONStr()
/*     */   {
/*  55 */     return CategoryTreeUtil.getAllFWTreeJSONStr();
/*     */   }
/*     */ 
/*     */   public static int getNewCategoryID()
/*     */   {
/*  60 */     return CategoryManager.getNewCategoryID();
/*     */   }
/*     */ 
/*     */   public static CategoryBean getCategoryBean(int cat_id)
/*     */   {
/*  70 */     return CategoryManager.getCategoryBean(cat_id);
/*     */   }
/*     */ 
/*     */   public static CategoryBean getCategoryBeanCatID(int cat_id, String site_id)
/*     */   {
/*  81 */     return CategoryManager.getCategoryBeanCatID(cat_id, site_id);
/*     */   }
/*     */ 
/*     */   public static int getWFIDByCatID(int cat_id, String site_id)
/*     */   {
/*  92 */     return CategoryManager.getWFIDByCatID(cat_id, site_id);
/*     */   }
/*     */ 
/*     */   public static String getCategoryCountBySiteAndType(Map<String, String> m)
/*     */   {
/* 102 */     return CategoryManager.getCategoryCountBySiteAndType(m);
/*     */   }
/*     */ 
/*     */   public static List<CategoryBean> getCategoryListBySiteAndType(Map<String, String> m)
/*     */   {
/* 112 */     return CategoryManager.getCategoryListBySiteAndType(m);
/*     */   }
/*     */ 
/*     */   public static List<CategoryBean> getCategoryListBySiteID(String site_id)
/*     */   {
/* 122 */     return CategoryManager.getCategoryListBySiteID(site_id, 0);
/*     */   }
/*     */ 
/*     */   public static String getInfoCategoryTreeByUserID(String site_id, int uesr_id)
/*     */   {
/* 133 */     return CategoryTreeUtil.getInfoCategoryTreeByUserID(site_id, uesr_id, 0);
/*     */   }
/*     */ 
/*     */   public static String getFWCategoryTreeByUserID(String site_id, int uesr_id)
/*     */   {
/* 144 */     return CategoryTreeUtil.getInfoCategoryTreeByUserID(site_id, uesr_id, 2);
/*     */   }
/*     */ 
/*     */   public static String getFWCategoryTreeByUserID(String site_id)
/*     */   {
/* 155 */     return CategoryTreeUtil.getInfoCategoryTreeByUserID(site_id, 2);
/*     */   }
/*     */ 
/*     */   public static String getCategoryTreeBySiteID(String site_id)
/*     */   {
/* 165 */     return CategoryTreeUtil.getCategoryTreeBySiteID(site_id, 0);
/*     */   }
/*     */ 
/*     */   public static String getCategoryTreeByCategoryID(int cat_id, String site_id)
/*     */   {
/* 176 */     return CategoryTreeUtil.getCategoryTreeByCategoryID(cat_id, site_id);
/*     */   }
/*     */ 
/*     */   public static String getCategoryTreeByClassID(int class_id)
/*     */   {
/* 186 */     return CategoryTreeUtil.getCategoryTreeByClassID(class_id);
/*     */   }
/*     */ 
/*     */   public static List<CategoryBean> getChildCategoryList(int cat_id, String site_id)
/*     */   {
/* 196 */     return CategoryManager.getChildCategoryList(cat_id, site_id);
/*     */   }
/*     */ 
/*     */   public static boolean categoryIsExist(int parent_id, String cat_ename, String app_id, String site_id)
/*     */   {
/* 209 */     String page_path = CategoryUtil.getFoldePathByCategoryID(parent_id, app_id, site_id);
/* 210 */     if ((PageManager.pageFileIsExist(page_path, cat_ename, site_id)) || (CategoryManager.categoryIsExist(parent_id, cat_ename, site_id))) {
/* 211 */       return true;
/*     */     }
/* 213 */     return false;
/*     */   }
/*     */ 
/*     */   public static String getFoldePathByCategoryID(int cat_id, String app_id, String site_id)
/*     */   {
/* 224 */     return CategoryUtil.getFoldePathByCategoryID(cat_id, app_id, site_id);
/*     */   }
/*     */ 
/*     */   public static boolean insertCategory(CategoryBean cgb, boolean is_share, HttpServletRequest request)
/*     */   {
/* 236 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 237 */     if (stl != null)
/*     */     {
/* 239 */       return CategoryManager.insertCategory(cgb, is_share, stl);
/*     */     }
/* 241 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean copyBasisCategory(int parent_id, int selected_cat_ids, String site_id, HttpServletRequest request)
/*     */   {
/* 253 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 254 */     if (stl != null)
/*     */     {
/* 256 */       return CategoryManager.copyBasisCategory(parent_id, selected_cat_ids, site_id, stl);
/*     */     }
/* 258 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean copyShareCategory(int parent_id, String selected_cat_ids, String app_id, String site_id, HttpServletRequest request)
/*     */   {
/* 271 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 272 */     if (stl != null)
/*     */     {
/* 274 */       return CategoryManager.copyShareCategory(parent_id, selected_cat_ids, app_id, site_id, stl);
/*     */     }
/* 276 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateCategory(CategoryBean cgb, HttpServletRequest request)
/*     */   {
/* 287 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 288 */     if (stl != null)
/*     */     {
/* 290 */       return CategoryManager.updateCategory(cgb, stl);
/*     */     }
/* 292 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean batchUpdateCategory(Map<String, String> cat_m, List<CategoryModel> l, String cat_id, String site_id, HttpServletRequest request)
/*     */   {
/* 303 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 304 */     if (stl != null)
/*     */     {
/* 306 */       return CategoryManager.batchUpdateCategory(cat_m, l, cat_id, site_id, stl);
/*     */     }
/* 308 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateCategoryTemplate(Map<String, String> m)
/*     */   {
/* 318 */     return CategoryManager.updateCategoryTemplate(m);
/*     */   }
/*     */ 
/*     */   public static boolean updateGKZNCateTemplate(String gkzy_list, String gkzn_list, String gknb_list)
/*     */   {
/* 330 */     return CategoryManager.updateGKZNCateTemplate(gkzy_list, gkzn_list, gknb_list);
/*     */   }
/*     */ 
/*     */   public static boolean updateCategoryArchiveStatus(String ids, String is_archive, HttpServletRequest request)
/*     */   {
/* 341 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 342 */     if (stl != null)
/*     */     {
/* 344 */       return CategoryManager.updateCategoryArchiveStatus(ids, is_archive, stl);
/*     */     }
/* 346 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean sortCategory(String ids, HttpServletRequest request)
/*     */   {
/* 357 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 358 */     if (stl != null)
/*     */     {
/* 360 */       return CategoryManager.sortCategory(ids, stl);
/*     */     }
/* 362 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteCategory(String cat_ids, String site_id, String app_id, HttpServletRequest request)
/*     */   {
/* 373 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 374 */     if (stl != null)
/*     */     {
/* 376 */       return CategoryManager.deleteCategory(cat_ids, site_id, app_id, stl);
/*     */     }
/* 378 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean releCategoryClass(String class_id, String cat_id, HttpServletRequest request)
/*     */   {
/* 390 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 391 */     if (stl != null)
/*     */     {
/* 393 */       return CategoryManager.releCategoryClass(class_id, cat_id, stl);
/*     */     }
/* 395 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean moveCategory(String parent_id, String cat_ids, String site_id, HttpServletRequest request)
/*     */   {
/* 406 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 407 */     if (stl != null)
/*     */     {
/* 409 */       return CategoryManager.moveCategory(parent_id, cat_ids, site_id, stl);
/*     */     }
/* 411 */     return false;
/*     */   }
/*     */ 
/*     */   public static int getCategoryCountByClassID(int class_id)
/*     */   {
/* 421 */     return CategoryManager.getCategoryCountByClassID(class_id);
/*     */   }
/*     */ 
/*     */   public static List<ModelBean> getCateReleModelBeanList(String cat_id, String site_id)
/*     */   {
/* 431 */     return CategoryModelManager.getCateReleModelBeanList(cat_id, site_id);
/*     */   }
/*     */ 
/*     */   public static String getTemplateID(String cat_id, String site_id, int model_id)
/*     */   {
/* 441 */     return CategoryModelManager.getTemplateID(cat_id, site_id, model_id);
/*     */   }
/*     */ 
/*     */   public static boolean deleteCategoryModel(String cat_id, String site_id)
/*     */   {
/* 452 */     return CategoryModelManager.deleteCategoryModel(cat_id, site_id);
/*     */   }
/*     */ 
/*     */   public static boolean updateBaseCategoryTemplate(String templage_id)
/*     */   {
/* 466 */     return CategoryManager.updateBaseCategoryTemplate(templage_id);
/*     */   }
/*     */ 
/*     */   public static String getBaseCategoryTemplateListID()
/*     */   {
/* 476 */     return CategoryManager.getBaseCategoryTemplateListID();
/*     */   }
/*     */ 
/*     */   public static List<CategorySharedBean> getCategorySharedListBySCS(String site_id, int cat_id, int shared_type)
/*     */   {
/* 488 */     return CategorySharedManager.getCategorySharedListBySCS(site_id, cat_id, shared_type);
/*     */   }
/*     */ 
/*     */   public static List<SiteBean> getAllowSharedSite(String t_site_id)
/*     */   {
/* 498 */     return CategorySharedManager.getAllowSharedSite(t_site_id);
/*     */   }
/*     */ 
/*     */   public static String getAllowSharedSiteJSONStr(String t_site_id)
/*     */   {
/* 503 */     return CategorySharedManager.getAllowSharedSiteJSONStr(t_site_id);
/*     */   }
/*     */ 
/*     */   public static List<SiteBean> getAllowReceiveSite(String t_site_id)
/*     */   {
/* 513 */     return CategorySharedManager.getAllowReceiveSite(t_site_id);
/*     */   }
/*     */ 
/*     */   public static String getSharedCategoryTreeBySiteID(String s_site_id, String t_site_id)
/*     */   {
/* 524 */     return CategorySharedManager.getSharedCategoryTreeBySiteID(s_site_id, t_site_id);
/*     */   }
/*     */ 
/*     */   public static String getReceiveCategoryTreeBySiteID(String s_site_id, String t_site_id)
/*     */   {
/* 535 */     return CategorySharedManager.getReceiveCategoryTreeBySiteID(s_site_id, t_site_id);
/*     */   }
/*     */ 
/*     */   public static boolean insertCategoryShared(CategorySharedBean csb)
/*     */   {
/* 545 */     return CategorySharedManager.insertCategoryShared(csb);
/*     */   }
/*     */ 
/*     */   public static boolean updateCategoryShared(CategorySharedBean csb)
/*     */   {
/* 555 */     return CategorySharedManager.updateCategoryShared(csb);
/*     */   }
/*     */ 
/*     */   public static SMCategoryBean getSMCategoryList(String ename)
/*     */   {
/* 569 */     return CateClassManager.getSMCategoryList(ename);
/*     */   }
/*     */ 
/*     */   public static List<CateClassBean> getBasisCateClassList()
/*     */   {
/* 579 */     return CateClassManager.getBasisCateClassList();
/*     */   }
/*     */ 
/*     */   public static List<CateClassBean> getAllCateClassList()
/*     */   {
/* 587 */     return CateClassManager.getAllCateClassList();
/*     */   }
/*     */ 
/*     */   public static List<CateClassBean> getCateClassListByApp(String app_id)
/*     */   {
/* 598 */     return CateClassManager.getCateClassListByApp(app_id);
/*     */   }
/*     */ 
/*     */   public static CateClassBean getCateClassBeanById(String id)
/*     */   {
/* 608 */     return CateClassManager.getCateClassBeanById(id);
/*     */   }
/*     */ 
/*     */   public static boolean insertCateClass(CateClassBean ccb, HttpServletRequest request)
/*     */   {
/* 620 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 621 */     if (stl != null)
/*     */     {
/* 623 */       return CateClassManager.insertCateClass(ccb, stl);
/*     */     }
/*     */ 
/* 627 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateCateClass(CateClassBean ccb, HttpServletRequest request)
/*     */   {
/* 640 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 641 */     if (stl != null)
/*     */     {
/* 643 */       return CateClassManager.updateCateClass(ccb, stl);
/*     */     }
/*     */ 
/* 647 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteCateClass(String ids, HttpServletRequest request)
/*     */   {
/* 661 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 662 */     if (stl != null)
/*     */     {
/* 664 */       return CateClassManager.deleteCateClass(ids, stl);
/*     */     }
/*     */ 
/* 668 */     return false;
/*     */   }
/*     */ 
/*     */   public static List<AppBean> getAppBeanList()
/*     */   {
/* 674 */     return AppRPC.getAppList();
/*     */   }
/*     */ 
/*     */   public static List<SyncBean> getToInfoCategoryList(String s_site_id, int cat_id)
/*     */   {
/* 688 */     return SyncManager.getToInfoCategoryList(s_site_id, cat_id);
/*     */   }
/*     */ 
/*     */   public static List<SyncBean> getSyncListBySiteCatID(String s_site_id, int cat_id)
/*     */   {
/* 699 */     return SyncManager.getSyncListBySiteCatID(s_site_id, cat_id);
/*     */   }
/*     */ 
/*     */   public static boolean insertSync(List<SyncBean> sList, String s_cat_id, String s_site_id, String orientation)
/*     */   {
/* 711 */     return SyncManager.insertSync(sList, s_cat_id, s_site_id, orientation);
/*     */   }
/*     */ 
/*     */   public static List<CategoryReleBean> getCategoryReleUserListByCatID(int cat_id, String site_id)
/*     */   {
/* 724 */     return CategoryReleManager.getCategoryReleUserListByCatID(cat_id, site_id);
/*     */   }
/*     */ 
/*     */   public static boolean insertCategoryReleUser(List<CategoryReleBean> list, int cat_id, String site_id)
/*     */   {
/* 734 */     return CategoryReleManager.insertCategoryReleUser(list, cat_id, site_id);
/*     */   }
/*     */ 
/*     */   public static boolean insertCategoryReleUser(String cat_ids, int priv_type, int prv_id, String site_id)
/*     */   {
/* 747 */     return CategoryReleManager.insertCategoryReleUser(cat_ids, priv_type, prv_id, site_id);
/*     */   }
/*     */ 
/*     */   public static String getCategoryIDSByUser(int priv_type, int prv_id, String site_id)
/*     */   {
/* 757 */     return CategoryReleManager.getCategoryIDSByUser(priv_type, prv_id, site_id);
/*     */   }
/*     */ 
/*     */   public static String getZTCategoryTreeJsonStr(String site_id, int uesr_id)
/*     */   {
/* 770 */     return ZTCategoryManager.getZTCategoryTreeJsonStr(site_id, uesr_id);
/*     */   }
/*     */ 
/*     */   public static String getZTCategoryTreeJsonStr(String site_id)
/*     */   {
/* 780 */     return ZTCategoryManager.getZTCategoryTreeJsonStr(site_id);
/*     */   }
/*     */ 
/*     */   public static List<ZTCategoryBean> getZTCategoryList(String site_id)
/*     */   {
/* 790 */     return ZTCategoryManager.getZTCategoryList(site_id);
/*     */   }
/*     */ 
/*     */   public static ZTCategoryBean getZRCategoryBean(int id)
/*     */   {
/* 800 */     return ZTCategoryManager.getZRCategoryBean(id);
/*     */   }
/*     */ 
/*     */   public static boolean insertZTCategory(ZTCategoryBean zb, HttpServletRequest request)
/*     */   {
/* 811 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 812 */     if (stl != null)
/*     */     {
/* 814 */       return ZTCategoryManager.insertZTCategory(zb, stl);
/*     */     }
/*     */ 
/* 818 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateZTCategory(ZTCategoryBean zb, HttpServletRequest request)
/*     */   {
/* 830 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 831 */     if (stl != null)
/*     */     {
/* 833 */       return ZTCategoryManager.updateZTCategory(zb, stl);
/*     */     }
/*     */ 
/* 837 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean sortZTCategory(String ids, HttpServletRequest request)
/*     */   {
/* 849 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 850 */     if (stl != null)
/*     */     {
/* 852 */       return ZTCategoryManager.sortZTCategory(ids, stl);
/*     */     }
/*     */ 
/* 856 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteZTCategory(int id, HttpServletRequest request)
/*     */   {
/* 868 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 869 */     if (stl != null)
/*     */     {
/* 871 */       return ZTCategoryManager.deleteZTCategory(id, stl);
/*     */     }
/*     */ 
/* 875 */     return false;
/*     */   }
/*     */ 
/*     */   public static List<CategoryModel> getCategoryReleModelList(String cat_id, String site_id)
/*     */   {
/* 888 */     return CategoryModelManager.getCategoryReleModelList(cat_id, site_id);
/*     */   }
/*     */ 
/*     */   public static boolean insertCategoryModel(List<CategoryModel> l)
/*     */   {
/* 900 */     return CategoryModelManager.insertCategoryModel(l);
/*     */   }
/*     */ 
/*     */   public static boolean updateCategoryModel(List<CategoryModel> l, String cat_id, String site_id)
/*     */   {
/* 912 */     return CategoryModelManager.updateCategoryModel(l, cat_id, site_id);
/*     */   }
/*     */ 
/*     */   public static List<CategoryGetRegu> getCatagoryReguList(int cat_id, String site_id)
/*     */   {
/* 924 */     return CategoryGetReguManager.getCatagoryReguList(cat_id, site_id);
/*     */   }
/*     */ 
/*     */   public static String insertCategoryRegu(List<CategoryGetRegu> l, int cat_id, String site_id, String app_id)
/*     */   {
/* 936 */     return CategoryGetReguManager.insertCategoryRegu(l, cat_id, site_id, app_id);
/*     */   }
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/* 943 */     System.out.println(getTemplateID("1879", "HIWCMdemo", 10));
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.cms.category.CategoryRPC
 * JD-Core Version:    0.6.2
 */