/*     */ package com.cicro.wcm.template.velocity.data;
/*     */ 
/*     */ import com.cicro.util.FormatUtil;
/*     */ import com.cicro.wcm.bean.cms.category.CateCurPositionBean;
/*     */ import com.cicro.wcm.bean.cms.info.InfoBean;
/*     */ import com.cicro.wcm.bean.template.TurnPageBean;
/*     */ import com.cicro.wcm.bean.zwgk.ser.SerCategoryBean;
/*     */ import com.cicro.wcm.bean.zwgk.ser.SerResouceBean;
/*     */ import com.cicro.wcm.services.zwgk.ser.SerCategoryManager;
/*     */ import com.cicro.wcm.services.zwgk.ser.SerResouceManager;
/*     */ import java.io.PrintStream;
/*     */ import java.util.List;
/*     */ 
/*     */ public class SerData
/*     */ {
/*     */   public static List<SerCategoryBean> getSerTopicList()
/*     */   {
/*  25 */     return SerCategoryManager.getSerCategoryRootListForPublish();
/*     */   }
/*     */ 
/*     */   public static SerCategoryBean getSerROOTObject(String ser_id)
/*     */   {
/*  30 */     return SerCategoryManager.getRootSerCategoryBean(Integer.parseInt(ser_id));
/*     */   }
/*     */ 
/*     */   public static SerCategoryBean getSerObject(String ser_id)
/*     */   {
/*  35 */     return SerCategoryManager.getSerCategoryBean(Integer.parseInt(ser_id));
/*     */   }
/*     */ 
/*     */   public static List<SerCategoryBean> getChildSerList(String ser_id)
/*     */   {
/*  40 */     return SerCategoryManager.getChildSerCategoryList(Integer.parseInt(ser_id));
/*     */   }
/*     */ 
/*     */   public static List<SerResouceBean> getSerResouceList(String ser_id)
/*     */   {
/*  45 */     return SerResouceManager.getSerResouceListByPublish(ser_id);
/*     */   }
/*     */ 
/*     */   public static List<InfoBean> getSerInfoList(String params)
/*     */   {
/*  56 */     return InfoUtilData.getFWInfoList(getSerInfoSqlStr(params));
/*     */   }
/*     */ 
/*     */   public static TurnPageBean getSerInfoCount(String params)
/*     */   {
/*  66 */     return InfoUtilData.getFWInfoCount(getSerInfoSqlStr(params));
/*     */   }
/*     */ 
/*     */   public static String getSerInfoSqlStr(String params)
/*     */   {
/*  71 */     int cat_id = 0;
/*  72 */     String ser_id = "";
/*  73 */     String info_type = "";
/*  74 */     String[] tempA = params.split(";");
/*  75 */     for (int i = 0; i < tempA.length; i++)
/*     */     {
/*  77 */       if (tempA[i].toLowerCase().startsWith("ser_id="))
/*     */       {
/*  79 */         String s_id = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));
/*     */ 
/*  81 */         if ((!"".equals(s_id)) && (!s_id.startsWith("$ser_id")) && (FormatUtil.isValiditySQL(s_id)))
/*     */         {
/*  83 */           ser_id = s_id;
/*     */         }
/*     */       }
/*  86 */       if (tempA[i].toLowerCase().startsWith("info_type="))
/*     */       {
/*  88 */         String i_t = FormatUtil.formatNullString(tempA[i].substring(tempA[i].indexOf("=") + 1));
/*     */ 
/*  90 */         if ((!"".equals(i_t)) && (!i_t.startsWith("$info_type")) && (FormatUtil.isValiditySQL(i_t)))
/*     */         {
/*  92 */           info_type = i_t;
/*     */         }
/*     */       }
/*     */     }
/*     */     try {
/*  97 */       SerCategoryBean scb = SerCategoryManager.getRootSerCategoryBean(Integer.parseInt(ser_id));
/*  98 */       if ("cjwt".equals(info_type))
/*  99 */         cat_id = scb.getCjwt_cat_id();
/*     */       else {
/* 101 */         cat_id = scb.getXgwt_cat_id();
/*     */       }
/* 103 */       return "cat_id=" + cat_id + ";" + params;
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 107 */       System.out.println("getSerInfoSqlStr  ser_id is null");
/* 108 */       e.printStackTrace();
/* 109 */     }return "";
/*     */   }
/*     */ 
/*     */   public static List<CateCurPositionBean> getCategoryPosition(String ser_id)
/*     */   {
/* 121 */     return SerCategoryManager.getSerCategoryTreeposition(Integer.parseInt(ser_id));
/*     */   }
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/* 126 */     System.out.println(getSerInfoList("ser_id=25;info_type=xgxx;cur_page=1;size=15;orderby=ci.released_dtime desc"));
/* 127 */     System.out.println(getSerInfoCount("ser_id=25;info_type=xgxx;cur_page=1;size=15;orderby=ci.released_dtime desc").getCount());
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.template.velocity.data.SerData
 * JD-Core Version:    0.6.2
 */