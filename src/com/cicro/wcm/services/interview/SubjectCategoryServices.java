/*     */ package com.cicro.wcm.services.interview;
/*     */ 
/*     */ import com.cicro.util.DateUtil;
/*     */ import com.cicro.wcm.bean.interview.SubjectCategory;
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.catchs.ISyncCatch;
/*     */ import com.cicro.wcm.catchs.SyncCatchHandl;
/*     */ import com.cicro.wcm.dao.interview.SubjectCategoryDAO;
/*     */ import java.io.PrintStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.UUID;
/*     */ 
/*     */ public class SubjectCategoryServices
/*     */   implements ISyncCatch
/*     */ {
/*  24 */   private static Map<Integer, SubjectCategory> cat_map = new HashMap();
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
/*  37 */     cat_map.clear();
/*  38 */     List l = SubjectCategoryDAO.getAllSubjectCategoryList();
/*  39 */     if ((l != null) && (l.size() > 0))
/*     */     {
/*  41 */       for (int i = 0; i < l.size(); i++)
/*     */       {
/*  43 */         cat_map.put(Integer.valueOf(((SubjectCategory)l.get(i)).getId()), (SubjectCategory)l.get(i));
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void reloadSubjectCategory()
/*     */   {
/*  50 */     SyncCatchHandl.reladCatchForRMI("com.cicro.wcm.services.interview.SubjectCategoryServices");
/*     */   }
/*     */ 
/*     */   public static String getSubjectCountByCategoryID(int id)
/*     */   {
/*  60 */     return SubjectCategoryDAO.getSubjectCountByCategoryID(id);
/*     */   }
/*     */ 
/*     */   public static String getSubCategoryCount(String con, String site_id)
/*     */   {
/*  70 */     return SubjectCategoryDAO.getSubCategoryCount(con, site_id);
/*     */   }
/*     */ 
/*     */   public static SubjectCategory getSubjectCategoryBean(int id)
/*     */   {
/*  80 */     if (cat_map.containsKey(Integer.valueOf(id)))
/*     */     {
/*  82 */       return (SubjectCategory)cat_map.get(Integer.valueOf(id));
/*     */     }
/*     */ 
/*  85 */     SubjectCategory sc = SubjectCategoryDAO.getSubjectCategoryBean(id);
/*  86 */     if (sc != null)
/*     */     {
/*  88 */       cat_map.put(Integer.valueOf(sc.getId()), sc);
/*  89 */       return sc;
/*     */     }
/*     */ 
/*  92 */     return null;
/*     */   }
/*     */ 
/*     */   public static SubjectCategory getSubjectCategoryBean(String category_id)
/*     */   {
/* 103 */     Set s = cat_map.keySet();
/* 104 */     for (Iterator localIterator = s.iterator(); localIterator.hasNext(); ) { int i = ((Integer)localIterator.next()).intValue();
/*     */ 
/* 106 */       SubjectCategory sc = (SubjectCategory)cat_map.get(Integer.valueOf(i));
/* 107 */       if (category_id.equals(sc.getCategory_id()))
/* 108 */         return sc;
/*     */     }
/* 110 */     return SubjectCategoryDAO.getSubjectCategoryBean(category_id);
/*     */   }
/*     */ 
/*     */   public static List getSubCategoryAllName(String site_id)
/*     */   {
/* 121 */     List l = new ArrayList();
/* 122 */     Set s = cat_map.keySet();
/* 123 */     for (Integer i : s)
/*     */     {
/* 125 */       SubjectCategory sc = (SubjectCategory)cat_map.get(i);
/* 126 */       if ((site_id.equals(sc.getSite_id())) && (sc.getIs_delete() == 0) && (sc.getPublish_status() == 1))
/* 127 */         l.add(sc);
/*     */     }
/* 129 */     return l;
/*     */   }
/*     */ 
/*     */   public static List getSubCategoryList(String con, int start_num, int page_size, String site_id)
/*     */   {
/* 141 */     Map m = new HashMap();
/* 142 */     m.put("start_num", Integer.valueOf(start_num));
/* 143 */     m.put("page_size", Integer.valueOf(page_size));
/* 144 */     m.put("con", con);
/* 145 */     m.put("site_id", site_id);
/* 146 */     return SubjectCategoryDAO.getSubCategoryList(m);
/*     */   }
/*     */ 
/*     */   public static boolean insertSubCategory(SubjectCategory sc, SettingLogsBean stl)
/*     */   {
/* 156 */     sc.setCategory_id(UUID.randomUUID().toString());
/* 157 */     sc.setAdd_time(DateUtil.getCurrentDateTime());
/*     */ 
/* 159 */     if (sc.getPublish_status() == 1)
/* 160 */       sc.setPublish_time(DateUtil.getCurrentDateTime());
/* 161 */     if (SubjectCategoryDAO.insertSubCategory(sc, stl))
/*     */     {
/* 163 */       reloadSubjectCategory();
/* 164 */       return true;
/*     */     }
/* 166 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateSubCategory(SubjectCategory sc, SettingLogsBean stl)
/*     */   {
/* 175 */     sc.setUpdate_time(DateUtil.getCurrentDateTime());
/* 176 */     if (sc.getPublish_status() == 1)
/* 177 */       sc.setPublish_time(DateUtil.getCurrentDateTime());
/* 178 */     if (SubjectCategoryDAO.updateSubCategory(sc, stl))
/*     */     {
/* 180 */       reloadSubjectCategory();
/* 181 */       return true;
/*     */     }
/* 183 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteSubCategory(String ids, String user_name, SettingLogsBean stl)
/*     */   {
/* 194 */     Map m = new HashMap();
/* 195 */     m.put("ids", ids);
/* 196 */     m.put("user_name", user_name);
/* 197 */     m.put("current_time", DateUtil.getCurrentDateTime());
/* 198 */     if (SubjectCategoryDAO.deleteSubCategory(m, stl))
/*     */     {
/* 200 */       reloadSubjectCategory();
/* 201 */       return true;
/*     */     }
/* 203 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateSubCategoryState(String ids, int status_flag, String user_id, SettingLogsBean stl)
/*     */   {
/* 215 */     Map m = new HashMap();
/* 216 */     m.put("ids", ids);
/* 217 */     m.put("status", status_flag);
/* 218 */     m.put("user_name", user_id);
/* 219 */     m.put("current_time", DateUtil.getCurrentDateTime());
/* 220 */     if (SubjectCategoryDAO.updateSubCategoryState(m, stl))
/*     */     {
/* 222 */       reloadSubjectCategory();
/* 223 */       return true;
/*     */     }
/* 225 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean saveSubCategorySort(String ids, SettingLogsBean stl)
/*     */   {
/* 235 */     return SubjectCategoryDAO.saveSubCategorySort(ids, stl);
/*     */   }
/*     */ 
/*     */   public static String getInterViewTemplate(String template_type, String site_id)
/*     */   {
/* 246 */     Set s = cat_map.keySet();
/* 247 */     for (Iterator localIterator = s.iterator(); localIterator.hasNext(); ) { int i = ((Integer)localIterator.next()).intValue();
/*     */ 
/* 249 */       SubjectCategory sc = (SubjectCategory)cat_map.get(Integer.valueOf(i));
/* 250 */       if (site_id.equals(sc.getSite_id()))
/*     */       {
/* 252 */         if ("list".equals(template_type))
/*     */         {
/* 254 */           if (!"".equals(sc.getM_hlist_path()))
/* 255 */             return sc.getM_hlist_path();
/*     */         }
/* 257 */         if ("live".equals(template_type))
/*     */         {
/* 259 */           if (!"".equals(sc.getM_on_path()))
/* 260 */             return sc.getM_on_path();
/*     */         }
/* 262 */         if ("forecastList".equals(template_type))
/*     */         {
/* 264 */           if (!"".equals(sc.getM_forecast_path()))
/* 265 */             return sc.getM_forecast_path();
/*     */         }
/* 267 */         if ("historyList".equals(template_type))
/*     */         {
/* 269 */           if (!"".equals(sc.getM_h_path()))
/* 270 */             return sc.getM_h_path();
/*     */         }
/* 272 */         if ("infoList".equals(template_type))
/*     */         {
/* 274 */           if (!"".equals(sc.getM_rlist_path()))
/* 275 */             return sc.getM_rlist_path();
/*     */         }
/* 277 */         if ("infoContent".equals(template_type))
/*     */         {
/* 279 */           if (!"".equals(sc.getM_rcontent_list()))
/* 280 */             return sc.getM_rcontent_list();
/*     */         }
/* 282 */         System.out.println(sc.getCategory_name() + "  " + sc.getM_rcontent_list());
/*     */       }
/*     */     }
/* 285 */     return "";
/*     */   }
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.interview.SubjectCategoryServices
 * JD-Core Version:    0.6.2
 */