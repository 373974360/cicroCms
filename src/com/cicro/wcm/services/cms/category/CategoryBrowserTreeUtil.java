/*    */ package com.cicro.wcm.services.cms.category;
/*    */ 
/*    */ import com.cicro.util.FormatUtil;
/*    */ import com.cicro.wcm.bean.cms.category.CategoryBean;
/*    */ import java.util.List;
/*    */ 
/*    */ public class CategoryBrowserTreeUtil
/*    */ {
/*    */   public static String getBroCategoryTreeByCategoryID(int cat_id, String site_id)
/*    */   {
/* 17 */     CategoryBean cbg = CategoryManager.getCategoryBeanCatID(cat_id, site_id);
/*    */ 
/* 19 */     if ((cbg != null) && (cbg.getIs_archive() == 0))
/*    */     {
/* 21 */       return getBroCategoryTreeByCategory(cbg);
/*    */     }
/* 23 */     return "[]";
/*    */   }
/*    */ 
/*    */   public static String getBroCategoryTreeByCategory(CategoryBean cbg)
/*    */   {
/* 33 */     String json_str = "";
/*    */     try
/*    */     {
/* 36 */       List child_list = CategoryManager.getChildCategoryList(cbg.getCat_id(), cbg.getSite_id());
/* 37 */       json_str = "[{\"id\":" + cbg.getCat_id() + ",\"text\":\"" + FormatUtil.formatJsonStr(cbg.getCat_cname()) + "\",\"attributes\":{\"url\":\"" + 
/* 38 */         cbg.getJump_url() + "\",\"is_mutilpage\":\"" + cbg.getIs_mutilpage() + "\"}";
/* 39 */       if ((child_list != null) && (child_list.size() > 0))
/*    */       {
/* 41 */         json_str = json_str + ",\"children\":[" + getBroCategoryTreeJsonStrHandl(child_list) + "]";
/*    */       }
/* 43 */       json_str = json_str + "}]";
/*    */     }
/*    */     catch (Exception e) {
/* 46 */       e.printStackTrace();
/*    */     }
/* 48 */     return json_str;
/*    */   }
/*    */ 
/*    */   public static String getBroCategoryTreeJsonStrHandl(List<CategoryBean> child_list)
/*    */   {
/* 58 */     String json_str = "";
/* 59 */     if ((child_list != null) && (child_list.size() > 0))
/*    */     {
/* 61 */       for (int i = 0; i < child_list.size(); i++)
/*    */       {
/* 63 */         if (((CategoryBean)child_list.get(i)).getIs_show_tree() == 1)
/*    */         {
/* 65 */           json_str = json_str + ",{";
/* 66 */           List child_m_list = CategoryManager.getChildCategoryList(((CategoryBean)child_list.get(i)).getCat_id(), ((CategoryBean)child_list.get(i)).getSite_id());
/* 67 */           if ((child_m_list != null) && (child_m_list.size() > 0))
/*    */           {
/* 69 */             json_str = json_str + "\"id\":" + ((CategoryBean)child_list.get(i)).getCat_id() + ",\"text\":\"" + FormatUtil.formatJsonStr(((CategoryBean)child_list.get(i)).getCat_cname()) + "\",\"attributes\":{\"url\":\"" + 
/* 70 */               ((CategoryBean)child_list.get(i)).getJump_url() + "\",\"is_mutilpage\":\"" + ((CategoryBean)child_list.get(i)).getIs_mutilpage() + "\"}";
/* 71 */             json_str = json_str + ",\"state\":'closed'";
/* 72 */             json_str = json_str + ",\"children\":[" + getBroCategoryTreeJsonStrHandl(child_m_list) + "]";
/*    */           } else {
/* 74 */             json_str = json_str + "\"id\":" + ((CategoryBean)child_list.get(i)).getCat_id() + ",\"text\":\"" + FormatUtil.formatJsonStr(((CategoryBean)child_list.get(i)).getCat_cname()) + "\",\"attributes\":{\"url\":\"" + 
/* 75 */               ((CategoryBean)child_list.get(i)).getJump_url() + "\",\"is_mutilpage\":\"" + ((CategoryBean)child_list.get(i)).getIs_mutilpage() + "\"}";
/* 76 */           }json_str = json_str + "}";
/*    */         }
/*    */       }
/* 79 */       if ((json_str != null) && (!"".equals(json_str)))
/* 80 */         json_str = json_str.substring(1);
/*    */     }
/* 82 */     return json_str;
/*    */   }
/*    */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.cms.category.CategoryBrowserTreeUtil
 * JD-Core Version:    0.6.2
 */