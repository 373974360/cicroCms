/*     */ package com.cicro.wcm.jsonlistener_old;

import com.cicro.wcm.jsonlistener.RPC_Action;

/*     */
/*     */ class JSON_RPC_Listener$RPC_Action
/*     */ {
/*     */   private String name;
/*     */   private String className;
/*     */ 
/*     */   public String getName()
/*     */   {
/* 106 */     return this.name;
/*     */   }
/*     */ 
/*     */   public void setName(String name)
/*     */   {
/* 111 */     this.name = name;
/*     */   }
/*     */ 
/*     */   public String getClassName() {
/* 115 */     return this.className;
/*     */   }
/*     */ 
/*     */   public void setClassName(String className)
/*     */   {
/* 120 */     this.className = className;
/*     */   }
/*     */ 
/*     */   public JSON_RPC_Listener$RPC_Action(String name, String className) {
/* 124 */     this.name = name;
/* 125 */     this.className = className;
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 130 */     int prime = 31;
/* 131 */     int result = 1;
/* 132 */     result = 31 * result + (
/* 133 */       this.className == null ? 0 : this.className.hashCode());
/* 134 */     result = 31 * result + (this.name == null ? 0 : this.name.hashCode());
/* 135 */     return result;
/*     */   }
/*     */ 
/*     */   public boolean equals(Object obj)
/*     */   {
/* 140 */     if (this == obj)
/* 141 */       return true;
/* 142 */     if (obj == null)
/* 143 */       return false;
/* 144 */     if (getClass() != obj.getClass())
/* 145 */       return false;
/* 146 */     RPC_Action other = (RPC_Action)obj;
/* 147 */     if (this.className == null) {
/* 148 */       if (other.className != null)
/* 149 */         return false;
/* 150 */     } else if (!this.className.equals(other.className))
/* 151 */       return false;
/* 152 */     if (this.name == null) {
/* 153 */       if (other.name != null)
/* 154 */         return false;
/* 155 */     } else if (!this.name.equals(other.name))
/* 156 */       return false;
/* 157 */     return true;
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 162 */     return "name : " + this.name + "\t className : " + this.className;
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.jsonlistener.JSON_RPC_Listener.RPC_Action
 * JD-Core Version:    0.6.2
 */