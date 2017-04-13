/*     */ package com.cicro.wcm.dao.interview;
/*     */ 
/*     */ import com.cicro.wcm.bean.interview.SubjectActor;
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.dao.PublicTableDAO;
/*     */ import com.cicro.wcm.db.DBManager;
/*     */ import java.io.PrintStream;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class SubjectActorDAO
/*     */ {
/*     */   public static String getActorListALLCount(Map m)
/*     */   {
/*  28 */     return DBManager.getString("getActorListALLCount", m);
/*     */   }
/*     */ 
/*     */   public static List getAllActorName(String sub_id)
/*     */   {
/*  39 */     List l = DBManager.queryFList("getAllActorName", sub_id);
/*  40 */     return l;
/*     */   }
/*     */ 
/*     */   public static List getActorList(Map m)
/*     */   {
/*  51 */     List l = DBManager.queryFList("getActorList", m);
/*  52 */     return l;
/*     */   }
/*     */ 
/*     */   public static List getActorListALL(Map m)
/*     */   {
/*  63 */     List l = DBManager.queryFList("getActorListALL", m);
/*  64 */     return l;
/*     */   }
/*     */ 
/*     */   public static SubjectActor getSubActor(int id)
/*     */   {
/*  74 */     return (SubjectActor)DBManager.queryFObj("getSubActor", Integer.valueOf(id));
/*     */   }
/*     */ 
/*     */   public static SubjectActor getSubActor(String id)
/*     */   {
/*  84 */     return (SubjectActor)DBManager.queryFObj("getSubActorByUUID", id);
/*     */   }
/*     */ 
/*     */   public static boolean insertSubActor(SubjectActor sa, SettingLogsBean stl)
/*     */   {
/*  95 */     int id = PublicTableDAO.getIDByTableName(PublicTableDAO.INTERVIEW_ACTOR_TABLE_NAME);
/*  96 */     sa.setId(id);
/*  97 */     if (DBManager.insert("cp_subActor_insert", sa))
/*     */     {
/*  99 */       PublicTableDAO.insertSettingLogs("添加", "访谈参与者", id, stl);
/* 100 */       return true;
/*     */     }
/* 102 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateSubActor(SubjectActor sa, SettingLogsBean stl)
/*     */   {
/* 112 */     if (DBManager.update("cp_subActor_update", sa))
/*     */     {
/* 114 */       PublicTableDAO.insertSettingLogs("修改", "访谈参与者", sa.getId(), stl);
/* 115 */       return true;
/*     */     }
/* 117 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteSubActor(Map m, SettingLogsBean stl)
/*     */   {
/* 129 */     if (DBManager.update("cp_subActor_delete", m))
/*     */     {
/* 131 */       PublicTableDAO.insertSettingLogs("删除", "访谈参与者", m.get("ids"), stl);
/* 132 */       return true;
/*     */     }
/* 134 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean saveSubActorSort(Map m, SettingLogsBean stl)
/*     */   {
/* 145 */     String ids = (String)m.get("ids");
/* 146 */     if ((ids != null) && (!"".equals(ids)))
/*     */     {
/* 148 */       Map new_m = new HashMap();
/* 149 */       String[] tempA = ids.split(",");
/*     */       try {
/* 151 */         for (int i = 0; i < tempA.length; i++)
/*     */         {
/* 153 */           new_m.put("sort", i + 1);
/* 154 */           new_m.put("id", tempA[i]);
/* 155 */           DBManager.update("cp_subActor_sort", new_m);
/*     */         }
/*     */       }
/*     */       catch (Exception e) {
/* 159 */         e.printStackTrace();
/* 160 */         return false;
/*     */       }
/* 162 */       PublicTableDAO.insertSettingLogs("保存排序", "访谈参与者", m.get("ids"), stl);
/*     */     }
/* 164 */     return true;
/*     */   }
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/* 170 */     SubjectActor s = new SubjectActor();
/* 171 */     s.setId(8);
/* 172 */     s.setActor_id("111");
/* 173 */     s.setSub_id("222");
/* 174 */     s.setActor_name("3333");
/* 175 */     s.setSex("ggg");
/* 176 */     System.out.println(getAllActorName("28f0916c-0b49-4ee5-b8e2-22bb1c72fa26"));
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.dao.interview.SubjectActorDAO
 * JD-Core Version:    0.6.2
 */