/*     */ package com.cicro.wcm.services.cms.category;
/*     */ 
/*     */ import com.cicro.wcm.bean.cms.category.CategoryBean;
import com.cicro.wcm.bean.cms.category.CategoryReleBean;
/*     */ import com.cicro.wcm.catchs.ISyncCatch;
/*     */ import com.cicro.wcm.catchs.SyncCatchHandl;
/*     */ import com.cicro.wcm.dao.cms.category.CategoryReleDAO;
/*     */ import com.cicro.wcm.services.org.group.GroupManager;

/*     */ import java.io.PrintStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ public class CategoryReleManager
/*     */   implements ISyncCatch
/*     */ {
/*  24 */   private static List<CategoryReleBean> cr_list = new ArrayList();
/*     */ 
/*     */   static {
/*  27 */     reloadCatchHandl();
/*     */   }
/*     */ 
/*     */   public void reloadCatch()
/*     */   {
/*  32 */     reloadCatchHandl();
/*     */   }
/*     */ 
/*     */   public static void reloadCatchHandl()
/*     */   {
/*  37 */     cr_list.clear();
/*  38 */     cr_list = CategoryReleDAO.getCategoryReleUserList();
/*  39 */     CategoryTreeUtil.reloadMap();
/*     */   }
/*     */ 
/*     */   public static void reloadCategoryRele()
/*     */   {
/*  44 */     SyncCatchHandl.reladCatchForRMI("com.cicro.wcm.services.cms.category.CategoryReleManager");
/*     */   }
/*     */ 
/*     */   public static List<CategoryReleBean> getCategoryReleUserListByCatID(int cat_id, String site_id)
/*     */   {
/*  54 */     List list = new ArrayList();
/*  55 */     if ((cr_list != null) && (cr_list.size() > 0))
/*     */     {
/*  57 */       for (int i = 0; i < cr_list.size(); i++)
/*     */       {
/*  59 */         if ((((CategoryReleBean)cr_list.get(i)).getCat_id() == cat_id) && (site_id.equals(((CategoryReleBean)cr_list.get(i)).getSite_id())))
/*  60 */           list.add((CategoryReleBean)cr_list.get(i));
/*     */       }
/*     */     }
/*  63 */     return list;
/*     */   }
/*     */ 
/*     */   public static String getCategoryIDSByUser(int priv_type, int prv_id, String site_id)
/*     */   {
/*  73 */     String ids = "";
/*  74 */     if ((cr_list != null) && (cr_list.size() > 0))
/*     */     {
/*  76 */       for (int i = 0; i < cr_list.size(); i++)
/*     */       {
/*  78 */         if ((((CategoryReleBean)cr_list.get(i)).getPriv_type() == priv_type) && (((CategoryReleBean)cr_list.get(i)).getPrv_id() == prv_id) && (site_id.equals(((CategoryReleBean)cr_list.get(i)).getSite_id())))
/*  79 */           ids = ids + "," + ((CategoryReleBean)cr_list.get(i)).getCat_id();
/*     */       }
/*  81 */       if ((ids != null) && (!"".equals(ids)))
/*  82 */         ids = ids.substring(1);
/*     */     }
/*  84 */     return ids;
/*     */   }
/*     */ 
/*     */   public static boolean insertCategoryReleUser(String cat_ids, int priv_type, int prv_id, String site_id)
/*     */   {
/*  97 */     CategoryReleDAO.deleteCategoryReleUserByCatID(priv_type, prv_id+"", site_id);
/*  98 */     if ((cat_ids != null) && (!"".equals(cat_ids))) {
/*     */       try
/*     */       {
/* 101 */         String[] tempA = cat_ids.split(",");
/* 102 */         CategoryReleBean crb = new CategoryReleBean();
/* 103 */         crb.setPriv_type(priv_type);
/* 104 */         crb.setPrv_id(prv_id);
/* 105 */         crb.setSite_id(site_id);
/* 106 */         for (int i = 0; i < tempA.length; i++)
/*     */         {
/* 108 */           crb.setCat_id(Integer.parseInt(tempA[i]));
/* 109 */           CategoryReleDAO.insertCategoryReleUser(crb);
/*     */         }
/*     */       }
/*     */       catch (Exception e)
/*     */       {
/* 114 */         e.printStackTrace();
/* 115 */         return false;
/*     */       }
/*     */     }
/* 118 */     reloadCategoryRele();
/* 119 */     return true;
/*     */   }
/*     */ 
/*     */   public static boolean deleteCateReleByPrv(int priv_type, String prv_id, String site_id)
/*     */   {
/* 131 */     if (CategoryReleDAO.deleteCategoryReleUserByCatID(priv_type, prv_id, site_id))
/*     */     {
/* 133 */       reloadCategoryRele();
/* 134 */       return true;
/*     */     }
/* 136 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean insertCategoryReleUser(List<CategoryReleBean> list, int cat_id, String site_id)
/*     */   {
/* 148 */     deleteCategoryReleUserByCatID(cat_id+"", site_id);
/* 149 */     if ((list != null) && (list.size() > 0)) {
/*     */       try
/*     */       {
/* 152 */         for (int i = 0; i < list.size(); i++)
/*     */         {
/* 154 */           CategoryReleDAO.insertCategoryReleUser((CategoryReleBean)list.get(i));
/*     */         }
/*     */       }
/*     */       catch (Exception e) {
/* 158 */         e.printStackTrace();
/* 159 */         return false;
/*     */       }
/*     */     }
/* 162 */     reloadCategoryRele();
/* 163 */     return true;
/*     */   }
/*     */ 
/*     */   public static boolean deleteCategoryReleUserByCatID(String cat_id, String site_id)
/*     */   {
/* 174 */     return CategoryReleDAO.deleteCategoryReleUserByCatID(cat_id, site_id);
/*     */   }
/*     */ 
/*     */   public static boolean deleteCategoryReleUserBySiteID(String site_id)
/*     */   {
/* 184 */     if (CategoryReleDAO.deleteCategoryReleUserBySiteID(site_id))
/*     */     {
/* 186 */       reloadCategoryRele();
/* 187 */       return true;
/*     */     }
/* 189 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean isCategoryManagerByUser(int user_id, String site_id, int cat_id)
/*     */   {
/* 202 */       //得到该用户所在的用户组
				String user_group_ids = ","+GroupManager.getGroupIDSByUserID(user_id+"")+",";
				CategoryBean cgb = CategoryManager.getCategoryBean(cat_id);
				CategoryBean cgb2 = null;
				if(cgb != null)
				{
					if(cr_list != null && cr_list.size() > 0)
					{
						for(int i=0;i<cr_list.size();i++)
						{//0表示用户  1表示用户组
							if(cr_list.get(i) != null)
							{
								if(site_id.equals(cr_list.get(i).getSite_id()))
								{
									cgb2 = CategoryManager.getCategoryBean(cr_list.get(i).getCat_id());
									if(cgb2 != null)
									{
										if(cr_list.get(i).getCat_id() == cat_id || cgb.getCat_position().indexOf(cr_list.get(i).getCat_id()+"") > 0 || cgb2.getCat_position().indexOf(cat_id+"") > 0)
										{
											boolean tmp1 = cr_list.get(i).getPriv_type() == 0 && cr_list.get(i).getPrv_id() == user_id;
											boolean tmp2 = cr_list.get(i).getPriv_type() == 1 && user_group_ids.contains(","+cr_list.get(i).getPrv_id()+",");
											if(tmp1 || tmp2)
												return true;		
										}
									}
								}
								
							}
						}
					}
				}
				return false;
/*     */   }
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/* 217 */     System.out.println(getCategoryIDSByUser(0, 116, "11111ddd"));
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.cms.category.CategoryReleManager
 * JD-Core Version:    0.6.2
 */