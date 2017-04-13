/*     */ package com.cicro.wcm.services.interview;
/*     */ 
/*     */ import com.cicro.util.DateUtil;
/*     */ import com.cicro.wcm.bean.interview.SubjectActor;
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.dao.interview.SubjectActorDAO;
/*     */ import java.io.PrintStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.UUID;
/*     */ 
/*     */ public class SubjectActorServices
/*     */ {
/*     */   public static String getActorListALLCount(String sub_id)
/*     */   {
/*  27 */     Map m = new HashMap();
/*  28 */     m.put("sub_id", sub_id);
/*  29 */     return SubjectActorDAO.getActorListALLCount(m);
/*     */   }
/*     */ 
/*     */   public static String getAllActorNames(String sub_id)
/*     */   {
/*  40 */     String names = "";
/*  41 */     List l = new ArrayList();
/*  42 */     l = SubjectActorDAO.getAllActorName(sub_id);
/*  43 */     if ((l != null) && (l.size() > 0))
/*     */     {
/*  45 */       for (int i = 0; i < l.size(); i++)
/*     */       {
/*  47 */         names = names + "," + ((SubjectActor)l.get(i)).getActor_name();
/*     */       }
/*     */     }
/*  50 */     if ((names != null) && (!"".equals(names))) {
/*  51 */       names = names.substring(1);
/*     */     }
/*  53 */     return names;
/*     */   }
/*     */ 
/*     */   public static List<SubjectActor> getAllActorName(String sub_id)
/*     */   {
/*  64 */     return SubjectActorDAO.getAllActorName(sub_id);
/*     */   }
/*     */ 
/*     */   public static List getActorList(String con, String sub_id)
/*     */   {
/*  75 */     Map m = new HashMap();
/*  76 */     m.put("sub_id", sub_id);
/*  77 */     m.put("con", con);
/*  78 */     return SubjectActorDAO.getActorList(m);
/*     */   }
/*     */ 
/*     */   public static List getActorListALL(String sub_id)
/*     */   {
/*  89 */     Map m = new HashMap();
/*  90 */     m.put("sub_id", sub_id);
/*  91 */     return SubjectActorDAO.getActorListALL(m);
/*     */   }
/*     */ 
/*     */   public static SubjectActor getSubActor(int id)
/*     */   {
/* 101 */     return SubjectActorDAO.getSubActor(id);
/*     */   }
/*     */ 
/*     */   public static SubjectActor getSubActor(String id)
/*     */   {
/* 111 */     return SubjectActorDAO.getSubActor(id);
/*     */   }
/*     */ 
/*     */   public static boolean insertSubActor(SubjectActor sa, SettingLogsBean stl)
/*     */   {
/* 121 */     sa.setActor_id(UUID.randomUUID().toString());
/* 122 */     sa.setAdd_time(DateUtil.getCurrentDateTime());
/* 123 */     return SubjectActorDAO.insertSubActor(sa, stl);
/*     */   }
/*     */ 
/*     */   public static boolean updateSubActor(SubjectActor sa, SettingLogsBean stl)
/*     */   {
/* 133 */     sa.setUpdate_time(DateUtil.getCurrentDateTime());
/* 134 */     return SubjectActorDAO.updateSubActor(sa, stl);
/*     */   }
/*     */ 
/*     */   public static boolean deleteSubActor(String ids, String user_name, SettingLogsBean stl)
/*     */   {
/* 144 */     Map m = new HashMap();
/* 145 */     m.put("ids", ids);
/* 146 */     m.put("user_name", user_name);
/* 147 */     m.put("current_time", DateUtil.getCurrentDateTime());
/* 148 */     return SubjectActorDAO.deleteSubActor(m, stl);
/*     */   }
/*     */ 
/*     */   public static boolean saveSubActorSort(String ids, SettingLogsBean stl)
/*     */   {
/* 158 */     Map m = new HashMap();
/* 159 */     m.put("ids", ids);
/* 160 */     return SubjectActorDAO.saveSubActorSort(m, stl);
/*     */   }
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/* 165 */     System.out.println(getSubActor("91c2b307-cd11-46e4-91d7-e7c3c7c8b4e4"));
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.interview.SubjectActorServices
 * JD-Core Version:    0.6.2
 */