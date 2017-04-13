/*     */ package com.cicro.wcm.services.org.user;
/*     */ 
/*     */ import com.cicro.wcm.bean.control.SiteBean;
/*     */ import com.cicro.wcm.bean.org.app.AppBean;
/*     */ import com.cicro.wcm.bean.zwgk.node.GKNodeBean;
/*     */ import com.cicro.wcm.services.control.site.SiteManager;
/*     */ import com.cicro.wcm.services.org.operate.OperateManager;
/*     */ import com.cicro.wcm.services.org.role.RoleUserManager;
/*     */ import com.cicro.wcm.services.zwgk.node.GKNodeManager;
/*     */ import java.io.PrintStream;
/*     */ import java.util.List;
/*     */ 
/*     */ public class UserOptManager
/*     */ {
/*     */   public static String getAppJSONStrByUserID(String user_id)
/*     */   {
/*  34 */     String json_str = "[";
/*  35 */     String role_ids = RoleUserManager.getAllUserRoleIDS(user_id);
/*  36 */     List l = OperateManager.getOptAppListbyRoleID(role_ids);
/*     */ 
/*  38 */     int i = 0;
/*  39 */     if ((l != null) && (l.size() > 0))
/*     */     {
/*  41 */       for (AppBean ab : l)
/*     */       {
/*  43 */         if (i > 0)
/*  44 */           json_str = json_str + ",";
/*  45 */         json_str = json_str + "{\"id\":" + i + ",\"text\":\"" + ab.getApp_name() + "\",\"attributes\":{\"state\":\"is_sub\",\"real_id\":\"" + ab.getApp_id() + "\"}";
/*  46 */         if ("cms".equals(ab.getApp_id()))
/*     */         {
/*  48 */           json_str = json_str + getSiteJSONStrByUserID(user_id);
/*     */         }
/*  50 */         if ("zwgk".equals(ab.getApp_id()))
/*     */         {
/*  52 */           json_str = json_str + getZWGKJSONStrByUserID(user_id);
/*     */         }
/*  54 */         i++;
/*  55 */         json_str = json_str + "}";
/*     */       }
/*     */     }
/*  58 */     json_str = json_str + "]";
/*  59 */     return json_str;
/*     */   }
/*     */ 
/*     */   public static String getSiteJSONStrByUserID(String user_id)
/*     */   {
/*  64 */     String json_str = "";
/*  65 */     List site_list = RoleUserManager.getAllUserSiteList(user_id, "cms");
/*  66 */     if ((site_list != null) && (site_list.size() > 0))
/*     */     {
/*  68 */       json_str = json_str + ",\"children\":[";
/*  69 */       int i = 0;
/*  70 */       for (String s : site_list)
/*     */       {
/*  72 */         SiteBean sb = SiteManager.getSiteBeanBySiteID(s);
/*  73 */         if (sb != null)
/*     */         {
/*  75 */           if (i > 0)
/*  76 */             json_str = json_str + ",";
/*  77 */           json_str = json_str + "{\"id\":" + (10000 + i) + ",\"text\":\"" + sb.getSite_name() + "\",\"attributes\":{\"state\":\"is_leaf\",\"real_id\":\"" + s + "\"}}";
/*  78 */           i++;
/*     */         }
/*     */       }
/*  81 */       json_str = json_str + "]";
/*     */     }
/*  83 */     return json_str;
/*     */   }
/*     */ 
/*     */   public static String getZWGKJSONStrByUserID(String user_id)
/*     */   {
/*  88 */     String json_str = "";
/*  89 */     List site_list = RoleUserManager.getAllUserSiteList(user_id, "zwgk");
/*  90 */     if ((site_list != null) && (site_list.size() > 0))
/*     */     {
/*  92 */       json_str = json_str + ",\"children\":[";
/*  93 */       int i = 0;
/*  94 */       for (String s : site_list)
/*     */       {
/*  96 */         GKNodeBean gnb = GKNodeManager.getGKNodeBeanByNodeID(s);
/*  97 */         if (gnb != null)
/*     */         {
/*  99 */           if (i > 0)
/* 100 */             json_str = json_str + ",";
/* 101 */           json_str = json_str + "{\"id\":" + (100000 + i) + ",\"text\":\"" + gnb.getNode_name() + "\",\"attributes\":{\"state\":\"is_leaf\",\"real_id\":\"" + s + "\"}}";
/* 102 */           i++;
/*     */         }
/*     */       }
/* 105 */       json_str = json_str + "]";
/*     */     }
/* 107 */     return json_str;
/*     */   }
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/* 113 */     System.out.println(getAppJSONStrByUserID("72"));
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.org.user.UserOptManager
 * JD-Core Version:    0.6.2
 */