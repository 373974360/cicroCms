/*    */ package com.cicro.wcm.services.appeal.cpLead;
/*    */ 
/*    */ import com.cicro.wcm.bean.appeal.cpLead.CpLeadBean;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class CpLeadRPC
/*    */ {
/*    */   public static List<CpLeadBean> getAllCpLeadList()
/*    */   {
/* 22 */     return CpLeadManager.getAllCpLeadList();
/*    */   }
/*    */ 
/*    */   public static boolean insertCpLead(CpLeadBean lead)
/*    */   {
/* 31 */     return CpLeadManager.insertCpLead(lead);
/*    */   }
/*    */ 
/*    */   public static boolean updateCpLead(CpLeadBean lead)
/*    */   {
/* 40 */     return CpLeadManager.updateCpLead(lead);
/*    */   }
/*    */ 
/*    */   public static boolean deleteCpLead(String leads)
/*    */   {
/* 50 */     return CpLeadManager.deleteCpLead(leads);
/*    */   }
/*    */ 
/*    */   public static CpLeadBean getCpLeadById(String lead_id)
/*    */   {
/* 59 */     return CpLeadManager.getCpLeadById(lead_id);
/*    */   }
/*    */ 
/*    */   public static boolean savesortCpLead(int start, String lead_ids)
/*    */   {
/* 68 */     return CpLeadManager.savesortCpLead(start, lead_ids);
/*    */   }
/*    */ 
/*    */   public static List<CpLeadBean> getCpLeadList(Map<String, String> m)
/*    */   {
/* 77 */     return CpLeadManager.getCpLeadList(m);
/*    */   }
/*    */ 
/*    */   public static String getCpLeadCount(Map<String, String> m)
/*    */   {
/* 86 */     return CpLeadManager.getCpLeadCount(m);
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.appeal.cpLead.CpLeadRPC
 * JD-Core Version:    0.6.2
 */