/*    */ package com.cicro.wcm.services.query;
/*    */ 
/*    */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*    */ import com.cicro.wcm.bean.query.QueryDicBean;
/*    */ import com.cicro.wcm.services.Log.LogManager;
/*    */ import java.io.PrintStream;
/*    */ import java.util.List;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ 
/*    */ public class QueryDicRPC
/*    */ {
/*    */   public static List<QueryDicBean> getDicListByConf_id(int conf_id)
/*    */   {
/* 18 */     return QueryDicManager.getDicListByConf_id(conf_id);
/*    */   }
/*    */ 
/*    */   public static boolean insertQueryDicBean(int conf_id, List<QueryDicBean> l, HttpServletRequest request)
/*    */   {
/* 23 */     SettingLogsBean stl = LogManager.getSettingLogsByRequest(request);
/* 24 */     if (stl != null)
/*    */     {
/* 26 */       return QueryDicManager.insertQueryDic(conf_id, l, stl);
/*    */     }
/* 28 */     return false;
/*    */   }
/*    */ 
/*    */   public static QueryDicBean getDicByConf_Dic_Id(int dic_id, int conf_id)
/*    */   {
/* 34 */     System.out.println("dic_id=====" + dic_id + "==conf_id   ====" + conf_id);
/* 35 */     return QueryDicManager.getDicByConf_Dic_Id(dic_id, conf_id);
/*    */   }
/*    */ 
/*    */   public static void main(String[] args)
/*    */   {
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.query.QueryDicRPC
 * JD-Core Version:    0.6.2
 */