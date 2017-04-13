/*     */ package com.cicro.wcm.services.comment;
/*     */ 
/*     */ import com.cicro.util.DateUtil;
/*     */ import com.cicro.util.ip.Utils;
/*     */ import com.cicro.wcm.bean.cms.info.InfoBean;
/*     */ import com.cicro.wcm.bean.comment.CommentBean;
/*     */ import com.cicro.wcm.bean.comment.CommentSet;
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.dao.PublicTableDAO;
/*     */ import com.cicro.wcm.dao.comment.CommentDAO;
/*     */ import com.cicro.wcm.services.cms.info.InfoBaseManager;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class CommentService
/*     */ {
/*  18 */   private static String delete_error = "该评论已经删除";
/*     */ 
/*     */   public static List<CommentBean> getHotCommentInfo(Map<String, String> m)
/*     */   {
/*  26 */     return CommentDAO.getHotCommentInfo(m);
/*     */   }
/*     */ 
/*     */   public static String getCommentMainCountForBrowser(Map<String, String> m)
/*     */   {
/*  36 */     return CommentDAO.getCommentMainCountForBrowser(m);
/*     */   }
/*     */ 
/*     */   public static Map<String, Object> getCommentMainListBrowserForInfo(Map<String, String> m)
/*     */   {
/*  47 */     InfoBean ib = InfoBaseManager.getInfoById((String)m.get("id"));
/*  48 */     if ((ib == null) || (ib.getInfo_status() != 8) || (ib.getFinal_status() == -1))
/*     */     {
/*  51 */       return null;
/*     */     }
/*  53 */     Map return_m = new HashMap();
/*  54 */     return_m.put("InfoObject", ib);
/*  55 */     return_m.put("CommentList", getCommentMainListForBrowserHandl(m));
/*  56 */     return_m.put("CommentConfig", CommentSetManager.getCommentSetBySiteIdAndAppID((String)m.get("site_id"), "cms"));
/*  57 */     return return_m;
/*     */   }
/*     */ 
/*     */   public static List<CommentBean> getCommentMainListForBrowserHandl(Map<String, String> m)
/*     */   {
/*  67 */     List l = CommentDAO.getCommentMainListForBrowser(m);
/*  68 */     if ((l != null) && (l.size() > 0))
/*     */     {
/*  70 */       for (CommentBean cb : l)
/*     */       {
/*  73 */         if (cb.getIs_replay() == 0)
/*     */         {
/*  75 */           if (cb.getParent_id() != 0)
/*     */           {
/*  79 */             cb.setParent_comment(commentListToParentBean(commentListToMap(CommentDAO.getCommentListForIDS(cb.getParent_str())), cb.getParent_id()));
/*  80 */             String country = Utils.getCountry(cb.getIp());
/*  81 */             if (!"局域网".equals(country))
/*     */             {
/*  83 */               cb.setCountry(country);
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*  89 */     return l;
/*     */   }
/*     */ 
/*     */   public static Map<Integer, CommentBean> commentListToMap(List<CommentBean> l)
/*     */   {
/*  98 */     Map m = new HashMap();
/*  99 */     int i = 1;
/* 100 */     for (CommentBean cb : l)
/*     */     {
/* 102 */       cb.setCom_sort(i);
/* 103 */       m.put(Integer.valueOf(cb.getId()), cb);
/* 104 */       i++;
/*     */     }
/* 106 */     return m;
/*     */   }
/*     */ 
/*     */   public static CommentBean commentListToParentBean(Map<Integer, CommentBean> m, int parent_id)
/*     */   {
/* 116 */     if (m.containsKey(Integer.valueOf(parent_id)))
/*     */     {
/* 118 */       CommentBean cb = (CommentBean)m.get(Integer.valueOf(parent_id));
/* 119 */       cb.setParent_comment(commentListToParentBean(m, cb.getParent_id()));
/* 120 */       return cb;
/*     */     }
/* 122 */     return null;
/*     */   }
/*     */ 
/*     */   public static String getCommentMainCount(Map<String, String> m, String info_type)
/*     */   {
/* 132 */     if ("info".equals(info_type))
/*     */     {
/* 134 */       return CommentDAO.getCommentMainCountForInfo(m);
/*     */     }
/* 136 */     return "";
/*     */   }
/*     */ 
/*     */   public static List<CommentBean> getCommentMainList(Map<String, String> m, String info_type)
/*     */   {
/* 146 */     if ("info".equals(info_type))
/*     */     {
/* 148 */       return CommentDAO.getCommentMainListForInfo(m);
/*     */     }
/* 150 */     return null;
/*     */   }
/*     */ 
/*     */   public static boolean insertCommentMain(CommentBean comB)
/*     */   {
/* 160 */     CommentSet cs = CommentSetManager.getCommentSetBySiteIdAndAppID(comB.getSite_id(), comB.getApp_id());
/*     */ 
/* 162 */     setCommentBeanStatus(comB, cs.getIs_need());
/*     */ 
/* 164 */     setCommentBeanQuest(comB, cs.getTact_word());
/*     */ 
/* 166 */     setCommentParentStr(comB);
/* 167 */     comB.setAdd_time(DateUtil.getCurrentDateTime());
/* 168 */     comB.setId(PublicTableDAO.getIDByTableName(PublicTableDAO.COMMENT_MAIN_TABLE_NAME));
/* 169 */     if (CommentDAO.insertCommentMain(comB))
/*     */     {
/* 171 */       if ((comB.getIs_status() == 1) && (comB.getParent_id() > 0))
/*     */       {
/* 174 */         Map m = new HashMap();
/* 175 */         m.put("is_replay", "1");
/* 176 */         m.put("id", comB.getParent_id());
/* 177 */         CommentDAO.updateCommentMain(m, null);
/*     */       }
/* 179 */       return true;
/*     */     }
/* 181 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean updateCommentMain(Map<String, String> m, SettingLogsBean stl)
/*     */   {
/* 192 */     return CommentDAO.updateCommentMain(m, stl);
/*     */   }
/*     */ 
/*     */   public static boolean updateCommentStatus(String ids, List<CommentBean> l, String is_status, SettingLogsBean stl)
/*     */   {
/* 205 */     if ("-1".equals(is_status))
/*     */     {
/* 207 */       Map m = new HashMap();
/* 208 */       m.put("ids", ids);
/* 209 */       m.put("is_status", is_status);
/* 210 */       m.put("content", delete_error);
/* 211 */       return CommentDAO.cancelCommentMain(m, stl);
/*     */     }
/*     */ 
/* 214 */     return CommentDAO.passCommentMain(ids, l, is_status, stl);
/*     */   }
/*     */ 
/*     */   public static boolean deleteCommentMain(Map<String, String> m, SettingLogsBean stl)
/*     */   {
/* 227 */     m.put("content", delete_error);
/* 228 */     return CommentDAO.deleteCommentMain(m, stl);
/*     */   }
/*     */ 
/*     */   public static void setCommentBeanStatus(CommentBean comB, String is_need)
/*     */   {
/* 234 */     if ("0".equals(is_need))
/*     */     {
/* 236 */       comB.setIs_status(1);
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void setCommentBeanQuest(CommentBean comB, String tact_word)
/*     */   {
/* 248 */     if ((tact_word != null) && (!"".equals(tact_word)))
/*     */     {
/* 250 */       tact_word = tact_word.trim();
/* 251 */       String[] tempA = tact_word.split(",");
/* 252 */       if ((tempA != null) && (tempA.length > 0))
/*     */       {
/* 254 */         for (int i = 0; i < tempA.length; i++)
/*     */         {
/* 256 */           if (comB.getContent().contains(tempA[i]))
/*     */           {
/* 258 */             comB.setIs_quest(1);
/* 259 */             break;
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void setCommentParentStr(CommentBean comB)
/*     */   {
/* 268 */     if (comB.getParent_id() != 0)
/*     */     {
/* 270 */       String parent_str = CommentDAO.getParentStr(comB.getParent_id());
/* 271 */       if ((parent_str != null) && (!"".equals(parent_str)) && (!"0".equals(parent_str)))
/*     */       {
/* 273 */         parent_str = parent_str + "," + comB.getParent_id();
/*     */       }
/* 275 */       else parent_str = comB.getParent_id();
/*     */ 
/* 277 */       comB.setParent_str(parent_str);
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.comment.CommentService
 * JD-Core Version:    0.6.2
 */