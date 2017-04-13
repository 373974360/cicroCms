/*     */ package com.cicro.wcm.template.velocity.data;
/*     */ 
/*     */ import com.cicro.wcm.bean.org.dept.DeptBean;
/*     */ import com.cicro.wcm.bean.org.user.UserBean;
/*     */ import com.cicro.wcm.services.org.dept.DeptManager;
/*     */ import com.cicro.wcm.services.org.user.UserManager;
/*     */ import java.io.PrintStream;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class MingLuData
/*     */ {
/*     */   public static DeptBean getDeptObject(String dept_id)
/*     */   {
/*  14 */     return DeptManager.getDeptBeanByID(dept_id);
/*     */   }
/*     */ 
/*     */   public static String getDeptName(String dept_id)
/*     */   {
/*  19 */     return DeptManager.getDeptName(dept_id);
/*     */   }
/*     */ 
/*     */   public static List<UserBean> getUserListForDept(String dept_id)
/*     */   {
/*  29 */     Map m = new HashMap();
/*  30 */     m.put("dept_id", dept_id);
/*  31 */     m.put("sort_name", "sort");
/*  32 */     m.put("sort_type", "asc");
/*  33 */     m.put("is_publish", "1");
/*  34 */     return UserManager.getUserListByDeptIDForDB(m);
/*     */   }
/*     */ 
/*     */   public static UserBean getUserObject(String user_id)
/*     */   {
/*  44 */     return UserManager.getAllUserInfoByID(user_id);
/*     */   }
/*     */ 
/*     */   public static List<DeptBean> getDeptChildList(String dept_id)
/*     */   {
/*  54 */     DeptBean db = DeptManager.getDeptBeanByID(dept_id);
/*  55 */     if (db != null)
/*     */     {
/*  57 */       return DeptManager.getChildDeptListForPublish(dept_id);
/*     */     }
/*  59 */     return null;
/*     */   }
/*     */ 
/*     */   public static String getDeptTree(String dept_id)
/*     */   {
/*  69 */     if ((dept_id == null) || ("".equals(dept_id))) {
/*  70 */       dept_id = "1";
/*     */     }
/*  72 */     DeptBean db = DeptManager.getDeptBeanByID(dept_id);
/*  73 */     if (db != null)
/*     */     {
/*  75 */       String json_str = "[{\"id\":\"dept_" + dept_id + "\",\"text\":\"" + db.getDept_name() + "\"";
/*  76 */       String child_str = deptListToStrHandl(DeptManager.getChildDeptListForPublish(dept_id), false);
/*  77 */       if ((child_str != null) && (!"".equals(child_str)))
/*  78 */         json_str = json_str + ",\"children\":[" + child_str + "]";
/*  79 */       json_str = json_str + "}]";
/*  80 */       return json_str;
/*     */     }
/*  82 */     return "[]";
/*     */   }
/*     */ 
/*     */   public static String getDeptUserTree(String dept_id)
/*     */   {
/*  92 */     if ((dept_id == null) || ("".equals(dept_id))) {
/*  93 */       dept_id = "1";
/*     */     }
/*  95 */     DeptBean db = DeptManager.getDeptBeanByID(dept_id);
/*  96 */     if (db != null)
/*     */     {
/*  98 */       String json_str = "[{\"id\":\"dept_" + dept_id + "\",\"text\":\"" + db.getDept_name() + "\"";
/*  99 */       String child_str = deptListToStrHandl(DeptManager.getChildDeptListForPublish(dept_id), true);
/* 100 */       String user_str = UserTreeHandl(dept_id);
/* 101 */       if ((child_str != null) && (!"".equals(child_str)))
/*     */       {
/* 103 */         if ((user_str != null) && (!"".equals(user_str)))
/* 104 */           child_str = child_str + "," + user_str;
/* 105 */         json_str = json_str + ",\"children\":[" + child_str + "]";
/*     */       }
/* 108 */       else if ((user_str != null) && (!"".equals(user_str)))
/*     */       {
/* 110 */         json_str = json_str + ",\"children\":[" + user_str + "]";
/*     */       }
/*     */ 
/* 113 */       json_str = json_str + "}]";
/* 114 */       return json_str;
/*     */     }
/* 116 */     return "[]";
/*     */   }
/*     */ 
/*     */   public static String deptListToStrHandl(List<DeptBean> dl, boolean add_user_str)
/*     */   {
/* 121 */     String json_str = "";
/* 122 */     if ((dl != null) && (dl.size() > 0))
/*     */     {
/* 124 */       for (DeptBean db : dl)
/*     */       {
/* 126 */         json_str = json_str + ",{";
/* 127 */         json_str = json_str + "\"id\":\"dept_" + db.getDept_id() + "\",\"text\":\"" + db.getDept_name() + "\",\"icon\":\"/wcm.files/images/ico_dept.gif\"";
/* 128 */         List child_d_list = DeptManager.getChildDeptListForPublish(db.getDept_id());
/* 129 */         if ((child_d_list != null) && (child_d_list.size() > 0))
/*     */         {
/* 131 */           String child_str = deptListToStrHandl(child_d_list, add_user_str);
/* 132 */           if (add_user_str)
/*     */           {
/* 134 */             String user_str = UserTreeHandl(db.getDept_id());
/* 135 */             if ((user_str != null) && (!"".equals(user_str)))
/* 136 */               child_str = child_str + "," + user_str;
/*     */           }
/* 138 */           json_str = json_str + ",\"children\":[" + child_str + "]";
/*     */         }
/* 141 */         else if (add_user_str)
/*     */         {
/* 143 */           String user_str = UserTreeHandl(db.getDept_id());
/* 144 */           if ((user_str != null) && (!"".equals(user_str)))
/*     */           {
/* 146 */             json_str = json_str + ",\"children\":[" + user_str + "]";
/*     */           }
/*     */         }
/*     */ 
/* 150 */         json_str = json_str + "}";
/*     */       }
/* 152 */       if ((json_str != null) && (!"".equals(json_str)))
/* 153 */         json_str = json_str.substring(1);
/*     */     }
/* 155 */     return json_str;
/*     */   }
/*     */ 
/*     */   public static String UserTreeHandl(String dept_id)
/*     */   {
/* 160 */     String json_str = "";
/* 161 */     List l = UserManager.getUserListForPublishByDeptID(dept_id);
/* 162 */     if ((l != null) && (l.size() > 0))
/*     */     {
/* 164 */       for (UserBean ub : l)
/*     */       {
/* 166 */         json_str = json_str + ",{";
/* 167 */         json_str = json_str + "\"id\":\"user_" + ub.getUser_id() + "\",\"text\":\"" + ub.getUser_realname() + "\",\"icon\":\"/wcm.files/images/ico_user.gif\"";
/* 168 */         json_str = json_str + "}";
/*     */       }
/* 170 */       if ((json_str != null) && (!"".equals(json_str)))
/* 171 */         json_str = json_str.substring(1);
/*     */     }
/* 173 */     return json_str;
/*     */   }
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/* 178 */     System.out.println(getDeptUserTree("1"));
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.template.velocity.data.MingLuData
 * JD-Core Version:    0.6.2
 */