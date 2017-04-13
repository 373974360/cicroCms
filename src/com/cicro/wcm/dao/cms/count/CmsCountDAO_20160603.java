/*     */ package com.cicro.wcm.dao.cms.count;
/*     */ 
/*     */ import com.cicro.wcm.bean.cms.count.CmsCountBean;
/*     */ import com.cicro.wcm.bean.cms.info.InfoBean;
/*     */ import com.cicro.wcm.db.DBManager;
/*     */ import java.io.PrintStream;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.TreeMap;
/*     */ 
/*     */ public class CmsCountDAO_20160603
/*     */ {
/*     */   private static final String HOST = "0";
/*     */   private static final String REF = "1";
/*     */   private static final String LINK = "2";
/*     */ 
/*     */   public static CmsCountBean getCountListByCat(Map<String, String> mp)
/*     */   {
/*  29 */     List result = DBManager.queryFList("getInfoCountListByCat", mp);
/*  30 */     CmsCountBean cntBean = new CmsCountBean();
/*     */ 
/*  32 */     for (Map bean : result)
/*     */     {
/*  34 */       String is_host = bean.get("IS_HOST").toString();
/*  35 */       String cnt = bean.get("COUNT").toString();
/*     */ 
/*  37 */       setCmsCountBean(cntBean, is_host, cnt);
/*     */     }
/*  39 */     return cntBean;
/*     */   }
/*     */ 
/*     */   public static Map<String, CmsCountBean> getCountListByDate(Map<String, String> mp)
/*     */   {
/*  51 */     List result = DBManager.queryFList("getInfoCountListByDate", mp);
/*  52 */     Map rs_mp = new TreeMap();
/*  53 */     for (Map bean : result)
/*     */     {
/*  55 */       String key = bean.get("RELEASED_DTIME").toString();
/*  56 */       String is_host = bean.get("IS_HOST").toString();
/*  57 */       String cnt = bean.get("COUNT").toString();
/*     */ 
/*  61 */       if (!rs_mp.containsKey(key)) {
/*  62 */         CmsCountBean cntBean = new CmsCountBean();
/*  63 */         cntBean.setTime(key);
/*  64 */         rs_mp.put(key, cntBean);
/*     */       }
/*     */ 
/*  67 */       CmsCountBean cntBean = (CmsCountBean)rs_mp.get(key);
/*  68 */       setCmsCountBean(cntBean, is_host, cnt);
/*     */     }
/*  70 */     return rs_mp;
/*     */   }
/*     */ 
/*     */   private static void setCmsCountBean(CmsCountBean cntBean, String is_host, String cnt)
/*     */   {
/*  80 */     if ("0".equals(is_host))
/*  81 */       cntBean.setHostInfoCount(Integer.parseInt(cnt));
/*  82 */     else if ("1".equals(is_host))
/*  83 */       cntBean.setRefInfoCount(Integer.parseInt(cnt));
/*     */     else
/*  85 */       cntBean.setLinkInfoCount(Integer.parseInt(cnt));
/*     */   }
/*     */ 
/*     */   public static Map<String, CmsCountBean> getInputCountList(Map<String, String> mp)
/*     */   {
/*  98 */     List result = DBManager.queryFList("getInputCountList", mp);
/*     */ 
/* 100 */     Map ret = new HashMap();
/* 101 */     for (Map m : result) {
/* 102 */       String count = m.get("COUNT").toString();
/* 103 */       String input_user = m.get("INPUT_USER").toString();
/* 104 */       String user_name = m.get("USER_REALNAME").toString();
/* 105 */       CmsCountBean cmb = new CmsCountBean(input_user, user_name, count);
/* 106 */       ret.put(input_user, cmb);
/*     */     }
/* 108 */     return ret;
/*     */   }
/*     */ 
/*     */   public static Map<String, CmsCountBean> getInputCountListByUserID(Map<String, String> mp)
/*     */   {
/* 121 */     List result = DBManager.queryFList("getInputCountListByUserID", mp);
/*     */ 
/* 123 */     String input_users = (String)mp.get("input_user");
/* 124 */     if (input_users == null) {
/* 125 */       input_users = "0";
/*     */     }
/* 127 */     int input_user = Integer.parseInt(input_users);
/*     */ 
/* 129 */     Map ret = new HashMap();
/* 130 */     for (Map m : result) {
/* 131 */       String count = m.get("COUNT").toString();
/* 132 */       String cat_id = m.get("CAT_ID").toString();
/* 133 */       String cat_name = m.get("CAT_CNAME").toString();
/*     */ 
/* 135 */       CmsCountBean cmb = new CmsCountBean();
/* 136 */       cmb.setCat_id(Integer.parseInt(cat_id));
/* 137 */       cmb.setCat_name(cat_name);
/* 138 */       cmb.setCount(Integer.parseInt(count));
/* 139 */       cmb.setInput_user(input_user);
/* 140 */       ret.put(cat_id, cmb);
/*     */     }
/* 142 */     return ret;
/*     */   }
/*     */ 
/*     */   public static Map<String, CmsCountBean> getInputCountListByCate(Map<String, String> mp)
/*     */   {
/* 155 */     mp.remove("cat_ids");
/* 156 */     System.out.println("--dao--getInputCountListByCate-- mp = " + mp);
/* 157 */     List result = DBManager.queryFList("getInputCountListByUserID", mp);
/*     */ 
/* 159 */     String input_users = (String)mp.get("input_user");
/* 160 */     if (input_users == null) {
/* 161 */       input_users = "0";
/*     */     }
/* 163 */     int input_user = Integer.parseInt(input_users);
/*     */ 
/* 165 */     Map ret = new HashMap();
/* 166 */     for (Map m : result) {
/* 167 */       String count = m.get("COUNT").toString();
/* 168 */       String cat_id = m.get("CAT_ID").toString();
/* 169 */       String cat_name = m.get("CAT_CNAME").toString();
/*     */ 
/* 171 */       CmsCountBean cmb = new CmsCountBean();
/* 172 */       cmb.setCat_id(Integer.parseInt(cat_id));
/* 173 */       cmb.setCat_name(cat_name);
/* 174 */       cmb.setCount(Integer.parseInt(count));
/* 175 */       cmb.setInput_user(input_user);
/* 176 */       ret.put(cat_id, cmb);
/*     */     }
/* 178 */     return ret;
/*     */   }
/*     */ 
/*     */   public static List<InfoBean> getInfoListByUserIDTimeType(Map<String, String> map)
/*     */   {
/* 184 */     return DBManager.queryFList("info_count.getInfoListByUserIDTimeType", map);
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.dao.cms.count.CmsCountDAO
 * JD-Core Version:    0.6.2
 */