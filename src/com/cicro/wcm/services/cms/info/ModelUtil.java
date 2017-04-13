/*     */ package com.cicro.wcm.services.cms.info;
/*     */ 
/*     */ import com.cicro.util.labelUtil.AutoPagerHandl;
/*     */ import com.cicro.wcm.bean.cms.info.InfoBean;
/*     */ import com.cicro.wcm.bean.cms.info.PicBean;
/*     */ import com.cicro.wcm.bean.logs.SettingLogsBean;
/*     */ import com.cicro.wcm.dao.cms.info.ModelUtilDAO;
/*     */ import com.cicro.wcm.services.model.services.BeanToMapUtil;
/*     */ import com.cicro.wcm.services.model.services.InfoCustomService;
/*     */ import com.cicro.wcm.services.model.services.MapManager;

/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;

/*     */ import org.apache.commons.beanutils.BeanUtils;
/*     */ 
/*     */ public class ModelUtil
/*     */ {
/*     */   public static boolean insert(Object ob, String model_name, SettingLogsBean stl)
/*     */   {
/*     */     try
/*     */     {
/*  35 */       Map mp = ModelConfig.getModelMap(model_name);
/*     */ 
/*  37 */       System.out.println("insert ---- model_name :: " + model_name);
/*     */ 
/*  39 */       setPageCountInObject(ob, model_name);
/*     */ 
/*  41 */       String addSql = "";
/*  42 */       if (mp != null) {
/*  43 */         addSql = (String)mp.get("AddSQL");
/*     */       }
/*  45 */       if (ModelUtilDAO.addModel(ob, addSql, model_name, stl))
/*     */       {
/*  48 */         if ((ob instanceof HashMap)) {
/*  49 */           return true;
/*     */         }
/*  51 */         InfoBean info = (InfoBean)ob;
/*     */ 
/*  53 */         InfoBaseManager.changeInfoStatus(info);
/*     */ 
/*  55 */         if (info.getInfo_status() == 8) {
/*  56 */           InfoPublishManager.publishAfterEvent(info);
/*     */         }
/*  58 */         return true;
/*     */       }
/*  60 */       return false;
/*     */     } catch (Exception e) {
/*  62 */       e.printStackTrace();
/*  63 */     }return false;
/*     */   }
/*     */ 
/*     */   public static boolean update(Object ob, String model_name, SettingLogsBean stl)
/*     */   {
/*     */     try
/*     */     {
/*  78 */       Map mp = ModelConfig.getModelMap(model_name);
/*  79 */       setPageCountInObject(ob, model_name);
/*     */ 
/*  81 */       String UpdateSQL = "";
/*  82 */       if (mp != null) {
/*  83 */         UpdateSQL = (String)mp.get("UpdateSQL");
/*     */       }
/*  85 */       if (ModelUtilDAO.updateModel(ob, UpdateSQL, model_name, stl))
/*     */       {
/*  87 */         InfoBean info = (InfoBean)ob;
/*  88 */         InfoBaseManager.changeInfoStatus(info);
/*  89 */         if (info.getInfo_status() == 8) {
/*  90 */           InfoPublishManager.publishAfterEvent(info);
/*     */         }
/*  92 */         return true;
/*     */       }
/*  94 */       return false;
/*     */     } catch (Exception e) {
/*  96 */       e.printStackTrace();
/*  97 */     }return false;
/*     */   }
/*     */ 
/*     */   public static void setPageCountInObject(Object ob, String model_name)
/*     */   {
/* 105 */     if ((InfoBaseManager.ARTICLE_MODEL_ENAME.equals(model_name)) || (InfoBaseManager.GKTYGS_MODEL_ENAME.equals(model_name)))
/*     */       try
/*     */       {
/* 108 */         String is_am_tupage = BeanUtils.getProperty(ob, "is_am_tupage");
/* 109 */         if ("1".equals(is_am_tupage))
/*     */         {
/* 111 */           String tupage_num = BeanUtils.getProperty(ob, "tupage_num");
/* 112 */           String item_name = "";
/* 113 */           if (InfoBaseManager.ARTICLE_MODEL_ENAME.equals(model_name))
/* 114 */             item_name = "info_content";
/* 115 */           if (InfoBaseManager.GKTYGS_MODEL_ENAME.equals(model_name)) {
/* 116 */             item_name = "gk_content";
/*     */           }
/* 118 */           String content = BeanUtils.getProperty(ob, item_name);
/*     */ 
/* 120 */           String page_count = AutoPagerHandl.splitContent(content, 0, Integer.parseInt(tupage_num));
/* 121 */           BeanUtils.setProperty(ob, "page_count", page_count);
/*     */         }
/*     */       }
/*     */       catch (IllegalAccessException e) {
/* 125 */         e.printStackTrace();
/*     */       }
/*     */       catch (InvocationTargetException e) {
/* 128 */         e.printStackTrace();
/*     */       }
/*     */       catch (NoSuchMethodException e) {
/* 131 */         e.printStackTrace();
/*     */       }
/*     */   }
/*     */ 
/*     */   public static Object select(String infoId, String siteId, String model_name)
/*     */   {
/*     */     try
/*     */     {
/* 147 */       Map mp = ModelConfig.getModelMap(model_name);
/* 148 */       String SelectSQL = "";
/* 149 */       if (mp != null) {
/* 150 */         SelectSQL = (String)mp.get("SelectSQL");
/* 151 */         return ModelUtilDAO.selectModel(infoId, siteId, SelectSQL);
/*     */       }
/* 153 */       System.out.println("ModelUtil.java----读取自定义表的内容----");
				InfoBean infoBean = InfoCustomService.getArticle(infoId);
				InfoBean ifb = InfoBaseManager.getInfoById(infoId);
				if (ifb != null)
			      {
			        if (ifb.getFrom_id() != 0)
			        {
			        	infoId = ifb.getFrom_id()+"";
			            infoBean = InfoCustomService.getArticle(infoId);
			        }
			      }
				Map mapInfo = BeanToMapUtil.convertBean(infoBean);
				
				String model_id = String.valueOf(infoBean.getModel_id());
				Map mapCustom = InfoCustomService.getCustomInfoMap(model_id, infoId);
				
				if (infoBean.getApp_id().equals("zwgk")) {
				  Map gkInfo = InfoCustomService.getGKInfo(infoId);
				  mapCustom.putAll(gkInfo);
				}
				
				if ((mapCustom != null) && (mapCustom.keySet().size() > 0)) {
				  mapCustom.putAll(mapInfo);
				  return MapManager.mapKeyValueToLow(mapCustom);
				}
				
/* 174 */       return null;
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 178 */       e.printStackTrace();
/* 179 */     }return null;
/*     */   }
			
			/*此方法是用在自定义模型的内容获取和推送上面，解决报错问题，所以单独写一个方法*/
			public static Object select2(String infoId, String siteId, String model_name)
/*     */   {
/*     */     try
/*     */     {
/* 147 */       Map mp = ModelConfig.getModelMap(model_name);
/* 148 */       String SelectSQL = "";
/* 149 */       if (mp != null) {
/* 150 */         SelectSQL = (String)mp.get("SelectSQL");
/* 151 */         return ModelUtilDAO.selectModel(infoId, siteId, SelectSQL);
/*     */       }
/* 153 */       System.out.println("ModelUtil.java----读取自定义表的内容----");
				InfoBean ifb = InfoBaseManager.getInfoById(infoId);
				if (ifb != null)
				{
				    InfoBean infoBean = InfoCustomService.getArticle(infoId);
				    Map mapInfo = BeanToMapUtil.convertBean(infoBean);
				
				    String model_id = String.valueOf(infoBean.getModel_id());
				    Map mapCustom = InfoCustomService.getCustomInfoMap(model_id, infoId);
				
				    if (infoBean.getApp_id().equals("zwgk")) {
				      Map gkInfo = InfoCustomService.getGKInfo(infoId);
				      mapCustom.putAll(gkInfo);
				    }
				
				    if ((mapCustom != null) && (mapCustom.keySet().size() > 0))
				    {
				      mapCustom.putAll(mapInfo);
				      return BeanToMapUtil.convertMap(InfoBean.class, MapManager.mapKeyValueToLow(mapInfo));
				    }
/*     */     	}
				return null;
			}
/*     */     catch (Exception e)
/*     */     {
/* 178 */       e.printStackTrace();
/* 179 */     }return null;
/*     */   }
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/* 188 */     PicBean pb = (PicBean)select("1150", "11111ddd", "pic");
/* 189 */     System.out.println(pb.getPic_content() + "----");
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.wcm.services.cms.info.ModelUtil
 * JD-Core Version:    0.6.2
 */