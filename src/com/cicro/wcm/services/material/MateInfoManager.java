/*     */ package com.cicro.wcm.services.material;
/*     */ 
/*     */ import com.cicro.util.DateUtil;
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.bean.material.MateInfoBean;
/*     */ import com.cicro.wcm.dao.PublicTableDAO;
/*     */ import com.cicro.wcm.dao.material.MateInfoDao;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class MateInfoManager
/*     */ {
/*     */   public static List<MateInfoBean> getMateInfoList(Map<String, String> con_m)
/*     */   {
/*  22 */     List mfa = new ArrayList();
/*  23 */     mfa = MateInfoDao.getMateInfoList(con_m);
/*  24 */     return mfa;
/*     */   }
/*     */ 
/*     */   public static MateInfoBean getMateInfoBean(String arr_id)
/*     */   {
/*  33 */     MateInfoBean mfa = new MateInfoBean();
/*  34 */     if (arr_id != "") {
/*  35 */       mfa = MateInfoDao.getMateInfoByArr_id(arr_id);
/*     */     }
/*  37 */     return mfa;
/*     */   }
/*     */ 
/*     */   public static String getArrIdFromTable()
/*     */   {
/*  46 */     return PublicTableDAO.getIDByTableName(PublicTableDAO.MateInfo_TABLE_NAME);
/*     */   }
/*     */ 
/*     */   public static String getMateInfoListCounts(Map<String, String> con_m)
/*     */   {
/*  56 */     String counts = "";
/*  57 */     if (con_m != null) {
/*  58 */       counts = MateInfoDao.getMateInfoListCounts(con_m);
/*  59 */       return counts;
/*     */     }
/*  61 */     return "0";
/*     */   }
/*     */ 
/*     */   public static boolean insertMateInfo(MateInfoBean mfb, SettingLogsBean stl)
/*     */   {
/*  72 */     int type = 0;
/*  73 */     String extName = "";
/*  74 */     String upload_dtime = DateUtil.getCurrentDateTime();
/*     */ 
/*  76 */     if (mfb.getAlias_name().lastIndexOf(".") >= 0) {
/*  77 */       extName = mfb.getAlias_name().substring(mfb.getAlias_name().lastIndexOf(".")).toLowerCase();
/*  78 */       if ((extName.equals(".gif")) || (extName.equals(".jpg")) || (extName.equals(".jpeg")) || 
/*  79 */         (extName.equals(".png")) || (extName.equals(".bmp")))
/*  80 */         type = 0;
/*  81 */       else if (extName.equals(".swf"))
/*  82 */         type = 1;
/*  83 */       else if ((extName.equals(".avi")) || (extName.equals(".flv")) || (extName.equals(".wmv")))
/*  84 */         type = 2;
/*  85 */       else if ((extName.equals(".txt")) || (extName.equals(".doc")) || (extName.equals(".docx")))
/*  86 */         type = 3;
/*     */       else {
/*  88 */         type = 4;
/*     */       }
/*     */     }
/*     */ 
/*  92 */     mfb.setApp_id("cms");
/*  93 */     mfb.setFileext(extName);
/*  94 */     mfb.setUpload_dtime(upload_dtime);
/*  95 */     mfb.setAtt_type(type);
/*     */ 
/*  97 */     if ((mfb.getHd_url() == null) || ("null".equals(mfb.getHd_url()))) {
/*  98 */       mfb.setHd_url("");
/*     */     }
/* 100 */     if ((mfb.getThumb_url() == null) || ("null".equals(mfb.getThumb_url()))) {
/* 101 */       mfb.setThumb_url("");
/*     */     }
/* 103 */     if ((mfb.getAtt_cname() == null) || ("null".equals(mfb.getAtt_cname()))) {
/* 104 */       mfb.setAtt_cname(mfb.getAlias_name());
/*     */     }
/* 106 */     if ((mfb.getAtt_description() == null) || ("null".equals(mfb.getAtt_description()))) {
/* 107 */       mfb.setAtt_description("");
/*     */     }
/*     */ 
/* 111 */     if (MateInfoDao.insertMateInfo(mfb, stl))
/*     */     {
/* 113 */       return true;
/*     */     }
/* 115 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateMateInfo(MateInfoBean mfb, SettingLogsBean stl)
/*     */   {
/* 126 */     if (MateInfoDao.updateMateInfo(mfb, stl))
/*     */     {
/* 128 */       return true;
/*     */     }
/* 130 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean moveMateInfo(String f_id, String att_ids)
/*     */   {
/* 142 */     return MateInfoDao.moveMateInfo(f_id, att_ids);
/*     */   }
/*     */ 
/*     */   public static boolean deleteMateInfo(String f_id, SettingLogsBean stl)
/*     */   {
/* 153 */     if (MateInfoDao.deleteMateInfo(f_id, stl))
/*     */     {
/* 155 */       return true;
/*     */     }
/* 157 */     return false;
/*     */   }
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.material.MateInfoManager
 * JD-Core Version:    0.6.2
 */