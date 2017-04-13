/*     */ package com.cicro.wcm.services.zwgk.count;
/*     */ 
/*     */ import com.cicro.wcm.bean.zwgk.count.GKysqCountBean;
/*     */ import com.cicro.wcm.dao.zwgk.count.GKysqCountDAO;
/*     */ import java.io.PrintStream;
/*     */ import java.text.ParseException;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Calendar;
/*     */ import java.util.Collection;
/*     */ import java.util.Date;
/*     */ import java.util.GregorianCalendar;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.TreeMap;
/*     */ 
/*     */ public class GKysqCountManager
/*     */ {
/*     */   public static List<GKysqCountBean> getYSQStateCnt(Map<String, String> mp)
/*     */   {
/*  26 */     List retLt = new ArrayList();
/*     */ 
/*  28 */     Map retMap = pretreatmentCnt(false, mp);
/*  29 */     for (Iterator i = retMap.values().iterator(); i
/*  30 */       .hasNext(); )
/*     */     {
/*  31 */       GKysqCountBean bean = (GKysqCountBean)i.next();
/*  32 */       retLt.add(bean);
/*     */     }
/*  34 */     return retLt;
/*     */   }
/*     */ 
/*     */   public static List<GKysqCountBean> getYSQStateCntByNode(Map<String, String> mp)
/*     */   {
/*  46 */     Map retMap = pretreatmentCnt(true, mp);
/*  47 */     List retLt = sortStateCntByMonth((String)mp.get("start_day"), (String)mp.get("end_day"), retMap);
/*     */ 
/*  49 */     return retLt;
/*     */   }
/*     */ 
/*     */   private static List<GKysqCountBean> sortStateCntByMonth(String s_day, String e_day, Map<String, GKysqCountBean> retMap)
/*     */   {
/*  61 */     TreeMap p = new TreeMap();
/*     */ 
/*  63 */     for (Iterator i = retMap.values().iterator(); i
/*  64 */       .hasNext(); )
/*     */     {
/*  65 */       GKysqCountBean bean = (GKysqCountBean)i.next();
/*  66 */       p.put(bean.getPut_dtime(), bean);
/*     */     }
/*     */ 
/*  69 */     String end_day = e_day.substring(0, 7);
/*  70 */     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
/*  71 */     Calendar cal = new GregorianCalendar();
/*     */     try
/*     */     {
/*  74 */       Date start = sdf.parse(s_day);
/*  75 */       cal.setTime(start);
/*     */ 
/*  77 */       while (end_day.compareTo(sdf.format(cal.getTime())) >= 0) {
/*  78 */         if (!p.containsKey(sdf.format(cal.getTime()))) {
/*  79 */           GKysqCountBean gb = new GKysqCountBean();
/*  80 */           gb.setPut_dtime(sdf.format(cal.getTime()));
/*  81 */           p.put(sdf.format(cal.getTime()), gb);
/*     */         }
/*  83 */         cal.add(2, 1);
/*     */       }
/*     */     }
/*     */     catch (ParseException localParseException)
/*     */     {
/*     */     }
/*  89 */     List retLt = new ArrayList();
/*  90 */     while (p.size() > 0) {
/*  91 */       GKysqCountBean b = (GKysqCountBean)p.get(p.lastKey());
/*  92 */       p.remove(p.lastKey());
/*  93 */       retLt.add(b);
/*     */     }
/*  95 */     return retLt;
/*     */   }
/*     */ 
/*     */   private static Map<String, GKysqCountBean> pretreatmentCnt(boolean is_node, Map mp)
/*     */   {
/* 108 */     Map retMap = new HashMap();
/* 109 */     List cntlt = null;
/*     */ 
/* 111 */     if (is_node)
/* 112 */       cntlt = GKysqCountDAO.getStateCountByNode(mp);
/*     */     else {
/* 114 */       cntlt = GKysqCountDAO.getStateCount(mp);
/*     */     }
/* 116 */     for (GKysqCountBean bean : cntlt) {
/* 117 */       String key = bean.getNode_id();
/* 118 */       if (is_node) {
/* 119 */         key = key + bean.getPut_dtime();
/*     */       }
/* 121 */       if (!retMap.containsKey(key)) {
/* 122 */         retMap.put(key, bean);
/*     */       }
/* 124 */       GKysqCountBean mapBean = (GKysqCountBean)retMap.get(key);
/*     */ 
/* 126 */       switch (bean.getDo_state()) {
/*     */       case -1:
/* 128 */         mapBean.setInvalidCnt(bean.getCount());
/* 129 */         break;
/*     */       case 0:
/* 131 */         mapBean.setUnAcceptCnt(bean.getCount());
/* 132 */         break;
/*     */       case 1:
/* 134 */         mapBean.setAcceptedCnt(bean.getCount());
/* 135 */         break;
/*     */       case 2:
/* 137 */         mapBean.setReplyCnt(bean.getCount());
/* 138 */         break;
/*     */       case 1000:
/* 140 */         mapBean.setThirdCnt(bean.getCount());
/* 141 */         break;
/*     */       case 2000:
/* 143 */         mapBean.setDelayCnt(bean.getCount());
/* 144 */         break;
/*     */       case 3000:
/* 146 */         mapBean.setTimeoutCnt(bean.getCount());
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 152 */     return retMap;
/*     */   }
/*     */ 
/*     */   public static List<GKysqCountBean> getYSQTypeCount(Map<String, String> mp)
/*     */   {
/* 162 */     Map retMap = new HashMap();
/* 163 */     List cntlt = GKysqCountDAO.getTypeCount(mp);
/* 164 */     for (GKysqCountBean bean : cntlt) {
/* 165 */       String key = bean.getNode_id();
/* 166 */       if (!retMap.containsKey(key)) {
/* 167 */         retMap.put(key, bean);
/*     */       }
/* 169 */       GKysqCountBean mapBean = (GKysqCountBean)retMap.get(key);
/* 170 */       switch (bean.getReply_type()) {
/*     */       case 1:
/* 172 */         mapBean.setQbgk_cnt(bean.getCount());
/* 173 */         break;
/*     */       case 2:
/* 175 */         mapBean.setBfgk_cnt(bean.getCount());
/* 176 */         break;
/*     */       case 3:
/* 178 */         mapBean.setBygk_cnt(bean.getCount());
/* 179 */         break;
/*     */       case 4:
/* 181 */         mapBean.setFbdwxx_cnt(bean.getCount());
/* 182 */         break;
/*     */       case 5:
/* 184 */         mapBean.setXxbcz_cnt(bean.getCount());
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 190 */     List retLt = new ArrayList();
/* 191 */     for (Iterator i = retMap.values().iterator(); i
/* 192 */       .hasNext(); )
/*     */     {
/* 193 */       GKysqCountBean bean = (GKysqCountBean)i.next();
/* 194 */       retLt.add(bean);
/*     */     }
/* 196 */     return retLt;
/*     */   }
/*     */ 
/*     */   public static void main(String[] arg)
/*     */   {
/* 202 */     Map mp = new HashMap();
/* 203 */     mp.put("start_day", "2011-01");
/* 204 */     mp.put("end_day", "2011-09");
/* 205 */     mp.put("node_id", "GKmzj");
/* 206 */     List lt = getYSQStateCntByNode(mp);
/*     */ 
/* 208 */     System.out.println(lt.size());
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.zwgk.count.GKysqCountManager
 * JD-Core Version:    0.6.2
 */