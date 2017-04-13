/*     */ package com.cicro.wcm.dao.interview;
/*     */ 
/*     */ import com.cicro.wcm.bean.interview.ChatBean;
/*     */ import com.cicro.wcm.bean.interview.SubjectResouse;
/*     */ import com.cicro.wcm.dao.PublicTableDAO;
/*     */ import com.cicro.wcm.db.DBManager;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class ChatRoomDAO
/*     */ {
/*     */   public static boolean setCountUser(String sub_id)
/*     */   {
/*  26 */     return DBManager.update("setCountUser", sub_id);
/*     */   }
/*     */ 
/*     */   public static boolean insertChat(ChatBean cb)
/*     */   {
/*  36 */     cb.setId(PublicTableDAO.getIDByTableName(PublicTableDAO.INTERVIEW_CHAT_TABLE_NAME));
/*  37 */     return DBManager.insert("cp_subChat_insert", cb);
/*     */   }
/*     */ 
/*     */   public static boolean updateChat(ChatBean cb)
/*     */   {
/*  47 */     return DBManager.update("cp_subChat_update", cb);
/*     */   }
/*     */ 
/*     */   public static boolean deleteChat(String chat_id)
/*     */   {
/*  57 */     return DBManager.delete("cp_subChat_delete", chat_id);
/*     */   }
/*     */ 
/*     */   public static List getLiveStatusSubjectList(String id)
/*     */   {
/*  67 */     return DBManager.queryFList("getLiveStatusSubject", id);
/*     */   }
/*     */ 
/*     */   public static List getChatListBySubID(String sub_id)
/*     */   {
/*  78 */     return DBManager.queryFList("getChatListBySubID", sub_id);
/*     */   }
/*     */ 
/*     */   public static boolean updateChatAuditStatus(String chat_id)
/*     */   {
/*  88 */     return DBManager.update("cp_subChat_auditstatus", chat_id);
/*     */   }
/*     */ 
/*     */   public static boolean insertLiveAffix(SubjectResouse sr)
/*     */   {
/*  98 */     int id = PublicTableDAO.getIDByTableName(PublicTableDAO.INTERVIEW_RESOUSE_TABLE_NAME);
/*  99 */     sr.setId(id);
/* 100 */     return DBManager.update("insertLiveAffix", sr);
/*     */   }
/*     */ 
/*     */   public static boolean insertLiveAffixForSingle(SubjectResouse sr)
/*     */   {
/* 110 */     sr.setId(PublicTableDAO.getIDByTableName(PublicTableDAO.INTERVIEW_RESOUSE_TABLE_NAME));
/* 111 */     return DBManager.insert("insertLiveAffixForSingle", sr);
/*     */   }
/*     */ 
/*     */   public static String getResouseInfoByCon(Map<String, String> m)
/*     */   {
/* 116 */     return DBManager.getString("getResouseInfoByCon", m);
/*     */   }
/*     */ 
/*     */   public static boolean updateLiveAffixForSingle(SubjectResouse sr)
/*     */   {
/* 121 */     return DBManager.update("updateLiveAffixForSingle", sr);
/*     */   }
/*     */ 
/*     */   public static boolean deleteLivePic(int id)
/*     */   {
/* 131 */     return DBManager.delete("deleteLivePic", Integer.valueOf(id));
/*     */   }
/*     */ 
/*     */   public static List getResouseBySubID(String sub_id)
/*     */   {
/* 142 */     return DBManager.queryFList("getResouseBySubID", sub_id);
/*     */   }
/*     */ 
/*     */   public static List<SubjectResouse> getResouseList(Map m)
/*     */   {
/* 152 */     return DBManager.queryFList("getResouseList", m);
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.dao.interview.ChatRoomDAO
 * JD-Core Version:    0.6.2
 */