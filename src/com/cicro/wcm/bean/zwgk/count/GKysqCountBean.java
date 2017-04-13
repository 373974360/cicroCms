/*     */ package com.cicro.wcm.bean.zwgk.count;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public class GKysqCountBean
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 9027633750624474062L;
/*  14 */   private int do_state = 0;
/*  15 */   private int count = 0;
/*     */ 
/*  18 */   private String node_id = "";
/*  19 */   private String node_name = "";
/*  20 */   private int totalCnt = 0;
/*  21 */   private int unAcceptCnt = 0;
/*  22 */   private int acceptedCnt = 0;
/*  23 */   private int replyCnt = 0;
/*  24 */   private int invalidCnt = 0;
/*     */ 
/*  26 */   private int thirdCnt = 0;
/*  27 */   private int delayCnt = 0;
/*  28 */   private int timeoutCnt = 0;
/*  29 */   private String put_dtime = "";
/*     */ 
/*  31 */   private String parent_id = "";
/*     */ 
/*  34 */   private int reply_type = 0;
/*  35 */   private int type_total = 0;
/*     */ 
/*  37 */   private int qbgk_cnt = 0;
/*  38 */   private int bfgk_cnt = 0;
/*  39 */   private int bygk_cnt = 0;
/*  40 */   private int fbdwxx_cnt = 0;
/*  41 */   private int xxbcz_cnt = 0;
/*     */ 
/*     */   public String getNode_name() {
/*  44 */     return this.node_name;
/*     */   }
/*     */   public int getTotalCnt() {
/*  47 */     return this.totalCnt;
/*     */   }
/*     */   public int getUnAcceptCnt() {
/*  50 */     return this.unAcceptCnt;
/*     */   }
/*     */   public int getAcceptedCnt() {
/*  53 */     return this.acceptedCnt;
/*     */   }
/*     */   public int getReplyCnt() {
/*  56 */     return this.replyCnt;
/*     */   }
/*     */   public int getInvalidCnt() {
/*  59 */     return this.invalidCnt;
/*     */   }
/*     */   public int getThirdCnt() {
/*  62 */     return this.thirdCnt;
/*     */   }
/*     */   public int getDelayCnt() {
/*  65 */     return this.delayCnt;
/*     */   }
/*     */   public int getTimeoutCnt() {
/*  68 */     return this.timeoutCnt;
/*     */   }
/*     */   public String getParent_id() {
/*  71 */     return this.parent_id;
/*     */   }
/*     */   public String getId() {
/*  74 */     return this.node_id;
/*     */   }
/*     */   public void setNode_name(String nodeName) {
/*  77 */     this.node_name = nodeName;
/*     */   }
/*     */   public void setTotalCnt() {
/*  80 */     this.totalCnt = (this.unAcceptCnt + this.acceptedCnt + this.replyCnt + this.invalidCnt);
/*     */   }
/*     */   public void setUnAcceptCnt(int unAcceptCnt) {
/*  83 */     this.unAcceptCnt = unAcceptCnt;
/*  84 */     setTotalCnt();
/*     */   }
/*     */   public void setAcceptedCnt(int acceptedCnt) {
/*  87 */     this.acceptedCnt = acceptedCnt;
/*  88 */     setTotalCnt();
/*     */   }
/*     */   public void setReplyCnt(int replyCnt) {
/*  91 */     this.replyCnt = replyCnt;
/*  92 */     setTotalCnt();
/*     */   }
/*     */   public void setInvalidCnt(int invalidCnt) {
/*  95 */     this.invalidCnt = invalidCnt;
/*  96 */     setTotalCnt();
/*     */   }
/*     */   public void setThirdCnt(int thirdCnt) {
/*  99 */     this.thirdCnt = thirdCnt;
/*     */   }
/*     */   public void setDelayCnt(int delayCnt) {
/* 102 */     this.delayCnt = delayCnt;
/*     */   }
/*     */   public void setTimeoutCnt(int timeoutCnt) {
/* 105 */     this.timeoutCnt = timeoutCnt;
/*     */   }
/*     */   public void setParent_id(String parentId) {
/* 108 */     this.parent_id = parentId;
/*     */   }
/*     */   public void setId(String id) {
/* 111 */     this.node_id = id;
/*     */   }
/*     */   public int getCount() {
/* 114 */     return this.count;
/*     */   }
/*     */   public int getDo_state() {
/* 117 */     return this.do_state;
/*     */   }
/*     */   public String getNode_id() {
/* 120 */     return this.node_id;
/*     */   }
/*     */   public void setCount(int count) {
/* 123 */     this.count = count;
/*     */   }
/*     */   public void setDo_state(int doState) {
/* 126 */     this.do_state = doState;
/*     */   }
/*     */   public void setNode_id(String nodeId) {
/* 129 */     this.node_id = nodeId;
/*     */   }
/*     */   public String getPut_dtime() {
/* 132 */     return this.put_dtime;
/*     */   }
/*     */   public void setPut_dtime(String putDtime) {
/* 135 */     this.put_dtime = putDtime;
/*     */   }
/*     */   public int getQbgk_cnt() {
/* 138 */     return this.qbgk_cnt;
/*     */   }
/*     */   public int getBfgk_cnt() {
/* 141 */     return this.bfgk_cnt;
/*     */   }
/*     */   public int getBygk_cnt() {
/* 144 */     return this.bygk_cnt;
/*     */   }
/*     */   public int getFbdwxx_cnt() {
/* 147 */     return this.fbdwxx_cnt;
/*     */   }
/*     */   public int getXxbcz_cnt() {
/* 150 */     return this.xxbcz_cnt;
/*     */   }
/*     */   public void setTotalCnt(int totalCnt) {
/* 153 */     this.totalCnt = totalCnt;
/*     */   }
/*     */   public void setQbgk_cnt(int qbgkCnt) {
/* 156 */     this.qbgk_cnt = qbgkCnt;
/* 157 */     setType_total();
/*     */   }
/*     */   public void setBfgk_cnt(int bfgkCnt) {
/* 160 */     this.bfgk_cnt = bfgkCnt;
/* 161 */     setType_total();
/*     */   }
/*     */   public void setBygk_cnt(int bygkCnt) {
/* 164 */     this.bygk_cnt = bygkCnt;
/* 165 */     setType_total();
/*     */   }
/*     */   public void setFbdwxx_cnt(int fbdwxxCnt) {
/* 168 */     this.fbdwxx_cnt = fbdwxxCnt;
/* 169 */     setType_total();
/*     */   }
/*     */   public void setXxbcz_cnt(int xxbczCnt) {
/* 172 */     this.xxbcz_cnt = xxbczCnt;
/* 173 */     setType_total();
/*     */   }
/*     */   public int getReply_type() {
/* 176 */     return this.reply_type;
/*     */   }
/*     */   public int getType_total() {
/* 179 */     return this.type_total;
/*     */   }
/*     */   public void setReply_type(int replyType) {
/* 182 */     this.reply_type = replyType;
/*     */   }
/*     */   public void setType_total() {
/* 185 */     this.type_total = (this.qbgk_cnt + this.bfgk_cnt + this.bygk_cnt + this.fbdwxx_cnt + this.xxbcz_cnt);
/*     */   }
/*     */ 
/*     */   public void setType_total(int typeTotal)
/*     */   {
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.bean.zwgk.count.GKysqCountBean
 * JD-Core Version:    0.6.2
 */