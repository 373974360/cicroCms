/*    */ package com.cicro.wcm.template.velocity.impl;
/*    */ 
/*    */ import com.cicro.wcm.bean.page.PageBean;
/*    */ import com.cicro.wcm.template.velocity.VelocityContextAbstract;
/*    */ 
/*    */ public class VelocityPageContextImp extends VelocityContextAbstract
/*    */ {
/*    */   public String getHtmlContent(PageBean pb)
/*    */   {
/*  9 */     this.site_id = pb.getSite_id();
/* 10 */     this.template_id = pb.getTemplate_id();
/* 11 */     return super.parseTemplate();
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.template.velocity.impl.VelocityPageContextImp
 * JD-Core Version:    0.6.2
 */