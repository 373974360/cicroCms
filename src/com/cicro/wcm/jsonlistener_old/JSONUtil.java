/*   */ package com.cicro.wcm.jsonlistener_old;
/*   */ 
/*   */ public class JSONUtil
/*   */ {
/*   */   public Object getBean(String fullClassName)
/*   */   {
/*   */     try
/*   */     {
/* 6 */       return Class.forName(fullClassName).newInstance(); } catch (Exception e) {
/*   */     }
/* 8 */     return new Object();
/*   */   }
/*   */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.jsonlistener.JSONUtil
 * JD-Core Version:    0.6.2
 */