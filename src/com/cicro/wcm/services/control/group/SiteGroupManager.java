/*     */ package com.cicro.wcm.services.control.group;
/*     */ 
/*     */ import com.cicro.wcm.bean.control.SiteGroupBean;
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.catchs.ISyncCatch;
/*     */ import com.cicro.wcm.catchs.SyncCatchHandl;
/*     */ import com.cicro.wcm.dao.control.SiteGroupDAO;
/*     */ import java.io.PrintStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.Set;
/*     */ import java.util.TreeMap;
/*     */ 
/*     */ public class SiteGroupManager
/*     */   implements ISyncCatch
/*     */ {
/*     */   private static List<SiteGroupBean> group_list;
/*  31 */   private static TreeMap<String, SiteGroupBean> group_map = new TreeMap();
/*     */ 
/*     */   static {
/*  34 */     reloadCatchHandl();
/*     */   }
/*     */ 
/*     */   public void reloadCatch()
/*     */   {
/*  39 */     reloadCatchHandl();
/*     */   }
/*     */ 
/*     */   public static void reloadCatchHandl()
/*     */   {
/*  44 */     group_list = SiteGroupDAO.getSiteGroupList();
/*  45 */     group_map.clear();
/*  46 */     if ((group_list != null) && (group_list.size() > 0))
/*  47 */       for (int i = 0; i < group_list.size(); i++)
/*  48 */         group_map.put(((SiteGroupBean)group_list.get(i)).getSgroup_id(), (SiteGroupBean)group_list.get(i));
/*     */   }
/*     */ 
/*     */   public static void reloadGroupList()
/*     */   {
/*  61 */     SyncCatchHandl.reladCatchForRMI("com.cicro.wcm.services.control.group.SiteGroupManager");
/*     */   }
/*     */ 
/*     */   public static SiteGroupBean getSGroupBeanByID(String sgroup_id)
/*     */   {
/*  73 */     SiteGroupBean sgb = new SiteGroupBean();
/*  74 */     if ((group_list != null) && (group_list.size() > 0))
/*     */     {
/*  76 */       for (int i = 0; i < group_list.size(); i++)
/*     */       {
/*  78 */         if (sgroup_id.equals(((SiteGroupBean)group_list.get(i)).getSgroup_id())) {
/*  79 */           sgb = (SiteGroupBean)group_list.get(i);
/*     */         }
/*     */       }
/*     */     }
/*  83 */     return sgb;
/*     */   }
/*     */ 
/*     */   public static String getGroupTreeJsonStr()
/*     */   {
/*     */     try
/*     */     {
/*  95 */       SiteGroupBean siteGroupBean = getSGroupRoot("0");
/*  96 */       if (siteGroupBean != null)
/*     */       {
/*  98 */         String json_str = "[{\"id\":\"" + siteGroupBean.getSgroup_id() + "\",\"text\":\"" + siteGroupBean.getSgroup_name() + "\",\"attributes\":{\"url\":\"" + 
/*  99 */           siteGroupBean.getSgroup_id() + "\"}";
/* 100 */         String child_str = getGroupTreeJsonStrHandl(getChildGroupListByDeep(siteGroupBean.getSgroup_id()));
/* 101 */         if ((child_str != null) && (!"".equals(child_str)))
/* 102 */           json_str = json_str + ",\"children\":[" + child_str + "]";
/* 103 */         return json_str + "}]";
/*     */       }
/*     */ 
/* 106 */       return "[]";
/*     */     } catch (Exception e) {
/* 108 */       e.printStackTrace();
/* 109 */     }return "[]";
/*     */   }
/*     */ 
/*     */   public static String getGroupTreeJsonStrHandl(List<SiteGroupBean> all_group_list)
/*     */   {
/* 121 */     String json_str = "";
/* 122 */     if ((all_group_list != null) && (all_group_list.size() > 0))
/*     */     {
/* 124 */       for (int i = 0; i < all_group_list.size(); i++)
/*     */       {
/* 126 */         json_str = json_str + "{";
/* 127 */         json_str = json_str + "\"id\":\"" + ((SiteGroupBean)all_group_list.get(i)).getSgroup_id() + "\",\"text\":\"" + ((SiteGroupBean)all_group_list.get(i)).getSgroup_name() + "\",\"attributes\":{\"url\":\"" + 
/* 128 */           ((SiteGroupBean)all_group_list.get(i)).getSgroup_id() + "\"}";
/* 129 */         List child_m_list = ((SiteGroupBean)all_group_list.get(i)).getChild_menu_list();
/* 130 */         if ((child_m_list != null) && (child_m_list.size() > 0))
/* 131 */           json_str = json_str + ",\"children\":[" + getGroupTreeJsonStrHandl(child_m_list) + "]";
/* 132 */         json_str = json_str + "}";
/* 133 */         if (i + 1 != all_group_list.size())
/* 134 */           json_str = json_str + ",";
/*     */       }
/*     */     }
/* 137 */     return json_str;
/*     */   }
/*     */ 
/*     */   public static List<SiteGroupBean> getChildGroupListByDeep(String group_id)
/*     */   {
/* 147 */     List g_List = new ArrayList();
/* 148 */     Iterator iter = group_map.entrySet().iterator();
/* 149 */     while (iter.hasNext()) {
/* 150 */       Map.Entry entry = (Map.Entry)iter.next();
/* 151 */       SiteGroupBean gb = (SiteGroupBean)entry.getValue();
/* 152 */       if (group_id.equals(gb.getParent_id())) {
/* 153 */         gb.setChild_menu_list(getChildGroupListByDeep(gb.getSgroup_id()));
/* 154 */         g_List.add((SiteGroupBean)entry.getValue());
/*     */       }
/*     */     }
/* 157 */     Collections.sort(g_List, new SiteGroupManager.GroupComparator());
/* 158 */     return g_List;
/*     */   }
/*     */ 
/*     */   public static List<SiteGroupBean> getSGroupChildListByID(String sgroup_id)
/*     */   {
/* 169 */     List l = new ArrayList();
/* 170 */     if ((group_list != null) && (group_list.size() > 0))
/*     */     {
/* 172 */       for (int i = 0; i < group_list.size(); i++)
/*     */       {
/* 174 */         if (sgroup_id.equals(((SiteGroupBean)group_list.get(i)).getParent_id()))
/* 175 */           l.add((SiteGroupBean)group_list.get(i));
/*     */       }
/*     */     }
/* 178 */     return l;
/*     */   }
/*     */ 
/*     */   public static List<SiteGroupBean> getSGroupAllChildListByID(String sgroup_id)
/*     */   {
/* 190 */     List l = new ArrayList();
/* 191 */     if ((group_list != null) && (group_list.size() > 0))
/*     */     {
/* 193 */       for (int i = 0; i < group_list.size(); i++)
/*     */       {
/* 195 */         if (sgroup_id.equals(((SiteGroupBean)group_list.get(i)).getParent_id()))
/*     */         {
/* 197 */           l.add((SiteGroupBean)group_list.get(i));
/* 198 */           List cl = getSGroupAllChildListByID(((SiteGroupBean)group_list.get(i)).getSgroup_id());
/* 199 */           if ((cl != null) && (cl.size() > 0))
/* 200 */             l.addAll(cl);
/*     */         }
/*     */       }
/*     */     }
/* 204 */     return l;
/*     */   }
/*     */ 
/*     */   public static boolean insertSiteGroup(SiteGroupBean sgb, SettingLogsBean stl)
/*     */   {
/* 216 */     if (SiteGroupDAO.insertSiteGroup(sgb, stl))
/*     */     {
/* 218 */       reloadGroupList();
/* 219 */       return true;
/*     */     }
/* 221 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateSiteGroup(SiteGroupBean sgb, SettingLogsBean stl)
/*     */   {
/* 234 */     if (SiteGroupDAO.updateSiteGroup(sgb, stl))
/*     */     {
/* 236 */       reloadGroupList();
/* 237 */       return true;
/*     */     }
/* 239 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean saveSGroupSort(String sgroup_ids, SettingLogsBean stl)
/*     */   {
/* 250 */     if (SiteGroupDAO.saveSGroupSort(sgroup_ids, stl))
/*     */     {
/* 252 */       reloadGroupList();
/* 253 */       return true;
/*     */     }
/* 255 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteSiteGroup(String sgroup_ids, SettingLogsBean stl)
/*     */   {
/*     */     try
/*     */     {
/* 267 */       String[] s_ids = sgroup_ids.trim().split(",");
/* 268 */       for (int i = 0; i < s_ids.length; i++) {
/* 269 */         SiteGroupDAO.deleteSiteGroup(s_ids[i], stl);
/*     */       }
/* 271 */       reloadGroupList();
/* 272 */       return true;
/*     */     } catch (Exception e) {
/* 274 */       e.printStackTrace();
/* 275 */     }return false;
/*     */   }
/*     */ 
/*     */   public static SiteGroupBean getSGroupRoot(String sgroup_id)
/*     */   {
/* 287 */     return (SiteGroupBean)getSGroupChildListByID(sgroup_id).get(0);
/*     */   }
/*     */ 
/*     */   public static int getSGroupSortByID(String sgroup_id)
/*     */   {
/*     */     try
/*     */     {
/* 297 */       List list = getSGroupChildListByID(sgroup_id);
/* 298 */       if ((list != null) && (list.size() > 0)) {
/* 299 */         return ((SiteGroupBean)list.get(list.size() - 1)).getSgroup_sort();
/*     */       }
/* 301 */       return 0;
/*     */     }
/*     */     catch (Exception e) {
/* 304 */       e.printStackTrace();
/* 305 */     }return -1;
/*     */   }
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/* 335 */     String str = getGroupTreeJsonStr();
/* 336 */     System.out.println(str);
/*     */   }
/*     */ 
/*     */   public static void insertSiteGroup()
/*     */   {
/* 341 */     SiteGroupBean sgb = new SiteGroupBean();
/* 342 */     sgb.setParent_id("0");
/* 343 */     sgb.setSgroup_id("11111");
/* 344 */     sgb.setSgroup_ip("192.168.12.18");
/* 345 */     sgb.setSgroup_memo("西安市站群");
/* 346 */     sgb.setSgroup_name("西安市站群");
/* 347 */     sgb.setSgroup_prot("8080");
/*     */ 
/* 350 */     SettingLogsBean slb = new SettingLogsBean();
/* 351 */     slb.setIp("192.168.12.78");
/* 352 */     slb.setUser_id(1);
/* 353 */     slb.setUser_name("李苏培");
/*     */ 
/* 355 */     insertSiteGroup(sgb, slb);
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.control.group.SiteGroupManager
 * JD-Core Version:    0.6.2
 */