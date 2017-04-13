/*     */ package com.cicro.wcm.dao.appeal.cpLead;
/*     */ 
/*     */ import com.cicro.wcm.bean.appeal.cpLead.CpLeadBean;
/*     */ import com.cicro.wcm.db.DBManager;
/*     */ import java.io.PrintStream;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class CpLeadDAO
/*     */ {
/*     */   public static boolean insertCpLead(CpLeadBean lead)
/*     */   {
/*  25 */     return DBManager.insert("insert_cpLead", lead);
/*     */   }
/*     */ 
/*     */   public static boolean updateCpLead(CpLeadBean lead)
/*     */   {
/*  34 */     return DBManager.update("update_cpLead", lead);
/*     */   }
/*     */ 
/*     */   public static boolean deleteCpLead(Map<String, String> m)
/*     */   {
/*  44 */     return DBManager.delete("delete_cpLead", m);
/*     */   }
/*     */ 
/*     */   public static CpLeadBean getCpLeadById(String lead_id)
/*     */   {
/*  53 */     return (CpLeadBean)DBManager.queryFObj("getCpLeadBean", lead_id);
/*     */   }
/*     */ 
/*     */   public static boolean savesortCpLead(int start, String lead_ids)
/*     */   {
/*  63 */     System.out.println("lead_ids-------" + lead_ids);
/*  64 */     String[] tempA = lead_ids.split(",");
/*  65 */     Map m = new HashMap();
/*     */     try
/*     */     {
/*  69 */       for (int i = 0; i < tempA.length; i++)
/*     */       {
/*  71 */         m.put("lead_id", tempA[i]);
/*  72 */         m.put("sort_id", Integer.valueOf(start + i + 1));
/*  73 */         System.out.println("update cp_lead set sort_id = " + m.get("sort_id") + " where lead_id = " + m.get("lead_id"));
/*  74 */         DBManager.update("savesort_cpLead", m);
/*     */       }
/*  76 */       return true;
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/*  80 */       e.printStackTrace();
/*  81 */     }return false;
/*     */   }
/*     */ 
/*     */   public static List<CpLeadBean> getCpLeadList(Map<String, String> m)
/*     */   {
/*  91 */     return DBManager.queryFList("getCpLeadList", m);
/*     */   }
/*     */ 
/*     */   public static String getCpLeadCount(Map<String, String> m)
/*     */   {
/* 100 */     return DBManager.getString("getCpLeadCount", m);
/*     */   }
/*     */ 
/*     */   public static List<CpLeadBean> getAllCpLeadList()
/*     */   {
/* 108 */     return DBManager.queryFList("getAllCpLeadList", "");
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.dao.appeal.cpLead.CpLeadDAO
 * JD-Core Version:    0.6.2
 */