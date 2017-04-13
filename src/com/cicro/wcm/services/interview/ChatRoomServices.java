/*      */ package com.cicro.wcm.services.interview;
/*      */ 
/*      */ import com.cicro.util.DateUtil;
/*      */ import com.cicro.util.MapManager;
/*      */ import com.cicro.wcm.bean.interview.ChatBean;
/*      */ import com.cicro.wcm.bean.interview.GuestBean;
/*      */ import com.cicro.wcm.bean.interview.SubjectResouse;
/*      */ import com.cicro.wcm.bean.system.filterWord.FilterWordBean;
/*      */ import com.cicro.wcm.dao.interview.ChatRoomDAO;
/*      */ import com.cicro.wcm.services.system.filterWord.FilterWordManager;
/*      */ import java.io.PrintStream;
/*      */ import java.util.ArrayList;
/*      */ import java.util.HashMap;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import java.util.Map.Entry;
/*      */ import java.util.Set;
/*      */ import java.util.UUID;
/*      */ 
/*      */ public class ChatRoomServices
/*      */ {
/*   35 */   private static Map<String, List> chat_map = new HashMap();
/*      */ 
/*   43 */   private static Map user_map = new HashMap();
/*      */ 
/*   48 */   private static Map other_map = new HashMap();
/*      */ 
/*   50 */   private static String keyword = "";
/*      */ 
/*   52 */   private static String nick_filter = "";
/*      */ 
/*      */   static
/*      */   {
/*   56 */     reloadChat("");
/*      */ 
/*   58 */     reloadFilterWord();
/*      */   }
/*      */ 
/*      */   public static void reloadFilterWord()
/*      */   {
/*   64 */     List f_l = FilterWordManager.getFilterWordList();
/*   65 */     if ((f_l != null) && (f_l.size() > 0))
/*      */     {
/*   67 */       for (int i = 0; i < f_l.size(); i++)
/*      */       {
/*   69 */         nick_filter = nick_filter + "," + ((FilterWordBean)f_l.get(i)).getPattern();
/*   70 */         keyword = keyword + "," + ((FilterWordBean)f_l.get(i)).getPattern();
/*      */       }
/*   72 */       if (nick_filter.startsWith(","))
/*   73 */         nick_filter = nick_filter.substring(1);
/*   74 */       if (keyword.startsWith(","))
/*   75 */         keyword = keyword.substring(1);
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void reloadChat(String id)
/*      */   {
	chat_map.clear();
	other_map.clear();
/*   87 */     List subjectList = ChatRoomDAO.getLiveStatusSubjectList(id);
/*   88 */     if ((subjectList != null) && (subjectList.size() > 0))
/*      */     {
/*   90 */       for (int i = 0; i < subjectList.size(); i++)
/*      */       {
/*   92 */         Map m = MapManager.mapKeyValueToLow((HashMap)subjectList.get(i));
/*   93 */         String sub_id = (String)m.get("sub_id");
/*   94 */         int is_p_audit = Integer.parseInt(m.get("is_p_audit").toString());
/*   95 */         int is_t_audit = Integer.parseInt(m.get("is_t_audit").toString());
/*      */ 
/*   97 */         reloadChatInfo(sub_id, is_p_audit, is_t_audit);
/*      */ 
/*   99 */         reloadSubjectResouse(sub_id);
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void reloadSubjectResouse(String sub_id)
/*      */   {
/*      */     try
/*      */     {
/*  111 */       List lp = ChatRoomDAO.getResouseBySubID(sub_id);
/*  112 */       if ((lp != null) && (lp.size() > 0))
/*      */       {
/*  114 */         List temp_picList = new ArrayList();
/*  115 */         for (int i = 0; i < lp.size(); i++)
/*      */         {
/*  118 */           if (("live".equals(((SubjectResouse)lp.get(i)).getAffix_status())) && ("pic".equals(((SubjectResouse)lp.get(i)).getAffix_type())))
/*      */           {
/*  120 */             temp_picList.add((SubjectResouse)lp.get(i));
/*      */           }
/*      */           else
/*      */           {
/*  125 */             other_map.put(sub_id + "_" + ((SubjectResouse)lp.get(i)).getAffix_status() + "_" + ((SubjectResouse)lp.get(i)).getAffix_type(), ((SubjectResouse)lp.get(i)).getAffix_path());
/*      */           }
/*      */         }
/*  128 */         if ((temp_picList != null) && (temp_picList.size() > 0))
/*  129 */           chat_map.put(sub_id + "_live_pic", temp_picList);
/*      */       }
/*      */     } catch (Exception e) {
/*  132 */       e.printStackTrace();
/*  133 */       System.out.println("reload live pic error");
/*      */     }
/*      */   }
/*      */ 
/*      */   private static void reloadChatInfo(String sub_id, int is_p_audit, int is_t_audit)
/*      */   {
/*  141 */     List chat_list = ChatRoomDAO.getChatListBySubID(sub_id);
/*  142 */     if ((chat_list != null) && (chat_list.size() > 0))
/*      */     {
/*  144 */       for (int j = 0; j < chat_list.size(); j++)
/*      */       {
/*  146 */         ChatBean cb = (ChatBean)chat_list.get(j);
/*  147 */         if ("pic".equals(cb.getChat_area()))
/*      */         {
/*  149 */           setPicTextInfo(sub_id, cb, is_p_audit, true);
/*      */         }
/*  151 */         if ("text".equals(cb.getChat_area()))
/*      */         {
/*  153 */           setTextInfo(sub_id, cb, is_t_audit, true);
/*      */         }
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void setProhibitType(String sub_id, String pro_type)
/*      */   {
/*  168 */     user_map.put(sub_id + "_prohibit_type", pro_type);
/*      */   }
/*      */ 
/*      */   public static String getProhibitType(String sub_id)
/*      */   {
/*  178 */     if (user_map.containsKey(sub_id + "_prohibit_type"))
/*      */     {
/*  180 */       return (String)user_map.get(sub_id + "_prohibit_type");
/*      */     }
/*      */ 
/*  183 */     return "uname";
/*      */   }
/*      */ 
/*      */   public static List getProhibitUsers(String sub_id)
/*      */   {
/*  194 */     List GuestList = new ArrayList();
/*  195 */     String users = "";
/*  196 */     String ips = "";
/*      */     try
/*      */     {
/*  199 */       if (("ip".equals(getProhibitType(sub_id))) && (user_map.containsKey(sub_id + "_prohibit_ips")))
/*      */       {
/*  202 */         ips = (String)user_map.get(sub_id + "_prohibit_ips");
/*      */ 
/*  204 */         if (user_map.containsKey(sub_id + "_prohibit_users")) {
/*  205 */           users = (String)user_map.get(sub_id + "_prohibit_users");
/*      */         }
/*      */ 
/*  208 */         if (user_map.containsKey(sub_id + "_live_user"))
/*      */         {
/*  210 */           Map live_user = (HashMap)user_map.get(sub_id + "_live_user");
/*      */ 
/*  212 */           Iterator iter = live_user.entrySet().iterator();
/*  213 */           while (iter.hasNext()) {
/*  214 */             Entry entry = (Entry)iter.next();
/*  215 */             String key = (String)entry.getKey();
/*      */ 
/*  217 */             GuestBean gb = (GuestBean)entry.getValue();
/*      */ 
/*  219 */             if ((ips.contains(gb.getIp() + ",")) || (users.contains(key))) {
/*  220 */               GuestList.add(gb);
/*      */             }
/*      */ 
/*      */           }
/*      */ 
/*      */         }
/*      */ 
/*      */       }
/*  228 */       else if (user_map.containsKey(sub_id + "_prohibit_users"))
/*      */       {
/*  230 */         users = (String)user_map.get(sub_id + "_prohibit_users");
/*      */ 
/*  232 */         if ((users != null) && (!"".equals(users)))
/*      */         {
/*  234 */           if (user_map.containsKey(sub_id + "_live_user"))
/*      */           {
/*  236 */             Map live_user = (HashMap)user_map.get(sub_id + "_live_user");
/*  237 */             String[] tempA = users.split(",");
/*      */ 
/*  239 */             for (int i = 0; i < tempA.length; i++)
/*      */             {
/*  241 */               GuestList.add((GuestBean)live_user.get(tempA[i]));
/*      */             }
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*      */     catch (Exception e)
/*      */     {
/*  249 */       e.printStackTrace();
/*      */     }
/*      */ 
/*  252 */     return GuestList;
/*      */   }
/*      */ 
/*      */   public static void setProhibitUsers(String sub_id, String user_num)
/*      */   {
/*  264 */     String str = "";
/*      */     try
/*      */     {
/*  267 */       if ("ip".equals(getProhibitType(sub_id)))
/*      */       {
/*  270 */         Map live_user = (HashMap)user_map.get(sub_id + "_live_user");
/*  271 */         if (live_user != null)
/*      */         {
/*  273 */           if (user_map.containsKey(sub_id + "_prohibit_ips"))
/*      */           {
/*  275 */             str = (String)user_map.get(sub_id + "_prohibit_ips");
/*      */           }
/*  277 */           if (!str.contains(((GuestBean)live_user.get(user_num)).getIp() + ","))
/*      */           {
/*  279 */             str = str + ((GuestBean)live_user.get(user_num)).getIp() + ",";
/*  280 */             user_map.put(sub_id + "_prohibit_ips", str);
/*      */           }
/*      */         }
/*      */       }
/*      */       else
/*      */       {
/*  286 */         if (user_map.containsKey(sub_id + "_prohibit_users"))
/*      */         {
/*  288 */           str = (String)user_map.get(sub_id + "_prohibit_users");
/*      */         }
/*  290 */         if (!str.contains(user_num + ","))
/*      */         {
/*  292 */           str = str + user_num + ",";
/*  293 */           user_map.put(sub_id + "_prohibit_users", str);
/*      */         }
/*      */       }
/*      */     } catch (Exception e) {
/*  297 */       e.printStackTrace();
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void recordUserInfo(String sub_id, GuestBean gb)
/*      */   {
/*  311 */     Map live_user = new HashMap();
/*  312 */     if (user_map.containsKey(sub_id + "_live_user"))
/*      */     {
/*  314 */       live_user = (HashMap)user_map.get(sub_id + "_live_user");
/*  315 */       live_user.put(gb.getUser_num(), gb);
/*      */     }
/*      */     else
/*      */     {
/*  319 */       live_user.put(gb.getUser_num(), gb);
/*  320 */       user_map.put(sub_id + "_live_user", live_user);
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void unRecordUserInfo(String sub_id, String user_name)
/*      */   {
/*  333 */     Map live_user = (HashMap)user_map.get(sub_id + "_live_user");
/*  334 */     live_user.remove(user_name);
/*      */   }
/*      */ 
/*      */   public static Map getLiveUserInfo(String sub_id)
/*      */   {
/*  345 */     return (HashMap)user_map.get(sub_id + "_live_user");
/*      */   }
/*      */ 
/*      */   public static int getUserMark(String sub_id)
/*      */   {
/*  355 */     int count = 100;
/*  356 */     if (user_map.containsKey(sub_id + "_user_count"))
/*      */     {
/*  358 */       count = ((Integer)user_map.get(sub_id + "_user_count")).intValue() + 1;
/*      */     }
/*      */     else
/*  361 */       count = 101;
/*  362 */     user_map.put(sub_id + "_user_count", Integer.valueOf(count));
/*      */ 
/*  364 */     ChatRoomDAO.setCountUser(sub_id);
/*  365 */     return count;
/*      */   }
/*      */ 
/*      */   public static HashMap getAllPicTextInfoListByAdmin(String sub_id, int start_num, int is_p_audit)
/*      */   {
/*      */     try
/*      */     {
/*  380 */       List tL = new ArrayList();
/*      */ 
/*  382 */       if ((is_p_audit == 1) && (chat_map.containsKey(sub_id + "_apl")))
/*      */       {
/*  384 */         tL = (List)chat_map.get(sub_id + "_apl");
/*      */       }
/*  386 */       if ((is_p_audit == 0) && (chat_map.containsKey(sub_id + "_cpl")))
/*      */       {
/*  388 */         tL = (List)chat_map.get(sub_id + "_cpl");
/*      */       }
/*  390 */       return setChatListToMap(tL, "pic", start_num);
/*      */     } catch (Exception e) {
/*  392 */       e.printStackTrace();
/*  393 */     }return null;
/*      */   }
/*      */ 
/*      */   public static HashMap getTextInfoList(String sub_id, int start_num)
/*      */   {
/*      */     try
/*      */     {
/*  407 */       List tL = new ArrayList();
/*  408 */       if (chat_map.containsKey(sub_id + "_ctl"))
/*      */       {
/*  410 */         tL = (List)chat_map.get(sub_id + "_ctl");
/*      */       }
/*      */ 
/*  419 */       return setChatListToMap(tL, "text", start_num);
/*      */     } catch (Exception e) {
/*  421 */       e.printStackTrace();
/*  422 */     }return null;
/*      */   }
/*      */ 
/*      */   public static HashMap getAllPicTextInfoList(String sub_id, int start_num)
/*      */   {
/*      */     try
/*      */     {
/*  438 */       List tL = new ArrayList();
/*  439 */       if (chat_map.containsKey(sub_id + "_cpl"))
/*      */       {
/*  441 */         tL = (List)chat_map.get(sub_id + "_cpl");
/*      */       }
/*  443 */       return setChatListToMap(tL, "pic", start_num);
/*      */     } catch (Exception e) {
/*  445 */       e.printStackTrace();
/*  446 */     }return null;
/*      */   }
/*      */ 
/*      */   public static HashMap getAllTextInfoListByAdmin(String sub_id, int start_num, int is_t_audit)
/*      */   {
/*      */     try
/*      */     {
/*  462 */       List tL = new ArrayList();
/*      */ 
/*  464 */       if ((is_t_audit == 1) && (chat_map.containsKey(sub_id + "_atl")))
/*      */       {
/*  466 */         tL = (List)chat_map.get(sub_id + "_atl");
/*      */       }
/*  468 */       if ((is_t_audit == 0) && (chat_map.containsKey(sub_id + "_ctl")))
/*      */       {
/*  470 */         tL = (List)chat_map.get(sub_id + "_ctl");
/*      */       }
/*  472 */       return setChatListToMap(tL, "text", start_num);
/*      */     } catch (Exception e) {
/*  474 */       e.printStackTrace();
/*  475 */     }return null;
/*      */   }
/*      */ 
/*      */   public static HashMap setChatListToMap(List tL, String keyName, int start_num)
/*      */   {
/*  483 */     HashMap rMap = new HashMap();
/*      */     try
/*      */     {
/*  486 */       if ((tL != null) && (tL.size() > 0))
/*      */       {
/*  488 */         rMap.put(keyName + "_mList", tL.subList(start_num, tL.size()));
/*  489 */         rMap.put(keyName + "_max_num", Integer.valueOf(tL.size()));
/*  490 */         return rMap;
/*      */       }
/*      */ 
/*  493 */       return null;
/*      */     } catch (Exception e) {
/*  495 */       e.printStackTrace();
/*  496 */     }return null;
/*      */   }
/*      */ 
/*      */   public static synchronized void setPicTextInfo(String sub_id, ChatBean cb, int is_p_audit, boolean is_db)
/*      */   {
/*  511 */     if (!is_db)
/*      */     {
/*  513 */       cb.setSub_id(sub_id);
/*  514 */       cb.setChat_id(UUID.randomUUID().toString());
/*  515 */       cb.setPut_time(DateUtil.getCurrentDateTime());
/*      */     }
/*  517 */     if (is_p_audit == 1)
/*      */     {
/*  519 */       if (chat_map.containsKey(sub_id + "_apl"))
/*      */       {
/*  522 */         cb.setIndex_num(((List)chat_map.get(sub_id + "_apl")).size());
/*  523 */         ((List)chat_map.get(sub_id + "_apl")).add(cb);
/*      */       }
/*      */       else
/*      */       {
/*  527 */         List l = new ArrayList();
/*      */ 
/*  529 */         cb.setId(0);
/*  530 */         l.add(cb);
/*  531 */         chat_map.put(sub_id + "_apl", l);
/*      */       }
/*      */     }
/*  534 */     if ((is_p_audit == 0) || (cb.getAudit_status() == 1))
/*      */     {
/*  536 */       if (chat_map.containsKey(sub_id + "_cpl"))
/*      */       {
/*  538 */         cb.setIndex_num(((List)chat_map.get(sub_id + "_cpl")).size());
/*  539 */         ((List)chat_map.get(sub_id + "_cpl")).add(cb);
/*      */       }
/*      */       else
/*      */       {
/*  543 */         List l = new ArrayList();
/*  544 */         cb.setIndex_num(0);
/*  545 */         l.add(cb);
/*  546 */         chat_map.put(sub_id + "_cpl", l);
/*      */       }
/*      */     }
/*      */ 
/*  550 */     if (!is_db)
/*  551 */       ChatRoomDAO.insertChat(cb);
/*      */   }
/*      */ 
/*      */   public static boolean updateChat(ChatBean cb, int is_p_audit, int is_t_audit, boolean isUpdateCatch)
/*      */   {
/*  564 */     if ("pic".equals(cb.getChat_area()))
/*      */     {
/*  566 */       return updateChatHandl(cb, is_p_audit, "p", isUpdateCatch);
/*      */     }
/*      */ 
/*  570 */     return updateChatHandl(cb, is_t_audit, "t", isUpdateCatch);
/*      */   }
/*      */ 
/*      */   public static boolean updateChatHandl(ChatBean cb, int is_audit, String list_type, boolean isUpdateCatch)
/*      */   {
/*  583 */     ChatBean old_cBean = new ChatBean();
/*      */     try {
/*  585 */       if (isUpdateCatch)
/*      */       {
/*  587 */         if (is_audit == 1)
/*      */         {
/*  589 */           old_cBean = (ChatBean)((List)chat_map.get(cb.getSub_id() + "_a" + list_type + "l")).get(cb.getIndex_num());
/*  590 */           old_cBean.setContent(cb.getContent());
/*      */ 
/*  593 */           List tempList = (List)chat_map.get(cb.getSub_id() + "_c" + list_type + "l");
/*  594 */           for (int i = tempList.size(); i > -1; i--)
/*      */           {
/*  596 */             ChatBean tempChatBena = (ChatBean)tempList.get(i - 1);
/*  597 */             if (cb.getChat_id().equals(tempChatBena.getChat_id()))
/*      */             {
/*  599 */               tempChatBena.setContent(cb.getContent());
/*  600 */               break;
/*      */             }
/*      */           }
/*      */ 
/*      */         }
/*      */         else
/*      */         {
/*  607 */           old_cBean = (ChatBean)((List)chat_map.get(cb.getSub_id() + "_c" + list_type + "l")).get(cb.getIndex_num());
/*  608 */           old_cBean.setContent(cb.getContent());
/*      */         }
/*      */       }
/*      */       else
/*  612 */         old_cBean = cb;
/*      */     }
/*      */     catch (Exception e) {
/*  615 */       e.printStackTrace();
/*      */     }
/*  617 */     return ChatRoomDAO.updateChat(old_cBean);
/*      */   }
/*      */ 
/*      */   public static boolean deleteChat(ChatBean cb, int is_p_audit, int is_t_audit, boolean isUpdateCatch)
/*      */   {
/*  630 */     if ("pic".equals(cb.getChat_area()))
/*      */     {
/*  632 */       return deleteChatHandl(cb, is_p_audit, "p", isUpdateCatch);
/*      */     }
/*      */ 
/*  636 */     return deleteChatHandl(cb, is_t_audit, "t", isUpdateCatch);
/*      */   }
/*      */ 
/*      */   public static boolean deleteChatHandl(ChatBean cb, int is_audit, String list_type, boolean isUpdateCatch)
/*      */   {
/*  650 */     ChatBean old_cBean = new ChatBean();
/*      */     try {
/*  652 */       if (isUpdateCatch)
/*      */       {
/*  654 */         if (is_audit == 1)
/*      */         {
/*  656 */           ((List)chat_map.get(cb.getSub_id() + "_a" + list_type + "l")).set(cb.getIndex_num(), null);
/*      */ 
/*  659 */           List tempList = (List)chat_map.get(cb.getSub_id() + "_c" + list_type + "l");
/*  660 */           for (int i = tempList.size(); i > -1; i--)
/*      */           {
/*  662 */             ChatBean tempChatBena = (ChatBean)tempList.get(i - 1);
/*  663 */             if (cb.getChat_id().equals(tempChatBena.getChat_id()))
/*      */             {
/*  665 */               ((List)chat_map.get(cb.getSub_id() + "_c" + list_type + "l")).set(i - 1, null);
/*  666 */               break;
/*      */             }
/*      */           }
/*      */ 
/*      */         }
/*      */         else
/*      */         {
/*  673 */           ((List)chat_map.get(cb.getSub_id() + "_c" + list_type + "l")).set(cb.getIndex_num(), null);
/*      */         }
/*      */       }
/*      */     }
/*      */     catch (Exception e)
/*      */     {
/*  679 */       e.printStackTrace();
/*      */     }
/*  681 */     return ChatRoomDAO.deleteChat(cb.getChat_id());
/*      */   }
/*      */ 
/*      */   public static synchronized void setTextInfo(String sub_id, ChatBean cb, int is_t_audit, boolean is_db)
/*      */   {
/*  695 */     if (!is_db)
/*      */     {
/*  697 */       cb.setSub_id(sub_id);
/*  698 */       cb.setChat_id(UUID.randomUUID().toString());
/*  699 */       cb.setPut_time(DateUtil.getCurrentDateTime());
/*      */ 
/*  701 */       if (!"admin".equals(cb.getChat_type()))
/*      */       {
/*  704 */         if (user_map.containsKey(sub_id + "_prohibit_users"))
/*      */         {
/*  706 */           String users = (String)user_map.get(sub_id + "_prohibit_users");
/*      */ 
/*  708 */           if ((!"admin".equals(cb.getChat_type())) && (users.contains(cb.getUser_num() + ",")))
/*      */           {
/*  710 */             return;
/*      */           }
/*      */         }
/*      */ 
/*  714 */         if ("ip".equals(getProhibitType(sub_id)))
/*      */         {
/*  716 */           if (user_map.containsKey(sub_id + "_prohibit_ips"))
/*      */           {
/*  718 */             String ips = (String)user_map.get(sub_id + "_prohibit_ips");
/*      */ 
/*  720 */             if ((!"admin".equals(cb.getChat_type())) && (ips.contains(cb.getIp() + ",")))
/*      */             {
/*  722 */               return;
/*      */             }
/*      */           }
/*      */         }
/*      */ 
/*  727 */         if ("0".equals(other_map.get(sub_id + "_is_permit_speak")))
/*      */         {
/*  729 */           return;
/*      */         }
/*      */ 
/*  733 */         if ("1".equals(other_map.get(sub_id + "_is_t_keyw")))
/*      */         {
/*  736 */           if (ifHaveKeyFilter(cb.getContent()))
/*      */           {
/*  739 */             if ((!other_map.containsKey(sub_id + "_filter_type")) || ("0".equals(other_map.get(sub_id + "_filter_type"))))
/*      */             {
/*  741 */               return;
/*      */             }
/*      */ 
/*  744 */             if ("1".equals(other_map.get(sub_id + "_filter_type")))
/*      */             {
/*  746 */               replacekeyWordByContent(cb);
/*      */             }
/*      */           }
/*      */         }
/*      */ 
/*  751 */         if ("1".equals(other_map.get(sub_id + "_is_t_flink")))
/*      */         {
/*  753 */           if (ifHaveNickFilter(cb.getChat_user()))
/*      */           {
/*  755 */             return;
/*      */           }
/*      */         }
/*      */ 
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/*  763 */     if (is_t_audit == 1)
/*      */     {
/*  765 */       if (chat_map.containsKey(sub_id + "_atl"))
/*      */       {
/*  768 */         cb.setIndex_num(((List)chat_map.get(sub_id + "_atl")).size());
/*  769 */         ((List)chat_map.get(sub_id + "_atl")).add(cb);
/*      */       }
/*      */       else
/*      */       {
/*  773 */         List l = new ArrayList();
/*      */ 
/*  775 */         cb.setIndex_num(0);
/*  776 */         l.add(cb);
/*  777 */         chat_map.put(sub_id + "_atl", l);
/*      */       }
/*      */ 
/*  781 */       if (cb.getAudit_status() == 1)
/*      */       {
/*  783 */         if (chat_map.containsKey(sub_id + "_ctl"))
/*      */         {
/*  785 */           cb.setIndex_num(((List)chat_map.get(sub_id + "_ctl")).size());
/*  786 */           ((List)chat_map.get(sub_id + "_ctl")).add(cb);
/*      */         }
/*      */         else
/*      */         {
/*  790 */           List l = new ArrayList();
/*  791 */           cb.setIndex_num(0);
/*  792 */           l.add(cb);
/*  793 */           chat_map.put(sub_id + "_ctl", l);
/*      */         }
/*      */       }
/*      */ 
/*      */     }
/*  798 */     else if (chat_map.containsKey(sub_id + "_ctl"))
/*      */     {
/*  800 */       cb.setIndex_num(((List)chat_map.get(sub_id + "_ctl")).size());
/*  801 */       ((List)chat_map.get(sub_id + "_ctl")).add(cb);
/*      */     }
/*      */     else
/*      */     {
/*  805 */       List l = new ArrayList();
/*  806 */       cb.setIndex_num(0);
/*  807 */       l.add(cb);
/*  808 */       chat_map.put(sub_id + "_ctl", l);
/*      */     }
/*      */ 
/*  812 */     if (!is_db)
/*  813 */       ChatRoomDAO.insertChat(cb);
/*      */   }
/*      */ 
/*      */   public static boolean isPassChat(ChatBean cb)
/*      */   {
/*  823 */     if ("pic".equals(cb.getChat_area()))
/*      */     {
/*  825 */       return isPassChatHandl(cb, "p");
/*      */     }
/*      */ 
/*  829 */     return isPassChatHandl(cb, "t");
/*      */   }
/*      */ 
/*      */   public static boolean isPassChatHandl(ChatBean cb, String list_type)
/*      */   {
/*  841 */     ChatBean old_cBean = new ChatBean();
/*      */     try
/*      */     {
/*  844 */       old_cBean = (ChatBean)((List)chat_map.get(cb.getSub_id() + "_a" + list_type + "l")).get(cb.getIndex_num());
/*  845 */       old_cBean.setAudit_status(1);
/*      */ 
/*  848 */       if (chat_map.containsKey(cb.getSub_id() + "_c" + list_type + "l"))
/*      */       {
/*  850 */         ((List)chat_map.get(cb.getSub_id() + "_c" + list_type + "l")).add(old_cBean);
/*      */       }
/*      */       else {
/*  853 */         List l = new ArrayList();
/*  854 */         l.add(old_cBean);
/*  855 */         chat_map.put(cb.getSub_id() + "_c" + list_type + "l", l);
/*      */       }
/*      */     }
/*      */     catch (Exception e)
/*      */     {
/*  860 */       e.printStackTrace();
/*      */     }
/*  862 */     return ChatRoomDAO.updateChatAuditStatus(old_cBean.getChat_id());
/*      */   }
/*      */ 
/*      */   public static boolean insertLivePic(SubjectResouse sr)
/*      */   {
/*  873 */     sr.setAdd_time(DateUtil.getCurrentDateTime());
/*  874 */     if (ChatRoomDAO.insertLiveAffix(sr))
/*      */     {
/*      */       try
/*      */       {
/*  879 */         if (chat_map.containsKey(sr.getSub_id() + "_live_pic"))
/*      */         {
/*  881 */           ((List)chat_map.get(sr.getSub_id() + "_live_pic")).add(sr);
/*      */         }
/*      */         else
/*      */         {
/*  885 */           List l = new ArrayList();
/*  886 */           l.add(sr);
/*  887 */           chat_map.put(sr.getSub_id() + "_live_pic", l);
/*      */         }
/*      */       } catch (Exception e) {
/*  890 */         e.printStackTrace();
/*      */       }
/*  892 */       return true;
/*      */     }
/*      */ 
/*  895 */     return false;
/*      */   }
/*      */ 
/*      */   public static boolean insertLiveVideo(SubjectResouse sr)
/*      */   {
/*  905 */     Map m = new HashMap();
/*  906 */     m.put("sub_id", sr.getSub_id());
/*  907 */     m.put("affix_type", sr.getAffix_type());
/*  908 */     m.put("affix_status", sr.getAffix_status());
/*  909 */     boolean isOk = false;
/*      */ 
/*  911 */     if ("0".equals(ChatRoomDAO.getResouseInfoByCon(m)))
/*      */     {
/*  913 */       sr.setAdd_time(DateUtil.getCurrentDateTime());
/*  914 */       isOk = ChatRoomDAO.insertLiveAffixForSingle(sr);
/*      */     } else {
/*  916 */       isOk = ChatRoomDAO.updateLiveAffixForSingle(sr);
/*  917 */     }if (isOk)
/*      */     {
/*  919 */       setOtherParam(sr.getSub_id(), sr.getAffix_status() + "_video", sr.getAffix_path());
/*  920 */       return true;
/*      */     }
/*      */ 
/*  923 */     return false;
/*      */   }
/*      */ 
/*      */   public static synchronized boolean deleteLivePic(String sub_id, int id)
/*      */   {
/*  934 */     if (ChatRoomDAO.deleteLivePic(id))
/*      */     {
/*      */       try {
/*  937 */         for (int i = 0; i < ((List)chat_map.get(sub_id + "_live_pic")).size(); i++)
/*      */         {
/*  939 */           SubjectResouse sr = (SubjectResouse)((List)chat_map.get(sub_id + "_live_pic")).get(i);
/*  940 */           if ((sr != null) && (sr.getId() == id))
/*      */           {
/*  942 */             ((List)chat_map.get(sub_id + "_live_pic")).set(i, null);
/*  943 */             break;
/*      */           }
/*      */         }
/*      */       } catch (Exception e) {
/*  947 */         e.printStackTrace();
/*      */       }
/*      */ 
/*  950 */       return true;
/*      */     }
/*      */ 
/*  954 */     return false;
/*      */   }
/*      */ 
/*      */   public static List getLivePicList(String sub_id, int index_num)
/*      */   {
/*  966 */     if (chat_map.containsKey(sub_id + "_live_pic"))
/*      */     {
/*  968 */       return ((List)chat_map.get(sub_id + "_live_pic")).subList(index_num, ((List)chat_map.get(sub_id + "_live_pic")).size());
/*      */     }
/*      */ 
/*  971 */     return null;
/*      */   }
/*      */ 
/*      */   public static Map getOtherMap(String sub_id)
/*      */   {
/*  981 */     Map o = new HashMap();
/*      */     try
/*      */     {
/*  984 */       Iterator iter = other_map.entrySet().iterator();
/*  985 */       while (iter.hasNext()) {
/*  986 */         Entry entry = (Entry)iter.next();
/*  987 */         String key = (String)entry.getKey();
/*  988 */         if (key.startsWith(sub_id))
/*      */         {
/*  990 */           o.put(key, entry.getValue());
/*      */         }
/*      */       }
/*      */     } catch (Exception e) {
/*  994 */       e.printStackTrace();
/*      */     }
/*      */ 
/*  997 */     return o;
/*      */   }
/*      */ 
/*      */   public static boolean setOtherParam(String sub_id, String param_name, String param_value)
/*      */   {
/*      */     try
/*      */     {
/* 1009 */       other_map.put(sub_id + "_" + param_name, param_value);
/* 1010 */       return true;
/*      */     } catch (Exception e) {
/* 1012 */       e.printStackTrace();
/* 1013 */     }return false;
/*      */   }
/*      */ 
/*      */   public static boolean ifHaveKeyFilter(String content)
/*      */   {
/* 1024 */     if ((keyword == null) || ("".equals(keyword)))
/*      */     {
/* 1026 */       return false;
/*      */     }
/*      */ 
/* 1030 */     String[] tempA = keyword.split(",");
/* 1031 */     for (int i = 0; i < tempA.length; i++)
/*      */     {
/* 1033 */       if (content.contains(tempA[i]))
/*      */       {
/* 1035 */         return true;
/*      */       }
/*      */     }
/* 1038 */     return false;
/*      */   }
/*      */ 
/*      */   public static void replacekeyWordByContent(ChatBean cb)
/*      */   {
/* 1050 */     if ((keyword != null) && (!"".equals(keyword)))
/*      */     {
/* 1052 */       String[] tempA = keyword.split(",");
/* 1053 */       for (int i = 0; i < tempA.length; i++)
/*      */       {
/* 1055 */         cb.setContent(cb.getContent().replaceAll(tempA[i], "***"));
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   public static boolean ifHaveNickFilter(String chat_user)
/*      */   {
/* 1067 */     if ((nick_filter == null) || ("".equals(nick_filter)))
/*      */     {
/* 1069 */       return false;
/*      */     }
/*      */ 
/* 1073 */     String[] tempA = nick_filter.split(",");
/* 1074 */     for (int i = 0; i < tempA.length; i++)
/*      */     {
/* 1076 */       if (chat_user.contains(tempA[i]))
/*      */       {
/* 1078 */         return true;
/*      */       }
/*      */     }
/* 1081 */     return false;
/*      */   }
/*      */ 
/*      */   public static List getChatListByDB(String sub_id)
/*      */   {
/* 1094 */     return ChatRoomDAO.getChatListBySubID(sub_id);
/*      */   }
/*      */ 
/*      */   public static String getHistoryVideo(String sub_id)
/*      */   {
/* 1103 */     Map m = new HashMap();
/* 1104 */     m.put("sub_id", sub_id);
/* 1105 */     m.put("affix_type", "video");
/* 1106 */     m.put("affix_status", "history");
/* 1107 */     List l = ChatRoomDAO.getResouseList(m);
/* 1108 */     if ((l != null) && (l.size() > 0))
/*      */     {
/* 1110 */       return ((SubjectResouse)l.get(0)).getAffix_path();
/*      */     }
/*      */ 
/* 1113 */     return "";
/*      */   }
/*      */ 
/*      */   public static List getResouseList(String sub_id, String affix_type, String affix_status)
/*      */   {
/* 1126 */     Map m = new HashMap();
/* 1127 */     m.put("sub_id", sub_id);
/* 1128 */     m.put("affix_type", affix_type);
/* 1129 */     m.put("affix_status", affix_status);
/* 1130 */     return ChatRoomDAO.getResouseList(m);
/*      */   }
/*      */ 
/*      */   public static void main(String[] args)
/*      */   {
/* 1137 */     System.out.println(getLivePicList("193ed61c-42dd-4e90-b5a3-8f8a48e02c21", 0));
/*      */   }
/*      */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.interview.ChatRoomServices
 * JD-Core Version:    0.6.2
 */