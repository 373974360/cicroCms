/*     */ package com.cicro.wcm.bean.cms.count;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.math.RoundingMode;
/*     */ import java.text.DecimalFormat;
/*     */ import java.text.NumberFormat;
/*     */ 
/*     */ public class CmsCountBean
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -1256934576049508129L;
/*     */   private static final int DIGIT = 2;
/*  41 */   private static NumberFormat nf = null;
/*     */ 
/*  48 */   private String user_name = "";
/*     */   private int input_user;
/*     */   private int count;
/*     */   private int cat_id;
/*  52 */   private String cat_name = "";
/*     */   private int allCount;
/*     */   private int hostInfoCount;
/*     */   private int refInfoCount;
/*     */   private int linkInfoCount;
/*     */   private int inputCount;
/*     */   private int releasedCount;
/*     */   private String releaseRate;
/*  63 */   private int picInfoCount = 0;
/*     */   private String time;
/*     */ 
/*     */   static
/*     */   {
/*  43 */     nf = new DecimalFormat();
/*  44 */     nf.setMaximumFractionDigits(2);
/*  45 */     nf.setRoundingMode(RoundingMode.HALF_UP);
/*     */   }
/*     */ 
/*     */   public CmsCountBean(String input_user, String user_name, String count)
/*     */   {
/*  16 */     int i_count = 0;
/*  17 */     int i_input_user = 0;
/*  18 */     setUser_name(user_name);
/*     */     try {
/*  20 */       i_count = Integer.parseInt(count);
/*  21 */       i_input_user = Integer.parseInt(input_user);
/*     */ 
/*  23 */       setCount(i_count);
/*  24 */       setInput_user(i_input_user);
/*     */     }
/*     */     catch (NumberFormatException localNumberFormatException)
/*     */     {
/*     */     }
/*     */   }
/*     */ 
/*     */   public CmsCountBean()
/*     */   {
/*     */   }
/*     */ 
/*     */   public String getUser_name()
/*     */   {
/*  68 */     return this.user_name;
/*     */   }
/*     */ 
/*     */   public int getInput_user() {
/*  72 */     return this.input_user;
/*     */   }
/*     */ 
/*     */   public int getCount() {
/*  76 */     return this.count;
/*     */   }
/*     */ 
/*     */   public int getCat_id() {
/*  80 */     return this.cat_id;
/*     */   }
/*     */ 
/*     */   public String getCat_name() {
/*  84 */     return this.cat_name;
/*     */   }
/*     */ 
/*     */   public int getAllCount() {
/*  88 */     return this.allCount;
/*     */   }
/*     */ 
/*     */   public int getHostInfoCount() {
/*  92 */     return this.hostInfoCount;
/*     */   }
/*     */ 
/*     */   public int getRefInfoCount() {
/*  96 */     return this.refInfoCount;
/*     */   }
/*     */ 
/*     */   public int getLinkInfoCount() {
/* 100 */     return this.linkInfoCount;
/*     */   }
/*     */ 
/*     */   public String getTime() {
/* 104 */     return this.time;
/*     */   }
/*     */ 
/*     */   public void setUser_name(String userName) {
/* 108 */     this.user_name = userName;
/*     */   }
/*     */ 
/*     */   public void setInput_user(int inputUser) {
/* 112 */     this.input_user = inputUser;
/*     */   }
/*     */ 
/*     */   public void setCount(int count) {
/* 116 */     this.count = count;
/*     */   }
/*     */ 
/*     */   public void setCat_id(int catId) {
/* 120 */     this.cat_id = catId;
/*     */   }
/*     */ 
/*     */   public void setCat_name(String catName) {
/* 124 */     this.cat_name = catName;
/*     */   }
/*     */ 
/*     */   public void setAllCount(int allCount) {
/* 128 */     this.allCount = allCount;
/*     */   }
/*     */ 
/*     */   private void setAllCount()
/*     */   {
/* 135 */     this.allCount = (getHostInfoCount() + getRefInfoCount() + getLinkInfoCount());
/*     */   }
/*     */ 
/*     */   public void setHostInfoCount(int hostInfoCount) {
/* 139 */     this.hostInfoCount = hostInfoCount;
/* 140 */     setAllCount();
/*     */   }
/*     */ 
/*     */   public void setRefInfoCount(int refInfoCount) {
/* 144 */     this.refInfoCount = refInfoCount;
/* 145 */     setAllCount();
/*     */   }
/*     */ 
/*     */   public void setLinkInfoCount(int linkInfoCount) {
/* 149 */     this.linkInfoCount = linkInfoCount;
/* 150 */     setAllCount();
/*     */   }
/*     */ 
/*     */   public void setTime(String time) {
/* 154 */     this.time = time;
/*     */   }
/*     */ 
/*     */   public int getInputCount() {
/* 158 */     return this.inputCount;
/*     */   }
/*     */ 
/*     */   public int getReleasedCount() {
/* 162 */     return this.releasedCount;
/*     */   }
/*     */ 
/*     */   public void setInputCount(int inputCount) {
/* 166 */     this.inputCount = inputCount;
/*     */   }
/*     */ 
/*     */   public void setReleasedCount(int releasedCount) {
/* 170 */     this.releasedCount = releasedCount;
/*     */   }
/*     */ 
/*     */   public void setReleaseRate()
/*     */   {
/* 178 */     if (getInput_user() != 0) {
/* 179 */       float i_rate = this.releasedCount * 100.0F / this.inputCount;
/* 180 */       this.releaseRate = nf.format(i_rate);
/*     */     } else {
/* 182 */       this.releaseRate = "0";
/*     */     }
/*     */   }
/*     */ 
/*     */   public String getReleaseRate() {
/* 187 */     return this.releaseRate;
/*     */   }
/*     */ 
/*     */   public int getPicInfoCount() {
/* 191 */     return this.picInfoCount;
/*     */   }
/*     */ 
/*     */   public void setPicInfoCount(int picInfoCount) {
/* 195 */     this.picInfoCount = picInfoCount;
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.bean.cms.count.CmsCountBean
 * JD-Core Version:    0.6.2
 */