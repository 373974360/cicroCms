/*    */ package com.cicro.wcm.services.system.config;
/*    */ 
/*    */ import com.cicro.wcm.bean.system.config.ConfigBean;
/*    */ import com.cicro.wcm.dao.PublicTableDAO;
/*    */ import com.cicro.wcm.dao.system.config.ConfigDAO;
/*    */ import com.cicro.wcm.services.control.site.ICloneSite;
/*    */ import java.util.List;
/*    */ 
/*    */ public class ConfigCloneImpl
/*    */   implements ICloneSite
/*    */ {
/*    */   public boolean cloneSite(String site_id, String s_site_id)
/*    */   {
/*    */     try
/*    */     {
/* 14 */       List l = ConfigDAO.getSystemConfigListBySiteID(s_site_id);
/* 15 */       if ((l != null) && (l.size() > 0))
/*    */       {
/* 17 */         for (ConfigBean cb : l)
/*    */         {
/* 19 */           String insert_sql = PublicTableDAO.getIDByTableName(PublicTableDAO.SYS_CONFIG_TABLE_NAME) + ",'attachment','" + cb.getKey() + "','" + cb.getValue() + "','" + site_id + "','cms'";
/*    */ 
/* 21 */           ConfigDAO.addConfig(insert_sql, null);
/*    */         }
/*    */       }
/* 24 */       return true;
/*    */     } catch (Exception e) {
/* 26 */       e.printStackTrace();
/* 27 */     }return false;
/*    */   }
/*    */ 
/*    */   public static void main(String[] args)
/*    */   {
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.system.config.ConfigCloneImpl
 * JD-Core Version:    0.6.2
 */