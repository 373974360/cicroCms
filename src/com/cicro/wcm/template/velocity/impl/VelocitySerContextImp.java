/*    */ package com.cicro.wcm.template.velocity.impl;
/*    */ 
/*    */ import com.cicro.wcm.bean.zwgk.ser.SerCategoryBean;
/*    */ import com.cicro.wcm.services.control.site.SiteAppRele;
/*    */ import com.cicro.wcm.services.zwgk.ser.SerCategoryManager;
/*    */ import com.cicro.wcm.template.velocity.VelocityContextAbstract;
/*    */ import java.io.PrintStream;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import org.apache.velocity.VelocityContext;
/*    */ 
/*    */ public class VelocitySerContextImp extends VelocityContextAbstract
/*    */ {
/*    */   public VelocitySerContextImp(HttpServletRequest request)
/*    */   {
/* 13 */     super(request);
/*    */   }
/*    */ 
/*    */   public VelocitySerContextImp()
/*    */   {
/*    */   }
/*    */ 
/*    */   public void vcontextPut(String key, Object o)
/*    */   {
/* 22 */     this.vcontext.put(key, o);
/*    */   }
/*    */ 
/*    */   public void setSerTopicTemplateID(String ser_id, String temp_type)
/*    */   {
/* 27 */     this.site_id = SiteAppRele.getSiteIDByAppID("ggfw");
/* 28 */     if ((ser_id != null) && (!"".equals(ser_id)))
/*    */     {
/* 30 */       this.vcontext.put("ser_id", ser_id);
/* 31 */       System.out.println("setSerTopicTemplateID--------" + this.vcontext.get("ser_id"));
/*    */       try {
/* 33 */         SerCategoryBean scb = SerCategoryManager.getRootSerCategoryBean(Integer.parseInt(ser_id));
/* 34 */         if ("index".equals(temp_type))
/*    */         {
/* 36 */           this.template_id = scb.getTemplate_index();
/*    */         }
/* 38 */         if ("list".equals(temp_type))
/*    */         {
/* 40 */           this.template_id = scb.getTemplate_list();
/*    */         }
/* 42 */         if ("content".equals(temp_type))
/*    */         {
/* 44 */           this.template_id = scb.getTemplate_content();
/*    */         }
/*    */       }
/*    */       catch (Exception e) {
/* 48 */         e.printStackTrace();
/* 49 */         System.out.println("setSerTopicTemplateID -- SerBean is null id:" + ser_id);
/*    */       }
/*    */     }
/*    */   }
/*    */ 
/*    */   public void setSerInfoListTemplateID(String ser_id)
/*    */   {
/* 56 */     this.site_id = SiteAppRele.getSiteIDByAppID("ggfw");
/* 57 */     if ((ser_id != null) && (!"".equals(ser_id)))
/*    */     {
/* 59 */       this.vcontext.put("ser_id", ser_id);
/* 60 */       this.template_id = SerCategoryManager.getSerTemplateID("list");
/*    */     }
/*    */   }
/*    */ 
/*    */   public static void main(String[] args)
/*    */   {
/* 66 */     VelocitySerContextImp s = new VelocitySerContextImp();
/* 67 */     s.template_id = "1122";
/* 68 */     s.site_id = "11111ddd";
/* 69 */     System.out.println(s.parseTemplate("#set($serObject=$SerData.getSerObject(\"25\"))<div>$serObject.cat_name</div>222"));
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.template.velocity.impl.VelocitySerContextImp
 * JD-Core Version:    0.6.2
 */