/*    */ package com.cicro.util.ip;
/*    */ 
/*    */ public class IPEntry
/*    */ {
/*    */   public String beginIp;
/*    */   public String endIp;
/*    */   public String country;
/*    */   public String area;
/*    */ 
/*    */   public IPEntry()
/*    */   {
/* 13 */     this.beginIp = (this.endIp = this.country = this.area = "");
/*    */   }
/*    */ 
/*    */   public String toString() {
/* 17 */     return this.area + "  " + this.country + "IP范围:" + this.beginIp + "-" + this.endIp;
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.util.ip.IPEntry
 * JD-Core Version:    0.6.2
 */