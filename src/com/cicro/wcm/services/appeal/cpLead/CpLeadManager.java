/*     */ package com.cicro.wcm.services.appeal.cpLead;
/*     */ 
/*     */ import com.cicro.wcm.bean.appeal.cpDept.CpDeptBean;
/*     */ import com.cicro.wcm.bean.appeal.cpLead.CpLeadBean;
/*     */ import com.cicro.wcm.catchs.ISyncCatch;
/*     */ import com.cicro.wcm.catchs.SyncCatchHandl;
/*     */ import com.cicro.wcm.dao.PublicTableDAO;
/*     */ import com.cicro.wcm.dao.appeal.cpLead.CpLeadDAO;
/*     */ import com.cicro.wcm.services.appeal.cpDept.CpDeptManager;
/*     */ import java.io.PrintStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class CpLeadManager
/*     */   implements ISyncCatch
/*     */ {
/*  25 */   private static List<CpLeadBean> cpLeadList = new ArrayList();
/*     */ 
/*     */   static {
/*  28 */     reloadCatchHandl();
/*     */   }
/*     */ 
/*     */   public void reloadCatch()
/*     */   {
/*  33 */     reloadCatchHandl();
/*     */   }
/*     */ 
/*     */   public static void reloadCatchHandl()
/*     */   {
/*  38 */     cpLeadList.clear();
/*  39 */     cpLeadList = CpLeadDAO.getAllCpLeadList();
/*     */   }
/*     */ 
/*     */   public static void reloadCpLeadList()
/*     */   {
/*  46 */     SyncCatchHandl.reladCatchForRMI("com.cicro.wcm.services.appeal.cpLead.CpLeadManager");
/*     */   }
/*     */ 
/*     */   public static List<CpLeadBean> getAllCpLeadList()
/*     */   {
/*  54 */     return cpLeadList;
/*     */   }
/*     */ 
/*     */   public static boolean insertCpLead(CpLeadBean lead)
/*     */   {
/*  63 */     int id = PublicTableDAO.getIDByTableName(PublicTableDAO.APPEAL_Lead_TABLE_NAME);
/*  64 */     lead.setLead_id(id);
/*  65 */     if (CpLeadDAO.insertCpLead(lead)) {
/*  66 */       reloadCpLeadList();
/*  67 */       return true;
/*     */     }
/*  69 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateCpLead(CpLeadBean lead)
/*     */   {
/*  79 */     if (CpLeadDAO.updateCpLead(lead)) {
/*  80 */       reloadCpLeadList();
/*  81 */       return true;
/*     */     }
/*  83 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteCpLead(String leads)
/*     */   {
/*  95 */     Map m = new HashMap();
/*  96 */     m.put("lead_ids", leads);
/*     */ 
/*  98 */     if (CpLeadDAO.deleteCpLead(m)) {
/*  99 */       reloadCpLeadList();
/* 100 */       return true;
/*     */     }
/* 102 */     return false;
/*     */   }
/*     */ 
/*     */   public static CpLeadBean getCpLeadById(String lead_id)
/*     */   {
/* 113 */     if ((cpLeadList != null) && (cpLeadList.size() > 0))
/*     */     {
/* 115 */       for (int i = 0; i < cpLeadList.size(); i++)
/*     */       {
/* 117 */         if (lead_id.equals(((CpLeadBean)cpLeadList.get(i)).getLead_id()))
/*     */         {
/* 119 */           CpLeadBean cpLead = (CpLeadBean)cpLeadList.get(i);
/* 120 */           CpDeptBean dept = CpDeptManager.getCpDeptBean(cpLead.getDept_id());
/* 121 */           if (dept != null) {
/* 122 */             cpLead.setDept_name(dept.getDept_name());
/*     */           }
/* 124 */           return cpLead;
/*     */         }
/*     */       }
/*     */     }
/* 128 */     CpLeadBean cpLead = CpLeadDAO.getCpLeadById(lead_id);
/* 129 */     if (cpLead != null)
/*     */     {
/* 131 */       CpDeptBean dept = CpDeptManager.getCpDeptBean(cpLead.getDept_id());
/* 132 */       if (dept != null) {
/* 133 */         cpLead.setDept_name(dept.getDept_name());
/*     */       }
/* 135 */       return cpLead;
/*     */     }
/* 137 */     return null;
/*     */   }
/*     */ 
/*     */   public static boolean savesortCpLead(int start, String lead_ids)
/*     */   {
/* 146 */     if (CpLeadDAO.savesortCpLead(start, lead_ids)) {
/* 147 */       reloadCpLeadList();
/* 148 */       return true;
/*     */     }
/* 150 */     return false;
/*     */   }
/*     */ 
/*     */   public static List<CpLeadBean> getCpLeadList(Map<String, String> m)
/*     */   {
/* 160 */     List leadList = CpLeadDAO.getCpLeadList(m);
/* 161 */     if ((leadList != null) && (leadList.size() > 0)) {
/* 162 */       for (int i = 0; i < leadList.size(); i++) {
/* 163 */         CpDeptBean dept = CpDeptManager.getCpDeptBean(((CpLeadBean)leadList.get(i)).getDept_id());
/* 164 */         if (dept != null) {
/* 165 */           ((CpLeadBean)leadList.get(i)).setDept_name(dept.getDept_name());
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/* 170 */     return leadList;
/*     */   }
/*     */ 
/*     */   public static String getCpLeadCount(Map<String, String> m)
/*     */   {
/* 179 */     return CpLeadDAO.getCpLeadCount(m);
/*     */   }
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/* 191 */     Map m = new HashMap();
/* 192 */     m.put("start_num", Integer.valueOf(0));
/* 193 */     m.put("page_size", Integer.valueOf(15));
/* 194 */     m.put("sort_name", "sort_id");
/* 195 */     m.put("sort_type", "asc");
/* 196 */     System.out.println(getCpLeadList(m));
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.appeal.cpLead.CpLeadManager
 * JD-Core Version:    0.6.2
 */