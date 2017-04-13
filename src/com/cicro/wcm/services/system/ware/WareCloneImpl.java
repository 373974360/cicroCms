/*    */ package com.cicro.wcm.services.system.ware;
/*    */ 
/*    */ import com.cicro.wcm.bean.system.ware.WareBean;
/*    */ import com.cicro.wcm.bean.system.ware.WareCategoryBean;
/*    */ import com.cicro.wcm.dao.PublicTableDAO;
/*    */ import com.cicro.wcm.dao.system.ware.WareCategoryDAO;
/*    */ import com.cicro.wcm.dao.system.ware.WareDAO;
/*    */ import com.cicro.wcm.services.control.site.ICloneSite;
/*    */ import java.io.IOException;
/*    */ import java.util.List;
/*    */ 
/*    */ public class WareCloneImpl
/*    */   implements ICloneSite
/*    */ {
/*    */   public boolean cloneSite(String site_id, String s_site_id)
/*    */   {
/*    */     try
/*    */     {
/* 17 */       wareCategoryClone(site_id, s_site_id);
/* 18 */       wareClone(site_id, s_site_id);
/* 19 */       return true;
/*    */     }
/*    */     catch (Exception e) {
/* 22 */       e.printStackTrace();
/* 23 */     }return false;
/*    */   }
/*    */ 
/*    */   public static boolean wareCategoryClone(String site_id, String s_site_id)
/*    */   {
/* 35 */     List l = WareCategoryDAO.getWareCataListBySiteID(s_site_id);
/* 36 */     if ((l != null) && (l.size() > 0))
/*    */     {
/* 38 */       for (WareCategoryBean wcb : l)
/*    */       {
/* 40 */         wcb.setSite_id(site_id);
/* 41 */         WareCategoryDAO.cloneWareCate(wcb);
/*    */       }
/* 43 */       WareCategoryManager.reloadMap();
/*    */     }
/* 45 */     return true;
/*    */   }
/*    */ 
/*    */   public static boolean wareClone(String site_id, String s_site_id)
/*    */   {
/* 56 */     List l = WareDAO.getWareListBySiteID(s_site_id);
/* 57 */     if ((l != null) && (l.size() > 0))
/*    */     {
/* 59 */       for (WareBean wb : l)
/*    */       {
/* 61 */         wb.setId(PublicTableDAO.getIDByTableName(PublicTableDAO.WARE_TABLE_NAME));
/* 62 */         wb.setSite_id(site_id);
/* 63 */         if (WareDAO.cloneWare(wb))
/*    */         {
/* 65 */           if ((wb.getWare_type() == 0) && (!"".equals(wb.getWare_content().trim())))
/*    */             try {
/* 67 */               WareManager.createWarePage(wb);
/*    */             }
/*    */             catch (IOException e) {
/* 70 */               e.printStackTrace();
/*    */             }
/*    */         }
/*    */       }
/* 74 */       WareManager.reloadMap();
/*    */     }
/* 76 */     return true;
/*    */   }
/*    */ 
/*    */   public static void main(String[] args)
/*    */   {
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.system.ware.WareCloneImpl
 * JD-Core Version:    0.6.2
 */