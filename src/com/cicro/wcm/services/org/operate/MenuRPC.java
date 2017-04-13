/*     */ package com.cicro.wcm.services.org.operate;
/*     */ 
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.bean.org.operate.MenuBean;
/*     */ import com.cicro.wcm.services.Log.LogManager;
/*     */ import java.io.PrintStream;
/*     */ import java.util.List;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ 
/*     */ public class MenuRPC
/*     */ {
/*     */   public static String getMenuTreeJsonStr()
/*     */   {
/*  19 */     return MenuManager.getMenuTreeJsonStr();
/*     */   }
/*     */ 
/*     */   public static int getMenuID()
/*     */   {
/*  29 */     return MenuManager.getMenuID();
/*     */   }
/*     */ 
/*     */   public static MenuBean getMenuBean(int menu_id)
/*     */   {
/*  39 */     return MenuManager.getMenuBean(menu_id);
/*     */   }
/*     */ 
/*     */   public static String getChildMenuCount(String menu_id)
/*     */   {
/*  50 */     return MenuManager.getChildMenuCount(menu_id);
/*     */   }
/*     */ 
/*     */   public static List<MenuBean> getChildMenuList(String menu_id)
/*     */   {
/*  61 */     return MenuManager.getChildMenuList(menu_id);
/*     */   }
/*     */ 
/*     */   public static boolean insertMenu(MenuBean mb, HttpServletRequest request)
/*     */   {
/*  72 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/*  73 */     if (stl != null)
/*     */     {
/*  75 */       return MenuManager.insertMenu(mb, stl);
/*     */     }
/*  77 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateMenu(MenuBean mb, HttpServletRequest request)
/*     */   {
/*  88 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/*  89 */     if (stl != null)
/*     */     {
/*  91 */       return MenuManager.updateMenu(mb, stl);
/*     */     }
/*  93 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean saveMenuSort(String menu_id, HttpServletRequest request)
/*     */   {
/* 104 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 105 */     if (stl != null)
/*     */     {
/* 107 */       return MenuManager.saveMenuSort(menu_id, stl);
/*     */     }
/* 109 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteMenu(String menu_id, HttpServletRequest request)
/*     */   {
/* 120 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 121 */     if (stl != null)
/*     */     {
/* 123 */       return MenuManager.deleteMenu(menu_id, stl);
/*     */     }
/* 125 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean moveMenu(String parent_id, String menu_ids, HttpServletRequest request)
/*     */   {
/* 135 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 136 */     if (stl != null)
/*     */     {
/* 138 */       return MenuManager.moveMenu(parent_id, menu_ids, stl);
/*     */     }
/* 140 */     return false;
/*     */   }
/*     */ 
/*     */   public static void main(String[] arg)
/*     */   {
/* 145 */     System.out.println(getMenuTreeJsonStr());
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.org.operate.MenuRPC
 * JD-Core Version:    0.6.2
 */