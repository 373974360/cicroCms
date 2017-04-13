/*    */ package com.cicro.wcm.services.appeal.model;
/*    */ 
/*    */ import com.cicro.wcm.bean.appeal.model.CPFrom;
/*    */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*    */ import com.cicro.wcm.catchs.ISyncCatch;
/*    */ import com.cicro.wcm.catchs.SyncCatchHandl;
/*    */ import com.cicro.wcm.dao.appeal.model.ModelDAO;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ 
/*    */ public class CPFromManager
/*    */   implements ISyncCatch
/*    */ {
/* 26 */   private static List<CPFrom> from_list = new ArrayList();
/*    */ 
/*    */   static {
/* 29 */     reloadCatchHandl();
/*    */   }
/*    */ 
/*    */   public void reloadCatch()
/*    */   {
/* 34 */     reloadCatchHandl();
/*    */   }
/*    */ 
/*    */   public static void reloadCatchHandl()
/*    */   {
/* 39 */     from_list.clear();
/* 40 */     from_list = ModelDAO.getAllCPFormList();
/*    */   }
/*    */ 
/*    */   public static void reloadCPFrom()
/*    */   {
/* 45 */     SyncCatchHandl.reladCatchForRMI("com.cicro.wcm.services.appeal.model.CPFromManager");
/*    */   }
/*    */ 
/*    */   public static List<CPFrom> getCPFormListByModel(int model_id)
/*    */   {
/* 50 */     List l = new ArrayList();
/* 51 */     if ((from_list != null) && (from_list.size() > 0))
/*    */     {
/* 53 */       for (int i = 0; i < from_list.size(); i++)
/*    */       {
/* 55 */         if (((CPFrom)from_list.get(i)).getModel_id() == model_id)
/*    */         {
/* 57 */           l.add((CPFrom)from_list.get(i));
/*    */         }
/*    */       }
/*    */     }
/* 61 */     return l;
/*    */   }
/*    */ 
/*    */   public static boolean insertCPFrom(int model_id, List<CPFrom> l, SettingLogsBean stl)
/*    */   {
/* 66 */     if (ModelDAO.insertCPFrom(model_id, l, stl))
/*    */     {
/* 68 */       reloadCPFrom();
/* 69 */       return true;
/*    */     }
/* 71 */     return false;
/*    */   }
/*    */ 
/*    */   public static boolean deleteCPFrom(String model_ids)
/*    */   {
/* 76 */     if (ModelDAO.deleteCPFrom(model_ids))
/*    */     {
/* 78 */       reloadCPFrom();
/* 79 */       return true;
/*    */     }
/* 81 */     return false;
/*    */   }
/*    */ 
/*    */   public static void main(String[] args)
/*    */   {
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.appeal.model.CPFromManager
 * JD-Core Version:    0.6.2
 */