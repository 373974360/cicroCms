/*     */ package com.cicro.wcm.services.interview;
/*     */ 
/*     */ import com.cicro.util.DateUtil;
/*     */ import com.cicro.wcm.bean.interview.SubReleInfo;
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.dao.interview.SubReleInfoDAO;
/*     */ import java.io.PrintStream;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.UUID;
/*     */ 
/*     */ public class SubReleInfoServices
/*     */ {
/*     */   public static String getReleInfoCountBySub_id(String sub_id)
/*     */   {
/*  29 */     return SubReleInfoDAO.getReleInfoCountBySub_id(sub_id);
/*     */   }
/*     */ 
/*     */   public static String getReleInfoListCountBySub_id(String con, String sub_id)
/*     */   {
/*  39 */     Map m = new HashMap();
/*  40 */     m.put("con", con);
/*  41 */     m.put("sub_id", sub_id);
/*  42 */     return SubReleInfoDAO.getReleInfoListCountBySub_id(m);
/*     */   }
/*     */ 
/*     */   public static List getReleInfoListBySub_id(String con, int start_num, int page_size, String sub_id)
/*     */   {
/*  53 */     Map m = new HashMap();
/*  54 */     m.put("start_num", Integer.valueOf(start_num));
/*  55 */     m.put("page_size", Integer.valueOf(page_size));
/*  56 */     m.put("con", con);
/*  57 */     m.put("sub_id", sub_id);
/*  58 */     return SubReleInfoDAO.getReleInfoListBySub_id(m);
/*     */   }
/*     */ 
/*     */   public static SubReleInfo getSubReleInfo(int id)
/*     */   {
/*  68 */     return SubReleInfoDAO.getSubReleInfo(id);
/*     */   }
/*     */ 
/*     */   public static SubReleInfo getSubReleInfo(String id)
/*     */   {
/*  78 */     return SubReleInfoDAO.getSubReleInfo(id);
/*     */   }
/*     */ 
/*     */   public static boolean insertReleInfo(SubReleInfo sri, SettingLogsBean stl)
/*     */   {
/*  88 */     String current_time = DateUtil.getCurrentDateTime();
/*  89 */     String info_id = UUID.randomUUID().toString();
/*  90 */     sri.setInfo_id(info_id);
/*  91 */     sri.setAdd_time(current_time);
/*     */ 
/*  93 */     return SubReleInfoDAO.insertReleInfo(sri, stl);
/*     */   }
/*     */ 
/*     */   public static boolean updateReleInfo(SubReleInfo sri, SettingLogsBean stl)
/*     */   {
/* 103 */     sri.setUpdate_time(DateUtil.getCurrentDateTime());
/* 104 */     sri.setUpdate_user(sri.getAdd_user());
/* 105 */     return SubReleInfoDAO.updateReleInfo(sri, stl);
/*     */   }
/*     */ 
/*     */   public static boolean deleteReleInfo(String ids, String user_name, SettingLogsBean stl)
/*     */   {
/* 116 */     Map m = new HashMap();
/* 117 */     m.put("ids", ids);
/* 118 */     m.put("user_name", user_name);
/* 119 */     m.put("current_time", DateUtil.getCurrentDateTime());
/* 120 */     return SubReleInfoDAO.deleteReleInfo(m, stl);
/*     */   }
/*     */ 
/*     */   public static boolean publishReleInfo(String ids, String user_name, int publish_flag, SettingLogsBean stl)
/*     */   {
/* 130 */     Map m = new HashMap();
/* 131 */     m.put("ids", ids);
/* 132 */     m.put("publish_flag", Integer.valueOf(publish_flag));
/* 133 */     m.put("oper_name", user_name);
/* 134 */     m.put("current_time", DateUtil.getCurrentDateTime());
/* 135 */     return SubReleInfoDAO.publishReleInfo(m, stl);
/*     */   }
/*     */ 
/*     */   public static boolean saveReleInfoSort(String ids, SettingLogsBean stl)
/*     */   {
/* 145 */     Map m = new HashMap();
/* 146 */     m.put("ids", ids);
/* 147 */     return SubReleInfoDAO.saveReleInfoSort(m, stl);
/*     */   }
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/* 152 */     System.out.println(getSubReleInfo("24feb736-9168-47e3-a941-bc58a721e839"));
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.interview.SubReleInfoServices
 * JD-Core Version:    0.6.2
 */