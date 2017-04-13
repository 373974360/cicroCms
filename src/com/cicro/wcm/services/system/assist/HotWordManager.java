/*     */ package com.cicro.wcm.services.system.assist;
/*     */ 
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.bean.system.assist.HotWordBean;
/*     */ import com.cicro.wcm.catchs.ISyncCatch;
/*     */ import com.cicro.wcm.catchs.SyncCatchHandl;
/*     */ import com.cicro.wcm.dao.system.assist.HotWordDAO;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ 
/*     */ public class HotWordManager
/*     */   implements ISyncCatch
/*     */ {
/*  21 */   private static Map<Integer, HotWordBean> hw_map = new HashMap();
/*     */ 
/*     */   static {
/*  24 */     reloadCatchHandl();
/*     */   }
/*     */ 
/*     */   public void reloadCatch()
/*     */   {
/*  29 */     reloadCatchHandl();
/*     */   }
/*     */ 
/*     */   public static void reloadCatchHandl()
/*     */   {
/*  34 */     List hw_list = HotWordDAO.getAllHotWordList();
/*  35 */     hw_map.clear();
/*  36 */     if ((hw_list != null) && (hw_list.size() > 0))
/*     */     {
/*  38 */       for (int i = 0; i < hw_list.size(); i++)
/*  39 */         hw_map.put(Integer.valueOf(((HotWordBean)hw_list.get(i)).getHot_id()), (HotWordBean)hw_list.get(i));
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void reloadHotWord()
/*     */   {
/*  46 */     SyncCatchHandl.reladCatchForRMI("com.cicro.wcm.services.system.assist.HotWordManager");
/*     */   }
/*     */ 
/*     */   public static List<HotWordBean> getHotWordList()
/*     */   {
/*  56 */     List hwl = new ArrayList();
/*  57 */     Set s = hw_map.keySet();
/*  58 */     for (Integer i : s)
/*     */     {
/*  60 */       hwl.add((HotWordBean)hw_map.get(i));
/*     */     }
/*  62 */     return hwl;
/*     */   }
/*     */ 
/*     */   public static String replaceContentHotWord(String content)
/*     */   {
/*  72 */     if ((content == null) || ("".equals(content.trim())))
/*  73 */       return content;
/*  74 */     Set s = hw_map.keySet();
/*  75 */     for (Integer i : s)
/*     */     {
/*  77 */       content = content.replace(((HotWordBean)hw_map.get(i)).getHot_name(), "<a target=\"_blank\" href=\"" + ((HotWordBean)hw_map.get(i)).getHot_url() + "\">" + ((HotWordBean)hw_map.get(i)).getHot_name() + "</a>");
/*     */     }
/*  79 */     return content;
/*     */   }
/*     */ 
/*     */   public static HotWordBean getHotWordBean(int hot_id)
/*     */   {
/*  89 */     if (hw_map.containsKey(Integer.valueOf(hot_id)))
/*     */     {
/*  91 */       return (HotWordBean)hw_map.get(Integer.valueOf(hot_id));
/*     */     }
/*     */ 
/*  94 */     HotWordBean hwb = HotWordDAO.getHotWordBean(hot_id);
/*  95 */     if (hwb != null)
/*     */     {
/*  97 */       hw_map.put(Integer.valueOf(hot_id), hwb);
/*  98 */       return hwb;
/*     */     }
/* 100 */     return null;
/*     */   }
/*     */ 
/*     */   public static String getHotWordCount(Map<String, String> con_map)
/*     */   {
/* 111 */     return HotWordDAO.getHotWordCount(con_map);
/*     */   }
/*     */ 
/*     */   public static boolean updateHotWord(HotWordBean hw, SettingLogsBean stl)
/*     */   {
/* 120 */     if (HotWordDAO.updateHotWord(hw, stl)) {
/* 121 */       hw_map.remove(Integer.valueOf(hw.getHot_id()));
/* 122 */       hw_map.put(Integer.valueOf(hw.getHot_id()), hw);
/* 123 */       reloadHotWord();
/* 124 */       return true;
/*     */     }
/* 126 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean addHotWord(HotWordBean hw, SettingLogsBean stl)
/*     */   {
/* 135 */     if (HotWordDAO.addHotWord(hw, stl)) {
/* 136 */       hw_map.put(Integer.valueOf(hw.getHot_id()), hw);
/* 137 */       reloadHotWord();
/* 138 */       return true;
/*     */     }
/* 140 */     return false;
/*     */   }
/*     */ 
/*     */   public static List<HotWordBean> getAllHotWordList()
/*     */   {
/* 149 */     Set set = hw_map.keySet();
/* 150 */     List list = new ArrayList();
/* 151 */     for (Iterator localIterator = set.iterator(); localIterator.hasNext(); ) { int i = ((Integer)localIterator.next()).intValue();
/* 152 */       list.add((HotWordBean)hw_map.get(Integer.valueOf(i)));
/*     */     }
/* 154 */     return list;
/*     */   }
/*     */ 
/*     */   public static List<HotWordBean> getHotWordListForDB(Map<String, String> con_map)
/*     */   {
/* 164 */     return HotWordDAO.getHotWordListForDB(con_map);
/*     */   }
/*     */ 
/*     */   public static boolean delHotWordById(String hot_ids, SettingLogsBean stl)
/*     */   {
/* 173 */     if (HotWordDAO.delHotWord(hot_ids, stl)) {
/* 174 */       if (hot_ids != null) {
/* 175 */         if (hot_ids.indexOf(",") != -1) {
/* 176 */           for (String id : hot_ids.split(","))
/* 177 */             hw_map.remove(Integer.valueOf(Integer.parseInt(id)));
/*     */         }
/*     */         else {
/* 180 */           hw_map.remove(Integer.valueOf(Integer.parseInt(hot_ids)));
/*     */         }
/*     */       }
/* 183 */       reloadHotWord();
/* 184 */       return true;
/*     */     }
/* 186 */     return false;
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.system.assist.HotWordManager
 * JD-Core Version:    0.6.2
 */