/*    */ package com.cicro.wcm.template.velocity.impl;
/*    */ 
/*    */ import com.cicro.wcm.bean.query.QueryConfBean;
/*    */ import com.cicro.wcm.services.query.QueryConfManager;
/*    */ import com.cicro.wcm.template.velocity.VelocityContextAbstract;
/*    */ import java.io.PrintStream;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import org.apache.velocity.VelocityContext;
/*    */ 
/*    */ public class VelocityQueryContextImp extends VelocityContextAbstract
/*    */ {
/*    */   public VelocityQueryContextImp(HttpServletRequest request)
/*    */   {
/* 14 */     super(request);
/*    */   }
/*    */   public void vcontextPut(String key, Object o) {
/* 17 */     this.vcontext.put(key, o);
/*    */   }
/*    */ 
/*    */   public void setTemplatID(String conf_id, String temp_type)
/*    */   {
/* 29 */     if ((conf_id != null) && (!"".equals(conf_id)))
/*    */     {
/* 31 */       this.vcontext.put("conf_id", conf_id);
/*    */       try {
/* 33 */         if ("list".equals(temp_type))
/* 34 */           this.template_id = QueryConfManager.getQueryConfBean(Integer.parseInt(conf_id)).getT_list_id();
/* 35 */         if ("content".equals(temp_type))
/* 36 */           this.template_id = QueryConfManager.getQueryConfBean(Integer.parseInt(conf_id)).getT_content_id();
/*    */       }
/*    */       catch (Exception e) {
/* 39 */         e.printStackTrace();
/* 40 */         System.out.println("setTemplatID -- ConfBean is null id:" + conf_id);
/*    */       }
/*    */     }
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.template.velocity.impl.VelocityQueryContextImp
 * JD-Core Version:    0.6.2
 */