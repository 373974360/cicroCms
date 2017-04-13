/*    */ package com.cicro.wcm.services.org.role;
/*    */ 
/*    */ import com.cicro.wcm.bean.org.role.RoleBean;
/*    */ import com.cicro.wcm.dao.org.role.RoleDAO;
/*    */ import com.cicro.wcm.dao.org.role.RoleOptDAO;
/*    */ import com.cicro.wcm.services.control.site.ICloneSite;
/*    */ import java.util.List;
/*    */ 
/*    */ public class RoleCloneImpl
/*    */   implements ICloneSite
/*    */ {
/*    */   public boolean cloneSite(String site_id, String s_site_id)
/*    */   {
/*    */     try
/*    */     {
/* 14 */       roleClone(site_id, s_site_id);
/* 15 */       return true;
/*    */     }
/*    */     catch (Exception e) {
/* 18 */       e.printStackTrace();
/* 19 */     }return false;
/*    */   }
/*    */ 
/*    */   public static boolean roleClone(String site_id, String s_site_id)
/*    */   {
/*    */     try
/*    */     {
/* 26 */       List l = RoleDAO.getRoleListBySiteID(s_site_id);
/* 27 */       if ((l != null) && (l.size() > 0))
/*    */       {
/* 29 */         for (RoleBean rb : l)
/*    */         {
/* 31 */           rb.setSite_id(site_id);
/* 32 */           String opt_ids = RoleOptManager.getOptIDSByRoleID(rb.getRole_id());
/* 33 */           RoleDAO.insertRole(rb, null);
/*    */ 
/* 35 */           RoleOptDAO.insertOptReleRole(rb.getRole_id(), opt_ids, null);
/*    */         }
/*    */       }
/* 38 */       return true;
/*    */     }
/*    */     catch (Exception e) {
/* 41 */       e.printStackTrace();
/* 42 */     }return false;
/*    */   }
/*    */ 
/*    */   public static void main(String[] args)
/*    */   {
/* 48 */     roleClone("11", "11111ddd");
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.org.role.RoleCloneImpl
 * JD-Core Version:    0.6.2
 */