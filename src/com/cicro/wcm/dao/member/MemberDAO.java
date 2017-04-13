/*     */ package com.cicro.wcm.dao.member;
/*     */ 
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.bean.member.MemberBean;
/*     */ import com.cicro.wcm.bean.member.MemberCategoryBean;
/*     */ import com.cicro.wcm.bean.member.MemberConfigBean;
/*     */ import com.cicro.wcm.bean.member.MemberRegisterBean;
/*     */ import com.cicro.wcm.dao.PublicTableDAO;
/*     */ import com.cicro.wcm.db.DBManager;
/*     */ import java.io.PrintStream;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class MemberDAO
/*     */ {
/*     */   public static List<MemberConfigBean> getAllMemberConfigList()
/*     */   {
/*  35 */     return DBManager.queryFList("getAllMemberConfigList", "");
/*     */   }
/*     */ 
/*     */   public static boolean updateMemberConfig(MemberConfigBean mcb, SettingLogsBean stl)
/*     */   {
/*  46 */     if (DBManager.update("updateMemberConfig", mcb))
/*     */     {
/*  48 */       PublicTableDAO.insertSettingLogs("修改", "会员配置", mcb.getConfig_id(), stl);
/*  49 */       return true;
/*     */     }
/*     */ 
/*  53 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteMemberConfig(Map<String, String> mp, SettingLogsBean stl)
/*     */   {
/*  65 */     if (DBManager.delete("deleteMemberConfig", mp))
/*     */     {
/*  67 */       PublicTableDAO.insertSettingLogs("删除", "会员配置", (String)mp.get("config_ids"), stl);
/*  68 */       return true;
/*     */     }
/*     */ 
/*  72 */     return false;
/*     */   }
/*     */ 
/*     */   public static List<MemberCategoryBean> getAllMemberCategoryList()
/*     */   {
/*  83 */     return DBManager.queryFList("getAllMemberCategoryList", "");
/*     */   }
/*     */ 
/*     */   public static boolean insertMemberCategory(MemberCategoryBean mcb, SettingLogsBean stl)
/*     */   {
/*  94 */     String id = PublicTableDAO.getIDByTableName(PublicTableDAO.MEMBER_CATEGORY_TABLE_NAME);
/*  95 */     mcb.setMcat_id(id);
/*  96 */     if (DBManager.insert("insertMemberCategory", mcb))
/*     */     {
/*  98 */       PublicTableDAO.insertSettingLogs("添加", "会员分类", mcb.getMcat_id(), stl);
/*  99 */       return true;
/*     */     }
/*     */ 
/* 103 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateMemberCategory(MemberCategoryBean mcb, SettingLogsBean stl)
/*     */   {
/* 115 */     if (DBManager.update("updateMemberCategory", mcb))
/*     */     {
/* 117 */       PublicTableDAO.insertSettingLogs("修改", "会员分类", mcb.getMcat_id(), stl);
/* 118 */       return true;
/*     */     }
/*     */ 
/* 122 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean saveMemberCategorySort(String mcat_id, SettingLogsBean stl)
/*     */   {
/* 135 */     if ((mcat_id != null) && (!"".equals(mcat_id))) {
/*     */       try
/*     */       {
/* 138 */         Map m = new HashMap();
/* 139 */         String[] tempA = mcat_id.split(",");
/* 140 */         for (int i = 0; i < tempA.length; i++)
/*     */         {
/* 142 */           m.put("sort_id", Integer.valueOf(i + 1));
/* 143 */           m.put("mcat_id", tempA[i]);
/* 144 */           DBManager.update("update_memberCategory_sort", m);
/*     */         }
/* 146 */         PublicTableDAO.insertSettingLogs("保存排序", "会员分类", mcat_id, stl);
/* 147 */         return true;
/*     */       }
/*     */       catch (Exception e) {
/* 150 */         e.printStackTrace();
/* 151 */         return false;
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 156 */     return true;
/*     */   }
/*     */ 
/*     */   public static boolean deleteMemberCategory(Map<String, String> mp, SettingLogsBean stl)
/*     */   {
/* 168 */     if (DBManager.delete("deleteMemberCategory", mp))
/*     */     {
/* 170 */       PublicTableDAO.insertSettingLogs("删除", "会员分类", (String)mp.get("mcat_ids"), stl);
/* 171 */       return true;
/*     */     }
/*     */ 
/* 175 */     return false;
/*     */   }
/*     */ 
/*     */   public static List<MemberBean> getAllMemberList()
/*     */   {
/* 186 */     return DBManager.queryFList("getAllMemberList", "");
/*     */   }
/*     */ 
/*     */   public static List<MemberBean> getMemberListByDB(Map map)
/*     */   {
/* 197 */     System.out.println(map.get("search_name") + "----" + map.get("search_value"));
/* 198 */     return DBManager.queryFList("getMemberListByDB", map);
/*     */   }
/*     */ 
/*     */   public static MemberBean getMemberBeanByID(String id)
/*     */   {
/* 208 */     return (MemberBean)DBManager.queryFObj("getMemberBeanByID", id);
/*     */   }
/*     */ 
/*     */   public static boolean insertMember(MemberBean mb)
/*     */   {
/* 219 */     if (DBManager.insert("insertMember", mb))
/*     */     {
/* 221 */       return true;
/*     */     }
/*     */ 
/* 225 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateMember(MemberBean mb, SettingLogsBean stl)
/*     */   {
/* 237 */     if (DBManager.update("updateMember", mb))
/*     */     {
/* 239 */       PublicTableDAO.insertSettingLogs("修改", "会员主表", mb.getMe_id(), stl);
/* 240 */       return true;
/*     */     }
/*     */ 
/* 244 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateMemberBrowser(MemberBean mb)
/*     */   {
/* 256 */     return DBManager.update("updateMember_browser", mb);
/*     */   }
/*     */ 
/*     */   public static boolean checkMemberByIDS(Map<String, String> mp, SettingLogsBean stl)
/*     */   {
/* 267 */     if (DBManager.update("checkMemberByIDS", mp))
/*     */     {
/* 269 */       PublicTableDAO.insertSettingLogs("修改", "会员主表", (String)mp.get("me_ids"), stl);
/* 270 */       return true;
/*     */     }
/*     */ 
/* 274 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteMember(Map<String, String> mp, SettingLogsBean stl)
/*     */   {
/* 286 */     if (DBManager.delete("deleteMember", mp))
/*     */     {
/* 288 */       PublicTableDAO.insertSettingLogs("删除", "会员主表", (String)mp.get("me_ids"), stl);
/* 289 */       return true;
/*     */     }
/*     */ 
/* 293 */     return false;
/*     */   }
/*     */ 
/*     */   public static List<MemberRegisterBean> getAllMemberRegisterList()
/*     */   {
/* 304 */     return DBManager.queryFList("getAllMemberRegisterList", "");
/*     */   }
/*     */ 
/*     */   public static MemberRegisterBean getMemberRegisterByID(String id)
/*     */   {
/* 314 */     return (MemberRegisterBean)DBManager.queryFObj("getMemberRegisterByID", id);
/*     */   }
/*     */ 
/*     */   public static String getMemberCnt(Map<String, String> mp)
/*     */   {
/* 323 */     return (String)DBManager.queryFObj("getMemberCount", mp);
/*     */   }
/*     */ 
/*     */   public static boolean insertMemberRegister(MemberRegisterBean mrb)
/*     */   {
/* 334 */     if (DBManager.insert("insertMemberRegister", mrb))
/*     */     {
/* 336 */       return true;
/*     */     }
/*     */ 
/* 340 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateMemberRegister(MemberRegisterBean mrb, SettingLogsBean stl)
/*     */   {
/* 352 */     if (DBManager.update("updateMemberRegister", mrb))
/*     */     {
/* 354 */       PublicTableDAO.insertSettingLogs("修改", "会员帐号", mrb.getRegister_id(), stl);
/* 355 */       return true;
/*     */     }
/*     */ 
/* 359 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateMemberPassword(String register_id, String password)
/*     */   {
/* 371 */     Map m = new HashMap();
/* 372 */     m.put("register_id", register_id);
/* 373 */     m.put("me_password", password);
/* 374 */     return DBManager.update("update_member_password", m);
/*     */   }
/*     */ 
/*     */   public static boolean deleteMemberRegister(Map<String, String> mp, SettingLogsBean stl)
/*     */   {
/* 385 */     if (DBManager.delete("deleteMemberRegister", mp))
/*     */     {
/* 387 */       PublicTableDAO.insertSettingLogs("删除", "会员帐号", (String)mp.get("register_ids"), stl);
/* 388 */       return true;
/*     */     }
/*     */ 
/* 392 */     return false;
/*     */   }
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/* 398 */     updateMemberPassword("0", "111");
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.dao.member.MemberDAO
 * JD-Core Version:    0.6.2
 */