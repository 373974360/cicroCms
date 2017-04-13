/*     */ package com.cicro.wcm.bean.comment;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ public class CommentBean
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -8621787369672322228L;
/*  28 */   private int id = 0;
/*  29 */   private int info_id = 0;
/*  30 */   private String info_uuid = "";
/*  31 */   private String app_id = "";
/*  32 */   private String site_id = "";
/*  33 */   private int info_type = 0;
/*  34 */   private int is_member = 0;
/*  35 */   private String nick_name = "";
/*  36 */   private String user_name = "";
/*  37 */   private int member_id = 0;
/*  38 */   private String tel = "";
/*  39 */   private String email = "";
/*  40 */   private String add_time = "";
/*  41 */   private String content = "";
/*  42 */   private String content2 = "";
/*  43 */   private int is_report = 0;
/*  44 */   private int report_count = 0;
/*  45 */   private String report_last_time = "";
/*  46 */   private int is_quest = 0;
/*  47 */   private String ip = "";
/*  48 */   private String country = "";
/*  49 */   private int is_status = 0;
/*  50 */   private int support_count = 0;
/*  51 */   private int parent_id = 0;
/*  52 */   private String parent_str = "";
/*  53 */   private int is_replay = 0;
/*  54 */   private int is_delete = 0;
/*  55 */   private List<CommentBean> parent_list = new ArrayList();
/*  56 */   private String title = "";
/*  57 */   private String content_url = "";
/*  58 */   private int is_top = 0;
/*  59 */   private String top_time = "";
/*  60 */   private CommentBean parent_comment = null;
/*  61 */   private int com_sort = 1;
/*  62 */   private int count = 0;
/*     */ 
/*  64 */   public int getCount() { return this.count; }
/*     */ 
/*     */   public void setCount(int count) {
/*  67 */     this.count = count;
/*     */   }
/*     */   public String getCountry() {
/*  70 */     return this.country;
/*     */   }
/*     */   public void setCountry(String country) {
/*  73 */     this.country = country;
/*     */   }
/*     */   public int getCom_sort() {
/*  76 */     return this.com_sort;
/*     */   }
/*     */   public void setCom_sort(int comSort) {
/*  79 */     this.com_sort = comSort;
/*     */   }
/*     */   public CommentBean getParent_comment() {
/*  82 */     return this.parent_comment;
/*     */   }
/*     */   public void setParent_comment(CommentBean parentComment) {
/*  85 */     this.parent_comment = parentComment;
/*     */   }
/*     */ 
/*     */   public String getIp() {
/*  89 */     return this.ip;
/*     */   }
/*     */   public void setIp(String ip) {
/*  92 */     this.ip = ip;
/*     */   }
/*     */   public int getIs_top() {
/*  95 */     return this.is_top;
/*     */   }
/*     */   public void setIs_top(int isTop) {
/*  98 */     this.is_top = isTop;
/*     */   }
/*     */   public String getTop_time() {
/* 101 */     return this.top_time;
/*     */   }
/*     */   public void setTop_time(String topTime) {
/* 104 */     this.top_time = topTime;
/*     */   }
/*     */   public String getContent2() {
/* 107 */     return this.content2;
/*     */   }
/*     */   public void setContent2(String content2) {
/* 110 */     this.content2 = content2;
/*     */   }
/*     */   public String getReport_last_time() {
/* 113 */     return this.report_last_time;
/*     */   }
/*     */   public void setReport_last_time(String reportLastTime) {
/* 116 */     this.report_last_time = reportLastTime;
/*     */   }
/*     */   public String getTitle() {
/* 119 */     return this.title;
/*     */   }
/*     */   public void setTitle(String title) {
/* 122 */     this.title = title;
/*     */   }
/*     */   public String getContent_url() {
/* 125 */     return this.content_url;
/*     */   }
/*     */   public void setContent_url(String contentUrl) {
/* 128 */     this.content_url = contentUrl;
/*     */   }
/*     */   public int getId() {
/* 131 */     return this.id;
/*     */   }
/*     */   public void setId(int id) {
/* 134 */     this.id = id;
/*     */   }
/*     */   public int getInfo_id() {
/* 137 */     return this.info_id;
/*     */   }
/*     */   public void setInfo_id(int infoId) {
/* 140 */     this.info_id = infoId;
/*     */   }
/*     */   public String getInfo_uuid() {
/* 143 */     return this.info_uuid;
/*     */   }
/*     */   public void setInfo_uuid(String infoUuid) {
/* 146 */     this.info_uuid = infoUuid;
/*     */   }
/*     */   public String getApp_id() {
/* 149 */     return this.app_id;
/*     */   }
/*     */   public void setApp_id(String appId) {
/* 152 */     this.app_id = appId;
/*     */   }
/*     */   public String getSite_id() {
/* 155 */     return this.site_id;
/*     */   }
/*     */   public void setSite_id(String siteId) {
/* 158 */     this.site_id = siteId;
/*     */   }
/*     */   public int getInfo_type() {
/* 161 */     return this.info_type;
/*     */   }
/*     */   public void setInfo_type(int infoType) {
/* 164 */     this.info_type = infoType;
/*     */   }
/*     */   public int getIs_member() {
/* 167 */     return this.is_member;
/*     */   }
/*     */   public void setIs_member(int isMember) {
/* 170 */     this.is_member = isMember;
/*     */   }
/*     */   public String getNick_name() {
/* 173 */     return this.nick_name;
/*     */   }
/*     */   public void setNick_name(String nickName) {
/* 176 */     this.nick_name = nickName;
/*     */   }
/*     */   public String getUser_name() {
/* 179 */     return this.user_name;
/*     */   }
/*     */   public void setUser_name(String userName) {
/* 182 */     this.user_name = userName;
/*     */   }
/*     */   public int getMember_id() {
/* 185 */     return this.member_id;
/*     */   }
/*     */   public void setMember_id(int memberId) {
/* 188 */     this.member_id = memberId;
/*     */   }
/*     */   public String getTel() {
/* 191 */     return this.tel;
/*     */   }
/*     */   public void setTel(String tel) {
/* 194 */     this.tel = tel;
/*     */   }
/*     */   public String getEmail() {
/* 197 */     return this.email;
/*     */   }
/*     */   public void setEmail(String email) {
/* 200 */     this.email = email;
/*     */   }
/*     */   public String getAdd_time() {
/* 203 */     return this.add_time;
/*     */   }
/*     */   public void setAdd_time(String addTime) {
/* 206 */     this.add_time = addTime;
/*     */   }
/*     */   public String getContent() {
/* 209 */     return this.content;
/*     */   }
/*     */   public void setContent(String content) {
/* 212 */     this.content = content;
/*     */   }
/*     */   public int getIs_report() {
/* 215 */     return this.is_report;
/*     */   }
/*     */   public void setIs_report(int isReport) {
/* 218 */     this.is_report = isReport;
/*     */   }
/*     */   public int getReport_count() {
/* 221 */     return this.report_count;
/*     */   }
/*     */   public void setReport_count(int reportCount) {
/* 224 */     this.report_count = reportCount;
/*     */   }
/*     */ 
/*     */   public int getIs_quest() {
/* 228 */     return this.is_quest;
/*     */   }
/*     */   public void setIs_quest(int isQuest) {
/* 231 */     this.is_quest = isQuest;
/*     */   }
/*     */ 
/*     */   public int getIs_status() {
/* 235 */     return this.is_status;
/*     */   }
/*     */   public void setIs_status(int isStatus) {
/* 238 */     this.is_status = isStatus;
/*     */   }
/*     */   public int getSupport_count() {
/* 241 */     return this.support_count;
/*     */   }
/*     */   public void setSupport_count(int supportCount) {
/* 244 */     this.support_count = supportCount;
/*     */   }
/*     */   public int getParent_id() {
/* 247 */     return this.parent_id;
/*     */   }
/*     */   public void setParent_id(int parentId) {
/* 250 */     this.parent_id = parentId;
/*     */   }
/*     */   public String getParent_str() {
/* 253 */     return this.parent_str;
/*     */   }
/*     */   public void setParent_str(String parentStr) {
/* 256 */     this.parent_str = parentStr;
/*     */   }
/*     */   public int getIs_replay() {
/* 259 */     return this.is_replay;
/*     */   }
/*     */   public void setIs_replay(int isReplay) {
/* 262 */     this.is_replay = isReplay;
/*     */   }
/*     */   public int getIs_delete() {
/* 265 */     return this.is_delete;
/*     */   }
/*     */   public void setIs_delete(int isDelete) {
/* 268 */     this.is_delete = isDelete;
/*     */   }
/*     */   public List<CommentBean> getParent_list() {
/* 271 */     return this.parent_list;
/*     */   }
/*     */   public void setParent_list(List<CommentBean> parentList) {
/* 274 */     this.parent_list = parentList;
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.bean.comment.CommentBean
 * JD-Core Version:    0.6.2
 */