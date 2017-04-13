/*     */ package com.cicro.wcm.services.appeal;
/*     */ 
/*     */ import com.cicro.wcm.bean.appeal.area.AreaBean;
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.dao.PublicTableDAO;
/*     */ import com.cicro.wcm.dao.appeal.AreaDAO;

/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.Set;
/*     */ import java.util.TreeMap;
/*     */ 
/*     */ public class AreaManager
/*     */ {
/*  32 */   private static TreeMap<Integer, AreaBean> area_map = new TreeMap();
/*  33 */   private static int root_area_id = 1;
/*     */ 
/*     */   static {
/*  36 */     reloadArea();
/*     */   }
/*     */ 
/*     */   public static void reloadArea()
/*     */   {
/*  44 */     List area_list = AreaDAO.getAllAreaList();
/*  45 */     area_map.clear();
/*  46 */     if ((area_list != null) && (area_list.size() > 0))
/*  47 */       for (int i = 0; i < area_list.size(); i++)
/*     */       {
/*  49 */         area_map.put(Integer.valueOf(((AreaBean)area_list.get(i)).getArea_id()), (AreaBean)area_list.get(i));
/*     */       }
/*     */   }
/*     */ 
/*     */   public static String getAreaTreeJsonStr()
/*     */   {
/*  61 */     AreaBean areabean = getAreaBean(root_area_id);
/*  62 */     if (areabean != null)
/*     */     {
/*  64 */       String json_str = "[{\"id\":" + root_area_id + ",\"text\":\"" + areabean.getArea_cname() + "\"";
/*  65 */       String child_str = getAreaTreeJsonStrHandl(getChildAreaList(root_area_id));
/*  66 */       if ((child_str != null) && (!"".equals(child_str)))
/*  67 */         json_str = json_str + ",\"children\":[" + child_str + "]";
/*  68 */       json_str = json_str + "}]";
/*  69 */       return json_str;
/*     */     }
/*  71 */     return "[]";
/*     */   }
/*     */ 
/*     */   public static String getAreaTreeJsonStrHandl(List<AreaBean> all_area_list)
/*     */   {
/*  80 */     String json_str = "";
/*  81 */     if ((all_area_list != null) && (all_area_list.size() > 0))
/*     */     {
/*  83 */       for (int i = 0; i < all_area_list.size(); i++)
/*     */       {
/*  85 */         json_str = json_str + "{";
/*  86 */         json_str = json_str + "\"id\":" + ((AreaBean)all_area_list.get(i)).getArea_id() + ",\"text\":\"" + ((AreaBean)all_area_list.get(i)).getArea_cname() + "\"";
/*  87 */         List child_o_list = getChildAreaList(((AreaBean)all_area_list.get(i)).getArea_id());
/*  88 */         if ((child_o_list != null) && (child_o_list.size() > 0))
/*  89 */           json_str = json_str + ",\"children\":[" + getAreaTreeJsonStrHandl(child_o_list) + "]";
/*  90 */         json_str = json_str + "}";
/*  91 */         if (i + 1 != all_area_list.size())
/*  92 */           json_str = json_str + ",";
/*     */       }
/*     */     }
/*  95 */     return json_str;
/*     */   }
/*     */ 
/*     */   public static List<AreaBean> getChildAreaList(int area_id)
/*     */   {
/* 105 */     List oL = new ArrayList();
/* 106 */     Iterator iter = area_map.entrySet().iterator();
/* 107 */     while (iter.hasNext()) {
/* 108 */       Entry entry = (Entry)iter.next();
/* 109 */       int key = ((Integer)entry.getKey()).intValue();
/* 110 */       if (area_id == ((AreaBean)area_map.get(Integer.valueOf(key))).getParent_id()) {
/* 111 */         oL.add((AreaBean)entry.getValue());
/*     */       }
/*     */     }
/*     */ 
/* 115 */     return oL;
/*     */   }
/*     */ 
/*     */   public static String getChildAreaCount(int area_id)
/*     */   {
/* 125 */     int count = 0;
/* 126 */     Iterator iter = area_map.entrySet().iterator();
/* 127 */     while (iter.hasNext()) {
/* 128 */       Entry entry = (Entry)iter.next();
/* 129 */       String key = (String)entry.getKey();
/* 130 */       if (area_id == ((AreaBean)area_map.get(key)).getParent_id()) {
/* 131 */         count++;
/*     */       }
/*     */     }
/* 134 */     return count+"";
/*     */   }
/*     */ 
/*     */   public static AreaBean getAreaBean(int area_id)
/*     */   {
/* 146 */     if (area_map.containsKey(Integer.valueOf(area_id)))
/*     */     {
/* 148 */       return (AreaBean)area_map.get(Integer.valueOf(area_id));
/*     */     }
/*     */ 
/* 151 */     AreaBean ob = AreaDAO.getAreaBean(area_id);
/* 152 */     area_map.put(Integer.valueOf(area_id), ob);
/* 153 */     return ob;
/*     */   }
/*     */ 
/*     */   public static int getAreaID()
/*     */   {
/* 163 */     return PublicTableDAO.getIDByTableName(PublicTableDAO.APPEAL_AREA_TABLE_NAME);
/*     */   }
/*     */ 
/*     */   public static boolean insertArea(AreaBean ob, SettingLogsBean stl)
/*     */   {
/* 173 */     ob.setArea_position(getAreaBean(ob.getParent_id()).getArea_position());
/* 174 */     if (AreaDAO.insertArea(ob, stl))
/*     */     {
/* 176 */       reloadArea();
/* 177 */       return true;
/*     */     }
/* 179 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateArea(AreaBean ob, SettingLogsBean stl)
/*     */   {
/* 190 */     if (AreaDAO.updateArea(ob, stl))
/*     */     {
/* 192 */       reloadArea();
/* 193 */       return true;
/*     */     }
/* 195 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteArea(String area_id, SettingLogsBean stl)
/*     */   {
/* 207 */     if (AreaDAO.deleteArea(area_id, stl))
/*     */     {
/* 209 */       reloadArea();
/* 210 */       return true;
/*     */     }
/* 212 */     return false;
/*     */   }
/*     */ 
/*     */   public static void main(String[] ares)
/*     */   {
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.appeal.AreaManager
 * JD-Core Version:    0.6.2
 */