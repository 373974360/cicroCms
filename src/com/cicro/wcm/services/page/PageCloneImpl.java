/*    */ package com.cicro.wcm.services.page;
/*    */ 
/*    */ import com.cicro.wcm.bean.page.PageBean;
/*    */ import com.cicro.wcm.dao.PublicTableDAO;
/*    */ import com.cicro.wcm.dao.page.PageDAO;
/*    */ import com.cicro.wcm.services.control.site.ICloneSite;
/*    */ import java.util.List;
/*    */ 
/*    */ public class PageCloneImpl
/*    */   implements ICloneSite
/*    */ {
/*    */   public boolean cloneSite(String site_id, String s_site_id)
/*    */   {
/*    */     try
/*    */     {
/* 14 */       List l = PageDAO.getPageListBySiteID(s_site_id);
/* 15 */       if ((l != null) && (l.size() > 0))
/*    */       {
/* 17 */         for (PageBean pb : l)
/*    */         {
/* 19 */           pb.setId(PublicTableDAO.getIDByTableName(PublicTableDAO.PAGE_TABLE_NAME));
/* 20 */           pb.setSite_id(site_id);
/* 21 */           if (PageDAO.clonePage(pb))
/*    */           {
/* 23 */             PageManager.createHtmlPage(pb);
/*    */           }
/*    */         }
/* 26 */         PageManager.reloadPage();
/*    */       }
/* 28 */       return true;
/*    */     }
/*    */     catch (Exception e) {
/* 31 */       e.printStackTrace();
/* 32 */     }return false;
/*    */   }
/*    */ 
/*    */   public static void main(String[] args)
/*    */   {
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.page.PageCloneImpl
 * JD-Core Version:    0.6.2
 */