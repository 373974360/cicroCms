/*     */ package com.cicro.wcm.services.org.desktop;
/*     */ 
/*     */ import com.cicro.wcm.bean.org.desktop.DeskTopBean;
/*     */ import com.cicro.wcm.catchs.ISyncCatch;
/*     */ import com.cicro.wcm.catchs.SyncCatchHandl;
/*     */ import com.cicro.wcm.dao.PublicTableDAO;
/*     */ import com.cicro.wcm.dao.org.desktop.DesktopDAO;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class DesktopManager
/*     */   implements ISyncCatch
/*     */ {
/*  26 */   private static Map<Integer, List<DeskTopBean>> desk_map = new HashMap();
/*     */ 
/*     */   static {
/*  29 */     reloadCatchHandl();
/*     */   }
/*     */ 
/*     */   public void reloadCatch()
/*     */   {
/*  34 */     reloadCatchHandl();
/*     */   }
/*     */ 
/*     */   public static void reloadCatchHandl()
/*     */   {
/*     */     try {
/*  40 */       desk_map.clear();
/*  41 */       List l = DesktopDAO.getUserDesktopList();
/*  42 */       if ((l != null) && (l.size() > 0))
/*     */       {
/*  44 */         for (DeskTopBean dtb : l)
/*     */         {
/*  46 */           if (desk_map.containsKey(Integer.valueOf(dtb.getUser_id())))
/*     */           {
/*  48 */             ((List)desk_map.get(Integer.valueOf(dtb.getUser_id()))).add(dtb);
/*     */           }
/*     */           else {
/*  51 */             List d_l = new ArrayList();
/*  52 */             d_l.add(dtb);
/*  53 */             desk_map.put(Integer.valueOf(dtb.getUser_id()), d_l);
/*     */           }
/*     */         }
/*     */       }
/*     */     } catch (Exception e) {
/*  58 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void reloadDesktop()
/*     */   {
/*  64 */     SyncCatchHandl.reladCatchForRMI("com.cicro.wcm.services.org.desktop.DesktopManager");
/*     */   }
/*     */ 
/*     */   public static List<DeskTopBean> getUserDesktop(int user_id)
/*     */   {
/*  74 */     if (desk_map.containsKey(Integer.valueOf(user_id))) {
/*  75 */       return (List)desk_map.get(Integer.valueOf(user_id));
/*     */     }
/*  77 */     return null;
/*     */   }
/*     */ 
/*     */   public static boolean insertUserDesktop(String user_id, List<DeskTopBean> l)
/*     */   {
/*  88 */     if (DesktopDAO.deleteUserDesktop(user_id))
/*     */     {
/*     */       try {
/*  91 */         if ((l != null) && (l.size() > 0))
/*     */         {
/*  93 */           for (DeskTopBean dtb : l)
/*     */           {
/*  95 */             dtb.setId(PublicTableDAO.getIDByTableName(PublicTableDAO.DESKTOP_TABLE_NAME));
/*  96 */             DesktopDAO.insertUserDesktop(dtb);
/*     */           }
/*     */         }
/*     */       } catch (Exception e) {
/* 100 */         e.printStackTrace();
/* 101 */         return false;
/*     */       }
/* 103 */       reloadDesktop();
/* 104 */       return true;
/*     */     }
/* 106 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteUserDesktop(String user_ids)
/*     */   {
/* 116 */     if (DesktopDAO.deleteUserDesktop(user_ids))
/*     */     {
/* 118 */       reloadDesktop();
/* 119 */       return true;
/*     */     }
/* 121 */     return false;
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.org.desktop.DesktopManager
 * JD-Core Version:    0.6.2
 */