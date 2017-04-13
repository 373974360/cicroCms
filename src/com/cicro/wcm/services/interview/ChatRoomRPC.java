/*     */ package com.cicro.wcm.services.interview;
/*     */ 
/*     */ import com.cicro.wcm.bean.interview.ChatBean;
/*     */ import com.cicro.wcm.bean.interview.GuestBean;
/*     */ import com.cicro.wcm.bean.interview.SubjectResouse;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import javax.servlet.http.Cookie;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ 
/*     */ public class ChatRoomRPC
/*     */ {
/*     */   public static String guestLoginSet(String sub_id, HttpServletRequest request)
/*     */   {
/*  33 */     String user_num = "";
/*  34 */     Cookie[] cookies = request.getCookies();
/*     */ 
/*  36 */     if (cookies != null)
/*     */     {
/*  38 */       for (int i = 0; i < cookies.length; i++)
/*     */       {
/*  40 */         Cookie c = cookies[i];
/*  41 */         if (c.getName().equalsIgnoreCase("user_num"))
/*     */         {
/*  43 */           user_num = c.getValue();
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/*  48 */     if ((user_num == null) || ("".equals(user_num)))
/*     */     {
/*  50 */       user_num = getUserMark(sub_id) + "";
/*  51 */       Cookie newcookie = new Cookie("user_num", user_num);
/*  52 */       newcookie.setMaxAge(10800);
/*     */     }
/*     */ 
/*  55 */     return user_num;
/*     */   }
/*     */ 
/*     */   public static boolean setOtherParam(String sub_id, String param_name, String param_value)
/*     */   {
/*  65 */     return ChatRoomServices.setOtherParam(sub_id, param_name, param_value);
/*     */   }
/*     */ 
/*     */   public static Map getOtherParamSet(String sub_id)
/*     */   {
/*  75 */     return ChatRoomServices.getOtherMap(sub_id);
/*     */   }
/*     */ 
/*     */   public static void setProhibitType(String sub_id, String pro_type)
/*     */   {
/*  86 */     ChatRoomServices.setProhibitType(sub_id, pro_type);
/*     */   }
/*     */ 
/*     */   public static String getProhibitType(String sub_id)
/*     */   {
/*  97 */     return ChatRoomServices.getProhibitType(sub_id);
/*     */   }
/*     */ 
/*     */   public static List getProhibitUsers(String sub_id)
/*     */   {
/* 107 */     return ChatRoomServices.getProhibitUsers(sub_id);
/*     */   }
/*     */ 
/*     */   public static void setProhibitUsers(String sub_id, String user_num)
/*     */   {
/* 118 */     ChatRoomServices.setProhibitUsers(sub_id, user_num);
/*     */   }
/*     */ 
/*     */   public static void recordUserInfo(String sub_id, GuestBean gb, HttpServletRequest request)
/*     */   {
/* 130 */     gb.setIp(request.getRemoteAddr());
/* 131 */     ChatRoomServices.recordUserInfo(sub_id, gb);
/*     */   }
/*     */ 
/*     */   public static void unRecordUserInfo(String sub_id, String user_name)
/*     */   {
/* 142 */     ChatRoomServices.unRecordUserInfo(sub_id, user_name);
/*     */   }
/*     */ 
/*     */   public static Map getLiveUserInfo(String sub_id)
/*     */   {
/* 152 */     return ChatRoomServices.getLiveUserInfo(sub_id);
/*     */   }
/*     */ 
/*     */   public static int getUserMark(String sub_id)
/*     */   {
/* 162 */     return ChatRoomServices.getUserMark(sub_id);
/*     */   }
/*     */ 
/*     */   public static HashMap getAllMessageListByAdmin(String sub_id, int pic_start_num, int text_start_num, int is_p_audit, int is_t_audit, int is_show_text)
/*     */   {
/* 177 */     HashMap h = new HashMap();
/* 178 */     HashMap temp_p = ChatRoomServices.getAllPicTextInfoListByAdmin(sub_id, pic_start_num, is_p_audit);
/*     */ 
/* 180 */     if (temp_p != null)
/*     */     {
/* 182 */       h.putAll(temp_p);
/* 183 */       temp_p.clear();
/*     */     }
/*     */ 
/* 186 */     if (is_show_text == 1)
/*     */     {
/* 188 */       temp_p = ChatRoomServices.getAllTextInfoListByAdmin(sub_id, text_start_num, is_t_audit);
/* 189 */       if (temp_p != null) {
/* 190 */         h.putAll(temp_p);
/*     */       }
/*     */     }
/*     */ 
/* 194 */     h.put("other_set", ChatRoomServices.getOtherMap(sub_id));
/* 195 */     return h;
/*     */   }
/*     */ 
/*     */   public static HashMap getAllPicTextInfoListByAdmin(String sub_id, int start_num, int is_p_audit)
/*     */   {
/* 207 */     return ChatRoomServices.getAllPicTextInfoListByAdmin(sub_id, start_num, is_p_audit);
/*     */   }
/*     */ 
/*     */   public static HashMap getAllMessageList(String sub_id, int pic_start_num, int text_start_num,boolean is_sync)
/*     */   {
				if(is_sync)
				{
					ChatRoomServices.reloadChat(sub_id);
				}
/* 219 */     HashMap h = new HashMap();
/* 220 */     HashMap temp_p = ChatRoomServices.getAllPicTextInfoList(sub_id, pic_start_num);
/*     */ 
/* 222 */     if (temp_p != null)
/*     */     {
/* 224 */       h.putAll(temp_p);
/* 225 */       temp_p.clear();
/*     */     }
/*     */ 
/* 228 */     temp_p = ChatRoomServices.getTextInfoList(sub_id, text_start_num);
/* 229 */     if (temp_p != null) {
/* 230 */       h.putAll(temp_p);
/*     */     }
/*     */ 
/* 233 */     h.put("other_set", ChatRoomServices.getOtherMap(sub_id));
/* 234 */     return h;
/*     */   }
/*     */ 
/*     */   public static void setPicTextInfo(String sub_id, ChatBean cb, int is_p_audit, HttpServletRequest request)
/*     */   {
/* 244 */     cb.setIp(request.getRemoteAddr());
/* 245 */     ChatRoomServices.setPicTextInfo(sub_id, cb, is_p_audit, false);
/*     */   }
/*     */ 
/*     */   public static void setTextInfo(String sub_id, ChatBean cb, int is_t_audit, HttpServletRequest request)
/*     */   {
/* 257 */     cb.setIp(request.getRemoteAddr());
/* 258 */     ChatRoomServices.setTextInfo(sub_id, cb, is_t_audit, false);
/*     */   }
/*     */ 
/*     */   public static void isPassChat(ChatBean cb)
/*     */   {
/* 268 */     ChatRoomServices.isPassChat(cb);
/*     */   }
/*     */ 
/*     */   public static boolean updateChat(ChatBean cb, int is_p_audit, int is_t_audit, boolean isUpdateCatch)
/*     */   {
/* 281 */     return ChatRoomServices.updateChat(cb, is_p_audit, is_t_audit, isUpdateCatch);
/*     */   }
/*     */ 
/*     */   public static boolean deleteChat(ChatBean cb, int is_p_audit, int is_t_audit, boolean isUpdateCatch)
/*     */   {
/* 294 */     return ChatRoomServices.deleteChat(cb, is_p_audit, is_t_audit, isUpdateCatch);
/*     */   }
/*     */ 
/*     */   public static boolean insertLivePic(SubjectResouse sr)
/*     */   {
/* 304 */     return ChatRoomServices.insertLivePic(sr);
/*     */   }
/*     */ 
/*     */   public static boolean insertLiveVideo(SubjectResouse sr)
/*     */   {
/* 315 */     return ChatRoomServices.insertLiveVideo(sr);
/*     */   }
/*     */ 
/*     */   public static boolean deleteLivePic(String sub_id, int id)
/*     */   {
/* 325 */     return ChatRoomServices.deleteLivePic(sub_id, id);
/*     */   }
/*     */ 
/*     */   public static List getLivePicList(String sub_id, int index_num)
/*     */   {
/* 336 */     return ChatRoomServices.getLivePicList(sub_id, index_num);
/*     */   }
/*     */ 
/*     */   public static List getChatListByDB(String sub_id)
/*     */   {
/* 347 */     return ChatRoomServices.getChatListByDB(sub_id);
/*     */   }
/*     */ 
/*     */   public static String getHistoryVideo(String sub_id)
/*     */   {
/* 357 */     return ChatRoomServices.getHistoryVideo(sub_id);
/*     */   }
/*     */ 
/*     */   public static List getResouseList(String sub_id, String affix_type, String affix_status)
/*     */   {
/* 369 */     return ChatRoomServices.getResouseList(sub_id, affix_type, affix_status);
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.interview.ChatRoomRPC
 * JD-Core Version:    0.6.2
 */