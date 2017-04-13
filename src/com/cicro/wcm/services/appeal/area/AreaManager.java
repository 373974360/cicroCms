/*     */ package com.cicro.wcm.services.appeal.area;
/*     */ 
/*     */ import com.cicro.wcm.bean.appeal.area.AreaBean;
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.catchs.ISyncCatch;
/*     */ import com.cicro.wcm.catchs.SyncCatchHandl;
/*     */ import com.cicro.wcm.dao.PublicTableDAO;
/*     */ import com.cicro.wcm.dao.appeal.area.AreaDAO;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.Set;
/*     */ import java.util.TreeMap;
/*     */ 
/*     */ public class AreaManager
/*     */   implements ISyncCatch
/*     */ {
/*  34 */   private static TreeMap<Integer, AreaBean> area_map = new TreeMap();
/*  35 */   private static int root_area_id = 1;
/*     */ 
/*     */   static {
/*  38 */     reloadCatchHandl();
/*     */   }
/*     */ 
/*     */   public void reloadCatch()
/*     */   {
/*  43 */     reloadCatchHandl();
/*     */   }
/*     */ 
/*     */   public static void reloadCatchHandl()
/*     */   {
/*  48 */     List area_list = AreaDAO.getAllAreaList();
/*  49 */     area_map.clear();
/*  50 */     if ((area_list != null) && (area_list.size() > 0))
/*  51 */       for (int i = 0; i < area_list.size(); i++)
/*  52 */         area_map.put(Integer.valueOf(((AreaBean)area_list.get(i)).getArea_id()), (AreaBean)area_list.get(i));
/*     */   }
/*     */ 
/*     */   public static void reloadArea()
/*     */   {
/*  63 */     SyncCatchHandl.reladCatchForRMI("com.cicro.wcm.services.appeal.area.AreaManager");
/*     */   }
/*     */ 
/*     */   public static String getAreaTreeJsonStr()
/*     */   {
/*  72 */     AreaBean areabean = getAreaBean(root_area_id);
/*  73 */     if (areabean != null)
/*     */     {
/*  75 */       String json_str = "[{\"id\":" + root_area_id + ",\"text\":\"" + areabean.getArea_cname() + "\"";
/*  76 */       String child_str = getAreaTreeJsonStrHandl(getChildAreaList(root_area_id));
/*  77 */       if ((child_str != null) && (!"".equals(child_str)))
/*  78 */         json_str = json_str + ",\"children\":[" + child_str + "]";
/*  79 */       json_str = json_str + "}]";
/*  80 */       return json_str;
/*     */     }
/*  82 */     return "[]";
/*     */   }
/*     */ 
/*     */   public static String getAreaTreeJsonStrHandl(List<AreaBean> all_area_list)
/*     */   {
/*  91 */     String json_str = "";
/*  92 */     if ((all_area_list != null) && (all_area_list.size() > 0))
/*     */     {
/*  94 */       for (int i = 0; i < all_area_list.size(); i++)
/*     */       {
/*  96 */         json_str = json_str + "{";
/*  97 */         json_str = json_str + "\"id\":" + ((AreaBean)all_area_list.get(i)).getArea_id() + ",\"text\":\"" + ((AreaBean)all_area_list.get(i)).getArea_cname() + "\"";
/*  98 */         List child_o_list = getChildAreaList(((AreaBean)all_area_list.get(i)).getArea_id());
/*  99 */         if ((child_o_list != null) && (child_o_list.size() > 0))
/* 100 */           json_str = json_str + ",\"state\":'closed',\"children\":[" + getAreaTreeJsonStrHandl(child_o_list) + "]";
/* 101 */         json_str = json_str + "}";
/* 102 */         if (i + 1 != all_area_list.size())
/* 103 */           json_str = json_str + ",";
/*     */       }
/*     */     }
/* 106 */     return json_str;
/*     */   }
/*     */ 
/*     */   public static List<AreaBean> getChildAreaList(int area_id)
/*     */   {
/* 116 */     List oL = new ArrayList();
/* 117 */     Iterator iter = area_map.entrySet().iterator();
/* 118 */     while (iter.hasNext()) {
/* 119 */       Map.Entry entry = (Map.Entry)iter.next();
/* 120 */       int key = ((Integer)entry.getKey()).intValue();
/* 121 */       if (area_id == ((AreaBean)area_map.get(Integer.valueOf(key))).getParent_id()) {
/* 122 */         oL.add((AreaBean)entry.getValue());
/*     */       }
/*     */     }
/* 125 */     Collections.sort(oL, new AreaManager.AreaComparator());
/* 126 */     return oL;
/*     */   }
/*     */ 
/*     */   public static String getChildAreaCount(int area_id)
/*     */   {
/* 136 */     int count = 0;
/* 137 */     Iterator iter = area_map.entrySet().iterator();
/* 138 */     while (iter.hasNext()) {
/* 139 */       Map.Entry entry = (Map.Entry)iter.next();
/* 140 */       String key = (String)entry.getKey();
/* 141 */       if (area_id == ((AreaBean)area_map.get(key)).getParent_id()) {
/* 142 */         count++;
/*     */       }
/*     */     }
/* 145 */     return count;
/*     */   }
/*     */ 
/*     */   public static AreaBean getAreaBean(int area_id)
/*     */   {
/* 154 */     if (area_map.containsKey(Integer.valueOf(area_id)))
/*     */     {
/* 156 */       return (AreaBean)area_map.get(Integer.valueOf(area_id));
/*     */     }
/*     */ 
/* 159 */     AreaBean ob = AreaDAO.getAreaBean(area_id);
/* 160 */     if (ob != null)
/*     */     {
/* 162 */       area_map.put(Integer.valueOf(area_id), ob);
/* 163 */       return ob;
/*     */     }
/* 165 */     return null;
/*     */   }
/*     */ 
/*     */   public static int getAreaID()
/*     */   {
/* 175 */     return PublicTableDAO.getIDByTableName(PublicTableDAO.APPEAL_AREA_TABLE_NAME);
/*     */   }
/*     */ 
/*     */   public static boolean insertArea(AreaBean ob, SettingLogsBean stl)
/*     */   {
/* 185 */     ob.setArea_position(getAreaBean(ob.getParent_id()).getArea_position());
/* 186 */     if (AreaDAO.insertArea(ob, stl))
/*     */     {
/* 188 */       reloadArea();
/* 189 */       return true;
/*     */     }
/* 191 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateArea(AreaBean ob, SettingLogsBean stl)
/*     */   {
/* 202 */     if (AreaDAO.updateArea(ob, stl))
/*     */     {
/* 204 */       reloadArea();
/* 205 */       return true;
/*     */     }
/* 207 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteArea(String area_id, SettingLogsBean stl)
/*     */   {
/* 219 */     if (AreaDAO.deleteArea(area_id, stl))
/*     */     {
/* 221 */       reloadArea();
/* 222 */       return true;
/*     */     }
/* 224 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean saveAreaSort(String area_id, SettingLogsBean stl)
/*     */   {
/* 235 */     if (AreaDAO.saveAreaSort(area_id, stl))
/*     */     {
/* 237 */       reloadArea();
/* 238 */       return true;
/*     */     }
/*     */ 
/* 241 */     return false;
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.appeal.area.AreaManager
 * JD-Core Version:    0.6.2
 */