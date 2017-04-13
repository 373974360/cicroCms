/*     */ package com.cicro.wcm.services.zwgk.ysqgk;
/*     */ 
/*     */ import com.cicro.util.DateUtil;
/*     */ import com.cicro.util.RandomStrg;
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.bean.zwgk.ysqgk.YsqgkBean;
/*     */ import com.cicro.wcm.bean.zwgk.ysqgk.YsqgkConfigBean;
/*     */ import com.cicro.wcm.bean.zwgk.ysqgk.YsqgkListBean;
/*     */ import com.cicro.wcm.dao.PublicTableDAO;
/*     */ import com.cicro.wcm.dao.zwgk.ysqgk.YsqgkInfoDAO;
/*     */ import com.cicro.wcm.services.zwgk.node.GKNodeManager;
/*     */ import java.io.PrintStream;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class YsqgkInfoManager
/*     */ {
/*  19 */   private static String defalut_randon_str = "0-9";
/*     */ 
/*     */   public static Map<String, String> insertYsqgkInfo(YsqgkBean ysqgk, SettingLogsBean stl)
/*     */   {
/*  28 */     YsqgkConfigBean ycf = new YsqgkConfigBean();
/*     */ 
/*  30 */     String ysqCode = ycf.getCode_pre() + DateUtil.getCurrentDateTime(ycf.getCode_rule()) + RandomStrg.getRandomStr(defalut_randon_str, new StringBuilder(String.valueOf(ycf.getCode_num())).toString());
/*  31 */     String query_code = "";
/*     */ 
/*  33 */     YsqgkConfigBean ycb = YsqgkConfigManager.getYsqgkConfigBean();
/*  34 */     query_code = RandomStrg.getRandomStr(defalut_randon_str, ycb.getQuery_num());
/*     */ 
/*  36 */     ysqgk.setYsq_code(ysqCode);
/*  37 */     ysqgk.setQuery_code(query_code);
/*  38 */     ysqgk.setYsq_id(PublicTableDAO.getIDByTableName(PublicTableDAO.YSQGK_INFO_TABLE_NAME));
/*     */ 
/*  40 */     if ((ysqgk.getPut_dtime() == null) || ("".equals(ysqgk.getPut_dtime()))) {
/*  41 */       ysqgk.setPut_dtime(DateUtil.getCurrentDateTime().substring(0, 10));
/*     */     }
/*  43 */     ysqgk.setNode_name(GKNodeManager.getNodeNameByNodeID(ysqgk.getNode_id()));
/*  44 */     if (YsqgkInfoDAO.insertYsqgkInfo(ysqgk, stl))
/*     */     {
/*  46 */       Map m = new HashMap();
/*  47 */       m.put("ysq_code", ysqCode);
/*  48 */       m.put("query_code", query_code);
/*  49 */       return m;
/*     */     }
/*  51 */     return null;
/*     */   }
/*     */ 
/*     */   public static YsqgkBean getYsqgkBean(String ysq_id)
/*     */   {
/*  61 */     return YsqgkInfoDAO.getYsqgkBean(ysq_id);
/*     */   }
/*     */ 
/*     */   public static YsqgkBean getYsqgkBeanForQuery(String ysq_code, String query_code)
/*     */   {
/*  72 */     Map m = new HashMap();
/*  73 */     m.put("ysq_code", ysq_code);
/*  74 */     m.put("query_code", query_code);
/*  75 */     return YsqgkInfoDAO.getYsqgkBeanForQuery(m);
/*     */   }
/*     */ 
/*     */   public static List<YsqgkListBean> getYsqgkLists(Map<String, String> m)
/*     */   {
/*  85 */     setTimeCon(m);
/*  86 */     return YsqgkInfoDAO.getYsqgkLists(m);
/*     */   }
/*     */ 
/*     */   public static void setTimeCon(Map<String, String> m)
/*     */   {
/* 101 */     if (m.containsKey("put_dtime"))
/*     */     {
/* 103 */       if ("day".equals(m.get("put_dtime")))
/*     */       {
/* 105 */         m.put("put_dtime", DateUtil.getCurrentDate() + " 00:00:00");
/* 106 */       } else if ("yestetoday".equals(m.get("put_dtime")))
/*     */       {
/* 108 */         m.put("put_dtime", DateUtil.getDateBefore(DateUtil.getCurrentDate(), 1) + " 00:00:00");
/* 109 */       } else if ("week".equals(m.get("put_dtime")))
/*     */       {
/* 111 */         m.put("put_dtime", DateUtil.getDateBefore(DateUtil.getCurrentDate(), 7) + " 00:00:00");
/* 112 */       } else if ("month".equals(m.get("put_dtime")))
/*     */       {
/* 114 */         m.put("put_dtime", DateUtil.getDateBefore(DateUtil.getCurrentDate(), 30) + " 00:00:00");
/*     */       }
/* 116 */       else m.remove("put_dtime");
/*     */     }
/*     */   }
/*     */ 
/*     */   public static int getYsqgkListsCount(Map<String, String> m)
/*     */   {
/* 127 */     setTimeCon(m);
/* 128 */     return YsqgkInfoDAO.getYsqgkListsCount(m);
/*     */   }
/*     */ 
/*     */   public static boolean updateYsqgkInfo(YsqgkBean ysqgk, SettingLogsBean stl)
/*     */   {
/* 138 */     if (YsqgkInfoDAO.updateYsqgkInfo(ysqgk, stl))
/*     */     {
/* 140 */       return true;
/*     */     }
/* 142 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateStatus(Map<String, String> map, SettingLogsBean stl)
/*     */   {
/* 152 */     if (map.size() > 0)
/*     */     {
/* 154 */       if ("0".equals(map.get("dealtype"))) {
/* 155 */         map.put("accept_dtime", DateUtil.getCurrentDateTime());
/* 156 */         map.put("reply_dtime", "");
/* 157 */       } else if ("1".equals(map.get("dealtype"))) {
/* 158 */         map.put("reply_dtime", DateUtil.getCurrentDateTime());
/*     */       }
/* 160 */       else if ("2".equals("dealtype")) {
/* 161 */         map.put("accept_dtime", DateUtil.getCurrentDateTime());
/*     */       }
/*     */     }
/* 164 */     if (YsqgkInfoDAO.updateStatus(map, stl))
/*     */     {
/* 166 */       return true;
/*     */     }
/* 168 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean setDeleteState(Map<String, String> m, SettingLogsBean stl)
/*     */   {
/* 179 */     if (YsqgkInfoDAO.setDeleteState(m, stl))
/*     */     {
/* 181 */       return true;
/*     */     }
/* 183 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean reBackInfos(Map<String, String> m, SettingLogsBean stl)
/*     */   {
/* 192 */     if (YsqgkInfoDAO.reBackInfos(m, stl))
/*     */     {
/* 194 */       return true;
/*     */     }
/* 196 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteYsqgkInfo(Map<String, String> m, SettingLogsBean stl)
/*     */   {
/* 205 */     if (YsqgkInfoDAO.deleteYsqgkInfo(m, stl))
/*     */     {
/* 207 */       return true;
/*     */     }
/* 209 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean clearDeleteYsqgkInfos(SettingLogsBean stl)
/*     */   {
/* 219 */     if (YsqgkInfoDAO.clearDeleteYsqgkInfos(stl))
/*     */     {
/* 221 */       return true;
/*     */     }
/* 223 */     return false;
/*     */   }
/*     */ 
/*     */   public static String getYsqStatistics(Map<String, String> m)
/*     */   {
/* 233 */     return YsqgkInfoDAO.getYsqStatistics(m);
/*     */   }
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/* 238 */     String d = "2011-09-01 00:11:38";
/* 239 */     System.out.println(getYsqgkBeanForQuery("YSQ201202108488", "577200"));
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.zwgk.ysqgk.YsqgkInfoManager
 * JD-Core Version:    0.6.2
 */