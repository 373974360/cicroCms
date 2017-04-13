/*    */ package com.cicro.wcm.template.velocity.impl;
/*    */ 
/*    */ import com.cicro.wcm.bean.appCom.guestbook.GuestBookCategory;
/*    */ import com.cicro.wcm.bean.appCom.guestbook.GuestBookSub;
/*    */ import com.cicro.wcm.services.appCom.guestbook.GuestBookCategoryManager;
/*    */ import com.cicro.wcm.services.appCom.guestbook.GuestBookSubManager;
/*    */ import com.cicro.wcm.template.velocity.VelocityContextAbstract;
/*    */ import java.io.PrintStream;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import org.apache.velocity.VelocityContext;
/*    */ 
/*    */ public class VelocityGuestBookContextImp extends VelocityContextAbstract
/*    */ {
/*    */   public VelocityGuestBookContextImp(HttpServletRequest request)
/*    */   {
/* 14 */     super(request);
/*    */   }
/*    */ 
/*    */   public void vcontextPut(String key, Object o) {
/* 18 */     this.vcontext.put(key, o);
/*    */   }
/*    */ 
/*    */   public void setTemplateID(String site_ids, String template_ids)
/*    */   {
/* 29 */     this.site_id = site_ids;
/* 30 */     this.template_id = template_ids;
/*    */   }
/*    */ 
/*    */   public void setGuestbookTemplateID(String gbs_id, String temp_type)
/*    */   {
/* 41 */     if ((gbs_id != null) && (!"".equals(gbs_id)))
/*    */     {
/* 43 */       this.vcontext.put("sub_id", gbs_id);
/*    */       try {
/* 45 */         GuestBookSub gbs = GuestBookSubManager.getGuestBookSub(Integer.parseInt(gbs_id));
/* 46 */         if ("index".equals(temp_type))
/*    */         {
/* 48 */           GuestBookCategory cat = GuestBookCategoryManager.getGuestBookCategoryBean(gbs.getCat_id());
/* 49 */           if (cat != null)
/* 50 */             this.template_id = cat.getTemplate_index();
/*    */         }
/* 52 */         if ("list".equals(temp_type))
/* 53 */           this.template_id = gbs.getTemplate_list();
/* 54 */         if ("form".equals(temp_type))
/* 55 */           this.template_id = gbs.getTemplate_form();
/* 56 */         if (!"content".equals(temp_type)) return;
/* 57 */         this.template_id = gbs.getTemplate_content();
/*    */       }
/*    */       catch (Exception e)
/*    */       {
/* 61 */         System.out.println("setGuestbookTemplateID----gbs_id is null");
/* 62 */         e.printStackTrace();
/*    */       }
/*    */     } else {
/* 65 */       System.out.println("setGuestbookTemplateID----gbs_id is null");
/*    */     }
/*    */   }
/*    */ 
/*    */   public void setGuestbookIndexTemplateID(String cat_id, String site_id)
/*    */   {
/* 76 */     if ((cat_id != null) && (!"".equals(cat_id)))
/*    */     {
/* 78 */       this.vcontext.put("gbcat_id", cat_id);
/*    */       try {
/* 80 */         GuestBookCategory cat = GuestBookCategoryManager.getGuestBookCategoryBean(Integer.parseInt(cat_id));
/* 81 */         if (cat == null) return;
/* 82 */         this.template_id = cat.getTemplate_index();
/*    */       }
/*    */       catch (Exception e)
/*    */       {
/* 86 */         System.out.println("setGuestbookIndexTemplateID----gbs_id is null");
/* 87 */         e.printStackTrace();
/*    */       }
/*    */     } else {
/* 90 */       System.out.println("setGuestbookIndexTemplateID----gbs_id is null");
/*    */     }
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.template.velocity.impl.VelocityGuestBookContextImp
 * JD-Core Version:    0.6.2
 */