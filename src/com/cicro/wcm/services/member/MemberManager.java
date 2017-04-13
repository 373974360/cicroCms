/*     */ package com.cicro.wcm.services.member;
/*     */ 
/*     */ import com.cicro.util.CryptoTools;
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.bean.member.MemberBean;
/*     */ import com.cicro.wcm.bean.member.MemberRegisterBean;
/*     */ import com.cicro.wcm.catchs.ISyncCatch;
/*     */ import com.cicro.wcm.catchs.SyncCatchHandl;
/*     */ import com.cicro.wcm.dao.PublicTableDAO;
/*     */ import com.cicro.wcm.dao.member.MemberDAO;
/*     */ import java.io.PrintStream;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ 
/*     */ public class MemberManager
/*     */   implements ISyncCatch
/*     */ {
/*  19 */   private static Map<String, MemberBean> member_map = new HashMap();
/*  20 */   private static Map<String, MemberRegisterBean> mem_reg_map = new HashMap();
/*     */ 
/*     */   static {
/*  23 */     reloadCatchHandl();
/*     */   }
/*     */ 
/*     */   public void reloadCatch()
/*     */   {
/*  28 */     reloadCatchHandl();
/*     */   }
/*     */ 
/*     */   public static void reloadCatchHandl()
/*     */   {
/*  33 */     member_map.clear();
/*  34 */     List ltMember = MemberDAO.getAllMemberList();
/*  35 */     if ((ltMember != null) && (ltMember.size() > 0))
/*     */     {
/*  37 */       for (int i = 0; i < ltMember.size(); i++)
/*     */       {
/*  39 */         member_map.put(((MemberBean)ltMember.get(i)).getMe_id(), (MemberBean)ltMember.get(i));
/*     */       }
/*     */     }
/*     */ 
/*  43 */     mem_reg_map.clear();
/*  44 */     List ltReg = MemberDAO.getAllMemberRegisterList();
/*  45 */     if ((ltReg != null) && (ltReg.size() > 0))
/*     */     {
/*  47 */       for (int i = 0; i < ltReg.size(); i++)
/*     */       {
/*  49 */         mem_reg_map.put(((MemberRegisterBean)ltReg.get(i)).getMe_account(), (MemberRegisterBean)ltReg.get(i));
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void reloadMemberMap()
/*     */   {
/*  59 */     SyncCatchHandl.reladCatchForRMI("com.cicro.wcm.services.member.MemberManager");
/*     */   }
/*     */ 
/*     */   public static void reloadRegisterMap()
/*     */   {
/*  64 */     SyncCatchHandl.reladCatchForRMI("com.cicro.wcm.services.member.MemberManager");
/*     */   }
/*     */ 
/*     */   public static String memberLogin(String me_account, String me_password)
/*     */   {
/*  72 */     CryptoTools ct = new CryptoTools();
/*  73 */     me_password = ct.encode(me_password);
/*  74 */     if (mem_reg_map.containsKey(me_account))
/*     */     {
/*  76 */       MemberRegisterBean mrb = (MemberRegisterBean)mem_reg_map.get(me_account);
/*  77 */       if (me_password.equals(mrb.getMe_password()))
/*     */       {
/*  79 */         System.out.println(member_map.size());
/*  80 */         MemberBean mb = (MemberBean)member_map.get(mrb.getMe_id());
/*  81 */         if (mb != null)
/*     */         {
/*  83 */           if (mb.getMe_status() == 1)
/*     */           {
/*  85 */             return "true";
/*     */           }
/*     */ 
/*  88 */           return mb.getMe_status();
/*     */         }
/*     */ 
/*  91 */         return "false";
/*     */       }
/*  93 */       return "false";
/*     */     }
/*  95 */     return "false";
/*     */   }
/*     */ 
/*     */   public static boolean passWordCheck(MemberBean cb, String password)
/*     */   {
/* 106 */     MemberRegisterBean mb = getMemberRegisterBeanByID(cb.getMe_id());
/* 107 */     return password.equals(mb.getMe_password());
/*     */   }
/*     */ 
/*     */   public static MemberBean getMemberBenaByAccount(String me_account)
/*     */   {
/* 115 */     MemberRegisterBean mrb = (MemberRegisterBean)mem_reg_map.get(me_account);
/* 116 */     MemberBean mb = (MemberBean)member_map.get(mrb.getMe_id());
/* 117 */     if (mb != null) {
/* 118 */       return mb;
/*     */     }
/* 120 */     return null;
/*     */   }
/*     */ 
/*     */   public static List<MemberBean> getMemberList(Map mp)
/*     */   {
/* 131 */     return MemberDAO.getMemberListByDB(mp);
/*     */   }
/*     */ 
/*     */   public static String getMemberCnt(Map<String, String> mp)
/*     */   {
/* 141 */     return MemberDAO.getMemberCnt(mp);
/*     */   }
/*     */ 
/*     */   public static MemberBean getMemberBeanByID(String id)
/*     */   {
/* 151 */     return MemberDAO.getMemberBeanByID(id);
/*     */   }
/*     */ 
/*     */   private static boolean insertMember(MemberBean mb)
/*     */   {
/* 162 */     if (MemberDAO.insertMember(mb))
/*     */     {
/* 164 */       reloadMemberMap();
/* 165 */       return true;
/*     */     }
/*     */ 
/* 169 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateMemberInfo(MemberBean mb, MemberRegisterBean mrb, SettingLogsBean stl)
/*     */   {
/* 182 */     CryptoTools ct = new CryptoTools();
/* 183 */     mrb.setMe_password(ct.encode(mrb.getMe_password()));
/* 184 */     boolean flg = updateMemberRegister(mrb, stl);
/* 185 */     if (flg)
/*     */     {
/* 187 */       flg = updateMember(mb, stl);
/*     */     }
/* 189 */     return flg;
/*     */   }
/*     */ 
/*     */   public static boolean updateMember(MemberBean mb, SettingLogsBean stl)
/*     */   {
/* 200 */     if (MemberDAO.updateMember(mb, stl))
/*     */     {
/* 202 */       reloadMemberMap();
/* 203 */       return true;
/*     */     }
/*     */ 
/* 207 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateMemberBrowser(MemberBean mb)
/*     */   {
/* 219 */     if (MemberDAO.updateMemberBrowser(mb))
/*     */     {
/* 221 */       reloadMemberMap();
/* 222 */       return true;
/*     */     }
/*     */ 
/* 226 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean checkMemberByIDS(Map<String, String> mp, SettingLogsBean stl)
/*     */   {
/* 237 */     if (MemberDAO.checkMemberByIDS(mp, stl))
/*     */     {
/* 239 */       reloadMemberMap();
/* 240 */       return true;
/*     */     }
/*     */ 
/* 244 */     return false;
/*     */   }
/*     */ 
/*     */   public static MemberRegisterBean getMemberRegisterBeanByID(String id)
/*     */   {
/* 255 */     MemberRegisterBean mrb = MemberDAO.getMemberRegisterByID(id);
/* 256 */     CryptoTools ct = new CryptoTools();
/* 257 */     mrb.setMe_password(ct.decode(mrb.getMe_password()));
/* 258 */     return mrb;
/*     */   }
/*     */ 
/*     */   public static Map<String, String> getMemberAccountList(List<MemberBean> lt)
/*     */   {
/* 268 */     Map ret = new HashMap();
/* 269 */     if ((lt != null) && (lt.size() > 0))
/*     */     {
/* 271 */       for (int i = 0; i < lt.size(); i++)
/*     */       {
/* 273 */         String key = ((MemberBean)lt.get(i)).getMe_id();
/* 274 */         ret.put(key, getMemberAccount(key));
/*     */       }
/*     */     }
/* 277 */     return ret;
/*     */   }
/*     */ 
/*     */   public static String getMemberAccount(String me_id)
/*     */   {
/* 287 */     Set set = mem_reg_map.keySet();
/* 288 */     for (String i : set) {
/* 289 */       MemberRegisterBean mrb = (MemberRegisterBean)mem_reg_map.get(i);
/* 290 */       if (me_id.equals(mrb.getMe_id()))
/* 291 */         return mrb.getMe_account();
/*     */     }
/* 293 */     return "";
/*     */   }
/*     */ 
/*     */   public static boolean isAccountExisted(String account)
/*     */   {
/* 303 */     if (mem_reg_map.keySet().contains(account))
/*     */     {
/* 305 */       return true;
/*     */     }
/*     */ 
/* 309 */     return false;
/*     */   }
/*     */ 
/*     */   private static boolean insertMemberRegister(MemberRegisterBean mrb)
/*     */   {
/* 321 */     CryptoTools ct = new CryptoTools();
/* 322 */     mrb.setMe_password(ct.encode(mrb.getMe_password()));
/* 323 */     if ((!isAccountExisted(mrb.getMe_account())) && (MemberDAO.insertMemberRegister(mrb)))
/*     */     {
/* 325 */       reloadRegisterMap();
/* 326 */       return true;
/*     */     }
/*     */ 
/* 330 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean RegisterMember(MemberBean mb, MemberRegisterBean mrb)
/*     */   {
/* 343 */     String me_id = PublicTableDAO.getIDByTableName(PublicTableDAO.MEMBER_TABLE_NAME);
/* 344 */     String reg_id = PublicTableDAO.getIDByTableName(PublicTableDAO.MEMBER_REGISTER_TABLE_NAME);
/* 345 */     mb.setMe_id(me_id);
/* 346 */     mrb.setMe_id(me_id);
/* 347 */     mrb.setRegister_id(reg_id);
/*     */ 
/* 349 */     boolean flg = insertMemberRegister(mrb);
/* 350 */     if (flg)
/*     */     {
/* 352 */       flg = insertMember(mb);
/*     */     }
/* 354 */     return flg;
/*     */   }
/*     */ 
/*     */   public static boolean updateMemberPassword(String me_id, String password)
/*     */   {
/* 365 */     MemberRegisterBean mrb = getMemberRegisterBeanByID(me_id);
/* 366 */     CryptoTools ct = new CryptoTools();
/* 367 */     password = ct.encode(password);
/* 368 */     if (MemberDAO.updateMemberPassword(mrb.getRegister_id(), password))
/*     */     {
/* 370 */       reloadRegisterMap();
/* 371 */       return true;
/*     */     }
/* 373 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateMemberRegister(MemberRegisterBean mrb, SettingLogsBean stl)
/*     */   {
/* 384 */     if (MemberDAO.updateMemberRegister(mrb, stl))
/*     */     {
/* 386 */       reloadRegisterMap();
/* 387 */       return true;
/*     */     }
/*     */ 
/* 391 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean deleteMember(String me_ids, SettingLogsBean stl)
/*     */   {
/* 403 */     Map mp = new HashMap();
/* 404 */     mp.put("ids", me_ids);
/* 405 */     mp.put("me_status", "-2");
/* 406 */     if (MemberDAO.checkMemberByIDS(mp, stl))
/*     */     {
/* 408 */       reloadRegisterMap();
/* 409 */       reloadMemberMap();
/* 410 */       return true;
/*     */     }
/*     */ 
/* 414 */     return false;
/*     */   }
/*     */ 
/*     */   public static void main(String[] arg)
/*     */   {
/* 430 */     System.out.println(getMemberBenaByAccount("qier121"));
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.member.MemberManager
 * JD-Core Version:    0.6.2
 */