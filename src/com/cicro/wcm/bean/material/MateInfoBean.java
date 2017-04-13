/*     */ package com.cicro.wcm.bean.material;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public class MateInfoBean
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 4732045569676799535L;
/*     */   private int att_id;
/*     */   private int f_id;
/*     */   private String att_ename;
/*     */   private String att_cname;
/*     */   private String att_path;
/*     */   private String alias_name;
/*     */   private String thumb_url;
/*     */   private String hd_url;
/*     */   private String fileext;
/*     */   private long filesize;
/*     */   private int att_type;
/*     */   private String att_description;
/*     */   private int user_id;
/*     */   private String upload_dtime;
/*     */   private String app_id;
/*     */   private String site_id;
/*     */ 
/*     */   public int getAtt_id()
/*     */   {
/*  30 */     return this.att_id;
/*     */   }
/*     */ 
/*     */   public int getF_id()
/*     */   {
/*  35 */     return this.f_id;
/*     */   }
/*     */ 
/*     */   public String getAtt_ename()
/*     */   {
/*  40 */     return this.att_ename;
/*     */   }
/*     */ 
/*     */   public String getAtt_cname()
/*     */   {
/*  45 */     return this.att_cname;
/*     */   }
/*     */ 
/*     */   public String getAtt_path()
/*     */   {
/*  50 */     return this.att_path;
/*     */   }
/*     */ 
/*     */   public String getAlias_name()
/*     */   {
/*  55 */     return this.alias_name;
/*     */   }
/*     */ 
/*     */   public String getThumb_url()
/*     */   {
/*  60 */     return this.thumb_url;
/*     */   }
/*     */ 
/*     */   public String getHd_url()
/*     */   {
/*  65 */     return this.hd_url;
/*     */   }
/*     */ 
/*     */   public String getFileext()
/*     */   {
/*  70 */     return this.fileext;
/*     */   }
/*     */ 
/*     */   public int getAtt_type() {
/*  74 */     return this.att_type;
/*     */   }
/*     */ 
/*     */   public String getAtt_description()
/*     */   {
/*  79 */     return this.att_description;
/*     */   }
/*     */ 
/*     */   public int getUser_id()
/*     */   {
/*  84 */     return this.user_id;
/*     */   }
/*     */ 
/*     */   public String getUpload_dtime()
/*     */   {
/*  89 */     return this.upload_dtime;
/*     */   }
/*     */ 
/*     */   public String getApp_id()
/*     */   {
/*  94 */     return this.app_id;
/*     */   }
/*     */ 
/*     */   public String getSite_id()
/*     */   {
/*  99 */     return this.site_id;
/*     */   }
/*     */ 
/*     */   public void setAtt_id(int attId)
/*     */   {
/* 104 */     this.att_id = attId;
/*     */   }
/*     */ 
/*     */   public void setF_id(int fId)
/*     */   {
/* 109 */     this.f_id = fId;
/*     */   }
/*     */ 
/*     */   public void setAtt_ename(String attEname)
/*     */   {
/* 114 */     this.att_ename = attEname;
/*     */   }
/*     */ 
/*     */   public void setAtt_cname(String attCname)
/*     */   {
/* 119 */     this.att_cname = attCname;
/*     */   }
/*     */ 
/*     */   public void setAtt_path(String attPath)
/*     */   {
/* 124 */     this.att_path = attPath;
/*     */   }
/*     */ 
/*     */   public void setAlias_name(String aliasName)
/*     */   {
/* 129 */     this.alias_name = aliasName;
/*     */   }
/*     */ 
/*     */   public void setThumb_url(String thumbUrl)
/*     */   {
/* 134 */     this.thumb_url = thumbUrl;
/*     */   }
/*     */ 
/*     */   public void setHd_url(String hdUrl)
/*     */   {
/* 139 */     this.hd_url = hdUrl;
/*     */   }
/*     */ 
/*     */   public void setFileext(String fileext)
/*     */   {
/* 144 */     this.fileext = fileext;
/*     */   }
/*     */ 
/*     */   public Long getFilesize()
/*     */   {
/* 150 */     return Long.valueOf(this.filesize);
/*     */   }
/*     */ 
/*     */   public void setFilesize(Long filesize)
/*     */   {
/* 155 */     this.filesize = filesize.longValue();
/*     */   }
/*     */ 
/*     */   public void setAtt_type(int attType)
/*     */   {
/* 160 */     this.att_type = attType;
/*     */   }
/*     */ 
/*     */   public void setAtt_description(String attDescription)
/*     */   {
/* 165 */     this.att_description = attDescription;
/*     */   }
/*     */ 
/*     */   public void setUser_id(int userId)
/*     */   {
/* 170 */     this.user_id = userId;
/*     */   }
/*     */ 
/*     */   public void setUpload_dtime(String uploadDtime)
/*     */   {
/* 175 */     this.upload_dtime = uploadDtime;
/*     */   }
/*     */ 
/*     */   public void setApp_id(String appId)
/*     */   {
/* 180 */     this.app_id = appId;
/*     */   }
/*     */ 
/*     */   public void setSite_id(String siteId)
/*     */   {
/* 185 */     this.site_id = siteId;
/*     */   }
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.bean.material.MateInfoBean
 * JD-Core Version:    0.6.2
 */