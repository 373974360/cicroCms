/*    */ package com.cicro.wcm.dao.org.desktop;
/*    */ 
/*    */ import com.cicro.wcm.bean.org.desktop.DeskTopBean;
/*    */ import com.cicro.wcm.db.DBManager;
/*    */ import java.util.HashMap;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class DesktopDAO
/*    */ {
/*    */   public static List<DeskTopBean> getUserDesktopList()
/*    */   {
/* 30 */     return DBManager.queryFList("getUserDesktopList", "");
/*    */   }
/*    */ 
/*    */   public static boolean insertUserDesktop(DeskTopBean dtb)
/*    */   {
/* 40 */     return DBManager.insert("insert_user_desktop", dtb);
/*    */   }
/*    */ 
/*    */   public static boolean deleteUserDesktop(String user_ids)
/*    */   {
/* 50 */     Map m = new HashMap();
/* 51 */     m.put("user_ids", user_ids);
/* 52 */     return DBManager.delete("delete_user_desktop", m);
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.dao.org.desktop.DesktopDAO
 * JD-Core Version:    0.6.2
 */