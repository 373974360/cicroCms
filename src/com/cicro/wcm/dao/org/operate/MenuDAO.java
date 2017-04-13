/*     */ package com.cicro.wcm.dao.org.operate;
/*     */ 
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.bean.org.operate.MenuBean;
/*     */ import com.cicro.wcm.dao.PublicTableDAO;
/*     */ import com.cicro.wcm.db.DBManager;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class MenuDAO
/*     */ {
/*     */   public static List getAllMenuList()
/*     */   {
/*  32 */     return DBManager.queryFList("getAllMenuList", "");
/*     */   }
/*     */ 
/*     */   public static MenuBean getMenuBean(int menu_id)
/*     */   {
/*  42 */     return (MenuBean)DBManager.queryFObj("getMenuBean", menu_id);
/*     */   }
/*     */ 
/*     */   public static boolean insertMenu(MenuBean mb, SettingLogsBean stl)
/*     */   {
/*  53 */     mb.setMenu_position(mb.getMenu_position() + mb.getMenu_id() + "$");
/*  54 */     if (DBManager.insert("insert_menu", mb))
/*     */     {
/*  56 */       PublicTableDAO.insertSettingLogs("添加", "菜单", mb.getMenu_id(), stl);
/*  57 */       return true;
/*     */     }
/*  59 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateMenu(MenuBean mb, SettingLogsBean stl)
/*     */   {
/*  70 */     if (DBManager.update("update_menu", mb))
/*     */     {
/*  72 */       PublicTableDAO.insertSettingLogs("修改", "菜单", mb.getMenu_id(), stl);
/*  73 */       return true;
/*     */     }
/*  75 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean saveMenuSort(String menu_id, SettingLogsBean stl)
/*     */   {
/*  87 */     if ((menu_id != null) && (!"".equals(menu_id))) {
/*     */       try
/*     */       {
/*  90 */         Map m = new HashMap();
/*  91 */         String[] tempA = menu_id.split(",");
/*  92 */         for (int i = 0; i < tempA.length; i++)
/*     */         {
/*  94 */           m.put("menu_sort", Integer.valueOf(i + 1));
/*  95 */           m.put("menu_id", tempA[i]);
/*  96 */           DBManager.update("update_menu_sort", m);
/*     */         }
/*  98 */         PublicTableDAO.insertSettingLogs("保存排序", "菜单", menu_id, stl);
/*  99 */         return true;
/*     */       }
/*     */       catch (Exception e) {
/* 102 */         e.printStackTrace();
/* 103 */         return false;
/*     */       }
/*     */     }
/*     */ 
/* 107 */     return true;
/*     */   }
/*     */ 
/*     */   public static boolean moveMenu(Map<String, String> m, SettingLogsBean stl)
/*     */   {
/* 118 */     if (DBManager.update("move_menu", m))
/*     */     {
/* 120 */       PublicTableDAO.insertSettingLogs("移动", "菜单", (String)m.get("menu_id"), stl);
/* 121 */       return true;
/*     */     }
/*     */ 
/* 124 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteMenu(String menu_id, SettingLogsBean stl)
/*     */   {
/* 135 */     Map m = new HashMap();
/* 136 */     m.put("menu_id", menu_id);
/* 137 */     if (DBManager.delete("delete_menu", m))
/*     */     {
/* 139 */       PublicTableDAO.insertSettingLogs("删除", "菜单", menu_id, stl);
/* 140 */       return true;
/*     */     }
/* 142 */     return false;
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.dao.org.operate.MenuDAO
 * JD-Core Version:    0.6.2
 */