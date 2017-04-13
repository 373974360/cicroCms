/*    */ package com.cicro.util.ip;
/*    */ 
/*    */ class IPSeeker$IPLocation
/*    */ {
/* 56 */   public String country = this.area = "";
/*    */   public String area;
/*    */ 
/*    */   public IPSeeker$IPLocation(IPSeeker paramIPSeeker)
/*    */   {
/*    */   }
/*    */ 
/*    */   public IPLocation getCopy()
/*    */   {
/* 59 */     IPLocation ret = new IPLocation(this.this$0);
/* 60 */     ret.country = this.country;
/* 61 */     ret.area = this.area;
/* 62 */     return ret;
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.util.ip.IPSeeker.IPLocation
 * JD-Core Version:    0.6.2
 */