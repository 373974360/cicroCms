/*     */ package com.cicro.wcm.services.cms.category;
/*     */ 
/*     */ import com.cicro.wcm.bean.cms.category.ZTCategoryBean;
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.catchs.ISyncCatch;
/*     */ import com.cicro.wcm.catchs.SyncCatchHandl;
/*     */ import com.cicro.wcm.dao.cms.category.ZTCategoryDAO;
/*     */ import java.io.PrintStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ 
/*     */ public class ZTCategoryManager
/*     */   implements ISyncCatch
/*     */ {
/*  29 */   private static Map<Integer, ZTCategoryBean> zt_c_map = new HashMap();
/*     */ 
/*  31 */   static { reloadCatchHandl(); }
/*     */ 
/*     */ 
/*     */   public void reloadCatch()
/*     */   {
/*  36 */     reloadCatchHandl();
/*     */   }
/*     */ 
/*     */   public static void reloadCatchHandl()
/*     */   {
/*  41 */     zt_c_map.clear();
/*  42 */     List l = ZTCategoryDAO.getALlZTCategoryList();
/*  43 */     if ((l != null) && (l.size() > 0))
/*     */     {
/*  45 */       for (int i = 0; i < l.size(); i++)
/*     */       {
/*  47 */         zt_c_map.put(Integer.valueOf(((ZTCategoryBean)l.get(i)).getId()), (ZTCategoryBean)l.get(i));
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void reloadZTCategory()
/*     */   {
/*  54 */     SyncCatchHandl.reladCatchForRMI("com.cicro.wcm.services.cms.category.ZTCategoryManager");
/*     */   }
/*     */ 
/*     */   public static String getZTCategoryTreeJsonStr(String site_id)
/*     */   {
/*  64 */     String json_str = "";
/*     */ 
/*  66 */     List l = getZTCategoryList(site_id);
/*  67 */     if ((l != null) && (l.size() > 0))
/*     */     {
/*  69 */       for (int i = 0; i < l.size(); i++)
/*     */       {
/*  71 */         String cate_str = CategoryTreeUtil.getZTCategoryTreeStr(((ZTCategoryBean)l.get(i)).getZt_cat_id(), site_id);
/*  72 */         if ((cate_str != null) && (!"".equals(cate_str.trim())))
/*     */         {
/*  74 */           json_str = json_str + "{\"id\":" + ((ZTCategoryBean)l.get(i)).getZt_cat_id() + ",\"iconCls\":\"icon-category\",\"text\":\"" + ((ZTCategoryBean)l.get(i)).getZt_cat_name() + "\",\"attributes\":{\"url\":\"\",\"handl\":\"\"}";
/*  75 */           json_str = json_str + ",\"children\":[" + cate_str + "]},";
/*     */         }
/*     */       }
/*     */ 
/*  79 */       if ((json_str != null) && (!"".equals(json_str)))
/*     */       {
/*  81 */         if (json_str.endsWith(","));
/*  82 */         json_str = json_str.substring(0, json_str.length() - 1);
/*     */       }
/*  84 */       json_str = "[" + json_str + "]";
/*  85 */       return json_str;
/*     */     }
/*     */ 
/*  88 */     return "[]";
/*     */   }
/*     */ 
/*     */   public static String getZTCategoryTreeJsonStr(String site_id, int user_id)
/*     */   {
/*  99 */     String json_str = "";
/*     */ 
/* 101 */     List l = getZTCategoryList(site_id);
/* 102 */     if ((l != null) && (l.size() > 0))
/*     */     {
/* 104 */       for (int i = 0; i < l.size(); i++)
/*     */       {
/* 107 */         String cate_str = CategoryTreeUtil.getZTCategoryTreeStr(((ZTCategoryBean)l.get(i)).getZt_cat_id(), site_id, user_id);
/* 108 */         if ((cate_str != null) && (!"".equals(cate_str.trim())))
/*     */         {
/* 110 */           json_str = json_str + "{\"id\":" + ((ZTCategoryBean)l.get(i)).getZt_cat_id() + ",\"iconCls\":\"icon-category\",\"text\":\"" + ((ZTCategoryBean)l.get(i)).getZt_cat_name() + "\",\"attributes\":{\"url\":\"\",\"handl\":\"\"}";
/* 111 */           json_str = json_str + ",\"children\":[" + cate_str + "]},";
/*     */         }
/*     */       }
/* 114 */       if (json_str.endsWith(","))
/* 115 */         json_str = json_str.substring(0, json_str.length() - 1);
/* 116 */       json_str = "[" + json_str + "]";
/* 117 */       return json_str;
/*     */     }
/*     */ 
/* 120 */     return "[]";
/*     */   }
/*     */ 
/*     */   public static List<ZTCategoryBean> getZTCategoryList(String site_id)
/*     */   {
/* 131 */     List l = new ArrayList();
/* 132 */     Set s = zt_c_map.keySet();
/* 133 */     for (Iterator localIterator = s.iterator(); localIterator.hasNext(); ) { int i = ((Integer)localIterator.next()).intValue();
/*     */ 
/* 135 */       ZTCategoryBean zb = (ZTCategoryBean)zt_c_map.get(Integer.valueOf(i));
/* 136 */       if (site_id.equals(zb.getSite_id()))
/* 137 */         l.add(zb);
/*     */     }
/* 139 */     if ((l != null) && (l.size() > 0))
/* 140 */       Collections.sort(l, new ZTCategoryManager.ZTCategoryComparator());
/* 141 */     return l;
/*     */   }
/*     */ 
/*     */   public static ZTCategoryBean getZRCategoryBean(int id)
/*     */   {
/* 151 */     if (zt_c_map.containsKey(Integer.valueOf(id)))
/*     */     {
/* 153 */       return (ZTCategoryBean)zt_c_map.get(Integer.valueOf(id));
/*     */     }
/*     */ 
/* 157 */     ZTCategoryBean zb = ZTCategoryDAO.getZRCategoryBean(id);
/* 158 */     if (zb != null)
/*     */     {
/* 160 */       zt_c_map.put(Integer.valueOf(id), zb);
/* 161 */       return zb;
/*     */     }
/* 163 */     return null;
/*     */   }
/*     */ 
/*     */   public static boolean insertZTCategory(ZTCategoryBean zb, SettingLogsBean stl)
/*     */   {
/* 175 */     if (ZTCategoryDAO.insertZTCategory(zb, stl))
/*     */     {
/* 177 */       reloadZTCategory();
/* 178 */       return true;
/*     */     }
/* 180 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateZTCategory(ZTCategoryBean zb, SettingLogsBean stl)
/*     */   {
/* 191 */     if (ZTCategoryDAO.updateZTCategory(zb, stl))
/*     */     {
/* 193 */       reloadZTCategory();
/* 194 */       return true;
/*     */     }
/* 196 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean sortZTCategory(String ids, SettingLogsBean stl)
/*     */   {
/* 207 */     if (ZTCategoryDAO.sortZTCategory(ids, stl))
/*     */     {
/* 209 */       reloadZTCategory();
/* 210 */       return true;
/*     */     }
/* 212 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteZTCategory(int id, SettingLogsBean stl)
/*     */   {
/* 223 */     if (ZTCategoryDAO.deleteZTCategory(id, stl))
/*     */     {
/* 225 */       reloadZTCategory();
/* 226 */       return true;
/*     */     }
/* 228 */     return false;
/*     */   }
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/* 250 */     System.out.println(getZTCategoryTreeJsonStr("11111ddd"));
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.cms.category.ZTCategoryManager
 * JD-Core Version:    0.6.2
 */