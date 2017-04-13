/*     */ package com.cicro.wcm.services.system.template;
/*     */ 
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.bean.system.template.TemplateEditBean;
/*     */ import com.cicro.wcm.bean.system.template.TemplateVerBean;
/*     */ import com.cicro.wcm.dao.system.template.TemplateVerDAO;
/*     */ import java.io.PrintStream;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class TemplateVerManager
/*     */ {
/*  21 */   private static Map<String, TemplateVerBean> template_map = new HashMap();
/*     */ 
/*     */   public static void reloadTemplate()
/*     */   {
/*  29 */     List template_list = TemplateVerDAO.getAllTemplateVerList();
/*  30 */     template_map.clear();
/*  31 */     if ((template_list != null) && (template_list.size() > 0))
/*     */     {
/*  33 */       for (int i = 0; i < template_list.size(); i++)
/*  34 */         if (((TemplateVerBean)template_list.get(i)).getT_status() != 0)
/*  35 */           template_map.put(((TemplateVerBean)template_list.get(i)).getT_id() + "_" + ((TemplateVerBean)template_list.get(i)).getSite_id() + "_" + ((TemplateVerBean)template_list.get(i)).getT_ver(), (TemplateVerBean)template_list.get(i));
/*     */     }
/*     */   }
/*     */ 
/*     */   public static TemplateVerBean getTemplateVerBean(int t_id, String ver, String site_id)
/*     */   {
/*  56 */     return TemplateVerDAO.getTemplateVerBean(t_id, site_id, ver);
/*     */   }
/*     */ 
/*     */   public static TemplateVerBean getSimpleTemplateVerBean(int t_id, String ver, String site_id)
/*     */   {
/*  75 */     TemplateVerBean tb = TemplateVerDAO.getTemplateVerBean(t_id, site_id, ver);
/*  76 */     if (tb != null)
/*  77 */       tb.setT_content("");
/*  78 */     return tb;
/*     */   }
/*     */ 
/*     */   public static TemplateVerBean getTemplateVerBeanByState(int t_id, String site_id)
/*     */   {
/*  84 */     return TemplateVerDAO.getTemplateVerBeanByState(t_id, site_id);
/*     */   }
/*     */ 
/*     */   public static TemplateVerBean getTemplateVerBeanForDB(int t_id, String site_id, String ver)
/*     */   {
/*  89 */     return TemplateVerDAO.getTemplateVerBean(t_id, site_id, site_id);
/*     */   }
/*     */ 
/*     */   public static String getTemplateVerCount(Map<String, String> con_map)
/*     */   {
/*  99 */     return TemplateVerDAO.getTemplateVerCount(con_map);
/*     */   }
/*     */ 
/*     */   public static boolean updateTemplateVer(TemplateVerBean template, SettingLogsBean stl)
/*     */   {
/* 108 */     if (TemplateVerDAO.updateTemplateVer(template, stl))
/*     */     {
/* 111 */       return true;
/*     */     }
/* 113 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean addTemplateVer(TemplateVerBean template, SettingLogsBean stl)
/*     */   {
/* 122 */     if (template == null) {
/* 123 */       return false;
/*     */     }
/*     */ 
/* 126 */     if (TemplateVerDAO.addTemplateVer(template, stl))
/*     */     {
/* 128 */       return true;
/*     */     }
/* 130 */     return false;
/*     */   }
/*     */ 
/*     */   public static List<TemplateVerBean> getAllTemplateVerList()
/*     */   {
/* 145 */     return TemplateVerDAO.getAllTemplateVerList();
/*     */   }
/*     */ 
/*     */   public static List<TemplateVerBean> getTemplateVerListForDB(Map<String, String> con_map)
/*     */   {
/* 155 */     return TemplateVerDAO.getTemplateVerListForDB(con_map);
/*     */   }
/*     */ 
/*     */   public static boolean delTemplateVerByIds(String t_ids, String site_id, SettingLogsBean stl)
/*     */   {
/* 164 */     if (TemplateVerDAO.delTemplateVer(t_ids, site_id, stl))
/*     */     {
/* 173 */       return true;
/*     */     }
/* 175 */     return false;
/*     */   }
/*     */ 
/*     */   public static int getTemplateVerNum(int t_id, String site_id)
/*     */   {
/* 184 */     String num = TemplateVerDAO.getTemplateVerNum(t_id, site_id);
/* 185 */     if ((num == null) || (num.equals(""))) {
/* 186 */       num = "0";
/*     */     }
/* 188 */     return Integer.parseInt(num);
/*     */   }
/*     */ 
/*     */   public static boolean recoveryTemplateVer(int template_id, String site_id, String version, SettingLogsBean stl) {
/* 192 */     TemplateVerBean tvb = getTemplateVerBean(template_id, version, site_id);
/* 193 */     return TemplateEditManager.updateTemplateEdit(TVBtoTEB(tvb), stl);
/*     */   }
/*     */ 
/*     */   private static TemplateEditBean TVBtoTEB(TemplateVerBean teb) {
/* 197 */     if (teb == null) return null;
/* 198 */     TemplateEditBean tvb = new TemplateEditBean();
/* 199 */     tvb.setT_id(teb.getT_id());
/* 200 */     tvb.setTcat_id(teb.getTcat_id());
/* 201 */     tvb.setT_ename(teb.getT_ename());
/* 202 */     tvb.setT_cname(teb.getT_cname());
/* 203 */     tvb.setT_path(teb.getT_path());
/* 204 */     tvb.setT_content(teb.getT_content());
/* 205 */     tvb.setT_ver(teb.getT_ver());
/* 206 */     tvb.setCreat_user(teb.getCreat_user());
/* 207 */     tvb.setCreat_dtime(teb.getCreat_dtime());
/* 208 */     tvb.setModify_user(teb.getModify_user());
/* 209 */     tvb.setModify_dtime(teb.getModify_dtime());
/* 210 */     tvb.setApp_id(teb.getApp_id());
/* 211 */     tvb.setSite_id(teb.getSite_id());
/* 212 */     return tvb;
/*     */   }
/*     */ 
/*     */   public static void main(String[] args) {
/* 216 */     System.out.println(getTemplateVerBean(124, "1", "11111ddd"));
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.system.template.TemplateVerManager
 * JD-Core Version:    0.6.2
 */