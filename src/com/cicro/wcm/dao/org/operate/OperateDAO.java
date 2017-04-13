/*     */ package com.cicro.wcm.dao.org.operate;
/*     */ 
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.bean.org.app.AppBean;
/*     */ import com.cicro.wcm.bean.org.operate.OperateBean;
/*     */ import com.cicro.wcm.dao.PublicTableDAO;
/*     */ import com.cicro.wcm.dao.org.role.RoleOptDAO;
/*     */ import com.cicro.wcm.db.DBManager;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class OperateDAO
/*     */ {
/*     */   public static List<AppBean> getAppList()
/*     */   {
/*  34 */     return DBManager.queryFList("getAppList", "");
/*     */   }
/*     */ 
/*     */   public static List<OperateBean> getAllOperateList()
/*     */   {
/*  45 */     return DBManager.queryFList("getAllOperateList", "");
/*     */   }
/*     */ 
/*     */   public static OperateBean getOperateBean(String opt_id)
/*     */   {
/*  55 */     return (OperateBean)DBManager.queryFObj("getOperateBean", opt_id);
/*     */   }
/*     */ 
/*     */   public static boolean insertOperate(OperateBean ob, SettingLogsBean stl)
/*     */   {
/*  66 */     ob.setTree_position(ob.getTree_position() + ob.getOpt_id() + "$");
/*  67 */     if (DBManager.insert("insert_operate", ob))
/*     */     {
/*  69 */       PublicTableDAO.insertSettingLogs("添加", "权限", ob.getOpt_id(), stl);
/*  70 */       return true;
/*     */     }
/*  72 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateOperate(OperateBean ob, SettingLogsBean stl)
/*     */   {
/*  83 */     if (DBManager.update("update_operate", ob))
/*     */     {
/*  85 */       PublicTableDAO.insertSettingLogs("添加", "修改", ob.getOpt_id(), stl);
/*  86 */       return true;
/*     */     }
/*  88 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean moveOperate(Map<String, String> m, SettingLogsBean stl)
/*     */   {
/*  99 */     if (DBManager.update("move_operate", m))
/*     */     {
/* 101 */       PublicTableDAO.insertSettingLogs("移动", "权限", (String)m.get("opt_id"), stl);
/* 102 */       return true;
/*     */     }
/*     */ 
/* 105 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteOperate(String opt_id, SettingLogsBean stl)
/*     */   {
/* 116 */     Map m = new HashMap();
/* 117 */     m.put("opt_id", opt_id);
/* 118 */     if (DBManager.delete("delete_operate", m))
/*     */     {
/* 121 */       RoleOptDAO.deleteOptReleRoleByOptID(opt_id);
/* 122 */       PublicTableDAO.insertSettingLogs("删除", "权限", opt_id, stl);
/* 123 */       return true;
/*     */     }
/* 125 */     return false;
/*     */   }
/*     */ 
/*     */   public static List<AppBean> getOptAppListbyRoleID(String role_ids)
/*     */   {
/* 136 */     Map m = new HashMap();
/* 137 */     m.put("role_ids", role_ids);
/* 138 */     return DBManager.queryFList("getOptAppListbyRoleID", m);
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.dao.org.operate.OperateDAO
 * JD-Core Version:    0.6.2
 */