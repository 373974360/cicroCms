/*     */ package com.cicro.wcm.bean.cms.count;
/*     */ 
/*     */ import java.io.PrintStream;
/*     */ import java.io.Serializable;
/*     */ import java.math.RoundingMode;
/*     */ import java.text.DecimalFormat;
/*     */ import java.text.NumberFormat;
/*     */ import java.util.List;
/*     */ 
/*     */ public class SiteCountBean
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -1256934576049508129L;
/*     */   private static final int DIGIT = 2;
/*  27 */   private static NumberFormat nf = null;
/*     */ 
/*  34 */   private String site_name = "";
/*  35 */   private String site_id = "";
/*  36 */   private String cat_name = "";
/*  37 */   private int cat_id = 0;
/*  38 */   private String user_name = "";
/*     */   private int input_user;
/*     */   private int dept_id;
/*     */   private String dept_name;
/*     */   private String tree_position;
/*  43 */   private int icount = 0;
/*     */ 
/*  45 */   private int allCount = 0;
/*  46 */   private int hostInfoCount = 0;
/*  47 */   private int refInfoCount = 0;
/*  48 */   private int linkInfoCount = 0;
/*     */ 
/*  50 */   private int inputCount = 0;
/*  51 */   private int releasedCount = 0;
/*  52 */   private int picReleasedCount = 0;
/*     */   private String releaseRate;
/*  55 */   private boolean is_leaf = true;
/*     */   private List<SiteCountBean> child_list;
/*     */   private String time;
/*     */ 
/*     */   static
/*     */   {
/*  29 */     nf = new DecimalFormat();
/*  30 */     nf.setMaximumFractionDigits(2);
/*  31 */     nf.setRoundingMode(RoundingMode.HALF_UP);
/*     */   }
/*     */ 
/*     */   public String getUser_name()
/*     */   {
/*  61 */     return this.user_name;
/*     */   }
/*     */ 
/*     */   public int getInput_user() {
/*  65 */     return this.input_user;
/*     */   }
/*     */ 
/*     */   public int getAllCount()
/*     */   {
/*  70 */     return this.allCount;
/*     */   }
/*     */ 
/*     */   public int getHostInfoCount() {
/*  74 */     return this.hostInfoCount;
/*     */   }
/*     */ 
/*     */   public int getRefInfoCount() {
/*  78 */     return this.refInfoCount;
/*     */   }
/*     */ 
/*     */   public int getLinkInfoCount() {
/*  82 */     return this.linkInfoCount;
/*     */   }
/*     */ 
/*     */   public String getTime() {
/*  86 */     return this.time;
/*     */   }
/*     */ 
/*     */   public void setUser_name(String userName) {
/*  90 */     this.user_name = userName;
/*     */   }
/*     */ 
/*     */   public void setInput_user(int inputUser) {
/*  94 */     this.input_user = inputUser;
/*     */   }
/*     */ 
/*     */   public void setAllCount(int allCount)
/*     */   {
/*  99 */     this.allCount = allCount;
/*     */   }
/*     */ 
/*     */   private void setAllCount()
/*     */   {
/* 106 */     this.allCount = (getHostInfoCount() + getRefInfoCount() + getLinkInfoCount());
/*     */   }
/*     */ 
/*     */   public void setHostInfoCount(int hostInfoCount) {
/* 110 */     this.hostInfoCount = hostInfoCount;
/* 111 */     setAllCount();
/*     */   }
/*     */ 
/*     */   public void setRefInfoCount(int refInfoCount) {
/* 115 */     this.refInfoCount = refInfoCount;
/* 116 */     setAllCount();
/*     */   }
/*     */ 
/*     */   public void setLinkInfoCount(int linkInfoCount) {
/* 120 */     this.linkInfoCount = linkInfoCount;
/* 121 */     setAllCount();
/*     */   }
/*     */ 
/*     */   public void setTime(String time) {
/* 125 */     this.time = time;
/*     */   }
/*     */ 
/*     */   public int getInputCount() {
/* 129 */     return this.inputCount;
/*     */   }
/*     */ 
/*     */   public int getReleasedCount() {
/* 133 */     return this.releasedCount;
/*     */   }
/*     */ 
/*     */   public void setInputCount(int inputCount) {
/* 137 */     this.inputCount = inputCount;
/*     */   }
/*     */ 
/*     */   public void setReleasedCount(int releasedCount) {
/* 141 */     this.releasedCount = releasedCount;
/*     */   }
/*     */ 
/*     */   public void setReleaseRate()
/*     */   {
/* 149 */     if (this.inputCount != 0) {
/* 150 */       float i_rate = this.releasedCount * 100.0F / this.inputCount;
/* 151 */       this.releaseRate = nf.format(i_rate);
/*     */     } else {
/* 153 */       this.releaseRate = "0";
/*     */     }
/* 155 */     this.releaseRate += "%";
/*     */   }
/*     */ 
/*     */   public String getReleaseRate() {
/* 159 */     return this.releaseRate;
/*     */   }
/*     */ 
/*     */   public int getIcount() {
/* 163 */     return this.icount;
/*     */   }
/*     */ 
/*     */   public void setIcount(int icount) {
/* 167 */     this.icount = icount;
/*     */   }
/*     */ 
/*     */   public int getDept_id() {
/* 171 */     return this.dept_id;
/*     */   }
/*     */ 
/*     */   public void setDept_id(int deptId) {
/* 175 */     this.dept_id = deptId;
/*     */   }
/*     */ 
/*     */   public int getPicReleasedCount() {
/* 179 */     return this.picReleasedCount;
/*     */   }
/*     */ 
/*     */   public void setPicReleasedCount(int picReleasedCount) {
/* 183 */     this.picReleasedCount = picReleasedCount;
/*     */   }
/*     */ 
/*     */   public String getDept_name() {
/* 187 */     return this.dept_name;
/*     */   }
/*     */ 
/*     */   public void setDept_name(String deptName) {
/* 191 */     this.dept_name = deptName;
/*     */   }
/*     */ 
/*     */   public boolean isIs_leaf() {
/* 195 */     return this.is_leaf;
/*     */   }
/*     */ 
/*     */   public void setIs_leaf(boolean isLeaf) {
/* 199 */     this.is_leaf = isLeaf;
/*     */   }
/*     */ 
/*     */   public List<SiteCountBean> getChild_list() {
/* 203 */     return this.child_list;
/*     */   }
/*     */ 
/*     */   public void setChild_list(List<SiteCountBean> childList) {
/* 207 */     this.child_list = childList;
/*     */   }
/*     */ 
/*     */   public static void main(String[] arr)
/*     */   {
/* 212 */     int releasedCount = 119;
/* 213 */     int inputCount = 127;
/*     */ 
/* 215 */     float i_rate = releasedCount * 100.0F / inputCount;
/* 216 */     String releaseRate = nf.format(i_rate);
/* 217 */     System.out.println(releaseRate);
/*     */   }
/*     */ 
/*     */   public String getSite_name() {
/* 221 */     return this.site_name;
/*     */   }
/*     */ 
/*     */   public void setSite_name(String siteName) {
/* 225 */     this.site_name = siteName;
/*     */   }
/*     */ 
/*     */   public String getSite_id() {
/* 229 */     return this.site_id;
/*     */   }
/*     */ 
/*     */   public void setSite_id(String siteId) {
/* 233 */     this.site_id = siteId;
/*     */   }
/*     */ 
/*     */   public String getCat_name() {
/* 237 */     return this.cat_name;
/*     */   }
/*     */ 
/*     */   public void setCat_name(String catName) {
/* 241 */     this.cat_name = catName;
/*     */   }
/*     */ 
/*     */   public int getCat_id() {
/* 245 */     return this.cat_id;
/*     */   }
/*     */ 
/*     */   public void setCat_id(int catId) {
/* 249 */     this.cat_id = catId;
/*     */   }
/*     */ 
/*     */   public String getTree_position() {
/* 253 */     return this.tree_position;
/*     */   }
/*     */ 
/*     */   public void setTree_position(String treePosition) {
/* 257 */     this.tree_position = treePosition;
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.bean.cms.count.SiteCountBean
 * JD-Core Version:    0.6.2
 */