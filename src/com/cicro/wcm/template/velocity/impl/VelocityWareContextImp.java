/*    */ package com.cicro.wcm.template.velocity.impl;
/*    */ 
/*    */ import com.cicro.wcm.bean.system.ware.WareBean;
/*    */ import com.cicro.wcm.services.system.ware.WareInfoManager;
/*    */ import com.cicro.wcm.template.velocity.VelocityContextAbstract;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import org.apache.velocity.VelocityContext;
/*    */ 
/*    */ public class VelocityWareContextImp extends VelocityContextAbstract
/*    */ {
/*    */   public VelocityWareContextImp(WareBean wb)
/*    */   {
/* 13 */     if (wb.getWare_type() == 2)
/*    */     {
/* 15 */       Map m = new HashMap();
/* 16 */       m.put("ware_id", wb.getWare_id());
/* 17 */       m.put("app_id", wb.getApp_id());
/* 18 */       m.put("site_id", wb.getSite_id());
/* 19 */       this.vcontext.put("wareData", WareInfoManager.getWareInfoList(m));
/*    */     }
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.template.velocity.impl.VelocityWareContextImp
 * JD-Core Version:    0.6.2
 */