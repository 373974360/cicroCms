/*     */ package com.cicro.wcm.services.system.ware;
/*     */ 
/*     */ import com.cicro.util.DateUtil;
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.bean.system.ware.WareBean;
/*     */ import com.cicro.wcm.bean.system.ware.WareVerBean;
/*     */ import com.cicro.wcm.dao.PublicTableDAO;
/*     */ import com.cicro.wcm.dao.system.ware.WareDAO;
/*     */ import java.io.IOException;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class WareManagerExtend
/*     */ {
/*     */   public static boolean insertWare(WareBean wb, SettingLogsBean stl)
/*     */     throws IOException
/*     */   {
/*  30 */     int id = PublicTableDAO.getIDByTableName(PublicTableDAO.WARE_TABLE_NAME);
/*  31 */     wb.setId(id);
/*  32 */     wb.setWare_id(id);
/*     */ 
/*  34 */     if (wb == null)
/*     */     {
/*  36 */       return false;
/*     */     }
/*     */ 
/*  39 */     wb.setWare_ver(1 + getWareVerNum(wb.getWare_id(), wb.getSite_id()));
/*     */ 
/*  42 */     if (WareDAO.insertWare(wb, stl))
/*     */     {
/*  45 */       addWareVer(WAREtoVER(wb), stl);
/*     */ 
/*  48 */       if (wb.getWare_type() == 2)
/*     */       {
/*  50 */         WareInfoManager.insertWareInfoByRowNum(id, wb.getInfo_num(), wb.getApp_id(), wb.getSite_id());
/*     */       }
/*  52 */       WareManager.reloadMap();
/*  53 */       if (((wb.getWare_type() == 0) || (wb.getWare_type() == 1)) && (!"".equals(wb.getWare_content().trim())))
/*  54 */         WareManager.createWarePage(wb);
/*  55 */       return true;
/*     */     }
/*     */ 
/*  58 */     return false;
/*     */   }
/*     */ 
/*     */   public static List<WareVerBean> getWareVerList(Map<String, String> m)
/*     */   {
/*  69 */     return WareDAO.getWareVerList(m);
/*     */   }
/*     */ 
/*     */   public static int getWareVerNum(String ware_id, String site_id)
/*     */   {
/*  80 */     String num = WareDAO.getWareVerNum(ware_id, site_id);
/*  81 */     if ((num == null) || (num.equals("")))
/*     */     {
/*  83 */       num = "0";
/*     */     }
/*  85 */     return Integer.parseInt(num);
/*     */   }
/*     */ 
/*     */   public static boolean addWareVer(WareVerBean wvb, SettingLogsBean stl)
/*     */   {
/*  96 */     if (wvb == null)
/*     */     {
/*  98 */       return false;
/*     */     }
/* 100 */     if (WareDAO.addWareVer(wvb, stl))
/*     */     {
/* 102 */       return true;
/*     */     }
/* 104 */     return false;
/*     */   }
/*     */ 
/*     */   public static String getWareVerListCount(String ware_id)
/*     */   {
/* 114 */     return WareDAO.getWareVerListCount(ware_id);
/*     */   }
/*     */ 
/*     */   public static boolean updateWare(WareBean wb, String recoveryType, SettingLogsBean stl)
/*     */     throws IOException
/*     */   {
/* 127 */     if (wb == null)
/*     */     {
/* 129 */       return false;
/*     */     }
/*     */ 
/* 132 */     if (recoveryType.equals("update"))
/*     */     {
/* 134 */       wb.setWare_ver(1 + getWareVerNum(wb.getWare_id(), wb.getSite_id()));
/*     */     }
/*     */ 
/* 138 */     if (WareDAO.updateWare(wb, stl))
/*     */     {
/* 141 */       if (recoveryType.equals("update")) {
/* 142 */         addWareVer(WAREtoVER(wb), stl);
/*     */       }
/* 144 */       WareManager.reloadMap();
/* 145 */       if ((wb.getWare_type() == 0) || (wb.getWare_type() == 1) || (wb.getWare_type() == 2))
/* 146 */         WareManager.createWarePage(wb);
/* 147 */       return true;
/*     */     }
/*     */ 
/* 151 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean recoveryWareVer(String wareid, String siteid, String verNum, SettingLogsBean stl)
/*     */     throws IOException
/*     */   {
/* 167 */     WareVerBean wvb = getWareObjbyVerNum(wareid, siteid, verNum);
/* 168 */     String recoveryType = "recovery";
/* 169 */     return updateWare(VERtoWARE(wvb, wareid), recoveryType, stl);
/*     */   }
/*     */ 
/*     */   public static WareVerBean getWareObjbyVerNum(String wareid, String siteid, String verNum)
/*     */   {
/* 182 */     return WareDAO.getWareObjbyVerNum(wareid, siteid, verNum);
/*     */   }
/*     */ 
/*     */   public static WareBean VERtoWARE(WareVerBean wvb, String wareid)
/*     */   {
/* 188 */     if (wvb == null)
/*     */     {
/* 190 */       return null;
/*     */     }
/* 192 */     WareBean wb = new WareBean();
/* 193 */     wb.setId(WareDAO.getWareIDBywareID(wareid));
/* 194 */     wb.setWcat_id(wvb.getWare_id());
/* 195 */     wb.setWcat_id(wvb.getWcat_id());
/* 196 */     wb.setWare_name(wvb.getWare_name());
/* 197 */     wb.setRss_url(wvb.getRss_url());
/* 198 */     wb.setWare_content(wvb.getWare_content());
/* 199 */     wb.setWare_type(wvb.getWare_type());
/* 200 */     wb.setWare_ver(wvb.getWare_ver());
/* 201 */     wb.setInfo_num(wvb.getInfo_num());
/* 202 */     wb.setWare_width(wvb.getWare_width());
/* 203 */     wb.setWare_interval(wvb.getWare_interval());
/* 204 */     wb.setWare_url(wvb.getWare_url());
/* 205 */     wb.setSort_id(wvb.getSort_id());
/* 206 */     wb.setWare_memo(wvb.getWare_memo());
/* 207 */     wb.setUpdate_dtime(wvb.getUpdate_dtime());
/* 208 */     wb.setNext_dtime(wvb.getNext_dtime());
/* 209 */     wb.setApp_id(wvb.getApp_id());
/* 210 */     wb.setSite_id(wvb.getSite_id());
/* 211 */     wb.setReceive_recom(wvb.getReceive_recom());
/*     */ 
/* 213 */     return wb;
/*     */   }
/*     */ 
/*     */   public static WareVerBean WAREtoVER(WareBean wb)
/*     */   {
/* 221 */     if (wb == null) return null;
/* 222 */     WareVerBean wvb = new WareVerBean();
/*     */ 
/* 224 */     wvb.setWare_id(wb.getWare_id());
/* 225 */     wvb.setWcat_id(wb.getWcat_id());
/* 226 */     wvb.setWare_name(wb.getWare_name());
/* 227 */     wvb.setRss_url(wb.getRss_url());
/* 228 */     wvb.setWare_content(wb.getWare_content());
/* 229 */     wvb.setWare_type(wb.getWare_type());
/* 230 */     wvb.setWare_ver(wb.getWare_ver());
/* 231 */     wvb.setInfo_num(wb.getInfo_num());
/* 232 */     wvb.setWare_width(wb.getWare_width());
/* 233 */     wvb.setWare_interval(wb.getWare_interval());
/* 234 */     wvb.setWare_url(wb.getWare_url());
/* 235 */     wvb.setSort_id(wb.getSort_id());
/* 236 */     wvb.setWare_memo(wb.getWare_memo());
/* 237 */     wvb.setUpdate_dtime(wb.getUpdate_dtime());
/* 238 */     wvb.setNext_dtime(wb.getNext_dtime());
/* 239 */     wvb.setCreat_dtime(DateUtil.getCurrentDateTime());
/* 240 */     wvb.setApp_id(wb.getApp_id());
/* 241 */     wvb.setSite_id(wb.getSite_id());
/* 242 */     wvb.setReceive_recom(wb.getReceive_recom());
/* 243 */     return wvb;
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.system.ware.WareManagerExtend
 * JD-Core Version:    0.6.2
 */